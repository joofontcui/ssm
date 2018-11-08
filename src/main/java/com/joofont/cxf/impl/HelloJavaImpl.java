package com.joofont.cxf.impl;

import com.joofont.cxf.HelloJava;
import com.joofont.entity.Book;
import com.joofont.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author cui jun on 2018/11/8.
 * @version 1.0
 */
@Component("helloJava")
@WebService(endpointInterface="com.joofont.cxf.HelloJava", targetNamespace="http://cxf.joofont.com/")
public class HelloJavaImpl implements HelloJava {

    @Autowired
    private BookService bookService;

    @Override
    public String getBook(Integer id) {
        Book book = bookService.getById(id);

        return "[book]:" + book;
    }

}
