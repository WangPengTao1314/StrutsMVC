package com.hoperun.drp.sale.advccancelsapp.action;

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
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModel;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModelChld;
import com.hoperun.drp.sale.advccancelsapp.service.AdvccancelsappService;
import com.hoperun.drp.sale.advcgoodsapp.action.AdvcgoodsappAction;
import com.hoperun.sys.model.UserBean;

public class AdvccancelsappAction  extends BaseAction {
	private Logger logger = Logger.getLogger(AdvcgoodsappAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS = "FX0022001";
    private static String PVG_EDIT = "FX0022002";
    private static String PVG_DELETE = "FX0022003";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE = "FX0022004";
    private static String PVG_TRACE = "FX0022005";
    //审核模块
    private static String PVG_BWS_AUDIT = "FX0022101";
    private static String PVG_AUDIT_AUDIT = "FX0022102";
    private static String PVG_TRACE_AUDIT = "";
    //审批流参数
    private static String AUD_TAB_NAME = "DRP_ADVC_SEND_CANCEL";
    private static String AUD_TAB_KEY = "ADVC_SEND_CANCEL_ID";
    private static String AUD_BUSS_STATE = "";	
    private static String AUD_BUSS_TYPE = "DRP_ADVC_SEND_CANCEL_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.impl.AdvccancelsappFlowInterfaceImpl";
    /**审批 end**/
	
    /** 业务service*/
	private AdvccancelsappService advccancelsappService;
	
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))) {
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "ADVC_SEND_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
        ParamUtil.putStr2Map(request, "TEL", params);
        ParamUtil.putStr2Map(request, "ADVC_SEND_CANCEL_NO", params);
        ParamUtil.putStr2Map(request, "CUST_NAME", params);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE = ParamUtil.get(request,"STATE");
		
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		//权限级别和审批流的封装
		if(module.equals("wh")){
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
		 
		params.put("QXJBCON", qx.toString());
	    
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advccancelsappService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_SEND_CANCEL_ID =ParamUtil.get(request, "ADVC_SEND_CANCEL_ID");
        if(!StringUtil.isEmpty(ADVC_SEND_CANCEL_ID)){
        	 List <AdvccancelsappModelChld> result = advccancelsappService.childQuery(ADVC_SEND_CANCEL_ID);
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
		String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(ADVC_SEND_CANCEL_ID)){
			entry= advccancelsappService.load(ADVC_SEND_CANCEL_ID);
		} 
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("rst", entry);
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
        String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID");
        if(!StringUtil.isEmpty(ADVC_SEND_CANCEL_ID)){
        	 List <AdvccancelsappModelChld> result = advccancelsappService.childQuery(ADVC_SEND_CANCEL_ID);
             request.setAttribute("rst", result);
             
        }
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
        String ADVC_SEND_CANCEL_DTL_IDS = request.getParameter("ADVC_SEND_CANCEL_DTL_IDS");
        if (!StringUtil.isEmpty(ADVC_SEND_CANCEL_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_SEND_CANCEL_DTL_IDS",ADVC_SEND_CANCEL_DTL_IDS);
          List <AdvccancelsappModelChld> list =  this.advccancelsappService.loadChilds(params);
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
            String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            AdvccancelsappModel model = new Gson().fromJson(parentJsonData, new TypeToken <AdvccancelsappModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvccancelsappModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvccancelsappModelChld>>() {
                }.getType());
            }
            advccancelsappService.txEdit(ADVC_SEND_CANCEL_ID, model, chldModelList, userBean);
        }catch(ServiceException s){
        	 jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            logger.info(e);
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
            String ADVC_SEND_CANCEL_ID = request.getParameter("ADVC_SEND_CANCEL_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <AdvccancelsappModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvccancelsappModelChld>>() {
                }.getType());
                advccancelsappService.txChildEdit(ADVC_SEND_CANCEL_ID, modelList);
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
			String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            advccancelsappService.txDelete(params);
            advccancelsappService.clearCaches(params);
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
            String ADVC_SEND_CANCEL_DTL_IDS = request.getParameter("ADVC_SEND_CANCEL_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            advccancelsappService.txBatch4DeleteChild(ADVC_SEND_CANCEL_DTL_IDS);
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
		String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID");
		if(!StringUtil.isEmpty(ADVC_SEND_CANCEL_ID)){
			Map<String,String> entry = advccancelsappService.load(ADVC_SEND_CANCEL_ID);
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
        	String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID"); 
            List<AdvccancelsappModelChld> childList = advccancelsappService.childQuery(ADVC_SEND_CANCEL_ID);
            if(null == childList || childList.isEmpty()){
            	errorId = "0";
            	throw new Exception("没有明细，不能提交!");
            }else{
            	this.advccancelsappService.txCommit(ADVC_SEND_CANCEL_ID, userBean);
            	jsonResult = jsonResult(true, "操作成功");
            }
            
        } catch (Exception e) {
        	logger.info(e);
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else {
                jsonResult = jsonResult(false, "操作失败");
            }
           
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * * 审核
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toAudit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
        	String ADVC_SEND_CANCEL_ID = ParamUtil.get(request, "ADVC_SEND_CANCEL_ID"); 
        	String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID"); 
            this.advccancelsappService.txAudit(ADVC_SEND_CANCEL_ID,ADVC_SEND_REQ_ID, userBean);
            jsonResult = jsonResult(true, "操作成功");
            
        } catch (Exception e) {
        	logger.info(e);
            jsonResult = jsonResult(false, "操作失败");
           
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
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 
		public AdvccancelsappService getAdvccancelsappService() {
			return advccancelsappService;
		}
		public void setAdvccancelsappService(AdvccancelsappService advccancelsappService) {
			this.advccancelsappService = advccancelsappService;
		}
	 
	 
	 
	 
	 

}
