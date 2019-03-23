package com.hoperun.drp.base.promote.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.promote.model.PromoteModel;
import com.hoperun.drp.base.promote.service.PromoteService;
import com.hoperun.sys.model.UserBean;

/***
 * 活动
 * 
 * @author zhang_zhongbin
 * 
 */
public class PromoteAction extends BaseAction {

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0010601";
	private static String PVG_EDIT = "FX0010602";
	private static String PVG_DELETE = "FX0010603";
	// 启用,停用
	private static String PVG_START_STOP = "FX0010604";

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

	private PromoteService promoteService;
	
	
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
        if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl",paramUrl );
		return mapping.findForward("frames");
	}
	
	
	
	
	/**
	 * 查询
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "PROMOTE_NO", params);
	    ParamUtil.putStr2Map(request, "PROMOTE_NAME", params);
	    ParamUtil.putStr2Map(request, "BEG_DATE", params);
	    ParamUtil.putStr2Map(request, "END_DATE", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
        String module = ParamUtil.get(request,"module");
 
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,null,userBean));
		String ZTXXID = userBean.getLoginZTXXID(); 
		sb.append(" and  LEDGER_ID='"+ZTXXID+"' ");
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME DESC,u.PROMOTE_NO ", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = promoteService.pageQuery(params, pageNo);
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	
	/**
	 * 到编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	 String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
        if (StringUtils.isNotEmpty(PROMOTE_ID)) {
            Map<String,String> entry = promoteService.load(PROMOTE_ID);
            request.setAttribute("rst", entry);
        }
        String ZTXXID = userBean.getLoginZTXXID(); 
        request.setAttribute("ZTXXID", ZTXXID);
        String CHANN_NO = userBean.getCHANN_NO();
        request.setAttribute("CHANN_NO", CHANN_NO);
        return mapping.findForward("toedit");
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
            String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
            String jsonData = ParamUtil.get(request, "jsonData");
            PromoteModel model = new Gson().
            fromJson(jsonData, new TypeToken <PromoteModel>() {}.getType());
          
            promoteService.txEdit(PROMOTE_ID,model,userBean);
            jsonResult = jsonResult(true, "保存成功");
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	/**
	 * * to 详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
		if(!StringUtil.isEmpty(PROMOTE_ID)){
			Map<String,String> entry = promoteService.load(PROMOTE_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	
	/**
     * 删除
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void delete(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	 String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
        	 promoteService.txDelete(PROMOTE_ID, userBean);
        	 jsonResult = jsonResult(true, "删除成功");
        } catch (Exception ex) {
            jsonResult = jsonResult(false, "删除失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 按钮修改状态为启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateQy(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            
            String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PROMOTE_ID", PROMOTE_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", DateUtil.now());
            params.put("STATE",  BusinessConsts.JC_STATE_ENABLE);
            promoteService.txStartById(params);
            jsonResult = jsonResult(true, "状态修改成功");
           
        } catch (Exception e) {
            jsonResult = jsonResult(false, "启用失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    

    /**
     * 按钮修改状态为停用
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateTy(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	 String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
             Map <String, String> params = new HashMap <String, String>();
             params.put("PROMOTE_ID", PROMOTE_ID);
             params.put("UPDATOR", userBean.getXTYHID());
             params.put("UPD_NAME", userBean.getXM());
             params.put("UPD_TIME", DateUtil.now());
             params.put("STATE",  BusinessConsts.JC_STATE_DISENABLE);
             promoteService.txStopById(params);
             jsonResult = jsonResult(true, "状态修改成功");
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
	 * * 主表 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void justRepeat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String PROMOTE_ID = ParamUtil.get(request, "PROMOTE_ID");
            String PROMOTE_NO = ParamUtil.get(request, "PROMOTE_NO");
            Map<String,String> params = new HashMap<String,String>();
            params.put("PROMOTE_ID", PROMOTE_ID);
            params.put("PROMOTE_NO", PROMOTE_NO);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            boolean b = promoteService.queryForInt(params);
            if(!b){
            	jsonResult = jsonResult(true, "重复");
            }
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		 
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	public PromoteService getPromoteService() {
		return promoteService;
	}

	public void setPromoteService(PromoteService promoteService) {
		this.promoteService = promoteService;
	}
	
	
	

}
