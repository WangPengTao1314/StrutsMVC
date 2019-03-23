/**
 * prjName:喜临门营销平台
 * ucName:客户退货结算
 * fileName:RestatementsAction
*/
package com.hoperun.drp.finance.restatements.action;
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
import org.apache.log4j.Logger;
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
import com.hoperun.drp.finance.restatements.model.RestatementsModel;
import com.hoperun.drp.finance.restatements.service.RestatementsService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-12 15:21:43
 */
public class RestatementsAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(RestatementsAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0050301";
    private static String PVG_EDIT="FX0050302";
    private static String PVG_DELETE="FX0050304";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0050303";
    private static String PVG_TRACE="?";
    //审核模块
    private static String PVG_BWS_AUDIT="?";
    private static String PVG_AUDIT_AUDIT="?";
    private static String PVG_TRACE_AUDIT="?";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_RETURN_STATEMENTS";
    private static String AUD_TAB_KEY="RETURN_STATEMENTS_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="?";
	private static String AUD_FLOW_INS="com.hoperun.drp.finance.restatements.service.RestatementsFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private RestatementsService restatementsService;
    /**
	 * Sets the Restatements service.
	 * 
	 * @param RestatementsService the new Restatements service
	 */
	public void setRestatementsService(RestatementsService restatementsService) {
		this.restatementsService = restatementsService;
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
	   ParamUtil.putStr2Map(request, "STATEMENTS_NO", params);
	   ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	   ParamUtil.putStr2Map(request, "TERM_NO", params);
	   ParamUtil.putStr2Map(request, "TERM_NAME", params);
	   ParamUtil.putStr2Map(request, "CUST_NAME", params);
	   ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
       ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
	   	params.put("LEDGER_ID", userBean.getLoginZTXXID());
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("BILL_TYPE", "'"+BusinessConsts.BILL_TYPE_RETURN+"'");
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装和状态过滤
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = restatementsService.pageQuery(params, pageNo);
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
		Map<String,String> entry = null;
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
		if(!StringUtil.isEmpty(STATEMENTS_ID)){
			entry = restatementsService.load(STATEMENTS_ID);
		}else{
			entry = new HashMap<String,String>();
			entry.put("BILL_TYPE",BusinessConsts.BILL_TYPE_ADVPAY);
			entry.put("LEDGER_ID", userBean.getLoginZTXXID());
			entry.put("STATEMENTS_NO", BusinessConsts.ZDSC);
		}
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
			RestatementsModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<RestatementsModel>(){}.getType());
			}
			String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
			restatementsService.txEdit(STATEMENTS_ID,aModel,userBean);
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
	 * 明细的列表
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public ActionForward toChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
		if(!StringUtil.isEmpty(STATEMENTS_ID)){
			Map<String,String> params = new HashMap<String,String>();
			params.put("STATEMENTS_ID", STATEMENTS_ID);
			List<Map<String,String>> dtlList = restatementsService.queryChild(params);
			request.setAttribute("rst", dtlList);
		}
		return mapping.findForward("childlist");
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
			String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STATEMENTS_ID", STATEMENTS_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			restatementsService.txDelete(params);
			restatementsService.clearCaches(params);
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
		String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
		if(!StringUtil.isEmpty(STATEMENTS_ID)){
			Map<String,String> entry = restatementsService.load(STATEMENTS_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
	 * 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void opSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STATEMENTS_ID = ParamUtil.get(request, "id");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STATEMENTS_ID", STATEMENTS_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            restatementsService.txSub(params);
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "提交失败!");
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