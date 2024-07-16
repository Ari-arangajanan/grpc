package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devProblems.BookAuthorServiceGrpc;
import com.devProblems.BookReq;
import com.devProblems.UserReq;
import com.devProblems.UserServiceGrpc;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.model.Author;
import org.example.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Objects;

@Service
public class AuthorService {


    @Autowired
    private AuthorDao authorDao;

    @GrpcClient("grpc-agentService")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub bookAuthorServiceBlockingStub;

    @GrpcClient("grpc-accountService")
    UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;


    public boolean insert(Author author) {
        try {
            authorDao.insert(author);
            Author author1 = authorDao.selectOne(new QueryWrapper<Author>().eq("name", author.getName()));

            BookReq bookReq = BookReq.newBuilder()
                    .setBookReq(ByteString.copyFrom(Objects.requireNonNull(SerializationUtils.serialize(author1.getId()))))
                    .build();
            byte[] serializableObj = bookAuthorServiceBlockingStub.saveBook(bookReq).getBookResponse().toByteArray();
            Boolean res = (Boolean) SerializationUtils.deserialize(serializableObj);
            System.out.println(SerializationUtils.deserialize(serializableObj));

            if (userAccountServe(author1) && Boolean.TRUE.equals(res)){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    private boolean userAccountServe(Author author) {
        try {
            UserReq userReq = UserReq.newBuilder()
                    .setId(author.getId())
                    .setName(author.getName())
                    .build();
            byte[] response = userServiceBlockingStub.saveUserAccount(userReq).getUserResponse().toByteArray();
            Boolean out = (Boolean) SerializationUtils.deserialize(response);
            return Boolean.TRUE.equals(out);
        }catch (Exception e){
            return false;
        }

    }
}
