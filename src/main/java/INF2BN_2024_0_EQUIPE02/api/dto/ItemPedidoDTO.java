package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long id_pedido;
    private Long id_produto;
    private Integer quantidade;
    private BigDecimal preco_unitario;

    // ✅ Construtor com (Long, Long, Integer, BigDecimal)
    public ItemPedidoDTO(Long id_pedido, Long id_produto, Integer quantidade, BigDecimal preco_unitario) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    // Getters
    public Long getId_pedido() { return id_pedido; }
    public Long getId_produto() { return id_produto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getPreco_unitario() { return preco_unitario; }
}