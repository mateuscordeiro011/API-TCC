package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.domain.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
    List<CarrinhoItem> findByIdUsuario(Long idUsuario);
    List<CarrinhoItem> findAll();
    void deleteByIdUsuario(Long idUsuario);
    void deleteByIdUsuarioAndIdProduto(Long idUsuario, Long idProduto);
}