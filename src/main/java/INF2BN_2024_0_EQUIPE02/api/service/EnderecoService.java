package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.dto.EnderecoDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository repository;

    public List<EnderecoDTO> listarEnderecos() {
        return enderecoRepository.findAllBasic();
    }

    // ✅ Adicione este método
    public List<EnderecoDTO> listarEnderecosPorCliente(Long clienteId) {
        Optional<EnderecoDTO> enderecoOpt = getEnderecoPrincipalDoCliente(clienteId);
        if (enderecoOpt.isPresent()) {
            List<EnderecoDTO> lista = new ArrayList<>();
            lista.add(enderecoOpt.get());
            return lista;
        }
        return new ArrayList<>(); // Retorna lista vazia
    }

    public Optional<EnderecoDTO> getEnderecoById(Long id) {
        return enderecoRepository.findBasicById(id);
    }

    public Endereco incluir(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if (enderecoRepository.existsById(id)) {
            endereco.setId_endereco(id);
            return enderecoRepository.save(endereco);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean associarEnderecoAoCliente(Long idEndereco, Long idCliente) {
        try {
            // Busca o cliente pelo ID
            Optional<Cliente> clienteOpt = repository.findById(idCliente);
            if (clienteOpt.isPresent()) {
                Cliente cliente = clienteOpt.get();
                // Atualiza o campo Id_endereco do cliente
                cliente.setIdEndereco(idEndereco);
                // Salva o cliente atualizado
                repository.save(cliente);
                return true;
            } else {
                // Cliente nao encontrado
                return false;
            }
        } catch (Exception e) {
            // Log do erro
            e.printStackTrace(); // Use um logger em producao
            return false; // Falha na associacao
        }
    }


    public Optional<EnderecoDTO> getEnderecoPrincipalDoCliente(Long idCliente) {
        try {
            Optional<Cliente> clienteOpt = repository.findById(idCliente);
            if (clienteOpt.isPresent()) {
                Cliente cliente = clienteOpt.get();
                Long idEnderecoPrincipal = cliente.getIdEndereco();
                if (idEnderecoPrincipal != null) {
                    return enderecoRepository.findBasicById(idEnderecoPrincipal);
                }
            }
            return Optional.empty(); // Cliente nao encontrado ou sem endereco principal
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty(); // Erro ao buscar
        }
    }

}