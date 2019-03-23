/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappAction
*/
package com.hoperun.drp.sale.advcgoodsapp.action;
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
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModel;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.drp.sale.advcgoodsapp.service.AdvcgoodsappService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-11-02 18:55:53
 */
public class AdvcgoodsappAction extends BaseAction {
	private Logger logger = Logger.getLogger(AdvcgoodsappAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0021501";
    private static String PVG_EDIT="FX0021502";
    private static String PVG_DELETE="FX0021504";
    
    //审核浏览
    private static String PVG_SBWS="FX0021701";
    //审核通过
    private static String PVG_COMMIT_SCANCLE="FX0021702";
    //审核打回
    private static String PVG_REVERSE="FX0021703";
    //修改要求到货日期
    private static String PVG_EDIT_DATE="FX0021704";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021503";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_STATE="";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvcgoodsappService advcgoodsappService;
    /**
	 * Sets the Advcgoodsapp service.
	 * 
	 * @param AdvcgoodsappService the new Advcgoodsapp service
	 */
	public void setAdvcgoodsappService(AdvcgoodsappService advcgoodsappService) {
		this.advcgoodsappService = advcgoodsappService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_SBWS)))
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_SBWS)))
    	{
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
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
        ParamUtil.putStr2Map(request, "CONTRACT_NO", params);//合同编号
        ParamUtil.putStr2Map(request, "CUST_NAME", params);//客户名称
        ParamUtil.putStr2Map(request, "TEL", params);//电话
        
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String searchSTATE="";
		String allSTATE="";
		String menu="";
		//权限级别和审批流的封装
		if(module.equals("wh")){
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			searchSTATE="'"+BusinessConsts.UNCOMMIT+"','退回'";
			allSTATE="'未提交','提交','退回','已发货','审核通过','已取消' ";
			menu="终端销售>>预订单发货申请";
		}else if(module.equals("sh")){
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_SBWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			searchSTATE="'"+BusinessConsts.COMMIT+"'";
			allSTATE="'审核通过','提交','已发货','退回','已取消'";
			menu="预订单处理>>预订单发货审核";
		}
	    //初始化页面查询
	    if(StringUtil.isEmpty(search)){
	    	params.put("searchSTATE", searchSTATE);
	    }
	    params.put("allSTATE", allSTATE);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME DESC,u.ADVC_SEND_REQ_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advcgoodsappService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("menu", menu);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_SBWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_SEND_REQ_ID =ParamUtil.get(request, "ADVC_SEND_REQ_ID");
        if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID))
        {
        	 List <AdvcgoodsappModelChld> result = advcgoodsappService.childQuery(ADVC_SEND_REQ_ID);
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
		String ADVC_SEND_REQ_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry=new HashMap<String,String>();
		if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID)){
			entry= advcgoodsappService.load(ADVC_SEND_REQ_ID);
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("SEND_CHANN_ID", userBean.getCHANN_ID());
		map.put("SEND_CHANN_NO", userBean.getCHANN_NO());
		map.put("SEND_CHANN_NAME", userBean.getCHANN_NAME());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("rst", entry);
		request.setAttribute("channInfo", map);
		request.setAttribute("TERM_CHARGE", userBean.getTERM_CHARGE());
		//add by zhuxw 20140717 预订单通用选取查询条件
		request.setAttribute("BEN_DETP_ID", userBean.getBMXXID());
		if("1".equals(userBean.getEMPY_FLAG()))
		{
			request.setAttribute("CONDISION", "TERM_ID='"+userBean.getBMXXID()+"'");
		}else
		{
			request.setAttribute("CONDISION", "CREATOR='"+userBean.getXTYHID()+"'");
		}
		
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		
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
        String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID");
        if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID))
        {
        	StringBuffer QUERYID=new StringBuffer();
        	QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_ADVC_SEND_REQ_DTL a where a.ADVC_SEND_REQ_ID='").
        			append(ADVC_SEND_REQ_ID).
        			append("' and DEL_FLAG=0 ");
        	String dtlId= advcgoodsappService.qeuryId(QUERYID.toString());
        	 List <AdvcgoodsappModelChld> result = advcgoodsappService.childQuery(ADVC_SEND_REQ_ID);
             request.setAttribute("rst", result);
             request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
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
    	String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID");
        String ADVC_SEND_REQ_DTL_IDs = request.getParameter("ADVC_SEND_REQ_DTL_IDS");
        StringBuffer QUERYID=new StringBuffer();
        //0156501--start 刘曰刚 --2014-06-18//
        if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID)){
        	QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_ADVC_SEND_REQ_DTL a where a.ADVC_SEND_REQ_ID='").
			append(ADVC_SEND_REQ_ID).append("' and a.DEL_FLAG=0 ");
        }
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(ADVC_SEND_REQ_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_SEND_REQ_DTL_IDS",ADVC_SEND_REQ_DTL_IDs);
		  params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
          List <AdvcgoodsappModelChld> list = advcgoodsappService.loadChilds(params);
          request.setAttribute("rst", list);
          QUERYID.append(" and ADVC_SEND_REQ_DTL_ID not in (").append(ADVC_SEND_REQ_DTL_IDs).append(")");
        }
        String dtlId= advcgoodsappService.qeuryId(QUERYID.toString());
    	request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
    	 //0156501--End 刘曰刚 --2014-06-18//
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
            String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            AdvcgoodsappModel model = new Gson().fromJson(parentJsonData, new TypeToken <AdvcgoodsappModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvcgoodsappModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvcgoodsappModelChld>>() {
                }.getType());
            }
            ADVC_SEND_REQ_ID=advcgoodsappService.txEdit(ADVC_SEND_REQ_ID, model, chldModelList, userBean);
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
            String ADVC_SEND_REQ_ID = request.getParameter("ADVC_SEND_REQ_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <AdvcgoodsappModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvcgoodsappModelChld>>() {
                }.getType());
                advcgoodsappService.txChildEdit(ADVC_SEND_REQ_ID, modelList,"list",userBean);
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
			String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			advcgoodsappService.txDelete(params,userBean);
			advcgoodsappService.clearCaches(params);
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
            String ADVC_SEND_REQ_DTL_IDs = request.getParameter("ADVC_SEND_REQ_DTL_IDS");
            String ADVC_SEND_REQ_ID=request.getParameter("ADVC_SEND_REQ_ID");
            //批量删除，多个Id之间用逗号隔开
            advcgoodsappService.txBatch4DeleteChild(ADVC_SEND_REQ_DTL_IDs,userBean,ADVC_SEND_REQ_ID);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_SBWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String ADVC_SEND_REQ_ID = ParamUtil.get(request, "ADVC_SEND_REQ_ID");
		if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID)){
			Map<String,String> entry = advcgoodsappService.load(ADVC_SEND_REQ_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	
    public void checkExsitChangeAdvcOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	String ADVC_ORDER_ID = ParamUtil.utf8Decoder(request, "ADVC_ORDER_ID");
    	if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
    		String message = advcgoodsappService.checkChangeOrder(ADVC_ORDER_ID);
             if(!"true".equals(message)){
            	 jsonResult = jsonResult(false, message);
             }else{
            	 jsonResult = jsonResult(true, "");
             }
    	}else{
    		jsonResult = jsonResult(true, "");
    	}
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    public void checkAmount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	String ADVC_SEND_REQ_ID = ParamUtil.utf8Decoder(request, "ADVC_SEND_REQ_ID");
    	try {
    		if(!StringUtil.isEmpty(ADVC_SEND_REQ_ID)){
        		int count = advcgoodsappService.checkAmount(ADVC_SEND_REQ_ID);
                 if(count>0){
                	 jsonResult = jsonResult(false, "1");
                 }else{
                	 jsonResult = jsonResult(true, "");
                 }
        	}else{
        		jsonResult = jsonResult(false, "1");
        	}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "提交失败");
			e.printStackTrace();
		}
    	
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    public void upOrderDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String jsonResult = jsonResult();
    	Map<String,String> map= new HashMap<String, String>();
    	map.put("ADVC_SEND_REQ_ID", ParamUtil.utf8Decoder(request, "ADVC_SEND_REQ_ID"));
    	map.put("ORDER_RECV_DATE", ParamUtil.utf8Decoder(request, "ORDER_RECV_DATE"));
    	map.put("REMARK", ParamUtil.utf8Decoder(request, "REMARK"));
    	map.put("UPDATOR", userBean.getXTYHID());
    	map.put("UPD_NAME", userBean.getXM());
    	map.put("UPD_TIME", "sysdate");
    	try {
    		boolean falg =advcgoodsappService.upOrderDate(map);
        	if(falg){
        		jsonResult = jsonResult(true, "");
        	}else{
        		jsonResult = jsonResult(false, "修改失败");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "修改失败");
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
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_SCANCLE)&&!QXUtil.checkMKQX(userBean, PVG_REVERSE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
        	String type = ParamUtil.utf8Decoder(request, "type");
        	String RETURN_RSON=ParamUtil.utf8Decoder(request, "RETURN_RSON");
        	String SEND_TYPE = ParamUtil.utf8Decoder(request, "SEND_TYPE");
            String ADVC_SEND_REQ_ID = ParamUtil.utf8Decoder(request, "ADVC_SEND_REQ_ID");
            List <AdvcgoodsappModelChld> dtlList = advcgoodsappService.childQuery(ADVC_SEND_REQ_ID);
            if (dtlList.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            Map<String,String> map=new HashMap<String,String>();
            map.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
            map.put("UPDATOR", userBean.getRYXXID());
            map.put("UPD_NAME", userBean.getXM());
            map.put("UPD_TIME", "sysdate");
            map.put("SEND_TYPE", SEND_TYPE);
            if(type.equals("退回")){
            	map.put("RETURN_RSON", RETURN_RSON);
            }
            if("终端发货".equals(SEND_TYPE)){
            	map.put("STATE", "已发货");
            }else{
            	map.put("STATE", type);
            }
            boolean flg = advcgoodsappService.txUpdateById(map,userBean,dtlList);
            if(!flg){
            	jsonResult = jsonResult(false, "库存不够!");
            }
            
        } catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        }  catch (Exception e) {
        	logger.info(e);
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("1".equals(errorId)){
            	jsonResult = jsonResult(false, "通知出库数量与冻结数量不等，不能提交发货!");
            } else {
                jsonResult = jsonResult(false, "操作失败");
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
	    	pvgMap.put("PVG_SBWS",QXUtil.checkPvg(userBean, PVG_SBWS) );
	        pvgMap.put("PVG_COMMIT_SCANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_SCANCLE) );
	        pvgMap.put("PVG_REVERSE",QXUtil.checkPvg(userBean, PVG_REVERSE) );
	        pvgMap.put("PVG_EDIT_DATE",QXUtil.checkPvg(userBean, PVG_EDIT_DATE) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}