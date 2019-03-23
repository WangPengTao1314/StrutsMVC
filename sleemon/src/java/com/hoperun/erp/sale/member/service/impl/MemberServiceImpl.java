package com.hoperun.erp.sale.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.member.model.MemberActModel;
import com.hoperun.erp.sale.member.model.MemberCardModel;
import com.hoperun.erp.sale.member.model.MemberModel;
import com.hoperun.erp.sale.member.model.MemberPointModel;
import com.hoperun.erp.sale.member.service.MemberService;
import com.hoperun.sys.model.UserBean;

public class MemberServiceImpl extends BaseService implements MemberService{
	
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Member.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Member.delete", params);
		 //删除子表 
		 update("Member.delMemberCardByFkId", params);
		 //删除子表 
		 return update("Member.delMemberActByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Member.updateById", params);
	}
	
	@Override
	public Map<String, Object> load(String MARKETING_ACT_ID) {
		return (Map<String,Object>) load("Member.loadById", MARKETING_ACT_ID);
	}

	 /**
     * * 根据主表Id查询子表活动记录
     */
	public List<MemberActModel> memberActQuery(String MEMBER_ID){
		 Map<String,String> params = new HashMap<String,String>();
         params.put("MEMBER_ID", MEMBER_ID);
         //只查询0的记录。1为删除。0为正常
 		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
         return this.findList("Member.queryMemberActByFkId", params);
	}
	
	public List<MemberActModel> loadMemberActModels(Map<String, String> params) {
		return findList("Member.loadMemberActByIds",params);
	}
	
	
	 /**
     * * 根据主表Id查询子表记录
     */
    public List <MemberCardModel> memberCardQuery(String MEMBER_ID){
    	  Map<String,String> params = new HashMap<String,String>();
          params.put("MEMBER_ID", MEMBER_ID);
          //只查询0的记录。1为删除。0为正常
  		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          return this.findList("Member.queryMemberCardByFkId", params);
    	
    }
    
    /**
     * * 积分流水
     */
    public List <MemberPointModel> memberPointQuery(String MEMBER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("MEMBER_ID", MEMBER_ID);
         //只查询0的记录。1为删除。0为正常
 		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
         return this.findList("Member.queryMemberPointByFkId", params);
    }
    
    
	public List<MemberCardModel> loadMemberCardModels(Map<String, String> params) {
		return findList("Member.loadMemberCardByIds",params);
	}

	
	
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Member.pageQuery", "Member.pageCount",params, pageNo);
	}

	/**
	 * 卡券删除
	 */
	public void txBatch4DeleteMemberCard(Map<String, String> params) {
        update("Member.deleteMemberCardByIds", params);
	}

	 /**
     * 活动子表的批量删除
     */
    public void txBatch4DeleteMemberAct(Map<String,String>params){
    	update("Member.deleteMemberActByIds", params);
    }
    

	/**
	 * 编辑
	 */
	public String txEdit(String MEMBER_ID, MemberModel model,
			List<MemberActModel> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("MEMBER_NAME",model.getMEMBER_NAME());
		params.put("MOBILE_PHONE",model.getMOBILE_PHONE());
		params.put("SEX",model.getSEX());
		params.put("POINT_VALUE",model.getPOINT_VALUE());
		params.put("CHANN_ID",model.getCHANN_ID());
		params.put("CHANN_NO",model.getCHANN_NO());
		params.put("CHANN_NAME",model.getCHANN_NAME());
		params.put("BIRTHDAY",model.getBIRTHDAY());
		params.put("HOBBY",model.getHOBBY());
		params.put("ADDRESS",model.getADDRESS());
		params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(MEMBER_ID)){
			MEMBER_ID = StringUtil.uuid32len();
			String MEMBER_NO = LogicUtil.getBmgz("ERP_MEMBER_NO_NO");
			params.put("MEMBER_ID",MEMBER_ID);
			params.put("MEMBER_NO",MEMBER_NO);
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
		    params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);//制定
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		}else{
			params.put("MEMBER_ID",MEMBER_ID);
			params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			txUpdateById(params);
			clearCaches(MEMBER_ID);
		}
		
		if(null != chldList && !chldList.isEmpty()){
			txMemberActEdit(MEMBER_ID,chldList,userBean);
		}
		return null;
	}

	/**
	 * 参加活动编辑
	 * @param MEMBER_ID
	 * @param chldList
	 * @param userBean
	 * @return
	 */
	public boolean txMemberActEdit(String MEMBER_ID, List<MemberActModel> chldList,
			UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for(MemberActModel child : chldList){
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("MEMBER_ID", MEMBER_ID);
        	params.put("MARKETING_ACT_ID", child.getMARKETING_ACT_ID());
        	params.put("MARKETING_ACT_NO", child.getMARKETING_ACT_NO());
        	params.put("MARKETING_ACT_NAME", child.getMARKETING_ACT_NAME());
        	params.put("START_DATE", child.getSTART_DATE());
        	params.put("END_DATE", child.getEND_DATE());
        	params.put("SPONSOR_NAME", child.getSPONSOR_NAME());
        	
        	 //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(child.getMEMBER_ACT_DTL_ID())) {
	        	params.put("MEMBER_ACT_DTL_ID", StringUtil.uuid32len());
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("MEMBER_ACT_DTL_ID", child.getMEMBER_ACT_DTL_ID());
	            updateList.add(params);
	        }
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Member.updateMemberActById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Member.insertMemberAct", addList);
        }
        
		return true;
	}

	/**
	 * 卡券编辑
	 */
	public boolean txMemberCardEdit(String MEMBER_ID, List<MemberCardModel> listCard,
			UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for(MemberCardModel child : listCard){
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("MEMBER_ID", MEMBER_ID);
        	params.put("MEMBER_ACT_DTL_ID", child.getMEMBER_ACT_DTL_ID());
        	params.put("MARKETING_CARD_ID", child.getMARKETING_CARD_ID());
        	params.put("MARKETING_CARD_NO", child.getMARKETING_CARD_NO());
        	params.put("CARD_TYPE", child.getCARD_TYPE());
        	params.put("CARD_VALUE", child.getCARD_VALUE());
        	params.put("CRE_TIME", child.getCRE_TIME());
        	 //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(child.getMEMBER_CARD_DTL_ID())) {
	        	params.put("MEMBER_CARD_DTL_ID", StringUtil.uuid32len());
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("MEMBER_CARD_DTL_ID", child.getMEMBER_CARD_DTL_ID());
	            updateList.add(params);
	        }
	        
        	
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Member.updateMemberCardById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Member.insertMemberCard", addList);
        }
        
        
		return true;
	}

	 /**
     * 更新状态
     * @param params
     */
    public void txUpdateSate(Map<String,String>params){
    	this.txUpdateById(params);
    }
	 
    /**
     * 查找已经参加的活动ID
     * @param MEMBER_ID
     * @return
     */
    public String queryActIdsByFkId(String MEMBER_ID){
    	Map<String,String>paramMap = new HashMap<String,String>();
    	paramMap.put("MEMBER_ID", MEMBER_ID);
    	List<Map<String,String>> list = this.findList("Member.queryActIdsByFkId", paramMap);
    	if(null != list && !list.isEmpty()){
    		return StringUtil.nullToSring(list.get(0).get("ACT_DTLS"));
    	}
    	return "";
    }
	
    /**
     * 查询已发送的卡券ID
     * @param MEMBER_ID
     * @return
     */
    public String queryCardIdsByFkId(String MEMBER_ID){
    	Map<String,String>paramMap = new HashMap<String,String>();
    	paramMap.put("MEMBER_ID", MEMBER_ID);
    	List<Map<String,String>> list = this.findList("Member.queryCardIdsByFkId", paramMap);
    	if(null != list && !list.isEmpty()){
    		return StringUtil.nullToSring(list.get(0).get("CARD_DTLS"));
    	}
    	return "";
    }
    

}
