/**

 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannAction.java
 */
package com.hoperun.base.chann.action;

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
import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.base.chann.model.DeliveraddrModelChld;
import com.hoperun.base.chann.service.ChannService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ChannAction.
 * 
 * @module 系统管理
 * @func 渠道信息维护
 * @version 1.0
 * @author 刘曰刚
 */
public class ChannAction extends BaseAction {
	/** The chann Service*/
	private ChannService channService;
	private Logger logger = Logger.getLogger(ChannAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="XT0012001";
    private static String PVG_EDIT="XT0012002";
    private static String PVG_DELETE="XT0012003";
    //启用,停用
    private static String PVG_START_STOP="XT0012004";
    //批量维护区域经理
    private static String PVG_BATCH ="XT0012005";
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
	public ChannService getChannService() {
		return channService;
	}
	/**
	 * @param channService the channService to set
	 */
	public void setChannService(ChannService channService) {
		this.channService = channService;
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
    	params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));
    	String CHANN_NO=ParamUtil.utf8Decoder(request, "CHANN_NO");
    	params.put("CHANN_NOQuery", StringUtil.creCon("c.CHANN_NO", CHANN_NO, ","));
    	params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
    	String CHANN_NAME=ParamUtil.utf8Decoder(request, "CHANN_NAME");
    	params.put("CHANN_NAMEQuery", StringUtil.creCon("c.CHANN_NAME", CHANN_NAME, ","));
    	params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
    	String CHANN_NO_P=ParamUtil.utf8Decoder(request, "CHANN_NO_P");
    	params.put("CHANN_NO_PQuery", StringUtil.creCon("c.CHANN_NO_P", CHANN_NO_P, ","));
    	params.put("CHANN_NO_P", ParamUtil.utf8Decoder(request, "CHANN_NO_P"));
    	params.put("CHANN_NAME_P", ParamUtil.utf8Decoder(request, "CHANN_NAME_P"));
    	String CHANN_NAME_P=ParamUtil.utf8Decoder(request, "CHANN_NAME_P");
    	params.put("CHANN_NAME_PQuery", StringUtil.creCon("c.CHANN_NAME_P", CHANN_NAME_P, ","));
    	params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));
    	params.put("AREA_ID", ParamUtil.utf8Decoder(request, "AREA_ID"));
    	params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
    	params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
    	params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
    	params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
    	params.put("BEGIN_JOIN_DATE", ParamUtil.utf8Decoder(request, "BEGIN_JOIN_DATE"));
    	params.put("END_JOIN_DATE",   ParamUtil.utf8Decoder(request, "END_JOIN_DATE"));
    	params.put("PROV", ParamUtil.utf8Decoder(request, "PROV"));
    	String PROV=ParamUtil.utf8Decoder(request, "PROV");
    	params.put("PROVQuery", StringUtil.creCon("c.PROV", PROV, ","));
    	params.put("CITY", ParamUtil.utf8Decoder(request, "CITY"));
    	params.put("COUNTY",  ParamUtil.utf8Decoder(request, "COUNTY"));
    	params.put("AREA_NAME_P", ParamUtil.utf8Decoder(request, "AREA_NAME_P"));
    	String AREA_NAME_P=ParamUtil.utf8Decoder(request, "AREA_NAME_P");
    	params.put("AREA_NAME_PQuery", StringUtil.creCon("AREA_NAME_P", AREA_NAME_P, ","));
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("AREA_MANAGE_NAME",ParamUtil.utf8Decoder(request, "AREA_MANAGE_NAME"));
		//只查询分销的渠道，过滤掉总部的渠道信息
		params.put("IS_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = channService.pageQuery(params, pageNo);
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
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        Map<String,String> entry = channService.load(CHANN_ID);
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
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if (StringUtils.isNotEmpty(CHANN_ID)) {
            Map<String,String> entry = channService.load(CHANN_ID);
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
        ChannModel model = new ChannModel();
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <ChannModel>() {
            }.getType());
        }
        if(StringUtils.isEmpty(CHANN_ID)&&(channService.getCountCHANN_NO(model.getCHANN_NO())>0)){
        	jsonResult = jsonResult(false, "编号有重复值，请重新输入");
        	if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
        }else{
        	try {
        		if(channService.loadSTATE(model.getCHANN_ID_P()))
            	{
        			CHANN_ID = channService.txEdit(CHANN_ID, model, userBean);
        			jsonResult = jsonResult(true, CHANN_ID);
            	}else{
            		jsonResult = jsonResult(false, "不可选择停用的渠道作为所属渠道");
            	}
            }catch(ServiceException x){
            	jsonResult = jsonResult(false, x.getMessage());
            } catch (RuntimeException e) {
            	e.printStackTrace();
                jsonResult = jsonResult(false, "保存失败");
            }
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
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if (StringUtils.isNotEmpty(CHANN_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("CHANN_ID", CHANN_ID);
                if (channService.checkSubChann(params) > 0) {
                    jsonResult = jsonResult(false, "该渠道有生效的下级渠道，不能删除！");
                    if (null != writer) {
                        writer.write(jsonResult);
                        writer.close();
                    }
                    return;
                }
                params.put("UPDATOR", userBean.getRYXXID());
    		    params.put("UPD_NAME", userBean.getXM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
                channService.txDelete(params);
                channService.clearCaches(CHANN_ID);
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
        boolean isChanged = false;
        try {
            logger.warn("取得对应记录的状态");
            String CHANN_ID = request.getParameter("CHANN_ID");
            
            Map <String, String> params = new HashMap <String, String>();
            String changedState = "";
            params.put("CHANN_ID", CHANN_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", DateUtil.now());
            changedState = BusinessConsts.JC_STATE_DISENABLE;
            params.put("STATE", changedState);
            channService.txStopById(params);
            isChanged = true;

            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
                jsonResult = jsonResult(false, "状态不用修改");
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
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
    	params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));
    	String CHANN_NO=ParamUtil.utf8Decoder(request, "CHANN_NO");
    	params.put("CHANN_NOQuery", StringUtil.creCon("c.CHANN_NO", CHANN_NO, ","));
    	params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
    	String CHANN_NAME=ParamUtil.utf8Decoder(request, "CHANN_NAME");
    	params.put("CHANN_NAMEQuery", StringUtil.creCon("c.CHANN_NAME", CHANN_NAME, ","));
    	params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
    	String CHANN_NO_P=ParamUtil.utf8Decoder(request, "CHANN_NO_P");
    	params.put("CHANN_NO_PQuery", StringUtil.creCon("c.CHANN_NO_P", CHANN_NO_P, ","));
    	params.put("CHANN_NO_P", ParamUtil.utf8Decoder(request, "CHANN_NO_P"));
    	params.put("CHANN_NAME_P", ParamUtil.utf8Decoder(request, "CHANN_NAME_P"));
    	String CHANN_NAME_P=ParamUtil.utf8Decoder(request, "CHANN_NAME_P");
    	params.put("CHANN_NAME_PQuery", StringUtil.creCon("c.CHANN_NAME_P", CHANN_NAME_P, ","));
    	params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));
    	params.put("AREA_ID", ParamUtil.utf8Decoder(request, "AREA_ID"));
    	params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
    	params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
    	params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
    	params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
    	params.put("PROV", ParamUtil.utf8Decoder(request, "PROV"));
    	String PROV=ParamUtil.utf8Decoder(request, "PROV");
    	params.put("PROVQuery", StringUtil.creCon("c.PROV", PROV, ","));
    	params.put("CITY", ParamUtil.utf8Decoder(request, "CITY"));
    	params.put("AREA_NAME_P", ParamUtil.utf8Decoder(request, "AREA_NAME_P"));
    	String AREA_NAME_P=ParamUtil.utf8Decoder(request, "AREA_NAME_P");
    	params.put("AREA_NAME_PQuery", StringUtil.creCon("AREA_NAME_P", AREA_NAME_P, ","));
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询分销的渠道，过滤掉总部的渠道信息
		params.put("IS_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		params.put("AREA_MANAGE_NAME", ParamUtil.utf8Decoder(request, "AREA_MANAGE_NAME"));
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		params.put("ORDER_CHANN_ID", userBean.getLoginZTXXID());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		List list = channService.qryChannExp(params);
		
        //excel数据上列显示的列明
        String tmpContent="CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,CHANN_NO_P,CHANN_NAME_P,AREA_NO,AREA_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,PERSON_CON,TEL," +
        		"MOBILE,TAX,POST,ADDRESS,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_NATRUE,BUSS_SCOPE,VAT_NO,INVOICE_TI,INVOICE_ADDR,BANK_NAME,BANK_ACCT,FI_CMP_NO,DEPOSIT,DEBT_DEF,PRICE_CLAUSE," +
        		"REMARK,CRE_NAME,CRE_TIME,UPD_NAME,UPD_TIME,DEPT_NAME,ORG_NAME,REBATE_TOTAL,REBATE_YEAR,REBATE_CURRT,REBATE_USED,REBATE_FREEZE,CREDIT_CURRT,CREDIT_USED," +
        		"TEMP_CREDIT,TEMP_CREDIT_VALDT,PAY_RATE,CREDIT_CRE_NAME,CREDIT_CRE_TIME,REBATE_UPD_NAME,REBATE_UPD_TIME,SHIP_POINT_NO,SHIP_POINT_NAME,AREA_SER_NO,AREA_SER_NAME," +
        		"PUNISH_FLAG,PUNISH_REMARK,DEAL_PSON_NAME,DEAL_TIME,INI_CREDIT,FREEZE_CREDIT,PAYMENT_CREDIT,JOIN_DATE,MAX_FREEZE_DAYS,OLD_CHANN_NO,AREA_MANAGE_NAME," +
        		"AREA_MANAGE_TEL,CHAA_SALE_LVL,TAX_RATE,COST_TYPE,INIT_YEAR,INIT_MONTH,STATE,CHANN_ID,CHANN_ID_P,AREA_ID,ZONE_ID,SHIP_POINT_ID,AREA_SER_ID";
        //
        String tmpContentCn="渠道编号,渠道名称,渠道简称,渠道类型,渠道级别,上级渠道编号,上级渠道名称,区域编号,区域名称,国家,省份,城市,区县,城市类型,联系人,电话,手机,传真,邮政编号,详细地址,电子邮件,网站,法人代表,营业执照号," +
        		"税务登记号,组织机构代码证,经营性质,经营范围,增值税号,发票抬头,发票地址,开户银行,银行账号,财务对照码,保证金,欠款期限,价格条款,备注,制单人,制单时间,更新人,更新时间,制单部门,制单机构,返利总额,返利年份,当前返利,已使用返利,冻结返利,当前信用,已使用信用,临时信用," +
        		"临时信用有效期,付款比例,信用修改人,信用修改时间,返利修改人,返利修改时间,生产基地编号,生产基地名称,区域服务中心编号,区域服务中心名称,是否惩罚,惩罚说明,处理人,处理时间,初始信用,冻结信用,付款凭证信用,加盟时间,最大冻结天数," +
        		"老渠道编号,区域经理,区域经理电话,渠道销售级别,税率,成本计算方式,初始化年份,初始化月份,状态,渠道ID,上级渠道ID,区域ID,行政区划ID,发货点ID,区域服务中心ID";
        try {
			this.doExport(response, list, "渠道信息", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
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
            String CHANN_ID = request.getParameter("CHANN_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("CHANN_ID", CHANN_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", DateUtil.now());
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
            channService.txStartById(params);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
       String DELIVER_ADDR_IDS = request.getParameter("DELIVER_ADDR_IDS");
    
       if (!StringUtil.isEmpty(DELIVER_ADDR_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("DELIVER_ADDR_IDS",DELIVER_ADDR_IDS);
          List <DeliveraddrModelChld> list = channService.loadChilds(params);
          request.setAttribute("rst", list);
       }
       return mapping.findForward("childedit");
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String CHANN_ID = request.getParameter("CHANN_ID");
            String jsonDate = request.getParameter("childJsonData");
//            String DELIVER_ADDR_NOS = request.getParameter("DELIVER_ADDR_NOS");
//            String DELIVER_ADDR_IDS = request.getParameter("DELIVER_ADDR_IDS");
//            int rr = channService.valiAddrNo(CHANN_ID,DELIVER_ADDR_IDS,DELIVER_ADDR_NOS);
//            if(rr>0){
//            	jsonResult = jsonResult(false, "编号与已有的编号重复，请检查");
//            }else{
            	 if (!StringUtil.isEmpty(jsonDate)) {
                     List <DeliveraddrModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <DeliveraddrModelChld>>() {
                     }.getType());
                     channService.txChildEdit(CHANN_ID, modelList);
                     jsonResult = jsonResult(true, "保存成功");
                 }
//            }
           
        }catch(ServiceException s) {
        	jsonResult = jsonResult(false, s.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
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
            String DELIVER_ADDR_IDS = request.getParameter("DELIVER_ADDR_IDS");
            //批量删除，多个Id之间用逗号隔开
            channService.txBatch4DeleteChild(DELIVER_ADDR_IDS);
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
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if(!StringUtil.isEmpty(CHANN_ID))
        {
        	 List <DeliveraddrModelChld> result = channService.childQuery(CHANN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
    }
    
    
    /**
     * * 渠道分管信息
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward chargList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
        if(!StringUtil.isEmpty(CHANN_ID))
        {
        	 List result = channService.chargQuery(CHANN_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("charglist");
    }
    
    
	/**
     * * 启用
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childStart(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String DELIVER_ADDR_IDS = request.getParameter("DELIVER_ADDR_IDS");
            if (!StringUtil.isEmpty(DELIVER_ADDR_IDS)) {
                channService.txStarChld(DELIVER_ADDR_IDS);
                jsonResult = jsonResult(true, "操作成功");
            }
        	 
        }catch (Exception e) {
        	jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
	/**
     * * 停用
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childStop(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String DELIVER_ADDR_IDS = request.getParameter("DELIVER_ADDR_IDS");
            if (!StringUtil.isEmpty(DELIVER_ADDR_IDS)) {
                channService.txStopChld(DELIVER_ADDR_IDS);
                jsonResult = jsonResult(true, "操作成功");
            }
        }catch (Exception e) {
        	jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    public ActionForward toBatchFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO").toString();
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME").toString();
		String CHANN_TYPE = ParamUtil.get(request, "CHANN_TYPE").toString();
		String CHAA_LVL   = ParamUtil.get(request, "CHAA_LVL").toString();
		String CHANN_NO_P = ParamUtil.get(request, "CHANN_NO_P").toString();
		String CHANN_NAME_P = ParamUtil.get(request, "CHANN_NAME_P").toString();
		String AREA_NO    = ParamUtil.get(request, "AREA_NO").toString();
		String AREA_NAME  = ParamUtil.get(request, "AREA_NAME").toString();
		String PROV       = ParamUtil.get(request, "PROV").toString();
		String CITY       = ParamUtil.get(request, "CITY").toString();
		String BEGIN_CRE_TIME = ParamUtil.get(request, "BEGIN_CRE_TIME").toString();
		String END_CRE_TIME   = ParamUtil.get(request, "END_CRE_TIME").toString();
		String STATE = ParamUtil.get(request, "STATE").toString();
		String AREA_NAME_P = ParamUtil.get(request, "AREA_NAME_P").toString();
		
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("JGXXID", userBean.getJGXXID());
		request.setAttribute("CHANN_NO", CHANN_NO);
		request.setAttribute("CHANN_NAME", CHANN_NAME);
		request.setAttribute("CHANN_TYPE", CHANN_TYPE);
		request.setAttribute("CHAA_LVL", CHAA_LVL);
		request.setAttribute("CHANN_NO_P", CHANN_NO_P);
		request.setAttribute("CHANN_NAME_P", CHANN_NAME_P);
		request.setAttribute("AREA_NO", AREA_NO);
		request.setAttribute("AREA_NAME", AREA_NAME);
		request.setAttribute("PROV", PROV);
		request.setAttribute("CITY", CITY);
		request.setAttribute("BEGIN_CRE_TIME", BEGIN_CRE_TIME);
		request.setAttribute("END_CRE_TIME", END_CRE_TIME);
		request.setAttribute("STATE", STATE);
		request.setAttribute("AREA_NAME_P", AREA_NAME_P);
		return mapping.findForward("batchFrames");
	}
    
    public ActionForward toUpdateStoreFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		return mapping.findForward("upStoreIn");
	}
    
    /**
     * 批量维护区域经理
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toBatch (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BATCH))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("CHANN_NO", ParamUtil.get(request, "CHANN_NO"));
    	String CHANN_NO=ParamUtil.utf8Decoder(request, "CHANN_NO");
    	params.put("CHANN_NOQuery", StringUtil.creCon("c.CHANN_NO", CHANN_NO, ","));
    	params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
    	String CHANN_NAME=ParamUtil.utf8Decoder(request, "CHANN_NAME");
    	params.put("CHANN_NAMEQuery", StringUtil.creCon("c.CHANN_NAME", CHANN_NAME, ","));
    	params.put("CHANN_TYPE", ParamUtil.utf8Decoder(request, "CHANN_TYPE"));
    	String CHANN_NO_P=ParamUtil.utf8Decoder(request, "CHANN_NO_P");
    	params.put("CHANN_NO_PQuery", StringUtil.creCon("c.CHANN_NO_P", CHANN_NO_P, ","));
    	params.put("CHANN_NO_P", ParamUtil.utf8Decoder(request, "CHANN_NO_P"));
    	params.put("CHANN_NAME_P", ParamUtil.utf8Decoder(request, "CHANN_NAME_P"));
    	String CHANN_NAME_P=ParamUtil.utf8Decoder(request, "CHANN_NAME_P");
    	params.put("CHANN_NAME_PQuery", StringUtil.creCon("c.CHANN_NAME_P", CHANN_NAME_P, ","));
    	params.put("CHAA_LVL", ParamUtil.utf8Decoder(request, "CHAA_LVL"));
    	params.put("AREA_ID", ParamUtil.utf8Decoder(request, "AREA_ID"));
    	params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
    	params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
    	params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
    	params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
    	params.put("PROV", ParamUtil.utf8Decoder(request, "PROV"));
    	String PROV=ParamUtil.utf8Decoder(request, "PROV");
    	params.put("PROVQuery", StringUtil.creCon("c.PROV", PROV, ","));
    	params.put("CITY", ParamUtil.utf8Decoder(request, "CITY"));
    	params.put("AREA_NAME_P", ParamUtil.utf8Decoder(request, "AREA_NAME_P"));
    	String AREA_NAME_P=ParamUtil.utf8Decoder(request, "AREA_NAME_P");
    	params.put("AREA_NAME_PQuery", StringUtil.creCon("AREA_NAME_P", AREA_NAME_P, ","));
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询分销的渠道，过滤掉总部的渠道信息
		params.put("IS_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = channService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        return mapping.findForward("batch");
    }
    
    public ActionForward  toBatchEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        request.setAttribute("JGXXID", userBean.getJGXXID());
        return mapping.findForward("toBatchEdit");
    }
    
    /**
     * 批量修改区域经理
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void batchUpdate(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
        
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        String flag = ParamUtil.utf8Decoder(request, "flag");
        String channIds = ParamUtil.utf8Decoder(request, "channIds");
        String jsonData = ParamUtil.utf8Decoder(request, "jsonData");
        if(!StringUtil.isEmpty(jsonData)){
        	ChannModel model = new Gson().fromJson(jsonData, 
            		new TypeToken <ChannModel>(){}.getType());
            channService.txbatchUpdate(flag,channIds, model,userBean);
        }
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
   }  
    
    /**
     * 批量修改渠道信息中的入库数量
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void batchUpdateStoreIn(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
        
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        String flag = ParamUtil.utf8Decoder(request, "flag");
        String channIds = ParamUtil.utf8Decoder(request, "channIds");
        String jsonData = ParamUtil.utf8Decoder(request, "jsonData");
        if(!StringUtil.isEmpty(jsonData)){
        	ChannModel model = new Gson().fromJson(jsonData, 
            		new TypeToken <ChannModel>(){}.getType());
            channService.txbatchUpdateStoreIn(flag,channIds, model,userBean);
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
	    	pvgMap.put("PVG_BATCH", QXUtil.checkPvg(userBean, PVG_BATCH));
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
	 
	 
	//Excel文件导出成文件流
		//dataList 需要导出的数据列表
		//execlName 导出后默认的文件名
		//tmpContent:数据库字段名，多字段以逗号分割
		//tmpContentCnexcel:文件名字段名，多字段以逗号分割
		/**
		 * Do export.
		 * 
		 * @param response the response
		 * @param dataList the data list
		 * @param execlName the execl name
		 * @param tmpContent the tmp content
		 * @param tmpContentCn the tmp content cn
		 * 
		 * @throws Exception the exception
		 */
		public  void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn) throws Exception{
			//生成excel
			HSSFWorkbook workbook =new HSSFWorkbook();
			workbook = printExcel(tmpContent,tmpContentCn,dataList);
	         //导出excel
	         writeExecl(response,workbook,execlName);
		}
		/**
		 * Prints the excel.
		 * 
		 * @param tmpContent the tmp content
		 * @param tmpContentCn the tmp content cn
		 * @param dataList the data list
		 * 
		 * @return the hSSF workbook
		 */
		private  HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList){

		     HSSFWorkbook workbook = null;
		     String[] titles_CN = tmpContentCn.split(",");
		     String[] titles_EN = tmpContent.split(",");
		     try{
		          //创建工作簿实例 
		           workbook = new HSSFWorkbook();
		          //创建工作表实例 
		         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
		          //设置列宽 
		          setSheetColumnWidth(titles_CN,sheet);
		        //获取样式 
		          HSSFCellStyle style = createTitleStyle(workbook); 
		          if(dataList != null){
		               //创建第一行标题 
		                HSSFRow row = sheet.createRow((short)0);// 建立新行
		                row.setHeight((short) 0);
		                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
		                HSSFRow rows = sheet.createRow((short)1);// 建立新行
		                for(int i=0;i<titles_CN.length;i++){
		                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
		                       titles_CN[i]);
		                }
		                //给excel填充数据 
		               for(int i=1;i<=dataList.size();i++){ 
		                        // 将dataList里面的数据取出来 
		                        Map<String,String> map = (HashMap)(dataList.get(i-1));
		                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
		                        boolean isOverTime = false;
		                         for(int j=0;j<titles_EN.length;j++){
		                            createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
		                                  }               
		                      }
		       }else{
		                createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "查无资料");
		       }
		  }catch(Exception e){
		              e.printStackTrace();
		  }
		 return workbook;
		}
		
		//设置列宽
		/**
		 * Sets the sheet column width.
		 * @param titles_CN the titles_ cn
		 * @param sheet the sheet
		 */
		private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
		   // 根据你数据里面的记录有多少列，就设置多少列
		  for(int i=0;i<titles_CN.length;i++){
			  if(i>79){
				  sheet.setColumnWidth((short)i, (short) 0);
			  }else{
				  sheet.setColumnWidth((short)i, (short) 6000);
			  }
		  }

		}
		
		/**
		 * Write execl.
		 * 
		 * @param response the response
		 * @param workbook the workbook
		 * @param execlName the execl name
		 */
		public  void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName) {
			if (null == workbook)
			{
				workbook = new HSSFWorkbook();
			}
			
			if (0 == workbook.getNumberOfSheets()) {
				HSSFSheet sheet = workbook.createSheet("无数据");
				sheet.createRow(3).createCell(3).setCellValue("未查询到数据!");
			}
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/msexcel;charset=UTF-8");
			ServletOutputStream outputStream = null;
			try {
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-dd") + ".xls");
				outputStream = response.getOutputStream();
				workbook.write(outputStream);
				outputStream.flush();
			} catch (Exception e) {
				logger.error("", e);
			} finally {
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (IOException e) {
						logger.error("", e);
					}
				}
			}
		}
		
		//设置excel的title样式  
		/**
		 * Creates the title style.
		 * @param wb the wb
		 * @return the hSSF cell style
		 */
		private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
		  HSSFFont boldFont = wb.createFont(); 
		  boldFont.setFontHeight((short) 200); 
		  HSSFCellStyle style = wb.createCellStyle(); 
		  style.setFont(boldFont); 
		  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
		  return style;  
		}
		
		//创建Excel单元格  
		/**
		 * Creates the cell.
		 * 
		 * @param row the row
		 * @param column the column
		 * @param style the style
		 * @param cellType the cell type
		 * @param value the value
		 */
		private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
		  HSSFCell cell = row.createCell( column);  
		  if (style != null) { 
		       cell.setCellStyle(style); 
		  }   
		  String res = (value==null?"":value).toString();
		  switch(cellType){ 
		       case HSSFCell.CELL_TYPE_BLANK: {} break; 
		       case HSSFCell.CELL_TYPE_STRING: {cell.setCellValue(res+"");} break; 
		       case HSSFCell.CELL_TYPE_NUMERIC: {
		       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
		           cell.setCellValue(Double.parseDouble(value.toString()));}break; 
		       default: break; 
			 }  
			
			} 
		
		/**
		 * 查询
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public ActionForward toBatchList(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) {
			
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			 
			Map<String, String> params = new HashMap<String, String>();
			ParamUtil.putStr2Map(request, "AREA_NO", params);
			ParamUtil.putStr2Map(request, "AREA_NAME", params);
			String search = ParamUtil.get(request, "search");
			String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
			String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
			String PROV = ParamUtil.get(request, "PROV");
			String CITY = ParamUtil.get(request, "CITY");
			String CHANN_TYPE = ParamUtil.get(request, "CHANN_TYPE");
			String CHAA_LVL   = ParamUtil.get(request, "CHAA_LVL");
			StringBuffer sql =  new StringBuffer();
			if(!StringUtil.isEmpty(CHANN_NO)){
				sql.append(StringUtil.creCon("u.CHANN_NO", CHANN_NO, ","));
			}
			if(!StringUtil.isEmpty(CHANN_NAME)){
				sql.append(StringUtil.creCon("u.CHANN_NAME", CHANN_NAME, ","));
			}
			if(!StringUtil.isEmpty(PROV)){
				sql.append(StringUtil.creCon("u.PROV", PROV, ","));
			}
			if(!StringUtil.isEmpty(CITY)){
				sql.append(StringUtil.creCon("u.CITY", CITY, ","));
			}
			if(!StringUtil.isEmpty(CHANN_TYPE)){
				sql.append(StringUtil.creCon("u.CHANN_TYPE", CHANN_TYPE, ","));
			}
			if(!StringUtil.isEmpty(CHAA_LVL)){
				sql.append(StringUtil.creCon("u.CHAA_LVL", CHAA_LVL,","));
			}
			params.put("sql", sql.toString());
			params.put("search", search);
			params.put("STATE", "启用");
			if(StringUtil.isEmpty(search)){
				params.put("searchFlag", "1<>1");
			}
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			List<Map<String,String>> list = channService.childQuery(params);
			request.setAttribute("result", list);
			request.setAttribute("params", params);
			request.setAttribute("pvg", setPvg(userBean));
			return mapping.findForward("batchList");
	    }
		
		/**
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public ActionForward toStoreInList(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) {
			
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String search = ParamUtil.get(request, "search");
			Map<String, String> params = new HashMap<String, String>();
			String AREA_NO = ParamUtil.get(request, "AREA_NO");
			String AREA_NAME=ParamUtil.get(request, "AREA_NAME");
			String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
			String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
			String CHANN_TYPE = ParamUtil.get(request, "CHANN_TYPE");
			String CHAA_LVL = ParamUtil.get(request, "CHAA_LVL");
			StringBuffer sql =  new StringBuffer();
			if(!StringUtil.isEmpty(CHANN_NO)){
				sql.append(StringUtil.creCon("u.CHANN_NO", CHANN_NO, ","));
			}
			if(!StringUtil.isEmpty(CHANN_NAME)){
				sql.append(StringUtil.creCon("u.CHANN_NAME", CHANN_NAME, ","));
			}
			if(!StringUtil.isEmpty(AREA_NO)){
				sql.append(StringUtil.creCon("u.AREA_NO", AREA_NO, ","));
			}
			if(!StringUtil.isEmpty(AREA_NAME)){
				sql.append(StringUtil.creCon("u.AREA_NAME", AREA_NAME, ","));
			}
			if(!StringUtil.isEmpty(CHANN_TYPE)){
				sql.append(StringUtil.creCon("u.CHANN_TYPE", CHANN_TYPE, ","));
			}
			if(!StringUtil.isEmpty(CHAA_LVL)){
				sql.append(StringUtil.creCon("u.CHAA_LVL", CHAA_LVL, ","));
			}
			params.put("sql", sql.toString());
			params.put("search", search);
			params.put("STATE", "启用");
//			if(StringUtil.isEmpty(search)){
//				params.put("searchFlag", "1<>1");
//			}
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			List<Map<String,String>> list = channService.childQuery(params);
			request.setAttribute("result", list);
			request.setAttribute("params", params);
			request.setAttribute("pvg", setPvg(userBean));
			return mapping.findForward("storeInList");
	    }
		
		
		public ActionForward toStoreInListT(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) {
			
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String search = ParamUtil.get(request, "search");
			Map<String, String> params = new HashMap<String, String>();
			String AREA_NO = ParamUtil.get(request, "AREA_NO");
			String AREA_NAME=ParamUtil.get(request, "AREA_NAME");
			String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
			String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
			String CHANN_TYPE = ParamUtil.get(request, "CHANN_TYPE");
			String CHAA_LVL = ParamUtil.get(request, "CHAA_LVL");
			StringBuffer sql =  new StringBuffer();
			if(!StringUtil.isEmpty(CHANN_NO)){
				sql.append(StringUtil.creCon("u.CHANN_NO", CHANN_NO, ","));
			}
			if(!StringUtil.isEmpty(CHANN_NAME)){
				sql.append(StringUtil.creCon("u.CHANN_NAME", CHANN_NAME, ","));
			}
			if(!StringUtil.isEmpty(AREA_NO)){
				sql.append(StringUtil.creCon("u.AREA_NO", AREA_NO, ","));
			}
			if(!StringUtil.isEmpty(AREA_NAME)){
				sql.append(StringUtil.creCon("u.AREA_NAME", AREA_NAME, ","));
			}
			if(!StringUtil.isEmpty(CHANN_TYPE)){
				sql.append(StringUtil.creCon("u.CHANN_TYPE", CHANN_TYPE, ","));
			}
			if(!StringUtil.isEmpty(CHAA_LVL)){
				sql.append(StringUtil.creCon("u.CHAA_LVL", CHAA_LVL, ","));
			}
			params.put("sql", sql.toString());
			params.put("search", search);
			params.put("STATE", "启用");
//			if(StringUtil.isEmpty(search)){
//				params.put("searchFlag", "1<>1");
//			}
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			//List<Map<String,String>> list = channService.childQuery(params);
			//request.setAttribute("result", list);
			request.setAttribute("params", params);
			request.setAttribute("pvg", setPvg(userBean));
			return mapping.findForward("storeInList");
	    }
		
		/**
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public ActionForward toTopPage(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			logger.info("toFrame方法调用开始");
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			request.setAttribute("pvg", setPvg(userBean));
			request.setAttribute("JGXXID", userBean.getJGXXID());
			return mapping.findForward("top");
		}
		
		/**
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public ActionForward toTopStoreIn(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			logger.info("toFrame方法调用开始");
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			request.setAttribute("pvg", setPvg(userBean));
			request.setAttribute("JGXXID", userBean.getJGXXID());
			return mapping.findForward("topStoreIn");
		}
}
