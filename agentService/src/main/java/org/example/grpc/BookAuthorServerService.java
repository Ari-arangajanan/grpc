package org.example.grpc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devProblems.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

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
            Book book = new Book();
            book.setAuthorId(request.getAuthorId());
            book.setName(request.getName());
            book.setRollbackId(request.getRollbackId());
            boolean isSaved = bookService.insert(book);

            BookResponse.Builder response = BookResponse.newBuilder()
                    .setBookResponse(isSaved);

            responseObserver.onNext(response.build());
        }catch (Exception e){
            responseObserver.onNext(null);
        }finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void transactionalRollBack(BookReq request, StreamObserver<TransactionalResponse> responseObserver) {
        try {
            Book book = new Book();
            book.setAuthorId(request.getAuthorId());
            book.setName(request.getName());
            book.setRollbackId(request.getRollbackId());

//            Book bookObj = bookService.getOne(new QueryWrapper<Book>().eq("authorId", authorId));
            boolean isDeleted = bookService.remove(new QueryWrapper<Book>().eq("rollbackId", request.getRollbackId()));
            TransactionalResponse.Builder transactionalResponse = TransactionalResponse.newBuilder();
            transactionalResponse.setResponse(isDeleted);

            responseObserver.onNext(transactionalResponse.build());
        }catch (Exception e){
            responseObserver.onError(new Throwable("rollback Failed"));
            responseObserver.onNext(null);
            throw new RuntimeException("Book save failed");
        }finally {
            responseObserver.onCompleted();
        }
    }
}
