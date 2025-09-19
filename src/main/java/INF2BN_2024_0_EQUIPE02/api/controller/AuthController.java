package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.dto.*;
import INF2BN_2024_0_EQUIPE02.api.infra.security.JwtUtil;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import INF2BN_2024_0_EQUIPE02.api.service.AuthService;
import INF2BN_2024_0_EQUIPE02.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api-salsi/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        System.out.println("=== TENTANDO LOGIN ===");
        System.out.println("Email recebido: " + authRequest.getEmail());
        System.out.println("Senha recebida: " + authRequest.getSenha());

        try {
            AuthResponse response = authService.login(authRequest);
            System.out.println("Login bem sucedido para: " + authRequest.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Login falhou para: " + authRequest.getEmail() + " - Erro: " + e.getMessage());
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            String result = authService.register(registerRequest);

            // ✅ Verificar se é um registro bem-sucedido e fazer login automático
            if (result.startsWith("Cliente registrado com sucesso")) {
                // Extrair o ID do cliente
                String[] parts = result.split("\\|");
                if (parts.length > 1) {
                    Long clienteId = Long.parseLong(parts[1]);

                    // Buscar o usuário recém-criado
                    Optional<Usuario> usuarioOpt = repository.findByEmail(registerRequest.getEmail());
                    if (usuarioOpt.isPresent()) {
                        Usuario usuario = usuarioOpt.get();

                        // Gerar token
                        String token = jwtUtil.generateToken(
                                usuario.getEmail(),
                                usuario.getTipo(),
                                clienteId
                        );

                        // Criar resposta de login
                        AuthResponse response = new AuthResponse();
                        response.setToken(token);
                        response.setTipo(usuario.getTipo());
                        response.setId(clienteId);

                        return ResponseEntity.ok(response);
                    }
                }
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> checkSession(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            if (token == null) {
                System.out.println("Token não encontrado na requisição");
                return ResponseEntity.ok(new SessionResponse(false, null, null));
            }

            String email = jwtUtil.extractEmail(token);
            System.out.println("Email extraído do token: " + email);

            if (email != null && jwtUtil.validateToken(token, email)) {
                // Aqui você pode buscar dados reais do usuário
                System.out.println("Token válido para: " + email);
                return ResponseEntity.ok(new SessionResponse(true, "CLIENTE", "Nome do Usuário"));
            }

            System.out.println("Token inválido");
            return ResponseEntity.ok(new SessionResponse(false, null, null));
        } catch (Exception e) {
            System.out.println("Erro ao verificar sessão: " + e.getMessage());
            return ResponseEntity.ok(new SessionResponse(false, null, null));
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}