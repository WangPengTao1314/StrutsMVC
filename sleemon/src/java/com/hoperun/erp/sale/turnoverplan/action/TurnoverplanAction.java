package com.hoperun.erp.sale.turnoverplan.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.erp.sale.turnoverplan.service.TurnoverplanService;
import com.hoperun.sys.model.UserBean;
/**
 * 制定交付计划单
 * @author zhang_zhongbin
 *
 */
public class TurnoverplanAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(TurnoverplanAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0011501";
	private static String PVG_EDIT = "BS0011502";
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";

	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";

	private TurnoverplanService turnoverplanService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}

	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("");
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		
		
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
		
		String STATE = ParamUtil.get(request, "STATE");

		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("XTYHID", userBean.getXTYHID());
		params.put("INIT", "init");

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		 
		if(StringUtil.isEmpty(STATE)){
			strQx.append(" and u.STATE in ('"); //'审核通过'
			strQx.append(BusinessConsts.PASS+"','"); //'审核通过'
//			strQx.append(BusinessConsts.HAVE_CHECK_PRICE+"','"); //'已核价'
//			strQx.append(BusinessConsts.UN_HAVE_CHECK_PRICE+"','"); //'未核价'
			strQx.append(BusinessConsts._BACK+"')"); //'退回'
		}else{
			params.put("STATE", STATE);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
	    
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		
		//渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_DATE", "ASC");

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = turnoverplanService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_IDS = ParamUtil.get(request, "SALE_ORDER_IDS");
		if (!StringUtil.isEmpty(SALE_ORDER_IDS)) {
			List<SaleorderspModelChld> result = turnoverplanService
					.childQuery(SALE_ORDER_IDS);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}

    
    
    /**
     * 编辑.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        String parentData = ParamUtil.get(request, "parentData");
        String childData = ParamUtil.get(request, "childData");
        
        TurnoverplanModel model = new TurnoverplanModel();
        List<TurnoverplanChildModel> childList = new ArrayList<TurnoverplanChildModel>();
        
    	try {
    		  
            if (StringUtils.isNotEmpty(parentData)) {
                model = new Gson().fromJson(parentData, new TypeToken <TurnoverplanModel>() {
                }.getType());
            }
            
            if (StringUtils.isNotEmpty(childData)) {
            	childList = new Gson().fromJson(childData, new TypeToken <List<TurnoverplanChildModel>>() {
                }.getType());
            }
            
    		turnoverplanService.txEdit(model,childList, userBean);
    		
    		jsonResult = jsonResult(true, "操作成功");
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    
    /**
     * 判断所选订单的发货方是否是同一个区域服务中心
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void justOnlyAreaSer(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
    	try {
    		String SALE_ORDER_IDS = ParamUtil.get(request, "SALE_ORDER_IDS");
    		boolean b = turnoverplanService.justOnlyAreaSer(SALE_ORDER_IDS);
    		if(!b){
    			jsonResult = jsonResult(true, "false");
    		}
    		
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "操作失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
     * 退回
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toReturn(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
    	try {
    		String SALE_ORDER_IDS = ParamUtil.get(request, "SALE_ORDER_IDS");
    		Map<String,String> map=new HashMap<String, String>();
    		map.put("SALE_ORDER_IDS", SALE_ORDER_IDS);
    		map.put("STATE", BusinessConsts.UNCOMMIT);
    		map.put("UPDATOR", userBean.getRYXXID());
    		map.put("UPD_NAME", userBean.getXM());
    		map.put("UPD_TIME", "数据库时间");
    		turnoverplanService.upStart(map);
    		jsonResult = jsonResult(true, "退回成功");
        } catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception s){
        	s.printStackTrace();
        	jsonResult = jsonResult(false, "操作失败");
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
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		
		
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
		
		String STATE = ParamUtil.get(request, "STATE");

		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("XTYHID", userBean.getXTYHID());
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(STATE)){
			strQx.append(" and u.STATE in ('"); //'审核通过'
			strQx.append(BusinessConsts.PASS+"','"); //'审核通过'
//			strQx.append(BusinessConsts.HAVE_CHECK_PRICE+"','"); //'已核价'
//			strQx.append(BusinessConsts.UN_HAVE_CHECK_PRICE+"','"); //'未核价'
			strQx.append(BusinessConsts._BACK+"')"); //'退回'
		}else{
			params.put("STATE", STATE);
		}
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		//区域分管
		params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        List list=turnoverplanService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="订单编号,订单类型,订货日期,订货方编号,订货方名称,收货方编号,收货方名称,所属区域,所属区域服务中心,生产基地,省份,联系人,联系方式,容积(立方),状态,是否抵库,赠品,订单编号,货品编号,货品名称,规格型号,特殊规格说明,是否非标,折后单价,订货数量,已排车,未排车,未发体积(立方),备注";
        String tmpContent="SALE_ORDER_NO,BILL_TYPE,ORDER_DATE,ORDER_CHANN_NO,ORDER_CHANN_NAME,SEND_CHANN_NO,SEND_CHANN_NAME,AREA_NO,AREA_NAME,SHIP_POINT_NAME,PROV,PERSON_CON,TEL,TOTL_AMOUNT,STATE,IS_BACKUP_FLAG,IS_FREE_FLAG,GOODS_ORDER_NO,PRD_NO,PRD_NAME,PRD_SPEC,SPCL_SPEC_REMARK,IS_NO_STAND_FLAG,DECT_PRICE,ORDER_NUM,PLANED_NUM,RES_NUM,RES_VOL,REMARK";
        try {
			ExcelUtil.doExport(response, list, "制定交付计划", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    
	/**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,
				PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	public TurnoverplanService getTurnoverplanService() {
		return turnoverplanService;
	}

	public void setTurnoverplanService(TurnoverplanService turnoverplanService) {
		this.turnoverplanService = turnoverplanService;
	}

}
