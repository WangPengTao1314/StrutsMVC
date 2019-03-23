package com.hoperun.drp.store.dststoreout.action;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.hoperun.drp.store.dststoreout.model.DststoreoutModel;
import com.hoperun.drp.store.dststoreout.model.DststoreoutModelChld;
import com.hoperun.drp.store.dststoreout.service.DststoreoutService;
import com.hoperun.sys.model.UserBean;

/**
 * 
*   预订单出库维护
* 项目名称：sleemon   
* 类名称：DststoreoutAction.java
* 类描述：   
* 创建人：liu_yg   
* 创建时间：2016-1-11 下午03:05:49   
* @version
 */
public class DststoreoutAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(DststoreoutAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0032401";
    private static String PVG_EDIT="FX0032402";
    private static String PVG_DELETE="FX0032403";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="FX0032404";
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
	private DststoreoutService dststoreoutService;
    /**
	 * Sets the Dststoreout service.
	 * 
	 * @param DststoreoutService the new Dststoreout service
	 */
	public void setDststoreoutService(DststoreoutService dststoreoutService) {
		this.dststoreoutService = dststoreoutService;
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
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	    params.put("LEDGER_ID", userBean.getCHANN_ID());
       //只查询0的记录。1为删除。0为正常
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if(StringUtil.isEmpty(search)){
			params.put("querySTATE", BusinessConsts.UNDONE);
		}
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.STOREOUT_ID", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = dststoreoutService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
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
        String STOREOUT_ID =ParamUtil.get(request, "STOREOUT_ID");
        if(!StringUtil.isEmpty(STOREOUT_ID))
        {
        	 List <DststoreoutModelChld> result = dststoreoutService.childQuery(STOREOUT_ID);
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
		String STOREOUT_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry=new HashMap<String, String>();
		if(!StringUtil.isEmpty(STOREOUT_ID)){
			entry = dststoreoutService.load(STOREOUT_ID);
		}else{
			entry.put("SEND_CHANN_ID", userBean.getCHANN_ID());
			entry.put("SEND_CHANN_NO", userBean.getCHANN_NO());
			entry.put("SEND_CHANN_NAME", userBean.getCHANN_NAME());
		}
		request.setAttribute("BMXXID", userBean.getBMXXID());
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
        String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
        if(!StringUtil.isEmpty(STOREOUT_ID))
        {
        	StringBuffer QUERYID=new StringBuffer();
        	QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_DST_STOREOUT_DTL a where a.STOREOUT_ID='").
			append(STOREOUT_ID).
			append("' and DEL_FLAG=0 ");
        	String dtlId= dststoreoutService.qeuryId(QUERYID.toString());
        	 List <DststoreoutModelChld> result = dststoreoutService.childQuery(STOREOUT_ID);
        	 request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
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
    	String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
        String STOREOUT_DTL_IDs = request.getParameter("STOREOUT_DTL_IDS");
        StringBuffer QUERYID=new StringBuffer();
        if(!StringUtil.isEmpty(STOREOUT_ID)){
        	QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_DST_STOREOUT_DTL a where a.STOREOUT_ID='").
			append(STOREOUT_ID).append("' and a.DEL_FLAG=0 ");
        }
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STOREOUT_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STOREOUT_DTL_IDS",STOREOUT_DTL_IDs);
          List <DststoreoutModelChld> list = dststoreoutService.loadChilds(params);
          request.setAttribute("rst", list);
          QUERYID.append(" and STOREOUT_DTL_ID not in (").append(STOREOUT_DTL_IDs).append(")");
        }
        String dtlId= dststoreoutService.qeuryId(QUERYID.toString());
    	request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
    	request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
            String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            DststoreoutModel model = new Gson().fromJson(parentJsonData, new TypeToken <DststoreoutModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <DststoreoutModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <DststoreoutModelChld>>() {
                }.getType());
            }
            dststoreoutService.txEdit(STOREOUT_ID, model, chldModelList, userBean);
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
            String STOREOUT_ID = request.getParameter("STOREOUT_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <DststoreoutModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <DststoreoutModelChld>>() {
                }.getType());
                dststoreoutService.txChildEdit(STOREOUT_ID, modelList,"list");
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
			String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STOREOUT_ID", STOREOUT_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			dststoreoutService.txDelete(params);
			dststoreoutService.clearCaches(params);
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
            String STOREOUT_DTL_IDs = request.getParameter("STOREOUT_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            dststoreoutService.txBatch4DeleteChild(STOREOUT_DTL_IDs);
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
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if(!StringUtil.isEmpty(STOREOUT_ID)){
			Map<String,String> entry = dststoreoutService.load(STOREOUT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}

	
	/**
	 * * 提交时，校验是否有明细.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void toCommit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_FINISH_CANCLE)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String errorId = "";
		try {
			String STOREOUT_ID = request.getParameter("STOREOUT_ID");
			Map<String,String> model = dststoreoutService.load(STOREOUT_ID);
			if("已处理".equals(model.get("STATE"))){
				throw new ServiceException("该单据已被["+model.get("DEAL_PSON_NAME")+"]处理过了!");
			}
			if("1".equals(String.valueOf(model.get("DEL_FLAG")))){
				throw new ServiceException("该单据已被删除，不能出库，请刷新页面!");
			}
			List chldlist = dststoreoutService.childQuery(STOREOUT_ID);
			if (chldlist.size() == 0) {
				errorId = "0";
				throw new Exception("没有明细");
			}
//			出库数量不能大于预订单订货数量 (出库数量<=预订单订货数量-已发货数量)
			String message = dststoreoutService.checkRevcNum(STOREOUT_ID);
			if(!StringUtil.isEmpty(message)){
				throw new ServiceException(message);
			}
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				errorId="2";
				throw new Exception("处于月结阶段,不能做出库!");
			}
			boolean storeFreezeFlag=LogicUtil.checkFreezeStore(model.get("STOREOUT_STORE_ID"));
			if(!storeFreezeFlag){
				errorId="2";
				throw new Exception("当前出库库房已被冻结,不能做出库!");
			}
			dststoreoutService.txCommit(model, userBean,STOREOUT_ID,chldlist);
		}catch (ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		}catch (Exception e) {
			if ("0".equals(errorId)) {
				jsonResult = jsonResult(false, "没有明细，不能提交!");
			}else if("1".equals(errorId)){
				jsonResult = jsonResult(false, "还有未扫码的货品,请进行扫码!");
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
	 * 获取所有可发货预订单明细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toQueryAdvcDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
    	String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
          List <DststoreoutModelChld> list = dststoreoutService.getAdvcDtlById(ADVC_ORDER_ID);
          request.setAttribute("rst", list);
        }
    	request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        return mapping.findForward("childedit");
    }
	
	/**
	 * 打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	 public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	UserBean userBean = ParamUtil.getUserBean(request);
		 	 String STOREOUT_ID = request.getParameter("STOREOUT_ID");
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_DST_STOREOUT");
		 	printMap.put("BUSS_ID", STOREOUT_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
			 String TEL=dststoreoutService.getChannTel(userBean.getCHANN_ID());
			 double gift_amount=dststoreoutService.getDtlGiftAmount(STOREOUT_ID);
			 StringBuffer paramSql=new StringBuffer();
			 paramSql.append("advcStoreOutPrint.raq&STOREOUT_ID=").append(STOREOUT_ID).append("&TEL=").append(TEL);
			 Map<String,String> map=dststoreoutService.getSellInfo(STOREOUT_ID);
			 Object obj=map.get("PAYABLE_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 paramSql.append("&arg1=").append(obj.toString())
			 	.append("&arg2=").append(userBean.getXM());
			 obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 paramSql.append("&arg3=").append(obj.toString()).append("&arg4=").append(gift_amount);
			 System.out.println(paramSql.toString());
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 return mapping.findForward("flashReportPrint");
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