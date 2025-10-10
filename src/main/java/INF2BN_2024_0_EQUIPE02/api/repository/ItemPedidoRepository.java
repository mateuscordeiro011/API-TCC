package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedidoId;
import INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoId> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO(" +
            "ip.pedido.id_pedido, ip.id, ip.quantidade, ip.precoUnitario) " +
            "FROM ItemPedido ip")
    List<ItemPedidoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO(" +
            "ip.pedido.id_pedido, ip.id, ip.quantidade, ip.precoUnitario) " +
            "FROM ItemPedido ip " +
            "WHERE ip.pedido.id_pedido = :idPedido AND ip.id = :idProduto")
    Optional<ItemPedidoDTO> findBasicById(
            @Param("idPedido") Long idPedido,
            @Param("idProduto") Long idProduto
    );
}