package u5w3d5.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public User registerUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @PostMapping("/login")
  public String loginUser(@RequestParam String username, @RequestParam String password) {
    return userService.loginUser(username, password);
  }
}
