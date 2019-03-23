package com.hoperun.erp.oamg.storeclose.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.erp.oamg.storeclose.model.StorecloseModel;
import com.hoperun.erp.oamg.storeclose.service.StorecloseService;
import com.hoperun.sys.model.UserBean;

/**
 * 专卖店撤店及终止申请单维护
 * @author gu_hongxiu
 *
 */
public class StorecloseAction extends BaseAction {

	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0030401";
    private static String PVG_EDIT="BS0030402";
    private static String PVG_DELETE="BS0030403";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0030404";
    private static String PVG_TRACE="BS0030405";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0030501";
    private static String PVG_AUDIT_AUDIT="BS0030502";
    private static String PVG_TRACE_AUDIT="BS0030503";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_SPCL_STORE_CC_REQ";
    private static String AUD_TAB_KEY="SPCL_STORE_CC_REQ_ID";    	
    private static String AUD_BUSS_TYPE="DRP_SPCL_STORE_CC_REQ_AUDIT";
    private static String AUD_BUSS_STATE="STATE";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private StorecloseService storecloseService;

	public StorecloseService getStorecloseService() {
		return storecloseService;
	}

	public void setStorecloseService(StorecloseService storecloseService) {
		this.storecloseService = storecloseService;
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
		Map<String,String> paramsT = new HashMap<String,String>();
		
		ParamUtil.putStr2Map(request, "PCL_STORE_CC_REQ_NO", params);
		ParamUtil.putStr2Map(request, "CHANN_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
	    ParamUtil.putStr2Map(request, "BUSS_SCOPE", params);
	    ParamUtil.putStr2Map(request, "BUSS_SCOPE", paramsT);
	    ParamUtil.putStr2Map(request, "STATE", params);	    
	    ParamUtil.putStr2Map(request, "SPCL_STORE_CANCEL_DATE_BEG", params);
	    ParamUtil.putStr2Map(request, "SPCL_STORE_CANCEL_DATE_END", params);	
	  	    
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		String BUSS_SCOPE = ParamUtil.get(request, "BUSS_SCOPE").toString();
		if(!BUSS_SCOPE.equals("")){
			BUSS_SCOPE =  storecloseService.queryName(BUSS_SCOPE);
			params.put("BUSS_SCOPE", BUSS_SCOPE);
		}
		
		
		
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    
		//设置帐套id
		//params.put("",LEDGER_ID userBean.getLoginZTXXID());
		//设置系统用户id
		params.put("XTYH_ID", userBean.getXTYHID());
		
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);       
       
		//字段排序
        ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
        
        //渠道分管sql  by czj 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		if("sh".equals(module)){
			//params.put("AUDIT_ID", userBean.getXTYHID());
		}
        
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storecloseService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("paramsT", paramsT);        
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		request.setAttribute("search", search);
		//查询所有品牌名称
        List<String> brandList=storecloseService.getBrand();
        request.setAttribute("brandList", brandList);
        request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
		
		return mapping.findForward("list");
	}
	
	/**
     * 查看渠道详细信息.
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
        String SPCL_STORE_CC_REQ_ID = ParamUtil.get(request, "SPCL_STORE_CC_REQ_ID");
        if(StringUtils.isNotEmpty(SPCL_STORE_CC_REQ_ID)){
        	Map<String,String> entry = storecloseService.load(SPCL_STORE_CC_REQ_ID);
            entry.put("CRE_DATE", DateUtil.format(entry.get("CRE_TIME"), "yyyy-MM-DD HH24:MI:SS", "yyyy-MM-DD"));
            request.setAttribute("rst", entry);
            
            List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(SPCL_STORE_CC_REQ_ID);
			request.setAttribute("flowInfoList", flowInfoList);
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
        String SPCL_STORE_CC_REQ_ID = ParamUtil.get(request, "SPCL_STORE_CC_REQ_ID");
        Map<String,String> entry = new HashMap<String,String>();
        
        if (StringUtils.isNotEmpty(SPCL_STORE_CC_REQ_ID)) {
        	entry = storecloseService.loadT(SPCL_STORE_CC_REQ_ID); 
        	entry.put("CRE_DATE", DateUtil.format(entry.get("CRE_TIME"), "yyyy-MM-DD HH24:MI:SS", "yyyy-MM-DD"));
        	
        	String picPath = storecloseService.loadAtt(SPCL_STORE_CC_REQ_ID);
            if(StringUtils.isNotEmpty(picPath)){
            	entry.put("PIC_PATH",picPath);//LogicUtil.getPicPath(picPath)
            }
        }else{        	
        	
        	entry.put("CRE_DATE", DateUtil.formatDateToStr(TimeComm.getDate()));        	
        }
        
        entry.put("LEDGER_ID", userBean.getLoginZTXXID());      
        entry.put("XTYH_ID", userBean.getXTYHID());
        request.setAttribute("rst", entry);
        request.setAttribute("QUERY_CHANN_ID", userBean.getCHANNS_CHARG());
      
        //查询所有品牌名称
       /* List<String> brandList=storecloseService.getBrand();
        request.setAttribute("brandList", brandList);*/
        
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
        StorecloseModel model = new StorecloseModel();
        String SPCL_STORE_CC_REQ_ID = ParamUtil.get(request, "SPCL_STORE_CC_REQ_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <StorecloseModel>() {
            }.getType());
        }
        
        SPCL_STORE_CC_REQ_ID = storecloseService.txEdit(SPCL_STORE_CC_REQ_ID, model, userBean);
		jsonResult = jsonResult(true, SPCL_STORE_CC_REQ_ID);       
       
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
        String SPCL_STORE_CC_REQ_ID = ParamUtil.get(request, "SPCL_STORE_CC_REQ_ID");
        if (StringUtils.isNotEmpty(SPCL_STORE_CC_REQ_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("SPCL_STORE_CC_REQ_ID", SPCL_STORE_CC_REQ_ID);
                
                params.put("UPDATOR", userBean.getRYXXID());
    		    params.put("UPD_NAME", userBean.getXM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    		    storecloseService.txDelete(params);
    		    storecloseService.clearCaches(SPCL_STORE_CC_REQ_ID);
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
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String SPCL_STORE_CC_REQ_ID    = ParamUtil.get(request, "SPCL_STORE_CC_REQ_ID");
		 int count = storecloseService.queryTerminalState(SPCL_STORE_CC_REQ_ID);
		 if(count !=0) {
			 storecloseService.upTerminal(SPCL_STORE_CC_REQ_ID,userBean);
		 }
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	}
    
    // 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PCL_STORE_CC_REQ_NO", params);
		ParamUtil.putStr2Map(request, "CHANN_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
		ParamUtil.putStr2Map(request, "BUSS_SCOPE", params);
		ParamUtil.putStr2Map(request, "SPCL_STORE_CANCEL_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "SPCL_STORE_CANCEL_DATE_END", params);
		ParamUtil.putStr2Map(request, "STATE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//权限级别和审批流的封装
        String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    //权限级别和审批流的封装
		if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
			   qx.append(" and u.STATE in('未提交','撤销','否决') ");
			}
		}
		if(module.equals("sh")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}else{
					qx.append(" and u.STATE in('提交','撤销','否决','审核通过') ");
				}
			}else{
				qx.append(" and STATE='提交' ");
			}
		}
	    params.put("QXJBCON",qx.toString());
	    
	    IListPage page = null;
	    request.setAttribute("module",module);
	     
	    //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    params.put("CHANNS_CHARG", CHANNS_CHARG);
	    //字段排序
  		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	 
		List list = storecloseService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="专卖店撤店申请单号,填表日期,终端编号,终端名称,终端类型,开店日期,渠道编号,渠道名称," +
				"渠道联系人,渠道联系电话,所属战区编号,所属战区名称,区域经理,区域经理电话,联系人,联系电话(座机),手机,传真,卖场名称,卖场位置类别,行政区域,经营品牌," +
				"负责区域,图纸面积(平米),专卖店撤店日期,撤店类型,原门店报销标准(元/平米),已报次数,已报总金额,状态,详细地址,撤店(终止)理由";
		String tmpContent = "PCL_STORE_CC_REQ_NO,CRE_TIME,TERM_NO,TERM_NAME,TERM_TYPE,BEG_SBUSS_DATE,CHANN_NO,CHANN_NAME," +
				"CHANN_PERSON_CON,CHANN_TEL,AREA_NO,AREA_NAME,AREA_MANAGE_NAME,AREA_MANAGE_TEL,PERSON_CON,TEL,MOBILE,TAX,SALE_STORE_NAME,LOCAL_TYPE,ZONE_ADDR,BUSS_SCOPE," +
				"CHARGE_AREA,BUSS_AREA,SPCL_STORE_CANCEL_DATE,STORE_CANCEL_TYPE,REIT_AMOUNT_PS,REITED_NUM,REITED_AMOUNT,STATE,ADDRESS,REQ_RSON";
		String colType= "string,string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "专卖店撤店及终止申请单维护", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
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
