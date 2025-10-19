package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Här börjar mitt Gibrary, "DJAYBRARY"
// @SpringBootApplication säger till Spring Boot att starta igång allting

@SpringBootApplication
public class LibraryApplication {

    // detta är min Main metod - så när jag "RUN" så kallar jag på denna metod som kör ingång allt
    public static void main(String[] args) {
        //Den startat också igång min localhost/8080
        SpringApplication.run(LibraryApplication.class, args);
    }
}
