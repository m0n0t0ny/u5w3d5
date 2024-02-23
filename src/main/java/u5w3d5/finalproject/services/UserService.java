package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.exceptions.NotFoundException;
import u5w3d5.finalproject.repositories.UserDAO;

import java.util.UUID;


@Service
public class UserService {

  @Autowired
  private UserDAO userDAO;

  @Value("{$jwt.secret}")
  private String jwtSecret;

  public User registerUser(User user) {
    return userDAO.save(user);
  }

  public String loginUser(String email, String password) {
    return jwtSecret;
  }

  public User findById(UUID userId) {
    return userDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
  }

  public User findByEmail(String email) {
    return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " non trovata"));
  }
}
