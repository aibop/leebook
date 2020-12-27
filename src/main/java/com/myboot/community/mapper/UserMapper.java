package com.myboot.community.mapper;

import com.myboot.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_no, name, token, created_at, updated_at) values (#{accountNo}, #{name}," +
            " #{token}, #{createdAt}, #{updatedAt})")
    void insert(User user);
}
