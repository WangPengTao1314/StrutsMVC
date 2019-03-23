/**
 * prjName:喜临门营销平台
 * ucName:库存储备信息
 * fileName:ResvstoreAction
*/
package com.hoperun.drp.base.resvstore.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.base.resvstore.model.ResvstoreModel;
import com.hoperun.drp.base.resvstore.service.ResvstoreService;
/**
 * *@serviceImpl
 * *@func 库存储备
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-07 14:13:12
 */
public class ResvstoreAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(ResvstoreAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0010301";
    private static String PVG_EDIT="FX0010302";
    private static String PVG_DELETE="FX0010304";
    //启用,停用
    private static String PVG_START_STOP="FX0010305";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
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
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private ResvstoreService resvstoreService;
    /**
	 * Sets the Resvstore service.
	 * 
	 * @param ResvstoreService the new Resvstore service
	 */
	public void setResvstoreService(ResvstoreService resvstoreService) {
		this.resvstoreService = resvstoreService;
	}
	
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	   ParamUtil.putStr2Map(request, "PRD_COLOR", params);
	   ParamUtil.putStr2Map(request, "STD_UNIT", params);
	   ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	   ParamUtil.putStr2Map(request, "BRAND", params);
	   ParamUtil.putStr2Map(request, "PRD_NAME", params);
	   ParamUtil.putStr2Map(request, "PRD_NO", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
	   ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
	   ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	   
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装和状态过滤
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = resvstoreService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 * * to 编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String RESV_STOCK_ID = ParamUtil.get(request, "RESV_STOCK_ID");
		if(!StringUtil.isEmpty(RESV_STOCK_ID)){
			Map<String,String> entry = resvstoreService.load(RESV_STOCK_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("toedit");
	}
	
	/**
	 * * 新增/修改数据
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
			String jsonData = ParamUtil.get(request, "jsonData");
			ResvstoreModel aModel = null;
			if(!StringUtil.isEmpty(jsonData)){
				aModel = new Gson().fromJson(jsonData, new TypeToken<ResvstoreModel>(){}.getType());
				
				String RESV_STOCK_ID = ParamUtil.get(request, "RESV_STOCK_ID");
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("PRD_ID", aModel.getPRD_ID());
				params.put("LEDGER_ID", userBean.getLoginZTXXID());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("RESV_STOCK_ID", RESV_STOCK_ID);
				
				//同一个帐套下，只能设置一个货品的 安全库存和最低库存
				boolean isNotHave = resvstoreService.queryForInt(params);
				if(isNotHave){
					resvstoreService.txEdit(RESV_STOCK_ID,aModel,userBean);
				}else{
					jsonResult = jsonResult(false, "已经设置过该货品的数量!");
				}
				
			}
			
			
		} catch (Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败!");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
    /**
	 * * 删除
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
			String RESV_STOCK_ID = ParamUtil.get(request, "RESV_STOCK_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("RESV_STOCK_ID", RESV_STOCK_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			resvstoreService.txDelete(params);
			resvstoreService.clearCaches(params);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String RESV_STOCK_ID = ParamUtil.get(request, "RESV_STOCK_ID");
		if(!StringUtil.isEmpty(RESV_STOCK_ID)){
			Map<String,String> entry = resvstoreService.load(RESV_STOCK_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
	 *  启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String RESV_STOCK_ID = request.getParameter("RESV_STOCK_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("RESV_STOCK_ID", RESV_STOCK_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_ENABLE);	
			resvstoreService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
        	e.printStackTrace();
			jsonResult = jsonResult(false, "状态修改失败");
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
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String RESV_STOCK_ID = request.getParameter("RESV_STOCK_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("RESV_STOCK_ID", RESV_STOCK_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_DISENABLE);
            resvstoreService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
		    e.printStackTrace();
            jsonResult = jsonResult(false, "状态修改失败!");
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