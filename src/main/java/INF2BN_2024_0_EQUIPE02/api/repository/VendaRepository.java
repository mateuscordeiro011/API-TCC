package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.VendaDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.VendaDTO(v.Id_Venda, v.Data_Venda, v.Valor_total, v.pedido.id_pedido, v.cliente.id_cliente) FROM Venda v")
    List<VendaDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.VendaDTO(v.Id_Venda, v.Data_Venda, v.Valor_total, v.pedido.id_pedido, v.cliente.id_cliente) FROM Venda v WHERE v.Id_Venda = :id")
    Optional<VendaDTO> findBasicById(Long id);
}