package com.joofont.service.impl;

import com.joofont.dao.ContentMapper;
import com.joofont.entity.Content;
import com.joofont.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cui jun on 2018/11/10.
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Content> findContentList() {
        return contentMapper.findContentList();
    }

    @Override
    public int insertSelective(Content content) {
        return contentMapper.insertSelective(content);
    }
}
