/**
d * prjName:喜临门营销平台
 * ucName:盘点单维护
 * fileName:InventoryAction
*/
package com.hoperun.drp.store.inventory.action;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.inventory.model.InventoryModel;
import com.hoperun.drp.store.inventory.model.InventoryModelChld;
import com.hoperun.drp.store.inventory.service.InventoryService;
import com.hoperun.sys.model.UserBean;



/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-07 09:54:59
 */
public class InventoryAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(InventoryAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0031101";
    private static String PVG_EDIT="FX0031102";
    private static String PVG_DELETE="FX0031103";
    private static String PVG_BEGIN_CHECK="FX0031106";//开始盘点
    private static String PVG_UPLOADING="FX0031107";//盘点导入
    private static String PVG_DOWNLOAD="FX0031108";//盘点导出
    private static String PVG_END_CHECK="FX0031109";//盘点结束
    private static String PVG_INI_UP ="FX0031110";//初始化盘点
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0031104";
    private static String PVG_TRACE="FX0031105";
    //审核模块
    //查看
    private static String PVG_BWS_AUDIT="FX0031501";
    //提交
    private static String PVG_AUDIT_AUDIT="FX0031502";
    //流程跟踪
    private static String PVG_TRACE_AUDIT="FX0031503";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_PRD_INV";
    private static String AUD_TAB_KEY="PRD_INV_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_PRD_INV_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.store.inventory.service.impl.InventoryFlowInterface";
	/**审批 end**/
    /** 业务service*/
	private InventoryService inventoryService;
    /**
	 * Sets the Inventory service.
	 * 
	 * @param InventoryService the new Inventory service
	 */
	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
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
	    ParamUtil.putStr2Map(request, "PRD_INV_NO", params);
	    ParamUtil.putStr2Map(request, "INV_TYPE", params);
	    ParamUtil.putStr2Map(request, "STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "INV_RANGE", params);
	    ParamUtil.putStr2Map(request, "STORG_NO", params);
	    ParamUtil.putStr2Map(request, "STORG_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    if(module.equals("wh")&&StringUtil.isEmpty(search)){
	    	//-- 0156210--Start--刘曰刚--2014-06-16//
	    	//页面初始化查询条件新增否决状态
	    	params.put("whSTATE", "'未提交','开始盘点','结束盘点','否决'");
	    	//-- 0156210--End--刘曰刚--2014-06-16//
	    }
	    if(module.equals("sh")&&StringUtil.isEmpty(search)){
	    	params.put("shSTATE", BusinessConsts.COMMIT);
	    }
	    if(!StringUtil.isEmpty(request.getParameter("STATE"))){
	    	ParamUtil.putStr2Map(request, "STATE", params);
	    }
	    search="true";
	    String Sql=ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean);
	    params.put("QXJBCON", Sql);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = inventoryService.pageQuery(params, pageNo);
		//判断页面路径显示审核还是
    	params.put("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("ZTXXID",userBean.getLoginZTXXID());
		
		
		request.setAttribute("page", page);
		request.setAttribute("sql", Sql);
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
        String PRD_INV_ID =ParamUtil.get(request, "PRD_INV_ID");
        if(!StringUtil.isEmpty(PRD_INV_ID))
        {
        	Map<String,String> map=new HashMap<String, String>();
        	int pageSize=ParamUtil.getInt(request, "pageSize",0);
        	ParamUtil.putStr2Map(request, "pageSize", map);
        	if(StringUtil.isEmpty(request.getParameter("pageSize")))
    		{
        		pageSize=200;
    			map.put("pageSize", "200");
    		}
        	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        	map.put("PRD_INV_ID", PRD_INV_ID);
        	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        	IListPage page  = inventoryService.pageQueryChld(map, pageNo);
        	Map invNum=inventoryService.getAllNum(map);
        	String html=getHtml(map, pageNo,pageSize);
            request.setAttribute("page", page);
            request.setAttribute("invNum", invNum);
            request.setAttribute("html", html);
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
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
		String PRD_INV_ID = ParamUtil.get(request, "PRD_INV_ID");
		if(!StringUtil.isEmpty(PRD_INV_ID)){
			Map<String,String> entry = inventoryService.load(PRD_INV_ID);
			request.setAttribute("rst", entry);
		}
		
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("ZTXXID",userBean.getLoginZTXXID());
		
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("JGXXID", userBean.getLoginZTXXID());
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_INV_ID = ParamUtil.get(request, "PRD_INV_ID");
        if(!StringUtil.isEmpty(PRD_INV_ID))
        {
        	 List <InventoryModelChld> result = inventoryService.childQuery(PRD_INV_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
        return mapping.findForward("childedit");
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
        String PRD_INV_DTL_IDs = request.getParameter("PRD_INV_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_INV_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_INV_DTL_IDS",PRD_INV_DTL_IDs);
          List <InventoryModelChld> list = inventoryService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
            String PRD_INV_ID = ParamUtil.get(request, "PRD_INV_ID");
            String parentJsonData = ParamUtil.get(request, "jsonData");
            InventoryModel model = new Gson().fromJson(parentJsonData, new TypeToken <InventoryModel>() {}.getType());
//            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <InventoryModelChld> chldModelList = null;
//            if (!StringUtil.isEmpty(jsonDate)) {
//                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <InventoryModelChld>>() {
//                }.getType());
//            }
            inventoryService.txEdit(PRD_INV_ID, model, chldModelList, userBean);
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
            String PRD_INV_ID = request.getParameter("PRD_INV_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <InventoryModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <InventoryModelChld>>() {
                }.getType());
                String INV_RANGE=inventoryService.getINV_RANGE(PRD_INV_ID);
                inventoryService.txChildEdit(PRD_INV_ID, modelList,INV_RANGE,userBean,"list");
            }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
     * * 一栏页面子表 修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEditToList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <InventoryModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <InventoryModelChld>>() {
                }.getType());
                inventoryService.upChld(modelList);
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
			String PRD_INV_ID = ParamUtil.get(request, "PRD_INV_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_INV_ID", PRD_INV_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			inventoryService.txDelete(params);
			inventoryService.clearCaches(params);
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
            String PRD_INV_DTL_IDs = request.getParameter("PRD_INV_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            inventoryService.txBatch4DeleteChild(PRD_INV_DTL_IDs);
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
		String PRD_INV_ID = ParamUtil.get(request, "PRD_INV_ID");
		if(!StringUtil.isEmpty(PRD_INV_ID)){
			Map<String,String> entry = inventoryService.load(PRD_INV_ID);
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
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
        	boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
    		if(isMonthAcc){
    			errorId="2";
    			throw new Exception("处于月结阶段,不能做出库!");
    		}
            String PRD_INV_ID = request.getParameter("PRD_INV_ID");
            int count = inventoryService.chldCountQuery(PRD_INV_ID);
            if (count == 0) {
                errorId = "0";
                throw new Exception();
            }
            //--0157490--Start--刘曰刚--2014-06-25--//
            //验证盘点货品是否重复，如果重复给予提示，按货品id或特殊工艺判断
            String pdtInvMsg=inventoryService.checkRepeat(PRD_INV_ID);
            if(!"true".equals(pdtInvMsg)){
            	throw new ServiceException(pdtInvMsg);
            }
          //--0157490--End--刘曰刚--2014-06-25--//
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
        	logger.info(e);
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("2".equals(errorId)){
				jsonResult = jsonResult(false, e.getMessage());
			} else {
                jsonResult = jsonResult(false, "提交失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    //点击开始盘点按钮，修改盘点状态
    public void toBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BEGIN_CHECK))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String PRD_INV_ID = request.getParameter("PRD_INV_ID");
        	String STORE_ID=request.getParameter("STORE_ID");
        	String INV_TYPE=ParamUtil.utf8Decoder(request, "INV_TYPE");
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("PRD_INV_ID", PRD_INV_ID);
        	map.put("STATE", "开始盘点");
        	map.put("UPDATOR", userBean.getRYXXID());
        	map.put("UPD_NAME", userBean.getXM());
        	map.put("UPD_TIME", "数据库时间");
        	map.put("STORE_ID", STORE_ID);
        	map.put("FREEZE_FLAG", BusinessConsts.DEL_FLAG_DROP);
        	map.put("INV_TYPE", INV_TYPE);
            inventoryService.alterState(map);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "开始盘点失败");
			e.printStackTrace();
		}
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    //点击结束盘点按钮，修改盘点状态
    public void toEnd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BEGIN_CHECK))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String PRD_INV_ID = request.getParameter("PRD_INV_ID");
        	String STORE_ID=request.getParameter("STORE_ID");
        	String INV_TYPE=ParamUtil.utf8Decoder(request, "INV_TYPE");
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("PRD_INV_ID", PRD_INV_ID);
        	map.put("STATE", "结束盘点");
        	map.put("INV_TYPE", INV_TYPE);
        	map.put("UPDATOR", userBean.getRYXXID());
        	map.put("UPD_NAME", userBean.getXM());
        	map.put("UPD_TIME", "数据库时间");
        	map.put("STORE_ID", STORE_ID);
        	map.put("FREEZE_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            inventoryService.alterState(map);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "结束盘点失败");
			e.printStackTrace();
		}
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
     * * to 主表编辑页面根据传递库房，库位，货品id获取货品信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward AccTypeToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String,String> map=new HashMap<String,String>();
        String STORE_ID=request.getParameter("STORE_ID");
        String STORG_ID=request.getParameter("STORG_ID");
        String PRD_IDS=request.getParameter("PRD_IDS");
        map.put("STORE_ID", STORE_ID);
        map.put("STORG_ID", STORG_ID);
        map.put("PRD_IDS", PRD_IDS);
        List <InventoryModelChld> result= inventoryService.accTypeChildQuery(map);
        request.setAttribute("rst", result);
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
        return mapping.findForward("childedit");
    }
    
    /**
     * EXECL导入解析
     * Description :
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    @SuppressWarnings("unchecked")
	public void parseExecl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	UserBean userBean = ParamUtil.getUserBean(request);
        	String serverDir = Properties.getString("UPLOADFILE_DIR");
        	String PRD_INV_ID=request.getParameter("PRD_INV_ID");
        	String type=request.getParameter("type");
        	String fileName= "sample"+ParamUtil.utf8Decoder(request, "fileName");
        	String secPath = Properties.getString("SAMPLE_DIR");
        	String p = serverDir+ File.separatorChar + secPath+ File.separatorChar + fileName;
        	List<Map<String,String>> alist=new ArrayList<Map<String,String>>();
        	String initData="";
        	Map<String,String> map=new HashMap<String,String>();
        	//如果类型为空时，为导入，如果不为空（“init”）时 为初始化盘点
        	if(StringUtil.isEmpty(type)){
            	map.put("PRD_NO", "0");
            	map.put("PRD_NAME", "1");
            	map.put("SPCL_TECH_NO", "2");
            	map.put("INV_NUM", "5");
        	}else{
    			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
    			if(isMonthAcc){
    				throw new ServiceException("处于月结阶段,不能做出库!");
    			}
        		//如果为初始化盘点，验证当前时间是否与所属渠道的初始化时间在同一个月，不在一个月不让初始化盘点
        		initData=inventoryService.getChannMonth(userBean.getCHANN_ID());
        		if(StringUtil.isEmpty(initData)){
        			throw new ServiceException("对不起，您的渠道没有设置初始化年份月份，不能初始化盘点 !");
        		}
            	map.put("STORE_NO", "0");
            	map.put("STORE_NAME", "1");
            	map.put("PRD_NO", "2");
            	map.put("PRD_NAME", "3");
            	map.put("SPCL_TECH_NO", "4");
            	map.put("PRICE", "5");
            	map.put("INV_NUM", "6");
        	}
        	String[] a=new String[]{"1"};
        	alist.add(map);
            List list=ExcelUtil.readExcelbyModel(fileName, p, 1, alist, a);
            inventoryService.txParseExeclToDbNew(list, userBean,PRD_INV_ID,type,initData);
            jsonResult = jsonResult(true, "上传成功");//
        } catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            jsonResult = jsonResult(false, "Execl解析失败." + e.getMessage());
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String PRD_INV_ID= request.getParameter("PRD_INV_ID");
        List list=inventoryService.childQuery(PRD_INV_ID);
        //excel数据上列显示的列明
        String tmpContentCn="货品编号（必填）,货品名称（非必填）,特殊工艺编号（不用填）,特殊工艺描述（不用填）,账面数量（不用填）,盘点数量（必填）,货品分类（不用填）";
        //
        String tmpContent="PRD_NO,PRD_NAME,DM_SPCL_TECH_NO,SPCL_DTL_REMARK,ACCT_NUM,INV_NUM,PAR_PRD_NAME";
        try {
			doExport(response, list, "明细盘点", tmpContent, tmpContentCn,"");
		} catch (Exception e) {
			e.printStackTrace();
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
	    	pvgMap.put("PVG_BEGIN_CHECK",QXUtil.checkPvg(userBean, PVG_BEGIN_CHECK) );
	    	pvgMap.put("PVG_UPLOADING",QXUtil.checkPvg(userBean, PVG_UPLOADING) );
	    	pvgMap.put("PVG_DOWNLOAD",QXUtil.checkPvg(userBean, PVG_DOWNLOAD) );
	    	pvgMap.put("PVG_END_CHECK",QXUtil.checkPvg(userBean, PVG_END_CHECK) );
	    	pvgMap.put("PVG_INI_UP",QXUtil.checkPvg(userBean, PVG_INI_UP) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
		public  void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn,String type) throws Exception{
			//生成excel
			HSSFWorkbook workbook =new HSSFWorkbook();
			if("temp".equals(type)){
				 workbook = tempPrintExcel(tmpContentCn);
			}else{
				 workbook = printExcel(tmpContent,tmpContentCn,dataList);
			}
            //导出excel
            writeExecl(response,workbook,execlName,type);
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
		private  HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList){

		     HSSFWorkbook workbook = null;
		     String[] titles_CN = tmpContentCn.split(",");
		     String[] titles_EN = tmpContent.split(",");
		     try{
		          //创建工作簿实例 
		           workbook = new HSSFWorkbook();
		          //创建工作表实例 
		         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
		          //设置列宽 
		          setSheetColumnWidth(titles_CN,sheet);
		        //获取样式 
		          HSSFCellStyle style = createTitleStyle(workbook); 
		          if(dataList != null){
		               //创建第一行标题 
		                HSSFRow row = sheet.createRow((short)0);// 建立新行
		                row.setHeight((short) 0);
		                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
		                HSSFRow rows = sheet.createRow((short)1);// 建立新行
		                for(int i=0;i<titles_CN.length;i++){
		                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
		                       titles_CN[i]);
		                }
		                //给excel填充数据 
		               for(int i=1;i<=dataList.size();i++){ 
		                        // 将dataList里面的数据取出来 
		                        Map<String,String> map = (HashMap)(dataList.get(i-1));
		                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
		                        boolean isOverTime = false;
		                         for(int j=0;j<titles_EN.length;j++){
		                            createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
		                                  }               
		                      }
		       }else{
		                createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "查无资料");
		       }
		  }catch(Exception e){
		              e.printStackTrace();
		  }
		 return workbook;
		}
		//设置列宽
		/**
		 * Sets the sheet column width.
		 * 
		 * @param titles_CN the titles_ cn
		 * @param sheet the sheet
		 */
		private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
		   // 根据你数据里面的记录有多少列，就设置多少列
		  for(int i=0;i<titles_CN.length;i++){
			  sheet.setColumnWidth((short)i, (short) 6000);
		          
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
		private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
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
		           cell.setCellValue(Double.parseDouble(value.toString()));}break; 
		       default: break; 
			 }  
			
			} 
		/**
		 * Write execl.
		 * 
		 * @param response the response
		 * @param workbook the workbook
		 * @param execlName the execl name
		 */
		public  void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName,String type) {
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
				if("temp".equals(type)){
					response.setHeader("Content-disposition", "attachment; filename="
							+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + ".xls");
				}else{
					response.setHeader("Content-disposition", "attachment; filename="
							+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-dd") + ".xls");
				}
				outputStream = response.getOutputStream();
				workbook.write(outputStream);
				outputStream.flush();
			} catch (Exception e) {
				logger.error("", e);
			} finally {
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (IOException e) {
						logger.error("", e);
					}
				}
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
		private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
		  HSSFFont boldFont = wb.createFont(); 
		  boldFont.setFontHeight((short) 200); 
		  HSSFCellStyle style = wb.createCellStyle(); 
		  style.setFont(boldFont); 
		  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
		  return style;  
		}
		//模版下载
		public void tempUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
			try {
//				UserBean userBean = ParamUtil.getUserBean(request);
//				List list=inventoryService.storeAcctQuery(userBean.getLoginZTXXID());
				String tmpContentCn="库房编号（必填）,库房名称(非必填),货品编号（必填）,货品名称（非必填）,特殊工艺编号（非必填）,单价（必填）,盘点数量（必填）";
				doExport(response, null, "盘点模版", null, tmpContentCn,"temp");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		private  HSSFWorkbook tempPrintExcel(String tmpContentCn){
			HSSFWorkbook workbook = null;
		     String[] titles_CN = tmpContentCn.split(",");
		     try{
		          //创建工作簿实例 
		           workbook = new HSSFWorkbook();
		          //创建工作表实例 
		         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
		          //设置列宽 
		          setSheetColumnWidth(titles_CN,sheet);
		        //获取样式 
		          HSSFCellStyle style = createTitleStyle(workbook); 
		               //创建第一行标题 
		                HSSFRow row = sheet.createRow((short)0);// 建立新行
		                row.setHeight((short) 0);
		                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
		                HSSFRow rows = sheet.createRow((short)1);// 建立新行
		                for(int i=0;i<titles_CN.length;i++){
		                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
		                       titles_CN[i]);
		                }
		  }catch(Exception e){
		              e.printStackTrace();
		  }
		 return workbook;
		}
		public String getHtml(Map<String,String> map,int pageNo,int pageSize){
	    	long totalCount=inventoryService.getCount(map);
	    	if(pageSize==0){
	    		pageSize =  Consts.PAGE_SIZE;
	    	}
			int start = (pageNo - 1) * pageSize;
			long curPageNo=start / pageSize + 1;
			long totalPageCount=0;
			if (totalPageCount == 0) {
				if (totalCount % pageSize == 0)
					totalPageCount = totalCount / pageSize;
				else
					totalPageCount = totalCount / pageSize + 1;
			}
	    	String html=getToolbarHtml(curPageNo, totalPageCount, totalCount,pageSize);
	    	return html;
	    }
		 /**
		 * 翻页控制.
		 * 
		 * @return the toolbar html
		 */
		public String getToolbarHtml(long curPageNo,long totalPage,long totalCount,int pageSize) {
			StringBuffer html = new StringBuffer();
			html.append("&nbsp;&nbsp;共").append(totalCount).append("条记录&nbsp;&nbsp;");
			//总页数大于1才显示上一页
			if(totalPage>1){
				html.append("&nbsp;<span class='otherPage' onclick='_gotopageahead()'>&nbsp;上一页&nbsp;</span>&nbsp;");
			}
			if(curPageNo < 6){
				for(int i=0 ;i<totalPage; i++){
					if(i>5){
						if(totalPage > 6){
							if(totalPage > 7){
								html.append("<b>.&nbsp;.&nbsp;.</b>");
							}
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+totalPage+")' >&nbsp;&nbsp;"+totalPage+"&nbsp;&nbsp;</span>&nbsp;");
						}
						break;
					}else{
						int j = i+1;
						if(curPageNo == j){
							html.append("<span class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
				}
			}else{
				html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+1+")' >&nbsp;&nbsp;"+1+"&nbsp;&nbsp;</span>&nbsp;");
				if(totalPage <= curPageNo+3){
					if(totalPage!=6&& totalPage!=7){
						html.append("<b>.&nbsp;.&nbsp;.</b>");
					}
					for(long i=(totalPage-6==0?1:totalPage-6) ;i<totalPage; i++){
						long j = i+1;
						if(curPageNo == j){
							html.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
				}else{
					html.append("<b>.&nbsp;.&nbsp;.</b>");
					for(long i=(curPageNo-3),t=curPageNo+2;i<t ; i++){
						long j = i+1;
						if(curPageNo == j){
							html.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
					html.append("<b>.&nbsp;.&nbsp;.</b>");
					html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+totalPage+")' >&nbsp;&nbsp;"+totalPage+"&nbsp;&nbsp;</span>&nbsp;");
				}
			}
			
			long tempNextPage = curPageNo+1;
			if(totalPage>1){
				html.append("&nbsp;<span class='otherPage' onclick='_gotopagenext()'>&nbsp;下一页&nbsp;</span>");
			}
			
			html.append("&nbsp;&nbsp;每页&nbsp;<select onChange='_gotoPage3()' style='font-size:12px;'  id='_gotoPageSize' class='page_no' maxlength='5'>");
			//xingkefa 2012-01-19 每页显示条数列表！  start
			String pageSizeList=Properties.getStrList("PAGE_SIZE_LIST");
			pageSizeList=pageSizeList+",200";
			String[] pagesizes = pageSizeList.split(",");
			String pagesize = pageSize + "";
			for(int i=0;i<pagesizes.length;i++){
				if(pagesize.equals(pagesizes[i])){
					html.append("<option selected='selected' value='"+pagesizes[i]+"'>").append(pagesizes[i]).append("</option>");
				}else{
					html.append("<option value='"+pagesizes[i]+"'>").append(pagesizes[i]).append("</option>");
				}
			}
			html.append("</select>&nbsp;条");
			//end 
			html.append("&nbsp;&nbsp;到第&nbsp;<input id='_gotoPageNo' class='page_no' maxlength='5' value="+curPageNo+">&nbsp;页");
			html.append("&nbsp;<input type='button' id='_gotoPageNobt'  class='btn'  onclick='_gotoPage2()' value='确定'>");
			
			html.append("<script type='text/javascript'>");
			//刘曰刚-2014-06-17 //
			//修改当有选中货品时跳转页面的提示框，改为确定提示框，如果确定则跳转，取消则不变
	        //选择确定的页面后的跳转
			html.append("function _gotopagecho(page){");
			html.append("listForm").append(".pageNo.value = page;");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			//点击下一页后的页面跳转
			//modify by zhuxw parent 有可能为null
			html.append("function _gotopagenext(){if("+tempNextPage+">"+totalPage+"){ if(parent.showErrorMsg){parent.showErrorMsg('已经是最后一页!');return;}else{showErrorMsg('已经是最后一页!');return;}}");
			html.append("listForm").append(".pageNo.value = "+tempNextPage+";");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			long tempAheadPage = curPageNo-1;
			//点击上一页后的页面跳转
			html.append("function _gotopageahead(){if("+tempAheadPage+"<"+1+"){if(parent.showErrorMsg){parent.showErrorMsg('已经是第一页!');return;}else{showErrorMsg('已经是第一页!');return;}}");
			html.append("listForm").append(".pageNo.value = "+tempAheadPage+";");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			//点击确定后的跳转
			html.append("function _gotoPage2(){");
			html.append("var inpt = g('_gotoPageNo');");
			html.append("var pageNo = inpt.value*1;");
			// 頁碼等於當前頁碼時，不飜頁
			html.append("if(").append(curPageNo).append(" == pageNo ){return;");
			// 頁碼是否超出範圍
			html.append("}else if (").append(totalPage).append(" < pageNo){");
			html.append("parent.showErrorMsg('页码超出范围!');return;");
			html.append("}else if ( 1 > pageNo){");
			html.append("parent.showErrorMsg('页码超出范围!');return;");
			html.append("}");
			// 頁碼跳轉
			html.append("listForm").append(".pageNo.value = pageNo;");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			
			html.append("}");
			//pagesizelist 改变时跳转！
			html.append("function _gotoPage3(){");
			html.append("var inpt = g('_gotoPageSize');");
			html.append("var pagesize = inpt.value;");
			// pagesize跳轉
			html.append("listForm").append(".pageSize.value = pagesize;");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			html.append("function checkPrdId(){")
				.append("var checkBox = $('#ordertb tr:gt(0) input:checked');")
				.append("if(!checkBox.length<1){")
				.append("parent.showConfirm('页面有未保存的盘点货品，如不保存则所有未保存盘点货品记录会丢失,确定跳转吗？','bottomcontent.retrue()','N');")
				.append("closeWindow();")
				.append("}else{")
				.append("setTimeout('").append("listForm").append(".submit()',100);")
				.append("}")
				.append("}");
			html.append("function retrue(){")
				.append("setTimeout('").append("listForm").append(".submit()',100);")
				.append("}");
			html.append("</script>");
			return html.toString();
		}
}