/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认
 * fileName:StorediffAction
*/
package com.hoperun.drp.store.storediff.action;
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
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storediff.model.StorediffModel;
import com.hoperun.drp.store.storediff.model.StorediffModelChld;
import com.hoperun.drp.store.storediff.model.StorediffdealModelChld;
import com.hoperun.drp.store.storediff.service.StorediffService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-30 14:03:21
 */
public class StorediffAction extends BaseAction {
	/**日志**/
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0030601";
    private static String PVG_EDIT="FX0030602";
    private static String PVG_DELETE="FX0030603";
    private static String PVG_COMMIT="FX0030604";
    private static String PVG_OVER="FX0030605";
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
	private StorediffService storediffService;
    /**
	 * Sets the Storediff service.
	 * 
	 * @param StorediffaffService the new Storediff service
	 */
	public void setStorediffService(StorediffService storediffService) {
		this.storediffService = storediffService;
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "STOREIN_DIFF_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		params.put("LEDGER_ID",userBean.getLoginZTXXID());
		
		if(!"true".equals(search)){
			params.put("STATEDEL" ,"STATE in ( '未提交','待发货方确认','退回','待入库方确认' )");
		}
	
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storediffService.pageQuery(params, pageNo);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_DIFF_ID =ParamUtil.get(request, "STOREIN_DIFF_ID");
        if(!StringUtil.isEmpty(STOREIN_DIFF_ID))
        {
        	 List <StorediffModelChld> result = storediffService.childQuery(STOREIN_DIFF_ID);
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
		String STOREIN_DIFF_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(STOREIN_DIFF_ID)){
			Map<String,String> entry = storediffService.load(STOREIN_DIFF_ID);
			request.setAttribute("rst", entry);
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_DIFF_ID = ParamUtil.get(request, "STOREIN_DIFF_ID");
        if(!StringUtil.isEmpty(STOREIN_DIFF_ID))
        {
        	 List <StorediffModelChld> result = storediffService.childQuery(STOREIN_DIFF_ID);
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
        String STOREIN_DIFF_DTL_IDs = request.getParameter("STOREIN_DIFF_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STOREIN_DIFF_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STOREIN_DIFF_DTL_IDS",STOREIN_DIFF_DTL_IDs);
          List <StorediffModelChld> list = storediffService.loadChilds(params);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_COMMIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String error="";
		try {
            String STOREIN_DIFF_ID = ParamUtil.get(request, "STOREIN_DIFF_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            StorediffModel model = new Gson().fromJson(parentJsonData, new TypeToken <StorediffModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <StorediffModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <StorediffModelChld>>() {
                }.getType());
            }
            int count=storediffService.countDeal(STOREIN_DIFF_ID);
            if(count==0){
            	error="1";
            	throw new Exception();
            }
            storediffService.txEdit(STOREIN_DIFF_ID, model, chldModelList, userBean);
        } catch (Exception e) {
        	if(error=="1"){
        		jsonResult = jsonResult(false, "没有差异处理明细信息，不能提交!");
        	}else{
        		 jsonResult = jsonResult(false, "提交失败");
                 e.printStackTrace();
        	}
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
	public void over(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_OVER))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String STOREIN_DIFF_ID = ParamUtil.get(request, "STOREIN_DIFF_ID");

            storediffService.txOver(STOREIN_DIFF_ID,userBean);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
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
            String STOREIN_DIFF_ID = request.getParameter("STOREIN_DIFF_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <StorediffModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <StorediffModelChld>>() {
                }.getType());
                storediffService.txChildEdit(STOREIN_DIFF_ID, modelList,userBean);
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
			String STOREIN_DIFF_ID = ParamUtil.get(request, "STOREIN_DIFF_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            
			storediffService.txDelete(params);
			storediffService.clearCaches(params);
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
            String STOREIN_DIFF_DTL_IDs = request.getParameter("STOREIN_DIFF_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            storediffService.txBatch4DeleteChild(STOREIN_DIFF_DTL_IDs);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STOREIN_DIFF_ID = ParamUtil.get(request, "STOREIN_DIFF_ID");
		if(!StringUtil.isEmpty(STOREIN_DIFF_ID)){
			Map<String,String> entry = storediffService.load(STOREIN_DIFF_ID);
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
            String STOREIN_DIFF_ID = request.getParameter("STOREIN_DIFF_ID");
            List <StorediffModelChld> list = storediffService.childQuery(STOREIN_DIFF_ID);
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
			pvgMap.put("PVG_COMMIT",QXUtil.checkPvg(userBean, PVG_COMMIT));
			pvgMap.put("PVG_OVER",QXUtil.checkPvg(userBean, PVG_OVER));
	    	return  pvgMap;
	   }
	 
	 
	 /**
	  * 差异处理
	  */
	 
	 /**
	     * * 明细列表
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * 
	     * @return the action forward
	     */
	    public ActionForward dealList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	    	
	    	UserBean userBean = ParamUtil.getUserBean(request);
	        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        String STOREIN_DIFF_ID =ParamUtil.get(request, "STOREIN_DIFF_ID");
	        String STOREIN_DIFF_DTL_ID = ParamUtil.get(request, "STOREIN_DIFF_DTL_ID");
	        
	        if(!StringUtil.isEmpty(STOREIN_DIFF_ID) && !StringUtil.isEmpty(STOREIN_DIFF_DTL_ID))
	        {
	        	 List <StorediffdealModelChld> result = storediffService.dealQuery(STOREIN_DIFF_ID,STOREIN_DIFF_DTL_ID);
	             request.setAttribute("rst", result);
	        }
	        request.setAttribute("pvg",setPvg(userBean));
	        
	        return mapping.findForward("deallist");
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
	    public ActionForward todealEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	//多个Id批量查询，用逗号隔开
	        String DIFF_DEAL_DTL_IDs = request.getParameter("DIFF_DEAL_DTL_IDS");
	        
	        String STOREIN_DIFF_DTL_ID = request.getParameter("STOREIN_DIFF_DTL_ID");
	        
	        //没有零星领料Id可以直接跳转。
	        if (!StringUtil.isEmpty(DIFF_DEAL_DTL_IDs)) {
			  Map<String,String> params = new HashMap<String,String>();
			  params.put("DIFF_DEAL_DTL_IDS",DIFF_DEAL_DTL_IDs);
	          List <StorediffdealModelChld> list = storediffService.loadDealChilds(params);
	          request.setAttribute("rst", list);
	        }
	        if(!StringUtil.isEmpty(STOREIN_DIFF_DTL_ID)){
	        	 Map<String,String> params = new HashMap<String,String>();
				 params.put("STOREIN_DIFF_DTL_IDS","'"+STOREIN_DIFF_DTL_ID+"'");
		         List <StorediffModelChld> list = storediffService.loadChilds(params);
		         request.setAttribute("prd", list.get(0));
	        }
	        
	        return mapping.findForward("dealedit");
	    }
	    
	    /**
	     * * 明细批量删除
	     * 
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void dealDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String DIFF_DEAL_DTL_IDs = request.getParameter("DIFF_DEAL_DTL_IDS");
	            //批量删除，多个Id之间用逗号隔开
	            storediffService.txBatch4DeleteDealChild(DIFF_DEAL_DTL_IDs);
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
	     * * 子表 新增/修改数据
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void dealEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String STOREIN_DIFF_ID = request.getParameter("STOREIN_DIFF_ID");
	            String STOREIN_DIFF_DTL_ID = request.getParameter("STOREIN_DIFF_DTL_ID");
	            
	            String jsonDate = request.getParameter("childJsonData");
	            if (!StringUtil.isEmpty(jsonDate)) {
	                List <StorediffdealModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <StorediffdealModelChld>>() {
	                }.getType());
	                storediffService.txdealEdit(STOREIN_DIFF_ID,STOREIN_DIFF_DTL_ID, modelList,userBean);
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
	    
}