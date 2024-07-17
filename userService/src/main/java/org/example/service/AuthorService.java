package org.example.service;

import com.devProblems.BookAuthorServiceGrpc;
import com.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.common.GenerateId;
import org.example.config.SagaOrchestrator;
import org.example.model.Author;
import org.example.repository.AuthorDao;
import org.springframework.stereotype.Service;
@Service
public class AuthorService {



    private final AuthorDao authorDao;

    @GrpcClient("grpc-agentService")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub bookAuthorServiceBlockingStub;

    @GrpcClient("grpc-accountService")
    UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    public boolean insert(Author author) {
        author.setRollbackId(GenerateId.generateUniqueId());
        CreateAuthor createAuthor = new CreateAuthor(authorDao,author );
        SaveBook saveBook = new SaveBook(bookAuthorServiceBlockingStub, author);
        SaveUserAccount saveUserAccount = new SaveUserAccount(userServiceBlockingStub, author);

        SagaOrchestrator sagaOrchestrator = new SagaOrchestrator();
        sagaOrchestrator.addStep(createAuthor);
        sagaOrchestrator.addStep(saveBook);
        sagaOrchestrator.addStep(saveUserAccount);

        try {
            sagaOrchestrator.execute();
            return true;
        }catch (RuntimeException e) {
            System.out.println("Saga execution failed: " + e.getMessage());
            return false;
        }
    }





    // todo: below code for general operation without transactional
//    public boolean insert(Author author) {
//        try {
//            authorDao.insert(author);
//            Author author1 = authorDao.selectOne(new QueryWrapper<Author>().eq("name", author.getName()));
//
//            BookReq bookReq = BookReq.newBuilder()
//                    .setBookReq(ByteString.copyFrom(Objects.requireNonNull(SerializationUtils.serialize(author1.getId()))))
//                    .build();
//            byte[] serializableObj = bookAuthorServiceBlockingStub.saveBook(bookReq).getBookResponse().toByteArray();
//            Boolean res = (Boolean) SerializationUtils.deserialize(serializableObj);
//            System.out.println(SerializationUtils.deserialize(serializableObj));
//
//            if (userAccountServe(author1) && Boolean.TRUE.equals(res)){
//                return true;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        return false;
//    }
//
//    private boolean userAccountServe(Author author) {
//        try {
//            UserReq userReq = UserReq.newBuilder()
//                    .setId(author.getId())
//                    .setName(author.getName())
//                    .build();
//            byte[] response = userServiceBlockingStub.saveUserAccount(userReq).getUserResponse().toByteArray();
//            Boolean out = (Boolean) SerializationUtils.deserialize(response);
//            return Boolean.TRUE.equals(out);
//        }catch (Exception e){
//            return false;
//        }
//
//    }


}
