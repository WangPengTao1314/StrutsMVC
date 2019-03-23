/**
 * 项目名称：平台
 * 系统名：自定义查询管理
 * 文件名：CustQueryAction.java
 */
package com.hoperun.sys.action; 

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.CustomQueryBean;
import com.hoperun.sys.model.CustomQueryDtlBean;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.CustQueryService;

/**
 * The Class CustQueryAction.
 * 
 * @module 系统管理
 * @func 自定义查询管理
 * @version 1.1
 * @author fcf
 * @edit by  zhuxw 2012-12-20
 */
public class CustQueryAction extends BaseAction {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(SysLoginAction.class);
	
    /** The cust query service. */
    private CustQueryService custQueryService;  


    /**
     * Sets the cust query service.
     * 
     * @param custQueryService the new cust query service
     */
    public void setCustQueryService(CustQueryService custQueryService) {
		this.custQueryService = custQueryService;
	}
    
    /**
     * 自定义报表设置框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("frames");
	}
	
	/**
	 * Open window.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward openWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("rptSql", (String)request.getParameter("rptSql"));
		return mapping.findForward("openWindows");
	}
	
	 /**
 	 * 自定义报表展示框架页面.
 	 * 
 	 * @param mapping the mapping
 	 * @param form the form
 	 * @param request the request
 	 * @param response the response
 	 * 
 	 * @return the action forward
 	 */
	public ActionForward toFrameDisplay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toFrameDisplay");
	}
	
	 /**
 	 * 自定义报表修改.
 	 * 
 	 * @param mapping the mapping
 	 * @param form the form
 	 * @param request the request
 	 * @param response the response
 	 * 
 	 * @return the action forward
 	 */
	public ActionForward toFrameEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toFrameEdit");
	}

	/**
	 * 自定义查询管理-->初始化查询.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
    public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表名称
    	ParamUtil.putStr2Map(request, "selRptName", params);
    	//报表编号
    	ParamUtil.putStr2Map(request, "selRptCode", params); 
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	String pageSize=ParamUtil.get(request,"pageSize");
    	if(StringUtil.isEmpty(pageSize)){
    		pageSize="25";
    	}
    	params.put("pageSize", pageSize);
//    	ParamUtil.putStr2Map(request, "pageSize", params);
        //调用查询接口
    	IListPage list = custQueryService.custPageQuery(params, pageNo);
    	request.setAttribute("page", list);
    	request.setAttribute("params",params);
        return mapping.findForward("queryList");
    }
    
    /**
     * 自定义查询展示-->初始化查询.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward queryListDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表名称
    	ParamUtil.putStr2Map(request, "selRptName", params);
    	//报表编号
    	ParamUtil.putStr2Map(request, "selRptCode", params);
    	
        ParamUtil.putStr2Map(request, "pageSize", params); 
        
        //用户信息
        UserBean userBean = ParamUtil.getUserBean(request);
    	params.put("userPk", userBean.getXTYHID()); 
    	
        //调用查询接口
    	IListPage list = custQueryService.custPageQueryDisplay(params);
    	
    	request.setAttribute("page", list);
    	request.setAttribute("params", params);
        return mapping.findForward("queryListDisplay");
    }
    
    
    /**
     * 自定义查询管理-->新增、编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward addEditRpt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表主键
    	ParamUtil.putStr2Map(request, "hidRptPk", params); 
    	//报表主键
    	String hidRptPk = (String)params.get("hidRptPk");
    	
    	//定义返回对象
    	CustomQueryBean resultBean = null;
    	
    	//如果报表主键为空则新增
    	if(StringUtil.isEmpty(hidRptPk)){
    		//创建空对象
    		resultBean = new CustomQueryBean(); 
    		//默认不设权限
    		resultBean.setIsRole("0");
    	}else{
    		//调用查询接口，查询相对应自定义报表详细信息
    		resultBean = custQueryService.queryForByRptPk(params);
    	}
    	//报表主键
    	request.setAttribute("hidRptPk",hidRptPk );
    	//返回自定义报表对象
    	request.setAttribute("resultBean", resultBean);
    	
        return mapping.findForward("insertEdit");
    }
    
    /**
     * 自定义查询管理-->下一步(新增、编辑).
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward nextAddEditRpt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表主键
    	ParamUtil.putStr2Map(request, "hidRptPk", params); 
    	
    	//首先保存前一页面数据
    	//报表编号
    	ParamUtil.putStr2Map(request, "rptCode", params); 
    	//报表名称
    	ParamUtil.putStr2Map(request, "rptName", params);
    	//报表SQL
    	ParamUtil.putStr2Map(request, "rptSql", params);
    	//是否设置权限
    	ParamUtil.putStr2Map(request, "isRole", params); 
    	
    	//说明
    	ParamUtil.putStr2Map(request, "remark", params);
    	//用户权限
    	ParamUtil.putStr2Map(request, "roleUser", params);
    	ParamUtil.putStr2Map(request, "roleUserName", params);
    	//角色权限
    	ParamUtil.putStr2Map(request, "roleCode", params);
    	ParamUtil.putStr2Map(request, "roleName", params);
    	//工作组权限
    	ParamUtil.putStr2Map(request, "workGroupCode", params);
    	ParamUtil.putStr2Map(request, "workGroupName", params);
    	
    	if(!StringUtil.isEmpty(params.get("rptSql"))){
    		params.put("rptSql", ((String)params.get("rptSql")).toUpperCase());
    	}
    	//用户对象
    	UserBean userBean = ParamUtil.getUserBean(request);
    	
    	params.put("uptPerson", userBean.getXTYHID());
    	params.put("crePersonName", userBean.getXM());
    	params.put("crePerson", userBean.getXTYHID());
    	
    	String rptPk = (String)params.get("hidRptPk");
    	
    	//保存前一页的数据
    	//为空时，新增
    	if(StringUtil.isEmpty(rptPk)){
    		//生成随机主键
    		params.put("hidRptPk", StringUtil.uuid32len());
    		params.put("state", "0");
    		custQueryService.txInsertRptMain(params);
    	}
    	//编辑
    	else{
    		custQueryService.txUpdateRptMain(params);
    	} 
    	
    	//处理权限表
    	custQueryService.txProcessRole(params);
    	
    	//初始化当前页面的数据
    	params.put("init", "初始化");
    	String rptSql =	((String)params.get("rptSql")).toUpperCase();
    	rptSql = rptSql.replaceAll("@USERID@", "''");
		rptSql = rptSql.replaceAll("@ORGANIDS@", "''");
		rptSql = rptSql.replaceAll("@DEPTIDS@", "''");
		rptSql = rptSql.replaceAll("@DEPTID@", "''");
		rptSql = rptSql.replaceAll("@ORGANID@", "''");
		rptSql = rptSql.replaceAll("@", "%");
		params.put("rptSql", rptSql);
		
    	List<CustomQueryDtlBean> list = custQueryService.queryRptQueryDtlByPk(params);
    	//报表主键
    	request.setAttribute("hidRptPk", (String)params.get("hidRptPk"));
    	request.setAttribute("rptSql", (String)params.get("rptSql"));
    	//返回结果集
    	request.setAttribute("page", list);
        return mapping.findForward("nextInsertEdit");
    }
    
    
    /**
     * 修改自定义报表状态.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {   
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
    	try{ 
	    	//获取需更新的状态
	    	String rptState = request.getParameter("rptState"); 
	    	String rptPk = request.getParameter("rptPk"); 
	    	Map<String,String> map = new HashMap<String,String>();
	    	//状态
	    	map.put("rptState", rptState);
	    	//报表主键
	    	map.put("rptPk", rptPk);
	    	
	    	//状态为3，则删除此自定义查询
	    	if(!StringUtil.isEmpty(rptState) && "3".equals(rptState)){
	    		//调用删除接口
	    		custQueryService.txDeleteRptRow(map);
	    	}else{
	    		//调用更新接口
	    		custQueryService.txUpdateRptState(map);
	    	}
	    	jsonResult = jsonResult(true, null);
    	}catch(Exception e){
    		logger.info(e); 
    		jsonResult = jsonResult(false, "操作异常");
    	} 
    	
    	if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		} 
    	
    	return null;
	}
    
    /**
     * 校验报表SQL是否正确.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward checkRptSql(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {   
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
    	try{ 
	    	//获取报表SQL
	    	String rptSql = new String(request.getParameter("rptSql").getBytes("ISO-8859-1"),"gbk");
	    	//参数
	    	Map<String,String> map = new HashMap<String,String>();
	    	
	    	if(!StringUtil.isEmpty(rptSql)){
	    		if((rptSql.toUpperCase()).trim().indexOf("SELECT") == -1){
	    			jsonResult = jsonResult(false, "报表SQL必须以SELECT开头");
	    		}
	    		//转换
		    	//转换#成*
	    		rptSql = rptSql.toUpperCase();
	    		//本人员($userid)
	    		rptSql = rptSql.replaceAll("@USERID@", "''");
	    		rptSql = rptSql.replaceAll("@ORGANIDS@", "''");
	    		System.err.println("rptSql======"+rptSql);
	    		rptSql = rptSql.replaceAll("@DEPTIDS@", "''");
	    		rptSql = rptSql.replaceAll("@DEPTID@", "''");
	    		System.err.println("rptSql======"+rptSql);
	    		rptSql = rptSql.replaceAll("@ORGANID@", "''");
	    		
	    		rptSql = rptSql.replaceAll("@", "%");
	    		
		    	map.put("rptSql", rptSql);
		    	
		    	//调用校验接口验证
		    	boolean flag = custQueryService.checkRptSql(map);
		    	if(flag)
		    		jsonResult = jsonResult(true, null);
		    	else
		    		jsonResult = jsonResult(false, "报表SQL错误");
	    	}
	    	
    	}catch(Exception e){
    		logger.info(e); 
    		jsonResult = jsonResult(false, "操作异常");
    	} 
    	
    	if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		} 
    	
    	return null;
	}
    
    /**
     * 自定义查询管理-->自定义报表展示.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward displayRpt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表主键
    	ParamUtil.putStr2Map(request, "hidRptPk", params); 
    	
    	//查询主表信息
    	CustomQueryBean mainBean = custQueryService.queryForByRptPk(params);
    	request.setAttribute("topRtpName", mainBean.getRptName());
    	
    	//查询条件
    	params.put("type", "1");
    	//查询条件结果集
    	List<CustomQueryDtlBean> queryList = custQueryService.queryDisplayRpt(params);
    	//初始化查询条件(HTML)
    	String queryHtml = createQueryHtmlForQuery(queryList,(String)params.get("hidRptPk"),request);
    	request.setAttribute("queryHtml", queryHtml);
    	
    	//生成按纽(HTML)
    	String buttonHtml = createBuffer(queryList,(String)params.get("hidRptPk"),request);
    	request.setAttribute("buttonHtml", buttonHtml);
    	System.err.println("buttonHtml==="+buttonHtml);
    	
    	String listOpen = createBufferOpen(queryList,request);
    	//生成表头显示
    	request.setAttribute("listOpen", listOpen);
    	
    	//结果列表 
    	params.put("type", "");
    	List<CustomQueryDtlBean> entityList = custQueryService.queryDisplayRpt(params);
    	//初始化查询列表展示(HTML)
    	String listHtml = createListHtmlForQuery(entityList,request);
    	request.setAttribute("listHtml", listHtml);
    	
    	return mapping.findForward("displayRpt");
    }
    
    /**
     * Creates the buffer open.
     * 
     * @param list the list
     * @param request the request
     * 
     * @return the string
     */
    private String createBufferOpen(List<CustomQueryDtlBean> list,HttpServletRequest request){
    	//获取查询条件
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "queryCondition", params);  
		String queryCondition = (String)params.get("queryCondition"); 
		
		//如果有查询条件时,拼查询SQL
		String result = "";
		StringBuffer sqlCondition = new StringBuffer(); 
		
		if(!StringUtil.isEmpty(queryCondition)){
			String[] arr = queryCondition.split(";");
			if(arr!=null && arr.length>0){
				for(int i=0;i<arr.length;i++){
					String[] arra = arr[i].split(",");
					if(arra!=null && arra.length == 3){
						for(CustomQueryDtlBean bean:list){
							if(arra[0].equals(bean.getPhysicName()) && "1".equals(bean.getDisplay())){
								//取查询条件的值
								result = (String)request.getParameter("hid_"+arra[0]); 
								//字符串
								if("0".equals(arra[2]) && !StringUtil.isEmpty(arra[0]) && !StringUtil.isEmpty(result)){ 
									//等于
									if("0".equals(arra[1])){
										sqlCondition.append("【"+bean.getLogicName() + "】：");
										sqlCondition.append(result.trim()+"	"); 
									}
									//左匹配
									else if("1".equals(arra[1])){ 
										sqlCondition.append("【"+bean.getLogicName() + "】左匹配");
										sqlCondition.append(result.trim()+"	"); 
									}
									//右匹配
									else if("2".equals(arra[1])){ 
										sqlCondition.append("【"+bean.getLogicName() + "】右匹配");
										sqlCondition.append(result.trim()+"	"); 
									} 
									//模糊匹配
									else if("3".equals(arra[1])){  
										sqlCondition.append("【"+bean.getLogicName() + "】模糊匹配");
										sqlCondition.append(result.trim()+"	"); 
									} 
								}
								//日期\数字
								else if(!StringUtil.isEmpty(arra[0])){
									result = (String)request.getParameter("hid_"+arra[0]+"START");
									String end = (String)request.getParameter("hid_"+arra[0]+"END"); 
									//大于
									if(!StringUtil.isEmpty(result)){ 
										sqlCondition.append("【"+bean.getLogicName() + "】大于等于");
										sqlCondition.append(result.trim()+"	"); 
									}
									//小于
									if(!StringUtil.isEmpty(end)){ 
										sqlCondition.append("【"+bean.getLogicName() + "】小于等于");
										sqlCondition.append(end+" "); 
									}
								} 
							}
						}
					}
				}
			}  
		}
		return sqlCondition.toString();
	}
    
    /**
     * Creates the buffer.
     * 
     * @param list the list
     * @param hidRptPk the hid rpt pk
     * @param request the request
     * 
     * @return the string
     */
    private String createBuffer(List<CustomQueryDtlBean> list,String hidRptPk,HttpServletRequest request){
    	StringBuffer sb = new StringBuffer();

    	sb.append("<table width=\"100%\" height=\"20\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"panel\">");
	    sb.append("<tr>");	
	    sb.append("<td height=\"20\">");
		sb.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"wz\">");
		sb.append("<tr>");		
		sb.append("<td width=\"28\" align=\"center\"><label class=\"wz_img\"></label></td>");		
		sb.append("<td>当前位置：系统管理&gt;&gt;查询管理&gt;&gt;自定义报表查询");	
		//
		sb.append("</td>");			
		sb.append("<td width=\"50\" align=\"right\"></td></tr></table></td>");
		sb.append("</tr></table>");	
		//生成按钮开始
		sb.append("<div class=\"tablayer\" >");
		sb.append("<table cellSpacing=0 cellPadding=0 border=0 width=\"100%\">");
      	sb.append("<tr><td align=\"left\" nowrap>");
      	//查询
		sb.append("<input id=\"b_query\" type=\"button\" class=\"btn\" value=\"查询(Q)\" title=\"Alt+Q\" onclick=\"doQuery();\" accesskey=\"Q\">"); 
		//重置
		sb.append("<input id=\"b_reset\" type=\"button\" class=\"btn\" value=\"重置(R)\" title=\"Alt+R\" onclick=\"doReset();\" accesskey=\"R\">");
		//导出
		sb.append("<input id=\"b_export\" type=\"button\" class=\"btn\" value=\"导出Exl(E)\" title=\"Alt+E\" onclick=\"doExport();\" accesskey=\"E\">");
		//打印
		sb.append("<input id=\"b_print\" type=\"button\" class=\"btn\" value=\"打印(P)\" title=\"Alt+P\" onclick=\"doPrint();\" accesskey=\"P\">");
		//返回
		sb.append("<input id=\"b_back\" type=\"button\" class=\"btn\" value=\"返回(B)\" title=\"Alt+B\" accesskey=\"B\" onclick=\"doBack();\">");
		
		sb.append("</td></tr></table></div>");
		return sb.toString();
    }
    
    /**
     * 根据结果生成查询条件HTML.
     * 
     * @param list the list
     * @param hidRptPk the hid rpt pk
     * @param request the request
     * 
     * @return the string
     */
    private String createQueryHtmlForQuery(List<CustomQueryDtlBean> list,String hidRptPk,HttpServletRequest request){
    	StringBuffer sb = new StringBuffer();

    	//生成导航条开始
		sb.append("<form id=\"queryForm\" method=\"post\" action=\"customQuery.shtml?action=displayRpt\">");
		//报表展示页面
		if("1".equals((String)request.getParameter("currentPageNo"))){
			sb.append("<input type=\"hidden\" name = \"currentPageNo\" id = \"currentPageNo\" value = \"1\"/>"); 
		}
		sb.append("<input type=\"hidden\" name = \"hidRptPk\" id = \"hidRptPk\" value = \"");
		sb.append(hidRptPk);
		sb.append("\"/>");
		if("1".equals(request.getParameter("queryType"))){
			sb.append("<input type=\"hidden\" name = \"queryType\" id = \"queryType\" value = \"1\"/>");
		}else{
			sb.append("<input type=\"hidden\" name = \"queryType\" id = \"queryType\" value = \"0\"/>");
		}
		
		//生成查询条件部分
		sb.append("<table width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"4\" class=\"detail\">");
		sb.append("<tr><td class=\"detail2\"><table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\" class=\"detail3\">");
		//一行显示三个
		int pageCount = 4;
		int count = 1;
		String logicName = "";
		String physicName = "";
		StringBuffer queryCondition = new StringBuffer();
		for(CustomQueryDtlBean qryList:list){ 
			if(count == 1)
				sb.append("<tr>"); 
			
			sb.append("<td width=\"10%\" nowrap align=\"right\" class=\"detail_label\">");
			logicName = qryList.getLogicName();
			sb.append(logicName);
			
			//字符串
			if("0".equals(qryList.getColType())) {
				sb.append("：</td>");
				sb.append("<td width=\"15%\" nowrap align=\"right\" class=\"detail_content\" ><div align=\"left\">");
				physicName = qryList.getPhysicName();
				//输入框开始
				sb.append("<input json=\"hid_");
				sb.append(physicName);
				sb.append("\" id=\"hid_");
				sb.append(physicName);
				sb.append("\" name=\"hid_");
				sb.append(physicName);
				sb.append("\" value = \"");
				sb.append(StringUtil.nullToSring((String)request.getParameter("hid_"+physicName)));
				sb.append("\" label=\"");
				sb.append(qryList.getLogicName() + "\"");
				//是否必输入
				if("1".equals(qryList.getMustInput())){
					sb.append(" autocheck=\"true\" mustinput=\"true\"");
				} 
				//文本框控件
				if("7".equals(qryList.getCompoentType())){
					sb.append("  inputtype=\"string\"  ");
				}
				//日期
			    if("0".equals(qryList.getCompoentType())){
					sb.append(" inputtype=\"date\" onclick=\"SelectDate();\" readonly ");
				}
				
				sb.append(" type=\"text\"/>");
 
			}
			//数字\日期
			else if("1".equals(qryList.getColType()) || "2".equals(qryList.getColType())){ 
				sb.append("</td>");
				sb.append("<td width=\"15%\" nowrap align=\"right\" class=\"detail_content\" ><div align=\"left\">");
				physicName = qryList.getPhysicName();
				//输入框开始
				sb.append("从：<input json=\"hid_");
				sb.append(physicName);
				sb.append("START\" size= \"8\" id=\"hid_");
				sb.append(physicName);
				sb.append("START\" name=\"hid_");
				sb.append(physicName);
				sb.append("START\" value = \"");
				sb.append(StringUtil.nullToSring((String)request.getParameter("hid_"+physicName+"START")));
				sb.append("\" label=\"");
				sb.append(qryList.getLogicName() + "\"");
				//是否必输入
				if("1".equals(qryList.getMustInput())){
					sb.append(" autocheck=\"true\" mustinput=\"true\"");
				}
				
				if("0".equals(qryList.getCompoentType())){
					sb.append(" size = \"6\" inputtype=\"date\" onclick=\"SelectDate();\" readonly ");
				}
				sb.append(" type=\"text\"/>");
                  
				//edit by zhuxw
				String conPath=request.getContextPath();
				UserBean userBean = ParamUtil.getUserBean(request);
				 
				//如果是日期，显示日期
				if("0".equals(qryList.getCompoentType())) {
					sb.append("<img align=\"absmiddle\" style=\"cursor: hand\" src=\""+conPath+"/styles/"+userBean.getUSERSTYLE()+"/images/plus/date_16x16.gif\" onclick=\"SelectDate(hid_");
					sb.append(physicName);
					sb.append("START);\">");
				}
				
				sb.append("到：<input json=\"hid_");
				sb.append(physicName);
				sb.append("END\" id=\"hid_");
				sb.append(physicName);
				sb.append("END\" size= \"8\" name=\"hid_");
				sb.append(physicName);
				sb.append("END\" value = \"");
				sb.append(StringUtil.nullToSring((String)request.getParameter("hid_"+physicName+"END")));
				sb.append("\" label=\"");
				sb.append(qryList.getLogicName() + "\"");
				//是否必输入
				if("1".equals(qryList.getMustInput())){
					sb.append(" autocheck=\"true\" mustinput=\"true\"");
				}
				
				//日期控件
				if("0".equals(qryList.getCompoentType())){
					sb.append(" size = \"6\" inputtype=\"date\" onclick=\"SelectDate();\" readonly ");
				}
				
				sb.append(" type=\"text\"/>");

				
				//如果是日期，显示日期
				if("0".equals(qryList.getCompoentType())) {
					sb.append("<img align=\"absmiddle\" style=\"cursor: hand\" src=\""+conPath+"/styles/"+userBean.getUSERSTYLE()+"/images/plus/date_16x16.gif\" onclick=\"SelectDate(hid_");
					sb.append(physicName);
					sb.append("END);\">");
				}
			} 
			//输入框结束
			
			sb.append("</div></td>"); 
			queryCondition.append(physicName);
			queryCondition.append(",");
			queryCondition.append(qryList.getMatchModel());
			queryCondition.append(",");
			queryCondition.append(qryList.getColType());
			queryCondition.append(";");
			if(count%pageCount == 0)
				sb.append("</tr>");
			count++; 
		}
		
		if((count-1)%pageCount!=0){
			for(int i= 0;i< pageCount - (count-1)%pageCount;i++){
				sb.append("<td width=\"10%\" nowrap align=\"right\" class=\"detail_label\"></td><td width=\"15%\" nowrap align=\"right\" class=\"detail_content\"></td>"); 
			}
			sb.append("</tr>");
		}
		sb.append("<input type=\"hidden\" name = \"queryCondition\" id = \"queryCondition\" value = \""+queryCondition.toString()+"\"/>");
		sb.append("</table></td></tr></table></form>");  			
					
		//查询HTML
    	return sb.toString();
    }
    
    /**
     * 根据结果生成结果集条件HTML.
     * 
     * @param list the list
     * @param request the request
     * 
     * @return the string
     */
    @SuppressWarnings("unchecked")
	private String createListHtmlForQuery(List<CustomQueryDtlBean> list,HttpServletRequest request){
    	StringBuffer sb = new StringBuffer();
    	StringBuffer suSb = new StringBuffer();
    	StringBuffer colName = new StringBuffer();
    	 //结果集中间部分开始
		//表头部分开始
		sb.append("<tr class=\"fixedRow\">");
		//处理表头列表
		for(CustomQueryDtlBean qryList:list){
			sb.append("<th nowrap align=\"center\" >");
			sb.append(qryList.getLogicName());
			sb.append("</th>");
			suSb.append(qryList.getPhysicName()+",");
			colName.append(qryList.getLogicName()+",");
		}
		sb.append("</tr>");
		//表头部分结束
	
		//处理具体列信息
		sb.append("<c:forEach items=\"${page.result}\" var=\"rst\" varStatus=\"row\"> <c:set var=\"r\" value=\"${row.count % 2}\"></c:set> <c:set var=\"rr\" value=\"${row.count}\"></c:set>");
		//循环具体数据
		
		//获取查询条件
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "queryCondition", params); 
		ParamUtil.putStr2Map(request, "hidRptPk", params); 
		String queryCondition = (String)params.get("queryCondition");
		String hidRptPk = (String)params.get("hidRptPk");
		
		//如果有查询条件时,拼查询SQL
		String result = "";
		StringBuffer sqlCondition = new StringBuffer();
		sqlCondition.append(" where 1=1 ");
		if(!StringUtil.isEmpty(queryCondition)){
			String[] arr = queryCondition.split(";");
			if(arr!=null && arr.length>0){
				for(int i=0;i<arr.length;i++){
					String[] arra = arr[i].split(",");
					if(arra!=null && arra.length == 3){
						//取查询条件的值
						result = (String)request.getParameter("hid_"+arra[0]);
						//字符串
						if("0".equals(arra[2]) && !StringUtil.isEmpty(arra[0]) && !StringUtil.isEmpty(result)){
							sqlCondition.append(" and " );
							//等于
							if("0".equals(arra[1])){
								sqlCondition.append(arra[0] + " = ");
								sqlCondition.append("'" + result.trim() + "'" ); 
							}
							//左匹配
							else if("1".equals(arra[1])){ 
								sqlCondition.append( arra[0]); 
								sqlCondition.append( " like '%");
								sqlCondition.append(result.trim() ); 
								sqlCondition.append("'"); 
							}
							//右匹配
							else if("2".equals(arra[1])){ 
								sqlCondition.append( arra[0]); 
								sqlCondition.append( " like '");
								sqlCondition.append(result.trim() ); 
								sqlCondition.append("%'"); 
							} 
							//模糊匹配
							else if("3".equals(arra[1])){  
								sqlCondition.append( arra[0]); 
								sqlCondition.append( " like '%");
								sqlCondition.append(result.trim() ); 
								sqlCondition.append("%'"); 
							} 
						}
						//日期
						else if("2".equals(arra[2])){
							result = (String)request.getParameter("hid_"+arra[0]+"START");
							String end = (String)request.getParameter("hid_"+arra[0]+"END"); 
							//大于
							if(!StringUtil.isEmpty(result)){
								sqlCondition.append(" and " );
								sqlCondition.append(arra[0] + " >= '");
								sqlCondition.append(result.trim()+"'"); 
							}
							//小于
							if(!StringUtil.isEmpty(end)){
								sqlCondition.append(" and " );
								sqlCondition.append(" '" +end+"'"); 
								sqlCondition.append(" >=" + arra[0] );
							}
						}
						//数字
						else if(!StringUtil.isEmpty(arra[0])){
							result = (String)request.getParameter("hid_"+arra[0]+"START");
							String end = (String)request.getParameter("hid_"+arra[0]+"END"); 
							//大于
							if(!StringUtil.isEmpty(result)){
								sqlCondition.append(" and " );
								sqlCondition.append(" " + arra[0] + " >= ");
								sqlCondition.append(" " + result.trim() + " "); 
							}
							//小于
							if(!StringUtil.isEmpty(end)){
								sqlCondition.append(" and " );
								sqlCondition.append(" " +  end + " "); 
								sqlCondition.append(" >= " + arra[0] );
							}
						}
					}
				}
			}
		}
		
		//调用接口，查询最终数据 
		boolean havaDate = true;
		Map<Object,Object> rstMap = null;
		params.put("sqlCondition", sqlCondition.toString());
		params.put("hidRptPk", hidRptPk);
		String rst = "";
		String rstColName = "";
		if(suSb.toString().length()>0 && colName.toString().length()>0){
			rst = suSb.toString().substring(0,suSb.toString().length()-1);
			rstColName = colName.toString().substring(0,colName.toString().length()-1);
		}
		params.put("rst", rst);
		
		//导出对应的字段(隐藏哉)
		sb.append("<input type=\"hidden\" name = \"hidColSql\" id = \"hidColSql\" value = \"" + rst + "\"/>"); 
		sb.append("<input type=\"hidden\" name = \"hidColSqlName\" id = \"hidColSqlName\" value = \"" + rstColName + "\"/>"); 
		
		//SQL
		CustomQueryBean mainBean = custQueryService.queryForByRptPk(params);
		StringBuffer sqlSb = new StringBuffer();
		
		sqlSb.append("SELECT "+ (String)params.get("rst") +" FROM (");
		sqlSb.append(mainBean.getRptSql());
		sqlSb.append(") ");
		sqlSb.append((String)params.get("sqlCondition"));
		sb.append("<input type=\"hidden\" name = \"hidDisplaySql\" id = \"hidDisplaySql\" value = \"" + sqlSb.toString() + "\"/>");  
		request.getSession().setAttribute(request.getSession().getId(), sqlSb.toString());
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1); 
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage dispList = null;
		
		//查询操作//必须存在显示列 
		if("1".equals((String)request.getParameter("queryType")) && !StringUtil.isEmpty((String)params.get("rst"))){
			//转换
			String sql = replaceRptSql(sqlSb.toString(),request);
			params.put("displaySql", sql);
			dispList = custQueryService.displayRptPageQuery(params, pageNo);
		}
		if(dispList != null && dispList.getResult() != null){
			List<HashMap<String,String>> lst = (ArrayList<HashMap<String,String>>)dispList.getResult();
			for(HashMap<String,String> listMap:lst){
				rstMap = new HashMap<Object,Object>();
				sb.append("<tr class=\"list_row${r}\" onmouseover=\"mover(this)\" onmouseout=\"mout(this)\"  onclick=\"setSelEid(document.getElementById('eid${rr}'));\">");
				havaDate = false;
				Iterator iter = listMap.entrySet().iterator(); 
				//遍历报表SQL字段
				while(iter.hasNext()){ 
					Map.Entry entry = (Map.Entry) iter.next(); 
					if(!"ROWNUM_".equals(entry.getKey())){
						rstMap.put((Object)entry.getKey(), (Object)entry.getValue());
					}
				}
				for(CustomQueryDtlBean qryList:list){
					sb.append("<td nowrap align=\"left\">&nbsp;");
					sb.append(StringUtil.nullToSring(rstMap.get(qryList.getPhysicName())));
					sb.append("</td>");
				}
				sb.append("</tr>");
			}
		}
		
		sb.append(" </c:forEach> ");
		//如果没有数据则显示没有数据
		if(havaDate)
			sb.append("<c:if test=\"${empty page.result}\"> <tr> <td height=\"25\" colspan=\"13\" align=\"center\" class=\"lst_empty\"> &nbsp;无相关信息或无显示列&nbsp; </td> </tr> </c:if>");
		//结果集中间部分结束 	 
		
		//生成按钮事件
		sb.append("<script type=\"text/javascript\">");
		//查询按钮
		sb.append(" function doQuery(){");
		sb.append("	if(!FormValidate(\"queryForm\")){return false; }");
		sb.append("	$(\"#queryType\").val(\"1\");"); 
		sb.append("	$(\"#queryForm\").submit();"); 
		sb.append(" }");
		//导出按钮
		StringBuffer para = new StringBuffer();
		para.append("&hidColSql=" + rst);
		para.append("&hidColSqlName=" + rstColName);
		para.append("&hidDisplaySql=" + sqlSb.toString());
		
		sb.append(" function doExport(){");
		sb.append("	var url = \"customQuery.shtml?action=rptExport");
		sb.append(para.toString());
		sb.append("\"; window.location=url;");
		sb.append(" }");
		
		sb.append(" function doPrint(){");
		sb.append("	$(\"#queryArea\").css('display','none');");
		sb.append("	$(\"#buttonDiv\").css('display','none');");
		sb.append("	$(\"#pageTr\").css('display','none');");
		sb.append("  window.print();");
		sb.append(" }");
		
		//重置按钮
		sb.append(" function doReset(){");
			sb.append(" $(\"input[name^=hid_]\").each(function(){");
				sb.append(" this.value = \"\";");
			sb.append(" }); ");
		sb.append(" }");  
		
		sb.append(" function doBack(){");
		sb.append(" var url = \"\";");
		
		//报表展示页面
		if("1".equals((String)request.getParameter("currentPageNo"))){
			sb.append("	url = \"customQuery.shtml?action=queryListDisplay\"; window.location=url;");
		}
		//报表配置页面
		else{
			sb.append("	url = \"customQuery.shtml?action=queryList\"; window.location=url;");
		}
		sb.append(" }");
		
		//自动校验
		sb.append("$(function(){ InitFormValidator(0); }); ");
		sb.append("</script>");
    	request.setAttribute("page", dispList);
    	return sb.toString();
    }
    
    
    /**
     * 保存自定义报表各数据项
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward saveRptDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) { 
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		//获取json数据
		String jsonData = ParamUtil.get(request, "jsonData");
    	//获取自定义报表明细项数据
		List<CustomQueryDtlBean> listQuestionModel = new Gson().fromJson(jsonData, new TypeToken<List<CustomQueryDtlBean>>(){}.getType());
		
		//自定义报表主键
		String hidRptPk = request.getParameter("hidRptPk"); 
		Map<String,String> map = new HashMap<String,String>();
		map.put("hidRptPk", hidRptPk);
		
		try{
			custQueryService.txSaveRptDtlDate(map, listQuestionModel);
			jsonResult = jsonResult(true, "");  
		}catch(Exception e){
			logger.info(e);
			jsonResult = jsonResult(false, "保存失败"); 
		}
    	//返回
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return mapping.findForward("nextInsertEdit");
	}
    
    /**
     * 重新初始化最后一个页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward loadRptDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表主键
    	ParamUtil.putStr2Map(request, "hidRptPk", params);  
    	//报表SQL
    	ParamUtil.putStr2Map(request, "rptSql", params);
    	ParamUtil.putStr2Map(request, "messageInfo", params);
    	
    	//初始化当前页面的数据
    	params.put("init", "初始化");
    	List<CustomQueryDtlBean> list = custQueryService.queryRptQueryDtlByPk(params);
    	//报表主键
    	request.setAttribute("hidRptPk", (String)params.get("hidRptPk"));
    	request.setAttribute("rptSql", (String)params.get("rptSql"));
    	request.setAttribute("messageInfo", (String)params.get("messageInfo"));
    	//返回结果集
    	request.setAttribute("page", list);
        return mapping.findForward("nextInsertEdit");
    }
    
    /**
     * 导出自定义报表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @SuppressWarnings("unchecked")
	public void rptExport (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//导出SQL
		String displaySql = (String)request.getSession().getAttribute(request.getSession().getId());//(String)request.getParameter("hidDisplaySql"); 
		//对应字段
		String colSql = (String)request.getParameter("hidColSql"); 
		//对应字段中文名称
		String colSqlName = new String(ParamUtil.utf8Decoder(request,"hidColSqlName").getBytes("ISO-8859-1"),"utf-8");
		try{
			Map<String, String> params = new HashMap<String,String>();
			params.put("displaySql", replaceRptSql(displaySql,request));
		 
			List<Map> rst=custQueryService.qryExpData(params);
			
			ExcelUtil.doExport(response,rst,"自定义查询报表",colSql,colSqlName); 
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    /**
     * 自定义数据修改列表页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditTableList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//表名
    	ParamUtil.putStr2Map(request, "tableName", params);
    	//查询条件
    	ParamUtil.putStr2Map(request, "whereSql", params);
    	
    	request.setAttribute("tableName", params.get("tableName"));
    	request.setAttribute("whereSql",  params.get("whereSql"));
    	
    	String tableName = request.getParameter("tableName");
    	
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	
    	IListPage list = null;
    	String whereSql= StringUtil.nullToSring(params.get("whereSql"));
    	//解析表名
		String[] arr = null;
    	if(StringUtil.isEmpty(whereSql)){
    		request.setAttribute("hidTableName", (String)request.getParameter("tableName"));
    	}else{
    		arr = whereSql.toUpperCase().split(" ");
    		StringBuffer sp = new StringBuffer(); 
    		if(arr!=null && arr.length>0){
    			for(int i=0;i<arr.length;i++){
    				if(!"WHERE".equals(arr[i]))
    					sp.append("'"+arr[i]+"',");
    				else 
    					break;
    			}
    			params.put("tableNames", (sp.toString()).substring(0,(sp.toString()).length()-1));
    		}
    		tableName = custQueryService.getTableNameFromWhereSql(params);
    		request.setAttribute("hidTableName", tableName);
    		params.put("tableName", tableName); 
    	}
    	
    	
    	if(!StringUtil.isEmpty(tableName)){
    		//调用查询接口
    		list = custQueryService.queryTable(params,pageNo);
        	if(list!=null){
	        	String sb = createTableEditListHtml(list,request);
	        	
	        	request.setAttribute("listHtml", sb);
	        	request.setAttribute("page", list);
        	}else{
        		request.setAttribute("messageInfo", "error");
        	}
    	}
        return mapping.findForward("toEditTableList");
    } 
    
    /**
     * 自定义数据修改详细页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toTableDateDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//定义查询Map
    	Map<String,String> params = new HashMap<String,String>();
    	//报表主键
    	ParamUtil.putStr2Map(request, "hidRptPk", params);   
        return mapping.findForward("toTableDateDtl");
    } 
    
    /**
     * Replace rpt sql.
     * 
     * @param rptSql the rpt sql
     * @param request the request
     * 
     * @return the string
     */
    private String replaceRptSql(String rptSql,HttpServletRequest request){ 
    	UserBean userBean = ParamUtil.getUserBean(request);
    	//获取当前用户
    	rptSql = rptSql.replaceAll("@USERID@", "'"+userBean.getXTYHID()+"'");
		rptSql = rptSql.replaceAll("@ORGANIDS@", "'"+userBean.getJGXXIDS()+"'");
		rptSql = rptSql.replaceAll("@DEPTIDS@", "'"+userBean.getBMXXIDS()+"'");
		rptSql = rptSql.replaceAll("@DEPTID@", "('"+userBean.getBMXXID()+"')");
		rptSql = rptSql.replaceAll("@ORGANID@", "('"+userBean.getJGXXID()+"')"); 
		
    	return rptSql.toString();
    }
    
    
    /**
     * Save table data.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward saveTableData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	StringBuffer sb = null;
    	Map<String,String> map = new HashMap<String,String>();
		ArrayList<Map<String,String>> updateList = new ArrayList<Map<String,String>>();
    	String colName = request.getParameter("colName");
    	String prekey = request.getParameter("prekey");
    	String tableName = request.getParameter("hidTableName"); 
    	int count = 0;
    	int rowCount = 0;
    	if(!StringUtil.isEmpty(colName)){
    		String[] arr = colName.split(",");
    		if(arr!=null && arr.length>0){
    			for(int i=0;i<arr.length;i++){ 
    				 String[] arrValue = request.getParameterValues("hidRst_"+arr[i]);
    				 if(arrValue!=null && arrValue.length>0){
    					 for(int j=0;j<arrValue.length;j++){
    	    				 map.put("hidRst_"+arr[i]+j, arrValue[j]);
    					 }
	    				 rowCount = arrValue.length;
    				 }
    				 count++; 
    			}
    		}
    		Map<String,String> updateMap= null; 
    		String sql = "";
        	if(rowCount!=0){
        		String[] pk = request.getParameterValues("hidRst_prekey");
        		for(int i=0;i<rowCount;i++){
            		sb = new StringBuffer();
        			if(count!=0){
        				sb.append("update "+tableName+" set ");
        				for(int j=0;j<count;j++){
        					if(!arr[j].equals("hidRst_prekey"))
        						sb.append(arr[j] + " = '" +StringUtil.nullToSring(map.get("hidRst_"+arr[j]+i)) + "', ");
        				}
        			}
        			sql = (sb.toString()).substring(0,(sb.toString()).length()-2);
        			sql += " where 1=1 and "+prekey +"='" + pk[i] +"'";
        			if(!StringUtil.isEmpty(pk[i])){
        				updateMap = new HashMap<String,String>();
        				updateMap.put("upateTableData", sql);
        				updateList.add(updateMap);
        			}
        		}
        	}
    	}
    	
    	if(updateList!=null){
    		boolean flag = custQueryService.updateTableData(updateList);
    		if(flag){
    			request.setAttribute("messageInfo", "sucess");
    		}else{
    			request.setAttribute("messageInfo", "fail");
    		}
    	}
    	
    	request.setAttribute("tableName", request.getParameter("tableName"));
    	return toEditTableList(mapping,form,request,response);
    }
    
    /**
     * 根据结果生成结果集条件HTML.
     * 
     * @param resultlist the resultlist
     * @param request the request
     * 
     * @return the string
     */
    @SuppressWarnings("unchecked")
	private String createTableEditListHtml(IListPage resultlist,HttpServletRequest request){
		
    	StringBuffer sb = new StringBuffer(); 
    	StringBuffer tp = new StringBuffer(); 
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("tableName", (String)request.getParameter("tableName"));
    	map.put("whereSql", (String)request.getParameter("whereSql"));
    	
    	Map<String,String> colMap = new HashMap<String,String>();
    	String colName = "";
    	String colLength = "";
		String whereSql = (String)request.getParameter("whereSql");
    	//解析表名
		String[] arr = null;
    	if(StringUtil.isEmpty(whereSql)){
    		request.setAttribute("hidTableName", (String)request.getParameter("tableName"));
    	}else{
    		arr = whereSql.toUpperCase().split(" ");
    		StringBuffer sp = new StringBuffer();
    		String tableName = "";
    		if(arr!=null && arr.length>0){
    			for(int i=0;i<arr.length;i++){
    				if(!"WHERE".equals(arr[i]))
    					sp.append("'"+arr[i]+"',");
    				else
    					break;
    			}
    			map.put("tableNames", (sp.toString()).substring(0,(sp.toString()).length()-1));
    		}
    		tableName = custQueryService.getTableNameFromWhereSql(map);
    		request.setAttribute("hidTableName", tableName);
    		map.put("tableName", tableName);
    	}
    	
		//获取字段长度
    	ArrayList<HashMap<String,String>> list = custQueryService.queryTableColLength(map);
    	if(list != null){ 
			for(HashMap<String,String> listMap:list){  
				Iterator iter = listMap.entrySet().iterator(); 
				//遍历报表SQL字段
				while(iter.hasNext()){ 
					Map.Entry entry = (Map.Entry) iter.next(); 
					if(!"ROWNUM_".equals(entry.getKey())){
						if("COL_NAME".equals(entry.getKey())){
							colName =  StringUtil.nullToSring(entry.getValue());
						}
						if("COL_LENGTH".equals(entry.getKey())){
							colLength = StringUtil.nullToSring(entry.getValue());
						}
					}
				} 
				colMap.put(colName, colLength);
			}
		}

    	String prekey = custQueryService.getTablePrimaryKery(map);
    	request.setAttribute("prekey", prekey);
    	
    	//获取数据库可编辑字段
    	HashMap<String, String> editMap = custQueryService.enanbleEditTableColumn(map);
    	if(!StringUtil.isEmpty(prekey)){
			IListPage dispList = resultlist; 
			//表头部分开始
			sb.append("<tr class=\"fixedRow\">");
			//查询列信息 
			if(dispList != null && dispList.getResult() != null){
				List<HashMap<String,String>> lst = (ArrayList<HashMap<String,String>>)dispList.getResult();
				for(HashMap<String,String> listMap:lst){  
					Iterator iter = listMap.entrySet().iterator(); 
					//遍历报表SQL字段
					while(iter.hasNext()){ 
						Map.Entry entry = (Map.Entry) iter.next(); 
						if(!"ROWNUM_".equals(entry.getKey()) && !prekey.equals(entry.getKey())&&!StringUtil.isEmpty(editMap.get(entry.getKey()))){ 
							sb.append("<th nowrap align=\"center\" >");
							sb.append(entry.getKey());
							sb.append("</th>");
							tp.append(entry.getKey());
							tp.append(",");
						}
					} 
					break;
				}
			}
			request.setAttribute("colName", tp.toString());
			sb.append("</tr>");
			//表头部分结束
		
			//处理具体列信息
			sb.append("<c:forEach items=\"${page.result}\" var=\"rst\" varStatus=\"row\"> <c:set var=\"r\" value=\"${row.count % 2}\"></c:set> <c:set var=\"rr\" value=\"${row.count}\"></c:set>");
			//循环具体数据
			boolean havaDate = true;
			if(dispList != null && dispList.getResult() != null){
				List<HashMap<String,String>> lst = (ArrayList<HashMap<String,String>>)dispList.getResult();
				for(HashMap<String,String> listMap:lst){ 
					sb.append("<tr class=\"list_row${r}\" onmouseover=\"mover(this)\" onmouseout=\"mout(this)\"  onclick=\"setSelEid(document.getElementById('eid${rr}'));\">");
					havaDate = false;
					Iterator iter = listMap.entrySet().iterator(); 
					//遍历报表SQL字段
					while(iter.hasNext()){ 
						Map.Entry entry = (Map.Entry) iter.next(); 
						if(!"ROWNUM_".equals(entry.getKey())){
							if(!prekey.equals(entry.getKey())){
								if(!StringUtil.isEmpty(editMap.get(entry.getKey())))
									sb.append("<td><input type=\"text\" size=\"6\" name = \"hidRst_" + entry.getKey() +"\" value =\""+StringUtil.nullToSring((Object)entry.getValue())+"\" maxlength=\""+colMap.get(entry.getKey())+"\"/></td>");
							}else{
								sb.append("<input type=\"hidden\" size=\"6\" name=\"hidRst_prekey\" value =\""+StringUtil.nullToSring((Object)entry.getValue())+"\" />");
							}
						}
					}
					sb.append("</tr>");
				}
			}
			
			sb.append(" </c:forEach> ");
			//如果没有数据则显示没有数据
			if(havaDate)
				sb.append("<c:if test=\"${empty page.result}\"> <tr> <td height=\"25\" colspan=\"13\" align=\"center\" class=\"lst_empty\"> &nbsp;无相关信息&nbsp; </td> </tr> </c:if>");
    	}else{
    		request.setAttribute("messageInfo", "key");
    	}
		//结果集中间部分结束 
    	return sb.toString();
    }
    
    //add by zhuxw 2014-08-06 通用导出
    public ActionForward getCommExportRptInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {   
    	PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
    	try{ 
    		
    		String rpt_Id = StringUtil.nullToSring(request.getParameter("rpt_Id"));
    		List<Map> rptCols = custQueryService.getRptCol(rpt_Id);
    	    StringBuffer  aBuf_logic=new StringBuffer("");
    		StringBuffer  aBuf_pysic=new StringBuffer("");
    		int i=0;
    	    for( Map tmp :rptCols )
    	    {
    	    	if(i!=0)
    	    	{
    	    		aBuf_logic.append(",");
        	    	aBuf_pysic.append(",");
    	    	}
    	    	aBuf_logic.append(tmp.get("LOGICNAME"));
    	    	aBuf_pysic.append(tmp.get("PHYSICNAME"));
    	    	i++;
    	    }
    	    Map<String,String> rptColMap = new HashMap<String,String>();
    	    rptColMap.put("LOGICNAME", aBuf_logic.toString());
    	    rptColMap.put("PHYSICNAME", aBuf_pysic.toString());
    	    jsonResult = new Gson().toJson(new Result(true, rptColMap, ""));

		}catch(Exception e){
    		logger.info(e); 
    		jsonResult = jsonResult(false, "操作异常");
    	} 
    	
    	if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		} 
    	
    	return null;
	}
    public void commExportDataExl (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	
		//对应字段
		String colSql = (String)request.getParameter("expCols");
		String rpt_Id = StringUtil.nullToSring(request.getParameter("rpt_Id"));
		List<Map> rptCols = custQueryService.getRptCol(rpt_Id);
	    StringBuffer  aBuf_logic=new StringBuffer("");
		StringBuffer  aBuf_pysic=new StringBuffer("");
		int i=0;
	    for( Map tmp :rptCols )
	    {
	    	if(i!=0)
	    	{
	    		aBuf_logic.append(",");
    	    	aBuf_pysic.append(",");
	    	}
	    	aBuf_logic.append(tmp.get("LOGICNAME"));
	    	aBuf_pysic.append(tmp.get("PHYSICNAME"));
	    	i++;
	    }
	    String tmpContent="XTYHID,YHM";
	    String tmpContentCn="系统用户ID,用户名";
	    String [] BILL_TYPE={"string","string"};
	    String secPath="sample";
	    String replaceRptSql="select XTYHID,YHM from T_SYS_XYYH";
	    String sheetName="thesheet";
	    String fileKeyName="";
		try{
			Map<String, String> params = new HashMap<String,String>();
			params.put("displaySql", replaceRptSql);
		    List<Map> outValue=custQueryService.qryExpData(params);
			
			ExcelUtil.doExport( response,outValue,sheetName, tmpContent, tmpContentCn) ;		
			
			//ExcelUtil.doExport(response,rst,"自定义查询报表",colSql,colSqlName); 
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
}
