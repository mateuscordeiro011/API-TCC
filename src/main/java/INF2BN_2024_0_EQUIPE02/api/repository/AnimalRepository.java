package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO(a.id, a.nome, a.especie, a.raca, a.dataNascimento, a.sexo, CAST(a.peso AS float), a.foto, a.idCliente) FROM Animal a")
    List<AnimalDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO(a.id, a.nome, a.especie, a.raca, a.dataNascimento, a.sexo, CAST(a.peso AS float), a.foto, a.idCliente) FROM Animal a WHERE a.id = :id")
    Optional<AnimalDTO> findBasicById(@Param("id") Long id);
}