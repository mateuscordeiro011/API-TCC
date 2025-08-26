package INF2BN_2024_0_EQUIPE02.api.domain;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table (name = "Funcionario")
@Data

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id_Funcionario ;
    @Column(nullable = true, length = 100)
    private String nome ;
    @Column(nullable = true, length = 30)
    private String cargo;
    @Column(name = "Email", nullable = false, unique = true, length = 200)
    private String email;
    @Column(name = "Senha", nullable = false, unique = true, length = 20)
    private String senha;
    @Column(nullable = true, length = 10000)
    private float salario;
  
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;
    @Column(name = "foto", nullable = true)
    private byte[] foto;
}
