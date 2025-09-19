package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.dto.AuthRequest;
import INF2BN_2024_0_EQUIPE02.api.dto.AuthResponse;
import INF2BN_2024_0_EQUIPE02.api.dto.RegisterRequest;
import INF2BN_2024_0_EQUIPE02.api.infra.security.JwtUtil;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service // ✅ Certifique-se de ter apenas esta annotation
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Removido AuthenticationManager

    public AuthResponse login(AuthRequest authRequest) {
        System.out.println("=== TENTANDO LOGIN ===");
        System.out.println("Email: " + authRequest.getEmail());

        // Buscar usuário pelo email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(authRequest.getEmail());

        if (usuarioOpt.isEmpty()) {
            System.out.println("USUÁRIO NÃO ENCONTRADO!");
            throw new RuntimeException("Credenciais inválidas");
        }

        Usuario usuario = usuarioOpt.get();
        System.out.println("Usuário encontrado: " + usuario.getEmail() + ", Tipo: " + usuario.getTipo());

        // Verificar senha manualmente
        if (!passwordEncoder.matches(authRequest.getSenha(), usuario.getSenha())) {
            System.out.println("SENHA INCORRETA!");
            throw new RuntimeException("Credenciais inválidas");
        }

        System.out.println("LOGIN BEM SUCEDIDO!");

        // Gerar token JWT
        Long idVinculado = null;
        if ("CLIENTE".equals(usuario.getTipo()) && usuario.getIdCliente() != null) {
            idVinculado = usuario.getIdCliente();
        } else if ("FUNCIONARIO".equals(usuario.getTipo()) && usuario.getIdFuncionario() != null) {
            idVinculado = usuario.getIdFuncionario();
        }

        String token = jwtUtil.generateToken(
                usuario.getEmail(),
                usuario.getTipo(),
                idVinculado
        );

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipo(usuario.getTipo());
        response.setId(idVinculado);

        return response;
    }

    public String register(RegisterRequest registerRequest) {
        // Verificar se email já existe
        if (usuarioRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Verificar se CPF já existe (para clientes)
        if ("CLIENTE".equalsIgnoreCase(registerRequest.getTipo())) {
            if (clienteRepository.findByCpf(registerRequest.getCpf()).isPresent()) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }

        // Verificar se CPF já existe (para funcionários)
        if ("FUNCIONARIO".equalsIgnoreCase(registerRequest.getTipo())) {
            if (funcionarioRepository.findByCpf(registerRequest.getCpf()).isPresent()) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }

        if ("CLIENTE".equalsIgnoreCase(registerRequest.getTipo())) {
            return registerCliente(registerRequest);
        } else if ("FUNCIONARIO".equalsIgnoreCase(registerRequest.getTipo())) {
            return registerFuncionario(registerRequest);
        } else {
            throw new RuntimeException("Tipo de usuário inválido");
        }
    }

    private String registerCliente(RegisterRequest request) {
        // Criar cliente
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setSenha(passwordEncoder.encode(request.getSenha()));
        cliente.setCpf(request.getCpf());

        cliente = clienteRepository.save(cliente);

        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setTipo("CLIENTE");
        usuario.setIdCliente(cliente.getId());

        usuarioRepository.save(usuario);

        return "Cliente registrado com sucesso|" + cliente.getId();
    }

    private String registerFuncionario(RegisterRequest request) {
        // Criar funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.getNome());
        funcionario.setEmail(request.getEmail());
        funcionario.setSenha(passwordEncoder.encode(request.getSenha()));
        funcionario.setCpf(request.getCpf());
        funcionario.setCargo(request.getCargo());

        // Converter salario para BigDecimal se necessário
        if (request.getSalario() != null) {
            funcionario.setSalario(new BigDecimal(request.getSalario().toString()));
        }

        funcionario = funcionarioRepository.save(funcionario);

        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setTipo("FUNCIONARIO");
        usuario.setIdFuncionario(funcionario.getId());

        usuarioRepository.save(usuario);

        return "funcionario registrado com sucesso|" + funcionario.getId();
    }
}