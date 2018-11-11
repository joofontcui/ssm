package com.joofont.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cui jun on 2018/10/28.
 * @version 1.0
 */
@Controller
@RequestMapping("/socket")
public class SocketController {

    private static Logger logger = Logger.getLogger(SocketController.class);

    @GetMapping("/chat/room")
    public String chatRoom() {
        return "websocket/websocket";
    }

}