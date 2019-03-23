/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductAction.java
 */
package com.hoperun.base.product.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hoperun.base.product.model.PrdSpclTechModel;
import com.hoperun.base.product.model.PrdSuitModel;
import com.hoperun.base.product.model.PrdUnitModel;
import com.hoperun.base.product.model.ProductModel;
import com.hoperun.base.product.model.ProductTree;
import com.hoperun.base.product.service.ProductService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;



/**
 * The Class ProductAction.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
public class ProductAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(ProductAction.class);
	/** 权限对象**/
	/** 总部维护*/
    //增删改查
    private static String PVG_BWS="XT0012401";
    private static String PVG_EDIT="XT0012402";
    private static String PVG_DELETE="XT0012403";
    private static String PVG_DRP_IMPORT="FX0010405";//渠道导入导出
    private static String PVG_ERP_IMPORT="XT0012405";//总部导入导出
    //启用,停用
    private static String PVG_START_STOP="XT0012404";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    
    /**分销维护**/
  //增删改查
    private static String PVG_DRP_BWS="FX0010401";
    private static String PVG_DRP_EDIT="FX0010402";
    private static String PVG_DRP_DELETE="FX0010403";
    //启用,停用
    private static String PVG_DRP_START_STOP="FX0010404";
    
    
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
	private ProductService productService;
	/**
	 * @return the productService
	 */
	public ProductService getProductService() {
		return productService;
	}

	/**
	 * @param productService the productService to set
	 */
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 货品框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			return mapping.findForward("drpFrames");
		}else{
			return mapping.findForward("frames");
		}
	}

	/**
	 *  查询结果列表
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRD_ID", ParamUtil.utf8Decoder(request, "PRD_ID").trim());
		params.put("productid", ParamUtil.utf8Decoder(request, "productid").trim());
		params.put("PRD_NO", ParamUtil.utf8Decoder(request, "PRD_NO").trim());
		params.put("PRD_NAME", ParamUtil.utf8Decoder(request, "PRD_NAME").trim());
		params.put("PRD_SPEC", ParamUtil.utf8Decoder(request, "PRD_SPEC").trim());
		params.put("PRD_COLOR", ParamUtil.utf8Decoder(request, "PRD_COLOR").trim());
		params.put("BRAND", ParamUtil.utf8Decoder(request, "BRAND"));
		String PAR_PRD_NO=ParamUtil.utf8Decoder(request, "PAR_PRD_NO");
		params.put("PAR_PRD_NOQuery", StringUtil.creCon("p.PAR_PRD_NO", PAR_PRD_NO, ","));
		params.put("PAR_PRD_NO", ParamUtil.utf8Decoder(request, "PAR_PRD_NO"));
		String PAR_PRD_NAME=ParamUtil.utf8Decoder(request, "PAR_PRD_NAME");
		params.put("PAR_PRD_NAMEQuery", StringUtil.creCon("p.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		params.put("PAR_PRD_NAME", ParamUtil.utf8Decoder(request, "PAR_PRD_NAME"));
		params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
		params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
		//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询终结点标记为1的
		params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		//字段排序
		ParamUtil.setOrderField(request, params, "p.CRE_TIME,p.PRD_NO", "DESC");
		String search = ParamUtil.get(request,"search");
		String LEDGERSQL;
		//权限级别和审批流的封装以及状态的封装
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_DRP_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			LEDGERSQL=" LEDGER_ID='"+userBean.getLoginZTXXID()+"' ";
//			if(StringUtil.isEmpty(search)&&StringUtil.isEmpty(params.get("STATE"))){
//				params.put("seachSTATE", BusinessConsts.JC_STATE_DEFAULT);
//			}
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			LEDGERSQL=" COMM_FLAG = 1 ";
		}
		params.put("LEDGERSQL", LEDGERSQL);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = productService.pageQuery(params, pageNo);
		//查询所有品牌名称
        List<String> list=productService.getBrand("toEdit");
        request.setAttribute("list", list);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 *  查询结果列表（分销）
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDrpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_DRP_BWS))) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRD_ID", ParamUtil.utf8Decoder(request, "PRD_ID"));
		params.put("productid", ParamUtil.utf8Decoder(request, "productid"));
		params.put("PRD_NO", ParamUtil.utf8Decoder(request, "PRD_NO"));
		params.put("PRD_NAME", ParamUtil.utf8Decoder(request, "PRD_NAME"));
		params.put("PRD_SPEC", ParamUtil.utf8Decoder(request, "PRD_SPEC"));		
		params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
		params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
    	params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
		//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询终结点标记为1的
		params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		//字段排序
		ParamUtil.setOrderField(request, params, "p.CRE_TIME", "DESC");
		String search = ParamUtil.get(request,"search");
		String LEDGERSQL;
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_DRP_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		LEDGERSQL=" LEDGER_ID='"+userBean.getLoginZTXXID()+"' ";		
		params.put("LEDGERSQL", LEDGERSQL);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = productService.pageQuery(params, pageNo);
		
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("drplist");
	}
	

	/**
	 *  货品信息编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_EDIT) && !QXUtil.checkMKQX(
						userBean, PVG_DRP_EDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String PRD_ID = ParamUtil.get(request, "PRD_ID");
		if (!StringUtil.isEmpty(PRD_ID)) {
			Map<String, String> entry = productService.load(PRD_ID);
			request.setAttribute("rst", entry);
		}

		if ("1".equals(userBean.getIS_DRP_LEDGER())) {
			request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
			return mapping.findForward("drptoedit");
		} else {
			// 查询所有品牌名称
			List<String> list = productService.getBrand("toEdit");
			request.setAttribute("list", list);
			return mapping.findForward("toedit");
		}
	}

	/**
	 *  货品信息编辑 新增/修改
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		logger.info("Enter edit()");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		ProductModel productModel = new ProductModel();
		String PRD_ID = ParamUtil.get(request, "PRD_ID");
		if (!StringUtil.isEmpty(jsonData)) {
			productModel = new Gson().fromJson(jsonData,
					new TypeToken<ProductModel>() {
					}.getType());
		}
		if(StringUtils.isEmpty(PRD_ID)&&(productService.getCountPRD_NO(productModel.getPRD_NO())>0)){
			jsonResult = jsonResult(false, "编号有重复值，请重新输入");
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}else{
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_ID", PRD_ID);
			try {
				PRD_ID = productService.txEdit(PRD_ID,productModel,userBean);
				jsonResult = jsonResult(true, PRD_ID);
			}catch(ServiceException s){
				jsonResult = jsonResult(false, s.getMessage());
			} catch (Exception e) {
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
	 *  查看货品详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String PRD_ID = ParamUtil.utf8Decoder(request, "PRD_ID");
		if(!StringUtil.isEmpty(PRD_ID))
        {
			String fileName=LogicUtil.getPicPath("");
			Map<String, String> entry = productService.load(PRD_ID);
			String PIC_PATH=LogicUtil.getPicPath(entry.get("PIC_PATH"));
			String DTL_PATH=LogicUtil.getPicPath(entry.get("DTL_PATH"));
			request.setAttribute("PIC_PATH", PIC_PATH);
			request.setAttribute("DTL_PATH", DTL_PATH);
			request.setAttribute("fileName", fileName);
			request.setAttribute("rst", entry);
        }
		return mapping.findForward("view");
	}
	
	/**
	 *  查看货品详细信息（分销）
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDrpDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_DRP_BWS))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String PRD_ID = ParamUtil.utf8Decoder(request, "PRD_ID");
		if (!StringUtil.isEmpty(PRD_ID)) {
			Map<String, String> entry = productService.load(PRD_ID);

			request.setAttribute("rst", entry);
		}
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("drpview");
	}

	/**
	 *  删除
	 * @param mapping the mapping
	 * @param formthe form
	 * @param requestthe request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_DELETE)&&!QXUtil.checkMKQX(userBean, PVG_DRP_DELETE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_ID = ParamUtil.get(request, "PRD_ID");
			Map <String, String> params = new HashMap <String, String>();
		    params.put("PRD_ID", PRD_ID);
		    params.put("UPDATOR", userBean.getXTYHID());
		    params.put("UPD_NAME", userBean.getYHM());
		    params.put("UPD_TIME", DateUtil.now());
		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		    productService.txDelete(params);
		    productService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 *  按钮修改状态为启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_START_STOP)&&!QXUtil.checkMKQX(userBean, PVG_DRP_START_STOP)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String PRD_ID = request.getParameter("PRD_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_ID", PRD_ID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPD_TIME", DateUtil.now());
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			productService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
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
	 * 按钮修改状态为停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	//权限验证
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_START_STOP)&&!QXUtil.checkMKQX(userBean, PVG_DRP_START_STOP)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String PRD_ID = request.getParameter("PRD_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_ID", PRD_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPD_TIME", DateUtil.now());
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            productService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
        	e.printStackTrace();
        	
            jsonResult = jsonResult(false, "状态修改失败!");
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
	    	pvgMap.put("PVG_DRP_BWS",QXUtil.checkPvg(userBean, PVG_DRP_BWS));
	    	pvgMap.put("PVG_DRP_EDIT",QXUtil.checkPvg(userBean, PVG_DRP_EDIT));
	    	pvgMap.put("PVG_DRP_DELETE",QXUtil.checkPvg(userBean, PVG_DRP_DELETE)  );
	    	pvgMap.put("PVG_DRP_START_STOP",QXUtil.checkPvg(userBean, PVG_DRP_START_STOP) );
	    	pvgMap.put("PVG_DRP_IMPORT",QXUtil.checkPvg(userBean, PVG_DRP_IMPORT) );
	    	pvgMap.put("PVG_ERP_IMPORT",QXUtil.checkPvg(userBean, PVG_ERP_IMPORT) );
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	   
	   /**
     * 显示树.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        List <ProductTree> trees;
        try {
            trees = productService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("msg", "获取货品信息失败！");
            return mapping.findForward(FAILURE);
        }
    }
    /**
     * * 货品计量单位列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toUnitList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	//权限验证
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_ID =ParamUtil.get(request, "PRD_ID");
        if(!StringUtil.isEmpty(PRD_ID))
        {
        	 List <PrdUnitModel> result = productService.unitQuery(PRD_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("toUnitList");
    }
    /**
     * * 货品套列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toSuitList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	//权限验证
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String MAIN_PRD_ID =ParamUtil.get(request, "MAIN_PRD_ID");
        if(!StringUtil.isEmpty(MAIN_PRD_ID))
        {
        	 List <PrdSuitModel> result = productService.suitQuery(MAIN_PRD_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("toSuitList");
    }
    /**
     * * 编辑框架页面加载子页面货品计量单位
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToUnitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_ID = ParamUtil.get(request, "PRD_ID");
        if(!StringUtil.isEmpty(PRD_ID))
        {
        	 List <PrdUnitModel> result = productService.unitQuery(PRD_ID);
             request.setAttribute("rst", result);
        }
		request.setAttribute("pvg", setPvg(userBean));
        return mapping.findForward("unitedit");
    }
    /**
     * * 编辑框架页面加载子页面货品套
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToSuitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String MAIN_PRD_ID = ParamUtil.get(request, "MAIN_PRD_ID");
        if(!StringUtil.isEmpty(MAIN_PRD_ID))
        {
        	 List <PrdSuitModel> result = productService.suitQuery(MAIN_PRD_ID);
             request.setAttribute("rst", result);
        }
		request.setAttribute("pvg", setPvg(userBean));
        return mapping.findForward("suitedit");
    }
    /**
     * * to 计量单位明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toUnitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRD_UNIT_IDs = request.getParameter("PRD_UNIT_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_UNIT_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_UNIT_IDS",PRD_UNIT_IDs);
          List <PrdUnitModel> list = productService.loadUnits(params);
          request.setAttribute("rst", list);
        }
		request.setAttribute("pvg", setPvg(userBean));
        return mapping.findForward("unitedit");
    }
    /**
     * * to 货品套明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toSuitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRD_UNIT_IDs = request.getParameter("PRD_UNIT_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_UNIT_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_UNIT_IDS",PRD_UNIT_IDs);
          List <PrdSuitModel> list = productService.loadSuits(params);
          request.setAttribute("rst", list);
        }
		request.setAttribute("pvg", setPvg(userBean));
        return mapping.findForward("suitedit");
    }
    /**
     * * 货品计量单位 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void unitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_ID = request.getParameter("PRD_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdUnitModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdUnitModel>>() {
                }.getType());
                for(int i=0;i<modelList.size();i++){
                	if(modelList.get(i).getPRD_UNIT_ID()==null&&productService.getCountUnitMEAS_UNIT_NO(modelList.get(i).getMEAS_UNIT_NO(),modelList.get(i).getPRD_ID())>0){
                		jsonResult = jsonResult(false, "编号已存在，请重新选取");
                		if (null != writer) {
                            writer.write(jsonResult);
                            writer.close();
                        }
                	}
                }
                productService.txUnitEdit(PRD_ID, modelList);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
     * * 货品套 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void suitEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String MAIN_PRD_ID = request.getParameter("MAIN_PRD_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdSuitModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdSuitModel>>() {
                }.getType());
                for(int i=0;i<modelList.size();i++){
                	if(modelList.get(i).getPRD_UNIT_ID()==null&&productService.getCountSuitPRD_NO(modelList.get(i).getPRD_NO(),modelList.get(i).getMAIN_PRD_ID())>0){
                		jsonResult = jsonResult(false, "编号已存在，请重新选取");
                	}
                }
                productService.txSuitEdit(MAIN_PRD_ID, modelList);
            }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
     * * 货品计量单位批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void unitDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_DELETE)&&!QXUtil.checkMKQX(userBean, PVG_DRP_DELETE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_UNIT_IDS = request.getParameter("PRD_UNIT_IDS");
            //批量删除，多个Id之间用逗号隔开
            productService.txBatch4DeleteUnit(PRD_UNIT_IDS);
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
     * * 货品套批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void suitDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_DELETE)&&!QXUtil.checkMKQX(userBean, PVG_DRP_DELETE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_UNIT_IDS = request.getParameter("PRD_UNIT_IDS");
            //批量删除，多个Id之间用逗号隔开
            productService.txBatch4DeleteSuit(PRD_UNIT_IDS);
		}catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
	 *  特殊工艺配置
	 *  added by huangru
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public ActionForward toPrdTech(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_ID = ParamUtil.get(request, "PRD_ID");
        if(!StringUtil.isEmpty(PRD_ID))
        {
        	 List <PrdSpclTechModel> resultSizereset = productService.prdSpclTechQuery(PRD_ID," SPCL_TECH_TYPE='尺寸调整' ");
        	 List <PrdSpclTechModel> resultPartReplace = productService.prdSpclTechQuery(PRD_ID," SPCL_TECH_TYPE='部件替换'  "); 
        	 List <PrdSpclTechModel> resultPartAdd = productService.prdSpclTechQuery(PRD_ID," SPCL_TECH_TYPE='部件新增' ");
        	 List <PrdSpclTechModel> resultFBAdd = productService.prdSpclTechQuery(PRD_ID," SPCL_TECH_TYPE='非标工艺说明' ");
        	 Map<String, String> entry = new HashMap<String, String>();
        	 if(resultFBAdd!=null && resultFBAdd.size()>0){
        		entry = (Map<String, String>)resultFBAdd.get(0);
        	 }
        	
             request.setAttribute("resultSizereset", resultSizereset);
             request.setAttribute("resultPartReplace", resultPartReplace);
             request.setAttribute("resultPartAdd", resultPartAdd);
             request.setAttribute("rst4", entry);
        }
        request.setAttribute("PRD_ID", PRD_ID);
		request.setAttribute("pvg", setPvg(userBean));
		
		return mapping.findForward("toPrdTech");
	}
	/**
	 *  特殊工艺新增和编辑
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void toPrdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
    	String jsonResult = jsonResult();
    	String jsonData = ParamUtil.get(request, "jsonData");
    	String PRD_SPCL_TECH_ID = ParamUtil.get(request, "PRD_SPCL_TECH_ID");
    	String PRD_ID = ParamUtil.get(request, "PRD_ID");
    	String SPCL_TECH_TYPE = ParamUtil.get(request, "SPCL_TECH_TYPE");
    	PrdSpclTechModel prdSpclTechModel = new PrdSpclTechModel();
		
		if(!StringUtil.isEmpty(jsonData)) {
			prdSpclTechModel = new Gson().fromJson(jsonData, new TypeToken<PrdSpclTechModel>() {}.getType());
			prdSpclTechModel.setPRD_ID(PRD_ID);
			prdSpclTechModel.setSPCL_TECH_TYPE(SPCL_TECH_TYPE);
			try {
				String reString  = productService.txPartAddEdit(PRD_SPCL_TECH_ID,prdSpclTechModel,userBean);
				jsonResult = jsonResult(true, "保存成功");
			} catch (Exception e) {
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
	 *  特殊工艺页面跳转
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	
	public ActionForward toPrdPartAddEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_EDIT)&&!QXUtil.checkMKQX(userBean, PVG_DRP_EDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_SPCL_TECH_ID = ParamUtil.get(request, "PRD_SPCL_TECH_ID");
        String PRD_ID = ParamUtil.get(request, "PRD_ID");
        String num = ParamUtil.get(request, "num");
        if(!StringUtil.isEmpty(PRD_SPCL_TECH_ID)){
        	Map<String, String> entry = productService.prdspctechQuery(PRD_SPCL_TECH_ID);
 			request.setAttribute("rst", entry);
 			
        }
        request.setAttribute("PRD_ID", PRD_ID);
		request.setAttribute("pvg", setPvg(userBean));
		if("SizeReset".equals(num)){
			return mapping.findForward("toPrdPartReset");
		}else if("PartReplace".equals(num)){
			return mapping.findForward("toPrdPartReplace");
		}else if("PartAdd".equals(num)){
			return mapping.findForward("toPrdPartAdd");
		}else{
			return mapping.findForward("");
		}
		
	}
	
	/**
	 *  特殊工艺删除
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	 public void deletePrdSpclTech(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_DELETE)&&!QXUtil.checkMKQX(userBean, PVG_DRP_DELETE)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String PRD_SPCL_TECH_ID = ParamUtil.get(request, "PRD_SPCL_TECH_ID");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	if(!StringUtil.isEmpty(PRD_SPCL_TECH_ID)){
        		productService.deletePrdSpclTechById(PRD_SPCL_TECH_ID);
        	}
		} catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	 public void toExpData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 UserBean userBean = ParamUtil.getUserBean(request);
		 if (Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_DRP_IMPORT)) {
				throw new ServiceException("对不起，您没有权限 !");
	    }
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRD_ID", ParamUtil.utf8Decoder(request, "PRD_ID"));
		params.put("productid", ParamUtil.utf8Decoder(request, "productid"));
		params.put("PRD_NO", ParamUtil.utf8Decoder(request, "PRD_NO"));
		params.put("PRD_NAME", ParamUtil.utf8Decoder(request, "PRD_NAME"));
		params.put("PRD_SPEC", ParamUtil.utf8Decoder(request, "PRD_SPEC"));
		params.put("PRD_COLOR", ParamUtil.utf8Decoder(request, "PRD_COLOR"));
		params.put("BRAND", ParamUtil.utf8Decoder(request, "BRAND"));
		params.put("PAR_PRD_NO", ParamUtil.utf8Decoder(request, "PAR_PRD_NO"));
		params.put("PAR_PRD_NAME", ParamUtil.utf8Decoder(request, "PAR_PRD_NAME"));
		params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
		params.put("BEGIN_CRE_TIME", ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME"));
		params.put("END_CRE_TIME", ParamUtil.utf8Decoder(request, "END_CRE_TIME"));
		//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询终结点标记为1的
		params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		//字段排序
		ParamUtil.setOrderField(request, params, "CRE_TIME", "DESC");
		String search = ParamUtil.get(request,"search");
		String LEDGERSQL;
		//权限级别和审批流的封装以及状态的封装
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_DRP_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			LEDGERSQL=" LEDGER_ID='"+userBean.getLoginZTXXID()+"' ";
	//				if(StringUtil.isEmpty(search)&&StringUtil.isEmpty(params.get("STATE"))){
	//					params.put("seachSTATE", BusinessConsts.JC_STATE_DEFAULT);
	//				}
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			LEDGERSQL=" COMM_FLAG = 1 ";
		}
		params.put("LEDGERSQL", LEDGERSQL);
		List list = productService.queryPrdExp(params);
		 //excel数据上列显示的列明
		String tmpContentCn="货品编号,货品名称,规格型号,标准单位,采购价,销售价";
		String tmpContent="PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,PRVD_PRICE,FACT_PRICE";
        try {
			ExcelUtil.doExport(response, list, "货品信息", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * 导入
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void toImportData(ActionMapping mapping, ActionForm form, 
			 HttpServletRequest request, HttpServletResponse response) {
		 PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	        	UserBean userBean = ParamUtil.getUserBean(request);
	        	String serverDir = Properties.getString("UPLOADFILE_DIR");
	        	String fileName = "sample"+ParamUtil.utf8Decoder(request, "fileName");
	        	String secPath = Properties.getString("SAMPLE_DIR");
	        	//总部分销标记，为0时总部，为1时渠道
	        	String DRPFLAG=ParamUtil.utf8Decoder(request, "DRPFLAG");
	        	String path = serverDir+ File.separatorChar + secPath+ File.separatorChar + fileName;
	        	List<Map<String,String>> alist = new ArrayList<Map<String,String>>();
	        	Map<String,String> map = new HashMap<String,String>();
	        	if("1".equals(DRPFLAG)){
	        		map.put("PRD_NO", "0");//货品编号
	            	map.put("PRD_NAME", "1");//货品名称
	            	map.put("PAR_PRD_NO","2"); //货品分类编号
	            	map.put("PAR_PRD_NAME","3"); //货品分类编号
	            	map.put("PRD_SPEC", "4");//规格型号
	            	map.put("STD_UNIT", "5");//标准单位
	            	map.put("PRVD_PRICE", "6");//E采购价
	            	map.put("FACT_PRICE", "7");//F 销售价
	            	map.put("REMARK", "8");//备注
	        	}else{
	        		map.put("PRD_NO", "0");//货品编号
	            	map.put("PRD_NAME", "1");//货品名称
	            	map.put("PRD_SPEC", "2");//规格型号
	            	map.put("BRAND", "3");//品牌
	            	map.put("STD_UNIT", "4");//标准单位
	            	map.put("PRVD_PRICE", "5");//供货价
	            	map.put("PRD_COLOR", "6");//颜色
	            	map.put("MEAS_UNIT", "7");//默认计量单位
	            	map.put("RATIO", "8");//换算关系
	            	map.put("PAR_PRD_NO", "9");//货品分类编号
	            	map.put("PAR_PRD_NAME", "10");//货品分类名称
	            	map.put("VOLUME", "11");//体积
	            	map.put("LENGTH", "12");//长度
	            	map.put("WIDTH", "13");//宽度
	            	map.put("HEIGHT", "14");//高度
	            	map.put("FACT_PRICE", "15");//零售价
	            	map.put("RET_PRICE_MIN", "16");//最低零售价格
	            	map.put("IS_CAN_FREE_FLAG", "17");//是否赠品
	            	map.put("IS_SCAN", "18");//是否扫码
	            	map.put("BEG_DATE", "19");//开始生产日期
	            	map.put("BAR_CODE", "20");//条码
	            	map.put("BAR_CODE_OLD", "21");//老条码
	            	map.put("U_CMP_CODE", "22");//财务对照码
	            	map.put("REMARK", "23");//备注
	        	}
	        	String[] a = new String[]{"1"};
	        	alist.add(map);
	            List list = ExcelUtil.readExcelbyModel(fileName, path, 1, alist, a);
	            productService.txImportExcel(list, userBean,DRPFLAG);
	            jsonResult = jsonResult(true, "上传成功"); 
	        } catch (ServiceException e) {
	            jsonResult = jsonResult(false, e.getMessage());
	        } catch (Exception e) {
	            logger.error(e);
	            e.printStackTrace();
	            jsonResult = jsonResult(false, "Execl解析失败." + e.getMessage());
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	 }
	 /**
	  * 导出模版
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	      //总部分销标记，为0时总部，为1时渠道
        	String DRPFLAG=ParamUtil.utf8Decoder(request, "DRPFLAG");
        	String tmpContentCn;
        	String tmpContent;
        	if("0".equals(DRPFLAG)){
        		 //excel数据上列显示的列明
    	         tmpContentCn="货品编号,货品名称(必填),规格型号,品牌(必填),标准单位(必填),供货价(必填),颜色,默认计量单位,换算关系,货品分类编号,货品分类名称,体积,长度,宽度,高度,零售价,最低零售价格,是否赠品(是/否),是否扫码(是/否),开始生产日期(yyyy(年)-mm(月)-dd(日)),条码,老条码,财务对照码,备注";
    	         tmpContent="PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,PRVD_PRICE,PRD_COLOR,MEAS_UNIT,RATIO,PAR_PRD_NO,PAR_PRD_NAME,VOLUME,LENGTH,WIDTH,HEIGHT,FACT_PRICE,RET_PRICE_MIN,IS_CAN_FREE_FLAG,IS_SCAN,BEG_DATE,BAR_CODE,BAR_CODE_OLD,U_CMP_CODE,REMARK";
        	}else{
        		 //excel数据上列显示的列明
    	         tmpContentCn="货品编号,货品名称(必填),货品分类编号(必填),货品分类名称(必填),规格型号(必填),标准单位(必填),采购价(必填),销售价(必填),备注";
    	         tmpContent="PRD_NO,PRD_NAME,PAR_PRD_NO,PAR_PRD_NAME,PRD_SPEC,STD_UNIT,PRVD_PRICE,FACT_PRICE,REMARK";
        	}
        	try {
				doExport(response, new ArrayList(), "货品模版", tmpContent, tmpContentCn,"");
			} catch (Exception e) {
				e.printStackTrace();
			}	
	    }
	 
	 /**
	  * 更新是否扫码
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	public void updateScan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			UserBean userBean = ParamUtil.getUserBean(request);
			String IS_SCAN =  ParamUtil.get(request, "IS_SCAN");
			String PRD_ID =  ParamUtil.get(request, "PRD_ID");
			Map<String,String>params = new HashMap<String,String>();
			params.put("IS_SCAN", IS_SCAN);
			params.put("PRD_ID", PRD_ID);
			params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称
	        params.put("UPD_TIME", "sysdate");//更新时间
            //0 表示  '是 ' 1 表示  '否'
			productService.txUpdateScan(params);
			jsonResult = jsonResult(true, "操作成功");//
		} catch (ServiceException e) {
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			jsonResult = jsonResult(false, "操作失败" + e.getMessage());
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
	public  void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn,String type) throws Exception{
		//生成excel
		HSSFWorkbook workbook =new HSSFWorkbook();
		if("temp".equals(type)){
			 workbook = tempPrintExcel(tmpContentCn);
		}else{
			 workbook = printExcel(tmpContent,tmpContentCn,dataList);
		}
        //导出excel
        writeExecl(response,workbook,execlName,type);
	}
	/**
	 * Prints the excel.
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
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
	 * 
	 * @param titles_CN the titles_ cn
	 * @param sheet the sheet
	 */
	private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
	   // 根据你数据里面的记录有多少列，就设置多少列
	  for(int i=0;i<titles_CN.length;i++){
		  sheet.setColumnWidth((short)i, (short) 6000);
	          
	  }

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
	 * Write execl.
	 * 
	 * @param response the response
	 * @param workbook the workbook
	 * @param execlName the execl name
	 */
	public  void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName,String type) {
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
			if("temp".equals(type)){
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + ".xls");
			}else{
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-dd") + ".xls");
			}
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
	 * 
	 * @param wb the wb
	 * 
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
	private  HSSFWorkbook tempPrintExcel(String tmpContentCn){
		HSSFWorkbook workbook = null;
	     String[] titles_CN = tmpContentCn.split(",");
	     try{
	          //创建工作簿实例 
	           workbook = new HSSFWorkbook();
	          //创建工作表实例 
	         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
	          //设置列宽 
	          setSheetColumnWidth(titles_CN,sheet);
	        //获取样式 
	          HSSFCellStyle style = createTitleStyle(workbook); 
	               //创建第一行标题 
	                HSSFRow row = sheet.createRow((short)0);// 建立新行
	                row.setHeight((short) 0);
	                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
	                HSSFRow rows = sheet.createRow((short)1);// 建立新行
	                for(int i=0;i<titles_CN.length;i++){
	                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
	                       titles_CN[i]);
	                }
	  }catch(Exception e){
	              e.printStackTrace();
	  }
	 return workbook;
	}
}
