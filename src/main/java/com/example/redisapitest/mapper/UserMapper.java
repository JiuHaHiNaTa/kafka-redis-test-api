package com.example.redisapitest.mapper;

import com.example.redisapitest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {


    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User findUserById(String id);
}
