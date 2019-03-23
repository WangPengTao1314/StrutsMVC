/**@describe 共通WebService接口
 * @author zhuxw
 * @date  2012-7-2
 */
package com.hoperun.commons.webservice;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;
// TODO: Auto-generated Javadoc

import com.hoperun.commons.model.Consts;

/**
 * The Class CommWebServiceStart.
 */
public class CommWebServiceStart extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8093479513345347389L;
	
	/**
	 * Instantiates a new comm web service start.
	 */
	public  CommWebServiceStart()
	{
		try {
			Endpoint.publish(Consts.DM_WSDL, new CommWebService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
