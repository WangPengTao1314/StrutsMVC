/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctooutAction
*/
package com.hoperun.drp.sale.advctoout.action;
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
import com.hoperun.drp.sale.advctoout.model.AdvctooutModelChld;
import com.hoperun.drp.sale.advctoout.service.AdvctooutService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvctooutAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
	//增删改查
	private static String PVG_BWS="FX0020201";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //提交发货
    private static String PVG_COMMIT="FX0020202";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
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
    private static String AUD_TAB_NAME="";//表名
    private static String AUD_TAB_KEY="";//主键
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvctooutService advctooutService;
	
	/**
	 * @return the advctooutService
	 */
	public AdvctooutService getAdvctooutService() {
		return advctooutService;
	}
	/**
	 * @param advctooutService the advctooutService to set
	 */
	public void setAdvctooutService(AdvctooutService advctooutService) {
		this.advctooutService = advctooutService;
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
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    //初始时只显示未处理的数据
		if(StringUtil.isEmpty(search))
	    { 
			//状态为提交
			params.put("STATE", BusinessConsts.COMMIT);
	    }
		else
		{
			ParamUtil.putStr2Map(request, "STATE", params);
		}
		if(StringUtil.isEmpty(params.get("STATE"))){
			params.put("STATES", "'提交','待发货','已发货','已收货'");
		}
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置终端销售
		params.put("BILL_TYPE", "终端销售");
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//字段排序
		ParamUtil.setOrderField(request, params, "b.ORDER_RECV_DATE", "asc");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advctooutService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		Integer count=advctooutService.getCountQual(params);
		request.setAttribute("count", count);
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
        	 List <AdvctooutModelChld> result = advctooutService.childQuery(ADVC_ORDER_ID);
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
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			Map<String,String> entry = advctooutService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
	    	pvgMap.put("PVG_COMMIT",QXUtil.checkPvg(userBean, PVG_COMMIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
    /**
     * 点击提交发货按钮后跳转页面显示list
     */
 	public ActionForward toChild(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
     	UserBean userBean = ParamUtil.getUserBean(request);
         if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
     	{
     		throw new ServiceException("对不起，您没有权限 !");
     	}
         String LEDGER_ID=userBean.getLoginZTXXID();
         String ADVC_ORDER_ID=ParamUtil.get(request, "ADVC_ORDER_ID");
         request.setAttribute("LEDGER_ID", LEDGER_ID);
         request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
         request.setAttribute("ADVC_ORDER_ID", ADVC_ORDER_ID);
         Map<String,String> params=new HashMap<String,String>();
         params.put("LEDGER_ID", LEDGER_ID);
         params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
         params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
         params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
         List<Map<String,String>> list=advctooutService.getDtl(params);
         request.setAttribute("list", list);
         return mapping.findForward("childGroEdit");
         
     }
	@SuppressWarnings("unchecked")
	public ActionForward toListPro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String PRD_IDS=request.getParameter("PRD_IDS");
		Map<String,String> map=new HashMap<String,String>();
		map.put("PRD_IDS", PRD_IDS);
		map.put("LEDGER_ID", userBean.getLoginZTXXID());
		List list=advctooutService.loadProduct(map);
		request.setAttribute("rst", list);
		return mapping.findForward("toListPro");
	}
	/**
	 * * 预订单发货
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_COMMIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            Map<String,String> params = new HashMap<String,String>();
    	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
    	    ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
    	    ParamUtil.putStr2Map(request, "REMARK", params);
    	    Map<String,String> storeOutMap=new HashMap<String,String>();
    	    storeOutMap=advctooutService.findSTOREOUT(params);
            if(storeOutMap==null){
            	throw new ServiceException("对不起，您输入的库房信息有误，请重新输入 !");
            }
            storeOutMap.put("REMARK", params.get("REMARK"));
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvctooutModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvctooutModelChld>>() {
                }.getType());
            }
            LogicUtil.genStoreOutOrder(ADVC_ORDER_ID, "DRP_ADVC_ORDER", userBean, storeOutMap,chldModelList);
        }catch(ServiceException a){
        	String error=a.getMessage();
        	jsonResult = jsonResult(false, error);
        	a.printStackTrace();
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
}
