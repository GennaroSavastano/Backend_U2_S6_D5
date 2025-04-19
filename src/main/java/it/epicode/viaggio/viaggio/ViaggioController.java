package it.epicode.viaggio.viaggio;

import it.epicode.viaggio.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createViaggio(@RequestBody @Valid Viaggio viaggio) {
         viaggioService.createViaggio(viaggio);
         return new CommonResponse(viaggio.getId());
    }

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.getAllViaggi();
    }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) {
        return viaggioService.getViaggioById(id);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody Viaggio updatedViaggio) {
        return viaggioService.updateViaggio(id, updatedViaggio);
    }

    @PutMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestBody StatoViaggio nuovoStatoViaggio) {
        return viaggioService.updateStatoViaggio(id, nuovoStatoViaggio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
    }
}
