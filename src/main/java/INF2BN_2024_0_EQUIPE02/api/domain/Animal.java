package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Animais")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_animal")
    private Long id;

    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "Especie", nullable = false, length = 50)
    private String especie;

    @Column(name = "Raca", length = 50)
    private String raca;

    @Column(name = "Sexo", length = 10)
    private String sexo;

    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    @Column(name = "Peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "Id_cliente")
    private Long idCliente;

    @Column(name = "foto")
    private byte[] foto;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }
}