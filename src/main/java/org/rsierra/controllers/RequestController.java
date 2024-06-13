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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/indexPaginate")
    public String indexPaginate(Model model,@PageableDefault(sort= {"id"},direction= Sort.Direction.DESC, size=20) Pageable pageable) {
        Page<Request> list = requestService.getAllRequests(pageable);
        model.addAttribute("requests", list);
        return "request/listRequest";
    }

    @GetMapping("/create/{id}")
    public String create(Request request,@PathVariable int id, Model model) {
        System.out.println("AQUIiii"+ id);
        Vacancy vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "request/formRequest";
    }

    @PostMapping("/save")
    public String save(Request request,
                       BindingResult result,
                       @RequestParam MultipartFile resumeFile,
                       Authentication auth,
                       RedirectAttributes redirectAttributes) {
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

        requestService.saveRequest(request);

        redirectAttributes.addFlashAttribute("message", "Request has been saved");
        return "redirect:/";
    }
}
