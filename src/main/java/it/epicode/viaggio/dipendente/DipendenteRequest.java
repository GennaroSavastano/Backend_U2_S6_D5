package it.epicode.viaggio.dipendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DipendenteRequest {

    @NotBlank(message = "Il campo 'username' è non può essere vuoto")
    private String username;

    @NotBlank(message = "Il campo 'nome' è non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il campo 'cognome' è non può essere vuoto")
    private String cognome;

    @NotBlank(message = "Il campo 'email' è non può essere vuoto")
    @Email(message = "Inserisci un indirizzo email valido")
    private String email;
}
