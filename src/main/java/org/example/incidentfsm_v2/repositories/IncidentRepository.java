package org.example.incidentfsm_v2.repositories;

import org.example.incidentfsm_v2.models.Incident;
import org.example.incidentfsm_v2.models.IncidentState;
import org.example.incidentfsm_v2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByCurrentState(IncidentState state);
    List<Incident> findByCriticalTrue();
    List<Incident> findByReportedBy(User user);

    // Дополнительно: найти инциденты после определённого времени
    List<Incident> findByTimestampAfter(LocalDateTime timestamp);

    // Например, найти критичные инциденты за сегодня
    @Query("SELECT i FROM Incident i WHERE i.critical = true AND i.timestamp >= :startOfDay")
    List<Incident> findTodayCriticalIncidents(LocalDateTime startOfDay);
}
