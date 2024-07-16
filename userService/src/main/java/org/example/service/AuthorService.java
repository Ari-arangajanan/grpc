package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devProblems.BookAuthorServiceGrpc;
import com.devProblems.BookReq;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.model.Author;
import org.example.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@Service
//@AllArgsConstructor
public class AuthorService {


    @Autowired
    private AuthorDao authorDao;

    @GrpcClient("grpc-agentService")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub bookAuthorServiceBlockingStub;


    public void insert(Author author) {
        try {
            authorDao.insert(author);
            Author author1 = authorDao.selectOne(new QueryWrapper<Author>().eq("name", author.getName()));

            BookReq bookReq = BookReq.newBuilder()
                    .setBookReq(ByteString.copyFrom(SerializationUtils.serialize(author1.getId())))
                    .build();
            byte[] serializableObj = bookAuthorServiceBlockingStub.saveBook(bookReq).getBookResponse().toByteArray();
            System.out.println(SerializationUtils.deserialize(serializableObj));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
