package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
import INF2BN_2024_0_EQUIPE02.api.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-salsi/itempedidos")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService service;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> listarItemPedidos() {
        List<ItemPedidoDTO> itens = service.listarItemPedidos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> get(@PathVariable("id") Long id) {
        return service.getItemPedidoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemPedido> incluir(@RequestBody ItemPedido itemPedido) {
        ItemPedido novo = service.incluir(itemPedido);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
        ItemPedido atualizado = service.atualizar(id, itemPedido);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }
}