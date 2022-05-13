package app.db;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.model.Carrello;

@Repository
public interface CarrelloRepository extends CrudRepository<Carrello, String>{
	
	List<Carrello> findByUtenteIdUtente(Integer idUtente);
	
	@Modifying @Transactional
	void deleteByUtenteIdUtenteAndProdottoIdProdotto(Integer idUtente, Integer idProdotto);
}
