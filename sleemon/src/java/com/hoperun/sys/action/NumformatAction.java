package com.hoperun.sys.action;

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
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.NumformatModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.NumformatService;

/**
 * 小数位设置
 */
public class NumformatAction extends BaseAction {

	/** The numformat service. */
	private NumformatService numformatService;

	/**
	 * Gets the numformat service.
	 * 
	 * @return the numformat service
	 */
	public NumformatService getNumformatService() {
		return numformatService;
	}

	/**
	 * Sets the numformat service.
	 * 
	 * @param numformatService the new numformat service
	 */
	public void setNumformatService(NumformatService numformatService) {
		this.numformatService = numformatService;
	}
	
	/**
	 * 框架页面.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

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
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map params = new HashMap();
        params.put("NUMFORMATMC", ParamUtil.utf8Decoder(request, "NUMFORMATMC"));
        params.put("NUMTYPE", ParamUtil.utf8Decoder(request, "NUMTYPE"));
        params.put("ROUNDTYPE", ParamUtil.utf8Decoder(request, "ROUNDTYPE"));
        params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        // 字段排序
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = numformatService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String NUMFORMATID = ParamUtil.get(request, "NUMFORMATID");
        if (StringUtils.isNotEmpty(NUMFORMATID)) {
            Map<String,String> entry = numformatService.load(NUMFORMATID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("view");
    }
    
    /**
     * 编辑.
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

        String NUMFORMATID = ParamUtil.get(request, "NUMFORMATID");
        if (StringUtils.isNotEmpty(NUMFORMATID)) {
            Map entry = numformatService.load(NUMFORMATID);
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

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        try {
        	UserBean userBean = ParamUtil.getUserBean(request);
            String jsonData = ParamUtil.get(request, "jsonData");
            NumformatModel model = null;
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <NumformatModel>() {
                }.getType());
            }

            String NUMFORMATID = ParamUtil.get(request, "NUMFORMATID");

            //if (StringUtils.isEmpty(NUMFORMATID) && (bmxxService.getBmxxExits(model.getBMBH())) > 0) {
            //    jsonResult = jsonResult(false, "部门编号已经存在");
            //} else {

                try {
                	numformatService.txEdit(NUMFORMATID, model, userBean);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    jsonResult = jsonResult(false, "保存失败");
                }
            //}
        } catch (JsonParseException e) {
            e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 停用/启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    @SuppressWarnings("unchecked")
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        Map<String,String> params = new HashMap<String,String>();
        String NUMFORMATID = request.getParameter("NUMFORMATID");
        params.put("NUMFORMATID", NUMFORMATID);
        String flag = request.getParameter("flag");
        if(flag.equals("1")){
            params.put("STATE", "启用");
            
        }else{
        	params.put("STATE", "停用");
        }
        try{
        	numformatService.chenagestate(params);
        }catch(Exception e){
        	jsonResult=jsonResult(false,"更新失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
    /**
     * Delete.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	String NUMFORMATID = request.getParameter("NUMFORMATID");
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	numformatService.deleteOneR(NUMFORMATID);
        }catch(Exception e){
        	jsonResult=jsonResult(false,"更新失败");
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
}
