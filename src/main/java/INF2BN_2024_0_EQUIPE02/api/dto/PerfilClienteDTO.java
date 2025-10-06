package INF2BN_2024_0_EQUIPE02.api.dto;

public class PerfilClienteDTO {
    private Long id_cliente;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String foto;
    private EnderecoDTO endereco;

    public PerfilClienteDTO() {}

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public EnderecoDTO getEndereco() { return endereco; }
    public void setEndereco(EnderecoDTO endereco) { this.endereco = endereco; }
}