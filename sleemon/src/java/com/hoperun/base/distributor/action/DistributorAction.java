package com.hoperun.base.distributor.action;

/**  
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.0
 * @author gu_hongxiu
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.distributor.model.DistributorModel;
import com.hoperun.base.distributor.service.DistributorService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class DistributorAction extends BaseAction {
	/** The chann Service*/
	private DistributorService distributorService;
	private Logger logger = Logger.getLogger(DistributorAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="XT0013401";
    private static String PVG_EDIT="XT0013402";
    private static String PVG_DELETE="XT0013403";
    //启用,停用
    private static String PVG_START_STOP="XT0013404";
    
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
   
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
	/**
	 * @return the channService
	 */
	public DistributorService getDistributorService() {
		return distributorService;
	}
	/**
	 * @param distributorService the channService to set
	 */
	public void setDistributorService(DistributorService distributorService) {
		this.distributorService = distributorService;
	}
	/**
	 * 渠道信息框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}
	
	/**
     * 查询结果列表.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	    	
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NO", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NAME", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_TYPE", params);
    	ParamUtil.putStr2Map(request, "AREA_NAME_P", params);
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
    	ParamUtil.putStr2Map(request, "PROV", params);
    	ParamUtil.putStr2Map(request, "CITY", params);
    	ParamUtil.putStr2Map(request, "COUNTY", params);
    	ParamUtil.putStr2Map(request, "PERSON_CON", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = distributorService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String DISTRIBUTOR_ID = ParamUtil.get(request, "DISTRIBUTOR_ID");
        Map<String,String> entry = distributorService.load(DISTRIBUTOR_ID);
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
        String DISTRIBUTOR_ID = ParamUtil.get(request, "DISTRIBUTOR_ID");
        if (StringUtils.isNotEmpty(DISTRIBUTOR_ID)) {
            Map<String,String> entry = distributorService.load(DISTRIBUTOR_ID);
            request.setAttribute("rst", entry);
        }
      //-- 0156117--Start--刘曰刚--2014-06-16//
        //获取当前登录人所属机构传到页面作为区域经理筛选条件
        request.setAttribute("JGXXID", userBean.getJGXXID());
      //-- 0156117--End--刘曰刚--2014-06-16//
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
        String DISTRIBUTOR_ID = ParamUtil.get(request, "DISTRIBUTOR_ID");
        DistributorModel model = null;
        
        try {
        	if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <DistributorModel>() {
                }.getType());
                
                if(distributorService.loadSTATE(model.getCHANN_ID()))
            	{
        			DISTRIBUTOR_ID = distributorService.txEdit(DISTRIBUTOR_ID, model, userBean);
        			jsonResult = jsonResult(true, DISTRIBUTOR_ID);
            	}else{
            		jsonResult = jsonResult(false, "不可选择停用的渠道作为所属加盟商渠道");
            	}
            }
        }catch(ServiceException x){
        	jsonResult = jsonResult(false, x.getMessage());
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }finally{
        	if (null != writer) {
                writer.write(jsonResult);
                writer.close();
            }
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
        String DISTRIBUTOR_ID = ParamUtil.get(request, "DISTRIBUTOR_ID");
        if (StringUtils.isNotEmpty(DISTRIBUTOR_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("DISTRIBUTOR_ID", DISTRIBUTOR_ID);
               
                params.put("UPDATOR", userBean.getRYXXID());
    		    params.put("UPD_NAME", userBean.getXM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
                distributorService.txDelete(params);
                distributorService.clearCaches(DISTRIBUTOR_ID);
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
     * 按钮修改状态为停用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateTy(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String DISTRIBUTOR_ID = request.getParameter("DISTRIBUTOR_ID");
            
            Map <String, String> params = new HashMap <String, String>();
            String changedState = "";
            params.put("DISTRIBUTOR_ID", DISTRIBUTOR_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            changedState = BusinessConsts.JC_STATE_DISENABLE;
            params.put("STATE", changedState);
            distributorService.txStopById(params);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }finally{
        	if (null != writer) {
                writer.write(jsonResult);
                writer.close();
            }
        }
        
    }
    
    /**
     * 按钮修改状态为启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateQy(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String DISTRIBUTOR_ID = request.getParameter("DISTRIBUTOR_ID");
            
            Map <String, String> params = new HashMap <String, String>();
            params.put("DISTRIBUTOR_ID", DISTRIBUTOR_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());            
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
            distributorService.txStartById(params);
        } catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "状态修改失败");
        }finally{
        	if (null != writer) {
                writer.write(jsonResult);
                writer.close();
            }
        }
    }
    
    
    public void toExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NO", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NAME", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_TYPE", params);
    	ParamUtil.putStr2Map(request, "AREA_NAME_P", params);
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
    	ParamUtil.putStr2Map(request, "PROV", params);
    	ParamUtil.putStr2Map(request, "CITY", params);
    	ParamUtil.putStr2Map(request, "COUNTY", params);
    	ParamUtil.putStr2Map(request, "PERSON_CON", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		List list = distributorService.qryExp(params);
		
        //excel数据上列显示的列明
		String tmpContent = "DISTRIBUTOR_ID,DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE,TEL,CHANN_ID,CHANN_NO,CHANN_NAME," +
				"AREA_ID,AREA_NO,AREA_NAME,ZONE_ID,NATION,PROV,CITY,COUNTY,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL," +
				"ADDRESS,TAX,EMAIL,PERSON_CON,MOBILE,SALE_STORE_NAME,SALE_STORE_LOCAL,BUSS_BRAND,BUSS_CLASS,COOPER_DATE," +
				"STATE,CREATOR,CRE_NAME,CRE_TIME,UPDATOR,UPD_NAME,UPD_TIME,DEPT_ID,DEPT_NAME,ORG_ID,ORG_NAME,LEDGER_ID,LEDGER_NAME";
		
		String tmpContentCn = "分销商ID,分销商编号,分销商名称,分销商类型,公司电话,渠道ID,渠道编号,渠道名称," +
				"区域ID,区域编号,区域名称,行政区划ID,国家,省份,城市,区县,区域经理ID,区域经理名称,区域经理电话," +
				"详细地址,传真,电子邮件,联系人,联系人电话,商场名称,商场位置,经营品牌,主营分类,合作日期," +
				"状态,制单人ID,制单人名称,制单时间,更新人ID,更新人名称,更新时间,制单部门ID,制单部门名称,制单机构ID,制单机构名称,帐套ID,帐套名称";
		
        try {
        	ExcelUtil.doExport(response, list, "分销渠道信息", tmpContent, tmpContentCn);
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
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }			
		
}
