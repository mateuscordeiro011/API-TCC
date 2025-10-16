package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.ProdutoRequest;
import INF2BN_2024_0_EQUIPE02.api.dto.ProdutoResponse;
import INF2BN_2024_0_EQUIPE02.api.domain.Produto;
import INF2BN_2024_0_EQUIPE02.api.repository.ProdutoRepository;
import INF2BN_2024_0_EQUIPE02.api.service.LogAtividadeService;
import INF2BN_2024_0_EQUIPE02.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private LogAtividadeService logService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> produtos = service.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> get(@PathVariable("id") Long id) {
        return service.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> incluir(@RequestBody ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(BigDecimal.valueOf(request.getPreco()));
        produto.setEstoque(request.getEstoque());

        if (request.getFoto() != null && !request.getFoto().trim().isEmpty()) {
            try {
                String fotoData = request.getFoto().trim();

                if (fotoData.contains(",")) {
                    fotoData = fotoData.substring(fotoData.indexOf(",") + 1);
                }

                fotoData = fotoData.replace(" ", "+");

                byte[] fotoBytes = Base64.getDecoder().decode(fotoData);
                produto.setFoto(fotoBytes);
            } catch (IllegalArgumentException e) {
                System.err.println("Base64 inválido: " + e.getMessage());
                return ResponseEntity.badRequest().build();
            }
        }

        Produto novo = service.incluir(produto);

        // Registrar log de cadastro de produto
        logService.registrarAtividadeAdministrativa("CADASTRO_PRODUTO",
                "Produto '" + produto.getNome() + "' cadastrado no sistema");

        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        Optional<Produto> produtoExistente = repository.findById(id);
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoExistente.get();
        String nomeAntigo = produto.getNome();

        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(BigDecimal.valueOf(request.getPreco()));
        produto.setEstoque(request.getEstoque());

        // Atualiza foto apenas se enviada
        if (request.getFoto() != null && !request.getFoto().trim().isEmpty()) {
            try {
                String fotoData = request.getFoto().trim();

                if (fotoData.contains(",")) {
                    fotoData = fotoData.split(",", 2)[1];
                }

                fotoData = fotoData.replace(" ", "+");

                byte[] fotoBytes = Base64.getDecoder().decode(fotoData);
                produto.setFoto(fotoBytes);

            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao decodificar Base64 da imagem: " + e.getMessage());
                return ResponseEntity.badRequest().build();
            }
        }

        Produto atualizado = service.atualizar(id, produto);

        // Registrar log de atualização
        logService.registrarAtividadeAdministrativa("ATUALIZACAO_PRODUTO",
                "Produto '" + nomeAntigo + "' foi atualizado para '" + produto.getNome() + "'");

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            String nomeProduto = produto.get().getNome();
            boolean deletado = service.deletar(id);

            if (deletado) {
                // Registrar log de exclusão
                logService.registrarAtividadeAdministrativa("EXCLUSAO_PRODUTO",
                        "Produto '" + nomeProduto + "' foi removido do sistema");
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}