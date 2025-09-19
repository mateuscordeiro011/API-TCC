package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO(p.id_pedido, p.dataPedido, p.status, p.valorTotal, p.cliente.id) FROM Pedido p")
    List<PedidoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO(p.id_pedido, p.dataPedido, p.status, p.valorTotal, p.cliente.id) FROM Pedido p WHERE p.id_pedido = :id")
    Optional<PedidoDTO> findBasicById(Long id);
}