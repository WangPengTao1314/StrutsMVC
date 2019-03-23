package com.hoperun.drp.visit.expandvisit.action;

import java.io.PrintWriter;
import java.lang.reflect.Type;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitDtlModel;
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitModel;
import com.hoperun.drp.visit.expandvisit.service.ExpandVisitService;
import com.hoperun.sys.model.UserBean;

public class ExpandVisitAction extends BaseAction {
   
	private  ExpandVisitService  expandvisitService;

	//增删改查
    private static String PVG_BWS="BS0032801";
    private static String PVG_EDIT="BS0032802";
    private static String PVG_DELETE="BS0032803";
    
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0032804";
    //流程跟踪
    private static String PVG_TRACE= "BS0032805";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0033101";
    private static String PVG_AUDIT_AUDIT="BS0033102";
    private static String PVG_TRACE_AUDIT="BS0033103";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_EXPAND_VISIT";
    private static String AUD_TAB_KEY="EXPAND_VISIT_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_EXPAND_VISIT_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
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
     * 拓展拜访维护列表 Description :.
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
         ParamUtil.putStr2Map(request, "EXPAND_VISIT_NO", params);
         ParamUtil.putStr2Map(request, "VISIT_PEOPLE", params);
         ParamUtil.putStr2Map(request, "CHANN_NAME", params);
         ParamUtil.putStr2Map(request, "STATE", params);
         ParamUtil.putStr2Map(request, "SVISIT_DATE", params);
         ParamUtil.putStr2Map(request, "EVISIT_DATE", params);
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
			params.put("QXJBCON",qx.toString());
		 }
		 if(module.equals("sh")){
			String strSql = qx.toString().substring(qx.toString().indexOf("(")+1,qx.toString().lastIndexOf(")"));
			String newStr = "("+strSql.replaceAll("u.CREATOR", "'"+userBean.getRYXXID()+"'")+") temp ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(newStr);
			buffer.append("WHERE u.DEL_FLAG = 0 ");
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					buffer.append("and STATE = '"+STATE+"'");
				}else{
					buffer.append("and STATE IN ('提交','撤销','否决','审核通过') ");
				}
			}else{
				  buffer.append("and STATE = '提交'");
			}
			params.put("QXJBCON",buffer.toString());
		 }
	     
	     IListPage page = null;
	     request.setAttribute("module",module);
	     //渠道分管 
		 String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		 params.put("CHANNS_CHARG", CHANNS_CHARG);
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 if(module.equals("sh")){
 		   page = expandvisitService.pageQuery(params, pageNo);
		 } else {
	 	   page = expandvisitService.pageQueryWH(params, pageNo);
		 }
 		 request.setAttribute("pvg",setPvg(userBean));
 		 request.setAttribute("module", module);
 		 request.setAttribute("search", search);
         request.setAttribute("params", params);
         request.setAttribute("page", page);  
         return mapping.findForward("list");
    }
    
    /**
     * 拓展拜访表明细列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 UserBean userBean = ParamUtil.getUserBean(request); 
    	 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	     }
    	 String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
         if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
             List<ExpandVisitDtlModel> result = expandvisitService.childQueryT(EXPAND_VISIT_ID);
             for(int i=0;i<result.size();i++){
            	 ExpandVisitDtlModel  model = (ExpandVisitDtlModel)result.get(i);
            	 String  CUST_INTENTION = expandvisitService.queryPro(model.getCUST_INTENTION());
            	 model.setCUST_INTENTION(CUST_INTENTION);
             }
             request.setAttribute("rst", result);
         }
         //为空直接跳转显示页面，而不是错误提示。
        return mapping.findForward("childlist");
    }
    
    public ActionForward childListSH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 UserBean userBean = ParamUtil.getUserBean(request); 
    	 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	     }
   	     String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
         if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
            List<ExpandVisitDtlModel> result = expandvisitService.childQueryT(EXPAND_VISIT_ID);
            for(int i=0;i<result.size();i++){
           	 ExpandVisitDtlModel  model = (ExpandVisitDtlModel)result.get(i);
           	 String  CUST_INTENTION = expandvisitService.queryPro(model.getCUST_INTENTION());
           	 model.setCUST_INTENTION(CUST_INTENTION);
            }
            request.setAttribute("rst", result);
        }
        //为空直接跳转显示页面，而不是错误提示。
       return mapping.findForward("childlistSH");
   }
    
    /**
     * 拓展拜访表编辑框架页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
        String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
        request.setAttribute("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
        String module  = ParamUtil.get(request, "module");
        request.setAttribute("module", module);
    	if(!EXPAND_VISIT_ID.equals("")){
    	 return mapping.findForward("editFrame_T");
    	} else {
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  	     String time = sdf.format(new Date());
  	     request.setAttribute("APPLY_DATE",time);
  	     request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
  	     // 获取当前登录人员姓名id
  	     Map<String,String> entry ;
  	     entry=new HashMap<String, String>();
  	     entry.put("APPLY_PERSON", userBean.getXM());
  	     entry.put("APPLY_ID",     userBean.getRYXXID());
  	     request.setAttribute("rst", entry);
		 request.setAttribute("LOGIN_NAME", userBean.getXM());
		 request.setAttribute("BM_NAME",userBean.getBMMC());
		 request.setAttribute("LOGIN_ID", userBean.getRYXXID());
         return mapping.findForward("toedit");
    	}
    }
    
    /**
     * 拓展拜访表维护修改页面跳转 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
 
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
    	String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修验收单号
    	String userName  = userBean.getXM();                      //申请人
    	request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	request.setAttribute("CHANN_ID",userBean.getCHANNS_CHARG());
        return mapping.findForward("toedit");
    }
    
    public ActionForward toParentEditT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
		if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
			Map entry =  expandvisitService.loadByConfId(EXPAND_VISIT_ID);
			request.setAttribute("rst", entry);
		}
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
		return mapping.findForward("toeditT");
	}
    
    public ActionForward toEdit1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
	    String jsonResult = jsonResult();
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    String userName  = userBean.getXM();  //申请人
	    Map<String,String> params = new HashMap<String,String>();
	    Map<String,String> paramsT = new HashMap<String,String>();
	    String EXPAND_VISIT_ID    = ParamUtil.get(request, "EXPAND_VISIT_ID");

	    String search = ParamUtil.get(request,"search");
	    String module    = ParamUtil.get(request, "module");
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    IListPage page   = null;
	    IListPage pageT  = null;
	    if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
			Map entry = expandvisitService.loadByConfIdT(EXPAND_VISIT_ID);
			request.setAttribute("rst", entry);
		}
		//权限级别和审批流的封装和状态过滤
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String time = sdf.format(new Date());
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	    request.setAttribute("userName",userName);
	    request.setAttribute("date", time);
	    request.setAttribute("state", "未提交");
	    request.setAttribute("page", page);
	    request.setAttribute("pageT", pageT);
	    request.setAttribute("pvg",setPvg(userBean));
	    
		return mapping.findForward("toeditT");
	}
    
    /**
     * 拓展拜访表详细信息 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
		if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
			Map entry = expandvisitService.loadByConfIdT(EXPAND_VISIT_ID);
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(EXPAND_VISIT_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
		}
        return mapping.findForward("todetail");
    }
    
    /**
     * 拓展拜访表维护编辑//新增或修改。 Description :.
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

		// url传值
		String ExistNames = ParamUtil.get(request, "ExistNames");
		String ExistAddrs = ParamUtil.get(request, "ExistAddrs");
		String ExistAreas = ParamUtil.get(request, "ExistAreas");
		String ExistRanges = ParamUtil.get(request, "ExistRanges");
		String ExistCompetions = ParamUtil.get(request, "ExistCompetions");

		String NewNames = ParamUtil.get(request, "NewNames");
		String NewAddrs = ParamUtil.get(request, "NewAddrs");
		String NewAreas = ParamUtil.get(request, "NewAreas");
		String NewRanges = ParamUtil.get(request, "NewRanges");
		String NewBeginDates = ParamUtil.get(request, "NewDates");

		String ChannNames = ParamUtil.get(request, "ChannNames");
		String BussScopes = ParamUtil.get(request, "BussScopes");
		String StoreNames = ParamUtil.get(request, "StoreNames");
		String PurposeNames = ParamUtil.get(request, "PurposeNames");
		String Tels = ParamUtil.get(request, "Tels");

		String TITLE = ParamUtil.get(request, "TITLE");
		String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");
		String EME_DEGREE = ParamUtil.get(request, "EME_DEGREE");
		String APPLY_PERSON = ParamUtil.get(request, "APPLY_PERSON");
		String APPLY_DEP = ParamUtil.get(request, "APPLY_DEP");
		String APPLY_DATE = ParamUtil.get(request, "APPLY_DATE");
		String VISIT_PEOPLE = ParamUtil.get(request, "VISIT_PEOPLE");
		String VISIT_DATE = ParamUtil.get(request, "VISIT_DATE");
		String CHANN_REMARK = ParamUtil.get(request, "CHANN_REMARK");
		String SOLUTION = ParamUtil.get(request, "SOLUTION");
		String SUPPORT_DEMAND = ParamUtil.get(request, "SUPPORT_DEMAND");
		String COMPETITION_INFO = ParamUtil.get(request, "COMPETITION_INFO");
		String PROCESS = ParamUtil.get(request, "PROCESS");
		String REMARK = ParamUtil.get(request, "REMARK");
		String ATT_PATH = ParamUtil.get(request, "ATT_PATH");
		String VISIT_TYPE = ParamUtil.get(request, "VISIT_TYPE");
		String ADVANTAGES = ParamUtil.get(request, "ADVANTAGES");

		ExpandVisitModel model = new ExpandVisitModel();
		model.setEXPAND_VISIT_ID(EXPAND_VISIT_ID);
		model.setTITLE(TITLE);
		model.setEME_DEGREE(EME_DEGREE);
		model.setAPPLY_PERSON(APPLY_PERSON);
		model.setAPPLY_DEP(APPLY_DEP);
		model.setAPPLY_DATE(APPLY_DATE);
		model.setVISIT_PEOPLE(VISIT_PEOPLE);
		model.setVISIT_DATE(VISIT_DATE);
		model.setEXIST_STORE_NAME(ExistNames);
		model.setEXIST_STORE_ADDR(ExistAddrs);
		model.setEXIST_STORE_AREA(ExistAreas);
		model.setEXIST_STORE_RANGE(ExistRanges);
		model.setEXIST_STORE_COMPETITION(ExistCompetions);

		model.setNEW_STORE_NAME(NewNames);
		model.setNEW_STORE_ADDR(NewAddrs);
		model.setNEW_STORE_AREA(NewAreas);
		model.setNEW_STORE_RANGE(NewRanges);
		model.setNEW_STORE_DATE(NewBeginDates);

		model.setCHANN_NAME(ChannNames);
		model.setBUSS_SCOPE(BussScopes);
		model.setSTORE_NAME(StoreNames);
		model.setPURPOSE_STORE_NAME(PurposeNames);
		model.setTEL(Tels);

		model.setCHANN_REMARK(CHANN_REMARK);
		model.setSOLUTION(SOLUTION);
		model.setSUPPORT_DEMAND(SUPPORT_DEMAND);
		model.setCOMPETITION_INFO(COMPETITION_INFO);
		model.setPROCESS(PROCESS);
		model.setREMARK(REMARK);
		model.setPIC_PATH(ATT_PATH);
		model.setVISIT_TYPE(VISIT_TYPE);
		model.setADVANTAGES(ADVANTAGES);
		try {
			expandvisitService.txEdit(EXPAND_VISIT_ID, model, userBean);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	    	
    }
    
    private Object fromJson(String jsonData, Type type) {
		return null;
	}

	/**
	 * 拓展拜访表明细编辑跳转页面 Description :.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	// 多个Id批量查询，用逗号隔开
        String EXPAND_VISIT_DTL_ID = request.getParameter("EXPAND_VISIT_DTL_ID");
        // 没有编码规则明细Id可以直接跳转。
        if (StringUtils.isNotEmpty(EXPAND_VISIT_DTL_ID)) {
            List <ExpandVisitDtlModel> list = expandvisitService.loadChilds(EXPAND_VISIT_DTL_ID);
            for (int i = 0; i < list.size(); i++) {
            	ExpandVisitDtlModel modle = (ExpandVisitDtlModel) list.get(i);
                    modle.setSTORE_NAME(modle.getSTORE_NAME());  //商场名称
                    modle.setSOFTWARE_LAYER(modle.getSOFTWARE_LAYER());  //软体层次
                    modle.setLOCATION(modle.getLOCATION());   //定位
                    modle.setPOPULARITY(modle.getPOPULARITY()); //知名度
                    modle.setOPENNING_TIME(modle.getOPENNING_TIME());
                    modle.setINVESTMENT_TIME(modle.getINVESTMENT_TIME());
                    modle.setOTHER_INFO(modle.getOTHER_INFO());
                    modle.setSTORE_MNG_NAME(modle.getSTORE_MNG_NAME());
                    modle.setSTORE_MNG_TEL(modle.getSTORE_MNG_TEL());
                    modle.setSTORE_MNG_EMAIL(modle.getSTORE_MNG_EMAIL());
                    modle.setINVESTMENT_MNG_NAME(modle.getINVESTMENT_MNG_NAME());
                    modle.setINVESTMENT_MNG_TEL(modle.getINVESTMENT_MNG_TEL());
                    modle.setINVESTMENT_MNG_EMAIL(modle.getINVESTMENT_MNG_EMAIL());
                    modle.setCUST_NAME(modle.getCUST_NAME());
                    modle.setCUST_FUNDS(modle.getCUST_FUNDS());
                    modle.setCUST_STATE(modle.getCUST_STATE());
                    modle.setBUSINESS_PHI(modle.getBUSINESS_PHI());
                    modle.setCUST_OTHER_INFO(modle.getCUST_OTHER_INFO());
                    modle.setCUST_INTENTION(modle.getCUST_INTENTION());
                    modle.setFOLLOW_WAY(modle.getFOLLOW_WAY());
            }
            request.setAttribute("rst", list);
        }
        return mapping.findForward("childedit");
    	
    }
    
    
    /**
     * 拓展拜访表维护编辑页面加载子页面 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String EXPAND_VISIT_ID = ParamUtil.utf8Decoder(request, "EXPAND_VISIT_ID");
        if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
            List<ExpandVisitDtlModel> result = expandvisitService.childQuery(EXPAND_VISIT_ID);
            request.setAttribute("rst", result);
        }
        request.setAttribute("EXPAND_VISIT_ID",EXPAND_VISIT_ID);
        return mapping.findForward("childedit");
    }
    
    
    
    /**
     * 拓展拜访表明细编辑
     * Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        int errorFlg = 0;
        try {
        	String EXPAND_VISIT_ID = ParamUtil.utf8Decoder(request, "EXPAND_VISIT_ID");//ParamUtil.get(request, "SJZDID");
            String jsonDate = request.getParameter("childJsonData");
            if (StringUtils.isNotEmpty(jsonDate)) {
                List <ExpandVisitDtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<ExpandVisitDtlModel>>() {
                }.getType());
                for(int i=0;i<modelList.size();i++){
                	ExpandVisitDtlModel model = (ExpandVisitDtlModel)modelList.get(i);
                	String EXPAND_VISIT_DTL_ID = model.getEXPAND_VISIT_DTL_ID().toString();
                	
                	//查询项目类型,项目名称在验收明细表中是否存在
                    String str = "";
        			if(StringUtils.isEmpty(EXPAND_VISIT_DTL_ID)){
         	           expandvisitService.insertChild(EXPAND_VISIT_ID,model);
        			} else{
                  	   expandvisitService.updateChild(EXPAND_VISIT_DTL_ID,model);
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
     * 拓展拜访表删除 Description :.
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
        String EXPAND_VISIT_ID = ParamUtil.get(request, "EXPAND_VISIT_ID");

        if (StringUtils.isNotEmpty(EXPAND_VISIT_ID)) {
            try {
            	expandvisitService.txDelete(EXPAND_VISIT_ID, userBean);
                expandvisitService.clearCaches(EXPAND_VISIT_ID);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
	      UserBean userBean = ParamUtil.getUserBean(request);
		  if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			 throw new ServiceException("对不起，您没有权限 !");
		  }
          PrintWriter writer = getWriter(response);
          String jsonResult = jsonResult();
          try {
              String EXPAND_VISIT_ID = ParamUtil.utf8Decoder(request, "EXPAND_VISIT_ID");//request.getParameter("BMGZID");
              String EXPAND_VISIT_DTL_IDS = request.getParameter("EXPAND_VISIT_DTL_IDs");
              // 批量删除，多个Id之间用逗号隔开
              expandvisitService.txBatch4DeleteChild(EXPAND_VISIT_DTL_IDS, EXPAND_VISIT_ID, userBean);
          } catch (Exception e) {
              jsonResult = jsonResult(false, "删除失败");
          }
          if (null != writer) {
              writer.write(jsonResult);
              writer.close();
          }
    }
    
    // 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "EXPAND_VISIT_NO", params);
		ParamUtil.putStr2Map(request, "VISIT_PEOPLE", params);
		ParamUtil.putStr2Map(request, "SVISIT_DATE", params);
		ParamUtil.putStr2Map(request, "EVISIT_DATE", params);
		ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//权限级别和审批流的封装
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
			 params.put("QXJBCON",qx.toString());
		}
		if(module.equals("sh")){
			String strSql = qx.toString().substring(qx.toString().indexOf("(")+1,qx.toString().lastIndexOf(")"));
			String newStr = "("+strSql.replaceAll("u.CREATOR", "'"+userBean.getRYXXID()+"'")+") temp ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(newStr);
			buffer.append("WHERE u.DEL_FLAG = 0 ");
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					buffer.append("and STATE = '"+STATE+"'");
				}else{
					buffer.append("and STATE IN ('提交','撤销','否决','审核通过') ");
				}
			}else{
				  buffer.append("and STATE = '提交'");
			}
			params.put("QXJBCON",buffer.toString());
		}
	    
	    IListPage page = null;
	    request.setAttribute("module",module);
	     
	    //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    params.put("CHANNS_CHARG", CHANNS_CHARG);
	    //字段排序
  		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    
		List<ExpandVisitModel> list = new ArrayList();
		if(module.equals("sh")){
			list = expandvisitService.expertExcelQuerySH(params);
		} else{
			list = expandvisitService.expertExcelQuery(params);
		}
		// excel数据上列显示的列明
		/*
		String tmpContentCn = "流程编号,紧急程度,拜访人,申请部门,申请日期,拜访时间,拜访方式,加盟商异议," +  
		        "解决方案,支持需求,竞品信息,现状及优势,名称,地址,面积,档次排名,进驻主竞品,名称,地址,面积,档次排名,开业时间,"+
				"客户姓名,经营品牌,进驻卖场,意向门店,联系电话";
		
		String tmpContent = "EXPAND_VISIT_NO,EME_DEGREE,VISIT_PEOPLE,APPLY_DEP,APPLY_DATE,VISIT_DATE,VISIT_TYPE,CHANN_REMARK," +
		        "SOLUTION,SUPPORT_DEMAND,COMPETITION_INFO,ADVANTAGES,EXIST_STORE_NAME,EXIST_STORE_ADDR,EXIST_STORE_AREA,EXIST_STORE_RANGE,EXIST_STORE_COMPETITION,"+
		        "NEW_STORE_NAME,NEW_STORE_ADDR,NEW_STORE_AREA,NEW_STORE_RANGE,NEW_STORE_DATE,CHANN_NAME,BUSS_SCOPE,STORE_NAME,PURPOSE_STORE_NAME,TEL";
		*/
		String colType= "string,string,string,string,string,string,string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string,string,string,string,string,string,string,string";
		
		try {
//			ExcelUtil.doExport(response, list, "拓展拜访申请单", tmpContent, tmpContentCn,colType);
			//生成excel
			 HSSFWorkbook workbook =  exportExcel(colType, list);
            //导出excel 
             ExcelUtil.writeExecl(response,workbook,"拓展拜访申请单");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private   HSSFWorkbook exportExcel(String tmpContent, List<ExpandVisitModel> dataList) {
		// 初始一个workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		String[] titles_EN = tmpContent.split(",");
		// 获取第一张
		HSSFSheet sheet = workbook.createSheet("拓展拜访申请单");
		try {
			 // 设置字体   
		    HSSFFont headfont = workbook.createFont();   
		    headfont.setFontName("宋体");   
		    headfont.setFontHeightInPoints((short) 10);// 字体大小   
		    headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
		    
		    HSSFCellStyle headstyle = workbook.createCellStyle();   
		    headstyle.setFont(headfont);   
		    headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		    headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		    headstyle.setLocked(true);   
 
//			headstyle.setFillBackgroundColor(HSSFColor.YELLOW.index);   
////		    headstyle.setFillForegroundColor(IndexedColors.RED.getIndex()); // 前景色
//			headstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);  
//			HSSFCellStyle headstyle2 = workbook.createCellStyle();   
		    
		    headstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); 
		    headstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);   // 填充模式
			
		    headstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());// 左边框的颜色   
		    headstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		    headstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
//		    headstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		    headstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 底部边框颜色
		    
		    headstyle.setBorderLeft(CellStyle.BORDER_THIN);// 边框的大小   
		    headstyle.setBorderRight(CellStyle.BORDER_THIN);// 边框的大小 
		    headstyle.setBorderTop(CellStyle.BORDER_THIN);// 边框的大小 
		    headstyle.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框
	 
		    
		    HSSFCellStyle columnstyle = workbook.createCellStyle(); 
		    columnstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		    columnstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		    columnstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());// 左边框的颜色   
		    columnstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		    columnstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		    columnstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 底部边框颜色
		    
		    columnstyle.setBorderLeft(CellStyle.BORDER_THIN);// 边框的大小   
		    columnstyle.setBorderRight(CellStyle.BORDER_THIN);// 边框的大小 
		    columnstyle.setBorderTop(CellStyle.BORDER_THIN);// 边框的大小 
		    columnstyle.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框
		    
			// 创建合并单元格的第一个单元格数据
			HSSFRow row = sheet.createRow(0);
			HSSFCell c0 = row.createCell(0);
			c0.setCellValue(new HSSFRichTextString("流程编号"));
			HSSFCell c1 = row.createCell(1);
			c1.setCellValue(new HSSFRichTextString("紧急程度"));
			HSSFCell c2 = row.createCell(2);
			c2.setCellValue(new HSSFRichTextString("拜访人"));
			HSSFCell c3 = row.createCell(3);
			c3.setCellValue(new HSSFRichTextString("申请部门"));
			HSSFCell c4 = row.createCell(4);
			c4.setCellValue(new HSSFRichTextString("申请日期"));
			HSSFCell c5 = row.createCell(5);
			c5.setCellValue(new HSSFRichTextString("拜访时间"));
			HSSFCell c6 = row.createCell(6);
			c6.setCellValue(new HSSFRichTextString("拜访方式"));
			HSSFCell c7 = row.createCell(7);
			c7.setCellValue(new HSSFRichTextString("加盟商异议"));
			HSSFCell c8 = row.createCell(8);
			c8.setCellValue(new HSSFRichTextString("解决方案"));
			HSSFCell c9 = row.createCell(9);
			c9.setCellValue(new HSSFRichTextString("支持需求"));
			HSSFCell c10 = row.createCell(10);
			c10.setCellValue(new HSSFRichTextString("竞品信息"));
			HSSFCell c11 = row.createCell(11);
			c11.setCellValue(new HSSFRichTextString("现状及优势"));
			HSSFCell c12 = row.createCell(12);
			c12.setCellValue(new HSSFRichTextString("现有卖场情况"));
			
			HSSFRow row1 = sheet.createRow(1);
			
			HSSFCell c120 = row1.createCell(12);
			c120.setCellValue(new HSSFRichTextString("名称"));
			HSSFCell c13 = row1.createCell(13);
			c13.setCellValue(new HSSFRichTextString("地址"));
			HSSFCell c14 = row1.createCell(14);
			c14.setCellValue(new HSSFRichTextString("面积"));
			HSSFCell c15 = row1.createCell(15);
			c15.setCellValue(new HSSFRichTextString("档次排名"));
			HSSFCell c16 = row1.createCell(16);
			c16.setCellValue(new HSSFRichTextString("进驻主竞品"));

			HSSFCell c17 = row.createCell(17);
			c17.setCellValue(new HSSFRichTextString("近期新开卖场情况调查"));
			
			HSSFCell c170 = row1.createCell(17);  
			c170.setCellValue(new HSSFRichTextString("名称"));
			HSSFCell c18 = row1.createCell(18);
			c18.setCellValue(new HSSFRichTextString("地址"));
			HSSFCell c19 = row1.createCell(19);
			c19.setCellValue(new HSSFRichTextString("面积"));
			HSSFCell c20 = row1.createCell(20);
			c20.setCellValue(new HSSFRichTextString("档次排名"));
			HSSFCell c21 = row1.createCell(21);
			c21.setCellValue(new HSSFRichTextString("开始时间"));

			HSSFCell c22 = row.createCell(22);
			c22.setCellValue(new HSSFRichTextString("目标加盟商拜访"));
			
			HSSFCell c220 = row1.createCell(22);
			c220.setCellValue(new HSSFRichTextString("客户姓名"));
			HSSFCell c23 = row1.createCell(23);
			c23.setCellValue(new HSSFRichTextString("经营品牌"));
			HSSFCell c24 = row1.createCell(24);
			c24.setCellValue(new HSSFRichTextString("进驻卖场"));
			HSSFCell c25 = row1.createCell(25);
			c25.setCellValue(new HSSFRichTextString("意向门店"));
			HSSFCell c26 = row1.createCell(26);
			c26.setCellValue(new HSSFRichTextString("联系电话"));
			
			for(int i=12;i<27;i++){
				HSSFCell cell1 = row1.getCell((short)i);
				if(null != cell1){
					cell1.setCellStyle(headstyle); 
				}
			}
			 
			// 设置合并单元格的区域
			// 四个参数分别是：起始行，起始列，结束行，结束列 
			Region region1 = new Region(0, (short) 0, 1, (short) 0);
			Region region2 = new Region(0, (short) 1, 1, (short) 1);
			Region region3 = new Region(0, (short) 2, 1, (short) 2);
			Region region4 = new Region(0, (short) 3, 1, (short) 3);
			Region region5 = new Region(0, (short) 4, 1, (short) 4);
			Region region6 = new Region(0, (short) 5, 1, (short) 5);
			Region region7 = new Region(0, (short) 6, 1, (short) 6);
			Region region8 = new Region(0, (short) 7, 1, (short) 7);
			Region region9 = new Region(0, (short) 8, 1, (short) 8);
			Region region10 = new Region(0, (short) 9, 1, (short) 9);
			Region region11 = new Region(0, (short) 10, 1, (short) 10);
			Region region12 = new Region(0, (short) 11, 1, (short) 11);
			
			Region region13 = new Region(0, (short) 12, 0, (short) 16);
			Region region17 = new Region(0, (short) 17, 0, (short) 21);
			Region region22 = new Region(0, (short) 22, 0, (short) 26);
			
			sheet.addMergedRegion(region1);
			sheet.addMergedRegion(region2);
			sheet.addMergedRegion(region3);
			sheet.addMergedRegion(region4);
			sheet.addMergedRegion(region5);
			sheet.addMergedRegion(region6);
			sheet.addMergedRegion(region7);
			sheet.addMergedRegion(region8);
			sheet.addMergedRegion(region9);
			sheet.addMergedRegion(region10);
			sheet.addMergedRegion(region11);
			sheet.addMergedRegion(region12);
			sheet.addMergedRegion(region13);
			sheet.addMergedRegion(region17);
			sheet.addMergedRegion(region22);
			// 创建Excel文件
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 12, 16));
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 17, 21));
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 26));
			 
//			RegionUtil.setBorderBottom(border,region, sheet, wb); 
			
			setRegionStyle(headstyle, region1,sheet);
			setRegionStyle(headstyle, region2,sheet);
			setRegionStyle(headstyle, region3,sheet);
			setRegionStyle(headstyle, region4,sheet);
			setRegionStyle(headstyle, region5,sheet);
			setRegionStyle(headstyle, region6,sheet);
			setRegionStyle(headstyle, region7,sheet);
			setRegionStyle(headstyle, region8,sheet);
			setRegionStyle(headstyle, region9,sheet);
			setRegionStyle(headstyle, region10,sheet);
			setRegionStyle(headstyle, region11,sheet);
			setRegionStyle(headstyle, region12,sheet);
			setRegionStyle(headstyle, region13,sheet);
			setRegionStyle(headstyle, region17,sheet);
			setRegionStyle(headstyle, region22,sheet);
			
			if(dataList != null){
				int currRowNum = 0;
				for (int i = 0; i < dataList.size(); i++) {
					if(0 == currRowNum){
						currRowNum = i+2;
					}
					
					row = sheet.createRow(currRowNum);
					ExpandVisitModel expand = (ExpandVisitModel) dataList.get(i);
					// 第四步，创建单元格，并设置值
					row.createCell((short) 0).setCellValue(expand.getEXPAND_VISIT_NO());
					row.createCell((short) 1).setCellValue(expand.getEME_DEGREE());
					row.createCell((short) 2).setCellValue(expand.getVISIT_PEOPLE());
					row.createCell((short) 3).setCellValue(expand.getAPPLY_DEP());
					row.createCell((short) 4).setCellValue(expand.getAPPLY_DATE());
					row.createCell((short) 5).setCellValue(expand.getVISIT_DATE());
					row.createCell((short) 6).setCellValue(expand.getVISIT_TYPE());
					row.createCell((short) 7).setCellValue(expand.getCHANN_REMARK());
					row.createCell((short) 8).setCellValue(expand.getSOLUTION());
					row.createCell((short) 9).setCellValue(expand.getSUPPORT_DEMAND());
					row.createCell((short) 10).setCellValue(expand.getCOMPETITION_INFO());
					row.createCell((short) 11).setCellValue(expand.getADVANTAGES());
					//此数组用来装填子表最大的行数
					int[] arryLength = new int[1];
					createChilsCell(sheet,row,expand.getEXIST_STORE_NAME(),currRowNum,12,arryLength,columnstyle);//现有卖场名称
					createChilsCell(sheet,row,expand.getEXIST_STORE_ADDR(),currRowNum,13,arryLength,columnstyle);//现有卖场地址
					createChilsCell(sheet,row,expand.getEXIST_STORE_AREA(),currRowNum,14,arryLength,columnstyle);//现有卖场面积
					createChilsCell(sheet,row,expand.getEXIST_STORE_RANGE(),currRowNum,15,arryLength,columnstyle);//档次排名
					createChilsCell(sheet,row,expand.getEXIST_STORE_RANGE(),currRowNum,16,arryLength,columnstyle);//进驻主竞品
					
					createChilsCell(sheet,row,expand.getNEW_STORE_NAME(),currRowNum,17,arryLength,columnstyle);//近期新开卖场名称
					createChilsCell(sheet,row,expand.getNEW_STORE_ADDR(),currRowNum,18,arryLength,columnstyle);//近期新开卖场地址
					createChilsCell(sheet,row,expand.getNEW_STORE_AREA(),currRowNum,19,arryLength,columnstyle);//近期新开卖场面积
					createChilsCell(sheet,row,expand.getNEW_STORE_RANGE(),currRowNum,20,arryLength,columnstyle);//近期新开卖场档次排名
					createChilsCell(sheet,row,expand.getNEW_STORE_DATE(),currRowNum,21,arryLength,columnstyle);//开业时间
					
					createChilsCell(sheet,row,expand.getCHANN_NAME(),currRowNum,22,arryLength,columnstyle);//客户姓名
					createChilsCell(sheet,row,expand.getBUSS_SCOPE(),currRowNum,23,arryLength,columnstyle);//经营品牌
					createChilsCell(sheet,row,expand.getSTORE_NAME(),currRowNum,24,arryLength,columnstyle);//进驻卖场
					createChilsCell(sheet,row,expand.getPURPOSE_STORE_NAME(),currRowNum,25,arryLength,columnstyle);//意向门店
					createChilsCell(sheet,row,expand.getTEL(),currRowNum,26,arryLength,columnstyle);//联系电话
					int length = arryLength[0];
					//启始行+要合并的行数，然后减1就是结束合并的那个行数
					for(int j=0;j<12;j++){
						Region regiond = new Region(currRowNum, (short) j, currRowNum+length-1, (short) j);// 四个参数分别是：起始行，起始列，结束行，结束列 
						sheet.addMergedRegion(regiond);
						setRegionStyle(columnstyle, regiond,sheet);
					}
					//合并行后 设置新的行号
					currRowNum = currRowNum +length;
		         }  
			}
		} catch (Exception e) {
			e.printStackTrace();
	   }

		return workbook;
	}
	
	/**
	 * 首行合并单元格 
	 * @param cs
	 * @param region
	 * @param sheet
	 */
	private  void setRegionStyle(CellStyle cs, Region region, HSSFSheet sheet){
		for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {  
			HSSFRow rowd = sheet.getRow(i);
//			if(region1.getColumnFrom() != region1.getColumnTo()){
				for (int j = region.getColumnFrom(); j<=region.getColumnTo(); j++){  
		        	HSSFCell celld = rowd.getCell(j);
		        	if(null == celld){  
		        		celld = rowd.createCell(j);  
	                    celld.setCellValue("");  
	                }  
		        	celld.setCellStyle(cs); 
		        }  
//			}
	        
	    } 
	}
	
	
	/**
	 * 单字段，多值，写入多个单元格
	 * @param sheet
	 * @param row
	 * @param column 
	 * @param index
	 * @param rowNum
	 * @param columnstyle 样式
	 */
	public void createChilsCell(HSSFSheet sheet,HSSFRow row,String column,int index,int rowNum,int[] arryLength,HSSFCellStyle columnstyle ){
		column = StringUtil.nullToSring(column);
		String[] array = column.split("\\|");
		int length = array.length;
		if(length > arryLength[0]){
			arryLength[0]=length;
		}
		for(int n=0;n<length;n++){
			row = sheet.getRow((int) index + n);
			if(null == row){
				row = sheet.createRow((int) index+n);
			}
			HSSFCell cell = row.createCell((short) rowNum);
			cell.setCellValue(array[n]);
			cell.setCellStyle(columnstyle); 
		}
 
	}
	
	
	
	private  void createCell(HSSFRow row, int column,HSSFCellStyle style, int cellType, Object value) {
		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}
		String res = (value == null ? "" : value).toString();
		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
		}
			break;
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellValue(res + "");
		}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(StringUtil.getDouble(res));
		}
			break; // modidy 2015-03-20
		default:
			break;
		}
	} 
	
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
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

	public ExpandVisitService getExpandvisitService() {
		return expandvisitService;
	}

	public void setExpandvisitService(ExpandVisitService expandvisitService) {
		this.expandvisitService = expandvisitService;
	}
}
