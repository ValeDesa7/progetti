package app.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.model.Prodotto;

@Repository
public interface ProdottoRepository extends CrudRepository<Prodotto, String>{
	
	List<Prodotto> findByIdProdotto(Integer id);
	
	List<Prodotto> findByCodice(String codice);
}
