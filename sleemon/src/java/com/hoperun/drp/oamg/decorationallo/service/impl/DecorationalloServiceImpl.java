package com.hoperun.drp.oamg.decorationallo.service.impl;

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
import com.hoperun.drp.oamg.decorationallo.model.DecorationalloModel;
import com.hoperun.drp.oamg.decorationallo.model.DrprnvtnsubststddtlModel;
import com.hoperun.drp.oamg.decorationallo.service.DecorationalloService;
import com.hoperun.sys.model.UserBean;

public class DecorationalloServiceImpl extends BaseService implements DecorationalloService{

	
	/**
	 * 分页查询
	 * Description :.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {

		return this.pageQuery("Decorationallo.pageQuery","Decorationallo.pageCount", params, pageNo);
	}
	
	public IListPage pageQueryT(Map<String, String> params, int pageNo) {

		return this.pageQuery("Decorationallo.pageQueryT","Decorationallo.pageCountT", params, pageNo);
	}
	
	
	 public String txEdit(String RNVTN_SUBST_STD_ID, DecorationalloModel obj, UserBean userBean,List <DrprnvtnsubststddtlModel>  mxList){
		 String UID = "";
		 Map<String, String> params = new HashMap<String, String>();
		 Map<String, String> paramsT = new HashMap<String, String>();
		 params.put("BRAND",  obj.getBRAND());
		 params.put("MIN_AREA", obj.getMIN_AREA());
		 params.put("STD_AREA",obj.getSTD_AREA());
		 params.put("BUILD_COST",obj.getBUILD_COST());
		 params.put("DECORATE_COST",obj.getDECORATE_COST());
		 params.put("RNVTN_SUBST_STD_VSION", obj.getRNVTN_SUBST_STD_VSION());
		 params.put("REMARK", obj.getREMARK());
		 
		 if(mxList !=null) {
			    for(int i=0;i<mxList.size()-1;i++){
			    	for  ( int j=mxList.size()-1;j>i;j--){ 
			    		if  (mxList.get(i).getLOCAL_TYPE().equals(mxList.get(j).getLOCAL_TYPE())){
			    			  return "0";
			    		} 
			    	}
			    }
		 }
		 
		 if (StringUtils.isEmpty(RNVTN_SUBST_STD_ID)) {
			UID = StringUtil.uuid32len();
			params.put("RNVTN_SUBST_STD_ID",UID);
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
		    params.put("STATE", "制定");
			String RNVTN_SUBST_STD_NO = LogicUtil.getBmgz("DRP_RNVTN_SUBST_STD_NO"); //装修补贴标准编号
			params.put("RNVTN_SUBST_STD_NO",RNVTN_SUBST_STD_NO);
			params.put("CREATOR",   userBean.getXTYHID());//制单人ID
		    params.put("CRE_NAME",  userBean.getXM());//制单人名称
			params.put("CRE_TIME",  DateUtil.now());//制单时间
			params.put("DEPT_ID",   userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID",    userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME",  userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); 
			params.put("DEL_FLAG", "0");
			this.insert("Decorationallo.insert", params);
			if(mxList !=null) {
			for(int i=0;i<mxList.size();i++){
				 /**
			     * 控制明细同一商场位置类型是否只有一条记录
			     * @param LOCAL_TYPE
			     * @return
			     */
			    int count = countLocalType(mxList.get(i).getLOCAL_TYPE(),UID);
			    if(count == 0) {
					paramsT.put("RNVTN_SUBST_STD_DTL_ID",StringUtil.uuid32len());
					paramsT.put("RNVTN_SUBST_STD_ID", UID);
					paramsT.put("LOCAL_TYPE", mxList.get(i).getLOCAL_TYPE());
					paramsT.put("SUBST_AMOUNT", mxList.get(i).getSUBST_AMOUNT());
					paramsT.put("REMARK",mxList.get(i).getREMARK());
					paramsT.put("DEL_FLAG", "0");
					this.insert("Decorationallo.insertChild", paramsT);
			    } else {
			    	return "0";
			    }
			}
		  }
			
		} else {
			params.put("DEL_FLAG", obj.getDEL_FLAG());
			params.put("STATE", obj.getSTATE());
			params.put("RNVTN_SUBST_STD_ID", obj.getRNVTN_SUBST_STD_ID());
			this.update("Decorationallo.updateById", params);
			if(mxList !=null) {
				for(int i=0;i<mxList.size();i++){
					        int count = this.countLocalType(mxList.get(i).getLOCAL_TYPE(),obj.getRNVTN_SUBST_STD_ID());
							paramsT.put("RNVTN_SUBST_STD_ID", obj.getRNVTN_SUBST_STD_ID());
							paramsT.put("LOCAL_TYPE", mxList.get(i).getLOCAL_TYPE());
							paramsT.put("SUBST_AMOUNT", mxList.get(i).getSUBST_AMOUNT());
							paramsT.put("REMARK",mxList.get(i).getREMARK());
							paramsT.put("DEL_FLAG", "0");
							if(StringUtils.isEmpty(mxList.get(i).getRNVTN_SUBST_STD_DTL_ID())){
							   if(count==0) {
						    	  paramsT.put("RNVTN_SUBST_STD_DTL_ID",StringUtil.uuid32len());
						    	  this.insert("Decorationallo.insertChild", paramsT);
							   }else {
									return "0";
							    }
						    }
							else {
								paramsT.put("RNVTN_SUBST_STD_DTL_ID",mxList.get(i).getRNVTN_SUBST_STD_DTL_ID());
						    	this.insert("Decorationallo.updateChild", paramsT);
							}
					}
			  }
		}
		 return "1";
	 }
	 
	 public String insertChild(String RNVTN_SUBST_STD_ID, String TYPE,
			String AMOUNT, String REMARK,List <DrprnvtnsubststddtlModel> mxList) {
		// 查看控制明细同一商场位置类型是否只有一条记录
		 
		if(mxList !=null) {
			    for(int i=0;i<mxList.size()-1;i++){
			    	for  ( int j=mxList.size()-1;j>i;j--){ 
			    		if  (mxList.get(i).getLOCAL_TYPE().equals(mxList.get(j).getLOCAL_TYPE())){
			    			  return "0";
			    		} 
			    	}
			    }
		 }
		int count = this.countLocalType(TYPE,RNVTN_SUBST_STD_ID);
		if(count==0) {
			Map<String, String> paramsT = new HashMap<String, String>();
			paramsT.put("RNVTN_SUBST_STD_DTL_ID", StringUtil.uuid32len());
			paramsT.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
			paramsT.put("LOCAL_TYPE", TYPE);
			paramsT.put("SUBST_AMOUNT", AMOUNT);
			paramsT.put("REMARK", REMARK);
			paramsT.put("DEL_FLAG", "0");
			this.insert("DecorationalloMX.insertChild", paramsT);
		} else {
			return "0";
		}
		return "1";
	}
	 
	  /**
	     * @param RNVTN_SUBST_STD_DTL_ID
	     * @param RNVTN_SUBST_STD_ID
	     * @param TYPE
	     * @param AMOUNT
	     * @param REMARK
	     * @return
	     */
	 public String updateChild(String RNVTN_SUBST_STD_DTL_ID,String RNVTN_SUBST_STD_ID, String TYPE,
				String AMOUNT, String REMARK,List <DrprnvtnsubststddtlModel> mxList) {
			// 查看控制明细同一商场位置类型是否只有一条记录
			//int count = this.countLocalType(TYPE,RNVTN_SUBST_STD_ID);
			//if(count==0) {
				Map<String, String> paramsT = new HashMap<String, String>();
				paramsT.put("RNVTN_SUBST_STD_DTL_ID", RNVTN_SUBST_STD_DTL_ID);
				paramsT.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
				paramsT.put("LOCAL_TYPE", TYPE);
				paramsT.put("SUBST_AMOUNT", AMOUNT);
				paramsT.put("REMARK", REMARK);
				paramsT.put("DEL_FLAG", "0");
				this.insert("DecorationalloMX.updateChild", paramsT);
		    /*
	          } else {
				return "0";
			}*/
			return "1";
		}
	 
	    /**
	     * @param RNVTN_SUBST_STD_ID
	     * @return
	     */
		public Map<String, String> loadByConfId(String RNVTN_SUBST_STD_ID) {
			return (Map<String, String>) load("Decorationallo.loadByConfId",RNVTN_SUBST_STD_ID);
		}
		
		/**
	     * @param RNVTN_SUBST_STD_ID
	     * @return
	     */
		public Map<String, String> loadByConfIdT(String RNVTN_SUBST_STD_ID) {
			return (Map<String, String>) load("Decorationallo.loadByConfIdT",RNVTN_SUBST_STD_ID);
		}
		
		/**
	     * 根据主表Id查询子表记录
	     * Description :.
	     * @param sjzdID the sjzd id
	     * @return the list< sjzd mx model>
	     */
		public List <DrprnvtnsubststddtlModel> childQuery(String RNVTN_SUBST_STD_ID){
		    Map params = new HashMap();
	        params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
	        return this.findList("Decorationallo.queryChildById", params);
		}
		
		 /**
	     * @param RNVTN_SUBST_STD_ID
	     * @return
	     */
        public List <DrprnvtnsubststddtlModel> childQueryT(String RNVTN_SUBST_STD_ID){
		    Map params = new HashMap();
	        params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
	        return this.findList("Decorationallo.queryChildByIdT", params);
		}
		
		 /**
	     * 删除数据
	     * @param DATA_CONF 
	     * @return true, if tx delete
	     */
	    public boolean txDelete(String RNVTN_SUBST_STD_ID, UserBean userBean){
	    	Map<String, String> params = new HashMap<String, String>();
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_ID);
			return update("Decorationallo.delete", params);
	    }
	    
	    /**
	     * 判断同品牌是否只有一条启用状态
	     */
	    public int countFrom(String BRAND,String LEDGER_ID) {
	    	Map<String, String> params = new HashMap<String, String>();
	    	params.put("BRAND", BRAND);
	    	params.put("STATE", "启用");
	    	params.put("DEL_FLAG", "0");
	    	params.put("LEDGER_ID", LEDGER_ID);
			Object count = load("Decorationallo.countFrom", params);
			if (count == null) {
				count = "0";
			}
			return Integer.parseInt(count.toString());
		}
	    
	    /**
	     * 控制明细同一商场位置类型是否只有一条记录
	     * @param LOCAL_TYPE
	     * @return
	     */
	    public int countLocalType(String LOCAL_TYPE,String RNVTN_SUBST_STD_ID){
	    	Map<String, String> params = new HashMap<String, String>();
	    	params.put("LOCAL_TYPE", LOCAL_TYPE);
	    	params.put("RNVTN_SUBST_STD_ID",RNVTN_SUBST_STD_ID);
	    	params.put("DEL_FLAG", "0");
			Object count = load("Decorationallo.countLocalType", params);
			if (count == null) {
				count = "0";
			}
			return Integer.parseInt(count.toString());
	    }

	    
	    /**
	     * 查询主表单条记录详细信息
	     * Description :.
	     * @param bmgzid the bmgzid
	     * @return the map< string, string>
	     */
	    public Map <String, String> load(String RNVTN_SUBST_STD_ID){
	    	return (Map <String, String>) load("Decorationallo.loadById", RNVTN_SUBST_STD_ID);
	    }
	    
	    /**
	     * 状态修改.
	     * @param params the params
	     */
	    public void updateState(Map <String, String> params){
	    	updateById(params);
	    }
        
	    /**
	     * 更新主表记录.
	     * @param params the params
	     * @return update的结果
	     */
	    public boolean updateById(Map <String, String> params) {

	        return update("Decorationallo.updateById", params);
	    }
	    
	    /**
	     * 批量删除.
	     * @param sjzdmxids the sjzdmxids
	     * @param userBean the user bean
	     */
	    public void txBatch4DeleteChild(String RNVTN_SUBST_STD_IDs, UserBean userBean) {

	        Map <String, String> params = new HashMap <String, String>();
	        params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_IDs);
	        params.put("DEL_FLAG", "1");
	        delete("Decorationallo.deleteByIds", RNVTN_SUBST_STD_IDs);
	    }
	    
	    /**
	     * @param RNVTN_SUBST_STD_IDs
	     * @param userBean
	     */
	    public void txBatch4DeleteChild1(String RNVTN_SUBST_STD_IDs, UserBean userBean) {

	        Map <String, String> params = new HashMap <String, String>();
	        params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_IDs);
	        delete("Decorationallo.deleteByIds1", RNVTN_SUBST_STD_IDs);
	    }
	    
	    /**
	     * 根据子表Id批量加载子表信息
	     * Description :.
	     * @param sjzdMxIds the sjzd mx ids
	     * @return the list< sjzd mx model>
	     */
	    public List<DrprnvtnsubststddtlModel> loadChilds(String RNVTN_SUBST_STD_IDs){
	    	return findList("Decorationallo.loadByIds", RNVTN_SUBST_STD_IDs);
	    }
	    
	    /**
	     * @param RNVTN_SUBST_STD_IDs
	     * @return
	     */
	    public List<DrprnvtnsubststddtlModel> loadByIds(String RNVTN_SUBST_STD_IDs){
	    	return findList("DecorationalloMX.loadByIds", RNVTN_SUBST_STD_IDs);
	    }
	    
	    /**
	     * 根据子表Id批量加载子表信息
	     * Description :.
	     * @param sjzdMxIds the sjzd mx ids
	     * @return the list< sjzd mx model>
	     */
	    public List loadChilds1(String RNVTN_SUBST_STD_IDs){
	    	Map params = new HashMap();
	    	List  list = new ArrayList();
	    	params.put("RNVTN_SUBST_STD_ID", RNVTN_SUBST_STD_IDs);
	    	List<Map<String,String>> result = this.findList("Decorationallo.loadByIds1", params);
	    	if(null != result || !result.isEmpty()){
	    		for(int i=0;i<result.size();i++) {
				String RNVTN_SUBST_STD_DTL_ID = result.get(i).get("RNVTN_SUBST_STD_DTL_ID");
				list.add(RNVTN_SUBST_STD_DTL_ID);
	    		}
	    	}
	    	return list;
	    }
	    
	    /**
	     * 查询数据字典信息
	     * @param DATA_DTL_ID
	     * @return
	     */
	    public String queryPro(String DATA_DTL_ID){
	    	   return (String) load("Decorationallo.queryPro", DATA_DTL_ID);
	    }
	    
	    /**
	     * @param DATA_DTL_NAME
	     * @return
	     */
	    public String queryProT(String DATA_DTL_NAME){
	    	return (String) load("Decorationallo.queryProT", DATA_DTL_NAME);
	    }

}
