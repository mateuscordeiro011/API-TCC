package INF2BN_2024_0_EQUIPE02.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import INF2BN_2024_0_EQUIPE02.api.domain.Adocao;
import INF2BN_2024_0_EQUIPE02.api.dto.AdocaoDTO;
import INF2BN_2024_0_EQUIPE02.api.service.AdocaoService;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/adocao")
public class AdocaoController {
    
    @Autowired
    private AdocaoService service;

    @GetMapping
    public ResponseEntity<List<AdocaoDTO>> listaradocoes(){
        return ResponseEntity.ok(service.listarAdocao());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoDTO> get(@PathVariable("id") Long id) {
        return service.getAdocaoById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Adocao> incluir(@RequestBody Adocao adocao) {
        Adocao novo = service.incluir(adocao);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping
    public ResponseEntity<Adocao> atualizar(@PathVariable Long id, @RequestBody Adocao adocao) {
        Adocao atualizado = service.atualizar(id, adocao);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } 
        return ResponseEntity.notFound().build();
    }
}
