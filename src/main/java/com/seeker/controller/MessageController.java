package com.seeker.controller;

import com.seeker.entity.ImMessage;
import com.seeker.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：悬崖上的列车(Jiuling Huan)
 * @date ：Created in 2025/2/27
 * @slogan: 莫听穿林打叶声 何妨吟啸且徐行 竹杖芒鞋轻胜马 一蓑烟雨任平生
 * @email: 514082870@qq.com
 * @desc:
 **/
@RestController
@RequestMapping(value = "/im")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/send")
    public String send(){
        messageService.insert();
        return "success";
    }
    @RequestMapping(value = "/read")
    public String read(){
        messageService.read();
        return "success";
    }

}
