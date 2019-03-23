/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：ShipPointAction.java
 */
package com.hoperun.base.shipPoint.action;

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
import com.hoperun.base.shipPoint.model.ShipPointModel;
import com.hoperun.base.shipPoint.service.ShipPointService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ShipPointAction.
 * 
 * @module 系统管理
 * @func 发货点维护
 * @version 1.0
 * @author 王志格
 */
public class ShipPointAction extends BaseAction{
	
	/**
	 * 发货点service
	 */
	private ShipPointService shipPointService;
	
	/**
	 * 设置发货点service
	 * @param prodbService
	 */
	public void setShipPointService(ShipPointService shipPointService) {
		this.shipPointService = shipPointService;
	}
	
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="XT0012701";
	private static String PVG_EDIT="XT0012702";
    private static String PVG_DELETE="XT0012703";
    //启用,停用
    private static String PVG_START_STOP="XT0012704";
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
        
        params.put("SHIP_POINT_ID", ParamUtil.get(request, "SHIP_POINT_ID"));
        params.put("SHIP_POINT_NO", ParamUtil.get(request, "SHIP_POINT_NO"));
        params.put("SHIP_POINT_NAME", ParamUtil.get(request, "SHIP_POINT_NAME"));
        params.put("CRE_TIME_FROM", ParamUtil.get(request, "CRE_TIME_FROM"));
        params.put("CRE_TIME_TO", ParamUtil.get(request, "CRE_TIME_TO"));
        params.put("STATE", ParamUtil.get(request, "STATE"));
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

        // 字段排序
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = shipPointService.pageQuery(params, pageNo);
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

        String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");
        
        if (StringUtils.isNotEmpty(SHIP_POINT_ID)) {
            Map entry = shipPointService.load(SHIP_POINT_ID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("view");
    }
    /**
     * 编辑跳转.
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

        String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");
        
        if (StringUtils.isNotEmpty(SHIP_POINT_ID)) {
            Map entry = shipPointService.load(SHIP_POINT_ID);
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
            ShipPointModel model = null;
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <ShipPointModel>() {
                }.getType());
            }

            String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");        
            
            //判断发货点编号是否已经存在
            
            if (StringUtils.isEmpty(SHIP_POINT_ID) && ( shipPointService.getShipExits(model.getSHIP_POINT_NO()) > 0)) {
                jsonResult = jsonResult(false, "发货点编号已经存在");
            }
            else
            {
            	SHIP_POINT_ID = shipPointService.txEdit(SHIP_POINT_ID, model, userBean);
            	jsonResult = jsonResult(true, SHIP_POINT_ID);
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
        
        String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");

        if (StringUtils.isNotEmpty(SHIP_POINT_ID)) {
            try {            
                shipPointService.txDelete(SHIP_POINT_ID, userBean);
                
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
        
        String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");

        Map <String, String> entry = shipPointService.load(SHIP_POINT_ID);

        String state = entry.get("STATE");
        Map <String, String> params = new HashMap <String, String>();
        
        try {
            String changedState = "";
            params.put("SHIP_POINT_ID", SHIP_POINT_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人ID
            params.put("UPD_NAME", userBean.getXM());//更新人名称

            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {  
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);               
                shipPointService.txUpdateById(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state)) {             
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
                shipPointService.txUpdateById(params);
                isChanged = true;
            } else {
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
                shipPointService.txUpdateById(params);
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
 
}


