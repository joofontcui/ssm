package com.joofont.dao;

import com.joofont.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cui jun on 2018/11/4.
 * @version 1.0
 */
public interface UserMapper {

    List<User> getAllUserList();

    User getUser(@Param("id") Integer id);

    int updateById(User user);

}
