package it.epicode.viaggio.dipendente;

import it.epicode.viaggio.cloudinary.CloudinaryService;
import it.epicode.viaggio.exceptions.BadRequestException;
import it.epicode.viaggio.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
    }

    public Dipendente createDipendente(Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new BadRequestException("Username" + dipendente.getUsername() +"già esistente");
        }
        if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new BadRequestException ("Email" + dipendente.getEmail() +"già esistente");
        }
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Long id, Dipendente dipendente) {
        Dipendente existingDipendente = getDipendenteById(id);
        if (!existingDipendente.getUsername().equals(dipendente.getUsername()) && dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new RuntimeException ("Username" + dipendente.getUsername() +"già esistente");
        }
        if (!existingDipendente.getEmail().equals(dipendente.getEmail()) && dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new RuntimeException ("Email" + dipendente.getEmail() +"già esistente");
        }

        existingDipendente.setUsername(dipendente.getUsername());
        existingDipendente.setNome(dipendente.getNome());
        existingDipendente.setCognome(dipendente.getCognome());
        existingDipendente.setEmail(dipendente.getEmail());

        return dipendenteRepository.save(existingDipendente);
    }

    public void deleteDipendente(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        dipendenteRepository.deleteById(id);
    }

    public void uploadImmagineProfilo(Long id, MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        dipendente.setImmagineProfiloUrl(cloudinaryService.uploadImage(file));
        dipendenteRepository.save(dipendente);
    }

}
