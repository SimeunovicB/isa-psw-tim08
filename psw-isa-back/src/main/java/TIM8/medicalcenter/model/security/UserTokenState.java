package TIM8.medicalcenter.model.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Version;

@Getter
@Setter
public class UserTokenState {
    private String accessToken;
    private Long expiresIn;

    @Version
    private Long version = 0L;


    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

}
