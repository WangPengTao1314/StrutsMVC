/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：MailModel.java
 */

package com.hoperun.commons.model;

import com.hoperun.commons.model.BaseModel;
import com.hoperun.commons.util.StringUtil;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

// TODO: Auto-generated Javadoc
/**
 * The Class MailModel.
 * 
 * @module 系统管理
 * @func 邮件Model
 * @version 1.1
 * @author 朱晓文
 */
public class MailModel extends BaseModel {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4943417535815136781L;
    
    /** 邮件服务器地址. */
    private String mailServerHost;
    
    /** 邮件服务器端口. */
    private String mailServerPort;
    
    /** 发送邮件地址. */
    private String fromAddress;    
    
    /** 收邮件地址. */
    private Address[] toAddress; 
    
    /** 发送邮件的用户名. */
    private String userName; 
    
    /** 发送邮件的密码. */
    private String password; 
    
    /** 是否身体验证. */
    private boolean  validate = false;
    
    /** 标题. */
    private String  subject; 
    
    /** 邮件内容. */
    private String  content;
    
    /** 邮件附件的文件名. */
    private String[] attachFileNames; 
    
    /** 邮件相关信息 - 邮件抄送地址. */ 
    private Address[] ccAddress = null; 
    
    /** 邮件相关信息 - 邮件密件抄送地址. */ 
    private Address[] bccAddress = null; 

    
    /**
     * 初始化邮件服务器.
     */    
    public MailModel(){    
    	this.mailServerHost=Consts.MAIL_SERVER_HOST; 
    	this.mailServerPort=Consts.MAIL_SERVER_PORT;
    	this.validate=Consts.MAIL_VALIDATE;
    	this.fromAddress=Consts.DEF_FROM_MAIL_AD;
    	this.userName=Consts.DEF_FROM_MAIL_AD;
    	this.password=Consts.DEF_MAIL_PASSWORD;
    }
    
    /**
     * 获得邮件会话属性.
     * 
     * @return the properties
     */    
    public Properties getProperties(){    
     Properties p = new Properties();    
     p.put("mail.smtp.host", this.mailServerHost);    
     p.put("mail.smtp.port", this.mailServerPort);    
     p.put("mail.smtp.auth", validate ? "true" : "false");    
     return p;    
    }
	
	/**
	 * Gets the mail server host.
	 * 
	 * @return the mail server host
	 */
	public String getMailServerHost() {
		return mailServerHost;
	}
	
	/**
	 * Sets the mail server host.
	 * 
	 * @param mailServerHost the new mail server host
	 */
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	
	/**
	 * Gets the mail server port.
	 * 
	 * @return the mail server port
	 */
	public String getMailServerPort() {
		return mailServerPort;
	}
	
	/**
	 * Sets the mail server port.
	 * 
	 * @param mailServerPort the new mail server port
	 */
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	
	/**
	 * Gets the from address.
	 * 
	 * @return the from address
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	
	/**
	 * Sets the from address.
	 * 
	 * @param fromAddress the new from address
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 * 
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 * 
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Checks if is validate.
	 * 
	 * @return true, if is validate
	 */
	public boolean isValidate() {
		return validate;
	}
	
	/**
	 * Sets the validate.
	 * 
	 * @param validate the new validate
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Sets the subject.
	 * 
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the content.
	 * 
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the to address.
	 * 
	 * @return the to address
	 */
	public Address[] getToAddress() {
		return toAddress;
	}

	/**
	 * Sets the to address.
	 * 
	 * @param strAddress the new to address
	 */
	public void setToAddress(String strAddress) {
			if(strAddress!=null)
			{
				String[] alAddress = StringUtil.toArr(strAddress,";"); 
		    	this.toAddress = new Address[alAddress.length]; 
		    	for (int i = 0; i < alAddress.length; i++)
		    	{
		    		try {
		    		    String temStr=alAddress[i];
		    		    if(temStr!=null&&!"".equals(temStr.trim()))
						this.toAddress[i] = new InternetAddress(temStr.trim());
					} catch (AddressException e) {
						e.printStackTrace();
					} 	
		    	}
			}
			
	}
    
    /**
     * Gets the attach file names.
     * 
     * @return the attach file names
     */
    public String[] getAttachFileNames() {
		return attachFileNames;
	}

	/**
	 * Sets the attach file names.
	 * 
	 * @param attachFileNames the new attach file names
	 */
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	/**
	 * Gets the cc address.
	 * 
	 * @return the cc address
	 */
	public Address[] getCcAddress() {
		return ccAddress;
	}

	/**
	 * Sets the cc address.
	 * 
	 * @param strAddress the new cc address
	 */
	public void setCcAddress(String strAddress) {
		if(strAddress!=null)
		{
			String[] alAddress = StringUtil.toArr(strAddress,";"); 
	    	this.ccAddress = new Address[alAddress.length]; 
	    	for (int i = 0; i < alAddress.length; i++)
	    	{
	    		try {
	    		    String temStr=alAddress[i];
	    		    if(temStr!=null&&!"".equals(temStr.trim()))
					this.ccAddress[i] = new InternetAddress(temStr.trim());
				} catch (AddressException e) {
					e.printStackTrace();
				} 	
	    	}
		}
	}

	/**
	 * Gets the bcc address.
	 * 
	 * @return the bcc address
	 */
	public Address[] getBccAddress() {
		return bccAddress;
	}

	/**
	 * Sets the bcc address.
	 * 
	 * @param strAddress the new bcc address
	 */
	public void setBccAddress(String strAddress) {
		if(strAddress!=null)
		{
			String[] alAddress = StringUtil.toArr(strAddress,";"); 
	    	this.bccAddress = new Address[alAddress.length]; 
	    	for (int i = 0; i < alAddress.length; i++)
	    	{
	    		try {
	    		    String temStr=alAddress[i];
	    		    if(temStr!=null&&!"".equals(temStr.trim()))
					this.bccAddress[i] = new InternetAddress(temStr.trim());
				} catch (AddressException e) {
					e.printStackTrace();
				} 	
	    	}
		}
	}
	
   
   

}
