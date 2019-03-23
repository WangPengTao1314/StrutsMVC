package com.hoperun.drp.finance.advcverify.action;

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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advcverify.model.WriteOffDtlModel;
import com.hoperun.drp.finance.advcverify.service.AdvcverifyService;
import com.hoperun.sys.model.UserBean;

public class AdvcverifyAction extends BaseAction{
	
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0050401";
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
    private static String AUD_BUSS_STATE="";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
	
	private AdvcverifyService advcverifyService;

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
	    ParamUtil.putStr2Map(request, "STATEMENTS_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_DATE_GEGIN", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
//	    ParamUtil.putStr2Map(request, "PAY_TYPE", params);
	    ParamUtil.putStr2Map(request, "PAY_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "ADVC_BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE = ParamUtil.get(request,"STATE");
		String PAY_TYPE = ParamUtil.get(request,"PAY_TYPE");
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(!StringUtil.isEmpty(PAY_TYPE)){
			String con = StringUtil.creCon("  b.PAY_TYPE", PAY_TYPE, ",");
			sb.append(con);
		}
		sb.append(" and u.STATE in  ('已核销','已结算') ");
		// 已核销 查询 核销金额等于sum明细金额
		if(BusinessConsts.STATE_CHECK_PAY.equals(STATE)){
			params.put("STATE_CHECK_PAY","1");
		}
		// 已核销 查询 核销金额大于sum明细金额
		if("未核销".equals(STATE) || StringUtil.isEmpty(search)){
			params.put("gt_zero","1");
		}
		params.put("STATE",STATE);
		//权限级别和审批流的封装
	    params.put("QXJBCON",sb.toString());
	    //只查核销的付款方式
	    params.put("IS_DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME DESC,u.STATEMENTS_ID,b.STATEMENTS_PAYMENT_DTL_ID", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advcverifyService.pageQuery(params, pageNo);
		params.put("PAY_TYPE", PAY_TYPE);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	/**
	 * 核销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void checkPayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserBean userBean =  ParamUtil.getUserBean(request);
		String STATEMENTS_IDS = ParamUtil.get(request, "STATEMENTS_IDS");
		String STATEMENTS_PAYMENT_DTL_IDS = ParamUtil.get(request, "IDS");
		String jsonData = ParamUtil.get(request, "jsonData");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult(); 
		try{
			List<WriteOffDtlModel> delModelList =  
				new Gson().fromJson(jsonData, new TypeToken <List <WriteOffDtlModel>>() {
            }.getType());
			//先检查单据的状态是不是能核销
			int rst = this.advcverifyService.checkState(STATEMENTS_IDS,"'已核销','已结算'");
			if(rst == 0){
				throw new ServiceException("有单据状态已改，请刷新页面");
			}
			this.advcverifyService.txCheckPayment(STATEMENTS_IDS,STATEMENTS_PAYMENT_DTL_IDS,delModelList, userBean);
			jsonResult = jsonResult(true,"操作成功");
			
		}catch(ServiceException e){
			jsonResult = jsonResult(false,e.getMessage());
		}catch(Exception e){
			jsonResult = jsonResult(false,"操作失败");
			e.printStackTrace();
		}
		
		if(null != writer){
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	/**
	 * 反核销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void unCheckPayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserBean userBean =  ParamUtil.getUserBean(request);
		String STATEMENTS_PAYMENT_DTL_ID = ParamUtil.get(request, "STATEMENTS_PAYMENT_DTL_ID");
		String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
		String WRITE_OFF_DTL_IDS = ParamUtil.get(request, "WRITE_OFF_DTL_IDS");
		String WRITE_OFF_PSON_TIMES = ParamUtil.get(request, "WRITE_OFF_PSON_TIMES");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult(); 
		try{
			String[] bussDate = WRITE_OFF_PSON_TIMES.split(",");
			for(int i=0;i<bussDate.length;i++){
				boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),bussDate[i]);
				if(isMonthAcc){
					throw new ServiceException(bussDate[i]+"日期已经月结，不能反核销");
				}
			}
			
			this.advcverifyService.txUnCheckPayment(STATEMENTS_ID,STATEMENTS_PAYMENT_DTL_ID,WRITE_OFF_DTL_IDS,userBean);
			jsonResult = jsonResult(true,"操作成功");
			
		}catch(ServiceException e){
			jsonResult = jsonResult(false,e.getMessage());
		}catch(Exception e){
			jsonResult = jsonResult(false,"操作失败");
			e.printStackTrace();
		}
		
		if(null != writer){
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	public ActionForward toUnCheckPayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		 
        String STATEMENTS_PAYMENT_DTL_ID = ParamUtil.get(request, "STATEMENTS_PAYMENT_DTL_ID");
        List<Map<String,Object>> result =  advcverifyService.queryChild(STATEMENTS_PAYMENT_DTL_ID);
        request.setAttribute("result", result);
		return mapping.findForward("tochild");
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
	 
	public AdvcverifyService getAdvcverifyService() {
		return advcverifyService;
	}

	public void setAdvcverifyService(AdvcverifyService advcverifyService) {
		this.advcverifyService = advcverifyService;
	}
	
	
	

}
