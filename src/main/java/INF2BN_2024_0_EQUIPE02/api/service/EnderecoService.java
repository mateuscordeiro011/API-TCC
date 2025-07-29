package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> listarEnderecos() {
        return enderecoRepository.findAllBasic();
    }

    public Optional<EnderecoDTO> getEnderecoById(Long id) {
        return enderecoRepository.findBasicById(id);
    }

    public Endereco incluir(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if (enderecoRepository.existsById(id)) {
            endereco.setId_endereco(id);
            return enderecoRepository.save(endereco);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}