/**
 * prjName:喜临门营销平台
 * ucName:推广费用申请单维护
 * fileName:PromoexpenServiceImpl
*/
package com.hoperun.drp.oamg.promoexpen.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.promoexpen.model.PromoexpenModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.oamg.promoexpen.service.PromoexpenService;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-24 10:59:55
 */
public class PromoexpenServiceImpl extends BaseService implements PromoexpenService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Promoexpen.pageQuery", "Promoexpen.pageCount",params, pageNo);
	}
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuerySH(Map<String,String> params, int pageNo){
		return this.pageQuery("Promoexpen.pageQuerySH", "Promoexpen.pageCountSH",params, pageNo);
	}
	
	/**
     * 新增的时候 自动判断是第几次申请，如果不是首次数据项自动带出
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public Map<String,Object> queryJudgeModel(String CHANN_ID,String QUARTER,String YEAR){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_ID", CHANN_ID);
    	paramMap.put("QUARTER", QUARTER);
    	paramMap.put("YEAR", YEAR);
     	List<Map<String,Object>> list = this.findList("Promoexpen.selectBrothers", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
    
    /**
	  * @param CHANN_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
    public String queryJudgeModel1(String CHANN_ID, String QUARTER, String YEAR) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_ID", CHANN_ID);
		params.put("QUARTER", QUARTER);
		params.put("YEAR", YEAR);
		List<Map<String,Object>> list =  this.findList("Promoexpen.selectBrothers1", params);
        Map<String, Object> map1 = list.get(0);
        System.out.println(map1.get("YUPI"));
        if(map1.get("YUPI")!=null){
             return  String.valueOf(map1.get("YUPI").toString());
        }
        return null;
		
//		if(null != list && !list.isEmpty()){
//     		return String.valueOf(list.get(0)) ;
//     	}
//     	return null;
		//return (String) load("Promoexpen.selectBrothers1", params);
	}
    
    /**
	 * @param RELATE_AREA_ID	 * @param YEAR
	 * @return
	 */
	public String queryJudgeModel2(String RELATE_AREA_ID, String QUARTER,
			String YEAR) {
		if(!RELATE_AREA_ID.equals("") && !QUARTER.equals("") && !YEAR.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("RELATE_AREA_ID", RELATE_AREA_ID);
			QUARTER = getQuarter(QUARTER);
			params.put("QUARTER", QUARTER);
			params.put("YEAR", YEAR);
			List<Map<String,Object>>  list =  this.findList("Promoexpen.selectBrothers2", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("AYUSUAN"));
	        return map1.get("AYUSUAN").toString();
		} else{
			return null;
		}
		
//		if(null != list && !list.isEmpty()){
//     		return (String) list.get(0);
//     	}
//     	return null;
		//return (String) load("Promoexpen.selectBrothers2", params);
	}
	 
	 /**
	  * @param RELATE_AREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	public String queryJudgeModel3(String RELATE_AREA_ID, String QUARTER,
			String YEAR) {
		if(!RELATE_AREA_ID.equals("") && !QUARTER.equals("") && !YEAR.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("RELATE_AREA_ID", RELATE_AREA_ID);
			QUARTER = getQuarter(QUARTER);
			params.put("QUARTER", QUARTER);
			params.put("YEAR", YEAR);
			List<Map<String,Object>>  list =  this.findList("Promoexpen.selectBrothers3", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("AREAYUPI"));
	        return  map1.get("AREAYUPI").toString();
		} else {return null;
		}
//		if(null != list && !list.isEmpty()){
//     		return (String) list.get(0);
//     	}
//     	return null;
		//return (String) load("Promoexpen.selectBrothers3", params);
	}
	 
	/**
	  * @param WAREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel4(String WAREA_ID, String QUARTER, String YEAR) {
		if(!WAREA_ID.equals("") && !QUARTER.equals("") && !YEAR.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("WAREA_ID", WAREA_ID);
			QUARTER = getQuarter(QUARTER);
			params.put("QUARTER", QUARTER);
			params.put("YEAR", YEAR);
			List<Map<String,Object>>  list =  this.findList("Promoexpen.selectBrothers4", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("WAREYUSUAN"));
	        return  map1.get("WAREYUSUAN").toString();
		} else {
			return  null;
		}
//		if(null != list && !list.isEmpty()){
//     		return (String) list.get(0);
//     	}
//     	return null;
		//return (String) load("Promoexpen.selectBrothers4", params);
	}
	 
	 /**
	  * @param WAREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel5(String WAREA_ID, String QUARTER, String YEAR) {
		if(!WAREA_ID.equals("") && !QUARTER.equals("") && !YEAR.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("WAREA_ID", WAREA_ID);
			QUARTER = getQuarter(QUARTER);
			params.put("QUARTER", QUARTER);
			params.put("YEAR", YEAR);
			List<Map<String,Object>>  list =  this.findList("Promoexpen.selectBrothers5", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("WAREYUPI"));
	        return map1.get("WAREYUPI").toString();
		} else {
			return null;
		}
//		if(null != list && !list.isEmpty()){
//     		return (String) list.get(0);
//     	}
//     	return null;
		//return (String) load("Promoexpen.selectBrothers5", params);
	}
	 
	/**
	 * * 增加 *
	 * @param params
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Promoexpen.insert", params);
		return true;
	}
	
	/**
	 * @删除
	 * @param PRMT_COST_REQ_ID
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Promoexpen.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Promoexpen.updateById", params);
	}
	
	/**
	 * @param quarter
	 * @return
	 */
	public String getQuarter(String quarter) {
		if (quarter.equals("1")) {
			quarter = "一季度";
		}
		if (quarter.equals("2")) {
			quarter = "二季度";
		}
		if (quarter.equals("3")) {
			quarter = "三季度";
		}
		if (quarter.equals("4")) {
			quarter = "四季度";
		}
		return quarter;
	} 
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRMT_COST_REQ_ID
	 * @param PromoexpenModel
	 * @param userBean.
	 * @return true, if tx txEdit
	 */
	public void txEdit(String PRMT_COST_REQ_ID, PromoexpenModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("CHANN_NO",model.getCHANN_NO());
		    params.put("REQ_ID",  model.getREQ_ID());
		    params.put("REQ_NAME",model.getREQ_NAME());
		    params.put("REQ_DATE",model.getREQ_DATE());
		    params.put("CHANN_ID", model.getCHANN_ID());
		    params.put("CHANN_NO", model.getCHANN_NO());
		    params.put("CHANN_NAME",model.getCHANN_NAME());
		    params.put("CHANN_PERSON_CON", model.getCHANN_PERSON_CON());
		    params.put("CHANN_TEL", model.getCHANN_TEL());
		    params.put("AREA_ID", model.getAREA_ID());
		    params.put("AREA_NO", model.getAREA_NO());
		    params.put("AREA_NAME", model.getAREA_NAME());
		    params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());
		    params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());
		    params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL());
		    params.put("REQ_REMARK",model.getREQ_REMARK());
		    params.put("TOTAL_PRMT_COST",model.getTOTAL_PRMT_COST());
		    params.put("REIT_REQ_AMOUNT",model.getREIT_REQ_AMOUNT());
		    params.put("COST_TYPE",model.getCOST_TYPE());
		    params.put("TOTAL_SEND_AMOUNT",model.getTOTAL_SEND_AMOUNT());
		    params.put("TOTAL_BUDGET_AMOUNT",model.getTOTAL_BUDGET_AMOUNT());
		    params.put("TOTAL_SEND_COST_AMOUNT",model.getTOTAL_SEND_COST_AMOUNT());
		    params.put("TOTAL_SEND_BUDGET_AMOUNT",model.getTOTAL_SEND_BUDGET_AMOUNT());
		    params.put("REQ_COST_AMOUNT",model.getREQ_COST_AMOUNT());
		    params.put("TOTAL_SEND_LEFT_AMOUNT",model.getTOTAL_SEND_LEFT_AMOUNT());
		    params.put("TOTAL_BUDGET_LEFT_AMOUNT",model.getTOTAL_BUDGET_LEFT_AMOUNT());
		    params.put("SEND_COST_RATE",model.getSEND_COST_RATE());
		    params.put("SEND_BUDGET_RATE",model.getSEND_BUDGET_RATE());
		    params.put("TOTAL_AREA_SEND_AMOUNT",model.getTOTAL_AREA_SEND_AMOUNT());
		    params.put("TOTAL_AREA_BUDGET_AMOUNT",model.getTOTAL_AREA_BUDGET_AMOUNT());
		    params.put("TOTAL_AREA_SEND_COST_AMOUNT",model.getTOTAL_AREA_SEND_COST_AMOUNT());
		    params.put("TOTAL_AREA_SEND_BUDGET_AMOUNT",model.getTOTAL_AREA_SEND_BUDGET_AMOUNT());
		    params.put("AREA_REQ_COST_AMOUNT",model.getAREA_REQ_COST_AMOUNT());
		    params.put("TOTAL_AREA_SEND_LEFT_AMOUNT",model.getTOTAL_AREA_SEND_LEFT_AMOUNT());
		    params.put("TOTAL_AREA_BUDGET_LEFT_AMOUNT",model.getTOTAL_AREA_BUDGET_LEFT_AMOUNT());
		    params.put("AREA_SEND_COST_RATE",model.getAREA_SEND_COST_RATE());
		    params.put("AREA_SEND_BUDGET_RATE",model.getAREA_SEND_BUDGET_RATE());
		    params.put("STATE", model.getSTATE());
		    params.put("ZONE_ID", model.getZONE_ID());
		    params.put("ZONE_ADDR", model.getZONE_ADDR());
		    params.put("TOTAL_REAL_AMOUNT", model.getTOTAL_REAL_AMOUNT());
		    params.put("TOTAL_ADVC_AMOUNT", model.getTOTAL_ADVC_AMOUNT());
		    params.put("TOTAL_RATE", model.getTOTAL_RATE());
		    params.put("AREA_TOTAL_BUDGET_AMOUNT", model.getAREA_TOTAL_BUDGET_AMOUNT());
		    params.put("AREA_TOTAL_PRE_AMOUNT", model.getAREA_TOTAL_PRE_AMOUNT());
		    params.put("AREA_TOTAL_AVALIABLE_AMOUNT", model.getAREA_TOTAL_AVALIABLE_AMOUNT());
		    params.put("WARE_TOTAL_BUDGET_AMOUNT", model.getWARE_TOTAL_BUDGET_AMOUNT());
		    params.put("WARE_TOTAL_PRE_AMOUNT", model.getWARE_TOTAL_PRE_AMOUNT());
		    params.put("WARE_TOTAL_AVALIABLE_AMOUNT", model.getWARE_TOTAL_AVALIABLE_AMOUNT());
		    params.put("PRMT_PLAN_ID", model.getPRMT_PLAN_ID());
		    params.put("PRMT_PLAN_NO", model.getPRMT_PLAN_NO());
		    params.put("PRMT_PLAN_NAME", model.getPRMT_PLAN_NAME());
		    params.put("PRMT_TYPE", model.getPRMT_TYPE());
		    params.put("DGET_ITEM_ID", model.getDGET_ITEM_ID());
		    params.put("BUDGET_ITEM_ID", model.getBUDGET_ITEM_ID());
		    params.put("BUDGET_ITEM_NO", model.getBUDGET_ITEM_NO());
		    params.put("BUDGET_ITEM_NAME", model.getBUDGET_ITEM_NAME());
		    params.put("BUDGET_ITEM_TYPE", model.getBUDGET_ITEM_TYPE());
		    params.put("CITY_NAME", model.getCITY_NAME());
		    params.put("CITY_LVL", model.getCITY_LVL());
		    params.put("BUDGET_AMOUNT", model.getBUDGET_AMOUNT());
		    params.put("RETAIL_AMOUNT", model.getRETAIL_AMOUNT());
		    params.put("BILL_AMOUNT", model.getBILL_AMOUNT());
		    params.put("BUDGET_QUOTA_ID", model.getBUDGET_QUOTA_ID());
		    params.put("PRO_SCREEN", model.getPRO_SCREEN());
		    
		if(StringUtil.isEmpty(PRMT_COST_REQ_ID)){
			PRMT_COST_REQ_ID= StringUtil.uuid32len();
			params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
			params.put("PRMT_COST_REQ_NO", LogicUtil.getBmgz("ERP_PRMT_COST_REQ_NO"));
			params.put("REQ_MAKE", LogicUtil.getBmgz("REQ_MAKE_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    
		    txInsert(params);
		    
		    String att_path = loadAtt(PRMT_COST_REQ_ID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", PRMT_COST_REQ_ID);
			attParams.put("ATT_PATH",model.getACTION_PATH()+";"+model.getBUDGET_PATH()+";"+model.getAGREE_PATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
			txUpdateById(params);
			
			clearCaches(PRMT_COST_REQ_ID);
			
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",PRMT_COST_REQ_ID);
			attParamsT.put("ATT_PATH", model.getACTION_PATH()+";"+model.getBUDGET_PATH()+";"+model.getAGREE_PATH());//图片路径
			update("Att.updateAtt", attParamsT);
		}
		
		//删除附件列表后重新添加附件信息
		/*
		Map<String,String> fileParamMap = new HashMap<String,String>();
		fileParamMap.put("FROM_BILL_ID", PRMT_COST_REQ_ID);
		this.delete("Promoexpen.deleteFile", fileParamMap);
		
		List<String> pathList = parsePath(model.getSTATENEBTS_ATT());
	    if(pathList!=null){
	    	for(String path : pathList){
		    	fileParamMap.put("ATT_ID", StringUtil.uuid32len());
		    	fileParamMap.put("ATT_PATH", path);
		    	fileParamMap.put("FROM_BILL_ID", PRMT_COST_REQ_ID);
		    	fileParamMap.put("CREATOR", userBean.getYHBH());
		    	fileParamMap.put("CRE_NAME", userBean.getXM());
		    	fileParamMap.put("DEL_FLAG", "0");
		    	this.insert("Promoexpen.saveFile", fileParamMap);//批量插入附件信息
		    }
	    }*/
	}
	
	/**
	 * 加载上传文件.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param){		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_BILL_ID", param);
        return (String) load("Att.loadAtt", params);
	}
	
	//解析多文件路径
	private List<String> parsePath(String paths){
		List<String> pathList = new ArrayList<String>();
		if(StringUtil.isEmpty(paths)){
			return null;
		}else{
			String[] pathArr = paths.split(",");
			for(String path : pathArr){
				pathList.add(path);
			}
		}
		return pathList;
	}
	
	/**
	 * @详细
	 * @param param PRMT_COST_REQ_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Promoexpen.loadById", param);
	}
	 /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {
        return update("Promoexpen.updStusById", params);
    }
	
    /**
     * 修改状态为启用
     * Description :.
     * @param params the params
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        return update("Promoexpen.updStusById", params);
    }

	@Override
	public String loadFiles(String param) {
		List<Map> fileList = this.findList("Promoexpen.queryFiles", param);
		String path = "";
		if(fileList!=null){
			for(Map fileMap : fileList){
				path += fileMap.get("ATT_PATH")+",";
			}
		}
		if(path.lastIndexOf(",")>0){
			path = path.substring(0,path.lastIndexOf(","));
		}
		return path;//去除最后逗号
	}
	
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Promoexpen.expertExcelSH",params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public List expertExcelQueryWH(Map<String,String> params){
		return findList("Promoexpen.expertExcel",params);
	}
	
	/**
	 * @param PRMT_COST_REQ_ID
	 * @return
	 */
	public BudgetquotaModel qyeryQuotaAmount(String PRMT_COST_REQ_ID){
		Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
    	List<BudgetquotaModel> list = this.findList("Budgetquota.qyeryQuotaAmountByPrmt", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
	}
	
	/**
	 * @param PRMT_COST_REQ_ID
	 * @return
	 */
	public List <PromoexpenModel> childQuery(String PRMT_COST_REQ_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Promoexpen.queryById", params);
	}
}