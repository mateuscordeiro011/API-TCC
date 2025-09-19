package INF2BN_2024_0_EQUIPE02.api.repository;

import INF2BN_2024_0_EQUIPE02.api.domain.TokenRecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TokenRecuperacaoSenhaRepository extends JpaRepository<TokenRecuperacaoSenha, Long> {
    Optional<TokenRecuperacaoSenha> findByToken(String token);

    @Modifying
    @Query("DELETE FROM TokenRecuperacaoSenha t WHERE t.idCliente = ?1")
    void deleteByClienteId(Long clienteId);

    @Modifying
    @Query("DELETE FROM TokenRecuperacaoSenha t WHERE t.idFuncionario = ?1")
    void deleteByFuncionarioId(Long funcionarioId);
}