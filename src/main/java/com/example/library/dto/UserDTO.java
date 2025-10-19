package com.example.library.dto;

import java.time.LocalDate;

public record UserDTO(
        Long userId,
        String firstName,
        String lastName,
        String email,
        LocalDate registrationDate
) {}
