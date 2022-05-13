package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.db.UtenteRepository;
import app.model.CriteriUtente;
import app.model.Utente;

@Controller
public class LoginController {
	
	@Autowired
	private UtenteRepository ur;
	
	@PostMapping("/effettuaLogin")
	public String effettuaLogin(@Valid @ModelAttribute ("criteri") CriteriUtente criteriUtente, Errors errors, Model model, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "login";
		} 
		String username = criteriUtente.getUsername();
		String password = criteriUtente.getPassword();
		List<Utente> utenti = ur.findByUsername(username);
		if (utenti.isEmpty()) {
			errors.rejectValue("username", "xy", "username inesistente");
			return "login";
		}
		Utente utente = utenti.get(0);
		if (!utente.getPassword().equals(password)) {
			errors.rejectValue("password", "xy", "password errata");
			return "login";
		}
		else {
			request.getSession().setAttribute("utenteSession", utenti);
			if(utente.getRuolo().equals("admin")) {
				return "menuAdmin";
			}
			else {
				return "menuUser";
			}
		}
	}
	
	@GetMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        model.addAttribute("criteri", new CriteriUtente());
    	return "login";
    }
}
