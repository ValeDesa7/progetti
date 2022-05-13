package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.db.CarrelloRepository;
import app.db.ProdottoRepository;
import app.model.Carrello;
import app.model.CriteriUtente;
import app.model.Prodotto;
import app.model.Utente;

@Controller
public class CarrelloController {
	
	@Autowired
	private ProdottoRepository pr;
	@Autowired
	private CarrelloRepository cr;
	
	@GetMapping("/aggiungiProdottoCarrello")
	public String aggiungiProdottoCarrello(@RequestParam(name="codice") String codice, Model model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Utente> utenti = (List<Utente>) request.getSession().getAttribute("utenteSession");
        if (utenti == null) {
        	model.addAttribute("criteri", new CriteriUtente());
        	return "login";
        }
        Utente utente = utenti.get(0);
        if(utente.getRuolo().equals("admin")) {
    		return "menuAdmin";
    	}
        List<Prodotto> prodotti = pr.findByCodice(codice);
        Prodotto prodotto = prodotti.get(0);
        Carrello carrello = new Carrello();
        carrello.setUtente(utente);
        carrello.setProdotto(prodotto);
        cr.save(carrello);
        List<Carrello> carrelli = cr.findByUtenteIdUtente(utente.getIdUtente());
        model.addAttribute("carrelli", carrelli);
		return "carrello";
	}
	
	@GetMapping("/visualizzaCarrello")
	public String visualizzaCarrello(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Utente> utenti = (List<Utente>) session.getAttribute("utenteSession");
        if (utenti == null) {
        	model.addAttribute("criteri", new CriteriUtente());
        	return "login";
        }
        Utente utente = utenti.get(0);
        if(utente.getRuolo().equals("admin")) {
    		return "menuAdmin";
    	}
        List<Carrello> carrelli = cr.findByUtenteIdUtente(utente.getIdUtente());
        model.addAttribute("carrelli", carrelli);
        return "carrello";
	}
	
	@GetMapping("/acquistaProdotto")
	public String acquistaProdotto(@RequestParam(name="codice") String codice, Model model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Utente> utenti = (List<Utente>) request.getSession().getAttribute("utenteSession");
        if (utenti == null) {
        	model.addAttribute("criteri", new CriteriUtente());
        	return "login";
        }
        Utente utente = utenti.get(0);
        if(utente.getRuolo().equals("admin")) {
    		return "menuAdmin";
    	}
        List<Prodotto> prodotti = pr.findByCodice(codice);
        Prodotto prodotto = prodotti.get(0);
        cr.deleteByUtenteIdUtenteAndProdottoIdProdotto(utente.getIdUtente(), prodotto.getIdProdotto());
        model.addAttribute("prodotto", prodotto);
		return "menuUser";
	}
}
