package com.hoperun.drp.oamg.rnvtnreform.service.impl;

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
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformChildModel;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.drp.oamg.rnvtnreform.service.RnvtnreformService;
import com.hoperun.sys.model.UserBean;

public class RnvtnreformServiceImpl extends BaseService implements  RnvtnreformService{

	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtnreform.pageQuery","rnvtnreform.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtnreform.pageQueryT","rnvtnreform.pageCountT", params, pageNo);
    }
	
    /**
	 * @param RNVTN_REFORM_ID
	 * @param obj
	 * @param userBean
	 * @param mxList
	 * @return
	 */
	public String txEdit(String RNVTN_REFORM_ID, RnvtnreformModel obj,
			UserBean userBean,List<RnvtnreformChildModel>  mxList, String chkLen) {
		
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		
		params.put("RNVTN_CHECK_NO", obj.getRNVTN_CHECK_NO());
		params.put("CHANN_RNVTN_ID", obj.getCHANN_RNVTN_ID());
		params.put("CHANN_RNVTN_NO", obj.getCHANN_RNVTN_NO());
	    params.put("CHECK_CHARGE",obj.getCHECK_CHARGE());
	    params.put("CHECK_TIME", obj.getCHECK_TIME());
	    params.put("PROCESS_CHARGE", obj.getPROCESS_CHARGE());
	    params.put("TERM_ID", obj.getTERM_ID());
	    params.put("TERM_NO", obj.getTERM_NO());
		params.put("TERM_NAME",obj.getTERM_NAME());
		params.put("RNVTN_PROP",obj.getRNVTN_PROP());
		params.put("PLAN_SBUSS_DATE",obj.getPLAN_SBUSS_DATE());
		params.put("SALE_STORE_ID",obj.getSALE_STORE_ID());
		params.put("SALE_STORE_NAME",obj.getSALE_STORE_NAME());
		params.put("SALE_STORE_AREA",obj.getSALE_STORE_AREA());
		params.put("LOCAL_TYPE",obj.getLOCAL_TYPE()); 
		params.put("BUSS_SCOPE",obj.getBUSS_SCOPE());
		params.put("REMARK", obj.getREMARK());
		
		if(mxList !=null) {
		    for(int i=0;i<mxList.size()-1;i++){
		    	for  ( int j=mxList.size()-1;j>i;j--){ 
		    		if  (mxList.get(i).getPRJ_TYPE().equals(mxList.get(j).getPRJ_TYPE()) && mxList.get(i).getPRJ_NAME().equals(mxList.get(j).getPRJ_NAME())){
		    			  return "1";
		    		} 
		    	}
		    }
		}

		if (StringUtils.isEmpty(obj.getRNVTN_REFORM_ID())) {
			String UID = StringUtil.uuid32len();
			params.put("RNVTN_REFORM_ID", UID);
			String RNVTN_REFORM_NO=LogicUtil.getBmgz("DRP_RNVTN_REFORM_NO");	
			params.put("RNVTN_REFORM_NO",RNVTN_REFORM_NO);
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM());// 制单人名称
			params.put("CRE_TIME", DateUtil.now());// 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());// 制单机构id
			params.put("ORG_NAME", userBean.getJGMC());// 制单机构名称
			params.put("DEL_FLAG", "0");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			params.put("STATE", "待整改");
			this.insert("rnvtnreform.insert", params);
            if(mxList !=null) {
		    for(int i=0;i<mxList.size();i++){
				 paramsT.put("RNVTN_REFORM_DTL_ID",StringUtil.uuid32len());
				 paramsT.put("RNVTN_REFORM_ID", UID);
    			 paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE());   //验收项目类型
 				 paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME());   //验收项目名称
     			 paramsT.put("PRJ_SCORE",mxList.get(i).getPRJ_SCORE());  //项目分值
     			 paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
     			 paramsT.put("CHECK_REMARK",mxList.get(i).getCHECK_REMARK());
				 paramsT.put("DEL_FLAG", "0");
             	 List<RnvtnreformChildModel> result = queryTypeAndName(mxList.get(i).getPRJ_TYPE().toString(),mxList.get(i).getPRJ_NAME().toString(),UID);
             	 if(result.size()==0) {
				     this.insert("rnvtnreform.insertChild", paramsT);
             	 } else {
             		 return "1";
             	 }
			 }
            }

		} else {
			params.put("DEL_FLAG", obj.getDEL_FLAG());
			params.put("RNVTN_REFORM_ID", obj.getRNVTN_REFORM_ID());
			this.update("rnvtnreform.updateById", params);

			if (mxList != null) {
				if(!chkLen.equals(mxList.size())) {
					List<RnvtnreformChildModel> list = queryDtlByReformId(RNVTN_REFORM_ID);
					for(int j=0;j<list.size();j++){
						RnvtnreformChildModel  model = (RnvtnreformChildModel)list.get(j);
						String RNVTN_REFORM_DTL_ID = model.getRNVTN_REFORM_DTL_ID().toString();
                        this.txDeleteChild(RNVTN_REFORM_DTL_ID);
					}
				}
				for (int i = 0; i < mxList.size(); i++) {
					paramsT.put("RNVTN_REFORM_ID", obj.getRNVTN_REFORM_ID());
					paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE()); // 验收项目类型
					paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME()); // 验收项目名称
					paramsT.put("PRJ_SCORE", mxList.get(i).getPRJ_SCORE()); // 项目分值
					paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
					paramsT.put("CHECK_REMARK", mxList.get(i).getCHECK_REMARK());
					paramsT.put("DEL_FLAG", "0");
	             	
					List<RnvtnreformChildModel> result = queryTypeAndName(mxList.get(i).getPRJ_TYPE().toString(),mxList.get(i).getPRJ_NAME().toString(),RNVTN_REFORM_ID);
					if(StringUtils.isEmpty(mxList.get(i).getRNVTN_REFORM_DTL_ID())){
						if(result.size()==0){
						   paramsT.put("RNVTN_REFORM_DTL_ID", StringUtil.uuid32len());
						   this.insert("rnvtnreform.insertChild", paramsT);
						} else{
							return "1";
						}
					} else {
						if(!chkLen.equals(mxList.size())) {
							paramsT.put("RNVTN_REFORM_DTL_ID", StringUtil.uuid32len());
					        this.insert("rnvtnreform.insertChild", paramsT);
						} else {
						    paramsT.put("RNVTN_REFORM_DTL_ID", mxList.get(i).getRNVTN_REFORM_DTL_ID());
					        this.insert("rnvtnreform.updateChild", paramsT);
						}
					}
				  }
				} 
		     }
		   return "0";
	}
	
	/**
     * @param RNVTN_REFORM_ID
     * @return
     */
	public Map<String, String> loadByConfId(String RNVTN_REFORM_ID) {
		return (Map<String, String>) load("rnvtnreform.loadByConfId",RNVTN_REFORM_ID);
	}
	
	 /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_REFORM_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
		return update("rnvtnreform.delete", params);
    }
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformChildModel> childQuery(String RNVTN_REFORM_ID){
    	    Map params = new HashMap();
	        params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
	        return this.findList("rnvtnreform.queryChildById", params);
    }
 

	public void updateState(String RNVTN_REFORM_ID, RnvtnreformModel obj,
			UserBean userBean, String STATE) {
		Map params = new HashMap();
		params.put("STATE",STATE);
		params.put("RNVTN_REFORM_ID",RNVTN_REFORM_ID);
		this.update("rnvtnreform.updateState",params);
	}
	
	 /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformChildModel> loadChilds(String RNVTN_REFORM_DTL_IDs){
    	return findList("rnvtnreformMX.loadByIds", RNVTN_REFORM_DTL_IDs);
    }
    
    /**
     * 查询验收明细中是否存在重复记录
     * @param type
     * @param name
     * @return
     */
    public List<RnvtnreformChildModel> queryTypeAndName(String type,String name,String RNVTN_REFORM_ID){
    	Map <String, String> params = new HashMap <String, String>();
    	params.put("PRJ_TYPE", type);
    	params.put("PRJ_NAME", name);
    	params.put("RNVTN_REFORM_ID",RNVTN_REFORM_ID);
    	return this.findList("rnvtnreformMX.queryTypeAndName",params);
    }
    
    /**
     * @param RNVTN_REFORM_DTL_IDs
     * @return
     */
    public List loadChilds1(String RNVTN_REFORM_DTL_IDs){
    	Map params = new HashMap();
    	List list = new ArrayList();
    	params.put("RNVTN_REFORM_ID", RNVTN_REFORM_DTL_IDs);
    	List<Map<String,String>> result = this.findList("rnvtnreform.loadByIds1", params);
    	if(null != result || !result.isEmpty()){
    		for(int i=0;i<result.size();i++) {
				String RNVTN_REFORM_DTL_ID = result.get(i).get("RNVTN_REFORM_DTL_ID");
				list.add(RNVTN_REFORM_DTL_ID);
    		}
    	}
    	return list;
    }
    
    /**
     * 批量删除.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_REFORM_DTL_IDs, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_REFORM_DTL_ID", RNVTN_REFORM_DTL_IDs);
        params.put("DEL_FLAG", "1");
        delete("rnvtnreform.deleteByIds",params);
    }
    
    /**
     * @param RNVTN_REFORM_DTL_IDs
     * @param userBean
     */
    public void txBatch4DeleteChild1(String RNVTN_REFORM_DTL_IDs, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_REFORM_DTL_ID", RNVTN_REFORM_DTL_IDs);
        delete("rnvtnreform.deleteByIds1", RNVTN_REFORM_DTL_IDs);
    }
    
    /**
     * @param RNVTN_REFORM_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     */
    public String insertChild(String RNVTN_REFORM_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,List <RnvtnreformChildModel> modelList){
    	
    	if(modelList !=null) {
		    for(int i=0;i<modelList.size()-1;i++){
		    	for  ( int j=modelList.size()-1;j>i;j--){ 
		    		if  (modelList.get(i).getPRJ_TYPE().equals(modelList.get(j).getPRJ_TYPE()) && modelList.get(i).getPRJ_NAME().equals(modelList.get(j).getPRJ_NAME())){
		    			  return "1";
		    		} 
		    	}
		    }
		}
    	Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("RNVTN_REFORM_DTL_ID", StringUtil.uuid32len());
		paramsT.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("DEL_FLAG", "0");
		this.insert("rnvtnreform.insertChild", paramsT);
		return "0";
    }
    
    /**
     * @param RNVTN_REFORM_DTL_ID
     * @param RNVTN_REFORM_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     */
    public void updateChild(String RNVTN_REFORM_DTL_ID,String RNVTN_REFORM_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK){
    	
    	Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("RNVTN_REFORM_DTL_ID", RNVTN_REFORM_DTL_ID);
		paramsT.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("DEL_FLAG", "0");
		this.insert("rnvtnreform.updateChild", paramsT);
    	
    }
    
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
    public  List<RnvtnreformChildModel>  queryDtlByReformId(String RNVTN_REFORM_ID){
    	Map <String, String> params = new HashMap <String, String>();
    	params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
    	params.put("DEL_FLAG", "0");
    	return this.findList("rnvtnreformMX.loadById",params);
    }
    
    /**
     * @param RNVTN_REFORM_DTL_ID
     */
    public void txDeleteChild(String RNVTN_REFORM_DTL_ID) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_REFORM_DTL_ID", RNVTN_REFORM_DTL_ID);
        params.put("DEL_FLAG", "1");
        delete("rnvtnreformMX.delete",params);
    }
}
