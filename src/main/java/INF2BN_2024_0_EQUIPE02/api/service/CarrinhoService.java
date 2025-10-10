package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.CarrinhoItem;
import INF2BN_2024_0_EQUIPE02.api.dto.CarrinhoItemRequest;
import INF2BN_2024_0_EQUIPE02.api.dto.CarrinhoItemResponse;
import INF2BN_2024_0_EQUIPE02.api.repository.CarrinhoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoItemRepository carrinhoRepository;

    public List<CarrinhoItemResponse> listarCarrinho(Long idUsuario) {
        return carrinhoRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<CarrinhoItemResponse> listarTodosCarrinhos() {
        return carrinhoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CarrinhoItemResponse adicionarItem(Long idUsuario, CarrinhoItemRequest request) {
        CarrinhoItem item = new CarrinhoItem(
                idUsuario,
                request.getIdProduto(),
                request.getQuantidade(),
                request.getPrecoUnitario(),
                request.getNomeProduto()
        );
        CarrinhoItem salvo = carrinhoRepository.save(item);
        return toResponse(salvo);
    }

    public void removerItem(Long idUsuario, Long idProduto) {
        carrinhoRepository.deleteByIdUsuarioAndIdProduto(idUsuario, idProduto);
    }

    public void limparCarrinho(Long idUsuario) {
        carrinhoRepository.deleteByIdUsuario(idUsuario);
    }

    private CarrinhoItemResponse toResponse(CarrinhoItem item) {
        CarrinhoItemResponse response = new CarrinhoItemResponse();
        response.setId(item.getId());
        response.setIdUsuario(item.getIdUsuario());
        response.setIdProduto(item.getIdProduto());
        response.setQuantidade(item.getQuantidade());
        response.setPrecoUnitario(item.getPrecoUnitario());
        response.setNomeProduto(item.getNomeProduto());
        return response;
    }
}