package com.example.library.service;

import com.example.library.dto.BookWithDetailsDTO;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


// controllern använder denna klass för att lista, posta eller söker efter books
@Service
public class BookService {
    private final BookRepository books;


    // tillgång till till book och author så jag kan hämta info från databasen
    public BookService(BookRepository books) {
        this.books = books;
    }

    // hämtar alla books från databasen
    public List<Book> findAll() {
        return books.findAll();
    }
// söker efter books så det matchar en specifik string antingen title eller author
    public List<Book> search(String query) {
        return books.searchByTitleOrAuthor(query);
    }
// lägger till en ny book
    public Book create(Book book) {
        return books.save(book);
    }
//säkertställ att denna author finns innan boken sparas
    public BookWithDetailsDTO toDetailsDTO(Book b) {
        return new BookWithDetailsDTO(
                b.getBookId(),
                b.getTitle(),
                b.getPublicationYear(),
                b.getAvailableCopies(),
                b.getTotalCopies(),
                b.getAuthor() != null ? b.getAuthor().getAuthorId() : null,
                b.getAuthor() != null ? b.getAuthor().getFirstName() : null,
                b.getAuthor() != null ? b.getAuthor().getLastName() : null
        );
    }
}
