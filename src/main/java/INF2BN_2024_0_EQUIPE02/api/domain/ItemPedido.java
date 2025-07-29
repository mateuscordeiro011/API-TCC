package INF2BN_2024_0_EQUIPE02.api.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "ItemPedido")
@Data

public class ItemPedido {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_produto", referencedColumnName = "id_produto")
    private Produto Produto;
    
    @Column(nullable = true)
    private int quantidade;
    @Column(nullable = true)
    private float precoUnitario;
}
