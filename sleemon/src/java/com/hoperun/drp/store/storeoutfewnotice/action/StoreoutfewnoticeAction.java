package com.hoperun.drp.store.storeoutfewnotice.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeout.action.StoreoutAction;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModel;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModelChld;
import com.hoperun.drp.store.storeoutfewnotice.service.StoreoutfewnoticeService;
import com.hoperun.sys.model.UserBean;

/**
 * *@StoreoutfewnoticeAction *@func *@version 1.1 *@author wujun *@createdate 2014-12-10
 * 14:59:50
 */
public class StoreoutfewnoticeAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(StoreoutAction.class);
	
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0032101";
    private static String PVG_EDIT="FX0032102";
    private static String PVG_DELETE="FX0032103";
    
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0032104";
    private static String PVG_TRACE="FX0032105";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_FEW_STOREOUT_REQ";
    private static String AUD_TAB_KEY="FEW_STOREOUT_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="FEW_STOREOUT_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	/** 审批 end **/
	/** 业务service */
	private StoreoutfewnoticeService storeoutfewnoticeService;

	/**
	 * Sets the Storeout service.
	 * 
	 * @param StoreoutService
	 *            the new Storeout service
	 */
	public void setStoreoutfewnoticeService(StoreoutfewnoticeService storeoutfewnoticeService) {
		this.storeoutfewnoticeService = storeoutfewnoticeService;
	}

	/**
	 * * to 框架页面
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
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}

	/**
	 * * query List data
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
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserBean userBean =  ParamUtil.getUserBean(request);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "FEW_STOREOUT_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "BUSS_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "BUSS_DEPT_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		 
		//权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeoutfewnoticeService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
		
	}
	/**
	 * * 主表 删除
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
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "FEW_STOREOUT_REQ_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			storeoutfewnoticeService.txDelete(params);
			storeoutfewnoticeService.clearCaches(params);
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
	 * * 明细批量删除
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
	public void childDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String FEW_STOREOUT_REQ_DTL_IDS = request
					.getParameter("FEW_STOREOUT_REQ_DTL_IDS");
			// 批量删除，多个Id之间用逗号隔开
			storeoutfewnoticeService.txBatch4DeleteChild(FEW_STOREOUT_REQ_DTL_IDS);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * * 明细列表
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
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "FEW_STOREOUT_REQ_ID");
		if (!StringUtil.isEmpty(FEW_STOREOUT_REQ_ID)) {
			List<StoreoutfewnoticeModelChld> result = storeoutfewnoticeService
					.childQuery(FEW_STOREOUT_REQ_ID);
			request.setAttribute("rst", result);
			request.setAttribute("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);	
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}
	
	/**
	 * * to 主表编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(FEW_STOREOUT_REQ_ID)){
			Map<String,String> entry = storeoutfewnoticeService.load(FEW_STOREOUT_REQ_ID);
			request.setAttribute("rst", entry);
		} else {
			Map<String,String> entry = new HashMap<String,String>();
			request.setAttribute("rst", entry);
		}
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("toedit");
	}

	/**
	 * * 编辑框架页面加载子页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward modifyToChildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "FEW_STOREOUT_REQ_ID");
		if (!StringUtil.isEmpty(FEW_STOREOUT_REQ_ID)) {
			List<StoreoutfewnoticeModelChld> result = storeoutfewnoticeService
					.childQuery(FEW_STOREOUT_REQ_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("childedit");
	}

	/**
	 * * to 直接编辑明细页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String FEW_STOREOUT_REQ_DTL_IDS = request.getParameter("FEW_STOREOUT_REQ_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(FEW_STOREOUT_REQ_DTL_IDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("FEW_STOREOUT_REQ_DTL_IDS", FEW_STOREOUT_REQ_DTL_IDS);
			List<StoreoutfewnoticeModelChld> list = storeoutfewnoticeService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		return mapping.findForward("childedit");
	}
	
	/**
	 * to 货品扫码页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toEditScanDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		if (Consts.FUN_CHEK_PVG ) {
//			throw new ServiceException("对不起，您没有权限 !");
//		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		String billType =ParamUtil.get(request, "BILL_TYPE");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			List<StoreoutfewnoticeModelChld> result = storeoutfewnoticeService
					.childQuery(STOREOUT_ID);
			request.setAttribute("rst", result);
		}
	    request.setAttribute("BILL_TYPE", billType);
	    request.setAttribute("STOREOUT_ID", STOREOUT_ID);
		return mapping.findForward("scan");
	}
		
	

	/**
	 * * 主表 新增/修改数据
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
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "FEW_STOREOUT_REQ_ID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");
			StoreoutfewnoticeModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<StoreoutfewnoticeModel>() {
					}.getType());
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<StoreoutfewnoticeModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<StoreoutfewnoticeModelChld>>() {
						}.getType());
			}
			storeoutfewnoticeService.txEdit(FEW_STOREOUT_REQ_ID, model, chldModelList, userBean);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 子表 新增/修改数据
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
	public void childEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String FEW_STOREOUT_REQ_ID = request.getParameter("FEW_STOREOUT_REQ_ID");
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<StoreoutfewnoticeModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<StoreoutfewnoticeModelChld>>() {
						}.getType());
				storeoutfewnoticeService.txChildEdit(FEW_STOREOUT_REQ_ID, modelList,userBean);
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
		String FEW_STOREOUT_REQ_ID = ParamUtil.get(request, "FEW_STOREOUT_REQ_ID");
		if (!StringUtil.isEmpty(FEW_STOREOUT_REQ_ID)) {
			Map<String, String> entry = storeoutfewnoticeService.load(FEW_STOREOUT_REQ_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        //设置跳转时需要的一些公用属性
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("editFrame");
    }

	/**
	 * * 提交时，校验是否有明细.
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
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		logger.info("按钮修改为启用单条记录开始");
		try {
			String FEW_STOREOUT_REQ_ID = request.getParameter("FEW_STOREOUT_REQ_ID");
			List<StoreoutfewnoticeModelChld> chldList = storeoutfewnoticeService.childQuery(FEW_STOREOUT_REQ_ID);
			if (chldList.size() == 0) {
				throw new ServiceException("没有明细信息，不能提交!");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
			map.put("STATE", BusinessConsts.PASS);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			storeoutfewnoticeService.txUpdateState(map,userBean);
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		} catch (Exception e) {
			jsonResult = jsonResult(false, "提交失败");
			logger.info(e);
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
	 
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
	



	
}
