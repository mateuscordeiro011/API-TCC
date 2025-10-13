package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.dto.AtualizarPerfilDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.ClienteDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.ClienteRepository;
import INF2BN_2024_0_EQUIPE02.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAllBasic();
    }

    public Optional<ClienteDTO> getClienteById(Long id) {
        return clienteRepository.findBasicById(id);
    }

    public Cliente incluir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean atualizarPerfilPorEmail(String email, AtualizarPerfilDTO dados) {

        System.out.println("=== DEBUG FOTO ===");
        System.out.println("Email: " + email);
        System.out.println("Foto recebida: " + (dados.getFoto() != null ? "SIM" : "NÃO"));
        if (dados.getFoto() != null) {
            System.out.println("Tamanho da string foto: " + dados.getFoto().length());
            System.out.println("Primeiros 100 caracteres: "
                    + dados.getFoto().substring(0, Math.min(100, dados.getFoto().length())));
        }
        System.out.println("=== FIM DEBUG ===");
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isEmpty()) {
            return false;
        }

        Cliente cliente = clienteOpt.get();

        // Atualiza dados do cliente
        if (dados.getNome() != null)
            cliente.setNome(dados.getNome());

        // Atualiza senha (criptografada)
        if (dados.getSenha() != null && !dados.getSenha().isEmpty()) {
            cliente.setSenha(passwordEncoder.encode(dados.getSenha()));
        }

        // Atualizar Foto (só atualiza se for fornecida)
        if (dados.getFoto() != null && !dados.getFoto().isEmpty()) {
            System.out.println("Foto recebida (Base64): "
                    + dados.getFoto().substring(0, Math.min(50, dados.getFoto().length())) + "...");
            // Converte Base64 para byte[]
            try {
                // Remove prefixo data:image/...;base64,
                String base64Data = dados.getFoto().replaceFirst("^data:image/\\w+;base64,", "");
                byte[] fotoBytes = java.util.Base64.getDecoder().decode(base64Data);
                cliente.setFoto(fotoBytes);
                System.out.println("Foto convertida para byte[] com tamanho: " + fotoBytes.length);
            } catch (Exception e) {
                System.out.println("Erro ao converter foto: " + e.getMessage());
            }
        }

        // Atualiza endereço
        Endereco endereco = null;
        Long idEndereco = cliente.getIdEndereco();

        if (idEndereco != null) {
            Optional<Endereco> enderecoOpt = enderecoRepository.findById(idEndereco);
            endereco = enderecoOpt.orElse(null);
        }

        if (endereco == null) {
            endereco = new Endereco();
        }

        // ✅ Atualiza TODOS os campos do endereço (incluindo numero)
        if (dados.getCep() != null)
            endereco.setCep(dados.getCep());
        if (dados.getRua() != null)
            endereco.setRua(dados.getRua());
        if (dados.getNumero() != null)
            endereco.setNumero(dados.getNumero()); // 👈 LINHA ADICIONADA
        if (dados.getComplemento() != null)
            endereco.setComplemento(dados.getComplemento());
        if (dados.getBairro() != null)
            endereco.setBairro(dados.getBairro());
        if (dados.getCidade() != null)
            endereco.setCidade(dados.getCidade());
        if (dados.getEstado() != null)
            endereco.setEstado(dados.getEstado());

        // Salva o endereço primeiro
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Atualiza a referência no cliente
        cliente.setIdEndereco(enderecoSalvo.getId_endereco()); // Corrigido: getId() em vez de getId_endereco()

        // Salva o cliente
        clienteRepository.save(cliente);
        return true;
    }

    public boolean atualizarFotoPorEmail(String email, MultipartFile foto) throws IOException {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isEmpty())
            return false;

        Cliente cliente = clienteOpt.get();
        cliente.setFoto(foto.getBytes());
        clienteRepository.save(cliente);
        return true;
    }

    public Optional<Map<String, Object>> getPerfilClienteByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(cliente -> {
                    Map<String, Object> perfil = new HashMap<>();
                    perfil.put("id", cliente.getId());
                    perfil.put("nome", cliente.getNome());
                    perfil.put("email", cliente.getEmail());
                    perfil.put("cpf", cliente.getCpf());

                    // Foto em Base64
                    if (cliente.getFoto() != null) {
                        String base64 = java.util.Base64.getEncoder().encodeToString(cliente.getFoto());
                        perfil.put("foto", base64);
                    }

                    // Endereço
                    if (cliente.getIdEndereco() != null) {
                        enderecoRepository.findById(cliente.getIdEndereco())
                                .ifPresent(endereco -> {
                                    Map<String, Object> endMap = new HashMap<>();
                                    endMap.put("cep", endereco.getCep());
                                    endMap.put("rua", endereco.getRua());
                                    endMap.put("numero", endereco.getNumero());
                                    endMap.put("complemento", endereco.getComplemento());
                                    endMap.put("bairro", endereco.getBairro());
                                    endMap.put("cidade", endereco.getCidade());
                                    endMap.put("estado", endereco.getEstado());
                                    perfil.put("endereco", endMap);
                                });
                    }

                    return perfil;
                });
    }
}