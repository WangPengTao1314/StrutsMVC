/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXAction.java
 */
package com.hoperun.sample.newsingletable.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sample.newsingletable.model.NewSingleTableModel;
import com.hoperun.sample.newsingletable.service.NewSingleTableService;
import com.hoperun.sys.model.UserBean;
/**
 * The Class NewSingleTableAction.
 * 
 * @module 系统管理
 * @func 单表示例
 * @version 1.1
 * @author 朱晓文
 */
public class NewSingleTableAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(NewSingleTableAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010101";
    private static String PVG_EDIT="BS0010102";
    private static String PVG_DELETE="BS0010103";
    //启用,停用
    private static String PVG_START_STOP="BS0010104";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**审批 end**/
	private NewSingleTableService newsingleTableService;
    
	/**
	 * 机构信息框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
		return mapping.findForward("frames");
	}

	/**
	 *  查询结果列表
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "JGXXID", params);
		ParamUtil.putStr2Map(request, "JGMC", params);
		ParamUtil.putStr2Map(request, "JGJC", params);
		ParamUtil.putStr2Map(request, "JGBH", params);
		ParamUtil.putStr2Map(request, "SJJG", params);
		ParamUtil.putStr2Map(request, "JGZT", params);
        ParamUtil.putStr2Map(request, "ZTXXID", params);
		ParamUtil.putStr2Map(request, "XH", params);
		//只查询0的记录。1为删除。0为正常
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRETIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = newsingleTableService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 *  机构信息编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
         String jgxxId = ParamUtil.get(request, "JGXXID");
         if(!StringUtil.isEmpty(jgxxId))
         {
        	Map<String, String> entry = newsingleTableService.load(jgxxId);
 			request.setAttribute("rst", entry);
         }
		return mapping.findForward("toedit");
	}

	/**
	 *  机构信息编辑 新增/修改
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		logger.info("Enter edit()");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		NewSingleTableModel singleTableModel = new NewSingleTableModel();
		if (!StringUtil.isEmpty(jsonData)) {
			singleTableModel = new Gson().fromJson(jsonData,
					new TypeToken<NewSingleTableModel>() {
					}.getType());
		}
		String jgxxId = ParamUtil.get(request, "JGXXID");
		Map<String, String> params = new HashMap<String, String>();
		params.put("JGXXID", jgxxId);
		params.put("JGBH", singleTableModel.getJGBH());
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		try {
			jgxxId = newsingleTableService.txEdit(jgxxId, singleTableModel, userBean);
			jsonResult = jsonResult(true, jgxxId);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
     
	/**
	 *  查看机构详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String jgxxId = ParamUtil.get(request, "JGXXID");
		if(!StringUtil.isEmpty(jgxxId))
        {
			Map<String, String> entry = newsingleTableService.load(jgxxId);
			request.setAttribute("rst", entry);
        }
		return mapping.findForward("view");
	}

	/**
	 *  删除
	 * @param mapping the mapping
	 * @param formthe form
	 * @param requestthe request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jgxxId = ParamUtil.get(request, "JGXXID");
			Map <String, String> params = new HashMap <String, String>();
		    params.put("JGXXID", jgxxId);
		    //params.put("DATARECYCLEID", StringUtil.uuid32len());
		    //params.put("DELETOR", userBean.getYHM());
		    params.put("UPDTOR", userBean.getXTYHID());
		    params.put("UPDNAME", userBean.getYHM());
		    params.put("DELFLAG", BusinessConsts.DEL_FLAG_DROP);
			newsingleTableService.txDelete(params);
			newsingleTableService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 *  启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String JGXXID = request.getParameter("JGXXID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("JGXXID", JGXXID);
			params.put("UPDATER", userBean.getXTYHID());
			params.put("JGZT", BusinessConsts.JC_STATE_ENABLE);
			newsingleTableService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
        	e.printStackTrace();
			jsonResult = jsonResult(false, "状态修改失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
    /**
	 * 停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String JGXXID = request.getParameter("JGXXID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("JGXXID", JGXXID);
            params.put("UPDATER", userBean.getXTYHID());
            params.put("JGZT", BusinessConsts.JC_STATE_DISENABLE);
            newsingleTableService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	/**
	 * Gets the newsingle table service.
	 * 
	 * @return the newsingle table service
	 */
	public NewSingleTableService getNewsingleTableService() {
		return newsingleTableService;
	}

	/**
	 * Sets the newsingle table service.
	 * 
	 * @param newsingleTableServic the new newsingle table service
	 */
	public void setNewsingleTableService(
			NewSingleTableService newsingleTableService) {
		this.newsingleTableService = newsingleTableService;
	}
	 /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}
