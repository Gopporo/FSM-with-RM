package org.example.incidentfsm_v2.services;

import org.example.incidentfsm_v2.models.Incident;
import org.example.incidentfsm_v2.models.IncidentState;
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
        return incidentRepository.findAll().stream()
                .sorted(Comparator.comparing(Incident::isCritical).reversed()
                        .thenComparing(Incident::getCurrentState))
                .collect(Collectors.toList());
    }

    /*public List<Incident> findAllIncidents() {
        return incidentRepository.findAll();
    }*/

    public void createIncident(String description, boolean critical, Long userId) {
        Incident incident = new Incident();
        incident.setDescription(description);
        incident.setCritical(critical);
        incident.setCurrentState(IncidentState.NORMAL);
        incident.setReportedBy(userService.findUserById(userId));
        incidentRepository.save(incident);
    }

    public void handleEvent(Long id, String event) {
        Incident incident = incidentRepository.findById(id).orElseThrow();
        IncidentState newState = handleFSMEvent(incident.getCurrentState(), event);
        incident.setCurrentState(newState);
        incidentRepository.save(incident);
    }

    public IncidentState handleFSMEvent(IncidentState currentState, String event) {
        switch (currentState) {
            case NORMAL:
                if (event.equals("SENSOR_TRIGGERED")) return IncidentState.ALERT_RECEIVED;
                break;
            case ALERT_RECEIVED:
                if (event.equals("CONFIRM_ALERT")) return IncidentState.ANALYZING;
                if (event.equals("CANCEL_ALERT")) return IncidentState.CANCELLED;
                break;
            case ANALYZING:
                if (event.equals("DECIDE_EVACUATION")) return IncidentState.EVACUATION;
                if (event.equals("NO_ACTION")) return IncidentState.DECISION_MADE;
                break;
        }
        return currentState;
    }
}
