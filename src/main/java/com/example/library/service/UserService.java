package com.example.library.service;

import com.example.library.dto.UserDTO;
import com.example.library.entity.User;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;


//Denna klass tar hand om allt som har med User att göra
// jag kan hitta en user genom email eller id eller skapa en ny

@Service
public class UserService {
    private final UserRepository users;

    //Kopplar UserService till respositoryn
    public UserService(UserRepository users) {
        this.users = users;
    }
    // skapa en ny user och spara i databasen
    public User create(User u) {
        return users.save(u);
    }
    // Hämta en användare via id, annars throw fel om den inte finns

    public User getOrThrow(Long id) {
        return users.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
    }
    // Hämta en användare via e-post, annars throw fel om den inte finns

    public User getByEmailOrThrow(String email) {
        return users.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new NotFoundException("User not found by email: " + email));
    }
    //SÅ controllern kan hämta alla användare som finns
    public java.util.List<User> findAll() {
        return users.findAll();
    }

    // Gör om User till en enklare DTO som inte innehåller lösenord
    public UserDTO toDTO(User u) {
        return new UserDTO(
                u.getUserId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getRegistrationDate()
        );
    }
}
