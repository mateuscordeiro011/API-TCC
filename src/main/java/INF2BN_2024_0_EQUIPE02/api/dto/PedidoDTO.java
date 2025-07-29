package INF2BN_2024_0_EQUIPE02.api.dto;

import java.util.Date;
import java.math.BigDecimal;

public class PedidoDTO {
    private Long id_pedido;
    private Date dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private Long id_cliente;

    public PedidoDTO(Long id_pedido, Date dataPedido, String status, BigDecimal valorTotal, Long id_cliente) {
        this.id_pedido = id_pedido;
        this.dataPedido = dataPedido;
        this.status = status;
        this.valorTotal = valorTotal;
        this.id_cliente = id_cliente;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}