package it.epicode.viaggio.prenotazione;

import it.epicode.viaggio.dipendente.Dipendente;
import it.epicode.viaggio.dipendente.DipendenteRepository;
import it.epicode.viaggio.exceptions.BadRequestException;
import it.epicode.viaggio.exceptions.ResourceNotFoundException;
import it.epicode.viaggio.viaggio.Viaggio;
import it.epicode.viaggio.viaggio.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    DipendenteRepository dipendenteRepository;

    @Autowired
    ViaggioRepository viaggioRepository;

    public Prenotazione createPrenotazione(Prenotazione prenotazione) {
        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        LocalDate  dataViaggio = viaggio.getDataViaggio();
        boolean prenotazioneEsistente = prenotazioneRepository.existsByDipendenteAndDataViaggio(dipendente, dataViaggio);

        if (prenotazioneEsistente) {
            throw new BadRequestException("Il dipendente ha già prenotato un viaggio per questa data");
        }

        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggio(viaggio);
        nuovaPrenotazione.setDataRichiesta(LocalDate.now());
        nuovaPrenotazione.setNotePreferenze(prenotazione.getNotePreferenze());

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }
    public List<Prenotazione> getPrenotazioniByDipendente(Long dipendenteId) {
        if (!dipendenteRepository.existsById(dipendenteId)) {
            throw new ResourceNotFoundException("Dipendente non trovato");
        }
        return prenotazioneRepository.findByDipendenteId(dipendenteId);
    }

    public List<Prenotazione> getPrenotazioniByViaggio(Long viaggioId) {
        if (!viaggioRepository.existsById(viaggioId)) {
            throw new ResourceNotFoundException("Viaggio non trovato");
        }
        return prenotazioneRepository.findByViaggioId(viaggioId);
    }

    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata"));
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        prenotazioneRepository.deleteById(id);
    }
}
