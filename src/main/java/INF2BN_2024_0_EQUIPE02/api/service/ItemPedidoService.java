package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedidoId;
import INF2BN_2024_0_EQUIPE02.api.dto.ItemPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedidoDTO> listarItemPedidos() {
        return itemPedidoRepository.findAllBasic();
    }

    // ✅ Agora usa Long
    public Optional<ItemPedidoDTO> getItemPedido(Long idPedido, Long idProduto) {
        return itemPedidoRepository.findBasicById(idPedido, idProduto);
    }

    public ItemPedido incluir(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido atualizarQuantidade(Long idPedido, Long idProduto, Integer novaQuantidade) {
        ItemPedidoId id = new ItemPedidoId(idPedido, idProduto);
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setQuantidade(novaQuantidade);
        return itemPedidoRepository.save(item);
    }

    public boolean deletar(Long idPedido, Long idProduto) {
        ItemPedidoId id = new ItemPedidoId(idPedido, idProduto);
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}