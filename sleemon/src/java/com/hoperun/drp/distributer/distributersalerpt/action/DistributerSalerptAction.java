package com.hoperun.drp.distributer.distributersalerpt.action;

import java.io.PrintWriter;
import java.util.Date;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.distributer.distributersalerpt.model.DistributerSalerptModel;
import com.hoperun.drp.distributer.distributersalerpt.service.DistributerSalerptService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */
public class DistributerSalerptAction extends BaseAction {

   
	//增删改查
    private static String PVG_BWS="BS0034401";
    private static String PVG_EDIT="BS0034402";
    private static String PVG_DELETE="BS0034403";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0034404";
    //流程跟踪
    private static String PVG_TRACE= "BS0034405";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0035101";
    private static String PVG_AUDIT_AUDIT="BS0035102";
    private static String PVG_TRACE_AUDIT="BS0035103";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_DISTRIBUTOR_SALE_RPT";
    private static String AUD_TAB_KEY="DISTRIBUTOR_SALE_RPT_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_DISTRIBUTOR_SALE_RPT_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private   DistributerSalerptService   distributerSalerptService;
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		//设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("module", ParamUtil.utf8Decoder(request, "module"));
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
		
		  //权限级别
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		Map<String, String> params = new HashMap<String, String>();
		    	
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "CRE_NAME", params);
    	ParamUtil.putStr2Map(request, "YEAR", params);
    	ParamUtil.putStr2Map(request, "MONTH", params);    	
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String STATE = ParamUtil.get(request,"STATE");
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if("".equals(module)){
			module = "wh";
		}
		//权限级别和审批流的封装以及状态的封装
		//params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

	     //权限级别和审批流的封装
		 if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
			   qx.append(" and STATE in('未提交','撤销','否决') ");
			}
		 }
		 if(module.equals("sh")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}else{
					qx.append(" and STATE in('提交','撤销','否决','审核通过') ");
				}
			}else{
				qx.append(" and STATE='提交' ");
			}
		 }
	     params.put("QXJBCON",qx.toString());
	     //渠道分管
	     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	     params.put("CHANNS_CHARG", CHANNS_CHARG);
		// 字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = distributerSalerptService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        request.setAttribute("module", module);
        return mapping.findForward("list");
	}
	
	/**
	 * 编辑框架页面.
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
	public ActionForward toEditFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("editFrame");
	}
	
	/**
	 * * to 编辑页面初始化
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
		String DISTRIBUTOR_SALE_RPT_ID = ParamUtil.get(request, "selRowId");
		//Map<String,String> entry = new HashMap<String,String>();
		DistributerSalerptModel entry = new DistributerSalerptModel();
		if(!StringUtil.isEmpty(DISTRIBUTOR_SALE_RPT_ID)){
			//entry = distributerSalerpt.load(DISTRIBUTOR_SALE_RPT_ID);
			entry = distributerSalerptService.find(DISTRIBUTOR_SALE_RPT_ID);
		}else{
			entry.setCHANN_ID(userBean.getCHANN_ID());
			entry.setCHANN_NO(userBean.getCHANN_NO());
			entry.setCHANN_NAME(userBean.getCHANN_NAME());
			
			Date currDate = new Date();
			entry.setCRE_TIME(DateUtil.format(currDate, "yyyy-MM-dd"));
			entry.setYEAR(DateUtil.format(currDate, "yyyy"));
		}
		
		request.setAttribute("userName", userBean.getXM());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		
		return mapping.findForward("toedit");
	}
	 
	/**
	 * 编辑.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);        
        String jsonResult = jsonResult();
        try{
        	String jsonData = ParamUtil.get(request, "jsonData");
        	DistributerSalerptModel model = new DistributerSalerptModel();
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, 
                		new TypeToken <DistributerSalerptModel>() {}.getType());
            }
            distributerSalerptService.txEdit(model,userBean);
            jsonResult = jsonResult(true, "保存成功");
            
        }catch(Exception e){
        	jsonResult = jsonResult(false, "保存失败");
        	e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 编辑框架页面.
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
	public ActionForward toDetailFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("detailFrame");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG
					&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
							.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String DISTRIBUTOR_SALE_RPT_ID = ParamUtil.get(request, "selRowId");
			
			//Map<String,String> entry = distributerSalerpt.load(DISTRIBUTOR_SALE_RPT_ID);
			DistributerSalerptModel entry = distributerSalerptService.find(DISTRIBUTOR_SALE_RPT_ID);
			request.setAttribute("rst", entry);	
					    
			return mapping.findForward("detail");
	}
	
	 /**
     * 删除.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DISTRIBUTOR_SALE_RPT_ID = ParamUtil.get(request, "DISTRIBUTOR_SALE_RPT_ID");

        if (StringUtils.isNotEmpty(DISTRIBUTOR_SALE_RPT_ID)) {
            try {
            	distributerSalerptService.txDelete(DISTRIBUTOR_SALE_RPT_ID, userBean);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
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
			String DISTRIBUTOR_SALE_RPT_DTL_IDS = request
					.getParameter("DISTRIBUTOR_SALE_RPT_DTL_IDS");
			// 批量删除，多个Id之间用逗号隔开
			distributerSalerptService.txBatch4DeleteChild(DISTRIBUTOR_SALE_RPT_DTL_IDS);
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
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String DISTRIBUTOR_SALE_RPT_ID = request.getParameter("DISTRIBUTOR_SALE_RPT_ID");
			String stateFlag = request.getParameter("stateFlag");
			
			 Map <String, String> params = new HashMap <String, String>();
	         params.put("DISTRIBUTOR_SALE_RPT_ID", DISTRIBUTOR_SALE_RPT_ID);
	         params.put("UPDATOR", userBean.getXTYHID());
	         params.put("UPD_NAME", userBean.getXM()); 
	         
	         if(!StringUtil.isEmpty(stateFlag) && "commit".equals(stateFlag)){
	        	 params.put("STATE", BusinessConsts.COMMIT);
	         }else{
	        	 params.put("STATE", BusinessConsts.UNCOMMIT);
	         }
	        
			 distributerSalerptService.txUpdateStateById(params);
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
	 * 导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "CRE_NAME", params);
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "YEAR", params);
    	ParamUtil.putStr2Map(request, "MONTH", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    params.put("CHANNS_CHARG", CHANNS_CHARG);
		List list = distributerSalerptService.qryExp(params);
		
        //excel数据上列显示的列明
		String tmpContent = "DISTRIBUTOR_SALE_RPT_NO,CHANN_NO,CHANN_NAME,MONTH,YEAR,"+
			"STATE,CRE_NAME,CRE_TIME,UPD_NAME,UPD_TIME,DEPT_NAME,ORG_NAME,LEDGER_NAME,"+
			"DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE,PRD_NO,PRD_NAME,PRD_SPEC,STOREOUT_NUM,STOREOUT_AMOUNT";
			
		String tmpContentCn = "分销商数据上报编号,渠道编号,渠道名称,月份,年份,"+
			"状态,制单人名称,制单时间,更新人名称,更新时间,制单部门名称,制单机构名称,帐套名称,"+
			"分销商编号,分销商名称,分销商类型,货品编号,货品名称,规格型号,提货数量,提货金额"; 

        try {
        	ExcelUtil.doExport(response, list, "分销商购货月报", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
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
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	  }

	public DistributerSalerptService getDistributerSalerptService() {
		return distributerSalerptService;
	}

	public void setDistributerSalerptService(
			DistributerSalerptService distributerSalerptService) {
		this.distributerSalerptService = distributerSalerptService;
	}
	 	 
}
