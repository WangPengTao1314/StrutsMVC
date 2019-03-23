package com.hoperun.drp.oamg.decorationreit.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.decorationreit.model.DecorationreitModel;
import com.hoperun.drp.oamg.decorationreit.service.DecorationreitService;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.sys.model.UserBean;

/**
 * 渠道管理-装修管理-装修报销申请单维护
 * @author zhang_zhongbin
 *
 */
public class DecorationreitAction extends BaseAction {
   
	/**日志**/
	private Logger logger = Logger.getLogger(DecorationreitAction.class);
	private   DecorationreitService   decorationreitService;
	
	 //增删改查
    private static String PVG_BWS = "BS0031801";
    private static String PVG_EDIT = "BS0031802";
    private static String PVG_DELETE = "BS0031803";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE = "BS0031804";
    //维护页面流程跟踪
    private static String PVG_TRACE = "BS0031805";
    //合同金额
    private static String PVG_SALE_COMPACT_AMOUNT = "BS0030312";
    
    //审核模块
    private static String PVG_BWS_AUDIT = "BS0030301";
    private static String PVG_AUDIT_AUDIT = "BS0030302";
    private static String PVG_TRACE_AUDIT = "BS0030303";
    
    //门店使用面积
    private static String PVG_USE_AREA = "BS0030304";
    //实际报销金额
    private static String PVG_REAL_REIT_AMOUNT = "BS0030305";
    //金额说明
    private static String PVG_AMOUNT_DESC = "BS0030306";
    
    //审核修改
    private static String PVG_EDIT_AUDIT = "BS0030304";
    //审核修改 装修完成时间                         
    private static String PVG_AUDIT_EDIT_RNVTN_FISH_DATE = "BS0030305";
    //审核修改 装修图纸面积
    private static String PVG_AUDIT_EDIT_DRAW_AREA = "BS0030306";
    
    //审核修改 装修 图纸设计完成时间
    private static String PVG_AUDIT_EDIT_DRAW_FISH_DATE = "BS0030307";
    //审核修改 装修 保证金
    private static String PVG_AUDIT_EDIT_DDEPOSIT= "BS0030308";
    //审核修改 装修 处罚意见
    private static String PVG_AUDIT_EDIT_DPUNISH_REMARK = "BS0030309";
    //审核修改 装修 报销相关附件
    private static String PVG_AUDIT_EDIT_DATT_PATH = "BS0030310";
    //审核修改 装修 实际报销金额
    private static String PVG_AUDIT_EDIT_DREAL_REIT_AMOUNT= "BS0030311";
    
	//审批流参数
    private static String AUD_TAB_NAME = "DRP_RNVTN_REIT_REQ";
    private static String AUD_TAB_KEY = "RNVTN_REIT_REQ_ID";
	private static String AUD_BUSS_STATE = "STATE";
    private static String AUD_BUSS_TYPE ="DRP_RNVTN_REIT_REQ_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.drp.oamg.decorationreit.service.impl.DecorationreitFlowInterface";
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
	 * * 查询
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
		
        UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
        }
        Map<String,String> params = new HashMap<String,String>();
        
        //装修报销申请单编号
        ParamUtil.putStr2Map(request, "RNVTN_REIT_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_RNVTN_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);//区域经理
	    ParamUtil.putStr2Map(request, "RNVTN_PROP", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
		
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
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",qx.toString()));
		if(module.equals("sh")){
			String RYXXID = userBean.getRYXXID().toString();
			int count = decorationreitService.queryZW(RYXXID);
			if(count==1){
				sb.append(" and DESIGN_ID ='"+userBean.getRYXXID()+"'");
			}
		}
		params.put("QXJBCON", sb.toString());
		  //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
 	    params.put("channChrg",CHANNS_CHARG);
 	    ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = decorationreitService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("module", module);
		request.setAttribute("page", page);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("search", search);
	 
		request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
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
		//修改权限  和 门店建设部设计师审核  
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	    String RNVTN_REIT_REQ_ID    = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
	    Map<String,String> entry = null;
	    if (StringUtils.isNotEmpty(RNVTN_REIT_REQ_ID)) {
	    	entry = decorationreitService.loadByIdT(RNVTN_REIT_REQ_ID);
		}else{
			entry = new HashMap<String,String>();
			entry.put("STATE", BusinessConsts.UNCOMMIT);
			entry.put("REQ_ID", userBean.getRYXXID());
			entry.put("REQ_NAME", userBean.getXM());
			entry.put("REQ_DATE", DateUtil.format(new Date(), "yyyy-MM-dd"));
		}
	    request.setAttribute("rst", entry);
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	    
	    List<DecorationreitModel>  result =  decorationreitService.queryBussScopeT();
	    request.setAttribute("result",result);
	    List<DecorationreitModel>  result1=  decorationreitService.queryRnvtnProp();
	    request.setAttribute("result1", result1);
	    List<DecorationreitModel>  result2=  decorationreitService.queryLocalType();
	    request.setAttribute("result2", result2);
	    int count =  decorationreitService.getRowcount();
	    request.setAttribute("count", count);
	    
	    String RYXXID = userBean.getRYXXID().toString();
		int counts = decorationreitService.queryZW(RYXXID);
		request.setAttribute("counts",counts);
		
	    String module = ParamUtil.get(request,"module");
	    request.setAttribute("module", module);
	    request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("toedit");
	}
	
	public ActionForward  toEdit1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		//修改权限  和 门店建设部设计师审核  
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	    String RNVTN_REIT_REQ_ID    = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
	    Map<String,String> entry = null;
	    if (StringUtils.isNotEmpty(RNVTN_REIT_REQ_ID)) {
	    	entry = decorationreitService.loadByIdT(RNVTN_REIT_REQ_ID);
		}else{
			entry = new HashMap<String,String>();
			entry.put("STATE", BusinessConsts.UNCOMMIT);
			entry.put("REQ_ID", userBean.getRYXXID());
			entry.put("REQ_NAME", userBean.getXM());
			entry.put("REQ_DATE", DateUtil.format(new Date(), "yyyy-MM-dd"));
		}
	    request.setAttribute("rst", entry);
	    request.setAttribute("pvg",setPvg(userBean));
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit1");
	}
	
	public ActionForward  getAuditState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		 
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	    String RNVTN_REIT_REQ_ID    = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
	    Map<String,String> entry = null;
	    entry = decorationreitService.loadByIdT(RNVTN_REIT_REQ_ID);
	    //状态
	    String STATE = entry.get("STATE").toString();
	    //装修完成时间
	    String LAST_DECOR_TIME = entry.get("RNVTN_FISH_DATE").toString();
	    //终端ID
	    String TERM_ID  = entry.get("TERM_ID").toString();
	    
	    if(STATE.equals("审核通过")){
	    	decorationreitService.updateTerm(LAST_DECOR_TIME, TERM_ID);
	    }
	    request.setAttribute("pvg",setPvg(userBean));
	    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
	}
	
	
	/**
	 * 新增的时候 自动判断是第几次申请
	 * 如果不是首次数据项自动带出
	 * 
	 */
	public void loadModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		//修改权限和门店建设部设计师审核  
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
             DecorationreitModel  result = decorationreitService.queryJudgeModel(CHANN_RNVTN_ID);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	logger.error(e);
        	jsonResult = jsonResult(false, "保存失败");
		}
       
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	public void loadModelT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String CHANN_ID = ParamUtil.get(request, "CHANN_ID").toString();
             String AREA_ID  = ParamUtil.get(request, "AREA_ID").toString();
             String AREA_NAME= ParamUtil.get(request, "AREA_NAME").toString();
             Date  date = new Date();
             String YEAR  = String.valueOf(date.getYear()+1900);
             Map<String,Object>  result = decorationreitService.queryJudgeModelT(CHANN_ID, YEAR);
             String  FNSH_RATE = decorationreitService.queryFnshRate(CHANN_ID,YEAR);
             String  YEAR_PLAN_AMOUNT = decorationreitService.queryYearPlanAmount(CHANN_ID,YEAR);
             result.put("FNSH_RATE", FNSH_RATE);
             result.put("YEAR_AMOUNT", YEAR_PLAN_AMOUNT);
             //获取战区信息
             if(AREA_NAME.contains("直营")){
            	 result.put("WAREA_NAME", AREA_NAME);
             }else{
            	 String WAREA = decorationreitService.queryWareaName(AREA_ID);
            	 result.put("WAREA_NAME", WAREA);
             }
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	logger.error(e);
        	jsonResult = jsonResult(false, "保存失败");
		}
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 修改.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		//修改权限和门店建设部设计师审核  
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT) && !QXUtil.checkMKQX(userBean,PVG_EDIT_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		
		PrintWriter writer = getWriter(response);
	    String jsonResult = jsonResult();
        try{
        	 String jsonData = ParamUtil.get(request, "jsonData");
             String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
             
             DecorationreitModel model = new DecorationreitModel();
             if (StringUtils.isNotEmpty(jsonData)) {
                 model = new Gson().fromJson(jsonData, new TypeToken <DecorationreitModel>(){}.getType());
             }
             decorationreitService.txEdit(RNVTN_REIT_REQ_ID, model, userBean); 
//             String TERM_ID = model.getTERM_ID().toString();
//             List<Map<String,String>> result = decorationreitService.queryTermState(TERM_ID);
//     		 if(result.size()==0){
// 			   jsonResult = jsonResult(false, "该终端为非正常营业状态，不能提交报销申请");
// 		     }
//     		 else{
//        	   decorationreitService.txEdit(RNVTN_REIT_REQ_ID, model, userBean); 
//     		 }
        	 
        }catch (Exception e) {
        	logger.error(e);
        	jsonResult = jsonResult(false, "保存失败");
		}
	 
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
		if (StringUtils.isNotEmpty(RNVTN_REIT_REQ_ID)) {
			Map<String,String> entry = decorationreitService.loadById(RNVTN_REIT_REQ_ID);
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(RNVTN_REIT_REQ_ID);
			request.setAttribute("flowInfoList", flowInfoList);
			
		}
		return mapping.findForward("todetail");
	}
	
	public void toQueryNameByCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String BUSS_SCOPE = ParamUtil.get(request, "BUSS_SCOPE");
             Map<String,Object>  result = new  HashMap<String,Object>();
             String   SCOPE = decorationreitService.toQBussScope(BUSS_SCOPE);
             result.put("BUSS_SCOPE", SCOPE);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
             System.out.print("jsounResult");
        }catch (Exception e) {
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
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");

        if (StringUtils.isNotEmpty(RNVTN_REIT_REQ_ID)) {
            try {
                decorationreitService.txDelete(RNVTN_REIT_REQ_ID, userBean);
                decorationreitService.clearCaches(RNVTN_REIT_REQ_ID);
                jsonResult = jsonResult(true, "删除成功");
            } catch (Exception e) {
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
            String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
            
        } catch (Exception e) {
           e.printStackTrace();
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
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String RNVTN_REIT_REQ_ID = request.getParameter("RNVTN_REIT_REQ_ID");
            List <DecorationreitModel> list = decorationreitService.childQuery(RNVTN_REIT_REQ_ID);
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
            String RNVTN_REIT_REQ_ID = request.getParameter("RNVTN_REIT_REQ_ID");
            List <DecorationreitModel> list = decorationreitService.childQuery(RNVTN_REIT_REQ_ID);
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
     * check预算额度
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
	public void isCheckMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,Object>  result = new HashMap<String,Object>();
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
			BudgetquotaModel  quota =  decorationreitService.qyeryQuotaAmount(RNVTN_REIT_REQ_ID);
		    String BUDGET_QUOTA = "";
		    String FREEZE_BUDGET_QUOTA = "";
		    String USE_BUDGET_QUOTA = "";
		    String REAL_REIT_AMOUNT = quota.getREAL_REIT_AMOUNT();
		    if(REAL_REIT_AMOUNT != null){
		    	BUDGET_QUOTA = quota.getBUDGET_QUOTA();
		    }else{
		    	BUDGET_QUOTA = "0";
		    }
		    if(quota.getFREEZE_BUDGET_QUOTA() !=null){
		    	FREEZE_BUDGET_QUOTA = quota.getFREEZE_BUDGET_QUOTA();
		    }else{
		    	FREEZE_BUDGET_QUOTA = "0";
		    }
		    if(quota.getUSE_BUDGET_QUOTA() !=null){
		    	USE_BUDGET_QUOTA = quota.getUSE_BUDGET_QUOTA();
		    }else{
		    	USE_BUDGET_QUOTA = "0";
		    }
		    result.put("BUDGET_QUOTA", BUDGET_QUOTA);
		    result.put("FREEZE_BUDGET_QUOTA", FREEZE_BUDGET_QUOTA);
		    result.put("USE_BUDGET_QUOTA", USE_BUDGET_QUOTA);
		    result.put("REAL_REIT_AMOUNT", REAL_REIT_AMOUNT);
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
     * 获取 装修报销明细
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
   public void getReimbursement(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response){
    	 
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	//装修申请单ID
            String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
            List<Map<String,String>> result = decorationreitService.getReimbursement(CHANN_RNVTN_ID);
            jsonResult = new Gson().toJson(new Result(true, result, ""));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }
   
	public void isStoreManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,Object>  result = new HashMap<String,Object>();
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "RNVTN_REIT_REQ_ID");
			String REAL_REIT_AMOUNT = decorationreitService.queryReitAmount(RNVTN_REIT_REQ_ID);
			if(REAL_REIT_AMOUNT == null || REAL_REIT_AMOUNT.equals("")){
				REAL_REIT_AMOUNT = "0";
			}
		    result.put("REAL_REIT_AMOUNT", REAL_REIT_AMOUNT);
		    String GZZXXID = decorationreitService.queryGZZXXID();
		    String RYXXID = userBean.getRYXXID().toString();
		    int count  = decorationreitService.queryZWT(RYXXID,GZZXXID);
		    
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
    * 计算已经报销的 报销金额
    * @param mapping
    * @param form
    * @param request
    * @param response
    */
  public void sumCanReturnAmout(ActionMapping mapping, ActionForm form, 
  		HttpServletRequest request, HttpServletResponse response){
	   
       PrintWriter writer = getWriter(response);
       String jsonResult = jsonResult();
      
       try {
       	  //装修申请单ID
           String CHANN_RNVTN_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");
           Map<String,String> result = decorationreitService.sumCanReturnAmout(CHANN_RNVTN_ID);
           jsonResult = new Gson().toJson(new Result(true, result, ""));
       } catch (Exception e) {
          e.printStackTrace();
       }
       if (null != writer) {
           writer.write(jsonResult);
           writer.close();
       }
	   
  }
  
	public DecorationreitService getDecorationreitService() {
		return decorationreitService;
	}

	public void setDecorationreitService(DecorationreitService decorationreitService) {
		this.decorationreitService = decorationreitService;
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
	 	 String RNVTN_REIT_REQ_ID = request.getParameter("RNVTN_REIT_REQ_ID").trim();
	 	StringBuffer paramSql=new StringBuffer();
	 	 paramSql.append("Decorationreit.raq&RNVTN_REIT_REQ_ID=").append(RNVTN_REIT_REQ_ID);
	 	 /**
		 StringBuffer advSql = new StringBuffer();
		 advSql.append("rptSql=select ")
		 		.append(" p.RNVTN_REIT_REQ_ID,")
		 		.append(" p.RNVTN_REIT_REQ_NO,")
		 		.append(" to_char(p.REQ_DATE,'yyyy-MM-dd') REQ_DATE,")
		 		.append(" p.REQ_NAME,")
		 		.append(" p.AREA_NAME,")
		 		.append(" p.CHANN_RNVTN_NO,")
		 		.append(" p.TERM_NO, ")
		 		.append(" p.TERM_NAME,")
		 		.append(" p.OPEN_SALE_YEAR,")
		 		.append(" p.DESIGN_PERSON,")
		 		.append(" p.CHANN_NAME,")
		 		.append(" p.SALE_STORE_NAME, ")
		 		.append(" p.ADDRESS,")
		 		.append(" (SELECT d.DATA_DTL_NAME FROM PROJECT_DATA_DICTIONARY_DTL d where d.DATA_DTL_CODE = p.BUSS_SCOPE  AND d.DELFLAG=0) BUSS_SCOPE, ")
		 		.append(" p.USE_AREA, ")
		 		.append(" (SELECT d.DATA_DTL_NAME FROM PROJECT_DATA_DICTIONARY_DTL d where d.DATA_DTL_CODE = p.LOCAL_TYPE  AND d.DELFLAG=0) LOCAL_TYPE, ")
		 		.append(" (SELECT d.DATA_DTL_NAME FROM PROJECT_DATA_DICTIONARY_DTL d where d.DATA_DTL_CODE = p.RNVTN_PROP  AND d.DELFLAG=0) RNVTN_PROP, ")
		 		.append(" p.IS_NORTH,")
		 		.append(" p.BUDGET_ITEM_NAME, ")
		 		.append(" p.DESIGN_PERSON,")
		 		.append(" p.TOTAL_REITED_NUM,")
		 		.append(" p.REITED_NUM,")
		 		.append(" p.REIT_AMOUNT,")
		 		.append(" p.REAL_REIT_AMOUNT,")
		 		.append(" p.AREA_MANAGE_NAME,")
		 		.append(" p.SALE_COMPACT_AMOUNT,")
		 		.append(" p.YEAR_GOODS_AMOUNT,")
		 		.append(" p.QUARTER_RATE,")
		 		.append(" p.IS_VIOLATE_REMARK")
		 		.append(" from  DRP_RNVTN_REIT_REQ p where p.DEL_FLAG =").append(BusinessConsts.DEL_FLAG_COMMON)
		 		.append(" and p.RNVTN_REIT_REQ_ID ='").append(RNVTN_REIT_REQ_ID.trim()).append("'; ");
		 
		 
		 StringBuffer dtlSql=new StringBuffer();
		 dtlSql.append(" dtlSql=   SELECT t1.NEXTOPERATORNAME, t1.REMARK, t1.OPERATETIME,t1.OPERATORNAME FROM ( SELECT a.NEXTOPERATORNAME, ")
		 	   .append("  lead(a.remark) over(order by a.operatetime) REMARK,")
		 	   .append("  lead(a.operatorname) over(order by a.operatetime) OPERATORNAME,")
		 	   .append("  LEAD(to_char(a.operatetime, 'yyyy-MM-DD HH24:MI:SS')) OVER(order by a.operatetime) OPERATETIME " )
		 	   .append("  from T_SYS_FLOWTRACE a ")
		 	   .append("  where a.BUSINESSID = '").append(RNVTN_REIT_REQ_ID.trim()).append("'")
		 	   .append("  ORDER BY a.OPERATETIME ) t1 where t1.NEXTOPERATORNAME is not null ")
		 	   .append("  and t1.OPERATORNAME IS NOT NULL ");
		 
		 StringBuffer paymentSql=new StringBuffer();
		 StringBuffer sql=new StringBuffer();
		 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString());
		 // 报表路径名称
		 request.setAttribute("reportFileName", "Decorationreit.raq");
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
		ParamUtil.putStr2Map(request, "RNVTN_REIT_REQ_NO", params);
		ParamUtil.putStr2Map(request, "CHANN_RNVTN_NO", params);
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
        String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
		//权限级别和审批流的封装
	    String channChrg = userBean.getCHANNS_CHARG();
	    params.put("channChrg", channChrg);
        //权限级别和审批流的封装和状态过滤
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.REQ_DATE", "DESC");
		params.put("QXJBCON", sb.toString());
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		List list = decorationreitService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="装修报销申请单编号,状态,装修申请单编号,所属战区,门店编号,门店名称,开店年份,设计师," +
				"加盟商,商场名称,商场地址,门店使用面积,经营品牌,商场位置类别,装修性质,是否北方公司,报销次数,第几次报销,"+
				"报销金额,实际报销金额,申请人,申请时间,合同金额(万元),本年度至今发货金额(万元),累计四个季度实际完成,有无违反现象";
		String tmpContent = "RNVTN_REIT_REQ_NO,STATE,CHANN_RNVTN_NO,AREA_NAME,TERM_NO,TERM_NAME,OPEN_SALE_YEAR,DESIGN_PERSON," +
				"CHANN_NAME,SALE_STORE_NAME,ADDRESS,USE_AREA,BUSS_SCOPE,LOCAL_TYPE,RNVTN_PROP,IS_NORTH,TOTAL_REITED_NUM,REITED_NUM,"+
				"REIT_AMOUNT,REAL_REIT_AMOUNT,REQ_NAME,REQ_DATE,SALE_COMPACT_AMOUNT,YEAR_GOODS_AMOUNT,QUARTER_RATE,IS_VIOLATE_REMARK";
		String colType= "string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "装修报销申请单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
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
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE) );
	    	
	    	pvgMap.put("PVG_EDIT_AUDIT",QXUtil.checkPvg(userBean,PVG_EDIT_AUDIT));
	    	pvgMap.put("PVG_AUDIT_EDIT_RNVTN_FISH_DATE",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_RNVTN_FISH_DATE));
	    	pvgMap.put("PVG_AUDIT_EDIT_DRAW_AREA",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DRAW_AREA));
	    	pvgMap.put("PVG_AUDIT_EDIT_DRAW_FISH_DATE",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DRAW_FISH_DATE));
	    	pvgMap.put("PVG_AUDIT_EDIT_DDEPOSIT",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DDEPOSIT));
	    	
	    	pvgMap.put("PVG_AUDIT_EDIT_DPUNISH_REMARK",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DPUNISH_REMARK));
	    	pvgMap.put("PVG_AUDIT_EDIT_DATT_PATH",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DATT_PATH));
	    	pvgMap.put("PVG_AUDIT_EDIT_DREAL_REIT_AMOUNT",QXUtil.checkPvg(userBean, PVG_AUDIT_EDIT_DREAL_REIT_AMOUNT));
	    	pvgMap.put("PVG_SALE_COMPACT_AMOUNT", QXUtil.checkPvg(userBean, PVG_SALE_COMPACT_AMOUNT));
	    	pvgMap.put("PVG_USE_AREA", QXUtil.checkPvg(userBean, PVG_USE_AREA));
	    	pvgMap.put("PVG_REAL_REIT_AMOUNT", QXUtil.checkPvg(userBean, PVG_REAL_REIT_AMOUNT));
	    	pvgMap.put("PVG_AMOUNT_DESC", QXUtil.checkPvg(userBean, PVG_AMOUNT_DESC));
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
} 
