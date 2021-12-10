package com.benyuantech.challenge.controller;

import com.benyuantech.challenge.model.Message;
import com.benyuantech.challenge.service.MessageService;
import com.benyuantech.challenge.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class MessageController {
    
    @Autowired
    private MessageService service;


    /**
     * 新增一层楼
     * @param request 请求头
     * @param name 昵称
     * @param contract 联系方式
     * @param message 留言内容
     */
    @RequestMapping(value = "/add/message")
    public @ResponseBody
    Object addMessage(
            HttpServletRequest request,
            String name,
            String contract,
            String message
    ) {
        Message object = new Message();
        object.setIp(IpUtil.getIpAddr(request));
        object.setName(name);
        object.setContract(contract);
        object.setCreatedTime(new Date());
        // message 可能为空，所以需要先进行判断，然后注入
        if (message == null || "".equals(message)){
            object.setMessage("未提供");// 同参数name，如果为空需要注入 未提供
        } else {
            object.setMessage(message);
        }
        return service.addMessage(object);
    }
}
