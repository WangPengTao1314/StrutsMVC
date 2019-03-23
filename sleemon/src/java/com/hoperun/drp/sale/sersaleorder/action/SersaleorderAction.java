package com.hoperun.drp.sale.sersaleorder.action;
import java.io.PrintWriter;
import java.rmi.ServerException;
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
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModel;
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModelChld;
import com.hoperun.drp.sale.sersaleorder.service.SersaleorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@func 分销-区域服务中心-销售订单维护
 * *@version 1.1
 * *@author zzb
 * *@createdate 2014-5-23 13:52:19
 */
public class SersaleorderAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(SersaleorderAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0070201";
    private static String PVG_EDIT="FX0070202";
    private static String PVG_DELETE="?";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0070203";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="?";
    private static String PVG_AUDIT_AUDIT="?";
    private static String PVG_TRACE_AUDIT="?";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_SALE_ORDER";
    private static String AUD_TAB_KEY="SALE_ORDER_ID";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="DRP_SALE_ORDER_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.sale.sersaleorder.service.impl.SersaleorderFlowInterface";
    /**审批 end**/
	
    /** 业务service*/
	private SersaleorderService sersaleorderService;
	
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)))
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE='未提交' ");
		}
		//权限级别和审批流的封装
	    params.put("QXJBCON", sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = sersaleorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_ORDER_ID =ParamUtil.get(request, "SALE_ORDER_ID");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SersaleorderModelChld> result = sersaleorderService.childQuery(SALE_ORDER_ID);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
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
		String SALE_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			Map<String,String> entry = sersaleorderService.load(SALE_ORDER_ID);
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
        String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SersaleorderModelChld> result = sersaleorderService.childQuery(SALE_ORDER_ID);
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
        String SALE_ORDER_DTL_IDs = request.getParameter("SALE_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(SALE_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDs);
          List <SersaleorderModelChld> list = sersaleorderService.loadChilds(params);
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
            String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            SersaleorderModel model = new Gson().fromJson(parentJsonData, new TypeToken <SersaleorderModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <SersaleorderModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <SersaleorderModelChld>>() {
                }.getType());
            }
            sersaleorderService.txEdit(SALE_ORDER_ID, model, chldModelList, userBean);
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
            String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <SersaleorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <SersaleorderModelChld>>() {
                }.getType());
                sersaleorderService.txChildEdit(SALE_ORDER_ID, modelList);
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
			String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_ID", SALE_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            sersaleorderService.txDelete(params);
            sersaleorderService.clearCaches(params);
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
            String SALE_ORDER_DTL_IDs = request.getParameter("SALE_ORDER_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            sersaleorderService.txBatch4DeleteChild(SALE_ORDER_DTL_IDs);
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
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			Map<String,String> entry = sersaleorderService.load(SALE_ORDER_ID);
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
            String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
            List <SersaleorderModelChld> list = sersaleorderService.childQuery(SALE_ORDER_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }else{
            	sersaleorderService.txCommit(SALE_ORDER_ID,userBean);
            	jsonResult = jsonResult(true, "提交成功");
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
     * 转非标订单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void convert(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	boolean isAll = ParamUtil.getBoolean(request, "isAll");
        	this.sersaleorderService.txConvertTechOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,isAll,userBean);
        	jsonResult = jsonResult(true,"转非标订单成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"转非标订单失败");
        	e.printStackTrace();
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
    /**
     * 取消预定
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void cancelOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String FROM_BILL_DTL_IDS = ParamUtil.get(request, "FROM_BILL_DTL_IDS");
        	if(StringUtil.isEmpty(SALE_ORDER_DTL_IDS)){
        		throw new ServerException("明细ID为空");
        	}
        	this.sersaleorderService.txCalcelSaleOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
        	logger.info(e);
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
    /**
     * 恢复预定
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void recoverOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
        	String SALE_ORDER_DTL_ID = ParamUtil.get(request, "SALE_ORDER_DTL_ID");
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String FROM_BILL_DTL_IDS = ParamUtil.get(request, "FROM_BILL_DTL_IDS");
        	this.sersaleorderService.txRecoverOrder(SALE_ORDER_ID,SALE_ORDER_DTL_ID,FROM_BILL_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
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
		public SersaleorderService getSersaleorderService() {
			return sersaleorderService;
		}
		public void setSersaleorderService(SersaleorderService sersaleorderService) {
			this.sersaleorderService = sersaleorderService;
		}
	 
	 
	 
}