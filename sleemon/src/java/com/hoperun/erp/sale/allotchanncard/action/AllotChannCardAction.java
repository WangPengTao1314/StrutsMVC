package com.hoperun.erp.sale.allotchanncard.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.allotchanncard.service.AllotChannCardService;
import com.hoperun.sys.model.UserBean;

public class AllotChannCardAction extends BaseAction {

	/** 日志 **/
	private Logger logger = Logger.getLogger(AllotChannCardAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0022301";
	private static String PVG_EDIT = "BS0022302";
	private static String PVG_DELETE = "BS0022303";
	//转卡
	private static String PVG_TRANSFER = "BS0022304";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";

	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";

	private AllotChannCardService allotChannCardService;
	
	
	
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&& !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl",paramUrl );
	 
		return mapping.findForward("frames");
	}
	
	
	public ActionForward toMainPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("pvg",setPvg(userBean));

        return mapping.findForward("toMainPage");
	}

	/**
	 * *list页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "MARKETING_ACT_ID", params);
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NO", params);
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NAME", params);
	    ParamUtil.putStr2Map(request, "START_DATE", params);
	    ParamUtil.putStr2Map(request, "END_DATE", params);
	    ParamUtil.putStr2Map(request, "SPONSOR_NAME", params);
	    ParamUtil.putStr2Map(request, "MARKETING_CARD_NO", params);
//	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CARD_SEQ_NO_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CARD_SEQ_NO_END", params); 
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		boolean firstFlag = ParamUtil.getBoolean(request, "firstFlag");
		
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		String CHANN_ID = ParamUtil.get(request,"CHANN_ID"); 
		String notallot = ParamUtil.get(request,"notallot");//
		params.put("STATE", "启用");
		 
		if(BusinessConsts.INTEGER_0.equals(notallot)){//未分配
			sb.append(" and u.CHANN_ID is null");
		}
		if(BusinessConsts.INTEGER_1.equals(notallot)){//已分配
			sb.append("  and u.CHANN_ID='"+CHANN_ID+"' ");
		}
		
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.MARKETING_CARD_NO", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		String pageSize = ParamUtil.get(request, "pageSize");
	    if(StringUtil.isEmpty(pageSize)){
		   pageSize = "100";
	    }
		params.put("pageSize", pageSize);
		if(!firstFlag){
			IListPage page = allotChannCardService.pageQuery(params, pageNo);
			request.setAttribute("page", page);
		}
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("list");
	}
	
	
	
    
    /**
	 * * 主表 新增/修改数据
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
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String MARKETING_CARD_IDS = ParamUtil.get(request,"MARKETING_CARD_IDS");
			String CHANN_ID = ParamUtil.get(request,"CHANN_ID");
			String CHANN_NO = ParamUtil.get(request,"CHANN_NO");
			String CHANN_NAME = ParamUtil.get(request,"CHANN_NAME");
			if(!StringUtil.isEmpty(MARKETING_CARD_IDS)){
				Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("MARKETING_CARD_IDS", MARKETING_CARD_IDS);
				paramMap.put("CHANN_ID", CHANN_ID);
				paramMap.put("CHANN_NO", CHANN_NO);
				paramMap.put("CHANN_NAME", CHANN_NAME);
				paramMap.put("ASSIGNER_NAME",userBean.getXM());
				paramMap.put("ASSIGNER_ID",userBean.getXTYHID());
				allotChannCardService.txUpdateCard(paramMap);
				jsonResult = jsonResult(true, "分配成功");
			}
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "分配失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	/**
	 * 已分配未销售的卡券list页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toNoSaleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_ID", params);
	    ParamUtil.putStr2Map(request, "CHANN_ID", params);
//	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NAME", params);
	    ParamUtil.putStr2Map(request, "START_DATE", params);
	    ParamUtil.putStr2Map(request, "END_DATE", params);
	    ParamUtil.putStr2Map(request, "SPONSOR_NAME", params);
	    ParamUtil.putStr2Map(request, "MARKETING_CARD_NO", params);
//	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CARD_SEQ_NO_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CARD_SEQ_NO_END", params); 
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("STATE", "启用");
	 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.MARKETING_CARD_NO", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		String pageSize = ParamUtil.get(request, "pageSize");
	    if(StringUtil.isEmpty(pageSize)){
		   pageSize = "100";
	    }
		params.put("pageSize", pageSize);
	 
		IListPage page = allotChannCardService.pageNoSaleQuery(params, pageNo);
		request.setAttribute("page", page);
		 
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("list");
	}
	
	
	
	/**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_TRANSFER", QXUtil.checkPvg(userBean, PVG_TRANSFER));
		
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}


	public AllotChannCardService getAllotChannCardService() {
		return allotChannCardService;
	}


	public void setAllotChannCardService(AllotChannCardService allotChannCardService) {
		this.allotChannCardService = allotChannCardService;
	}
	
	
	




	 
	
	

}
