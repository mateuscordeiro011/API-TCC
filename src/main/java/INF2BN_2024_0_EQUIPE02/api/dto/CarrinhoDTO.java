package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class CarrinhoDTO {
    private Integer id_pedido;
    private List<ItemCarrinhoDTO> itens;
    private BigDecimal valor_total;

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }
}