package me.dio.service;

import me.dio.domain.model.Book;
import me.dio.domain.model.User;

import java.util.List;

public interface BookService {

    //Get methods
    Book findById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    Iterable<Book> getAllBooks();

    //Post methods
    Book saveBook(Book newBook);

    //Put methods
    Book updateBook(Long id, Book newBook);

    //Delete methods
    Book deleteBook(Long id);
}
