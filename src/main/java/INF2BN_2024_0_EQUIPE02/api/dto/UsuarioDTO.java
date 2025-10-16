package INF2BN_2024_0_EQUIPE02.api.dto;



public class UsuarioDTO {
    private Long id;
    private String email;
    private String tipo;
    private Long idVinculado;
    private String nome;

    public UsuarioDTO(Long id, String email, String tipo, Long idVinculado, String nome) {
        this.id = id;
        this.email = email;
        this.tipo = tipo;
        this.idVinculado = idVinculado;
        this.nome = nome;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdVinculado() {
        return idVinculado;
    }

    public void setIdVinculado(Long idVinculado) {
        this.idVinculado = idVinculado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}