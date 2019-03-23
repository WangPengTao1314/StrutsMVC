/**
 * prjName:喜临门营销平台
 * ucName:客户付款单
 * fileName:PaymentAction
*/
package com.hoperun.erp.sale.payment.action;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.erp.sale.payment.model.PaymentModel;
import com.hoperun.erp.sale.payment.service.PaymentService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-15 09:31:13
 */

public class PaymentAction extends BaseAction {
	private Logger logger = Logger.getLogger(PaymentAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0012801";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
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
	private PaymentService paymentService;
    /**
	 * Sets the Payment service.
	 * 
	 * @param PaymentService the new Payment service
	 */
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
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
	   ParamUtil.putStr2Map(request, "CHANN_NO", params);
	   ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	   ParamUtil.putStr2Map(request, "AREA_NO", params);
	   ParamUtil.putStr2Map(request, "AREA_NAME", params);
	   ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
       ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装和状态过滤
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		String CHANNS_CHARG  = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.PAY_DATE,PAYMENT_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = paymentService.pageQuery(params, pageNo);
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
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PAYMENT_ID = ParamUtil.get(request, "PAYMENT_ID");
		if(!StringUtil.isEmpty(PAYMENT_ID)){
			Map<String,String> entry = paymentService.load(PAYMENT_ID);
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
			PaymentModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<PaymentModel>(){}.getType());
			}
			String PAYMENT_ID = ParamUtil.get(request, "PAYMENT_ID");
			paymentService.txEdit(PAYMENT_ID,aModel,userBean);
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
			String PAYMENT_ID = ParamUtil.get(request, "PAYMENT_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PAYMENT_ID", PAYMENT_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			paymentService.txDelete(params);
			paymentService.clearCaches(params);
		} catch (Exception e) {
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
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PAYMENT_ID = ParamUtil.get(request, "PAYMENT_ID");
		if(!StringUtil.isEmpty(PAYMENT_ID)){
			Map<String,String> entry = paymentService.load(PAYMENT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
	 
	 
	 public void InstPayMentDtl(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 String jsonResult = jsonResult();
		 PrintWriter writer = getWriter(response);
		 try {
			String jsonData = ParamUtil.get(request, "jsonData");
			PaymentModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<PaymentModel>(){}.getType());
				String strChannId = aModel.getCHANN_ID();
				String payAmont = aModel.getPAY_AMONT();
				String strDate = DateUtil.formatDateToStr(DateUtil.getDate());
				String strRemark = aModel.getREMARK();
				
            	HashMap inputMap = new HashMap();
            	HashMap headMap = new HashMap();
            	headMap.put("ServiceCode", "OrderManage");
            	headMap.put("Operation", "CreateOrder");
            	headMap.put("AppCode", "erp");
            	headMap.put("UId", "414d5120514d5f6c6f63616c202020203baa474c20012802");
            	headMap.put("AuthId", "erp;password");
            	ArrayList headList = new ArrayList();
            	headList.add(headMap);
            	inputMap.put("MbfHeader", headList);
            	
            	HashMap bodyMap = new HashMap();
            	bodyMap.put("PAYMENT_NO",LogicUtil.getBmgz("ERP_PAYMENT_NO"));
            	bodyMap.put("CHANN_NO", strChannId);
            	bodyMap.put("PAY_AMONT", payAmont);
            	bodyMap.put("PAY_DATE", strDate);
            	bodyMap.put("REMARK", strRemark);
            	bodyMap.put("LEDGER_NO", strChannId);
            	ArrayList bodyList = new ArrayList();
            	bodyList.add(bodyMap);
            	inputMap.put("MbfBody", bodyList);
				
            	HashMap serMap = new HashMap();
            	ArrayList inputList = new ArrayList();
            	inputList.add(inputMap);
            	serMap.put("input1", inputList);
            	
            	HashMap jsonMap = new HashMap();
            	ArrayList serList = new ArrayList();
            	serList.add(serMap);
            	jsonMap.put("MbfService", serList);
            	String impJsonData = new Gson().toJson(jsonMap);
            	
            	String msg =LogicUtil.createPayMent(Consts.DM_USERNAME,Consts.DM_PASSWORD,impJsonData);
            	InterReturnMsg returnMsg = new Gson().fromJson(msg,InterReturnMsg.class);
            	//修改equals参数，调换returnMsg.getFLAG()位置，不然returnMsg.getFLAG()为空时报错
            	//刘曰刚 2014-06-17
            	if(BusinessConsts.FLASE.equals(returnMsg.getFLAG())){
            		jsonResult = jsonResult(false, returnMsg.getMESSAGE());
            	}else{
            		jsonResult = jsonResult(true, msg);
            	}
				//LogicUtil.doRecivePayment(strChannId,payAmont,strDate,strRemark);
			}
		} catch (Exception e){
			logger.info(e);
			jsonResult = jsonResult(false, "保存失败!");
			e.printStackTrace();
		}
		 if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
}