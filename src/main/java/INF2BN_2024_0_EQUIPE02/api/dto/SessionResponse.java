package INF2BN_2024_0_EQUIPE02.api.dto;

public class SessionResponse {
    private boolean logado;
    private String tipo;
    private String nome;

    public SessionResponse(boolean logado, String tipo, String nome) {
        this.logado = logado;
        this.tipo = tipo;
        this.nome = nome;
    }

    // Getters e Setters
    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}