package it.epicode.viaggio.dipendente;

import it.epicode.viaggio.cloudinary.CloudinaryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
    }

    public Dipendente createDipendente(Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new BadRequestException("Username" + dipendente.getUsername() +"già esistente");
        }
        if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new BadRequestException("Email" + dipendente.getEmail() +"già esistente");
        }
        return dipendenteRepository.save(dipendente);
    }

}
