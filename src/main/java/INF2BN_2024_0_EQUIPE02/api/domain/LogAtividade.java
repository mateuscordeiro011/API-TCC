package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_atividades")
public class LogAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_acao", nullable = false)
    private String tipoAcao;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "funcionario_id")
    private Long funcionarioId;

    @Column(name = "usuario_nome")
    private String usuarioNome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    private BigDecimal valor;

    @Column(columnDefinition = "JSON")
    private String detalhes;

    @Column(name = "ip_origem")
    private String ipOrigem;

    @Column(name = "data_hora")
    private LocalDateTime dataHora = LocalDateTime.now();

    private String status = "CONCLUIDA";

    // Construtores
    public LogAtividade() {}

    public LogAtividade(String tipoAcao, Long usuarioId, String usuarioNome, String descricao) {
        this.tipoAcao = tipoAcao;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoAcao() { return tipoAcao; }
    public void setTipoAcao(String tipoAcao) { this.tipoAcao = tipoAcao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }

    public String getIpOrigem() { return ipOrigem; }
    public void setIpOrigem(String ipOrigem) { this.ipOrigem = ipOrigem; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
