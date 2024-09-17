package andreialionte.realestatebackend.business.concretes;

import andreialionte.realestatebackend.business.abstracts.UserService;
import andreialionte.realestatebackend.entities.User;
import andreialionte.realestatebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {
//    public String generateToken(Auth auth);
//    public Claims validateToken(String token);

    private final UserRepository userRepository;
    public UserManager(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public List<User> GetUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> GetUser(Long id) { //optional means it can be null like object user or null
        return userRepository.findById(id);
    }
}
