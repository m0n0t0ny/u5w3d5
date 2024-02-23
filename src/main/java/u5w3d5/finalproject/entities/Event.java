package u5w3d5.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String title;
  private String description;
  private LocalDateTime date;
  private String location;
  private int availableSeats;

  @OneToMany(mappedBy = "event")
  private List<Booking> bookings = new ArrayList<>();
}