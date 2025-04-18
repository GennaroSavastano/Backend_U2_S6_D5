package it.epicode.viaggio.prenotazione;

import it.epicode.viaggio.dipendente.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>{
    // verifica se un dipendente ha già prenotato un viaggio per una data specifica
    @Query("SELECT COUNT(p) > 0 FROM Prenotazione p WHERE p.dipendente.id = ?1 AND p.dataViaggio = ?2")
    boolean existsByDipendenteAndDataViaggio(Dipendente dipendente, LocalDate dataViaggio);

    List<Prenotazione> findByDipendenteId(Long id);
    List <Prenotazione> findByViaggioId(Long id);
}
