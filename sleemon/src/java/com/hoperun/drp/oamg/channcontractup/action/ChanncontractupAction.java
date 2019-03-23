/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqAction.java
 */
package com.hoperun.drp.oamg.channcontractup.action;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.drp.oamg.channcontractup.model.ChanncontractupModel;
import com.hoperun.drp.oamg.channcontractup.service.ChanncontractupService;
import com.hoperun.sys.model.UserBean;
/**
 * The Class ChanncontractupAction.
 * 
 * @module 渠道合同管理
 * @func 渠道合同上传
 * @version 1.1
 * @author 刘曰刚
 */
public class ChanncontractupAction extends BaseAction {
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(ChanncontractupAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0033901";
    private static String PVG_EDIT="BS0033902";
    private static String PVG_DELETE="BS0033903";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0033904";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    private static String PVG_TEMP_CREDIT_EDIT="";
    //关闭
    private static String  PVG_CLOSE = "";
    
    //审批流参数
    private static String AUD_TAB_NAME="";//表名
    private static String AUD_TAB_KEY="";//主键
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
	
    private ChanncontractupService channcontractupService;
    
	public ChanncontractupService getChanncontractupService() {
		return channcontractupService;
	}

	public void setChanncontractupService(
			ChanncontractupService channcontractupService) {
		this.channcontractupService = channcontractupService;
	}

	/**
	 * 框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
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
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	
	    ParamUtil.putStr2Map(request, "CHANN_CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "YEAR", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	params.put("module", module);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = channcontractupService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    /**
     * 查看详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_CONTRACT_ID = ParamUtil.get(request, "CHANN_CONTRACT_ID");
        Map<String,String> entry = channcontractupService.load(CHANN_CONTRACT_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("view");
    }
    
    /**
     * 点击新增或修改按钮跳转到编辑页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_CONTRACT_ID = ParamUtil.get(request, "CHANN_CONTRACT_ID");
        Map<String,String> entry=new HashMap<String,String>();
        if (StringUtils.isNotEmpty(CHANN_CONTRACT_ID)) {
        	entry= channcontractupService.load(CHANN_CONTRACT_ID);
            request.setAttribute("rst", entry);
        }
        request.setAttribute("ZTXXID", userBean.getCHANN_ID());
        request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        return mapping.findForward("toedit");
    }
    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        ChanncontractupModel model = new ChanncontractupModel();
        String CHANN_CONTRACT_ID = ParamUtil.get(request, "CHANN_CONTRACT_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <ChanncontractupModel>() {
            }.getType());
        }
    	try {
    		channcontractupService.txEdit(CHANN_CONTRACT_ID, model, userBean);
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
     * 删除
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void delete(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response){
    	// TODO Auto-generated method stub
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_CONTRACT_ID = ParamUtil.get(request, "CHANN_CONTRACT_ID");
        if (StringUtils.isNotEmpty(CHANN_CONTRACT_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("CHANN_CONTRACT_ID", CHANN_CONTRACT_ID);
                params.put("UPDATOR", userBean.getXTYHID());
    		    params.put("UPD_NAME", userBean.getYHM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    		    channcontractupService.txDelete(CHANN_CONTRACT_ID, userBean);
    		    channcontractupService.clearCaches(CHANN_CONTRACT_ID);
            } catch (Exception ex) {
            	ex.printStackTrace();
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
	 * * 提交
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
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		logger.info("按钮修改为启用单条记录开始");
		try {
			String CHANN_CONTRACT_ID = request.getParameter("CHANN_CONTRACT_ID");
			Map<String, String> map = new HashMap<String, String>();
			map.put("CHANN_CONTRACT_ID", CHANN_CONTRACT_ID);
			map.put("STATE", BusinessConsts.COMMIT);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			channcontractupService.toCommit(map);
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		} catch (Exception e) {
			jsonResult = jsonResult(false, "提交失败");
			logger.info(e);
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
	    	pvgMap.put("PVG_CLOSE",QXUtil.checkPvg(userBean, PVG_CLOSE) );
	    	pvgMap.put("PVG_TEMP_CREDIT_EDIT",QXUtil.checkPvg(userBean, PVG_TEMP_CREDIT_EDIT) );
	    	
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 
	    
}
