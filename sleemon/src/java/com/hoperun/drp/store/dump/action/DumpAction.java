/**
 * prjName:喜临门营销平台
 * ucName:转储单
 * fileName:DumpAction
*/
package com.hoperun.drp.store.dump.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.Logger;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.store.dump.model.DumpModel;
import com.hoperun.drp.store.dump.model.DumpModelChld;
import com.hoperun.drp.store.dump.service.DumpService;
/**
 * *@serviceImpl
 * *@func转储
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:34
 */
public class DumpAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(DumpAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0030901";
    private static String PVG_EDIT="FX0030902";
    private static String PVG_DELETE="FX0030903";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0030904";
    private static String PVG_TRACE="FX0030905";
    //审核模块
    private static String PVG_BWS_AUDIT="FX0031001";
    private static String PVG_AUDIT_AUDIT="FX0031002";
    private static String PVG_TRACE_AUDIT="FX0031003";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_DUMP";
    private static String AUD_TAB_KEY="DUMP_ID";
	private static String AUD_BUSS_STATE="u.STATE";
    private static String AUD_BUSS_TYPE="DRP_DUMP_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.store.dump.service.DumpFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private DumpService dumpService;
    /**
	 * Sets the Dump service.
	 * 
	 * @param DumpService the new Dump service
	 */
	public void setDumpService(DumpService dumpService) {
		this.dumpService = dumpService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "DUMP_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "DUMP_OUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "DUMP_OUT_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "DUMP_IN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "DUMP_IN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "DUMP_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "DUMP_DATE_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = dumpService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("module", module);
		request.setAttribute("pvg",setPvg(userBean));
		
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("ZTXXID",userBean.getLoginZTXXID());
		request.setAttribute("page", page);
		request.setAttribute("search", search);
		return mapping.findForward("list");
	}
	
	 /**
     * * 明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String DUMP_ID =ParamUtil.get(request, "DUMP_ID");
        if(!StringUtil.isEmpty(DUMP_ID))
        {
        	 List <DumpModelChld> result = dumpService.childQuery(DUMP_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
    }
	
	 /**
     * *
     * * to 编辑框架页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        //设置跳转时需要的一些公用属性
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("editFrame");
    }
	
	/**
	 * * to 主表编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String DUMP_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(DUMP_ID)){
			entry = dumpService.load(DUMP_ID);
			String MX_IDS = this.dumpService.queryReltDtls(DUMP_ID);
			request.setAttribute("MX_IDS", MX_IDS);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", false);
		}else{
			entry.put("BILL_TYPE", BusinessConsts.DUMP_DEFAULT_TYPE);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", true);
		}
		
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		 
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("toedit");
	}
	
	/**
     * * 编辑框架页面加载子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String DUMP_ID = ParamUtil.get(request, "DUMP_ID");
        if(!StringUtil.isEmpty(DUMP_ID))
        {
        	 List <DumpModelChld> result = dumpService.childQuery(DUMP_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("childedit");
    }
    
    
    
	/**
     * *选择关联单据的时候 自动加载明细
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public void modifyReltToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			 String RELT_BILL_NO = ParamUtil.get(request, "RELT_BILL_NO");
	         if(!StringUtil.isEmpty(RELT_BILL_NO)){
	        	 List <Map<String,String>> result = dumpService.childReltQuery(RELT_BILL_NO);
	        	 GsonBuilder gsonbuilder = new GsonBuilder().serializeNulls();
	        	 jsonResult = gsonbuilder.create().toJson(new Result(true, result, "成功"));
//	        	 jsonResult = new Gson().toJson(new Result(true, result, "成功"));
	        	 System.out.println(jsonResult);
	         }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	 /**
     * * to 直接编辑明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String DUMP_DTL_IDs = request.getParameter("DUMP_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(DUMP_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("DUMP_DTL_IDS",DUMP_DTL_IDs);
          List <DumpModelChld> list = dumpService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("childedit");
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
            String DUMP_ID = ParamUtil.get(request, "DUMP_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            DumpModel model = new Gson().fromJson(parentJsonData, new TypeToken <DumpModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <DumpModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <DumpModelChld>>() {
                }.getType());
            }
            dumpService.txEdit(DUMP_ID, model, chldModelList, userBean);
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
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String DUMP_ID = request.getParameter("DUMP_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <DumpModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <DumpModelChld>>() {
                }.getType());
                dumpService.txChildEdit(DUMP_ID, modelList,"list");
            }
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
	 * * 主表 删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String DUMP_ID = ParamUtil.get(request, "DUMP_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("DUMP_ID", DUMP_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
			dumpService.txDelete(params);
			dumpService.clearCaches(params);
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	 /**
     * * 明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String DUMP_DTL_IDs = request.getParameter("DUMP_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            dumpService.txBatch4DeleteChild(DUMP_DTL_IDs);
		} catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String DUMP_ID = ParamUtil.get(request, "DUMP_ID");
		if(!StringUtil.isEmpty(DUMP_ID)){
			Map<String,String> entry = dumpService.load(DUMP_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        String DUMP_ID = request.getParameter("DUMP_ID");
        try {
        	Map<String,String> model = dumpService.load(DUMP_ID);
        	String STATE = StringUtil.nullToSring(model.get("STATE"));
        	if("提交".equals(STATE) || "审核通过".equals(STATE)){
        		errorId="2";
        		throw new Exception("该单据已经提交,请刷新页面!"); 
        	}
        	
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				errorId="2";
				throw new Exception("处于月结阶段,不能做出库!");
			}
			
			isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),model.get("DUMP_DATE"));
			if(isMonthAcc){
				errorId="2";
				throw new Exception("转储日期"+model.get("DUMP_DATE")+"已月结,不能做出库!");
			}
            List <DumpModelChld> list = dumpService.childQuery(DUMP_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception("没有明细，不能提交");
            }
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("2".equals(errorId)){
				jsonResult = jsonResult(false, e.getMessage());
			}  else {
                jsonResult = jsonResult(false, "提交失败");
            }
           e.printStackTrace();
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
	 public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	UserBean userBean = ParamUtil.getUserBean(request);
		 	 String DUMP_ID = request.getParameter("DUMP_ID");
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_DUMP");
		 	printMap.put("BUSS_ID", DUMP_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
		 	StringBuffer paramSql=new StringBuffer();
		 	paramSql.append("dumpPrint.raq&DUMP_ID=").append(DUMP_ID).append("&arg1=").append(userBean.getXM());;
		 	/**
			 StringBuffer params = new StringBuffer();
			 params.append("rptSql=select rownum, temp.* from (")
			 		.append("select ")
			 		.append("a.DUMP_OUT_STORE_NAME,")
			 		.append("a.DUMP_IN_STORE_NAME,")
			 		.append("a.DUMP_NO,")
			 		.append("to_char(a.CRE_TIME, 'yyyy-mm-dd') CRE_TIME,")
			 		.append("a.REMARK,")
			 		.append("b.PRD_NO,")
			 		.append("b.PRD_NAME,")
			 		.append("(select '").append(userBean.getXM()).append("' from dual) LOGNAME,")
			 		.append("b.PRD_SPEC,")
			 		.append("b.STD_UNIT,")
			 		.append("to_char(a.DUMP_DATE,'yyyy-MM-DD')DUMP_DATE,")
			 		.append("b.DUMP_NUM,")
			 		.append("a.CRE_NAME,")
			 		.append("a.RELT_BILL_NO,")
			 		.append("to_char(sysdate,'yyyy-mm-dd') GETDATE,")
			 		.append("(case when c.SPCL_DTL_REMARK is null then b.REMARK else  b.REMARK || '(' || c.SPCL_DTL_REMARK || ')' end) REMARKDTL")
			 		.append(" from DRP_DUMP a left join DRP_DUMP_DTL b on  a.DUMP_ID = b.DUMP_ID and b.DEL_FLAG = 0  ")
			 		.append(" left join DRP_SPCL_TECH c on c.SPCL_TECH_ID = b.SPCL_TECH_ID and USE_FLAG=1 ")
			 		.append(" where ")
			 		.append(" a.DUMP_ID ='").append(DUMP_ID).append("'")
			 		.append(" order by PRD_NO asc) temp ");
			 int count=dumpService.getDtlCount(DUMP_ID);
			 int pageSize=7;
			 double page=0;
			 if(count%pageSize!=0){
				 page=Math.ceil((double)count/pageSize);
			 }
			 double newCount=page*pageSize;
			 for (int i = 0; i < (newCount-count); i++) {
				 params.append(" union all select ").append(count+i+1).append(",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual ");
			}
			 // 报表路径名称
			 request.setAttribute("reportFileName","dumpPrint.raq");
			**/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			
			 return mapping.findForward("flashReportPrint");
	}
	 
	 
	 
	// 导出
		@SuppressWarnings("unchecked")
		public void expertExcel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();
			ParamUtil.putStr2Map(request, "DUMP_NO", params);
		    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		    ParamUtil.putStr2Map(request, "DUMP_OUT_STORE_NO", params);
		    ParamUtil.putStr2Map(request, "DUMP_OUT_STORE_NAME", params);
		    ParamUtil.putStr2Map(request, "DUMP_IN_STORE_NO", params);
		    ParamUtil.putStr2Map(request, "DUMP_IN_STORE_NAME", params);
		    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	        ParamUtil.putStr2Map(request, "DUMP_DATE_BEG", params);
	        ParamUtil.putStr2Map(request, "DUMP_DATE_END", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
	        //只查询0的记录。1为删除。0为正常
	        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			//权限级别和审批流的封装
			StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,"u.DUMP_ID", AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.getStrQX("u",strQx.toString()));
			params.put("QXJBCON",sb.toString());
 			//字段排序
			ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		 
			List list = dumpService.expertExcelQuery(params);
			// excel数据上列显示的列明
			String tmpContentCn ="转储单编号,单据类型,转储日期,转出库房编号,转出库房名称,转入库房编号,转入库房名称,制单时间,状态," +
					"货品编号,货品名称,规格型号,品牌,特殊规格说明,转储数量," +
					"备注";
			String tmpContent = "DUMP_NO,BILL_TYPE,DUMP_DATE,DUMP_OUT_STORE_NO,DUMP_OUT_STORE_NAME,DUMP_IN_STORE_NO,DUMP_IN_STORE_NAME,CRE_TIME,STATE," +
					"PRD_NO,PRD_NAME,PRD_SPEC,BRAND,SPCL_DTL_REMARK,DUMP_NUM," +
					"ROW_REMARK";
			String colType= "string,string,string,string,string,string,string,string,string," +
						   "string,string,string,string,string,number," +
						   "string";
			try {
				ExcelUtil
						.doExport(response, list, "转储单", tmpContent, tmpContentCn,colType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	 
}