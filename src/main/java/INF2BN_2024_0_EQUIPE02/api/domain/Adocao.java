package INF2BN_2024_0_EQUIPE02.api.domain;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Adocao", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id_animal")
})
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_adocao")
    private Long id_adocao;

    @Column(name = "Data_adocao", nullable = true)
    private LocalDate data_adocao;

    @Column(name = "Status", nullable = true)
    private boolean status;

    @Column(name = "Observacoes", nullable = true, length = 500)
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "Id_animal", nullable = true)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "Id_cliente", nullable = true)
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
