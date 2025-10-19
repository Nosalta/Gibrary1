package com.example.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")

// Denna klass representerar en bok i databasen.
// Här sparas info som titel, årtal, antal ex och vem som är author

public class Book {

    @Id
    @Column(name = "book_id")
    private Long bookId;

    private String title;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "total_copies")
    private Integer totalCopies;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // ----- getters & setters -----
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }

    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer totalCopies) { this.totalCopies = totalCopies; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
