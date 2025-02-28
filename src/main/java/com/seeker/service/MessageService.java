package com.seeker.service;

import com.seeker.entity.ImMessage;
import com.seeker.mapper.ImMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：悬崖上的列车(Jiuling Huan)
 * @date ：Created in 2025/2/27
 * @slogan: 莫听穿林打叶声 何妨吟啸且徐行 竹杖芒鞋轻胜马 一蓑烟雨任平生
 * @email: 514082870@qq.com
 * @desc:
 **/
@Service
public class MessageService {

    @Autowired
    private ImMessageMapper messageMapper;

    public String insert(){
        ImMessage imMessage = new ImMessage();
        imMessage.setMessage("test");
        imMessage.setSenderId(1);
        imMessage.setReceiver(2);
        imMessage.setStatus("1");
        messageMapper.insert(imMessage);
        return "success";
    }

    public String read(){
        messageMapper.selectByPrimaryKey(1);
        return "success";
    }

}
