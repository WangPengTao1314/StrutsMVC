package com.hoperun.commons.techorder.action;

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
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.techorder.model.TechorderManagerModelChld;
import com.hoperun.commons.techorder.service.TechorderManagerService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
/**
 * 订货特殊工艺单共同
 * @author liu_yuegang
 *
 */
public class TechorderManagerAction extends BaseAction {
	/** 订货特殊工艺业务service*/
	private TechorderManagerService techorderManagerService;

	/**
	 * @return the techorderManagerService
	 */
	public TechorderManagerService getTechorderManagerService() {
		return techorderManagerService;
	}

	/**
	 * @param techorderManagerService the techorderManagerService to set
	 */
	public void setTechorderManagerService(
			TechorderManagerService techorderManagerService) {
		this.techorderManagerService = techorderManagerService;
	}
	/**
	 * 查看页面 不可编辑 显示价格
	 * 需要参数  
	 * 			特殊工艺主表id(SPCL_TECH_ID)  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewTechWithPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//订单特殊工艺ID
		String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
		//-- 0156143--Start--刘曰刚--2014-06-17//
		//修改特殊工艺获取价格方式
		Map<String,String> prdMap=new HashMap<String,String>();
		List<Map<String,String>> prdList=new ArrayList<Map<String,String>>();
		String PRICE=ParamUtil.get(request, "PRICE");
		Map<String,String> queryMap=new HashMap<String, String>();
		queryMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
		queryMap.put("PRICE", PRICE);
		prdMap=techorderManagerService.getViewSpclById(queryMap);
		//-- 0156143--End--刘曰刚--2014-06-17//
		//按特殊工艺单id查找货品
		String PRD_ID=techorderManagerService.getPrdId(SPCL_TECH_ID);
		Map<String,String> map=new HashMap<String,String>();
		map.put("PRD_ID", PRD_ID);
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		prdList=techorderManagerService.getSpclById(map);
		request.setAttribute("rst", prdMap);
		request.setAttribute("list", prdList);
		request.setAttribute("SPCL_TECH_ID", SPCL_TECH_ID);
		request.setAttribute("saleFlag", ParamUtil.get(request, "saleFlag"));
		request.setAttribute("NEW_SPEC", ParamUtil.get(request, "NEW_SPEC"));
		request.setAttribute("NEW_COLOR", ParamUtil.get(request, "NEW_COLOR"));
		request.setAttribute("PRODUCT_REMARK", ParamUtil.get(request, "PRODUCT_REMARK"));
		request.setAttribute("ORDERID", ParamUtil.get(request, "ORDERID"));
		request.setAttribute("editTable", ParamUtil.get(request, "editTable"));
		
		//显示价格
		request.setAttribute("showPrice", "show");
		request.setAttribute("type", "view");
		return mapping.findForward("toEdit");
    }
	
	/**
	 * 查看页面 不可编辑 不显示价格
	 * 需要参数  
	 * 			特殊工艺主表id(SPCL_TECH_ID)  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewTechWithOutPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//订单特殊工艺ID
		String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
		//-- 0156143--Start--刘曰刚--2014-06-17//
		//修改特殊工艺获取价格方式
		Map<String,String> prdMap=new HashMap<String,String>();
		List<Map<String,String>> prdList=new ArrayList<Map<String,String>>();
		Map<String,String> queryMap=new HashMap<String, String>();
		queryMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
		queryMap.put("PRICE", "");
		prdMap=techorderManagerService.getViewSpclById(queryMap);
		//-- 0156143--End--刘曰刚--2014-06-17//
		//按特殊工艺单id查找货品
		String PRD_ID=techorderManagerService.getPrdId(SPCL_TECH_ID);
		Map<String,String> map=new HashMap<String,String>();
		map.put("PRD_ID", PRD_ID);
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		prdList=techorderManagerService.getSpclById(map);
		request.setAttribute("rst", prdMap);
		request.setAttribute("list", prdList);
		//隐藏价格
		request.setAttribute("showPrice", "hide");
		request.setAttribute("type", "view");
		return mapping.findForward("toEdit");
    }
	/**
	 * 新增/修改页面 可编辑 计算价格
	 * 需要参数  
	 * 		特殊工艺主表id(SPCL_TECH_ID)  
	 * 		货品id(PRD_ID) 
	 * 		渠道id(CHANN_ID)
	 * 		是否反填使用标记(USE_FLAG)
	 * 		需要反填的表明(TABLE)
	 * 		需要反填的ID(BUSSID)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editTechWithPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//订单特殊工艺ID
		String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
		//货品id
		String PRD_ID=ParamUtil.get(request, "PRD_ID");
		//渠道id
		String CHANN_ID=ParamUtil.get(request, "CHANN_ID");
		//使用标记
		String USE_FLAG=ParamUtil.get(request, "USE_FLAG");
		//是否保存id（只有从历史记录过来的特殊工艺才会有该字段）
		String addFlag=ParamUtil.get(request, "addFlag");
		if("undefined".equals(addFlag)){
			addFlag="";
		}
		//需要反填的表
		String TABLE =ParamUtil.get(request, "TABLE");
		//需要反填的id
		String BUSSID=ParamUtil.get(request, "BUSSID");
		//单价
		String PRICE=ParamUtil.get(request, "PRICE");
		
		//add by zzb 总部的人员修改特殊工艺不做下面的判断
		UserBean userBean = ParamUtil.getUserBean(request);
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		
		//-- 0156143--Start--刘曰刚--2014-06-16//
		String DECT_RATE=LogicUtil.getChannDsct(CHANN_ID);
		if(StringUtil.isEmpty(DECT_RATE) && "1".equals(IS_DRP_LEDGER)){
			request.setAttribute("error", "总部未设置您的货品价格，请联系总部！");
			return mapping.findForward("toEdit");
		}
		//如果订单特殊工艺id为空时，说明第一次打开特殊工艺页面，所以查询货品特殊工艺表
		//不为空时 查询订单特殊工艺表
		Map<String,String> prdMap=new HashMap<String,String>();
		List<Map<String,String>> prdList=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("PRD_ID", PRD_ID);
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		map.put("PRICE", PRICE);
		map.put("ADDFLAG", addFlag);
		//-- 0156143--Start--刘曰刚--2014-06-17//
		if(StringUtil.isEmpty(SPCL_TECH_ID)){
			prdMap=techorderManagerService.getPrdInfoById(map);
		}else{
			prdMap=techorderManagerService.getSpclInfoById(map);
		}
		
		prdList=techorderManagerService.getSpclByIdNotUSE_FLAG(map);
		//-- 0156143--End--刘曰刚--2014-06-17//
		request.setAttribute("rst", prdMap);
		request.setAttribute("list", prdList);
		request.setAttribute("PRD_ID", PRD_ID);
		request.setAttribute("USE_FLAG", USE_FLAG);
		//显示价格
		request.setAttribute("showPrice", "show");
		request.setAttribute("TABLE", TABLE);
		request.setAttribute("BUSSID", BUSSID);
		request.setAttribute("type", "edit");
		request.setAttribute("addFlag", addFlag);
		request.setAttribute("IS_NO_SPCL_FLAG", Consts.IS_NO_SPCL_FLAG);
    	return mapping.findForward("toEdit");
    }
	/**
	 * 新增/修改页面 可编辑 不显示价格
	 * 需要参数  
	 * 		特殊工艺主表id(SPCL_TECH_ID)  
	 * 		货品id(PRD_ID) 
	 * 		渠道id(CHANN_ID)
	 * 		是否反填使用标记(USE_FLAG)
	 * 		是否验证渠道折扣率(check)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editTechWithOutPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//订单特殊工艺ID
		String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
		//货品id
		String PRD_ID=ParamUtil.get(request, "PRD_ID");
		//是否保存id（只有从历史记录过来的特殊工艺才会有该字段）
		String addFlag=ParamUtil.get(request, "addFlag");
		if("undefined".equals(addFlag)){
			addFlag="";
		}
		//渠道id
		String CHANN_ID=ParamUtil.get(request, "CHANN_ID");
		//为1时，不可编辑，可以选择历史记录
		String optOldFlag=ParamUtil.get(request, "optOldFlag");
		//使用标记
		String USE_FLAG=ParamUtil.get(request, "USE_FLAG");
		//需要反填的表
		String TABLE =ParamUtil.get(request, "TABLE");
		//需要反填的id
		String BUSSID=ParamUtil.get(request, "BUSSID");
		//是否验证渠道折扣率
		String check=ParamUtil.get(request, "check");
		//单价
		String PRICE=ParamUtil.get(request, "PRICE");
		//-- 0156143--Start--刘曰刚--2014-06-16//
		//-- 0156143--Start--刘曰刚--2014-06-16//
		//渠道折扣率根据明细折扣率查询
		String DECT_RATE=LogicUtil.getChannDsct(CHANN_ID);
		//如果渠道没有折扣率又要检查渠道折扣率，则提示error并且自动关闭特殊工艺页面
		if(StringUtil.isEmpty(DECT_RATE)&&!"N".equals(check)){
			request.setAttribute("error", "总部未设置您的货品价格，请联系总部！");
			return mapping.findForward("toEdit");
		}
		//如果渠道没有折扣率又不要检查渠道折扣率，则给渠道折扣率默认为0
		if(StringUtil.isEmpty(DECT_RATE)&&"N".equals(check)){
			DECT_RATE="0";
		}
		//如果订单特殊工艺id为空时，说明第一次打开特殊工艺页面，所以查询货品特殊工艺表
		//不为空时 查询订单特殊工艺表
		Map<String,String> map=new HashMap<String,String>();
		map.put("PRD_ID", PRD_ID);
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		//-- 0156143--Start--刘曰刚--2014-06-17//
		//修改特殊工艺获取价格方式
		
		map.put("PRICE", PRICE);
		map.put("ADDFLAG", addFlag);
		Map<String,String> prdMap=new HashMap<String, String>();
		List<Map<String,String>> prdList=new ArrayList<Map<String,String>>();
		if(StringUtil.isEmpty(SPCL_TECH_ID)){
			prdMap=techorderManagerService.getPrdInfoById(map);
		}else{
			prdMap=techorderManagerService.getSpclInfoById(map);
		}
		prdList=techorderManagerService.getSpclByIdNotUSE_FLAG(map);
		request.setAttribute("rst", prdMap);
		request.setAttribute("list", prdList);
		request.setAttribute("PRD_ID", PRD_ID);
		request.setAttribute("DECT_RATE",DECT_RATE);
		//-- 0156143--End--刘曰刚--2014-06-17//
		//隐藏价格
		request.setAttribute("showPrice", "hide");
		request.setAttribute("USE_FLAG", USE_FLAG);
		request.setAttribute("TABLE", TABLE);
		request.setAttribute("BUSSID", BUSSID);
		request.setAttribute("type", "edit");
		request.setAttribute("addFlag", addFlag);
		System.out.println(optOldFlag);
		request.setAttribute("optOldFlag", optOldFlag);
		request.setAttribute("IS_NO_SPCL_FLAG", Consts.IS_NO_SPCL_FLAG);
		//-- 0156143--End--刘曰刚--2014-06-16//
    	return mapping.findForward("toEdit");
    }
	
	/**
	 * * 工艺单 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
			//尺寸调整json
            String adjustSizeData = ParamUtil.get(request, "adjustSizeData");
            //部件替换json
            String unitReplaceData = ParamUtil.get(request, "unitReplaceData");
            //部件新增json
            String addUnitData = ParamUtil.get(request, "addUnitData");
            //说明json
            String REMARKDate = ParamUtil.get(request, "REMARKDate");
            //一般特殊工艺描述
            String SAMEREMARK=ParamUtil.get(request, "SAMEREMARK");
            //货品id
            String PRD_ID=ParamUtil.get(request, "PRD_ID");
            //订单特殊工艺ID
            String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
            //基准价
            String BASE_PRICE=ParamUtil.get(request, "BASE_PRICE");
            //折扣率
            String DECT_RATE=ParamUtil.get(request, "DECT_RATE");
            //使用标记
            String USE_FLAG=ParamUtil.get(request, "USE_FLAG");
            
          //是否保存id（只有从历史记录过来的特殊工艺才会有该字段）
    		String addFlag=ParamUtil.get(request, "addFlag");
            String TABLE=ParamUtil.get(request, "TABLE");
            String BUSSID=ParamUtil.get(request, "BUSSID");
            List <TechorderManagerModelChld> adjustSizeList = null;
            List <TechorderManagerModelChld> unitReplaceList = null;
            List <TechorderManagerModelChld> addUnitList = null;
            List <TechorderManagerModelChld> REMARKList = null;
            List <TechorderManagerModelChld> SAMEREMARKList = null;
            if (!StringUtil.isEmpty(adjustSizeData)) {
            	adjustSizeList = new Gson().fromJson(adjustSizeData, new TypeToken <List <TechorderManagerModelChld>>() {
                }.getType());
            }
            if (!StringUtil.isEmpty(unitReplaceData)) {
            	unitReplaceList = new Gson().fromJson(unitReplaceData, new TypeToken <List <TechorderManagerModelChld>>() {
                }.getType());
            }
            if (!StringUtil.isEmpty(adjustSizeData)) {
            	addUnitList = new Gson().fromJson(addUnitData, new TypeToken <List <TechorderManagerModelChld>>() {
                }.getType());
            }
            if (!StringUtil.isEmpty(adjustSizeData)) {
            	REMARKList = new Gson().fromJson(REMARKDate, new TypeToken <List <TechorderManagerModelChld>>() {
                }.getType());
            }
            if (!StringUtil.isEmpty(SAMEREMARK)) {
            	SAMEREMARKList = new Gson().fromJson(SAMEREMARK, new TypeToken <List <TechorderManagerModelChld>>() {
                }.getType());
            }
            //合并4个list
            List<TechorderManagerModelChld> list=new ArrayList<TechorderManagerModelChld>();
            list.addAll(adjustSizeList);
            list.addAll(unitReplaceList);
            list.addAll(addUnitList);
            list.addAll(REMARKList);
            list.addAll(SAMEREMARKList);
            Map<String,String> map=techorderManagerService.txEdit(SPCL_TECH_ID,list,PRD_ID,BASE_PRICE,DECT_RATE,USE_FLAG,TABLE,BUSSID,addFlag,userBean);
            jsonResult = new Gson().toJson(new Result(true, map, ""));
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
	 * 拼接按当前登录人帐套查询历史记录
	 * @param TABLE 所要查询的表
	 * @param LEDGER_ID 当前登录人所属帐套
	 * @param PRD_ID 货品id
	 * @return
	 */
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		String TABLE =ParamUtil.get(request, "TABLE");
		String PRD_ID =ParamUtil.get(request, "PRD_ID");
		params.put("TABLE", TABLE);
		params.put("PRD_ID", PRD_ID);
		ParamUtil.putStr2Map(request, "SPCL_TECH_ID", params);//特殊工艺id
		ParamUtil.putStr2Map(request, "CHANN_ID", params);//渠道id
		ParamUtil.putStr2Map(request, "USE_FLAG", params);//是否使用标记
		ParamUtil.putStr2Map(request, "BUSSID", params);//单据主表id
		ParamUtil.putStr2Map(request, "check", params);//是否验证渠道折扣率
		ParamUtil.putStr2Map(request, "citeUrl", params);//跳转action
		ParamUtil.putStr2Map(request, "PRICE", params);//单价
		ParamUtil.putStr2Map(request, "optOldFlag", params);//是否显示历史记录
//		ParamUtil.putStr2Map(request, "filterSpclFlag", params);//是否过滤非标特殊工艺
		params.put("filterSpclFlag", Consts.IS_NO_SPCL_FLAG);//是否过滤非标特殊工艺
		request.setAttribute("params", params);
		//判断当前登录人是总部还是分销商，如果是分销商，则显示历史记录按钮，不然则隐藏
		String flag;
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			flag="show";
		}else{
			flag="hidden";
		}
		request.setAttribute("flag", flag);
		return mapping.findForward("toFrame");
	}
	//查看历史记录
	public ActionForward toHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> params=new HashMap<String, String>();
		params.put("DM_SPCL_TECH_NO",ParamUtil.get(request, "DM_SPCL_TECH_NO"));
		params.put("PRD_ID",ParamUtil.get(request, "PRD_ID"));
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		params.put("CRE_TIME_BEGIN", ParamUtil.get(request, "CRE_TIME_BEGIN"));
		params.put("CRE_TIME_END", ParamUtil.get(request, "CRE_TIME_END"));
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		params.put("SPCL_DTL_REMARK", ParamUtil.get(request, "SPCL_DTL_REMARK"));
		if("0".equals(ParamUtil.get(request, "filterSpclFlag"))){
			params.put("SPCL_TECH_FLAG", " SPCL_TECH_FLAG <2 ");
		}
		// 字段排序
		ParamUtil.setOrderField(request, params, "a.CRE_TIME,a.DM_SPCL_TECH_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = techorderManagerService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("toHistoryList");
	}
	public void editSaleChld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		Map<String,String> params=new HashMap<String, String>();
		params.put("NEW_SPEC",ParamUtil.get(request, "NEW_SPEC"));
		params.put("NEW_COLOR",ParamUtil.get(request, "NEW_COLOR"));
		params.put("PRODUCT_REMARK",ParamUtil.get(request, "PRODUCT_REMARK"));
		params.put("ORDERID",ParamUtil.get(request, "ORDERID"));
		params.put("editTable",ParamUtil.get(request, "editTable"));
		try {
			if (techorderManagerService.txUpdateSaleChld(params)) {
				jsonResult = new Gson().toJson(new Result(true, params, ""));
			}else{
				jsonResult = jsonResult(false, "保存失败");
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
}
