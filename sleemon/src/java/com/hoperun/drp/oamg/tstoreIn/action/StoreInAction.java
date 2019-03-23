package com.hoperun.drp.oamg.tstoreIn.action;

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
import com.hoperun.drp.oamg.tstoreIn.service.StoreInService;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModel;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModelChld;
import com.hoperun.sys.model.UserBean;

public class StoreInAction extends BaseAction {
    
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0014501";
    private static String PVG_EDIT="BS0014502";
    private static String PVG_DELETE="BS0014503";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0014504";
    //审核模块
    private static String PVG_BWS_AUDIT=" ";
    private static String PVG_AUDIT_AUDIT=" ";
    private static String PVG_TRACE_AUDIT=" ";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_STOREIN_NOTICE";
    private static String AUD_TAB_KEY="STOREIN_NOTICE_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_STOREIN_NOTICE_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.store.storeinnotice.service.impl.StoreinnoticeFlowImpl";
    /**审批 end**/
    /** 业务service*/
	private StoreInService storeInService;
 
	public StoreInService getStoreInService() {
		return storeInService;
	}
	public void setStoreInService(StoreInService storeInService) {
		this.storeInService = storeInService;
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
		
		// [STATE][search]add by zzb  2014-6-21 分销首页 [待入库]url用到 该参数
		String STATE = ParamUtil.utf8Decoder(request, "STATE");
		String search = ParamUtil.utf8Decoder(request, "search");
		request.setAttribute("STATE", STATE);
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
	    ParamUtil.putStr2Map(request, "STOREIN_NOTICE_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_ID", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	   
        ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		String PRD_NO=ParamUtil.utf8Decoder(request, "PRD_NO");
		params.put("PRD_NOQuery", StringUtil.creCon("a.PRD_NO", PRD_NO, ","));
		String PRD_NAME=ParamUtil.utf8Decoder(request, "PRD_NAME");
		params.put("PRD_NAMEQuery", StringUtil.creCon("a.PRD_NAME", PRD_NAME, ","));
        
        
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//modify zzb   2014-6-21 分销首页 [待入库]url用到 该参数
		if("isFirst".equals(search)){
			String STATE = ParamUtil.utf8Decoder(request, "STATE");
			if(!StringUtil.isEmpty(STATE)){
				params.put("STATE", STATE);
			}
		}else{
			 ParamUtil.putStr2Map(request, "STATE", params);
		}
		//权限级别和审批流的封装
		String strQXJB = ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean);
		if(StringUtil.isEmpty(search)){
			strQXJB =  strQXJB.replace(",'撤销','否决'", "");  
		}
		params.put("QXJBCON",strQXJB );
	    params.put("CRE_NAME","U9");
	    
	    //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
 	    params.put("channChrg",CHANNS_CHARG);
	    
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeInService.pageQueryByStoreIn(params, pageNo);
		//判断页面路径显示审核还是
    	params.put("module", module);
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
        String STOREIN_NOTICE_ID =ParamUtil.get(request, "STOREIN_NOTICE_ID");
        if(!StringUtil.isEmpty(STOREIN_NOTICE_ID))
        {
        	 List <StoreinnoticeModelChld> result = storeInService.childQuery(STOREIN_NOTICE_ID);
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
		String STOREIN_NOTICE_ID = ParamUtil.get(request, "STOREIN_NOTICE_ID");
		if(!StringUtil.isEmpty(STOREIN_NOTICE_ID)){
			Map<String,String> entry = storeInService.load(STOREIN_NOTICE_ID);
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
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String STOREIN_NOTICE_ID = request.getParameter("STOREIN_NOTICE_ID");
            Map<String,String> entry = storeInService.load(STOREIN_NOTICE_ID);
            if("审核通过".equals(entry.get("STATE")) || "提交".equals(entry.get("STATE"))){
            	errorId = "2";
            	throw new Exception();
            }
            
            List <StoreinnoticeModelChld> list = storeInService.childQuery(STOREIN_NOTICE_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            for (int i = 0; i < list.size(); i++) {
            	Map<String,String> map= (Map<String, String>) list.get(i);
				if(StringUtil.isEmpty(map.get("STOREIN_STORE_ID"))){
					errorId="1";
					throw new Exception();
				}
			}
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("1".equals(errorId)){
            	jsonResult = jsonResult(false, "请点击修改按钮，选择入库库房信息！");
            }else if("2".equals(errorId)){
                jsonResult = jsonResult(false, "此单据已经提交，请牢记单号并刷新页面");
            }else {
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
        String STOREIN_NOTICE_DTL_IDs = request.getParameter("STOREIN_NOTICE_DTL_IDS");
        String STOREIN_NOTICE_ID      = "";
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STOREIN_NOTICE_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STOREIN_NOTICE_DTL_IDS",STOREIN_NOTICE_DTL_IDs);
          List <StoreinnoticeModelChld> list = storeInService.loadChilds(params);
          request.setAttribute("rst", list);
          
          List <StoreinnoticeModelChld> li = storeInService.loadChildsByModel(params);
          for(int i=0;i<li.size();i++){
        	  StoreinnoticeModelChld  noticeChld = (StoreinnoticeModelChld)li.get(i);
        	  STOREIN_NOTICE_ID  = noticeChld.getSTOREIN_NOTICE_ID().toString();
          }
          Map<String,String> entry = storeInService.load(STOREIN_NOTICE_ID);
          String RECV_CHANN_NO = entry.get("RECV_CHANN_NO").toString();
          String IS_UPDATE_STOREIN = storeInService.getStoreInCount(RECV_CHANN_NO);
          request.setAttribute("IS_UPDATE_STOREIN", IS_UPDATE_STOREIN);
         }
        request.setAttribute("channNo", userBean.getCHANN_NO());
        request.setAttribute("channId", userBean.getCHANN_ID());
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
        return mapping.findForward("childedit");
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
		String STOREIN_NOTICE_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(STOREIN_NOTICE_ID)){
			Map<String,String> entry = storeInService.load(STOREIN_NOTICE_ID);
			entry.put("HAND_AND", BusinessConsts.HAND_AND);
			entry.put("editType", "update");
			request.setAttribute("rst", entry);
			
		} else {
			Map<String,String> entry = new HashMap<String,String>();
			entry.put("RECV_CHANN_ID", userBean.getCHANN_ID());
			entry.put("RECV_CHANN_NO", userBean.getCHANN_NO());
			entry.put("RECV_CHANN_NAME", userBean.getCHANN_NAME());
			entry.put("HAND_AND", BusinessConsts.HAND_AND);
			request.setAttribute("rst", entry);
		}
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_NOTICE_ID = ParamUtil.get(request, "STOREIN_NOTICE_ID");
        if(!StringUtil.isEmpty(STOREIN_NOTICE_ID))
        {
        	 List <StoreinnoticeModelChld> result = storeInService.childQuery(STOREIN_NOTICE_ID);
             request.setAttribute("rst", result);
             
             Map<String,String> entryT = storeInService.load(STOREIN_NOTICE_ID);
             String RECV_CHANN_NO = entryT.get("RECV_CHANN_NO").toString();
             String IS_UPDATE_STOREIN = storeInService.getStoreInCount(RECV_CHANN_NO);
             request.setAttribute("IS_UPDATE_STOREIN", IS_UPDATE_STOREIN);
        }
        request.setAttribute("channNo", userBean.getCHANN_NO());
        request.setAttribute("channId", userBean.getCHANN_ID());
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
            String STOREIN_NOTICE_ID = ParamUtil.get(request, "STOREIN_NOTICE_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            StoreinnoticeModel model = new Gson().fromJson(parentJsonData, new TypeToken <StoreinnoticeModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <StoreinnoticeModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <StoreinnoticeModelChld>>() {
                }.getType());
            }
            storeInService.txEdit(STOREIN_NOTICE_ID, model, chldModelList, userBean);
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
            String STOREIN_NOTICE_ID = request.getParameter("STOREIN_NOTICE_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <StoreinnoticeModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <StoreinnoticeModelChld>>() {
                }.getType());
                storeInService.txChildEdit(STOREIN_NOTICE_ID, modelList,userBean);
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
			String STOREIN_NOTICE_ID = ParamUtil.get(request, "STOREIN_NOTICE_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_NOTICE_ID", STOREIN_NOTICE_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            storeInService.txDelete(params);
            storeInService.clearCaches(params);
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
            String STOREIN_NOTICE_DTL_IDs = request.getParameter("STOREIN_NOTICE_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            storeInService.txBatch4DeleteChild(STOREIN_NOTICE_DTL_IDs);
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
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
     {
    	Map<String,String>pvgMap=new HashMap<String,String>();
    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
    	pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
    	pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
    	pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
		pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
    }
}
