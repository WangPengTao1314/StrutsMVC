package com.hoperun.drp.oamg.termrefinecheck.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModel;
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModelChild;
import com.hoperun.drp.oamg.termrefinecheck.service.ChanncheckplanService;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmagedtlModel;
import com.hoperun.sys.model.UserBean;

public class ChanncheckplanAction  extends BaseAction {
   
	/**日志**/
	private Logger logger = Logger.getLogger(TermrefinecheckAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0032701";
    private static String PVG_EDIT="BS0032702";
    private static String PVG_DELETE="BS0032703";
    //启用,停用
    private static String PVG_START_STOP="BS0032704";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032004";
    private static String PVG_TRACE="BS0032005";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0031201";
    private static String PVG_AUDIT_AUDIT="BS0031202";
    private static String PVG_TRACE_AUDIT="BS0031203";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_CHANN_CHECK_PLAN";
    private static String AUD_TAB_KEY="CHANN_CHECK_PLAN_ID";
	private static String AUD_BUSS_STATE="u.STATE";
    private static String AUD_BUSS_TYPE="ERP_CHANN_CHECK_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
	
	private   ChanncheckplanService    channcheckplanService;
	
	public ChanncheckplanService getChanncheckplanService() {
		return channcheckplanService;
	}
	public void setChanncheckplanService(ChanncheckplanService channcheckplanService) {
		this.channcheckplanService = channcheckplanService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
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
	    ParamUtil.putStr2Map(request, "TERM_REFINE_CHECK_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_VERSION", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	    ParamUtil.putStr2Map(request, "CHECK_ORG_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		
		//渠道分管
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		//params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = channcheckplanService.pageQuery(params, pageNo);
		String menu="";
		if("wh".equals(module)){
			menu="门店精致化检查结果维护";
		}else{
			menu="门店精致化检查结果审核";
		}
		request.setAttribute("menu", menu);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
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
        String CHANN_CHECK_PLAN_ID =ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
        if(!StringUtil.isEmpty(CHANN_CHECK_PLAN_ID))
        {
        	 List <ChanncheckplanModelChild> result = channcheckplanService.childQuery(CHANN_CHECK_PLAN_ID);
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
		String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
		if(!StringUtil.isEmpty(CHANN_CHECK_PLAN_ID)){
			Map<String,String> entry = channcheckplanService.loadByConf(CHANN_CHECK_PLAN_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	 /**
     * *
     * * to 编辑框架页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        //设置跳转时需要的一些公用属性
    	ParamUtil.setCommAttributeEx(request);
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
    	
    	String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
     	if(!CHANN_CHECK_PLAN_ID.equals("")){
     	 request.setAttribute("CHANN_CHECK_PLAN_ID",CHANN_CHECK_PLAN_ID);
     	 return mapping.findForward("editFrame_T");
     	} else {
         return mapping.findForward("editFrame");
     	}
    }
    
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
   	 UserBean userBean = ParamUtil.getUserBean(request);
   	 String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
   	 if (StringUtils.isNotEmpty(CHANN_CHECK_PLAN_ID)) {
			Map entry = channcheckplanService.loadByConfT(CHANN_CHECK_PLAN_ID);
			request.setAttribute("rst", entry);
		 }
   	 request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
   	 request.setAttribute("CHANN_CHECK_PLAN_ID",CHANN_CHECK_PLAN_ID);
   	 return mapping.findForward("toeditT");
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
		String TERM_REFINE_CHECK_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry;
		if(!StringUtil.isEmpty(TERM_REFINE_CHECK_ID)){
			entry= channcheckplanService.loadByConf(TERM_REFINE_CHECK_ID);
		}else{
			//按当前登录人员查询所属机构
			entry=new HashMap<String, String>();
			entry.put("CHECK_ORG_ID", userBean.getJGXXID());
			entry.put("CHECK_ORG_NAME", userBean.getJGMC());
		}
		 
        request.setAttribute("rst", entry);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("QUERY_CHANN_ID", userBean.getCHANNS_CHARG());
		request.setAttribute("XTYH_ID", userBean.getXTYHID());
		request.setAttribute("STATE",  "制定");
		return mapping.findForward("toedit");
	}
	
	
	/**
     * * 编辑框架页面加载子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
        if(!StringUtil.isEmpty(CHANN_CHECK_PLAN_ID))
        {
        	 List <ChanncheckplanModelChild> result = channcheckplanService.childQuery(CHANN_CHECK_PLAN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
        return mapping.findForward("childedit");
    }
    
    public ActionForward modifyToChildEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");
        if(!StringUtil.isEmpty(CHANN_CHECK_PLAN_ID))
        {
        	 List <ChanncheckplanModelChild> result = channcheckplanService.childQuery(CHANN_CHECK_PLAN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
        return mapping.findForward("childedit");
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
		
		
		 PrintWriter writer = getWriter(response);
         String jsonResult = jsonResult();
         String parentJsonData = ParamUtil.get(request, "parentJsonData");
         
         UserBean userBean = ParamUtil.getUserBean(request);
         String CHANN_CHECK_PLAN_ID = ParamUtil.utf8Decoder(request, "CHANN_CHECK_PLAN_ID");  
         String chkLen = ParamUtil.utf8Decoder(request, "chkLen");
         ChanncheckplanModel model = new ChanncheckplanModel();
         if (StringUtils.isNotEmpty(parentJsonData)) {
             model = new Gson().fromJson(parentJsonData, new TypeToken <ChanncheckplanModel>() {
             }.getType());
         }
         String jsonData = ParamUtil.get(request, "childJsonData");
         List <ChanncheckplanModelChild>  mxList = null;
         if (StringUtils.isNotEmpty(jsonData)) {
             mxList = new Gson().fromJson(jsonData, new TypeToken <List<ChanncheckplanModelChild>>() {
             }.getType());
         }
         String str = channcheckplanService.txEdit(CHANN_CHECK_PLAN_ID, model,mxList,userBean,chkLen);
         if(str.equals("1")){
        	 jsonResult = jsonResult(false, "检查项目编号不能重复");
         }
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
	}
	
	public ActionForward childList2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String CHANN_CHECK_PLAN_NO = ParamUtil.get(request, "CHANN_CHECK_PLAN_NO");   //工作计划ID
		String CHANN_CHECK_PLAN_ID = channcheckplanService.queryIdByNo(CHANN_CHECK_PLAN_NO);
		//String WAREA_ID     = ParamUtil.get(request, "WAREA_ID");       //战区ID
		List<ChanncheckplanModelChild>  result =  channcheckplanService.queryJudgeModel(CHANN_CHECK_PLAN_ID);
		request.setAttribute("rst", result);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("CHANN_CHECK_PLAN_NO", CHANN_CHECK_PLAN_NO);
//		if(!WORK_PLAN_ID.equals("自动生成") && !WAREA_ID.equals("")){
//            workplanmageService.txDeleteChildT(WORK_PLAN_ID,userBean);				
//		}
		return mapping.findForward("childedit");
	}  
	
	 /**
     * 停用启用按钮修改单条记录状态.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        String str = "";
        try {
            String CHANN_CHECK_PLAN_ID = ParamUtil.utf8Decoder(request, "CHANN_CHECK_PLAN_ID"); 
            // 取得状态
            Map <String, String> entry = channcheckplanService.load(CHANN_CHECK_PLAN_ID);
            String state = entry.get("STATE");
            Map <String, String> params = new TreeMap <String, String>();
            String changedState = "";
            params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
            UserBean userBean = ParamUtil.getUserBean(request);
            params.put("UPDATER", userBean.getXM());
            // 启用==>停用
            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
                channcheckplanService.updateState(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state) || BusinessConsts.JC_STATE_DEFAULT.equals(state)) {
                // 停用 ==>启用
                // 判断同品牌是否只能有一条启用状态
            	//String BRANDT    = channcheckplanService.queryProT(BRAND);
//            	String LEDGER_ID = userBean.getLoginZTXXID().toString();
//                int count = channcheckplanService.countFrom(BRANDT,LEDGER_ID); 	
//                if(count == 0){
	                changedState = BusinessConsts.JC_STATE_ENABLE;
	                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
	                channcheckplanService.updateState(params);
	                isChanged = true;
//                } else {
//                	str = "同品牌中只能有一条启用状态";
//                	jsonResult = jsonResult(false, str);
//                }
            }
            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
            	if(str.equals("")) {
                    jsonResult = jsonResult(false, "状态不用修改");
            	} else {
            		 jsonResult = jsonResult(false, str);
            	}
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    public void changeStateT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        String str = "";
        try {
            String CHANN_CHECK_PLAN_ID = ParamUtil.utf8Decoder(request, "CHANN_CHECK_PLAN_ID"); 
            // 取得状态
            Map <String, String> entry = channcheckplanService.load(CHANN_CHECK_PLAN_ID);
            String state = entry.get("STATE");
            Map <String, String> params = new TreeMap <String, String>();
            String changedState = "";
            params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            UserBean userBean = ParamUtil.getUserBean(request);
            params.put("UPDATER", userBean.getXM());
            channcheckplanService.updateState(params);
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_CHECK_PLAN_ID = ParamUtil.get(request, "CHANN_CHECK_PLAN_ID");

        if (StringUtils.isNotEmpty(CHANN_CHECK_PLAN_ID)) {
            try {
            	channcheckplanService.txDelete(CHANN_CHECK_PLAN_ID, userBean);
            	channcheckplanService.clearCaches(CHANN_CHECK_PLAN_ID);
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
