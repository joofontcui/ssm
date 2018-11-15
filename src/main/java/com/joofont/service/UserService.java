package com.joofont.service;

import com.joofont.entity.User;

import java.util.List;

/**
 * @author cui jun on 2018/11/4.
 * @version 1.0
 */
public interface UserService {

    void addIndex(User user) throws Exception;

    List<User> searchBlog(String q) throws Exception;

    List<User> getAllUserList();

    User getUser(Integer id);

}
