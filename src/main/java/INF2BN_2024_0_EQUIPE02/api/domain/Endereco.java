package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    @Column(name = "Cep", nullable = true, length = 15)
    private String cep;

    @Column(name = "Rua", nullable = true, length = 100)
    private String rua;

    @Column(name = "Bairro", nullable = true, length = 50)
    private String bairro;

    @Column(name = "Cidade", nullable = true, length = 50)
    private String cidade;

    @Column(name = "Estado", nullable = true, length = 50)
    private String estado;

    @Column(name = "Complemento", nullable = true, length = 20)
    private String complemento;

    @Column(name = "Numero", nullable = true, length = 10)
    private int numero;

    public Long getId_endereco() {
        return id_endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getNumero() {
        return numero;
    }
}