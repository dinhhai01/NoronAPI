package com.mh.repository;


import com.mh.data.mapper.IUserMapper;
import com.mh.data.reponse.UserResponse;
import com.mh.data.request.UserRequest;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final DSLContext dslContext;



    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;

    }

    public void insertUser(Users user) {
        LocalDate time = LocalDateTime.now().toLocalDate();
        dslContext.insertInto(
                        Tables.USERS, Tables.USERS.USERNAME,
                        Tables.USERS.PASS, Tables.USERS.EMAIL, Tables.USERS.PHONENUMBER,
                        Tables.USERS.CREATEAT, Tables.USERS.UPDATEAT, Tables.USERS.DELETEAT)
                .values(user.getUsername(), user.getPass(),
                        user.getEmail(),
                        user.getPhonenumber(), time, time, time)
                .execute();
    }

    @Override
    public List<Users> getAllUsers(List<Integer> postId) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.USERNAME,
                        Tables.USERS.EMAIL, Tables.USERS.PHONENUMBER)
                .from(Tables.USERS)
                .where(Tables.USERS.ID.in(postId))
                .fetchInto(Users.class);
    }

    @Override
    public void deleteUserById(int id) {
        dslContext.deleteFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .execute();
    }

    @Override
    public void updatePasswordById(int id, String pass) {
        dslContext.update(Tables.USERS)
                .set(Tables.USERS.PASS, pass)
                .where(Tables.USERS.ID.eq(id))
                .execute();
    }

    @Override
    public void updatePhone(String phone, int id) {
        dslContext.update(Tables.USERS)
                .set(Tables.USERS.PHONENUMBER, phone)
                .where(Tables.USERS.ID.eq(id));
    }

    @Override
    public Users getUserById(int id) {
        return dslContext.select()
                .from(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOneInto(Users.class);
    }

    @Override
    public List<Users> getAllUsersById(List<Integer> userIds) {
        return dslContext.select()
                .from(Tables.USERS)
                .where(Tables.USERS.ID.in(userIds))
                .fetchInto(Users.class);
    }

    @Override
    public Users updateUser(Users user) {
        int id = user.getId();
        Users users = dslContext.update(Tables.USERS)
                .set(Tables.USERS.USERNAME, user.getUsername())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.PHONENUMBER, user.getPhonenumber())
                .set(Tables.USERS.PASS, user.getPass())
                .set(Tables.USERS.CREATEAT, user.getCreateat())
                .set(Tables.USERS.UPDATEAT, user.getUpdateat())
                .set(Tables.USERS.DELETEAT, user.getDeleteat())
                .where(Tables.USERS.ID.eq(id))
                .returning(Tables.USERS.USERNAME,Tables.USERS.EMAIL,Tables.USERS.PHONENUMBER)
                .fetchOne().into(Users.class);
        return users;
    }
}
