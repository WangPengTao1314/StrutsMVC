package com.hoperun.drp.oamg.decorationallo.action;
 
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
import com.hoperun.drp.oamg.decorationallo.model.DecorationalloModel;
import com.hoperun.drp.oamg.decorationallo.model.DrprnvtnsubststddtlModel;
import com.hoperun.drp.oamg.decorationallo.service.DecorationalloService;
import com.hoperun.sys.model.UserBean;
 

public class DecorationalloAction extends BaseAction {

	private   DecorationalloService     decorationalloService; 
	
	//增删改查
    private static String PVG_BWS="BS0030601";
    private static String PVG_EDIT="BS0030602";
    private static String PVG_DELETE="BS0030603";
    
    // 启用,停用
	private static String PVG_START_STOP = "BS0030604";
	
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
    private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
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
     * 装修补贴标准维护列表 Description :.
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
         ParamUtil.putStr2Map(request, "RNVTN_SUBST_STD_NO", params);
         ParamUtil.putStr2Map(request, "RNVTN_SUBST_STD_VSION", params);
         ParamUtil.putStr2Map(request, "BRAND", params);
         ParamUtil.putStr2Map(request, "STATE", params);
         params.put("LEDGER_ID", userBean.getLoginZTXXID().toString());
         
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
				qx.append(" and STATE in('制定','启用') ");
			}
		 }
		 params.put("QXJBCON", qx.toString());
		 if ("sh".equals(module)) {
			String channChrg = userBean.getCHANNS_CHARG();
			params.put("channChrg", channChrg);
		 }
		 
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 ParamUtil.putStr2Map(request, "pageSize", params);
		 IListPage page = decorationalloService.pageQuery(params, pageNo);
		 request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		 request.setAttribute("params", params);
		 request.setAttribute("module", module);
		 request.setAttribute("page", page);
		 request.setAttribute("pvg", setPvg(userBean));
		 return mapping.findForward("list");
    }
   
    /**
     * 装修补贴标准维护编辑框架页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String RNVTN_SUBST_STD_ID = ParamUtil.get(request, "RNVTN_SUBST_STD_ID");
    	if(!RNVTN_SUBST_STD_ID.equals("")){
    	 request.setAttribute("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
    	 return mapping.findForward("editFrame_T");
    	} else {
         return mapping.findForward("editFrame");
    	}
    }


    /**
     * 装修补贴标准维护明细列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	 String RNVTN_SUBST_STD_ID = ParamUtil.get(request, "RNVTN_SUBST_STD_ID");
         if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
             List<DrprnvtnsubststddtlModel> result = decorationalloService.childQueryT(RNVTN_SUBST_STD_ID);
             request.setAttribute("rst", result);
             System.out.println(request.getAttribute("rst"));
         }
        //为空直接跳转显示页面，而不是错误提示。
        return mapping.findForward("childlist");
    }


    /**
     * 装修补贴标准维护修改页面跳转 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	 UserBean userBean = ParamUtil.getUserBean(request);
    	 String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
         String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request, "selRowId"));//ParamUtil.utf8Decoder(request, "BMGZID");//;
         String RNVTN_SUBST_STD_ID = StringUtil.utf8Decoder(ParamUtil.get(request, "RNVTN_SUBST_STD_ID"));
         // 为空则是新增，否则是修改
         if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
             Map <String, String> entry = decorationalloService.loadByConfId(RNVTN_SUBST_STD_ID);
             request.setAttribute("rst", entry);
             request.setAttribute("isNew", false);
         }  else {
        	 String userName  = userBean.getXM();                      //申请人
             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
      	     String time = sdf.format(new Date());                     //制单日期
         	 request.setAttribute("RNVTN_SUBST_STD_NO",currentNo);
         	 request.setAttribute("CRE_NAME",userName);
         	 request.setAttribute("CRE_TIME", time);
         }
         request.setAttribute("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
         return mapping.findForward("toedit");
    }
    
    
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 UserBean userBean = ParamUtil.getUserBean(request);
    	 String RNVTN_SUBST_STD_ID = ParamUtil.get(request, "RNVTN_SUBST_STD_ID");
    	 if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
 			Map entry = decorationalloService.loadByConfId(RNVTN_SUBST_STD_ID);
 			request.setAttribute("rst", entry);
 		 }
    	 request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	 request.setAttribute("RNVTN_SUBST_STD_ID",RNVTN_SUBST_STD_ID);
    	 return mapping.findForward("toeditT");
    }
    
    
    /**
     * 装修补贴标准维护详细信息 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	String RNVTN_SUBST_STD_ID = ParamUtil.get(request, "RNVTN_SUBST_STD_ID");
		if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
			Map entry = decorationalloService.loadByConfIdT(RNVTN_SUBST_STD_ID);
			request.setAttribute("rst", entry);
		}
        return mapping.findForward("todetail");
    }


    /**
     * 装修补贴标准维护编辑跳转页面 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        String RNVTN_SUBST_STD_DTL_IDS = request.getParameter("RNVTN_SUBST_STD_DTL_IDS");
        // 没有编码规则明细Id可以直接跳转。
        if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_DTL_IDS)) {
            List <DrprnvtnsubststddtlModel> list = decorationalloService.loadByIds(RNVTN_SUBST_STD_DTL_IDS);
            for (int i = 0; i < list.size(); i++) {
            	DrprnvtnsubststddtlModel modle = (DrprnvtnsubststddtlModel) list.get(i);
                // 验收项目类型
                //if (BusinessConsts.PRO_TYPE.equals(modle.getPRJ_TYPE())) {
            	    modle.setRNVTN_SUBST_STD_DTL_ID(modle.getRNVTN_SUBST_STD_DTL_ID());
                    modle.setLOCAL_TYPE(modle.getLOCAL_TYPE());
                    modle.setSUBST_AMOUNT(modle.getSUBST_AMOUNT());
                    modle.setREMARK(modle.getREMARK());
                //}
            }
            request.setAttribute("rst", list);
        }
        return mapping.findForward("childedit");
    }


    /**
     * 装修补贴标准维护编辑页面加载子页面 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	String RNVTN_SUBST_STD_ID = ParamUtil.utf8Decoder(request, "RNVTN_SUBST_STD_ID");//ParamUtil.get(request, "BMGZID");
        if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
            List<DrprnvtnsubststddtlModel> result = decorationalloService.childQuery(RNVTN_SUBST_STD_ID);
            request.setAttribute("rst", result);
        }
        request.setAttribute("RNVTN_SUBST_STD_ID",RNVTN_SUBST_STD_ID);
        return mapping.findForward("childedit");
    }


    /**
     * 装修补贴标准维护删除 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      
    	UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String RNVTN_SUBST_STD_ID = ParamUtil.get(request, "RNVTN_SUBST_STD_ID");

        if (StringUtils.isNotEmpty(RNVTN_SUBST_STD_ID)) {
            try {
                decorationalloService.txDelete(RNVTN_SUBST_STD_ID, userBean);
                decorationalloService.clearCaches(RNVTN_SUBST_STD_ID);
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
     * 装修补贴标准维护编辑//新增或修改。 Description :.
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
         String RNVTN_SUBST_STD_ID = ParamUtil.utf8Decoder(request, "RNVTN_SUBST_STD_ID");  
         DecorationalloModel model = new DecorationalloModel();
         if (StringUtils.isNotEmpty(parentJsonData)) {
             model = new Gson().fromJson(parentJsonData, new TypeToken <DecorationalloModel>() {
             }.getType());
         }
         String jsonData = ParamUtil.get(request, "childJsonData");
         List <DrprnvtnsubststddtlModel>  mxList = null;
         if (StringUtils.isNotEmpty(jsonData)) {
             mxList = new Gson().fromJson(jsonData, new TypeToken <List<DrprnvtnsubststddtlModel>>() {
             }.getType());
         }
         String str = decorationalloService.txEdit(RNVTN_SUBST_STD_ID, model, userBean,mxList);
         if(str.equals("0")){
        	 jsonResult = jsonResult(false, "同一商场位置类型只能有一条记录");
         }
         if (null != writer) {
             writer.write(jsonResult);
             writer.close();
         }
    }

	public DecorationalloService getDecorationalloService() {
		return decorationalloService;
	}


	public void setDecorationalloService(DecorationalloService decorationalloService) {
		this.decorationalloService = decorationalloService;
	}
	
	
    /**
     * 停用启用按钮修改单条记录状态.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        String str = "";
        try {
            String RNVTN_SUBST_STD_ID = ParamUtil.utf8Decoder(request, "RNVTN_SUBST_STD_ID"); 
            String BRAND  = ParamUtil.utf8Decoder(request, "BRAND");
            // 取得状态
            Map <String, String> entry = decorationalloService.load(RNVTN_SUBST_STD_ID);
            String state = entry.get("STATE");
            Map <String, String> params = new TreeMap <String, String>();
            String changedState = "";
            params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
            UserBean userBean = ParamUtil.getUserBean(request);
            params.put("UPDATER", userBean.getXM());
            // 启用==>停用
            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
                decorationalloService.updateState(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state) || BusinessConsts.JC_STATE_DEFAULT.equals(state)) {
                // 停用 ==>启用
                // 判断同品牌是否只能有一条启用状态
            	String BRANDT    = decorationalloService.queryProT(BRAND);
            	String LEDGER_ID = userBean.getLoginZTXXID().toString();
                int count = decorationalloService.countFrom(BRANDT,LEDGER_ID); 	
                if(count == 0){
	                changedState = BusinessConsts.JC_STATE_ENABLE;
	                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
	                decorationalloService.updateState(params);
	                isChanged = true;
                } else {
                	str = "同品牌中只能有一条启用状态";
                	jsonResult = jsonResult(false, str);
                }
            }
            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
            	if(str.equals("")) {
                    jsonResult = jsonResult(false, "状态不用修改");
            	} else {
            		 jsonResult = jsonResult(false, str);
            	}
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    public void changeStateT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        String str = "";
        try {
            String RNVTN_SUBST_STD_ID = ParamUtil.utf8Decoder(request, "RNVTN_SUBST_STD_ID"); 
            String BRAND  = ParamUtil.utf8Decoder(request, "BRAND");
            // 取得状态
            Map <String, String> entry = decorationalloService.load(RNVTN_SUBST_STD_ID);
            String state = entry.get("STATE");
            Map <String, String> params = new TreeMap <String, String>();
            String changedState = "";
            params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            UserBean userBean = ParamUtil.getUserBean(request);
            params.put("UPDATER", userBean.getXM());
            decorationalloService.updateState(params);
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 装修补贴标准维护明细编辑
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
        	String RNVTN_SUBST_STD_ID = ParamUtil.utf8Decoder(request, "RNVTN_SUBST_STD_ID");//ParamUtil.get(request, "SJZDID");
            String jsonDate = request.getParameter("childJsonData");
            if (StringUtils.isNotEmpty(jsonDate)) {
                List <DrprnvtnsubststddtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<DrprnvtnsubststddtlModel>>() {
                }.getType());
                for(int i=0;i<modelList.size();i++){
                	String TYPE   = modelList.get(i).getLOCAL_TYPE().toString();
                	String AMOUNT = modelList.get(i).getSUBST_AMOUNT().toString();
                	String REMARK = modelList.get(i).getREMARK().toString();
                	String RNVTN_SUBST_STD_DTL_ID = modelList.get(i).getRNVTN_SUBST_STD_DTL_ID().toString();
                	String str = "";
                	if(RNVTN_SUBST_STD_DTL_ID.equals("")){
                	   str = decorationalloService.insertChild(RNVTN_SUBST_STD_ID,TYPE, AMOUNT, REMARK,modelList);
                	}else{
                	   str = decorationalloService.updateChild(RNVTN_SUBST_STD_DTL_ID,RNVTN_SUBST_STD_ID, TYPE, AMOUNT, REMARK,modelList);
                	}
                	if(str.equals("0")){
                		jsonResult = jsonResult(false, "同一商场中位置类型只能有一条记录");
                	}
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
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE));
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP));
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
	        	String RNVTN_SUBST_STD_DTL_IDs = ParamUtil.get(request, "RNVTN_SUBST_STD_DTL_IDs");
	            //批量删除，多个Id之间用逗号隔开
	            decorationalloService.txBatch4DeleteChild(RNVTN_SUBST_STD_DTL_IDs, userBean);
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "删除失败");
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
}
