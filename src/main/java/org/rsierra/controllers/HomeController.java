package org.rsierra.controllers;

import org.rsierra.models.Vacancy;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class HomeController {
	@Autowired
	private IVacancyService vacancyService;

	@GetMapping("/table")
	public String showTable(Model model) {
		List<Vacancy> lista = vacancyService.getVacancies();
		model.addAttribute("vacantes", lista);
		return "tabla";
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
		List<Vacancy> list = vacancyService.getVacancies();
		model.addAttribute("vacancies", list);
		return "home";
	}
	
}
