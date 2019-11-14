package com.bj.test.springboot02.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {
    @Autowired
    private MailServiceImpl mailService;
    @Test
    public void sendSimpleMail() throws Exception {
       // mailService.sendSimpleMail("bjgz1022@163.com","test simple mail"," hello this is simple mail");
    }
    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendSimpleMail("bjgz1022@163.com","test simple mail",content);
    }
    @Test
    public void sendAttachmentsMail() throws Exception {
        String filePath="C:\\Users\\jstd_bj\\Pictures\\1R62131Q-2.jpg";
        mailService.sendAttachmentsMail("bjgz1022@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }
    @Test
    public void sendInlineResourceMail() throws Exception {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\jstd_bj\\Pictures\\021.gif";

        mailService.sendInlineResourceMail("bjgz1022@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
    @Test
    public void sendTemplateMail() {
        TemplateEngine templateEngine = new TemplateEngine();
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        mailService.sendTemplateMail("bjgz1022@163.com","主题：这是模板邮件",context,null );
    }
}