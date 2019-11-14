package com.bj.study.springboot.jms.service;

import java.util.Map;

public interface MailService {
    public void sendTxtMail(String subject, String text) throws Exception;
    public void sendHtmlMail(String subject, String text, Map<String, String> attachmentMap) throws Exception;
    public void sendAttachedImageMail() throws Exception;
    public void sendAttendedFileMail() throws Exception;
    public void sendTemplateMail(String subject, Map<String, Object> params) throws Exception;
}
