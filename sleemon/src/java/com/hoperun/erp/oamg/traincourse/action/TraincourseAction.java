package com.hoperun.erp.oamg.traincourse.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.erp.oamg.traincourse.model.TraincourseModel;
import com.hoperun.erp.oamg.traincourse.service.TraincourseService;
import com.hoperun.sys.model.UserBean;

/**
 * 培训课程维护
 * @author gu_hongxiu
 *
 */
public class TraincourseAction extends BaseAction {

	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0031301";
    private static String PVG_EDIT="BS0031302";
    private static String PVG_DELETE="BS0031303";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0031304";
    private static String PVG_TRACE="BS0031305";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0030501";
    private static String PVG_AUDIT_AUDIT="BS0030502";
    private static String PVG_TRACE_AUDIT="BS0030503";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_TRAIN_COURSE";
    private static String AUD_TAB_KEY="TRAIN_COURSE_ID";    	
    private static String AUD_BUSS_TYPE="ERP_TRAIN_COURSE_AUDIT";
    private static String AUD_BUSS_STATE="STATE";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private TraincourseService traincourseService;

	public TraincourseService getTraincourseService() {
		return traincourseService;
	}

	public void setTraincourseService(TraincourseService traincourseService) {
		this.traincourseService = traincourseService;
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
		ParamUtil.putStr2Map(request, "TRAIN_COURSE_NO", params);
		ParamUtil.putStr2Map(request, "TRAIN_COURSE_NAME", params);
		ParamUtil.putStr2Map(request, "TRAIN_TYPE", params);
	    ParamUtil.putStr2Map(request, "TRAIN_ADDR", params);
	    ParamUtil.putStr2Map(request, "TRAIN_GOAL", params);	   
	    ParamUtil.putStr2Map(request, "STATE", params);
	  	    
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    
		//设置帐套id
		//params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//设置系统用户id
		params.put("XTYH_ID", userBean.getXTYHID());
		
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);       
       
		//字段排序
        ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
                
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = traincourseService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
				
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
        String TRAIN_COURSE_ID = ParamUtil.get(request, "TRAIN_COURSE_ID");
        if(StringUtils.isNotEmpty(TRAIN_COURSE_ID)){
        	Map<String,String> entry = traincourseService.load(TRAIN_COURSE_ID);
                 
            request.setAttribute("rst", entry);
        }
        
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
        String TRAIN_COURSE_ID = ParamUtil.get(request, "TRAIN_COURSE_ID");
        Map<String,String> entry = new HashMap<String,String>();
        
        if (StringUtils.isNotEmpty(TRAIN_COURSE_ID)) {
        	entry = traincourseService.load(TRAIN_COURSE_ID); 
        	
        	String picPath = traincourseService.loadAtt(TRAIN_COURSE_ID);
            if(StringUtils.isNotEmpty(picPath)){
            	entry.put("PIC_PATH",picPath);
            }
        }
        
        entry.put("LEDGER_ID", userBean.getLoginZTXXID());      
        entry.put("XTYH_ID", userBean.getXTYHID());
        request.setAttribute("rst", entry);
        
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
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        TraincourseModel model = new TraincourseModel();
        String TRAIN_COURSE_ID = ParamUtil.get(request, "TRAIN_COURSE_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <TraincourseModel>() {
            }.getType());
        }
        
        TRAIN_COURSE_ID = traincourseService.txEdit(TRAIN_COURSE_ID, model, userBean);
		jsonResult = jsonResult(true, TRAIN_COURSE_ID);       
       
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
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String TRAIN_COURSE_ID = ParamUtil.get(request, "TRAIN_COURSE_ID");
        if (StringUtils.isNotEmpty(TRAIN_COURSE_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("TRAIN_COURSE_ID", TRAIN_COURSE_ID);
                params.put("FROM_BILL_ID", TRAIN_COURSE_ID);
                
                params.put("UPDATOR", userBean.getRYXXID());
    		    params.put("UPD_NAME", userBean.getXM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    		    traincourseService.txDelete(params);
    		    traincourseService.clearCaches(TRAIN_COURSE_ID);
            } catch (Exception ex) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	
	/**
	 * 设置权限Map
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
