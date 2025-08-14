package INF2BN_2024_0_EQUIPE02.api.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name ="Produto")
@Data
public class Produto {
   
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_produto;
        @Column(name = "Nome", length = 200)
        private String nome;
        @Column(name = "Descricao", length = 500)
        private String descricao;
        @Column(name = "Preco")
        private float preco;
        @Column(name = "Estoque")
        private int estoque;
        @Column(name = "foto", nullable = true)
        private byte[] foto;

        public Long getId_produto() {
            return id_produto;
        }

        public void setId_produto(Long id_produto) {
            this.id_produto = id_produto;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public float getPreco() {
            return preco;
        }

        public void setPreco(float preco) {
            this.preco = preco;
        }

        public int getEstoque() {
            return estoque;
        }

        public void setEstoque(int estoque) {
            this.estoque = estoque;
        }

        public byte[] getFoto() {
            return foto;
        }

        public void setFoto(byte[] foto) {
            this.foto = foto;
        }
}       


