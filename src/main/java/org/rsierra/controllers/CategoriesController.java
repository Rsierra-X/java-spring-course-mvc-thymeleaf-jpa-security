package org.rsierra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/categories")
public class CategoriesController {

    // @GetMapping("/index")
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String showIndex(Model model) {
        return "categories/listCategories";
    }

    // @GetMapping("/create")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create() {
        return "categories/formCategories";
    }

    // @PostMapping("/save")
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(@RequestParam("name") String name, @RequestParam("description") String description) {
        System.out.println("Category:" + name);
        System.out.println("Description: " + description);

        return "categories/listCategories";
    }
}
