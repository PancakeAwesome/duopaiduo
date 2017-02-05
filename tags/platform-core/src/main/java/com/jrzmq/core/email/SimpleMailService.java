/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.jrzmq.core.email;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 纯文本邮件服务类.
 * 
 * @author calvin
 */
public class SimpleMailService {
    private static Logger logger = LoggerFactory.getLogger(SimpleMailService.class);
    
    private static final String DEFAULT_ENCODING = "utf-8";

    private JavaMailSender mailSender;
    
    private Configuration freemarkerConfiguration;
    
    /**
     * 发送纯文本邮件
     * @param emailTo 邮箱
     * @param project 主题
     * @param 邮件模板名称
     * @param map     邮件内容模板对象
     */
    public void sendMail(String emailTo, String subject, String templateName, Map<String, Object> map) {
        SimpleMailMessage msg = new SimpleMailMessage();        
        msg.setSentDate(new Date());
        msg.setTo(emailTo);
        msg.setSubject(subject);

        try {
            msg.setText(generateContent(templateName, map));
            mailSender.send(msg);
            if (logger.isInfoEnabled()) {
                logger.info("纯文本邮件已发送至{}", StringUtils.join(msg.getTo(), ","));
            }
        } catch (Exception e) {
            logger.error("发送邮件到{}失败,邮件主题:{},", new Object[]{emailTo, subject}, e);
        }
    }

    /**
     * 生成邮件内容
     * @param templateName        模板名称
     * @param map                 邮件参数对象
     * @return
     * @throws MessagingException
     */
    private String generateContent(String templateName, Map<String, Object> map) throws MessagingException {

        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(templateName, DEFAULT_ENCODING), map);
        } catch (IOException e) {
            logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
            throw new MessagingException("FreeMarker模板不存在", e);
        } catch (TemplateException e) {
            logger.error("生成邮件内容失败, FreeMarker处理失败", e);
            throw new MessagingException("FreeMarker处理失败", e);
        }
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Configuration getFreemarkerConfiguration() {
        return freemarkerConfiguration;
    }

    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
