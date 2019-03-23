/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappAction
*/
package com.hoperun.drp.sale.repairapp.action;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.drp.sale.repairapp.service.RepairappService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairappAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(RepairappAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0021601";
    private static String PVG_EDIT="FX0021602";
    private static String PVG_DELETE="FX0021604";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021603";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0013201";
    private static String PVG_AUDIT_AUDIT="BS0013202";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private RepairappService repairappService;
    /**
	 * Sets the Repairapp service.
	 * 
	 * @param RepairStoreoutService the new Repairapp service
	 */
	public void setRepairappService(RepairappService repairappService) {
		this.repairappService = repairappService;
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
		String module = ParamUtil.get(request,"module");
		if(!StringUtil.isEmpty(module)){
			if("sh".equals(module)){
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
				
			}else{
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS) )
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}
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
		String module = ParamUtil.get(request,"module");
		if(!StringUtil.isEmpty(module)){
			if("sh".equals(module)){
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
				
			}else{
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS) )
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}
		}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "ERP_REPAIR_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "REPAIR_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "REPAIR_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "FINISH_DATE_BEG", params);
	    ParamUtil.putStr2Map(request, "FINISH_DATE_END", params);
	    
	    
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String frameTolist = ParamUtil.get(request,"frameTolist");
       /* if(!StringUtil.isEmpty(frameTolist)){
        	params.put("STATE","未提交");
        }*/
		//权限级别和审批流的封装
	    //params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    params.put("QXJBCON", getQXString(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = repairappService.pageQuery(params, pageNo);
		params.put("module",module);
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
    	String module = ParamUtil.get(request,"module");
		if(!StringUtil.isEmpty(module)){
			if("sh".equals(module)){
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
				
			}else{
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS) )
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}
		}
        String ERP_REPAIR_ORDER_ID =ParamUtil.get(request, "ERP_REPAIR_ORDER_ID");
        if(!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID))
        {
        	 List <Map<String,Object>> result = repairappService.childQueryByParentId(ERP_REPAIR_ORDER_ID);
             request.setAttribute("rst", result);
             Map<String,Object> totalResult = repairappService.queryTotal(ERP_REPAIR_ORDER_ID);
             request.setAttribute("totalResult", totalResult);
        }
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("module",module);
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
		String ERP_REPAIR_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)){
			Map<String,String> entry = repairappService.load(ERP_REPAIR_ORDER_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", false);
		}else{
			request.setAttribute("isNew", true);
		}
		
		Map<String,String> area = repairappService.getAreaByuserChann(userBean.getCHANN_ID());
		request.setAttribute("area", area);
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ERP_REPAIR_ORDER_ID = ParamUtil.get(request, "ERP_REPAIR_ORDER_ID");
        if(!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID))
        {
        	 List <RepairappModelChld> result = repairappService.childQuery(ERP_REPAIR_ORDER_ID);
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
        String REPAIR_ORDER_DTL_IDs = request.getParameter("REPAIR_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(REPAIR_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("REPAIR_ORDER_DTL_IDS",REPAIR_ORDER_DTL_IDs);
          List <RepairappModelChld> list = repairappService.loadChilds(params);
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
            String ERP_REPAIR_ORDER_ID = ParamUtil.get(request, "ERP_REPAIR_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            RepairappModel model = new Gson().fromJson(parentJsonData, new TypeToken <RepairappModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <RepairappModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <RepairappModelChld>>() {
                }.getType());
            }
            repairappService.txEdit(ERP_REPAIR_ORDER_ID, model, chldModelList, userBean);
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
            String ERP_REPAIR_ORDER_ID = request.getParameter("ERP_REPAIR_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <RepairappModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <RepairappModelChld>>() {
                }.getType());
                repairappService.txChildEdit(ERP_REPAIR_ORDER_ID, modelList);
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
			String ERP_REPAIR_ORDER_ID = ParamUtil.get(request, "ERP_REPAIR_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			repairappService.txDelete(params);
			repairappService.clearCaches(params);
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
            String REPAIR_ORDER_DTL_IDs = request.getParameter("REPAIR_ORDER_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            repairappService.txBatch4DeleteChild(REPAIR_ORDER_DTL_IDs);
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
		String module = ParamUtil.get(request,"module");
		if(!StringUtil.isEmpty(module)){
			if("sh".equals(module)){
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
				
			}else{
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_BWS) )
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}
		}
		String ERP_REPAIR_ORDER_ID = ParamUtil.get(request, "ERP_REPAIR_ORDER_ID");
		if(!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)){
			Map<String,String> entry = repairappService.load(ERP_REPAIR_ORDER_ID);
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
    	String module = ParamUtil.get(request,"module");
		if(!StringUtil.isEmpty(module)){
			if("sh".equals(module)){
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT))
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}else{
				if(Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE) )
		    	{
		    		throw new ServiceException("对不起，您没有权限 !");
		    	}
			}
		}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        Map <String, String> params = new HashMap <String, String>();
        String type = ParamUtil.get(request, "type");
        String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");
        //String SHIP_POINT_NAME = ParamUtil.get(request, "SHIP_POINT_NAME");
        String ERP_REPAIR_ORDER_ID = request.getParameter("selRowId");
        try {
            
            List <RepairappModelChld> list = repairappService.childQuery(ERP_REPAIR_ORDER_ID);
            
            params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
            params.put("UPDATOR", userBean.getYHBH());
            params.put("UPD_NAME", userBean.getXM());
           
            
            if("commit".equals(type)){
            	params.put("STATE", BusinessConsts.COMMIT);
	            jsonResult = jsonResult(true, "提交成功");
            }else if("auditPass".equals(type)){
            	params.put("STATE", BusinessConsts.PASS);
            	
            	params.put("SHIP_POINT_ID", SHIP_POINT_ID);
            	params.put("SHIP_POINT_NAME", ParamUtil.utf8Decoder(request,"SHIP_POINT_NAME"));
	            jsonResult = jsonResult(true, "审核通过成功");
            }else{
            	params.put("STATE", BusinessConsts.ORDER_BACK);
	            jsonResult = jsonResult(true, "退回成功");
            }
            
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            
            repairappService.txSubmitById(params);
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
	    	return  pvgMap;
	   }
	 /**
		 * 
		 * 权限获取 重写
		 * @return
		 */
		 
		 public String getQXString(String search,String module,String pvgBws, String pvgAudit,
					String pkId, String tableName, String businessType,String state,UserBean userBean) {
			 StringBuffer tmp=new StringBuffer("");
			 if (!StringUtil.isEmpty(module))
	         {
	        	if ("sh".equals(module)) {//审核
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
	    			} else {//没有查询条件下只有能看见提交和审核通过的记录
	    				
	    				tmp.append(state);
	    				tmp.append(" in ('");
        				tmp.append(BusinessConsts.COMMIT);
        				tmp.append("','");
        				tmp.append(BusinessConsts.PASS);
        				tmp.append("')");
        				tmp.append(" and ");
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgAudit));
	    			}
	    		} else {//申请维护
	    			if (StringUtil.isEmpty(search)) {
	    				tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
	    				if(!StringUtil.isEmpty(state))
	    				{
	    					tmp.append(" and ");
	        				tmp.append(state);
	        				tmp.append(" ='");
	        				tmp.append(BusinessConsts.UNCOMMIT);
	        				tmp.append("'");
	    				}
	    			} else {
	    				
	    				tmp.append(state);
	    				tmp.append(" in ('未提交','提交','关闭','审核通过','待发货','已发货','总部收货确认','总部已发货')");
	    				tmp.append(" and ");
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