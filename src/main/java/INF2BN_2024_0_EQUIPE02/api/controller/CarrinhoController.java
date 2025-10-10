package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.CarrinhoItemRequest;
import INF2BN_2024_0_EQUIPE02.api.dto.CarrinhoItemResponse;
import INF2BN_2024_0_EQUIPE02.api.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    // Cliente: listar seu próprio carrinho
    @GetMapping("/meu")
    public ResponseEntity<List<CarrinhoItemResponse>> listarMeuCarrinho(
            @RequestParam Long idUsuario) {
        return ResponseEntity.ok(carrinhoService.listarCarrinho(idUsuario));
    }

    

    // Cliente: adicionar item
    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoItemResponse> adicionarItem(
            @RequestParam Long idUsuario,
            @RequestBody CarrinhoItemRequest request) {
        return ResponseEntity.ok(carrinhoService.adicionarItem(idUsuario, request));
    }

    // Cliente: remover item
    @DeleteMapping("/remover")
    public ResponseEntity<Void> removerItem(
            @RequestParam Long idUsuario,
            @RequestParam Long idProduto) {
        carrinhoService.removerItem(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }

    // Funcionário: ver TODOS os carrinhos
    @GetMapping
    public ResponseEntity<List<CarrinhoItemResponse>> listarTodosCarrinhos() {
        // TODO: Adicionar validação de permissão de funcionário aqui
        // Por enquanto, retorna todos os itens de todos os usuários
        // Em produção, filtre por usuário logado ou adicione segurança
        return ResponseEntity.ok(carrinhoService.listarCarrinho(null)); // Isso não funciona!
    }
}