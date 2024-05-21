package org.rsierra.controllers;

import org.rsierra.models.Vacancy;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private IVacancyService vacancyService;

    @GetMapping("/create")
    public String createVacancy() {
        return "vacancy/formVacancy";
    }

    @PostMapping("/saveVacancy")
    public String saveVacancy(Vacancy vacancy) {
        System.out.println(vacancy);
        return "vacancy/listVacancies";
    }

    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        System.out.println("IdVacante: " + vacancy);
        model.addAttribute("vacancy", vacancy);
        return "vacancy/detail";
    }

    //sending params as a http
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        System.out.println("Borrando vacante con id: " + id);
        model.addAttribute("id", id);
        return "message";
    }

}
