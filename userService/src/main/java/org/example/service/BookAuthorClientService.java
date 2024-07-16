package org.example.service;

import com.devProblems.Author;
import com.devProblems.BookAuthorServiceGrpc;
import com.google.protobuf.Descriptors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@Service
public class BookAuthorClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookAuthorClientService.class);
    /*
     This code snippet's primary purpose is to create a client-side interface to interact with a gRPC service
     named "book-author-service". By using this stub, you can call methods defined in the service's Protobuf definition,
     and the framework will handle the underlying gRPC communication.
     */

    @GrpcClient("grpc-devproblems-service")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub bookAuthorServiceBlockingStub;


    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId) {
        //builderMethod: Using a builder pattern (Author.newBuilder()) to construct the object.
        LOGGER.info("Attempting to get author with ID: {}", authorId);
        if (bookAuthorServiceBlockingStub != null) {
            LOGGER.error("bookAuthorServiceBlockingStub is null. Injection or initialization issue.");
            throw new IllegalStateException("bookAuthorServiceBlockingStub is null. Injection or initialization issue.");
        }
       Author authorReq = Author.newBuilder().setAuthorId(authorId).build();
       Author authorResponse =  bookAuthorServiceBlockingStub.getAuthor(authorReq);
        LOGGER.info("Received author response: {}", authorResponse);
       return authorResponse.getAllFields();
    }
}
