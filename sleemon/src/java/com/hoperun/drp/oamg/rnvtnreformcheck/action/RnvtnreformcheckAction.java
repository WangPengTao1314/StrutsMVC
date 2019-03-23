package com.hoperun.drp.oamg.rnvtnreformcheck.action;

import java.io.PrintWriter;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckChildModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.service.RnvtnreformcheckService;
import com.hoperun.sys.model.UserBean;

public class RnvtnreformcheckAction extends BaseAction   {

    //验收和查询
    private static String PVG_BWS  ="BS0031001";
    private static String PVG_CHECK="BS0031002";
    
    //审核模块                             
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    private static String PVG_EDIT_AUDIT ="";
    
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
	
	private   RnvtnreformcheckService   rnvtnreformcheckService;
	
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
     * 装修整改验收单编辑框架页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String RNVTN_REFORM_ID= ParamUtil.get(request, "RNVTN_REFORM_ID");
    	if(!RNVTN_REFORM_ID.equals("")){
    	 request.setAttribute("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
    	 return mapping.findForward("editFrame_T");
    	} else {
         return mapping.findForward("editFrame");
    	}
    }
    
    /**
     * 装修整改验收单维护列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 
    	 Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "RNVTN_REFORM_NO", params);
         ParamUtil.putStr2Map(request, "RNVTN_CHECK_NO", params);
         ParamUtil.putStr2Map(request, "CHANN_RNVTN_NO", params);
         ParamUtil.putStr2Map(request, "CHECK_CHARGE", params);
         ParamUtil.putStr2Map(request, "TERM_NO", params);
         ParamUtil.putStr2Map(request, "TERM_NAME", params);
         ParamUtil.putStr2Map(request, "SALE_STORE_NAME", params);
         ParamUtil.putStr2Map(request, "STATE", params);
        
         //权限级别
         UserBean userBean = ParamUtil.getUserBean(request);
         
         String search = ParamUtil.get(request,"search");
 	     String module = ParamUtil.get(request,"module");
 	     String STATE = ParamUtil.get(request,"STATE");
         StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		 // 权限级别和审批流的封装
		 if (module.equals("wh")||module.equals("")) {
			if (!StringUtil.isEmpty(search)) {
				if (!StringUtil.isEmpty(STATE)) {
					params.put("STATE", STATE);
				}
			} else {
				qx.append(" and STATE in('已整改','整改通过','重新整改') ");
			}
		 }
		 params.put("QXJBCON", qx.toString());
		
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 ParamUtil.putStr2Map(request, "pageSize", params);
		 IListPage page = rnvtnreformcheckService.pageQuery(params, pageNo);
		 request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		 request.setAttribute("params", params);
		 request.setAttribute("module", module);
		 request.setAttribute("page", page);
		 request.setAttribute("pvg", setPvg(userBean));
		 return mapping.findForward("list");
    }
    
    /**
	 * 装修整改验收单编辑页面加载子页面 Description :.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param responsethe response
	 * @return the action forward
	 */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("childedit");
    }
    
    
    /**
     * 装修整改验收单维护明细列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	 String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");
         if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
              List<RnvtnreformcheckChildModel> result = rnvtnreformcheckService.childQuery(RNVTN_REFORM_ID);
              request.setAttribute("rst", result);
              System.out.println(request.getAttribute("rst"));
         }
         //为空直接跳转显示页面，而不是错误提示。
        return mapping.findForward("childlist");
    }
    
    public void   commRecv (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String parentJsonData = ParamUtil.get(request, "parentJsonData");
        
        UserBean userBean = ParamUtil.getUserBean(request);
        String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");  
        RnvtnreformModel model = new RnvtnreformModel();
        if (StringUtils.isNotEmpty(parentJsonData)) {
            model = new Gson().fromJson(parentJsonData, new TypeToken <RnvtnreformModel>() {
            }.getType());
        }
        rnvtnreformcheckService.updateState(RNVTN_REFORM_ID, model, userBean,"整改通过");
        rnvtnreformcheckService.clearCaches(RNVTN_REFORM_ID);
        jsonResult = jsonResult(true, "整改通过");
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }
    
  public void   reCheck (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String parentJsonData = ParamUtil.get(request, "parentJsonData");
        
        UserBean userBean = ParamUtil.getUserBean(request);
        String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");  
        RnvtnreformModel model = new RnvtnreformModel();
        if (StringUtils.isNotEmpty(parentJsonData)) {
            model = new Gson().fromJson(parentJsonData, new TypeToken <RnvtnreformModel>() {
            }.getType());
        }
        rnvtnreformcheckService.updateState(RNVTN_REFORM_ID, model, userBean,"重新整改");
        rnvtnreformcheckService.clearCaches(RNVTN_REFORM_ID);
        jsonResult = jsonResult(true, "重新整改");
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }
    
    
    
    /**
     * 显示记录详细信息 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	String RNVTN_REFORM_ID= ParamUtil.get(request, "RNVTN_REFORM_ID");
		if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
			 Map entry = rnvtnreformcheckService.loadByConfIdT(RNVTN_REFORM_ID);
			 request.setAttribute("rst", entry);
			 
		     String  checkNo = rnvtnreformcheckService.queryCheckByReform(RNVTN_REFORM_ID);
			 String  checkId = rnvtnreformcheckService.queryCheckById(checkNo);
			 Map entryT = rnvtnreformcheckService.loadByConfIdTt(RNVTN_REFORM_ID,checkId);
			 request.setAttribute("rstT", entryT);
			 
		}
        return mapping.findForward("todetail");
    }
    
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");
		if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
			//由于整改单ID获取到验收单ID
			String  checkNo = rnvtnreformcheckService.queryCheckByReform(RNVTN_REFORM_ID);
			String  checkId = rnvtnreformcheckService.queryCheckById(checkNo);
			Map<String, String> entry = rnvtnreformcheckService.loadByConfIdTt(RNVTN_REFORM_ID,checkId);
			request.setAttribute("rst", entry);
			Map<String, String> entryT = rnvtnreformcheckService.loadByConfIdT(RNVTN_REFORM_ID);
			request.setAttribute("rstT", entryT);
		}

		return mapping.findForward("toedit");

	}
    
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
      	 String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");
      	 if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
   			Map entry = rnvtnreformcheckService.loadByConfId(RNVTN_REFORM_ID);
   		    request.setAttribute("rst", entry);
   		}
      	 return mapping.findForward("toeditT");
    }
    
    
    /**
     * 装修整改验收单维护编辑//新增或修改。 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    @SuppressWarnings("unchecked")
	public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
         PrintWriter writer = getWriter(response);
         String jsonResult = jsonResult();
         String parentJsonData = ParamUtil.get(request, "jsonData");
         
         UserBean userBean = ParamUtil.getUserBean(request);
         String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");
         RnvtnreformcheckModel model = new RnvtnreformcheckModel();
         if (StringUtils.isNotEmpty(parentJsonData)) {
             model = new Gson().fromJson(parentJsonData, new TypeToken <RnvtnreformcheckModel>() {
             }.getType());
         }
         rnvtnreformcheckService.txEdit(RNVTN_REFORM_ID, model, userBean);
         jsonResult = jsonResult(true, "保存成功");
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
    }
    
	public RnvtnreformcheckService getRnvtnreformcheckService() {
		return rnvtnreformcheckService;
	}
	public void setRnvtnreformcheckService(
			RnvtnreformcheckService rnvtnreformcheckService) {
		this.rnvtnreformcheckService = rnvtnreformcheckService;
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
    	pvgMap.put("PVG_CHECK",QXUtil.checkPvg(userBean, PVG_CHECK));
    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
        pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT));
		pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
   }
}
