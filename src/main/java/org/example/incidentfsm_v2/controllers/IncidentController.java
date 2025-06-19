package org.example.incidentfsm_v2.controllers;

import org.example.incidentfsm_v2.services.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public String createIncident(@RequestParam String description,
                                 @RequestParam boolean critical,
                                 @RequestParam Long reportedBy) {
        incidentService.createIncident(description, critical, reportedBy);
        return "redirect:/";
    }

    @PostMapping("/{id}/event")
    public String handleEvent(@PathVariable Long id, @RequestParam String event) {
        incidentService.handleEvent(id, event);
        return "redirect:/";
    }
}


