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
    private String Cargo;
    @Column(nullable = true, length = 10000)
    private float salario;
  
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco Endereco;
    @Column(name = "foto", nullable = true)
    private boolean foto;
}
