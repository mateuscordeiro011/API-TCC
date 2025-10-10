package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Funcionario")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_funcionario")
    private Long id;

    @Column(name = "Nome", nullable = true, length = 100)
    private String nome;

    @Column(name = "Cargo", nullable = true, length = 30)
    private String cargo;

    @Column(name = "Salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "Email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "Senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "Cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_endereco")
    private Endereco endereco;

    @Column(name = "foto", nullable = true, columnDefinition = "TEXT")
    private String foto;
}