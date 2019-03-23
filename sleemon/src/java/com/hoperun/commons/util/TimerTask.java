package com.hoperun.commons.util;

import com.hoperun.commons.util.MessTimerTask;
import java.util.Timer;

import javax.servlet.http.HttpServlet;
// TODO: Auto-generated Javadoc

/**
 * The Class TimerTask.
 */
public class TimerTask extends HttpServlet {
	
	/**
	 * Instantiates a new timer task.
	 */
	public TimerTask(){
		Timer timer = new Timer();
		timer.schedule(MessTimerTask.getInstance(), 1000,120000);
	}
}
