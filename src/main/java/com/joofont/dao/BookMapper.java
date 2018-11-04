package com.joofont.dao;

import com.joofont.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cui jun on 2018/10/28.
 * @version 1.0
 */
public interface BookMapper {

    Book getById(@Param("id") Integer id);

    List<Book> getAllBooks();

}
