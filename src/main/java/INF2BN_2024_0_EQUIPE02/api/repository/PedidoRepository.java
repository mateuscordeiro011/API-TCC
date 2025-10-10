package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // 🔹 1. Buscar CARRINHO (status = 'Carrinho') de um cliente específico
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId AND p.status = :status")
    Optional<Pedido> findByClienteIdAndStatus(@Param("clienteId") Long clienteId, @Param("status") String status);

    // 🔹 2. Listar todos os pedidos com status específico
    List<Pedido> findByStatus(String status);

    // 🔹 3. Buscar pedido por ID retornando DTO
    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO(" +
            "p.id_pedido, p.data_pedido, p.status, p.valor_total, p.cliente.id) " +
            "FROM Pedido p WHERE p.id_pedido = :id")
    Optional<PedidoDTO> findBasicById(@Param("id") Long id); // também corrigido para Long

    // 🔹 4. Listar todos os pedidos como DTO
    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO(" +
            "p.id_pedido, p.data_pedido, p.status, p.valor_total, p.cliente.id) " +
            "FROM Pedido p")
    List<PedidoDTO> findAllBasic();

    // 🔹 5. Listar pedidos concluídos de um cliente
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId AND p.status = 'Concluído'")
    List<Pedido> findPedidosConcluidosByClienteId(@Param("clienteId") Long clienteId); // e aqui também

}