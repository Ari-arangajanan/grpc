package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    //save Book
    @PostMapping("/book")
    public void saveBook(@RequestBody Book book) {
        bookService.insert(book);
    }
}
