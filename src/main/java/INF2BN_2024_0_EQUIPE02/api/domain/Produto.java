package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
@Data // Gera getters, setters, toString, equals, hashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    @Column(name = "Nome", length = 200)
    private String nome;

    @Column(name = "Descricao", length = 500)
    private String descricao;

    @Column(name = "Preco")
    private BigDecimal preco; // ✅ Correto para valores monetários

    @Column(name = "Estoque")
    private Integer estoque; // ✅ Integer em vez de int (permite null, mais seguro)

    @Column(name = "foto", nullable = true)
    private byte[] foto;
}