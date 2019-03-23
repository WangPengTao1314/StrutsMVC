package com.hoperun.erp.sale.deliverconfm.action;

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
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.deliverconfm.service.DeliverconfmService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;
/**
 * 发运确认
 * @author zhang_zhongbin
 *
 */
public class DeliverconfmAction extends BaseAction{
	
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(DeliverconfmAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0011801";
	private static String PVG_EDIT = "BS0011803";
	private static String PVG_DELETE = "";
	
	private static String PVG_AFFIRM = "BS0011802";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
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
	
	private DeliverconfmService deliverconfmService;
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
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
		
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_START", params);
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "JOIN_DELIVER_ORDER_NO", params);
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
	    
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		String STATE = ParamUtil.get(request, "STATE");
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE in ('已发货','部分发货') ");// 
		}else if(StringUtil.isEmpty(STATE)){
//			sb.append(" and u.STATE in ('已提交库房','已发货','已收货','待收货','部分发货') ");//
		}else{
			params.put("STATE",STATE);
		}
		
		
//		//发运单号 转换为大写
//		String DELIVER_ORDER_NO = StringUtil.toUpperCase(ParamUtil.get(request, "DELIVER_ORDER_NO"));
//		params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
//		
//		//来源发运单编号 转换为大写
//		String FROM_BILL_NO = StringUtil.toUpperCase(ParamUtil.get(request, "FROM_BILL_NO"));
//		params.put("FROM_BILL_NO", FROM_BILL_NO);
//		
//		//出货计划号  转换为大写
//		String JOIN_DELIVER_ORDER_NO = StringUtil.toUpperCase(ParamUtil.get(request, "JOIN_DELIVER_ORDER_NO"));
//		params.put("JOIN_DELIVER_ORDER_NO", JOIN_DELIVER_ORDER_NO);
//		
//		//销售订单编号  转换为大写
//		String SALE_ORDER_NO = StringUtil.toUpperCase(ParamUtil.get(request, "SALE_ORDER_NO"));
//		params.put("SALE_ORDER_NO", SALE_ORDER_NO);
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		
		
		params.put("QXJBCON",sb.toString());
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ADVC_SEND_DATE", "ASC");
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = deliverconfmService.pageQuery(params, pageNo);
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
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			
			// 字段排序
			ParamUtil.setOrderField(request, params, "u.SALE_ORDER_NO", "desc");
			
			List<TurnoverplanChildModel> result = deliverconfmService.childQuery(params);
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
			Map<String,String> entry = deliverconfmService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
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
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_AFFIRM)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String,String> result = deliverconfmService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("toedit");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AFFIRM))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String parentJsonData = ParamUtil.get(request, "parentJsonData");
        String childJsonData = ParamUtil.get(request, "childJsonData");
        TurnoverplanModel model = new TurnoverplanModel();
        List<TurnoverplanChildModel> childList = null;
    	try {
    		   
            if (StringUtils.isNotEmpty(parentJsonData)) {
                model = new Gson().fromJson(parentJsonData, new TypeToken <TurnoverplanModel>() {
                }.getType());
            }
            
            if (StringUtils.isNotEmpty(childJsonData)) {
            	childList = new Gson().fromJson(childJsonData, new TypeToken <List<TurnoverplanChildModel>>() {
                }.getType());
            }
            
            deliverconfmService.txEdit(model,childList, userBean);
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
     * 发运确认
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void despatchAffirm(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AFFIRM)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
        String childData = ParamUtil.get(request, "childData");
        List<TurnoverplanChildModel> childList = new ArrayList<TurnoverplanChildModel>();
    	try {
    		   
            if (StringUtils.isNotEmpty(childData)) {
            	childList = new Gson().fromJson(childData, new TypeToken <List<TurnoverplanChildModel>>() {
                }.getType());
            }
            Map<String,Object> returnMap = deliverconfmService.txDespatchAffirm(DELIVER_ORDER_ID, userBean);
    		returnMap.put("strMessage",  "操作成功");
    		jsonResult = new Gson().toJson(new Result(true, returnMap, ""));
    		
        }catch(ServiceException e){
        	e.printStackTrace();
            jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AFFIRM))
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AFFIRM))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String DELIVER_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
			entry = deliverconfmService.load(DELIVER_ORDER_ID);
		}
		request.setAttribute("rst",entry);
		request.setAttribute("pvg",setPvg(userBean));
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AFFIRM))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
        if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
        	 List <TurnoverplanChildModel> result = deliverconfmService.childQuery(DELIVER_ORDER_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String DELIVER_ORDER_DTL_IDS = request.getParameter("DELIVER_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(DELIVER_ORDER_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("DELIVER_ORDER_DTL_IDS",DELIVER_ORDER_DTL_IDS);
          List <TurnoverplanChildModel> list = deliverconfmService.loadChilds(params);
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
    public void childEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String DELIVER_ORDER_ID = request.getParameter("DELIVER_ORDER_ID");
            String DELIVER_ORDER_NO = request.getParameter("DELIVER_ORDER_NO");
            String childJsonData = request.getParameter("childJsonData");
            
            if (!StringUtil.isEmpty(childJsonData)) {
                List <TurnoverplanChildModel> modelList = new Gson().fromJson(childJsonData, 
                		new TypeToken <List <TurnoverplanChildModel>>(){}.getType());
                 
                deliverconfmService.txChildEdit(DELIVER_ORDER_ID,DELIVER_ORDER_NO,modelList,userBean);
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
     * 发运确认的时候验证明细是否有差异数量
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void checkCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AFFIRM)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
        
    	try {
    		if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
    			//发运确认的时候验证明细是否有差异数量
                boolean have = deliverconfmService.txCheckCommit(DELIVER_ORDER_ID);
                if(have){
                	jsonResult = jsonResult(false, "该单据还有差异数量，请选择剩余货品的处理方式!");
                }else{
                	jsonResult = jsonResult(true, "");
                }
    		}
    		
        }catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    /**
     * 发运确认 填写延迟原因
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void writeReason(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AFFIRM)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
        String DELAY_TYPE = ParamUtil.get(request, "DELAY_TYPE");
        String REMARK = ParamUtil.get(request, "REMARK");
        
    	try {
    		if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
    			Map<String,String>params = new HashMap<String,String>();
                params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
                params.put("DELAY_TYPE", DELAY_TYPE);
                params.put("REMARK", REMARK);
                deliverconfmService.txWriteReason(params);
        		jsonResult = jsonResult(true, "操作成功");
    		}
    		
        }catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_START", params);
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "JOIN_DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
	    ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
	    
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		params.put("QXJBCON", sb.toString());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		
		
        List list=deliverconfmService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="发运单编号,合并发运单号,预计发货日期,单据类型,发货方式,车型,进场时间,发货类型,生产基地,代发区域服务中心编号,代发区域服务中心名称,发货方编号,发货方名称,订货方编号,订货方名称,收货方编号,收货方名称,收货地址编号,收货地址,总体积,物流公司编号,物流公司名称,状态,主表备注,制单人,制单时间,是否赠品,订单编号,货品编号,货品名称,规格型号,花号,品牌,标准单位,特殊规格说明,是否非标,是否抵库,预排发运数量,计划发运数量,实际发运数量,剩余数量,折后单价,单个体积,计发金额,计发体积,实发金额,实发体积,剩余发运体积,剩余货品处理方式,备注,行状态,U9订单编号,U9订单行编号";
        String colType = "string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,number,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,number,number,number,number,number,number,number,number,number,number,number,string,string,string,string,string";
        String tmpContent="DELIVER_ORDER_NO,JOIN_DELIVER_ORDER_NO,ADVC_SEND_DATE,BILL_TYPE,DELIVER_TYPE,TRUCK_TYPE,APPCH_TIME,CHANN_TYPE,SHIP_POINT_NAME,AREA_SER_NO,AREA_SER_NAME,SEND_CHANN_NO,SEND_CHANN_NAME,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO,RECV_ADDR,ALLVOL,PRVD_NO,PRVD_NAME,STATE,PRIMARYREMARK,CRE_NAME,CRE_TIME,IS_FREE_FLAG,SALE_ORDER_NO,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,SPCL_SPEC_REMARK,IS_NO_STAND_FLAG,IS_BACKUP_FLAG,ADVC_PLAN_NUM,PLAN_NUM,REAL_SEND_NUM,DIFFNUM,DECT_PRICE,VOLUME,PLANPRICE,PLANVOLDTL,ALLPRICE,ALLVOLDTL,NOSENDVOLDTL,NO_SEND_DEAL_TYPE,REMARK,ROWSTATE,U9_SALE_ORDER_NO,U9_SALE_ORDER_DTL_NO";
        String color="'',153-204-255,153-204-255,'','',255-255-0,255-255-0,'','','','','','','','',255-255-0,255-255-0,255-255-0,153-204-255,'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";
        try {
//			ExcelUtil.doExport(response, list, "货品发运", tmpContent, tmpContentCn);
			ExcelUtil.doExport(response, list, "发运确认", tmpContent, tmpContentCn,colType,color);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
//    /**
//     * U9接口
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     */
//    public void handlePdtDeliver(ActionMapping mapping, ActionForm form, 
//    		HttpServletRequest request, HttpServletResponse response){
//    	UserBean userBean =  ParamUtil.getUserBean(request);
//    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AFFIRM)){
//    		throw new ServiceException("对不起，您没有权限 !");
//    	}
//        PrintWriter writer = getWriter(response);
//        String jsonResult = jsonResult();
//        String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO");
//        Map<String,String> dliverMap = deliverconfmService.loadMain(DELIVER_ORDER_NO);
//        if(dliverMap==null){
//        	jsonResult = jsonResult(false, "发运单编号出错");
//        	return ;
//        }
//        String jsonData = ParamUtil.get(request, "jsonData");
//        List<TurnoverplanChildModel> childList = new ArrayList<TurnoverplanChildModel>();
//    	try {
//            if (StringUtils.isNotEmpty(jsonData)) {
//            	childList = new Gson().fromJson(jsonData, new TypeToken <List<TurnoverplanChildModel>>() {
//                }.getType());
//            	HashMap inputMap = new HashMap();
//            	HashMap headMap = new HashMap();
//            	headMap.put("ServiceCode", "OrderManage");
//            	headMap.put("Operation", "CreateOrder");
//            	headMap.put("AppCode", "erp");
//            	headMap.put("UId", "414d5120514d5f6c6f63616c202020203baa474c20012802");
//            	headMap.put("AuthId", "erp;password");
//            	ArrayList headList = new ArrayList();
//            	headList.add(headMap);
//            	inputMap.put("MbfHeader", headList);
//            	HashMap deliverMap = new HashMap();
//            	
//            	 ArrayList dtlList = new ArrayList();
//            	 ArrayList pdSnList = new ArrayList();
//            	for(TurnoverplanChildModel c : childList){
//            		
//            		Map<String,Object> map = new HashMap<String,Object>();
//                    String saleOrderNo = c.getSALE_ORDER_NO();
//                    String pdNo = c.getPRD_NO();
//                    if(saleOrderNo.trim().length()==0||pdNo.trim().length()==0){
//                    	continue;
//                    }
//                    Map<String,String> params = new HashMap<String,String>();
//                    params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
//                    params.put("SALE_ORDER_NO", saleOrderNo);
//                    params.put("PRD_NO", pdNo);
//                    Map<String,String> dliverDtlMap = deliverconfmService.loadDtlBySaleOrderNo(params);
//
//            		map.put("DELIVER_ORDER_DTL_ID", dliverDtlMap.get("DELIVER_ORDER_DTL_ID").trim());
//            		map.put("REAL_STOREOUT_NUM", c.getREAL_SEND_NUM());
//            		map.put("PRD_SN", c.getSN());
//            		dtlList.add(map);
//            	}
//            	deliverMap.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
//            	deliverMap.put("DETAIL", dtlList);
//            	ArrayList bodyList = new ArrayList();
//            	bodyList.add(deliverMap);
//            	inputMap.put("MbfBody", bodyList);
//            	
//            	HashMap serMap = new HashMap();
//            	ArrayList iputList = new ArrayList();
//            	iputList.add(inputMap);
//            	serMap.put("input1", iputList);
//            	
//            	HashMap jsonMap = new HashMap();
//            	ArrayList serList = new ArrayList();
//            	serList.add(serMap);
//            	jsonMap.put("MbfService", serList);
//            	String jsonDeliver = new Gson().toJson(jsonMap);
//            	String msg =LogicUtil.updateDeliverOrder(Consts.DM_USERNAME,Consts.DM_PASSWORD,jsonDeliver);
//            	InterReturnMsg returnMsg = new Gson().fromJson(msg,InterReturnMsg.class);
//            	if(BusinessConsts.FLASE.equals(returnMsg.getFLAG())){
//            		jsonResult = jsonResult(false, returnMsg.getMESSAGE());
//            	}else{
//            		jsonResult = jsonResult(false, msg);
//            	}
//            	
//            }
//    		
//        } catch (Exception e) {
//        	logger.info(e);
//            jsonResult = jsonResult(false, "操作失败");
//        }
//        if (null != writer) {
//            writer.write(jsonResult);
//            writer.close();
//        }
//    }
    
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
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_AFFIRM", QXUtil.checkPvg(userBean, PVG_AFFIRM));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}


	public DeliverconfmService getDeliverconfmService() {
		return deliverconfmService;
	}


	public void setDeliverconfmService(DeliverconfmService deliverconfmService) {
		this.deliverconfmService = deliverconfmService;
	}


	 
	
	
	 
	
	
	

}
