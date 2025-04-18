package it.epicode.viaggio.prenotazione;

import it.epicode.viaggio.dipendente.Dipendente;
import it.epicode.viaggio.viaggio.Viaggio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prenotazioni")

public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @NotNull(message = "Il campo 'dipendente' è obbligatorio")
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @NotNull(message = "Il campo 'viaggio' è obbligatorio")
    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @NotNull(message = "Il campo 'dataRichiesta' è obbligatorio")
    @Column(nullable = false)
    private LocalDate dataRichiesta;

    private String notePreferenze;

}