package com.joofont.controller;

import com.joofont.entity.Book;
import com.joofont.service.BookService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author cui jun on 2018/10/28.
 * @version 1.0
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    private static Logger logger = Logger.getLogger(HelloWorldController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/world")
    @ResponseBody
    public String helloWorld(@RequestParam("id") Integer id) {
        Book book = bookService.getById(id);

        logger.info("book：" + book);

//        return "Hello world! book:" + book;
        return "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDnel0XTWqOqk0A+RQ6ryMt8GiRy5g7inPLQomvSIP9UPetAHiMIS1fMuSuDh/4y3Ob7daztCynGpR6M5bf9dKaVziXtmgDcRn/WsOt/fDF2rl33qlD3/lAWhMb8LRjL2s5mSMoBb+CiETK21ldxyAFtiIi0+W66iJFRI2N2URIT2azc3IZAwYNO+b8LtT4zw6bbYqqTfBf0fNMfxPtWbNflrovJ8tAfdM3BwS/dWPlVVFO5jCMBtVMOFMsZjIEioqP2hT4gigoYB2BvJwHEnmH94cFy/btIbOaT65Qhrk33z/3TfB/D+jffsKksURhBPfuo1sQhBM7Bs8b6VqIN4sN cuijun@cuijuns-MBP.lan";
    }

    @GetMapping("/book")
    public String helloBook(@RequestParam("id") Integer id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books")
    @ResponseBody
    public String getBookList() {
        List<Book> bookList = bookService.getAllBooks();
//        List<Book> books = Arrays.asList(new Book(),new Book());

        // 在Java8之前，对集合排序只能创建一个匿名内部类
//        Collections.sort(bookList, new Comparator<Book>() {
//            @Override
//            public int compare(Book o1, Book o2) {
//                return o2.getId().compareTo(o1.getId());
//            }
//        });

        // 函数式方式的比较器,利用泛型简化
//        bookList.sort((b1, b2) -> b1.getId().compareTo(b2.getId()));

//        Collections.sort(bookList, (b1, b2) -> b2.getId().compareTo(b1.getId()));

        // 静态方法引用
//        bookList.sort(Book::compareById);

        // 比较器
//        Collections.sort(bookList, Comparator.comparing(Book::getId));

        // 链式操作进行复合操作
//        bookList.sort(Comparator.comparing(Book::getId).thenComparing(Book::getCount));

        logger.info("bookList：" + bookList);

        return "bookList:" + bookList;
    }

    @GetMapping("/chat/room")
    public String chatRoom() {
        return "websocket/websocket";
    }

    private static int compareById(Book b1, Book b2) {
        if(b1.getId().equals(b2.getId())) {
            return b1.getCount().compareTo(b2.getCount());
        }
        return b1.getId().compareTo(b2.getId());
    }
}
