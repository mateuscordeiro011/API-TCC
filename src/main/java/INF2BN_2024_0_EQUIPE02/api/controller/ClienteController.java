package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.dto.AtualizarPerfilDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.PerfilClienteDTO;
import INF2BN_2024_0_EQUIPE02.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;
    @Autowired
    private View error;


    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> get(@PathVariable("id") Long id) {
        return service.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> getPerfilByEmail(@RequestParam(required = false) String email) {
        // 1. Verifica se o parâmetro 'email' foi fornecido
        if (email == null || email.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", "O parâmetro 'email' é obrigatório.");
            return ResponseEntity.badRequest().body(error);
        }

        String emailLimpo = email.trim();

        // 2. Validação de formato de e-mail (regex simples)
        if (!isValidEmail(emailLimpo)) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", "Formato de e-mail inválido.");
            return ResponseEntity.badRequest().body(error);
        }

        // 3. Busca o cliente
        Optional<Map<String, Object>> perfilOpt = service.getPerfilClienteByEmail(emailLimpo);

        if (perfilOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", "Cliente não encontrado com este e-mail.");
            return ResponseEntity.status(404).body(error);
        }

        // 4. Retorna o perfil com sucesso
        return ResponseEntity.ok(perfilOpt.get());
    }

    // Método auxiliar para validar e-mail
    private boolean isValidEmail(String email) {
        if (email == null || email.length() > 254) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }


    @PostMapping("/perfil/foto")
    public ResponseEntity<?> atualizarFotoPerfil(@RequestParam("foto") MultipartFile foto,
                                                 @RequestParam("email") String email) {
        try {
            boolean atualizado = service.atualizarFotoPorEmail(email, foto);
            if (atualizado) {
                return ResponseEntity.ok(Map.of("mensagem", "Foto atualizada com sucesso"));
            }
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao atualizar foto: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> incluir(@RequestBody Cliente cliente) {
        Cliente novo = service.incluir(cliente);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = service.atualizar(id, cliente);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/perfil")
    public ResponseEntity<?> atualizarPerfil(@RequestBody Map<String, String> dados, Errors errors) {
        System.out.println("Dados recebidos: " + dados);
        if (dados == null || dados.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Dados inválidos"));
        }

        String email = dados.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Email é obrigatório"));
        }

        // Cria DTO temporário
        AtualizarPerfilDTO dto = new AtualizarPerfilDTO();
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
            return ResponseEntity.badRequest().body(errors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao atualizar perfil: " + e.getMessage()));
        }
    }
}