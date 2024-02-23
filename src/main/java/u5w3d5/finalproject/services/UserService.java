package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.exceptions.NotFoundException;
import u5w3d5.finalproject.repositories.UserDAO;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {
  @Autowired
  private UserDAO userDAO;

  public List<User> getUsers() {
    return this.userDAO.findAll();
  }

  public User findById(UUID id) {
    return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
  }

  public User findByEmail(String email) {
    return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!!!"));
  }

  public User findByIdAndUpdate(UUID id, User body) {
    User found = this.findById(id);
    if (body.getName() != null) {
      found.setName(body.getName());
    }
    if (body.getSurname() != null) {
      found.setSurname(body.getSurname());
    }
    if (body.getEmail() != null) {
      found.setEmail(body.getEmail());
    }
    if (body.getPassword() != null) {
      found.setPassword(body.getPassword());
    }
    if (body.getRole() != null) {
      found.setRole(body.getRole());
    }

    return userDAO.save(found);
  }
}
