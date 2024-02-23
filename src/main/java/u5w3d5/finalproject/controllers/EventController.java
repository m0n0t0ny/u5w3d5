package u5w3d5.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import u5w3d5.finalproject.entities.Event;
import u5w3d5.finalproject.services.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/{id}")
  public Event getEventById(@PathVariable UUID id) {
    return eventService.getEventById(id);
  }

  @PostMapping
  public Event createEvent(@RequestBody Event event) {
    return eventService.createEvent(event);
  }

  @PutMapping("/{id}")
  public Event updateEvent(@PathVariable UUID id, @RequestBody Event event) {
    return eventService.updateEvent(id, event);
  }

  @DeleteMapping("/{id}")
  public void deleteEvent(@PathVariable UUID id) {
    eventService.deleteEvent(id);
  }
}
