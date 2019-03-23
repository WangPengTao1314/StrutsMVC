/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderAction
*/
package com.hoperun.erp.sale.goodsorder.action;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModel;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.erp.sale.goodsorder.service.GoodsorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func 总部 - 订货订单处理
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-08-30 15:55:09
 */
public class GoodsorderAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(GoodsorderAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010601";
    private static String PVG_EDIT="BS0010602";
    private static String PVG_DELETE="BS0010603";
    //提交
    private static String PVG_TO_DISPOSED="BS0010603";
    
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0010603";
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
	
	//交易关闭
	private static String PVG_CLOSE="";
	//回退
	private static String PVG_RETURN = "BS0010604";
	
	//取消 订单明细
	private static String PVG_CAL_MX = "BS0010605";
	
    /**审批 end**/
    /** 业务service*/
	private GoodsorderService goodsorderService;
    /**
	 * Sets the Goodsorder service.
	 * 
	 * @param GoodsorderService the new Goodsorder service
	 */
	public void setGoodsorderService(GoodsorderService goodsorderService) {
		this.goodsorderService = goodsorderService;
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
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
	    
	    

	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("XTYHID",userBean.getXTYHID());
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE ='" + BusinessConsts.UNDONE+"' ");//菜单页 点击显示 “未处理“
		}else if(StringUtil.isEmpty(params.get("STATE"))){
			//点击查询  只查询 “未处理、已处理、总部退回”
			sb.append(" and u.STATE in('" + BusinessConsts.UNDONE+"','"+BusinessConsts.DONE+"','总部退回') ");
		}
		sb.append(" and NVL(u.IS_AREA_SER_ORDER,0) = 0 ");
		//权限级别和审批流的封装
	    params.put("QXJBCON",sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE", "ASC");
		 
		//渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = goodsorderService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("JGXXID", userBean.getJGXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
        Map<String, String> params = new HashMap<String, String>();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        // 字段排序
		ParamUtil.setOrderField(request, params, "u.ROW_NO", "ASC");
		
        if(!StringUtil.isEmpty(GOODS_ORDER_ID)) {
        	 List <GoodsorderModelChld> result = goodsorderService.childQuery(params);
        	 if(null != result){
        		  request.setAttribute("resultSize", result.size());
        	 }
             request.setAttribute("rst", result);
        }
        request.setAttribute("IS_NO_ADVC_DATE_FLAG", Consts.IS_NO_ADVC_DATE_FLAG);
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        //设置跳转时需要的一些公用属性
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("editFrame");
    }
	
	/**
	 * * to 到处理页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDisposed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("todisposed");
	}
	
	/**
	 * 处理页面 上帧
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward parentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String GOODS_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
			Map<String,String> entry = goodsorderService.load(GOODS_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("JGXXID", userBean.getJGXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("parentlist");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
        if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
        	 List <GoodsorderModelChld> result = goodsorderService.childQuery(GOODS_ORDER_ID);
//        	 Map<String,String> factory = goodsorderService.queryDefaultFactory(GOODS_ORDER_ID);
//        	 request.setAttribute("factory", factory);
        	 
        	 if(null != result){
       		  request.setAttribute("resultSize", result.size());
       	     }
             request.setAttribute("rst", result);
             request.setAttribute("pvg",setPvg(userBean));
             request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
        String GOODS_ORDER_DTL_IDs = ParamUtil.get(request, "GOODS_ORDER_DTL_IDS"); 
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(GOODS_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("GOODS_ORDER_DTL_IDS",GOODS_ORDER_DTL_IDs);
          List <GoodsorderModelChld> list = goodsorderService.loadChilds(params);
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
            String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
            String parentJsonData = ParamUtil.utf8Decoder(request,	"parentJsonData");
            GoodsorderModel model = new Gson().fromJson(parentJsonData, new TypeToken <GoodsorderModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <GoodsorderModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <GoodsorderModelChld>>() {
                }.getType());
            }
            goodsorderService.txEdit(GOODS_ORDER_ID, model, chldModelList, userBean);
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
    public void childEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String GOODS_ORDER_ID =  ParamUtil.get(request, "GOODS_ORDER_ID"); 
            String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE"); 
            
            String jsonDate = ParamUtil.get(request,"childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <GoodsorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <GoodsorderModelChld>>() {
                }.getType());
                goodsorderService.txChildEdit(GOODS_ORDER_ID,BILL_TYPE, modelList,userBean);
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
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			goodsorderService.txDelete(params);
			goodsorderService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String GOODS_ORDER_DTL_IDs = ParamUtil.get(request, "GOODS_ORDER_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            goodsorderService.txBatch4DeleteChild(GOODS_ORDER_DTL_IDs);
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
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
			Map<String,String> entry = goodsorderService.load(GOODS_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
             
            String jsonDate =  ParamUtil.get(request,"childData");
            String GOODS_ORDER_ID = ParamUtil.get(request,	"selRowId");
            //是否走新的发运流程
            if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
//	            List<GoodsorderModelChld> childList = goodsorderService.childModelQuery(GOODS_ORDER_ID);
//	            if(null != childList && !childList.isEmpty()){
//	            	for(GoodsorderModelChld child : childList){
//	            		if(StringUtil.isEmpty(child.getADVC_SEND_DATE())){
//	            			errorId = "1";
//	            			throw new ServiceException("明细的'预计交货日期'不能为空");
//	            		}
//	            	}
//	            }
            }
            GoodsorderModel model = goodsorderService.loadById(GOODS_ORDER_ID);
            if(!"未处理".equals(model.getSTATE())){
            	throw new ServiceException("该单据已经处理，请刷新页面");
            }
            if (!StringUtil.isEmpty(jsonDate)) {
                List <GoodsorderModelChld> childModelList = new Gson().fromJson(jsonDate, 
                		new TypeToken <List <GoodsorderModelChld>>() {}.getType());
                
                //不合并
                Map<String,Object> returnMap = goodsorderService.txAddSalOrder(model, childModelList, userBean);
               //因为需要合并订货订单明细，改用此方法
//                String MERGER_REMARKS  = ParamUtil.get(request,"MERGER_REMARKS");
//                Map<String,Object> returnMap = this.goodsorderService.txMergerBill("'"+GOODS_ORDER_ID+"'",MERGER_REMARKS,model,childModelList,userBean);
                jsonResult = jsonResult(true, "提交成功");
                
              //合并成功后给工艺工程部发消息
               sendMessage(returnMap,userBean);

            }
            
        }catch(ServiceException se){
            jsonResult = jsonResult(false, se.getMessage());
        }catch (Exception e) {
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
    
    
 
    
    
    public ActionForward toSpclDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
//		String GOODS_ORDER_DTL_ID = ParamUtil.get(request, "GOODS_ORDER_DTL_ID");
//		if(!StringUtil.isEmpty(GOODS_ORDER_DTL_ID)){
//			Map<String,String> entry = goodsorderService.load(GOODS_ORDER_DTL_ID);
//			request.setAttribute("rst", entry);
//		}
		return mapping.findForward("tospcldetail");
	}
    
    
    
    
    /**
     * * 到回退原因页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toReason(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
     
        return mapping.findForward("toreason");
    }
    
    
    /**
     * * 确认回退
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void returnBack(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_RETURN))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try {
        	String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
        	String reason = ParamUtil.get(request, "msg");
        	
        	//更新订货订单的状态为“退回”
        	this.goodsorderService.txUpdateAdvcOrder(GOODS_ORDER_ID,reason,userBean);
        	jsonResult = jsonResult(true,"退回成功");
            
        } catch (Exception e) {
        	jsonResult = jsonResult(true,"退回失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**
     * * 取消订单明细
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void orderClose(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_CAL_MX))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try {
        	String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
        	String mxIds = ParamUtil.get(request, "mxIds");
        	String remark = ParamUtil.get(request, "remark");
        	boolean isAll = ParamUtil.getBoolean(request, "isAll");
        	
        	//更新当前订货订单的状态为“交易关闭”
            this.goodsorderService.updateOrderClose(GOODS_ORDER_ID,mxIds,remark,isAll,userBean);
            jsonResult = jsonResult(true,"操作成功");
            
        } catch (Exception e) {
        	jsonResult = jsonResult(true,"操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
	 /**
     * * 生命周期
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward traceList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_ID =ParamUtil.get(request, "GOODS_ORDER_ID");
        if(!StringUtil.isEmpty(GOODS_ORDER_ID))
        {
        	 List <GoodsorderhdModelTrace> result = goodsorderService.traceQuery(GOODS_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("traceList");
    }
    
    
	public ActionForward toMergeFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String jsonData = ParamUtil.utf8Decoder(request, "jsonData");
        GoodsorderModel model = new Gson().fromJson(jsonData, new TypeToken <GoodsorderModel>() {}.getType());
        System.out.println(model.getORDER_CHANN_NAME());
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("rst", model);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("toMergeFrame");
	}
	
	
	/**
	 * 合并提交 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMergeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
		
		ParamUtil.putStr2Map(request, "ORDER_CHANN_ID", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_ID", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_ID", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		ParamUtil.putStr2Map(request, "AREA_ID", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_ID", params);
        
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	  
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("XTYHID",userBean.getXTYHID());
        String selRowId  = ParamUtil.get(request,"selRowId");
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		if(StringUtil.isEmpty(search)){
			 String jsonData = ParamUtil.utf8Decoder(request, "jsonData");
		     GoodsorderModel model = new Gson().fromJson(jsonData, new TypeToken <GoodsorderModel>() {}.getType());
		        
			params.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID()); 
	        params.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO()); 
	        params.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME()); 
	        params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID()); 
	        params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO()); 
	        params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME()); 
	        params.put("BILL_TYPE", model.getBILL_TYPE()); 
	        params.put("AREA_ID", model.getAREA_ID()); 
	        params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID()); 
		}
		
		
		
		params.put("GOODS_ORDER_ID", selRowId); 
		params.put("STATE", BusinessConsts.UNDONE);//菜单页 点击显示 “未处理“
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		 
		//权限级别和审批流的封装
	    params.put("QXJBCON",sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "ASC");
		 
		//渠道分管sql   
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
	 
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = goodsorderService.pageMergeQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		return mapping.findForward("toMergeList");
	}
	
	
    
    /**
     * 合并提交
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void mergerBill(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try {
        	String MERGER_GOODS_ORDER_IDS = ParamUtil.get(request, "MERGER_GOODS_ORDER_IDS");
            String childJson = ParamUtil.get(request, "childJson");
        	String selRowId  = ParamUtil.get(request,"selRowId");
        	String MERGER_REMARKS  = ParamUtil.get(request,"MERGER_REMARKS");
        	
        	GoodsorderModel model = this.goodsorderService.loadById(selRowId);
        	List <GoodsorderModelChld> childModelList = new Gson().fromJson(childJson, 
        			 new TypeToken <List <GoodsorderModelChld>>() {}.getType());
        	 
        	Map<String,Object> returnMap = this.goodsorderService.txMergerBill(MERGER_GOODS_ORDER_IDS,MERGER_REMARKS,model,childModelList,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        	
        	//合并成功后给工艺工程部发消息。 
        	sendMessage(returnMap,userBean);
            
        }catch(ServiceException e){
        	logger.error(e.getMessage());
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    //  合并成功后给工艺工程部发消息。异常的时候抛出 ServiceException
    public void sendMessage(Map<String,Object> returnMap,UserBean userBean){
    	try{
    		if(null != returnMap ){
        		String TECH_ORDER_NO = StringUtil.nullToSring(returnMap.get("TECH_ORDER_NO"));
        		if(!StringUtil.isEmpty(TECH_ORDER_NO)){
        			goodsorderService.txSendMessage(TECH_ORDER_NO, userBean);
        		}
        		
        	}
    	}catch(Exception e){
        	logger.error(e.getMessage());
        }
    	
    }
    
    /**
     * 合并提交页面 明细list
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward childListMore(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String GOODS_ORDER_IDS = ParamUtil.get(request, "GOODS_ORDER_IDS");
        String mergeFlage = ParamUtil.get(request, "mergeFlage");
        
        if(!StringUtil.isEmpty(GOODS_ORDER_IDS)) {
        	 List <GoodsorderModelChld> result = goodsorderService.childQueryMore(GOODS_ORDER_IDS);
        	 if(null != result){
        		  request.setAttribute("resultSize", result.size());
        	 }
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("mergeFlage", mergeFlage);
        return mapping.findForward("childlist");
    }
    
    
    /**
	 * * 查看库存
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void queryStore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_TO_DISPOSED)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String PRD_NO = ParamUtil.get(request, "PRD_NO");
            String SPCL_TECH_ID = ParamUtil.get(request, "SPCL_TECH_ID");
            String ORDER_CHANN_NO = ParamUtil.get(request, "ORDER_CHANN_NO");
            String SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");
            
            Map<String,String> params = new HashMap<String,String>();
            params.put("PRD_NO", PRD_NO);
            params.put("SPCL_TECH_ID", SPCL_TECH_ID);
            params.put("ORDER_CHANN_NO", ORDER_CHANN_NO);
            params.put("SHIP_POINT_ID", SHIP_POINT_ID);
            
            Map<String,Object> result = goodsorderService.queryStore(params, userBean);
            jsonResult = new Gson().toJson(new Result(true, result, ""));
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
 
	/**
	 * *加载主表信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public void loadParentModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	try {
    		
    		String GOODS_ORDER_ID = ParamUtil.get(request, "selRowId");
    		Map<String,String> entry = new HashMap<String,String>();
    		if(!StringUtil.isEmpty(GOODS_ORDER_ID)){
    			entry = goodsorderService.load(GOODS_ORDER_ID);
    			jsonResult = new Gson().toJson(new Result(true, entry, ""));

    		} 
    		
    	}catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
            e.printStackTrace();
        }
		
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 保存备注信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	 public void modifyRemark(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String GOODS_ORDER_ID =  ParamUtil.get(request, "GOODS_ORDER_ID"); 
	            String REMARK =  ParamUtil.get(request, "REMARK"); 
	            goodsorderService.modifyRemark(GOODS_ORDER_ID, REMARK, userBean);
	            jsonResult = jsonResult(true, "");
	        }catch (Exception e) {
	            jsonResult = jsonResult(false, "操作失败");
	            e.printStackTrace();
	        }
	        
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    	
	 }   	
	 
	 
	 
	 //导出
		@SuppressWarnings("unchecked")
		public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();
			ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
		    ParamUtil.putStr2Map(request, "AREA_NO", params);
		    ParamUtil.putStr2Map(request, "AREA_NAME", params);
		    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		    ParamUtil.putStr2Map(request, "PRD_NO", params);
		    ParamUtil.putStr2Map(request, "PRD_NAME", params);
		    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
			String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			StringBuffer strQx = new StringBuffer("");
			strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			params.put("XTYHID",userBean.getXTYHID());
			//权限级别和审批流的封装
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.getStrQX("u",strQx.toString()));
			sb.append(" and NVL(u.IS_AREA_SER_ORDER,0) = 0 ");
			params.put("QXJBCON", sb.toString());
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			//渠道分管sql  by zzb 2014-6-17
			String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			params.put("CHANNS_CHARG", CHANNS_CHARG);
			
	        List list=goodsorderService.downQuery(params);
	        //excel数据上列显示的列明
	        String tmpContentCn="订货订单编号,订货日期,订货总额,生产基地名称,订单类型,是否使用返利,区域编号,订货区域,订货方编号,订货方名称,发货方编号,发货方名称,收货方编号,收货方名称,联系人,联系方式,状态,收货地址,主表备注,货品编号,货品名称,规格型号,品牌,标准单位,是否非标,特殊规格说明,是否抵库,折后单价,订货数量,金额,体积,状态,备注";
	        String tmpContent="GOODS_ORDER_NO,ORDER_DATE,TOTAL_AMOUNT,SHIP_POINT_NAME,BILL_TYPE,IS_USE_REBATE,AREA_NO,AREA_NAME,ORDER_CHANN_NO,ORDER_CHANN_NAME,SEND_CHANN_NO,SEND_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,PERSON_CON,TEL,STATE,RECV_ADDR,PRIMARYREMARK,PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,IS_NO_STAND_FLAG,SPCL_SPEC_REMARK,IS_BACKUP_FLAG,DECT_PRICE,ORDER_NUM,ORDER_AMOUNT,VOLUME,CANCEL_FLAG,REMARK";
	        try {
				ExcelUtil.doExport(response, list, "订货订单", tmpContent, tmpContentCn);
			} catch (Exception e) {
				e.printStackTrace();
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
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
	    	pvgMap.put("PVG_RETURN",QXUtil.checkPvg(userBean, PVG_RETURN));
	    	pvgMap.put("PVG_CAL_MX",QXUtil.checkPvg(userBean, PVG_CAL_MX));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
			
			pvgMap.put("PVG_TO_DISPOSED",QXUtil.checkPvg(userBean, PVG_TO_DISPOSED) );
			
	    	return  pvgMap;
	   }
}