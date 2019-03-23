/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderAction
*/
package com.hoperun.drp.store.storeouta.action;
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
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeouta.model.StoreoutaModelChld;
import com.hoperun.drp.store.storeouta.service.StoreoutaService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class StoreoutaAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
	//增删改查
	private static String PVG_BWS="FX0032301";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    private static String PVG_IMPORT="FX0032302";
    private static String PVG_PRINT="FX0032303";
    private static String AUD_BUSS_STATE = "";
    //启用,停用
    private static String PVG_START_STOP="";
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
    private static String AUD_TAB_NAME="";//表名
    private static String AUD_TAB_KEY="";//主键
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private StoreoutaService storeoutaService;
	
	
	public StoreoutaService getStoreoutaService() {
		return storeoutaService;
	}
	public void setStoreoutaService(StoreoutaService storeoutaService) {
		this.storeoutaService = storeoutaService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String search = ParamUtil.get(request, "search");
		request.setAttribute("search", search);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
        ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "DEAL_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "DEAL_TIME_END", params);
	    params.put("TERM_CHARGE", userBean.getTERM_CHARGE());
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME,u.STOREOUT_NO", "asc");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeoutaService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("search", search);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREOUT_ID =ParamUtil.get(request, "STOREOUT_ID");
        if(!StringUtil.isEmpty(STOREOUT_ID))
        {
        	 List <StoreoutaModelChld> result = storeoutaService.childQuery(STOREOUT_ID);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if(!StringUtil.isEmpty(STOREOUT_ID)){
			Map<String,String> entry = storeoutaService.load(STOREOUT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_IMPORT",QXUtil.checkPvg(userBean, PVG_IMPORT) );
	    	pvgMap.put("PVG_PRINT",QXUtil.checkPvg(userBean, PVG_PRINT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	
	// 导出
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_IMPORT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
        ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "DEAL_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "DEAL_TIME_END", params);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		List list = storeoutaService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="销售出库通知单编号,来源单据编号,出库库房编号,出库库房名称,收货方编号,收货方名称,负责人,销售日期,收货地址,出库总金额,状态," +
							 "备注,制单人,制单时间,更新人,更新时间,制单部门,制单机构,货品编号,货品名称,规格型号," +
							 "颜色,品牌,标准单位,通知出库数量,单价,折扣率,折扣价,折后金额,明细备注,实际出库数量,特殊工艺说明";
		String tmpContent = "STOREOUT_NO,FROM_BILL_NO,STOREOUT_STORE_NO,STOREOUT_STORE_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RSP_NAME,SALE_DATE,RECV_ADDR,STOREOUT_AMOUNT,STATE," +
							"REMARK,CRE_NAME,CRE_TIME,UPD_NAME,UPD_TIME,DEPT_NAME,ORG_NAME,PRD_NO,PRD_NAME,PRD_SPEC," +
							"PRD_COLOR,BRAND,STD_UNIT,NOTICE_NUM,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT,DTLREMARK,REAL_NUM,SPCL_DTL_REMARK";
		try {
			ExcelUtil
					.doExport(response, list, "下级销售出库单明细", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ActionForward toAllPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_PRINT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
        ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    params.put("TERM_CHARGE", userBean.getTERM_CHARGE());
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		params.put("QXJBCON", ParamUtil.getPvgCon(search, "", PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		if(StringUtil.isEmpty(params.get("pageSize"))){
			params.put("pageSize", "20");
		}
		IListPage page = storeoutaService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		request.setAttribute("search", search);
		return mapping.findForward("allPrint");
	}
	/**
	 * 批量打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward allPrint(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String sessionId = request.getParameter("sessionId");
		 	 String STOREOUT_IDS=(String) request.getSession().getAttribute(sessionId);
		 	StringBuffer paramSql=new StringBuffer();
			 paramSql.append("batchStoreOutaPrint.raq&STOREOUT_IDS=").append(STOREOUT_IDS);
			 /**
			 StringBuffer storeSql = new StringBuffer();
			 StringBuffer sql=new StringBuffer();
			 StringBuffer dtlSql=new StringBuffer();
			 StringBuffer disAmountSql=new StringBuffer();
			 StringBuffer allAmountSql=new StringBuffer();
			 storeSql.append("rptSql=select * from PRINTSTOREOUTA_VW where OLD_STOREOUT_ID  in (").append(STOREOUT_IDS).append("); ");
			 dtlSql.append("dtlSql= select * from PRINTSTOREOUTA_DTL_VW where OLD_STOREOUT_ID in (").append(STOREOUT_IDS).append(");");
			 disAmountSql.append("disAmountSql=select a.STOREOUT_ID,")
			 		  .append("sum(NVL(a.REAL_NUM, 0) * NVL(a.PRICE, 0)) DISAMOUNT ")
			 		  .append(" from DRP_STOREOUT_DTL_A a ")
			 		  .append(" where a.DEL_FLAG = 0 ")
			 		  .append(" and a.PRD_TYPE = '赠品' ")
			 		  .append(" and a.STOREOUT_ID in (").append(STOREOUT_IDS).append(") group by a.STOREOUT_ID; ");
			 allAmountSql.append("allAmountSql=select b.STOREOUT_ID, ")
			 		  .append(" sum(NVL(b.REAL_NUM, 0) * NVL(b.PRICE, 0)) ALLAMOUNT ")
			 		  .append(" from DRP_STOREOUT_DTL_A b ")
			 		  .append(" where b.DEL_FLAG = 0 ")
			 		  .append(" and b.PRD_TYPE = '销售货品' ")
			 		  .append(" and b.STOREOUT_ID in (").append(STOREOUT_IDS).append(") ")
			 		  .append(" group by b.STOREOUT_ID;");
			 sql.append("").append(storeSql.toString()).append(dtlSql.toString()).append(disAmountSql.toString()).append(allAmountSql.toString());
			 request.getSession().removeAttribute(sessionId);
			 sql.append(storeSql.toString()).append(dtlSql.toString());
			 request.setAttribute("reportFileName", "batchStoreOutaPrint.raq");
			 **/
			 request.setAttribute("params",paramSql.toString());
			 
			 return mapping.findForward("flashReportPrint");
	}
	public void getSaleSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STOREOUT_IDS = request.getParameter("STOREOUT_IDS");
			String sessionId=StringUtil.uuid32len();
			Map<String,String> map=new HashMap<String, String>();
			if(!StringUtil.isEmpty(STOREOUT_IDS)){
				request.getSession().setAttribute(sessionId, STOREOUT_IDS);
				map.put("sessionId",sessionId);
			}
			jsonResult = new Gson().toJson(new Result(true, map, ""));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "存储选取出库单信息失败！");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
}

