package INF2BN_2024_0_EQUIPE02.api.repository;


import INF2BN_2024_0_EQUIPE02.api.domain.LogAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface LogAtividadeRepository extends JpaRepository<LogAtividade, Long> {

    List<LogAtividade> findByTipoAcao(String tipoAcao);

    List<LogAtividade> findByUsuarioId(Long usuarioId);

    @Query("SELECT l FROM LogAtividade l ORDER BY l.dataHora DESC")
    List<LogAtividade> findAllOrderByDataHoraDesc();

    @Query("SELECT l FROM LogAtividade l WHERE l.dataHora >= :dataInicio ORDER BY l.dataHora DESC")
    List<LogAtividade> findRecentLogs(@Param("dataInicio") LocalDateTime dataInicio);
}
