package INF2BN_2024_0_EQUIPE02.api.dto;

import java.math.BigDecimal;

public class ProdutoResponse {
    private Long id_produto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private String foto; 

    // Construtor padrão
    public ProdutoResponse() {}
    

    // Getters e Setters
    public Long getId_produto() { return id_produto; }
    public void setId_produto(Long id_produto) { this.id_produto = id_produto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal  preco) { this.preco = preco; }

    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }


}