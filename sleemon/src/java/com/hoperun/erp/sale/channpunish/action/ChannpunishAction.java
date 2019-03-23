/**

 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannAction.java
 */
package com.hoperun.erp.sale.channpunish.action;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.erp.sale.channpunish.service.ChannpunishService;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ChannAction.
 * 
 * @module 系统管理
 * @func 渠道惩罚设定
 * @version 1.0
 * @author 刘曰刚
 */
public class ChannpunishAction extends BaseAction {
	/** The chann Service*/
	private ChannpunishService channpunishService;
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0013001";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //惩罚
    private static String PVG_PUNISH="BS0013002";
    //取消惩罚
    private static String PVG_CANCEL="BS0013003";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
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
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    
	/**
	 * @return the channpunishService
	 */
	public ChannpunishService getChannpunishService() {
		return channpunishService;
	}
	/**
	 * @param channpunishService the channpunishService to set
	 */
	public void setChannpunishService(ChannpunishService channpunishService) {
		this.channpunishService = channpunishService;
	}
	/**
	 * 渠道信息框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
		
	}
	/**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));
    	params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
    	params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
    	params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));
    	params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
    	params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
    	params.put("BEGIN_DEAL_TIME", ParamUtil.utf8Decoder(request, "BEGIN_DEAL_TIME"));
    	params.put("END_DEAL_TIME", ParamUtil.utf8Decoder(request, "END_DEAL_TIME"));
    	params.put("DEAL_PSON_NAME", ParamUtil.utf8Decoder(request, "DEAL_PSON_NAME"));
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "CHANN_ID", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = channpunishService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("list");
    }
    /**
     * 查看渠道详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        Map<String,String> entry = channpunishService.load(CHANN_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("detail");
    }
    //惩罚/取消惩罚
    public void toPunish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_CANCEL)&&!QXUtil.checkMKQX(userBean, PVG_PUNISH)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String jsonResult = jsonResult();
		PrintWriter writer = getWriter(response);
		try {
	        String CHANN_ID = ParamUtil.utf8Decoder(request, "CHANN_ID");
	        String type=ParamUtil.utf8Decoder(request, "type");
	        String PUNISH_FLAG="";
	        Map<String,String> map=new HashMap<String,String>();
	        map.put("CHANN_ID", CHANN_ID);
	        if("puinsh".equals(type)){
	        	PUNISH_FLAG=BusinessConsts.YJLBJ_FLAG_TRUE;
	        	map.put("PUNISH_REMARK", ParamUtil.utf8Decoder(request, "PUNISH_REMARK"));
	        }else{
	        	PUNISH_FLAG=BusinessConsts.YJLBJ_FLAG_FALSE;
	        	map.put("PUNISH_REMARK", "");
	        }
	        map.put("PUNISH_FLAG", PUNISH_FLAG);
	        map.put("DEAL_PSON_ID", userBean.getRYXXID());
	        map.put("DEAL_PSON_NAME", userBean.getXM());
	        map.put("DEAL_TIME", "sysdate");
        	channpunishService.txUpdateById(map);
        	jsonResult = jsonResult(true, "");
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
	    	pvgMap.put("PVG_PUNISH",QXUtil.checkPvg(userBean, PVG_PUNISH) );
	    	pvgMap.put("PVG_CANCEL",QXUtil.checkPvg(userBean, PVG_CANCEL) );
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}
