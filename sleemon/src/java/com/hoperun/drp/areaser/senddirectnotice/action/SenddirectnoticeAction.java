package com.hoperun.drp.areaser.senddirectnotice.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.areaser.senddirectnotice.model.SenddirectnoticeModelChld;
import com.hoperun.drp.areaser.senddirectnotice.service.SenddirectnoticeService;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public class SenddirectnoticeAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0070601";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="FX0070602";
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
	
	private SenddirectnoticeService senddirectnoticeService;
	
	/**
	 * @return the senddirectnoticeService
	 */
	public SenddirectnoticeService getSenddirectnoticeService() {
		return senddirectnoticeService;
	}

	/**
	 * @param senddirectnoticeService the senddirectnoticeService to set
	 */
	public void setSenddirectnoticeService(
			SenddirectnoticeService senddirectnoticeService) {
		this.senddirectnoticeService = senddirectnoticeService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "BASE_DELIVER_NOTICE_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        
        ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_BEGIN", params);
        ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);
        
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
	    params.put("AREA_SER_ID", userBean.getCHANN_ID());
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE = ParamUtil.get(request,"STATE");
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		params.put("QXJBCON", sb.toString());
		
		params.put("STATE", STATE);
		
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = senddirectnoticeService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("module", module);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	/**
	 * * to 详细信息
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String BASE_DELIVER_NOTICE_ID = ParamUtil.get(request, "BASE_DELIVER_NOTICE_ID");
		if (!StringUtil.isEmpty(BASE_DELIVER_NOTICE_ID)) {
			Map<String, String> entry = senddirectnoticeService.load(BASE_DELIVER_NOTICE_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
        String BASE_DELIVER_NOTICE_ID =ParamUtil.get(request, "BASE_DELIVER_NOTICE_ID");
        if(!StringUtil.isEmpty(BASE_DELIVER_NOTICE_ID))
        {
        	 List <SaleorderModelChld> result = senddirectnoticeService.childQuery(BASE_DELIVER_NOTICE_ID);
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
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public ActionForward toAuditInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String BASE_DELIVER_NOTICE_ID =ParamUtil.get(request, "BASE_DELIVER_NOTICE_ID");
        String sessionId=StringUtil.uuid32len();
        if(!StringUtil.isEmpty(BASE_DELIVER_NOTICE_ID))
        {
        	 List <SaleorderModelChld> result = senddirectnoticeService.childQuery(BASE_DELIVER_NOTICE_ID);
        	 String error=null;
        	 if(result.size()==0){
        		 error="该数据没有明细，不能处理！";
        	 }
        	 String totalNum=senddirectnoticeService.getTotalNum(BASE_DELIVER_NOTICE_ID);
        	 request.setAttribute("totalNum", totalNum);
        	 request.setAttribute("error", error);
             request.setAttribute("rst", result);
             request.setAttribute("BASE_DELIVER_NOTICE_ID", BASE_DELIVER_NOTICE_ID);
             request.setAttribute("sessionId", sessionId);
        }
        return mapping.findForward("childInfo");
	}
    
	/**
	 * 点击转订货订单按钮后跳转页面显示list
	 */
	    public ActionForward toListShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean =  ParamUtil.getUserBean(request);
	        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			Map<String,String> params = new HashMap<String,String>();
	        ParamUtil.putStr2Map(request, "BASE_DELIVER_NOTICE_ID", params);
	        String sessionId=request.getParameter("sessionId");
	        ParamUtil.putStr2Map(request, "PRD_ID", params);
	        ParamUtil.putStr2Map(request, "SPCL_TECH_ID", params);
	       
	        List<List<SenddirectnoticeModelChld>> list=(List<List<SenddirectnoticeModelChld>>) request.getSession().getAttribute(sessionId);
	        boolean flag=true;
	        if(list!=null&&list.size()!=0){
	        	for (int i = 0; i < list.size(); i++) {
					List<SenddirectnoticeModelChld> chldList=list.get(i);
					if(chldList!=null&&chldList.size()!=0){
						SenddirectnoticeModelChld chldModel=chldList.get(0);
						if(chldModel.getPRD_ID().equals(params.get("PRD_ID"))&&chldModel.getSPCL_TECH_ID().equals(params.get("SPCL_TECH_ID"))){
							request.setAttribute("rst", chldList);
							flag=false;
							break;
						}
					}
				}
	        }
	        if(flag){
	        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	        	ParamUtil.setOrderField(request, params, "a.ORDER_DATE", "asc");
	        	List<Map<String,String>> chldList = senddirectnoticeService.querySaleOrder(params);
	        	request.setAttribute("rst", chldList);
	        }
	        String REAL_SEND_NUM=request.getParameter("REAL_SEND_NUM");
	        request.setAttribute("params", params);
	        request.setAttribute("REAL_SEND_NUM", REAL_SEND_NUM);
	        
	        request.setAttribute("sessionId", sessionId);
			return mapping.findForward("allotEdit");
	    }
    
	    
	    /**
		 * * 分配货品
		 * 
		 * @param mapping
		 *            the mapping
		 * @param form
		 *            the form
		 * @param request
		 *            the request
		 * @param response
		 *            the response
		 */
		public void toAllot(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG	&& !QXUtil.checkMKQX(userBean, PVG_FINISH_CANCLE)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				String BASE_DELIVER_NOTICE_ID=ParamUtil.utf8Decoder(request, "BASE_DELIVER_NOTICE_ID");
		        String sessionId=ParamUtil.utf8Decoder(request, "sessionId");
		        List<List<SenddirectnoticeModelChld>> list=(List<List<SenddirectnoticeModelChld>>) request.getSession().getAttribute(sessionId);
		        ArrayList modelList = new ArrayList();
		        if(list!=null){
		        	for (int i = 0; i < list.size(); i++) {
		        		modelList.addAll(list.get(i));
					}
		        	senddirectnoticeService.txbackSale(BASE_DELIVER_NOTICE_ID,modelList,userBean );
		        }
		        jsonResult = new Gson().toJson(new Result(true, "true", ""));
			} catch (ServiceException s) {
				jsonResult = jsonResult(false, s.getMessage());
			} catch (Exception e) {
				jsonResult = jsonResult(false, "提交失败");
				e.printStackTrace();
			}
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}   
	    
		/**
		 * * 保存分配货品到会话里 
		 * 
		 * @param mapping
		 *            the mapping
		 * @param form
		 *            the form
		 * @param request
		 *            the request
		 * @param response
		 *            the response
		 */
		public void savePrdInfo(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG	&& !QXUtil.checkMKQX(userBean, PVG_FINISH_CANCLE)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				String allotNum=request.getParameter("allotNum");
		        String jsonDate = request.getParameter("childJsonData");
		        String sessionId=request.getParameter("sessionId");
		        setPrdList(jsonDate, request, response,sessionId);
		        jsonResult = new Gson().toJson(new Result(true, allotNum, ""));
			} catch (ServiceException s) {
				jsonResult = jsonResult(false, s.getMessage());
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
	 public void setPrdList(String json,HttpServletRequest request, HttpServletResponse response,String sessionId){
		 List<List<SenddirectnoticeModelChld>> templist=new ArrayList<List<SenddirectnoticeModelChld>>();
		 if (!StringUtil.isEmpty(json)) {
				List<SenddirectnoticeModelChld> modelList = new Gson().fromJson(
						json, new TypeToken<List<SenddirectnoticeModelChld>>() {
						}.getType());
				List<List<SenddirectnoticeModelChld>> list=(List<List<SenddirectnoticeModelChld>>) request.getSession().getAttribute(sessionId);
				int flag=-1;
				if(list!=null&&list.size()!=0){
					for (int i = 0; i < list.size(); i++) {
						List<SenddirectnoticeModelChld> chldList=list.get(i);
						if(chldList!=null&&chldList.size()!=0){
							SenddirectnoticeModelChld chldModel=chldList.get(0);//旧
							SenddirectnoticeModelChld tempModel=modelList.get(0);//新
							if(chldModel.getPRD_ID().equals(tempModel.getPRD_ID())&&chldModel.getSPCL_TECH_ID().equals(tempModel.getSPCL_TECH_ID())){
								templist.add(modelList);
								flag=i;
								break;
							}
						}
					}
					if(flag>-1){
						list.remove(flag);
					}else{
						templist.add(modelList);
					}
					templist.addAll(list);
				}else{
					templist.add(modelList);
				}
			}
		 request.getSession().setAttribute(sessionId, templist);
	 }
	 /**
		 * *页面关闭时删除session
		 * 
		 * @param mapping
		 *            the mapping
		 * @param form
		 *            the form
		 * @param request
		 *            the request
		 * @param response
		 *            the response
		 */
		public void windowClose(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				String sessionId=ParamUtil.utf8Decoder(request, "sessionId");
				request.getSession().removeAttribute(sessionId);
				jsonResult = jsonResult(true, "");
			} catch (Exception e) {
				jsonResult = jsonResult(false, "操作失败");
				e.printStackTrace();
			}
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}   
}
