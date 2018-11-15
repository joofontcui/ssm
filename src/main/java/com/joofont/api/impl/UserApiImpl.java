package com.joofont.api.impl;

import api.UserApi;
import com.alibaba.dubbo.config.annotation.Service;
import com.joofont.entity.User;
import com.joofont.service.UserService;
import dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import req.UserReq;

/**
 * @author cui jun on 2018/11/14.
 * @version 1.0
 */
@Service
public class UserApiImpl implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO getUserDTO(UserReq req) {
        User user = userService.getUser(req.getId());

        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }

}
