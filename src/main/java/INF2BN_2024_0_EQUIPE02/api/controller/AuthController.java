package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.dto.*;
import INF2BN_2024_0_EQUIPE02.api.infra.security.JwtUtil;
import INF2BN_2024_0_EQUIPE02.api.repository.UsuarioRepository;
import INF2BN_2024_0_EQUIPE02.api.service.AuthService;
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
            System.out.println("Login bem-sucedido para: " + authRequest.getEmail());
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

            if (result.startsWith("Cliente registrado com sucesso")) {
                String[] parts = result.split("\\|");
                if (parts.length > 1) {
                    Long clienteId = Long.parseLong(parts[1]);
                    Optional<Usuario> usuarioOpt = repository.findByEmail(registerRequest.getEmail());
                    if (usuarioOpt.isPresent()) {
                        Usuario usuario = usuarioOpt.get(); // ✅ usuario é Usuario (não Optional)
                        String token = jwtUtil.generateToken(
                                usuario.getEmail(),
                                usuario.getTipo(),      // ✅ SEM .get() extra!
                                clienteId
                        );
                        AuthResponse response = new AuthResponse();
                        response.setToken(token);
                        response.setTipo(usuario.getTipo()); // ✅
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
    public ResponseEntity<SessionResponse> checkSession(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);

            if (token == null) {
                System.out.println("Token não encontrado na requisição");
                return ResponseEntity.ok(new SessionResponse(false, null, null, null));
            }

            String email = jwtUtil.extractEmail(token);
            System.out.println("Email extraído do token: " + email);

            if (email != null && jwtUtil.validateToken(token, email)) {
                Optional<Usuario> usuarioOpt = repository.findByEmail(email);
                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get(); // ✅ Aqui você tem o objeto Usuario

                    // ✅ USE DIRETAMENTE: usuario.getTipo(), NÃO usuario.get().getTipo()
                    System.out.println("Token válido para: " + usuario.getEmail());
                    return ResponseEntity.ok(new SessionResponse(
                            true,
                            usuario.getTipo(),
                            usuario.getEmail(),
                            usuario.getId()
                    ));
                }
            }

            System.out.println("Token inválido ou usuário não encontrado");
            return ResponseEntity.ok(new SessionResponse(false, null, null, null));

        } catch (Exception e) {
            System.out.println("Erro ao verificar sessão: " + e.getMessage());
            return ResponseEntity.ok(new SessionResponse(false, null, null, null));
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