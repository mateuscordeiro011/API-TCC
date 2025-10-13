package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.LogAtividade;
import INF2BN_2024_0_EQUIPE02.api.repository.LogAtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAtividadeService {

    @Autowired
    private LogAtividadeRepository repository;

    public List<LogAtividade> listarTodos() {
        return repository.findAllOrderByDataHoraDesc();
    }

    public List<LogAtividade> listarRecentes() {
        LocalDateTime umDiaAtras = LocalDateTime.now().minusDays(1);
        return repository.findRecentLogs(umDiaAtras);
    }

    public LogAtividade salvar(LogAtividade log) {
        return repository.save(log);
    }

    // === Métodos específicos ===

    public void registrarCompra(Long usuarioId, String usuarioNome, String descricao, BigDecimal valor) {
        LogAtividade log = new LogAtividade("COMPRA", usuarioId, usuarioNome, descricao);
        log.setValor(valor);
        log.setStatus("CONCLUIDA");
        repository.save(log);
    }

    public void registrarDoacao(Long usuarioId, String usuarioNome, String nomeAnimal) {
        String descricao = "Cadastrou animal '" + nomeAnimal + "' para doação";
        LogAtividade log = new LogAtividade("DOACAO", usuarioId, usuarioNome, descricao);
        log.setStatus("PENDENTE");
        repository.save(log);
    }

    public void registrarAdocao(Long usuarioId, String usuarioNome, String nomeAnimal) {
        String descricao = "Iniciou processo de adoção do animal '" + nomeAnimal + "'";
        LogAtividade log = new LogAtividade("ADOCAO", usuarioId, usuarioNome, descricao);
        log.setStatus("EM_ANDAMENTO");
        repository.save(log);
    }

    public void registrarCadastro(Long usuarioId, String usuarioNome) {
        String descricao = "Novo cliente cadastrado no sistema";
        LogAtividade log = new LogAtividade("CADASTRO", usuarioId, usuarioNome, descricao);
        log.setStatus("ATIVO");
        repository.save(log);
    }

    // para logs genéricos de administração (produtos, etc)
    public void registrarAtividadeAdministrativa(String tipoAcao, String descricao) {
        LogAtividade log = new LogAtividade(tipoAcao, null, "SISTEMA", descricao);
        log.setStatus("CONCLUIDA");
        repository.save(log);
    }
}