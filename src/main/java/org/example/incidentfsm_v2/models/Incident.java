package org.example.incidentfsm_v2.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private IncidentState currentState;

    private LocalDateTime timestamp;

    @ManyToOne
    private User reportedBy;

    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;

    @Transient
    private IncidentFSM fsm;

    public IncidentFSM getFsm() {
        if (fsm == null) {
            fsm = new IncidentFSM(currentState);
        }
        return fsm;
    }

    public void applyEvent(IncidentEvent event) {
        this.currentState = getFsm().apply(event);
    }
}

