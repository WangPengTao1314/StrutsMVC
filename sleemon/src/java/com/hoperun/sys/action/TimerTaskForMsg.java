package com.hoperun.sys.action;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
// TODO: Auto-generated Javadoc

/**
 * The Class TimerTaskForMsg.
 * 
 * @module 消息定时器
 * @version 1.1
 * @author 朱晓文
 */
public class TimerTaskForMsg extends TimerTask  {
	
	/** The task map. */
	private static Map<String,Object> taskMap = new HashMap<String,Object>();
	
	/**
	 * Gets the task map.
	 * 
	 * @return the task map
	 */
	public static Map<String, Object> getTaskMap() {
		return taskMap;
	}


	/**
	 * Sets the task map.
	 * 
	 * @param taskMap the task map
	 */
	public static void setTaskMap(Map<String, Object> taskMap) {
		TimerTaskForMsg.taskMap = taskMap;
	}

	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		//System.out.println("start~~"); 
    }

}
