package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.domain.Animal;
import INF2BN_2024_0_EQUIPE02.api.dto.AnimalRequest;
import INF2BN_2024_0_EQUIPE02.api.dto.AnimalResponse;
import INF2BN_2024_0_EQUIPE02.api.service.AnimalService;
import INF2BN_2024_0_EQUIPE02.api.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api-salsi/animais")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalRepository repository;

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> listarAnimais() {
        System.out.println("=== AnimalController: Iniciando listagem de animais ===");
        try {
            List<AnimalResponse> animais = service.listarAnimais();
            System.out.println("=== AnimalController: Animais listados com sucesso, total: " + animais.size() + " ===");
            return ResponseEntity.ok(animais);
        } catch (Exception e) {
            System.err.println(" AnimalController: Erro ao listar animais");
            e.printStackTrace(); // Imprime a stack trace no console do servidor
            return ResponseEntity.status(500).build(); // Retorna 500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> get(@PathVariable("id") Long id) {
        return service.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Animal> incluir(@RequestBody AnimalRequest request) {
        Animal animal = new Animal();
        animal.setNome(request.getNome());
        animal.setEspecie(request.getEspecie());
        animal.setRaca(request.getRaca());
        animal.setSexo(request.getSexo());
        animal.setDataNascimento(request.getData_Nascimento());
        animal.setPeso(java.math.BigDecimal.valueOf(request.getPeso()));

        if (request.getFoto() != null && !request.getFoto().trim().isEmpty()) {
            try {
                String fotoData = request.getFoto().trim();

                if (fotoData.contains(",")) {
                    fotoData = fotoData.substring(fotoData.indexOf(",") + 1);
                }

                fotoData = fotoData.replace(" ", "+");
                byte[] fotoBytes = Base64.getDecoder().decode(fotoData);
                animal.setFoto(fotoBytes);

            } catch (IllegalArgumentException e) {
                System.err.println("Base64 inválido: " + e.getMessage());
                return ResponseEntity.badRequest().build();
            }
        }

        Animal novo = service.incluir(animal);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody AnimalRequest request) {
        Optional<Animal> animalExistente = repository.findById(id);
        if (animalExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Animal animal = animalExistente.get();
        animal.setNome(request.getNome());
        animal.setEspecie(request.getEspecie());
        animal.setRaca(request.getRaca());
        animal.setSexo(request.getSexo());
        animal.setDataNascimento(request.getData_Nascimento()); // ✅ Corrigido: nome correto
        animal.setPeso(java.math.BigDecimal.valueOf(request.getPeso())); // ✅ Corrigido: converter float para BigDecimal

        if (request.getFoto() != null && !request.getFoto().trim().isEmpty()) {
            try {
                String fotoData = request.getFoto().trim();

                if (fotoData.contains(",")) {
                    fotoData = fotoData.substring(fotoData.indexOf(",") + 1);
                }

                fotoData = fotoData.replace(" ", "+");
                byte[] fotoBytes = Base64.getDecoder().decode(fotoData);
                animal.setFoto(fotoBytes);

            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao decodificar Base64 da imagem: " + e.getMessage());
                return ResponseEntity.badRequest().build();
            }
        }

        Animal atualizado = service.atualizar(id, animal);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}