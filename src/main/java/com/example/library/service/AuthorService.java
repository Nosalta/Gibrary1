package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// Denna klass tar hand om allt som har me Author att göra
// controllern frågar denna klass om den ska hämta eller spara author datan

@Service
public class AuthorService {
    private final AuthorRepository authors;

    // denna ger access till servicen till respositoryn asså databasen
    public AuthorService(AuthorRepository authors) {
        this.authors = authors;
    }
// hämtar alla authors frn databasen
    public List<Author> findAll() {
        return authors.findAll();
    }
        // hittar authors genom efternamnet
    public List<Author> findByLastName(String lastName) {
        return authors.findByLastNameIgnoreCase(lastName);
    }

    // lägger till en ny author
    public Author create(Author author) {
        return authors.save(author);
    }

    // Hämta en användare via id, annars skicka "Author not found" om den inte finns

    public Author getOrThrow(Long id) {
        return authors.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found: " + id));
    }
}
