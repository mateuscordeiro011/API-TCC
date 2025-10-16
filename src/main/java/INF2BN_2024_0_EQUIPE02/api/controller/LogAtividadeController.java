package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.LogAtividade;
import INF2BN_2024_0_EQUIPE02.api.service.LogAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api-salsi/logs")
@CrossOrigin(origins = "http://localhost:5173")
public class LogAtividadeController {

    @Autowired
    private LogAtividadeService service;

    @GetMapping
    public ResponseEntity<List<LogAtividade>> listarTodos() {
        List<LogAtividade> logs = service.listarTodos();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/recentes")
    public ResponseEntity<List<LogAtividade>> listarRecentes() {
        List<LogAtividade> logs = service.listarRecentes();
        return ResponseEntity.ok(logs);
    }

    @PostMapping
    public ResponseEntity<LogAtividade> criarLog(@RequestBody LogAtividade log) {
        LogAtividade novoLog = service.salvar(log);
        return ResponseEntity.ok(novoLog);
    }
}
