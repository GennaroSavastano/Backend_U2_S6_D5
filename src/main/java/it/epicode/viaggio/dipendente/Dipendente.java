package it.epicode.viaggio.dipendente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dipendenti")

public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il campo 'username' è obbligatorio")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Il campo 'nome' è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Il campo 'cognome' è obbligatorio")
    @Column(nullable = false)
    private String cognome;

    @NotBlank(message = "Il campo 'email' è obbligatorio")
    @Email(message = "Inserisci un indirizzo email valido")
    @Column(unique = true, nullable = false)
    private String email;

    private String immagineProfiloUrl;
}
