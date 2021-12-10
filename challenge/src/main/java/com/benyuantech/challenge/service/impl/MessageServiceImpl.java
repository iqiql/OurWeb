package com.benyuantech.challenge.service.impl;

import com.benyuantech.challenge.mapper.MessageMapper;
import com.benyuantech.challenge.model.Message;
import com.benyuantech.challenge.service.MessageService;
import com.benyuantech.challenge.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;

    @Autowired
    private EmailUtil util;

    @Override
    public boolean addMessage(Message message) {
        String text = "留言人的ip：" + message.getIp() + "\n留言者填的称呼：" + message.getName() + "\n留言者填的联系方式：" + message.getContract() +
                "\n留言者的留言内容:" + message.getMessage() + "\n留言时间:" + message.getCreatedTime();
        String goal = "qiqilong1043@163.com";
        String subject = "网站有了新的留言";
        util.sendMail(goal,subject,text);
        return mapper.insert(message) == 1;
    }
}