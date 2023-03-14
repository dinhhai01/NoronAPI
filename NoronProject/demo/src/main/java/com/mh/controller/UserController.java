package com.mh.controller;

import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.mh.service.UserService;
import com.mh.utils.DateTime;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }


//    @GetMapping()
//    public List<Users> getAllUsers(){
//        return service.getAllUsers();
//    }

    @GetMapping(value = "/{id}")
    public Single<String> getUserById(@PathVariable(name = "id") int id){
        return service.getUserById(id);
    }

    @PostMapping()
    public UserResponse insertUser(@RequestBody UserRequest userRequest) {

        return service.insertUser(userRequest);
    }

    @PutMapping(value = "/update")
    public Single<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        return service.updateUser(userRequest)
                .subscribeOn(Schedulers.io());
    }

//    @PutMapping(value = "/{id}/{phone}")
//    public void updateUser(@PathVariable(name = "id") int id,@PathVariable(name = "phone") String phone){
//        service.updatePhoneUserByID(phone, id);
//    }

//    @PutMapping(value = "/{id}")
//    public void updatePasswordUserByID(@PathVariable(name = "id") int id, @RequestBody User user){
//        service.updatePasswordUserByID(id, user.getPass());
//    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable(name = "id") int id){
        service.deleteUserById(id);
    }

}
