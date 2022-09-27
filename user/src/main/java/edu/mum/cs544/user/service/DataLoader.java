package edu.mum.cs544.user.service;

import edu.mum.cs544.user.entity.Role;
import edu.mum.cs544.user.entity.User;
import edu.mum.cs544.user.repository.RoleRepository;
import edu.mum.cs544.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role("USER");
        if (roleRepository.findAll().size() == 0) roleRepository.save(role);
        if (userRepository.findAll().size() == 0)
            userRepository.save(new User("johnsmith", "John", "Smith", "12345", Collections.singleton(role)));
    }
}