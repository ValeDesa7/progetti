package app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idutente")
	private Integer idUtente;
	
	@NotBlank(message="username obbligatoria")
	@Size(max=20, message="massimo 20 caratteri")
	@Column(name="username")
	private String username;
	
	@NotBlank(message="password obbligatoria")
	@Size(max=20, message="massimo 20 caratteri")
	@Column(name="password")
	private String password;
	
	@NotBlank(message="ruolo obbligatorio")
	@Size(max=10, message="massimo 10 caratteri")
	@Column(name="ruolo")
	private String ruolo;
	
	@OneToMany(mappedBy = "utente")
    private Set<Carrello> carrelli;

	public Utente() {
		super();
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Set<Carrello> getCarrelli() {
		return carrelli;
	}

	public void setCarrelli(Set<Carrello> carrelli) {
		this.carrelli = carrelli;
	}
}
