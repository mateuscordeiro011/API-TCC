package INF2BN_2024_0_EQUIPE02.api.dto;

import java.time.LocalDate;

public class AnimalDTO {
    private Long id_animal;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate data_nascimento;
    private String sexo;
    private float peso;
    private byte[] foto;
    private Long id_cliente; // Apenas ID, não o cliente completo

    public AnimalDTO(Long id_animal, String nome, String especie, String raca,
                     LocalDate data_nascimento, String sexo, float peso, byte[] foto, Long id_cliente) {
        this.id_animal = id_animal;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.foto = foto;
        this.id_cliente = id_cliente;
    }

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }

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

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
