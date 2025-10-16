package INF2BN_2024_0_EQUIPE02.api.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class DoacaoDTO {
    private Long id_cliente_doador;
    private String nome;
    private String especie;
    private String raca;
    private String data_nascimento; // String para receber do front
    private String sexo;
    private Double peso; // Double para receber do front
    private String foto;
    private String status;

    // Getters e Setters
    public Long getId_cliente_doador() { return id_cliente_doador; }
    public void setId_cliente_doador(Long id_cliente_doador) { this.id_cliente_doador = id_cliente_doador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public String getData_nascimento() { return data_nascimento; }
    public void setData_nascimento(String data_nascimento) { this.data_nascimento = data_nascimento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}