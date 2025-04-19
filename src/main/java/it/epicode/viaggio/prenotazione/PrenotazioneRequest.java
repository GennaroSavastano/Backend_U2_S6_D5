package it.epicode.viaggio.prenotazione;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneRequest {

    @NotNull(message = "Il campo 'dipendenteId' è obbligatorio")
    private Long dipendenteId;

    @NotNull(message = "Il campo 'viaggioId' è obbligatorio")
    private Long viaggioId;

    private String notePreferenze;
}
