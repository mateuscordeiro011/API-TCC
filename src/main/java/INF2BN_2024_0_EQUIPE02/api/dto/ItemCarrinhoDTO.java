package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;

public class ItemCarrinhoDTO {
    private Integer id_produto;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco_unitario;
    private byte[] foto;


    public Integer getId_produto() {
        return id_produto;
    }
    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }
    public void setPreco_unitario(BigDecimal preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }



}