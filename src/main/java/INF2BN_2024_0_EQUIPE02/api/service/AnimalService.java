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
    public List<AnimalResponse> listaranimais() {
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
    res.setId_animal(animal.getId_animal());
    res.setNome(animal.getNome());
    res.setEspecie(animal.getEspecie());
    res.setRaca(animal.getRaca());
    res.setSexo(animal.getSexo());

    res.setData_Nascimento(animal.getData_Nascimento());

    res.setPeso(animal.getPeso());
    res.setId_cliente(animal.getCliente() != null ? animal.getCliente().getId_cliente() : null);

    if (animal.getFoto() != null && animal.getFoto().length > 0) {
        res.setFoto(Base64.getEncoder().encodeToString(animal.getFoto()));
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
            animal.setId_animal(id);
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