/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderAction
*/
package com.hoperun.drp.sale.advctoorder.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelChld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelGoodChlds;
import com.hoperun.drp.sale.advctoorder.service.AdvctoorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvctoorderAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
	//增删改查
	private static String PVG_BWS="FX0020101";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    private static String PVG_SHIFT="FX0020102";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    //打回
    private static String PVG_REVERSE="FX0020103";
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
	private AdvctoorderService advctoorderService;
    /**
	 * Sets the Advctoorder service.
	 * 
	 * @param AdvctooutService the new Advctoorder service
	 */
	public void setAdvctoorderService(AdvctoorderService advctoorderService) {
		this.advctoorderService = advctoorderService;
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
		String search = ParamUtil.get(request, "search");
		request.setAttribute("search", search);
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
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String DEAL_FLAG = ParamUtil.get(request,"DEAL_FLAG");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    //初始时只显示未处理的数据
		if(StringUtil.isEmpty(search)) { 
			//状态为未提交
			params.put("searchDEAL_FLAG",BusinessConsts.YJLBJ_FLAG_FALSE);
			params.put("IS_TO_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
	    } else {
	    	params.put("DEAL_FLAG", DEAL_FLAG);
		}
		if("0".equals(DEAL_FLAG)){
			String sql="u.ADVC_ORDER_ID in (select a.ADVC_ORDER_ID from DRP_ADVC_ORDER_DTL a  where a.IS_TO_BASE_FLAG=0 and a.STATE='正常' and a.DEL_FLAG=0 and NVL(a.ORDER_NUM, 0) > NVL(a.SEND_NUM, 0)  group by a.ADVC_ORDER_ID )";
			params.put("sql", sql);
		}
		if(StringUtil.isEmpty(DEAL_FLAG)){
		  //String sql="u.ADVC_ORDER_ID in (select a.ADVC_ORDER_ID from DRP_ADVC_ORDER_DTL a  where  a.STATE='正常' and a.DEL_FLAG=0 group by a.ADVC_ORDER_ID )";
			String sql="u.ADVC_ORDER_ID in (select a.ADVC_ORDER_ID from DRP_ADVC_ORDER_DTL a  where  a.STATE='正常' and a.DEL_FLAG=0 )";
			params.put("sql", sql);
		}
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置终端销售
		params.put("BILL_TYPE", "终端销售");
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        if("2".equals(DEAL_FLAG)){
        	 //2->退回
        	 params.remove("DEAL_FLAG");
        	 params.put("STATE", "退回");
        }else{
        	 params.put("STATE", BusinessConsts.PASS);
        }
      //获取渠道参数
		Map<String,String> channInfo=advctoorderService.getChannInfo(userBean.getCHANN_ID());
		if(Integer.parseInt(String.valueOf(channInfo.get("ADVC_SCOPE_DAYS")))>0){
			params.put("querySql", " u.ORDER_RECV_DATE<=sysdate+"+String.valueOf(channInfo.get("ADVC_SCOPE_DAYS")));
		}
       
		//字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE,u.ADVC_ORDER_NO", "asc");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advctoorderService.pageQuery(params, pageNo);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_ORDER_ID =ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        	 List <AdvctoorderModelChld> result = advctoorderService.childQuery(ADVC_ORDER_ID);
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
			Map<String,String> entry = advctoorderService.load(ADVC_ORDER_ID);
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
	    	pvgMap.put("PVG_SHIFT",QXUtil.checkPvg(userBean, PVG_SHIFT) );
	    	pvgMap.put("PVG_REVERSE",QXUtil.checkPvg(userBean, PVG_REVERSE) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
		//状态为未提交
		params.put("STATE",BusinessConsts.PASS);
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置终端销售
		params.put("BILL_TYPE", "终端销售");
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        //处理标记
        params.put("IS_TO_BASE_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
        params.put("IS_TO_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
      //获取渠道参数
		Map<String,String> channInfo=advctoorderService.getChannInfo(userBean.getCHANN_ID());
		if(Integer.parseInt(String.valueOf(channInfo.get("ADVC_SCOPE_DAYS")))>0){
			params.put("querySql", " u.ORDER_RECV_DATE<=sysdate+"+String.valueOf(channInfo.get("ADVC_SCOPE_DAYS")));
		}
        params.put("QXJBCON", ParamUtil.getPvgCon("","",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE", "asc");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		if(StringUtil.isEmpty(request.getParameter("pageSize")))
		{
			params.put("pageSize", "20");
		}
		IListPage page = advctoorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
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
        String ADVC_ORDER_IDS=request.getParameter("ADVC_ORDER_IDS");
        map.put("ADVC_ORDER_IDS", ADVC_ORDER_IDS);
        map.put("BILL_TYPE", "终端销售");
        map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        map.put("LEDGER_ID", userBean.getLoginZTXXID());
        map.put("CHANN_ID", userBean.getCHANN_ID());
        map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        map.put("IS_TO_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
        String DECT_RATE=LogicUtil.getChannDsct(userBean.getCHANN_ID());
        if(StringUtil.isEmpty(DECT_RATE)){
        	request.setAttribute("error", "该渠道没有设置渠道折扣率");
        	map.put("DECT_RATE", "0");
        }else{
        	map.put("DECT_RATE", DECT_RATE);
        }
        List<Map<String, String>> result = advctoorderService.findGroInfo(map);
        //-- 0156143--Start--刘曰刚--2014-06-16//
//        StringBuffer prdId=new StringBuffer();
//        for (int i = 0; i < result.size(); i++) {
//			Map<String,String> param=result.get(i);
//			prdId.append("'").append(param.get("PRD_ID")).append("',");
//		}
//        String PRDIDS="";
//        if(!StringUtil.isEmpty(prdId.toString())){
//        	PRDIDS=prdId.toString().substring(0,prdId.toString().length() - 1);
//        }
//        String PrdNos=advctoorderService.getNotIncludedPrd(PRDIDS, ADVC_ORDER_IDS);
     
        //获取渠道折扣率
        //String DECT_RATE=advctoorderService.getChannDiscount(userBean.getCHANN_ID());
        request.setAttribute("DECT_RATE", DECT_RATE);
        //request.setAttribute("PrdNos", PrdNos);
        request.setAttribute("rst", result);
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("ADVC_ORDER_IDS", ADVC_ORDER_IDS);
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        return mapping.findForward("childGroEdit");
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
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String REMARK = ParamUtil.utf8Decoder(request,"REMARK");
            boolean shipmentFlag=advctoorderService.checkShipmentFlag(ADVC_ORDER_ID);
            if(!shipmentFlag){
            	throw new ServiceException("该预订单已做发货申请，请删除发货申请后再进行退回 !");
            }
            String message=advctoorderService.checkOrderFlag(ADVC_ORDER_ID);
            if(!StringUtil.isEmpty(message)){
            	throw new ServiceException(message);
            }
            boolean amountFlag=advctoorderService.checkamountFlag(ADVC_ORDER_ID);
            if(!amountFlag){
            	String NO=advctoorderService.getStatementsNo(ADVC_ORDER_ID);
            	throw new ServiceException("该预订单已存在付款单或销售退款单，单号:"+NO+";请删除后再进行退回 !");
            }
            boolean writeFlag=advctoorderService.checkWriteFlag(ADVC_ORDER_ID);
            if(!writeFlag){
            	throw new ServiceException("该预订单已存在核销过的单据，请删除后再进行退回 !");
            }
            Map<String,String> model=advctoorderService.load(ADVC_ORDER_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),model.get("SALE_DATE"));
			if(isMonthAcc){
				throw new ServiceException("销售结算日期:"+model.get("SALE_DATE")+"已月结不能退回");
			}
            Map<String,String> map=new HashMap<String,String>();
            map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            map.put("UPDATOR", userBean.getRYXXID());
            map.put("UPD_NAME", userBean.getXM());
            map.put("UPD_TIME", "数据库时间");
            map.put("REMARK", REMARK);
            map.put("STATE", "退回");
            map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
            advctoorderService.txReverse(map,userBean);
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
            String ADVC_ORDER_IDS = ParamUtil.get(request, "ADVC_ORDER_IDS");
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvctoorderModelGoodChlds> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvctoorderModelGoodChlds>>() {
                }.getType());
            }
            advctoorderService.txEdit(ADVC_ORDER_IDS,chldModelList,userBean);
            String[] advcs=ADVC_ORDER_IDS.split(",");
            for (int i = 0; i < advcs.length; i++) {
            	Map<String,String> map=new HashMap<String,String>();
            	String ADVC_ORDER_ID=advcs[i].replaceAll("'", "");
            	map.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
            	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            	map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
				int count=advctoorderService.getCount(map);
				if(0==count){
					advctoorderService.delete("Advctoorder.upAdv", map);
				}
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
		List list=advctoorderService.loadProduct(map);
		request.setAttribute("rst", list);
		return mapping.findForward("toListPro");
	}
	/**
	 * * 明细列表
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
	public ActionForward gchildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelGchld> result = advctoorderService
					.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
	}
	// 导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String DEAL_FLAG = ParamUtil.get(request,"DEAL_FLAG");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    //初始时只显示未处理的数据
		if(StringUtil.isEmpty(search)) { 
			//状态为未提交
			params.put("searchDEAL_FLAG",BusinessConsts.YJLBJ_FLAG_FALSE);
	    } else {
	    	params.put("DEAL_FLAG", DEAL_FLAG);
		}
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置终端销售
		params.put("BILL_TYPE", "终端销售");
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        if("2".equals(DEAL_FLAG)){
        	 //2->退回
        	 params.remove("DEAL_FLAG");
        	 params.put("STATE", "退回");
        }else{
        	 params.put("STATE", BusinessConsts.PASS);
        }
		List list = advctoorderService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="预订单编号,终端编号,终端名称,客户姓名,合同编号,订金金额,应收总额,业务员,销售日期,要求到货日期," +
							 "制单人,制单时间,制单部门,备注,处理状态,状态,货品编号,货品名称,规格型号,品牌," +
							 "标准单位,特殊工艺说明,单价,折扣率,折后单价,订货数量,抵库数量,已转数量,应收金额,交货日期";
		String tmpContent = "ADVC_ORDER_NO,TERM_NO,TERM_NAME,CUST_NAME,CONTRACT_NO,ADVC_AMOUNT,PAYABLE_AMOUNT,SALE_PSON_NAME,SALE_DATE,ORDER_RECV_DATE," +
							"CRE_NAME,CRE_TIME,DEPT_NAME,REMARK,DEAL_FLAG,STATE,PRD_NO,PRD_NAME,PRD_SPEC,BRAND," +
							"STD_UNIT,SPCL_DTL_REMARK,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,OFFSET_STORE_NUM,TO_BASE_ORDER_NUM,PAYABLE_AMOUNT,ORDER_RECV_DATE";
		String colType= "string,string,string,string,string,number,number,string,string,string," +
					   "string,string,string,string,string,string,string,string,string,string," +
					   "string,string,number,number,number,number,number,number,number,string";
		try {
			ExcelUtil
					.doExport(response, list, "预订单转订货明细", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

