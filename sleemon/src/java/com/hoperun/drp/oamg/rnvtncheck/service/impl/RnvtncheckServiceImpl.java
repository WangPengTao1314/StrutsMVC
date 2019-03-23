package com.hoperun.drp.oamg.rnvtncheck.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.rnvtncheck.model.RnvtncheckChildModel;
import com.hoperun.drp.oamg.rnvtncheck.model.RnvtncheckModel;
import com.hoperun.drp.oamg.rnvtncheck.service.RnvtncheckService;
import com.hoperun.sys.model.UserBean;

public class RnvtncheckServiceImpl extends BaseService implements
		RnvtncheckService {

	
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtncheck.pageQuery","rnvtncheck.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtncheck.pageQueryT","rnvtncheck.pageCountT", params, pageNo);
    }
	
    /**
	 * @param RNVTN_CHECK_ID
	 * @param obj
	 * @param userBean
	 * @param mxList
	 * @return
	 */
	public String txEdit(String RNVTN_CHECK_ID, RnvtncheckModel obj,
			UserBean userBean,List <RnvtncheckChildModel>  mxList,String chk) {
		String [] strChk = chk.split(",");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		String UID = "";
		params.put("CHANN_RNVTN_ID", obj.getCHANN_RNVTN_ID());
		params.put("CHANN_RNVTN_NO", obj.getCHANN_RNVTN_NO());
		params.put("RNVTN_PRINCIPAL",obj.getRNVTN_PRINCIPAL());
	    params.put("RNVTN_CHECK_DATE", obj.getRNVTN_CHECK_DATE());
	    params.put("TERM_ID",obj.getTERM_ID());
	    params.put("TERM_NO",obj.getTERM_NO());
		params.put("TERM_NAME",obj.getTERM_NAME());
		params.put("RNVTN_PROP",obj.getRNVTN_PROP());
		params.put("PLAN_SBUSS_DATE",obj.getPLAN_SBUSS_DATE());
		params.put("AREA_ID",obj.getAREA_ID());
		params.put("AREA_NO",obj.getAREA_NO());
		params.put("AREA_NAME", obj.getAREA_NAME()); 
		params.put("AREA_MANAGE_ID",obj.getAREA_MANAGE_ID());
		params.put("AREA_MANAGE_NAME",obj.getAREA_MANAGE_NAME());
		params.put("AREA_MANAGE_TEL", obj.getAREA_MANAGE_TEL());
		params.put("RNVTN_PRINCIPAL", obj.getRNVTN_PRINCIPAL());
        params.put("RNVTN_CHECK_DATE", obj.getRNVTN_CHECK_DATE());
        params.put("SALE_STORE_ID", obj.getSALE_STORE_ID());
        params.put("SALE_STORE_NAME", obj.getSALE_STORE_NAME());
        params.put("ZONE_ADDR", obj.getZONE_ADDR());
		params.put("LOCAL_TYPE", obj.getLOCAL_TYPE());
		params.put("CHECK_SCORE",obj.getCHECK_SCORE());
		params.put("CHECK_REMARK",obj.getCHECK_REMARK());
		params.put("PUNISH_REMARK", obj.getPUNISH_REMARK());
		params.put("REMARK", obj.getREMARK());
		params.put("BUSS_SCOPE", obj.getBUSS_SCOPE());
		
		if(mxList !=null) {
		    for(int i=0;i<mxList.size()-1;i++){
		    	for  ( int j=mxList.size()-1;j>i;j--){ 
		    		if  (mxList.get(i).getPRJ_TYPE().equals(mxList.get(j).getPRJ_TYPE()) && mxList.get(i).getPRJ_NAME().equals(mxList.get(j).getPRJ_NAME())){
		    			  return "1";
		    		} 
		    	}
		    }
		}
	 
		if (StringUtils.isEmpty(RNVTN_CHECK_ID)) {
		    UID = StringUtil.uuid32len();
			params.put("RNVTN_CHECK_ID", UID);
			String RNVTN_CHECK_NO=LogicUtil.getBmgz("DRP_RNVTN_CHECK_NO");	
			params.put("RNVTN_CHECK_NO",RNVTN_CHECK_NO);
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM());// 制单人名称
			params.put("CRE_TIME", DateUtil.now());// 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());// 制单机构id
			params.put("ORG_NAME", userBean.getJGMC());// 制单机构名称
			params.put("DEL_FLAG", "0");
			params.put("STATE", "制定");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			this.insert("rnvtncheck.insert", params);
						
            if(mxList !=null) {
		    for(int i=0;i<mxList.size();i++){
				 paramsT.put("RNVTN_CHECK_DTL_ID",StringUtil.uuid32len());
				 paramsT.put("RNVTN_CHECK_ID", UID);
    			 paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE());   //验收项目类型
 				 paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME());   //验收项目名称
     			 paramsT.put("PRJ_SCORE",mxList.get(i).getPRJ_SCORE());  //项目分值
     			 paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
     			 paramsT.put("CHECK_REMARK",mxList.get(i).getCHECK_REMARK());
				 paramsT.put("DEL_FLAG", "0");
				 paramsT.put("IS_REFORM_FLAG",String.valueOf(strChk[i]));
				 List<RnvtncheckChildModel> count =  queryTypeAndName(mxList.get(i).getPRJ_TYPE().toString(),mxList.get(i).getPRJ_NAME().toString(), UID);
				 if(count.size()==0){
				     this.insert("rnvtncheck.insertChild", paramsT);
				 } else{
					 return "1";
				 }
			 }
            }

		} else {
			params.put("DEL_FLAG", obj.getDEL_FLAG());
			params.put("RNVTN_CHECK_ID",obj.getRNVTN_CHECK_ID());
			params.put("RNVTN_CHECK_NO",obj.getRNVTN_CHECK_NO());
			this.update("rnvtncheck.updateById", params);
		    if(mxList !=null) {
			    for(int i=0;i<mxList.size();i++){
			    	 paramsT.put("RNVTN_CHECK_ID", obj.getRNVTN_CHECK_ID());
	    			 paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE());   //验收项目类型
	 				 paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME());   //验收项目名称
	     			 paramsT.put("PRJ_SCORE",mxList.get(i).getPRJ_SCORE());  //项目分值
	     			 paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
	     			 paramsT.put("CHECK_REMARK",mxList.get(i).getCHECK_REMARK());
					 paramsT.put("DEL_FLAG", "0");
					 paramsT.put("IS_REFORM_FLAG", String.valueOf(strChk[i]));
					 List<RnvtncheckChildModel> count =  queryTypeAndName(mxList.get(i).getPRJ_TYPE().toString(),mxList.get(i).getPRJ_NAME().toString(),obj.getRNVTN_CHECK_ID());
					 //if(count.size()==0){
						 if(StringUtils.isEmpty(mxList.get(i).getRNVTN_CHECK_DTL_ID())){
							 if(count.size()==0){
								paramsT.put("RNVTN_CHECK_DTL_ID",StringUtil.uuid32len());
								this.insert("rnvtncheck.insertChild", paramsT);
							 }else{
								 return "1";
							 }
						 } else {
							 paramsT.put("RNVTN_CHECK_DTL_ID",mxList.get(i).getRNVTN_CHECK_DTL_ID());
							 this.insert("rnvtncheck.updateChild", paramsT);
						 }
				     }
	             }
		} 
		if(StringUtils.isNotEmpty(obj.getPIC_PATH())){
			params.put("PIC_PATH", obj.getPIC_PATH());//图片路径
			txInsertAtt(params);
		}	
		return "0";
	}
	
	/**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("RNVTN_CHECK_ID");
		
		//查询是否存在
		String att_path = loadAtt(fromBillId);
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", fromBillId);
		attParams.put("ATT_PATH", params.get("PIC_PATH"));//图片路径
		if(StringUtils.isEmpty(att_path)){			
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME"));
			attParams.put("CRE_TIME", DateUtil.now());//制单时间
			attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
			insert("Att.insertAtt", attParams);
		}else{
			update("Att.updateAtt", attParams);
		}
		
        return true;
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
	
   /**
     * @param obj
     * @param userBean
     * @param RNVTN_CHECK_ID
     */
	public void txEditReform(RnvtncheckModel obj,UserBean userBean,String RNVTN_CHECK_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("RNVTN_CHECK_ID", obj.getRNVTN_CHECK_ID());
		params.put("RNVTN_CHECK_NO", obj.getRNVTN_CHECK_NO());
		params.put("CHANN_RNVTN_ID", obj.getCHANN_RNVTN_ID());
		params.put("CHANN_RNVTN_NO", obj.getCHANN_RNVTN_NO());
		params.put("CHECK_CHARGE", obj.getRNVTN_PRINCIPAL());
		params.put("TERM_ID", obj.getTERM_ID());
		params.put("TERM_NO", obj.getTERM_NO());
		params.put("TERM_NAME", obj.getTERM_NAME());
		params.put("RNVTN_PROP", obj.getRNVTN_PROP());
		SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd");
		String planDateStr  = null;
		String checkDateStr = null;
		try {
			Date   planDate    = sdf.parse(obj.getPLAN_SBUSS_DATE());
		    planDateStr = sdf.format(planDate);
		    Date   checkDate   = sdf.parse(obj.getRNVTN_CHECK_DATE());
		    checkDateStr= sdf.format(checkDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		params.put("PLAN_SBUSS_DATE", planDateStr);
		params.put("CHECK_TIME", checkDateStr);
		params.put("SALE_STORE_ID", obj.getSALE_STORE_ID());
		params.put("SALE_STORE_NAME", obj.getSALE_STORE_NAME());
		params.put("LOCAL_TYPE", obj.getLOCAL_TYPE());
		params.put("BUSS_SCOPE", obj.getBUSS_SCOPE());
		params.put("SPCL_STORE_TYPE", obj.getSPCL_STORE_TYPE());
		params.put("STATE", obj.getSTATE());
		String UID = StringUtil.uuid32len();
		params.put("RNVTN_REFORM_ID", UID);
		String RNVTN_REFORM_NO =LogicUtil.getBmgz("DRP_RNVTN_REFORM_NO");	
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
		this.insert("rnvtnreform.insert", params);
		
		Map<String, String> paramsT = new HashMap<String, String>();
		List<RnvtncheckChildModel> result = this.queryReformTtl(obj.getRNVTN_CHECK_ID());
		if(result !=null){
			for(int i=0;i<result.size();i++){
				paramsT.put("RNVTN_REFORM_DTL_ID", StringUtil.uuid32len());
				paramsT.put("RNVTN_REFORM_ID", UID);
				RnvtncheckChildModel  child = (RnvtncheckChildModel)result.get(i);
				paramsT.put("RNVTN_CHECK_DTL_ID", child.getRNVTN_CHECK_DTL_ID());
				paramsT.put("PRJ_TYPE",child.getPRJ_TYPE());
				paramsT.put("PRJ_NAME",child.getPRJ_NAME());
				paramsT.put("PRJ_SCORE",child.getPRJ_SCORE());
				paramsT.put("CHECK_SCORE",child.getCHECK_SCORE());
				paramsT.put("CHECK_REMARK",child.getCHECK_REMARK());
				paramsT.put("DEL_FLAG", "0");
				if(child.getIS_REFORM_FLAG() !=null) {
					if(child.getIS_REFORM_FLAG().toString().equals("1")) {
					  this.insert("rnvtnreformMX.insertChild", paramsT);
					}
				}
			}
		}else {
			paramsT.put("RNVTN_REFORM_DTL_ID", StringUtil.uuid32len());
			paramsT.put("RNVTN_REFORM_ID", UID);
			paramsT.put("DEL_FLAG", "0");
			this.insert("rnvtnreformMX.insertChild", paramsT);
		}
	}
	
    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
	public Map<String, String> loadByConfId(String RNVTN_CHECK_ID) {
		return (Map<String, String>) load("rnvtncheck.loadByConfId",RNVTN_CHECK_ID);
	}
	
	 /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_CHECK_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
		return update("rnvtncheck.deleteCheck", params);
    }
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtncheckChildModel> childQuery(String RNVTN_CHECK_ID){
	    Map params = new HashMap();
        params.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
        return this.findList("rnvtncheck.queryChildById", params);
    }
    
    /**
     * 根据RNVTN_CHECK_ID查询
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckChildModel> queryTtl(String RNVTN_CHECK_ID){
    	Map params = new HashMap();
        params.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
        return this.findList("rnvtncheckMX.queryTtl", params);
    }
    
    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckChildModel> queryReformTtl(String RNVTN_CHECK_ID){
    	Map params = new HashMap();
        params.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
        return this.findList("rnvtncheckMX.queryReformTtl", params);
    }

    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckModel> childQueryT(String RNVTN_CHECK_ID){
    	Map params = new HashMap();
        params.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
        return this.findList("rnvtncheck.queryChildByIdT", params);
    }
    
    /**
     * @param RNVTN_CHECK_ID
     * @param obj
     * @param userBean
     * @param STATE
     */
	public void updateState(String RNVTN_CHECK_ID, RnvtncheckModel obj,
			UserBean userBean, String STATE) {
		Map params = new HashMap();
		params.put("STATE",STATE);
		params.put("RNVTN_CHECK_ID",RNVTN_CHECK_ID);
		this.update("rnvtncheck.updateById",params);
	}
	
	 /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<RnvtncheckChildModel> loadChilds(String RNVTN_CHECK_DTL_IDs){
    	return findList("rnvtncheckMX.loadByIds", RNVTN_CHECK_DTL_IDs);
    }
    
    /**
     * 根据验收单ID查询相关信息 
     */
    public List<RnvtncheckModel>  queryByCheckId(String RNVTN_CHECK_ID){
    	return findList("rnvtncheck.loadByCheckId", RNVTN_CHECK_ID);
    }
    
    /**
     * @param RNVTN_CHECK_DTL_IDs
     * @return
     */
    public List loadChilds1(String RNVTN_CHECK_IDs){
    	Map params = new HashMap();
    	List list = new ArrayList();
    	params.put("RNVTN_CHECK_ID", RNVTN_CHECK_IDs);
    	List<Map<String,String>> result = this.findList("rnvtncheck.loadByIds1", params);
    	if(null != result || !result.isEmpty()){
    		for(int i=0;i<result.size();i++) {
			String RNVTN_CHECK_DTL_ID = result.get(i).get("RNVTN_CHECK_DTL_ID");
			System.out.print("RNVTN_CHECK_DTL_ID-----"+RNVTN_CHECK_DTL_ID);
			list.add(RNVTN_CHECK_DTL_ID);
    		}
    	}
    	return list;
    }
    
    /**
     * 批量删除.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_CHECK_DTL_IDs, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_CHECK_DTL_ID", RNVTN_CHECK_DTL_IDs);
        params.put("DEL_FLAG", "1");
        update("rnvtncheck.delete", params);
    }
    
    /**
     * 子表的批量删除
     * Description :.
     * @param bmgzMxIds the bmgz mx ids
     * @param bmgzId the bmgz id
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild1(String RNVTN_CHECK_DTL_IDs, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_CHECK_DTL_ID", RNVTN_CHECK_DTL_IDs);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
        this.update("rnvtncheck.deleteByIds1",params);
    }
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_CHECK_DTL_IDS, String RNVTN_CHECK_ID,UserBean userBean){
    	
    	Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_CHECK_DTL_ID", RNVTN_CHECK_DTL_IDS);
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
        update("rnvtncheckMX.deleteByIds",params);
    }
    
    /**
     * 批量删除.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChildT(String RNVTN_CHECK_DTL_IDs, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("RNVTN_CHECK_DTL_ID", RNVTN_CHECK_DTL_IDs);
        delete("rnvtncheck.deleteByIdsT", RNVTN_CHECK_DTL_IDs);
    }
    
    /**
     * 更新主表记录.
     * @param params the params
     * @return update的结果
     */
    public boolean updateById(Map <String, String> params) {
        return update("rnvtncheck.updateById", params);
    }
    
    /**
     * 查询验收明细中是否存在重复记录
     * @param type
     * @param name
     * @return
     */
    public List<RnvtncheckChildModel> queryTypeAndName(String type,String name,String RNVTN_CHECK_ID){
    	Map <String, String> params = new HashMap <String, String>();
    	params.put("PRJ_TYPE", type);
    	params.put("PRJ_NAME", name);
    	params.put("RNVTN_CHECK_ID",RNVTN_CHECK_ID);
    	return this.findList("rnvtncheckMX.queryTypeAndName",params);
    }
    
    /**
     * @param RNVTN_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     * @param IS_REFORM_FLAG
     * @return
     */
    public String insertChild(String RNVTN_CHECK_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,String IS_REFORM_FLAG){
    	
    	Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("RNVTN_CHECK_DTL_ID", StringUtil.uuid32len());
		paramsT.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("IS_REFORM_FLAG",IS_REFORM_FLAG);
		paramsT.put("DEL_FLAG", "0");
		
    	List<RnvtncheckChildModel> result = queryTypeAndName(PRJ_TYPE,PRJ_NAME,RNVTN_CHECK_ID);
    	if(result.size()==0){
		  this.insert("rnvtncheck.insertChild", paramsT);
    	}else{
    		return "1";
    	}
    	return "0";
    }
    
    /**
     * @param RNVTN_CHECK_DTL_ID
     * @param RNVTN_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     * @param IS_REFORM_FLAG
     * @return
     */
    public String updateChild(String RNVTN_CHECK_DTL_ID,String RNVTN_CHECK_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,String IS_REFORM_FLAG){
    	
    	Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("RNVTN_CHECK_DTL_ID", RNVTN_CHECK_DTL_ID);
		paramsT.put("RNVTN_CHECK_ID", RNVTN_CHECK_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("IS_REFORM_FLAG",IS_REFORM_FLAG);
		paramsT.put("DEL_FLAG", "0");
		this.insert("rnvtncheck.updateChild", paramsT);
		return "0";
    }
    
	/**
	 * 查询名称
	 * @param  RNVTN_PROP
	 * @return
	 */
	public  String  queryName(String RNVTN_PROP){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("DATA_DTL_CODE", RNVTN_PROP);
		 String str = (String)load("rnvtncheck.queryNum", params);
		 return str;
	}
    
	 /**
	 * @param localType
	 * @return
	 */
	public List<DecorationappModel> loadByDictionary(String localType){
		Map params = new HashMap();
		params.put("DATA_NAME", localType);
		return this.findList("Decorationapp.loadByDictionary",
				params);
	}
}
