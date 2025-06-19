package org.example.incidentfsm_v2.controllers;

import org.example.incidentfsm_v2.services.IncidentService;
import org.example.incidentfsm_v2.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.GrantedAuthority;


import java.security.Principal;

@Controller
public class MainPageController {

    private final IncidentService incidentService;
    private final UserService userService;

    public MainPageController(IncidentService incidentService, UserService userService) {
        this.incidentService = incidentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showMainPage(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName()); // или из сервиса пользователя
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        model.addAttribute("role", role);
        model.addAttribute("incidents", incidentService.findAllIncidents());
        model.addAttribute("users", userService.findAllUsers());
        return "mainPage";
    }
}

