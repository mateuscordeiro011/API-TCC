package INF2BN_2024_0_EQUIPE02.api.dto;


public class CriarPedidoDTO {
    private Long idUsuario;
    private Double total;

    // Construtores
    public CriarPedidoDTO() {}

    // Getters e Setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

}
