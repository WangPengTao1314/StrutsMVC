/**
 * 项目名称：平台
 * 系统名：信用管理
 * 文件名：CreditAction.java
 */
package com.hoperun.erp.sale.annualClosing.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.annualClosing.service.AnnualClosingService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandAction.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public class AnnualClosingAction extends BaseAction {

	/** 权限对象维护**/
    //增删改查
    private static String PVG_BWS="BS0014001";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
    /**权限 end*/
    
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
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**审批 end**/
   
    /** The credit service. */
    private AnnualClosingService annualClosingService;
    
    /**
     * 信用额度设定框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//设置跳转时需要的一些公用属性
    	ParamUtil.setCommAttributeEx(request);
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 信用额度列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * s
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
    	Map<String,String> params = new HashMap<String,String>();
    	String YEAR=ParamUtil.utf8Decoder(request, "YEAR");
    	List page=new ArrayList();
    	if(!StringUtil.isEmpty(YEAR)){
    		 //只查询0的记录。1为删除。0为正常
    		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    		params.put("STATE", "'启用','停用'");
    		params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    		params.put("YEAR", YEAR);
    		//权限级别和审批流的封装以及状态的封装
    		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    		// 字段排序
    		ParamUtil.setOrderField(request, params, "a.CHANN_NO", "ASC");
    		page = annualClosingService.pageQuery(params);
    	}
		request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    /**
     * 验证所选年份是否有年结数据
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toCheckClosing(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
            String YEAR = ParamUtil.get(request, "YEAR");
            if(StringUtil.isEmpty(YEAR)){
            	throw new ServiceException("请选择年份 !");
            }
            Map<String,String> params=new HashMap<String, String>();
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            params.put("STATE", "'启用','停用'");
            params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
            params.put("YEAR", YEAR);
            int count=annualClosingService.checkInfoCount(params);
            Map map=new HashMap();
            if(count>0){
            	map.put("flag", true);
            }else{
            	map.put("flag", false);
            }
            jsonResult = new Gson().toJson(new Result(true, map, ""));
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "年结失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
    
    
    public void toClosing(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
            String YEAR = ParamUtil.get(request, "YEAR");
            if(StringUtil.isEmpty(YEAR)){
            	throw new ServiceException("请选择年份 !");
            }
            Map<String,String> params=new HashMap<String, String>();
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            params.put("STATE", "'启用','停用'");
            params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
            params.put("YEAR", YEAR);
            annualClosingService.txClosing(params);
            jsonResult = jsonResult(true, "");
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "年结失败");
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
	 private Map<String,String> setPvg(UserBean userBean) {
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


	public AnnualClosingService getAnnualClosingService() {
		return annualClosingService;
	}


	public void setAnnualClosingService(AnnualClosingService annualClosingService) {
		this.annualClosingService = annualClosingService;
	}


}
