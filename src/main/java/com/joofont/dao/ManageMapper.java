package com.joofont.dao;

import com.joofont.entity.Manage;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author cui jun on 2018/11/6.
 * @version 1.0
 */
public interface ManageMapper {

    Manage findUserByUsername(@Param("name") String name);

    Set<String> findRoles(@Param("name") String name);

    Set<String> findPermissions(@Param("name") String name);

}
