package com.hoperun.erp.sale.forecasttask.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.forecasttask.model.ForecastTaskModel;
import com.hoperun.erp.sale.forecasttask.service.ForecastTaskService;
import com.hoperun.sys.model.UserBean;

public class ForecastTaskAction extends BaseAction{
	
	/** 日志 **/
	private Logger logger = Logger.getLogger(ForecastTaskAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0014301";
	private static String PVG_EDIT = "BS0014302";
	private static String PVG_DELETE = "BS0014303";

	// 发布，取消发布
	private static String PVG_RELEASE_CANCEL = "BS0014304";
	// 结束上报  ，打开上报
	private static String PVG_OVER_OPEN = "BS0014305";
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

	private ForecastTaskService forecastTaskService;
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
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE in ('制定','发布')"); 
		} 
		//权限级别和审批流的封装
	    params.put("QXJBCON",sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "ASC");
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = forecastTaskService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	
	 /**
     * 查看详细信息.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG &&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
        Map<String,String> entry = forecastTaskService.load(ADVC_RPT_JOB_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("todetail");
    }
	
    /**
     * 新增
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
        Map<String,String> entry = null;
        if (StringUtils.isNotEmpty(ADVC_RPT_JOB_ID)) {
        	entry = forecastTaskService.load(ADVC_RPT_JOB_ID);
        }else{
        	entry = new HashMap<String,String>();
        	entry.put("SENDER_ID", userBean.getXTYHID());
        	entry.put("SENDER_NAME", userBean.getXM());
        }
        request.setAttribute("rst", entry);
        return mapping.findForward("toedit");
    }
	
    /**
	 * * 主表 新增/修改数据
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
            String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
            String jsonData = ParamUtil.get(request, "jsonData");
            ForecastTaskModel model = new Gson().fromJson(jsonData, 
            		new TypeToken <ForecastTaskModel>() {}.getType());
            Map<String,String>paramMap = new HashMap<String,String>();
            paramMap.put("YEAR", model.getYEAR());
            paramMap.put("MONTH", model.getMONTH());
            int num = forecastTaskService.validateRptYearMonth(paramMap);
            if(0 < num){
            	throw new ServiceException(model.getMONTH()+"月，已经做过任务");
            }else{
            	forecastTaskService.txEdit(ADVC_RPT_JOB_ID, model, userBean);
                jsonResult = jsonResult(true, "保存成功");
            }
        }catch (ServiceException e){
        	jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	  /**
	 * * 发布
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void release(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
            forecastTaskService.txRelease(ADVC_RPT_JOB_ID, userBean);
            jsonResult = jsonResult(true, "发布成功");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "发布失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}

	
	  /**
	 * * 取消发布
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
          String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
          forecastTaskService.txCancel(ADVC_RPT_JOB_ID, userBean);
          jsonResult = jsonResult(true, "取消发布成功");
      }catch(ServiceException e){
    	  jsonResult = jsonResult(false, e.getMessage());
      } catch (Exception e) {
          jsonResult = jsonResult(false, "取消发布失败");
          e.printStackTrace();
      }
      if (null != writer) {
          writer.write(jsonResult);
          writer.close();
      }
	}
	
	/**
	 * 渠道列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward queryJobChann(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG &&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
        int flag = ParamUtil.getInt(request, "flag",0);
        if(!StringUtil.isEmpty(ADVC_RPT_JOB_ID)){
        	  Map<String,String>param = new HashMap<String,String>();
              param.put("ADVC_RPT_JOB_ID",ADVC_RPT_JOB_ID);
              if(1 == flag){
              	param.put("SQL"," u.STATE in('提交','审核通过') ");
              }else{
              	param.put("SQL"," u.STATE='未填报' ");
              }
              List<Map<String,String>> list = forecastTaskService.queryJobChann(param);
              request.setAttribute("rst", list);
        }
        return mapping.findForward("tochann");
    }
    
    /**
     * 删除.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");

        if (StringUtils.isNotEmpty(ADVC_RPT_JOB_ID)) {
            try {
            	forecastTaskService.txDelete(ADVC_RPT_JOB_ID, userBean);
            	jsonResult = jsonResult(true, "删除成功");
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
	 * 结束上报
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void over(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
          String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
          forecastTaskService.txOver(ADVC_RPT_JOB_ID, userBean);
          jsonResult = jsonResult(true, "操作成功");
      } catch (Exception e) {
          jsonResult = jsonResult(false, "操作失败");
          e.printStackTrace();
      }
      if (null != writer) {
          writer.write(jsonResult);
          writer.close();
      }
	}
	
	
	/**
	 * 打开上报
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void open(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
          String ADVC_RPT_JOB_ID = ParamUtil.get(request, "ADVC_RPT_JOB_ID");
          forecastTaskService.txOpen(ADVC_RPT_JOB_ID, userBean);
          jsonResult = jsonResult(true, "操作成功");
      } catch (Exception e) {
          jsonResult = jsonResult(false, "操作失败");
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
		pvgMap.put("PVG_RELEASE_CANCEL", QXUtil.checkPvg(userBean, PVG_RELEASE_CANCEL));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_OVER_OPEN", QXUtil.checkPvg(userBean,PVG_OVER_OPEN));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}


	public ForecastTaskService getForecastTaskService() {
		return forecastTaskService;
	}


	public void setForecastTaskService(ForecastTaskService forecastTaskService) {
		this.forecastTaskService = forecastTaskService;
	}
	
	
	

}
