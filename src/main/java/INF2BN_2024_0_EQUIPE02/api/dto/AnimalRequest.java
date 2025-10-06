package INF2BN_2024_0_EQUIPE02.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class AnimalRequest {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("especie")
    private String especie;

    @JsonProperty("raca")
    private String raca;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("nascimento")
    private LocalDate data_nascimento;

    @JsonProperty("peso")
    private float peso;

    @JsonProperty("foto")
    private String foto;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getData_Nascimento() {
        return data_nascimento;
    }

    public void setData_Nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}