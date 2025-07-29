package INF2BN_2024_0_EQUIPE02.api.dto;

import java.util.List;

public class ClienteDTO {
    private Long id_cliente;
    private String nome;
    private String email;
    private String cpf;
    private byte[] foto;
    private Long id_endereco;
    private List<Long> ids_pedidos;

    public ClienteDTO(Long id_cliente, String nome, String email, String cpf,
                      byte[] foto, Long id_endereco, List<Long> ids_pedidos) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.foto = foto;
        this.id_endereco = id_endereco;
        this.ids_pedidos = ids_pedidos;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(Long id_endereco) {
        this.id_endereco = id_endereco;
    }

    public List<Long> getIds_pedidos() {
        return ids_pedidos;
    }

    public void setIds_pedidos(List<Long> ids_pedidos) {
        this.ids_pedidos = ids_pedidos;
    }
}