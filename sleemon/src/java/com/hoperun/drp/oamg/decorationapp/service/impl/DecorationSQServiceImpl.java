package com.hoperun.drp.oamg.decorationapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.decorationapp.service.DecorationSQService;
import com.hoperun.sys.model.UserBean;

public class DecorationSQServiceImpl extends BaseService implements DecorationSQService {

	/**
	 * @param CHANN_RNVTN_ID
	 * @param obj
	 * @param userBean
	 * @param SelType
	 */
	
	/*
	public void txEdit(String CHANN_RNVTN_ID, DecorationappModel obj, UserBean userBean,String SelType) {

		Map<String, String> params = new HashMap<String, String>();

		params.put("RNVTN_REQ_NAME", obj.getRNVTN_REQ_NAME()); //申请人
		if(obj.getCHANN_NO().equals("")){
			params.put("CHANN_ID", obj.getCHANN_ID()); //渠道ID
		} else {
			params.put("CHANN_ID", obj.getCHANN_NO()); //渠道ID
		}
		params.put("CHANN_NO", obj.getCHANN_NO()); //渠道编号
		params.put("CHANN_NAME", obj.getCHANN_NAME()); //渠道名称
		params.put("CHANN_PERSON_CON", obj.getCHANN_PERSON_CON()); //渠道联系人
		params.put("CHANN_TEL", obj.getCHANN_TEL()); //渠道联系电话
		params.put("TERM_ID", obj.getTERM_ID()); //终端ID
		params.put("TERM_NO", obj.getTERM_NO()); //终端编号
		params.put("TERM_NAME", obj.getTERM_NAME()); //终端名称
		params.put("STATE", obj.getSTATE()); //状态
		params.put("AREA_ID", obj.getAREA_ID());
		params.put("AREA_NO", obj.getAREA_NO()); //所属战区编号
		params.put("AREA_NAME", obj.getAREA_NAME()); //所属战区名称
		params.put("SALE_STORE_ID", obj.getSALE_STORE_ID());     //卖场ID
		params.put("SALE_STORE_NAME", obj.getSALE_STORE_NAME()); //卖场名称
		params.put("ZONE_ID", obj.getZONE_ID()); //行政区域
		params.put("ADDRESS", obj.getADDRESS()); //详细地址
		params.put("BUSS_SCOPE", this.queryName(obj.getBUSS_SCOPE())); //经营品牌
		params.put("BEAR_WAY", obj.getBEAR_WAY());
		params.put("SPEC_CONTENT", obj.getSPEC_CONTENT());

		if (!obj.getLOCAL_TYPE().equals("")) {
			params.put("LOCAL_TYPE", this.queryName(obj.getLOCAL_TYPE())); //商场位置类别
		} else {
			params.put("LOCAL_TYPE", "");
		}
		params.put("PLAN_SBUSS_DATE", obj.getPLAN_SBUSS_DATE()); //计划开业时间
		params.put("DMD_DRAW_DATE", obj.getDMD_DRAW_DATE()); //要求出图时间
		params.put("USE_AREA", obj.getUSE_AREA().toString()); //上报使用面积
		params.put("TERM_WHICH_NUM", obj.getTERM_WHICH_NUM().toString()); //一场多店信息
		params.put("MARKET_RENT", obj.getMARKET_RENT().toString());       //商场租金
		params.put("EXP_YEAR_SALE", obj.getEXP_YEAR_SALE().toString());   //预计年销量
		params.put("SALE_STORE_ANALYSE", obj.getSALE_STORE_ANALYSE());    //商场情况分析
        params.put("RNVTN_PROP", obj.getRNVTN_PROP());                    //装修性质
        params.put("RNVTN_REQ_DATE", obj.getRNVTN_REQ_DATE());            //申请时间
        params.put("COMPACT_AMOUNT", obj.getCOMPACT_AMOUNT());            //合同签订金额

		if (!obj.getREIT_POLICY().equals("")) {
			params.put("REIT_POLICY", obj.getREIT_POLICY()); //报销政策
		} else {
			params.put("REIT_POLICY", "");
		}
		//是否标准内
		params.put("IS_STAD_FLAG", obj.getIS_STAD_FLAG());
		params.put("REIT_AMOUNT_PS", obj.getREIT_AMOUNT_PS().toString()); //报销金额
		params.put("REIT_AMOUNT", obj.getREIT_AMOUNT().toString());
		params.put("REMARK", obj.getREMARK()); //特殊说明
		params.put("AREA_MANAGE_NAME", obj.getAREA_MANAGE_NAME());   //区域经理
		params.put("AREA_MANAGE_TEL", obj.getAREA_MANAGE_TEL());     //区域经理电话
        params.put("ZONE_ADDR",obj.getZONE_ADDR()); //行政区划
		params.put("CREATOR", userBean.getXTYHID());//制单人ID
		params.put("CRE_NAME", userBean.getXM());//制单人名称
		params.put("CRE_TIME", DateUtil.now());//制单时间
		params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
		params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
		params.put("ORG_ID", userBean.getJGXXID());//制单机构id
		params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
		if (StringUtils.isEmpty(CHANN_RNVTN_ID)) {
			params.put("DEL_FLAG", "0");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			String UID = StringUtil.uuid32len();
			params.put("CHANN_RNVTN_ID", UID);
			String CHANN_RNVTN_NO =LogicUtil.getBmgz("DRP_CHANN_RNVTN_NO");	  //装修申请单号
			params.put("CHANN_RNVTN_NO", CHANN_RNVTN_NO);
			this.insert("Decorationapp.insert", params);
			
			String att_path = loadAtt(UID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getPIC_PATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
			
		} else {
			params.put("DEL_FLAG", obj.getDEL_FLAG());
			params.put("CHANN_RNVTN_ID", obj.getCHANN_RNVTN_ID());
			
			List<DecorationappModel>  list = this.queryReit(CHANN_RNVTN_ID);
			Map<String, String> paramsT = new HashMap<String, String>();
			
		    if(SelType.equals("1")){      
		    	for(int i=0;i<list.size();i++){
					DecorationappModel  model = (DecorationappModel)list.get(i);
					String RNVTN_REIT_DTL_ID = model.getRNVTN_REIT_DTL_ID();
					this.update("Decorationapp.updateReitById",RNVTN_REIT_DTL_ID);
				}
	        	List<DecorationappModel>  result = queryDecorT(paramsT);
	        	for(int t=0;t<result.size();t++){
	        		int countT = queryReitDtl(CHANN_RNVTN_ID);
	        		DecorationappModel  model = (DecorationappModel)result.get(t);
	        		paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
	        		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
	        		paramsT.put("RNVTN_REIT_NO", String.valueOf(countT+1));
	        		paramsT.put("RNVTN_SCALE", model.getDATA_DTL_DES_1());
	        		paramsT.put("REIT_REMARK", model.getREMARK());
	        		paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        		insert("Decorationapp.insertReitDtl",paramsT);
	        	} 
		    }
		    
		    if(SelType.equals("5")){
	        	List<DecorationappModel>  result = queryDecorT(paramsT);
	        	for(int t=0;t<result.size();t++){
	        		int countT = queryReitDtl(CHANN_RNVTN_ID);
	        		DecorationappModel  model = (DecorationappModel)result.get(t);
	        		paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
	        		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
	        		paramsT.put("RNVTN_REIT_NO", String.valueOf(countT+1));
	        		paramsT.put("RNVTN_SCALE", model.getDATA_DTL_DES_1());
	        		paramsT.put("REIT_REMARK", model.getREMARK());
	        		paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        		insert("Decorationapp.insertReitDtl",paramsT);
	        	} 
		    }
		    
		    if(SelType.equals("4")){     //特殊政策转常规政策
		    	for(int i=0;i<list.size();i++){
					DecorationappModel  model = (DecorationappModel)list.get(i);
					String RNVTN_REIT_DTL_ID = model.getRNVTN_REIT_DTL_ID();
					this.update("Decorationapp.updateReitById",RNVTN_REIT_DTL_ID);
				}
	        	List<DecorationappModel>  result = queryDecorT(paramsT);
	        	for(int t=0;t<result.size();t++){
	        		int countT = queryReitDtl(CHANN_RNVTN_ID);
	        		DecorationappModel  model = (DecorationappModel)result.get(t);
	        		paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
	        		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
	        		paramsT.put("RNVTN_REIT_NO", String.valueOf(countT+1));
	        		paramsT.put("RNVTN_SCALE", model.getDATA_DTL_DES_1());
	        		paramsT.put("REIT_REMARK", model.getREMARK());
	        		paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        		insert("Decorationapp.insertReitDtl",paramsT);
	        	} 
		    }
			this.update("Decorationapp.updateById", params);
			
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",CHANN_RNVTN_ID);
			attParamsT.put("ATT_PATH", obj.getPIC_PATH());//图片路径
			update("Att.updateAtt", attParamsT);
		}
	}*/
	
	public void txEdit(String CHANN_RNVTN_ID, DecorationappModel obj, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();

		params.put("RNVTN_REQ_NAME", obj.getRNVTN_REQ_NAME()); //申请人
	    params.put("CHANN_ID", obj.getCHANN_ID()); //渠道ID
		params.put("CHANN_NO", obj.getCHANN_NO()); //渠道编号
		params.put("CHANN_NAME", obj.getCHANN_NAME()); //渠道名称
		params.put("CHANN_PERSON_CON", obj.getCHANN_PERSON_CON()); //渠道联系人
		params.put("CHANN_TEL", obj.getCHANN_TEL()); //渠道联系电话
		params.put("TERM_ID", obj.getTERM_ID()); //终端ID
		params.put("TERM_NO", obj.getTERM_NO()); //终端编号
		params.put("TERM_NAME", obj.getTERM_NAME()); //终端名称
		params.put("STATE", obj.getSTATE()); //状态
		params.put("AREA_ID", obj.getAREA_ID());
		params.put("AREA_NO", obj.getAREA_NO()); //所属战区编号
		params.put("AREA_NAME", obj.getAREA_NAME()); //所属战区名称
		params.put("SALE_STORE_ID", obj.getSALE_STORE_ID());     //卖场ID
		params.put("SALE_STORE_NAME", obj.getSALE_STORE_NAME()); //卖场名称
		params.put("ZONE_ID", obj.getZONE_ID()); //行政区域
		params.put("ADDRESS", obj.getADDRESS()); //详细地址
		params.put("BUSS_SCOPE", obj.getBUSS_SCOPE()); //经营品牌
		params.put("BEAR_WAY", obj.getBEAR_WAY());
		params.put("SPEC_CONTENT", obj.getSPEC_CONTENT());
	    params.put("LOCAL_TYPE",   obj.getLOCAL_TYPE());
		params.put("PLAN_SBUSS_DATE", obj.getPLAN_SBUSS_DATE()); //计划开业时间
		params.put("DMD_DRAW_DATE", obj.getDMD_DRAW_DATE()); //要求出图时间
		params.put("USE_AREA", obj.getUSE_AREA().toString()); //上报使用面积
		params.put("TERM_WHICH_NUM", obj.getTERM_WHICH_NUM()); //一场多店信息
		params.put("MARKET_RENT", obj.getMARKET_RENT());       //商场租金
		params.put("EXP_YEAR_SALE", obj.getEXP_YEAR_SALE());   //预计年销量
		params.put("SALE_STORE_ANALYSE", obj.getSALE_STORE_ANALYSE());    //商场情况分析
        params.put("RNVTN_PROP", obj.getRNVTN_PROP());                    //装修性质
        params.put("RNVTN_REQ_DATE", obj.getRNVTN_REQ_DATE());            //申请时间
        params.put("COMPACT_AMOUNT", obj.getCOMPACT_AMOUNT());            //合同签订金额

		params.put("IS_STAD_FLAG", obj.getIS_STAD_FLAG());   //报销标准
		params.put("REIT_AMOUNT_PS", obj.getREIT_AMOUNT_PS()); //报销金额
		params.put("REIT_AMOUNT", obj.getREIT_AMOUNT());
		params.put("REMARK", obj.getREMARK()); //特殊说明
		params.put("AREA_MANAGE_NAME", obj.getAREA_MANAGE_NAME());   //区域经理
		params.put("AREA_MANAGE_TEL", obj.getAREA_MANAGE_TEL());     //区域经理电话
        params.put("ZONE_ADDR",obj.getZONE_ADDR()); //行政区划
		params.put("CREATOR", userBean.getXTYHID());//制单人ID
		params.put("CRE_NAME", userBean.getXM());//制单人名称
		params.put("CRE_TIME", DateUtil.now());//制单时间
		params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
		params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
		params.put("ORG_ID", userBean.getJGXXID());//制单机构id
		params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
		params.put("OPEN_TERMINAL_REQ_ID",  obj.getOPEN_TERMINAL_REQ_ID());
		params.put("OPEN_TERMINAL_REQ_NO",  obj.getOPEN_TERMINAL_REQ_NO());
		params.put("DESIGN_PERSON", obj.getDESIGN_PERSON());
		params.put("DESIGN_ID", obj.getDESIGN_ID());
		params.put("NATION", obj.getNATION());
		params.put("PROV", obj.getPROV());
		params.put("CITY", obj.getCITY());
		params.put("COUNTY", obj.getCOUNTY());
		params.put("BUDGET_QUOTA_ID", obj.getBUDGET_QUOTA_ID());
		params.put("BUDGET_ITEM_ID", obj.getBUDGET_ITEM_ID());
		params.put("BUDGET_ITEM_NO", obj.getBUDGET_ITEM_NO());
		params.put("BUDGET_ITEM_NAME", obj.getBUDGET_ITEM_NAME());
		params.put("BUDGET_ITEM_TYPE", obj.getBUDGET_ITEM_TYPE());
		params.put("AMOUNT_DESC", obj.getAMOUNT_DESC());    //金额说明
		
		if (StringUtils.isEmpty(CHANN_RNVTN_ID)) {
			params.put("DEL_FLAG", "0");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			String UID = StringUtil.uuid32len();
			params.put("CHANN_RNVTN_ID", UID);
			String CHANN_RNVTN_NO =LogicUtil.getBmgz("DRP_CHANN_RNVTN_NO");	  //装修申请单号
			params.put("CHANN_RNVTN_NO", CHANN_RNVTN_NO);
			params.put("STATE", "未提交");
			this.insert("Decorationapp.insert", params);
			
			String att_path = loadAtt(CHANN_RNVTN_ID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getSALE_STORE_ANALYSE()+";"+obj.getXIANCHANGPIC()+";"+obj.getMARKETPIC());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
		}  else {
			params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
			List<DecorationappModel>  list = this.queryReit(CHANN_RNVTN_ID);
			Map<String, String> paramsT = new HashMap<String, String>();
			this.update("Decorationapp.updateById", params);
			
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",CHANN_RNVTN_ID);
			attParamsT.put("ATT_PATH",obj.getSALE_STORE_ANALYSE()+";"+obj.getXIANCHANGPIC()+";"+obj.getMARKETPIC());//图片路径
			update("Att.updateAtt", attParamsT);
		}
	}
	
	/**
     * @param CHANN_RNVTN_ID
     * @param LOCAL_TYPE
     * @param COMPACT_AMOUNT
     * @param IS_STAD_FLAG
     * @param REIT_AMOUNT_PS
     * @param REIT_AMOUNT
     */	
	public void updateByRnvtnId(String CHANN_RNVTN_ID,String LOCAL_TYPE,String COMPACT_AMOUNT,String IS_STAD_FLAG,String REIT_AMOUNT_PS,String REIT_AMOUNT){
		 Map <String, String> params = new HashMap <String, String>();
		 params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		 params.put("LOCAL_TYPE", LOCAL_TYPE);
		 params.put("COMPACT_AMOUNT", COMPACT_AMOUNT);
		 params.put("IS_STAD_FLAG", IS_STAD_FLAG);
		 params.put("REIT_AMOUNT_PS", REIT_AMOUNT_PS);
		 params.put("REIT_AMOUNT", REIT_AMOUNT);
		 this.update("Decorationapp.updateById", params);
	}
	
	/**
     * @param CHANN_RNVTN_ID
     * @param COMPACT_AMOUNT
     * @param IS_STAD_FLAG
     * @param REIT_AMOUNT_PS
     * @param REIT_AMOUNT
     */
	public void updateByRnvtnIdT(String CHANN_RNVTN_ID,String COMPACT_AMOUNT,String IS_STAD_FLAG,String REIT_AMOUNT_PS,String REIT_AMOUNT){
		 Map <String, String> params = new HashMap <String, String>();
		 params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		 params.put("COMPACT_AMOUNT", COMPACT_AMOUNT);
		 params.put("IS_STAD_FLAG", IS_STAD_FLAG);
		 params.put("REIT_AMOUNT_PS", REIT_AMOUNT_PS);
		 params.put("REIT_AMOUNT", REIT_AMOUNT);
		 this.update("Decorationapp.updateById", params);
		
	}
	
	 public  String getNum(String TERM_WHICH_NUM){
		 
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("DATA_DTL_CODE", TERM_WHICH_NUM);
		 String str = (String)load("Decorationapp.queryNum", params);
		 if(str.equals("单一商场内新增第一家品牌店")){
			 str="1";
		 }
		 if(str.equals("单一商场内新增第二家品牌店")){
			 str="2";
		 }
		 if(str.equals("单一商场内新增第三家品牌店")){
			 str="3";
		 }
		 if(str.equals("单一商场内新增第四家及以上品牌店")){
			 str="4";
		 }
		 return str;
	 }
	
	 /**
     * 根据装修申请单号修改报销频次
     * @param CHANN_RNVTN_ID
     * @param REIT_POLICY
     */
	 public  void updatePolicy(String CHANN_RNVTN_ID,String REIT_POLICY){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		 params.put("REIT_POLICY", REIT_POLICY);
		 this.update("Decorationapp.updateById", params);
	 }
	
	/**
     * 查询报销次数
     * @param CHANN_RNVTN_ID
     * @return
     */
	public  int  queryReitDtl(String CHANN_RNVTN_ID){
		return queryForInt("Decorationapp.queryReitDtl",CHANN_RNVTN_ID);
	}
	
	/**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public  int queryRvinState(String CHANN_RNVTN_ID){
		return queryForInt("Decorationapp.queryRvinState",CHANN_RNVTN_ID);
	}
	
	/**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public  int   getRowcount(String CHANN_RNVTN_ID){
		return queryForInt("Decorationapp.getRowcount",CHANN_RNVTN_ID);
	}
	
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID){
		return queryForInt("Decorationapp.queryZW",RYXXID);
    }
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZWT(String RYXXID,String GZZXXID){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("RYXXID", RYXXID);
    	params.put("GZZXXID", GZZXXID);
    	return queryForInt("Decorationapp.queryZWT",params);
    }
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryStoreManage(String RYXXID){
    	return queryForInt("Decorationapp.queryStoreManage",RYXXID);
    }
	
	/**
	 * @param CHANN_RNVTN_ID
	 * @param userBean
	 */
	public void txReitDtl(String CHANN_RNVTN_ID, UserBean userBean){
		
		Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		String str = (String)load("Decorationapp.loadByIdT", paramsT);
		if(str.equals("常规政策")){
			
			int countT = queryReitDtl(CHANN_RNVTN_ID);
        	List<DecorationappModel>  result = queryDecorT(paramsT);
        	for(int t=0;t<result.size();t++){
        		DecorationappModel  model = (DecorationappModel)result.get(t);
        		paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
        		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
        		paramsT.put("RNVTN_REIT_NO", String.valueOf(countT+1));
        		paramsT.put("RNVTN_SCALE", model.getRNVTN_SCALE());
        		paramsT.put("REIT_REMARK", model.getREIT_REMARK());
        		paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        		insert("Decorationapp.insertReitDtl",paramsT);
        	} 
		}
	}
	
	public void  txEditReit(String CHANN_RNVTN_ID,String RNVTN_SCALE,String REIT_REMARK,String SelType){
		
		List<DecorationappModel>  list = this.queryReit(CHANN_RNVTN_ID);
		Map<String, String> paramsT = new HashMap<String, String>();
		
		if(SelType.equals("1")){      //常规政策转特殊政策
			for(int i=0;i<list.size();i++){
				DecorationappModel  model = (DecorationappModel)list.get(i);
				String RNVTN_REIT_DTL_ID = model.getRNVTN_REIT_DTL_ID();
				this.update("Decorationapp.updateReitById",RNVTN_REIT_DTL_ID);
			}
			 String[] scales  = RNVTN_SCALE.split(",");
		     String[] remarks = REIT_REMARK.split(",");
		     for(int t=0;t<scales.length;t++){
		    	 int count  = queryReitDtl(CHANN_RNVTN_ID);
        		 paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
        		 paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
        		 paramsT.put("RNVTN_REIT_NO", String.valueOf(count+1));
        		 paramsT.put("RNVTN_SCALE", scales[t]);
        		 paramsT.put("REIT_REMARK", remarks[t]);
        		 paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        		 insert("Decorationapp.insertReitDtl",paramsT);
		     }
		}
		
		if(SelType.equals("3")){     //特殊政策
			
			 String[] scales  = RNVTN_SCALE.split(",");
		     String[] remarks = REIT_REMARK.split(",");
		     for(int t=0;t<scales.length;t++){
		    	 int count  = queryReitDtl(CHANN_RNVTN_ID);
        		 paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
        		 paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
        		 paramsT.put("RNVTN_REIT_NO", String.valueOf(count+1));
        		 paramsT.put("RNVTN_SCALE", scales[t]);
        		 paramsT.put("REIT_REMARK", remarks[t]);
        		 paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        		 insert("Decorationapp.insertReitDtl",paramsT);
		    }
		}
		
		if(SelType.equals("6")){     //特殊政策
			 String[] scales  = RNVTN_SCALE.split(",");
		     String[] remarks = REIT_REMARK.split(",");
		     for(int t=0;t<scales.length;t++){
		     int count  = queryReitDtl(CHANN_RNVTN_ID);
       		 paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
       		 paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
       		 paramsT.put("RNVTN_REIT_NO", String.valueOf(count+1));
       		 paramsT.put("RNVTN_SCALE", scales[t]);
       		 paramsT.put("REIT_REMARK", remarks[t]);
       		 paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
       		 insert("Decorationapp.insertReitDtl",paramsT);
		    }
		}
		
	    if(SelType.equals("4")){     //特殊政策转常规政策
	    	if(list.size()==0){
	    		int count = queryReitDtl(CHANN_RNVTN_ID);
	    		Map<String, String> params = new HashMap<String, String>();
				params.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
				params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
				params.put("RNVTN_SCALE", RNVTN_SCALE);
				params.put("REIT_REMARK",REIT_REMARK);
				params.put("RNVTN_REIT_NO",String.valueOf(count+1));  
				params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
				insert("Decorationapp.insertReitDtl",params);
	    		
	    	} else {
		    	for(int i=0;i<list.size();i++){
					DecorationappModel  model = (DecorationappModel)list.get(i);
					String RNVTN_REIT_DTL_ID = model.getRNVTN_REIT_DTL_ID();
					this.update("Decorationapp.updateReitById",RNVTN_REIT_DTL_ID);
				}
	        	List<DecorationappModel>  result = queryDecorT(paramsT);
	        	for(int t=0;t<result.size();t++){
	        		int count  = queryReitDtl(CHANN_RNVTN_ID);
	        		DecorationappModel  model = (DecorationappModel)result.get(t);
	        		paramsT.put("RNVTN_REIT_DTL_ID", StringUtil.uuid32len());
	        		paramsT.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
	        		paramsT.put("RNVTN_REIT_NO", String.valueOf(count+1));
	        		paramsT.put("RNVTN_SCALE", model.getRNVTN_SCALE());
	        		paramsT.put("REIT_REMARK", model.getREIT_REMARK());
	        		paramsT.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        		insert("Decorationapp.insertReitDtl",paramsT);
	        	} 
	    	}
	    }
	}

	/**
	 * 分页查询
	 * Description :.
	 * @param params the params
	 * @param pageNo the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Decorationapp.pageQuery",
				"Decorationapp.pageCount", params, pageNo);
	}
	
	/**
     * @param params
     * @param pageNo
     * @param CHANN_RNVTN_ID
     * @return
     */
	public IListPage queryDecor(Map<String, String> params, int pageNo,String CHANN_RNVTN_ID){
        params.put("CHANN_RNVTN_ID",CHANN_RNVTN_ID);
        return this.pageQuery("Decorationapp.queryDecor","Decorationapp.pageCount", params, pageNo);
	}
	
	/**
     * @param params
     * @return
     */
    public List<DecorationappModel>  queryDecorT(Map <String, String> params){
        return this.findList("Decorationapp.queryDecorT",params);
    }
	
    /**
     * @param params
     * @param pageNo
     * @return
     */
	public IListPage pageQueryT(Map<String, String> params, int pageNo) {
		 
	    params.put("STATE",params.get("STATE"));
		return this.pageQuery("Decorationapp.pageQuery",
				"Decorationapp.pageCount", params, pageNo);
	}

	 /**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public Map <String, String> loadTypeByConfId(String CHANN_RNVTN_ID){
		return (Map<String, String>) load("Decorationapp.loadTypeByConfId",
				CHANN_RNVTN_ID);
	}

	/**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public Map<String, String> loadByConfId(String CHANN_RNVTN_ID) {
		return (Map<String, String>) load("Decorationapp.loadByConfId",
				CHANN_RNVTN_ID);
	}
	
	 /**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public Map <String, String> loadByConfIdF(String CHANN_RNVTN_ID) {
		return (Map<String, String>) load("Decorationapp.loadByConfIdF",
				CHANN_RNVTN_ID);
	}
	
	/**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadT(String CHANN_RNVTN_ID){
    	return (Map<String, String>) load("Decorationapp.loadT",
				CHANN_RNVTN_ID);
    }
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadTt(String CHANN_RNVTN_ID){
    	return (Map<String, String>) load("Decorationapp.loadTt",
				CHANN_RNVTN_ID);
    }
	
	/**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public Map<String, String> loadByConfIdT(String CHANN_RNVTN_ID) {
		return (Map<String, String>) load("Decorationapp.loadByConfIdT",
				CHANN_RNVTN_ID);
	}
	
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
	public Map<String, String> loadByConfIdTt(String CHANN_RNVTN_ID) {
		return (Map<String, String>) load("Decorationapp.loadByConfIdTt",
				CHANN_RNVTN_ID);
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
    
	/**
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public List<DecorationappModel> queryReit(String CHANN_RNVTN_ID){
		Map params = new HashMap();
		params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		return this.findList("Decorationapp.queryReit",
				params);
	}
	
    /**
     * @param CHANN_RNVTN_ID
     */
	public void updateState(String CHANN_RNVTN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		params.put("STATE", "提交");
		this.update("Decorationapp.updState", params);
	}
	
    /**
     * @param USE_AREA
     * @param LOCAL_TYPE
     * @param TERM_ID
     */
	public  void  updateTerm(Integer USE_AREA,String LOCAL_TYPE,String TERM_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("LOCAL_TYPE", LOCAL_TYPE);
		params.put("BUSS_AREA", String.valueOf(USE_AREA));
		params.put("TERM_ID", TERM_ID);
		this.update("Decorationapp.updateTerm", params);
	}

	/**
	 * 删除数据
	 * @param DATA_CONF 
	 * @return true, if tx delete
	 */
	public boolean txDelete(String CHANN_RNVTN_ID, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		return update("Decorationapp.delete", params);
	}
	
	/**
     * @param BUSS_SCOPE
     * @param LEDGER_ID
     * @param LOCAL_TYPE
     * @return
     */
	public List queryByBussScope(String BUSS_SCOPE,String LEDGER_ID,String LOCAL_TYPE) {
		Map params = new HashMap();
		params.put("BRAND", BUSS_SCOPE);
		params.put("LEDGER_ID", LEDGER_ID);
		params.put("LOCAL_TYPE", LOCAL_TYPE);
		params.put("STATE", "启用");
		return  this.findList("Decorationallo.queryByBussScope", params);
		 
   }
	
	/**
     * @param TERM_WHICH_NUM
     * @return
     */
	public  List<String> queryTermWhichNum(String TERM_WHICH_NUM){
		Map params = new HashMap();
		params.put("DATA_NAME", "一场多店信息");
		if(TERM_WHICH_NUM.equals("1")){
			params.put("DATA_DTL_NAME","单一商场内新增第一家品牌店");
		} else if(TERM_WHICH_NUM.equals("2")){
			params.put("DATA_DTL_NAME","单一商场内新增第二家品牌店");
		} else if(TERM_WHICH_NUM.equals("3")){
			params.put("DATA_DTL_NAME","单一商场内新增第三家品牌店");
		} else if(TERM_WHICH_NUM.equals("4")){
			params.put("DATA_DTL_NAME","单一商场内新增第四家及以上品牌店");
		}
		params.put("TERM_WHICH_NUM", TERM_WHICH_NUM);
		return  this.findList("Decorationallo.queryTermWhichNum", params);
	}
	
	/**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("CHANN_RNVTN_ID");
		
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
	 * 根据ID查询相应数据
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public  String  loadById(String CHANN_RNVTN_ID){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		 String str = (String)load("Decorationapp.loadByIdT", params);
		 return str;
	}
	
	/**
	 * 查询名称
	 * @param  LOCAL_TYPE
	 * @return
	 */
	public  String  queryName(String LOCAL_TYPE){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("DATA_DTL_CODE", LOCAL_TYPE);
		 String str = (String)load("Decorationapp.queryNum", params);
		 return str;
	}
	
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public String toQRnvtnPropt(String RNVTN_PROP){
    	if(!RNVTN_PROP.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("DATA_DTL_NAME", RNVTN_PROP);
			List<Map<String,Object>>  list =  this.findList("Decorationapp.selectBrothers1", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("CODE"));
	        return map1.get("CODE").toString();
		} else {
			return null;
		}
    }
    
    public String toQLocalType(String LOCAL_TYPE){
    	if(!LOCAL_TYPE.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("DATA_DTL_NAME", LOCAL_TYPE);
			List<Map<String,Object>>  list =  this.findList("Decorationapp.selectBrothers1", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("CODE"));
	        return map1.get("CODE").toString();
		} else {
			return null;
		}
    }
    
    /**
     * @param BUSS_SCOPE
     * @return
     */
    public String toQBussScope(String BUSS_SCOPE){
    	if(!BUSS_SCOPE.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("DATA_DTL_NAME", BUSS_SCOPE);
			List<Map<String,Object>>  list =  this.findList("Decorationapp.selectBrothers1", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("CODE"));
	        return map1.get("CODE").toString();
		} else {
			return null;
		}
    	
    }
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public String toReQRnvtnPropt(String RNVTN_PROP){
    	if(!RNVTN_PROP.equals("")){
			Map<String, String> params = new HashMap<String, String>();
			params.put("RNVTN_PROP", RNVTN_PROP);
			List<Map<String,Object>>  list =  this.findList("Decorationapp.reSelectBrothers", params);
			Map<String, Object> map1 = list.get(0);
	        System.out.println(map1.get("RNVTN_PROP"));
	        return map1.get("RNVTN_PROP").toString();
		} else {
			return null;
		}
    }
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public String queryReitAmountPS(String CHANN_RNVTN_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		List<Map<String, Object>> list = this.findList("Decorationapp.queryReitAmountPS", params);
		if (null != list && !list.isEmpty()) {
			Map<String, Object> map1 = list.get(0);
			if (map1.get("REIT_AMOUNT_PS") != null) {
				return String.valueOf(map1.get("REIT_AMOUNT_PS").toString());
			}
		}
		return null;
    }
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Decorationapp.expertExcel",params);
	}
	
	/**
     * 获取经营品牌
     * @return
     */
    public List<DecorationappModel>  queryBussScopeT(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryBussScope",params);
    }
    
	/**
     * 获取装修性质
     * @return
     */
    public List<DecorationappModel>  queryRnvtnProp(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryRnvtnProp",params);
    }
    
    
    /**
     * 获取商场位置类别
     * @return
     */
    public List<DecorationappModel>  queryLocalType(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryLocalType",params);
    }
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public String queryReitAmount(String CHANN_RNVTN_ID){
    	Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_RNVTN_ID",CHANN_RNVTN_ID);
        return (String) load("Decorationapp.queryReitAmount", params);
    }
    
    /**
     * @return
     */
    public String queryGZZXXID(){
    	Map<String, String> params = new HashMap<String, String>();
        return (String) load("Decorationapp.queryGZZXXID", params);	
    }
    
    public void txStoreManage(String CHANN_RNVTN_ID) throws Exception{
    	CreditCtrlUtil.txRnvtnReqAcct(CHANN_RNVTN_ID,"0");  //冻结预批金额
     }
    
    /**
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public List <DecorationappModel> childQuery(String CHANN_RNVTN_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Decorationapp.queryById", params);
	}

}
