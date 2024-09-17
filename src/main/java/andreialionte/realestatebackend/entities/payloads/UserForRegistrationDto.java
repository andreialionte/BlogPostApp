package andreialionte.realestatebackend.entities.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForRegistrationDto {
//    private Long id;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
