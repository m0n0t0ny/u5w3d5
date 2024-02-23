package u5w3d5.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String title;
  private String description;
  private LocalDateTime date;
  private String location;
  private int availableSeats;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
  private List<Booking> bookings = new ArrayList<>();

  public Event(String title, String description, LocalDateTime date, String location, int availableSeats) {
    this.title = title;
    this.description = description;
    this.date = date;
    this.location = location;
    this.availableSeats = availableSeats;
  }

  public void addBooking(Booking booking) {
    bookings.add(booking);
  }

  public void removeBooking(Booking booking) {
    bookings.remove(booking);
  }
}