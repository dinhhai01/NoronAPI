package com.mh.repository;


import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import java.util.List;

public interface IUserRepository{

    Users insertUser(Users user);

    List<Users> getAllUsers(List<Integer> postId);


    void deleteUserById(int id);


    void updatePhone(String phone, int id);


    void updatePasswordById(int id,String pass);

    Users updateUser(Users user);

    Users getUserById(int id);

    List<Users> getAllUsersById(List<Integer> userIds);
}
