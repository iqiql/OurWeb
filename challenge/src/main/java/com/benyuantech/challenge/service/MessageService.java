package com.benyuantech.challenge.service;

import com.benyuantech.challenge.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    boolean addMessage(Message message);//添加留言
}
