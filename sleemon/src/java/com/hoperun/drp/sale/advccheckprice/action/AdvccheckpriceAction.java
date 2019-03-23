/**
 * prjName:喜临门营销平台
 * ucName:预订单核价
 * fileName:AdvcorderAction
*/
package com.hoperun.drp.sale.advccheckprice.action;
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

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advccheckprice.model.AdvccheckpriceModelChld;
import com.hoperun.drp.sale.advccheckprice.model.AdvccheckpriceModelGchld;
import com.hoperun.drp.sale.advccheckprice.service.AdvccheckpriceService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvccheckpriceAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(AdvccheckpriceAction.class);
	/** 权限对象**/
	/** 维护*/
	//增删改查
	private static String PVG_BWS="FX0021301";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021302";
    private static String PVG_TRACE="";
    //打回
    private static String PVG_REVERSE="FX0021303";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";//表名
    private static String AUD_TAB_KEY="";//主键
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvccheckpriceService advccheckpriceService;
	
	/**
	 * @return the advccheckpriceService
	 */
	public AdvccheckpriceService getAdvccheckpriceService() {
		return advccheckpriceService;
	}
	/**
	 * @param advccheckpriceService the advccheckpriceService to set
	 */
	public void setAdvccheckpriceService(AdvccheckpriceService advccheckpriceService) {
		this.advccheckpriceService = advccheckpriceService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//初始时只显示未接收的数据
		if(StringUtil.isEmpty(search))
	    { 
			//状态为未提交
			params.put("searchSTATE", "'待核价'");
	    }
		else
		{
			String STATE = ParamUtil.utf8Decoder(request,"STATE");
			params.put("STATE", STATE);
		}
		params.put("CHK_PRICE_PSON_ID", userBean.getRYXXID());
		search="true";
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置终端销售
		params.put("BILL_TYPE", "终端销售");
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
        	params.put("searchSTATE", "'退回','提交','待核价'");
        }
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advccheckpriceService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
        String ADVC_ORDER_ID =ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        	 List <AdvccheckpriceModelChld> result = advccheckpriceService.childQuery(ADVC_ORDER_ID);
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
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        	 List <AdvccheckpriceModelChld> result = advccheckpriceService.childQuery(ADVC_ORDER_ID);
             request.setAttribute("rst", result);
        }
        return mapping.findForward("childedit");
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
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			Map<String,String> entry = advccheckpriceService.load(ADVC_ORDER_ID);
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
        logger.info("按钮修改为启用单条记录开始");
        try {
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String type=request.getParameter("type");
            Map<String,String> map=new HashMap<String,String>();
            map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            if("reverse".equals(type)){
            	String RETURN_RSON = ParamUtil.utf8Decoder(request, "RETURN_RSON");
            	map.put("STATE", "退回");
            	map.put("RETURN_RSON", RETURN_RSON);
            }else if("commit".equals(type)){
            	map.put("STATE", BusinessConsts.COMMIT);
            	 map.put("addSQL", " PAYED_TOTAL_AMOUNT=ADVC_AMOUNT ");
            }
            map.put("CHK_PRICE_TIME", "sysdate");
            map.put("UPDATOR", userBean.getRYXXID());
            map.put("UPD_NAME", userBean.getXM());
            map.put("UPD_TIME", "sysdate");
//            MsgInfo mesgObj = advccheckpriceService.txUpdateById(map,userBean);
            advccheckpriceService.upStateById(map);
//            if(!mesgObj.isFLAG()){
//            	jsonResult = jsonResult(false, mesgObj.getMESS());
//        	}else{
//        		jsonResult = jsonResult(true, "");
//        	}
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
           jsonResult = jsonResult(false, "提交失败");
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
	    	pvgMap.put("PVG_REVERSE",QXUtil.checkPvg(userBean, PVG_REVERSE) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
	    public ActionForward gchildList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        String ADVC_ORDER_ID =ParamUtil.get(request, "ADVC_ORDER_ID");
	        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
	        {
	        	 List <AdvccheckpriceModelGchld> result = advccheckpriceService.gchildQuery(ADVC_ORDER_ID);
	             request.setAttribute("rst", result);
	        }
	        request.setAttribute("pvg",setPvg(userBean));
	        return mapping.findForward("gchildlist");
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
	    public ActionForward modifyToGchildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
	        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
	        {
	        	 List <AdvccheckpriceModelGchld> result = advccheckpriceService.gchildQuery(ADVC_ORDER_ID);
	             request.setAttribute("rst", result);
	        }
	        return mapping.findForward("gchildedit");
	    }

}