package br.com.cast.avaliacao.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType = "Bearer";
    @JsonProperty("expires_in")
    private String expiresIn = "7200";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}