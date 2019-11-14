package com.bj.test.springboot02.service;

import javafx.util.Pair;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface MailService {
    /**
     * 发送简单邮件
     * @param to 收件人地址
     * @param subject  邮件标题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送简单邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     * @param filePath 附件列表
     */
    public void sendAttachmentsMail(String sendTo, String titel, String content, String filePath);
    /*发送带静态资源的邮件*/
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    /**
     * 发送模板邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content<key, 内容> 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    public void sendTemplateMail(String sendTo, String titel, Context content, List<Pair<String, File>> attachments);
}
