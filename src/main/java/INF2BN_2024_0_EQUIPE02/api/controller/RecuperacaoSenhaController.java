package INF2BN_2024_0_EQUIPE02.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import INF2BN_2024_0_EQUIPE02.api.service.RecuperacaoSenhaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api-salsi/clientes")
public class RecuperacaoSenhaController {

    @Autowired
    private RecuperacaoSenhaService recuperacaoSenhaService;

    /**
     * Etapa 1: Gera token de recuperação
     */
    @GetMapping("/esqueci-senha")
    public ResponseEntity<?> gerarToken(@RequestParam String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Identificador (email/CPF) é obrigatório"));
        }

        return recuperacaoSenhaService.gerarTokenRecuperacao(identificador)
                .map(token -> ResponseEntity.ok(Map.of("token", token)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensagem", "Usuário não encontrado")));
    }


    @PostMapping("/redefinir-senha")
    public ResponseEntity<?> redefinirSenha(
            @RequestParam String token,
            @RequestParam String novaSenha) {

        if (token == null || token.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Token é obrigatório"));
        }
        if (novaSenha == null || novaSenha.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Senha deve ter pelo menos 6 caracteres"));
        }

        boolean sucesso = recuperacaoSenhaService.redefinirSenha(token, novaSenha);

        if (sucesso) {
            return ResponseEntity.ok(Map.of("mensagem", "Senha redefinida com sucesso"));
        } else {
            return ResponseEntity.status(400).body(Map.of("mensagem", "Token inválido, expirado ou já usado"));
        }
    }
}