package com.joofont.service;

import com.joofont.entity.Manage;

import java.util.Set;

/**
 * @author cui jun on 2018/11/6.
 * @version 1.0
 */
public interface ManageService {

    Manage findUserByUsername(String name);

    Set<String> findRoles(String name);

    Set<String> findPermissions(String name);

}
