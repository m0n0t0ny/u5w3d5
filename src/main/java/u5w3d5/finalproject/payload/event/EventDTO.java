package u5w3d5.finalproject.payload.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

;

public record EventDTO(
        @NotEmpty(message = "titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il titolo deve avere minimo 3 caratteri, massimo 30")
        String title,
        @NotEmpty(message = "la descrizione è obbligatoria")
        @Size(min = 3, max = 30, message = "la descrizione deve avere minimo 3 caratteri, massimo 30")
        String description,
        @NotEmpty(message = "la data è obbligatoria")
        LocalDate date,
        @NotEmpty(message = "il luogo è obbligatoria")
        @Size(min = 3, max = 30, message = "il luogo deve avere minimo 3 caratteri, massimo 30")
        String location,
        @NotNull(message = "posti disponibili è un campo obbligatorio!")
        Integer availableSeats
) {
}
