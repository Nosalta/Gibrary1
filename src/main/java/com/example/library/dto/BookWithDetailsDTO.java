package com.example.library.dto;

public record BookWithDetailsDTO(
        Long bookId,
        String title,
        Integer publicationYear,
        Integer availableCopies,
        Integer totalCopies,
        Long authorId,
        String authorFirstName,
        String authorLastName
) {}
