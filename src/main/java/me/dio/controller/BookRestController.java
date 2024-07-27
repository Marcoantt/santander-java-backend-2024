package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.Book;
import me.dio.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }



    //Get Methods
    @GetMapping("/{id}")
    @Operation(summary = "Find a book by Id", method = "GET")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        var book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    @Operation(summary = "Get a list with all books", method = "GET")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Iterable<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    //Post Methods
    @PostMapping
    @Operation(summary = "Add a new book on repository", method = "POST")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        var bookSaved = bookService.saveBook(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(bookSaved);
    }

    //Put Methods
    @PutMapping("/{id}")
    @Operation(summary = "Update a book by Id", method = "PUT")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        var bookUpdated = bookService.updateBook(id, book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(bookUpdated);
    }

    //Delete Methods
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by Id", method = "DELETE")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        var bookDeleted = bookService.deleteBook(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookDeleted.getId())
                .toUri();
        return ResponseEntity.created(location).body(bookDeleted);
    }
}
