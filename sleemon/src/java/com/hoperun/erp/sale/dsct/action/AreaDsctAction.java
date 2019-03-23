/**
 * 项目名称：喜临门
 * 系统名：区域折扣信息
 * 文件名：AreaDsctAction.java
 */
package com.hoperun.erp.sale.dsct.action;

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
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.erp.sale.dsct.model.AreaDsctModel;
import com.hoperun.erp.sale.dsct.service.AreaDsctService;
import com.hoperun.sys.model.UserBean;

/**
 * The Class AreaDsctModel.
 * 
 * @module 总部->销售管理->价格管理
 * @func 区域折扣信息
 * @version 1.0
 * @author 王志格
 */
public class AreaDsctAction extends BaseAction {
	
	/**
	 * 区域折扣Service
	 */
	private AreaDsctService areaDsctService;

	/**
	 * 设置区域折扣Service
	 * @param areaDsctService
	 */
	public void setAreaDsctService(AreaDsctService areaDsctService) {
		this.areaDsctService = areaDsctService;
	}
	
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010101";
    private static String PVG_EDIT="BS0010101";
    private static String PVG_DELETE="BS0010101";
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
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**审批 end**/
	 
 	/**
     * 机构信息框架页面.
     * 
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	ParamUtil.setCommAttributeEx(request);
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
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
		
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

        Map params = new HashMap();
        
        params.put("DECT_TYPE", ParamUtil.get(request, "DECT_TYPE"));
		
        // 字段排序
        ParamUtil.setOrderField(request, params);
        
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        
        IListPage page = areaDsctService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));

        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String AREA_DSCT_ID = ParamUtil.get(request, "AREA_DSCT_ID");
        Map entry = areaDsctService.load(AREA_DSCT_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("detail");
    }
    
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String AREA_DSCT_ID = ParamUtil.get(request, "AREA_DSCT_ID");
        Map entry = areaDsctService.load(AREA_DSCT_ID);
        request.setAttribute("rst", entry);
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
    public void edit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
       
    	try {
    		 String AREA_DSCT_ID = ParamUtil.get(request, "AREA_DSCT_ID");
	        String jsonData = ParamUtil.get(request, "jsonData");
	        AreaDsctModel model = new AreaDsctModel();
	        if (StringUtils.isNotEmpty(jsonData)) {
	            model = new Gson().fromJson(jsonData, new TypeToken <AreaDsctModel>() {
	            }.getType());
	        }
    		areaDsctService.txEdit(AREA_DSCT_ID,model,userBean); 
        } catch (RuntimeException e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
                  
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
       
    	try {
    		 
    		String AREA_DSCT_ID = ParamUtil.get(request, "AREA_DSCT_ID");
    		String AREA_ID = ParamUtil.get(request, "AREA_ID");
    		areaDsctService.txDeleteById(AREA_DSCT_ID, AREA_ID);
    		jsonResult = jsonResult(true, "删除成功");
        } catch (RuntimeException e) {
            jsonResult = jsonResult(false, "删除失败");
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
	
}
