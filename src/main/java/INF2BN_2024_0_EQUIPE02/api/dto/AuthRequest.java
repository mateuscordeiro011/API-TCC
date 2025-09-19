package INF2BN_2024_0_EQUIPE02.api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}