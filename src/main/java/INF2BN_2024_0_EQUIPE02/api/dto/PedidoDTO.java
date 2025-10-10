package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDTO {

    private Long id_pedido;
    private LocalDate data_pedido;
    private String status;
    private BigDecimal valor_total;
    private Long clienteId; // nome do campo pode variar

    // 🔸 Construtor obrigatório para JPQL
    public PedidoDTO(Long id_pedido, LocalDate data_pedido, String status, BigDecimal valor_total, Long clienteId) {
        this.id_pedido = id_pedido;
        this.data_pedido = data_pedido;
        this.status = status;
        this.valor_total = valor_total;
        this.clienteId = clienteId;
    }

    // 🔸 Construtor padrão (opcional, mas bom ter)
    public PedidoDTO() {}

    // 🔸 Getters e setters
    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}