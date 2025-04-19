package it.epicode.viaggio.viaggio;

import it.epicode.viaggio.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    public Viaggio getViaggioById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));
    }

    public Viaggio createViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Long id, Viaggio viaggio) {
        Viaggio existingViaggio = getViaggioById(id);

        existingViaggio.setDestinazione(viaggio.getDestinazione());
        existingViaggio.setDataViaggio(viaggio.getDataViaggio());

        return viaggioRepository.save(existingViaggio);
    }

    public void deleteViaggio(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));
        viaggioRepository.deleteById(id);
    }

    public Viaggio updateStatoViaggio(Long id, StatoViaggio nuovoStatoViaggio) {
        Viaggio viaggio = getViaggioById(id);
        viaggio.setStato(nuovoStatoViaggio);
        return viaggioRepository.save(viaggio);
    }
}
