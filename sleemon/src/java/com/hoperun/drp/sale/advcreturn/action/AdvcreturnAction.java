/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:advcreturnAction
*/
package com.hoperun.drp.sale.advcreturn.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcreturn.model.AdvcreturnModelChld;
import com.hoperun.drp.sale.advcreturn.service.AdvcreturnService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class AdvcreturnAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0020301";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
  //打回
    private static String PVG_REVERSE="FX0020103";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    //提交退货入库
    private static String PVG_COMMIT="FX0020302";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_ADVC_ORDER";
    private static String AUD_TAB_KEY="ADVC_ORDER_ID";
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvcreturnService advcreturnService;
	
	/**
	 * @return the advcreturnService
	 */
	public AdvcreturnService getAdvcreturnService() {
		return advcreturnService;
	}
	/**
	 * @param advcreturnService the advcreturnService to set
	 */
	public void setAdvcreturnService(AdvcreturnService advcreturnService) {
		this.advcreturnService = advcreturnService;
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
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_END", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    params.put("BILL_TYPE", "终端退货");
	    //查询时状态为查询选择状态，不然状态为未提交状态
	    if(StringUtil.isEmpty(search)){
	    	 params.put("searchSTATE",BusinessConsts.COMMIT);
	    }else
		{
	    	ParamUtil.putStr2Map(request, "STATE", params);
		}
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
	    	params.put("STATES", "'提交','待退货','已退货','退回'");
	    }
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advcreturnService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
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
        String ADVC_ORDER_ID =ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        	 List <AdvcreturnModelChld> result = advcreturnService.childQuery(ADVC_ORDER_ID);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			Map<String,String> entry = advcreturnService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * 点击提交退货货按钮后跳转页面显示页面
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
         request.setAttribute("ADVC_ORDER_ID", ADVC_ORDER_ID);
         return mapping.findForward("childGroEdit");
         
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
	    	pvgMap.put("PVG_COMMIT",QXUtil.checkPvg(userBean, PVG_COMMIT) );
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
		 * * 预订单退货
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
	            ParamUtil.putStr2Map(request, "STORE_ID", params);
//	    	    ParamUtil.putStr2Map(request, "STORE_NO", params);
	    	    ParamUtil.putStr2Map(request, "STORE_NAME", params);
	    	    ParamUtil.putStr2Map(request, "STORAGE_FLAG", params);
	    	    Map<String,String> storeOutMap=new HashMap<String,String>();
	    	    storeOutMap=advcreturnService.findSTOREOUT(params);
	            if(storeOutMap==null){
	            	throw new ServiceException("对不起，您输入的库房信息有误，请重新输入 !");
	            }
	            Map<String,String> map=new HashMap<String,String>();
	            map.put("STOREIN_STORE_ID", storeOutMap.get("STORE_ID"));
	            map.put("STOREIN_STORE_NO", storeOutMap.get("STORE_NO"));
	            map.put("STOREIN_STORE_NAME", storeOutMap.get("STORE_NAME"));
	            Object obj=storeOutMap.get("STORAGE_FLAG");
	            map.put("STORAGE_FLAG", obj.toString());
	            LogicUtil.genStoreInOrder(ADVC_ORDER_ID, "DRP_ADVC_ORDER", userBean, map);
	        }catch (Exception e) {
	            jsonResult = jsonResult(false, "保存失败");
	            e.printStackTrace();
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
		}
		 /**
	     * * 打回.
	     * 
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void toReverse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_REVERSE))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
	            String REMARK = ParamUtil.get(request, "REMARK");
	            Map<String,String> map=new HashMap<String,String>();
	            map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
	            map.put("UPDATOR", userBean.getRYXXID());
	            map.put("UPD_NAME", userBean.getXM());
	            map.put("UPD_TIME", "数据库时间");
	            map.put("REMARK", REMARK);
	            map.put("STATE", BusinessConsts._BACK);
	            advcreturnService.txReverse(map);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    public ActionForward printInfo(ActionMapping mapping, ActionForm form,
				 HttpServletRequest request, HttpServletResponse response) {
			 	 UserBean userBean = ParamUtil.getUserBean(request);
			 	 String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
				 
				//记录打印次数
			 	Map<String,String> printMap=new HashMap<String, String>();
			 	printMap.put("TABLE_NAME", "DRP_ADVC_ORDER");
			 	printMap.put("BUSS_ID", ADVC_ORDER_ID);
			 	if(!LogicUtil.txUpdatePrintNum(printMap)){
			 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
			 	}
			 	StringBuffer paramSql=new StringBuffer();
			 	 paramSql.append("returnPrint.raq&ADVC_ORDER_ID=").append(ADVC_ORDER_ID).append("&arg1=").append(userBean.getXM());
			 	/**
			 	StringBuffer advSql = new StringBuffer();
				 advSql.append("rptSql=select ")
				 		.append(" to_char(CRE_TIME,'yyyy-mm-dd') CRE_TIME,")
				 		.append("CONTRACT_NO,")
				 		.append("ADVC_ORDER_NO,")
				 		.append("TEL,")
				 		.append("to_char(RETURN_STATEMENT_DATE,'yyyy-MM-DD')RETURN_STATEMENT_DATE,")
				 		.append("RECV_ADDR,")
				 		.append("FROM_BILL_NO,")
				 		.append("PAYABLE_AMOUNT,")
				 		.append("TERM_NAME,")
				 		.append("CRE_NAME,")
				 		.append("REMARK,")
				 		.append("sysdate GETDATE")
				 		.append(" from DRP_ADVC_ORDER  ")
				 		.append(" where DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
				 		.append(" and ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("'; ");
				 StringBuffer dtlSql=new StringBuffer();
				 dtlSql.append("dtlSql=select rownum,temp.* from (select ")
				 	   .append("a.PRD_NAME,")
				 	   .append("a.PRD_SPEC,")
				 	   .append("a.PRD_NO,")
				 	   .append("a.PRICE,")
				 	   .append("to_char(a.DECT_RATE)DECT_RATE,")
				 	   .append("to_char(a.DECT_PRICE)DECT_PRICE,")
				 	   .append("to_char(a.ORDER_NUM)ORDER_NUM,")
				 	   .append("a.REMARK,")
				 	   .append("to_char(a.PAYABLE_AMOUNT)PAYABLE_AMOUNT,")
				 	   .append("a.STD_UNIT,")
				 	   .append("b.SPCL_DTL_REMARK SPCLREMARK")
				 	   .append(" from DRP_ADVC_ORDER_DTL a  ")
				 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and USE_FLAG= ").append(BusinessConsts.YJLBJ_FLAG_TRUE)
				 	   .append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
				 	   .append(" and ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("' ")
				 	   .append("order by PRD_NAME asc ) temp ");
				 int count=advcreturnService.getDtlCount(ADVC_ORDER_ID);
				 double page=0;
				 if(count%5!=0){
					 page=Math.ceil((double)count/5);
				 }
				 double newCount=page*5;
				 for (int i = 0; i < (newCount-count); i++) {
					 dtlSql.append(" union all select ").append(count+i+1).append(",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual ");
				}
				 dtlSql.append(";");
				 StringBuffer sql=new StringBuffer();
				 StringBuffer arg=new StringBuffer();
				 arg.append(" arg1=").append(userBean.getXM());
				 sql.append(advSql.toString()).append(dtlSql.toString())
//				 .append(paymentSql.toString())
				 .append(arg.toString());
				 System.out.println(sql.toString());
				 // 报表路径名称
				 request.setAttribute("reportFileName",
				 "returnPrint.raq");
				 **/
				 // 要传递的宏参数！！！
				 request.setAttribute("params", paramSql.toString());
				 
				 return mapping.findForward("flashReportPrint");
		}
}