package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.Role;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.exceptions.BadRequestException;
import u5w3d5.finalproject.exceptions.UnauthorizedException;
import u5w3d5.finalproject.payload.login.UserLoginDTO;
import u5w3d5.finalproject.payload.user.UserDTO;
import u5w3d5.finalproject.repositories.UserDAO;
import u5w3d5.finalproject.security.JWTTools;

@Service
public class AuthService {
  @Autowired
  private UserService userService;
  @Autowired
  private JWTTools jwTtools;
  @Autowired
  private UserDAO userDAO;
  @Autowired
  private PasswordEncoder bcrypt;

  public String authenticateUserAndGenerateToken(UserLoginDTO body) {
    User user = userService.findByEmail(body.email());
    if (bcrypt.matches(body.password(), user.getPassword())) {
      return jwTtools.createToken(user);
    } else {
      throw new UnauthorizedException("Credenziali non valide!!");
    }
  }

  public User save(UserDTO body) {
    userDAO.findByEmail(body.email()).ifPresent(user -> {
      throw new BadRequestException("email " + user.getEmail() + " già usata!!!");
    });
    User newUser = new User();
    newUser.setName(body.name());
    newUser.setSurname(body.surname());
    newUser.setEmail(body.email());
    newUser.setPassword(bcrypt.encode(body.password()));
    newUser.setRole(Role.USER);
    return userDAO.save(newUser);
  }
}
