package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.model.CriteriUtente;
import app.model.Utente;

@Controller
public class MenuController {
	
	@GetMapping("/")
	public String inviaMenu(Model model, HttpSession session) {
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
    	else {
    		return "menuUser";
    	}
	}
}
