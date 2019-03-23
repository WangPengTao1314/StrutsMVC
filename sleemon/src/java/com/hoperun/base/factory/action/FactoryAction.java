/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：FactoryAction.java
 */
package com.hoperun.base.factory.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.hoperun.base.factory.model.FactoryModel;
import com.hoperun.base.factory.model.FactoryProductModel;
import com.hoperun.base.factory.service.FactoryService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class FactoryAction.
 * 
 * @module 系统管理
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public class FactoryAction extends BaseAction{
	
	/**
	 * 生产基地service
	 */
	private FactoryService factoryService;
	
	/**
	 * 设置生产基地service
	 * @param prodbService
	 */
	 public void setFactoryService(FactoryService factoryService) {
			this.factoryService = factoryService;
		}
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="XT0012601";
    private static String PVG_EDIT="XT0012602";
    private static String PVG_DELETE="XT0012603";
    //启用,停用
    private static String PVG_START_STOP="XT0012604";
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
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
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
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

        Map params = new HashMap();
        
        params.put("FACTORY_NO", ParamUtil.get(request, "FACTORY_NO"));
        params.put("FACTORY_NAME", ParamUtil.get(request, "FACTORY_NAME"));
        params.put("PERSON_CON", ParamUtil.get(request, "PERSON_CON"));
        params.put("MOBILE_NO", ParamUtil.get(request, "MOBILE_NO"));
        params.put("CRE_TIME_FROM", ParamUtil.get(request, "CRE_TIME_FROM"));
        params.put("CRE_TIME_TO", ParamUtil.get(request, "CRE_TIME_TO"));
        params.put("STATE", ParamUtil.get(request, "STATE"));
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
                
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

        // 字段排序
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = factoryService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
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
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

        String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");
        if (StringUtils.isNotEmpty(FACTORY_ID)) {
            Map entry = factoryService.load(FACTORY_ID);
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
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

        String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");
        if (StringUtils.isNotEmpty(FACTORY_ID)) {
            Map entry = factoryService.load(FACTORY_ID);
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

        try {
        	
            String jsonData = ParamUtil.get(request, "jsonData");
            FactoryModel model = null;
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <FactoryModel>() {
                }.getType());
            }

            String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");        
            
            //判断生产工厂编号是否已经存在

            if (StringUtils.isEmpty(FACTORY_ID) && ( factoryService.getScjdExits(model.getFACTORY_NO()) > 0)) {
                jsonResult = jsonResult(false, "生产工厂编号已经存在");
            }
            else
            {
            	factoryService.txEdit(FACTORY_ID, model, userBean);
            }
            
            
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
     * 删除.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");

        if (StringUtils.isNotEmpty(FACTORY_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("FACTORY_ID", FACTORY_ID);
                
                factoryService.txDelete(FACTORY_ID, userBean);

            } catch (Exception ex) {
                ex.printStackTrace();
                jsonResult = jsonResult(false, "删除失败");
            }
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
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        boolean isChanged = false;
        
        String FACTORY_ID = request.getParameter("FACTORY_ID");

        Map <String, String> entry = factoryService.load(FACTORY_ID);

        String state = entry.get("STATE");
        Map <String, String> params = new HashMap <String, String>();
        
        try {
            String changedState = "";
            params.put("FACTORY_ID", FACTORY_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人ID
            params.put("UPD_NAME", userBean.getXM());//更新人名称

            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {  
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);               
                factoryService.txUpdateById(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state)) {             
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
                factoryService.txUpdateById(params);
                isChanged = true;
            } else {
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
                factoryService.txUpdateById(params);
                isChanged = true;
            }
            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
                jsonResult = jsonResult(false, "状态不用修改");
            }
        } catch (RuntimeException e) {
            jsonResult = jsonResult(false, "状态修改失败");
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
	  * 生产货品类型->列表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public ActionForward toProductList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	 {
		 UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		 String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");
		 
        if (StringUtils.isNotEmpty(FACTORY_ID)) {
            List <FactoryProductModel> result = factoryService.productQuery(FACTORY_ID);
            request.setAttribute("rst", result);
        }
        
	     //为空直接跳转显示页面，而不是错误提示。		 
		 return mapping.findForward("toProductList");
		 
	 }
	 
	 /**
     * 生产货品类型->编辑跳转
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toProductEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        //多个Id批量查询，用逗号隔开
        String FACTORY_P_IDS = request.getParameter("FACTORY_PRD_IDS");
        String chistate = ParamUtil.utf8Decoder(request, "chistate");
        //没有零星领料Id可以直接跳转。
        if (StringUtils.isNotEmpty(FACTORY_P_IDS)) {
            List <FactoryProductModel> list = factoryService.loadProducts(FACTORY_P_IDS);
            request.setAttribute("rst", list);

        }
        request.setAttribute("chistate", chistate);
        return mapping.findForward("toProductEdit");
    }
    /**
     * 生产货品类型->批量删除
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void productDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String FACTORY_PRD_IDS = request.getParameter("FACTORY_PRD_IDS");
            
            String[] FACTORY_P_ID_arr = StringUtil.toArr(FACTORY_PRD_IDS); 
            
          //修改列表
            List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
            for (String FACTORY_P_ID : FACTORY_P_ID_arr) {
                Map <String, String> params = new HashMap <String, String>();
                params.put("FACTORY_PRD_ID", FACTORY_P_ID);
                params.put("DEL_FLAG", "1");
                updateList.add(params);
            }
            
            factoryService.txProductDelete(updateList);
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
     * 生产货品类型->编辑
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void productEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

         PrintWriter writer = getWriter(response);
         String jsonResult = jsonResult();
         
         boolean errorFlg = false;
         try {
         	String FACTORY_ID = ParamUtil.get(request, "FACTORY_ID");

             String jsonDate = request.getParameter("productJsonData");
             if (StringUtils.isNotEmpty(jsonDate)) {
                 List <FactoryProductModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <FactoryProductModel>>() {
                 }.getType());
                 
	             //明细去重复判断
	             List <FactoryProductModel> result = factoryService.productQuery(FACTORY_ID);
	             
	             breakLable:
	             for(FactoryProductModel editMode: modelList)
	             {
	            	 for(FactoryProductModel existMode: result){
	            		 
	            		 if(StringUtils.isEmpty(editMode.getFACTORY_PRD_ID())&&editMode.getPRD_NO().equals(existMode.getPRD_NO()))
	            		 {
	            			 errorFlg = true;
	            			 jsonResult = jsonResult(false, "货品类型编号:"+editMode.getPRD_ID()+"的数据重复,保存失败!");
	            			 break breakLable;
	            		 }
	            	 }
	             }
	             
	             if(!errorFlg)
	             {
	            	 factoryService.txProductEdit(FACTORY_ID, modelList);
	             }
             }
         } catch (Exception e) {
        	 e.printStackTrace();
             jsonResult = jsonResult(false, "保存失败");
         }
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
    }
	    
}

