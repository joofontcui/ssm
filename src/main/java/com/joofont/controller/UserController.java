package com.joofont.controller;

import com.joofont.annotation.OperationRecord;
import com.joofont.entity.User;
import com.joofont.enums.PlatformEnum;
import com.joofont.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author cui jun on 2018/11/22.
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/v1/changeAmount")
    @ResponseBody
    @OperationRecord(platform = PlatformEnum.BOOK)
    public String changeAmount() {
        try {
            for (int i=0;i<10;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = userService.getUser(16);
                        int amount = new Random().nextInt(20);
                        user.setAmount(amount);
                        int count = userService.updateById(user);
                        if (count == 0){
                            logger.error("更新失败");
                        }else {
                            logger.info("更新成功");
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            logger.error("e:"+e);
        }
        return "ok! user";
    }

}
