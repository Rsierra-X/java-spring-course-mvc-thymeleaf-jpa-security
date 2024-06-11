package org.rsierra.controllers;

import org.rsierra.models.Request;
import org.rsierra.models.User;
import org.rsierra.models.Vacancy;
import org.rsierra.repository.RequestRepository;
import org.rsierra.repository.UserRepository;
import org.rsierra.service.IRequestService;
import org.rsierra.service.IUserService;
import org.rsierra.service.IVacancyService;
import org.rsierra.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Value("${companiesApp.path.cv}")
    private String pathCv;

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IVacancyService vacancyService;

    @Autowired
    private IUserService userService;

    @GetMapping("/create/{id}")
    public String create(Request request,@PathVariable int id, Model model) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "request/formRequest";
    }

    @PostMapping("/save")
    public String save(Request request, BindingResult result, @RequestParam MultipartFile resumeFile, Authentication auth, Model model) {
        String username = auth.getName();

        if (result.hasErrors()) {
            return "request/formRequest";
        }
        if (!resumeFile.isEmpty()) {
            String fileName = Utility.saveFile(resumeFile,pathCv);
            if (fileName!=null){
                request.setFile(fileName);
            }
        }

        User user = userService.findByUsername(username);
        request.setUser(user);

        return "redirect:/";
    }
}
