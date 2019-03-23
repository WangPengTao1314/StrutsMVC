package com.hoperun.drp.oamg.decorationapp.action;

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
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.decorationallo.model.DecorationalloModel;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.decorationapp.service.DecorationSQService;
import com.hoperun.sys.model.UserBean;

public class DecorationSQAction extends BaseAction {
   
	private   DecorationSQService   decorationSQService;
	
	//增删改查
    private static String PVG_BWS="BS0031701";
    private static String PVG_EDIT="BS0031702";
    private static String PVG_DELETE="BS0031703";
	//报销标准
	private static String PVG_IS_STAD_FLAG  = "BS0030105";
	//报销承担方式
	private static String PVG_BEAR_WAY      = "BS0030106";
	//商场位置类别
    private static String PVG_LOCAL_TYPE = "BS0030107";
    //金额说明
    private static String PVG_AMOUNT_DESC = "BS0030108";
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0031706";
    //流程跟踪
    private static String PVG_TRACE= "BS0031709";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0030101";
    private static String PVG_AUDIT_AUDIT="BS0030102";
    private static String PVG_TRACE_AUDIT="BS0030103";
    private static String PVG_EDIT_AUDIT ="BS0030104";
    
    private static String PVG_BWS_AUDIT_T="BS0035001";
    private static String PVG_AUDIT_AUDIT_T="BS0035002";
	private static String PVG_TRACE_AUDIT_T="BS0035003";
	private static String PVG_EDIT_AUDIT_T ="BS0035004";
	
	//审批流参数
    private static String AUD_TAB_NAME="DRP_CHANN_RNVTN";
    private static String AUD_TAB_KEY="CHANN_RNVTN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_CHANN_RNVTN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.oamg.decorationapp.service.impl.DecorationSQFlowInterface";
	
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
	    String module = ParamUtil.get(request,"module");
		if(module.equals("sh")){
			request.setAttribute("module",module);
			return mapping.findForward("framesT");
		}
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
		 
        UserBean userBean = ParamUtil.getUserBean(request);
		
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String> paramsT = new HashMap<String,String>();
        
	    ParamUtil.putStr2Map(request, "CHANN_RNVTN_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	    ParamUtil.putStr2Map(request, "BUSS_SCOPE", params);
	    ParamUtil.putStr2Map(request, "BUSS_SCOPE", paramsT);
	    ParamUtil.putStr2Map(request, "RNVTN_PROP", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "OPEN_TERMINAL_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "RNVTN_REQ_NAME", params);
	    
	    String BUSS_SCOPE  = ParamUtil.get(request, "BUSS_SCOPE").toString();
	    if(!BUSS_SCOPE.equals("")){
	    	BUSS_SCOPE  =   decorationSQService.queryName(BUSS_SCOPE);
	    	params.put("BUSS_SCOPE", BUSS_SCOPE);
	    }
	    
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
			String RYXXID = userBean.getRYXXID().toString();
			int count = decorationSQService.queryZW(RYXXID);
			if(count==1){
				qx.append(" and DESIGN_ID ='"+userBean.getRYXXID()+"'");
			}
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
	    params.put("CHANN_ChargQuery", "AND ( CHANN_ID IN "+CHANNS_CHARG +"or CREATOR = '"+userBean.getRYXXID()+"')");
	    //字段排序
  		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    ParamUtil.putStr2Map(request, "pageSize", params);
	    page = decorationSQService.pageQuery(params, pageNo);
	    int isManage = decorationSQService.queryStoreManage(userBean.getRYXXID());
	    request.setAttribute("isManage", isManage);
	    request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("module", module);
        request.setAttribute("params", params);
        request.setAttribute("paramsT", paramsT);
        request.setAttribute("search", search);
        request.setAttribute("page", page);  
        if(module.equals("sh")){
        	return mapping.findForward("listSH");
        }
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
	    if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
			Map entry = decorationSQService.loadByConfId(CHANN_RNVTN_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		}
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
	    request.setAttribute("pvg",setPvg(userBean));
	    
	    List<DecorationappModel>  result =  decorationSQService.queryBussScopeT();
	    request.setAttribute("result",result);
	    List<DecorationappModel>  result1=  decorationSQService.queryRnvtnProp();
	    request.setAttribute("result1", result1);
	    List<DecorationappModel>  result2=  decorationSQService.queryLocalType();
	    request.setAttribute("result2", result2);
		return mapping.findForward("toedit");
	}
	
	
	public void toSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
		 if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
				decorationSQService.updateState(CHANN_RNVTN_ID);
			}
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	}
	
	public void toQueryManage (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			String RYXXID = userBean.getRYXXID().toString();
			int count = decorationSQService.queryStoreManage(RYXXID);
			if(count==1){
				String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
				String REIT_AMOUNT_PS = decorationSQService
						.queryReitAmountPS(CHANN_RNVTN_ID);
				if(REIT_AMOUNT_PS==null || REIT_AMOUNT_PS.equals("")){
					result.put("REIT_AMOUNT_PS","");
				}else{
				    result.put("REIT_AMOUNT_PS", REIT_AMOUNT_PS);
				}
			}
			jsonResult = new Gson().toJson(new Result(true, result, ""));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "");
		}
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
		 int countT = decorationSQService.queryRvinState(CHANN_RNVTN_ID);
		 if(countT !=0) {
		 int count = decorationSQService.queryReitDtl(CHANN_RNVTN_ID);
		 if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
				//decorationSQService.txReitDtl(CHANN_RNVTN_ID,userBean,count);
			}
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
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
	    String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");

	    String search = ParamUtil.get(request,"search");
	    String module    = ParamUtil.get(request, "module");
	    
	    if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_EDIT_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
         }
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    IListPage page   = null;
	    IListPage pageT  = null;
	    if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
			Map entry = decorationSQService.loadByConfIdT(CHANN_RNVTN_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
			params.put("CHANN_RNVTN_ID",CHANN_RNVTN_ID);
			ParamUtil.setOrderField(request, paramsT, "t.rnvtn_reit_no", "ASC");
			//page   = decorationSQService.queryDecor(paramsT, pageNo,CHANN_RNVTN_ID);
		}
//	    List<DecorationappModel>  result = decorationSQService.queryDecorT(paramsT);
//	    for(int i=0;i<result.size();i++){
//	    	DecorationappModel model = (DecorationappModel)result.get(i);
//	    }
//	    request.setAttribute("result",result);
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
	    List<DecorationappModel>  result =  decorationSQService.queryBussScopeT();
	    request.setAttribute("result",result);
	    List<DecorationappModel>  result1=  decorationSQService.queryRnvtnProp();
	    request.setAttribute("result1", result1);
	    List<DecorationappModel>  result2=  decorationSQService.queryLocalType();
	    request.setAttribute("result2", result2);
		return mapping.findForward("toedit1");
	}
	
	public ActionForward toEditSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    String userName  = userBean.getXM();  //申请人
	    Map<String,String> params = new HashMap<String,String>();
	    Map<String,String> paramsT = new HashMap<String,String>();
	    String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
	    if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_EDIT_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
         }
	    //params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    IListPage page = null;
	    if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
			Map entry = decorationSQService.loadByConfIdT(CHANN_RNVTN_ID);
			Map entryT = decorationSQService.loadByConfIdTt(CHANN_RNVTN_ID);
			
			request.setAttribute("rst", entry);
			request.setAttribute("rstT", entryT);
			request.setAttribute("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
			
			params.put("CHANN_RNVTN_ID",CHANN_RNVTN_ID);
			ParamUtil.setOrderField(request, paramsT, "t.rnvtn_reit_no", "ASC");
			page = decorationSQService.queryDecor(paramsT, pageNo,CHANN_RNVTN_ID);
		}
	    int rowcount  = decorationSQService.getRowcount(CHANN_RNVTN_ID);
	    
		//权限级别和审批流的封装和状态过滤s
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String time = sdf.format(new Date());
	    request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
	    request.setAttribute("userName",userName);
	    request.setAttribute("date", time);
	    request.setAttribute("state", "未提交");
	    request.setAttribute("page", page);
	    request.setAttribute("pvg",setPvg(userBean));
	    
	    List<DecorationappModel>  result = decorationSQService.queryDecorT(paramsT);
	    for(int i=0;i<result.size();i++){
	    	DecorationappModel model = (DecorationappModel)result.get(i);
	    }
	    request.setAttribute("result",result);
	    request.setAttribute("rowcount", rowcount);
	    String RYXXID = userBean.getRYXXID().toString();
	    String GZZXXID = decorationSQService.queryGZZXXID();
	    int count  = decorationSQService.queryZWT(RYXXID,GZZXXID);
		request.setAttribute("count", count);
	    
	    List<DecorationappModel>  result0 =  decorationSQService.queryBussScopeT();
	    request.setAttribute("result",result0);
	    List<DecorationappModel>  result1=  decorationSQService.queryRnvtnProp();
	    request.setAttribute("result1", result1);
	    List<DecorationappModel>  result2=  decorationSQService.queryLocalType();
	    request.setAttribute("result2", result2);
		return mapping.findForward("toeditsh");
	}
	
	/**
	 * 修改.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData       = ParamUtil.get(request, "jsonData");
        String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
        
        DecorationappModel model = new DecorationappModel();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <DecorationappModel>() {
            }.getType());
        }
        decorationSQService.txEdit(CHANN_RNVTN_ID, model, userBean); 
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    String BUSS_SCOPE_NAME = "";
			String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
			if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
				Map entry = decorationSQService.loadByConfIdF(CHANN_RNVTN_ID);  //品牌
				request.setAttribute("rst", entry);
			 
				Map entryT = decorationSQService.loadT(CHANN_RNVTN_ID);  //商场位置
				System.out.print(entryT.get("BUSS_SCOPE"));
				request.setAttribute("rstT", entryT);
				if(entryT.get("BUSS_SCOPE") != null){
					String BUSS_SCOPE = entryT.get("BUSS_SCOPE").toString();
					String[] BUSS_SCOPEs = BUSS_SCOPE.split(",");
					for(int i=0;i<BUSS_SCOPEs.length;i++){
						BUSS_SCOPE_NAME += decorationSQService.queryName(BUSS_SCOPEs[i].toString())+",";
					}
					BUSS_SCOPE_NAME = BUSS_SCOPE_NAME.substring(0,BUSS_SCOPE_NAME.length()-1);
					request.setAttribute("BUSS_SCOPE_NAME", BUSS_SCOPE_NAME);
				}
				/*
				Map entryTt = decorationSQService.loadTt(CHANN_RNVTN_ID);  //装修性质
				request.setAttribute("rstTt", entryTt);
				*/
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(CHANN_RNVTN_ID);
				request.setAttribute("flowInfoList", flowInfoList);
			}
			return mapping.findForward("detail");
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
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");

        if (StringUtils.isNotEmpty(CHANN_RNVTN_ID)) {
            try {
                decorationSQService.txDelete(CHANN_RNVTN_ID, userBean);
                decorationSQService.clearCaches(CHANN_RNVTN_ID);
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
     * * 提交时
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
            decorationSQService.updateState(CHANN_RNVTN_ID);
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
    public void query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String BUSS_SCOPE = ParamUtil.get(request, "BUSS_SCOPE");
        String TERM_WHICH_NUM = ParamUtil.get(request, "TERM_WHICH_NUM");
        String LOCAL_TYPE     = ParamUtil.get(request, "LOCAL_TYPE");
        UserBean userBean = ParamUtil.getUserBean(request);
 
        String LEDGER_ID = userBean.getLoginZTXXID().toString();
        List result = decorationSQService.queryByBussScope(BUSS_SCOPE,LEDGER_ID,LOCAL_TYPE);
        String minArea = "";
        String stdArea = "";
        String amount  = "";
        String dtldes  = "";
        for(int i=0;i<result.size();i++){
       	System.out.print(result.get(i));
       	DecorationalloModel decor = (DecorationalloModel) result.get(i); 
       	minArea =  decor.getMIN_AREA().toString();
       	stdArea =  decor.getSTD_AREA().toString();
       	amount  =  decor.getSUBST_AMOUNT().toString();
        }
        
        List resultT = decorationSQService.queryTermWhichNum(TERM_WHICH_NUM);
        for(int j=0;j<resultT.size();j++){
        	dtldes  =   resultT.get(j).toString();
        }
        String str = minArea+","+stdArea+","+amount+","+dtldes;
        jsonResult = jsonResult(false, str);
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }
   
    
    public ActionForward  getAuditState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		 
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	    String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
	    Map<String,String> entry = null;
	    entry =  decorationSQService.loadByConfIdT(CHANN_RNVTN_ID);
	    //状态
	    String STATE = entry.get("STATE").toString();
	    //上报使用面积
	    String d = String.valueOf(entry.get("USE_AREA"));
	    Integer AREA = Integer.valueOf(d.substring(0,d.length()));
 
	    //终端ID
	    String TERM_ID  = entry.get("TERM_ID").toString();
	    //商场位置类别
	    String LOCAL_TYPE = entry.get("LOCAL_TYPE").toString();
	    
	    if(STATE.equals("审核通过")){
	    	decorationSQService.updateTerm(AREA,LOCAL_TYPE, TERM_ID);
	    }
	    request.setAttribute("rst", entry.get("STATE"));
	    request.setAttribute("pvg",setPvg(userBean));
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
	}
   
   public void queryT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
       
       PrintWriter writer = getWriter(response);
       String jsonResult = jsonResult();
       String TERM_WHICH_NUM = ParamUtil.get(request, "TERM_WHICH_NUM");
       
       UserBean userBean = ParamUtil.getUserBean(request);

       String LEDGER_ID = userBean.getLoginZTXXID().toString();
       List result = decorationSQService.queryTermWhichNum(TERM_WHICH_NUM);
       String dataDtlDes1 = "";
       String dataDtlDes2 = "";
       for(int i=0;i<result.size();i++){
	       	System.out.print(result.get(i));
	       	DecorationalloModel child = (DecorationalloModel) result.get(i); 
	       	dataDtlDes1 =  child.getDATA_DTL_DES_1().toString();
	       	dataDtlDes2 =  child.getDATA_DTL_DES_2().toString();
       }
       String str = dataDtlDes1;
       jsonResult = jsonResult(false, dataDtlDes1);
       if (null != writer) {
           writer.write(jsonResult);
           writer.close();
       }
   }
   

   public void  commitT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
       
       PrintWriter writer = getWriter(response);
       String jsonResult = jsonResult();
       UserBean userBean = ParamUtil.getUserBean(request);
       String strscale  = ParamUtil.get(request, "strscale");
       String strremark = ParamUtil.get(request, "strremark");
       String CHANN_RNVTN_ID    = ParamUtil.get(request, "CHANN_RNVTN_ID");
       
       String LOCAL_TYPE      = ParamUtil.get(request, "LOCAL_TYPE");     //商场位置类别
       //String LOCAL_TYPE    = decorationSQService.queryName(LOCAL_TYPE_CODE);
       String COMPACT_AMOUNT  = ParamUtil.get(request, "COMPACT_AMOUNT"); //合同签订金额
       String IS_STAD_FLAG    = ParamUtil.get(request, "IS_STAD_FLAG");   //是否标准内
       String REIT_AMOUNT_PS  = ParamUtil.get(request, "REIT_AMOUNT_PS"); //每平米标准参考报销金额
       String REIT_AMOUNT     = ParamUtil.get(request, "REIT_AMOUNT");    //总标准参考报销金额
       
       String REIT_POLICY  = ParamUtil.get(request, "REIT_POLICY");
       String SelType      = ParamUtil.get(request, "SelType");
       String LEDGER_ID = userBean.getLoginZTXXID().toString();
       String[] scales = strscale.split(",");
       String[] remarks = strremark.split(",");
       
       decorationSQService.txEditReit(CHANN_RNVTN_ID,strscale,strremark,SelType);
       if(!LOCAL_TYPE.equals("")) {
          LOCAL_TYPE = decorationSQService.queryName(LOCAL_TYPE);
          decorationSQService.updateByRnvtnId(CHANN_RNVTN_ID, LOCAL_TYPE, COMPACT_AMOUNT, IS_STAD_FLAG,REIT_AMOUNT_PS,REIT_AMOUNT); 
       }
       if(!COMPACT_AMOUNT.equals("")){
    	   if(!LOCAL_TYPE.equals("")) {
    	          LOCAL_TYPE = decorationSQService.queryName(LOCAL_TYPE);
    	          decorationSQService.updateByRnvtnId(CHANN_RNVTN_ID, LOCAL_TYPE, COMPACT_AMOUNT, IS_STAD_FLAG,REIT_AMOUNT_PS,REIT_AMOUNT); 
    	       }
    	   else {
 	              decorationSQService.updateByRnvtnIdT(CHANN_RNVTN_ID,COMPACT_AMOUNT, IS_STAD_FLAG,REIT_AMOUNT_PS,REIT_AMOUNT); 
    	   }
       }
       decorationSQService.updatePolicy(CHANN_RNVTN_ID, REIT_POLICY);
       
       jsonResult = jsonResult(true, "确认成功");
       if (null != writer) {
         writer.write(jsonResult);
         writer.close();
       }
   }
   
   public void showDecor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

       PrintWriter writer = getWriter(response);
       UserBean userBean = ParamUtil.getUserBean(request);
       String jsonResult = jsonResult();
       String reitNo = "";
       String sacle  = "";
       String remark = "";
       String total  = "";
       if (null != writer) {
    	   jsonResult = jsonResult(true, total);
           writer.write(jsonResult);
           writer.close();
       }
   }

	public DecorationSQService getDecorationSQService() {
		return decorationSQService;
	}

	public void setDecorationSQService(DecorationSQService decorationSQService) {
		this.decorationSQService = decorationSQService;
	}
	
	public void toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String RNVTN_PROP = ParamUtil.get(request, "RNVTN_PROP");
             Map<String,Object>  result = new  HashMap<String,Object>();
             String  RNVTN_PROPt = decorationSQService.toQRnvtnPropt(RNVTN_PROP);
             result.put("RNVTN_PROP", RNVTN_PROPt);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	jsonResult = jsonResult(false, "保存失败");
		}
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	public void toQueryLocalType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
        	 String  LOCAL_TYPEt = "";
        	 String  BUSS_SCOPEt = "";
             String LOCAL_TYPE = ParamUtil.get(request, "LOCAL_TYPE");
             String BUSS_SCOPE = ParamUtil.get(request, "BUSS_SCOPE");
             
             Map<String,Object>  result = new  HashMap<String,Object>();
             if(!LOCAL_TYPE.isEmpty()){
                LOCAL_TYPEt = decorationSQService.toQLocalType(LOCAL_TYPE);
             }
             if(!BUSS_SCOPE.isEmpty()){
                BUSS_SCOPEt = decorationSQService.toQBussScope(BUSS_SCOPE);
             }
             result.put("LOCAL_TYPE", LOCAL_TYPEt);
             result.put("BUSS_SCOPE", BUSS_SCOPEt);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	//jsonResult = jsonResult(false, "保存失败");
		}
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	public void toReQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String RNVTN_PROP = ParamUtil.get(request, "RNVTN_PROP");
             Map<String,Object>  result = new  HashMap<String,Object>();
             String  RNVTN_PROPt = decorationSQService.toReQRnvtnPropt(RNVTN_PROP);
             result.put("RNVTN_PROP", RNVTN_PROPt);
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
	 * 打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	 	 String CHANN_RNVTN_ID = request.getParameter("CHANN_RNVTN_ID").trim();
	 	 StringBuffer paramSql=new StringBuffer();
	 	 paramSql.append("Decorationapprint_New.raq&CHANN_RNVTN_ID=").append(CHANN_RNVTN_ID);
	 	/**
		 StringBuffer advSql = new StringBuffer();
		 advSql.append("rptSql=select ")
		 		.append(" p.CHANN_RNVTN_ID,")
		 		.append(" p.CHANN_RNVTN_NO,")
		 		.append(" to_char(p.RNVTN_REQ_DATE,'yyyy-MM-dd') RNVTN_REQ_DATE,")
		 		.append(" p.CHANN_ID,")
		 		.append(" p.CHANN_NO,")
		 		.append(" p.CHANN_NAME,")
		 		.append(" p.TERM_ID,")
		 		.append(" p.TERM_NO, ")
		 		.append(" p.TERM_NAME,")
		 		.append(" p.RNVTN_REQ_ID,")
		 		.append(" p.RNVTN_REQ_NAME,")
		 		.append(" p.CHANN_PERSON_CON,")
		 		.append(" p.CHANN_TEL, ")
		 		.append(" p.AREA_ID,")
		 		.append(" p.AREA_NO,")
		 		.append(" p.AREA_NAME, ")
		 		.append(" p.AREA_MANAGE_ID, ")
		 		.append(" p.AREA_MANAGE_TEL,")
		 		.append(" p.AREA_MANAGE_NAME,")
		 		.append(" p.OPEN_TERMINAL_REQ_NO, ")
		 		.append(" p.DESIGN_PERSON,")
		 		.append(" p.RNVTN_REQ_DATE,")
		 		.append(" p.SALE_STORE_NAME,")
		 		.append(" p.ADDRESS,")
		 		.append(" p.REIT_AMOUNT_PS,")
		 		.append(" p.AMOUNT_DESC,")
		 		.append(" p.PLAN_SBUSS_DATE,")
		 		.append(" (SELECT d1.DATA_DTL_NAME FROM PROJECT_DATA_DICTIONARY_DTL d1 where d1.DATA_DTL_CODE = p.RNVTN_PROP  AND d1.DELFLAG=0 ) RNVTN_PROP,")
		 		.append(" p.USE_AREA,")
		 		.append(" (SELECT d1.DATA_DTL_NAME FROM PROJECT_DATA_DICTIONARY_DTL d1 where d1.DATA_DTL_CODE =  p.LOCAL_TYPE  AND d1.DELFLAG=0 ) LOCAL_TYPE,")
		 		.append(" d.DATA_DTL_NAME BUSS_SCOPE,")
		 		.append(" (case when  p.IS_STAD_FLAG =1 then '一、二季度' else '三、四季度' end) IS_STAD_FLAG,")
		 		.append(" (case when p.BEAR_WAY =1 THEN '三次报销' ELSE '特殊政策'END) BEAR_WAY, ")
		 		.append(" (case when p.BEAR_WAY =1 THEN '装修完成验收合格报50%，满1年25%，满2年25%' ELSE  p.SPEC_CONTENT END) SPEC_CONTENT,")
		 		.append(" p.REMARK")
		 		.append(" from  DRP_CHANN_RNVTN p  LEFT JOIN PROJECT_DATA_DICTIONARY_DTL d on d.DATA_DTL_CODE = p.BUSS_SCOPE ")
		 		.append(" where p.DEL_FLAG = ").append(BusinessConsts.DEL_FLAG_COMMON).append(" and p.CHANN_RNVTN_ID ='").append(CHANN_RNVTN_ID.trim()).append("' ;");
		 		
		 
		 StringBuffer dtlSql=new StringBuffer();
		 dtlSql.append(" dtlSql=   SELECT t1.NEXTOPERATORNAME, t1.REMARK, t1.OPERATETIME,t1.OPERATORNAME FROM ( SELECT a.NEXTOPERATORNAME, ")
		 	   .append("  lead(a.remark) over(order by a.operatetime) REMARK,")
		 	   .append("  lead(a.operatorname) over(order by a.operatetime) OPERATORNAME,")
		 	   .append("  LEAD(to_char(a.operatetime, 'yyyy-MM-DD HH24:MI:SS')) OVER(order by a.operatetime) OPERATETIME ")
		 	   .append("  from T_SYS_FLOWTRACE a ")
		 	   .append("  where a.BUSINESSID = '").append(CHANN_RNVTN_ID.trim()).append("'")
		 	   .append("  ORDER BY a.OPERATETIME ) t1 where t1.NEXTOPERATORNAME is not null ")
		 	   .append("  and t1.OPERATORNAME IS NOT NULL ");
		 
		 StringBuffer paymentSql=new StringBuffer();
		 StringBuffer sql=new StringBuffer();
		 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString());
		 // 报表路径名称
		 request.setAttribute("reportFileName", "Decorationapprint_New.raq");
		 **/
		 // 要传递的宏参数！！！
		 request.setAttribute("params", paramSql.toString());
		 
		 return mapping.findForward("flashReportPrint");
	}
	
	// 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "CHANN_RNVTN_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "OPEN_TERMINAL_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "RNVTN_REQ_NAME", params);
		
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
		}
		if(module.equals("sh")){
			String RYXXID = userBean.getRYXXID().toString();
			int count = decorationSQService.queryZW(RYXXID);
			if(count==1){
				qx.append(" and DESIGN_ID ='"+userBean.getRYXXID()+"'");
			}
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
	    params.put("CHANN_ChargQuery", "AND ( CHANN_ID IN "+CHANNS_CHARG +"or CREATOR = '"+userBean.getRYXXID()+"')");
	    //字段排序
  		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	 
		List list = decorationSQService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="装修申请单号,申请人,申请日期,状态,评估单号,所属战区,渠道编号,渠道名称," +
		        "终端编号,终端名称,"+
				"设计师,商场名称,经营品牌,装修性质,商场位置类别,计划开业时间,实际使用面积(平米),区域经理, 详细地址," +
				"报销金额(元),总额(元),特殊说明";
		String tmpContent = "CHANN_RNVTN_NO,RNVTN_REQ_NAME,RNVTN_REQ_DATE,STATE,OPEN_TERMINAL_REQ_NO,AREA_NAME,CHANN_NO,CHANN_NAME," +
		        "TERM_NO,TERM_NAME,"+
				"DESIGN_PERSON,SALE_STORE_NAME,BUSS_SCOPE,RNVTN_PROP,LOCAL_TYPE,PLAN_SBUSS_DATE,USE_AREA,AREA_MANAGE_NAME,ADDRESS," +
				"REIT_AMOUNT_PS,REIT_AMOUNT,REMARK";
		String colType= "string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "装修申请单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void isStoreManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		// 修改权限和门店建设部设计师审核
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_EDIT) && !QXUtil.checkMKQX(
						userBean, PVG_EDIT_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String,Object>  result = new HashMap<String,Object>();
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
			String REIT_AMOUNT_PS = decorationSQService.queryReitAmount(CHANN_RNVTN_ID);
			if(REIT_AMOUNT_PS == null || REIT_AMOUNT_PS.equals("")){
				REIT_AMOUNT_PS = "0";
			}
		    result.put("REIT_AMOUNT_PS", REIT_AMOUNT_PS);
		    String GZZXXID = decorationSQService.queryGZZXXID();
		    String RYXXID = userBean.getRYXXID().toString();
		    int count  = decorationSQService.queryZWT(RYXXID,GZZXXID);
//		    if(count>0&&!"0".equals(REIT_AMOUNT_PS)){   //count =1表示门店建设经理
//		    	decorationSQService.txStoreManage(CHANN_RNVTN_ID);
//		    }
			result.put("count", count);
		    
			jsonResult = new Gson().toJson(new Result(true, result, ""));
		}catch (Exception e) {
			jsonResult = jsonResult(false, e.getMessage());
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toCommitT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String CHANN_RNVTN_ID = request.getParameter("CHANN_RNVTN_ID");
            List <DecorationappModel> list = decorationSQService.childQuery(CHANN_RNVTN_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            jsonResult = jsonResult(true, "提交成功");
        } catch (Exception e) {
           jsonResult = jsonResult(false, "提交失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	/**
	 * 审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toAuditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String CHANN_RNVTN_ID = request.getParameter("CHANN_RNVTN_ID");
            List <DecorationappModel> list = decorationSQService.childQuery(CHANN_RNVTN_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            jsonResult = jsonResult(true, "审核成功");
        } catch (Exception e) {
           jsonResult = jsonResult(false, "审核失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	
	/**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
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
	        pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE) );
	        pvgMap.put("PVG_IS_STAD_FLAG", QXUtil.checkPvg(userBean, PVG_IS_STAD_FLAG) );
	        pvgMap.put("PVG_BEAR_WAY", QXUtil.checkPvg(userBean, PVG_BEAR_WAY) );
	        pvgMap.put("PVG_LOCAL_TYPE", QXUtil.checkPvg(userBean, PVG_LOCAL_TYPE));
	        pvgMap.put("PVG_AMOUNT_DESC", QXUtil.checkPvg(userBean, PVG_AMOUNT_DESC));
	        pvgMap.put("PVG_BWS_AUDIT_T", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT_T));
	        pvgMap.put("PVG_AUDIT_AUDIT_T",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT_T));
	        pvgMap.put("PVG_TRACE_AUDIT_T",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT_T));
	        pvgMap.put("PVG_EDIT_AUDIT_T ",QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT_T));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
} 
