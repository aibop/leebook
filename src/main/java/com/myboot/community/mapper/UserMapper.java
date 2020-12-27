package com.myboot.community.mapper;

import com.myboot.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_no, name, token, created_at, updated_at) values (#{accountNo}, #{name}," +
            " #{token}, #{createdAt}, #{updatedAt})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
