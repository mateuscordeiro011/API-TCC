package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Animal;
import INF2BN_2024_0_EQUIPE02.api.dto.AnimalResponse;
import INF2BN_2024_0_EQUIPE02.api.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<AnimalResponse> listarAnimais() {
        return animalRepository.findAll().stream()
                .map(this::toAnimalResponse)
                .collect(Collectors.toList());
    }

    public Optional<AnimalResponse> getAnimalById(Long id) {
        return animalRepository.findById(id)
                .map(this::toAnimalResponse);
    }

    private AnimalResponse toAnimalResponse(Animal animal) {
        AnimalResponse res = new AnimalResponse();
        res.setId(animal.getId());
        res.setNome(animal.getNome());
        res.setEspecie(animal.getEspecie());
        res.setRaca(animal.getRaca());
        res.setSexo(animal.getSexo());
        res.setData_Nascimento(animal.getDataNascimento());

        // Proteção contra null
        if (animal.getPeso() != null) {
            res.setPeso(animal.getPeso().floatValue());
        } else {
            res.setPeso(0.0f); // ou null, dependendo da sua lógica
        }

        res.setId_cliente(animal.getIdCliente());

        // Proteção contra null na foto
        if (animal.getFoto() != null && animal.getFoto().length > 0) {
            try {
                res.setFoto(Base64.getEncoder().encodeToString(animal.getFoto()));
            } catch (Exception e) {
                System.err.println("Erro ao codificar foto do animal ID " + animal.getId() + ": " + e.getMessage());
                res.setFoto(null); // Define como null em caso de erro
            }
        } else {
            res.setFoto(null);
        }

        return res;
    }

    public Animal incluir(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal atualizar(Long id, Animal animal) {
        if (animalRepository.existsById(id)) {
            animal.setId(id); // ✅ Correto: usando o setter da entidade
            return animalRepository.save(animal);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}