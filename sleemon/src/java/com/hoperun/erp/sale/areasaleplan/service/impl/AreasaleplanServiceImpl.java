package com.hoperun.erp.sale.areasaleplan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.decorationreit.model.DecorationreitModel;
import com.hoperun.erp.sale.areasaleplan.model.AreasaleplanModel;
import com.hoperun.erp.sale.areasaleplan.model.AreasaleplandtlModel;
import com.hoperun.erp.sale.areasaleplan.service.AreasaleplanService;
import com.hoperun.sys.model.UserBean;

public class AreasaleplanServiceImpl extends BaseService implements AreasaleplanService {

	 /**
     * @param AREA_SALE_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     * @return
     */
    public void txEdit(String AREA_SALE_PLAN_ID, AreasaleplanModel obj, UserBean userBean,List <AreasaleplandtlModel>  mxList){
    	
    	 String UID = "";
		 Map<String, String> params = new HashMap<String, String>();
		 Map<String, String> paramsT = new HashMap<String, String>();
		 
		 params.put("AREA_ID",   obj.getAREA_ID());
		 params.put("AREA_NO",   obj.getAREA_NO());
		 params.put("AREA_NAME", obj.getAREA_NAME());
		 params.put("WAREA_ID",  obj.getWAREA_ID());
		 params.put("WAREA_NO",  obj.getWAREA_NO());
		 params.put("WAREA_NAME",obj.getWAREA_NAME());
		 params.put("AREA_MANAGE_ID",  obj.getAREA_MANAGE_ID());
		 params.put("AREA_MANAGE_NAME",obj.getAREA_MANAGE_NAME());
		 params.put("PLAN_YEAR", obj.getPLAN_YEAR());
		 params.put("PLAN_SALE_AMOUNT_TOTAL", obj.getPLAN_SALE_AMOUNT_TOTAL());
		 params.put("CHANN_SALE_AMOUNT_TOTAL",obj.getCHANN_SALE_AMOUNT_TOTAL());
		 params.put("REMARK", obj.getREMARK());
		 params.put("YEAR_PLAN_AMOUNT", obj.getYEAR_PLAN_AMOUNT());
		 params.put("FIRST_QUARTER_AMOUNT", obj.getFIRST_QUARTER_AMOUNT());
		 params.put("SECOND_QUARTER_AMOUNT", obj.getSECOND_QUARTER_AMOUNT());
		 params.put("THIRD_QUARTER_AMOUNT", obj.getTHIRD_QUARTER_AMOUNT());
		 params.put("FOURTH_QUARTER_AMOUNT", obj.getFOURTH_QUARTER_AMOUNT());
		 params.put("JAN_AMOUNT", obj.getJAN_AMOUNT());
		 params.put("FEB_AMOUNT", obj.getFEB_AMOUNT());
		 params.put("MAR_AMOUNT", obj.getMAR_AMOUNT());
		 params.put("APR_AMOUNT", obj.getAPR_AMOUNT());
		 params.put("MAY_AMOUNT", obj.getMAY_AMOUNT());
		 params.put("JUN_AMOUNT", obj.getJUN_AMOUNT());
		 params.put("JUL_AMOUNT", obj.getJUL_AMOUNT());
		 params.put("AUG_AMOUNT", obj.getAUG_AMOUNT());
		 params.put("SEP_AMOUNT", obj.getSEP_AMOUNT());
		 params.put("OCT_AMOUNT", obj.getOCT_AMOUNT());
		 params.put("NOV_AMOUNT", obj.getNOV_AMOUNT());
		 params.put("DEC_AMOUNT", obj.getDEC_AMOUNT());
		 Float PLAN_SALE_AMOUNT_TOTAL = Float.parseFloat(obj.getFIRST_QUARTER_AMOUNT().toString())+Float.parseFloat(obj.getSECOND_QUARTER_AMOUNT().toString())+Float.parseFloat(obj.getTHIRD_QUARTER_AMOUNT().toString())+Float.parseFloat(obj.getFOURTH_QUARTER_AMOUNT().toString());
	     params.put("PLAN_SALE_AMOUNT_TOTAL", String.valueOf(PLAN_SALE_AMOUNT_TOTAL));
		 
		 if (StringUtils.isEmpty(AREA_SALE_PLAN_ID)) {
			UID = StringUtil.uuid32len();
			params.put("AREA_SALE_PLAN_ID",UID);
		    params.put("STATE", "未提交");
			params.put("CREATOR",   userBean.getXTYHID());//制单人ID
		    params.put("CRE_NAME",  userBean.getXM());//制单人名称
			params.put("CRE_TIME",  DateUtil.now());//制单时间
			params.put("DEPT_ID",   userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID",    userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME",  userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
			params.put("DEL_FLAG", "0");
			this.insert("Areasaleplan.insert", params);
			
			for(int i=1;i<13;i++){
				paramsT.put("AREA_SALE_PLAN_DTL_ID",StringUtil.uuid32len());
				paramsT.put("AREA_SALE_PLAN_ID", UID);
				paramsT.put("PLAN_YEAR",  obj.getPLAN_YEAR());
				if(i<10){
					paramsT.put("PLAN_MONTH", String.valueOf("0"+i));
				}else{
				    paramsT.put("PLAN_MONTH", String.valueOf(i));
				}
				paramsT.put("DEL_FLAG", "0");
				if(i==1){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJAN_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==2){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getFEB_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==3){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getMAR_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==4){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getAPR_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==5){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getMAY_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==6){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJUN_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==7){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJUL_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==8){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getAUG_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==9){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getSEP_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==10){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getOCT_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				if(i==11){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getNOV_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				if(i==12){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getDEC_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				this.insert("Areasaleplan.insertChild", paramsT);
			}
			
		} else {
			params.put("DEL_FLAG", obj.getDELFLAG());
			params.put("STATE", obj.getSTATE());
			params.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
			params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称           
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			this.update("Areasaleplan.updateById", params);
			List<AreasaleplandtlModel> list = this.childQueryT(AREA_SALE_PLAN_ID);
			if(list !=null) {
				for(int i=0;i<list.size();i++){
					        AreasaleplandtlModel  check = (AreasaleplandtlModel)list.get(i);
					        paramsT.put("AREA_SALE_PLAN_DTL_ID", check.getAREA_SALE_PLAN_DTL_ID());
							paramsT.put("DEL_FLAG", "1");
							update("Areasaleplan.deleteByIdsT", paramsT);
					}
			  }
			
			for(int i=1;i<13;i++){
				paramsT.put("AREA_SALE_PLAN_DTL_ID",StringUtil.uuid32len());
				paramsT.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
				paramsT.put("PLAN_YEAR",  obj.getPLAN_YEAR());
				if(i<10){
					paramsT.put("PLAN_MONTH", String.valueOf("0"+i));
				}else{
				    paramsT.put("PLAN_MONTH", String.valueOf(i));
				}
				paramsT.put("DEL_FLAG", "0");
				if(i==1){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJAN_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==2){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getFEB_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==3){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getMAR_AMOUNT());
					paramsT.put("PLAN_QUARTER", "1");
				}
				if(i==4){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getAPR_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==5){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getMAY_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==6){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJUN_AMOUNT());
					paramsT.put("PLAN_QUARTER", "2");
				}
				if(i==7){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getJUL_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==8){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getAUG_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==9){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getSEP_AMOUNT());
					paramsT.put("PLAN_QUARTER", "3");
				}
				if(i==10){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getOCT_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				if(i==11){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getNOV_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				if(i==12){
					paramsT.put("PLAN_SALE_AMOUNT", obj.getDEC_AMOUNT());
					paramsT.put("PLAN_QUARTER", "4");
				}
				this.insert("Areasaleplan.insertChild", paramsT);
			}
		}
    }
    
	/**
	 * 查询并分页.
	 * @param params the params
	 * @param pageNo the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Areasaleplan.pageQuery",
				"Areasaleplan.pageCount", params, pageNo);
	}
	
	public IListPage pageBatch(Map<String, String> params, int pageNo) {
		return this.pageQuery("Areasaleplan.pageBatch",
				"Areasaleplan.pageBatchCount", params, pageNo);
	}
	
	/**
	 * 根据主表Id查询子表记录
	 * @param DELIVER_ORDER_ID 主表ID
	 * @return the list
	 */
	public List<AreasaleplandtlModel> childQuery(String AREA_SALE_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Areasaleplan.childQuery", params);
	}
	
	/**
	 * @param SALE_PLAN_ID
	 * @return
	 */
	public List<AreasaleplandtlModel> childQueryT(String AREA_SALE_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Areasaleplan.childQueryT", params);
	}
	
	/**
 	 * @主表详细页面
 	 * @param param SALE_PLAN_ID
 	 */
 	public Map<String,String> load(String AREA_SALE_PLAN_ID){
 		return (Map<String, String>) this.load("Areasaleplan.loadById", AREA_SALE_PLAN_ID);
 	}
 	
 	/**
     * @param year
     * @param month
     * @param saleAmount
     * @param chanAmount
     * @return
     */
 	 public void insertChild(String AREA_SALE_PLAN_ID,String year,String month, String saleAmount, String chanAmount){
 		
 		Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("AREA_SALE_PLAN_DTL_ID", StringUtil.uuid32len());
		paramsT.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
		paramsT.put("PLAN_YEAR", year);
		paramsT.put("PLAN_MONTH",month);
		paramsT.put("PLAN_SALE_AMOUNT", saleAmount);
		paramsT.put("CHANN_SALE_AMOUNT",chanAmount);
		paramsT.put("DEL_FLAG", "0");
		this.insert("Areasaleplan.insertChild", paramsT);
 	 }
 	 
 	 /**
      * @param SALE_PLAN_ID
      * @param year
      * @param month
      * @param saleAmount
      * @param chanAmount
      * @return
      */
     public void updateChild(String AREA_SALE_PLAN_DTL_ID,String AREA_SALE_PLAN_ID,String year,String month,
 			String saleAmount, String chanAmount){
    	 
    	    Map<String, String> paramsT = new HashMap<String, String>();
    	    paramsT.put("AREA_SALE_PLAN_DTL_ID", AREA_SALE_PLAN_DTL_ID);
			paramsT.put("AREA_SALE_PLAN_ID",     AREA_SALE_PLAN_ID);
			paramsT.put("PLAN_YEAR", year);
			paramsT.put("PLAN_MONTH",month);
			paramsT.put("PLAN_SALE_AMOUNT", saleAmount);
			paramsT.put("CHANN_SALE_AMOUNT",chanAmount);
			paramsT.put("DEL_FLAG", "0");
	    	this.insert("Saleplan.updateChild", paramsT);
     }
     
     /**
     * 批量删除.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String AREA_SALE_PLAN_DTL_IDs, UserBean userBean) {
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	    params.put("AREA_SALE_PLAN_DTL_IDs", AREA_SALE_PLAN_DTL_IDs);
        update("Areasaleplan.deleteByIds", params);
    }
 
	/**
	 * 删除数据
	 * @param DATA_CONF 
	 * @return true, if tx delete
	 */
     public boolean txDelete(String AREA_SALE_PLAN_ID, UserBean userBean){
    	    
    	    Map<String, String> params = new HashMap<String, String>();
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			params.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
			return update("Areasaleplan.delete", params);
     }
     
     /**
      * @param SALE_PLAN_ID
      * @param userBean
      * @return
      */
     public void txDeleteChild(String AREA_SALE_PLAN_ID, UserBean userBean){
    	    
    	    Map<String, String> params = new HashMap<String, String>();
			List<AreasaleplandtlModel> list = this.childQueryT(AREA_SALE_PLAN_ID);
			if(list!=null){
				for(int i=0;i<list.size();i++){
					AreasaleplandtlModel  saleModel = (AreasaleplandtlModel)list.get(i);
					String AREA_SALE_PLAN_DTL_ID  = saleModel.getAREA_SALE_PLAN_DTL_ID().toString();
					params.put("AREA_SALE_PLAN_DTL_ID",AREA_SALE_PLAN_DTL_ID);
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
					this.insert("Areasaleplan.deleteChldByIds", params); 
				}
			}
     }
     
     /**
      * @param SALE_PLAN_DTL_IDS
      * @return
      */
     public List<AreasaleplandtlModel> loadByIds(String AREA_SALE_PLAN_DTL_IDS){
    	 return findList("Areasaleplan.loadByIds", AREA_SALE_PLAN_DTL_IDS);
     }
     
     public List<AreasaleplanModel> queryJudgeModel(String PLAY_YEAR){
     	Map<String,String> paramMap = new HashMap<String,String>();
      	paramMap.put("SJZDBH", PLAY_YEAR);
      	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
       	List<AreasaleplanModel> list = this.findList("Areasaleplan.selectBrothers", paramMap);
       	if(null != list && !list.isEmpty()){
       		return list;
       	}
       	return null;
      }
     
     /**
      * @param obj
      * @param userBean
      */
     public void batchUpdate(AreasaleplanModel obj, UserBean userBean){
    	 
    	 String UID = "";
   		 Map<String, String> params = new HashMap<String, String>();
   		 Map<String, String> paramsT = new HashMap<String, String>();
   		 params.put("AREA_SALE_PLAN_ID", obj.getAREA_SALE_PLAN_ID());
   		 params.put("YEAR_PLAN_AMOUNT", obj.getYEAR_PLAN_AMOUNT());
   		 params.put("FIRST_QUARTER_AMOUNT", obj.getFIRST_QUARTER_AMOUNT());
   		 params.put("SECOND_QUARTER_AMOUNT", obj.getSECOND_QUARTER_AMOUNT());
   		 params.put("THIRD_QUARTER_AMOUNT", obj.getTHIRD_QUARTER_AMOUNT());
   		 params.put("FOURTH_QUARTER_AMOUNT", obj.getFOURTH_QUARTER_AMOUNT());
   		 params.put("JAN_AMOUNT", obj.getJAN_AMOUNT());
   		 params.put("FEB_AMOUNT", obj.getFEB_AMOUNT());
   		 params.put("MAR_AMOUNT", obj.getMAR_AMOUNT());
   		 params.put("APR_AMOUNT", obj.getAPR_AMOUNT());
   		 params.put("MAY_AMOUNT", obj.getMAY_AMOUNT());
   		 params.put("JUN_AMOUNT", obj.getJUN_AMOUNT());
   		 params.put("JUL_AMOUNT", obj.getJUL_AMOUNT());
   		 params.put("AUG_AMOUNT", obj.getAUG_AMOUNT());
   		 params.put("SEP_AMOUNT", obj.getSEP_AMOUNT());
   		 params.put("OCT_AMOUNT", obj.getOCT_AMOUNT());
   		 params.put("NOV_AMOUNT", obj.getNOV_AMOUNT());
   		 params.put("DEC_AMOUNT", obj.getDEC_AMOUNT());
   		 params.put("STATE", "提交");
   		 params.put("AREA_MANAGE_NAME", obj.getAREA_MANAGE_NAME());
   		 params.put("AREA_MANAGE_ID",  obj.getAREA_MANAGE_ID());
   		 this.update("Areasaleplan.updateById", params);
     }
     
     /**
      * @param obj
      * @return
      */     
     public List<AreasaleplanModel> queryAreaAndYear(Map<String,String> params){
    	 return this.findList("Areasaleplan.queryAreaAndYear", params);     }
     
     public  String  queryRYXXID(String XM){
    	Map<String, String> params = new HashMap<String, String>();
 		params.put("XM", XM);
    	List<Map<String,Object>> list =  this.findList("Areasaleplan.queryRYXXID", params);
 		if(null != list && !list.isEmpty()){
         Map<String, Object> map1 = list.get(0);
         if(map1.get("FNSH_RATE")!=null){
              return  String.valueOf(map1.get("RYXXID").toString());
         }
 		}
         return null;
	}
     
     public Map<String,Object> queryJudgeModelT(String AREA_MANAGE_NAME){
     	Map<String,String> paramMap = new HashMap<String,String>();
     	paramMap.put("XM", AREA_MANAGE_NAME);
      	List<Map<String,Object>> list = this.findList("Areasaleplan.queryJudgeModelT", paramMap);
      	if(null != list && !list.isEmpty()){
      		return list.get(0);
      	}
      	return null;
     }
     
     /**
      * 撤销
      * @param AREA_SALE_PLAN_ID
      */
     public void txRevoke(String AREA_SALE_PLAN_ID,UserBean userBean){
    	Map<String,String>params = new HashMap<String,String>();
		params.put("STATE", "撤销");
		params.put("AREA_SALE_PLAN_ID", AREA_SALE_PLAN_ID);
		params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
		this.update("Areasaleplan.updateStateById", params);
     }
}
