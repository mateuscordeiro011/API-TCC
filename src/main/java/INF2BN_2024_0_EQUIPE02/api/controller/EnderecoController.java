package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO;
import INF2BN_2024_0_EQUIPE02.api.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-salsi/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        return ResponseEntity.ok(service.listarEnderecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> get(@PathVariable("id") Long id) {
        return service.getEnderecoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> incluir(@RequestBody Endereco endereco) {
        Endereco novo = service.incluir(endereco);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco atualizado = service.atualizar(id, endereco);
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
}