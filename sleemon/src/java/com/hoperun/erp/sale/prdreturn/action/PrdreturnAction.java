/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnAction
*/
package com.hoperun.erp.sale.prdreturn.action;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModel;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModelChld;
import com.hoperun.erp.sale.prdreturn.service.PrdreturnService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public class PrdreturnAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PrdreturnAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0012201";
    private static String PVG_EDIT="BS0012202";
    private static String PVG_DELETE="BS0012203";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0012204";
    private static String PVG_TRACE="BS0012205";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0012301";
    private static String PVG_AUDIT_AUDIT="BS0012303";
    private static String PVG_TRACE_AUDIT="BS0012302";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_PRD_RETURN";
    private static String AUD_TAB_KEY="PRD_RETURN_ID";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="ERP_PRD_RETURN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
	/**鉴定页面权限**/
	//浏览
	private static String COMMIT_TO_PVG="BS0012401";
	//鉴定
	private static String COMMIT_TO_FACTORY = "BS0012402";
	//编辑
	private static String COMMIT_TO_EDIT="BS0012403";
	
	
	/** 业务service*/
	private PrdreturnService prdreturnService;
    /**
	 * Sets the Prdreturn service.
	 * 
	 * @param PrdreturnfinService the new Prdreturn service
	 */
	public void setPrdreturnService(PrdreturnService prdreturnService) {
		this.prdreturnService = prdreturnService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_PVG)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("flag", ParamUtil.get(request, "flag"));
		
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_PVG)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "PRD_RETURN_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_BEG", params);
        ParamUtil.putStr2Map(request, "EXPECT_RETURNDATE_END", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        
        ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	     
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String decide = ParamUtil.get(request, "flag");
		String STATE = ParamUtil.get(request, "STATE");
		String PVG=PVG_BWS;
	    
	    String moduleName = "退货单维护";
	    if("wh".equals(module)){
	    	moduleName = "退货单维护";
	    	if(StringUtil.isEmpty(search)){
	    		params.put("allSTATE", "'未提交','撤销','否决'");
	    	}else if(StringUtil.isEmpty(STATE)){
	    		params.put("allSTATE", "'未提交','提交','撤销','否决','已鉴定','审核通过'");
	    	}else{
	    		params.put("STATE", STATE);
	    	}
	    }
	    if("sh".equals(module)){
	    	moduleName = "退货单审核";
	    	if(StringUtil.isEmpty(STATE) || StringUtil.isEmpty(search)){
	    		params.put("allSTATE", "'提交','审核通过','否决'");
	    	}else{
	    		params.put("STATE", STATE);
	    	}
	    	
	    }	    
	    //维护和审核     列表页面过滤的条件为：系统权限  and 帐套ID=’当前登录帐套ID’  ;点击查询的时候，过滤条件不变
	    if(!"decide".equals(decide)&&!"".equals(module)){
    		params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    }
	    //鉴定   页面过滤条件为:系统权限 and 帐套信息ID=’登录帐套ID’ and STATE=’审核通过’
	    //查询的过滤条件为: 系统权限 and 帐套信息ID=’登录帐套ID’ and STATE in（ ’审核通过’，‘已提交厂家’）
	    if("decide".equals(decide)&&"".equals(module)){
	    	moduleName = "退货单鉴定";
	    	PVG=COMMIT_TO_PVG;
	    	params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    	if(!"true".equals(search)){
	    		params.put("DECIDE", " STATE in ('"+BusinessConsts.PASS+"','退回')");
	    	}else if(StringUtil.isEmpty(STATE)){
	    		params.put("DECIDE", " STATE in ('"+BusinessConsts.PASS+"' , '已鉴定','退回')");
	    	}else{
	    		params.put("STATE",STATE);
	    	}
	    }
	    //权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    request.setAttribute("moduleName", moduleName);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prdreturnService.pageQuery(params, pageNo);
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_PVG)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_RETURN_ID =ParamUtil.get(request, "PRD_RETURN_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_ID))
        {
        	 List <PrdreturnModelChld> result = prdreturnService.childQuery(PRD_RETURN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        
        
        if("decide".equals(ParamUtil.get(request, "flag"))){
        	return mapping.findForward("childDecidelist");
        }
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
			Map<String,String> entry = prdreturnService.load(PRD_RETURN_ID);
			request.setAttribute("rst", entry);
		}else{
			Map<String,String> entry = new HashMap<String,String>();
		
			//收货方默认值(自己)
			entry.put("RECV_CHANN_ID", userBean.getCHANN_ID());
			entry.put("RECV_CHANN_NO", userBean.getCHANN_NO());
			entry.put("RECV_CHANN_NAME", userBean.getCHANN_NAME());

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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_ID))
        {
        	 List <PrdreturnModelChld> result = prdreturnService.childQuery(PRD_RETURN_ID);
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRD_RETURN_DTL_IDs = request.getParameter("PRD_RETURN_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_RETURN_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_RETURN_DTL_IDS",PRD_RETURN_DTL_IDs);
          List <PrdreturnModelChld> list = prdreturnService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        if("decide".equals(ParamUtil.get(request, "flag"))){
        	return mapping.findForward("childDecideedit");
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
            PrdreturnModel model = new Gson().fromJson(parentJsonData, new TypeToken <PrdreturnModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <PrdreturnModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnModelChld>>() {
                }.getType());
            }
            prdreturnService.txEdit(PRD_RETURN_ID, model, chldModelList, userBean);
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdreturnModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnModelChld>>() {
                }.getType());
                prdreturnService.txChildEdit(PRD_RETURN_ID, modelList,userBean);
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
			prdreturnService.txDelete(params);
			prdreturnService.clearCaches(params);
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
            prdreturnService.txBatch4DeleteChild(PRD_RETURN_DTL_IDs);
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_PVG)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRD_RETURN_ID = ParamUtil.get(request, "PRD_RETURN_ID");
		if(!StringUtil.isEmpty(PRD_RETURN_ID)){
			Map<String,String> entry = prdreturnService.load(PRD_RETURN_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	 public void upState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 	PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        UserBean userBean = ParamUtil.getUserBean(request);
	        try {
	        	 String PRD_RETURN_ID = ParamUtil.utf8Decoder(request, "PRD_RETURN_ID");
	             Object obj = prdreturnService.getStoreInDiff(PRD_RETURN_ID);
	             if(obj!=null){
	            	 Map<String,String> map=new HashMap<String,String>();
	            	 map.put("PRD_RETURN_ID", PRD_RETURN_ID);
	            	 map.put("AUDIT_ID", userBean.getRYXXID());
	            	 map.put("AUDIT_NAME", userBean.getXM());
	            	 map.put("AUDIT_TIME", "sysdate");
	            	 map.put("STATE", "已鉴定");
	            	 prdreturnService.txUpdateById(map);
	             }
	             jsonResult = jsonResult(true, "");
	        }catch (Exception e) {
	           e.printStackTrace();
	           jsonResult = jsonResult(false, "提交失败");
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)&&!QXUtil.checkMKQX(userBean, COMMIT_TO_FACTORY)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	 String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
             List<PrdreturnModelChld> list = prdreturnService.childQuery(PRD_RETURN_ID);
             boolean flag=true;
             if (list.size() == 0) {
             	jsonResult = jsonResult(false, "您还有没有核价的明细!");
             	flag=false;
             }
             if(flag){
            	 for (int i=0;i<list.size();i++) {
            		 Map<String,String> model=(Map<String, String>) list.get(i);
     				if(null==model.get("PRICE_DECIDE")){
     					throw new ServiceException("请检查核价价格是否全部填写");
     				}
     			 }
            	 Map<String,String> map=new HashMap<String,String>();
            	 map.put("PRD_RETURN_ID", PRD_RETURN_ID);
            	 map.put("AUDIT_ID", userBean.getRYXXID());
            	 map.put("AUDIT_NAME", userBean.getXM());
            	 map.put("AUDIT_TIME", "sysdate");
            	 map.put("STATE", "已鉴定");
            	 prdreturnService.txUpdateById(map);
            	 jsonResult = jsonResult(true, "");
             }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
           e.printStackTrace();
           jsonResult = jsonResult(false, "提交失败");
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
	    	pvgMap.put("COMMIT_TO_PVG",QXUtil.checkPvg(userBean, COMMIT_TO_PVG) );
	    	pvgMap.put("COMMIT_TO_EDIT",QXUtil.checkPvg(userBean, COMMIT_TO_EDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
			pvgMap.put("COMMIT_TO_FACTORY",QXUtil.checkPvg(userBean, COMMIT_TO_FACTORY));
	    	return  pvgMap;
	   }
	 /**
	     * * 退货单维护提交时，校验是否有明细.
	     * 
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void toCommitVindicate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String error="";
	        try {
	        	 String PRD_RETURN_ID = request.getParameter("PRD_RETURN_ID");
	             List<PrdreturnModelChld> list = prdreturnService.childQuery(PRD_RETURN_ID);
	             if (list.size() == 0) {
	            	error="1";
	             	throw new Exception();
	             }
	             //-- 0156108--Start--刘曰刚--2014-06-17//
	             //提交时增加预计退货日期校验	
	             Map<String,String> map=prdreturnService.load(PRD_RETURN_ID);
	             if(StringUtil.isEmpty(map.get("EXPECT_RETURNDATE"))){
	            	 error="2";
		             throw new Exception();
	             }
	             jsonResult = jsonResult(true, "");
	        }catch (Exception e) {
	        	if("1".equals(error)){
	        		jsonResult = jsonResult(false, "没有明细信息，不能提交!");
	        	}else if("2".equals(error)){
	        		jsonResult = jsonResult(false, "没有预计退货日期，不能提交!");
	        	}else{
	        		jsonResult = jsonResult(false, "提交失败");
	        	}
	           e.printStackTrace();
	        }
	        //-- 0156108--End--刘曰刚--2014-06-17//
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
}
