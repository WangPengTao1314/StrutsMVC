package com.hoperun.drp.store.statechange.action;
import java.io.PrintWriter;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.statechange.model.StateChangeModel;
import com.hoperun.drp.store.statechange.model.StateChangeModelChild;
import com.hoperun.drp.store.statechange.service.StateChangeService;
import com.hoperun.sys.model.UserBean;
/**
 * prjName:喜临门营销平台
 * ucName:形态转换
 * fileName:StateChangeAction
*/
public class StateChangeAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(StateChangeAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0031901";
    private static String PVG_EDIT="FX0031902";
    private static String PVG_DELETE="FX0031903";
    //启用,停用
    private static String PVG_START_STOP = "";
    //确认，取消
    private static String PVG_FINISH_CANCLE = "";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE = "FX0031904";
    private static String PVG_TRACE = "";
    //审核模块
    private static String PVG_BWS_AUDIT = "";
    private static String PVG_AUDIT_AUDIT = "";
    private static String PVG_TRACE_AUDIT = "";
    //审批流参数
    private static String AUD_TAB_NAME = "";
    private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
    private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
    /**审批 end**/
    /** 业务service*/
	private StateChangeService stateChangeService;
  
	
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
	    ParamUtil.putStr2Map(request, "STATE_CHANGE_NO", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search)){
			qx.append(" and u.STATE='未提交' ");
	    }
	    params.put("QXJBCON", qx.toString());
	    
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = stateChangeService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("module", module);
		request.setAttribute("pvg",setPvg(userBean));
		
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("ZTXXID",userBean.getLoginZTXXID());
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
        String STATE_CHANGE_ID = ParamUtil.get(request, "STATE_CHANGE_ID");
        if(!StringUtil.isEmpty(STATE_CHANGE_ID))
        {
        	List<Map<String,Object>>  result = stateChangeService.childQuery(STATE_CHANGE_ID);
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
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
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
		String STATE_CHANGE_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(STATE_CHANGE_ID)){
			entry = stateChangeService.load(STATE_CHANGE_ID);
		}
		//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		 
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
		request.setAttribute("rst",entry);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STATE_CHANGE_ID = ParamUtil.get(request, "STATE_CHANGE_ID");
        if(!StringUtil.isEmpty(STATE_CHANGE_ID)){
        	List<Map<String,Object>>  result = stateChangeService.childQuery(STATE_CHANGE_ID);
             request.setAttribute("rst", result);
        }
        
    	//终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		 
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
        String STATE_CHANGE_DTL_IDS = request.getParameter("STATE_CHANGE_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STATE_CHANGE_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STATE_CHANGE_DTL_IDS",STATE_CHANGE_DTL_IDS);
          List <StateChangeModelChild> list = stateChangeService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        //终端ID
		String TERM_ID = userBean.getTERM_ID();
	    request.setAttribute("TERM_ID",TERM_ID);	
		 
		//终端分管
		String TERM_CHARGE = userBean.getTERM_CHARGE();
		request.setAttribute("TERM_CHARGE",TERM_CHARGE);
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
            String STATE_CHANGE_ID = ParamUtil.get(request, "STATE_CHANGE_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            StateChangeModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <StateChangeModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <StateChangeModelChild> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <StateChangeModelChild>>() {
                }.getType());
            }
            stateChangeService.txEdit(STATE_CHANGE_ID, model, chldModelList, userBean);
        }catch(ServiceException e){
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
            String STATE_CHANGE_ID = request.getParameter("STATE_CHANGE_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <StateChangeModelChild> modelList = new Gson().
                fromJson(jsonDate, new TypeToken <List <StateChangeModelChild>>() {
                }.getType());
                stateChangeService.txChildEdit(STATE_CHANGE_ID, modelList);
            }
        }catch(ServiceException e){
        	jsonResult = jsonResult(false, e.getMessage());
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
			String STATE_CHANGE_ID = ParamUtil.get(request, "STATE_CHANGE_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STATE_CHANGE_ID", STATE_CHANGE_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
			stateChangeService.txDelete(params);
			stateChangeService.clearCaches(params);
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
            String STATE_CHANGE_DTL_IDS = request.getParameter("STATE_CHANGE_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            stateChangeService.txBatch4DeleteChild(STATE_CHANGE_DTL_IDS);
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
		String STATE_CHANGE_ID = ParamUtil.get(request, "STATE_CHANGE_ID");
		if(!StringUtil.isEmpty(STATE_CHANGE_ID)){
			Map<String,String> entry = stateChangeService.load(STATE_CHANGE_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				errorId="2";
				throw new Exception("处于月结阶段,不能做出库!");
			}
            String STATE_CHANGE_ID = request.getParameter("STATE_CHANGE_ID");
            List<Map<String,Object>> list = stateChangeService.childQuery(STATE_CHANGE_ID);
            if(list.size() == 0) {
                errorId = "0";
                throw new Exception("没有明细，不能提交");
            }else{
            	for(Map<String,Object> child : list){
            		int CHANGE_NUM = StringUtil.getInteger(child.get("CHANGE_NUM"));
            		int SAFE_NUM = StringUtil.getInteger(child.get("SAFE_NUM"));
            		if(CHANGE_NUM>SAFE_NUM){
            			errorId = "1";
                        throw new Exception("明细有货品["+ child.get("PAR_CHANGE_PRD_NO")+"]的转换数量大于库存数量");
            		}
            	}
            }
            //提交
            stateChangeService.txCommit(STATE_CHANGE_ID);
            jsonResult = jsonResult(true, "提交成功");
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }if ("1".equals(errorId)) {
                jsonResult = jsonResult(false, e.getMessage());
            }else if("2".equals(errorId)){
				jsonResult = jsonResult(false, e.getMessage());
			}else{
                jsonResult = jsonResult(false, "提交失败");
            }
           e.printStackTrace();
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
	 private Map<String,String> setPvg(UserBean userBean) {
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
		public StateChangeService getStateChangeService() {
			return stateChangeService;
		}
		public void setStateChangeService(StateChangeService stateChangeService) {
			this.stateChangeService = stateChangeService;
		}
	 
	 
	 

	 
}