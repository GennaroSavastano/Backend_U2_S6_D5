package it.epicode.viaggio.dipendente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Dipendente> findByEmail(String email);
    Optional<Dipendente> findByUsername(String username);
}
