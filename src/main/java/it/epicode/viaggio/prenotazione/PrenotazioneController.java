package it.epicode.viaggio.prenotazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(Prenotazione prenotazione) {
        return prenotazioneService.createPrenotazione(prenotazione);
    }

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Long id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @GetMapping("/dipendente/{dipendenteId}")
    public List<Prenotazione> getPrenotazioniByDipendente(@PathVariable Long dipendenteId) {
        return prenotazioneService.getPrenotazioniByDipendente(dipendenteId);
    }

    @GetMapping("/viaggio/{viaggioId}")
    public List<Prenotazione> getPrenotazioniByViaggio(@PathVariable Long viaggioId) {
        return prenotazioneService.getPrenotazioniByViaggio(viaggioId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
    }
}
