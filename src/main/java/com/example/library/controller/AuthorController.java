package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Author> all() {
        return service.findAll();
    }

    @GetMapping("/name/{lastName}")
    public List<Author> byLastName(@PathVariable String lastName) {
        return service.findByLastName(lastName);
    }

    @PostMapping
    public Author create(@RequestBody Author a) {
        return service.create(a);
    }
}
