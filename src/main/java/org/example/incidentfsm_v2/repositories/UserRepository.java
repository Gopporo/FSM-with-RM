package org.example.incidentfsm_v2.repositories;

import org.example.incidentfsm_v2.models.User;
import org.example.incidentfsm_v2.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

