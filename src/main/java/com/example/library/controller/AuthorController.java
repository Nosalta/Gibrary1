package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Denna controller hanterar allt som har med author o göra
// hämta, söka och skapa nya authors


@RestController
@RequestMapping("/authors")

// kopplar ihop controllern
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    // GET /authors
    // hämtar alla författare från databasen

    @GetMapping
    public List<Author> all() {
        return service.findAll();
    }

    // GET /authors/name/{lastName}
    // hittar en eller flera authors baserat på efternamn

    @GetMapping("/name/{lastName}")
    public List<Author> byLastName(@PathVariable String lastName) {
        return service.findByLastName(lastName);
    }

    // POST /authors
    // Skapar en ny author i databasen

    @PostMapping
    public Author create(@RequestBody Author a) {
        return service.create(a);
    }
}
