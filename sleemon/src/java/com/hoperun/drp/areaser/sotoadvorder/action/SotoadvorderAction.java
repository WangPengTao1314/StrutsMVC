package com.hoperun.drp.areaser.sotoadvorder.action;

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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.areaser.sotoadvorder.model.SotoadvorderGoodModelChld;
import com.hoperun.drp.areaser.sotoadvorder.service.SotoadvorderService;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public class SotoadvorderAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0070301";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="FX0070303";
    //转订货
    private static String PVG_SHIFT="FX0070302";
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
    private static String AUD_BUSS_STATE="";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
	
	private SotoadvorderService sotoAdvorderService;
	
	/**
	 * @return the sotoAdvorderService
	 */
	public SotoadvorderService getSotoAdvorderService() {
		return sotoAdvorderService;
	}

	/**
	 * @param sotoAdvorderService the sotoAdvorderService to set
	 */
	public void setSotoAdvorderService(SotoadvorderService sotoAdvorderService) {
		this.sotoAdvorderService = sotoAdvorderService;
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
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE", "ASC");
		params.put("STATE", BusinessConsts.PASS);
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		params.put("BILL_TYPE", "订货销售");
		ParamUtil.setOrderField(request, params, "u.ORDER_DATE", "DESC");
		StringBuffer sql=new StringBuffer();
		sql.append(" SALE_ORDER_ID in(select SALE_ORDER_ID from DRP_SALE_ORDER_DTL where NVL(ORDER_NUM,0)>NVL(CHANGE_NUM,0) and DEL_FLAG=0) ")
		.append(" and u.ORDER_CHANN_ID in (select CHANN_ID from BASE_CHANN where CHANN_ID_P ='").append(userBean.getCHANN_ID()).append("' and DEL_FLAG=0 ) ");
		params.put("SQL", sql.toString());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = sotoAdvorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	/**
	 * * to 详细信息
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
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if (!StringUtil.isEmpty(SALE_ORDER_ID)) {
			Map<String, String> entry = sotoAdvorderService.load(SALE_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
        String SALE_ORDER_ID =ParamUtil.get(request, "SALE_ORDER_ID");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SaleorderModelChld> result = sotoAdvorderService.childQuery(SALE_ORDER_ID);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
             request.setAttribute("rst", result);
            
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
    }
    
    
    /**
     * 点击转订货订单按钮后跳转页面显示list
     */
        public ActionForward toListShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        	UserBean userBean =  ParamUtil.getUserBean(request);
            if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
        	{
        		throw new ServiceException("对不起，您没有权限 !");
        	}
    		Map<String,String> params = new HashMap<String,String>();
    		String RECV_CHANN_ID=request.getParameter("RECV_CHANN_ID");
    		if(StringUtil.isEmpty(RECV_CHANN_ID)){
    			RECV_CHANN_ID =userBean.getLoginZTXXID();
    		}
    		Map<String,String> channINfo=sotoAdvorderService.getChannInfoLv(RECV_CHANN_ID);
    		StringBuffer sql=new StringBuffer();
    		if("区域服务中心".equals(channINfo.get("CHANN_TYPE"))){
    			sql.append(" RECV_CHANN_ID in (")
    				.append(" select CHANN_ID from BASE_CHANN ")
    				.append(" where AREA_SER_ID='").append(RECV_CHANN_ID).append("' ")
    				.append(" and STATE='").append(BusinessConsts.JC_STATE_ENABLE).append("' ")
    				.append(" and DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
    				.append(" or CHANN_ID='").append(RECV_CHANN_ID).append("' )");
    		}else{
    			sql.append(" RECV_CHANN_ID='").append(RECV_CHANN_ID).append("' ");
    		}
    		params.put("CHANNSQL", sql.toString());
    		params.put("BILL_TYPE", "订货销售");
    		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    		params.put("STATE", BusinessConsts.PASS);
    		ParamUtil.setOrderField(request, params, "u.ORDER_DATE", "DESC");
    		params.put("SQL", " u.SALE_ORDER_ID in(select SALE_ORDER_ID from DRP_SALE_ORDER_DTL where NVL(ORDER_NUM,0)>NVL(CHANGE_NUM,0) and DEL_FLAG=0)");
            params.put("QXJBCON", ParamUtil.getPvgCon("","",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    		//字段排序
    		ParamUtil.setOrderField(request, params, "ADVC_SEND_DATE", "asc");
    		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    		ParamUtil.putStr2Map(request, "pageSize", params);
    		IListPage page = sotoAdvorderService.query(params, pageNo);
    		Map<String,String> map=new HashMap<String, String>();
    		map.put("ORDER_CHANN_ID", userBean.getCHANN_ID());
    		map.put("ORDER_CHANN_NO", userBean.getCHANN_NO());
    		map.put("ORDER_CHANN_NAME", userBean.getCHANN_NAME());
    		request.setAttribute("channINfo", channINfo);
            request.setAttribute("params", map);
    		request.setAttribute("pvg",setPvg(userBean));
    		request.setAttribute("page", page);
    		return mapping.findForward("shiftlist");
        }
        /**
         * 获取选中行id 查询货品明细
         */
         @SuppressWarnings("unchecked")
     	public ActionForward toChild(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
         	UserBean userBean = ParamUtil.getUserBean(request);
             if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
         	{
         		throw new ServiceException("对不起，您没有权限 !");
         	}
             Map<String,String> map=new HashMap<String,String>();
             String SALE_ORDER_IDS=request.getParameter("SALE_ORDER_IDS");
             map.put("SALE_ORDER_IDS", SALE_ORDER_IDS);
             map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
             map.put("LEDGER_ID", userBean.getLoginZTXXID());
             map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
             String REBATEFLAG=request.getParameter("REBATEFLAG");
             if("1".equals(REBATEFLAG)){
            	 //add by zhuxw 2014-08-03
     			String[] REBATE_PRD_NUMBER=REBATE_PRD_NUMBERS.split(",");
     			StringBuffer PRD_NOS=new StringBuffer();
     			for (int i = 0; i < REBATE_PRD_NUMBER.length; i++) {
     				PRD_NOS.append("'").append(REBATE_PRD_NUMBER[i]).append("',");
     			}
     			String PRDNOS=PRD_NOS.toString();
     			if(PRDNOS.length()>0){
     				PRDNOS = PRDNOS.substring(0,PRDNOS.length() - 1);
     			}
     			StringBuffer sql=new StringBuffer();
     			sql.append(" and c.PAR_PRD_NO in (").append(PRDNOS).append(") and a.SPCL_TECH_ID is null ");
     			map.put("sql", sql.toString());
             }
             List<Map<String, String>> result = sotoAdvorderService.findGroInfo(map);
             Map<String,String> params=getChannInfo(request);
             Map<String,String> channInfo=sotoAdvorderService.getChannInfoLv(params.get("RECV_CHANN_ID"));
             String flag;
     		if("区域服务中心".equals(channInfo.get("CHANN_TYPE"))){
            	 flag="1";
             }else{
            	 flag="0";
             }
           //获取返利折扣
     		Map<String,Object> REBATEINFO = LogicUtil.getChannRebateDsct(userBean.getCHANN_ID());
     		if(null!=REBATEINFO){
     			request.setAttribute("REBATEDSCT", REBATEINFO.get("DECT_RATE"));//返利折扣
     			request.setAttribute("REBATE_TOTAL", REBATEINFO.get("REBATE_TOTAL"));//返利总金额
     			request.setAttribute("REBATE_CURRT", REBATEINFO.get("REBATE_CURRT"));//当前返利
     			request.setAttribute("REBATE_FREEZE", REBATEINFO.get("REBATE_FREEZE"));//冻结的返利
     		}
             request.setAttribute("flag", flag);
             request.setAttribute("rst", result);
             request.setAttribute("params", params);
             request.setAttribute("REBATEFLAG", REBATEFLAG);
             request.setAttribute("pvg",setPvg(userBean));
             request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
             request.setAttribute("SALE_ORDER_IDS", SALE_ORDER_IDS);
             request.setAttribute("", channInfo);
             return mapping.findForward("childGroEdit");
         }
         /**
          * 查询货品库存
          * @param mapping
          * @param form
          * @param request
          * @param response
          * @return
          */
         public ActionForward toListPro(ActionMapping mapping, ActionForm form,
     			HttpServletRequest request, HttpServletResponse response) {
     		UserBean userBean = ParamUtil.getUserBean(request);
     		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
         	{
         		throw new ServiceException("对不起，您没有权限 !");
         	}
     		String FROM_BILL_DTL_IDS = request.getParameter("FROM_BILL_DTL_IDS");
     		
     		Map<String,String> map = new HashMap<String,String>();
     		map.put("SALE_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
     		map.put("LEDGER_ID", userBean.getLoginZTXXID());
     		List<Map<String,Object>> list = sotoAdvorderService.loadProductStoreNum(map);
     		request.setAttribute("rst", list);
     		return mapping.findForward("toListPro");
     	}
         
         
         /**
     	 * * 主表 新增
     	 * @param mapping the mapping
     	 * @param form the form
     	 * @param request the request
     	 * @param response the response
     	 */
     	public void edit(ActionMapping mapping, ActionForm form,
     			HttpServletRequest request, HttpServletResponse response) {
     		UserBean userBean = ParamUtil.getUserBean(request);
         	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_SHIFT))
         	{
         		throw new ServiceException("对不起，您没有权限 !");
         	}	
     		PrintWriter writer = getWriter(response);
     		String jsonResult = jsonResult();
     		try {
                 String SALE_ORDER_IDS = ParamUtil.get(request, "SALE_ORDER_IDS");
                 String REBATEFLAG=ParamUtil.get(request, "REBATEFLAG");
                 String REBATEDSCT=ParamUtil.get(request, "REBATEDSCT");
                 Map<String,String> map=getChannInfo(request);
                 String jsonDate = ParamUtil.get(request, "childJsonData");
                 List <SotoadvorderGoodModelChld> chldModelList = null;
                 if (!StringUtil.isEmpty(jsonDate)) {
                     chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <SotoadvorderGoodModelChld>>() {
                     }.getType());
                 }
                 sotoAdvorderService.txEdit(SALE_ORDER_IDS,chldModelList,userBean,map,REBATEFLAG,REBATEDSCT);
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
	    	pvgMap.put("PVG_SHIFT",QXUtil.checkPvg(userBean, PVG_SHIFT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 public Map<String,String> getChannInfo(HttpServletRequest request){
		 Map<String,String> map=new HashMap<String, String>();
 		map.put("ORDER_CHANN_ID", ParamUtil.utf8Decoder(request, "ORDER_CHANN_ID"));
 		map.put("ORDER_CHANN_NO", ParamUtil.utf8Decoder(request, "ORDER_CHANN_NO"));
 		map.put("ORDER_CHANN_NAME", ParamUtil.utf8Decoder(request, "ORDER_CHANN_NAME"));
 		map.put("RECV_CHANN_ID", ParamUtil.utf8Decoder(request, "RECV_CHANN_ID"));
 		map.put("RECV_CHANN_NO", ParamUtil.utf8Decoder(request, "RECV_CHANN_NO"));
 		map.put("RECV_CHANN_NAME", ParamUtil.utf8Decoder(request, "RECV_CHANN_NAME"));
 		map.put("RECV_ADDR_NO", ParamUtil.utf8Decoder(request, "RECV_ADDR_NO"));
 		map.put("RECV_ADDR", ParamUtil.utf8Decoder(request, "RECV_ADDR"));
 		return map;
	 }
}
