package com.hoperun.drp.oamg.channscoremkm.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmDtlModel;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmModel;
import com.hoperun.drp.oamg.channscoremkm.model.ScoreStandModel;
import com.hoperun.drp.oamg.channscoremkm.service.ChannScoreMkmService;
import com.hoperun.sys.model.UserBean;

public class ChannScoreMkmAction extends BaseAction {
	   
    //增删改查
    private static String PVG_BWS="FX0060301";
    private static String PVG_EDIT="FX0060302";
    private static String PVG_DELETE="FX0060303";
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0060304";
    //流程跟踪
    private static String PVG_TRACE= "FX0060305";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0034501";
    private static String PVG_AUDIT_AUDIT="BS0034502";
    private static String PVG_TRACE_AUDIT="BS0034503";
	//审批流参数
    private static String AUD_TAB_NAME="DRP_CHANN_SCORE_MKM";
    private static String AUD_TAB_KEY="CHANN_SCORE_MKM_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_CHANN_SCORE_MKM_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private  ChannScoreMkmService  channScoreMkmService;
   
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
		request.setAttribute("module",  ParamUtil.get(request,"module"));
		return mapping.findForward("frames");
	}
	
	/**
     * 加盟商拜访维护列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

         //权限级别
         UserBean userBean = ParamUtil.getUserBean(request);
     	 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
         }
         Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "CHANN_SCORE_MKM_NO", params);
         ParamUtil.putStr2Map(request, "MKM_NAME", params);
         ParamUtil.putStr2Map(request, "SCORE_NAME", params);
         ParamUtil.putStr2Map(request, "SCORE_DATE_BEG", params);
         ParamUtil.putStr2Map(request, "SCORE_DATE_END", params);
         ParamUtil.putStr2Map(request, "STATE", params);
         ParamUtil.putStr2Map(request, "pageSize", params);
         
         String search = ParamUtil.get(request,"search");
 	     String module = ParamUtil.get(request,"module");
 	     String STATE = ParamUtil.get(request,"STATE");
		 StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		 //权限级别和审批流的封装
		 if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
			   qx.append(" and STATE in('未提交','撤销','否决') ");
			}
		 }
		 if(module.equals("sh")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}else{
					qx.append(" and STATE in('提交','撤销','否决','审核通过') ");
				}
			}else{
				qx.append(" and STATE='提交' ");
			}
		 }
	     params.put("QXJBCON",qx.toString());
	     IListPage page = null;
	     request.setAttribute("module",module);
	     //渠道分管
	     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	     params.put("CHANNS_CHARG", CHANNS_CHARG);
	     params.put("AREA_CHARG", userBean.getAREA_CHARG());
			
	     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
 		 page = channScoreMkmService.pageQuery(params, pageNo);
	     
 		 request.setAttribute("pvg",setPvg(userBean));
 		 request.setAttribute("module", module);
 		 request.setAttribute("search", search);
         request.setAttribute("params", params);
         request.setAttribute("page", page);  
         return mapping.findForward("list");
    }
    
    /**
     * 加盟商拜访表编辑框架页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String EDIT_FLAG = ParamUtil.get(request, "EDIT_FLAG");
		request.setAttribute("EDIT_FLAG", EDIT_FLAG);
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("editFrame");
    }
    
    /**
     * 加盟商拜访表维护修改页面跳转 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String CHANN_SCORE_MKM_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		
		if(!StringUtil.isEmpty(CHANN_SCORE_MKM_ID)){
			entry   =  channScoreMkmService.load(CHANN_SCORE_MKM_ID);
			List<String> list =  channScoreMkmService.loadbydtl(CHANN_SCORE_MKM_ID);
			List<String> li  =   channScoreMkmService.queryOptionScore(CHANN_SCORE_MKM_ID);
			for(int i=0;i<li.size();i++){
				entry.put("SCORE_DESC"+(i+1), list.get(i));
				entry.put("OPTION_SCORE"+(i+1), li.get(i));
			}
		} else{
			
			Map<String,String> entryT = channScoreMkmService.qeryMkm(userBean.getCHANN_ID());
			entry = new HashMap<String,String>();
			entry.put("STATE", BusinessConsts.UNCOMMIT);
			entry.put("SCORE_ID", userBean.getRYXXID());
			entry.put("SCORE_NAME", userBean.getXM());
			entry.put("SCORE_DATE", DateUtil.format(new Date(), "yyyy-MM-dd"));
			entry.put("MKM_ID", entryT.get("AREA_MANAGE_ID"));
			entry.put("MKM_NAME", entryT.get("AREA_MANAGE_NAME"));
		}
		request.setAttribute("userName", userBean.getXM());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
    }
    
    /**
     * 加盟商拜访表详细信息 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
    	String CHANN_SCORE_MKM_ID = ParamUtil.get(request, "CHANN_SCORE_MKM_ID");
		if (StringUtils.isNotEmpty(CHANN_SCORE_MKM_ID)) {
			Map entry   =  channScoreMkmService.load(CHANN_SCORE_MKM_ID);
			List<String> list =  channScoreMkmService.loadbydtl(CHANN_SCORE_MKM_ID);
			List<String> li  =   channScoreMkmService.queryOptionScore(CHANN_SCORE_MKM_ID);
			for(int i=0;i<li.size();i++){
				entry.put("SCORE_DESC"+(i+1), list.get(i));
				entry.put("OPTION_SCORE"+(i+1), li.get(i));
			}
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(CHANN_SCORE_MKM_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
		}
        return mapping.findForward("todetail");
    }
    
    /**
     * 加盟商拜访表维护编辑//新增或修改。 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    @SuppressWarnings("unchecked")
	public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
	    UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		String CHANN_SCORE_MKM_ID = ParamUtil.get(request, "CHANN_SCORE_MKM_ID");

		ChannScoreMkmModel    scoreMkm   = new ChannScoreMkmModel();    //加盟商营销经理评价
		ScoreStandModel       scoreStand = new ScoreStandModel();       //评分标准
		ChannScoreMkmDtlModel scoreMkmdtl= new ChannScoreMkmDtlModel(); //加盟商营销经理详细
		
		if (StringUtils.isNotEmpty(jsonData)) { 
			scoreMkm = new Gson().fromJson(jsonData,
					new TypeToken<ChannScoreMkmModel>() {
					}.getType());
			scoreStand= new Gson().fromJson(jsonData,
					new TypeToken<ScoreStandModel>() {
			}.getType());
			scoreMkmdtl= new Gson().fromJson(jsonData,
					new TypeToken<ChannScoreMkmDtlModel>() {
			}.getType());
		}
		try {
			channScoreMkmService.txEdit(CHANN_SCORE_MKM_ID, scoreMkm,scoreStand,scoreMkmdtl,userBean);
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
     * 营销经理日报删除 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_SCORE_MKM_ID = ParamUtil.get(request, "CHANN_SCORE_MKM_ID");

        if (StringUtils.isNotEmpty(CHANN_SCORE_MKM_ID)) {
            try {
            	channScoreMkmService.txDelete(CHANN_SCORE_MKM_ID, userBean);
            	channScoreMkmService.clearCaches(CHANN_SCORE_MKM_ID);
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

	public ChannScoreMkmService getChannScoreMkmService() {
		return channScoreMkmService;
	}

	public void setChannScoreMkmService(ChannScoreMkmService channScoreMkmService) {
		this.channScoreMkmService = channScoreMkmService;
	}
}
