package com.hoperun.drp.store.storeoutconfirm.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelChld;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelGchld;
import com.hoperun.drp.store.storeoutconfirm.service.StoreoutconfirmService;
import com.hoperun.sys.model.UserBean;

public class StoreoutconfirmAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0031701";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="FX0031702";
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
	
	
	private StoreoutconfirmService storeoutconfirmService;
	
	/**
	 * @return the storeoutconfirmService
	 */
	public StoreoutconfirmService getStoreoutconfirmService() {
		return storeoutconfirmService;
	}
	/**
	 * @param storeoutconfirmService the storeoutconfirmService to set
	 */
	public void setStoreoutconfirmService(
			StoreoutconfirmService storeoutconfirmService) {
		this.storeoutconfirmService = storeoutconfirmService;
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
		ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
		ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
		ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
		ParamUtil.putStr2Map(request, "STORAGE_FLAG", params);
		ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "RECV_FLAG", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    params.put("STATE", BusinessConsts.DONE);
	    if(StringUtil.isEmpty(search)){
	    	params.put("searchRECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
	    }
	    params.put("BILL_TYPE", "销售出库");
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeoutconfirmService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREOUT_ID =ParamUtil.get(request, "STOREOUT_ID");
        if(!StringUtil.isEmpty(STOREOUT_ID))
        {
        	 List <StoreoutconfirmModelChld> result = storeoutconfirmService.childQuery(STOREOUT_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
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
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if(!StringUtil.isEmpty(STOREOUT_ID)){
			Map<String,String> entry = storeoutconfirmService.load(STOREOUT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	/**
	 * * 库位明细列表
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
	public ActionForward storgChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
	    String STOREOUT_DTL_IDs = request.getParameter("STOREOUT_DTL_IDS");
	    if (!StringUtil.isEmpty(STOREOUT_ID)) {
	    	 Map<String,String> params = new HashMap<String,String>();
	    	 
	    	 params.put("STOREOUT_DTL_IDS",STOREOUT_DTL_IDs);
	    	 params.put("STOREOUT_ID",STOREOUT_ID);
	    	 List<StoreoutconfirmModelGchld> result = storeoutconfirmService.GchildQuery(params);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
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
	  * ajax异步获取主表明细
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public ActionForward getChld(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
			String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
			List<StoreoutconfirmModelChld> result = storeoutconfirmService.childQuery(STOREOUT_ID);
			request.setAttribute("rst", result);
			request.setAttribute("STOREOUT_ID", STOREOUT_ID);
			return mapping.findForward("firmList");
		}
	 public void toVerify(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			UserBean userBean = ParamUtil.getUserBean(request);
			try{
				String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
				String REMARK = ParamUtil.get(request, "REMARK");
				String RECV_ATT = ParamUtil.get(request, "RECV_ATT");
				String jsonDate = ParamUtil.get(request, "childJsonData");
	            List <StoreoutconfirmModelChld> chldModelList = null;
	            if (!StringUtil.isEmpty(jsonDate)) {
	                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <StoreoutconfirmModelChld>>() {
	                }.getType());
	            }
	            Map<String,String> map=new HashMap<String,String>();
	            map.put("STOREOUT_ID", STOREOUT_ID);
	            map.put("REMARK", REMARK);
	            map.put("RECV_ATT", RECV_ATT);
	            map.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
	            map.put("RECV_TIME", "sysdate");
	            map.put("RECV_PSON_ID", userBean.getRYXXID());
	            map.put("RECV_PSON_NAME", userBean.getXM());
	            storeoutconfirmService.txChildEdit(chldModelList,map,STOREOUT_ID,userBean);
				
			}catch(ServiceException s){
				jsonResult = jsonResult(false, s.getMessage());
			}catch(Exception e){
				jsonResult = jsonResult(false, "收货确认失败");
				e.printStackTrace();
			}
			if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
		}
}
