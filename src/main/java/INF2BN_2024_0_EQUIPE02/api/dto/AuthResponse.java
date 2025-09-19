package INF2BN_2024_0_EQUIPE02.api.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tipo;
    private Long id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}