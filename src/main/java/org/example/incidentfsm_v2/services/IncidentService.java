package org.example.incidentfsm_v2.services;

import org.example.incidentfsm_v2.models.Incident;
import org.example.incidentfsm_v2.models.IncidentEvent;
import org.example.incidentfsm_v2.models.IncidentState;
import org.example.incidentfsm_v2.models.IncidentType;
import org.example.incidentfsm_v2.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserService userService;

    public IncidentService(IncidentRepository incidentRepository, UserService userService) {
        this.incidentRepository = incidentRepository;
        this.userService = userService;
    }

    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll();
    }

    public void createIncident(String description, boolean critical, Long userId) {
        Incident incident = new Incident();
        incident.setDescription(description);
        incident.setIncidentType(IncidentType.CRITICAL);
        incident.setCurrentState(IncidentState.START);
        incident.setReportedBy(userService.findUserById(userId));
        incidentRepository.save(incident);
    }

    public void handleEvent(Long id, String eventString) {
        Incident incident = incidentRepository.findById(id).orElseThrow();

        IncidentEvent event;
        try {
            event = IncidentEvent.valueOf(eventString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown event: " + eventString);
        }

        incident.applyEvent(event);
        incidentRepository.save(incident);
    }

}
