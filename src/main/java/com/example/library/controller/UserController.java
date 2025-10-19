package com.example.library.controller;

import com.example.library.dto.UserDTO;
import com.example.library.entity.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

// Hanterar endpoints för användare
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET /users/email/{email}

    // Returnerar UserDTO - utan password
    @GetMapping("/email/{email}")
    public UserDTO byEmail(@PathVariable String email) {
        return service.toDTO(service.getByEmailOrThrow(email));
    }

    // POST /users

    // skapar en ny användare och returnerar DTO
    @PostMapping
    public UserDTO create(@RequestBody User user) {
        return service.toDTO(service.create(user));
    }

    // lägger till en GET /users okså
    @GetMapping
    public java.util.List<com.example.library.dto.UserDTO> all() {
        return service.findAll().stream()
                .map(service::toDTO)
                .toList();
    }

}
