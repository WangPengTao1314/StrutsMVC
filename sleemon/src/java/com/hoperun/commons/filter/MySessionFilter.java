package com.hoperun.commons.filter;
  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class MySessionFilter.
 */
public class MySessionFilter implements HttpSessionListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		MySessionContext.addSession(httpSessionEvent.getSession());
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        MySessionContext.delSession(session);
    }
}
