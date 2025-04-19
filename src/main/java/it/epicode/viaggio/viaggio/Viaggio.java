package it.epicode.viaggio.viaggio;



import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viaggi")

public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il campo 'destinazione' è obbligatorio")
    @Column(nullable = false)
    private String  destinazione;

    @NotNull(message = "Il campo 'dataViaggio' è obbligatorio")
    @FutureOrPresent(message = "La data del viaggio deve essere oggi o futura")
    @Column(nullable = false)
    private LocalDate dataViaggio;

    @NotNull(message = "Il campo 'stato' è obbligatorio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

}
