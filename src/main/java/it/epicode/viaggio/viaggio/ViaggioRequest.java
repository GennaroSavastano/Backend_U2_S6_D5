package it.epicode.viaggio.viaggio;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioRequest {

    @NotBlank(message = "Il campo 'destinazione' è obbligatorio")
    private String destinazione;

    @NotNull(message = "Il campo 'dataViaggio' è obbligatorio")
    @FutureOrPresent(message = "La data del viaggio deve essere oggi o futura")
    private LocalDate dataViaggio;
}
