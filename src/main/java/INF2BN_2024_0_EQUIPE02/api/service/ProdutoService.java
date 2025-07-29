package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Produto;
import INF2BN_2024_0_EQUIPE02.api.dto.ProdutoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAllBasic();
    }

    public Optional<ProdutoDTO> getProdutoById(Long id) {
        return produtoRepository.findBasicById(id);
    }

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