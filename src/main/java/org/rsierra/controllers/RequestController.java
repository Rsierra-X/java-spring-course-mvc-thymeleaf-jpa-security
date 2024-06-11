package org.rsierra.controllers;

import org.rsierra.models.Request;
import org.rsierra.models.Vacancy;
import org.rsierra.repository.RequestRepository;
import org.rsierra.service.IRequestService;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Value("${companiesApp.path.cv}")
    private String pathCv;

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IVacancyService vacancyService;

    @GetMapping("/create/{id}")
    public String create(Request request,@PathVariable int id, Model model) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "request/formRequest";
    }

    @PostMapping("/save")
    public String save(Request request, Model model) {
        return "redirect:/";
    }
}
