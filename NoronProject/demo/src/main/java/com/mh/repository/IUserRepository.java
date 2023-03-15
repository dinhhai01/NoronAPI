package com.mh.repository;


import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;

import java.util.List;

public interface IUserRepository{

    Single<Users> insertUser(Users user);

    Single<List<Users>> getAllUsers(List<Integer> postId);


    void deleteUserById(int id);


    void updatePhone(String phone, int id);


    void updatePasswordById(int id,String pass);

    Single<Users> updateUser(Users user);

    Single<Users> getUserById(int id);

    Single<List<Users>> getAllUsersById(List<Integer> userIds);
}
