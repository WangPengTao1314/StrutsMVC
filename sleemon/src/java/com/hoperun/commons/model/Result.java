/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Result.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class Result implements IResult {
	
	/** The total count. */
	private int totalCount=0;
	
	/** The success. */
	private boolean success;
	
	/** The messages. */
	private String messages;
	
	/** The data. */
	private Object data;
	
	/** The current. */
	private int current;
	
	/** The last. */
	private int last;
	
	/**
	 * Instantiates a new result.
	 * 
	 * @param data the data
	 */
	public Result(Object data) {
		super();
		this.success = true;
		this.data = data;
		
	}
	
	/**
	 * Instantiates a new result.
	 * 
	 * @param totalCount the total count
	 * @param data the data
	 * @param current the current
	 * @param last the last
	 */
	public Result(int totalCount,Object data, int current, int last) {
		super();
		this.success = true;
		this.totalCount = totalCount;
		this.data = data;
		this.current = current;
		this.last = last;
	}
	
	/**
	 * Instantiates a new result.
	 * 
	 * @param success the success
	 * @param data the data
	 */
	public Result(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}

	/**
	 * Instantiates a new result.
	 * 
	 * @param success the success
	 * @param data the data
	 * @param messages the messages
	 */
	public Result(boolean success,Object data,String messages) {
		super();
		this.messages = messages;
		this.success = success;
		this.data = data;
	}
	
	/**
	 * Instantiates a new result.
	 * 
	 * @param success the success
	 * @param data the data
	 * @param messages the messages
	 */
	public Result(boolean success,Object data,String... messages) {
		super();
		this.success = success;
		this.data = data;
		if(messages!=null)
			for(String message : messages)
				addMessage(message);
	}
	
	/**
	 * Adds the message.
	 * 
	 * @param message the message
	 */
	public void addMessage(String message){
		if(message==null||message.trim().length()<=0)
			return;
		if(this.messages == null)
			this.messages = "";
		this.messages+=message+"<BR>";
	}
	/* (non-Javadoc)
	 * @see net.omw.framework.core.IResult#getData()
	 */
	public Object getData() {
		return this.data;
	}

	

	/* (non-Javadoc)
	 * @see com.hoperun.commons.model.IResult#getMessages()
	 */
	public String getMessages() {
		return messages;
	}

	/* (non-Javadoc)
	 * @see net.omw.framework.core.IResult#isSuccess()
	 */
	public boolean isSuccess() {
		return this.success;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.model.IResult#getTotalCount()
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Sets the total count.
	 * 
	 * @param totalCount the new total count
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Gets the current.
	 * 
	 * @return the current
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * Sets the current.
	 * 
	 * @param current the new current
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * Gets the last.
	 * 
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 * Sets the last.
	 * 
	 * @param last the new last
	 */
	public void setLast(int last) {
		this.last = last;
	}

}
