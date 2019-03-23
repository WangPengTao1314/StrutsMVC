/**
 * prjName:喜临门营销平台
 * ucName:付款凭证确认
 * fileName:PaymentcmtAction
*/
package com.hoperun.erp.sale.paymentcmt.action;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.erp.sale.paymentcmt.model.PaymentcmtModel;
import com.hoperun.erp.sale.paymentcmt.service.PaymentcmtService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func付款凭证
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-25 09:50:09
 */
public class PaymentcmtAction extends BaseAction {
	private Logger logger = Logger.getLogger(PaymentcmtAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0011001";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    //打回
    private static String PVG_REVERSE="BS0011002";
    //确认
    private static String PVG_VERIFY="BS0011003";
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
    private static String AUD_BUSS_STATE="";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private PaymentcmtService paymentcmtService;
    /**
	 * Sets the Paymentcmt service.
	 * 
	 * @param PaymentcmtService the new Paymentcmt service
	 */
	public void setPaymentcmtService(PaymentcmtService paymentcmtService) {
		this.paymentcmtService = paymentcmtService;
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
		ParamUtil.putStr2Map(request, "PAYMENT_NO", params);
	   ParamUtil.putStr2Map(request, "AREA_NAME", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
	   ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	   ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
       ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
       ParamUtil.putStr2Map(request, "AREA_NO", params);
       ParamUtil.putStr2Map(request, "CHANN_NO", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE = ParamUtil.get(request,"STATE");
		
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
	    //初始时显示提交的数据
		if(StringUtil.isEmpty(search)) { 
			qx.append(" and u.STATE = '提交' ");
	    } else if(StringUtil.isEmpty(STATE)){
	    	qx.append(" and u.STATE in('提交','退回','确认') ");
		}else{
			params.put("STATE",STATE);
		}
		
		//权限级别和审批流的封装和状态过滤
	    params.put("QXJBCON", qx.toString());
	    
	    //区域分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    params.put("CHANNS_CHARG", CHANNS_CHARG);
	    
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = paymentcmtService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 * * to 编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PAYMENT_UPLOAD_ID = ParamUtil.get(request, "PAYMENT_UPLOAD_ID");
		if(!StringUtil.isEmpty(PAYMENT_UPLOAD_ID)){
			Map<String,String> entry = paymentcmtService.load(PAYMENT_UPLOAD_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("toedit");
	}
	
	/**
	 * * 新增/修改数据
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
			String jsonData = ParamUtil.get(request, "jsonData");
			PaymentcmtModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<PaymentcmtModel>(){}.getType());
			}
			String PAYMENT_UPLOAD_ID = ParamUtil.get(request, "PAYMENT_UPLOAD_ID");
			paymentcmtService.txEdit(PAYMENT_UPLOAD_ID,aModel,userBean);
		} catch (Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败!");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
    /**
	 * * 删除
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
			String PAYMENT_UPLOAD_ID = ParamUtil.get(request, "PAYMENT_UPLOAD_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			paymentcmtService.txDelete(params);
			paymentcmtService.clearCaches(params);
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
		String PAYMENT_UPLOAD_ID = ParamUtil.get(request, "PAYMENT_UPLOAD_ID");
		if(!StringUtil.isEmpty(PAYMENT_UPLOAD_ID)){
			Map<String,String> entry = paymentcmtService.load(PAYMENT_UPLOAD_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	/**
     *确认/打回.
     * 状态修改
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toEditState(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	String stateTab=request.getParameter("stateTab");
    	if(stateTab.equals("verify")){
    		if(!QXUtil.checkMKQX(userBean, PVG_VERIFY))
        	{
        		throw new ServiceException("对不起，您没有权限 !");
        	}
        }else{
        	if(!QXUtil.checkMKQX(userBean, PVG_REVERSE))
        	{
        		throw new ServiceException("对不起，您没有权限 !");
        	}
        }
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PAYMENT_UPLOAD_ID = request.getParameter("PAYMENT_UPLOAD_ID");
            String REMARK = ParamUtil.utf8Decoder(request, "REMARK");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
            params.put("AUDIT_ID", userBean.getXTYHID());
            params.put("AUDIT_NAME", userBean.getXM());
            params.put("AUDIT_TIME", "数据库时间");
            if(stateTab.equals("verify")){
            	params.put("STATE", "确认");
            }else{
            	params.put("STATE", "退回");
            	params.put("REMARK", REMARK);
            }
            
            paymentcmtService.txPaymentComit(params);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
        	logger.info(e);
            jsonResult = jsonResult(false, "状态修改失败");
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
			pvgMap.put("PVG_REVERSE", QXUtil.checkPvg(userBean, PVG_REVERSE));
			pvgMap.put("PVG_VERIFY", QXUtil.checkPvg(userBean, PVG_VERIFY));
	    	return  pvgMap;
	   }
}