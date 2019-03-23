package com.hoperun.erp.sale.turnoverhd.action;

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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.turnoverhd.service.TurnoverhdService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;
/**
 * 交付单维护
 * @author zhang_zhongbin
 *
 */
public class TurnoverhdAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(TurnoverhdAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0011601";
	private static String PVG_EDIT = "BS0011602";
	private static String PVG_SHIP_EDIT = "BS0011604";
	
	//行关闭
	private static String PVG_ROW_CLOSE = "BS0011606";
	
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0011605";//撤销
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";

	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";

	private TurnoverhdService turnoverhdService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request,"paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}
 
	/**
	 * 一览页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("");
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "TRUCK_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
//		ParamUtil.putStr2Map(request, "PROV", params);
//		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    
	    ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
	    
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		 
		if(StringUtil.isEmpty(search)){
			 sb.append(" and u.STATE = '" + BusinessConsts.UNCOMMIT+"'");//'未提交'
		}else{
//			 sb.append(" and u.STATE in('" + BusinessConsts.UNCOMMIT+"','已提交生产')");
		}
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);

		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ADVC_SEND_DATE", "ASC");
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = turnoverhdService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			List<TurnoverplanChildModel> result = turnoverhdService
					.childQuery(DELIVER_ORDER_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
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
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
			Map<String,String> entry = turnoverhdService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
	}
    
	/**
	 * * 到编辑页面
	 * 
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String,String> result = turnoverhdService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("edit");
	}
	
    /**
     * 编辑.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        TurnoverplanModel model = new TurnoverplanModel();
    	try {
    		   
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <TurnoverplanModel>() {
                }.getType());
            }
            turnoverhdService.txEdit(model, userBean);
    		jsonResult = jsonResult(true, "修改成功");
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
        String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
        request.setAttribute("paramUrl", paramUrl);
        return mapping.findForward("editFrame");
    }
    
    /**
     * 重排
     */
    public void again(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
     
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
    	try {
    		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
            turnoverhdService.txAgain(DELIVER_ORDER_ID, userBean);
    		jsonResult = jsonResult(true, "操作成功");
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
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
            String DELIVER_ORDER_ID =  ParamUtil.get(request, "DELIVER_ORDER_ID"); 
            String jsonDate = ParamUtil.get(request,"childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <TurnoverplanChildModel> modelList = new Gson().fromJson(jsonDate,
                		new TypeToken <List <TurnoverplanChildModel>>() {
                }.getType());
                turnoverhdService.txChildEdit(DELIVER_ORDER_ID, modelList,userBean);
                jsonResult = jsonResult(true, "保存成功");
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
     *	撤销
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toCancel(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        int errorId = 0;
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO");     	
        try {
    		Map<String,String> map = turnoverhdService.load(DELIVER_ORDER_ID);
    		String FORM_BILL_ID = map.get("FROM_BILL_ID");
    		String STATE = map.get("STATE");
    		if(!"已提交生产".equals(STATE)){
    			errorId = 1;
    			throw new ServiceException("当前主表状态下不能撤销!");
    		}

    		if(!StringUtil.isEmpty(FORM_BILL_ID)){
    			errorId = 2;
    			throw new ServiceException("有来源发运单据的不能撤销!");
    		}
    		String ServiceCode = "TC0300010";
    		String AppCode = "DM";
    		String UId = StringUtil.uuid32len();
    		String OPRCODE = ServiceCode + ":" + "createSO";
   /*****************************接口暂时关闭*********************************/
    		String strResult = null;
    		try{
	    		String strJsonData = LogicUtil.getStrCreateSO(DELIVER_ORDER_ID,"1");
	    		LogicUtil.actLog("销售订单管理", "成功调入销售订单撤销生产", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
	    		strResult = turnoverhdService.txCancel(DELIVER_ORDER_ID, userBean,strJsonData);
	            if("操作成功".equals(strResult)){
                	LogicUtil.actLog("销售订单管理", "销售订单撤销生产成功", "U9系统", "成功",strResult,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                	jsonResult = jsonResult(true,strResult);
                }else{
                	LogicUtil.actLog("销售订单管理", "销售订单撤销生产失败", "U9系统", "失败",strResult,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                	jsonResult = jsonResult(false,strResult);
                }
    		}catch(Exception ex){
    			LogicUtil.actLog("销售订单管理", "销售订单撤销生产失败", "U9系统", "失败",ex.getMessage(),AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
    			throw new ServiceException(ex.getMessage());
    		}
    		 /*****************************接口暂时关闭*********************************/
    		jsonResult = jsonResult(true, "撤销成功");
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
        	if(errorId == 1){
        		jsonResult = jsonResult(false, e.getMessage());
        	}else if(errorId == 2){
        		jsonResult = jsonResult(false, e.getMessage());
        	}else{
        		jsonResult = jsonResult(false, e.getMessage());
        	}
            
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    

    /**
     * 行关闭
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void closeChilds(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_ROW_CLOSE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        int erroeId = 0;
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO"); 
        try { 
    		Map<String,String> bill = turnoverhdService.load(DELIVER_ORDER_ID);
    		if(!"已提交生产".equals(bill.get("STATE"))){
    			erroeId = 1;
    			throw new ServiceException("当前主表状态下不能行关闭!");
    		}
    		String DELIVER_ORDER_DTL_IDS = ParamUtil.get(request, "DELIVER_ORDER_DTL_IDS");
            if (StringUtils.isNotEmpty(DELIVER_ORDER_DTL_IDS)) {
        		String ServiceCode = "TC0300010";
        		String AppCode = "DM";
        		String UId = StringUtil.uuid32len();
        		String OPRCODE = ServiceCode + ":" + "createSO";
        		String strJsonData = "";
        		try{
        			strJsonData = LogicUtil.getStrCreateSO(DELIVER_ORDER_ID,"0");
        			LogicUtil.actLog("销售订单管理", "成功调入销售订单行关闭成功", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
	        		 String strResult = turnoverhdService.txCloseChilds(DELIVER_ORDER_ID,DELIVER_ORDER_DTL_IDS, userBean,strJsonData);
	                if("操作成功".equals(strResult)){
	                	LogicUtil.actLog("销售订单管理", "销售订单行关闭成功", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
	                	jsonResult = jsonResult(true,strResult);
	                }else{
	                	LogicUtil.actLog("销售订单管理", "销售订单行关闭失败", "U9系统", "失败","["+strResult+"]"+strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
	                	throw new ServiceException(strResult);
	                }
        		}catch(Exception ex){
        			LogicUtil.actLog("销售订单管理", "销售订单行关闭失败", "U9系统", "失败", "["+ex.getMessage()+"]"+strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
        			throw new ServiceException(ex.getMessage());
        		}
            	jsonResult = jsonResult(true,"操作成功");
            }
    		
        } catch (Exception e) {
        	if(erroeId == 1){
        		jsonResult = jsonResult(false,e.getMessage());
        	}else{
        		jsonResult = jsonResult(false,e.getMessage());
        	}
        	e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    
    
    /**
     * 提交交付计划
     */
    public void commitPlan(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID"); 
		String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO");    	 
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
		String ServiceCode = "TC0300010";
		String AppCode = "DM";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strResult ="";
		String strJsonData ="";
    	try {
    		List checkList = turnoverhdService.checkDeliverOrderDtl(DELIVER_ORDER_ID);
    		if(checkList!=null && checkList.size()>0){
    			throw new ServiceException("存在数量为0或者单价为0的货品,不能提交!");
    		}else{
        		strJsonData = LogicUtil.getStrCreateSO(DELIVER_ORDER_ID,null);
        		LogicUtil.actLog("销售订单管理", "开始调入生成销售订单接口", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                strResult = turnoverhdService.txCommitPlan(DELIVER_ORDER_ID,strJsonData, userBean,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                if("操作成功".equals(strResult)){
                	LogicUtil.actLog("销售订单管理", "生成销售订单成功", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                	jsonResult = jsonResult(true,strResult);
                }else{
                	LogicUtil.actLog("销售订单管理", "生成销售订单失败", "U9系统", "失败","["+strResult+"]"+strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
                	jsonResult = jsonResult(false,strResult);
                }
    		}	
        } catch (Exception e) {
        	LogicUtil.actLog("销售订单管理", "生成销售订单失败", "U9系统", "失败","["+e.getMessage()+"]"+strJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
            jsonResult = jsonResult(false,"接口出错："+(null==e.getMessage()?"":e.getMessage()));
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 查询 未排车货品
     * @return
     */
	public ActionForward toNotPlanList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "IS_HAVE_AREA_SER", params);
		ParamUtil.putStr2Map(request, "IS_NO_STAND_FLAG", params);
		ParamUtil.putStr2Map(request, "ORDER_DATE", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		String search = ParamUtil.get(request, "search");
		String UN_PLAN_NUM = ParamUtil.get(request, "UN_PLAN_NUM");
		if(!StringUtil.isEmpty(search)){
			if(!StringUtil.isEmpty(UN_PLAN_NUM)){
				Integer UN_PLAN_NUM_I = ParamUtil.getInt(request, "UN_PLAN_NUM",-111);
				params.put("UN_PLAN_NUM", UN_PLAN_NUM_I.toString());
			}
			
			String IS_HAVE_AREA_SER = ParamUtil.get(request, "IS_HAVE_AREA_SER");
			if(!StringUtil.isEmpty(IS_HAVE_AREA_SER)){
				if(BusinessConsts.INTEGER_1.equals(IS_HAVE_AREA_SER)){
					params.put("FLAG_NOT_NULL", BusinessConsts.DEL_FLAG_COMMON);
				}else{
					params.put("FLAG_NULL", BusinessConsts.DEL_FLAG_COMMON);
				}
			}
			
		}else{
			params.put("UN_PLAN_NUM", BusinessConsts.INTEGER_2);//默认查未排车货品数量小于2
			params.put("FLAG_NULL", BusinessConsts.DEL_FLAG_COMMON);//默认查 没有区域服务中心渠道商
		}
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("STATE", "('退回','审核通过')");
		params.put("MORE_THAN_ZERO", "0");
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "s.ORDER_DATE", "DESC");

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = turnoverhdService.pageNotPalanQuery(params, pageNo);
		
		params.put("UN_PLAN_NUM", UN_PLAN_NUM);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);

		return mapping.findForward("unplan");
	}
	
	/**
	 * 修改交期
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void modifyAdvDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	 
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
		String ServiceCode = "TU0300020";
		String Operation = "modifyRequireDate";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "modifyRequireDate";
    	try {
    		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID"); 
    		String TEMP_DATE = ParamUtil.get(request, "TEMP_DATE"); 
    		//修改交期 与U9交互
    		String strJsonData = LogicUtil.strModifyRequireDate(DELIVER_ORDER_ID,TEMP_DATE,ServiceCode,Operation,AppCode, DestCode,UId,OPRCODE);
    		LogicUtil.actLog("销售订单管理", "开始调入修改交期接口", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE);
    		String strResult = turnoverhdService.txUpdateAdvDate(DELIVER_ORDER_ID,strJsonData,TEMP_DATE,userBean);
    		if("操作成功".equals(strResult)){
            	LogicUtil.actLog("销售订单管理", "修改交期成功", "U9系统", "成功",strResult,AppCode,UId,OPRCODE);
            	jsonResult = jsonResult(true,strResult);
            }else{
            	LogicUtil.actLog("销售订单管理", "修改交期失败", "U9系统", "失败",strResult,AppCode,UId,OPRCODE);
            	jsonResult = jsonResult(false,strResult);
            }
            
        } catch (Exception e) {
        	LogicUtil.actLog("销售订单管理", "修改交期失败", "U9系统", "失败",e.getMessage(),AppCode,UId,OPRCODE);
            jsonResult = jsonResult(false,e.getMessage());
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
			List<Map<String,String>> result = turnoverhdService.queryTrace(SALE_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("toTrace");
	}
	
	 //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "TRUCK_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
	    
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		
        List list=turnoverhdService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="发运单编号,预计发货日期,单据类型,发货方式,车型,发货类型,生产基地,代发区域服务中心编号,代发区域服务中心名称,发货方编号,发货方名称,订货方编号,订货方名称,收货方编号,收货方名称,收货地址编号,收货地址,总体积,状态,主表备注,制单人,制单时间,订单编号,订货方名称,货品编号,货品名称,规格型号,花号,品牌,标准单位,特殊规格说明,是否非标,是否抵库,预排发运数量,收货地址,计划发运数量,剩余数量,折后单价,金额,单个体积,体积,剩余发运体积,备注,U9订单编号,U9订单行编号";
        String tmpContent="DELIVER_ORDER_NO,ADVC_SEND_DATE,BILL_TYPE,DELIVER_TYPE,TRUCK_TYPE,CHANN_TYPE,SHIP_POINT_NAME,AREA_SER_NO,AREA_SER_NAME,SEND_CHANN_NO,SEND_CHANN_NAME,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO,RECV_ADDR,ALLVOL,STATE,PRIMARYREMARK,CRE_NAME,CRE_TIME,SALE_ORDER_NO,ORDER_CHANN_NAME,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,SPCL_SPEC_REMARK,IS_NO_STAND_FLAG,IS_BACKUP_FLAG,ADVC_PLAN_NUM,DELIVER_DTL_ADDR,PLAN_NUM,NO_SEND_NUM,DECT_PRICE,ALLPRICE,VOLUME,ALLVOLDTL,NOSENDVOLDTL,REMARK,U9_SALE_ORDER_NO,U9_SALE_ORDER_DTL_NO";
        try {
			ExcelUtil.doExport(response, list, "货品发运", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,
				PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_ROW_CLOSE", QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_SHIP_EDIT", QXUtil.checkPvg(userBean, PVG_SHIP_EDIT));
		return pvgMap;
	}

	public TurnoverhdService getTurnoverhdService() {
		return turnoverhdService;
	}

	public void setTurnoverhdService(TurnoverhdService turnoverhdService) {
		this.turnoverhdService = turnoverhdService;
	}

}
