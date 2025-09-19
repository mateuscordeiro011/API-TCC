package INF2BN_2024_0_EQUIPE02.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long>{

    // Buscar animais disponíveis para adoção
    @Query("SELECT d FROM Adocao d WHERE d.status = 'Disponível'")
    List<Adocao> findAnimaisDisponiveis();

    // Buscar doações de um cliente específico (animais que ele cadastrou)
    @Query("SELECT d FROM Adocao d WHERE d.id_cliente_doador = :idCliente")
    List<Adocao> findByIdClienteDoador(@Param("idCliente") Long idCliente);
}
