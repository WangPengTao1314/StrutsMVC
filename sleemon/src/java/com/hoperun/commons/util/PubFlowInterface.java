package com.hoperun.commons.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

// TODO: Auto-generated Javadoc
/**
 * The Class PubFlowInterface.
 */
public class PubFlowInterface implements FlowInterface
{
    
	//提交之前，如果需要执行后台操作，请在这里加代码
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.FlowInterface#beforeAffirm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
	 */
	public boolean beforeAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception
	{
		return true;
	}
	//提交之后，如果需要执行后台操作，请在这里加代码
   /* (non-Javadoc)
	 * @see com.hoperun.sys.service.FlowInterface#afterAffirm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
	 */
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception
	{
		return true;
	}

    //审核通过之后，如果需要执行后台操作，请在这里加代码  
	/* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowInterface#afterAuditing(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
     */
    public boolean afterAuditing(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception
	{
		//String state = (String) request.getAttribute("currentState");
		//String id = (String) request.getAttribute("id");
	   // if(state.equals("审核通过")){
		
		//}else if(state.equals("否决")){
			
	   // }
		return true;
	}
	
	 //提交之后，如果需要执行后台操作，请在这里加代码
    /**
 	 * After affirm.
 	 * 
 	 * @param request the request
 	 * @param session the session
 	 * 
 	 * @return true, if successful
 	 * 
 	 * @throws Exception the exception
 	 */
 	public boolean afterAffirm(HttpServletRequest request, HttpSession session) throws Exception {

        // TODO Auto-generated method stub
        return true;
    }
    //审核通过之后，如果需要执行后台操作，请在这里加代码
    /**
     * After auditing.
     * 
     * @param request the request
     * @param session the session
     * 
     * @return true, if successful
     * 
     * @throws Exception the exception
     */
    public boolean afterAuditing(HttpServletRequest request, HttpSession session) throws Exception {

        //String state = (String) request.getAttribute("currentState");
        //String id = (String) request.getAttribute("id");
       // if(state.equals("审核通过")){
        
        //}else if(state.equals("否决")){
            
       // }
        // TODO Auto-generated method stub
        return true;
    }
   
  //提交之前，如果需要执行后台操作，请在这里加代码
    /**
   * Before affirm.
   * 
   * @param request the request
   * @param session the session
   * 
   * @return true, if successful
   * 
   * @throws Exception the exception
   */
  public boolean beforeAffirm(HttpServletRequest request, HttpSession session) throws Exception {

        // TODO Auto-generated method stub
        return true;
    }
	
}
