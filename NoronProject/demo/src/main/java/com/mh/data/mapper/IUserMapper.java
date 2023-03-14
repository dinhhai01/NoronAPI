package com.mh.data.mapper;

import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class IUserMapper {
    public abstract Users toEntity(UserRequest userRequest);
    public abstract UserResponse toResponse(Users user);
}
