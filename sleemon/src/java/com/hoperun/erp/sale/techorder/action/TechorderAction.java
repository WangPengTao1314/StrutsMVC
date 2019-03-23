/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderAction
*/
package com.hoperun.erp.sale.techorder.action;
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
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.techorder.model.TechorderModelChld;
import com.hoperun.erp.sale.techorder.service.TechorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 18:31:07
 */
public class TechorderAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
	private static String PVG_BWS="BS0011201";
    private static String PVG_EDIT="BS0011202";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //审核完成
    private static String PVG_AUDIT="BS0011203";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
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
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private TechorderService techorderService;
    /**
	 * Sets the Techorder service.
	 * 
	 * @param TechorderPriceService the new Techorder service
	 */
	public void setTechorderService(TechorderService techorderService) {
		this.techorderService = techorderService;
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
	    ParamUtil.putStr2Map(request, "TECH_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	  //工艺单表里DEL_FLAG为是否可生产标记，不是是否删除标记，所以工艺单审核条件不做过滤
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
        if(StringUtil.isEmpty(search)){
        	params.put("searchSTATE", "提交");
        }else{
        	ParamUtil.putStr2Map(request, "STATE", params);
        }
        if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
        	params.put("STATES", "'待核价','提交'");
        }
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = techorderService.pageQuery(params, pageNo);
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
        String TECH_ORDER_ID =ParamUtil.get(request, "TECH_ORDER_ID");
        if(!StringUtil.isEmpty(TECH_ORDER_ID))
        {
        	 List <TechorderModelChld> result = techorderService.childQuery(TECH_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
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
        return mapping.findForward("editFrame");
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
		String TECH_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(TECH_ORDER_ID)){
			Map<String,String> entry = techorderService.load(TECH_ORDER_ID);
			request.setAttribute("rst", entry);
		}
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
//        String TECH_ORDER_ID = ParamUtil.get(request, "TECH_ORDER_ID");
//        if(!StringUtil.isEmpty(TECH_ORDER_ID))
//        {
//        	 List <TechorderModelChld> result = techorderService.childQuery(TECH_ORDER_ID);
//             request.setAttribute("rst", result);
//        }
    	Map<String,String> map=new HashMap<String, String>();
    	
    	ParamUtil.putStr2Map(request, "PRD_SPEC", map);
	    ParamUtil.putStr2Map(request, "PRD_NO", map);
	    ParamUtil.putStr2Map(request, "PRD_NAME", map);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", map);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", map);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", map);
	    ParamUtil.putStr2Map(request, "TECH_ORDER_NO", map);
	    ParamUtil.putStr2Map(request, "CRE_NAME", map);
	    ParamUtil.putStr2Map(request, "TECH_ORDER_NO", map);
	    String search = ParamUtil.get(request,"search");
	    if(StringUtil.isEmpty(search)){
	    	map.put("INIT_STATE", BusinessConsts.COMMIT);
	    }else{
	    	ParamUtil.putStr2Map(request, "STATE", map);
	    }
    	
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//    	map.put("XTYHID", userBean.getXTYHID());
    	//字段排序
		ParamUtil.setOrderField(request, map, "b.FROM_BILL_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", map);
    	IListPage page = techorderService.childQueryEdit(map, pageNo);
        request.setAttribute("page", page);
        request.setAttribute("params", map);
        return mapping.findForward("childedit");
    }
	
	 /**
     * * to 直接编辑明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String TECH_ORDER_DTL_IDs = request.getParameter("TECH_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(TECH_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("TECH_ORDER_DTL_IDS",TECH_ORDER_DTL_IDs);
          List <TechorderModelChld> list = techorderService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        return mapping.findForward("childedit");
    }
	
	/**
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String TECH_ORDER_ID = request.getParameter("TECH_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <TechorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <TechorderModelChld>>() {
                }.getType());
                techorderService.txChildEdit(TECH_ORDER_ID, modelList, userBean);
            }
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
		String TECH_ORDER_ID = ParamUtil.get(request, "TECH_ORDER_ID");
		if(!StringUtil.isEmpty(TECH_ORDER_ID)){
			Map<String,String> entry = techorderService.load(TECH_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 审核
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
             String TECH_ORDER_ID = ParamUtil.utf8Decoder(request, "TECH_ORDER_ID");
             String SALE_ORDER_ID = ParamUtil.utf8Decoder(request, "SALE_ORDER_ID");
             String TECH_ORDER_NO = ParamUtil.utf8Decoder(request, "TECH_ORDER_NO");
             
             List <TechorderModelChld> result = techorderService.childQuery(TECH_ORDER_ID);
             int size=result.size();
             if(size>0){
            	 for (int i = 0; i < size; i++) {
            		 Map<String,String> model=(Map<String, String>) result.get(i);
            		 Object IS_CAN_PRD_FLAG=model.get("IS_CAN_PRD_FLAG");
            		 if("1".equals(IS_CAN_PRD_FLAG.toString())){
            			 if("".equals(model.get("NEW_PRD_ID"))||null==model.get("NEW_PRD_ID")){
            				 throw new ServiceException("请检查所有可生产货品是否都选取新货品 !");
            			 }
            		 }
				}
             }
             techorderService.txAudit(TECH_ORDER_ID,SALE_ORDER_ID,TECH_ORDER_NO,userBean);
        }catch (ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
           jsonResult = jsonResult(false, "审核失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    /**
     * * 审核
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toChldAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
             String TECH_ORDER_DTL_IDS = ParamUtil.utf8Decoder(request, "TECH_ORDER_DTL_IDS");
             String TECH_ORDER_ID = request.getParameter("TECH_ORDER_ID");
             String jsonDate = request.getParameter("childJsonData");
             if (!StringUtil.isEmpty(jsonDate)) {
                 List <TechorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <TechorderModelChld>>() {
                 }.getType());
                 techorderService.txChildEdit(TECH_ORDER_ID, modelList, userBean);
             }
             int count = techorderService.checkChild(TECH_ORDER_DTL_IDS);
             if(count>0){
            	throw new ServiceException("请检查所有可生产货品是否都选取新货品 !");
             }
             techorderService.txChldAudit(TECH_ORDER_DTL_IDS,userBean);
        }catch (ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
           jsonResult = jsonResult(false, "审核失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * * 撤销
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toChldCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
             String TECH_ORDER_DTL_IDS = ParamUtil.utf8Decoder(request, "TECH_ORDER_DTL_IDS");
             techorderService.txChldCancel(TECH_ORDER_DTL_IDS);
        }catch (ServiceException s){
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
     * 查询订单的生命周期
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward toTrace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			List<Map<String,String>> result = techorderService.queryTrace(SALE_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("toTrace");
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
	    	pvgMap.put("PVG_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}