package com.hoperun.erp.sale.member.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.marketing.model.MarketActivitiyModel;
import com.hoperun.erp.sale.marketing.model.MarketCardModel;
import com.hoperun.erp.sale.marketing.model.MarketChannModel;
import com.hoperun.erp.sale.member.model.MemberActModel;
import com.hoperun.erp.sale.member.model.MemberCardModel;
import com.hoperun.erp.sale.member.model.MemberModel;
import com.hoperun.erp.sale.member.model.MemberPointModel;
import com.hoperun.erp.sale.member.service.MemberService;
import com.hoperun.sys.model.UserBean;

public class MemberAction extends BaseAction{
	

	/**日志**/
	private Logger logger = Logger.getLogger(MemberAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS = "BS0022101";
    private static String PVG_EDIT = "BS0022102";
    private static String PVG_DELETE = "BS0022103";
    
    //启用,停用
    private static String PVG_START_STOP = "BS0022104";
    
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE = "";
    private static String PVG_TRACE = "";
    
    
    //审核模块
    private static String PVG_BWS_AUDIT = "";
    private static String PVG_AUDIT_AUDIT = "";
    private static String PVG_TRACE_AUDIT = "";
    //审批流参数
    private static String AUD_TAB_NAME = "";
    private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
    private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	private MemberService memberService;
	
	
	
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
	    ParamUtil.putStr2Map(request, "MEMBER_NO", params);
	    ParamUtil.putStr2Map(request, "MEMBER_NAME", params);
	    ParamUtil.putStr2Map(request, "MOBILE_PHONE", params);
	    ParamUtil.putStr2Map(request, "SEX", params);
	    ParamUtil.putStr2Map(request, "POINT_VALUE", params);
	    ParamUtil.putStr2Map(request, "REMARK", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params); 
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
		 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = memberService.pageQuery(params, pageNo);
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	
	  /**
	    * * 会员活动列表
	    * @param mapping the mapping
	    * @param form the form
	    * @param request the request
	    * @param response the response
	    * 
	    * @return the action forward
	    */
	   public ActionForward memberActList(ActionMapping mapping, ActionForm form, 
	   		HttpServletRequest request, HttpServletResponse response) {
		   UserBean userBean = ParamUtil.getUserBean(request);
	       if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
	    		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
		   		throw new ServiceException("对不起，您没有权限 !");
		   }
	       String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
	       if(!StringUtil.isEmpty(MEMBER_ID)){
	       	 List <MemberActModel> result = memberService.memberActQuery(MEMBER_ID);
	            request.setAttribute("rst", result);
	       }
	       request.setAttribute("pvg",setPvg(userBean));
	       return mapping.findForward("memberActlist");
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
   public ActionForward memberCardList(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
	   UserBean userBean = ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
    		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
	   		throw new ServiceException("对不起，您没有权限 !");
	   }
       String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
       if(!StringUtil.isEmpty(MEMBER_ID)){
       	 List <MemberCardModel> result = memberService.memberCardQuery(MEMBER_ID);
            request.setAttribute("rst", result);
       }
       request.setAttribute("pvg",setPvg(userBean));
       return mapping.findForward("memberCardlist");
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
		String MEMBER_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(MEMBER_ID)){
			entry = memberService.load(MEMBER_ID);
		} 
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("toedit");
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
            String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            String childJsonData = ParamUtil.get(request, "childJsonData");
            
            MemberModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <MemberModel>() {}.getType());
            
            List <MemberActModel> chldModelList = null;
            if (!StringUtil.isEmpty(childJsonData)) {
                chldModelList = new Gson().fromJson(childJsonData, 
                		new TypeToken <List <MemberActModel>>(){}.getType());
            }
            memberService.txEdit(MEMBER_ID, model, chldModelList, userBean);
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
     * * 编辑框架页面加载子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToMemberActEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
        if(!StringUtil.isEmpty(MEMBER_ID)){
        	 List <MemberActModel> result = memberService.memberActQuery(MEMBER_ID);
             request.setAttribute("rst", result);
        }
        return mapping.findForward("memberActedit");
    }
	
    
	/**
     * * to 直接编辑活动面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toMemberActEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
    	String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
        String MEMBER_ACT_DTL_IDS = ParamUtil.get(request, "MEMBER_ACT_DTL_IDS"); 
        if (!StringUtil.isEmpty(MEMBER_ACT_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("MEMBER_ACT_DTL_IDS",MEMBER_ACT_DTL_IDS);
          List <MemberActModel> list = memberService.loadMemberActModels(params);
          request.setAttribute("rst", list);
        }
        String ACT_IDS =  memberService.queryActIdsByFkId(MEMBER_ID);
        request.setAttribute("ACT_IDS", ACT_IDS);
        return mapping.findForward("memberActedit");
    }
	
    /**
     * 活动子表编辑
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void memberActEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	  
    	PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	try {
	        String MEMBER_ID = request.getParameter("MEMBER_ID");
	        String jsonDate = request.getParameter("childJsonData");
	        if(!StringUtil.isEmpty(jsonDate)){
	        	 List <MemberActModel> modelList = new Gson().fromJson(
	             		jsonDate, new TypeToken <List <MemberActModel>>(){}.getType());
	        	 
	        	  memberService.txMemberActEdit(MEMBER_ID,modelList,userBean);
	        	  jsonResult = jsonResult(true, "保存成功");  
	        }
        } catch(Exception e){
    	  jsonResult = jsonResult(false, "保存失败");
          e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
    public ActionForward toMemberCardEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
    	//多个Id批量查询，用逗号隔开
        String MEMBER_CARD_DTL_IDS = ParamUtil.get(request, "MEMBER_CARD_DTL_IDS");
        if (!StringUtil.isEmpty(MEMBER_CARD_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("MEMBER_CARD_DTL_IDS",MEMBER_CARD_DTL_IDS);
          List <MemberCardModel> list = memberService.loadMemberCardModels(params);
          request.setAttribute("rst", list);
        }
        String ACT_IDS =  memberService.queryActIdsByFkId(MEMBER_ID);
        request.setAttribute("ACT_IDS", ACT_IDS);
        //查询已发放的卡券ID
        String CARD_IDS =  memberService.queryCardIdsByFkId(MEMBER_ID);
        request.setAttribute("CARD_IDS", CARD_IDS);

        return mapping.findForward("memberCardedit");
    }
    
	/**
	 * 卡券子表 编辑
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
    public void memberCardEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
	  
    	PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	try {
	        String MEMBER_ID = request.getParameter("MEMBER_ID");
	        String jsonDate = request.getParameter("childJsonData");
	        if(!StringUtil.isEmpty(jsonDate)){
	        	 List <MemberCardModel> modelList = new Gson().fromJson(
	             		jsonDate, new TypeToken <List <MemberCardModel>>(){}.getType());
	        	 
	        	  memberService.txMemberCardEdit(MEMBER_ID,modelList,userBean);
	        	  jsonResult = jsonResult(true, "保存成功");  
	        }
        } catch(Exception e){
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
			String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("MEMBER_ID", MEMBER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            memberService.txDelete(params);
            memberService.clearCaches(params);
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
    public void memberActDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MEMBER_ACT_DTL_IDS = request.getParameter("MEMBER_ACT_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MEMBER_ACT_DTL_IDS", MEMBER_ACT_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            memberService.txBatch4DeleteMemberAct(params);
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
     * * 明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void memberCardDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MEMBER_CARD_DTL_IDS = request.getParameter("MEMBER_CARD_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MEMBER_CARD_DTL_IDS", MEMBER_CARD_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            memberService.txBatch4DeleteMemberCard(params);
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
		String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
		if(!StringUtil.isEmpty(MEMBER_ID)){
			Map<String,Object> entry = memberService.load(MEMBER_ID);
			request.setAttribute("rst", entry);
		}
		 
		return mapping.findForward("todetail");
	}
	
	
	
    /**
     * 启用
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toStart(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MEMBER_ID = request.getParameter("MEMBER_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MEMBER_ID", MEMBER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//启用
            memberService.txUpdateSate(params);
            jsonResult = jsonResult(true, "操作成功");
		} catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    } 
    

    /**
     * 停用
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toStop(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MEMBER_ID = request.getParameter("MEMBER_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("MEMBER_ID", MEMBER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);//停用
            memberService.txUpdateSate(params);
            jsonResult = jsonResult(true, "操作成功");
		} catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    } 
    
    
	/**
	 * 积分流水
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward memberPointList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String MEMBER_ID = ParamUtil.get(request, "MEMBER_ID");
		if(!StringUtil.isEmpty(MEMBER_ID)){
			List<MemberPointModel> list = memberService.memberPointQuery(MEMBER_ID);
			request.setAttribute("rst", list);
		}
		 
		return mapping.findForward("memberPointlist");
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
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	  
	}


	public MemberService getMemberService() {
		return memberService;
	}


	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	 
	 
	
	
 
	

}
