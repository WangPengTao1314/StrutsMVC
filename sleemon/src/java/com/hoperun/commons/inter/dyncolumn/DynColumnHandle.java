package com.hoperun.commons.inter.dyncolumn;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.inter.dyncolumn.model.DynColumnModel;
import com.hoperun.commons.inter.dyncolumn.service.DynColumnService;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class DynColumnHandle.
 * 
 * @module 共通模块
 * @func 动态列
 * @version 1.1
 * @author zhuxw
 */
public class DynColumnHandle extends BaseAction {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(DynColumnHandle.class);

	/** The dyn column service. */
	private DynColumnService dynColumnService;
	
	/** The instance. */
	private static DynColumnHandle instance;

	/**
	 * Instantiates a new dyn column handle.
	 */
	private DynColumnHandle() {
		super();
	}

	/**
	 * Sets the dyn column service.
	 * 
	 * @param dynColumnService the new dyn column service
	 */
	public void setDynColumnService(DynColumnService dynColumnService) {
		this.dynColumnService = dynColumnService;
	}

	/**
	 * 获取DynColumnHandle实例
	 * Description :.
	 * 
	 * @return the instance
	 */
	public static DynColumnHandle getInstance() {
		if (null == instance) {
			instance = (DynColumnHandle) SpringContextUtil
					.getBean("dynColumnHandle");
		}
		return instance;
	}

	/**
	 * 设置用户配置信息
	 * Description :.
	 * 
	 * @param request the request
	 * @param confNo the conf no
	 * @param isEdit the is edit
	 */
	public void pageCustomized(HttpServletRequest request,String confNo,String isEdit){
		List<DynColumnModel> list = getDynColumn(request, confNo,isEdit);
		if(null != list && !list.isEmpty()){
			request.setAttribute("Customized", list);
		}
	}
	
	/**
	 * 跳转用户动态配置页面
	 * Description :.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
    public ActionForward toSetPageColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	String confNo = request.getParameter("tbNo");
    	//只查询可编辑的字段
    	pageCustomized(request, confNo,"1");
    	request.setAttribute("tbNo", confNo);
        return mapping.findForward("setPageColumn");
    }
    
    /**
     * 保存用户配置信息
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		
    	try {
			String jsonData = request.getParameter("jsonData");
			String confNo = request.getParameter("tbNo");
			List<DynColumnModel> custList=null;
			if(StringUtils.isNotEmpty(jsonData)){
				custList = new Gson().fromJson(jsonData, new TypeToken<List<DynColumnModel>>(){}.getType());
			}
			UserBean userBean =(UserBean)request.getSession(false).getAttribute("UserBean");
			String xtyhId = userBean.getXTYHID();
			dynColumnService.txSaveCustomizedPage(custList,xtyhId,confNo);
		} catch (Exception e) {
			logger.error(e);
			jsonResult = jsonResult(false,"处理失败");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return null;
    }
    
    /**
     * 获取动态配置信息
     * Description :.
     * 
     * @param request the request
     * @param confNo the conf no
     * @param isEdit the is edit
     * 
     * @return the dyn column
     */
    private List<DynColumnModel> getDynColumn(HttpServletRequest request,String confNo,String isEdit){
    	UserBean userBean =(UserBean)request.getSession(false).getAttribute("UserBean");
		if(null == userBean){
			logger.error("用户已失效");
			return null;
		}
		String xtyhId = userBean.getXTYHID();
		return dynColumnService.getDynColumn(xtyhId,confNo,isEdit);
    }

}
