package com.hoperun.erp.sale.pdtdeliver.action;

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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.pdtdeliver.service.PdtdeliverService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;
/**
 * 货品发运
 * @author zhang_zhongbin
 *
 */
public class PdtdeliverAction extends BaseAction{
	
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(PdtdeliverAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0011701";
	private static String PVG_EDIT = "BS0011702";
	private static String PVG_DELETE = "BS0011703";
	//行关闭
	private static String PVG_ROW_CLOSE = "BS0011704";
	//整单关闭
	private static String PVG_ORDER_CLOSE = "BS0011705";
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
	
	private PdtdeliverService pdtdeliverService;
	
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
	 * 点击催款
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward selectFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("selectFrame");
	}
	
	/**
	 * 点击生成出货计划号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shipmentFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("shipmentFrame");
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

		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_START", params);//预计发货日期从
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);//预计发货日期到
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);//发运单号
//		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
//		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);//整车发运
//		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);//销售发运
		ParamUtil.putStr2Map(request, "CRE_NAME", params);//制单人
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);//制单时间从
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);//制单时间到
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);//收货方编号
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);//收货方名称
//		ParamUtil.putStr2Map(request, "JOIN_DELIVER_ORDER_NO", params);//出货计划号
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);//规格型号
	    ParamUtil.putStr2Map(request, "PRD_NO", params);//货品编号
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
	    ParamUtil.putStr2Map(request, "SHIP_POINT_ID", params);//货品名称编号
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);//生产基地名称
//		ParamUtil.putStr2Map(request, "PROV", params);//省份
//		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);//销售订单编号
//		ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);//来源发运单编号
		
//		ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
		
		String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO");
		DELIVER_ORDER_NO = StringUtil.toUpperCase(DELIVER_ORDER_NO);
		//拼装 like sql
		String sql = StringUtil.creCon("u.DELIVER_ORDER_NO", DELIVER_ORDER_NO, ",");
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String STATE = ParamUtil.get(request, "STATE");
		 
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		String forWard = ParamUtil.get(request, "forWard");
 
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ADVC_SEND_DATE", "ASC");
		if(!StringUtil.isEmpty(forWard)){
			if("select".equals(forWard)){
				params.put("NO_IN_CHANN","1");
			}
			params.put("STATE","已提交生产");
			
			String  pageSize =  ParamUtil.get(request, "pageSize");
			if(StringUtil.isEmpty(pageSize) || "7".equals(pageSize)){
				pageSize = "15";
			}
			params.put("pageSize", pageSize);
			
		}else{
			if(StringUtil.isEmpty(search)){
				sb.append(" and u.STATE = '已提交生产' ");
			}else if(StringUtil.isEmpty(STATE)){
//				sb.append(" and u.STATE in('已提交生产','已提交库房','发货延迟','已发货','已收货','待收货','未提交')");// 
			}else{
				params.put("STATE",STATE);
			}
			ParamUtil.putStr2Map(request, "pageSize", params);
		}
		
		sb.append(sql);
		params.put("QXJBCON",sb.toString());
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = pdtdeliverService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		if(StringUtil.isEmpty(forWard)){
			return mapping.findForward("list");
		}else if("shipment".equals(forWard)){
			return mapping.findForward("shipment");
		}else{
			return mapping.findForward("select");
		}
		
	}

	/**
	 * * 明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		UserBean userBean = ParamUtil.getUserBean(request);
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
			List<TurnoverplanChildModel> result = pdtdeliverService.childQuery(params);
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
			Map<String,String> entry = pdtdeliverService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
//		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		String DELIVER_ORDER_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String,String> result = pdtdeliverService.load(DELIVER_ORDER_ID);
			//增加默认物流
			if(StringUtil.isEmpty(result.get("PRVD_ID"))){
				Map<String,String> prvdMap=LogicUtil.getPrvdInfo();
				if(null!=prvdMap){
					result.putAll(prvdMap);
				}
			}
			request.setAttribute("rst", result);
		}
		
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
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
            pdtdeliverService.txEdit(model, userBean);
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
     * 提交库房
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void commitStore(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("提交库房开始");
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "createShipPlan";
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
    	try {
    		
    		
    		//将单据ID放入资源池 防止重复提交
//            String bussID =  ParamsPool.getInstance().getBillIDMap().get(DELIVER_ORDER_ID);
//            if(StringUtil.isEmpty(bussID)){
//            	ParamsPool.getInstance().getBillIDMap().put(DELIVER_ORDER_ID, DELIVER_ORDER_ID);
//            }else{
//            	throw new ServiceException("该单据正在处理....请稍后！");
//            }
            
            Map<String,String> map=pdtdeliverService.load(DELIVER_ORDER_ID);
            String childData = ParamUtil.get(request, "childData");
            List<TurnoverplanChildModel> childList = new ArrayList<TurnoverplanChildModel>();
    		
            if (StringUtils.isNotEmpty(childData)) {
            	childList = new Gson().fromJson(childData, new TypeToken <List<TurnoverplanChildModel>>() {
                }.getType());
            }
            /*********信用控制去掉2014/9/25*********/
//            String returnMesg = LogicUtil.chkCanUseCreditForSend(BusinessConsts.ACCT_DELIVER_CONF_ID, DELIVER_ORDER_ID);
//            if("true".equals(returnMesg)){
            	String strJsonData = LogicUtil.strCreateShipPlan(DELIVER_ORDER_ID, InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE); 
            	LogicUtil.actLog("出货单管理", "成功调入创建出货单接口", "UA系统", "成功",strJsonData,AppCode,UId,OPRCODE);
                //提交库房操作
            	Map<String,Object> returnMap = pdtdeliverService.txCommitStore(DELIVER_ORDER_ID,map,strJsonData,childList, userBean);
            	//将资源池的存放的单据ID释放
//            	ParamsPool.getInstance().getBillIDMap().remove(DELIVER_ORDER_ID);
            	 
            	String strResult = StringUtil.nullToSring(returnMap.get("strMessage"));
            	if("操作成功".equals(strResult)){
                	LogicUtil.actLog("出货单管理", "生成出货单成功", "UA系统", "成功",strResult,AppCode,UId,OPRCODE);
                	jsonResult = new Gson().toJson(new Result(true, returnMap, ""));
                }else{
                	LogicUtil.actLog("出货单管理", "生成出货单失败", "UA系统", "失败",strResult,AppCode,UId,OPRCODE);
                	jsonResult = jsonResult(false,strResult);
                }
//            }else{
//            	jsonResult = jsonResult(false, returnMesg);
//            }
        } catch (Exception e) {
        	logger.info(e);
        	LogicUtil.actLog("出货单管理", "生成出货单失败", "UA系统", "失败",e.getMessage(),AppCode,UId,OPRCODE);
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
  
	
    /**
     * 明细保存 保存 数字
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void childSave(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_DTL_ID = ParamUtil.get(request, "DELIVER_ORDER_DTL_ID");
        String PLAN_NUM = ParamUtil.get(request, "PLAN_NUM");
        String NO_SEND_NUM = ParamUtil.get(request, "NO_SEND_NUM");
        String NO_SEND_DEAL_TYPE = ParamUtil.get(request, "NO_SEND_DEAL_TYPE");
        String REMARK = ParamUtil.get(request, "REMARK");
    	try {
    		 Map<String, Object> params = new HashMap<String, Object>();
    		 params.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
    		 params.put("PLAN_NUM", PLAN_NUM);
    		 if("0".equals(PLAN_NUM)){
    			 params.put("IS_SEND_FIN", "1");
    		 }else{
    			 params.put("IS_SEND_FIN", "0");
    		 }
    		 params.put("NO_SEND_NUM", NO_SEND_NUM);
    		 params.put("NO_SEND_DEAL_TYPE", NO_SEND_DEAL_TYPE);
    		 params.put("REMARK", REMARK);
        	 pdtdeliverService.txUpdatChild(params);
        	 jsonResult = jsonResult(true, "操作成功");
    		
        }catch(Exception e){
        	logger.info(e);
            jsonResult = jsonResult(false, "操作失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
    

    /**
     * 明细保存 保存 汉字
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void updateChildSave(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_DTL_ID = ParamUtil.get(request, "DELIVER_ORDER_DTL_ID");
        String REMARK = ParamUtil.get(request, "REMARK");
        String NO_SEND_DEAL_TYPE = ParamUtil.get(request, "NO_SEND_DEAL_TYPE");
    	try {
    		 Map<String, Object> params = new HashMap<String, Object>();
    		 params.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
    		 params.put("REMARK", REMARK);
    		 params.put("NO_SEND_DEAL_TYPE", NO_SEND_DEAL_TYPE);
        	 pdtdeliverService.txUpdatChild(params);
        	 jsonResult = jsonResult(true, "操作成功");
    		
        }catch(Exception e){
        	logger.info(e);
            jsonResult = jsonResult(false, "操作失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
    
    
    /**
     * 催款
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void queryGooderOrderCreate(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
    	try {
    		String DELIVER_ORDER_IDS = ParamUtil.get(request, "DELIVER_ORDER_IDS");
            if (StringUtils.isNotEmpty(DELIVER_ORDER_IDS)) {
            	 List<Map<String, Object>> result = pdtdeliverService.queryDuingPerson(DELIVER_ORDER_IDS);
            	 jsonResult = new Gson().toJson(new Result(true, result, ""));
            }
    		
        } catch (RuntimeException e) {
        	jsonResult = jsonResult(false,"操作失败");
        	e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    
    
    /**
     * 生产状态查询
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward queryProStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		//获取参数
		Map params = new HashMap();
    	params.put("DeliverPlanNo", ParamUtil.utf8Decoder(request, "DELIVER_ORDER_NO"));//发运单号
		
		Map<String,Object> page = new HashMap<String,Object>();
		
		//调用服务
		List rst = pdtdeliverService.pageQueryU9(params);		
		page.put("result", rst);
		
		//检查服务调用结果
		if(rst != null && rst.size() == 1){
			Map temp = (Map) rst.get(0);
			String status =  (String) temp.get("Status");
			String showMsg = (String) temp.get("Desc");
			
			if(!StringUtil.isEmpty(status) && "false".equalsIgnoreCase(status)){
				if("No Results for Query!".equals(showMsg)){
					page.put("errorMsg", "U9未查找到数据");
				}else{
					page.put("errorMsg", showMsg);
				}
			}
		}
		
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("toQueryStatus");
	}
    
    
    
    
    
    
    
    
    
     //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_START", params);//预计发货日期从
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);//预计发货日期到
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);//发运单号
//		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
//		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);//整车发运
//		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);//销售发运
		ParamUtil.putStr2Map(request, "CRE_NAME", params);//制单人
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);//制单时间从
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);//制单时间到
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);//收货方编号
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);//收货方名称
//		ParamUtil.putStr2Map(request, "JOIN_DELIVER_ORDER_NO", params);//出货计划号
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);//规格型号
	    ParamUtil.putStr2Map(request, "PRD_NO", params);//货品编号
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);//生产基地名称
		ParamUtil.putStr2Map(request, "PROV", params);//省份
//		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);//销售订单编号
//		ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);//来源发运单编号
		ParamUtil.putStr2Map(request, "STATE", params);//状态
		
	    ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
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
		
        List list=pdtdeliverService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="发运单编号,出货计划号,交货日期,订货方编号,订货方名称,收货方编号,收货方名称,收货地址编号,收货地址,生产基地,货品编号,货品名称,规格型号,特殊规格说明,是否抵库,预排发运数量,计划发运数量,实发数量,未发数量,单价,计划发运金额,单位体积,汇总行总体积,备注,U9订单编号,U9订单行编号,货品分类,行状态,状态";
        String colType="string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,number,number,number,number,number,number,number,number,string,string,string,string,string,string";
        String color="'',153-204-255,153-204-255,'','',255-255-0,255-255-0,'','','','','','','','','',255-255-0,255-255-0,255-255-0,153-204-255,'','','','','','','','',''";
        String tmpContent="DELIVER_ORDER_NO,JOIN_DELIVER_ORDER_NO,ADVC_SEND_DATE,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO,RECV_ADDR,SHIP_POINT_NAME,PRD_NO,PRD_NAME,PRD_SPEC,SPCL_SPEC_REMARK,IS_BACKUP_FLAG,ADVC_PLAN_NUM,PLAN_NUM,REAL_SEND_NUM,DIFFNUM,DECT_PRICE,PLANPRICE,VOLUME,PLANVOLDTL,REMARK,U9_SALE_ORDER_NO,U9_SALE_ORDER_DTL_NO,PAR_PRD_NAME,ROWSTATE,STATE";
        
        try {
			ExcelUtil.doExport(response, list, "货品发运", tmpContent, tmpContentCn,colType,color);
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
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_ROW_CLOSE", QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
		pvgMap.put("PVG_ORDER_CLOSE", QXUtil.checkPvg(userBean, PVG_ORDER_CLOSE));
		return pvgMap;
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
        String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "createShipPlan";
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_ROW_CLOSE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String strJsonData = "";
    	try {
    		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
    		String IS_ALL_CLOSE = ParamUtil.get(request, "IS_ALL_CLOSE");
    		Map<String,String> bill = pdtdeliverService.load(DELIVER_ORDER_ID);
    		String DELIVER_ORDER_NO = bill.get("DELIVER_ORDER_NO");
    		String stats = (String)bill.get("STATE");
    		if(checkIsClose(stats)){
    			String DELIVER_ORDER_DTL_IDS = ParamUtil.get(request, "DELIVER_ORDER_DTL_IDS");
    			String deliverStats = "已发货";
    			if("true".equals(IS_ALL_CLOSE)){
    				DELIVER_ORDER_DTL_IDS = getDeliverDtlId(DELIVER_ORDER_ID);
    				deliverStats = "已提交生产";
    			}
                if (StringUtils.isNotEmpty(DELIVER_ORDER_DTL_IDS)) {
            		strJsonData = LogicUtil.strCreateShipPlanByClose(DELIVER_ORDER_ID,DELIVER_ORDER_DTL_IDS, InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE); 
                   	LogicUtil.actLog("出货单管理", "成功调入关闭出货单接口", "UA系统", "成功",strJsonData,AppCode,UId,OPRCODE);
                	pdtdeliverService.txCloseChilds(deliverStats,DELIVER_ORDER_NO,DELIVER_ORDER_ID,DELIVER_ORDER_DTL_IDS, userBean,strJsonData,IS_ALL_CLOSE);
                	jsonResult = jsonResult(true,"操作成功");
                }
    		}else{
    			jsonResult = jsonResult(false,"此主表状态不允许关闭发运单");
    		}
        }catch(ServiceException ex) {
        	LogicUtil.actLog("出货单管理", "关闭出货单失败", "UA系统", "失败",ex.getMessage()+strJsonData,AppCode,UId,OPRCODE);
            jsonResult = jsonResult(false, ex.getMessage());
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    private String getDeliverDtlId(String deliverId){
    	StringBuffer sqlBuff = new StringBuffer();
    	List deliverDtlIdList = pdtdeliverService.getDeliverDtlId(deliverId);
    	for(int i=0;deliverDtlIdList!=null && i<deliverDtlIdList.size();i++){
    		HashMap deliverDtlIdMap = (HashMap)deliverDtlIdList.get(i);
    		String DELIVER_ORDER_DTL_ID = (String)deliverDtlIdMap.get("DELIVER_ORDER_DTL_ID");
    		sqlBuff.append("'").append(DELIVER_ORDER_DTL_ID).append("'").append(",");
    	}
    	String deliverDtlIds = sqlBuff.toString();
    	if(deliverDtlIds.endsWith(",")){
    		deliverDtlIds = deliverDtlIds.substring(0, deliverDtlIds.length()-1);
    	}
    	return deliverDtlIds;
    }
    
    
	  /**
     * 整单冻结
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void deliverOrderFreeze(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	String jsonResult = jsonResult();
    	String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
    	try {
    		boolean isFreezeFlg = pdtdeliverService.checkIsAllFreezeFlg(DELIVER_ORDER_ID,"1");
    		if(!isFreezeFlg){
    			throw new ServiceException("已经整单冻结,不允许重复冻结!");
    		}
    		boolean flg = LogicUtil.txDeliverOrderFreeze(DELIVER_ORDER_ID);
    		if(!flg){
    			throw new ServiceException("整单冻结出错!");
    		}
    		jsonResult = jsonResult(true,"冻结成功!");
    	}catch(Exception ex){
    		jsonResult = jsonResult(false,ex.getMessage());
    	}
        PrintWriter writer = getWriter(response);
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
	  /**
     * 整单解冻
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void deliverOrderunFreeze(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	String jsonResult = jsonResult();
    	String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
    	try {
       		boolean isFreezeFlg = pdtdeliverService.checkIsAllFreezeFlg(DELIVER_ORDER_ID,"0");
    		if(!isFreezeFlg){
    			throw new ServiceException("订单未冻结,不允许解冻!");
    		}
    		boolean flg = LogicUtil.txDeliverOrderunFreeze(DELIVER_ORDER_ID);
    		if(!flg){
    			throw new ServiceException("整单解冻出错!");
    		}
    		jsonResult = jsonResult(true,"解冻成功!");
    	}catch(Exception ex){
    		jsonResult = jsonResult(false,ex.getMessage());
    	}
        PrintWriter writer = getWriter(response);
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    private boolean checkIsClose(String stats){
    	String[] closeStats = {"已提交库房","部分发货"};
  		for (int i = 0; i < closeStats.length; i++) {
			String colseStats = closeStats[i];
			if(stats.equals(colseStats)){
				return true;
			}
		}
  		return false;
    }

	/**
	 *  出货计划明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward shipmentChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_IDS = ParamUtil.get(request, "DELIVER_ORDER_IDS");
		if (!StringUtil.isEmpty(DELIVER_ORDER_IDS)) {
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_ORDER_IDS", DELIVER_ORDER_IDS);
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			// 字段排序
			ParamUtil.setOrderField(request, params, "u.SALE_ORDER_NO", "desc");
			List<TurnoverplanChildModel> result = pdtdeliverService.childQueryByParentIDS(params);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("shipmentChildlist");
	}
    
    
    /**
     * 生成出货计划号
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void createPlanNo(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DELIVER_ORDER_IDS = ParamUtil.get(request, "DELIVER_ORDER_IDS");
    	try {
            String JOIN_DELIVER_ORDER_NO = pdtdeliverService.createPlanNo(DELIVER_ORDER_IDS);
    		jsonResult = new Gson().toJson(new Result(true, JOIN_DELIVER_ORDER_NO, ""));
    		
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
     * 判断所选订单的发货方是否是同一个区域服务中心
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void justOnlyAreaSer(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
    	try {
    		String DELIVER_ORDER_IDS = ParamUtil.get(request, "DELIVER_ORDER_IDS");
    		boolean b = pdtdeliverService.justOnlyAreaSer(DELIVER_ORDER_IDS);
    		if(!b){
    			jsonResult = jsonResult(true, "false");
    		}
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    

	public PdtdeliverService getPdtdeliverService() {
		return pdtdeliverService;
	}


	public void setPdtdeliverService(PdtdeliverService pdtdeliverService) {
		this.pdtdeliverService = pdtdeliverService;
	}
	
	
	 
	
	
	

}
