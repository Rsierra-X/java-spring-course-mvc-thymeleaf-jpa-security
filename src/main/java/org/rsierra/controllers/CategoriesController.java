package org.rsierra.controllers;

import org.rsierra.models.Category;
import org.rsierra.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/categories")
public class CategoriesController {

    @Autowired
    //@Qualifier("categoryServiceJpa")
    private ICategoryService categoryService;

    // @GetMapping("/index")
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String showCategories(Model model) {
        List<Category> categoryList = categoryService.getCategories();
        model.addAttribute("categories", categoryList);
        return "categories/listCategories";
    }

    // @GetMapping("/create")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create(Category category) {
        return "categories/formCategories";
    }

    // @PostMapping("/save")
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Category category, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "categories/formCategories";
        }
        categoryService.addCategory(category);
        attributes.addFlashAttribute("successMsg", "Save Success");
        return "redirect:/categories/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categories/formCategories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attributes) {
        // Eliminamos la categoria.
        categoryService.delete(id);
        attributes.addFlashAttribute("successMsg", "La categoría fue eliminada!.");
        return "redirect:/categories/index";
    }
}
