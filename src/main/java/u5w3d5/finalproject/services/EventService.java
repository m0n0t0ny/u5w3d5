package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.Event;
import u5w3d5.finalproject.exceptions.BadRequestException;
import u5w3d5.finalproject.exceptions.NotFoundException;
import u5w3d5.finalproject.payload.event.EventDTO;
import u5w3d5.finalproject.repositories.EventDAO;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
  @Autowired
  private EventDAO eventDAO;

  public List<Event> getAllEvents() {
    return this.eventDAO.findAll();
  }

  public Event findById(UUID uuid) {
    return eventDAO.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
  }

  public Event findByIdAndUpdate(UUID uuid, Event body) {
    Event found = this.findById(uuid);
    if (body.getTitle() != null) {
      found.setTitle(body.getTitle());
    }
    if (body.getDescription() != null) {
      found.setDescription(body.getDescription());
    }
    if (body.getDate() != null) {
      found.setDate(body.getDate());
    }
    if (body.getLocation() != null) {
      found.setLocation(body.getLocation());
    }
    if (body.getAvailableSeats() != 0) {
      found.setAvailableSeats(body.getAvailableSeats());
    }

    return eventDAO.save(found);
  }

  public void findByIdAndDelete(UUID uuid) {
    Event found = this.findById(uuid);
    if (!found.getBookings().isEmpty()) {
      throw new BadRequestException("ATTENZIONE impossibile cancellare Evento di id: " + uuid + " è associato a uno o più prenotazioni.");
    }
    eventDAO.delete(found);
  }

  public Event saveEvent(EventDTO payload) {
    Event newEvent = new Event();
    newEvent.setTitle(payload.title());
    newEvent.setDate(payload.date());
    newEvent.setDescription(payload.description());
    newEvent.setLocation(payload.location());
    newEvent.setAvailableSeats(payload.availableSeats());
    return eventDAO.save(newEvent);
  }

}
