package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Animal;
import INF2BN_2024_0_EQUIPE02.api.dto.AnimalDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<AnimalDTO> listarAnimal() {
        return animalRepository.findAllBasic();
    }

    public Optional<AnimalDTO> getAnimalById(Long id) {
        return animalRepository.findBasicById(id);
    }

    // Deixe o save com a entidade Animal (não é problema salvar como entidade)
    public Animal incluir(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal atualizar(Long id, Animal animal) {
        if (animalRepository.existsById(id)) {
            animal.setId_animal(id);
            return animalRepository.save(animal);
        }
        return null;
    }
}