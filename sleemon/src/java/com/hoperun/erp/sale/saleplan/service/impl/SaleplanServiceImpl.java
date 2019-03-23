package com.hoperun.erp.sale.saleplan.service.impl;

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
import com.hoperun.erp.sale.saleplan.model.SaleplanModel;
import com.hoperun.erp.sale.saleplan.model.SaleplandtlModel;
import com.hoperun.erp.sale.saleplan.service.SaleplanService;
import com.hoperun.sys.model.UserBean;

public class SaleplanServiceImpl extends BaseService implements SaleplanService  {

	
	 /**
     * @param SALE_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     * @return
     */
    public void txEdit(String SALE_PLAN_ID, SaleplanModel obj, UserBean userBean,List <SaleplandtlModel>  mxList){
    	
    	 String UID = "";
		 Map<String, String> params = new HashMap<String, String>();
		 Map<String, String> paramsT = new HashMap<String, String>();
		 params.put("CHANN_ID",  obj.getCHANN_ID());
		 params.put("CHANN_NO",  obj.getCHANN_NO());
		 params.put("CHANN_NAME",obj.getCHANN_NAME());
		 params.put("CHANN_ABBR",obj.getCHANN_ABBR());
		 params.put("CHANN_TYPE",obj.getCHANN_TYPE());
		 params.put("CHAA_LVL",  obj.getCHAA_LVL());
		 params.put("AREA_ID",   obj.getAREA_ID());
		 params.put("AREA_NO",   obj.getAREA_NO());
		 params.put("AREA_NAME", obj.getAREA_NAME());
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
		 
		 if (StringUtils.isEmpty(SALE_PLAN_ID)) {
			UID = StringUtil.uuid32len();
			params.put("SALE_PLAN_ID",UID);
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
		    params.put("STATE", "未提交");
			String SALE_PLAN_NO = LogicUtil.getBmgz("ERP_SALE_PLAN_NO"); //装修补贴标准编号
			params.put("SALE_PLAN_NO",SALE_PLAN_NO);
			params.put("CREATOR",   userBean.getXTYHID());//制单人ID
		    params.put("CRE_NAME",  userBean.getXM());//制单人名称
			params.put("CRE_TIME",  DateUtil.now());//制单时间
			params.put("DEPT_ID",   userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID",    userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME",  userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); 
			params.put("DEL_FLAG", "0");
			this.insert("Saleplan.insert", params);
			
			for(int i=1;i<13;i++){
				paramsT.put("SALE_PLAN_DTL_ID",StringUtil.uuid32len());
				paramsT.put("SALE_PLAN_ID", UID);
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
				this.insert("Saleplan.insertChild", paramsT);
			}
		} else {
			params.put("DEL_FLAG", obj.getDELFLAG());
			params.put("STATE", obj.getSTATE());
			params.put("SALE_PLAN_ID",  SALE_PLAN_ID);
			params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称           
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			this.update("Saleplan.updateById", params);
			List<SaleplandtlModel> list = this.childQueryT(SALE_PLAN_ID);
			if(list !=null) {
				for(int i=0;i<list.size();i++){
					        SaleplandtlModel  check = (SaleplandtlModel)list.get(i);
							paramsT.put("SALE_PLAN_DTL_ID", check.getSALE_PLAN_DTL_ID());
							paramsT.put("DEL_FLAG", "1");
							update("Saleplan.deleteByIdsT", paramsT);
					}
			  }
			for(int i=1;i<13;i++){
				paramsT.put("SALE_PLAN_DTL_ID",StringUtil.uuid32len());
				paramsT.put("SALE_PLAN_ID", SALE_PLAN_ID);
				paramsT.put("PLAN_YEAR",  obj.getPLAN_YEAR());
				if(i<10){
					paramsT.put("PLAN_MONTH", String.valueOf("0"+i));
				} else {
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
				this.insert("Saleplan.insertChild", paramsT);
			}
//			paramsT.put("SALE_PLAN_ID", SALE_PLAN_ID);
//			paramsT.put("PLAN_YEAR",   check.getPLAN_YEAR());
//			paramsT.put("PLAN_MONTH",  check.getPLAN_MONTH());
//			paramsT.put("PLAN_SALE_AMOUNT", check.getPLAN_SALE_AMOUNT());
//			//paramsT.put("CHANN_SALE_AMOUNT" check.getCHANN_SALE_AMOUNT());
//			paramsT.put("DEL_FLAG", "0");
//	    	this.insert("Saleplan.updateChild", paramsT);
		}
    }
    
    public void batchUpdate(SaleplanModel obj, UserBean userBean){
    	
      	 String UID = "";
   		 Map<String, String> params = new HashMap<String, String>();
   		 Map<String, String> paramsT = new HashMap<String, String>();
   		 params.put("SALE_PLAN_ID", obj.getSALE_PLAN_ID());
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
   		 params.put("AREA_MANAGE_ID", obj.getAREA_MANAGE_ID());
   		 params.put("AREA_MANAGE_NAME", obj.getAREA_MANAGE_NAME());
   		 params.put("STATE", "提交");
   		 this.update("Saleplan.updateById", params);
      }
    
	/**
	 * 查询并分页.
	 * @param params the params
	 * @param pageNo the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Saleplan.pageQuery",
				"Saleplan.pageCount", params, pageNo);
	}
	
	public IListPage pageBatch(Map<String, String> params, int pageNo) {
		return this.pageQuery("Saleplan.pageBatch",
				"Saleplan.pageBatchCount", params, pageNo);
	}
	
	/**
	 * 根据主表Id查询子表记录
	 * @param DELIVER_ORDER_ID 主表ID
	 * @return the list
	 */
	public List<SaleplandtlModel> childQuery(String SALE_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_PLAN_ID", SALE_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Saleplan.childQuery", params);
	}
	
	/**
	 * @param SALE_PLAN_ID
	 * @return
	 */
	public List<SaleplandtlModel> childQueryT(String SALE_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_PLAN_ID", SALE_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Saleplan.childQueryT", params);
	}
	
	/**
 	 * @主表详细页面
 	 * @param param SALE_PLAN_ID
 	 */
 	public Map<String,String> load(String SALE_PLAN_ID){
 		return (Map<String, String>) this.load("Saleplan.loadById", SALE_PLAN_ID);
 	}
 	
 	/**
     * @param year
     * @param month
     * @param saleAmount
     * @param chanAmount
     * @return
     */
 	 public void insertChild(String SALE_PLAN_ID,String year,String month, String saleAmount, String chanAmount){
 		
 		Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("SALE_PLAN_DTL_ID", StringUtil.uuid32len());
		paramsT.put("SALE_PLAN_ID", SALE_PLAN_ID);
		paramsT.put("PLAN_YEAR", year);
		paramsT.put("PLAN_MONTH",month);
		paramsT.put("PLAN_SALE_AMOUNT", saleAmount);
		paramsT.put("CHANN_SALE_AMOUNT",chanAmount);
		paramsT.put("DEL_FLAG", "0");
		this.insert("DecorationalloMX.insertChild", paramsT);
 	 }
 	 
 	 /**
      * @param SALE_PLAN_ID
      * @param year
      * @param month
      * @param saleAmount
      * @param chanAmount
      * @return
      */
     public void updateChild(String SALE_PLAN_DTL_ID,String SALE_PLAN_ID,String year,String month,
 			String saleAmount, String chanAmount){
    	 
    	    Map<String, String> paramsT = new HashMap<String, String>();
    	    paramsT.put("SALE_PLAN_DTL_ID", SALE_PLAN_DTL_ID);
			paramsT.put("SALE_PLAN_ID",     SALE_PLAN_ID);
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
    public void txBatch4DeleteChild(String SALE_PLAN_DTL_IDs, UserBean userBean) {
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	    params.put("SALE_PLAN_DTL_IDs", SALE_PLAN_DTL_IDs);
        update("Saleplan.deleteByIds", params);
    }
 
	/**
	 * 删除数据
	 * @param DATA_CONF 
	 * @return true, if tx delete
	 */
     public boolean txDelete(String SALE_PLAN_ID, UserBean userBean){
    	    
    	    Map<String, String> params = new HashMap<String, String>();
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_PLAN_ID", SALE_PLAN_ID);
			return update("Saleplan.delete", params);
     }
     
     /**
      * @param SALE_PLAN_ID
      * @param userBean
      * @return
      */
     public void txDeleteChild(String SALE_PLAN_ID, UserBean userBean){
    	    
    	    Map<String, String> params = new HashMap<String, String>();
			List<SaleplandtlModel> list = this.childQueryT(SALE_PLAN_ID);
			if(list!=null){
				for(int i=0;i<list.size();i++){
					SaleplandtlModel  saleModel = (SaleplandtlModel)list.get(i);
					String SALE_PLAN_DTL_ID  = saleModel.getSALE_PLAN_DTL_ID().toString();
					params.put("SALE_PLAN_DTL_ID",SALE_PLAN_DTL_ID);
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
					this.insert("Saleplan.deleteChldByIds", params); 
				}
			}
     }
     
     /**
      * @param SALE_PLAN_DTL_IDS
      * @return
      */
     public List<SaleplandtlModel> loadByIds(String SALE_PLAN_DTL_IDS){
    	 return findList("Saleplan.loadByIds", SALE_PLAN_DTL_IDS);
     }
     
     public List<SaleplanModel> queryJudgeModel(String PLAY_YEAR){
    	Map<String,String> paramMap = new HashMap<String,String>();
     	paramMap.put("SJZDBH", PLAY_YEAR);
     	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
      	List<SaleplanModel> list = this.findList("Saleplan.selectBrothers", paramMap);
      	if(null != list && !list.isEmpty()){
      		return list;
      	}
      	return null;
     }
     
     /**
      * @param obj
      * @return
      */     
     public List<SaleplanModel> queryChannAndYear(Map<String,String> params){
    	 return this.findList("Saleplan.queryChannAndYear", params);
     }
}
