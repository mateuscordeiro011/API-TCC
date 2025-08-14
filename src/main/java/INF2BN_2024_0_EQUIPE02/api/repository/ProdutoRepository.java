package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}