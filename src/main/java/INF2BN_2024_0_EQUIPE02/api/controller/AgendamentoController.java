package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.service.EmailService;
import INF2BN_2024_0_EQUIPE02.api.repository.AnimalRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.domain.Animal;
import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api-salsi/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/visita")
    public ResponseEntity<?> criarAgendamento(@RequestBody Map<String, Object> request) {
        try {
            Long animalId = Long.valueOf(request.get("animalId").toString());
            Long clienteId = Long.valueOf(request.get("clienteId").toString());
            String dataVisita = request.get("dataVisita").toString();

            // Buscar dados do animal e cliente
            Animal animal = animalRepository.findById(animalId)
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // Enviar email de confirmação
            emailService.enviarEmailAgendamentoVisita(
                    cliente.getEmail(),
                    cliente.getNome(),
                    animal.getNome(),
                    dataVisita
            );

            return ResponseEntity.ok("Agendamento criado e email enviado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarAgendamentos() {
        // Retorna lista vazia por enquanto, pois não temos tabela de agendamentos
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }
}
