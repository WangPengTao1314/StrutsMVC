/**
 * prjName:喜临门营销平台
 * ucName:门店下级退货单
 * fileName:DrpreturnaAction
*/
package com.hoperun.drp.sale.drpreturna.action;
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
import org.apache.log4j.Logger;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModel;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModelChld;
import com.hoperun.drp.sale.drpreturna.service.DrpreturnaService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 15:41:36
 */
public class DrpreturnaAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(DrpreturnaAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0023001";
    //导出
    private static String PVG_EXPORT = "FX0023002";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    private static String PVG_PRINT="FX0023003";
    //启用,停用
    private static String PVG_START_STOP="";
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
	private DrpreturnaService drpreturnaService;
    /**
	 * Sets the Drpreturna service.
	 * 
	 * @param DrpreturnaService the new Drpreturna service
	 */
	public void setDrpreturnaService(DrpreturnaService drpreturnaService) {
		this.drpreturnaService = drpreturnaService;
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
	    ParamUtil.putStr2Map(request, "PRD_RETURN_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    ParamUtil.putStr2Map(request, "ORG_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_BEG", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_END", params);
	    
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = drpreturnaService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_RETURN_ID =ParamUtil.get(request, "PRD_RETURN_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_ID))
        {
        	 List <DrpreturnaModelChld> result = drpreturnaService.childQuery(PRD_RETURN_ID);
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
		String PRD_RETURN_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(PRD_RETURN_ID)){
			Map<String,String> entry = drpreturnaService.load(PRD_RETURN_ID);
			request.setAttribute("rst", entry);
		}
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
        String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_ID))
        {
        	 List <DrpreturnaModelChld> result = drpreturnaService.childQuery(PRD_RETURN_ID);
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
        String PRD_RETURN_DTL_IDs = request.getParameter("PRD_RETURN_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_RETURN_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_RETURN_DTL_IDS",PRD_RETURN_DTL_IDs);
          List <DrpreturnaModelChld> list = drpreturnaService.loadChilds(params);
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
            String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            DrpreturnaModel model = new Gson().fromJson(parentJsonData, new TypeToken <DrpreturnaModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <DrpreturnaModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <DrpreturnaModelChld>>() {
                }.getType());
            }
            drpreturnaService.txEdit(PRD_RETURN_ID, model, chldModelList, userBean);
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
            String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <DrpreturnaModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <DrpreturnaModelChld>>() {
                }.getType());
                drpreturnaService.txChildEdit(PRD_RETURN_ID, modelList);
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
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_RETURN_ID", PRD_RETURN_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			drpreturnaService.txDelete(params);
			drpreturnaService.clearCaches(params);
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
            String PRD_RETURN_DTL_IDs = request.getParameter("PRD_RETURN_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            drpreturnaService.txBatch4DeleteChild(PRD_RETURN_DTL_IDs);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
		if(!StringUtil.isEmpty(PRD_RETURN_ID)){
			Map<String,String> entry = drpreturnaService.load(PRD_RETURN_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * 
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
            String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
            List <DrpreturnaModelChld> list = drpreturnaService.childQuery(PRD_RETURN_ID);
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
    
    // 导出
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EXPORT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PRD_RETURN_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
 	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    ParamUtil.putStr2Map(request, "ORG_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_BEG", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_END", params);
	    
	    
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		List list = drpreturnaService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="退货单编号,单据类型,来源单据编号,退货方编号,退货方名称,负责人,货品编号,货品名称,规格型号,特殊工艺,退货数量,退货单价,退货金额,退货原因,制单人,制单时间,状态";
		String tmpContent = "PRD_RETURN_NO,BILL_TYPE,FROM_BILL_NO,RETURN_CHANN_NO,RETURN_CHANN_NAME,RSP_NAME," 
				+"PRD_NO,PRD_NAME,PRD_SPEC,SPCL_DTL_REMARK,RETURN_NUM,RETURN_PRICE,RETURN_AMOUNT,RETURN_RSON,CRE_NAME,CRE_TIME,STATE";
		String colType = "string,string,string,string,string,string,string,string,string,string,number,number,number,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "直营办下级退货单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 批量打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward allPrint(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String sessionId = request.getParameter("sessionId");
		 	 String PRD_RETURN_IDS=(String) request.getSession().getAttribute(sessionId);
		 	StringBuffer paramSql=new StringBuffer();
		 	 paramSql.append("batchReturnPrint.raq&PRD_RETURN_IDS=").append(PRD_RETURN_IDS);
		 	 /**
			 StringBuffer storeSql = new StringBuffer();
			 StringBuffer sql=new StringBuffer();
			 StringBuffer dtlSql=new StringBuffer();
			 storeSql.append("rptSql=select * from PRINTRETURNA_VW where OLD_PRD_RETURN_ID  in (").append(PRD_RETURN_IDS).append("); ");
			 dtlSql.append("dtlSql= select * from PRINTRETURNA_DTL_VW where OLD_PRD_RETURN_ID in (").append(PRD_RETURN_IDS).append(");");
			 sql.append(storeSql.toString()).append(dtlSql.toString());
			 request.getSession().removeAttribute(sessionId);
			 sql.append(storeSql.toString()).append(dtlSql.toString());
			  request.setAttribute("reportFileName", "batchReturnPrint.raq");
			 **/
			 request.setAttribute("params",paramSql);
			
			 return mapping.findForward("flashReportPrint");
	}
	public ActionForward toAllPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "PRD_RETURN_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
        ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_NAME", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    ParamUtil.putStr2Map(request, "ORG_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_BEG", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_END", params);
	    
	    params.put("LEDGER_ID", userBean.getLoginZTXXID());
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = drpreturnaService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("allPrint");
	}
	public void getSaleSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_RETURN_IDS = request.getParameter("PRD_RETURN_IDS");
			String sessionId=StringUtil.uuid32len();
			Map<String,String> map=new HashMap<String, String>();
			if(!StringUtil.isEmpty(PRD_RETURN_IDS)){
				request.getSession().setAttribute(sessionId, PRD_RETURN_IDS);
				map.put("sessionId",sessionId);
			}
			jsonResult = new Gson().toJson(new Result(true, map, ""));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "存储选取退货单信息失败！");
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
	    	pvgMap.put("PVG_EXPORT",QXUtil.checkPvg(userBean, PVG_EXPORT) );
	    	pvgMap.put("PVG_PRINT",QXUtil.checkPvg(userBean, PVG_PRINT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}