package org.example;

import com.devProblems.Author;
import com.devProblems.BookAuthorServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorServerService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {
    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
//        TempDb.getAuthorsFromTempDb()
//                .stream()
//                .filter(author -> author.getAuthorId() == request.getAuthorId())
//                .findFirst()
//                .ifPresent(responseObserver::onNext);
        responseObserver.onNext(Author.newBuilder().setFirstName("This from Service ").build());
//        responseObserver.onError(new RuntimeException("Author not found"));// this line is to throw an error if occurred
        responseObserver.onCompleted();
    }

}
