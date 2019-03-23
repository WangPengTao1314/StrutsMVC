package com.hoperun.erp.sale.channdiscount.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.channdiscount.model.ChannDiscountModel;
import com.hoperun.erp.sale.channdiscount.service.ChannDiscountService;
import com.hoperun.sys.model.UserBean;

public class ChannDiscountAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010501";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
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
    
    private ChannDiscountService channDiscountService;
    
    /**
     * 上贞查询页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
    	return mapping.findForward("frame");
    }

	/**
	 * 下帧帧显示渠道信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String,String> params=new HashMap<String,String>();
		params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
		params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
		params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
		params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
		params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));
		params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));
		params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
		String set=ParamUtil.utf8Decoder(request, "set");
		String DECT_TYPE=ParamUtil.utf8Decoder(request, "DECT_TYPE");
		if(StringUtil.isEmpty(DECT_TYPE)){
			DECT_TYPE=BusinessConsts.DECT_TYPE;
		}
		String sql="";
		if("已设置".equals(set)){
			sql=" DECT_TYPE is not null";
		}else if("未设置".equals(set)||StringUtil.isEmpty(set)){
			sql=" DECT_TYPE is null";
		}else{
			sql="1=1";
		}
		params.put("sql", sql);
		//-- 0156143--Start--刘曰刚--2014-06-17//
		//增加折扣类别查询条件,增加渠道状态筛选条件
		params.put("DECT_TYPE", DECT_TYPE);
		params.put("STATE", BusinessConsts.STATE_MAKE);
		//-- 0156143--End--刘曰刚--2014-06-17//
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		List list=channDiscountService.getChannInfo(params);
		request.setAttribute("rst", list);
		return mapping.findForward("list");
	}
    
    /**
     * 保存销售折扣
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = ParamUtil.get(request, "jsonData");
			String CHANN_DSCT_IDS=ParamUtil.get(request, "CHANN_DSCT_IDS");
			String DECT_TYPE=ParamUtil.get(request, "DECT_TYPE");
			List<ChannDiscountModel> ModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				ModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<ChannDiscountModel>>() {
						}.getType());
				channDiscountService.txInsertDsct(CHANN_DSCT_IDS,ModelList,DECT_TYPE);
			}
			
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
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
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 /**
		 * @return the channDiscountService
		 */
		public ChannDiscountService getChannDiscountService() {
			return channDiscountService;
		}

		/**
		 * @param channDiscountService the channDiscountService to set
		 */
		public void setChannDiscountService(ChannDiscountService channDiscountService) {
			this.channDiscountService = channDiscountService;
		}
}
