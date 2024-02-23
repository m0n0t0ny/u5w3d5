package u5w3d5.finalproject.payload.Booking;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDTO(
        @NotNull(message = "l'id dell' evento è un campo obbligatorio!")
        UUID event_id,
        @NotNull(message = "Il numero dei partecipanti è un campo obbligatorio!")
        int participants,
        @NotNull(message = "l'id dell'user è un campo obbligatorio!")
        UUID user_id,
        @NotEmpty(message = "la data è obbligatoria")
        LocalDate date
) {
}
