package u5w3d5.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import u5w3d5.finalproject.entities.Booking;

import java.util.UUID;

@Repository
public interface BookingDAO extends JpaRepository<Booking, UUID> {
}
