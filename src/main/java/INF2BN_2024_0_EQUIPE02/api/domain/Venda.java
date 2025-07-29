package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Venda")
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long Id_Venda ;
    @Column
    private Date Data_Venda ;
    @Column
    private float Valor_total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_pedido", referencedColumnName = "Id_pedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_cliente", referencedColumnName = "Id_cliente")
    private Cliente cliente;
}