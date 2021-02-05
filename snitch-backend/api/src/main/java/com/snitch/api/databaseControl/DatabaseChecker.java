package com.snitch.api.databaseControl;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.snitch.api.repository.RoleRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.entities.model.Role;
import com.snitch.entities.model.User;

import enums.ERole;
import javassist.NotFoundException;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class DatabaseChecker {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Value("${snitch.app.adminEmail}")
    private String mainEmail;

    @PostConstruct
    private void check() throws NotFoundException {
        checkRoles();
        checkAdminAccount();
    }

    private void checkRoles() {
        for(ERole role : ERole.values()) {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
            }
        }
    }

    private void checkAdminAccount() throws NotFoundException {
        Role adminRole = roleRepository.getByName(ERole.ROLE_ADMIN).orElseThrow(() -> new NotFoundException("ROLE_ADMIN does not exist!"));
        if (!userRepository.existsUserByRolesIn(Collections.singletonList(adminRole))) {
            userRepository.save(new User("admin",
                    "admin",
                    "admin",
                    "admin",
                    mainEmail,
                    encoder.encode("admin"),
                    Collections.singleton(adminRole)));
        }
    }
}
