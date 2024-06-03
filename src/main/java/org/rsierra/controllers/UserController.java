package org.rsierra.controllers;

import org.rsierra.models.User;
import org.rsierra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("users", list);
        return "users/userList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attributes) {
        // Eliminamos el usuario
        userRepository.deleteById(id);
        attributes.addFlashAttribute("successMsg", "El usuario fue eliminado!.");
        return "redirect:/users/index";
    }
}
