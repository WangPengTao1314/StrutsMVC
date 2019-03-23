/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseAction
*/
package com.hoperun.drp.sale.advise.action;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advise.model.AdviseModel;
import com.hoperun.drp.sale.advise.model.AdviseModelChld;
import com.hoperun.drp.sale.advise.service.AdviseService;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func 分销  
 * *@version 1.1 
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdviseAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(AdviseAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0040201";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //重提
    private static String PVG_AGAIN = "FX0040202";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
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
	private AdviseService adviseService;
    /**
	 * Sets the Advise service.
	 * 
	 * @param AdviseService the new Advise service
	 */
	public void setAdviseService(AdviseService adviseService) {
		this.adviseService = adviseService;
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
        String type = ParamUtil.get(request,"CMPL_ADVS_TYPE");
        if (type != null) {
        	request.setAttribute("CMPL_ADVS_TYPE",type);
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
		ParamUtil.putStr2Map(request, "CMPL_ADVS_NO", params);
		ParamUtil.putStr2Map(request, "CMPL_ADVS_TITLE", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_FROM", params);
		ParamUtil.putStr2Map(request, "RAISE_TIME_TO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "CMPL_OBJ", params);
//		ParamUtil.putStr2Map(request, "CMPL_TO_PSON", params);
		//0158002--start--刘曰刚--2014--06--30//
		//修改传入后台的投诉类型值为类型，以前为1，2，3
		String type = ParamUtil.get(request,"CMPL_ADVS_TYPE");
		if(type.equals("1")||"产品投诉".equals(type)){
			type = "产品投诉" ;
			params.put("CMPL_ADVS_TYPE", type);
		} else if (type.equals("2")||"服务投诉".equals(type)) {
			type = "服务投诉" ;
			params.put("CMPL_ADVS_TYPE", type);
			ParamUtil.putStr2Map(request, "CMPL_OBJ", params); //投诉对象
			ParamUtil.putStr2Map(request, "CMPL_TO_PSON", params);//投诉人员
		} else if (type.equals("3")||"建议".equals(type)) {
			type = "建议" ;
			params.put("CMPL_ADVS_TYPE", type);
			ParamUtil.putStr2Map(request, "ADVS_TYPE", params);//建议类型
		} else {
			ParamUtil.putStr2Map(request, "CMPL_ADVS_TYPE", params);
		}
		//0158002--End--刘曰刚--2014--06--30//
	    ParamUtil.putStr2Map(request, "STATE", params);
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
	   
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = adviseService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	 /**
     * * 产品投诉回馈信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward prdcmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {
        	 List <AdviseModelChld> result = adviseService.prdcmplQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("prdcmpl");
    }
    
    /**
     * * 服务投诉回馈信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward sercmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {	
        	 AdviseModel result = adviseService.sercmplQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("sercmpl");
    }
    
    /**
     * * 建议信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward advs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
        if(!StringUtil.isEmpty(CMPL_ADVS_ID))
        {	
        	 AdviseModel result = adviseService.advsQuery(CMPL_ADVS_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("advs");
    }
	
    /**
     * 处理过程页面跳转
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward dealProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String CMPL_ADVS_ID =ParamUtil.get(request, "CMPL_ADVS_ID");
    	
    	List <AdvisehdModelProcess> result = adviseService.processQuery(CMPL_ADVS_ID);
    	
    	request.setAttribute("list",result);
    	request.setAttribute("CMPL_ADVS_ID",CMPL_ADVS_ID);
    	
    	return mapping.findForward("dealProcess");
    }
    
    
    
    /**
     * 重提
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
	public void again(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_AGAIN))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String CMPL_ADVS_ID = ParamUtil.get(request, "CMPL_ADVS_ID");
			if(!StringUtil.isEmpty(CMPL_ADVS_ID)){
				adviseService.txAgain(CMPL_ADVS_ID, userBean);
				jsonResult = jsonResult(true, "操作成功");
			}
         
        } catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
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
	    	pvgMap.put("PVG_AGAIN",QXUtil.checkPvg(userBean, PVG_AGAIN) );
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}