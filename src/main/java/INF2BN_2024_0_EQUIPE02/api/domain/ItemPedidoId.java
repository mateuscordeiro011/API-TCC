package INF2BN_2024_0_EQUIPE02.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class ItemPedidoId implements Serializable {

    private Long pedido;   // ← agora é Long
    private Long produto;  // ← agora é Long

    public ItemPedidoId() {}

    public ItemPedidoId(Long pedido, Long produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoId that = (ItemPedidoId) o;
        return Objects.equals(pedido, that.pedido) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}