package com.hoperun.drp.sale.changeadvcorder.action;

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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModel;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelChld;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelGchld;
import com.hoperun.drp.sale.changeadvcorder.service.ChangeadvcorderService;
import com.hoperun.sys.model.UserBean;

/**
 * 销售管理-终端销售-预订单变更
 * @author gu_hongxiu
 *
 */
public class ChangeadvcorderAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
	//增删改查
	private static String PVG_BWS="FX0021901";
    private static String PVG_EDIT="FX0021902";
    private static String PVG_DELETE="FX0021903";
    private static String PVG_COMMIT_CANCLE="FX0021904";
    private static String PVG_TRACE = "FX0021905";
       
    private static String AUD_BUSS_STATE="STATE";
  //审核模块
    private static String PVG_AUDIT_AUDIT="FX0022202";
    private static String PVG_TRACE_AUDIT="FX0022203";
    
    //审核模块
    private static String PVG_BWS_AUDIT="FX0022201";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_ADVC_ORDER_CHANGE";//表名
    private static String AUD_TAB_KEY="ADVC_ORDER_CHANGE_ID";//主键
    private static String AUD_BUSS_TYPE="DRP_ADVC_ORDER_CHANGE_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.sale.changeadvcorder.service.impl.ChangeFlowInterfaceImpl";
    
    /** 业务service*/
	private ChangeadvcorderService changeadvcorderService;
   
	public void setChangeadvcorderService(ChangeadvcorderService changeadvcorderService) {
		this.changeadvcorderService = changeadvcorderService;
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
		//是否是预订单录入过来的引用，为1时是，为0或空是为否
		String showFlag=request.getParameter("showFlag");
		String ADVC_ORDER_ID=request.getParameter("ADVC_ORDER_ID");
		if(!"1".equals(showFlag)){
			if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("showFlag", showFlag);
		request.setAttribute("ADVC_ORDER_ID", ADVC_ORDER_ID);
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
        Map<String,String> params = new HashMap<String,String>();
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
        String showFlag=request.getParameter("showFlag");
        if("1".equals(showFlag)){
        	request.setAttribute("showFlag", showFlag);
        }else{
        	 if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
         	{
         		throw new ServiceException("对不起，您没有权限 !");
         	}
        	//设置帐套id
    		params.put("LEDGER_ID", userBean.getLoginZTXXID());
        	params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
        }
		ParamUtil.putStr2Map(request, "ADVC_ORDER_CHANGE_NO", params);
		ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);	
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_ID", params);
	    if("1".equals(showFlag)){
	    	params.put("STATE", BusinessConsts.PASS);
	    }
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);       
		//字段排序
        ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = changeadvcorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		//add by zhuxw 20140717 预订单通用选取查询条件
		if("1".equals(userBean.getEMPY_FLAG()))
		{
			request.setAttribute("CONDISION", "TERM_ID='"+userBean.getBMXXID()+"'");
		}else
		{
			request.setAttribute("CONDISION", "CREATOR='"+userBean.getXTYHID()+"'");
		}
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
    	String showFlag=request.getParameter("showFlag");
        if("1".equals(showFlag)){
        	request.setAttribute("showFlag", showFlag);
        }else{
        	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
        		throw new ServiceException("对不起，您没有权限 !");
        	}
        }
        String ADVC_ORDER_CHANGE_ID =ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID)){
        	 List <ChangeadvcorderModelChld> result = changeadvcorderService.childQuery(ADVC_ORDER_CHANGE_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
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
	public ActionForward gchildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String showFlag=request.getParameter("showFlag");
		if(!"1".equals(showFlag)){
        	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
        		throw new ServiceException("对不起，您没有权限 !");
        	}
        }
		String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID)) {
			List<ChangeadvcorderModelGchld> result = changeadvcorderService.gchildQuery(ADVC_ORDER_CHANGE_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
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
		String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry=new HashMap<String,String>();
		if(!StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID)){
			entry = changeadvcorderService.load(ADVC_ORDER_CHANGE_ID);			
		}
		entry.put("LEDGER_ID", userBean.getLoginZTXXID());//预订单通用选取查询条件
		
		//add by zhuxw 20140717 预订单通用选取查询条件
		if("1".equals(userBean.getEMPY_FLAG()))
		{
			entry.put("CONDISION", "TERM_ID='"+userBean.getBMXXID()+"'");
		}else
		{
			entry.put("CONDISION", "CREATOR='"+userBean.getXTYHID()+"'");
		}
		
		request.setAttribute("rst", entry);
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
        String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID))
        {
        	 List <ChangeadvcorderModelChld> result = changeadvcorderService.childQuery(ADVC_ORDER_CHANGE_ID);
             request.setAttribute("rst", result);
        }
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
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            ChangeadvcorderModel model = new Gson().fromJson(parentJsonData, new TypeToken <ChangeadvcorderModel>() {}.getType());
            String childJsonData = ParamUtil.get(request, "childJsonData");
            List <ChangeadvcorderModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(childJsonData)) {
                chldModelList = new Gson().fromJson(childJsonData, new TypeToken <List <ChangeadvcorderModelChld>>() {
                }.getType());
            }
            changeadvcorderService.txEdit(ADVC_ORDER_CHANGE_ID, model, chldModelList, userBean);
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
	 * 详细信息
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
		String showFlag=request.getParameter("showFlag");
        if(!"1".equals(showFlag)){
        	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
           	{
           		throw new ServiceException("对不起，您没有权限 !");
           	}
        }
		String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID)){
			Map<String,String> entry = changeadvcorderService.load(ADVC_ORDER_CHANGE_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	 /**
     * * 明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ADVC_ORDER_CHANGE_DTL_IDs = request.getParameter("ADVC_ORDER_CHANGE_DTL_IDS");
            String ADVC_ORDER_CHANGE_ID=request.getParameter("ADVC_ORDER_CHANGE_ID");
            //批量删除，多个Id之间用逗号隔开
            if(!StringUtil.isEmpty(ADVC_ORDER_CHANGE_DTL_IDs)) {
            	changeadvcorderService.txBatch4DeleteChild(ADVC_ORDER_CHANGE_DTL_IDs,ADVC_ORDER_CHANGE_ID);
            }            
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
     * 直接编辑明细页面
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
        String ADVC_ORDER_CHANGE_DTL_IDs = request.getParameter("ADVC_ORDER_CHANGE_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(ADVC_ORDER_CHANGE_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_ORDER_CHANGE_DTL_IDS",ADVC_ORDER_CHANGE_DTL_IDs);
		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          List <ChangeadvcorderModelChld> list = changeadvcorderService.loadChilds(params);
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
            String ADVC_ORDER_CHANGE_ID = request.getParameter("ADVC_ORDER_CHANGE_ID");
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <ChangeadvcorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <ChangeadvcorderModelChld>>() {
                }.getType());
                changeadvcorderService.txChildEdit(ADVC_ORDER_CHANGE_ID,ADVC_ORDER_ID, modelList,"list");
            }
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
     * * 提交时，校验是否有明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try {
            String ADVC_ORDER_CHANGE_ID = request.getParameter("ADVC_ORDER_CHANGE_ID");
            List <ChangeadvcorderModelChld> list = changeadvcorderService.childQuery(ADVC_ORDER_CHANGE_ID);
            if (list.size() == 0) {
            	jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else{
            	jsonResult = jsonResult(true, "");
            }            
            
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
	 * * 主表 删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_CHANGE_ID = ParamUtil.get(request, "ADVC_ORDER_CHANGE_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            changeadvcorderService.txDelete(params);
            changeadvcorderService.clearCaches(params);
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
	 * * 预订单明细表 
	 * 	根据预订单明细ID，查询预订单明细信息
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void queryAdvcOrderChildInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_DTL_ID = ParamUtil.get(request, "ADVC_ORDER_DTL_ID");
			if(!StringUtil.isEmpty(ADVC_ORDER_DTL_ID)){
				Map<String, String> oldChildInfo = changeadvcorderService.loadOldChild(ADVC_ORDER_DTL_ID);
				
				if(oldChildInfo == null){
					jsonResult = jsonResult(false, "查询预订单明细信息失败!");
				}else{				
					jsonResult = new Gson().toJson(new Result(true, oldChildInfo, ""));
				}
			}
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "查询预订单明细信息失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * * 预订单明细表 
	 * 	根据多个预订单明细ID，查询多个预订单明细信息
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void queryAdvcOrderChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_DTL_IDS = ParamUtil.get(request, "ADVC_ORDER_DTL_IDS");
			           
			List<Map<String, String>> oldChildList = changeadvcorderService.loadOldChildList(ADVC_ORDER_DTL_IDS);
			
			if(oldChildList == null){
				jsonResult = jsonResult(false, "查询预订单明细列表失败!");
			}else{				
				jsonResult = new Gson().toJson(new Result(true, oldChildList, ""));
			}
            
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "查询预订单明细列表失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * * 预订单查询
	 * 	根据预订单ID，查询预订单信息是否在变更表中存在
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void queryAdvcOrderInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			           
			List<Map<String, String>> oldChilds = changeadvcorderService.loadOld(ADVC_ORDER_ID);
			
			if(oldChilds == null || oldChilds.size() == 0){
				jsonResult = jsonResult(true, "");
			}else{				
				jsonResult = new Gson().toJson(new Result(false, oldChilds, "该预订单已经存在变更记录!"));
			}
            
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "查询预订单变更表失败!");
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
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );	 
	    	
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );	    	
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	//pvgMap.put("PVG_SHIFT",QXUtil.checkPvg(userBean, PVG_SHIFT) );
	    	//pvgMap.put("PVG_REVERSE",QXUtil.checkPvg(userBean, PVG_REVERSE) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	   	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	   	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	   	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	
}
