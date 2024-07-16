package org.example.controller;

import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.example.model.Account;
import org.example.service.BookAuthorClientService;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {

    BookAuthorClientService bookAuthorClientService;

    private AccountService bookService;



    @GetMapping("/author/{authorId}")
    public Map<Descriptors.FieldDescriptor,Object> getAuthor(@PathVariable String authorId) {
        return bookAuthorClientService.getAuthor(Integer.parseInt(authorId));
    }

    //save Author
    @PostMapping("/author")
    public void saveAuthor(@RequestBody Account account) {
        bookService.insert(account);
    }

}
