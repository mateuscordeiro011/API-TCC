package INF2BN_2024_0_EQUIPE02.api.service;

import java.util.Optional;

import INF2BN_2024_0_EQUIPE02.api.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.dto.UsuarioDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UsuarioDTO> autenticar(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = usuarioOpt.get();

        // Verifica senha com BCrypt
        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            return Optional.empty();
        }

        // Monta o DTO com dados do usuário e vinculado
        Long idVinculado = null;
        String nome = null;

        if (usuario.isCliente() && usuario.getIdCliente() != null) {
            Optional<Cliente> clienteOpt = clienteRepository.findById(usuario.getIdCliente());
            if (clienteOpt.isPresent()) {
                Cliente cliente = clienteOpt.get();
                idVinculado = cliente.getId();
                nome = cliente.getNome();
            }
        } else if (usuario.isFuncionario() && usuario.getIdFuncionario() != null) {
            Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(usuario.getIdFuncionario());
            if (funcionarioOpt.isPresent()) {
                Funcionario funcionario = funcionarioOpt.get();
                idVinculado = funcionario.getId();
                nome = funcionario.getNome();
            }
        }

        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getTipo(),
                idVinculado,
                nome
        );

        return Optional.of(dto);
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
}