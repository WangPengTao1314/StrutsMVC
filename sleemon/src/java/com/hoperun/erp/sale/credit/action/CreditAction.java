/**
 * 项目名称：平台
 * 系统名：信用管理
 * 文件名：CreditAction.java
 */
package com.hoperun.erp.sale.credit.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.credit.model.CreditModel;
import com.hoperun.erp.sale.credit.service.CreditService;
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
public class CreditAction extends BaseAction {

	/** 权限对象维护**/
    //增删改查
    private static String PVG_BWS="BS0012501";
    private static String PVG_EDIT="BS0012502";
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
    private CreditService creditService;
    
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
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map params = new HashMap();
    	params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));				//渠道编号
        params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));			//渠道名称
        params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));			//渠道类型
        params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));				//渠道级别
        params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));				//区域编号
        params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));			//区域名称
        params.put("CREDIT_CRE_TIME_FROM", ParamUtil.utf8Decoder(request, "CREDIT_CRE_TIME_FROM"));	//信用修改时间从
        params.put("CREDIT_CRE_TIME_TO", ParamUtil.utf8Decoder(request, "CREDIT_CRE_TIME_TO"));		//信用修改时间到
        
        params.put("UPD_TIME_BEGIN", ParamUtil.utf8Decoder(request, "UPD_TIME_BEGIN"));	//信用修改时间从
        params.put("UPD_TIME_END", ParamUtil.utf8Decoder(request, "UPD_TIME_END"));		//信用修改时间到
        
        // add by zzb 2014-6-25 添加 渠道分管
//        String CHANN_CHARG = userBean.getCHANNS_CHARG();
//        params.put("CHANN_CHARG", CHANN_CHARG);
        
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		// 字段排序
		ParamUtil.setOrderField(request, params, "CHANN_NO", "ASC");
		
		IListPage page = creditService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 信用额度详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//渠道编号
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        Map entry = creditService.load(CHANN_ID);
        request.setAttribute("rst", entry);

        return mapping.findForward("detail");
    }


    /**
     * 点击修改按钮跳转到编辑页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//渠道编号
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if (StringUtils.isNotEmpty(CHANN_ID)) {
            Map entry = creditService.load(CHANN_ID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("toedit");
    }


    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        //渠道编号
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        String jsonData = ParamUtil.get(request, "jsonData");
        CreditModel model = new CreditModel();
        
    	try {
    		if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <CreditModel>() {
                }.getType());
            }
    		//编辑：新增/删除
    		creditService.txEdit(CHANN_ID,model,userBean); 
        } catch (Exception e) {
        	String cause = e.getCause().toString();
        	if(!StringUtil.isEmpty(cause) && -1 != cause.indexOf("ORA-01438")){
        		jsonResult = jsonResult(false, "'初始信用额度'值过大");
        	}else{
        		jsonResult = jsonResult(false, "保存失败");
        	}
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

	 /**
	  * Sets the credit service.
	  * 
	  * @param creditService the new credit service
	  */
	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

}
