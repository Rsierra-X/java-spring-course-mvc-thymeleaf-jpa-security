package org.rsierra.controllers;

import org.rsierra.models.Vacancy;
import org.rsierra.service.ICategoryService;
import org.rsierra.service.IVacancyService;
import org.rsierra.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @Value("${companiesApp.path.images}")
    private String path;

    @Autowired
    private IVacancyService vacancyService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/edit/{id}")
    public String updateVacancy(@PathVariable int id, Model model) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("categories", categoryService.getCategories());
        return "vacancy/formVacancy";
    }

    @GetMapping("/index")
    public String showVacancies(Model model) {
        List<Vacancy> vacancyList = vacancyService.getVacancies();
        model.addAttribute("vacancies", vacancyList);
        return "vacancy/listVacancies";
    }

    @GetMapping("/create")
    public String createVacancy(Vacancy vacancy, Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "vacancy/formVacancy";
    }

    @PostMapping("/saveVacancy")
    public String saveVacancy(Vacancy vacancy, @RequestParam("imageFile")MultipartFile multiPart, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "vacancy/formVacancy";
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            //String path = "c:/empleos/img-vacantes/"; // Windows
            String nameImage = Utility.saveFile(multiPart, path);
            if (nameImage != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacancy.setImage(nameImage);
            }
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
