package INF2BN_2024_0_EQUIPE02.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;
import INF2BN_2024_0_EQUIPE02.api.repository.AdocaoRepository;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    public List<Adocao> listarAnimaisDisponiveis() {
        return repository.findAnimaisDisponiveis();
    }

    public List<Adocao> listarDoacoesPorCliente(Long idCliente) {
        return repository.findByIdClienteDoador(idCliente);
    }

    public Optional<Adocao> getDoacaoById(Long id) {
        return repository.findById(id);
    }

    public Adocao incluir(Adocao doacao) {
        try {
            // Garante data de cadastro e status inicial
            doacao.setData_cadastro(java.time.LocalDate.now());
            if (doacao.getStatus() == null || doacao.getStatus().isEmpty()) {
                doacao.setStatus("Disponível");
            }
            return repository.save(doacao);
        } catch (Exception e) {
            System.err.println("Erro no AdocaoService.incluir: " + e.getMessage());
            throw e;
        }
    }

    public Adocao atualizar(Long id, Adocao doacao) {
        if (repository.existsById(id)) {
            doacao.setId_animal(id);
            return repository.save(doacao);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}