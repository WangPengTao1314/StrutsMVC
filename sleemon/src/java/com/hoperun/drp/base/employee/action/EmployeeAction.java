/**
 * prjName:喜临门营销平台
 * ucName:人员信息
 * fileName:EmployeeAction
*/
package com.hoperun.drp.base.employee.action;
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
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.security.MD5Encrypt;
import com.hoperun.drp.base.employee.model.EmployeeModel;
import com.hoperun.drp.base.employee.model.EmployeeModelChld;
import com.hoperun.drp.base.employee.service.EmployeeService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class EmployeeAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0010201";
    private static String PVG_EDIT="FX0010202";
    private static String PVG_DELETE="FX0010203";
    //启用,停用
    private static String PVG_START_STOP="FX0010204";
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
    private static String AUD_BUSS_STATE="";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private EmployeeService employeeService;
   
	
	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
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
	    ParamUtil.putStr2Map(request, "RYBH", params);//人员编号
	    ParamUtil.putStr2Map(request, "XM", params);//姓名
	    ParamUtil.putStr2Map(request, "BMBH", params);//部门编号
	    ParamUtil.putStr2Map(request, "BMMC", params);//部门名称
	    ParamUtil.putStr2Map(request, "XB", params);//性别
	    ParamUtil.putStr2Map(request, "RYZT", params);//人员状态
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "JSBH", params);//角色编号
        ParamUtil.putStr2Map(request, "JSMC", params);//角色名称
        
       //只查询0的记录。1为删除。0为正常
        params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("ZTXXID", userBean.getLoginZTXXID());//查询列表条件为登陆帐套id
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "r.CRETIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = employeeService.pageQuery(params, pageNo);
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
        String RYXXID =ParamUtil.get(request, "RYXXID");
        if(!StringUtil.isEmpty(RYXXID))
        {
        	 List <EmployeeModelChld> result = employeeService.childQuery(RYXXID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("RYXXID", RYXXID);
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
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
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String TERM_DECT_CTR_FLAG=employeeService.getTERM_DECT_CTR_FLAG(userBean.getCHANN_ID());
		String RYXXID = ParamUtil.get(request, "RYXXID");
		Map<String,String> entry=new HashMap<String,String>();
		if(!StringUtil.isEmpty(RYXXID)){
			entry= employeeService.load(RYXXID);
		}
		//通用查询条件
		entry.put("ZTXXID", userBean.getLoginZTXXID());//条件为登陆帐套id
		entry.put("topTab", userBean.getCHANN_NO());//传递当前登录人员渠道，验证新增人员编号
		request.setAttribute("rst", entry);
		request.setAttribute("TERM_DECT_CTR_FLAG", TERM_DECT_CTR_FLAG);//判断当前登录人是否有终端销售折扣控制标记
		return mapping.findForward("toedit");
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
        String XTYHJSIDS = request.getParameter("XTYHJSIDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(XTYHJSIDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("XTYHJSIDS",XTYHJSIDS);
          List <EmployeeModelChld> list = employeeService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        String RYXXID = request.getParameter("RYXXID");//子表list页面传值过来带入子表edit页面
        String CHANN_TYPE = userBean.getCHANN_TYPE();
        String ROLE_TYPE = "";
        if("加盟商".equals(CHANN_TYPE)){
        	ROLE_TYPE = "DRP_J";
        }
 
        if("区域服务中心".equals(CHANN_TYPE)){
        	ROLE_TYPE = "DRP_Q";
        }

        if("直营办".equals(CHANN_TYPE)){
        	ROLE_TYPE = "DRP_F";
        }

 
        request.setAttribute("RYXXID", RYXXID);
        request.setAttribute("ROLE_TYPE", ROLE_TYPE);
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
            String RYXXID = ParamUtil.get(request, "RYXXID");
            String parentJsonData = ParamUtil.get(request, "jsonData");
            EmployeeModel model = new Gson().fromJson(parentJsonData, new TypeToken <EmployeeModel>() {}.getType());
            if(StringUtil.isEmpty(RYXXID)){
            	if(employeeService.getRyxxExits(model.getRYBH())>0){//判断人员编号是否重复
                	jsonResult = jsonResult(false, "人员编号重复,请重新输入");
                	if (null != writer) {
                        writer.write(jsonResult);
                        writer.close();
                    }
                	return;
                }
            	if(employeeService.getRHMExits(model.getYHM())>0){//判断人员用户名是否重复
                	jsonResult = jsonResult(false, "用户名重复,请重新输入");
                	if (null != writer) {
                        writer.write(jsonResult);
                        writer.close();
                    }
                	return;
                }
            }
        	RYXXID=employeeService.txEdit(RYXXID, model, userBean);
            jsonResult = jsonResult(true, RYXXID);

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
            String RYXXID = request.getParameter("RYXXID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <EmployeeModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <EmployeeModelChld>>() {
                }.getType());
                employeeService.txChildEdit(RYXXID, modelList);
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
			String RYXXID = ParamUtil.get(request, "RYXXID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("RYXXID", RYXXID);
            params.put("UPDATER", userBean.getXTYHID());
            params.put("DELFLAG", BusinessConsts.DEL_FLAG_DROP);
            employeeService.txDelete(params);
			employeeService.clearCaches(params);
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
            String XTYHJSIDS = request.getParameter("XTYHJSIDS");
            //批量删除，多个Id之间用逗号隔开
            employeeService.txBatch4DeleteChild(XTYHJSIDS);
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
        String TERM_DECT_CTR_FLAG=employeeService.getTERM_DECT_CTR_FLAG(userBean.getCHANN_ID());
        request.setAttribute("TERM_DECT_CTR_FLAG", TERM_DECT_CTR_FLAG);//判断当前登录人是否有终端销售折扣控制标记
		String RYXXID = ParamUtil.get(request, "RYXXID");
		if(!StringUtil.isEmpty(RYXXID)){
			Map<String,String> entry = employeeService.load(RYXXID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
   
    /**
     * 停用/启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        String RYXXID = request.getParameter("RYXXID");
        String tab=request.getParameter("tab");
        Map <String, String> entry = new HashMap<String, String>();
        try {
        	entry.put("RYXXID", RYXXID);
        	entry.put("UPDATER", userBean.getXTYHID());
        	entry.put("UPDTIME", "系统时间");
            if (tab.equals("stop")) {
            	entry.put("RYZT", BusinessConsts.JC_STATE_DISENABLE);
            }
            else {
            	entry.put("RYZT", BusinessConsts.JC_STATE_ENABLE);
            }
            employeeService.txUpdateById(entry);
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "状态修改失败");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 设为默认密码按钮.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     * 
     * @author tang_yun
     */
    public void updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
	        Map <String, String> params = new HashMap <String, String>();
	        ParamUtil.putStr2Map(request, "ryxxid", params);
	        String pwd = Properties.getString("DEF_PWD");
	        params.put("password", MD5Encrypt.MD5(pwd));
	        employeeService.updatePassword(params);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = jsonResult(false, "更新失败");
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