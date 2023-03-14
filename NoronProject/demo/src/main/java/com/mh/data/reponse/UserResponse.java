package com.mh.data.reponse;


import com.mh.utils.DateTime;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class UserResponse {

    private int id;
    private String username;
    private String email;
    private String phonenumber;

    public String getEmail() {
        System.out.println("hi");
        return email;
    }

    @Override
    public String toString() {
        System.out.println("hello");
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                "Time: "+DateTime.time2 +
                '}';
    }
}
