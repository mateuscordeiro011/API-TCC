package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.ItemVenda;
import INF2BN_2024_0_EQUIPE02.api.dto.ItemVendaDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public List<ItemVendaDTO> listarItemVenda() {
        return itemVendaRepository.findAllBasic();
    }

    public Optional<ItemVendaDTO> getItemVendaById(Long id) {
        return itemVendaRepository.findBasicById(id);
    }

    public ItemVenda incluir(ItemVenda itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    public ItemVenda atualizar(Long id, ItemVenda itemVenda) {
        if (itemVendaRepository.existsById(id)) {
            itemVenda.setId_ItemVenda(id);
            return itemVendaRepository.save(itemVenda);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (itemVendaRepository.existsById(id)) {
            itemVendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}