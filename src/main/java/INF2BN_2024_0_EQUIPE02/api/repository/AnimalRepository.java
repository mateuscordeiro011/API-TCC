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

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO(a.id_animal, a.nome, a.especie, a.raca, a.data_nascimento, a.sexo, a.peso, a.foto, a.cliente.id_cliente) FROM Animal a")
    List<AnimalDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO(" +
       "a.id_animal, a.nome, a.especie, a.raca, " +
       "a.data_nascimento, a.sexo, a.peso, a.foto, " +
       "a.cliente.id_cliente) " +
       "FROM Animal a WHERE a.id_animal = :id")
Optional<AnimalDTO> findBasicById(@Param("id") Long id);
}