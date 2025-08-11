package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.dto.ProdutoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ProdutoDTO(pr.id_produto, pr.nome, pr.descricao, pr.preco, pr.estoque, pr.foto) FROM Produto pr")
    List<ProdutoDTO> findAllBasic();

    @Query("SELECT new INF2BN_2024_0_EQUIPE02.api.dto.ProdutoDTO(pr.id_produto, pr.nome, pr.descricao, pr.preco, pr.estoque, pr.foto) FROM Produto pr WHERE pr.id_produto = :id")
    Optional<ProdutoDTO> findBasicById(Long id);
}