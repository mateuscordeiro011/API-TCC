package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Pedido")
@Data
@Getter
@Setter
public class Pedido {

    @Id
    @Column(name = "Id_pedido")
    private Long id_pedido;


    @Column(name = "Data_pedido")
    private Date dataPedido;

    @Column(name = "Status", length = 50)
    private String status;


    @Column(name = "Valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_cliente", referencedColumnName = "Id_cliente")
    private Cliente cliente;
}