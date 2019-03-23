package com.hoperun.drp.oamg.rnvtnreform.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.rnvtncheck.model.RnvtncheckModel;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformChildModel;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.drp.oamg.rnvtnreform.service.RnvtnreformService;
import com.hoperun.sys.model.UserBean;

public class RnvtnreformAction  extends BaseAction  {

  //增删改查
  private static String PVG_BWS="BS0030701";
  private static String PVG_EDIT="BS0030702";
  private static String PVG_DELETE="BS0030703";

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
	
  private    RnvtnreformService   rnvtnreformService;
 	
	/**
	 * *装修整改单框架页面
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
     * 装修整改单维护列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
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
				qx.append(" and STATE in('待整改','发起整改') ");
			}
		 }
		 params.put("QXJBCON", qx.toString());
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 ParamUtil.putStr2Map(request, "pageSize", params);
		 IListPage page = rnvtnreformService.pageQuery(params, pageNo);
		 request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		 request.setAttribute("params", params);
		 request.setAttribute("module", module);
		 request.setAttribute("page", page);
		 request.setAttribute("pvg", setPvg(userBean));
		 return mapping.findForward("list");
    }
    
    /**
     * 装修整改单框架页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String RNVTN_REFORM_ID= ParamUtil.get(request, "RNVTN_REFORM_ID");
        request.setAttribute("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
    	if(!RNVTN_REFORM_ID.equals("")){
    	 return mapping.findForward("editFrame_T");
    	} else {
         return mapping.findForward("editFrame");
    	}
    }
    
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
     UserBean userBean = ParamUtil.getUserBean(request);
   	 String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");
   	 if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
		   Map entry = rnvtnreformService.loadByConfId(RNVTN_REFORM_ID);
		   request.setAttribute("rst", entry);
		}
   	 request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
   	 request.setAttribute("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
   	 return mapping.findForward("toeditT");
   }
    

    /**
     * 装修整改单维护删除 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");

        if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
            try {
            	rnvtnreformService.txDelete(RNVTN_REFORM_ID, userBean);
            	rnvtnreformService.clearCaches(RNVTN_REFORM_ID);
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
     * 装修整改单维护修改页面跳转 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	UserBean userBean = ParamUtil.getUserBean(request);
    	String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修验收单号
    	String userName  = userBean.getXM();                      //申请人
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 	    String time = sdf.format(new Date());                     //制单日期
    	request.setAttribute("RNVTN_SUBST_STD_NO",currentNo);
    	request.setAttribute("RNVTN_PRINCIPAL",userName);
    	request.setAttribute("RNVTN_CHECK_DATE", time);
    	request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	request.setAttribute("STATE","待整改");
    	String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
        return mapping.findForward("toedit");
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
			 Map entry = rnvtnreformService.loadByConfId(RNVTN_REFORM_ID);
			 request.setAttribute("rst", entry);
		}
        return mapping.findForward("todetail");
    }
    
    
    /**
     * 装修整改单维护编辑/新增或修改。 Description :.
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
         String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");  
         String chkLen = ParamUtil.utf8Decoder(request, "chkLen");
         RnvtnreformModel model = new RnvtnreformModel();
         if (StringUtils.isNotEmpty(parentJsonData)) {
             model = new Gson().fromJson(parentJsonData, new TypeToken <RnvtnreformModel>() {
             }.getType());
         }
         String jsonData = ParamUtil.get(request, "childJsonData");
         List <RnvtnreformChildModel>  mxList = null;
         if (StringUtils.isNotEmpty(jsonData)) {
             mxList = new Gson().fromJson(jsonData, new TypeToken <List<RnvtnreformChildModel>>() {
             }.getType());
         }
         String str = rnvtnreformService.txEdit(RNVTN_REFORM_ID, model, userBean,mxList,chkLen);
         if(str.equals("1")){
        	 jsonResult = jsonResult(false, "明细项目不能重复");
         }
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
    }
    
    /**
     * 确认
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
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
        rnvtnreformService.updateState(RNVTN_REFORM_ID, model, userBean,"已整改");
        rnvtnreformService.clearCaches(RNVTN_REFORM_ID);
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }
    
    /**
     * 生成整改单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public  void  generatecheck (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String parentJsonData = ParamUtil.get(request, "parentJsonData");
        
        UserBean userBean = ParamUtil.getUserBean(request);
        String RNVTN_CHECK_ID = ParamUtil.utf8Decoder(request, "RNVTN_CHECK_ID");  
        RnvtncheckModel model = new RnvtncheckModel();
        if (StringUtils.isNotEmpty(parentJsonData)) {
            model = new Gson().fromJson(parentJsonData, new TypeToken <RnvtncheckModel>() {
            }.getType());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 装修整改单维护明细列表 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	 String RNVTN_REFORM_ID = ParamUtil.get(request, "RNVTN_REFORM_ID");
         if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
              List<RnvtnreformChildModel> result = rnvtnreformService.childQuery(RNVTN_REFORM_ID);
              request.setAttribute("rst", result);
              System.out.println(request.getAttribute("rst"));
         }
         //为空直接跳转显示页面，而不是错误提示。
        return mapping.findForward("childlist");
    }

    
    /**
     * 装修整改单维护明细编辑跳转页面 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	
    	 //多个Id批量查询，用逗号隔开
        String RNVTN_REFORM_DTL_IDs = request.getParameter("RNVTN_REFORM_DTL_IDs");
        // 没有编码规则明细Id可以直接跳转。
        if (StringUtils.isNotEmpty(RNVTN_REFORM_DTL_IDs)) {
            List <RnvtnreformChildModel> list = rnvtnreformService.loadChilds(RNVTN_REFORM_DTL_IDs);
            for (int i = 0; i < list.size(); i++) {
            	RnvtnreformChildModel modle = (RnvtnreformChildModel) list.get(i);
                // 验收项目类型
                //if (BusinessConsts.PRO_TYPE.equals(modle.getPRJ_TYPE())) {
                    modle.setPRJ_TYPE(modle.getPRJ_TYPE());
                    modle.setPRJ_NAME(modle.getPRJ_NAME());
                    modle.setPRJ_SCORE(modle.getPRJ_SCORE());
                    modle.setCHECK_SCORE(modle.getCHECK_SCORE());
                    modle.setCHECK_REMARK(modle.getCHECK_REMARK());
                //}
            }
            request.setAttribute("rst", list);
        }
        return mapping.findForward("childedit");
    }
    
    
    /**
     * 装修整改单维护编辑页面加载子页面 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        //return mapping.findForward("childedit");
    	String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");//ParamUtil.get(request, "BMGZID");
        if (StringUtils.isNotEmpty(RNVTN_REFORM_ID)) {
            List<RnvtnreformChildModel> result = rnvtnreformService.childQuery(RNVTN_REFORM_ID);
            request.setAttribute("rst", result);
        }
        request.setAttribute("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
        return mapping.findForward("childedit");
    }
    
    
    
    /**
     * 装修整改单明细编辑
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
        	String RNVTN_REFORM_ID = ParamUtil.utf8Decoder(request, "RNVTN_REFORM_ID");//ParamUtil.get(request, "SJZDID");
            String jsonDate = request.getParameter("childJsonData");
            if (StringUtils.isNotEmpty(jsonDate)) {
                List <RnvtnreformChildModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<RnvtnreformChildModel>>() {
                }.getType());
                String str = "";
                for(int i=0;i<modelList.size();i++){
                	String PRJ_TYPE   = modelList.get(i).getPRJ_TYPE().toString() ;
                	String PRJ_NAME   = modelList.get(i).getPRJ_NAME().toString();
                	String PRJ_SCORE  = modelList.get(i).getPRJ_SCORE().toString();
                	String CHECK_SCORE = modelList.get(i).getCHECK_SCORE().toString();
                	String CHECK_REMARK= modelList.get(i).getCHECK_REMARK().toString();
                	String RNVTN_REFORM_DTL_ID = modelList.get(i).getRNVTN_REFORM_DTL_ID();
                	//String IS_REFORM_FLAG = modelList.get(i).getIS_REFORM_FLAG().toString();
                	//查询项目类型,项目名称在验收明细表中是否存在
                	List<RnvtnreformChildModel> result =  rnvtnreformService.queryTypeAndName(PRJ_TYPE,PRJ_NAME,RNVTN_REFORM_ID);
                	if(RNVTN_REFORM_DTL_ID.equals("")){
                		if(result.size()==0){
                	      str = rnvtnreformService.insertChild(RNVTN_REFORM_ID,PRJ_TYPE,PRJ_NAME,PRJ_SCORE,CHECK_SCORE,CHECK_REMARK,modelList);
                		}else {
                		  jsonResult = jsonResult(false, "明细项目不能重复");
                		}
                	}else{
                  	  rnvtnreformService.updateChild(RNVTN_REFORM_DTL_ID,RNVTN_REFORM_ID,PRJ_TYPE,PRJ_NAME,PRJ_SCORE,CHECK_SCORE,CHECK_REMARK);
                	}
                }
                if(str.equals("1")){
                	 jsonResult = jsonResult(false, "明细项目不能重复");
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
    
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        UserBean userBean = ParamUtil.getUserBean(request);
        String jsonResult = jsonResult();
        try {
            String RNVTN_REFORM_DTL_IDs = request.getParameter("RNVTN_REFORM_DTL_IDs");
            //批量删除，多个Id之间用逗号隔开
            rnvtnreformService.txBatch4DeleteChild(RNVTN_REFORM_DTL_IDs, userBean);
            rnvtnreformService.clearCaches(RNVTN_REFORM_DTL_IDs);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

	public RnvtnreformService getRnvtnreformService() {
		return rnvtnreformService;
	}
	public void setRnvtnreformService(RnvtnreformService rnvtnreformService) {
		this.rnvtnreformService = rnvtnreformService;
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
	        pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }

}
