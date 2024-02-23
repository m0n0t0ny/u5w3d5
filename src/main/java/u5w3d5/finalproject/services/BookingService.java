package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.Booking;
import u5w3d5.finalproject.entities.Event;
import u5w3d5.finalproject.entities.User;
import u5w3d5.finalproject.exceptions.NotFoundException;
import u5w3d5.finalproject.payload.Booking.BookingDTO;
import u5w3d5.finalproject.repositories.BookingDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
  @Autowired
  private BookingDAO bookingDAO;
  @Autowired
  private UserService userService;
  @Autowired
  private EventService eventService;

  public List<Booking> getBookings() {
    return this.bookingDAO.findAll();
  }

  public Booking saveBooking(BookingDTO body) {
    User user = userService.findById(body.user_id());
    Event event = eventService.findById(body.event_id());
    Booking booking = new Booking();
    booking.setUser(user);
    booking.setEvent(event);
    booking.setBookingDate(LocalDate.now());
    booking.setParticipants(body.participants());
    return bookingDAO.save(booking);
  }

  public Booking findById(UUID id) {
    return bookingDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
  }

  public Booking findByIdAndUpdate(UUID id, BookingDTO body) {
    Booking found = this.findById(id);
    User user = userService.findById(body.user_id());
    Event event = eventService.findById(body.event_id());
    found.setBookingDate(LocalDate.now());
    found.setEvent(event);
    found.setUser(user);
    return bookingDAO.save(found);
  }

  public void findByIdAndDelete(UUID id) {
    Booking found = this.findById(id);
    bookingDAO.delete(found);
  }
}
