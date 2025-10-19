package com.example.library.controller;

import com.example.library.entity.Loan;
import com.example.library.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Denna klass ör för att svara på allt som har med loan att tgöra, typ låna en book, returnera, förlänga
@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }
//  GET /loans/user/{userId}
// om en user lånar en book så skapar denna ett nytt loan
    @GetMapping("/user/{userId}")
    public List<Loan> byUser(@PathVariable Long userId) {
        return service.getUserLoans(userId);
    }

    @PostMapping
    public Loan borrow(@RequestParam Long loanId,
                       @RequestParam Long userId,
                       @RequestParam Long bookId) {
        return service.borrow(loanId, userId, bookId);
    }

    // PUT /loans/{id}/return
    // visar att en book är returnerad
    @PutMapping("/{id}/return")
    public Loan returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }

    // PUT  /Loans/{id}/extend
    // lägger till 14 dagar på låntiden

    @PutMapping("/{id}/extend")
    public Loan extend(@PathVariable Long id) {
        return service.extend(id);
    }

    // GET /users/{userId}/loans
    // hämta en användares loan via /users/{userId}/loans.
    @GetMapping("/users/{userId}/loans")
    public List<Loan> userLoansAlias(@PathVariable Long userId) {
        return service.getUserLoans(userId);
    }
    // GET /loan
    // hämta alla loans
    @GetMapping
    public java.util.List<com.example.library.entity.Loan> all() {
        return service.findAll();
    }
}
