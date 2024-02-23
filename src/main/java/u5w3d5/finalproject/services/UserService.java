package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.repositories.UserRepository;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Value("{$jwt.secret}")
  private String jwtSecret;

  public User registerUser(User user) {
    return userRepository.save(user);
  }

  public String loginUser(String email, String password) {
    return jwtSecret;
  }
}
