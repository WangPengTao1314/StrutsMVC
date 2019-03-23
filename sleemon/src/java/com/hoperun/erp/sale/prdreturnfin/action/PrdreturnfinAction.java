/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnAction
*/
package com.hoperun.erp.sale.prdreturnfin.action;
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
import com.hoperun.erp.sale.prdreturnfin.model.PrdreturnfinModelChld;
import com.hoperun.erp.sale.prdreturnfin.service.PrdreturnfinService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public class PrdreturnfinAction extends BaseAction {
	
	private Logger logger = Logger.getLogger(PrdreturnfinAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0012901";
    private static String PVG_EDIT="BS0012902";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0012903";
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
    /**审批 end**/
	
	/** 业务service*/
	private PrdreturnfinService prdreturnfinService;
	
	/**
	 * @return the prdreturnfinService
	 */
	public PrdreturnfinService getPrdreturnfinService() {
		return prdreturnfinService;
	}
	/**
	 * @param prdreturnfinService the prdreturnfinService to set
	 */
	public void setPrdreturnfinService(PrdreturnfinService prdreturnfinService) {
		this.prdreturnfinService = prdreturnfinService;
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
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));		
		request.setAttribute("flag", ParamUtil.get(request, "flag"));
		
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
	    ParamUtil.putStr2Map(request, "PRD_RETURN_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_BEG", params);
        ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_END", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if(StringUtil.isEmpty(search)){
			params.put("searchSTATE", "'已鉴定'");
		}
		if(StringUtil.isEmpty(params.get("searchSTATE"))&&StringUtil.isEmpty(params.get("STATE"))){
			params.put("searchSTATE", "'已鉴定','已提交工厂','退回'");
		}
		 //权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    //维护和审核     列表页面过滤的条件为：系统权限  and 帐套ID=’当前登录帐套ID’  ;点击查询的时候，过滤条件不变
	    
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prdreturnfinService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
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
        String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_ID))
        {
        	 List <PrdreturnfinModelChld> result = prdreturnfinService.childQuery(PRD_RETURN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childDecideedit");
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
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdreturnfinModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnfinModelChld>>() {
                }.getType());
                prdreturnfinService.txChildEdit(modelList);
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
		String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
		if(!StringUtil.isEmpty(PRD_RETURN_ID)){
			Map<String,String> entry = prdreturnfinService.load(PRD_RETURN_ID);
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
		String ServiceCode = "TC0200120";
		String Operation = "createRMA";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "createRMA";
        try {
        	 String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
        	 Map<String,String> map=new HashMap<String,String>();
        	 map.put("PRD_RETURN_ID", PRD_RETURN_ID);
        	 String strJsonData = LogicUtil.getStrCreateRMA(PRD_RETURN_ID);
     		 LogicUtil.actLog("退货单管理", "开始调入创建退货单接口", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE);
        	 map.put("JSON_DATA", strJsonData);
        	 String strResult = prdreturnfinService.txCommit(map);
            if("操作成功".equals(strResult)){
                LogicUtil.actLog("退货单管理", "创建退货单成功", "U9系统", "成功",strResult,AppCode,UId,OPRCODE);
                jsonResult = jsonResult(true,strResult);
               }else{
                LogicUtil.actLog("退货单管理", "创建退货单失败", "U9系统", "失败",strResult,AppCode,UId,OPRCODE);
                jsonResult = jsonResult(false,strResult);
            }
        } catch (Exception e) {
        	LogicUtil.actLog("退货单管理", "创建退货单失败", "U9系统", "失败",e.getMessage(),AppCode,UId,OPRCODE);
        	jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 退回
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void reback(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	 String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
        	 String REMARK=ParamUtil.utf8Decoder(request, "REMARK");
        	 prdreturnfinService.txReback(PRD_RETURN_ID, userBean,REMARK);
        	 jsonResult = jsonResult(true, "操作成功");
        } catch (Exception e) {
        	logger.info(e);
        	jsonResult = jsonResult(false, "操作失败");
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
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}