package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.ItemVendaDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemVendaDTO(iv.id_ItemVenda, iv.Produto.id_produto, iv.quantidade, iv.precoUnitario) FROM ItemVenda iv")
    List<ItemVendaDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ItemVendaDTO(iv.id_ItemVenda, iv.Produto.id_produto, iv.quantidade, iv.precoUnitario) FROM ItemVenda iv WHERE iv.id_ItemVenda = :id")
    Optional<ItemVendaDTO> findBasicById(Long id);
}