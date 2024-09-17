package andreialionte.realestatebackend.entities.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    private String email;
//    private String passwordsalt;
    private String passwordhash;
}
