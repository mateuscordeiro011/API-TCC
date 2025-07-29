package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO(i.id_pedido, i.Produto.id_produto, i.quantidade, i.precoUnitario) FROM ItemPedido i")
    List<ItemPedidoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO(i.id_pedido, i.Produto.id_produto, i.quantidade, i.precoUnitario) FROM ItemPedido i WHERE i.id_pedido = :id")
    Optional<ItemPedidoDTO> findBasicById(Long id);
}