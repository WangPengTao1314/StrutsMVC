/**
 * prjName:喜临门营销平台
 * ucName:退货申请单维护
 * fileName:PrdreturnAction
*/
package com.hoperun.drp.areaser.returnpdtrls.action;
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
import com.hoperun.drp.areaser.returnpdtrls.service.ReturnpdtrlsService;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModel;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@func 退货申请单审核 (有区域区域服务中心)
 * *@version 1.1 
 * *@author zzb
 * *@createdate 2013-08-15 10:17:13
 */
public class ReturnpdtrlsAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(ReturnpdtrlsAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS = "FX0070401";
    private static String PVG_EDIT = "";
    private static String PVG_DELETE = "";
    //启用,停用
    private static String PVG_START_STOP = "";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE = "";
    private static String PVG_TRACE = "";
    //审核模块
    private static String PVG_BWS_AUDIT = "FX0070402";
    private static String PVG_AUDIT_AUDIT = "FX0070402";
    private static String PVG_TRACE_AUDIT = "FX0070403";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_PRD_RETURN_REQ";
    private static String AUD_TAB_KEY="PRD_RETURN_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="DRP_SER_PRD_RETURN_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.areaser.returnpdtrls.service.impl.ReturnpdtrlsFlowImpl";

	/**审批 end**/
	
    /** 业务service*/
	private ReturnpdtrlsService returnpdtrlsService;
  
	
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "PRD_RETURN_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);//订货订单编号
	    
	    params.put("AREA_SER_ID", userBean.getCHANN_ID());
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE = ParamUtil.get(request,"STATE");
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		params.put("QXJBCON", sb.toString());
		
		params.put("STATE", STATE);
		
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = returnpdtrlsService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("module", module);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_RETURN_REQ_ID =ParamUtil.get(request, "PRD_RETURN_REQ_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID))
        {
        	 List <PrdreturnreqModelChld> result = returnpdtrlsService.childQuery(PRD_RETURN_REQ_ID);
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
		String PRD_RETURN_REQ_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = null;
		if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID)){
			entry = returnpdtrlsService.load(PRD_RETURN_REQ_ID);
			request.setAttribute("rst", entry);
		}else{
			 entry = new HashMap<String,String>();
			//单据类型
			entry.put("BILL_TYPE", "销售退货");
			//退货方默认值(自己)
			entry.put("RETURN_CHANN_ID", userBean.getCHANN_ID());
			entry.put("RETURN_CHANN_NO", userBean.getCHANN_NO());
			entry.put("RETURN_CHANN_NAME", userBean.getCHANN_NAME());
			
			//收货方默认值(总部)
			entry.put("RECV_CHANN_ID", userBean.getBASE_CHANN_ID());
			entry.put("RECV_CHANN_NO", userBean.getBASE_CHANN_NO());
			entry.put("RECV_CHANN_NAME", userBean.getBASE_CHANN_NAME ());
			request.setAttribute("rst", entry);
		}
	 
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
    	request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	
        String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
        
        if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID))
        {
        	 List <PrdreturnreqModelChld> result = returnpdtrlsService.childQuery(PRD_RETURN_REQ_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRD_RETURN_DTL_REQ_IDs = request.getParameter("PRD_RETURN_DTL_REQ_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_RETURN_DTL_REQ_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_RETURN_DTL_REQ_IDS",PRD_RETURN_DTL_REQ_IDs);
          List <PrdreturnreqModelChld> list = returnpdtrlsService.loadChilds(params);
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
		try {
            String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            PrdreturnreqModel model = new Gson().fromJson(parentJsonData, new TypeToken <PrdreturnreqModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <PrdreturnreqModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnreqModelChld>>() {
                }.getType());
            }
            returnpdtrlsService.txEdit(PRD_RETURN_REQ_ID, model, chldModelList, userBean);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
            String PRD_RETURN_REQ_ID = request.getParameter("PRD_RETURN_REQ_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdreturnreqModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnreqModelChld>>() {
                }.getType());
                returnpdtrlsService.txChildEdit(PRD_RETURN_REQ_ID, modelList);
            }
        }catch(ServiceException s) {
        	jsonResult = jsonResult(false, s.getMessage());
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
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			returnpdtrlsService.txDelete(params);
			returnpdtrlsService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_DTL_REQ_IDs = request.getParameter("PRD_RETURN_DTL_REQ_IDS");
            //批量删除，多个Id之间用逗号隔开
            returnpdtrlsService.txBatch4DeleteChild(PRD_RETURN_DTL_REQ_IDs);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
		if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID)){
			Map<String,String> entry = returnpdtrlsService.load(PRD_RETURN_REQ_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * 审核
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_REQ_ID = request.getParameter("PRD_RETURN_REQ_ID");
            returnpdtrlsService.txAudit(PRD_RETURN_REQ_ID,userBean);
            jsonResult = jsonResult(true, "操作成功");
            
        } catch (Exception e) {
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
	 private Map<String,String> setPvg(UserBean userBean){
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
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
		
	 public ReturnpdtrlsService getReturnpdtrlsService() {
			return returnpdtrlsService;
		}
		public void setReturnpdtrlsService(ReturnpdtrlsService returnpdtrlsService) {
			this.returnpdtrlsService = returnpdtrlsService;
		}
	 
	 
	 
	 
	 
}