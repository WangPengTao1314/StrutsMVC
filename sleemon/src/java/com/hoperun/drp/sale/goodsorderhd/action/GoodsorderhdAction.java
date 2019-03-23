/**
 * prjName:喜临门营销平台
 * ucName:订货订单维护
 * fileName:GoodsorderhdAction
*/
package com.hoperun.drp.sale.goodsorderhd.action;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModel;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.goodsorderhd.service.GoodsorderhdService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func 分销-订货订单维护
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-15 10:35:10
 */
public class GoodsorderhdAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(GoodsorderhdAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0020401";
    private static String PVG_EDIT="FX0020402";
    private static String PVG_DELETE="FX0020403";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0020404";
    private static String PVG_TRACE="FX0020405";
    //审核模块
    private static String PVG_BWS_AUDIT="FX0020501";
    private static String PVG_AUDIT_AUDIT="FX0020502";
    private static String PVG_TRACE_AUDIT="FX0020503";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_GOODS_ORDER";
    private static String AUD_TAB_KEY="GOODS_ORDER_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_GOODS_ORDER_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.impl.GoodsOrderFlowInterfaceImpl";
    /**审批 end**/
    /** 业务service*/
	private GoodsorderhdService goodsorderhdService;
    /**
	 * Sets the Goodsorderhd service.
	 * 
	 * @param GoodsorderhdService the new Goodsorderhd service
	 */
	public void setGoodsorderhdService(GoodsorderhdService goodsorderhdService) {
		this.goodsorderhdService = goodsorderhdService;
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
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		String GOODS_ORDER_ID = ParamUtil.get(request,"GOODS_ORDER_ID");
		String doCommitSave = ParamUtil.get(request,"doCommitSave");
		String flag = ParamUtil.get(request,"flag");
		request.setAttribute("paramUrl",paramUrl );
		request.setAttribute("GOODS_ORDER_ID",GOODS_ORDER_ID);
		request.setAttribute("doCommitSave",doCommitSave);
		request.setAttribute("flag",flag);
		String AREA_SER_ID =  userBean.getAREA_SER_ID(); //区域服务中心ID
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			//走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
		}
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
		String GOODS_ORDER_ID = ParamUtil.get(request,"selRowId");
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    
	    String ADVC_ORDER_NO=ParamUtil.get(request,"ADVC_ORDER_NO");
	    params.put("ADVC_ORDER_NOQuery", StringUtil.creCon("c.ADVC_ORDER_NO", ADVC_ORDER_NO, ","));
	    String PRD_NOS=ParamUtil.get(request,"PRD_NO");
	    params.put("PRD_NOQuery", StringUtil.creCon("PRD_NO", PRD_NOS, ","));
	    String PRD_NAMES=ParamUtil.get(request,"PRD_NAME");
	    params.put("PRD_NAMEQuery", StringUtil.creCon("PRD_NAME", PRD_NAMES, ","));
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String STATE = ParamUtil.get(request,"STATE");
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String flag = ParamUtil.get(request,"flag");
		
		if("reback".equals(flag)){
			params.put("reback","u.STATE in ('总部退回','区域服务中心退回') ");
		}
		
		if("isSend".equals(flag)){
			params.put("isSend","isSend");
		}
		
		if(StringUtil.isEmpty(search) && !StringUtil.isEmpty(GOODS_ORDER_ID) && -1 !=GOODS_ORDER_ID.indexOf("_")){
			params.put("GOODS_ORDER_ID",GOODS_ORDER_ID);//订货订单生命跟踪 跳转
		} 
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,null,userBean));
		sb.append(" and u.LEDGER_ID ='").append(userBean.getLoginZTXXID()).append("'");
		if("sh".equals(module)){
			if(StringUtil.isEmpty(search)){
				sb.append(" and u.STATE in('");
				sb.append(BusinessConsts.COMMIT);
				sb.append("')");
			}else if(StringUtil.isEmpty(STATE)){
				sb.append(" and u.STATE in('提交','否决','已处理','未处理','总部退回')");
			}else{
				params.put("STATE", STATE);
			}
		}
		if("wh".equals(module)){
			if(StringUtil.isEmpty(search)){
				sb.append(" and u.STATE in('");
				sb.append(BusinessConsts.STATE_MAKE);//制作
				sb.append("','");
				sb.append(BusinessConsts.ORDER_BACK);//总部退回
				sb.append("','");
				sb.append("区域服务中心退回");// 
//				sb.append("','");
//				sb.append(BusinessConsts.REVOKE);//撤销
				sb.append("')");
			}else if(!StringUtil.isEmpty(STATE)){
			   params.put("STATE", STATE);
				 
			}
		}
		 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.BILL_TYPE,u.CRE_TIME desc", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = goodsorderhdService.pageQuery(params, pageNo);
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		
		Map<String,Object> entry = new HashMap<String,Object>();
		//用户所在渠道信息
		Map<String,Object> chann = goodsorderhdService.loadChann(userBean.getCHANN_ID());
		entry.put("INI_CREDIT", chann.get("INI_CREDIT"));//初始信用
		entry.put("REBATE_TOTAL", chann.get("REBATE_TOTAL"));//返利总额
		entry.put("REBATE_CURRT", chann.get("REBATE_CURRT"));//当前返利
//		entry.put("CREDIT_TOTAL", chann.get("CREDIT_TOTAL"));//信用总额
		entry.put("TEMP_CREDIT_REQ", chann.get("TEMP_CREDIT_REQ"));//临时信用
		entry.put("CREDIT_CURRT", chann.get("CREDIT_CURRT"));//当前信用
		entry.put("FREEZE_CREDIT", chann.get("FREEZE_CREDIT"));//冻结信用
		entry.put("PAYMENT_CREDIT", chann.get("PAYMENT_CREDIT"));//付款凭证信用
		entry.put("CHANN_NO", chann.get("CHANN_NO")); 
		entry.put("CHANN_NAME", chann.get("CHANN_NAME")); 
		
		String AREA_SER_ID =  userBean.getAREA_SER_ID(); //区域服务中心ID
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			//走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
			request.setAttribute("AREA_SER_ID", AREA_SER_ID);
		}
		
		//获取返利折扣
		Map<String,Object> result = LogicUtil.getChannRebateDsct(userBean.getCHANN_ID());
		if(null != result){
			request.setAttribute("REBATEDSCT", result.get("DECT_RATE"));//返利折扣
			request.setAttribute("REBATE_TOTAL", result.get("REBATE_TOTAL"));//返利总金额
			request.setAttribute("REBATE_CURRT", result.get("REBATE_CURRT"));//当前返利
			request.setAttribute("REBATE_FREEZE", result.get("REBATE_FREEZE"));//冻结的返利
		}
		//查询可用信用  add by zzb 2014-09-23
//		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
//			String userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
//			request.setAttribute("userCredit", userCredit);
//		}
		request.setAttribute("rst", entry);
		request.setAttribute("flag", flag);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("BASE_CHANN_ID", userBean.getBASE_CHANN_ID());
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID =ParamUtil.get(request, "GOODS_ORDER_ID");
        Map<String,String> param=goodsorderhdService.queryTotal(GOODS_ORDER_ID);
        if(!StringUtil.isEmpty(GOODS_ORDER_ID))
        {
        	 List <GoodsorderhdModelChld> result = goodsorderhdService.childQuery(GOODS_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("totalInfo", param);
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
		String GOODS_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		//用户所在渠道信息
		Map<String,Object> chann = goodsorderhdService.loadChann(userBean.getCHANN_ID());
		if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
			entry = goodsorderhdService.load(GOODS_ORDER_ID);
		}else{
			String AREA_SER_ID = userBean.getAREA_SER_ID();
			//走区域服务中心流程
			if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
				entry.put("SEND_CHANN_ID", AREA_SER_ID);
				entry.put("SEND_CHANN_NO", userBean.getAREA_SER_NO());
				entry.put("SEND_CHANN_NAME", userBean.getAREA_SER_NAME());
			}else{
				entry.put("SEND_CHANN_ID", userBean.getBASE_CHANN_ID());
				entry.put("SEND_CHANN_NO", userBean.getBASE_CHANN_NO());
				entry.put("SEND_CHANN_NAME", userBean.getBASE_CHANN_NAME());
			}
			
			entry.put("ORDER_CHANN_ID", userBean.getCHANN_ID());
			entry.put("ORDER_CHANN_NO", userBean.getCHANN_NO());
			entry.put("ORDER_CHANN_NAME", userBean.getCHANN_NAME());
			
			entry.put("RECV_CHANN_ID", userBean.getCHANN_ID());
			entry.put("RECV_CHANN_NO", userBean.getCHANN_NO());
			entry.put("RECV_CHANN_NAME", userBean.getCHANN_NAME());
			entry.put("PERSON_CON", chann.get("PERSON_CON"));
			entry.put("TEL", chann.get("TEL"));
//			entry.put("RECV_ADDR", chann.get("ADDRESS"));
			entry.put("AREA_ID", chann.get("AREA_ID"));
			entry.put("AREA_NO", chann.get("AREA_NO"));
			entry.put("AREA_NAME", chann.get("AREA_NAME"));
		}
		entry.put("INI_CREDIT", chann.get("INI_CREDIT"));//初始信用
		entry.put("TEMP_CREDIT_REQ", chann.get("TEMP_CREDIT_REQ"));//临时信用
		entry.put("REBATE_TOTAL", chann.get("REBATE_TOTAL"));//返利总额
		entry.put("REBATE_CURRT", chann.get("REBATE_CURRT"));//当前返利
		entry.put("REBATE_FREEZE", chann.get("REBATE_FREEZE"));//冻结的返利
		entry.put("REBATEDSCT", chann.get("DECT_RATE"));//返利折扣
		
		entry.put("CREDIT_TOTAL", chann.get("CREDIT_TOTAL"));//信用总额
		entry.put("TEMP_CREDIT", chann.get("TEMP_CREDIT"));//临时信用
		entry.put("CREDIT_CURRT", chann.get("CREDIT_CURRT"));//当前信用
		entry.put("FREEZE_CREDIT", chann.get("FREEZE_CREDIT"));//冻结信用
		entry.put("PAYMENT_CREDIT", chann.get("PAYMENT_CREDIT"));//付款凭证信用
		String AREA_SER_ID = userBean.getAREA_SER_ID();
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			//走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
			request.setAttribute("AREA_SER_ID", AREA_SER_ID);
		}
		
		//获取返利折扣
		Map<String,Object> result = LogicUtil.getChannRebateDsct(userBean.getCHANN_ID());
		if(null != result){
			request.setAttribute("REBATEDSCT", result.get("DECT_RATE"));//返利折扣
		}
		
		//查询可用信用  add by zzb 2014-09-23
		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
			String userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
			request.setAttribute("userCredit", userCredit);
		}
		request.setAttribute("rst", entry);
		request.setAttribute("BASE_CHANN_ID", userBean.getBASE_CHANN_ID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
        if(!StringUtil.isEmpty(GOODS_ORDER_ID))
        {
        	 List <GoodsorderhdModelChld> result = goodsorderhdService.childQuery(GOODS_ORDER_ID);
             request.setAttribute("rst", result);
        }
        //获取返利货品分类
		String[] REBATE_PRD_NUMBER=REBATE_PRD_NUMBERS.split(",");
		StringBuffer PRD_NOS=new StringBuffer();
		for (int i = 0; i < REBATE_PRD_NUMBER.length; i++) {
			PRD_NOS.append("'").append(REBATE_PRD_NUMBER[i]).append("',");
		}
		String PRDNOS=PRD_NOS.toString();
		if(PRDNOS.length()>0){
			PRDNOS = PRDNOS.substring(0,PRDNOS.length() - 1);
		}
        //-- 0156143--Start--刘曰刚--2014-06-16//
		//获取渠道折扣率
        String DECT_RATE=LogicUtil.getChannDsct(userBean.getCHANN_ID());
        request.setAttribute("DECT_RATE", DECT_RATE);
        //-- 0156143--End--刘曰刚--2014-06-16//
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("PRDNOS", PRDNOS);
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
        String GOODS_ORDER_DTL_IDs = request.getParameter("GOODS_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(GOODS_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("GOODS_ORDER_DTL_IDS",GOODS_ORDER_DTL_IDs);
          List <GoodsorderhdModelChld> list = goodsorderhdService.loadChilds(params);
          request.setAttribute("rst", list);
        }
      //获取返利货品分类
		String[] REBATE_PRD_NUMBER=REBATE_PRD_NUMBERS.split(",");
		StringBuffer PRD_NOS=new StringBuffer();
		for (int i = 0; i < REBATE_PRD_NUMBER.length; i++) {
			PRD_NOS.append("'").append(REBATE_PRD_NUMBER[i]).append("',");
		}
		String PRDNOS=PRD_NOS.toString();
		if(PRDNOS.length()>0){
			PRDNOS = PRDNOS.substring(0,PRDNOS.length() - 1);
		}
      //-- 0156143--Start--刘曰刚--2014-06-16//
		//获取渠道折扣率
        String DECT_RATE=LogicUtil.getChannDsct(userBean.getCHANN_ID());
        request.setAttribute("DECT_RATE", DECT_RATE);
        //-- 0156143--End--刘曰刚--2014-06-16//

        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("PRDNOS", PRDNOS);
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
            String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            String REBATEDSCT = ParamUtil.get(request, "REBATEDSCT");//返利折扣
            
            GoodsorderhdModel model = new Gson().fromJson(parentJsonData, new TypeToken <GoodsorderhdModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <GoodsorderhdModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <GoodsorderhdModelChld>>() {
                }.getType());
            }
            GOODS_ORDER_ID =  goodsorderhdService.txEdit(GOODS_ORDER_ID, model, chldModelList, userBean,REBATEDSCT);
//            jsonResult = jsonResult(true, GOODS_ORDER_ID);
            Map<String,String> p = new HashMap<String,String>();
            p.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
            jsonResult = new Gson().toJson(new Result(true, p, "保存成功"));
            
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
            String GOODS_ORDER_ID = request.getParameter("GOODS_ORDER_ID");
            String ORDER_RECV_DATE = request.getParameter("ORDER_RECV_DATE");//交期
            String jsonDate = request.getParameter("childJsonData");
            
            String IS_USE_REBATE = ParamUtil.get(request, "IS_USE_REBATE");//是否使用返利
            String REBATEDSCT = ParamUtil.get(request, "REBATEDSCT");//返利折扣
            Map<String,String> params = new HashMap<String,String>();
            params.put("IS_USE_REBATE", IS_USE_REBATE);
            params.put("REBATEDSCT", REBATEDSCT);
            
            if (!StringUtil.isEmpty(jsonDate)) {
                List <GoodsorderhdModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <GoodsorderhdModelChld>>() {
                }.getType());
                 
                goodsorderhdService.txChildEdit(GOODS_ORDER_ID, modelList,ORDER_RECV_DATE,params,"list");
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
     * * 查询 折扣率
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void getRate(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	 String PRD_ID = ParamUtil.get(request, "PRD_ID");
        	 String AREA_ID = ParamUtil.get(request, "AREA_ID");
        	 Map<String,String> reault = goodsorderhdService.getRateByAreaIdPId(AREA_ID,PRD_ID);
//             if(StringUtil.isEmpty(rate)){
//            	 Map<String,Object> chann = goodsorderhdService.loadChann(userBean.getCHANN_ID());
//             	 jsonResult = new Gson().toJson(new Result(true, chann, ""));
//             }else{
//            	 jsonResult = jsonResult(true, rate);
//             }
             jsonResult = new Gson().toJson(new Result(true, reault, ""));
        } catch (Exception e) {
        	jsonResult = jsonResult(false, "");
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
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			goodsorderhdService.txDelete(params);
			goodsorderhdService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String GOODS_ORDER_DTL_IDs = request.getParameter("GOODS_ORDER_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            goodsorderhdService.txBatch4DeleteChild(GOODS_ORDER_DTL_IDs);
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
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
			Map<String,Object> entry = goodsorderhdService.load(GOODS_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		String AREA_SER_ID = userBean.getAREA_SER_ID();
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			//走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
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
            String GOODS_ORDER_ID = ParamUtil.get(request,"GOODS_ORDER_ID");
            String GOODS_ORDER_NO = ParamUtil.get(request,"GOODS_ORDER_NO");
            String BILL_TYPE = ParamUtil.get(request,"BILL_TYPE");
            if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
//            	Map<String,Object> model = goodsorderhdService.txLoadForUpdate(GOODS_ORDER_ID);
//            	if("未处理".equals(StringUtil.nullToSring(model.get("STATE")))){
//            		throw new ServiceException("改单据已经提交到总部！");
//            	}
            	List <GoodsorderhdModelChld> list = goodsorderhdService.childQuery(GOODS_ORDER_ID);
                if (list.size() == 0) {
                    errorId = "0";
                    throw new Exception("没有明细，不能提交!");
                }else{
//                	try{
//                		String AREA_SER_ID =  userBean.getAREA_SER_ID();
//                		if(StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
//                			//检查信用 
//                    		goodsorderhdService.txCheckCredit(userBean.getCHANN_ID(),GOODS_ORDER_ID,BILL_TYPE);
//                		}
//                		
//                	}catch(Exception e){
//                		errorId = "1";
//                		throw e;
//                	}
                	boolean flag=LogicUtil.checkSpclOrder("DRP_GOODS_ORDER_DTL", "GOODS_ORDER_ID", GOODS_ORDER_ID);
        			if(!flag){
        				throw new ServiceException("对不起，您的单据明细存在非标特殊工艺，不能提交 !");
        			}
                	goodsorderhdService.txCommit(GOODS_ORDER_ID,BILL_TYPE,GOODS_ORDER_NO, userBean);
                	jsonResult = jsonResult(true, "提交成功");
                }
            }
            
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if ("1".equals(errorId)) {
                jsonResult = jsonResult(false, e.getMessage());
            }else {
                jsonResult = jsonResult(false, "提交失败");
            }
            logger.info(e);
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 撤销
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void revoke(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request,HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
        	 String GOODS_ORDER_ID = ParamUtil.get(request,"GOODS_ORDER_ID");
             String GOODS_ORDER_NO = ParamUtil.get(request,"GOODS_ORDER_NO");
             
        	goodsorderhdService.revoke(GOODS_ORDER_ID, GOODS_ORDER_NO, userBean);
        	jsonResult = jsonResult(true, "撤销成功");
        }catch(Exception e){
        	 logger.info(e);
        	jsonResult = jsonResult(false, "撤销失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
	 /**
     * * 生命周期
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward traceList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID =ParamUtil.get(request, "GOODS_ORDER_ID");
        if(!StringUtil.isEmpty(GOODS_ORDER_ID))
        {
        	 List <GoodsorderhdModelTrace> result = goodsorderhdService.traceQuery(GOODS_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("traceList");
    }
    
    /**
     * 查看预订单明细
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toLookAdvc(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID =ParamUtil.get(request, "GOODS_ORDER_ID");
        String FROM_BILL_DTL_ID =ParamUtil.get(request, "FROM_BILL_DTL_ID");
        String GOODS_ORDER_DTL_ID =ParamUtil.get(request, "GOODS_ORDER_DTL_ID");
        
        if(!StringUtil.isEmpty(GOODS_ORDER_DTL_ID)){
        	Map<String,String>params = new HashMap<String,String>();
        	params.put("FROM_BILL_DTL_ID", FROM_BILL_DTL_ID);
        	params.put("GOODS_ORDER_DTL_ID", GOODS_ORDER_DTL_ID);
        	List <Map<String,Object>> result = goodsorderhdService.toQuertAvdcDtl(params);
            request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("lookAdvcDtl");
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
	 
	 /**
		 * * 主表 新增/修改数据
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 */
		public void getUserCredit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}	
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				//用户所在渠道信息
				Map<String,Object> chann = goodsorderhdService.loadChann(userBean.getCHANN_ID());
				String userCredit="0";
				if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
					userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
				}
				BigDecimal userCreditBig = new BigDecimal(userCredit);
				BigDecimal INI_CREDIT = new BigDecimal(String.valueOf(chann.get("INI_CREDIT")));
				BigDecimal TEMP_CREDIT_REQ = new BigDecimal(String.valueOf(chann.get("TEMP_CREDIT_REQ")));
				BigDecimal FREEZE_CREDIT = new BigDecimal(String.valueOf(chann.get("FREEZE_CREDIT")));
				String canUserCredit=String.valueOf(INI_CREDIT.add(userCreditBig).add(TEMP_CREDIT_REQ).subtract(FREEZE_CREDIT));
				Map<String,String> map=new HashMap<String, String>();
				map.put("canUserCredit", canUserCredit);
	            jsonResult = new Gson().toJson(new Result(true, map, ""));
	            
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "查询信用失败");
	            e.printStackTrace();
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
		}
}