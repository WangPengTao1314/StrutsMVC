package com.hoperun.erp.sale.marketing.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.marketing.model.MarketActivitiyModel;
import com.hoperun.erp.sale.marketing.model.MarketCardModel;
import com.hoperun.erp.sale.marketing.model.MarketChannModel;
import com.hoperun.erp.sale.marketing.service.MarketActivitiyService;
import com.hoperun.sys.model.UserBean;

public class MarketActivitiyAction extends BaseAction{
	
	/**日志**/
	private Logger logger = Logger.getLogger(MarketActivitiyAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS = "BS0022001";
    private static String PVG_EDIT = "BS0022002";
    private static String PVG_DELETE = "BS0022003";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE = "BS0022004";
    private static String PVG_TRACE = "BS0022005";
    
    //审核模块
    private static String PVG_BWS_AUDIT = "BS0022201";
    private static String PVG_AUDIT_AUDIT = "BS0022202";
    private static String PVG_TRACE_AUDIT = "BS0022203";
    //审批流参数
    private static String AUD_TAB_NAME = "ERP_MARKETING_ACT";
    private static String AUD_TAB_KEY = "MARKETING_ACT_ID";
	private static String AUD_BUSS_STATE = "STATE";
    private static String AUD_BUSS_TYPE = "ERP_MARKETING_ACT_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.PublicFlowInterface";
	
	private MarketActivitiyService marketActivitiyService;

	
	
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
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NO", params);
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NAME", params);
	    ParamUtil.putStr2Map(request, "START_DATE", params);
	    ParamUtil.putStr2Map(request, "END_DATE", params);
	    ParamUtil.putStr2Map(request, "SPONSOR_NAME", params);
	    ParamUtil.putStr2Map(request, "REMARK", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params); 
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		sb.append(" and u.LEDGER_ID ='").append(userBean.getLoginZTXXID()).append("'");
		if("sh".equals(module)){
		}
		if("wh".equals(module)){
			 
		}
		 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.START_DATE", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = marketActivitiyService.pageQuery(params, pageNo);
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
   public ActionForward marketChannList(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
   	
	   UserBean userBean = ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
    		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
	   		throw new ServiceException("对不起，您没有权限 !");
	   }
       String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
       if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
       	 List <MarketChannModel> result = marketActivitiyService.marketChannQuery(MARKETING_ACT_ID);
            request.setAttribute("rst", result);
       }
       request.setAttribute("pvg",setPvg(userBean));
       return mapping.findForward("marketChannlist");
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
		String MARKETING_ACT_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
			entry = marketActivitiyService.load(MARKETING_ACT_ID);
		} 
		request.setAttribute("rst", entry);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
    public ActionForward modifyToMarketChannEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
        if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
        	 List <MarketChannModel> result = marketActivitiyService.marketChannQuery(MARKETING_ACT_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        return mapping.findForward("marketChannedit");
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
    public ActionForward toMarketChannEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
    	//多个Id批量查询，用逗号隔开
        String MARKETING_CHANN_IDS = request.getParameter("MARKETING_CHANN_IDS");
        if (!StringUtil.isEmpty(MARKETING_CHANN_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("MARKETING_CHANN_IDS",MARKETING_CHANN_IDS);
          List <MarketChannModel> list = marketActivitiyService.loadMarketChannModels(params);
          request.setAttribute("rst", list);
        }
        String CHANN_IDS = marketActivitiyService.queryChannIdsByFkId(MARKETING_ACT_ID);
        request.setAttribute("CHANN_IDS", CHANN_IDS);
        return mapping.findForward("marketChannedit");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            MarketActivitiyModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <MarketActivitiyModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            
            List <MarketChannModel> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <MarketChannModel>>() {
                }.getType());
            }
            marketActivitiyService.txEdit(MARKETING_ACT_ID, model, chldModelList, userBean);
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
    public void marketChannEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MARKETING_ACT_ID = request.getParameter("MARKETING_ACT_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <MarketChannModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <MarketChannModel>>(){}.getType());
                marketActivitiyService.txMarketChannEdit(MARKETING_ACT_ID, modelList);
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
			String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            marketActivitiyService.txDelete(params);
            marketActivitiyService.clearCaches(params);
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
    public void marketChannDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MARKETING_CHANN_IDS = request.getParameter("MARKETING_CHANN_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MARKETING_CHANN_IDS", MARKETING_CHANN_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            marketActivitiyService.txBatch4DeleteMarketChann(params);
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
		String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
		if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
			Map<String,Object> entry = marketActivitiyService.load(MARKETING_ACT_ID);
			request.setAttribute("rst", entry);
		}
		 
		return mapping.findForward("todetail");
	}
	
	
	
	
	
    /**
     * * to 直接编辑卡券页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toMarketCardEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String MARKETING_CARD_IDS = request.getParameter("MARKETING_CARD_IDS");
        if (!StringUtil.isEmpty(MARKETING_CARD_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("MARKETING_CARD_IDS",MARKETING_CARD_IDS);
          List <MarketCardModel> list = marketActivitiyService.loadMarketCardModels(params);
          request.setAttribute("rst", list);
        }

        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        return mapping.findForward("marketCardedit");
    }
	
    
    /**
     * 卡券 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void marketCardEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MARKETING_ACT_ID = request.getParameter("MARKETING_ACT_ID");
            String jsonDate = request.getParameter("jsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
            	MarketCardModel model = new Gson().fromJson(
                		jsonDate, new TypeToken <MarketCardModel>(){}.getType());
                marketActivitiyService.txMarketCardEdit(MARKETING_ACT_ID, model,userBean);
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
     * * 卡券明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void marketCardDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MARKETING_CARD_IDS = request.getParameter("MARKETING_CARD_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MARKETING_CARD_IDS", MARKETING_CARD_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            marketActivitiyService.txBatch4DeleteMarketCard(params);
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
	   * * 明细列表
	   * @param mapping the mapping
	   * @param form the form
	   * @param request the request
	   * @param response the response
	   * 
	   * @return the action forward
	   */
	  public ActionForward marketCardList(ActionMapping mapping, ActionForm form, 
	  		HttpServletRequest request, HttpServletResponse response) {
	  	
		  UserBean userBean = ParamUtil.getUserBean(request);
	      if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
	   		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
		   		throw new ServiceException("对不起，您没有权限 !");
		   }
	      String MARKETING_ACT_ID = ParamUtil.get(request, "MARKETING_ACT_ID");
//	      if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
//	      	 List <MarketCardModel> result = marketActivitiyService.marketCardQuery(MARKETING_ACT_ID);
//	         request.setAttribute("rst", result);
//	      }
	      Map<String,String> params = new HashMap<String,String>();
	      params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
	      ParamUtil.setOrderField(request, params, "u.MARKETING_CARD_NO", "");
		  int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		  String pageSize = ParamUtil.get(request, "pageSize");
		  if(StringUtil.isEmpty(pageSize)){
			  pageSize = "50";
		  }
		  params.put("pageSize", pageSize);
		  IListPage page = marketActivitiyService.pageCardQuery(params, pageNo);
		  request.setAttribute("page",page);
	      request.setAttribute("pvg",setPvg(userBean));
	      return mapping.findForward("marketCardlist");
	  }
	  
	  
	    /**
	     * 卡券 开启
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void toCardStart(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String MARKETING_CARD_IDS = request.getParameter("MARKETING_CARD_IDS");
                if(!StringUtil.isEmpty(MARKETING_CARD_IDS)){
                	 marketActivitiyService.txMarketCardStart(MARKETING_CARD_IDS,userBean);
                     jsonResult = jsonResult(true, "开启成功");
                }
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
	     * 卡券 关闭
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void toCardStop(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String MARKETING_CARD_IDS = request.getParameter("MARKETING_CARD_IDS");
                if(!StringUtil.isEmpty(MARKETING_CARD_IDS)){
                	 marketActivitiyService.txMarketCardStop(MARKETING_CARD_IDS,userBean);
                     jsonResult = jsonResult(true, "关闭成功");
                }
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
	 * 生成卡券
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
    public void toSaveCard(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MARKETING_CARD_IDS = request.getParameter("MARKETING_CARD_IDS");
            if(!StringUtil.isEmpty(MARKETING_CARD_IDS)){
            	 marketActivitiyService.txMarketCardStop(MARKETING_CARD_IDS,userBean);
                 jsonResult = jsonResult(true, "关闭成功");
            }
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
     * 提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
	public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try {
            String MARKETING_ACT_ID = request.getParameter("MARKETING_ACT_ID");
            if(!StringUtil.isEmpty(MARKETING_ACT_ID)){
            	List <MarketChannModel> channList =  marketActivitiyService.marketChannQuery(MARKETING_ACT_ID);
            	List <MarketCardModel> cardList =  marketActivitiyService.marketCardQuery(MARKETING_ACT_ID);
            	if(null == channList || channList.isEmpty()){
            		throw new ServiceException("参加活动加盟商未填写！");
            	}
            	if(null == cardList || cardList.isEmpty()){
            		throw new ServiceException("营销活动卡券未填写！");
            	}
                jsonResult = jsonResult(true, "提交成功");
            }
        }catch(ServiceException e){
        	jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "提交失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	
	// 导出
	public void exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "MARKETING_ACT_ID", params);
		List list = marketActivitiyService.exportExcel(params);
		// excel数据上列显示的列明
		String tmpContentCn ="卡券编号,卡卷类型,卡卷面值,创建时间 ,状态";
		String tmpContent = "MARKETING_CARD_NO,CARD_TYPE,CARD_VALUE,CRE_TIME,STATE";
//		String colType = "string,string,string,string" ;
		try {
			ExcelUtil.doExport(response, list, "卡券信息", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean) {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
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
	
	public MarketActivitiyService getMarketActivitiyService() {
		return marketActivitiyService;
	}

	public void setMarketActivitiyService(
			MarketActivitiyService marketActivitiyService) {
		this.marketActivitiyService = marketActivitiyService;
	}
	
	
	
	
	

}
