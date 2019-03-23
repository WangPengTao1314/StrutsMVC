/**
 * prjName:喜临门营销平台
 * ucName:促销品发放
 * fileName:PrmtexitAction
*/
package com.hoperun.erp.sale.prmtexit.action;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModel;
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModelChld;
import com.hoperun.erp.sale.prmtexit.service.PrmtexitService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-19 16:54:28
 */
public class PrmtexitAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PrmtexitAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0020501";
    private static String PVG_EDIT="BS0020502";
    private static String PVG_DELETE="BS0020503";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0020504";
    private static String PVG_TRACE="BS0020504";
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
	private PrmtexitService prmtexitService;
    /**
	 * Sets the Prmtexit service.
	 * 
	 * @param PrmtexitService the new Prmtexit service
	 */
	public void setPrmtexitService(PrmtexitService prmtexitService) {
		this.prmtexitService = prmtexitService;
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
		String flag = ParamUtil.get(request, "flag");
		request.setAttribute("flag",flag);
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
	    ParamUtil.putStr2Map(request, "PRMT_GOODS_EXTD_NO", params);
	    ParamUtil.putStr2Map(request, "PRMT_PLAN_NO", params);
	    ParamUtil.putStr2Map(request, "PRMT_PLAN_NAME", params);
	    
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    
	    ParamUtil.putStr2Map(request, "COUNT_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "COUNT_DATE_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("LEDGER_ID",userBean.getLoginZTXXID());
        
        String search = ParamUtil.get(request,"search");
        
        String frameTolist = ParamUtil.get(request,"frameTolist");
        if(!StringUtil.isEmpty(frameTolist)){
        	params.put("STATE","未提交");
        }
        
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prmtexitService.pageQuery(params, pageNo);
		params.put("STATE","");
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
        String PRMT_GOODS_EXTD_ID =ParamUtil.get(request, "PRMT_GOODS_EXTD_ID");
        if(!StringUtil.isEmpty(PRMT_GOODS_EXTD_ID))
        {
        	 List <PrmtexitModelChld> result = prmtexitService.childQuery(PRMT_GOODS_EXTD_ID);
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
		String PRMT_GOODS_EXTD_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(PRMT_GOODS_EXTD_ID)){
			Map<String,String> entry = prmtexitService.load(PRMT_GOODS_EXTD_ID);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", false);
		}else{
			request.setAttribute("isNew", true);
		}
		request.setAttribute("userNum", userBean.getRYBH());
		request.setAttribute("userName", userBean.getXM());
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
        String PRMT_GOODS_EXTD_ID = ParamUtil.get(request, "PRMT_GOODS_EXTD_ID");
        if(!StringUtil.isEmpty(PRMT_GOODS_EXTD_ID))
        {
        	 List <PrmtexitModelChld> result = prmtexitService.childQuery(PRMT_GOODS_EXTD_ID);
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
        String PRMT_GOODS_EXTD_DTL_IDs = request.getParameter("PRMT_GOODS_EXTD_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRMT_GOODS_EXTD_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRMT_GOODS_EXTD_DTL_IDS",PRMT_GOODS_EXTD_DTL_IDs);
          List <PrmtexitModelChld> list = prmtexitService.loadChilds(params);
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
            String PRMT_GOODS_EXTD_ID = ParamUtil.get(request, "PRMT_GOODS_EXTD_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            PrmtexitModel model = new Gson().fromJson(parentJsonData, new TypeToken <PrmtexitModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <PrmtexitModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrmtexitModelChld>>() {
                }.getType());
            }
            prmtexitService.txEdit(PRMT_GOODS_EXTD_ID, model, chldModelList, userBean);
            
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
            String PRMT_GOODS_EXTD_ID = request.getParameter("PRMT_GOODS_EXTD_ID");
            String jsonDate = request.getParameter("childJsonData");
            Map<String,String> params = new HashMap<String,String>();
  		    params.put("PRMT_GOODS_EXTD_ID",PRMT_GOODS_EXTD_ID);
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrmtexitModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrmtexitModelChld>>() {
                }.getType());
                List<String> prdNum = new ArrayList<String>();
                boolean isFalg =false;
                for(PrmtexitModelChld  model : modelList){
                	prdNum.add(model.getPRD_ID());
                	params.put("PRD_ID",model.getPRD_ID());
                	boolean isExist	= prmtexitService.loadPrmtExitByPrd(params);//判断数据库是否已存在要录入的货品
                	if(isExist){
                		isFalg=isExist;
                	}
                }
                
                boolean isFalgE = isExist(prdNum);//判断页面输入是否有货品重复
                
                if(isFalg || isFalgE){
                	jsonResult = jsonResult(false, "货品编号重复或已存在!");
                }else{
                	prmtexitService.txChildEdit(PRMT_GOODS_EXTD_ID, modelList);
                }
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
			String PRMT_GOODS_EXTD_ID = ParamUtil.get(request, "PRMT_GOODS_EXTD_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_GOODS_EXTD_ID", PRMT_GOODS_EXTD_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			prmtexitService.txDelete(params);
			prmtexitService.clearCaches(params);
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
            String PRMT_GOODS_EXTD_DTL_IDs = request.getParameter("PRMT_GOODS_EXTD_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            prmtexitService.txBatch4DeleteChild(PRMT_GOODS_EXTD_DTL_IDs);
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
		String PRMT_GOODS_EXTD_ID = ParamUtil.get(request, "PRMT_GOODS_EXTD_ID");
		if(!StringUtil.isEmpty(PRMT_GOODS_EXTD_ID)){
			Map<String,String> entry = prmtexitService.load(PRMT_GOODS_EXTD_ID);
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
		Map <String, String> params = new HashMap <String, String>();
       
        try {
            String PRMT_GOODS_EXTD_ID = request.getParameter("PRMT_GOODS_EXTD_ID");
            params.put("PRMT_GOODS_EXTD_ID", PRMT_GOODS_EXTD_ID);
            params.put("STATE", BusinessConsts.COMMIT);
            List <PrmtexitModelChld> list = prmtexitService.childQuery(PRMT_GOODS_EXTD_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            prmtexitService.commit(params);
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
	 /**
	  * 
	  * 根据渠道编号关联预订单合计应付金额
	  * @param deptId
	  * @return
	  */
	 public void getCounSum(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
		 
		 String deptId = request.getParameter("deptId");
		 String sum = prmtexitService.countSum(deptId);
		 PrintWriter writer = getWriter(response);
		 String jsonResult = jsonResult();
		 if(null!=sum &&!"".equals(sum)){
			 jsonResult = jsonResult(true, sum);
		 }else{
			 jsonResult = jsonResult(false, "没有销售金额数据!");
		 }
		 if (null != writer) {
	    	 writer.write(jsonResult);
	         writer.close();
	     }  
		 
	 }
	 //判断新增子记录是否有货品编号重复
	 public boolean isExist(List<String> ary){
		 
		 for(int i=0;i<ary.size();i++){            
			 for(int j=0;j<ary.size();j++){
				 System.out.println(ary.get(i)+" == "+ary.get(j));
				 if(ary.get(i).endsWith(ary.get(j)) && i!=j){
					  
					 return true ;
				 }
			 }
		 }
		 
		 return false;
	 }
}