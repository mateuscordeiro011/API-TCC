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

    @Column(name = "Logradouro", nullable = true, length = 100)
    private String logradouro;

    @Column(name = "Bairro", nullable = true, length = 50)
    private String bairro;

    @Column(name = "Cidade", nullable = true, length = 50)
    private String cidade;

    @Column(name = "Estado", nullable = true, length = 50)
    private String estado;
}