/**
 * prjName:喜临门营销平台
 * ucName:推广费用报销单维护
 * fileName:PromoreimAction
*/
package com.hoperun.drp.oamg.promoreim.action;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.oamg.promoreim.model.PromoreimModel;
import com.hoperun.drp.oamg.promoreim.service.PromoreimService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-25 19:49:05
 */
public class PromoreimAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PromoreimAction.class);
	/** 权限对象哈**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0021001";
    private static String PVG_EDIT="BS0021002";
    private static String PVG_DELETE="BS0021003";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0021004";
    private static String PVG_TRACE="BS0021005";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0020701";
    private static String PVG_AUDIT_AUDIT="BS0020702";
    private static String PVG_TRACE_AUDIT="BS0020703";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_PRMT_COST_REIT";
    private static String AUD_TAB_KEY="PRMT_COST_REIT_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_PRMT_COST_REIT_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PromoreimInterface";
    /**审批 end**/
    /** 业务service*/
	private PromoreimService promoreimService;
    /**
	 * Sets the Promoreim service.
	 * @param PromoreimService the new Promoreim service
	 */
	public void setPromoreimService(PromoreimService promoreimService) {
		this.promoreimService = promoreimService;
	}
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("audit", request.getParameter("audit"));
		
		return mapping.findForward("frames");
	}
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	   ParamUtil.putStr2Map(request, "PRMT_COST_REIT_NO", params);
	   ParamUtil.putStr2Map(request, "PRMT_COST_REQ_NO", params);
	   ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	   ParamUtil.putStr2Map(request, "ZONE_ADDR", params);
	   ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	   ParamUtil.putStr2Map(request, "COST_TYPE", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if(StringUtil.isEmpty(search)){
			search = "-1";
		}
		//权限级别和审批流的封装和状态过滤
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		if(StringUtil.isEmpty(search) || "-1".equals(search)){
			//初始化查询如果是维护菜单
			if(StringUtil.isEmpty(request.getParameter("audit"))){
				params.put("searchSTATE","('"+BusinessConsts.UNCOMMIT+"','"+BusinessConsts.REJECT+"','"+BusinessConsts.REVOKE+"')");
			}else{//如果是审核菜单
				params.put("searchSTATE","('"+BusinessConsts.COMMIT+"')");
			}
		}
		IListPage page = promoreimService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("audit",request.getParameter("audit"));
		request.setAttribute("module", module);
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
		Map<String,String> entry = new HashMap<String,String>();
		
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRMT_COST_REIT_ID = ParamUtil.get(request, "PRMT_COST_REIT_ID");
		if(!StringUtil.isEmpty(PRMT_COST_REIT_ID)){
			entry = promoreimService.load(PRMT_COST_REIT_ID);
			
			String path = promoreimService.loadFiles(PRMT_COST_REIT_ID);
			entry.put("STATENEBTS_ATT", path);
		}else{
			entry.put("LEDGER_ID", userBean.getLoginZTXXID());
			entry.put("REQ_ID", userBean.getRYXXID());
			entry.put("REQ_NAME", userBean.getXM());
			entry.put("REQ_DATE", StringUtil.getCurrentDate());
			entry.put("STATE", BusinessConsts.UNCOMMIT);
			entry.put("PRMT_COST_REIT_NO", BusinessConsts.ZDSC);
			entry.put("CHANN_ID",userBean.getCHANNS_CHARG());
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
			PromoreimModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<PromoreimModel>(){}.getType());
			}
			String PRMT_COST_REIT_ID = ParamUtil.get(request, "PRMT_COST_REIT_ID");
			promoreimService.txEdit(PRMT_COST_REIT_ID,aModel,userBean);
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
			String PRMT_COST_REIT_ID = ParamUtil.get(request, "PRMT_COST_REIT_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_COST_REIT_ID", PRMT_COST_REIT_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			promoreimService.txDelete(params);
			promoreimService.clearCaches(params);
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
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRMT_COST_REIT_ID = ParamUtil.get(request, "PRMT_COST_REIT_ID");
		if(!StringUtil.isEmpty(PRMT_COST_REIT_ID)){
			Map<String,String> entry = promoreimService.load(PRMT_COST_REIT_ID);
			String path = promoreimService.loadFiles(PRMT_COST_REIT_ID);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(PRMT_COST_REIT_ID);
			request.setAttribute("flowInfoList", flowInfoList);
			entry.put("STATENEBTS_ATT", path);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
	 *  启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String PRMT_COST_REIT_ID = request.getParameter("PRMT_COST_REIT_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRMT_COST_REIT_ID", PRMT_COST_REIT_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_ENABLE);	
			promoreimService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
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
	 * 停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String PRMT_COST_REIT_ID = request.getParameter("PRMT_COST_REIT_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_COST_REIT_ID", PRMT_COST_REIT_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_DISENABLE);
            promoreimService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
		    e.printStackTrace();
            jsonResult = jsonResult(false, "状态修改失败!");
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