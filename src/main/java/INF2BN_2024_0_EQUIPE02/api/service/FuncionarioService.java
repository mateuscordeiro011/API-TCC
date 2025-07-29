package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDTO> listarFuncionarios() {
        return funcionarioRepository.findAllBasic();
    }

    public Optional<FuncionarioDTO> getFuncionarioById(Long id) {
        return funcionarioRepository.findBasicById(id);
    }

    public Funcionario incluir(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionario) {
        if (funcionarioRepository.existsById(id)) {
            funcionario.setId_Funcionario(id);
            return funcionarioRepository.save(funcionario);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}