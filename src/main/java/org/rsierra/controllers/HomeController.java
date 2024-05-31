package org.rsierra.controllers;

import jakarta.servlet.http.HttpSession;
import org.rsierra.models.User;
import org.rsierra.models.Vacancy;
import org.rsierra.service.IUserService;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class HomeController {
	@Autowired
	private IVacancyService vacancyService;

	@Autowired
	private IUserService serviceUser;

	@GetMapping("/table")
	public String showTable(Model model) {
		List<Vacancy> lista = vacancyService.getVacancies();
		model.addAttribute("vacantes", lista);
		return "table";
	}

	@GetMapping("/vacancyDetail")
	public String showDetail(Model model) {
		Vacancy vacancy = new Vacancy();
		vacancy.setName("System Engineer");
		vacancy.setDate(new Date());
		vacancy.setDescription("In need of a System Engineer with knowledge in Java Spring Boot");
		vacancy.setSalary(2500.00);
		model.addAttribute("vacancy", vacancy);
		return "vacancyDetail";
	}

	@GetMapping("/list")
	public String showList(Model model) {
		List<String> positionsList = new LinkedList<>();
		positionsList.add("System Engineer");
		positionsList.add("Account Manager");
		positionsList.add("Seller Manager");
		positionsList.add("Architect");

		model.addAttribute("positionsList", positionsList);
		return "positionsList";
	}

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/index")
	public String showIndex(Authentication auth, HttpSession session) {
		String username = auth.getName();
		for(GrantedAuthority rol: auth.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		if (session.getAttribute("user") == null){
			User user = serviceUser.findByUsername(username);
			user.setPassword(null);
			System.out.println("Usuario: " + user);
			session.setAttribute("usuario", user);
		}

		return "redirect:/";
	}

	@ModelAttribute
	public void setGenerics(Model model) {
		model.addAttribute("vacancies", vacancyService.searchFeaturedVacancies());
	}
	
}
