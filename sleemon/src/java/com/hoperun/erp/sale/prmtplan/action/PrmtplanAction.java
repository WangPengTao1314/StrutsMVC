/**
 * prjName:喜临门营销平台
 * ucName:推广促销方案维护
 * fileName:PrmtplanAction
*/
package com.hoperun.erp.sale.prmtplan.action;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.prmtplan.model.PrmtplaAreaModel;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModelChld;
import com.hoperun.erp.sale.prmtplan.service.PrmtplanService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func  推广促销
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-08-23 16:04:28
 */
public class PrmtplanAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PrmtplanAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0020301";
    private static String PVG_EDIT="BS0020302";
    private static String PVG_DELETE="BS0020303";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0020304";
    private static String PVG_TRACE="BS0020304";
    //发布
    private static String PVG_ISSUANCE = "BS0020306";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0020305";
    private static String PVG_AUDIT_AUDIT="BS0020305";
    private static String PVG_TRACE_AUDIT="BS0020305";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_PRMT_PLAN";
    private static String AUD_TAB_KEY="PRMT_PLAN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_PRMT_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private PrmtplanService prmtplanService;
    /**
	 * Sets the Prmtplan service.
	 * 
	 * @param PrmtplanService the new Prmtplan service
	 */
	public void setPrmtplanService(PrmtplanService prmtplanService) {
		this.prmtplanService = prmtplanService;
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
		logger.info("");
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String FLAG = ParamUtil.get(request, "FLAG");
		request.setAttribute("FLAG",FLAG);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        
        String FLAG = ParamUtil.get(request,"FLAG");
        if(!StringUtil.isEmpty(FLAG) && BusinessConsts.FLAG_PRMT.equals(FLAG)){
        	 if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_ISSUANCE)){
         		throw new ServiceException("对不起，您没有权限 !");
         	}
        }
       
        
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "PRMT_PLAN_NO", params);
		ParamUtil.putStr2Map(request, "PRMT_PLAN_NAME", params);
		ParamUtil.putStr2Map(request, "PRMT_TYPE", params);
		ParamUtil.putStr2Map(request, "EFFCT_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "EFFCT_DATE_END", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		StringBuffer qxjbcn = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//发布页面查询时的条件
		if(!StringUtil.isEmpty(FLAG) && BusinessConsts.FLAG_PRMT.equals(FLAG)){
			qxjbcn.append(" and STATE in ('");
			qxjbcn.append(BusinessConsts.PASS+"','"+BusinessConsts.OVER+"','"+BusinessConsts.ISSUANCE);
			qxjbcn.append("')");
			request.setAttribute("FLAG", FLAG);
	    	 
	    }
		qxjbcn.append(" and LEDGER_ID = '");
		qxjbcn.append(userBean.getLoginZTXXID());
		qxjbcn.append("' ");
		 
		//权限级别和审批流的封装
	    params.put("QXJBCON", qxjbcn.toString());
	    
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prmtplanService.pageQuery(params, pageNo);
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRMT_PLAN_ID =ParamUtil.get(request, "PRMT_PLAN_ID");
        if(!StringUtil.isEmpty(PRMT_PLAN_ID))
        {
        	 List <PrmtplanModelChld> result = prmtplanService.childQuery(PRMT_PLAN_ID);
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
		String PRMT_PLAN_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(PRMT_PLAN_ID)){
			Map<String,String> entry = prmtplanService.load(PRMT_PLAN_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", false);
		}else{
			request.setAttribute("isNew", true);
		}
		
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
        String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
        if(!StringUtil.isEmpty(PRMT_PLAN_ID))
        {
        	 List <PrmtplanModelChld> result = prmtplanService.childQuery(PRMT_PLAN_ID);
             request.setAttribute("rst", result);
        }
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRMT_PRD_GROUP_IDs = request.getParameter("PRMT_PRD_GROUP_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRMT_PRD_GROUP_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRMT_PRD_GROUP_IDS",PRMT_PRD_GROUP_IDs);
          List <PrmtplanModelChld> list = prmtplanService.loadChilds(params);
          request.setAttribute("rst", list);
        }
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
            String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            PrmtplanModel model = new Gson().fromJson(parentJsonData, new TypeToken <PrmtplanModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <PrmtplanModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrmtplanModelChld>>() {
                }.getType());
            }
            prmtplanService.txEdit(PRMT_PLAN_ID, model, chldModelList, userBean);
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
    public void childEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRMT_PLAN_ID = request.getParameter("PRMT_PLAN_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrmtplanModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrmtplanModelChld>>() {
                }.getType());
                prmtplanService.txChildEdit(PRMT_PLAN_ID, modelList);
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
			String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_PLAN_ID", PRMT_PLAN_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			prmtplanService.txDelete(params);
			prmtplanService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRMT_PRD_GROUP_IDs = request.getParameter("PRMT_PRD_GROUP_IDS");
            //批量删除，多个Id之间用逗号隔开
            prmtplanService.txBatch4DeleteChild(PRMT_PRD_GROUP_IDs);
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
		String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
		if(!StringUtil.isEmpty(PRMT_PLAN_ID)){
			Map<String,String> entry = prmtplanService.load(PRMT_PLAN_ID);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(PRMT_PLAN_ID);
			request.setAttribute("flowInfoList", flowInfoList);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
	 * 发布 和 活动终止 按钮
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toIssuance(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_ISSUANCE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
//        String errorId = "";
        String tag = null;
        try{
        	 String id = request.getParameter("PRMT_PLAN_ID");
        	 tag = request.getParameter("tag");

        	 if(!StringUtil.isEmpty(tag)){
        		 prmtplanService.updateIssuance(id,BusinessConsts.OVER, userBean);
        		 jsonResult = jsonResult(true, "活动终止成功"); 
        	 }else{
        		 prmtplanService.updateIssuance(id,BusinessConsts.ISSUANCE, userBean);
        		 jsonResult = jsonResult(true, "发布成功");
        	 }
        	 
        }catch(Exception e){
        	if(!StringUtil.isEmpty(tag)){
        		jsonResult = jsonResult(false, "活动终止失败");
        	}else{
        		jsonResult = jsonResult(false, "发布失败");
        	}
        	
        	e.printStackTrace();
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
        try {
            String PRMT_PLAN_ID = request.getParameter("PRMT_PLAN_ID");
            List <PrmtplanModelChld> list = prmtplanService.childQuery(PRMT_PLAN_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            } else {
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
     * * 生效区域 明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward areaList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRMT_PLAN_ID =ParamUtil.get(request, "PRMT_PLAN_ID");
        if(!StringUtil.isEmpty(PRMT_PLAN_ID))
        {
        	 List <PrmtplaAreaModel> result = prmtplanService.areaQuery(PRMT_PLAN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("arealist");
    }
    
    
    
    /**
     * * 编辑框架页面加载 生效区域子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToAreaEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
        if(!StringUtil.isEmpty(PRMT_PLAN_ID))
        {
        	 List <PrmtplaAreaModel> result = prmtplanService.areaQuery(PRMT_PLAN_ID);
             request.setAttribute("rst", result);
        }
        return mapping.findForward("areaedit");
    }
	
	 /**
     * * to 直接编辑 生效区域明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toAreaEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRMT_EFFCT_AREA_IDS = request.getParameter("PRMT_EFFCT_AREA_IDS");
        //没有Id可以直接跳转。
        if (!StringUtil.isEmpty(PRMT_EFFCT_AREA_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRMT_EFFCT_AREA_IDS",PRMT_EFFCT_AREA_IDS);
          List <PrmtplaAreaModel> list = prmtplanService.loadAreas(params);
          request.setAttribute("rst", list);
        }
        
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("areaedit");
    }
    
	/**
     * * 生效区域  新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void areaEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorMsg = null;
        try {
            String PRMT_PLAN_ID = request.getParameter("PRMT_PLAN_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrmtplaAreaModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrmtplaAreaModel>>() {
                }.getType());
                
                errorMsg =  vailChild(PRMT_PLAN_ID,modelList);
                if(null == errorMsg ){
                	 prmtplanService.txAreaEdit(PRMT_PLAN_ID, modelList);
                }else{
                	 jsonResult = jsonResult(false, errorMsg);
                }
               
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
	 * 明细重复判断
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String vailChild(String pId, 
			List<PrmtplaAreaModel> modelList) {

		String returnMessage = null;
		int size = modelList.size();
		StringBuffer CHRGNOS = new StringBuffer();
		String MIXIDS = null;

		// 如果页面的之无重复，在和同一区域下的数据库的值判断
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				PrmtplaAreaModel model = modelList.get(i);
				CHRGNOS.append("'" + model.getAREA_NO() + "'");
				if ((i + 1) != size) {
					CHRGNOS.append(",");
				}
				
				if(!StringUtil.isEmpty(model.getPRMT_EFFCT_AREA_ID())){
					if(null != MIXIDS){
						MIXIDS+=",";
						MIXIDS += "'" + model.getPRMT_EFFCT_AREA_ID() + "'";
					}else{
						MIXIDS = "'" + model.getPRMT_EFFCT_AREA_ID() + "'";
					}
					
					
				}
			}
			

			Map<String, String> paramsMx = new HashMap<String, String>();
			paramsMx.put("PRMT_PLAN_ID", pId);
			paramsMx.put("CHRGNOS", CHRGNOS.toString());
			paramsMx.put("MIXIDS", MIXIDS);
			paramsMx.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			
			List list = prmtplanService.findAreaList(paramsMx);
			if (null != list && list.size() > 0) {
				returnMessage = "生效区域编号与已有的记录重复";
			}
		}

		return returnMessage;
	}
	 /**
     * * 生效区域  批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void areaDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRMT_EFFCT_AREA_IDS = request.getParameter("PRMT_EFFCT_AREA_IDS");
            //批量删除，多个Id之间用逗号隔开
            prmtplanService.txBatch4DeleteArea(PRMT_EFFCT_AREA_IDS);
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
	    	pvgMap.put("PVG_ISSUANCE",QXUtil.checkPvg(userBean, PVG_ISSUANCE));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}