package org.example.controller;

import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.example.model.Author;
import org.example.service.BookAuthorClientService;
import org.example.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthorController {

    BookAuthorClientService bookAuthorClientService;

    private AuthorService bookService;



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
