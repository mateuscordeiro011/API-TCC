package INF2BN_2024_0_EQUIPE02.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;
import INF2BN_2024_0_EQUIPE02.api.dto.DoacaoDTO;
import INF2BN_2024_0_EQUIPE02.api.service.AdocaoService;
import java.time.LocalDate;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api-salsi/doacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    // @Autowired
    // private EmailService emailService;

    // @Autowired
    // private ClienteService clienteService;

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Adocao>> listarAnimaisDisponiveis() {
        return ResponseEntity.ok(service.listarAnimaisDisponiveis());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Adocao>> listarDoacoesPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.listarDoacoesPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adocao> getDoacao(@PathVariable Long id) {
        return service.getDoacaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Adocao> incluir(@RequestBody DoacaoDTO dto) {
        try {
            System.out.println("Recebendo doação: " + dto.getNome());

            // Converter DTO para Entity
            Adocao doacao = new Adocao();
            doacao.setId_cliente_doador(dto.getId_cliente_doador());
            doacao.setNome(dto.getNome());
            doacao.setEspecie(dto.getEspecie());
            doacao.setRaca(dto.getRaca());

            // Converter data string para LocalDate
            if (dto.getData_nascimento() != null && !dto.getData_nascimento().isEmpty()) {
                doacao.setData_nascimento(LocalDate.parse(dto.getData_nascimento()));
            }

            doacao.setSexo(dto.getSexo());

            // Converter peso Double para BigDecimal
            if (dto.getPeso() != null) {
                doacao.setPeso(BigDecimal.valueOf(dto.getPeso()));
            }

            // Converter foto base64 para byte[]
            if (dto.getFoto() != null && !dto.getFoto().isEmpty()) {
                try {
                    String base64Data = dto.getFoto().replaceFirst("^data:image/\\w+;base64,", "");
                    doacao.setFoto(java.util.Base64.getDecoder().decode(base64Data));
                } catch (Exception e) {
                    System.err.println("Erro ao processar foto: " + e.getMessage());
                }
            }

            Adocao novaDoacao = service.incluir(doacao);
            System.out.println("Doação criada com ID: " + novaDoacao.getId_animal());

            return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);

        } catch (Exception e) {
            System.err.println("Erro ao criar doação: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adocao> atualizar(@PathVariable Long id, @RequestBody Adocao doacao) {
        Adocao atualizada = service.atualizar(id, doacao);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Método comentado temporariamente devido a dependências
    // @PostMapping("/iniciar-adocao")
}