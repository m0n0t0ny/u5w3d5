package u5w3d5.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.payload.login.UserLoginDTO;
import u5w3d5.finalproject.payload.login.UserLoginResponseDTO;
import u5w3d5.finalproject.payload.user.UserDTO;
import u5w3d5.finalproject.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
    String token = authService.authenticateUserAndGenerateToken(body);
    return new UserLoginResponseDTO(token);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public User saveUser(@RequestBody UserDTO newUser) {
    return this.authService.save(newUser);
  }
}
