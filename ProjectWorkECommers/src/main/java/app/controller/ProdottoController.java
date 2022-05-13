package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.db.ProdottoRepository;
import app.model.CriteriUtente;
import app.model.Prodotto;
import app.model.Utente;

@Controller
public class ProdottoController {
	
	@Autowired
	private ProdottoRepository pr;
	
	@GetMapping("/visualizzaProdottiAdmin")
	public String visualizzaProdottiAdmin(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Utente> utenti = (List<Utente>) session.getAttribute("utenteSession");
        if (utenti == null) {
        	model.addAttribute("criteri", new CriteriUtente());
        	return "login";
        }
        Utente utente = utenti.get(0);
        if(!utente.getRuolo().equals("admin")) {
    		return "menuUser";
    	}
		Iterable<Prodotto> prodotti = pr.findAll();
		model.addAttribute("prodotti", prodotti);
		return "listaProdottiAdmin";
	}
	
	@GetMapping("/visualizzaProdottiUser")
	public String visualizzaProdottiUser(Model model, HttpSession session) {
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
		Iterable<Prodotto> prodotti = pr.findAll();
		model.addAttribute("prodotti", prodotti);
		return "listaProdottiUser";
	}
	
	@GetMapping("/inserisciProdotto")
	public String inserisciProdotto(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Utente> utenti = (List<Utente>) session.getAttribute("utenteSession");
        if (utenti == null) {
        	model.addAttribute("criteri", new CriteriUtente());
        	return "login";
        }
        Utente utente = utenti.get(0);
        if(!utente.getRuolo().equals("admin")) {
    		return "menuUser";
    	}
		model.addAttribute("prodotto", new Prodotto());
		return "inserisciProdotto";
	}
	
	@PostMapping("/inserisciProdotto")
	public String inserisciCorso(@Valid @ModelAttribute ("prodotto") Prodotto prodotto, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "inserisciProdotto";
		} 
		List<Prodotto> prodotti = pr.findByCodice(prodotto.getCodice());
		if (!prodotti.isEmpty()) {
			errors.rejectValue("codice", "xy", "codice già esistente");
            return "inserisciProdotto";
		}
		pr.save(prodotto); 
		model.addAttribute("prodotto", prodotto);
		return "menuAdmin";  
	} 
}
