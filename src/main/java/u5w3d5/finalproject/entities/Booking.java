package u5w3d5.finalproject.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Booking {
  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private int participants;
  private LocalDate bookingDate;
  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
