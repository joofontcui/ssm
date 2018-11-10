package com.joofont.service;

import com.joofont.entity.Content;

import java.util.List;

/**
 * @author cui jun on 2018/11/10.
 * @version 1.0
 */
public interface ContentService {

    /**
     * 获取内容list
     * @return
     */
    List<Content> findContentList();

    /**
     * 根据条件新增
     * @param content
     * @return
     */
    int insertSelective(Content content) ;

}
