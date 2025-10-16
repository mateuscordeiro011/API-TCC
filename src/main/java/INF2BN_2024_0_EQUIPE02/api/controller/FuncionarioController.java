package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.dto.AtualizarPerfilFuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios() {
        return ResponseEntity.ok(service.listarFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> get(@PathVariable("id") Long id) {
        return service.getFuncionarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> incluir(@RequestBody Funcionario funcionario) {
        Funcionario novo = service.incluir(funcionario);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario atualizado = service.atualizar(id, funcionario);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.length() > 254) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> getPerfilByEmail(@RequestParam(required = false) String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "O parâmetro 'email' é obrigatório."));
        }

        String emailLimpo = email.trim();
        if (!isValidEmail(emailLimpo)) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Formato de e-mail inválido."));
        }

        Optional<Map<String, Object>> perfilOpt = service.getPerfilFuncionarioByEmail(emailLimpo);
        if (perfilOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("erro", "Funcionário não encontrado com este e-mail."));
        }

        return ResponseEntity.ok(perfilOpt.get());
    }

    @PostMapping("/perfil/foto")
    public ResponseEntity<?> atualizarFotoPerfil(@RequestParam("foto") MultipartFile foto,
                                                 @RequestParam("email") String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("erro", "Email é obrigatório"));
            }
            boolean atualizado = service.atualizarFotoPorEmail(email.trim(), foto);
            if (atualizado) {
                return ResponseEntity.ok(Map.of("mensagem", "Foto atualizada com sucesso"));
            }
            return ResponseEntity.status(404).body(Map.of("erro", "Funcionário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao atualizar foto: " + e.getMessage()));
        }
    }

    @PostMapping("/perfil")
    public ResponseEntity<?> atualizarPerfil(@RequestBody Map<String, String> dados) {
        if (dados == null || dados.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Dados inválidos"));
        }

        String email = dados.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Email é obrigatório"));
        }

        AtualizarPerfilFuncionarioDTO dto = new AtualizarPerfilFuncionarioDTO();
        dto.setNome(dados.get("nome"));
        dto.setSenha(dados.get("senha"));
        dto.setCep(dados.get("cep"));
        dto.setRua(dados.get("rua"));
        dto.setNumero(dados.get("numero"));
        dto.setComplemento(dados.get("complemento"));
        dto.setBairro(dados.get("bairro"));
        dto.setCidade(dados.get("cidade"));
        dto.setEstado(dados.get("estado"));

        try {
            boolean atualizado = service.atualizarPerfilPorEmail(email.trim(), dto);
            if (atualizado) {
                return ResponseEntity.ok(Map.of("mensagem", "Perfil atualizado com sucesso"));
            }
            return ResponseEntity.badRequest().body(Map.of("erro", "Funcionário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao atualizar perfil: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}