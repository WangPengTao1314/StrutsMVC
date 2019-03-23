/**
 * 项目名称：平台
 * 系统名：共通功能
 * 文件名：sendTextMail.java
 */
package com.hoperun.commons.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.hoperun.commons.model.MailModel;
// TODO: Auto-generated Javadoc

/**
 * The Class MailUtil.
 * 
 * @module 系统管理
 * @func 发送邮件
 * @version 1.1
 * @author 朱晓文
 */
public class MailUtil {
	
	/**
	 * 以文本格式发送邮件
	 * 不支持附件.
	 * 
	 * @param mailInfo the mail info
	 * 
	 * @return true, if send text mail
	 */
	public  boolean sendTextMail(MailModel mailInfo) {
		// 判断是否需要身份认证
		Authenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			//如果需要身份认证，则创建一个密码验证器
			authenticator = new  MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword()); 
			if(authenticator==null)
			{
				return false;
			}
        }
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			mailMessage.addRecipients(Message.RecipientType.TO, mailInfo.getToAddress());
			mailMessage.addRecipients(Message.RecipientType.CC, mailInfo.getCcAddress());
			mailMessage.addRecipients(Message.RecipientType.BCC, mailInfo.getBccAddress());
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(StringUtil.nullToSring(mailContent));
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 支持附件.
	 * 
	 * @param mailInfo 待发送的邮件信息
	 * 
	 * @return true, if send html mail
	 */
	public  boolean sendHtmlMail(MailModel mailInfo) {
		// 判断是否需要身份认证
		Authenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new  MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
			if(authenticator==null)
			{
				return false;
			}
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			//Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.addRecipients(Message.RecipientType.TO, mailInfo.getToAddress());
			mailMessage.addRecipients(Message.RecipientType.CC, mailInfo.getCcAddress());
			mailMessage.addRecipients(Message.RecipientType.BCC, mailInfo.getBccAddress());
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			//MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(StringUtil.nullToSring(mailInfo.getContent()) , "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			//设置附件
			String []attachFileNames=mailInfo.getAttachFileNames();
			if(attachFileNames!=null)
			{
				for(String attachFileName:attachFileNames)
				{
					File tempFile = new File(attachFileName); 
					if (!tempFile.exists() || tempFile.isDirectory()) { 
					   continue;
					} 
					String strFileName = tempFile.getName(); 
					BodyPart attachFilePart = new MimeBodyPart();
					attachFilePart.setDataHandler(new DataHandler(new FileDataSource(attachFileName))); 
					try {
						attachFilePart.setFileName(MimeUtility.encodeText(strFileName));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} 
					mainPart.addBodyPart(attachFilePart);
				}	
			}
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			//发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 以HTML格式发送邮件.
	 */
	public  void sendMailSamp(){
	 MailModel mailInfo = new MailModel(); 
     mailInfo.setToAddress("zhu_xiaowen@hoperun.com");
     mailInfo.setSubject("测试邮件");
     mailInfo.setContent("测试信息类容：http://www.hoperun.com");
     String[]attachFileNames={"E:/aaa.csv","E:/CGCS.ctl"};
     mailInfo.setAttachFileNames(attachFileNames);
     //不支持附件 ,带链接
     sendTextMail(mailInfo);//发送文体格式 
     //支持附件，不带链接
     sendHtmlMail(mailInfo);//发送html格式   
   }  
}
