package INF2BN_2024_0_EQUIPE02.api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_usuario")
    private Long id;

    @Column(name = "Email", unique = true, nullable = false, length = 200)
    private String email;

    @Column(name = "Senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "Tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "Id_cliente")
    private Long idCliente;

    @Column(name = "Id_funcionario")
    private Long idFuncionario;

    // Construtores
    public Usuario() {}

    public Usuario(String email, String senha, String tipo) {
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public boolean isCliente() {
        return "CLIENTE".equalsIgnoreCase(tipo);
    }

    public boolean isFuncionario() {
        return "FUNCIONARIO".equalsIgnoreCase(tipo);
    }
}