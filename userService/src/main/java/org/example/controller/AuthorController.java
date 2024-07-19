package org.example.controller;

import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.example.model.Author;
import org.example.service.BookAuthorClientService;
import org.example.service.AuthorServiceConductor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthorController {

    private final BookAuthorClientService bookAuthorClientService;

    private final AuthorServiceConductor bookService;

    public AuthorController(BookAuthorClientService bookAuthorClientService, AuthorServiceConductor bookService) {
        this.bookAuthorClientService = bookAuthorClientService;
        this.bookService = bookService;
    }


    @GetMapping("/author/{authorId}")
    public Map<Descriptors.FieldDescriptor,Object> getAuthor(@PathVariable String authorId) {
        return bookAuthorClientService.getAuthor(Integer.parseInt(authorId));
    }

    //save Author
    @PostMapping("/author")
    public boolean saveAuthor(@RequestBody Author author) {
        return bookService.insert(author);
    }

}
