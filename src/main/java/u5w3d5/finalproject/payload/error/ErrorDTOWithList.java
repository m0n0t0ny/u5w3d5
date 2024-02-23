package u5w3d5.finalproject.payload.error;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTOWithList(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList
) {
}
