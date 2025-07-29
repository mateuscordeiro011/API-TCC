package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.ItemVendaDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemVenda;
import INF2BN_2024_0_EQUIPE02.api.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-salsi/itemvenda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService service;

    // Lista todos os itens de venda como DTO
    @GetMapping
    public ResponseEntity<List<ItemVendaDTO>> listarItemVenda() {
        List<ItemVendaDTO> itens = service.listarItemVenda();
        return ResponseEntity.ok(itens);
    }

    // Busca um item de venda por ID e retorna como DTO
    @GetMapping("/{id}")
    public ResponseEntity<ItemVendaDTO> getItemVenda(@PathVariable("id") Long id) {
        return service.getItemVendaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Inclui um novo item de venda
    @PostMapping
    public ResponseEntity<ItemVenda> incluir(@RequestBody ItemVenda itemVenda) {
        ItemVenda novoItemVenda = service.incluir(itemVenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItemVenda);
    }

    // Atualiza um item de venda existente
    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> atualizar(@PathVariable Long id, @RequestBody ItemVenda itemVenda) {
        ItemVenda itemAtualizado = service.atualizar(id, itemVenda);
        if (itemAtualizado != null) {
            return ResponseEntity.ok(itemAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
}