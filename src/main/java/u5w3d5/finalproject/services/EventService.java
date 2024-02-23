package u5w3d5.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u5w3d5.finalproject.entities.Event;
import u5w3d5.finalproject.repositories.EventDAO;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

  @Autowired
  private EventDAO eventRepository;

  @Autowired
  private EventDAO eventDAO;

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public Event getEventById(UUID id) {
    return eventDAO.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
  }

  public Event createEvent(Event event) {
    return eventDAO.save(event);
  }

  public Event updateEvent(UUID id, Event event) {
    if (!eventDAO.existsById(id)) {
      throw new RuntimeException("Event not found with id: " + id);
    }
    event.setId(id);
    return eventDAO.save(event);
  }

  public void deleteEvent(UUID id) {
    if (!eventDAO.existsById(id)) {
      throw new RuntimeException("Event not found with id: " + id);
    }
    eventDAO.deleteById(id);
  }
}
