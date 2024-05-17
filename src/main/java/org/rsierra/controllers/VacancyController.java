package org.rsierra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        System.out.println("IdVacante: " + id);
        model.addAttribute("idVacante", id);
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
