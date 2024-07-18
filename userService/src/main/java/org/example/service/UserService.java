package org.example.service;import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;import com.devProblems.BookAuthorServiceGrpc;import com.devProblems.BookReq;import com.user.UserServiceGrpc;import net.devh.boot.grpc.client.inject.GrpcClient;import org.apache.rocketmq.spring.core.RocketMQTemplate;import org.example.common.GenerateId;import org.example.commonModels.UserChore;import org.example.repository.UserChoreDao;import org.springframework.stereotype.Service;@Servicepublic class UserService extends UserServiceGrpc.UserServiceImplBase {    private final RocketMQTemplate rocketMQTemplate;    private final UserChoreDao userChoreDao;    @GrpcClient("grpc-agentService")    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub bookAuthorServiceBlockingStub;    public UserService(RocketMQTemplate rocketMQTemplate, UserChoreDao userChoreDao) {        this.rocketMQTemplate = rocketMQTemplate;        this.userChoreDao = userChoreDao;    }    // create user saga choreography method    public void createUserChore(UserChore request) {        UserChore userChore = new UserChore();        userChore.setName(request.getName());        userChore.setRollbackId(GenerateId.generateUniqueId());        userChoreDao.insert(userChore);        try {            BookReq bookReq = BookReq.newBuilder()                    .setName(userChore.getName())                    .setRollbackId(userChore.getRollbackId())                    .build();            boolean res = bookAuthorServiceBlockingStub.saveBook(bookReq).getBookResponse();            rocketMQTemplate.convertAndSend("UserTopic", userChore);            System.out.println("user Saved: " + res);        } catch (RuntimeException e) {            rollbackUser(userChore.getRollbackId());            throw new RuntimeException("Failed to save book");        }    }    // this for compensation    public void rollbackUser(String rollbackId) {        userChoreDao.delete(new QueryWrapper<UserChore>().eq("rollbackId", rollbackId));    }}