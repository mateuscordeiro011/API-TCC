package INF2BN_2024_0_EQUIPE02.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;
import INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO;
import INF2BN_2024_0_EQUIPE02.api.service.AdocaoService;

@RestController
@RequestMapping("/api-salsi/doacoes") // Endpoint para doações
@CrossOrigin(origins = "http://localhost:5173")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    // Listar animais disponíveis para adoção
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Adocao>> listarAnimaisDisponiveis() {
        List<Adocao> animais = service.listarAnimaisDisponiveis();
        return ResponseEntity.ok(animais);
    }

    // Listar doações de um cliente (animais que ele cadastrou)
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Adocao>> listarDoacoesPorCliente(@PathVariable Long idCliente) {
        List<Adocao> doacoes = service.listarDoacoesPorCliente(idCliente);
        return ResponseEntity.ok(doacoes);
    }

    // Buscar uma doação específica
    @GetMapping("/{id}")
    public ResponseEntity<Adocao> getDoacao(@PathVariable Long id) {
        Optional<Adocao> doacaoOpt = service.getDoacaoById(id);
        return doacaoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cadastrar um novo animal para doação
    @PostMapping
    public ResponseEntity<Adocao> incluir(@RequestBody Adocao doacao) {
        // Assume que o ID do cliente doador vem no próprio objeto doacao
        Adocao novaDoacao = service.incluir(doacao);
        return ResponseEntity.status(201).body(novaDoacao);
    }

    // Atualizar uma doação
    @PutMapping("/{id}")
    public ResponseEntity<Adocao> atualizar(@PathVariable Long id, @RequestBody Adocao doacao) {
        Adocao doacaoAtualizada = service.atualizar(id, doacao);
        if (doacaoAtualizada != null) {
            return ResponseEntity.ok(doacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar uma doação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}