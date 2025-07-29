package INF2BN_2024_0_EQUIPE02.api.dto;


    public class ItemVendaDTO {
        private Long id_ItemVenda;
        private Long id_produto;
        private int quantidade;
        private float precoUnitario;

        public ItemVendaDTO(Long id_ItemVenda, Long id_produto, int quantidade, float precoUnitario) {
            this.id_ItemVenda = id_ItemVenda;
            this.id_produto = id_produto;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public Long getId_ItemVenda() {
            return id_ItemVenda;
        }

        public void setId_ItemVenda(Long id_ItemVenda) {
            this.id_ItemVenda = id_ItemVenda;
        }

        public Long getId_produto() {
            return id_produto;
        }

        public void setId_produto(Long id_produto) {
            this.id_produto = id_produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public float getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(float precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        // Getters e Setters
    }

