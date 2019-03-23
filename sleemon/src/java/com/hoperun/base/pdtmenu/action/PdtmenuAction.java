
package com.hoperun.base.pdtmenu.action;

import java.io.PrintWriter;
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
import com.hoperun.base.pdtmenu.model.PdtmenuModel;
import com.hoperun.base.pdtmenu.model.PdtmenuTree;
import com.hoperun.base.pdtmenu.service.PdtmenuService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
/**
 * The Class PdtmenuAction.
 * 
 * @module 系统管理
 * @func 货品分类
 * @version 1.1
 * @author 刘曰刚
 */
public class PdtmenuAction extends BaseAction{
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(PdtmenuAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="XT0013001";
    private static String PVG_EDIT="XT0013002";
    private static String PVG_DELETE="XT0013003";
    //启用,停用
    private static String PVG_START_STOP="XT0013004";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
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
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    
    public PdtmenuService pdtmenuService;
    
    /**
	 * 货品框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
		return mapping.findForward("frames");
	}
	/**
	 *  查询结果列表
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRD_ID", ParamUtil.utf8Decoder(request, "PRD_ID"));
		params.put("productid", ParamUtil.utf8Decoder(request, "productid"));
		params.put("PRD_NO", ParamUtil.utf8Decoder(request, "PRD_NO"));
		params.put("PRD_NAME", ParamUtil.utf8Decoder(request, "PRD_NAME"));
		params.put("BRAND", ParamUtil.utf8Decoder(request, "BRAND"));
		params.put("PAR_PRD_NO", ParamUtil.utf8Decoder(request, "PAR_PRD_NO"));
		params.put("PAR_PRD_NAME", ParamUtil.utf8Decoder(request, "PAR_PRD_NAME"));
		params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
		params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
		//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询终结点标记为0的
		params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME,u.PRD_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = pdtmenuService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//初始时只显示未接收的数据
		if(StringUtil.isEmpty(search))
	    { 
			//状态为启用
			params.put("STATE",BusinessConsts.JC_STATE_ENABLE);
	    }
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//查询所有品牌名称
        List<String> list=pdtmenuService.getBrand();
        request.setAttribute("list", list);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	/**
	 *  货品分类编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
         String PRD_ID = ParamUtil.get(request, "PRD_ID");
         if(!StringUtil.isEmpty(PRD_ID))
         {
        	Map<String, String> entry = pdtmenuService.load(PRD_ID);
 			request.setAttribute("rst", entry);
         }
         //查询所有品牌名称
         List<String> list=pdtmenuService.getBrand();
         request.setAttribute("list", list);
		return mapping.findForward("toedit");
	}
	/**
	 *  货品信息编辑 新增/修改
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
		logger.info("Enter edit()");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		PdtmenuModel PdtmenuModel = new PdtmenuModel();
		String PRD_ID = ParamUtil.get(request, "PRD_ID");
		if (!StringUtil.isEmpty(jsonData)) {
			PdtmenuModel = new Gson().fromJson(jsonData,
					new TypeToken<PdtmenuModel>() {
					}.getType());
		}
		try {
		if(StringUtils.isEmpty(PRD_ID)&&(pdtmenuService.getCountPRD_NO(PdtmenuModel.getPRD_NO())>0)){
			throw new ServiceException("编号有重复值，请重新输入!");
		}
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_ID", PRD_ID);
			PRD_ID = pdtmenuService.txEdit(PRD_ID,PdtmenuModel,userBean);
			jsonResult = jsonResult(true, PRD_ID);
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
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
	   
	   /**
     * 显示树.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        List <PdtmenuTree> trees;
        try {
            trees = pdtmenuService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("msg", "获取货品信息失败！");
            return mapping.findForward(FAILURE);
        }
    }
    /**
	 *  查看货品详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String PRD_ID = ParamUtil.get(request, "PRD_ID");
		if(!StringUtil.isEmpty(PRD_ID))
        {
			String fileName=LogicUtil.getPicPath("");
			Map<String, String> entry = pdtmenuService.load(PRD_ID);
			String PIC_PATH=LogicUtil.getPicPath(entry.get("PIC_PATH"));
			String DTL_PATH=LogicUtil.getPicPath(entry.get("DTL_PATH"));
			request.setAttribute("PIC_PATH", PIC_PATH);
			request.setAttribute("DTL_PATH", DTL_PATH);
			request.setAttribute("fileName", fileName);
			request.setAttribute("rst", entry);
        }
		return mapping.findForward("view");
	}

	/**
	 *  删除
	 * @param mapping the mapping
	 * @param formthe form
	 * @param requestthe request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_ID = ParamUtil.get(request, "PRD_ID");
			Map <String, String> params = new HashMap <String, String>();
		    params.put("PRD_ID", PRD_ID);
		    params.put("UPDATOR", userBean.getXTYHID());
		    params.put("UPD_NAME", userBean.getYHM());
		    params.put("UPD_TIME", DateUtil.now());
		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		    pdtmenuService.txDelete(params);
		    pdtmenuService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 *  按钮修改状态为启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String PRD_ID = request.getParameter("PRD_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_ID", PRD_ID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPD_TIME", DateUtil.now());
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			pdtmenuService.txStartById(params);
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
	 * 按钮修改状态为停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	//权限验证
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String PRD_ID = request.getParameter("PRD_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_ID", PRD_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPD_TIME", DateUtil.now());
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            pdtmenuService.txStopById(params);
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
	 * @return the pdtmenuService
	 */
	public PdtmenuService getPdtmenuService() {
		return pdtmenuService;
	}

	/**
	 * @param pdtmenuService the pdtmenuService to set
	 */
	public void setPdtmenuService(PdtmenuService pdtmenuService) {
		this.pdtmenuService = pdtmenuService;
	}

}
