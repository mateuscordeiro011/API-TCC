package INF2BN_2024_0_EQUIPE02.api.dto;

public class FuncionarioDTO {

    private Long id_funcionario;
    private String nome;
    private String cargo;
    private String email;
    private String cpf;
    private float salario;
    private Long id_endereco;
    private byte[] foto;

    public FuncionarioDTO(
            Long id_funcionario,
            String nome,
            String cargo,
            String email,
            String cpf,
            float salario,
            Long id_endereco,
            byte[] foto) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.cpf = cpf;
        this.salario = salario;
        this.id_endereco = id_endereco;
        this.foto = foto;
    }

    public FuncionarioDTO(
            Long id_funcionario,
            String nome,
            String cargo,
            String email,
            float salario,
            Long id_endereco,
            byte[] foto) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.salario = salario;
        this.id_endereco = id_endereco;
        this.foto = foto;
    }
    

     private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }




    public Long getId_Funcionario() { return id_funcionario; }
    public void setId_Funcionario(Long id_funcionario) { this.id_funcionario = id_funcionario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

        public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getSalario() { return salario; }
    public void setSalario(float salario) { this.salario = salario; }

    public Long getId_endereco() { return id_endereco; }
    public void setId_endereco(Long id_endereco) { this.id_endereco = id_endereco; }

    public byte[] getFoto() { 
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}