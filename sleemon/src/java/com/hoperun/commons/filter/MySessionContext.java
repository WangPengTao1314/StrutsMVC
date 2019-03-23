package com.hoperun.commons.filter;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MySessionContext.
 */
public class MySessionContext {
	
	/** The mymap. */
	@SuppressWarnings("unchecked")
	private static HashMap mymap = new HashMap();

    /**
     * Adds the session.
     * 
     * @param session the session
     */
    @SuppressWarnings("unchecked")
	public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session); 
        }
    }

    /**
     * Del session.
     * 
     * @param session the session
     */
    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
        	HttpSession httpSession = (HttpSession)mymap.get(session.getId());
        	if(null != httpSession && null != (UserBean) httpSession.getAttribute("UserBean"))
        		httpSession.invalidate();
            mymap.remove(session.getId());
        }
    }

    /**
     * Gets the session.
     * 
     * @param session_id the session_id
     * 
     * @return the session
     */
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null) 
        	return null;
        return (HttpSession) mymap.get(session_id);
    }
}
