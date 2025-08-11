package INF2BN_2024_0_EQUIPE02.api.dto;

public class FuncionarioDTO {
    private Long id_Funcionario;
    private String nome;
    private String cargo;
    private float salario;
    private Long id_endereco;
    private byte[] foto;

    public FuncionarioDTO(Long id_Funcionario, String nome, String cargo, float salario, Long id_endereco, byte[] foto) {
        this.id_Funcionario = id_Funcionario;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.id_endereco = id_endereco;
        this.foto = foto;
    }

    public Long getId_Funcionario() {
        return id_Funcionario;
    }

    public void setId_Funcionario(Long id_Funcionario) {
        this.id_Funcionario = id_Funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Long getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(Long id_endereco) {
        this.id_endereco = id_endereco;
    }

    public byte[] isFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}