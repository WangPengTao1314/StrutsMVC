/**
 * 项目名称：平台
 * 系统名：共通功能
 * 文件名：MyAuthenticator.java
 */
package com.hoperun.commons.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
// TODO: Auto-generated Javadoc

/**
 * The Class MyAuthenticator.
 * 
 * @module 系统管理
 * @func 邮件身份认证
 * @version 1.1
 * @author 朱晓文
 */
public class MyAuthenticator extends Authenticator {
	
	/** The user name. */
	String userName=null;   
    
    /** The password. */
    String password=null;   
        
    /**
     * Instantiates a new my authenticator.
     */
    public MyAuthenticator(){   
    }   
    
    /**
     * Instantiates a new my authenticator.
     * 
     * @param username the username
     * @param password the password
     */
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    
    /* (non-Javadoc)
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   

}
