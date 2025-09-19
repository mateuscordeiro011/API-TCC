package INF2BN_2024_0_EQUIPE02.api.domain;
// INF2BN_2024_0_EQUIPE02.api.domain.Doacao.java

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Animais")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_animal")
    private Long id_animal;

    @Column(name = "Id_cliente")
    private Long id_cliente_doador;

    @Column(name = "Nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "Especie", nullable = false, length = 200)
    private String especie;

    @Column(name = "Raca", length = 200)
    private String raca;

    @Column(name = "Data_nascimento")
    private LocalDate data_nascimento;

    @Column(name = "Sexo", length = 20)
    private String sexo;

    @Column(name = "Peso", precision = 6, scale = 2)
    private java.math.BigDecimal peso;

    @Column(name = "foto")
    private byte[] foto;

    // Status: 'Disponível' (para adoção) ou 'Adotado'
    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "Data_cadastro")
    private LocalDate data_cadastro;

    public Adocao() {
        this.data_cadastro = LocalDate.now();
        this.status = "Disponível"; // Status inicial
    }

    public Long getId_animal() { return id_animal; }
    public void setId_animal(Long id_animal) { this.id_animal = id_animal; }

    public Long getId_cliente_doador() { return id_cliente_doador; }
    public void setId_cliente_doador(Long id_cliente_doador) { this.id_cliente_doador = id_cliente_doador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public LocalDate getData_nascimento() { return data_nascimento; }
    public void setData_nascimento(LocalDate data_nascimento) { this.data_nascimento = data_nascimento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public java.math.BigDecimal getPeso() { return peso; }
    public void setPeso(java.math.BigDecimal peso) { this.peso = peso; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getData_cadastro() { return data_cadastro; }
    public void setData_cadastro(LocalDate data_cadastro) { this.data_cadastro = data_cadastro; }
}