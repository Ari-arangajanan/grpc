package org.example.controller;

import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.example.model.Capital;
import org.example.grpc.client.CapitalClientService;
import org.example.service.CapitalService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {

    CapitalClientService bookAuthorClientService;

    private CapitalService bookService;



    @GetMapping("/author/{authorId}")
    public Map<Descriptors.FieldDescriptor,Object> getAuthor(@PathVariable String authorId) {
        return null;
    }

    //save Author
    @PostMapping("/author")
    public void saveAuthor(@RequestBody Capital account) {

    }

}
