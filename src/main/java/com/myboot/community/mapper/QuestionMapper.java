package com.myboot.community.mapper;

import com.myboot.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, creater, tag, created_at, updated_at) values (#{title}, " +
            "#{description}, #{tag}, #{creater}, #{createdAt}, #{updatedAt})")
    void create(Question question);
}
