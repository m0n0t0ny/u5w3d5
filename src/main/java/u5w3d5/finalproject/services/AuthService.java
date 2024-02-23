package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.Role;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.exceptions.BadRequestException;
import u5w3d5.finalproject.exceptions.UnauthorizedException;
import u5w3d5.finalproject.payload.user.NewUserDTO;
import u5w3d5.finalproject.payload.user.UserLoginDTO;
import u5w3d5.finalproject.repositories.UserDAO;
import u5w3d5.finalproject.security.JWTTools;

@Service
public class AuthService {
  @Autowired
  private UserService usersService;

  @Autowired
  private PasswordEncoder bcrypt;

  @Autowired
  private UserDAO usersDAO;

  @Autowired
  private JWTTools jwtTools;

  public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
    User user = usersService.findByEmail(payload.email());
    if (bcrypt.matches(payload.password(), user.getPassword())) {
      return jwtTools.createToken(user);
    } else {
      throw new UnauthorizedException("Credenziali sbagliate!");
    }
  }

  public User saveUser(NewUserDTO payload) {
    usersDAO.findByEmail(payload.email()).ifPresent(user -> {
      throw new BadRequestException("L'indirizzo email '" + payload.email() + "' è già in uso");
    });
    User newUser = new User();
    newUser.setEmail(payload.email());
    newUser.setName(payload.name());
    newUser.setSurname(payload.surname());
    newUser.setEmail(payload.email());
    newUser.setPassword(bcrypt.encode(payload.password()));
    newUser.setRole(Role.USER);
    return usersDAO.save(newUser);
  }
}
