package com.hoperun.drp.oamg.advreit.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.advreit.model.AdvreitModel;
import com.hoperun.drp.oamg.advreit.service.AdvreitService;
import com.hoperun.sys.model.UserBean;

public class AdvreitAction extends BaseAction {
      
		private   AdvreitService   advreitService;
	
		//增删改查
	    private static String PVG_BWS="BS0021201";
	    private static String PVG_EDIT="BS0021202";
	    private static String PVG_DELETE="BS0021203";
	    
	    //提交撤销
	    private static String PVG_COMMIT_CANCLE="BS0021204";
	    //流程跟踪
	    private static String PVG_TRACE= "BS0021205";
	    //审核模块                             
	    private static String PVG_BWS_AUDIT="BS0020801";
	    private static String PVG_AUDIT_AUDIT="BS0020802";
	    private static String PVG_TRACE_AUDIT="BS0020803";
		
		//审批流参数
	    private static String AUD_TAB_NAME="ERP_ADV_REQ_REIT";
	    private static String AUD_TAB_KEY="ERP_ADV_REIT_ID";
		private static String AUD_BUSS_STATE="STATE";
	    private static String AUD_BUSS_TYPE="ERP_ADV_REQ_REIT_AUDIT";
		private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
		
		
		public AdvreitService getAdvreitService() {
			return advreitService;
		}
	
		public void setAdvreitService(AdvreitService advreitService) {
			this.advreitService = advreitService;
		}
		
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
			 return mapping.findForward("frames");
		}
		
		/**
		 * *query List data
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
	        UserBean userBean = ParamUtil.getUserBean(request);
			
	        Map<String,String> params = new HashMap<String,String>();
	        
		    ParamUtil.putStr2Map(request, "ERP_ADV_REIT_NO", params);
			ParamUtil.putStr2Map(request, "ERP_ADV_REQ_NO", params);
		    ParamUtil.putStr2Map(request, "CHANN_NO", params);
		    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		    ParamUtil.putStr2Map(request, "AREA_NAME", params);
		    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
		    ParamUtil.putStr2Map(request, "RNVTN_REQ_NAME", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
		    
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
		    
		    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		    ParamUtil.putStr2Map(request, "pageSize", params);
		    page =  advreitService.pageQuery(params, pageNo);
			request.setAttribute("params", params);
			request.setAttribute("module", module);
			request.setAttribute("page", page);
			request.setAttribute("pvg",setPvg(userBean));
			return mapping.findForward("list");
		}
		
		/**
		 * 新增.
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toEdit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			UserBean userBean = ParamUtil.getUserBean(request);
			PrintWriter writer = getWriter(response);
		    String jsonResult = jsonResult();
		    
		    String currentTime = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
		    String userName  = userBean.getXM();                        //申请人
		    String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
 
		    Map<String,String> params = new HashMap<String,String>();
		    String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		    String time = sdf.format(new Date());
		    request.setAttribute("currentTime",currentTime);
		    request.setAttribute("userName",userName);
		    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		    request.setAttribute("date", time);
		    request.setAttribute("state", "未提交");
			return mapping.findForward("toedit");
		}
		
		
		
		public void getReitAmount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
			
			 UserBean userBean = ParamUtil.getUserBean(request);
		     PrintWriter writer = getWriter(response);
		     String jsonResult = jsonResult();
			 String ERP_ADV_REQ_ID    = ParamUtil.get(request, "ERP_ADV_REQ_ID");
			 String reitAmount = "";
			 if (StringUtils.isNotEmpty(ERP_ADV_REQ_ID)) {
				 reitAmount = advreitService.queryReitAmount(ERP_ADV_REQ_ID);
			 }
			 jsonResult = jsonResult(true,reitAmount);
		     if (null != writer) {
		            writer.write(jsonResult);
		            writer.close();
		     }
		}
		
		public void toAudit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
			 UserBean userBean = ParamUtil.getUserBean(request);
		     PrintWriter writer = getWriter(response);
		     String jsonResult = jsonResult();
			 String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
		     if (null != writer) {
		            writer.write(jsonResult);
		            writer.close();
		        }
		}
		
		/**
		 * 新增.
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toEdit1(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			UserBean userBean = ParamUtil.getUserBean(request);
			PrintWriter writer = getWriter(response);
		    String jsonResult = jsonResult();
		    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		    String userName  = userBean.getXM();  //申请人
		    Map<String,String> params = new HashMap<String,String>();
		    Map<String,String> paramsT = new HashMap<String,String>();
		    String ERP_ADV_REIT_ID    = ParamUtil.get(request, "ERP_ADV_REIT_ID");

		    String search = ParamUtil.get(request,"search");
		    String module    = ParamUtil.get(request, "module");
		  
 		    IListPage page   = null;
		    IListPage pageT  = null;
		    if (StringUtils.isNotEmpty(ERP_ADV_REIT_ID)) {
				Map entry = advreitService.loadByConfId(ERP_ADV_REIT_ID);
				request.setAttribute("rst", entry);
				request.setAttribute("ERP_ADV_REIT_ID", ERP_ADV_REIT_ID);
				
				String ERP_ADV_REQ_ID = entry.get("ERP_ADV_REQ_ID").toString();
				Map entryT = advreitService.loadPath(ERP_ADV_REQ_ID);
				request.setAttribute("rstT", entryT);
			}
			//权限级别和审批流的封装和状态过滤
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		    String time = sdf.format(new Date());
		    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		    request.setAttribute("userName",userName);
		    request.setAttribute("date", time);
		    request.setAttribute("state", "未提交");
		    request.setAttribute("page", page);
		    request.setAttribute("pvg",setPvg(userBean));
			return mapping.findForward("toeditT");
		}
		
		
		/**
		 * 修改.
		 * @param mapping the mapping
		 * @param form  the form
		 * @param request  the request
		 * @param response the response
		 */
		public void edit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
	         
			UserBean userBean = ParamUtil.getUserBean(request);
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String jsonData       = ParamUtil.get(request, "jsonData");
	        String ERP_ADV_REIT_ID = ParamUtil.get(request, "ERP_ADV_REIT_ID");
	        
	        AdvreitModel model = new AdvreitModel();
	        if (StringUtils.isNotEmpty(jsonData)) {
	            model = new Gson().fromJson(jsonData, new TypeToken <AdvreitModel>() {
	            }.getType());
	        }
	        advreitService.txEdit(ERP_ADV_REIT_ID, model, userBean); 
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
		}
		
		public ActionForward toDetail(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
				String ERP_ADV_REIT_ID = ParamUtil.get(request, "ERP_ADV_REIT_ID");
				if (StringUtils.isNotEmpty(ERP_ADV_REIT_ID)) {
					Map entry = advreitService.loadByConfId(ERP_ADV_REIT_ID);
					request.setAttribute("rst", entry);
					String ERP_ADV_REQ_ID = entry.get("ERP_ADV_REQ_ID").toString();
					Map entryT = advreitService.loadPath(ERP_ADV_REQ_ID);
					request.setAttribute("rstT", entryT);
					
					List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(ERP_ADV_REIT_ID);
					request.setAttribute("flowInfoList", flowInfoList);
					
				}
				return mapping.findForward("detail");
		}
		
		 /**
	     * 删除.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	UserBean userBean = ParamUtil.getUserBean(request);
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String ERP_ADV_REIT_ID = ParamUtil.get(request, "ERP_ADV_REIT_ID");

	        if (StringUtils.isNotEmpty(ERP_ADV_REIT_ID)) {
	            try {
	                advreitService.txDelete(ERP_ADV_REIT_ID, userBean);
	                advreitService.clearCaches(ERP_ADV_REIT_ID);
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
}
