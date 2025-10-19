package com.example.library.controller;

import com.example.library.dto.BookWithDetailsDTO;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Allt relaterat till Books här
// Lista Books, söka efter Books och lägga till nya Books

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

// kopplar ihop controllern
    public BookController(BookService service) {
        this.service = service;
    }

    // GET/books,
    @GetMapping
    public List<BookWithDetailsDTO> all() {
        return service.findAll().stream().map(service::toDetailsDTO).collect(Collectors.toList());
    }

    // GET /books/search
    @GetMapping("/search")

    // allt som matchar kommer upp
    public List<BookWithDetailsDTO> search(@RequestParam("q") String q) {
        return service.search(q).stream().map(service::toDetailsDTO).collect(Collectors.toList());
    }

    // POST /books här, så om jag vill lägga till nya books
    @PostMapping
    public Book create(@RequestBody Book b) { //spring
        return service.create(b);
    }
}
