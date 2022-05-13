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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="prodotti")
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idprodotto")
	private Integer idProdotto;
	
	@NotBlank(message="nome obbligatorio")
	@Size(max=20, message="massimo 20 caratteri")
	@Column(name="nome")
	private String nome;
	
	@NotNull(message="prezzo obbligatorio")
	@Positive(message="il prezzo deve essere positivo")
	@Column(name="prezzo")
	private double prezzo;
	
	@NotBlank(message="codcie obbligatorio")
	@Size(max=20, message="massimo 20 caratteri")
	@Column(name="codice")
	private String codice;
	
	@OneToMany(mappedBy = "prodotto")
    private Set<Carrello> carrelli;

	public Prodotto() {
		super();
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Set<Carrello> getCarrelli() {
		return carrelli;
	}

	public void setCarrelli(Set<Carrello> carrelli) {
		this.carrelli = carrelli;
	}
}
