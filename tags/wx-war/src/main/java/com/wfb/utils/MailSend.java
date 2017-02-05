package com.wfb.utils;

import java.util.Properties;

//import javax.mail.Authenticator;
//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

//import com.jrzmq.core.utils.Base64Util;
import com.jrzmq.core.utils.PropertiesUtil;

public class MailSend {

	@Autowired
	protected PropertiesUtil propertiesUtil;
	
	public void sendMail(String email, String content, String title) throws MessagingException{
		String username ="wangj@digirun.cn";
		String password ="Junaim2";
//		String valiPassword = Base64Util.decodeBase64(password);
		String host = "smtp.qq.com";
		String protocal = "smtp";
		String to = email;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setRecipients(RecipientType.TO, to);
		message.setFrom(new InternetAddress(username));
		message.setSubject(title);
		message.setContent(content, "text/html;charset=UTF-8");
//		message.setText(content, "text/html;charset=UTF-8");
		Transport transport = session.getTransport(protocal);
		transport.connect(host, username, password);
		transport.sendMessage(message, message.getAllRecipients());
		
	}
	
	/**
	 * 

	public void sendMail(String email, String content, String title) throws MessagingException{
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
//          可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
//          mail.user / mail.from
        // 表示SMTP发送邮件，需要进行身份验证
		String username = propertiesUtil.getProperty("mail.user");
		String password = propertiesUtil.getProperty("mail.password");
		String valiPassword = Base64Util.decodeBase64(password);
		 
        props.put("mail.smtp.auth", propertiesUtil.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.host", propertiesUtil.getProperty("mail.smtp.host"));
        // 发件人的账号
        props.put("mail.user", username);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", valiPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                return new PasswordAuthentication(username, valiPassword);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(username);
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(email);
        message.setRecipient(RecipientType.TO, to);

        // 设置抄送
//        InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
//        message.setRecipient(RecipientType.CC, cc);

        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
	}
		 */
}
