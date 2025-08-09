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
    
    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO(" +
       "a.id_adocao, " +
       "a.animal.id_animal, " +
       "a.cliente.id_cliente, " +
       "a.observacoes, " +
       "a.data_adocao, " +
       "a.status" +
       ") FROM Adocao a " +
       "JOIN a.animal " +
       "JOIN a.cliente")
List<AdocaoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO(" +
       "a.id_adocao, " +
       "a.animal.id_animal, " +
       "a.cliente.id_cliente, " +
       "a.observacoes, " +          
       "a.data_adocao, " +        
       "a.status" +               
       ") FROM Adocao a " +
       "JOIN a.animal " +
       "JOIN a.cliente " +
       "WHERE a.id_adocao = :id")
Optional<AdocaoDTO> findBasicById(@Param("id") Long id);
}
