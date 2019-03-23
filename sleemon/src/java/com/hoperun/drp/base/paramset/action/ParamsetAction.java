package com.hoperun.drp.base.paramset.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.paramset.model.ParamsetModel;
import com.hoperun.drp.base.paramset.service.ParamsetService;
import com.hoperun.sample.newsingletable.action.NewSingleTableAction;
import com.hoperun.sys.model.UserBean;
 
/**
 * 渠道参数设置
 * @author zhang_zhongbin
 *
 */
public class ParamsetAction extends BaseAction {
	
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(NewSingleTableAction.class);
	
	/** 权限对象维护**/
    //增删改查
    private static String PVG_BWS="FX0010501";
    private static String PVG_EDIT="FX0010502";
    private static String PVG_DELETE="FX0010503";
    private static String PVG_RECV_DATE="FX0010504";
    private static String PVG_SPEC_ENABLE="FX0010505";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
    /**权限 end*/
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
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**审批 end**/
	
	private  ParamsetService  paramsetService;

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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//设置跳转时需要的一些公用属性
    	ParamUtil.setCommAttributeEx(request);
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}
	
	/**
	 * * 查询
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		
		String CHANN_ID = userBean.getCHANN_ID();
		Map<String, String> chann = paramsetService.getChannInfo(CHANN_ID);
		request.setAttribute("rst", chann);
		String LEDGER_ID = userBean.getLoginZTXXID();
		Map<String,String> params = new HashMap<String,String>();
		
		String search = ParamUtil.get(request, "search");
		String DATA_TYPE = BusinessConsts.PAY_TYPE;//付款方式
	    if(StringUtil.isEmpty(search)){
	    	params.put("DATA_TYPE", DATA_TYPE);
	    }else{
	    	ParamUtil.putStr2Map(request, "DATA_TYPE", params);
	    }
		params.put("LEDGER_ID", LEDGER_ID);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		 //权限级别和审批流的封装以及状态的封装
//	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
//		IListPage page = paramsetService.pageQuery(params, pageNo);
		
		List<ParamsetModel> result = paramsetService.query(DATA_TYPE, LEDGER_ID);
	 
		request.setAttribute("params", params);
		request.setAttribute("result", result);
		request.setAttribute("LEDGER_ID", LEDGER_ID);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("list");
	}
	
	
	
	/**
	 * 修改.
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
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_ID  = userBean.getCHANN_ID();
        
        String jsonData  = ParamUtil.get(request, "jsonData");
        List<ParamsetModel> modelList = new  ArrayList<ParamsetModel>();
        
        if (StringUtils.isNotEmpty(jsonData)) {
        	modelList = new Gson().fromJson(jsonData, new TypeToken <List <ParamsetModel>>() {
            }.getType());
        }
    	try {
    		 paramsetService.txEdit(CHANN_ID, modelList,userBean); 
    		 jsonResult =  jsonResult(true,"保存成功");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
        }
                  
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	 /**
     * 查询
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void query(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DATA_TYPE = ParamUtil.get(request, "DATA_TYPE");
        String LEDGER_ID = userBean.getLoginZTXXID();
        try {
        	List<ParamsetModel> result = paramsetService.query(DATA_TYPE, LEDGER_ID);
        	jsonResult = new Gson().toJson(new Result(true, result, ""));
        } catch (Exception e) {
             logger.error(e);
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
        String DATA_CONF_IDS = ParamUtil.get(request, "DATA_CONF_IDS");

        if (StringUtils.isNotEmpty(DATA_CONF_IDS)) {
            try {
                paramsetService.txDelete(DATA_CONF_IDS, userBean);
                paramsetService.clearCaches(DATA_CONF_IDS);
            } catch (Exception e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 数据字典编辑框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
    	
        //设置跳转时需要的一些公用属性
        String chistate = ParamUtil.utf8Decoder(request, "chistate");
        String freezeDays = ParamUtil.utf8Decoder(request,"freezeDays");
        String CHANN_ID   = ParamUtil.utf8Decoder(request,"CHANN_ID");
        
        // paramsetService.upFreenDaysById(CHANN_ID, freezeDays);
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("chistate", chistate);
        request.setAttribute("freezeDays",freezeDays);
        String LEDGER_ID = userBean.getLoginZTXXID();
		request.setAttribute("LEDGER_ID", LEDGER_ID);
        return mapping.findForward("editFrame");
    }
    
    /**
     *  修改最大冻结天数
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public void toEditFreezeDays (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
       
    	UserBean userBean = ParamUtil.getUserBean(request);
    	 
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        Map<String,String> map=new HashMap<String, String>();
        map.put("MAX_FREEZE_DAYS", ParamUtil.utf8Decoder(request,"MAX_FREEZE_DAYS"));
        map.put("INIT_YEAR", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"INIT_YEAR")));
        map.put("INIT_MONTH", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"INIT_MONTH")));
        map.put("COST_TYPE", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"COST_TYPE")));
        map.put("TAX_RATE", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"TAX_RATE")));
        map.put("IS_RETURN_SALE_AUD_FLAG", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"IS_RETURN_SALE_AUD_FLAG")));
        map.put("CHANN_SALE_RATE", ParamUtil.utf8Decoder(request,"CHANN_SALE_RATE"));
        map.put("ADVC_SCOPE_DAYS", ParamUtil.utf8Decoder(request,"ADVC_SCOPE_DAYS"));
        map.put("CHANN_ID", userBean.getCHANN_ID());
        map.put("IS_SPEC_TECH_ENABLE", StringUtil.nullToSring(ParamUtil.utf8Decoder(request,"IS_SPEC_TECH_ENABLE")));
        try {
        	 paramsetService.upFreenDaysById(map);
        	 jsonResult = jsonResult(true, "保存成功");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
         
    }
    
	
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			String DATA_CONF_ID = ParamUtil.get(request, "DATA_CONF_ID");
			if (StringUtils.isNotEmpty(DATA_CONF_ID)) {
				Map entry = paramsetService.loadByConfId(DATA_CONF_ID);
				request.setAttribute("rst", entry);
			}
			return mapping.findForward("detail");
	}
	
	/**
	 * 新增.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		
		String DATA_CONF_ID = ParamUtil.get(request, "DATA_CONF_ID");
		
		if (StringUtils.isNotEmpty(DATA_CONF_ID)) {
			 
		}
		String LEDGER_ID = userBean.getLoginZTXXID();
		request.setAttribute("LEDGER_ID", LEDGER_ID);
		return mapping.findForward("toedit");
	}

	
    /**
     * 数据字典修改页面跳转
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
        String selRowId   = ParamUtil.get(request, "selRowId");
        String freezeDays = ParamUtil.get(request, "freezeDays");
        //为空则是新增，否则是修改
        if (StringUtils.isNotEmpty(selRowId)) {
            Map <String, String> entry = paramsetService.loadByConfId(selRowId);
            request.setAttribute("rst", entry);
        }
        request.setAttribute("freezeDays",freezeDays);
        String LEDGER_ID = userBean.getLoginZTXXID();
		request.setAttribute("LEDGER_ID", LEDGER_ID);
        return mapping.findForward("toedit");
    }
    
    
    /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean) {
		 
    	Map<String,String>pvgMap = new HashMap<String,String>();
    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
    	pvgMap.put("PVG_RECV_DATE",QXUtil.checkPvg(userBean, PVG_RECV_DATE) );
    	pvgMap.put("PVG_SPEC_ENABLE",QXUtil.checkPvg(userBean, PVG_SPEC_ENABLE) );
    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
   }

		public ParamsetService getParamsetService() {
			return paramsetService;
		}

		public void setParamsetService(ParamsetService paramsetService) {
			this.paramsetService = paramsetService;
		}
 
}
