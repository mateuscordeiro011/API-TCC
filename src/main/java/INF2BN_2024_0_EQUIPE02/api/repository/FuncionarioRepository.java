package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        Optional<Funcionario> findByCpf(String cpf);

        @Query("SELECT f FROM Funcionario f WHERE f.email = :email OR f.cpf = :cpf")
        Optional<Funcionario> findByEmailOrCpf(@Param("email") String email, @Param("cpf") String cpf);

        // ✅ CORRIGIDO: f.endereco.id_endereco em vez de f.idEndereco
        @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO(" +
                "f.id, f.nome, f.cargo, f.email, CAST(f.salario AS float), " +
                "f.endereco.id_endereco, f.foto) " +
                "FROM Funcionario f")
        List<FuncionarioDTO> findAllBasic();

        // ✅ CORRIGIDO: f.endereco.id_endereco em vez de f.idEndereco
        @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO(" +
                "f.id, f.nome, f.cargo, f.email, CAST(f.salario AS float), " +
                "f.endereco.id_endereco, f.foto) " +
                "FROM Funcionario f WHERE f.id = :id")
        Optional<FuncionarioDTO> findBasicById(@Param("id") Long id);

        // ✅ CORRIGIDO: f.endereco.id_endereco em vez de f.idEndereco
        @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO(" +
                "f.id, f.nome, f.cargo, f.email, f.cpf, CAST(f.salario AS float), " +
                "f.endereco.id_endereco, f.foto) " +
                "FROM Funcionario f WHERE f.id = :id")
        Optional<FuncionarioDTO> findCompleteById(@Param("id") Long id);
}