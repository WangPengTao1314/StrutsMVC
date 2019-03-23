package com.hoperun.drp.oamg.decorationapp.model;


import com.hoperun.commons.model.BaseModel;

/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2013-08-13 14:01:22
 */

public class DecorationappModel  extends BaseModel {
     
	public String getTERM_WHICH_NUM() {
		return TERM_WHICH_NUM;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**装修申请单ID**/
	private String  CHANN_RNVTN_ID;
	/**装修申请单编号**/
	private String  CHANN_RNVTN_NO;
	/**渠道ID**/
	private String  CHANN_ID;
	/**渠道编号**/
	private String  CHANN_NO;
	/**渠道名称**/
	private String  CHANN_NAME;
	/**终端信息ID**/
	private String  TERM_ID;
	/**终端编号**/
	private String  TERM_NO;
	/**终端名称**/
	private String  TERM_NAME;
	/**装修申请人ID**/
	private String  RNVTN_REQ_ID;
	/**装修申请人姓名**/
	private String  RNVTN_REQ_NAME;
	/**渠道联系人**/
	private String  CHANN_PERSON_CON;
	/**渠道电话**/
	private String  CHANN_TEL;
	/**区域ID**/
	private String  AREA_ID;
	/**区域编号**/
	private String  AREA_NO;
	/**区域名称**/
	private String     AREA_NAME;
	/**区域经理ID**/
	private String     AREA_MANAGE_ID;
	/**区域经理名称**/
	private String     AREA_MANAGE_NAME;
	/**区域经理电话**/
	private String     AREA_MANAGE_TEL;
	/**卖场ID**/
	private String     SALE_STORE_ID;
	/**卖场名称**/
	private String     SALE_STORE_NAME;
	/**行政区划ID**/
	private String     ZONE_ID;
	/**行政区划地址**/
	private String      ZONE_ADDR;
	/**经营范围(经营品牌)**/
	private String      BUSS_SCOPE;
	/**详细地址**/
	private String      ADDRESS;
	/**商场位置类别**/
	private String      LOCAL_TYPE;
	/**计划开业时间**/
	private String       PLAN_SBUSS_DATE;
	/**要求出图时间**/
	private String        DMD_DRAW_DATE;
	/**上报使用面积(不含公摊)**/
	private String     USE_AREA;
	/**商场店铺数**/
	private String     TERM_WHICH_NUM;
	/**装修面积**/
	private String     RNVTN_AREA;
	/**商场租金**/
	private String     MARKET_RENT;
	
	private String     BEAR_WAY;
	
	private String     SPEC_CONTENT;
	
	/**现场照片**/
	private String     XIANCHANGPIC;
	/**商场综合平面图**/
	private String     MARKETPIC;
	/**新开门店ID**/
    private String     OPEN_TERMINAL_REQ_ID;
    /**新开门店编号**/
    private String     OPEN_TERMINAL_REQ_NO;
    /**设计师**/
    private String     DESIGN_PERSON;
    /**国家**/
    private String     NATION;
    /**省份**/
    private String     PROV;
    /**城市**/
    private String     CITY;
    /**区县**/
    private String     COUNTY;

    private String     BUDGET_ITEM_ID;
    private String     BUDGET_ITEM_NO;
    private String     BUDGET_ITEM_NAME;
    private String     BUDGET_ITEM_TYPE;
    private String     BUDGET_QUOTA_ID;
    private String     DESIGN_ID;
    
    /**金额说明**/
    private String     AMOUNT_DESC;
    
	public String getAMOUNT_DESC() {
		return AMOUNT_DESC;
	}
	public void setAMOUNT_DESC(String amount_desc) {
		AMOUNT_DESC = amount_desc;
	}
	public String getDESIGN_ID() {
		return DESIGN_ID;
	}
	public void setDESIGN_ID(String design_id) {
		DESIGN_ID = design_id;
	}
	public String getBUDGET_ITEM_ID() {
		return BUDGET_ITEM_ID;
	}
	public void setBUDGET_ITEM_ID(String budget_item_id) {
		BUDGET_ITEM_ID = budget_item_id;
	}
	public String getBUDGET_ITEM_NO() {
		return BUDGET_ITEM_NO;
	}
	public void setBUDGET_ITEM_NO(String budget_item_no) {
		BUDGET_ITEM_NO = budget_item_no;
	}
	public String getBUDGET_ITEM_NAME() {
		return BUDGET_ITEM_NAME;
	}
	public void setBUDGET_ITEM_NAME(String budget_item_name) {
		BUDGET_ITEM_NAME = budget_item_name;
	}
	public String getBUDGET_ITEM_TYPE() {
		return BUDGET_ITEM_TYPE;
	}
	public void setBUDGET_ITEM_TYPE(String budget_item_type) {
		BUDGET_ITEM_TYPE = budget_item_type;
	}
	public String getBUDGET_QUOTA_ID() {
		return BUDGET_QUOTA_ID;
	}
	public void setBUDGET_QUOTA_ID(String budget_quota_id) {
		BUDGET_QUOTA_ID = budget_quota_id;
	}
	public String getOPEN_TERMINAL_REQ_ID() {
		return OPEN_TERMINAL_REQ_ID;
	}
	public void setOPEN_TERMINAL_REQ_ID(String open_terminal_req_id) {
		OPEN_TERMINAL_REQ_ID = open_terminal_req_id;
	}
	public String getOPEN_TERMINAL_REQ_NO() {
		return OPEN_TERMINAL_REQ_NO;
	}
	public void setOPEN_TERMINAL_REQ_NO(String open_terminal_req_no) {
		OPEN_TERMINAL_REQ_NO = open_terminal_req_no;
	}
	public String getDESIGN_PERSON() {
		return DESIGN_PERSON;
	}
	public void setDESIGN_PERSON(String design_person) {
		DESIGN_PERSON = design_person;
	}
	public String getNATION() {
		return NATION;
	}
	public void setNATION(String nation) {
		NATION = nation;
	}
	public String getPROV() {
		return PROV;
	}
	public void setPROV(String prov) {
		PROV = prov;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String city) {
		CITY = city;
	}
	public String getCOUNTY() {
		return COUNTY;
	}
	public void setCOUNTY(String county) {
		COUNTY = county;
	}
	public String getXIANCHANGPIC() {
		return XIANCHANGPIC;
	}
	public void setXIANCHANGPIC(String xianchangpic) {
		XIANCHANGPIC = xianchangpic;
	}
	public String getBEAR_WAY() {
		return BEAR_WAY;
	}
	public void setBEAR_WAY(String bear_way) {
		BEAR_WAY = bear_way;
	}
	public String getSPEC_CONTENT() {
		return SPEC_CONTENT;
	}
	public void setSPEC_CONTENT(String spec_content) {
		SPEC_CONTENT = spec_content;
	}
	public void setTERM_WHICH_NUM(String tERMWHICHNUM) {
		TERM_WHICH_NUM = tERMWHICHNUM;
	}
	/**预计年销额**/
	private String     EXP_YEAR_SALE;
	/**商场综合情况分析**/
	private String      SALE_STORE_ANALYSE;
	/**报销政策**/
	private String      REIT_POLICY;
	/**报销金额(元/平米)**/
	private String     REIT_AMOUNT_PS;
	/**报销金额**/
	private String     REIT_AMOUNT;
	/**状态**/
	private String      STATE;
	/**备注**/
	private String      REMARK;
	/**制单人ID**/
	private String      CREATOR;
	/**制单人名称**/
	private String      CRE_NAME;
	/**更新人ID**/
	private String      UPDATOR;
	/**更新人名称**/
	private String      UPD_NAME;
	/**更新时间**/
	private String      UPD_TIME;
	/**审核人ID**/
	private String      AUDIT_ID;
	/**审核人姓名**/
	private String      AUDIT_NAME;
	/**审核时间**/
	private String      AUDIT_TIME;
	/**制单部门ID**/
	private String      DEPT_ID;
	/**制单部门名称**/
	private String      DEPT_NAME;
	/**制单机构ID**/
	private String      ORG_ID;
	/**制单机构名称**/
	private String      ORG_NAME;
	/**帐套ID**/
	private String      LEDGER_ID;
	/**帐套名称**/
	private String      LEDGER_NAME;
	/**删除标记**/
	private String     DEL_FLAG;
	/**申请日期**/
	private String     RNVTN_REQ_DATE;
	/**装修性质**/
	private String     RNVTN_PROP;
	/**合同签订金额**/
	private String     COMPACT_AMOUNT;
	/**是否标准内**/
    private String     IS_STAD_FLAG;
    /**图片路径**/
    private String PIC_PATH; 
    
    private String DATA_DTL_NAME;
    
    private String DATA_DTL_ID;
	
	private String RNVTN_REIT_NO;
	
	private String RNVTN_SCALE;
	
	private String REIT_REMARK;
	
	private String DATA_DTL_DES_1;
	
	private String RNVTN_REIT_DTL_ID;
	
	public String getRNVTN_REIT_DTL_ID() {
		return RNVTN_REIT_DTL_ID;
	}
	public void setRNVTN_REIT_DTL_ID(String rnvtn_reit_dtl_id) {
		RNVTN_REIT_DTL_ID = rnvtn_reit_dtl_id;
	}
	public String getDATA_DTL_DES_1() {
		return DATA_DTL_DES_1;
	}
	public void setDATA_DTL_DES_1(String data_dtl_des_1) {
		DATA_DTL_DES_1 = data_dtl_des_1;
	}
	public String getRNVTN_REIT_NO() {
		return RNVTN_REIT_NO;
	}
	public void setRNVTN_REIT_NO(String rnvtn_reit_no) {
		RNVTN_REIT_NO = rnvtn_reit_no;
	}
	public String getRNVTN_SCALE() {
		return RNVTN_SCALE;
	}
	public void setRNVTN_SCALE(String rnvtn_scale) {
		RNVTN_SCALE = rnvtn_scale;
	}
	public String getREIT_REMARK() {
		return REIT_REMARK;
	}
	public void setREIT_REMARK(String reit_remark) {
		REIT_REMARK = reit_remark;
	}
	public String getIS_STAD_FLAG() {
		return IS_STAD_FLAG;
	}
	public void setIS_STAD_FLAG(String is_stad_flag) {
		IS_STAD_FLAG = is_stad_flag;
	}
	public String getRNVTN_REQ_DATE() {
		return RNVTN_REQ_DATE;
	}
	public void setRNVTN_REQ_DATE(String rnvtn_req_date) {
		RNVTN_REQ_DATE = rnvtn_req_date;
	}
	public String getRNVTN_PROP() {
		return RNVTN_PROP;
	}
	public void setRNVTN_PROP(String rnvtn_prop) {
		RNVTN_PROP = rnvtn_prop;
	}
	public String getCOMPACT_AMOUNT() {
		return COMPACT_AMOUNT;
	}
	public void setCOMPACT_AMOUNT(String compact_amount) {
		COMPACT_AMOUNT = compact_amount;
	}
	public String getCHANN_RNVTN_ID() {
		return CHANN_RNVTN_ID;
	}
	public void setCHANN_RNVTN_ID(String chann_rnvtn_id) {
		CHANN_RNVTN_ID = chann_rnvtn_id;
	}
	public String getCHANN_RNVTN_NO() {
		return CHANN_RNVTN_NO;
	}
	public void setCHANN_RNVTN_NO(String chann_rnvtn_no) {
		CHANN_RNVTN_NO = chann_rnvtn_no;
	}
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	public void setCHANN_ID(String chann_id) {
		CHANN_ID = chann_id;
	}
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	public void setCHANN_NO(String chann_no) {
		CHANN_NO = chann_no;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getTERM_ID() {
		return TERM_ID;
	}
	public void setTERM_ID(String term_id) {
		TERM_ID = term_id;
	}
	public String getTERM_NO() {
		return TERM_NO;
	}
	public void setTERM_NO(String term_no) {
		TERM_NO = term_no;
	}
	public String getTERM_NAME() {
		return TERM_NAME;
	}
	public void setTERM_NAME(String term_name) {
		TERM_NAME = term_name;
	}
	public String getRNVTN_REQ_ID() {
		return RNVTN_REQ_ID;
	}
	public void setRNVTN_REQ_ID(String rnvtn_req_id) {
		RNVTN_REQ_ID = rnvtn_req_id;
	}
	public String getRNVTN_REQ_NAME() {
		return RNVTN_REQ_NAME;
	}
	public void setRNVTN_REQ_NAME(String rnvtn_req_name) {
		RNVTN_REQ_NAME = rnvtn_req_name;
	}
	public String getCHANN_PERSON_CON() {
		return CHANN_PERSON_CON;
	}
	public void setCHANN_PERSON_CON(String chann_person_con) {
		CHANN_PERSON_CON = chann_person_con;
	}
	public String getCHANN_TEL() {
		return CHANN_TEL;
	}
	public void setCHANN_TEL(String chann_tel) {
		CHANN_TEL = chann_tel;
	}
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String area_id) {
		AREA_ID = area_id;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String area_no) {
		AREA_NO = area_no;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String area_name) {
		AREA_NAME = area_name;
	}
	public String getAREA_MANAGE_ID() {
		return AREA_MANAGE_ID;
	}
	public void setAREA_MANAGE_ID(String area_manage_id) {
		AREA_MANAGE_ID = area_manage_id;
	}
	public String getAREA_MANAGE_NAME() {
		return AREA_MANAGE_NAME;
	}
	public void setAREA_MANAGE_NAME(String area_manage_name) {
		AREA_MANAGE_NAME = area_manage_name;
	}
	public String getAREA_MANAGE_TEL() {
		return AREA_MANAGE_TEL;
	}
	public void setAREA_MANAGE_TEL(String area_manage_tel) {
		AREA_MANAGE_TEL = area_manage_tel;
	}
	public String getSALE_STORE_ID() {
		return SALE_STORE_ID;
	}
	public void setSALE_STORE_ID(String sale_store_id) {
		SALE_STORE_ID = sale_store_id;
	}
	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}
	public void setSALE_STORE_NAME(String sale_store_name) {
		SALE_STORE_NAME = sale_store_name;
	}
	public String getZONE_ID() {
		return ZONE_ID;
	}
	public void setZONE_ID(String zone_id) {
		ZONE_ID = zone_id;
	}
	public String getZONE_ADDR() {
		return ZONE_ADDR;
	}
	public void setZONE_ADDR(String zone_addr) {
		ZONE_ADDR = zone_addr;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String buss_scope) {
		BUSS_SCOPE = buss_scope;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String address) {
		ADDRESS = address;
	}
	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}
	public void setLOCAL_TYPE(String local_type) {
		LOCAL_TYPE = local_type;
	}
	public String getSALE_STORE_ANALYSE() {
		return SALE_STORE_ANALYSE;
	}
	public void setSALE_STORE_ANALYSE(String sale_store_analyse) {
		SALE_STORE_ANALYSE = sale_store_analyse;
	}
	public String getREIT_POLICY() {
		return REIT_POLICY;
	}
	public void setREIT_POLICY(String reit_policy) {
		REIT_POLICY = reit_policy;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String creator) {
		CREATOR = creator;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cre_name) {
		CRE_NAME = cre_name;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String updator) {
		UPDATOR = updator;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String upd_name) {
		UPD_NAME = upd_name;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String upd_time) {
		UPD_TIME = upd_time;
	}
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String audit_id) {
		AUDIT_ID = audit_id;
	}
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String audit_name) {
		AUDIT_NAME = audit_name;
	}
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dept_id) {
		DEPT_ID = dept_id;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dept_name) {
		DEPT_NAME = dept_name;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String ledger_id) {
		LEDGER_ID = ledger_id;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String ledger_name) {
		LEDGER_NAME = ledger_name;
	}
	public String getPLAN_SBUSS_DATE() {
		return PLAN_SBUSS_DATE;
	}
	public void setPLAN_SBUSS_DATE(String plan_sbuss_date) {
		PLAN_SBUSS_DATE = plan_sbuss_date;
	}
	public String getDMD_DRAW_DATE() {
		return DMD_DRAW_DATE;
	}
	public void setDMD_DRAW_DATE(String dmd_draw_date) {
		DMD_DRAW_DATE = dmd_draw_date;
	}
	public String getUSE_AREA() {
		return USE_AREA;
	}
	public void setUSE_AREA(String use_area) {
		USE_AREA = use_area;
	}
	 
	public String getRNVTN_AREA() {
		return RNVTN_AREA;
	}
	public void setRNVTN_AREA(String rnvtn_area) {
		RNVTN_AREA = rnvtn_area;
	}
	public String getMARKET_RENT() {
		return MARKET_RENT;
	}
	public void setMARKET_RENT(String market_rent) {
		MARKET_RENT = market_rent;
	}
	public String getEXP_YEAR_SALE() {
		return EXP_YEAR_SALE;
	}
	public void setEXP_YEAR_SALE(String exp_year_sale) {
		EXP_YEAR_SALE = exp_year_sale;
	}
	public String getREIT_AMOUNT_PS() {
		return REIT_AMOUNT_PS;
	}
	public void setREIT_AMOUNT_PS(String reit_amount_ps) {
		REIT_AMOUNT_PS = reit_amount_ps;
	}
	public String getREIT_AMOUNT() {
		return REIT_AMOUNT;
	}
	public void setREIT_AMOUNT(String reit_amount) {
		REIT_AMOUNT = reit_amount;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String audit_time) {
		AUDIT_TIME = audit_time;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
	public String getDATA_DTL_NAME() {
		return DATA_DTL_NAME;
	}
	public void setDATA_DTL_NAME(String data_dtl_name) {
		DATA_DTL_NAME = data_dtl_name;
	}
	public String getDATA_DTL_ID() {
		return DATA_DTL_ID;
	}
	public void setDATA_DTL_ID(String data_dtl_id) {
		DATA_DTL_ID = data_dtl_id;
	}
	public String getMARKETPIC() {
		return MARKETPIC;
	}
	public void setMARKETPIC(String marketpic) {
		MARKETPIC = marketpic;
	}
}
