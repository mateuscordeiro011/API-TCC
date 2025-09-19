package INF2BN_2024_0_EQUIPE02.api.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.domain.TokenRecuperacaoSenha;
import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.TokenRecuperacaoSenhaRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenRecuperacaoSenhaRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final SecureRandom random = new SecureRandom();

    private String gerarToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }
        return token.toString();
    }

    @Transactional
    public Optional<String> gerarTokenRecuperacao(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            return Optional.empty();
        }

        String identificadorLimpo = identificador.replaceAll("[^\\dA-Za-z@.]", "");

        // Buscar cliente por email ou CPF
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(identificadorLimpo);
        if (clienteOpt.isEmpty()) {
            clienteOpt = clienteRepository.findByCpf(identificadorLimpo);
        }

        // Buscar funcionário por email ou CPF
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByEmail(identificadorLimpo);
        if (funcionarioOpt.isEmpty()) {
            funcionarioOpt = funcionarioRepository.findByCpf(identificadorLimpo);
        }

        if (clienteOpt.isEmpty() && funcionarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Long idCliente = clienteOpt.map(Cliente::getId).orElse(null);
        Long idFuncionario = funcionarioOpt.map(Funcionario::getId).orElse(null);

        // Deletar tokens existentes (precisa implementar no repository)
        // Como não temos o método, vamos deletar manualmente
        if (idCliente != null) {
            tokenRepository.deleteByClienteId(idCliente);
        }
        if (idFuncionario != null) {
            tokenRepository.deleteByFuncionarioId(idFuncionario);
        }

        String token = gerarToken();

        TokenRecuperacaoSenha novoToken = new TokenRecuperacaoSenha();
        novoToken.setToken(token);
        novoToken.setIdCliente(idCliente);
        novoToken.setIdFuncionario(idFuncionario);
        novoToken.setExpiraEm(LocalDateTime.now().plusMinutes(15));
        novoToken.setUsado(false);

        tokenRepository.save(novoToken);

        return Optional.of(token);
    }

    @Transactional
    public boolean redefinirSenha(String token, String novaSenha) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        if (novaSenha == null || novaSenha.length() < 6) {
            return false;
        }

        Optional<TokenRecuperacaoSenha> tokenOpt = tokenRepository.findByToken(token);

        if (tokenOpt.isEmpty()) {
            return false;
        }

        TokenRecuperacaoSenha tokenEntity = tokenOpt.get();

        if (tokenEntity.isUsado() || LocalDateTime.now().isAfter(tokenEntity.getExpiraEm())) {
            return false;
        }

        // Buscar usuário pelo ID do cliente ou funcionário
        Optional<Usuario> usuarioOpt = Optional.empty();

        if (tokenEntity.getIdCliente() != null) {
            usuarioOpt = usuarioRepository.findByEmailAndTipo(
                    clienteRepository.findById(tokenEntity.getIdCliente())
                            .map(Cliente::getEmail)
                            .orElse(null),
                    "CLIENTE"
            );
        } else if (tokenEntity.getIdFuncionario() != null) {
            usuarioOpt = usuarioRepository.findByEmailAndTipo(
                    funcionarioRepository.findById(tokenEntity.getIdFuncionario())
                            .map(Funcionario::getEmail)
                            .orElse(null),
                    "FUNCIONARIO"
            );
        }

        if (usuarioOpt.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

        tokenEntity.setUsado(true);
        tokenRepository.save(tokenEntity);

        return true;
    }
}