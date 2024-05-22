package org.rsierra.controllers;

import org.rsierra.models.Vacancy;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private IVacancyService vacancyService;

    @GetMapping("/index")
    public String showVacancies(Model model) {
        List<Vacancy> vacancyList = vacancyService.getVacancies();
        model.addAttribute("vacancies", vacancyList);
        return "vacancy/listVacancies";
    }

    @GetMapping("/create")
    public String createVacancy(Vacancy vacancy) {
        return "vacancy/formVacancy";
    }

    @PostMapping("/saveVacancy")
    public String saveVacancy(Vacancy vacancy, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "vacancy/formVacancy";
        }
        vacancyService.saveVacancy(vacancy);
        attributes.addFlashAttribute("successMsg", "Save Success");
        return "redirect:/vacancy/index";
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
