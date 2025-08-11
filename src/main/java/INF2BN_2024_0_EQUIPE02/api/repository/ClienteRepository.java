package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO(c.id_cliente, c.nome, c.email, c.cpf, c.senha, c.foto, c.endereco.id_endereco, null) FROM Cliente c")
    List<ClienteDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO(c.id_cliente, c.nome, c.email, c.cpf, c.senha, c.foto, c.endereco.id_endereco, null) FROM Cliente c WHERE c.id_cliente = :id")
    Optional<ClienteDTO> findBasicById(Long id);
}