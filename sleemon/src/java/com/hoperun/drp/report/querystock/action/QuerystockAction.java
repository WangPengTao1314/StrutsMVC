package com.hoperun.drp.report.querystock.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.querystock.service.QuerystockService;

/**
 * 库存查询
 * @author zhu_changxia
 */

public class QuerystockAction extends BaseAction {

	
	private  QuerystockService  querystockService;
	
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取参数
		Map params = new HashMap();
    	params.put("CustNo", ParamUtil.utf8Decoder(request, "CustNo"));     //客户编号
    	params.put("PrdNo", ParamUtil.utf8Decoder(request, "PrdNo"));       //商品编号
    	params.put("OrgCode",ParamUtil.utf8Decoder(request, "OrgCode"));    //组织编码
		
    	request.setAttribute("CustNo", ParamUtil.utf8Decoder(request, "CustNo"));
    	request.setAttribute("PrdNo",ParamUtil.utf8Decoder(request, "PrdNo"));
    	request.setAttribute("OrgCode", ParamUtil.utf8Decoder(request, "OrgCode"));
    	
    	Map<String,Object> page = new HashMap<String,Object>();
    	
    	//调用服务
		List rst = querystockService.queryStoreAcct(params);
		
		String flag    = ParamUtil.get(request, "flag");
		
		page.put("result", rst);
		
		//检查服务调用结果
 
		if(rst!=null && rst.size() == 1){
			Map temp = (Map) rst.get(0);
			String status =  (String) temp.get("Status");
			String showMsg = (String) temp.get("Desc");
			
			if(!StringUtil.isEmpty(status) && "false".equalsIgnoreCase(status)){
				page.put("errorMsg", showMsg);
			}
		}
		Float sumSotreNum = 0.0f;
		Float sumTotalVolume = 0.0f;
		for(int i=0;i<rst.size();i++){
			HashMap queryMap = (HashMap)rst.get(i);
			String StoreNum =(String)queryMap.get("StoreNum");
			Float i_StoreNum = Float.parseFloat(StoreNum); 
			sumSotreNum +=i_StoreNum;
			String Total_Volume  = (String)queryMap.get("Total_Volume");
			Float i_Total_Volume = Float.parseFloat(Total_Volume); 
			sumTotalVolume +=i_Total_Volume;
		}
		request.setAttribute("SUM_STORE_NUM", sumSotreNum);
		request.setAttribute("SUM_TOTAL_VOLUME", sumTotalVolume);
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		request.setAttribute("flag", flag); 
		return mapping.findForward("list");
	}
	
	
	public ActionForward toListT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("list");
	}
	

	public QuerystockService getQuerystockService() {
		return querystockService;
	}

	public void setQuerystockService(QuerystockService querystockService) {
		this.querystockService = querystockService;
	}
	//导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		Map params = new HashMap();
    	params.put("CustNo", ParamUtil.utf8Decoder(request, "CustNo"));     //客户编号
    	params.put("PrdNo", ParamUtil.utf8Decoder(request, "PrdNo"));       //商品编号
    	params.put("OrgCode",ParamUtil.utf8Decoder(request, "OrgCode"));    //组织编码
    	//调用服务
		List rst = querystockService.queryStoreAcct(params);
        //excel数据上列显示的列明
        String tmpContentCn="商品编号,商品名称,规格型号,库房编号,库房名称,库存单价,库存数量,库存金额,供货价,单位体积,库存体积,客户编号,客户名称,送货详细地址,描述,帐套名称";
        //
        String tmpContent="PrdNo,PrdName,PrdSpec,StoreNo,StoreName,StorePrice,StoreNum,StoreAmount,PrvdPrice,Unit_Volume,Total_Volume,CustNo,CustName,DeliverAddr,Remark,LedgerName";
        try {
        	ExcelUtil.doExport(response, rst, "库存", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
