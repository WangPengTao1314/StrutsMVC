package com.hoperun.commons.model.inter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**  
 * @func
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-1 上午09:33:26 
 */
@XmlRootElement
public class ReturnInterInfo {
	/**
	 * 返回类型，S为成功，E为失败
	 */
	private String MessageType;
	/**
	 * 返回字符串
	 */
	private String MessageText;
	
	@XmlElement(name="MessageType")
	public String getMessageType() {
		return MessageType;
	}
	public void setMessageType(String messageType) {
		MessageType = messageType;
	}
	@XmlElement(name="MessageText")
	public String getMessageText() {
		return MessageText;
	}
	public void setMessageText(String messageText) {
		MessageText = messageText;
	}
	
}
