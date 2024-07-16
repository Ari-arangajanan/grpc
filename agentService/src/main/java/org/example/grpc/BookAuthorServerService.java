package org.example.grpc;

import com.devProblems.Author;
import com.devProblems.BookAuthorServiceGrpc;
import com.devProblems.BookReq;
import com.devProblems.BookResponse;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.util.Objects;

@GrpcService
public class BookAuthorServerService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {
    @Autowired
    private BookService bookService;

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

    @Override
    public void saveBook(BookReq request, StreamObserver<BookResponse> responseObserver) {
        try {
            Object req = SerializationUtils.deserialize(request.getBookReq().toByteArray());
            Integer authorId = (Integer) req;
            Book book = new Book();
            book.setAuthorId(authorId);
            book.setName(authorId + "sampleName");

            boolean isSaved = bookService.insert(book);

            BookResponse.Builder response = BookResponse.newBuilder()
                    .setBookResponse(ByteString.copyFrom(Objects.requireNonNull(SerializationUtils.serialize(isSaved))));

            responseObserver.onNext(response.build());
        }catch (Exception e){
            responseObserver.onNext(null);
        }finally {
            responseObserver.onCompleted();
        }


    }
}
