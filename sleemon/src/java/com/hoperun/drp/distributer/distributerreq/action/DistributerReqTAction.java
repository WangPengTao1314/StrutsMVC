package com.hoperun.drp.distributer.distributerreq.action;

import java.io.PrintWriter;
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
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.distributer.distributerreq.model.DistributerReqModel;
import com.hoperun.drp.distributer.distributerreq.service.DistributerReqService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 */
public class DistributerReqTAction  extends BaseAction {

   
	//增删改查
    private static String PVG_BWS="FX0060401";
    private static String PVG_EDIT="FX0060402";
    private static String PVG_DELETE="FX0060403";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE="FX0060404";
    //流程跟踪
    private static String PVG_TRACE= "FX0060405";
    //审核模块                             
    private static String PVG_BWS_AUDIT="FX0060501";
    private static String PVG_AUDIT_AUDIT="FX0060502";
    private static String PVG_TRACE_AUDIT="FX0060503";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_DISTRIBUTOR_REQ";
    private static String AUD_TAB_KEY="DISTRIBUTOR_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_DISTRIBUTOR_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.distributer.distributerreq.service.impl.DistributerReqFlowInterface";
	
	private   DistributerReqService   distributerReqService;
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		//设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("module", ParamUtil.utf8Decoder(request, "module"));
	    return mapping.findForward("frames");
	}
	
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		  //权限级别
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		Map<String, String> params = new HashMap<String, String>();
				
		ParamUtil.putStr2Map(request, "DISTRIBUTOR_REQ_ID", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_REQ_NO", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NAME", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_TYPE", params);
    	ParamUtil.putStr2Map(request, "AREA_NAME_P", params);
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
    	ParamUtil.putStr2Map(request, "PROV", params);
    	ParamUtil.putStr2Map(request, "CITY", params);
    	ParamUtil.putStr2Map(request, "COUNTY", params);
    	ParamUtil.putStr2Map(request, "PERSON_CON", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE  = ParamUtil.get(request, "STATE");
		if("".equals(module)){
			module = "wh";
		}
		
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
	    StringBuffer sb = new StringBuffer();
	    sb.append(StringUtil.getStrQX("u",strQx.toString()));
	    //params.put("QXJBCON", sb.toString());
	    //字段排序
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    ParamUtil.putStr2Map(request, "pageSize", params);
	    
    	if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
				strQx.append(" and STATE in('未提交','撤销','否决') ");
			}
		}
		if(module.equals("sh")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}else{
					strQx.append(" and STATE in('提交','撤销','否决','审核通过') ");
				}
			}else{
				strQx.append(" and STATE='提交' ");
			}
		}
		if(module.equals("wh")){
			params.put("CREATOR", userBean.getRYXXID());
		}else{
//			params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
			params.put("CHANN_ID", userBean.getCHANN_ID());
		}
		params.put("QXJBCON",strQx.toString());
    	IListPage page = distributerReqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
    	request.setAttribute("module", module);
        request.setAttribute("page", page);
        return mapping.findForward("list");
	}
	
	/**
	 * 编辑框架页面.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toEditFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("editFrame");
	}
	
	/**
	 * * to 编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String DISTRIBUTOR_REQ_ID = ParamUtil.get(request, "selRowId");
		if(DISTRIBUTOR_REQ_ID.equals("undefined") ){
			DISTRIBUTOR_REQ_ID = "";
		}
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(DISTRIBUTOR_REQ_ID)){
			entry = distributerReqService.load(DISTRIBUTOR_REQ_ID);
		}else{
			ChannModel  chann  =  distributerReqService.getChannInfo(userBean.getCHANN_ID().toString());
			if(chann !=null){
				entry.put("CHANN_ID", chann.getCHANN_ID());
				entry.put("CHANN_NO", chann.getCHANN_NO());
				entry.put("CHANN_NAME", chann.getCHANN_NAME());
				entry.put("AREA_NAME_P", chann.getAREA_NAME_P());
				entry.put("AREA_MANAGE_ID", chann.getAREA_MANAGE_ID());
				entry.put("AREA_MANAGE_NAME", chann.getAREA_MANAGE_NAME());
				entry.put("AREA_MANAGE_TEL", chann.getAREA_MANAGE_TEL());
				entry.put("PROV", chann.getPROV());
				entry.put("CITY", chann.getCITY());
				entry.put("COUNTY", chann.getCOUNTY());
				entry.put("AREA_ID", chann.getAREA_ID());
				entry.put("AREA_NO", chann.getAREA_NO());
				entry.put("AREA_NAME",chann.getAREA_NAME());
				entry.put("ZONE_ID", chann.getZONE_ID());
			}
		}
		request.setAttribute("nFlag", checkDistributerType(entry));		
		request.setAttribute("userName", userBean.getXM());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
	}
	 
	/**
	 * 编辑.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);        
        String jsonResult = jsonResult();
        try{
        	String jsonData = ParamUtil.get(request, "jsonData");
        	DistributerReqModel model = new DistributerReqModel();
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, 
                		new TypeToken <DistributerReqModel>() {}.getType());
            }
            distributerReqService.txEdit(model,userBean);
            jsonResult = jsonResult(true, "保存成功");
            
        }catch(Exception e){
        	jsonResult = jsonResult(false, "保存失败");
        	e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 编辑框架页面.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetailFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("detailFrame");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG
					&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
							.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String DISTRIBUTOR_REQ_ID = ParamUtil.get(request, "selRowId");
			
			Map<String,String> entry = distributerReqService.load(DISTRIBUTOR_REQ_ID);
			request.setAttribute("rst", entry);			
			request.setAttribute("nFlag", checkDistributerType(entry));
			
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(DISTRIBUTOR_REQ_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
		    
			return mapping.findForward("detail");
	}
	
	 /**
     * 删除.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String DISTRIBUTOR_REQ_ID = ParamUtil.get(request, "DISTRIBUTOR_REQ_ID");

        if (StringUtils.isNotEmpty(DISTRIBUTOR_REQ_ID)) {
            try {
            	distributerReqService.txDelete(DISTRIBUTOR_REQ_ID, userBean);            	
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
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
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 
	/**
	 * @return
	 */
	public DistributerReqService getDistributerReqService() {
		return distributerReqService;
	}

	/**
	 * @param distributerReqService
	 */
	public void setDistributerReqService(DistributerReqService distributerReqService) {
		this.distributerReqService = distributerReqService;
	}
	
	@SuppressWarnings("unused")
	private boolean checkDistributerType(Map<String,String> entry){
		boolean nFlag = false;
		
		if(entry == null || entry.size() ==0){
			return false;
		}
		
		String DISTRIBUTOR_TYPE = entry.get("DISTRIBUTOR_TYPE");
		if(!StringUtil.isEmpty(DISTRIBUTOR_TYPE) && DISTRIBUTOR_TYPE.indexOf("1+N") >= 0){
			nFlag = true;
		}
		
		return nFlag;
	}
}
