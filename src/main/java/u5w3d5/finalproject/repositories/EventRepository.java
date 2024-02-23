package u5w3d5.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import u5w3d5.finalproject.entities.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
