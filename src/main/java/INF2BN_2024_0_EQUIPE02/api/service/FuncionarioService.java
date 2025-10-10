package INF2BN_2024_0_EQUIPE02.api.service;

import INF2BN_2024_0_EQUIPE02.api.domain.Endereco;
import INF2BN_2024_0_EQUIPE02.api.domain.Funcionario;
import INF2BN_2024_0_EQUIPE02.api.dto.AtualizarPerfilFuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.FuncionarioDTO;
import INF2BN_2024_0_EQUIPE02.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<FuncionarioDTO> listarFuncionarios() {
        return funcionarioRepository.findAllBasic();
    }

    public Optional<FuncionarioDTO> getFuncionarioById(Long id) {
        return funcionarioRepository.findBasicById(id);
    }

    public Funcionario incluir(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionario) {
        if (funcionarioRepository.existsById(id)) {
            funcionario.setId(id); // ✅ Corrigido: usando setId() em vez de setId_Funcionario()
            return funcionarioRepository.save(funcionario);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Map<String, Object>> getPerfilFuncionarioByEmail(String email) {
        return funcionarioRepository.findByEmail(email).map(func -> {
            Map<String, Object> perfil = new HashMap<>();
            perfil.put("id", func.getId());
            perfil.put("nome", func.getNome());
            perfil.put("email", func.getEmail());
            perfil.put("cpf", func.getCpf());
            perfil.put("foto", func.getFoto());
            perfil.put("endereco", func.getEndereco());
            return perfil;
        });
    }

    // Método para atualizar perfil (igual ao do cliente)
    public boolean atualizarPerfilPorEmail(String email, AtualizarPerfilFuncionarioDTO dto) {
        Optional<Funcionario> optFunc = funcionarioRepository.findByEmail(email);
        if (optFunc.isEmpty()) {
            return false;
        }

        Funcionario func = optFunc.get();

        // Atualiza nome
        if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) {
            func.setNome(dto.getNome().trim());
        }

        // Atualiza senha (com criptografia)
        if (dto.getSenha() != null && !dto.getSenha().trim().isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(dto.getSenha().trim());
            func.setSenha(senhaCriptografada);
        }

        // Trata o endereço
        Endereco endereco = func.getEndereco();
        if (endereco == null) {
            endereco = new Endereco(); // Cria novo se não existir
            func.setEndereco(endereco);
        }

        // Atualiza os campos do endereço (mesmo que vazios, para permitir limpar)
        endereco.setCep(dto.getCep() != null ? dto.getCep().trim() : "");
        endereco.setRua(dto.getRua() != null ? dto.getRua().trim() : "");
        endereco.setNumero(dto.getNumero() != null ? dto.getNumero().trim() : "");
        endereco.setComplemento(dto.getComplemento() != null ? dto.getComplemento().trim() : "");
        endereco.setBairro(dto.getBairro() != null ? dto.getBairro().trim() : "");
        endereco.setCidade(dto.getCidade() != null ? dto.getCidade().trim() : "");
        endereco.setEstado(dto.getEstado() != null ? dto.getEstado().trim() : "");

        funcionarioRepository.save(func);
        return true;
    }

    public boolean atualizarFotoPorEmail(String email, MultipartFile foto) throws Exception {
        Optional<Funcionario> optFunc = funcionarioRepository.findByEmail(email);
        if (optFunc.isEmpty()) {
            return false;
        }

        Funcionario func = optFunc.get();
        // ✅ Converte para Base64 e salva como String
        String fotoBase64 = java.util.Base64.getEncoder().encodeToString(foto.getBytes());
        func.setFoto(fotoBase64);
        funcionarioRepository.save(func);
        return true;
    }


}