package com.bj.study.springboot.jms.serviceimpl;

import com.bj.study.springboot.jms.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {
    @Autowired
    private MailService mailService;
    @Test
    public void sendMail() throws Exception{
        mailService.sendTxtMail("测试Springboot发送邮件", "发送邮件...");
    }
    @Test
    public void send1Mail() throws Exception{
        Map<String, String> attachmentMap = new HashMap<>();
        attachmentMap.put("附件", "file.txt的绝对路径");
        mailService.sendHtmlMail("测试Springboot发送带附件的邮件", "欢迎进入<a href=\"http://www.baidu.com\">百度首页</a>", attachmentMap);
    }
    @Test
    public void testFreemarkerMail() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "Cay");
        mailService.sendTemplateMail("测试Springboot发送模版邮件", params);
    }
}