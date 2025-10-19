package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :q, '%')) " +
            "OR lower(b.author.lastName) LIKE lower(concat('%', :q, '%'))")
    List<Book> searchByTitleOrAuthor(@Param("q") String query);
}
