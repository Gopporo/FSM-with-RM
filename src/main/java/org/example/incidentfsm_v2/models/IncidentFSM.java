package org.example.incidentfsm_v2.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class IncidentFSM {
    @Getter
    private IncidentState currentState;

    private final Map<IncidentState, Map<IncidentEvent, IncidentState>> transitions = new HashMap<>();

    public IncidentFSM(IncidentState initialState) {
        this.currentState = initialState;
        configure();
    }

    private void configure() {
        add(IncidentState.START, IncidentEvent.ADD_INCIDENT, IncidentState.ANALYZE);
        add(IncidentState.ANALYZE, IncidentEvent.INCIDENT_CRITICAL_OR_EMERGENCY, IncidentState.ALERT);
        add(IncidentState.ANALYZE, IncidentEvent.INCIDENT_MAJOR, IncidentState.REVIEW);
        add(IncidentState.ANALYZE, IncidentEvent.INCIDENT_MINOR_OR_NORMAL, IncidentState.RESOLVE);
        add(IncidentState.ANALYZE, IncidentEvent.SKIP_INCIDENT, IncidentState.SKIP);
        add(IncidentState.ALERT, IncidentEvent.ESCALATE_NEEDED, IncidentState.REVIEW);
        add(IncidentState.ALERT, IncidentEvent.IMMEDIATELY_RESOLVE, IncidentState.RESOLVE);
        add(IncidentState.REVIEW, IncidentEvent.CLOSE_INCIDENT, IncidentState.CLOSED);
        add(IncidentState.RESOLVE, IncidentEvent.CLOSE_INCIDENT, IncidentState.CLOSED);
    }

    private void add(IncidentState from, IncidentEvent event, IncidentState to) {
        transitions.computeIfAbsent(from, k -> new HashMap<>()).put(event, to);
    }

    public IncidentState apply(IncidentEvent event) {
        IncidentState next = transitions.getOrDefault(currentState, new HashMap<>()).get(event);
        if (next != null) {
            currentState = next;
        }
        return currentState;
    }

}
