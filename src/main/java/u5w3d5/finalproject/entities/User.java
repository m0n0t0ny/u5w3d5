package u5w3d5.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(unique = true)
  private String email;
  private String password;
  private boolean isOrganizer;

  @OneToMany(mappedBy = "user")
  private List<Booking> bookings = new ArrayList<>();

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }
}