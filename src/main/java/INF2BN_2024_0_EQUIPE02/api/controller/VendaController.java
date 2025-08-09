package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.VendaDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Venda;
import INF2BN_2024_0_EQUIPE02.api.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        return ResponseEntity.ok(service.listarVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> get(@PathVariable("id") Long id) {
        return service.getVendaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> incluir(@RequestBody Venda venda) {
        Venda novaVenda = service.incluir(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        Venda vendaAtualizada = service.atualizar(id, venda);
        if (vendaAtualizada != null) {
            return ResponseEntity.ok(vendaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }
}