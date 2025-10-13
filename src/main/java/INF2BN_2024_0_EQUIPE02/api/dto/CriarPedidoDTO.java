package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;

public class CriarPedidoDTO {
    private Long idUsuario;
    private BigDecimal total;
    private String paymentMethod;

    // Construtores
    public CriarPedidoDTO() {}

    // Getters e Setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
