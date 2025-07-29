package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO(e.id_endereco, e.cep, e.logradouro, e.bairro, e.cidade, e.estado) FROM Endereco e")
    List<EnderecoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO(e.id_endereco, e.cep, e.logradouro, e.bairro, e.cidade, e.estado) FROM Endereco e WHERE e.id_endereco = :id")
    Optional<EnderecoDTO> findBasicById(Long id);
}