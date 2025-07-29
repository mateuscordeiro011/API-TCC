package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Venda;
import INF2BN_2024_0_EQUIPE02.api.dto.VendaDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<VendaDTO> listarVendas() {
        return vendaRepository.findAllBasic();
    }

    public Optional<VendaDTO> getVendaById(Long id) {
        return vendaRepository.findBasicById(id);
    }

    public Venda incluir(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda atualizar(Long id, Venda venda) {
        if (vendaRepository.existsById(id)) {
            venda.setId_Venda(id);
            return vendaRepository.save(venda);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}