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

    @Autowired  // ✅ Adicionado: injeção do repositório
    private AnimalRepository repository;

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> listaranimais() {
        List<AnimalResponse> animais = service.listaranimais();
        return ResponseEntity.ok(animais);
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
        animal.setData_Nascimento(request.getData_Nascimento());
        animal.setPeso(request.getPeso()); 

 
        if (request.getFoto() != null && !request.getFoto().trim().isEmpty()) {
            try {
                String fotoData = request.getFoto().trim();

                if (fotoData.contains(",")) {
                    fotoData = fotoData.substring(fotoData.indexOf(",") + 1);
                }

                // Substitui espaços por '+' (caso tenha sido codificado assim)
                fotoData = fotoData.replace(" ", "+");

                // Decodifica base64 para byte[]
                byte[] fotoBytes = Base64.getDecoder().decode(fotoData);
                animal.setFoto(fotoBytes); // ✅ define a foto

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
        animal.setData_Nascimento(request.getData_Nascimento());
        animal.setPeso(request.getPeso());

        // Atualiza foto apenas se enviada
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