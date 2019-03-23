
package com.hoperun.drp.base.retalprice.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.drp.base.retalprice.model.RetalpriceModel;
import com.hoperun.drp.base.retalprice.service.RetalpriceService;
import com.hoperun.sys.model.UserBean;

public class RetalpriceAction extends BaseAction {
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0010701";
	private static String PVG_EDIT = "FX0010702";
	private static String PVG_DELETE = "FX0010703";
	// 启用,停用
	private static String PVG_START_STOP = "";
	private static String AUD_BUSS_STATE="";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_RETURN="";//退回
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	private RetalpriceService retalpriceService;
	
	/**
	 * @return the retalpriceService
	 */
	public RetalpriceService getRetalpriceService() {
		return retalpriceService;
	}


	/**
	 * @param retalpriceService the retalpriceService to set
	 */
	public void setRetalpriceService(RetalpriceService retalpriceService) {
		this.retalpriceService = retalpriceService;
	}


	/**
	 * 框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
		
	}
	
	
	/**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "PRD_NO", params);
    	String PRD_NO=ParamUtil.utf8Decoder(request, "PRD_NO");
    	params.put("PRD_NOQuery", StringUtil.creCon("p.PRD_NO", PRD_NO, ","));
    	ParamUtil.putStr2Map(request, "PRD_NAME", params);
    	String PRD_NAME=ParamUtil.utf8Decoder(request, "PRD_NAME");
    	params.put("PRD_NAMEQuery", StringUtil.creCon("p.PRD_NAME", PRD_NAME, ","));
    	ParamUtil.putStr2Map(request, "PRD_SPEC", params);
    	String PRD_SPEC=ParamUtil.utf8Decoder(request, "PRD_SPEC");
    	params.put("PRD_SPECQuery", StringUtil.creCon("p.PRD_SPEC", PRD_SPEC, ","));
    	ParamUtil.putStr2Map(request, "BRAND", params);
    	String BRAND=ParamUtil.utf8Decoder(request, "BRAND");
    	params.put("BRANDQuery", StringUtil.creCon("p.BRAND", BRAND, ","));
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
    	String PAR_PRD_NAME=ParamUtil.utf8Decoder(request, "PAR_PRD_NAME");
    	params.put("PAR_PRD_NAMEQuery", StringUtil.creCon("p.PAR_PRD_NAME",PAR_PRD_NAME, ","));
    	int pageSize=ParamUtil.getInt(request, "pageSize",0);
    	if(0==pageSize){
    		pageSize=100;
    	}
    	params.put("pageSize", String.valueOf(pageSize));
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		// 字段排序
		ParamUtil.setOrderField(request, params, "p.PRD_NO", "ASC");
    	
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = retalpriceService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
	
    
    
    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        List<RetalpriceModel> model = new ArrayList<RetalpriceModel>();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <List<RetalpriceModel>>() {
            }.getType());
        }
    	try {
    		retalpriceService.txEdit(model, userBean);
        }catch(ServiceException x){
        	jsonResult = jsonResult(false, x.getMessage());
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
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
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
		pvgMap.put("PVG_RETURN", QXUtil.checkPvg(userBean,PVG_RETURN));
		return pvgMap;
	}
}
