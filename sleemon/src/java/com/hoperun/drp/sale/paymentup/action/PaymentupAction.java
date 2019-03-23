/**
 * prjName:喜临门营销平台
 * ucName:付款凭证上传
 * fileName:PaymentupAction
*/
package com.hoperun.drp.sale.paymentup.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.paymentup.model.PaymentupModel;
import com.hoperun.drp.sale.paymentup.service.PaymentupService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-23 10:25:58
 */
public class PaymentupAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0020601";
    private static String PVG_EDIT="FX0020602";
    private static String PVG_DELETE="FX0020603";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0020604";
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
	private PaymentupService paymentupService;
    /**
	 * Sets the Paymentup service.
	 * 
	 * @param PaymentupService the new Paymentup service
	 */
	public void setPaymentupService(PaymentupService paymentupService) {
		this.paymentupService = paymentupService;
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
		//权限级别和审批流的封装和状态过滤
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	  //初始时显示提交的数据
		if(StringUtil.isEmpty(search))
	    { 
			params.put("STATETAB","初始页面");
	    }
		else
		{
			ParamUtil.putStr2Map(request, "STATE", params);
		}
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = paymentupService.pageQuery(params, pageNo);
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
		Map<String,String> entry =new HashMap<String,String>();
		if(!StringUtil.isEmpty(PAYMENT_UPLOAD_ID)){
			entry = paymentupService.load(PAYMENT_UPLOAD_ID);
		}else{
			entry.put("REQ_PSON_ID", userBean.getRYXXID());
			entry.put("REQ_PSON_NAME", userBean.getXM());
			entry.put("CHANN_ID", userBean.getCHANN_ID());
			entry.put("CHANN_NO", userBean.getCHANN_NO());
			entry.put("CHANN_NAME", userBean.getCHANN_NAME());
			Map<String,String> chann = paymentupService.loadChann(userBean.getCHANN_ID());
            entry.put("AREA_ID", chann.get("AREA_ID"));
            entry.put("AREA_NO", chann.get("AREA_NO"));
            entry.put("AREA_NAME", chann.get("AREA_NAME"));
		}
		entry.put("LEDGER_ID", userBean.getLoginZTXXID());//查询条件传值
		request.setAttribute("rst", entry);
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
			PaymentupModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<PaymentupModel>(){}.getType());
			}
			String PAYMENT_UPLOAD_ID = ParamUtil.get(request, "PAYMENT_UPLOAD_ID");
			if(StringUtils.isEmpty(PAYMENT_UPLOAD_ID)&&(paymentupService.checkPAYMENT_NO(aModel.getPAYMENT_NO())>0)){
				jsonResult = jsonResult(false, "凭证号有重复，请重新输入");
	        	if (null != writer) {
					writer.write(jsonResult);
					writer.close();
				}
			}else{
				paymentupService.txEdit(PAYMENT_UPLOAD_ID,aModel,userBean);
			}
			
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
			paymentupService.txDelete(params);
			paymentupService.clearCaches(params);
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
			Map<String,String> entry = paymentupService.load(PAYMENT_UPLOAD_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
     *提交.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PAYMENT_UPLOAD_ID = request.getParameter("PAYMENT_UPLOAD_ID");
            
            Map <String, String> params = new HashMap <String, String>();
            params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", "数据库时间");
            params.put("STATE", BusinessConsts.COMMIT);
            paymentupService.commit(params);
        } catch (Exception e) {
        	e.printStackTrace();
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
	    	return  pvgMap;
	   }
}