/**
 * prjName:喜临门营销平台
 * ucName:广告投放申请单维护
 * fileName:AdvreqAction
*/
package com.hoperun.drp.adv.advreq.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.adv.advreq.model.AdvreqModel;
import com.hoperun.drp.adv.advreq.model.AdvreqModelChld;
import com.hoperun.drp.adv.advreq.service.AdvreqService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-15
 */
public class AdvreqAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(AdvreqAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0021101";
    private static String PVG_EDIT="BS0021102";
    private static String PVG_DELETE="BS0021103";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0021104";
    private static String PVG_TRACE="BS0021105";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0060401";
    private static String PVG_AUDIT_AUDIT="BS0060402";
    private static String PVG_TRACE_AUDIT="BS0060403";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_ADV_REQ";
    private static String AUD_TAB_KEY="ERP_ADV_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_ADV_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
    /**业务service*/
	private AdvreqService advreqService;
    /**
	 * Sets the Advreq service.
	 * 
	 * @param AdvreqService the new Advreq service
	 */
	public void setAdvreqService(AdvreqService advreqService) {
		this.advreqService = advreqService;
	}
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
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
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "ERP_ADV_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "ADV_TYPE", params);
	    ParamUtil.putStr2Map(request, "ADV_CONTENT", params);
	    ParamUtil.putStr2Map(request, "ADV_ADDR", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		
		//区域分管
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advreqService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		return mapping.findForward("list");
	}
	
	 /**
     * * 明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ERP_ADV_REQ_ID =ParamUtil.get(request, "ERP_ADV_REQ_ID");
        if(!StringUtil.isEmpty(ERP_ADV_REQ_ID))
        {
        	 List <AdvreqModelChld> result = advreqService.childQuery(ERP_ADV_REQ_ID);
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
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
		String ERP_ADV_REQ_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(ERP_ADV_REQ_ID)){
			entry = advreqService.load(ERP_ADV_REQ_ID);
			
			String picPath = advreqService.loadAtt(ERP_ADV_REQ_ID);
            if(StringUtils.isNotEmpty(picPath)){
            	entry.put("PIC_PATH",picPath);
            }
		} else {
			//状态
			entry.put("STATE", BusinessConsts.UNCOMMIT);			
			//申请人
			entry.put("CRE_NAME", userBean.getXM());			
			//申请时间
			entry.put("CRE_TIME", DateUtil.formatDateToStr(TimeComm.getDate()));	
		}
		request.setAttribute("rst", entry);
		request.setAttribute("XTYH_ID", userBean.getXTYHID());
		return mapping.findForward("toedit");
	}
	
	/**
     * * 编辑框架页面加载子页面
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
        String ERP_ADV_REQ_ID = ParamUtil.get(request, "ERP_ADV_REQ_ID");
        if(!StringUtil.isEmpty(ERP_ADV_REQ_ID))
        {
        	 List <AdvreqModelChld> result = advreqService.childQuery(ERP_ADV_REQ_ID);
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
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String ERP_ADV_REQ_DTL_IDs = request.getParameter("ERP_ADV_REQ_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(ERP_ADV_REQ_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ERP_ADV_REQ_DTL_IDS",ERP_ADV_REQ_DTL_IDs);
          List <AdvreqModelChld> list = advreqService.loadChilds(params);
          request.setAttribute("rst", list);
        }
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String seq = null;
		try {
            String ERP_ADV_REQ_ID = ParamUtil.get(request, "ERP_ADV_REQ_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            AdvreqModel model = new Gson().fromJson(parentJsonData, new TypeToken <AdvreqModel>() {}.getType());
            
            if(StringUtil.isEmpty(ERP_ADV_REQ_ID)){
            	seq  = LogicUtil.getBmgz("ERP_ADV_REQ_NO");
            	model.setERP_ADV_REQ_NO(seq);
    		}
            
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvreqModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvreqModelChld>>() {
                }.getType());
            }
            advreqService.txEdit(ERP_ADV_REQ_ID, model, chldModelList, userBean);
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
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ERP_ADV_REQ_ID = request.getParameter("ERP_ADV_REQ_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <AdvreqModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvreqModelChld>>() {
                }.getType());
                advreqService.txChildEdit(ERP_ADV_REQ_ID, modelList);
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
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ERP_ADV_REQ_ID = ParamUtil.get(request, "ERP_ADV_REQ_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ERP_ADV_REQ_ID", ERP_ADV_REQ_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			advreqService.txDelete(params);
			advreqService.clearCaches(params);
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
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ERP_ADV_REQ_DTL_IDs = request.getParameter("ERP_ADV_REQ_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            advreqService.txBatch4DeleteChild(ERP_ADV_REQ_DTL_IDs);
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
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String ERP_ADV_REQ_ID = ParamUtil.get(request, "ERP_ADV_REQ_ID");		
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(ERP_ADV_REQ_ID)){
			entry = advreqService.load(ERP_ADV_REQ_ID);
			
			String picPath = advreqService.loadAtt(ERP_ADV_REQ_ID);
            if(StringUtils.isNotEmpty(picPath)){
            	entry.put("ATT_PATH",picPath);
            }
		}
		request.setAttribute("rst", entry);
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String ERP_ADV_REQ_ID = request.getParameter("ERP_ADV_REQ_ID");
            List <AdvreqModelChld> list = advreqService.childQuery(ERP_ADV_REQ_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            } else {
                jsonResult = jsonResult(false, "提交失败");
            }
           e.printStackTrace();
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
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}