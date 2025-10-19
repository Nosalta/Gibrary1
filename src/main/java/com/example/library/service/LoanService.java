package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.User;
import com.example.library.exception.BadRequestException;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// Här finns logiken för lånesystemet i Gibrary
// både låna en bok, retunrera en och förlänga lånetiden

@Service
public class LoanService {
    private final LoanRepository loans;
    private final UserRepository users;
    private final BookRepository books;


    public LoanService(LoanRepository loans, UserRepository users, BookRepository books) {
        this.loans = loans;
        this.users = users;
        this.books = books;
    }

    //Hämta Loan för en specifik User
    public List<Loan> getUserLoans(Long userId) {
        return loans.findByUserUserId(userId);
    }

    // här kan controllern hämta alla loans
    public java.util.List<com.example.library.entity.Loan> findAll() {
        return loans.findAll();
    }

    // Låna en bok
    @Transactional // så både Loan och Book uppdateras samtidigt
    public Loan borrow(Long loanId, Long userId, Long bookId) {

        // kollar upp User
        User user = users.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        // kollar upp Book
        Book book = books.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found: " + bookId));

        // sen om det inte finns någon bok kvar så säger den "no available copies for book"
        if (book.getAvailableCopies() == null || book.getAvailableCopies() <= 0) {
            throw new BadRequestException("No available copies for book: " + bookId);
        }

        // skapar ett nytt Loan
        Loan loan = new Loan();

        // om loanId skickas in manuellt, använd det – annars genererar databasen id själv
        if (loanId != null) {
            loan.setLoanId(loanId); // bara om du skickar in manuellt id
        }

        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14)); // +14 dagar

        // minskar availableCopies med 1
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        books.save(book);

        // sparar Loan
        return loans.save(loan);
    }


    // lämna tillbaka en Book
    @Transactional

    public Loan returnBook(Long loanId) {

        // hitta Loan
        Loan loan = loans.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan not found: " + loanId));


        if (loan.getReturnedDate() != null) { // sen om det redan är returnerat så säger jag "Loan already returned"
            throw new BadRequestException("Loan already returned");
        }

            // annars så sätter den att den är returnerad idag
        loan.setReturnedDate(LocalDate.now());


        // öka availableCopies (men inte för mkt så det går över totalCopies)
        Book book = loan.getBook();
        if (book.getAvailableCopies() != null && book.getTotalCopies() != null
                && book.getAvailableCopies() < book.getTotalCopies()) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            books.save(book);
        }

        return loans.save(loan);
    }

    // öka lånet med 14 dagar
    @Transactional
    public Loan extend(Long loanId) {
        // hitta Loan
        Loan loan = loans.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan not found: " + loanId));

        // om den redan är retunrnerad så skicka "cannot extend a returned loan"

        if (loan.getReturnedDate() != null) {
            throw new BadRequestException("Cannot extend a returned loan");
        }

        loan.setDueDate(loan.getDueDate().plusDays(14)); //förläng lånetiden med 14dgr
        return loans.save(loan);
    }
}
