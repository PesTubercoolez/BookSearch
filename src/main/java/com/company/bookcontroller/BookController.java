package com.company.bookcontroller;

import com.company.bookservice.BookService;
import com.company.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        bookService.addBook(newBook);
        return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
    }

    @PostMapping("/updateBook")
    public ResponseEntity<Book> updateBooks(@RequestBody Book book) {
        bookService.updateBookMap(book, book.getId());
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<Book> deleteBooks(@RequestParam Long id) {
        Book deletedBook = bookService.getBookFromMap(id);
        bookService.deleteBookFromMap(id);
        return new ResponseEntity<Book>(deletedBook, HttpStatus.OK);
    }

    @GetMapping("/getBookById")
    public ResponseEntity<Book> getBook(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.getBookFromMap(id), HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(new ArrayList<>(bookService.returnAllBooks().values()), HttpStatus.OK);
    }

    @GetMapping("/getAllByPriceAndHigher")
    public ResponseEntity<List<Book>> getBooksByPriceAndHigher(@RequestParam String price) {
       return new ResponseEntity<>(new ArrayList<>(bookService.findByPriceAndHigher(price).values()), HttpStatus.OK);
    }

    @GetMapping("/getAllByPriceAndLower")
    public ResponseEntity<List<Book>> getBooksByPriceAndLower(@RequestParam String price) {
        return new ResponseEntity<>(new ArrayList<>(bookService.findByPriceAndLower(price).values()), HttpStatus.OK);
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        return new ResponseEntity<>(new ArrayList<>(bookService.findByAuthor(author).values()), HttpStatus.OK);
    }
}
