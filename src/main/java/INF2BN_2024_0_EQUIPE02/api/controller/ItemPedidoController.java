package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
import INF2BN_2024_0_EQUIPE02.api.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/itempedidos")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService service;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> listarItemPedidos() {
        List<ItemPedidoDTO> itens = service.listarItemPedidos();
        return ResponseEntity.ok(itens);
    }

    // ✅ ALTERADO: dois parâmetros na URL
    @GetMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<ItemPedidoDTO> get(
            @PathVariable("idPedido") Long idPedido,
            @PathVariable("idProduto") Long idProduto) {
        return service.getItemPedido(idPedido, idProduto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemPedido> incluir(@RequestBody ItemPedido itemPedido) {
        ItemPedido novo = service.incluir(itemPedido);
        return ResponseEntity.status(201).body(novo);
    }

    // ✅ Também atualize o PUT se necessário
    @PutMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<ItemPedido> atualizar(
            @PathVariable("idPedido") Long idPedido,
            @PathVariable("idProduto") Long idProduto,
            @RequestBody ItemPedido itemAtualizado) {

        // Ajuste a lógica conforme seu serviço
        ItemPedido atualizado = service.atualizarQuantidade(
                idPedido,
                idProduto,
                itemAtualizado.getQuantidade()
        );
        return ResponseEntity.ok(atualizado);
    }
}