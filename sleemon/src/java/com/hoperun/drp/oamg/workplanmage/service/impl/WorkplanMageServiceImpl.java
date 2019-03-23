package com.hoperun.drp.oamg.workplanmage.service.impl;

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
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmageModel;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmagedtlModel;
import com.hoperun.drp.oamg.workplanmage.service.WorkplanMageService;
import com.hoperun.sys.model.UserBean;

public class WorkplanMageServiceImpl extends BaseService implements WorkplanMageService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("workplanmage.pageQuery",
				"workplanmage.pageCount", params, pageNo);
    }
    
    /**
 	 * @主表详细页面
 	 * @param param WORK_PLAN_ID
 	 */
 	public Map<String,String> load(String WORK_PLAN_ID){
 		return (Map<String, String>) this.load("workplanmage.loadById", WORK_PLAN_ID);
 	}
 	
 	/**
     * * 根据主表Id查询子表记录
     * @param WORK_PLAN_ID  主表ID
     * @return the list
     */
    public List <WorkplanmagedtlModel> childQuery(String WORK_PLAN_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("WORK_PLAN_ID", WORK_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("workplanmage.childQuery", params);
    }
    
    
    public List <WorkplanmagedtlModel> childQueryT(String WORK_PLAN_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("WORK_PLAN_ID", WORK_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("workplanmageMX.childQuery", params);
    }
    
    public List <WorkplanmagedtlModel> queryJudgeModel(String WAREA_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("WAREA_ID", WAREA_ID);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
     	List<WorkplanmagedtlModel> list = this.findList("workplanmageMX.selectBrothers", paramMap);
     	return list;
    }
    
    /**
     * @param WORK_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     */
    public String txEdit(String WORK_PLAN_ID, WorkplanmageModel obj, UserBean userBean,List <WorkplanmagedtlModel>  mxList){
    	 String UID = "";
		 Map<String, String> params = new HashMap<String, String>();
		 Map<String, String> paramsT = new HashMap<String, String>();
		 params.put("WAREA_ID",  obj.getWAREA_ID());
		 params.put("WAREA_NO",  obj.getWAREA_NO());
		 params.put("WAREA_NAME",obj.getWAREA_NAME());
		 params.put("PLAN_YEAR", obj.getPLAN_YEAR());
		 params.put("PLAN_MONTH",obj.getPLAN_MONTH());
		 params.put("TOTAL_UP_REPORT_NUM",  obj.getTOTAL_UP_REPORT_NUM());
		 params.put("REMARK", obj.getREMARK());
		 
		 if(mxList !=null) {
			    for(int i=0;i<mxList.size()-1;i++){
			    	for  ( int j=mxList.size()-1;j>i;j--){ 
			    		if  (mxList.get(i).getRYBH().equals(mxList.get(j).getRYBH()) && mxList.get(i).getRYBH().equals(mxList.get(j).getRYBH())){
			    			  return "1";
			    		} 
			    	}
			    }
		  }
		 
		 if (StringUtils.isEmpty(WORK_PLAN_ID)) {
			UID = StringUtil.uuid32len();
			params.put("WORK_PLAN_ID",UID);
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
		    params.put("STATE", "未提交");
			String WORK_PLAN_NO = LogicUtil.getBmgz("ERP_WORK_PLAN_NO"); //装修补贴标准编号
			params.put("WORK_PLAN_NO",WORK_PLAN_NO);
			params.put("CREATOR",   userBean.getXTYHID());//制单人ID
		    params.put("CRE_NAME",  userBean.getXM());//制单人名称
			params.put("CRE_TIME",  DateUtil.now());//制单时间
			params.put("DEPT_ID",   userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID",    userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME",  userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); 
			params.put("DEL_FLAG", "0");
			this.insert("workplanmage.insert", params);
			if(mxList !=null) {
			for(int i=0;i<mxList.size();i++){
					paramsT.put("WORK_PLAN_DTL_ID",StringUtil.uuid32len());
					paramsT.put("WORK_PLAN_ID", UID);
					paramsT.put("RYXXID",  mxList.get(i).getRYXXID());
					paramsT.put("RYBH", mxList.get(i).getRYBH());
					paramsT.put("RYMC",mxList.get(i).getRYMC());
					paramsT.put("UP_REPORT_NUM", "1");
					paramsT.put("DEL_FLAG", "0");
					this.insert("workplanmageMX.insert", paramsT);
			}
		  }
		} else {
			params.put("DEL_FLAG", obj.getDELFLAG());
			params.put("STATE", obj.getSTATE());
			params.put("WORK_PLAN_ID", obj.getWORK_PLAN_ID());
			params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称           
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			this.update("workplanmage.updateById", params);
			//txDeleteChildT(WORK_PLAN_ID,userBean);
			if(mxList !=null) {
				for(int i=0;i<mxList.size();i++){
							paramsT.put("WORK_PLAN_DTL_ID", mxList.get(i).getWORK_PLAN_DTL_ID());
							paramsT.put("WORK_PLAN_ID",     WORK_PLAN_ID);
							paramsT.put("RYXXID",  mxList.get(i).getRYXXID());
							paramsT.put("RYBH",    mxList.get(i).getRYBH());
							paramsT.put("RYMC",    mxList.get(i).getRYMC());
							paramsT.put("UP_REPORT_NUM", "1");
							paramsT.put("DEL_FLAG", "0");
							
							if(StringUtils.isEmpty(mxList.get(i).getWORK_PLAN_DTL_ID())){
									paramsT.put("WORK_PLAN_DTL_ID",StringUtil.uuid32len());
									this.insert("workplanmageMX.insert", paramsT);
							} else {
								 paramsT.put("WORK_PLAN_DTL_ID",mxList.get(i).getWORK_PLAN_DTL_ID());
								 this.insert("workplanmageMX.updateById", paramsT);
					       }
					}
			  }
		}
		 return "0";
    }
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String WORK_PLAN_ID, UserBean userBean){
        Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("WORK_PLAN_ID", WORK_PLAN_ID);
		return update("workplanmage.delete", params);
    }
    
    /**
     * @param WORK_PLAN_ID
     * @param userBean
     * @return
     */
    public void txDeleteChild(String WORK_PLAN_ID, UserBean userBean){
    	 Map<String, String> params = new HashMap<String, String>();
	     List<WorkplanmagedtlModel> list = this.childQuery(WORK_PLAN_ID);
		 if(list!=null){
			for(int i=0;i<list.size();i++){
				WorkplanmagedtlModel  saleModel = (WorkplanmagedtlModel)list.get(i);
				String WORK_PLAN_DTL_ID  = saleModel.getWORK_PLAN_DTL_ID().toString();
				params.put("WORK_PLAN_DTL_ID",WORK_PLAN_DTL_ID);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
				this.insert("workplanmage.deleteChldByIds", params); 
			}
		}
    }
    
    public void txDeleteChildT(String WORK_PLAN_ID, UserBean userBean){
   	     Map<String, String> params = new HashMap<String, String>();
	     List<WorkplanmagedtlModel> list = this.childQueryT(WORK_PLAN_ID);
		 if(list!=null){
			for(int i=0;i<list.size();i++){
				WorkplanmagedtlModel  saleModel = (WorkplanmagedtlModel)list.get(i);
				String WORK_PLAN_DTL_ID  = saleModel.getWORK_PLAN_DTL_ID().toString();
				params.put("WORK_PLAN_DTL_ID",WORK_PLAN_DTL_ID);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
				this.insert("workplanmage.deleteChldByIds", params); 
			}
			}
   }
    
   /**
    * 根据子表Id批量加载子表信息
    * Description :.
    * @param sjzdMxIds the sjzd mx ids
    * @return the list< sjzd mx model>
    */
   public List<WorkplanmagedtlModel> loadChilds(String WORK_PLAN_DTL_IDs){
   	return findList("workplanmageMX.loadByIdsT", WORK_PLAN_DTL_IDs);
   }
   
   /**
    * @param WORK_PLAN_ID
    * @param RYBH
    * @return
    */
   public int  queryRYForInt(String WORK_PLAN_ID,String RYBH){
	   
		Map<String, String> params = new HashMap<String, String>();
		params.put("WORK_PLAN_ID", WORK_PLAN_ID);
		params.put("RYBH", RYBH);
		params.put("DEL_FLAG", "0");
		Object count = load("workplanmageMX.queryRYForInt", params);
		if (count == null) {
			count = "0";
		}
		return Integer.parseInt(count.toString());
   }
   
   /**
    * @param WORK_PLAN_ID
    * @param RYXXID
    * @param RYBH
    * @param RYMC
    */
   public String insertChild(String WORK_PLAN_ID,String RYXXID,String RYBH,String RYMC,List <WorkplanmagedtlModel> modelList){
	    Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("WORK_PLAN_DTL_ID", StringUtil.uuid32len());
		paramsT.put("WORK_PLAN_ID", WORK_PLAN_ID);
		paramsT.put("RYXXID", RYXXID);
		paramsT.put("RYBH",   RYBH);
		paramsT.put("RYMC",   RYMC);
		paramsT.put("UP_REPORT_NUM", "1");
		paramsT.put("DEL_FLAG", "0");
		
		int count = queryRYForInt(WORK_PLAN_ID,RYBH);
		if(count==0){
		   this.insert("workplanmageMX.insert", paramsT);
		} else {
			return "1";
		}
		 if(modelList !=null) {
			    for(int i=0;i<modelList.size()-1;i++){
			    	for  ( int j=modelList.size()-1;j>i;j--){ 
			    		if  (modelList.get(i).getRYBH().equals(modelList.get(j).getRYBH()) && modelList.get(i).getRYBH().equals(modelList.get(j).getRYBH())){
			    			  return "1";
			    		} 
			    	}
			    }
		  }
		
		List<WorkplanmagedtlModel> list = this.childQueryT(WORK_PLAN_ID);
		int  totalNum = 0;
		if(list!=null){
		   for(int i=0;i<list.size();i++){
			WorkplanmagedtlModel  dtlModel = (WorkplanmagedtlModel)list.get(i);
			String  WORK_PLAN_DTL_ID = dtlModel.getWORK_PLAN_DTL_ID();
			String  UP_REPORT_NUM    = queryChildbyNum(WORK_PLAN_DTL_ID);
			totalNum = totalNum+Integer.parseInt(UP_REPORT_NUM);
			paramsT.put("TOTAL_UP_REPORT_NUM",String.valueOf(totalNum));
			this.update("workplanmage.updateById", paramsT);
		   }
		}
		return "0";
   }
   
   /**
    * @param SALE_PLAN_DTL_ID
    * @param WORK_PLAN_ID
    * @param RYXXID
    * @param RYBH
    * @param RYMC
    */
   public String  updateChild(String WORK_PLAN_DTL_ID,String WORK_PLAN_ID,String RYXXID,String RYBH,String RYMC,List <WorkplanmagedtlModel> modelList){
	    
	   if(modelList !=null) {
		    for(int i=0;i<modelList.size()-1;i++){
		    	for  ( int j=modelList.size()-1;j>i;j--){ 
		    		if  (modelList.get(i).getRYBH().equals(modelList.get(j).getRYBH()) && modelList.get(i).getRYBH().equals(modelList.get(j).getRYBH())){
		    			  return "1";
		    		} 
		    	}
		    }
	    }
	   
	    Map<String, String> paramsT = new HashMap<String, String>();
	    paramsT.put("WORK_PLAN_DTL_ID", WORK_PLAN_DTL_ID);
		paramsT.put("WORK_PLAN_ID",     WORK_PLAN_ID);
		paramsT.put("RYXXID", RYXXID);
		paramsT.put("RYBH",   RYBH);
		paramsT.put("RYMC",   RYMC);
		paramsT.put("DEL_FLAG", "0");
     	this.insert("workplanmageMX.updateById", paramsT);
     	return "0";
   }
   
   /**
    * @param WORK_PLAN_DTL_IDs
    * @param userBean
    */
   public void txBatch4DeleteChild(String WORK_PLAN_DTL_IDs, UserBean userBean){
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("WORK_PLAN_DTL_IDs", WORK_PLAN_DTL_IDs);
        update("workplanmage.deleteByIds1", params); 
   }
   
   /**
    * @param WORK_PLAN_DTL_IDs
    * @param userBean
    */
   public String txBatch4DeleteChildT(String WORK_PLAN_DTL_IDs, UserBean userBean,String WORK_PLAN_ID){
	    Map<String, String> params = new HashMap<String, String>();
	    String[] strs = WORK_PLAN_DTL_IDs.split(",");
	    for(int i=0;i<strs.length;i++){
		     String TOTAL_UP_REPORT_NUM = querybyNum(WORK_PLAN_ID);
	         String UP_REPORT_NUM       = queryChildbyNum(String.valueOf(strs[i]));
	         int Num = Integer.parseInt(TOTAL_UP_REPORT_NUM)-Integer.parseInt(UP_REPORT_NUM);
	         params.put("TOTAL_UP_REPORT_NUM", String.valueOf(Num));
	         params.put("WORK_PLAN_ID",  WORK_PLAN_ID);
	         params.put("UPDATOR", userBean.getXTYHID());//更新人id
		     params.put("UPD_NAME", userBean.getXM());//更新人名称           
		     params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间   
			 this.update("workplanmage.updateById", params);	
		    	
		     params.put("WORK_PLAN_DTL_IDs", strs[i].toString());
	         update("workplanmage.deleteByIdsT", params); 
	    }
	    String TOTAL_UP_REPORT_NUM = querybyNum(WORK_PLAN_ID);
	    return TOTAL_UP_REPORT_NUM;
   }
   
   public String querybyNum(String WORK_PLAN_ID){
  	     return (String) load("workplanmage.querybyNum", WORK_PLAN_ID);
   }
  
   public String queryChildbyNum(String WORK_PLAN_DTL_ID){
 	     return (String) load("workplanmage.queryChildbyNum", WORK_PLAN_DTL_ID);
   }
}
