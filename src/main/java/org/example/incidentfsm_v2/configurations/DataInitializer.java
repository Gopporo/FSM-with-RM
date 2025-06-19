package org.example.incidentfsm_v2.configurations;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.incidentfsm_v2.models.User;
import org.example.incidentfsm_v2.models.UserRole;
import org.example.incidentfsm_v2.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        createUserIfNotExists("monitor", "monitor123", UserRole.MONITOR);
        createUserIfNotExists("operator", "operator123", UserRole.OPERATOR);
        createUserIfNotExists("supervisor", "supervisor123", UserRole.SUPERVISOR);
        createUserIfNotExists("admin", "admin123", UserRole.ADMIN);
    }

    private void createUserIfNotExists(String username, String rawPassword, UserRole role) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            userRepository.save(user);
            System.out.println("Создан пользователь: " + username + " с ролью " + role);
        }
    }
}
