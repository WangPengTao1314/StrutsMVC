package com.hoperun.drp.sale.forecasttaskup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;
import com.hoperun.drp.sale.forecasttaskup.service.ForecastTaskupService;
import com.hoperun.sys.model.UserBean;

public class ForecastTaskupServiceImpl extends BaseService implements ForecastTaskupService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("ForecastTaskup.pageQuery",
				"ForecastTaskup.pageCount", params, pageNo);
	}
	
	/**
	 * 查询填报的货品
	 * @param params
	 * @return
	 */
	public List<Map<String,String>> queryPrd(Map<String,String> params){
		return this.findList("ForecastTaskup.queryJobPrd", params);
	}
	
	
	/**
	 * 保存
	 * @param ADVC_RPT_JOB_ID
	 * @param chidList
	 */
	public void txEdit(String RPT_JOB_CHANN_ID, Map<String,String> entry,
			List<AdvcRptChannDtl> chidList,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("CHANN_ID", userBean.getCHANN_ID());
//		params.put("ADVC_RPT_JOB_ID", RPT_JOB_CHANN_ID);
//		Map<String,String> rptChann = (Map<String, String>) this.load("ForecastTaskup.queryJobChann", params);
//	    RPT_JOB_CHANN_ID = rptChann.get("RPT_JOB_CHANN_ID");
//	    if("未填报".equals(rptChann.get("STATE"))){
//	    	 this.update("ForecastTaskup.updateRptChannState", rptChann);
//	    }
	    Map<String,String>chann = (Map<String, String>) this.load("chann.loadById", params);
		List<Map<String,String>> addList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> updateList = new ArrayList<Map<String,String>>();
		for(AdvcRptChannDtl model : chidList){
			if(!StringUtil.isEmpty(model.getPRD_NO())) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("CHANN_NO", userBean.getCHANN_NO());
				param.put("PRD_NO", model.getPRD_NO());
				param.put("YEAR", entry.get("YEAR"));
				param.put("MONTH",entry.get("MONTH"));
				if(StringUtil.isEmpty(param.get("YEAR"))||StringUtil.isEmpty(param.get("MONTH"))){
					throw new ServiceException("上报年份月份为空，请联系管理员 !");
				}
				List<String> li = this.findList("ForecastTaskup.queryDtlId", param);
				String ADVC_RPT_CHANN_DTL_ID = "";
				if(li.size() != 0){
                   ADVC_RPT_CHANN_DTL_ID = String.valueOf(li.get(0));
				}
				//String ADVC_RPT_CHANN_DTL_ID = ""; //model.getADVC_RPT_CHANN_DTL_ID();
				Map<String,String> map = new HashMap<String,String>();
				map.put("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
				map.put("YEAR", entry.get("YEAR"));
				map.put("MONTH", entry.get("MONTH"));
				map.put("SHIP_POINT_ID", chann.get("SHIP_POINT_ID"));
				map.put("SHIP_POINT_NAME",chann.get("SHIP_POINT_NAME"));
				
				map.put("CHANN_ID", userBean.getCHANN_ID());
				map.put("CHANN_NO", userBean.getCHANN_NO()); 
				map.put("CHANN_NAME", userBean.getCHANN_NAME());
				map.put("PRD_ID", model.getPRD_ID());
				map.put("PRD_ID", model.getPRD_ID());
				map.put("PRD_NO", model.getPRD_NO());
				map.put("PRD_NAME", model.getPRD_NAME());
				map.put("PRD_SPEC", model.getPRD_SPEC());
				map.put("BRAND", model.getBRAND());
				map.put("PAR_PRD_NAME", model.getPAR_PRD_NAME());
				map.put("PRVD_PRICE", model.getPRVD_PRICE());
				map.put("ADVC_RPT_NUM", model.getADVC_RPT_NUM());
				map.put("ADVC_RPT_AMOUNT", model.getADVC_RPT_AMOUNT());
				if(StringUtil.isEmpty(ADVC_RPT_CHANN_DTL_ID)){
					ADVC_RPT_CHANN_DTL_ID = StringUtil.uuid32len();
					map.put("ADVC_RPT_CHANN_DTL_ID",ADVC_RPT_CHANN_DTL_ID);
					map.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
					map.put("PRD_ID",  model.getPRD_ID());
					addList.add(map);
				}else{
					map.put("ADVC_RPT_CHANN_DTL_ID",ADVC_RPT_CHANN_DTL_ID);
					updateList.add(map);
				}
			}
		}
		if(!addList.isEmpty()){
			this.batch4Update("ForecastTaskup.insertRptChannDtl", addList);
		}
		if(!updateList.isEmpty()){
			this.batch4Update("ForecastTaskup.updateRptChannDtl", updateList);
		}
		
	}
	
	public void txCommit(String RPT_JOB_CHANN_ID,UserBean userBean){
		Map<String,String> map = new HashMap<String,String>();
		map.put("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
		map.put("STATE", "提交");
		map.put("SUBMIT_ID",   userBean.getXTYHID());//提交人ID
        map.put("SUBMIT_NAME", userBean.getXM());    //提交人姓名     
        map.put("SUBMIT_TIME", BusinessConsts.UPDATE_TIME);  //提交时间
		this.update("ForecastTaskup.updateRptChann", map);
	}

	public void txRevoke(String RPT_JOB_CHANN_ID){
		Map<String,String> map = new HashMap<String,String>();
		map.put("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
		map.put("STATE", "撤销");
		this.update("ForecastTaskup.updateRptChann", map);
	}
	
	public Map<String,String> load(String RPT_JOB_CHANN_ID){
		return (Map<String, String>) this.load("ForecastTaskup.loadById", RPT_JOB_CHANN_ID);
	}
	public String getData(){
		return (String) this.load("ForecastTaskup.getDate","");
	}
}
