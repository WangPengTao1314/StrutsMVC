/**
 * prjName:喜临门营销平台
 * ucName:门店精致化检查结果
 * fileName:TermrefinecheckAction
*/
package com.hoperun.drp.oamg.termrefinecheck.action;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModel;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModelChld;
import com.hoperun.drp.oamg.termrefinecheck.service.TermrefinecheckService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-01-26 14:46:31
 */
public class TermrefinecheckAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(TermrefinecheckAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0032001";
    private static String PVG_EDIT="BS0032002";
    private static String PVG_DELETE="BS0032003";
    private static String PVG_UPLOADING="BS0032006";//导入
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032004";
    private static String PVG_TRACE="BS0032005";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0031201";
    private static String PVG_AUDIT_AUDIT="BS0031202";
    private static String PVG_TRACE_AUDIT="BS0031203";
    private static String PVG_SAVE_AUDIT="BS0031204";
    
    //审批流参数
    private static String AUD_TAB_NAME="TERM_REFINE_CHECK";
    private static String AUD_TAB_KEY="TERM_REFINE_CHECK_ID";
	private static String AUD_BUSS_STATE="u.STATE";
    private static String AUD_BUSS_TYPE="DRP_TERM_REFINE_CHECK_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private TermrefinecheckService termrefinecheckService;
    /**
	 * Sets the Termrefinecheck service.
	 * @param TermrefinecheckService the new Termrefinecheck service
	 */
	public void setTermrefinecheckService(TermrefinecheckService termrefinecheckService) {
		this.termrefinecheckService = termrefinecheckService;
	}
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
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
	    ParamUtil.putStr2Map(request, "TERM_REFINE_CHECK_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_VERSION", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	    ParamUtil.putStr2Map(request, "CHECK_ORG_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
	    ParamUtil.putStr2Map(request, "WAREA_NO", params);
	    ParamUtil.putStr2Map(request, "WAREA_NAME", params);
	    ParamUtil.putStr2Map(request, "BUSS_SCOPE", params);
	    
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		
		//渠道分管
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = termrefinecheckService.pageQuery(params, pageNo);
		String menu="";
		if("wh".equals(module)){
			menu="门店精致化检查结果维护";
		}else{
			menu="门店精致化检查结果审核";
		}
		request.setAttribute("menu", menu);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
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
        String TERM_REFINE_CHECK_ID =ParamUtil.get(request, "TERM_REFINE_CHECK_ID");
        String module = ParamUtil.get(request,"module");
        if(!StringUtil.isEmpty(TERM_REFINE_CHECK_ID))
        {
        	 List <TermrefinecheckModelChld> result = termrefinecheckService.childQuery(TERM_REFINE_CHECK_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("module",module);
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
		String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry;
		if(!StringUtil.isEmpty(TERM_REFINE_CHECK_ID)){
			entry= termrefinecheckService.loadByConf(TERM_REFINE_CHECK_ID);
		}else{
			//按当前登录人员查询所属机构
			entry=new HashMap<String, String>();
			entry.put("CHECK_ORG_ID", userBean.getJGXXID());
			entry.put("CHECK_ORG_NAME", userBean.getJGMC());
		}
		//查询所有品牌名称
        List<String> list=termrefinecheckService.getBrand("toList");
        request.setAttribute("rst", entry);
        request.setAttribute("brandList", list);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("QUERY_CHANN_ID", userBean.getCHANNS_CHARG());
		request.setAttribute("XTYH_ID", userBean.getXTYHID());
		
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
        String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "TERM_REFINE_CHECK_ID");
        if(!StringUtil.isEmpty(TERM_REFINE_CHECK_ID))
        {
        	 List <TermrefinecheckModelChld> result = termrefinecheckService.childQuery(TERM_REFINE_CHECK_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String TERM_REFINE_CHECK_DTL_IDs = request.getParameter("TERM_REFINE_CHECK_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(TERM_REFINE_CHECK_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("TERM_REFINE_CHECK_DTL_IDS",TERM_REFINE_CHECK_DTL_IDs);
          List <TermrefinecheckModelChld> list = termrefinecheckService.loadChilds(params);
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
		
		
		 PrintWriter writer = getWriter(response);
         String jsonResult = jsonResult();
         String parentJsonData = ParamUtil.get(request, "parentJsonData");
         
         UserBean userBean = ParamUtil.getUserBean(request);
         String TERM_REFINE_CHECK_ID = ParamUtil.utf8Decoder(request, "TERM_REFINE_CHECK_ID");  
         String chkLen = ParamUtil.utf8Decoder(request, "chkLen");
         TermrefinecheckModel model = new TermrefinecheckModel();
         if (StringUtils.isNotEmpty(parentJsonData)) {
             model = new Gson().fromJson(parentJsonData, new TypeToken <TermrefinecheckModel>() {
             }.getType());
         }
         String jsonData = ParamUtil.get(request, "childJsonData");
         List <TermrefinecheckModelChld>  mxList = null;
         if (StringUtils.isNotEmpty(jsonData)) {
             mxList = new Gson().fromJson(jsonData, new TypeToken <List<TermrefinecheckModelChld>>() {
             }.getType());
         }
         String str = termrefinecheckService.txEdit(TERM_REFINE_CHECK_ID, model,mxList,userBean,chkLen);
         if(str.equals("1")){
        	 jsonResult = jsonResult(false, "检查项目编号不能重复");
         }
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
		
		/*
		UserBean userBean = ParamUtil.getUserBean(request);
		String error = null;
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "TERM_REFINE_CHECK_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            String chkLen = ParamUtil.utf8Decoder(request, "chkLen");
            TermrefinecheckModel model = new Gson().fromJson(parentJsonData, new TypeToken <TermrefinecheckModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <TermrefinecheckModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <TermrefinecheckModelChld>>() {
                }.getType());
                
                if(chldModelList!=null && chldModelList.size()>0){
                	Map map  = new HashMap();
                	for(TermrefinecheckModelChld chldModel : chldModelList){
                		if(map.get(chldModel.getPRJ_CODE())!=null){
                			error = "项目编号'"+ chldModel.getPRJ_CODE() + "'重复，请重新选择！";
                			jsonResult = jsonResult(false, error);
                			break;
                		}
                		map.put(chldModel.getPRJ_CODE(), chldModel.getPRJ_CODE());
                	}
                }
            }
            
            if(!StringUtil.isEmpty(error)){
            	jsonResult = jsonResult(false,error);
            }else{
            	error = termrefinecheckService.txEdit(TERM_REFINE_CHECK_ID, model, chldModelList, userBean,chkLen);
            }
            
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }*/
	}
	
	/**
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	/*
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String error = null;
        try {
            String TERM_REFINE_CHECK_ID = request.getParameter("TERM_REFINE_CHECK_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <TermrefinecheckModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <TermrefinecheckModelChld>>() {
                }.getType());
                
                if(modelList!=null && modelList.size()>0){
                	Map map  = new HashMap();
                	for(TermrefinecheckModelChld chldModel : modelList){
                		if(map.get(chldModel.getPRJ_CODE())!=null){
                			error = "项目编号'"+ chldModel.getPRJ_CODE() + "'重复，请重新选择！";
                			jsonResult = jsonResult(false, error);
                			break;
                		}
                		map.put(chldModel.getPRJ_CODE(), chldModel.getPRJ_CODE());
                	}
                }
                error = termrefinecheckService.txChildEdit(TERM_REFINE_CHECK_ID, modelList);
                if(!StringUtil.isEmpty(error)){
                	jsonResult = jsonResult(false, error);
                }
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, error);
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }*/
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        int errorFlg = 0;
        try {
        	String TERM_REFINE_CHECK_ID = ParamUtil.utf8Decoder(request, "TERM_REFINE_CHECK_ID");//ParamUtil.get(request, "SJZDID");
            String jsonDate = request.getParameter("childJsonData");
            if (StringUtils.isNotEmpty(jsonDate)) {
                List<TermrefinecheckModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<TermrefinecheckModelChld>>() {
                }.getType());
                String str = "";
                for(int i=0;i<modelList.size();i++){
                	String PRJ_TYPE   = modelList.get(i).getPRJ_TYPE().toString() ;
                	String PRJ_CODE   = modelList.get(i).getPRJ_CODE().toString();
                	String PRJ_NAME   = modelList.get(i).getPRJ_NAME().toString();
                	String PRJ_SCORE  = modelList.get(i).getPRJ_SCORE().toString();
                	String CHECK_SCORE = modelList.get(i).getCHECK_SCORE().toString();
                	String CHECK_REMARK= modelList.get(i).getCHECK_REMARK().toString();
                	String TERM_REFINE_CHECK_DTL_ID = modelList.get(i).getTERM_REFINE_CHECK_DTL_ID().toString();
                	//String IS_REFORM_FLAG = modelList.get(i).getIS_REFORM_FLAG().toString();
                	//查询项目类型,项目名称在验收明细表中是否存在
                	List<TermrefinecheckModelChld> result =  termrefinecheckService.queryTypeAndName(PRJ_CODE,PRJ_NAME,TERM_REFINE_CHECK_ID);
                	if(TERM_REFINE_CHECK_DTL_ID.equals("")){
                		if(result.size()==0){
                	      str = termrefinecheckService.insertChild(TERM_REFINE_CHECK_ID,PRJ_TYPE,PRJ_CODE,PRJ_NAME,PRJ_SCORE,CHECK_SCORE,CHECK_REMARK,modelList);
                		}else {
                		  jsonResult = jsonResult(false, "明细项目不能重复");
                		}
                	}else{
                		  termrefinecheckService.updateChild(TERM_REFINE_CHECK_DTL_ID,TERM_REFINE_CHECK_ID,PRJ_TYPE,PRJ_CODE,PRJ_NAME,PRJ_SCORE,CHECK_SCORE,CHECK_REMARK);
                	}
                }
                if(str.equals("1")){
                	 jsonResult = jsonResult(false, "明细项目不能重复");
                }
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
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
			String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "TERM_REFINE_CHECK_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			termrefinecheckService.txDelete(params);
			termrefinecheckService.clearCaches(params);
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
        	String TERM_REFINE_CHECK_ID = request.getParameter("TERM_REFINE_CHECK_ID");
            String TERM_REFINE_CHECK_DTL_IDs = request.getParameter("TERM_REFINE_CHECK_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            termrefinecheckService.txBatch4DeleteChild(TERM_REFINE_CHECK_ID,TERM_REFINE_CHECK_DTL_IDs);
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
		String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "TERM_REFINE_CHECK_ID");
		if(!StringUtil.isEmpty(TERM_REFINE_CHECK_ID)){
			Map<String,String> entry = termrefinecheckService.loadByConf(TERM_REFINE_CHECK_ID);
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(TERM_REFINE_CHECK_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
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
            String TERM_REFINE_CHECK_ID = request.getParameter("TERM_REFINE_CHECK_ID");
            List <TermrefinecheckModelChld> list = termrefinecheckService.childQuery(TERM_REFINE_CHECK_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }else{
            	termrefinecheckService.txUpdateChild(TERM_REFINE_CHECK_ID);
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
    
    public ActionForward childList2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String CHANN_CHECK_PLAN_NO = ParamUtil.get(request, "CHANN_CHECK_PLAN_NO");   //工作计划ID
		String BUSS_SCOPE  = ParamUtil.get(request, "BUSS_SCOPE");
		String CHANN_CHECK_PLAN_ID = termrefinecheckService.queryIdByNo(CHANN_CHECK_PLAN_NO);
		List<TermrefinecheckModelChld>  result =  termrefinecheckService.queryJudgeModel(CHANN_CHECK_PLAN_ID,BUSS_SCOPE);
		request.setAttribute("rst", result);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("CHANN_CHECK_PLAN_NO", CHANN_CHECK_PLAN_NO);
		return mapping.findForward("childedit");
	}  
    
    
    
    public void childSave(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String jsonDate = request.getParameter("childJsonData");
            List<TermrefinecheckModelChld> modelList = new Gson().fromJson(jsonDate, 
            		new TypeToken <List<TermrefinecheckModelChld>>() {}.getType());
            termrefinecheckService.txChildSave("",modelList);
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
	    	pvgMap.put("PVG_UPLOADING",QXUtil.checkPvg(userBean, PVG_UPLOADING) );
	    	pvgMap.put("PVG_SAVE_AUDIT",QXUtil.checkPvg(userBean, PVG_SAVE_AUDIT) );
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
	        	String TERM_REFINE_CHECK_ID=request.getParameter("TERM_REFINE_CHECK_ID");
	        	String fileName= "sample"+ParamUtil.utf8Decoder(request, "fileName");
	        	String secPath = Properties.getString("SAMPLE_DIR");
	        	String p = serverDir+ File.separatorChar + secPath+ File.separatorChar + fileName;
	        	List<Map<String,String>> alist=new ArrayList<Map<String,String>>();
	        	Map<String,String> map=new HashMap<String,String>();
	        	map.put("TERM_REFINE_TASK_NO", "0");
	        	map.put("TERM_NO", "1");
	        	map.put("PRJ_TYPE", "2");
	        	map.put("PRJ_CODE", "3");
	        	map.put("PRJ_NAME", "4");
	        	map.put("PRJ_SCORE", "5");
	        	map.put("CHECK_REMARK", "6");
	        	map.put("CHECK_SCORE", "7");
	        	String[] a=new String[]{"1"};
	        	alist.add(map);
	            List list=ExcelUtil.readExcelbyModel(fileName, p, 1, alist, a);
	            termrefinecheckService.txParseExeclToDbNew(list, userBean,TERM_REFINE_CHECK_ID);
	            jsonResult = jsonResult(true, "上传成功");//
	        } catch (ServiceException e) {
	            e.printStackTrace();
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
	    
	    
	    public static String getPvgCon(String search,String module,String pvgBws, String pvgAudit,
				String pkId, String tableName, String businessType,String state,UserBean userBean) {
			StringBuffer tmp=new StringBuffer("");
			if (!StringUtil.isEmpty(module))
	         {
	        	if ("sh".equals(module)) {
	    			if (StringUtil.isEmpty(search)) {
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgAudit));
	    				if(!StringUtil.isEmpty(state))
	    				{
	    					tmp.append(" and ");
	        				tmp.append(state);
	        				tmp.append(" ='");
	        				tmp.append(BusinessConsts.COMMIT);
	        				tmp.append("'");
	    				}
	    				tmp.append(" and ");
	    				tmp.append(pkId);
	    				tmp.append(" in ");
	    				tmp.append(QXUtil.getAudCondition(userBean, tableName,businessType));
	    			} else {
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgAudit));
	    				tmp.append(" and ");
	    				tmp.append(pkId);
	    				tmp.append(" in ");
	    				tmp.append(QXUtil.getAudQureyCon(userBean, tableName,businessType));
	    			}
	    		} else {
	    			if (StringUtil.isEmpty(search)) {
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
	    				if(!StringUtil.isEmpty(state))
	    				{
	        			tmp.append(" and ");
	    				tmp.append(state);
	    				tmp.append(" in ('");
	    				tmp.append(BusinessConsts.UNCOMMIT);
	    				tmp.append("','");
	    				tmp.append(BusinessConsts.REVOKE);
	    				tmp.append("','");
	    				tmp.append(BusinessConsts.REJECT);
	    				tmp.append("')");
	    				}
	    			} else {
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
	    			}
	    			
	    		}
	        }else
	        {
	        	 tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
	        }
		  return tmp.toString();
		}
	 
}