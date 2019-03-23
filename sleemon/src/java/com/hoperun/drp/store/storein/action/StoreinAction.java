/**
 * prjName:喜临门营销平台
 * ucName:入库单处理
 * fileName:StoreinAction
*/
package com.hoperun.drp.store.storein.action;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storein.model.StoreinModelChld;
import com.hoperun.drp.store.storein.model.StoreinModelGrandChld;
import com.hoperun.drp.store.storein.service.StoreinService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-22 09:20:20
 */
public class StoreinAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0030501";
    private static String PVG_EDIT="FX0030501";
    private static String PVG_DELETE="FX0030501";
    //入库处理
    private static String PVG_DONE="FX0030504";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    //退回
    private static String PVG_RETURN="FX0030505";
    //反入库
    private static String PVG_BACKIN="FX0030506";
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
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private StoreinService storeinService;
    /**
	 * Sets the Storein service.
	 * 
	 * @param StoreinService the new Storein service
	 */
	public void setStoreinService(StoreinService storeinService) {
		this.storeinService = storeinService;
	}
	/** 日志 **/
	private Logger logger = Logger.getLogger(StoreinAction.class);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "STOREIN_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STORAGE_FLAG", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       
	    //默认条件beg
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        //按帐套过滤
		params.put("LEDGER_ID",userBean.getLoginZTXXID());
		//params.put("STOREIN_STORE_ID",userBean.getSTORE_CHARG());
		//默认条件end
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//初始时只显示未处理的数据
		if(StringUtil.isEmpty(search))
	    { 
			//状态为未处理
			params.put("STATE",BusinessConsts.UNDONE);
			params.put("selState", "");
	    } else {
	    	params.put("selState", params.get("STATE"));
	    }
		
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeinService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("RECV_CHANN_NO",userBean.getCHANN_NO());
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_ID =ParamUtil.get(request, "STOREIN_ID");
        if(!StringUtil.isEmpty(STOREIN_ID))
        {
        	 List <StoreinModelChld> result = storeinService.childQuery(STOREIN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
        return mapping.findForward("childlist");
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
        String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
        if(!StringUtil.isEmpty(STOREIN_ID))
        {
        	 List <StoreinModelChld> result = storeinService.childQuery(STOREIN_ID);
             request.setAttribute("rst", result);
        }
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
        String STOREIN_DTL_IDs = request.getParameter("STOREIN_DTL_IDS");
        if (!StringUtil.isEmpty(STOREIN_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STOREIN_DTL_IDS",STOREIN_DTL_IDs);
          List <StoreinModelChld> list = storeinService.loadChilds(params);
          request.setAttribute("rst", list);
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
	public void toDone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_DONE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
            //获取入库单信息
            Map<String,String> map = storeinService.load(STOREIN_ID);
			if("已处理".equals(map.get("STATE"))){
				throw new ServiceException("该单据已被["+map.get("UPD_NAME")+"]处理过了!");
			}
			
            Map<String,String> params=new HashMap<String,String>();
            params.put("STOREIN_ID", STOREIN_ID);
            List<StoreinModelChld> list=storeinService.childQuery(STOREIN_ID);
            storeinService.txEdit(STOREIN_ID, map, list, userBean);
            
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
            String STOREIN_ID = request.getParameter("STOREIN_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <StoreinModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <StoreinModelChld>>() {
                }.getType());
                storeinService.txChildEdit(STOREIN_ID, modelList,userBean);
            }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "新增失败");
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
			String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_ID", STOREIN_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			storeinService.txDelete(params);
			storeinService.clearCaches(params);
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
            String STOREIN_DTL_IDs = request.getParameter("STOREIN_DTL_IDS");
            //批量删除明细，多个Id之间用逗号隔开
            storeinService.txBatch4DeleteChild(STOREIN_DTL_IDs);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
		if(!StringUtil.isEmpty(STOREIN_ID)){
			Map<String,String> entry = storeinService.load(STOREIN_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 入库处理时校验
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DONE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            Map<String,String> params = new HashMap<String,String>();
    	    ParamUtil.putStr2Map(request, "STOREIN_ID", params);
    	    //只查询0的记录。1为删除。0为正常
            params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON); 
            Map<String,String> map = storeinService.queryList(params);
            Object ob = map.get("STORAGE_FLAG");
            String STORAGE_FLAG = ob.toString();
            ob =  map.get("STOREIN_NOTICE_DTL_CON");
            String STOREIN_NOTICE_DTL_CON = ob.toString();
            ob =  map.get("STOREIN_DTL_SUM");
            String STOREIN_DTL_SUM = ob.toString();
        	if ("1".equals(STORAGE_FLAG)) {
        		if ("0".equals(STOREIN_NOTICE_DTL_CON)) {
        			errorId = "0";
        			throw new Exception();
        		}
        		
        	} else if ("0".equals(STORAGE_FLAG)) {
        		if ("0".equals(STOREIN_DTL_SUM)) {
        			errorId = "1";
        			throw new Exception();
        		} 
        	} else {
        		throw new Exception();
        	}
        	String STOREIN_ID = request.getParameter("STOREIN_ID");
        	Map<String,String> storeMap = storeinService.load(STOREIN_ID);
        	String STATEMENUT_DATE = storeMap.get("STATEMENUT_DATE");
        	if(StringUtil.isEmpty(STATEMENUT_DATE)){
        		STATEMENUT_DATE = DateUtil.newDataTime();
        	}
        	boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),STATEMENUT_DATE);
			if(isMonthAcc){
				errorId="2";
				throw new Exception("处于月结阶段,不能做入库!");
			}
			boolean storeFreezeFlag=LogicUtil.checkFreezeStore(map.get("STOREIN_STORE_ID"));
			if(!storeFreezeFlag){
				errorId="2";
				throw new Exception("当前入库库房已被冻结,不能入库!");
			}
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "当前记录没有维护库位明细");
            } else if("1".equals(errorId)) {
                jsonResult = jsonResult(false, "当前记录没有维护入库明细");
            }else if("2".equals(errorId)){
				jsonResult = jsonResult(false, e.getMessage());
            }else{
            	jsonResult = jsonResult(false, "操作失败！");
            }
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /* ===============孙表处理开始===============*/
	 /**
     * * 明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward grandChildList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_DTL_ID = ParamUtil.get(request, "STOREIN_DTL_ID");
        String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
        List <StoreinModelGrandChld> result = storeinService.grandChild(STOREIN_ID,STOREIN_DTL_ID);
        request.setAttribute("rst", result);
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("STOREIN_DTL_ID",STOREIN_DTL_ID);
        return mapping.findForward("grandChildlist");
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
    public ActionForward modifyToGrandChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STOREIN_DTL_ID = ParamUtil.get(request, "STOREIN_DTL_ID");
        if(!StringUtil.isEmpty(STOREIN_DTL_ID))
        {
        	 List <StoreinModelGrandChld> result = storeinService.grandChildQuery(STOREIN_DTL_ID);
             request.setAttribute("rst", result);
         	//多个Id批量查询，用逗号隔开
     		 Map<String,String> params = new HashMap<String,String>();
     		 params.put("STOREIN_DTL_IDS","'"+STOREIN_DTL_ID+"'");
             List <StoreinModelChld> list = storeinService.loadChilds(params);
             request.setAttribute("child", list);
        }
        request.setAttribute("STOREIN_DTL_ID", STOREIN_DTL_ID);
        return mapping.findForward("grandChildedit");
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
    public ActionForward toGrandChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String STOREIN_STORG_DTL_IDs = request.getParameter("STOREIN_STORG_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STOREIN_STORG_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STOREIN_STORG_DTL_IDS",STOREIN_STORG_DTL_IDs);
          List <StoreinModelGrandChld> list = storeinService.loadGrandChilds(params);
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
    public void grandChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <StoreinModelGrandChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <StoreinModelGrandChld>>() {
                }.getType());
                storeinService.txGrandChildEdit(modelList);
                List<StoreinModelChld> chldList = new ArrayList<StoreinModelChld>();
                String STOREIN_ID = modelList.get(0).getSTOREIN_ID();
                String realNum = ParamUtil.get(request, "realNum");
                StoreinModelChld child = new StoreinModelChld();
                child.setREAL_NUM(realNum);
                child.setSTOREIN_DTL_ID( modelList.get(0).getSTOREIN_DTL_ID());
                chldList.add(child);
                storeinService.txChildEdit(STOREIN_ID,chldList,userBean);
                jsonResult = jsonResult(true, STOREIN_ID);
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
     * 退回
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STOREIN_ID = request.getParameter("STOREIN_ID");
            Map<String,String> storeinMap=storeinService.load(STOREIN_ID);
            String BILL_TYPE=storeinMap.get("BILL_TYPE");//单据类型
            String FROM_BILL_ID=storeinMap.get("FROM_BILL_ID");//来源单据id
            storeinService.txReturnStorein(STOREIN_ID,FROM_BILL_ID,BILL_TYPE,userBean);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "退回失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 反入库
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void backin(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BACKIN))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STOREIN_ID = request.getParameter("STOREIN_ID");
            Map<String,String> storeMap = storeinService.load(STOREIN_ID);
            String BillType = storeMap.get("BILL_TYPE");
        	String STATEMENUT_DATE = storeMap.get("STATEMENUT_DATE");
        	if(StringUtil.isEmpty(STATEMENUT_DATE)){
        		STATEMENUT_DATE = DateUtil.newDataTime();
        	}
        	boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),STATEMENUT_DATE);
			if(isMonthAcc){
				throw new ServiceException("处于月结阶段,不能做入库!");
			}
			boolean storeFreezeFlag=LogicUtil.checkFreezeStore(storeMap.get("STOREIN_STORE_ID"));
			if(!storeFreezeFlag){
				throw new Exception("当前入库库房已被冻结,不能入库!");
			}
            storeinService.txBackiin(STOREIN_ID,BillType,userBean);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "反入库失败");
            e.printStackTrace();
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
    public void grandChildDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STOREIN_STORG_DTL_IDs = request.getParameter("STOREIN_STORG_DTL_IDS");
            String STOREIN_DTL_ID=request.getParameter("STOREIN_DTL_ID");
            //批量删除，多个Id之间用逗号隔开
            storeinService.txBatch4DeleteGrandChild(STOREIN_STORG_DTL_IDs,STOREIN_DTL_ID);
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
	 * to 货品扫码页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toEditScanDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String STOREIN_ID = ParamUtil.get(request, "STOREIN_ID");
		if (!StringUtil.isEmpty(STOREIN_ID)) {
			List<StoreinModelChld> result = storeinService.childQuery(STOREIN_ID);
			request.setAttribute("rst", result);
		}
	    request.setAttribute("STOREIN_ID", STOREIN_ID);
		return mapping.findForward("scan");
	}
	public void scanChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String STOREIN_ID =ParamUtil.get(request, "STOREIN_ID");
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<StoreinModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<StoreinModelChld>>() {
						}.getType());
				storeinService.txScanChildEdit(STOREIN_ID, modelList,userBean);
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**跟据序列号查出库货品信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	 
	@SuppressWarnings("unchecked")
	public void editScanList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try{
			String pdtSN = ParamUtil.get(request, "SN");
			Map<String,Object> erpmap = storeinService.getErpStoreInDtl(pdtSN);
		    if(erpmap!=null){
		    	String pdtId = (String)erpmap.get("PRD_ID");
		    	pdtId = pdtId.trim();
			    Map<String,Object> drpMap = storeinService.getDrpStoreInDtl(pdtId);
				if(drpMap!=null){
					 jsonResult = new Gson().toJson(new Result(true, drpMap, ""));
				}else{
					jsonResult = jsonResult(false, "该货品不是此次要发的货品");	
				}		    	
		    }else{
		    	jsonResult = jsonResult(false, "没有匹配到货品信息,请检查");	
		    }
		}catch(Exception e){
			logger.info(e);
			jsonResult = jsonResult(false, "描码货品失败");	
		}
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	
	
	// 导出
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "STOREIN_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "STORAGE_FLAG", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "STOREIN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       
	    //默认条件beg
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        //按帐套过滤
		params.put("LEDGER_ID",userBean.getLoginZTXXID());
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		if(StringUtil.isEmpty(search)){ 
			//状态为未处理
			params.put("STATE",BusinessConsts.UNDONE);
	    }
		
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		List list = storeinService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="入库单编号,来源单据编号,单据类型,发货方/退货门店名称,收货方编号,收货方名称,入库库房,处理时间,处理人,制单人,制单时间,状态," +
				"货品编号,货品名称,规格型号,特殊工艺说明,品牌,标准单位,通入库数量,实际入库数量,单价,折扣率,折扣价,折后金额";
		String tmpContent = "STOREIN_NO,FROM_BILL_NO,BILL_TYPE,SEND_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,STOREIN_STORE_NAME,DEAL_TIME,DEAL_PSON_NAME,CRE_NAME,CRE_TIME,STATE," +
							"PRD_NO,PRD_NAME,PRD_SPEC,SPCL_DTL_REMARK,BRAND,STD_UNIT,NOTICE_NUM,REAL_NUM,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT";
		String colType = "string,string,string,string,string,string,string,string,string,string,string,string," +
		   "string,string,string,string,string,string,number,number,number,number,number,number" ;
		try {
			ExcelUtil
					.doExport(response, list, "入库单明细", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
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
	    	pvgMap.put("PVG_RETURN",QXUtil.checkPvg(userBean, PVG_RETURN) );
	    	pvgMap.put("PVG_DONE",QXUtil.checkPvg(userBean, PVG_DONE) );
	    	pvgMap.put("PVG_BACKIN",QXUtil.checkPvg(userBean, PVG_BACKIN) );
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}