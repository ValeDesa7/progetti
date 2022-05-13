package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="carrelli")
public class Carrello {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcarrello")
	private Integer idCarrello;
	
	@ManyToOne
	@NotNull(message="utente obbligatorio")
	@JoinColumn(name="idutente")
	private Utente utente;
	
	@ManyToOne
	@NotNull(message="carrello obbligatorio")
	@JoinColumn(name="idprodotto")
	private Prodotto prodotto;

	public Carrello() {
		super();
	}

	public Integer getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

}
