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

    private boolean critical;
}

