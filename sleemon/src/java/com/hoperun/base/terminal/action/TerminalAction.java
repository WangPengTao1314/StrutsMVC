/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：TerminalAction.java
 */
package com.hoperun.base.terminal.action;

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
import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.base.terminal.service.TerminalService;
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

// TODO: Auto-generated Javadoc
/**
 * The Class BrandAction.
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
 */
public class TerminalAction extends BaseAction {

	/** 权限对象维护**/
    //增删改查
    private static String PVG_BWS="XT0012101";
    private static String PVG_EDIT="XT0012102";
    private static String PVG_DELETE="XT0012103";
    //启用,停用
    private static String PVG_START_STOP="XT0012104";
    //门店价格公式设置
    private static String PVG_PRICE_EXPRESS = "XT0012105";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
    /**权限 end*/
    
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
    /**审批 end**/
   
    /** The terminal service. */
    private TerminalService terminalService;
    
    /**
     * 品牌信息框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//设置跳转时需要的一些公用属性
    	ParamUtil.setCommAttributeEx(request);
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 品牌信息列表.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map params = new HashMap();
    	params.put("TERM_NO", ParamUtil.utf8Decoder(request, "TERM_NO"));				//终端编号
    	params.put("TERM_NAME", ParamUtil.utf8Decoder(request, "TERM_NAME"));			//终端名称
        params.put("TERM_TYPE", ParamUtil.utf8Decoder(request, "TERM_TYPE"));			//终端类型
        params.put("TERM_LVL", ParamUtil.utf8Decoder(request, "TERM_LVL"));				//终端级别
        params.put("CHANN_NO_P", ParamUtil.utf8Decoder(request, "CHANN_NO_P"));			//上级渠道编号
        params.put("CHANN_NAME_P", ParamUtil.utf8Decoder(request, "CHANN_NAME_P"));		//上级渠道名称
        params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));				//销售区域编号
        params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));			//销售区域名称
        params.put("CRE_TIME_FROM", ParamUtil.utf8Decoder(request, "CRE_TIME_FROM"));	//制单时间从
        params.put("CRE_TIME_TO", ParamUtil.utf8Decoder(request, "CRE_TIME_TO"));		//制单时间到
        params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));					//状态
        params.put("BUSS_STATE", ParamUtil.utf8Decoder(request, "BUSS_STATE"));			//经营状态
        params.put("AREA_NAME_P", ParamUtil.utf8Decoder(request, "AREA_NAME_P"));		//战区
        params.put("AREA_MANAGE_NAME", ParamUtil.utf8Decoder(request, "AREA_MANAGE_NAME"));  //区域经理
        params.put("BEG_SBUSS_DATE_BEG", ParamUtil.utf8Decoder(request, "BEG_SBUSS_DATE_BEG")); //开店日期从
        params.put("BEG_SBUSS_DATE_END", ParamUtil.utf8Decoder(request, "BEG_SBUSS_DATE_END")); //开店日期到
        
        params.put("PROV", ParamUtil.utf8Decoder(request, "PROV")); //省份
        params.put("CITY", ParamUtil.utf8Decoder(request, "CITY")); //城市
        params.put("COUNTY", ParamUtil.utf8Decoder(request, "COUNTY")); //区县
        
        params.put("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
        if ("1".equals(userBean.getIS_DRP_LEDGER())) {
			params.put("CHANN_NO_P", userBean.getCHANN_ID());
		}
        
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		ParamUtil.setOrderField(request, params, "CRE_TIME", "DESC");
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("t",qx.toString()));
		params.put("QXJBCON", sb.toString());
		 //字段排序
  		//ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = terminalService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 查看人员详细信息.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String TERM_ID = ParamUtil.get(request, "TERM_ID");
        Map entry = terminalService.load(TERM_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("detail");
    }


    /**
     * 点击新增或修改按钮跳转到编辑页面.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String TERM_ID = ParamUtil.get(request, "TERM_ID");
        if (StringUtils.isNotEmpty(TERM_ID)) {
            Map entry = terminalService.loadT(TERM_ID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("toedit");
    }


    /**
     * 编辑.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String TERM_ID = ParamUtil.get(request, "TERM_ID");
			String jsonData = ParamUtil.get(request, "jsonData");
			TerminalModel model = new TerminalModel();

			if (StringUtils.isNotEmpty(jsonData)) {
				model = new Gson().fromJson(jsonData,
						new TypeToken<TerminalModel>() {
						}.getType());
			}

			// 0154519:现在终端编号允许重复保存 modify 不许重复 2014-6-5
			String TERM_NO = model.getTERM_NO();
			int t = terminalService.getExits(TERM_NO);
			if (t != 0 && StringUtil.isEmpty(TERM_ID)) {
				jsonResult = jsonResult(false, "'终端编号'重复，请检查是否已存在或更改终端编号");
			} else {
				terminalService.txEdit(TERM_ID, model, userBean);
			}
		} catch (RuntimeException e) {
			jsonResult = jsonResult(false, "保存失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
    }


    /**
	 * 删除.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String TERM_ID = ParamUtil.get(request, "TERM_ID");

        if (StringUtils.isNotEmpty(TERM_ID)) {
            try {
	        	terminalService.txDelete(TERM_ID, userBean);
	        	terminalService.clearCaches(TERM_ID);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

    /**
     * 启用.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
         	String TERM_ID = request.getParameter("TERM_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("TERM_ID", TERM_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
            terminalService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
	 * 停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String TERM_ID = request.getParameter("TERM_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("TERM_ID", TERM_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            terminalService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    /**
     * 编辑价格计算公式
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void modifyExpress(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String TERM_ID = ParamUtil.get(request, "TERM_ID");
			String PRICE_EXPRESS = ParamUtil.get(request, "PRICE_EXPRESS");
			String PRICE_EXPRESS_CHS = ParamUtil.get(request, "PRICE_EXPRESS_CHS");
			String flag = ParamUtil.get(request, "flag");
			Map<String, String> params = new HashMap<String, String>();
			params.put("TERM_ID",TERM_ID);
			params.put("PRICE_EXPRESS",PRICE_EXPRESS);
			params.put("PRICE_EXPRESS_CHS",PRICE_EXPRESS_CHS);
			if(!StringUtil.isEmpty(flag)){
				terminalService.testExpress(params);
				jsonResult = jsonResult(true, "测试成功");
			}else{
				terminalService.txModifyExpress(params);
				jsonResult = jsonResult(true, "保存成功");
			}
			
		}catch(ServiceException se){
			jsonResult = jsonResult(false,se.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			e.printStackTrace();
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
		ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_TYPE", params);
	    ParamUtil.putStr2Map(request, "TERM_LVL", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO_P", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME_P", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_FROM", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_TO", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        params.put("BUSS_STATE", ParamUtil.utf8Decoder(request, "BUSS_STATE"));			//经营状态
        params.put("AREA_NAME_P", ParamUtil.utf8Decoder(request, "AREA_NAME_P"));		//战区
        params.put("AREA_MANAGE_NAME", ParamUtil.utf8Decoder(request, "AREA_MANAGE_NAME")); //区域经理
        params.put("BEG_SBUSS_DATE_BEG", ParamUtil.utf8Decoder(request, "BEG_SBUSS_DATE_BEG")); //开业日期从
        params.put("BEG_SBUSS_DATE_END", ParamUtil.utf8Decoder(request, "BEG_SBUSS_DATE_END")); //开业日期到
        params.put("PROV", ParamUtil.utf8Decoder(request, "PROV"));      //省份
        params.put("CITY", ParamUtil.utf8Decoder(request, "CITY"));      //城市
        params.put("COUNTY", ParamUtil.utf8Decoder(request, "COUNTY"));  //区县
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		//权限级别和审批流的封装
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("t",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		
	    //查询时状态为查询选择状态，不然状态为未提交和退回状态
//	    if(StringUtil.isEmpty(search)){
//	    	 params.put("searchSTATE","'未提交','退回'");
//	    }else {
//	    	ParamUtil.putStr2Map(request, "STATE", params);
//		}
//	    if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
//	    	params.put("STATES", "'未提交','提交','待退货','已入库','已结算','已退货','退回'");
//	    }
	    
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
	 
		List list = terminalService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="终端编号,终端名称,终端简称,终端类型,终端级别,营业性质,上级渠道编号,上级渠道名称," +
				"终端版本,门店分类,区域编号,区域名称,战区名称,区域经理名称,国家,省份,城市,区县,城市类型,营业状态,开店类型,卖场名称,商场位置类别,开业时间,联系人," +
				"电话,手机,传真,邮政编码,电子邮件,网站,法人代表,营业执照号,税务登记号,组织结构代码证,经营范围,财务对照码,营业面积,楼层数,装修完成时间," +
				"制单人,制单时间,账套名称,状态,详细地址,备注";
		String tmpContent = "TERM_NO,TERM_NAME,TERM_ABBR,TERM_TYPE,TERM_LVL,BUSS_NATRUE,CHANN_NO_P,CHANN_NAME_P," +
				"TERM_VERSION,TERM_CLASS,AREA_NO,AREA_NAME,AREA_NAME_P,AREA_MANAGE_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,BUSS_STATE,BEG_BUSS_TYPE,SALE_STORE_NAME,LOCAL_TYPE,BEG_SBUSS_DATE,PERSON_CON," +
				"TEL,MOBILE_PHONE,TAX,POST,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_SCOPE,FI_CMP_NO,BUSS_AREA,STOR_NO,LAST_DECOR_TIME," +
				"CRE_NAME,CRE_TIME,LEDGER_NAME,STATE,ADDRESS,REMARK";
		String colType= "string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "终端信息", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean) {
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
    	pvgMap.put("PVG_PRICE_EXPRESS",QXUtil.checkPvg(userBean, PVG_PRICE_EXPRESS) );
    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
   }
	 
	    /**
	     * Sets the terminal service.
	     * 
	     * @param terminalService the new terminal service
	     */
	    public void setTerminalService(TerminalService terminalService) {
			this.terminalService = terminalService;
	    }
	    


}
