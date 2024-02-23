package u5w3d5.finalproject.payload.error;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}
