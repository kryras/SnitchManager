package com.snitch.api.databaseControl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.snitch.api.repository.RoleRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.api.service.IEmailService;
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

    @Autowired
    private IEmailService emailService;

    @Value("${snitch.app.adminEmail}")
    private String adminEmail;

    private final List<CharacterRule> passwordGenerationRules = Arrays.asList(
            new CharacterRule(EnglishCharacterData.LowerCase, 2),
            new CharacterRule(EnglishCharacterData.UpperCase, 2),
            new CharacterRule(EnglishCharacterData.Digit,3));

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
            String newPassword = new PasswordGenerator().generatePassword(8, passwordGenerationRules);
            User newUser = new User("admin",
                    "admin",
                    "admin",
                    "admin",
                    adminEmail,
                    encoder.encode(newPassword),
                    Collections.singleton(adminRole));
            userRepository.save(newUser);
            sendEmail(newPassword);
        }
    }

    private void sendEmail(String newPassword) {
        emailService.sendAdminAccountRecoverEmail(adminEmail, newPassword);
    }
}
