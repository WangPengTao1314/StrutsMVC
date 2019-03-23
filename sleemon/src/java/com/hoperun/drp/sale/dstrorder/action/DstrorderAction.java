/**
 * prjName:喜临门营销平台
 * ucName:分发指令接收
 * fileName:DstrorderAction
*/
package com.hoperun.drp.sale.dstrorder.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.dstrorder.model.DstrorderModelChld;
import com.hoperun.drp.sale.dstrorder.service.DstrorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-16 10:31:37
 */
public class DstrorderAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(DstrorderAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0021101";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    
    //接收
    private static String PVG_RICV="FX0021102";
    
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
	private DstrorderService dstrorderService;
    
	/**
	 * Sets the Dstrorder service.
	 * 
	 * @param DstrorderService the new Dstrorder service
	 */
	public void setDstrorderService(DstrorderService dstrorderService){
		this.dstrorderService = dstrorderService;
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
	    ParamUtil.putStr2Map(request, "DSTR_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "RECV_TIME_END", params);
		 ParamUtil.putStr2Map(request, "STATE", params);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装 
		params.put("QXJBCON",ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		//默认条件beg
		//删除标记，默认显示0 的数据
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        //按帐套过滤
        params.put("LEDGER_ID",userBean.getLoginZTXXID());
        
		params.put("u.DSTR_CHANN_ID",userBean.getLoginZTXXID());
		//默认条件end
		
		//初始时只显示未接收的数据
		if(StringUtil.isEmpty(search)&&StringUtil.isEmpty(params.get("STATE")))
	    { 
			//状态为未接收
			params.put("searchSTATE",BusinessConsts.UNRECEIVE);
	    }
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = dstrorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	/**
     * 明细列表
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
        String DSTR_ORDER_ID =ParamUtil.get(request, "DSTR_ORDER_ID");
        if(!StringUtil.isEmpty(DSTR_ORDER_ID))
        {
        	 List <DstrorderModelChld> result = dstrorderService.childQuery(DSTR_ORDER_ID);
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
		String DSTR_ORDER_ID = ParamUtil.get(request, "DSTR_ORDER_ID");
		if(!StringUtil.isEmpty(DSTR_ORDER_ID)){
			Map<String,String> entry = dstrorderService.load(DSTR_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
    
    /**
	 * 接收
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	@SuppressWarnings("unchecked")
	public void changeStateReceived(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        logger.info("按钮修改为接收单条记录开始");
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_RICV))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String DSTR_ORDER_ID = request.getParameter("DSTR_ORDER_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("STOREOUT_STORE_ID", ParamUtil.utf8Decoder(request, "STOREOUT_STORE_ID"));
			params.put("STOREOUT_STORE_NO", ParamUtil.utf8Decoder(request, "STOREOUT_STORE_NO"));
			params.put("STOREOUT_STORE_NAME", ParamUtil.utf8Decoder(request, "STOREOUT_STORE_NAME"));
			params.put("STORAGE_FLAG", ParamUtil.utf8Decoder(request, "STORAGE_FLAG"));
			List list=new ArrayList();
			LogicUtil.genStoreOutOrder(DSTR_ORDER_ID,"ERP_DSTR_ORDER", userBean, params,list);
			jsonResult = jsonResult(true, "接收成功!");
        } catch (Exception e) {
        	e.printStackTrace();
			jsonResult = jsonResult(false, "接收失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 设置权限Map
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
		pvgMap.put("PVG_RICV",QXUtil.checkPvg(userBean, PVG_RICV) );
		pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
	}
}