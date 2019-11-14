package com.bj.study.springboot.jms.serviceimpl;

import com.bj.study.springboot.jms.service.MailService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Override
    public void sendTxtMail(String subject, String text) throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人,此处内容可配置
        simpleMailMessage.setTo(new String[] {"1371786675@qq.com"});
        simpleMailMessage.setFrom("bjgz1022@163.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        // 发送邮件
        mailSender.send(simpleMailMessage);
    }
    @Override
    public void sendHtmlMail(String subject, String text, Map<String, String> attachmentMap) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //是否发送的邮件是富文本（附件，图片，html等）
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo("1371786675@qq.com");
        mimeMessageHelper.setFrom("bjgz1022@163.com");
        mimeMessageHelper.setSubject(subject);

        /*StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");*/

        // 启用html
        mimeMessageHelper.setText(/*sb.toString()*/text, true);//默认为false，显示原始html代码，无效果
        // 发送邮件
        mailSender.send(mimeMessage);
    }
    @Override
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo("bjgz1022@163.com");
        mimeMessageHelper.setFrom("bjgz1022@163.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src=\"cid:imageId\"/></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("E:/1.jpg"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);
    }
    @Override
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo("bjgz1022@163.com");
        mimeMessageHelper.setFrom("bjgz1022@163.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource img = new FileSystemResource(new File("E:/1.jpg"));
        mimeMessageHelper.addAttachment("image.jpg", img);

        // 发送邮件
        mailSender.send(mimeMessage);
    }
    @Override
    public void sendTemplateMail(String subject, Map<String, Object> params) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("bjgz1022@163.com");
        helper.setTo("bjgz1022@163.com");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), params);
        helper.setSubject(subject);
        helper.setText(html, true);//重点，默认为false，显示原始html代码，无效果

        mailSender.send(mimeMessage);
    }
}
