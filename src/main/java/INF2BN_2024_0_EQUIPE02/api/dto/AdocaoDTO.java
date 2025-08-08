package INF2BN_2024_0_EQUIPE02.api.dto;

import java.time.LocalDate;

public class AdocaoDTO {
    private Long id_adocao;
    private Long id_animal;
    private boolean status;
    private LocalDate data_adocao;
    private Long id_cliente;
    private String observacoes;

    public AdocaoDTO(Long id_adocao, Long id_animal,Long id_cliente, String observacoes,
                     LocalDate data_adocao, boolean status) {
        this.id_adocao = id_adocao;
        this.id_animal = id_animal;
        this.id_cliente = id_cliente;
        this.observacoes = observacoes;
        this.data_adocao = data_adocao;
        this.status = status;
    }

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }
    public Long getId_adocao() {
        return id_adocao;
    }

    public void setId_adocao(Long id_adocao) {
        this.id_adocao = id_adocao;
    }

    public LocalDate getData_adocao() {
        return data_adocao;
    }

    public void setData_adocao(LocalDate data_adocao) {
        this.data_adocao = data_adocao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
