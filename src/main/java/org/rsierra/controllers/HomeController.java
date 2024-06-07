package org.rsierra.controllers;

import jakarta.servlet.http.HttpSession;
import org.rsierra.models.Profile;
import org.rsierra.models.User;
import org.rsierra.models.Vacancy;
import org.rsierra.service.ICategoryService;
import org.rsierra.service.IUserService;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class HomeController {
	@Autowired
	private IVacancyService vacancyService;

	@Autowired
	private IUserService serviceUser;

	@Autowired
	private ICategoryService serviceCategory;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacancy vacancy, Model model) {
		System.out.println("Buscando por : " + vacancy);

		ExampleMatcher matcher = ExampleMatcher.
				// where descripcion like '%?%'
						matching().withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());

		Example<Vacancy> example = Example.of(vacancy,matcher);
		List<Vacancy> list = vacancyService.searchByExample(example);
		model.addAttribute("vacancies", list);
		return "home";
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

	@GetMapping("/signup")
	public String registrarse(User user) {
		return "registrationForm";
	}

	@PostMapping("/signup")
	public String saveRegister(User user, RedirectAttributes attributes) {
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String podEncrypt = passwordEncoder.encode(pwdPlano);

		user.setPassword(podEncrypt);
		user.setStatus(1); // Activado por defecto
		user.setRegisterDate(new Date()); // Fecha de Registro, la fecha actual del servidor

		// Creamos el Perfil que le asignaremos al usuario nuevo
		Profile profile = new Profile();
		profile.setId(3); // Perfil USUARIO
		user.addProfile(profile);

		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUser.save(user);
		attributes.addFlashAttribute("successMsg", "El registro fue guardado correctamente!");

		return "redirect:/users/index";
	}

	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encryptors(@PathVariable String texto) {
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ModelAttribute
	public void setGenerics(Model model) {
		Vacancy searchVacancy = new Vacancy();
		searchVacancy.reset();
		model.addAttribute("vacancies", vacancyService.searchFeaturedVacancies());
		model.addAttribute("categories", serviceCategory.getCategories());
		model.addAttribute("search", searchVacancy);
	}
	
}
