package app.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, String> {
	
	List<Utente> findByUsername(String username);
}
