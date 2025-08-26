package INF2BN_2024_0_EQUIPE02.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class AnimalResponse {
    private Long id_animal;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate data_nascimento;
    private String sexo;
    private float peso;
    private String foto;
    private Long id_cliente;

    public AnimalResponse() {}

    // Getters e Setters

    public Long getId_animal() { return id_animal; }
    public void setId_animal(Long id_animal) { this.id_animal = id_animal; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    // ✅ Força o nome no JSON para "nascimento"
    @JsonProperty("nascimento")
    public LocalDate getData_Nascimento() {
        return data_nascimento;
    }

    @JsonProperty("nascimento")
    public void setData_Nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}