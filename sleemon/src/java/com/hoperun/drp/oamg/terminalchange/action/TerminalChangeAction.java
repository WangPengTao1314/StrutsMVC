package com.hoperun.drp.oamg.terminalchange.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.terminalchange.model.TerminalChangeModel;
import com.hoperun.drp.oamg.terminalchange.service.TerminalChangeService;
import com.hoperun.sys.model.UserBean;

public class TerminalChangeAction extends BaseAction {

	
	//增删改查
    private static String PVG_BWS="BS0032301";
    private static String PVG_EDIT="BS0032302";
    private static String PVG_DELETE="BS0032303";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032304";
    //流程跟踪
    private static String PVG_TRACE= "BS0032305";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0032401";
    private static String PVG_AUDIT_AUDIT="BS0032402";
    private static String PVG_TRACE_AUDIT="BS0032403";
	
	//审批流参数
    private static String AUD_TAB_NAME="BASE_TERMINAL_CHANGE";
    private static String AUD_TAB_KEY="TERMINAL_CHANGE_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="BASE_TERMINAL_CHANGE_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private   TerminalChangeService  terminalchangeService;
	
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
	    String module = ParamUtil.get(request,"module");
		if(module.equals("sh")){
			request.setAttribute("module",module);
			return mapping.findForward("framesT");
		}
		return mapping.findForward("frames");
	}
	
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		  //权限级别
        UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
        Map <String, String> params = new TreeMap <String, String>();
        ParamUtil.putStr2Map(request, "TERM_NO", params);
        ParamUtil.putStr2Map(request, "TERM_NAME", params);
        ParamUtil.putStr2Map(request, "TERM_TYPE", params);
        ParamUtil.putStr2Map(request, "TERM_LVL",  params);
        ParamUtil.putStr2Map(request, "CHANN_NO_P", params);
        ParamUtil.putStr2Map(request, "CHANN_NAME_P", params);
        ParamUtil.putStr2Map(request, "AREA_NO", params);
        ParamUtil.putStr2Map(request, "AREA_NAME", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_FROM", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_TO", params);
        ParamUtil.putStr2Map(request, "pageSize", params);
        
        String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

	     //权限级别和审批流的封装
		 if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
			   qx.append(" and STATE in('未提交','撤销','否决') ");
			}
		 }
		 if(module.equals("sh")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}else{
					qx.append(" and STATE in('提交','撤销','否决','审核通过') ");
				}
			}else{
				qx.append(" and STATE='提交' ");
			}
		 }
	     params.put("QXJBCON",qx.toString());
	     IListPage page = null;
	     request.setAttribute("module",module);
	     //渠道分管
	     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	     params.put("CHANNS_CHARG", CHANNS_CHARG);
			
	     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 page =  terminalchangeService.pageQuery(params, pageNo);
		 request.setAttribute("pvg",setPvg(userBean));
		 request.setAttribute("module", module);
         request.setAttribute("params", params);
         request.setAttribute("page", page); 
         request.setAttribute("CHANN_ID", CHANNS_CHARG);
         return mapping.findForward("list");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean =  ParamUtil.getUserBean(request);
			if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
		    		throw new ServiceException("对不起，您没有权限 !");
		    }
			String TERMINAL_CHANGE_ID = ParamUtil.get(request, "TERMINAL_CHANGE_ID");
			if (StringUtils.isNotEmpty(TERMINAL_CHANGE_ID)) {
				Map entry = terminalchangeService.load(TERMINAL_CHANGE_ID);
				request.setAttribute("rst", entry);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(TERMINAL_CHANGE_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
			return mapping.findForward("detail");
	}
	
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */	
	public ActionForward toShowHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String TERM_NO = ParamUtil.get(request, "TERM_NO");
		if (StringUtils.isNotEmpty(TERM_NO)) {
			List<TerminalChangeModel> list = terminalchangeService.loadByTerm(TERM_NO);
			request.setAttribute("rst", list.get(0));
		}
		return mapping.findForward("toshow");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
	    String jsonResult = jsonResult();
	    String userName  = userBean.getXM();                        //申请人
	    String TERMINAL_CHANGE_ID   = ParamUtil.get(request, "TERMINAL_CHANGE_ID");
	    if (StringUtils.isNotEmpty(TERMINAL_CHANGE_ID)) {
			Map entry = terminalchangeService.loadT(TERMINAL_CHANGE_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("TERMINAL_CHANGE_ID", TERMINAL_CHANGE_ID);
		}
	    Map<String,String> params = new HashMap<String,String>();
	    String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String time = sdf.format(new Date());
	    request.setAttribute("currentTime",time);
	    request.setAttribute("userName",userName);
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	    request.setAttribute("date", time);
	    request.setAttribute("state", "未提交");

		return mapping.findForward("toedit");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
    public void toAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String TERMINAL_CHANGE_ID    = ParamUtil.get(request, "TERMINAL_CHANGE_ID");
		 int count =  terminalchangeService.queryTerminalState(TERMINAL_CHANGE_ID);
		 if(count !=0) {
			 terminalchangeService.updateTerminal(TERMINAL_CHANGE_ID,userBean);
		 }
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String TERMINAL_CHANGE_ID = ParamUtil.get(request, "TERMINAL_CHANGE_ID");
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        
        TerminalChangeModel model = new TerminalChangeModel();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <TerminalChangeModel>() {
            }.getType());
            //渠道到选择的终端编号
            String  TERM_NO = model.getTERM_NO().toString();
            TerminalModel terminal = terminalchangeService.queryTermHistory(TERM_NO);
            if(terminal !=null && TERMINAL_CHANGE_ID.isEmpty()){
            	 terminalchangeService.txEditTerm(terminal, userBean); 
            }
        }
        terminalchangeService.txEdit(TERMINAL_CHANGE_ID, model, userBean); 
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}

  /**
    * 删除.
    * @param mapping the mapping
    * @param form the form
    * @param request the request
    * @param response the response
    */
   public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
   	
	   UserBean userBean = ParamUtil.getUserBean(request);
	   if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
	   }
       PrintWriter writer = getWriter(response);
       String jsonResult = jsonResult();
       String TERMINAL_CHANGE_ID = ParamUtil.get(request, "TERMINAL_CHANGE_ID");

       if (StringUtils.isNotEmpty(TERMINAL_CHANGE_ID)) {
           try {
           	terminalchangeService.txDelete(TERMINAL_CHANGE_ID, userBean);
           	terminalchangeService.clearCaches(TERMINAL_CHANGE_ID);
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
	 * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
     
	 /**
	  * @return
	  */
	 public TerminalChangeService getTerminalchangeService() {
		return terminalchangeService;
     }
	 /**
	  * @param terminalchangeService
	  */
	 public void setTerminalchangeService(TerminalChangeService terminalchangeService) {
		this.terminalchangeService = terminalchangeService;
	 }
}
