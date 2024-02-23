package u5w3d5.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import u5w3d5.finalproject.entities.Event;
import u5w3d5.finalproject.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface EventDAO extends JpaRepository<Event, UUID> {
  Optional<Event> findById(UUID id);
}
