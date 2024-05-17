package org.rsierra.controllers;

import org.rsierra.models.Vacancy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class HomeController {

	@GetMapping("/table")
	public String showTable(Model model) {
		List<Vacancy> lista = getVacantes();
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
		List<String> positionsList = new LinkedList<String>();
		positionsList.add("System Engineer");
		positionsList.add("Account Manager");
		positionsList.add("Seller Manager");
		positionsList.add("Architect");

		model.addAttribute("positionsList", positionsList);
		return "positionsList";
	}

	@GetMapping("/")
	public String home(Model model) {

		String nombre = "System Engineer";
		Date fechaPub = new Date();
		double salario = 9000.0;
		boolean vigente = true;
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("fecha", fechaPub);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		
		return "home";
	}

	/**
	 * Método que regresa una lista de objetos de tipo Vacante
	 * @return
	 */
	private List<Vacancy> getVacantes() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacancy> lista = new LinkedList<Vacancy>();
		try {
			// Creamos la oferta de Trabajo 1.
			Vacancy vacante1 = new Vacancy();
			vacante1.setId(1);
			vacante1.setName("Ingeniero Civil"); // Titulo de la vacante
			vacante1.setDescription("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacante1.setDate(sdf.parse("08-02-2019"));
			vacante1.setSalary(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");


			// Creamos la oferta de Trabajo 2.
			Vacancy vacante2 = new Vacancy();
			vacante2.setId(2);
			vacante2.setName("Contador Publico");
			vacante2.setDescription("Empresa importante solicita contador con 5 años de experiencia titulado.");
			vacante2.setDate(sdf.parse("09-02-2019"));
			vacante2.setSalary(12000.0);
			vacante1.setDestacado(0);
			vacante2.setImagen("empresa2.png");


			// Creamos la oferta de Trabajo 3.
			Vacancy vacante3 = new Vacancy();
			vacante3.setId(3);
			vacante3.setName("Ingeniero Eléctrico");
			vacante3.setDescription("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica.");
			vacante3.setDate(sdf.parse("10-02-2019"));
			vacante3.setSalary(10500.0);
			vacante1.setDestacado(0);

			// Creamos la oferta de Trabajo 4.
			Vacancy vacante4 = new Vacancy();
			vacante4.setId(4);
			vacante4.setName("Diseñador Gráfico");
			vacante4.setDescription("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
			vacante4.setDate(sdf.parse("11-02-2019"));
			vacante4.setSalary(7500.0);
			vacante1.setDestacado(1);
			vacante4.setImagen("empresa3.png");


			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return lista;

	}

	
}
