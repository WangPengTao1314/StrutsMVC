/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：GrgzjhAction.java
 */
package com.hoperun.base.grgzjh.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hoperun.base.grgzjh.model.GrgzjhModel;
import com.hoperun.base.grgzjh.model.GrgzjhModelChld;
import com.hoperun.base.grgzjh.service.GrgzjhService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
/**
 * The Class GrgzjhAction.
 * 
 * @module 系统管理
 * @func 个人工作计划
 * @version 1.0
 * @author 吴军
 */
public class GrgzjhAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(GrgzjhAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0033601";
    private static String PVG_EDIT="BS0033602";
    private static String PVG_DELETE="BS0033603";
    
  //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0033604";
    //流程跟踪
    private static String PVG_TRACE= "BS0033605";
   
   
    /**审批维护必须维护字段**/
   
  //审核模块                             
    private static String PVG_BWS_AUDIT="BS0033701";
    private static String PVG_AUDIT_AUDIT="BS0033702";
    private static String PVG_TRACE_AUDIT="BS0033703";
    private static String PVG_EDIT_AUDIT ="";
  //审批流参数
    private static String AUD_TAB_NAME="ERP_PER_WORK_PLAN";
    private static String AUD_TAB_KEY="PER_WORK_PLAN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_PER_WORK_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	// 字段权限
	// 主表字段
    private static String AUD_FED_XFRID="?";
    private static String AUD_FED_XFR="?";
    private static String AUD_FED_XFYF="?";
    private static String AUD_FED_XFNF="?";
    private static String AUD_FED_XFRXM="?";
    private static String AUD_FED_SSQYID="?";
	//明细字段
    private static String AUD_SED_SSQY="?";
    private static String AUD_SED_XFNF="?";
    private static String AUD_SED_XFYF="?";
    private static String AUD_SED_XFRXM="?";
    private static String AUD_SED_XFR="?";
    private static String AUD_SED_XFRID="?";
    private static String AUD_SED_XFWDID="?";
    /**审批 end**/
    /** 业务service*/
	private GrgzjhService grgzjhService;
    /**
	 * Sets the Grgzjh service.
	 * 
	 * @param GrgzjhService the new Grgzjh service
	 */
	public void setGrgzjhService(GrgzjhService grgzjhService) {
		this.grgzjhService = grgzjhService;
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
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG  
        		&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))))
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
        if(Consts.FUN_CHEK_PVG 
        		 && (!QXUtil.checkMKQX(userBean, PVG_BWS) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "XFSJ_BEG", params);
	    ParamUtil.putStr2Map(request, "XFSJ_END", params);
	    //ParamUtil.putStr2Map(request, "RYID", params);
	    ParamUtil.putStr2Map(request, "XM", params);
	    ParamUtil.putStr2Map(request, "PER_WORK_PLAN_NO", params);
	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
	    ParamUtil.putStr2Map(request, "STATE", params);

       //只查询0的记录。1为删除。0为正常
        params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,"u.PER_WORK_PLAN_ID", AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		IListPage page = grgzjhService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("module", module);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 * 个人工作计划明细列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG
        		&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}

//        request.setAttribute("BMRY",wdxfjhService.currentBmRyxx(userBean.getBMXXID()));
        request.setAttribute("DEPT_ID", userBean.getBMXXID());

        String XFJHID = ParamUtil.get(request,"XFJHID");
        Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        String currentNY ="";
        String PLAN_PSON_ID = "";
        Map<String, String> model = new HashMap<String,String>();
        if(!StringUtil.isEmpty(XFJHID)){
            model = grgzjhService.load(XFJHID);
            PLAN_PSON_ID =  model.get("PLAN_PSON_ID");
            request.setAttribute("PLAN_PSON_NAME", model.get("PLAN_PSON_NAME"));
            request.setAttribute("DEPT_ID", model.get("DEPT_ID"));
            currentNY =  StringUtil.nullToSring(model.get("PLAN_YEAR"))+"-"
            +StringUtil.nullToSring(model.get("PLAN_MONTH"));
            
            List<HashMap<String, String>> events = grgzjhService.loadEventsForCurrentUser(PLAN_PSON_ID,currentNY);
            request.setAttribute("events",events);
            
        }else{
        	PLAN_PSON_ID = userBean.getRYXXID();
            request.setAttribute("PLAN_PSON_NAME",userBean.getXM());
            request.setAttribute("DEPT_ID", userBean.getBMXXID());
            currentNY = formatter.format(date);
        }
        
        
       //菜单导航条
        request.setAttribute("currentNYR", formatter2.format(date));
        request.setAttribute("currentNY", currentNY);
      
        request.setAttribute("rst", model);
        request.setAttribute("RYXXID", PLAN_PSON_ID);
        request.setAttribute("currentUser", userBean.getRYXXID());
		return mapping.findForward("mxlist");
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
        if(Consts.FUN_CHEK_PVG
        		&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String JHID =ParamUtil.get(request, "JHID");
        if(!StringUtil.isEmpty(JHID))
        {
        	 List <GrgzjhModelChld> result = grgzjhService.childQuery(JHID);
             request.setAttribute("rst", result);
            Map<String, String> model = grgzjhService.load(JHID);
            request.setAttribute("currentNY", (((HashMap) model).get("PLAN_YEAR")).toString()+"-"+(((HashMap) model).get("PLAN_MONTH")).toString());
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
		String selRowId = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(selRowId)){
			Map<String,String> entry = grgzjhService.load(selRowId);
			request.setAttribute("rst", entry);
		}else{
            setDefaultValue(userBean, request);
        }
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("toedit");
	}

    public void setDefaultValue(UserBean userBean, HttpServletRequest request){
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String currentNYR = formatter.format(date);
        String[] nyr = currentNYR.split("-");
        Map<String,String> entry = new HashMap<String, String>();
        entry.put("PLAN_PSON_ID",userBean.getRYXXID());
        entry.put("PLAN_PSON_NAME",userBean.getXM());
        entry.put("PLAN_YEAR",nyr[0]);
        entry.put("PLAN_MONTH",nyr[1]);
        entry.put("PLAN_DAY",nyr[2]);
        request.setAttribute("rst",entry);
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
        String XFJHID = ParamUtil.get(request, "XFJHID");
        if(!StringUtil.isEmpty(XFJHID))
        {
        	 List <GrgzjhModelChld> result = grgzjhService.childQuery(XFJHID);
             request.setAttribute("rst", result);
        }else{
            setDefaultValue(userBean, request);
        }
		request.setAttribute("pvg",setPvg(userBean));
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
		String XFJHID = ParamUtil.get(request, "XFJHID");
		try {
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            GrgzjhModel model = new Gson().fromJson(parentJsonData, new TypeToken <GrgzjhModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <GrgzjhModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <GrgzjhModelChld>>() {
                }.getType());
            }
            grgzjhService.txEdit(XFJHID, model, chldModelList, userBean);
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
        String XFJHID = request.getParameter("XFJHID");
        try {
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <GrgzjhModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <GrgzjhModelChld>>() {
                }.getType());
                grgzjhService.txChildEdit(XFJHID, modelList, userBean);
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
			String PER_WORK_PLAN_ID = ParamUtil.get(request, "PER_WORK_PLAN_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PER_WORK_PLAN_ID", PER_WORK_PLAN_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			grgzjhService.txDelete(params);
			grgzjhService.clearCaches(params);
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
            String PER_WORK_PLAN_IDs = request.getParameter("PER_WORK_PLAN_IDs");
            //批量删除，多个Id之间用逗号隔开
            grgzjhService.txBatch4DeleteChild(PER_WORK_PLAN_IDs);
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
            String XFJHID = request.getParameter("XFJHID");
            List <GrgzjhModelChld> list = grgzjhService.childQuery(XFJHID);
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
     * 生成唯一ID
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void getUUID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            jsonResult = jsonResult(true,StringUtil.uuid32len());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "获取UUID失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 添加个人工作计划
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void addEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
        {
            throw new ServiceException("对不起，您没有权限 !");
        }
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PER_WORK_PLAN_ID = ParamUtil.get(request, "PER_WORK_PLAN_ID");
            GrgzjhModel model = new GrgzjhModel();
            model.setPLAN_YEAR( ParamUtil.get(request, "PLAN_YEAR"));
            model.setPLAN_MONTH(ParamUtil.get(request, "PLAN_MONTH"));
            model.setPLAN_PSON_ID( ParamUtil.get(request, "ryxxid"));
            model.setREMARK(ParamUtil.get(request, "REMARK"));
            String jsonDate = ParamUtil.get(request, "events");
            List <GrgzjhModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <GrgzjhModelChld>>() {
                }.getType());
            }
            PER_WORK_PLAN_ID = grgzjhService.txEdit(PER_WORK_PLAN_ID,model,chldModelList,userBean);
            jsonResult = jsonResult(true, PER_WORK_PLAN_ID);
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
     * 显示个人工作计划
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void allEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String xfrid = ParamUtil.get(request,"xfrid");
            String currentNY = ParamUtil.get(request,"currentNY");
            request.setAttribute("RYXXID", xfrid);
            request.setAttribute("currentNY", currentNY);
            List<HashMap<String, String>> list = grgzjhService.loadEventsForCurrentUser(xfrid,currentNY);
            StringBuffer message = new StringBuffer("[");
            int len =  list.size();
            HashMap<String, String> temp;
            for(int i = 0; i< len; i++){
                temp = list.get(i);
                message.append("{id:'"+temp.get("PER_WORK_PLAN_DTL_ID")+"',title:'"+temp.get("PLAN_CONTENT")+"',start:'"+temp.get("ESTART")+"',className:'event-size',xfjhid:'"+temp.get("PER_WORK_PLAN_ID")+"'}");
                if(i != len -1){
                    message.append(",");
                }
            }
            message.append("]");
            jsonResult = jsonResult(true,message.toString());
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
     * 删除个人工作计划
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void deleteEvent(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
        UserBean userBean = ParamUtil.getUserBean(request);
        if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
            throw new ServiceException("对不起，您没有权限 !");
        }

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	HashMap<String, String> params = new HashMap<String, String>(5);
            params.put("PER_WORK_PLAN_DTL_ID",ParamUtil.get(request, "PER_WORK_PLAN_DTL_ID"));
            grgzjhService.deleteChild(params);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败!");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

    
    /**
     * 修改备注
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void saveRemark(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String currentUser = ParamUtil.get(request, "currentUser");
        	HashMap<String, String> params = new HashMap<String, String>(5);
            params.put("PER_WORK_PLAN_ID",ParamUtil.get(request, "PER_WORK_PLAN_ID"));
            params.put("REMARK",ParamUtil.get(request, "REMARK"));
            grgzjhService.update(params);
            jsonResult = jsonResult(true, "保存成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败!");
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
            pvgMap.put("AUD_FED_XFRID",QXUtil.checkPvg(userBean, AUD_FED_XFRID));
            pvgMap.put("AUD_FED_XFR",QXUtil.checkPvg(userBean, AUD_FED_XFR));
            pvgMap.put("AUD_FED_XFYF",QXUtil.checkPvg(userBean, AUD_FED_XFYF));
            pvgMap.put("AUD_FED_XFNF",QXUtil.checkPvg(userBean, AUD_FED_XFNF));
            pvgMap.put("AUD_FED_XFRXM",QXUtil.checkPvg(userBean, AUD_FED_XFRXM));
            pvgMap.put("AUD_FED_SSQYID",QXUtil.checkPvg(userBean, AUD_FED_SSQYID));
            pvgMap.put("AUD_SED_SSQY",QXUtil.checkPvg(userBean, AUD_SED_SSQY));
            pvgMap.put("AUD_SED_XFNF",QXUtil.checkPvg(userBean, AUD_SED_XFNF));
            pvgMap.put("AUD_SED_XFYF",QXUtil.checkPvg(userBean, AUD_SED_XFYF));
            pvgMap.put("AUD_SED_XFRXM",QXUtil.checkPvg(userBean, AUD_SED_XFRXM));
            pvgMap.put("AUD_SED_XFR",QXUtil.checkPvg(userBean, AUD_SED_XFR));
            pvgMap.put("AUD_SED_XFRID",QXUtil.checkPvg(userBean, AUD_SED_XFRID));
            pvgMap.put("AUD_SED_XFWDID",QXUtil.checkPvg(userBean, AUD_SED_XFWDID));
	    	return  pvgMap;
	   }
}