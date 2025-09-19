package INF2BN_2024_0_EQUIPE02.api.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;

@Entity
@Table(name = "Funcionario")
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

    @Column(name = "Id_endereco")
    private Long idEndereco;

    @Column(name = "foto", nullable = true)
    private byte[] foto;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}