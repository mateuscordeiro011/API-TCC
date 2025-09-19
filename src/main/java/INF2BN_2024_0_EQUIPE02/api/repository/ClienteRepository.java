package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email OR c.cpf = :cpf")
    Optional<Cliente> findByEmailOrCpf(@Param("email") String email, @Param("cpf") String cpf);

    // ✅ Corrigido: passando null para ids_pedidos
    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO(c.id, c.nome, c.email, c.cpf, c.senha, c.foto, c.idEndereco, null) FROM Cliente c")
    List<ClienteDTO> findAllBasic();

    // ✅ Corrigido: passando null para ids_pedidos
    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO(c.id, c.nome, c.email, c.cpf, c.senha, c.foto, c.idEndereco, null) FROM Cliente c WHERE c.id = :id")
    Optional<ClienteDTO> findBasicById(@Param("id") Long id);
}