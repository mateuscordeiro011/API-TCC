package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.ItemPedido;
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

    public Optional<ItemPedidoDTO> getItemPedidoById(Long id) {
        return itemPedidoRepository.findBasicById(id);
    }

    public ItemPedido incluir(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido atualizar(Long id, ItemPedido itemPedido) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedido.setId_pedido(id);
            return itemPedidoRepository.save(itemPedido);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}