package com.mh.service;

import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.mh.entity.User;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {
    List<Users> getAllUsers(List<Integer> postId);

    UserResponse insertUser(UserRequest userRequest);

    void updatePhoneUserByID(String phone,int id);

    void updatePasswordUserByID(int id,String pass);

    void deleteUserById(int id);

    Single<UserResponse> updateUser(UserRequest userRequest);

    Single<String> getUserById(int id);
}
