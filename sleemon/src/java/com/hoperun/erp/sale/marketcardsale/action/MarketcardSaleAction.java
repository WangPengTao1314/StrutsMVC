package com.hoperun.erp.sale.marketcardsale.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleModel;
import com.hoperun.erp.sale.marketcardsale.service.MarketcardSaleService;
import com.hoperun.sys.model.UserBean;
/**
 * 卡券销售
 * @author zhang_zhongbin
 *
 */
public class MarketcardSaleAction extends BaseAction {
	
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0023701";
	private static String PVG_EDIT = "BS0023702";
	private static String PVG_DELETE = "BS0023703";
	  //启用,停用
    private static String PVG_START_STOP = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0023704";
	private static String PVG_TRACE = "BS0023705";
	//开启关闭
	private static String PVG_OPEN_CLOSE = "BS0023706";

	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0023801";
	private static String PVG_AUDIT_AUDIT = "BS0023802";
	private static String PVG_TRACE_AUDIT = "BS0023803";
	// 审批流参数
	private static String AUD_TAB_NAME = "ERP_CARD_SALE_ORDER";
	private static String AUD_TAB_KEY = "CARD_SALE_ORDER_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "ERP_CARD_SALE_ORDER_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.PublicFlowInterface";
	
	private MarketcardSaleService marketcardSaleService;
	
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl",paramUrl );
	 
		return mapping.findForward("frames");
	}
	
	
	/**
	 * *list页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "CARD_SALE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params); 
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NO", params);  
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NAME", params);  
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEGIN", params); 
	    ParamUtil.putStr2Map(request, "SALE_DATE_END", params);  
	    ParamUtil.putStr2Map(request, "STATE", params);  
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
//		if("wh".equals(module)){
//			sb.append(" and u.LEDGER_ID ='").append(userBean.getLoginZTXXID()).append("'");
//		}
		 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = marketcardSaleService.pageQuery(params, pageNo);
		request.setAttribute("module", module);
        request.setAttribute("params", params);
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
        request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	

	 /**
    * * 明细列表
    * @param mapping the mapping
    * @param form the form
    * @param request the request
    * @param response the response
    * 
    * @return the action forward
    */
   public ActionForward childList(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
   	
	   UserBean userBean = ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
    		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
	   		throw new ServiceException("对不起，您没有权限 !");
	   }
       String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
       if(!StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
       	 List <MarketcardSaleChldModel> result = 
       		marketcardSaleService.childQuery(CARD_SALE_ORDER_ID);
            request.setAttribute("rst", result);
       }
       request.setAttribute("pvg",setPvg(userBean));
       return mapping.findForward("childlist");
   }

    
   /**
    * *
    * * to 编辑框架页面
    * @param mapping the mapping
    * @param form the form
    * @param request the request
    * @param response the response
    * 
    * @return the action forward
    */
   public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
   	UserBean userBean = ParamUtil.getUserBean(request);
   	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
   		throw new ServiceException("对不起，您没有权限 !");
   	}
       //设置跳转时需要的一些公用属性
       ParamUtil.setCommAttributeEx(request);
       request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
       return mapping.findForward("editFrame");
   }
	
	/**
	 * * to 主表编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
	   	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	   		throw new ServiceException("对不起，您没有权限 !");
	   	}	
		String CARD_SALE_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		entry.put("SALE_DATE",DateUtil.format(new Date(), "yyyy-MM-dd"));
		if(!StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
			entry = marketcardSaleService.load(CARD_SALE_ORDER_ID);
		}else if("1".equals(userBean.getIS_DRP_LEDGER())){
			entry.put("CHANN_ID", userBean.getCHANN_ID());
			entry.put("CHANN_NO", userBean.getCHANN_NO());
			entry.put("CHANN_NAME", userBean.getCHANN_NAME());
			entry.put("TERM_ID", userBean.getTERM_ID());
			entry.put("TERM_NO", userBean.getTERM_NO());
			entry.put("TERM_NAME", userBean.getTERM_NAME());
		}
		
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("toedit");
	}
	
	/**
     * * 编辑框架页面加载子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
        if(!StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
        	 List <MarketcardSaleChldModel> result = 
        		 marketcardSaleService.childQuery(CARD_SALE_ORDER_ID);
             request.setAttribute("rst", result);
        }
        return mapping.findForward("childedit");
    }
	
    
    
    
    /**
     * * to 直接编辑明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
    	//多个Id批量查询，用逗号隔开
        String CARD_SALE_ORDER_DTL_IDS = request.getParameter("CARD_SALE_ORDER_DTL_IDS");
        if (!StringUtil.isEmpty(CARD_SALE_ORDER_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("CARD_SALE_ORDER_DTL_IDS",CARD_SALE_ORDER_DTL_IDS);
		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          List <MarketcardSaleChldModel> list = marketcardSaleService.loadChilds(params);
          request.setAttribute("rst", list);
        }
//        String advcIds = erpAdvcorderStatementsService.queryAdvcIds(CARD_SALE_ORDER_ID);
//        request.setAttribute("advcIds", advcIds);
        return mapping.findForward("childedit");
    }
    
    /**
	 * * 主表 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            MarketcardSaleModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <MarketcardSaleModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            
            List <MarketcardSaleChldModel> chldList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldList = new Gson().fromJson(jsonDate, 
            			new TypeToken <List <MarketcardSaleChldModel>>(){}.getType());
            }
            marketcardSaleService.txEdit(CARD_SALE_ORDER_ID, model, chldList, userBean);
            jsonResult = jsonResult(true, "保存成功");
            
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
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String CARD_SALE_ORDER_ID = request.getParameter("CARD_SALE_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <MarketcardSaleChldModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <MarketcardSaleChldModel>>(){}.getType());
                marketcardSaleService.txChildEdit(CARD_SALE_ORDER_ID, modelList,userBean);
                jsonResult = jsonResult(true, "保存成功");
            }
            
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
	 * * 主表 删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            marketcardSaleService.txDelete(params);
            marketcardSaleService.clearCaches(params);
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	 /**
     * * 明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
            String CARD_SALE_ORDER_DTL_IDS = request.getParameter("CARD_SALE_ORDER_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
            params.put("CARD_SALE_ORDER_DTL_IDS", CARD_SALE_ORDER_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            marketcardSaleService.txBatch4DeleteChild(params);
		} catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 已销售的卡券可以关闭
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void childClose(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
            String CARD_SALE_ORDER_DTL_IDS = request.getParameter("CARD_SALE_ORDER_DTL_IDS");
            marketcardSaleService.txChildClose(CARD_SALE_ORDER_ID,CARD_SALE_ORDER_DTL_IDS,userBean);
		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
            jsonResult = jsonResult(false, "关闭失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * 已销售的卡券可以关闭
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void childOpen(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
            String CARD_SALE_ORDER_DTL_IDS = request.getParameter("CARD_SALE_ORDER_DTL_IDS");
            marketcardSaleService.txChildOpen(CARD_SALE_ORDER_ID,CARD_SALE_ORDER_DTL_IDS,userBean);
		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
            jsonResult = jsonResult(false, "开启失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
	
	/**
	 * * to 详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String CARD_SALE_ORDER_ID = ParamUtil.get(request, "CARD_SALE_ORDER_ID");
		if(!StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
			Map<String,Object> entry = marketcardSaleService.load(CARD_SALE_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		 
		return mapping.findForward("todetail");
	}
	
	
	 
	 /**
     * *  提交
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String CARD_SALE_ORDER_ID = request.getParameter("CARD_SALE_ORDER_ID");
            if(!StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
            	List <MarketcardSaleChldModel> list 
            	= marketcardSaleService.childQuery(CARD_SALE_ORDER_ID);
            	if(null == list || list.isEmpty()){
            		throw new ServiceException("明细信息未填写！");
            	}else{
            		//查询同一个活动下一个客户只能领取一张券
                	marketcardSaleService.queryCusNum(CARD_SALE_ORDER_ID);
                }
            }
            //插入会员信息表
            marketcardSaleService.txCreateMemberInfo(CARD_SALE_ORDER_ID,userBean);
            jsonResult = jsonResult(true, "提交成功");
		}catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
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
     * 查询会员信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void getMemberInfo(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MOBILE_PHONE = request.getParameter("MOBILE_PHONE");
            Map<String,String> info = marketcardSaleService.getMemberInfo(MOBILE_PHONE);
            jsonResult = new Gson().toJson(new Result(true, info, "查询会员信息成功"));
		}catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "查询会员信息失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    

    
    
    /**
	 * * 手机卡券销售
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void mobileCardSaleEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String MARKETING_CARD_NO = ParamUtil.get(request, "MARKETING_CARD_NO");
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <MarketcardSaleChldModel> chldList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldList = new Gson().fromJson(jsonDate, 
            			new TypeToken <List <MarketcardSaleChldModel>>(){}.getType());
            	if(null != chldList && !chldList.isEmpty()){
            		marketcardSaleService.txMobileCrdSaleEdit(MARKETING_CARD_NO,chldList, userBean);
            	}
                jsonResult = jsonResult(true, "保存成功");
            }
            
        }catch(ServiceException se){
        	jsonResult = jsonResult(false, se.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	/**
	 * 根据卡券编号加载卡券信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void loadCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String MARKETING_CARD_NO = ParamUtil.get(request, "MARKETING_CARD_NO");
            Map<String,String> entry = marketcardSaleService.loadCard(MARKETING_CARD_NO);
            if(null == entry || entry.isEmpty()){
				entry = marketcardSaleService.loadNoSaleCard(MARKETING_CARD_NO);
			}
            jsonResult = new Gson().toJson(new Result(true, entry, "加载成功"));
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "加载失败");
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
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_OPEN_CLOSE", QXUtil.checkPvg(userBean, PVG_OPEN_CLOSE));
		
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}


	public MarketcardSaleService getMarketcardSaleService() {
		return marketcardSaleService;
	}


	public void setMarketcardSaleService(MarketcardSaleService marketcardSaleService) {
		this.marketcardSaleService = marketcardSaleService;
	}
	
	

}
