package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO;
import INF2BN_2024_0_EQUIPE02.api.infra.security.JwtUtil;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import INF2BN_2024_0_EQUIPE02.api.service.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi") // Raiz da API
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // --- Endpoints Gerais para Endereços (se ainda forem necessários) ---

    @GetMapping("/enderecos")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        return ResponseEntity.ok(service.listarEnderecos());
    }

    @GetMapping("/enderecos/{id}")
    public ResponseEntity<EnderecoDTO> get(@PathVariable("id") Long id) {
        return service.getEnderecoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Este endpoint agora é mais específico para criação geral, se necessário.
    // O endpoint principal para criação associada ao usuário é /endereco
    @PostMapping("/enderecos")
    public ResponseEntity<Endereco> incluir(@RequestBody Endereco endereco) {
        Endereco novo = service.incluir(endereco);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco atualizado = service.atualizar(id, endereco);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/enderecos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --- Novo Endpoint: Cadastrar Endereço e Associar ao Usuário Logado ---

    /**
     * Endpoint para cadastrar um endereço e associá-lo ao usuário logado (Cliente ou Funcionario).
     * O usuário é identificado pelo token JWT.
     */
    @PostMapping("/endereco") // Endpoint específico para o usuário logado
    public ResponseEntity<?> cadastrarEnderecoUsuarioLogado(
            @RequestBody Endereco endereco,
            HttpServletRequest request) {

        try {
            // 1. Extrair o token do cabeçalho Authorization
            String token = extractTokenFromRequest(request);
            if (token == null) {
                return ResponseEntity.status(401).body("Token não fornecido.");
            }

            // 2. Validar o token e extrair o email
            String email;
            try {
                if (!jwtUtil.validateToken(token, jwtUtil.extractEmail(token))) {
                    return ResponseEntity.status(401).body("Token inválido ou expirado.");
                }
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                return ResponseEntity.status(401).body("Token inválido: " + e.getMessage());
            }

            // 3. Buscar o usuário pelo email
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Usuário não encontrado com o email: " + email);
            }
            Usuario usuario = usuarioOpt.get();

            // 4. Salvar o novo endereço
            Endereco novoEndereco = service.incluir(endereco);
            Long idNovoEndereco = novoEndereco.getId_endereco(); // getId() retorna Id_endereco

            // 5. Associar o endereço ao Cliente ou Funcionario
            if ("CLIENTE".equals(usuario.getTipo()) && usuario.getIdCliente() != null) {
                // Busca e atualiza o Cliente
                clienteRepository.findById(usuario.getIdCliente())
                        .ifPresentOrElse(cliente -> {
                            cliente.setIdEndereco(idNovoEndereco);
                            clienteRepository.save(cliente);
                        }, () -> {
                            // Se o cliente não for encontrado, faz rollback
                            service.deletar(idNovoEndereco);
                            throw new RuntimeException("Cliente não encontrado com ID: " + usuario.getIdCliente());
                        });

            } else if ("FUNCIONARIO".equals(usuario.getTipo()) && usuario.getIdFuncionario() != null) {
                //Busca e atualiza o Funcionario
                funcionarioRepository.findById(usuario.getIdFuncionario())
                        .ifPresentOrElse(funcionario -> {
                            funcionario.setEndereco(novoEndereco);
                            funcionarioRepository.save(funcionario);
                        }, () -> {
                            // Se o funcionário não for encontrado, faz rollback
                            service.deletar(idNovoEndereco);
                            throw new RuntimeException("Funcionário não encontrado com ID: " + usuario.getIdFuncionario());
                        });
            } else {
                // Tipo inválido ou ID não encontrado, faz rollback
                service.deletar(idNovoEndereco);
                return ResponseEntity.badRequest().body("Tipo de usuário inválido ou ID não encontrado.");
            }

            // 6. Retornar sucesso com o DTO do endereço criado
            // Certifique-se de que o construtor do EnderecoDTO corresponde aos campos
            EnderecoDTO enderecoDTO = new EnderecoDTO(
                    novoEndereco.getId_endereco(), // id_endereco
                    novoEndereco.getCep(),
                    novoEndereco.getRua(),
                    novoEndereco.getBairro(),
                    novoEndereco.getCidade(),
                    novoEndereco.getEstado(),
                    novoEndereco.getComplemento(),
                    novoEndereco.getNumero()
            );

            return ResponseEntity.ok(enderecoDTO);

        } catch (Exception e) {
            // Log do erro para depuração (opcional, use logger em produção)
            // e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao cadastrar endereço: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para extrair o token Bearer do cabeçalho Authorization.
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer "
        }
        return null;
    }
}