package com.hoperun.drp.sale.forecasttaskup.action;

import java.io.PrintWriter;
import java.util.Date;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;
import com.hoperun.drp.sale.forecasttaskup.service.ForecastTaskupService;
import com.hoperun.sys.model.UserBean;

public class ForecastTaskupAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(ForecastTaskupAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0023401";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";

	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0023402";
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
	
	ForecastTaskupService forecastTaskupService;

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
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}
	
	
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "ADVC_RPT_JOB_NO", params);
	    ParamUtil.putStr2Map(request, "YEAR", params);
	    ParamUtil.putStr2Map(request, "MONTH", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "SENDER_NAME", params);
	    ParamUtil.putStr2Map(request, "SENDER_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "SENDER_TIME_END", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    
        //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search)){
			//sb.append(" and u.STATE ='未填报' "); 
		}else{
//			/sb.append(" and u.STATE in ('发布','已填报') "); 
		}
		sb.append(" and u.CHANN_ID='").append(userBean.getLoginZTXXID()).append("' and b.STATE in('发布','结束上报') "); 
		//权限级别和审批流的封装
	    params.put("QXJBCON",sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.SENDER_TIME", "DESC");
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = forecastTaskupService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 * 填报货品列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toPrdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String PRD_SPEC = ParamUtil.get(request, "PRD_SPEC");
		params.put("PRD_NO",PRD_NO);
		params.put("PRD_NAME",PRD_NAME);
		params.put("PRD_SPEC",PRD_SPEC);
        String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
        String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
        String YEAR  = ParamUtil.get(request, "YEAR");
        String MONTH = ParamUtil.get(request, "MONTH");
        Date date = DateUtil.parseDate(forecastTaskupService.getData());
        String CUUR_DATE = DateUtil.format(date, "yyyy-MM-dd");
        String BEGIN_DATE = DateUtil.getDateYearBegin(date);//日期的年初
        String getLastDate = DateUtil.getLastDate(date);//上个月
        params.put("YEAR",getLastDate.substring(0, 4));
        params.put("LAST_MONTH", getLastDate.substring(5, 7));
        params.put("CHANN_ID",userBean.getLoginZTXXID());
        params.put("BEGIN_DATE",BEGIN_DATE);
        params.put("CUUR_DATE",CUUR_DATE);
        params.put("LAST_DATE",DateUtil.getLastDateBegin(date).substring(0, 7));
	    params.put("ADVC_RPT_JOB_ID",ADVC_RPT_JOB_ID);
	    params.put("RPT_JOB_CHANN_ID",RPT_JOB_CHANN_ID);
		List<Map<String,String>> prdList = forecastTaskupService.queryPrd(params);
        request.setAttribute("rst", prdList);
        int count = 0;
        if(!prdList.isEmpty()){
        	 count = prdList.size();
        }
        request.setAttribute("count", count);
        request.setAttribute("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
        request.setAttribute("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
        request.setAttribute("YEAR", YEAR);
        request.setAttribute("MONTH",MONTH);
        request.setAttribute("param", params);
		return mapping.findForward("prdlist");
	}
 
	/**
	 * 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
//    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
//    	{
//    		throw new ServiceException("对不起，您没有权限 !");
//    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
            String jsonDate = ParamUtil.get(request, "jsonDate");
            Map<String,String> entry = forecastTaskupService.load(RPT_JOB_CHANN_ID);
            if(entry.isEmpty()){
            	throw new ServiceException("该任务已被总部取消，请关闭页面并刷新");
            }
            List<AdvcRptChannDtl> chldList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldList = new Gson().fromJson(jsonDate, 
            			new TypeToken <List <AdvcRptChannDtl>>(){}.getType());
            }
            forecastTaskupService.txEdit(RPT_JOB_CHANN_ID,entry,chldList, userBean);
            jsonResult = jsonResult(true, "保存成功");
            
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
	 * 提交
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void commit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
//    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
//    	{
//    		throw new ServiceException("对不起，您没有权限 !");
//    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
            forecastTaskupService.txCommit(RPT_JOB_CHANN_ID,userBean);
            jsonResult = jsonResult(true, "提交成功");
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "提交失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	/**
	 * 撤销
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void revoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
            forecastTaskupService.txRevoke(RPT_JOB_CHANN_ID);
            jsonResult = jsonResult(true, "撤销成功");
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "撤销失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	/**
	 * 设置权限Map
	 * @param UserBean  the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
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
	
	public ForecastTaskupService getForecastTaskupService() {
		return forecastTaskupService;
	}

	public void setForecastTaskupService(ForecastTaskupService forecastTaskupService) {
		this.forecastTaskupService = forecastTaskupService;
	}
	
	
	
	
}
