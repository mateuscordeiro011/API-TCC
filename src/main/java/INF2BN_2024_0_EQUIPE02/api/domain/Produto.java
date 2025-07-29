package INF2BN_2024_0_EQUIPE02.api.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name ="Produto")
@Data
public class Produto {
   
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_produto;
        @Column
        private String nome;
        @Column
        private String descricao;
        @Column
        private float preco;
        @Column
        private int estoque;
        @Column(name = "foto", nullable = true)
        private boolean foto;
}
