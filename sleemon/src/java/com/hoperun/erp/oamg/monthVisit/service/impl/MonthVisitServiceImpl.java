package com.hoperun.erp.oamg.monthVisit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitModel;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitdtlModel;
import com.hoperun.erp.oamg.monthVisit.service.MonthVisitService;
import com.hoperun.sys.model.UserBean;

public class MonthVisitServiceImpl extends BaseService implements MonthVisitService {

	/**
     * @param MONTH_VISIT_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     */
    public void txEdit(String MONTH_VISIT_PLAN_ID, MonthvisitModel obj, UserBean userBean,List <MonthvisitdtlModel>  mxList){
    	
    	 String UID = "";
		 Map<String, String> params = new HashMap<String, String>();
		 Map<String, String> paramsT = new HashMap<String, String>();
		 params.put("RYXXID",    obj.getRYXXID());
		 params.put("RYBH",      obj.getRYBH());
		 params.put("RYMC",      obj.getRYMC());
		 params.put("PLAN_YEAR", obj.getPLAN_YEAR());
		 params.put("PLAN_MONTH",obj.getPLAN_MONTH());
		 params.put("PLAN_VISIT_NUM_TOTAL",  obj.getPLAN_VISIT_NUM_TOTAL());
		 params.put("REMARK",    obj.getREMARK());
		 
		 if (StringUtils.isEmpty(MONTH_VISIT_PLAN_ID)) {
			UID = StringUtil.uuid32len();
			params.put("MONTH_VISIT_PLAN_ID",UID);
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
		    params.put("STATE", "未提交");
			String MONTH_VISIT_PLAN_NO = LogicUtil.getBmgz("ERP_MONTH_VISIT_PLAN_NO"); //装修补贴标准编号
			params.put("MONTH_VISIT_PLAN_NO",MONTH_VISIT_PLAN_NO);
			params.put("CREATOR",   userBean.getXTYHID());//制单人ID
		    params.put("CRE_NAME",  userBean.getXM());//制单人名称
			params.put("CRE_TIME",  DateUtil.now());//制单时间
			params.put("DEPT_ID",   userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID",    userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME",  userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); 
			params.put("DEL_FLAG", "0");
			this.insert("monthvisit.insert", params);
			if(mxList !=null) {
			for(int i=0;i<mxList.size();i++){
					paramsT.put("MONTH_VISIT_PLAN_DTL_ID",StringUtil.uuid32len());
					paramsT.put("MONTH_VISIT_PLAN_ID", UID);
					paramsT.put("VISIT_OBJ_TYPE",  mxList.get(i).getVISIT_OBJ_TYPE());
					paramsT.put("VISIT_OBJ_ID",    mxList.get(i).getVISIT_OBJ_ID());
					paramsT.put("VISIT_OBJ_NO",    mxList.get(i).getVISIT_OBJ_NO());
					paramsT.put("VISIT_OBJ_NAME",  mxList.get(i).getVISIT_OBJ_NAME());
					paramsT.put("PLAN_VISIT_NUM",  mxList.get(i).getPLAN_VISIT_NUM());
					paramsT.put("VISIT_TYPE", mxList.get(i).getVISIT_TYPE());
					paramsT.put("DEL_FLAG", "0");
					this.insert("monthvisitMX.insert", paramsT);
			}
		  }
		} else {
			params.put("DEL_FLAG", obj.getDELFLAG());
			params.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
			params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称           
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			this.update("monthvisit.updateById", params);
			if(mxList !=null) {
				for(int i=0;i<mxList.size();i++){
							paramsT.put("MONTH_VISIT_PLAN_DTL_ID", mxList.get(i).getMONTH_VISIT_PLAN_DTL_ID());
							paramsT.put("MONTH_VISIT_PLAN_ID",     MONTH_VISIT_PLAN_ID);
							paramsT.put("VISIT_OBJ_TYPE",  mxList.get(i).getVISIT_OBJ_TYPE());
							paramsT.put("VISIT_OBJ_ID",    mxList.get(i).getVISIT_OBJ_ID());
							paramsT.put("VISIT_OBJ_NO",    mxList.get(i).getVISIT_OBJ_NO());
							paramsT.put("VISIT_OBJ_NAME",  mxList.get(i).getVISIT_OBJ_NAME());
							paramsT.put("PLAN_VISIT_NUM",  mxList.get(i).getPLAN_VISIT_NUM());
							paramsT.put("VISIT_TYPE", mxList.get(i).getVISIT_TYPE());
							paramsT.put("DEL_FLAG", "0");
							if(StringUtils.isEmpty(mxList.get(i).getMONTH_VISIT_PLAN_DTL_ID())){
									paramsT.put("MONTH_VISIT_PLAN_DTL_ID",StringUtil.uuid32len());
									this.insert("monthvisitMX.insert", paramsT);
								 
							 } else {
								 paramsT.put("MONTH_VISIT_PLAN_DTL_ID",mxList.get(i).getMONTH_VISIT_PLAN_DTL_ID());
								 this.insert("monthvisitMX.updateById", paramsT);
					        }
					}
			  }
		}
		 String message=checkDtlRepeat(MONTH_VISIT_PLAN_ID);
    	 if(!StringUtil.isEmpty(message)){
    		 throw new ServiceException(message);
    	 }
    }
    
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String MONTH_VISIT_PLAN_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
		return update("monthvisit.delete", params);
    }
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param userBean
     */
    public void txDeleteChild(String MONTH_VISIT_PLAN_ID, UserBean userBean){
    	  Map<String, String> params = new HashMap<String, String>();
		  List<MonthvisitdtlModel> list = this.childQueryT(MONTH_VISIT_PLAN_ID);
		  if(list!=null){
				for(int i=0;i<list.size();i++){
					MonthvisitdtlModel  monthvisitModel = (MonthvisitdtlModel)list.get(i);
					String MONTH_VISIT_PLAN_DTL_ID  = monthvisitModel.getMONTH_VISIT_PLAN_DTL_ID().toString();
					params.put("MONTH_VISIT_PLAN_DTL_ID",MONTH_VISIT_PLAN_DTL_ID);
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
					this.insert("monthvisitMX.delete", params); 
				}
			}
    }
    
	/**
	 * @param MONTH_VISIT_PLAN_ID
	 * @return
	 */
	public List<MonthvisitdtlModel> childQueryT(String MONTH_VISIT_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("monthvisitMX.childQueryT", params);
	}
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("monthvisit.pageQuery",
				"monthvisit.pageCount", params, pageNo);
    }
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <MonthvisitdtlModel> childQuery(String MONTH_VISIT_PLAN_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("monthvisit.childQuery", params);
    }
    
    /**
 	 * @主表详细页面
 	 * @param param MONTH_VISIT_PLAN_ID
 	 */
 	public Map<String,String> load(String MONTH_VISIT_PLAN_ID){
 		return (Map<String, String>) this.load("monthvisit.loadById", MONTH_VISIT_PLAN_ID);
 	}
 	
 	 /**
     * @param MONTH_VISIT_PLAN_DTL_IDS
     * @return
     */
    public List<MonthvisitdtlModel> loadByIds(String MONTH_VISIT_PLAN_DTL_IDS){
    	return findList("monthvisit.loadByIds", MONTH_VISIT_PLAN_DTL_IDS);
    }
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param VISIT_OBJ_TYPE
     * @param VISIT_OBJ_ID
     * @param VISIT_OBJ_NO
     * @param VISIT_OBJ_NAME
     * @param PLAN_VISIT_NUM
     */
    public void txInsertChild(String MONTH_VISIT_PLAN_ID,String VISIT_OBJ_TYPE,String PLAN_VISIT_NUM,String VISIT_TYPE){
    	Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("MONTH_VISIT_PLAN_DTL_ID", StringUtil.uuid32len());
		paramsT.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
		paramsT.put("VISIT_OBJ_TYPE", VISIT_OBJ_TYPE);
		paramsT.put("PLAN_VISIT_NUM",PLAN_VISIT_NUM);
		paramsT.put("DEL_FLAG", "0");
		paramsT.put("VISIT_TYPE", VISIT_TYPE);
		this.insert("monthvisitMX.insert", paramsT);
		
		List<MonthvisitdtlModel> list = this.childQueryT(MONTH_VISIT_PLAN_ID);
		int  totalNum = 0;
		if(list!=null){
		   for(int i=0;i<list.size();i++){
			MonthvisitdtlModel  dtlModel = (MonthvisitdtlModel)list.get(i);
			String  MONTH_VISIT_PLAN_DTL_ID = dtlModel.getMONTH_VISIT_PLAN_DTL_ID();
			String  LAN_VISIT_NUM   = queryChildbyNum(MONTH_VISIT_PLAN_DTL_ID);
			totalNum = totalNum+Integer.parseInt(LAN_VISIT_NUM);
			paramsT.put("PLAN_VISIT_NUM_TOTAL",String.valueOf(totalNum));
			this.update("monthvisit.updateById", paramsT);
		   }
		}
		String message=checkDtlRepeat(MONTH_VISIT_PLAN_ID);
	   	 if(!StringUtil.isEmpty(message)){
	   		 throw new ServiceException(message);
	   	 }
    }
    
    /**
     * @param MONTH_VISIT_PLAN_DTL_ID
     * @param MONTH_VISIT_PLAN_ID
     * @param VISIT_OBJ_TYPE
     * @param VISIT_OBJ_ID
     * @param VISIT_OBJ_NO
     * @param VISIT_OBJ_NAME
     * @param PLAN_VISIT_NUM
     */    
    public void txUpdateChild(String MONTH_VISIT_PLAN_DTL_ID,String MONTH_VISIT_PLAN_ID,String VISIT_OBJ_TYPE,String PLAN_VISIT_NUM,String VISIT_TYPE){
	    Map<String, String> paramsT = new HashMap<String, String>();
	    paramsT.put("MONTH_VISIT_PLAN_DTL_ID", MONTH_VISIT_PLAN_DTL_ID);
		paramsT.put("MONTH_VISIT_PLAN_ID",     MONTH_VISIT_PLAN_ID);
		paramsT.put("VISIT_OBJ_TYPE", VISIT_OBJ_TYPE);
		paramsT.put("PLAN_VISIT_NUM", PLAN_VISIT_NUM);
		paramsT.put("DEL_FLAG", "0");
		paramsT.put("VISIT_TYPE", VISIT_TYPE);
    	this.insert("monthvisitMX.updateById", paramsT);
		int  totalNum = 0;
    	    List<MonthvisitdtlModel> list   = queryChildbyNumT(MONTH_VISIT_PLAN_ID);
    	    if(list!=null){
    	      for(int i=0;i<list.size();i++){
					totalNum = totalNum+Integer.parseInt(list.get(i).getPLAN_VISIT_NUM());
					paramsT.put("MONTH_VISIT_PLAN_DTL_ID", MONTH_VISIT_PLAN_DTL_ID);
					paramsT.put("PLAN_VISIT_NUM_TOTAL",String.valueOf(totalNum));
					this.update("monthvisit.updateById", paramsT);
		   }
		}
    	 String message=checkDtlRepeat(MONTH_VISIT_PLAN_ID);
    	 if(!StringUtil.isEmpty(message)){
    		 throw new ServiceException(message);
    	 }
    }
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public String txBatch4DeleteChild(String MONTH_VISIT_PLAN_DTL_IDs, UserBean userBean,String MONTH_VISIT_PLAN_ID){
    	Map<String, String> params = new HashMap<String, String>();
        //删除后递减拜访数
        String[] arrs =  MONTH_VISIT_PLAN_DTL_IDs.split(",");
        String PLAN_VISIT_NUM_TOTAL = "";
        for(int i=0;i<arrs.length;i++){
        	PLAN_VISIT_NUM_TOTAL = querybyNum(MONTH_VISIT_PLAN_ID);
        	String PLAN_VISIT_NUM = queryChildbyNum(String.valueOf(arrs[i]));
        	int Num = Integer.parseInt(PLAN_VISIT_NUM_TOTAL)-Integer.parseInt(PLAN_VISIT_NUM);
        	params.put("PLAN_VISIT_NUM_TOTAL", String.valueOf(Num));
        	params.put("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
        	params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称           
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			this.update("monthvisit.updateById", params);
			
			params.put("MONTH_VISIT_PLAN_DTL_ID", arrs[i]);
			this.update("monthvisit.deleteByIds", params);
        }
        PLAN_VISIT_NUM_TOTAL = querybyNum(MONTH_VISIT_PLAN_ID);
        return PLAN_VISIT_NUM_TOTAL;
    }
    
    public String querybyNum(String MONTH_VISIT_PLAN_ID){
    	 return (String) load("monthvisit.querybyNum", MONTH_VISIT_PLAN_ID);
    }
    
    public String queryChildbyNum(String ONTH_VISIT_PLAN_DTL_ID){
   	     return (String) load("monthvisit.queryChildbyNum", ONTH_VISIT_PLAN_DTL_ID);
    }
    
    public List<MonthvisitdtlModel> queryChildbyNumT(String MONTH_VISIT_PLAN_ID){
    	return findList("monthvisitMX.queryChildbyNumT", MONTH_VISIT_PLAN_ID);
   }
    /**
     * 根据主表id查询明细是否存在重复项
     * @param MONTH_VISIT_PLAN_ID
     * @return
     */
    public String checkDtlRepeat(String MONTH_VISIT_PLAN_ID){
    	int allCount=StringUtil.getInteger(this.load("monthvisitMX.getDtlAllCount",MONTH_VISIT_PLAN_ID));
    	int count=StringUtil.getInteger(this.load("monthvisitMX.getDtlCount",MONTH_VISIT_PLAN_ID));
    	if(allCount!=count){
    		return "对不起，同一个拜访对象，拜访方式只能有一条记录，不能保存 !";
    	}
    	Map<String,String> map=(Map<String, String>) this.load("monthvisitMX.getMainCount",MONTH_VISIT_PLAN_ID);
    	if(null!=map){
    		return "对不起 "+map.get("RYMC")+"在"+map.get("DATA")+"已存在计划拜访信息，不能保存！";
    	}
    	return null;
    }
}
