package INF2BN_2024_0_EQUIPE02.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;
import INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.AdocaoRepository;

@Service
public class AdocaoService {
    
    @Autowired
    private AdocaoRepository adocaoRepository;

    public List<AdocaoDTO> listarAdocao() {
        return adocaoRepository.findAllBasic();
    }

    public Optional<AdocaoDTO> getAdocaoById(Long id) {
        return adocaoRepository.findBasicById(id);
    }

    public Adocao incluir(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    public Adocao atualizar(Long id, Adocao adocao) {
        if (adocaoRepository.existsById(id)) {
            adocao.setId_adocao(id);
            return adocaoRepository.save(adocao);
        }
        return null;
    }
}
