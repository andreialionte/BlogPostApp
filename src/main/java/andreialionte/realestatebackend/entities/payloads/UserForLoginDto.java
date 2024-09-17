package andreialionte.realestatebackend.entities.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForLoginDto {
    public String email;
    public String password;
}
