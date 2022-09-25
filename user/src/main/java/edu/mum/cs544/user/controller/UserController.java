package edu.mum.cs544.user.controller;

import edu.mum.cs544.user.dto.UserLogin;
import edu.mum.cs544.user.dto.UserRequest;
import edu.mum.cs544.user.dto.UserResponse;
import edu.mum.cs544.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public UserResponse getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserResponse addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteById(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @PostMapping("/login")
    public UserResponse getByUsername(@Valid @RequestBody UserLogin userLogin) {
        return userService.getByUsername(userLogin);
    }
}
