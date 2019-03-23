package com.hoperun.drp.oamg.openterminal.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalChildModel;
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalModel;
import com.hoperun.drp.oamg.openterminal.model.TerminalCommModel;
import com.hoperun.drp.oamg.openterminal.service.OpenTerminalService;
import com.hoperun.sys.model.UserBean;

public class OpenTerminalAction extends BaseAction {

   
	//增删改查
    private static String PVG_BWS="BS0032101";
    private static String PVG_EDIT="BS0032102";
    private static String PVG_DELETE="BS0032103";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032104";
    //流程跟踪
    private static String PVG_TRACE= "BS0032105";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0032201";
    private static String PVG_AUDIT_AUDIT="BS0032202";
    private static String PVG_TRACE_AUDIT="BS0032203";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_OPEN_TERMINAL_REQ";
    private static String AUD_TAB_KEY="OPEN_TERMINAL_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_OPEN_TERMINAL_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private   OpenTerminalService   openterminalService;
	
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
	    String module = ParamUtil.get(request,"module");
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
        Map <String, String> params = new TreeMap <String, String>();
        ParamUtil.putStr2Map(request, "OPEN_TERMINAL_REQ_NO", params);
        ParamUtil.putStr2Map(request, "TERM_NO", params);
        ParamUtil.putStr2Map(request, "TERM_NAME", params);
        ParamUtil.putStr2Map(request, "TERM_TYPE", params);
        ParamUtil.putStr2Map(request, "TERM_LVL",  params);
        ParamUtil.putStr2Map(request, "CHANN_NO_P", params);
        ParamUtil.putStr2Map(request, "CHANN_NAME_P", params);
        ParamUtil.putStr2Map(request, "AREA_NO", params);
        ParamUtil.putStr2Map(request, "AREA_NAME", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_FROM", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_TO", params);
        ParamUtil.putStr2Map(request, "RNVTN_PROP", params);
        ParamUtil.putStr2Map(request, "BEG_BUSS_TYPE", params);
        ParamUtil.putStr2Map(request, "LAST_DECOR_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "LAST_DECOR_TIME_END", params);
        ParamUtil.putStr2Map(request, "pageSize", params);
        
        String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
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
	     IListPage page = null;
	     request.setAttribute("module",module);
	     //渠道分管
	     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	     params.put("CHANNS_CHARG", CHANNS_CHARG);
			
	     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 page = openterminalService.pageQuery(params, pageNo);
		 request.setAttribute("pvg",setPvg(userBean));
		 request.setAttribute("module", module);
         request.setAttribute("params", params);
         request.setAttribute("page", page);
         return mapping.findForward("list");
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
        String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
        String jsonResult = jsonResult();
        try{
        	String jsonData = ParamUtil.get(request, "jsonData");
            OpenTerminalModel model = new OpenTerminalModel();
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, 
                		new TypeToken <OpenTerminalModel>() {}.getType());
            }
            openterminalService.txEdit(OPEN_TERMINAL_REQ_ID, model,userBean);
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
			String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
			if (StringUtils.isNotEmpty(OPEN_TERMINAL_REQ_ID)) {
				Map<String,String> entry = openterminalService.load(OPEN_TERMINAL_REQ_ID);
				request.setAttribute("rst", entry);
				Map<String,String> entryT = openterminalService.loadTt(OPEN_TERMINAL_REQ_ID);
				request.setAttribute("rstT", entryT);
				Map<String,String> entryTt = openterminalService.loadTtT(OPEN_TERMINAL_REQ_ID);
				request.setAttribute("rstTt", entryTt);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(OPEN_TERMINAL_REQ_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
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
        String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");

        if (StringUtils.isNotEmpty(OPEN_TERMINAL_REQ_ID)) {
            try {
            	openterminalService.txDelete(OPEN_TERMINAL_REQ_ID, userBean);
            	openterminalService.clearCaches(OPEN_TERMINAL_REQ_ID);
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
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String OPEN_TERMINAL_REQ_ID    = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
 		 int count = openterminalService.queryTerminalState(OPEN_TERMINAL_REQ_ID);
		 String RNVTN_PROP = openterminalService.queryRrop(OPEN_TERMINAL_REQ_ID);
		 if(count !=0 && RNVTN_PROP.equals("新开")) {
			 openterminalService.txTerminal(OPEN_TERMINAL_REQ_ID,userBean);
		 }
		 if(count !=0 && RNVTN_PROP.equals("翻新")){
			 openterminalService.upTerminal(OPEN_TERMINAL_REQ_ID,userBean);
		 }
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	}
    
    public void IsCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String OPEN_TERMINAL_REQ_ID    = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
 		 int count = openterminalService.queryIsCommit(OPEN_TERMINAL_REQ_ID);
 		 Map<String,Object>  result = new HashMap<String,Object>();
 		 result.put("count", count);
 		 jsonResult = new Gson().toJson(new Result(true, result, ""));
	     if (null != writer) {
            writer.write(jsonResult);
            writer.close();
	     }
	}
    
    /**
	 * * to 主表编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(OPEN_TERMINAL_REQ_ID)){
			entry = openterminalService.load(OPEN_TERMINAL_REQ_ID);
		} 
		request.setAttribute("userName", userBean.getXM());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
	}
	
    /**
	 * 明细保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void childEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
			String jsonDate = request.getParameter("jsonData");
			String childJsonData = request.getParameter("childJsonData");
			
			OpenTerminalChildModel childModel = null;
			List<TerminalCommModel> commList = null;
			if (StringUtils.isNotEmpty(childJsonData)) {
				commList = new Gson().fromJson(childJsonData,
						new TypeToken<List<TerminalCommModel>>() {
						}.getType());
			}
			
			if (StringUtils.isNotEmpty(jsonDate)) {
				childModel = new Gson().fromJson(jsonDate,
						new TypeToken<OpenTerminalChildModel>() {
						}.getType());
				openterminalService.txChildEdit(OPEN_TERMINAL_REQ_ID, childModel,commList);
			}
			
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

	/**
	 * 到明细编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
		if (StringUtils.isNotEmpty(OPEN_TERMINAL_REQ_ID)) {
			Map<String,Object> child = openterminalService.loadChild(OPEN_TERMINAL_REQ_ID);
			if(null != child){
				List<Map<String,Object>> commList = openterminalService
				.loadComms(StringUtil.nullToSring(child.get("OPEN_TERMINAL_REQ_DTL_ID")));
				request.setAttribute("commList", commList);
			}
			
			request.setAttribute("rst", child);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childedit");
	}

	/**
	 * 区域分管明细列表 Description :.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
		if (StringUtils.isNotEmpty(OPEN_TERMINAL_REQ_ID)) {
			Map<String,Object> child = openterminalService.loadChild(OPEN_TERMINAL_REQ_ID);
			if(null != child){
				Map<String,Object> commNames = openterminalService
				.loadCommNames(StringUtil.nullToSring(child.get("OPEN_TERMINAL_REQ_DTL_ID")));
				request.setAttribute("commNames", commNames);
			}
			request.setAttribute("rst", child);
		}
		request.setAttribute("pvg", setPvg(userBean));
		// 为空直接跳转显示页面，而不是错误提示。
		return mapping.findForward("childlist");
	}

	/**
	 *  明细删除
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
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
			//区域ID
			String OPEN_TERMINAL_REQ_ID = request.getParameter("OPEN_TERMINAL_REQ_ID");
			Map<String,String> params = new HashMap<String,String>();
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
			openterminalService.txBatch4DeleteChild(params);
			jsonResult = jsonResult(true, "删除成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

    /**
	 * 删除竞品
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void deleteComm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID");
			String COMMODITIES_ID = ParamUtil.get(request, "COMMODITIES_ID");
			if (StringUtils.isNotEmpty(COMMODITIES_ID)) {
				openterminalService.txDeleteComm(COMMODITIES_ID);
			}
			jsonResult = jsonResult(true, "删除成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}
	
	/**
	 * 主从表编辑框架页面.
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
		String EDIT_FLAG = ParamUtil.get(request, "EDIT_FLAG");
		request.setAttribute("EDIT_FLAG", EDIT_FLAG);
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("editFrame");
	}

	/**
	 * 编码规则维护编辑页面加载子页面 Description :.
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
	public ActionForward modifyToChildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		String OPEN_TERMINAL_REQ_ID = ParamUtil.get(request, "OPEN_TERMINAL_REQ_ID"); 
		if (StringUtils.isNotEmpty(OPEN_TERMINAL_REQ_ID)) {
			List<OpenTerminalChildModel> result = this.openterminalService.childQuery(OPEN_TERMINAL_REQ_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childedit");
	}
	
	
	
	/**
     * *  提交
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String OPEN_TERMINAL_REQ_ID = request.getParameter("OPEN_TERMINAL_REQ_ID");
            if(!StringUtil.isEmpty(OPEN_TERMINAL_REQ_ID)){
            	List <OpenTerminalChildModel> list = openterminalService.childQuery(OPEN_TERMINAL_REQ_ID);
            	if(null == list || list.isEmpty()){
            		throw new ServiceException("评估表未填写！");
            	}
            }
            jsonResult = jsonResult(true, "提交成功");
		}catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "提交失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    public void loadModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String RNVTN_PROP = ParamUtil.get(request, "RNVTN_PROP");
        Map<String,Object>  result = openterminalService.queryJudgeModel(RNVTN_PROP);
        jsonResult = new Gson().toJson(new Result(true, result, ""));
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
	 
	/**
	 * @return
	 */
	public OpenTerminalService getOpenterminalService() {
		return openterminalService;
	}

	/**
	 * @param openterminalService
	 */
	public void setOpenterminalService(OpenTerminalService openterminalService) {
		this.openterminalService = openterminalService;
	}
}
