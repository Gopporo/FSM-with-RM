package org.example.incidentfsm_v2.models;

public enum IncidentState {
    NORMAL,
    ALERT_RECEIVED,
    ANALYZING,
    DECISION_MADE,
    EVACUATION,
    CANCELLED
}

