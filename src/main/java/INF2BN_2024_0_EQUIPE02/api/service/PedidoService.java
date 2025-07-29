package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;
import INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAllBasic();
    }

    public Optional<PedidoDTO> getPedidoById(Long id) {
        return pedidoRepository.findBasicById(id);
    }

    public Pedido incluir(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        if (pedidoRepository.existsById(id)) {
            pedidoAtualizado.setId_pedido(id);
            return pedidoRepository.save(pedidoAtualizado);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}