package com.mh.service;

import com.mh.data.mapper.IUserMapper;
import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.mh.repository.IUserRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService implements IUserService {

    private final IUserMapper mapper;

    private final IUserRepository repository;



    public UserService(IUserMapper mapper,
                       IUserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;

    }

    @Override
    public Single<List<Users>> getAllUsers(List<Integer> postId) {
        return repository.getAllUsers(postId);
//        return Single.create(singleEmitter -> {
//            List<Users> users = repository.getAllUsers(postId);
//            singleEmitter.onSuccess(users);
//        });

    }

    public Single<UserResponse> insertUser(UserRequest userRequest){
        Users usersEntity = mapper.toEntity(userRequest);
        return repository.insertUser(usersEntity)
                .map(mapper::toResponse);
//        Users users = repository.insertUser(usersEntity);
//        return Single.just(users)
//                .subscribeOn(Schedulers.io())
//                .map(mapper::toResponse);

    }

    @Override
    public void updatePhoneUserByID(String phone, int id) {
        repository.updatePhone(phone, id);
    }

    @Override
    public void updatePasswordUserByID(int id, String pass) {
        repository.updatePasswordById(id, pass);
    }

    @Override
    public void deleteUserById(int id) {
        repository.deleteUserById(id);
    }

public Single<UserResponse> updateUser(UserRequest userRequest){
        int id = userRequest.getId();
        return repository.getUserById(id)
                .flatMap(user->{
                    String userName = userRequest.getUsername();
                    String pass = userRequest.getPass();
                    String phone = userRequest.getPhonenumber();
                    String email = userRequest.getEmail();
                    if (userName!= null &&!userName.isEmpty()) {
                        user.setUsername(userName);
                    }
                    if (pass!= null &&!pass.isEmpty()) {
                        user.setPass(pass);
                    }
                    if (phone!= null &&!phone.isEmpty()) {
                        user.setPhonenumber(phone);
                    }
                    if (email!= null &&!email.isEmpty()) {
                        user.setEmail(email);
                    }

                    return repository.updateUser(user)
                            .map(mapper::toResponse);
                });

//        return Single.create(singleEmitter -> {
//            int id = userRequest.getId();
//            Users user = repository.getUserById(id);
//            String userName = userRequest.getUsername();
//            String pass = userRequest.getPass();
//            String phone = userRequest.getPhonenumber();
//            String email = userRequest.getEmail();
//            if (userName != null && !userName.isEmpty()) {
//                user.setUsername(userName);
//            }
//            if (pass != null && !pass.isEmpty()) {
//                user.setPass(pass);
//            }
//            if (phone != null && !phone.isEmpty()) {
//                user.setPhonenumber(phone);
//            }
//            if (email != null && !email.isEmpty()) {
//                user.setEmail(email);
//            }
//            Users users = repository.updateUser(user);
//            singleEmitter.onSuccess(mapper.toResponse(users));
//        });
    }

    @Override
    public Single<String> getUserById(int id) {
        return repository.getUserById(id)
                .map(mapper::toResponse)
                .map(UserResponse::getEmail);
//        Single<UserResponse> userResponseSingle = Single.create(singleSubscribe->{
//            Users users = repository.getUserById(id);
//            if(users!=null){
//                singleSubscribe.onSuccess(mapper.toResponse(users));
//            }else{
//                singleSubscribe.onError(new Exception());
//            }
//        });
//
//        return userResponseSingle.subscribeOn(Schedulers.io())
//                .map(UserResponse::getEmail);
    }

}
