package com.hoperun.drp.oamg.workplanmage.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmageModel;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmagedtlModel;
import com.hoperun.drp.oamg.workplanmage.service.WorkplanMageService;
import com.hoperun.sys.model.UserBean;

public class WorkplanmageAction extends BaseAction  {

	//增删改查
    private static String PVG_BWS="BS0032501";
    private static String PVG_EDIT="BS0032502";
    private static String PVG_DELETE="BS0032503";
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032504";
    //流程跟踪
    private static String PVG_TRACE= "BS0032505";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0032601";
    private static String PVG_AUDIT_AUDIT="BS0032602";
    private static String PVG_TRACE_AUDIT="BS0032603";
    private static String PVG_EDIT_AUDIT ="BS0032604";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_WORK_PLAN";
    private static String AUD_TAB_KEY="WORK_PLAN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_WORK_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private    WorkplanMageService    workplanmageService;
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
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
         
         Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "WORK_PLAN_NO", params);
         ParamUtil.putStr2Map(request, "WAREA_NO", params);
         ParamUtil.putStr2Map(request, "WAREA_NAME", params);
         ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
         ParamUtil.putStr2Map(request, "PLAN_MONTH", params);
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
	     //params.put("CHANNS_CHARG", CHANNS_CHARG);
			
	     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
 		 page = workplanmageService.pageQuery(params, pageNo);
 		 
 		 List list  = new  ArrayList();
    	 List list1 = new  ArrayList();
         for(int i=1;i<13;i++){
       	  list.add(i);
         }
         request.setAttribute("list", list);
         for(int j=2010;j<2031;j++){
       	  list1.add(j);
         }
         request.setAttribute("list1", list1);
         
 		 request.setAttribute("pvg",setPvg(userBean));
 		 request.setAttribute("module", module);
         request.setAttribute("params", params);
         request.setAttribute("page", page);  
         request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
         request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
         request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
         return mapping.findForward("list");
    }
	
    /**
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

       String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");
	   if(!WORK_PLAN_ID.equals("")){
	   	    request.setAttribute("WORK_PLAN_ID", WORK_PLAN_ID);
	   	    return mapping.findForward("editFrame_T");
	   } else {
	        return mapping.findForward("editFrame");
	   }
    }
    
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 UserBean userBean = ParamUtil.getUserBean(request);
    	 String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");
    	 if (StringUtils.isNotEmpty(WORK_PLAN_ID)) {
 			Map entry = workplanmageService.load(WORK_PLAN_ID);
 			request.setAttribute("rst", entry);
 		 }
    	 request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	 request.setAttribute("WORK_PLAN_ID",WORK_PLAN_ID);
    	 List list  = new  ArrayList();
    	 List list1 = new  ArrayList();
         for(int i=1;i<13;i++){
       	  list.add(i);
         }
         request.setAttribute("list", list);
         for(int j=2010;j<2031;j++){
       	  list1.add(j);
         }
         request.setAttribute("list1", list1);
    	 return mapping.findForward("toeditT");
    }
    
	 /**
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	 public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 
   	    UserBean userBean = ParamUtil.getUserBean(request);
        String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request, "selRowId"));//ParamUtil.utf8Decoder(request, "BMGZID");//;
        String WORK_PLAN_ID = StringUtil.utf8Decoder(ParamUtil.get(request, "WORK_PLAN_ID"));
        // 为空则是新增，否则是修改
        if (StringUtils.isNotEmpty(WORK_PLAN_ID)) {
            Map <String, String> entry =  workplanmageService.load(WORK_PLAN_ID);
            request.setAttribute("rst", entry);
            request.setAttribute("isNew", false);
        }  else {
       	 String userName  = userBean.getXM();                      //申请人
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     	     String time = sdf.format(new Date());                     //制单日期
        	 request.setAttribute("CRE_NAME",userName);
        	 request.setAttribute("CRE_TIME", time);
        }
          List list  = new  ArrayList();
          List list1 = new  ArrayList();
          for(int i=1;i<13;i++){
        	  list.add(i);
          }
          request.setAttribute("list", list);
          Date  date   = new Date();
          String month = String.valueOf(date.getMonth()+1);
          String year  = String.valueOf(date.getYear()+1900);
          for(int j=2010;j<2031;j++){
        	  list1.add(j);
          }
          request.setAttribute("list1", list1);
          request.setAttribute("year", year);
          request.setAttribute("month", month);
          return mapping.findForward("toedit");
       }
	 
	 
	 /**
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return
	     */
     public ActionForward modifyToChildEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	String WORK_PLAN_ID = ParamUtil.utf8Decoder(request, "WORK_PLAN_ID");//ParamUtil.get(request, "BMGZID");
	        if (StringUtils.isNotEmpty(WORK_PLAN_ID)) {
	            List<WorkplanmagedtlModel> result = workplanmageService.childQuery(WORK_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("WORK_PLAN_ID",WORK_PLAN_ID);
	        return mapping.findForward("childeditT");
	    }
	 
	    /**
	     * 工作计划维护编辑页面加载子页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	String WORK_PLAN_ID = ParamUtil.utf8Decoder(request, "WORK_PLAN_ID");//ParamUtil.get(request, "BMGZID");
	        if (StringUtils.isNotEmpty(WORK_PLAN_ID)) {
	            List<WorkplanmagedtlModel> result = workplanmageService.childQuery(WORK_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("WORK_PLAN_ID",WORK_PLAN_ID);
	        return mapping.findForward("childedit");
	    }
	
	    /**
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response 
	     * @return
	     */
		public ActionForward childList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");
			if (!StringUtil.isEmpty(WORK_PLAN_ID)) {
				List<WorkplanmagedtlModel> result = workplanmageService.childQuery(WORK_PLAN_ID);
				if (null != result) {
					request.setAttribute("resultSize", result.size());
				}
				request.setAttribute("rst", result);
			}
			request.setAttribute("pvg", setPvg(userBean));
			request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
			request.setAttribute("WORK_PLAN_ID", WORK_PLAN_ID);
			return mapping.findForward("childlist");
		}  
		
		public ActionForward childList2(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");   //工作计划ID
			String WAREA_ID     = ParamUtil.get(request, "WAREA_ID");       //战区ID
			List<WorkplanmagedtlModel>  result =  workplanmageService.queryJudgeModel(WAREA_ID);
			request.setAttribute("rst", result);
			request.setAttribute("pvg", setPvg(userBean));
			request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
			request.setAttribute("WORK_PLAN_ID", WORK_PLAN_ID);
			if(!WORK_PLAN_ID.equals("自动生成") && !WAREA_ID.equals("")){
                workplanmageService.txDeleteChildT(WORK_PLAN_ID,userBean);				
			}
			return mapping.findForward("childedit");
		}   

		/**
		 * * to 详细信息
		 * @param mapping  the mapping
		 * @param form the form
		 * @param request  the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toDetail(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG
					&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
							userBean, PVG_BWS_AUDIT))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");
			if (!StringUtil.isEmpty(WORK_PLAN_ID)) {
				Map<String, String> entry = workplanmageService
						.load(WORK_PLAN_ID);
				request.setAttribute("rst", entry);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(WORK_PLAN_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
			return mapping.findForward("detail");
		}
		
     /**
      * @param mapping
      * @param form
      * @param request
      * @param response
      */
	 public void loadRYXX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		//修改权限和门店建设部设计师审核  
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String WAREA_ID = ParamUtil.get(request, "WAREA_ID");
             List<WorkplanmagedtlModel>  result =  workplanmageService.queryJudgeModel(WAREA_ID);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	jsonResult = jsonResult(false, "保存失败");
		}
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	 
	 
	   /**
	     * 工作计划维护编辑//新增或修改。 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    @SuppressWarnings("unchecked")
		public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	         PrintWriter writer = getWriter(response);
	         String jsonResult = jsonResult();
	         String parentJsonData = ParamUtil.get(request, "parentJsonData");
	         
	         UserBean userBean = ParamUtil.getUserBean(request);
	         String WORK_PLAN_ID = ParamUtil.utf8Decoder(request, "WORK_PLAN_ID");  
	         WorkplanmageModel model = new WorkplanmageModel();
	         if (StringUtils.isNotEmpty(parentJsonData)) {
	             model = new Gson().fromJson(parentJsonData, new TypeToken <WorkplanmageModel>() {
	             }.getType());
	         }
	         String jsonData = ParamUtil.get(request, "childJsonData");
	         List <WorkplanmagedtlModel>  mxList = null;
	         if (StringUtils.isNotEmpty(jsonData)) {
	             mxList = new Gson().fromJson(jsonData, new TypeToken <List<WorkplanmagedtlModel>>() {
	             }.getType());
	         }
	         String str = workplanmageService.txEdit(WORK_PLAN_ID, model, userBean,mxList);
	         if(str.equals("1")){
	        	 jsonResult = jsonResult(false, "人员信息不能重复");
	         }
	         if (null != writer) {
	             writer.write(jsonResult);
	             writer.close();
	         }
	    } 
	    
	    /**
	     * 工作计划维护删除 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	      
	    	UserBean userBean = ParamUtil.getUserBean(request);
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String WORK_PLAN_ID = ParamUtil.get(request, "WORK_PLAN_ID");

	        if (StringUtils.isNotEmpty(WORK_PLAN_ID)) {
	            try {
	                workplanmageService.txDelete(WORK_PLAN_ID, userBean);
	                workplanmageService.clearCaches(WORK_PLAN_ID);
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
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	 
	    	// 多个Id批量查询，用逗号隔开
	        String WORK_PLAN_DTL_IDS = request.getParameter("WORK_PLAN_DTL_IDS");
	        String WORK_PLAN_ID      = request.getParameter("WORK_PLAN_ID");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(WORK_PLAN_DTL_IDS)) {
	            List <WorkplanmagedtlModel> list = workplanmageService.loadChilds(WORK_PLAN_DTL_IDS);
	            for (int i = 0; i < list.size(); i++) {
	            	WorkplanmagedtlModel modle = (WorkplanmagedtlModel) list.get(i);
	                // 验收项目类型
	                //if (BusinessConsts.PRO_TYPE.equals(modle.getPRJ_TYPE())) {
	                    modle.setRYXXID(modle.getRYXXID());
	                    modle.setRYBH(modle.getRYBH());
	                    modle.setRYMC(modle.getRYMC());
	                //}
	            }
	            request.setAttribute("rst", list);
	        }
	        request.setAttribute("WORK_PLAN_ID", WORK_PLAN_ID);
	        return mapping.findForward("childeditT");
	    	
	    }
	    
	    /**
	     * 工作计划维护明细编辑
	     * Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    
	    	UserBean userBean = ParamUtil.getUserBean(request);
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        int errorFlg = 0;
	        try {                                                          
	        	String WORK_PLAN_ID = ParamUtil.utf8Decoder(request, "WORK_PLAN_ID"); 
	            String jsonDate = request.getParameter("childJsonData");
	            if (StringUtils.isNotEmpty(jsonDate)) {
	                List <WorkplanmagedtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<WorkplanmagedtlModel>>() {
	                }.getType());
	                String str = "";
	                for(int i=0;i<modelList.size();i++){
	                	String WORK_PLAN_DTL_ID = modelList.get(i).getWORK_PLAN_DTL_ID().toString();
	                	String RYXXID           = modelList.get(i).getRYXXID().toString();
	                	String RYBH             = modelList.get(i).getRYBH().toString();
	                	String RYMC             = modelList.get(i).getRYMC().toString();
	                	if(WORK_PLAN_DTL_ID.equals("")){
	                	    str = workplanmageService.insertChild(WORK_PLAN_ID,RYXXID,RYBH, RYMC,modelList);
	                	}else{
	                	    str = workplanmageService.updateChild(WORK_PLAN_DTL_ID,WORK_PLAN_ID,RYXXID,RYBH, RYMC,modelList);
	                	}
	                }
	                if(str.equals("1")){
	   	        	 jsonResult = jsonResult(false, "人员信息不能重复");
	   	           }
	            }
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "保存失败");
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
	    /**
	     * 数据字典明细批量删除
	     * Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	        PrintWriter writer = getWriter(response);
	        UserBean userBean = ParamUtil.getUserBean(request);
	        String jsonResult = jsonResult();
	        try {                                                                
	        	String WORK_PLAN_DTL_IDs = ParamUtil.get(request, "WORK_PLAN_DTL_IDs");
	        	String WORK_PLAN_ID      = ParamUtil.get(request, "WORK_PLAN_ID");
	            //批量删除，多个Id之间用逗号隔开
	            String TOTAL_UP_REPORT_NUM = workplanmageService.txBatch4DeleteChildT(WORK_PLAN_DTL_IDs, userBean,WORK_PLAN_ID);
	            jsonResult = jsonResult(true, TOTAL_UP_REPORT_NUM);
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "删除失败");
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
        pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT));
        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
   }

	public WorkplanMageService getWorkplanmageService() {
		return workplanmageService;
	}

	public void setWorkplanmageService(WorkplanMageService workplanmageService) {
		this.workplanmageService = workplanmageService;
	}
}
