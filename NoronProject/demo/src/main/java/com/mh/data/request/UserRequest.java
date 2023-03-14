package com.mh.data.request;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class UserRequest {

    private int id;
    private String username;
    private String pass;
    private String email;
    private String phonenumber;
}

