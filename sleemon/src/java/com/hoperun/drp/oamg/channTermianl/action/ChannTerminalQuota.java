package com.hoperun.drp.oamg.channTermianl.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.drp.oamg.channTermianl.model.ChannTerminalQuotaModel;
import com.hoperun.drp.oamg.channTermianl.service.ChannTerminalQuotaService;
import com.hoperun.sys.model.UserBean;

public class ChannTerminalQuota  extends BaseAction {

	private   ChannTerminalQuotaService     channTerminalQuotaService;
	
	//增删改查
    private static String PVG_BWS="BS0033801";
    private static String PVG_EDIT="BS0033802";
    private static String PVG_DELETE="BS0033803";

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
    private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	 
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		 
    	//设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}
    /**
     * 装修补贴标准维护列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

         //权限级别
         UserBean userBean = ParamUtil.getUserBean(request);
    	 
         Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "TERMINAL_QUOTA_NO", params);
         ParamUtil.putStr2Map(request, "WAREA_NAME", params);
         ParamUtil.putStr2Map(request, "YEAR", params);
         ParamUtil.putStr2Map(request, "MONTH", params);
         ParamUtil.putStr2Map(request, "STATE", params);
         params.put("LEDGER_ID", userBean.getLoginZTXXID().toString());
         
         String search = ParamUtil.get(request,"search");
 	     String module = ParamUtil.get(request,"module");
 	     String STATE = ParamUtil.get(request,"STATE");
         StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
         String YEAR = ParamUtil.get(request, "YEAR");
         if(YEAR.equals("")){
        	 Date  date   = new Date();
             String year  = String.valueOf(date.getYear()+1900);
             params.put("YEAR", year);
         } else {
        	 params.put("YEAR", YEAR);
         }
		 // 权限级别和审批流的封装
		 params.put("QXJBCON", qx.toString());
		 if ("sh".equals(module)) {
			String channChrg = userBean.getCHANNS_CHARG();
			params.put("channChrg", channChrg);
		 }
		 
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 ParamUtil.putStr2Map(request, "pageSize", params);
		 
		 IListPage page = null;
		 page = channTerminalQuotaService.pageQuery(params, pageNo);
		 request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		 request.setAttribute("params", params);
		 request.setAttribute("module", module);
		 request.setAttribute("page", page);
		 request.setAttribute("pvg", setPvg(userBean));
		 
		    
         List list  = new  ArrayList();
         for(int j=2010;j<2020;j++){
       	  list.add(j);
         }
         request.setAttribute("list", list);
         Date  date   = new Date();
         String year  = String.valueOf(date.getYear()+1900);
         request.setAttribute("year", year);
         
         
		 return mapping.findForward("list");
    }
    
    
    public ActionForward toListT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        //权限级别
        UserBean userBean = ParamUtil.getUserBean(request);
   	 
        Map <String, String> params = new TreeMap <String, String>();
        ParamUtil.putStr2Map(request, "TERMINAL_QUOTA_NO", params);
        ParamUtil.putStr2Map(request, "WAREA_NAME", params);
        ParamUtil.putStr2Map(request, "YEAR", params);
        ParamUtil.putStr2Map(request, "MONTH", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        params.put("LEDGER_ID", userBean.getLoginZTXXID().toString());
        
        String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
        StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
        String YEAR = ParamUtil.get(request, "YEAR");
        if(YEAR.equals("")){
       	 Date  date   = new Date();
            String year  = String.valueOf(date.getYear()+1900);
            params.put("YEAR", year);
            request.setAttribute("year", year);
        } else {
       	    params.put("YEAR", YEAR);
       	    request.setAttribute("year", YEAR);
        }
        
		 // 权限级别和审批流的封装
		 params.put("QXJBCON", qx.toString());
		 if ("sh".equals(module)) {
			String channChrg = userBean.getCHANNS_CHARG();
			params.put("channChrg", channChrg);
		 }
		 
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 ParamUtil.putStr2Map(request, "pageSize", params);
		 
		 IListPage page = null;
		 page = channTerminalQuotaService.pageQuery(params, pageNo);
		 request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		 request.setAttribute("params", params);
		 request.setAttribute("module", module);
		 request.setAttribute("page", page);
		 request.setAttribute("pvg", setPvg(userBean));
		 
		    
        List list  = new  ArrayList();
        for(int j=2010;j<2020;j++){
      	  list.add(j);
        }
        request.setAttribute("list", list);
		return mapping.findForward("list");
   }
    /**
     * 加盟商门店详细信息 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	String TERMINAL_QUOTA_ID = ParamUtil.get(request, "TERMINAL_QUOTA_ID");
		if (StringUtils.isNotEmpty(TERMINAL_QUOTA_ID)) {
			Map entry = channTerminalQuotaService.loadByConfIdT(TERMINAL_QUOTA_ID);
			request.setAttribute("rst", entry);
		}
        return mapping.findForward("todetail");
    }
    
    /**
	 * 修改.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData       = ParamUtil.get(request, "jsonData");
        String TERMINAL_QUOTA_ID = ParamUtil.get(request, "TERMINAL_QUOTA_ID");
        ChannTerminalQuotaModel model = new ChannTerminalQuotaModel();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <ChannTerminalQuotaModel>() {
            }.getType());
        }
        channTerminalQuotaService.txEdit(TERMINAL_QUOTA_ID, model, userBean); 
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 新增.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
	    String jsonResult = jsonResult();
	    
	    String currentTime = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
	    String userName  = userBean.getXM();                        //申请人
	    String TERMINAL_QUOTA_ID    = ParamUtil.get(request, "TERMINAL_QUOTA_ID");
	    if (StringUtils.isNotEmpty(TERMINAL_QUOTA_ID)) {
			Map entry = channTerminalQuotaService.loadByConfIdT(TERMINAL_QUOTA_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("TERMINAL_QUOTA_ID", TERMINAL_QUOTA_ID);
		}
	    Map<String,String> params = new HashMap<String,String>();
	    String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String time = sdf.format(new Date());
	    request.setAttribute("currentTime",currentTime);
	    request.setAttribute("userName",userName);
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	    request.setAttribute("date", time);

		return mapping.findForward("toedit");
	}
	
	 /**
     * 删除.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String TERMINAL_QUOTA_ID = ParamUtil.get(request, "TERMINAL_QUOTA_ID");

        if (StringUtils.isNotEmpty(TERMINAL_QUOTA_ID)) {
            try {
            	channTerminalQuotaService.txDelete(TERMINAL_QUOTA_ID, userBean);
            	channTerminalQuotaService.clearCaches(TERMINAL_QUOTA_ID);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
	public ChannTerminalQuotaService getChannTerminalQuotaService() {
		return channTerminalQuotaService;
	}
	public void setChannTerminalQuotaService(
			ChannTerminalQuotaService channTerminalQuotaService) {
		this.channTerminalQuotaService = channTerminalQuotaService;
	}
}
