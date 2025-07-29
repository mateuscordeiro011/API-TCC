package INF2BN_2024_0_EQUIPE02.api.dto;

import java.util.Date;

public class VendaDTO {
    private Long id_venda;
    private Date data_venda;
    private float valor_total;
    private Long id_pedido;
    private Long id_cliente;

    public VendaDTO(Long id_venda, Date data_venda, float valor_total, Long id_pedido, Long id_cliente) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.valor_total = valor_total;
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
    }

    public Long getId_venda() {
        return id_venda;
    }

    public void setId_venda(Long id_venda) {
        this.id_venda = id_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}