package com.hoperun.erp.sale.budgetitem.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemModel;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemTree;
import com.hoperun.erp.sale.budgetitem.service.BudgetitemService;
import com.hoperun.sys.model.UserBean;
/**
 * 预算科目
 * @author zhang_zhongbin
 *
 */
public class BudgetitemAction extends BaseAction{
	/** 日志 **/
	private Logger logger = Logger.getLogger(BudgetitemAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0022401";
	private static String PVG_EDIT = "BS0022402";
	private static String PVG_DELETE = "BS0022403";
	
	  //启用,停用
    private static String PVG_START_STOP = "BS0022404";
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
	
	
	private BudgetitemService budgetitemService;
	
	/**
	 * 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
		 
		return mapping.findForward("frames");
	 
	}
	
	/**
	 *  查询结果列表
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "BUDGET_ITEM_ID", params);
		ParamUtil.putStr2Map(request, "BUDGET_ITEM_NO", params);
		ParamUtil.putStr2Map(request, "BUDGET_ITEM_NAME", params);
		ParamUtil.putStr2Map(request, "BUDGET_ITEM_TYPE", params);
		ParamUtil.putStr2Map(request, "PAR_BUDGET_ITEM_NO", params);
		ParamUtil.putStr2Map(request, "PAR_BUDGET_ITEM_NAME", params);
		ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		String search = ParamUtil.get(request,"search");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,"",
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		 
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = budgetitemService.pageQuery(params, pageNo);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	
	
	/**
	 *  货品信息编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String BUDGET_ITEM_ID = ParamUtil.get(request, "BUDGET_ITEM_ID");
		if (!StringUtil.isEmpty(BUDGET_ITEM_ID)) {
			Map<String, String> entry = budgetitemService.load(BUDGET_ITEM_ID);
			request.setAttribute("rst", entry);
		}
 
	  return mapping.findForward("toedit");
	 
	}

	/**
	 *  信息编辑 新增/修改
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		logger.info("Enter edit()");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		BudgetitemModel model = new BudgetitemModel();
		String BUDGET_ITEM_ID = ParamUtil.get(request, "BUDGET_ITEM_ID");
		
		try {
			if (!StringUtil.isEmpty(jsonData)) {
				model = new Gson().fromJson(jsonData, 
						new TypeToken<BudgetitemModel>(){}.getType());
			}
			if(StringUtils.isEmpty(BUDGET_ITEM_ID)){
				boolean isRepeat =  budgetitemService.isRepeat(model.getBUDGET_ITEM_NO());
				if(isRepeat){
					throw new ServiceException("编号有重复值，请重新输入");
				}
			} 
			Map<String, String> params = new HashMap<String, String>();
			params.put("BUDGET_ITEM_ID", BUDGET_ITEM_ID);
			BUDGET_ITEM_ID = budgetitemService.txEdit(BUDGET_ITEM_ID,model,userBean);
			jsonResult = jsonResult(true, "保存成功");
			
		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
     
	/**
	 *  查看详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String BUDGET_ITEM_ID = ParamUtil.utf8Decoder(request, "BUDGET_ITEM_ID");
		if(!StringUtil.isEmpty(BUDGET_ITEM_ID)) {
			Map<String, String> entry = budgetitemService.load(BUDGET_ITEM_ID);
			request.setAttribute("rst", entry);
        }
		return mapping.findForward("todetail");
	}
	
	
	
	/**
	 *  删除
	 * @param mapping the mapping
	 * @param formthe form
	 * @param requestthe request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String BUDGET_ITEM_ID = ParamUtil.get(request, "BUDGET_ITEM_ID");
			Map <String, String> params = new HashMap <String, String>();
		    params.put("BUDGET_ITEM_ID", BUDGET_ITEM_ID);
		    params.put("UPDATOR", userBean.getXTYHID());
		    params.put("UPD_NAME", userBean.getYHM());
		    params.put("UPD_TIME", DateUtil.now());
		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		    budgetitemService.txDelete(params);
		    budgetitemService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 *  按钮修改状态为启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_START_STOP)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String BUDGET_ITEM_ID = request.getParameter("BUDGET_ITEM_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("BUDGET_ITEM_ID", BUDGET_ITEM_ID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPD_TIME", DateUtil.now());
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			budgetitemService.txStartById(params);
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
	 *  按钮修改状态为停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_START_STOP)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String BUDGET_ITEM_ID = request.getParameter("BUDGET_ITEM_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("BUDGET_ITEM_ID", BUDGET_ITEM_ID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPD_TIME", DateUtil.now());
			params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
			budgetitemService.txStopById(params);
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
     * 显示树.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTree(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) {

        List <BudgetitemTree> trees;
        try {
            trees = budgetitemService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("msg", "获取信息失败！");
            return mapping.findForward(FAILURE);
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
	public BudgetitemService getBudgetitemService() {
		return budgetitemService;
	}
	public void setBudgetitemService(BudgetitemService budgetitemService) {
		this.budgetitemService = budgetitemService;
	}
	
	
	


}
