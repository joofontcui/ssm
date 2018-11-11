package com.joofont.dao;

import com.joofont.entity.Content;

import java.util.List;

/**
 * @author cui jun on 2018/11/10.
 * @version 1.0
 */
public interface ContentMapper {

    /**
     * 返回所有内容
     * @return
     */
    List<Content> findContentList();

    /**
     * 新增
     * @param content
     * @return
     */
    int insertContent(Content content) ;

}
