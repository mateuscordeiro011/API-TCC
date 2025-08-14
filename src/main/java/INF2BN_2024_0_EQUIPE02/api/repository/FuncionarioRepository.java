package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmail(String email);

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO(" +
            "f.idFuncionario, f.nome, f.cargo, f.email, f.salario, f.endereco.id_endereco, f.foto) " +
            "FROM Funcionario f")
    List<FuncionarioDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO(" +
            "f.idFuncionario, f.nome, f.cargo, f.email, f.salario, f.endereco.id_endereco, f.foto) " +
            "FROM Funcionario f WHERE f.idFuncionario = :id")
    Optional<FuncionarioDTO> findBasicById(@Param("id") Long id);
}