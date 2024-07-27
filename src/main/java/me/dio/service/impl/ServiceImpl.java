package me.dio.service.impl;

import me.dio.domain.model.Book;
import me.dio.domain.model.Loan;
import me.dio.domain.model.User;
import me.dio.domain.repository.BookRepository;
import me.dio.domain.repository.LoanRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.service.BookService;
import me.dio.service.LoanService;
import me.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ServiceImpl implements BookService, UserService, LoanService {

    //Autowireds
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;




    //Book Services

    @Override
    //Find book by id
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(NoSuchElementException::new);
    }

    @Override
    //Find first book with specific title (TEST)
    public List<Book> findByTitle(String title) {
        Optional<List<Book>> rightBook = Optional.empty();
        for(Book B:bookRepository.findAll()) {
            if (B.getTitle().contains(title)) {
                rightBook.stream().toList();
            }
        }
        return null;
    }

    @Override
    //Find first book with specific author (TEST)
    public List<Book> findByAuthor(String author) {
        Optional<List<Book>> rightBook = Optional.empty();
        for(Book B:bookRepository.findAll()) {
            if(B.getAuthor().contains(author)) {
                rightBook.get().add(B);
            }
        }
        return null;
    }

    @Override
    //Get all books
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    //Save new book on repository
    public Book saveBook(Book newBook) {
        if(bookAlreadyExists(newBook)) {
            throw new RuntimeException("Book already exists");
        }
        return bookRepository.save(newBook);
    }

    @Override
    //Update specific book title/author on repository
    public Book updateBook(Long id, Book newBook) {
        if(!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        newBook.setId(id);
        return bookRepository.save(newBook);
    }

    @Override
    //Delete specific book on repository
    public Book deleteBook(Long id) {
        if(!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        Book bookDeleted = bookRepository.findById(id).get();
        bookDeleted.setAvailable(false);
        bookDeleted.setTitle(bookDeleted.getTitle()+" (REMOVED FROM REPOSITORY)");
        bookRepository.deleteById(id);
        return bookDeleted;
    }




    //User Services

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User saveUser(User newUser) {
        if(userAlreadyExists(newUser)) {
            throw new RuntimeException("User already exists");
        }
        newUser.setRole("READER");
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        newUser.setRole(userRepository.findById(id).get().getRole());
        newUser.setId(id);
        return userRepository.save(newUser);
    }

    @Override
    public User deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User userDeleted = userRepository.findById(id).get();
        userDeleted.setRole("DELETED");
        userRepository.deleteById(id);
        return userDeleted;
    }




    //Loan Services

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Iterable<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Iterable<Loan> getAllPendentLoans() {
        List<Loan> pendentBooks = null;
        for(Loan L:loanRepository.findAll()) {
            if(L.getReturnDate().isBefore(LocalDate.now())){
                pendentBooks.add(L);
            }
        }
        return pendentBooks;
    }

    @Override
    //Add a new loan on repository
    public Loan saveLoan(Loan newLoan) {
        if(loanAlreadyExists(newLoan)) {
            throw new RuntimeException("Loan already exists");
        }
        newLoan.setLoanDate(LocalDate.now());
        newLoan.setReturnDate(LocalDate.now().plusDays(15));
        //Updating the book on repository to "not availble" (false)
        Book newLoanBook = bookRepository.findById(newLoan.getBook().getId()).get();
        newLoanBook.setAvailable(false);
        updateBook(newLoanBook.getId(), newLoanBook);
        return loanRepository.save(newLoan);
    }

    @Override
    //Add 15 days at a loan return date
    public Loan renewLoan(Long id) {
        if(!loanRepository.existsById(id)) {
            throw new RuntimeException("Loan not found");
        }
        Loan loanToRenew = loanRepository.findById(id).get();
        loanToRenew.setReturnDate(LocalDate.now().plusDays(15));
        return loanRepository.save(loanToRenew);
    }

    @Override
    public Loan deleteLoan(Long id) {
        if(!loanRepository.existsById(id)) {
            throw new RuntimeException("Loan not found");
        }
        Loan loanRemoved = loanRepository.findById(id).get();
        loanRemoved.getBook().setAvailable(true);
        //Updating the book on repository to "availble" (true)
        Book newBook = bookRepository.findById(loanRemoved.getBook().getId()).get();
        newBook.setAvailable(true);
        updateBook(newBook.getId(), newBook);
        loanRepository.deleteById(id);
        return loanRemoved;
    }




    //Private methods (support)

    private boolean bookAlreadyExists(Book book) {
        //If there's no equal book on repository, false. If there's equal book, true.
        boolean bookExists = false;
        for(Book B:bookRepository.findAll()) {
            if(B.getTitle().equalsIgnoreCase(book.getTitle()) && B.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                bookExists = true;
                break;
            }
        }
        return bookExists;
    }

    private boolean userAlreadyExists(User user) {
        //If there's no equal user on repository, false. If there's equal user, true.
        boolean userExists = false;
        for(User U:userRepository.findAll()) {
            if(U.getEmail().equalsIgnoreCase(user.getEmail())) {
                //Email is unique for each user;
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    private boolean loanAlreadyExists(Loan loan) {
        //If there's no equal loan on repository, false. If there's equal loan, true.
        boolean loanExists = false;
        for(Loan L:loanRepository.findAll()) {
            if(L.getBook().equals(loan.getBook()) || L.getUser().equals(loan.getUser())) {
                //Book and User are unique for each loan
                loanExists = true;
                break;
            }
        }
        return loanExists;
    }
}
