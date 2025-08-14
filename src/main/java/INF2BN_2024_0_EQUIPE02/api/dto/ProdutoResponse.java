package INF2BN_2024_0_EQUIPE02.api.dto;

public class ProdutoResponse {
    private Long id_produto;
    private String nome;
    private String descricao;
    private Float preco;
    private Integer estoque;
    private String foto; // ← String Base64 com prefixo

    // Construtor padrão
    public ProdutoResponse() {}
    

    // Getters e Setters
    public Long getId_produto() { return id_produto; }
    public void setId_produto(Long id_produto) { this.id_produto = id_produto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Float getPreco() { return preco; }
    public void setPreco(Float preco) { this.preco = preco; }

    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }


}