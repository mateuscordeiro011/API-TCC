package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Produto;
import INF2BN_2024_0_EQUIPE02.api.dto.ProdutoResponse;
import INF2BN_2024_0_EQUIPE02.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponse> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoResponse> getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .map(this::toResponse);
    }

 private ProdutoResponse toResponse(Produto produto) {
        ProdutoResponse res = new ProdutoResponse();
        res.setId_produto(produto.getId_produto());
        res.setNome(produto.getNome());
        res.setDescricao(produto.getDescricao());
        res.setPreco(produto.getPreco());
        res.setEstoque(produto.getEstoque());

        if (produto.getFoto() != null && produto.getFoto().length > 0) {
            String mimeType = "image/jpeg";
            byte[] foto = produto.getFoto();

            // Detecta PNG
            if (foto.length > 3 && foto[0] == (byte) 0x89 && foto[1] == 0x50 && foto[2] == 0x4E) {
                mimeType = "image/png";
            }

            String base64Data = Base64.getEncoder().encodeToString(foto);
            res.setFoto("data:" + mimeType + ";base64," + base64Data);
        } else {
            res.setFoto(null);
        }

        return res;
    }


    // Métodos de inclusão, atualização, etc. permanecem iguais
    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId_produto(id);
            return produtoRepository.save(produto);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}