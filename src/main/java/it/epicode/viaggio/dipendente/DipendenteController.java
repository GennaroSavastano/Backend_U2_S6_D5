package it.epicode.viaggio.dipendente;

import it.epicode.viaggio.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createDipendente(@RequestBody @Valid Dipendente dipendente) {
         dipendenteService.createDipendente(dipendente);
             return new CommonResponse(dipendente.getId());
    }

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(Long id) {
        return dipendenteService.getDipendenteById(id);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable Long id, @RequestBody @Valid Dipendente updatedDipendente) {
        return dipendenteService.updateDipendente(id, updatedDipendente);
    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }

    @PatchMapping (value= "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Dipendente uploadImmagineProfilo(@PathVariable Long id, @RequestParam MultipartFile file)  {
        dipendenteService.uploadImmagineProfilo(id, file);
        return dipendenteService.getDipendenteById(id);
    }
}
