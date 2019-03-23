/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseAction
*/
package com.hoperun.erp.sale.advisehd.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModel;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelChld;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.erp.sale.advisehd.service.AdvisehdService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func总部-投诉与建议处理
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdvisehdAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(AdvisehdAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0020401";
    private static String PVG_EDIT="BS0020402";
    private static String PVG_DELETE="";
    //指派处理人
    private static String PVG_DEAL="BS0020403";
    private static String PVG_RETURN="BS0020404";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
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
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvisehdService advisehdService;
    /**
	 * Sets the Advisehd service.
	 * 
	 * @param AdvisehdService the new Advise service
	 */
	public void setAdvisehdService(AdvisehdService advisehdService) {
		this.advisehdService = advisehdService;
	}
	
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request,"paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}
	/**
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
		
		ParamUtil.putStr2Map(request, "CMPL_ADVS_NO", params);
		ParamUtil.putStr2Map(request, "CMPL_ADVS_TITLE", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_FROM", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_TO", params);
		ParamUtil.putStr2Map(request, "DEAL_PSON_ID", params);
		ParamUtil.putStr2Map(request, "DEAL_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		ParamUtil.putStr2Map(request,"CMPL_ADVS_TYPE",params);
	   /* if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DEAL)){
	    	params.put("APPOINT_PSON_ID", userBean.getXTYHID());
	    } else {
	    	params.put("APPOINT_PSON_ID", null);
	    }*/
//	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    
	    ParamUtil.putStr2Map(request, "APPOINT_PSON_ZW", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		String CMPL_ADVS_TYPE = ParamUtil.get(request,"CMPL_ADVS_TYPE");
		String QX = "";
		if(!StringUtil.isEmpty(CMPL_ADVS_TYPE)){
			if("建议".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "ADVS_TYPE", params);//建议类型
			}
			if("服务投诉".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "CMPL_OBJ", params); //投诉对象
			    ParamUtil.putStr2Map(request, "CMPL_TO_PSON", params);//投诉人员
			}
			if("产品投诉".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "PRD_NO", params); //货品编号
			    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
			}
		}
		//权限级别和审批流的封装
		String qxjbCon = ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean);
		qxjbCon =  qxjbCon.replaceAll("\"", "'");

		if(-1 != qxjbCon.indexOf("00")){
			QX = "1";//1 表示本部门以上的权限
		}
		
		if(StringUtil.isEmpty(search)){
			qxjbCon = qxjbCon + " and u.STATE in('未反馈','重提') ";
		}else{
			 ParamUtil.putStr2Map(request, "STATE", params);
		}
		
		if(StringUtil.isEmpty(search)){
			params.put("QXJBCON", qxjbCon);
		}
		
		
		
		//字段排序
		ParamUtil.setOrderField(request, params, "CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advisehdService.pageQuery(params, pageNo);
		request.setAttribute("QX", QX);//权限 级别 ==1表示配置了 多部分以上的权限
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("search", search);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	 /**
     * * 产品投诉回馈信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward prdcmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {
        	 List <AdvisehdModelChld> result = advisehdService.prdcmplQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        //设置 点击权限
        setQX(request,userBean);
        return mapping.findForward("prdcmpl");
    }
    
    /**
     * * 服务投诉回馈信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward sercmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {	
        	 AdvisehdModel result = advisehdService.sercmplQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        //设置 点击权限
        setQX(request,userBean);
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("sercmpl");
    }
    
    /**
     * * 建议信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward advs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {	
        	 AdvisehdModel result = advisehdService.advsQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        //设置 点击权限
        setQX(request,userBean);
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("advs");
    }
    
    /**
     * 回馈信息添加
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void saveFeedback(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String feedback = ParamUtil.get(request, "feedback");
		String cmplId = ParamUtil.get(request, "CMPL_ADVS_ID");
		try {
			String CMPL_ADVS_ID = advisehdService.saveFeedback(cmplId, feedback,userBean);
			jsonResult = jsonResult(true, CMPL_ADVS_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
    }
    
    /**
     * 指定处理人窗口跳转
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toAppointPson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
    	return mapping.findForward("appoint");
    }
    
    /**
     * 处理信息页面跳转
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward dealInformation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
    	request.setAttribute("userId",userBean.getXTYHID());
    	request.setAttribute("userName",userBean.getXM());
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    	request.setAttribute("nowTime",df.format(new Date()));
    	request.setAttribute("pvg",setPvg(userBean));
    	return mapping.findForward("dealInformation");
    }
    
    /**
     * 指派处理人
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public void toAppoint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String jsonResult = jsonResult();
    	PrintWriter writer = getWriter(response);
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	Map<String,String> params = new HashMap<String,String>();
    	ParamUtil.putStr2Map(request, "CMPL_ADVS_ID", params);
    	ParamUtil.putStr2Map(request, "DEAL_PSON_ID", params);
    	ParamUtil.putStr2Map(request, "DEAL_PSON_NAME", params);
    	ParamUtil.putStr2Map(request, "APPOINT_PSON_ID", params);
    	//ParamUtil.putStr2Map(request, "FEEDBACK_INFO", params);
    	String FEEDBACK_INFO = ParamUtil.get(request, "FEEDBACK_INFO");
    	String APPOINT_PSON_NAME = ParamUtil.get(request, "APPOINT_PSON_NAME");
    	
    	params.put("FEEDBACK_INFO", "指派 "+APPOINT_PSON_NAME+" 处理, "+FEEDBACK_INFO);
    	params.put("APPOINT_PSON_NAME", APPOINT_PSON_NAME);
    	
    	
    	
    	try {
    		advisehdService.txToAppoint(params,userBean);
			jsonResult = jsonResult(true, "指派处理人成功!");
			request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "指派失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
    }
    
    /**
     * 处理完成
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public void dealDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String jsonResult = jsonResult();
    	PrintWriter writer = getWriter(response);
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	Map<String,String> params = new HashMap<String,String>();
    	ParamUtil.putStr2Map(request, "CMPL_ADVS_ID", params);
    	ParamUtil.putStr2Map(request, "DEAL_PSON_ID", params);
    	ParamUtil.putStr2Map(request, "DEAL_PSON_NAME", params);
    	ParamUtil.putStr2Map(request, "FEEDBACK_INFO", params);
    	try {
    		Map<String,String> map = advisehdService.txdealDone(params,userBean);
//			jsonResult = jsonResult(true, "处理完成成功!");
			jsonResult = new Gson().toJson(new Result(true, map, ""));
			request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "处理失败!");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
    }
    /**
     * 处理过程页面跳转
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward dealProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	
    	List <AdvisehdModelProcess> result = advisehdService.processQuery(CMPL_ADVS_ID);
    	
    	request.setAttribute("list",result);
    	request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
    	
    	return mapping.findForward("dealProcess");
    }
    /**
     * 指定处理人
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void appointPson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String CMPL_ADVS_ID = ParamUtil.get(request, "CMPL_ADVS_ID");
		String APPOINT_PSON_ID = ParamUtil.get(request, "APPOINT_PSON_ID");
		try {
			advisehdService.appointPson(CMPL_ADVS_ID, APPOINT_PSON_ID,userBean);
			jsonResult = jsonResult(true, CMPL_ADVS_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
    }
	
    /**
     * 设置页面 点击权限
     * @param request
     * @param userBean
     */
    private void setQX(HttpServletRequest request, UserBean userBean){
    	String pvg_edit = QXUtil.getQXTJ(userBean, "BS0020402");
        String pvg_deal = QXUtil.getQXTJ(userBean, "BS0020403");
        if(-1 != pvg_edit.indexOf("00") || -1 != pvg_deal.indexOf("00") ){
        	request.setAttribute("QX", "1");//权限 级别 ==1表示配置了 多部分以上的权限
        }
        request.setAttribute("XTYHID", userBean.getXTYHID());
    }
    
    
    
    
    public void toExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String,String> params=new HashMap<String, String>();
        ParamUtil.putStr2Map(request, "CMPL_ADVS_NO", params);
		ParamUtil.putStr2Map(request, "CMPL_ADVS_TITLE", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_FROM", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_TO", params);
		ParamUtil.putStr2Map(request, "DEAL_PSON_ID", params);
		ParamUtil.putStr2Map(request, "DEAL_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		ParamUtil.putStr2Map(request,"CMPL_ADVS_TYPE",params);
	   /* if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DEAL)){
	    	params.put("APPOINT_PSON_ID", userBean.getXTYHID());
	    } else {
	    	params.put("APPOINT_PSON_ID", null);
	    }*/
//	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    
	    ParamUtil.putStr2Map(request, "APPOINT_PSON_ZW", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		String CMPL_ADVS_TYPE = ParamUtil.get(request,"CMPL_ADVS_TYPE");
		String QX = "";
		//权限级别和审批流的封装
		String qxjbCon = ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean);
		qxjbCon =  qxjbCon.replaceAll("\"", "'");

		if(-1 != qxjbCon.indexOf("00")){
			QX = "1";//1 表示本部门以上的权限
		}
		
		if(StringUtil.isEmpty(search)){
			qxjbCon = qxjbCon + " and u.STATE in('未反馈','重提') ";
		}else{
			 ParamUtil.putStr2Map(request, "STATE", params);
		}
		
		if(StringUtil.isEmpty(search)){
			params.put("QXJBCON", qxjbCon);
		}
		//字段排序
		ParamUtil.setOrderField(request, params, "CRE_TIME", "DESC");
	    List list=new ArrayList();
	    if(!StringUtil.isEmpty(CMPL_ADVS_TYPE)){
	    	if("产品投诉".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "PRD_NO", params); //货品编号
			    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
			    
			  //产品投诉
			    String prdTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
			    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,回馈信息,状态,货品编号,货品名称,规格型号,产品类型,产品问题类型,消费者使用时间,生产基地,生产日期,购买日期,备注说明,回馈信息";
			    String prdTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
			    				  "ADVS_SOURCE,TEL,CHANN_NO,CHANN_NAME,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,FEEDBACK_INFO,STATE,PRD_NO,PRD_NAME,PRD_SPEC,PRD_TYPE,PRD_PROBLEM_TYPE,USE_TIME,SHIP_POINT_NAME,PRDC_DATE,BUY_DATE,REMARK,FEEDBACK_INFO";
			    List prdList = advisehdService.expPrdcmplImport(params);
			    Map map=new HashMap();
			    map.put("TmpContentCn", prdTmpContentCn);
			    map.put("TmpContent", prdTmpContent);
			    map.put("list", prdList);
			    map.put("name", "产品投诉");
			    list.add(map);
			}else if("服务投诉".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "CMPL_OBJ", params); //投诉对象
			    ParamUtil.putStr2Map(request, "CMPL_TO_PSON", params);//投诉人员
			  //服务投诉
			    String serveTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
			    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,状态,投诉对象,投诉的人员,投诉内容,投诉回馈信息";
			    String serveTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
			    				  "ADVS_SOURCE,CHANN_NO,CHANN_NAME,TEL,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,STATE,CMPL_OBJ,CMPL_TO_PSON,CMPL_ADVS_CONTENT,FEEDBACK_INFO";
			    List serveList = advisehdService.expSrvcmplImport(params);
			    Map map=new HashMap();
			    map.put("TmpContentCn", serveTmpContentCn);
			    map.put("TmpContent", serveTmpContent);
			    map.put("list", serveList);
			    map.put("name", "服务投诉");
			    list.add(map);
			}else if("建议".equals(CMPL_ADVS_TYPE)){
				ParamUtil.putStr2Map(request, "ADVS_TYPE", params);//建议类型
				//建议
			    String proposeTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
			    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,状态,建议概述,建议内容,建议回馈信息";
			    String proposeTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
			    				  "ADVS_SOURCE,TEL,CHANN_NO,CHANN_NAME,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,STATE,ADVS_SMMRY,CMPL_ADVS_CONTENT,FEEDBACK_INFO";
			    List proposeList = advisehdService.expAdvsImport(params);
			    Map map=new HashMap();
			    map.put("TmpContentCn", proposeTmpContentCn);
			    map.put("TmpContent", proposeTmpContent);
			    map.put("list", proposeList);
			    map.put("name", "建议");
			    list.add(map);
		       
			}
		}else{
			//产品投诉
			Map map=new HashMap();
			  
		    String prdTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
		    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,状态,货品编号,货品名称,规格型号,产品类型,产品问题类型,消费者使用时间,生产基地,生产日期,购买日期,备注说明,回馈信息";
		    String prdTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
		    				  "ADVS_SOURCE,CHANN_NO,CHANN_NAME,TEL,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,STATE,PRD_NO,PRD_NAME,PRD_SPEC,PRD_TYPE,PRD_PROBLEM_TYPE,USE_TIME,SHIP_POINT_NAME,PRDC_DATE,BUY_DATE,REMARK,FEEDBACK_INFO";
		    List prdList = advisehdService.expPrdcmplImport(params);
		    map=new HashMap();
		    map.put("TmpContentCn", prdTmpContentCn);
		    map.put("TmpContent", prdTmpContent);
		    map.put("list", prdList);
		    map.put("name", "产品投诉");
		    list.add(map);
		    
		  //服务投诉
		  
		    String serveTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
		    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,状态,投诉对象,投诉的人员,投诉内容,投诉回馈信息";
		    String serveTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
		    				  "ADVS_SOURCE,CHANN_NO,CHANN_NAME,TEL,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,STATE,CMPL_OBJ,CMPL_TO_PSON,CMPL_ADVS_CONTENT,FEEDBACK_INFO";
		    List serveList = advisehdService.expSrvcmplImport(params);
		    map=new HashMap();
		    map.put("TmpContentCn", serveTmpContentCn);
		    map.put("TmpContent", serveTmpContent);
		    map.put("list", serveList);
		    map.put("name", "服务投诉");
		    list.add(map);
		    map=new HashMap();
		  //建议
		    String proposeTmpContentCn="编号,标题,类型,紧急程度,时间,区域,区域经理,总监,提出人," +
		    					"投诉来源,加盟商编号,加盟商名称,加盟商电话,消费者姓名,消费者电话,处理人,处理时间,状态,建议概述,建议内容,建议回馈信息";
		    String proposeTmpContent="CMPL_ADVS_NO,CMPL_ADVS_TITLE,CMPL_ADVS_TYPE,EMG_LVL,RAISE_TIME,AREA_NAME,AREA_MANAGE_NAME,AREA_CHIEF_NAME,RAISE_PSON_NAME," +
		    				  "ADVS_SOURCE,TEL,CHANN_NO,CHANN_NAME,CUSTOMER_NAME,CUSTOMER_TEL,DEAL_PSON_NAME,DEAL_TIME,STATE,ADVS_SMMRY,CMPL_ADVS_CONTENT,FEEDBACK_INFO";
		    List proposeList = advisehdService.expAdvsImport(params);
		    
		    map.put("TmpContentCn", proposeTmpContentCn);
		    map.put("TmpContent", proposeTmpContent);
		    map.put("list", proposeList);
		    map.put("name", "建议");
		    list.add(map);
		  
		}
        try {
			doExport(response, list, "投诉与建议处理");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
  //Excel文件导出成文件流
	//dataList 需要导出的数据列表
	//execlName 导出后默认的文件名
	//tmpContent:数据库字段名，多字段以逗号分割
	//tmpContentCnexcel:文件名字段名，多字段以逗号分割
	/**
	 * Do export.
	 * 
	 * @param response the response
	 * @param dataList the data list
	 * @param execlName the execl name
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * 
	 * @throws Exception the exception
	 */
	public  static void  doExport(HttpServletResponse response,List dataList,String execlName) throws Exception{
				//生成excel
	            HSSFWorkbook workbook = printExcel(dataList);
	            //导出excel
	            writeExecl(response,workbook,execlName);
	}
    
	
	/**
	 * Prints the excel.
	 * 
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
	 * 
	 * @return the hSSF workbook
	 */
	private  static HSSFWorkbook printExcel(List list){

	     HSSFWorkbook workbook = null;
	     try{
	    	//创建工作簿实例 
	          workbook = new HSSFWorkbook();
		     for (int s = 0; s < list.size(); s++) {
		    	 Map params=(Map) list.get(s);
		    	 String tmpContentCn=(String) params.get("TmpContentCn");
		    	 String tmpContent=(String) params.get("TmpContent");
		    	 List dataList=(List) params.get("list");
		    	 String name=(String) params.get("name");
		    	 String[] titles_CN = tmpContentCn.split(",");
			     String[] titles_EN = tmpContent.split(",");
			          
			          //创建工作表实例 
			         HSSFSheet sheet = workbook.createSheet(name); 
			          //设置列宽 
			          setSheetColumnWidth(titles_CN,sheet);
			        //获取样式 
			          HSSFCellStyle style = createTitleStyle(workbook); 
			          if(dataList != null){
			               //创建第一行标题 
			                HSSFRow row = sheet.createRow((short)0);// 建立新行
			                for(int i=0;i<titles_CN.length;i++){
			                createCell(row, i, null, HSSFCell.CELL_TYPE_STRING, 
			                       titles_CN[i]);
			                }
			                //给excel填充数据 
			               for(int i=0;i<dataList.size();i++){ 
			                        // 将dataList里面的数据取出来 
			                        Map<String,String> map = (HashMap)(dataList.get(i));
			                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
			               
			                        boolean isOverTime = false;
			                         for(int j=0;j<titles_EN.length;j++){
			                            createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
			                                  }               
			                      }
			       }else{
			                createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "查无资料");
			       }
			}
	  }catch(Exception e){
	              e.printStackTrace();
	  }
	 return workbook;
	}
	
	/**
	 * Write execl.
	 * 
	 * @param response the response
	 * @param workbook the workbook
	 * @param execlName the execl name
	 */
	public static void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName) {
		if (null == workbook)
		{
			workbook = new HSSFWorkbook();
		}
		
		if (0 == workbook.getNumberOfSheets()) {
			HSSFSheet sheet = workbook.createSheet("无数据");
			sheet.createRow(3).createCell(3).setCellValue("未查询到数据!");
		}
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel;charset=UTF-8");
		ServletOutputStream outputStream = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-DD") + ".xls");
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//创建Excel单元格  
	/**
	 * Creates the cell.
	 * 
	 * @param row the row
	 * @param column the column
	 * @param style the style
	 * @param cellType the cell type
	 * @param value the value
	 */
	private static void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
	  HSSFCell cell = row.createCell( column);  
	  if (style != null) { 
	       cell.setCellStyle(style); 
	  }   
	  String res = (value==null?"":value).toString();
	  switch(cellType){ 
	       case HSSFCell.CELL_TYPE_BLANK: {} break; 
	       case HSSFCell.CELL_TYPE_STRING: {cell.setCellValue(res+"");} break; 
	       case HSSFCell.CELL_TYPE_NUMERIC: {
	       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
	           cell.setCellValue(StringUtil.getDouble(res));}break; //modidy 2015-03-20
	       default: break; 
		 }  
		
		} 
	//设置excel的title样式  
	/**
	 * Creates the title style.
	 * 
	 * @param wb the wb
	 * 
	 * @return the hSSF cell style
	 */
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
	  HSSFFont boldFont = wb.createFont(); 
	  boldFont.setFontHeight((short) 200); 
	  HSSFCellStyle style = wb.createCellStyle(); 
	  style.setFont(boldFont); 
	  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00")); 
	  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
	  return style;  
	}
	//设置列宽
	/**
	 * Sets the sheet column width.
	 * 
	 * @param titles_CN the titles_ cn
	 * @param sheet the sheet
	 */
	private static void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
	   // 根据你数据里面的记录有多少列，就设置多少列
	  for(int i=0;i<titles_CN.length;i++){
	          sheet.setColumnWidth((short)i, (short) 3000);
	  }

	}
	
	  /**
     * 退回
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String CMPL_ADVS_ID = ParamUtil.get(request, "CMPL_ADVS_ID");
		String REMARK = ParamUtil.get(request, "REMARK");
		try {
			advisehdService.txReturn(CMPL_ADVS_ID, REMARK,userBean);
			jsonResult = jsonResult(true, CMPL_ADVS_ID);
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "退回失败");
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
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE));
	    	pvgMap.put("PVG_DEAL", QXUtil.checkPvg(userBean, PVG_DEAL));
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_RETURN",QXUtil.checkPvg(userBean, PVG_RETURN) );
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}