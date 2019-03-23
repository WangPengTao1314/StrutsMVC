/**
 *@module 系统共通   
 *@func exl共通类 
 *@version 1.1
 *@author 朱晓文     
 */
package com.hoperun.commons.job.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.job.service.JobService;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class JobAction.
 */
public class JobAction extends BaseAction {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(JobAction.class);

	/** The job service. */
	private JobService jobService;

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param jobService the job service
	 * 
	 * @author administrator
	 */
	public void setjobService(JobService jobService) {
		this.jobService = jobService;
	}

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Enter method toFrame();");
		return mapping.findForward("toFrame");
	}

	// 通用插入任务信息方法
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @author administrator
	 */
	public void chgBillInsJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBeandc");
		try {
			String jsonParam = ParamUtil.get(request, "jsonParam");
			System.out.println("jsonParam=="+jsonParam);
			if (StringUtil.isEmpty(jsonParam)) {
				jsonResult = jsonResult(true, "加入处理队列失败！原因：您传入的参数为空!");
			} else {
				jobService.txSaveJob(jsonParam, userBean);
				jsonResult = jsonResult(true, "已经加入处理队列!");
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "加入处理队列失败！原因：" + e.getMessage());
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param request the request
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public  String getAppPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

}
