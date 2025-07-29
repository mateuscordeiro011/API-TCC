package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;
import INF2BN_2024_0_EQUIPE02.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-salsi/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    // Lista todos os pedidos como DTO
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Busca um pedido por ID e retorna como DTO
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> get(@PathVariable Long id) {
        Optional<PedidoDTO> pedido = service.getPedidoById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Inclui um novo pedido (aceita Pedido, converte automaticamente)
    @PostMapping
    public ResponseEntity<Pedido> incluir(@RequestBody Pedido pedido) {
        Pedido novoPedido = service.incluir(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    // Atualiza um pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoAtualizado = service.atualizar(id, pedido);
        if (pedidoAtualizado != null) {
            return ResponseEntity.ok(pedidoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
}