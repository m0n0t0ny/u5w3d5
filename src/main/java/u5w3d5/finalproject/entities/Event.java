package u5w3d5.finalproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Event {
  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String title;
  private String description;
  private LocalDate date;
  private String location;
  private int availableSeats;
  @OneToMany(mappedBy = "event")
  @JsonIgnore
  private List<Booking> bookings;
}