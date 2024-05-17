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

    /*@GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("id", idVacante);
        return "mensaje";
    }*/

}
