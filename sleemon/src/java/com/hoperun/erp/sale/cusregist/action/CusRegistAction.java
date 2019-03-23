package com.hoperun.erp.sale.cusregist.action;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.erp.sale.cusregist.service.CusRegistService;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.sys.model.UserBean;
/**
 * 顾客现场签到
 * @author zhang_zhongbin
 *
 */
public class CusRegistAction extends BaseAction {
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0023901";
	private static String PVG_EDIT = "BS0023902";
	private static String PVG_DELETE = "BS0023903";
	
	  //启用,停用
    private static String PVG_START_STOP = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";

	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	
	private CusRegistService cusRegistService;
	
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl",paramUrl );
	 
		return mapping.findForward("frames");
	}
	
	
	/**
	 * *list页面
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "MARKETING_CARD_NO", params);
	    ParamUtil.putStr2Map(request, "CARD_TYPE", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "MOBILE_PHONE", params);
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEGIN", params); 
	    ParamUtil.putStr2Map(request, "SALE_DATE_END", params);  
	    ParamUtil.putStr2Map(request, "REGIST_STATE", params);  
	    
	    ParamUtil.putStr2Map(request, "TERM_NO", params);  
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);  
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);  
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);  
	    
	    
	    //只查询0的记录。1为删除。0为正常
//        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
//		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装
//		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
//				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
//		StringBuffer sb = new StringBuffer();
//		sb.append(StringUtil.getStrQX("u",strQx.toString()));
//	    params.put("QXJBCON", sb.toString());
		if(StringUtil.isEmpty(search)){
			params.put("REGIST_FLAG", BusinessConsts.INTEGER_1);
		}
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		params.put("pageSize", "20");
		IListPage page = cusRegistService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
        request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
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
	   public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
	   		HttpServletRequest request, HttpServletResponse response) {
	   	UserBean userBean = ParamUtil.getUserBean(request);
	   	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
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
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
	   	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	   		throw new ServiceException("对不起，您没有权限 !");
	   	}	
		String CARD_SALE_ORDER_DTL_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(CARD_SALE_ORDER_DTL_ID)){
			entry = cusRegistService.load(CARD_SALE_ORDER_DTL_ID);
		}
		request.setAttribute("rst", entry);
		return mapping.findForward("toedit");
	}
 
   
	/**
	 * 根据卡券编号加载卡券信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String MARKETING_CARD_NO = ParamUtil.get(request, "MARKETING_CARD_NO");
            String jsonDate = ParamUtil.get(request, "childJsonData");
            MarketcardSaleChldModel childModel = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	childModel = new Gson().fromJson(jsonDate, 
            			new TypeToken <MarketcardSaleChldModel>(){}.getType());
            	
            	cusRegistService.txEdit(childModel,userBean);
            }
            jsonResult = jsonResult(true, "保存成功");
            
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
	 * 根据卡券编号加载卡券信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void loadCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String MARKETING_CARD_NO = ParamUtil.get(request, "MARKETING_CARD_NO");
            Map<String,String> entry = cusRegistService.loadCard(MARKETING_CARD_NO);
            if(null == entry || entry.isEmpty()){
				entry = cusRegistService.loadNoSaleCard(MARKETING_CARD_NO);
			}
            if(null == entry || entry.isEmpty()){
            	jsonResult = jsonResult(false, "卡券无效");
            }else{
            	jsonResult = new Gson().toJson(new Result(true, entry, "加载成功"));
            }
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "加载失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 签出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void registout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String CARD_SALE_ORDER_DTL_ID = ParamUtil.get(request, "selRowId");
            Map<String,String> params = new HashMap<String,String>();
            params.put("CARD_SALE_ORDER_DTL_ID", CARD_SALE_ORDER_DTL_ID);
        	params.put("REGIST_TIME", "1");
        	params.put("REGIST_STATE", "已签出");
            cusRegistService.txUpdateCardMx(params);
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "签出失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
   /**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
	 
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	
	
	public CusRegistService getCusRegistService() {
		return cusRegistService;
	}

	public void setCusRegistService(CusRegistService cusRegistService) {
		this.cusRegistService = cusRegistService;
	}

}
