package com.hoperun.drp.oamg.terminalchange.model;

import com.hoperun.commons.model.BaseModel;

public class TerminalChangeModel extends BaseModel {
 
	 private static final long serialVersionUID = 1L;
	 
	 /**终端信息变更ID**/
	 private   String  TERMINAL_CHANGE_ID;
	 /**终端ID**/
	 private   String  TERM_ID;
	 /**终端编号**/
	 private   String  TERM_NO;
	 /**终端名称**/
	 private   String  TERM_NAME;
	 /**终端简称**/
	 private   String  TERM_ABBR;
	 /**终端类型**/
	 private   String  TERM_TYPE;
	 /**终端级别**/
	 private   String  TERM_LVL;
	 /**上级渠道ID**/
	 private   String  CHANN_ID_P;
	 /**上级渠道编号**/
	 private   String  CHANN_NO_P;
	 /**上级渠道名称**/
	 private   String  CHANN_NAME_P;
	 /**营业性质**/
	 private   String  BUSS_NATRUE;
	 /**物流方式**/
	 private   String  LOGIC_TYPE;
	 /**区域ID**/
	 private   String  AREA_ID;
	 /**区域编号**/
	 private   String  AREA_NO;
	 /**区域名称**/
	 private   String  AREA_NAME;
	 /**行政区划ID**/
	 private   String  ZONE_ID;
	 /**国家**/
	 private   String  NATION;
	 /**省份**/
	 private   String  PROV;
	 /**城市**/
	 private   String  CITY;
	 /**区县**/
	 private   String  COUNTY;
	 /**城市类型**/
	 private   String  CITY_TYPE;
	 /**联系人**/
	 private   String  PERSON_CON;
	 /**电话**/
	 private   String  TEL;
	 /**手机**/
	 private   String  MOBILE;
	 /**传真**/
	 private   String  TAX;
	 /**邮政编号**/
	 private   String  POST;
	 /**详细地址**/
	 private   String  ADDRESS;
	 /**电子邮件**/
	 private   String  EMAIL;
	 /**网站**/
	 private   String  WEB;
	 /**法人代表**/
	 private   String  LEGAL_REPRE;
	 /**营业执照号**/
	 private   String  BUSS_LIC;
	 /**税务登记号**/
	 private   String  AX_BURDEN;
	 /**组织机构代码证**/
	 private   String  ORG_CODE_CERT;
	 /**经营范围**/
	 private   String  BUSS_SCOPE;
	 /**财务对照码**/
	 private   String  FI_CMP_NO;
	 /**营业面积**/
	 private   String  BUSS_AREA;
	 /**楼层数**/
	 private   String  STOR_NO;
	 /**最后装潢时间**/
	 private   String  LAST_DECOR_TIME;
	 /**卖场ID**/
	 private   String  SALE_STORE_ID;
	 /**卖场名称**/
	 private   String  SALE_STORE_NAME;
	 /**商场位置类别**/
	 private   String  LOCAL_TYPE;
	 /**开业时间**/
	 private   String  BEG_SBUSS_DATE;
	 /**打款银行账号**/
	 private   String  PLAY_BANK_NAME;
	 /**打款银行账号**/
	 private   String  PLAY_BANK_ACCT;
	 /**营业状态**/
	 private   String  BUSS_STATE;
	 /**终端版本**/
	 private   String  TERM_VERSION;
	 /**开店类型**/
	 private   String  BEG_BUSS_TYPE;
	 /**状态**/
	 private   String  STATE;
	 /**备注**/
	 private   String  REMARK;
	public String getTERMINAL_CHANGE_ID() {
		return TERMINAL_CHANGE_ID;
	}
	public void setTERMINAL_CHANGE_ID(String terminal_change_id) {
		TERMINAL_CHANGE_ID = terminal_change_id;
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
	public String getTERM_ABBR() {
		return TERM_ABBR;
	}
	public void setTERM_ABBR(String term_abbr) {
		TERM_ABBR = term_abbr;
	}
	public String getTERM_TYPE() {
		return TERM_TYPE;
	}
	public void setTERM_TYPE(String term_type) {
		TERM_TYPE = term_type;
	}
	public String getTERM_LVL() {
		return TERM_LVL;
	}
	public void setTERM_LVL(String term_lvl) {
		TERM_LVL = term_lvl;
	}
	public String getCHANN_ID_P() {
		return CHANN_ID_P;
	}
	public void setCHANN_ID_P(String chann_id_p) {
		CHANN_ID_P = chann_id_p;
	}
	public String getCHANN_NO_P() {
		return CHANN_NO_P;
	}
	public void setCHANN_NO_P(String chann_no_p) {
		CHANN_NO_P = chann_no_p;
	}
	public String getCHANN_NAME_P() {
		return CHANN_NAME_P;
	}
	public void setCHANN_NAME_P(String chann_name_p) {
		CHANN_NAME_P = chann_name_p;
	}
	public String getBUSS_NATRUE() {
		return BUSS_NATRUE;
	}
	public void setBUSS_NATRUE(String buss_natrue) {
		BUSS_NATRUE = buss_natrue;
	}
	public String getLOGIC_TYPE() {
		return LOGIC_TYPE;
	}
	public void setLOGIC_TYPE(String logic_type) {
		LOGIC_TYPE = logic_type;
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
	public String getZONE_ID() {
		return ZONE_ID;
	}
	public void setZONE_ID(String zone_id) {
		ZONE_ID = zone_id;
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
	public String getCITY_TYPE() {
		return CITY_TYPE;
	}
	public void setCITY_TYPE(String city_type) {
		CITY_TYPE = city_type;
	}
	public String getPERSON_CON() {
		return PERSON_CON;
	}
	public void setPERSON_CON(String person_con) {
		PERSON_CON = person_con;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tel) {
		TEL = tel;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mobile) {
		MOBILE = mobile;
	}
	public String getTAX() {
		return TAX;
	}
	public void setTAX(String tax) {
		TAX = tax;
	}
	public String getPOST() {
		return POST;
	}
	public void setPOST(String post) {
		POST = post;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String address) {
		ADDRESS = address;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String email) {
		EMAIL = email;
	}
	public String getWEB() {
		return WEB;
	}
	public void setWEB(String web) {
		WEB = web;
	}
	public String getLEGAL_REPRE() {
		return LEGAL_REPRE;
	}
	public void setLEGAL_REPRE(String legal_repre) {
		LEGAL_REPRE = legal_repre;
	}
	public String getBUSS_LIC() {
		return BUSS_LIC;
	}
	public void setBUSS_LIC(String buss_lic) {
		BUSS_LIC = buss_lic;
	}
	public String getAX_BURDEN() {
		return AX_BURDEN;
	}
	public void setAX_BURDEN(String ax_burden) {
		AX_BURDEN = ax_burden;
	}
	public String getORG_CODE_CERT() {
		return ORG_CODE_CERT;
	}
	public void setORG_CODE_CERT(String org_code_cert) {
		ORG_CODE_CERT = org_code_cert;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String buss_scope) {
		BUSS_SCOPE = buss_scope;
	}
	public String getFI_CMP_NO() {
		return FI_CMP_NO;
	}
	public void setFI_CMP_NO(String fi_cmp_no) {
		FI_CMP_NO = fi_cmp_no;
	}
	public String getBUSS_AREA() {
		return BUSS_AREA;
	}
	public void setBUSS_AREA(String buss_area) {
		BUSS_AREA = buss_area;
	}
	public String getSTOR_NO() {
		return STOR_NO;
	}
	public void setSTOR_NO(String stor_no) {
		STOR_NO = stor_no;
	}
	public String getLAST_DECOR_TIME() {
		return LAST_DECOR_TIME;
	}
	public void setLAST_DECOR_TIME(String last_decor_time) {
		LAST_DECOR_TIME = last_decor_time;
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
	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}
	public void setLOCAL_TYPE(String local_type) {
		LOCAL_TYPE = local_type;
	}
	public String getBEG_SBUSS_DATE() {
		return BEG_SBUSS_DATE;
	}
	public void setBEG_SBUSS_DATE(String beg_sbuss_date) {
		BEG_SBUSS_DATE = beg_sbuss_date;
	}
	public String getPLAY_BANK_NAME() {
		return PLAY_BANK_NAME;
	}
	public void setPLAY_BANK_NAME(String play_bank_name) {
		PLAY_BANK_NAME = play_bank_name;
	}
	public String getPLAY_BANK_ACCT() {
		return PLAY_BANK_ACCT;
	}
	public void setPLAY_BANK_ACCT(String play_bank_acct) {
		PLAY_BANK_ACCT = play_bank_acct;
	}
	public String getBUSS_STATE() {
		return BUSS_STATE;
	}
	public void setBUSS_STATE(String buss_state) {
		BUSS_STATE = buss_state;
	}
	public String getTERM_VERSION() {
		return TERM_VERSION;
	}
	public void setTERM_VERSION(String term_version) {
		TERM_VERSION = term_version;
	}
	public String getBEG_BUSS_TYPE() {
		return BEG_BUSS_TYPE;
	}
	public void setBEG_BUSS_TYPE(String beg_buss_type) {
		BEG_BUSS_TYPE = beg_buss_type;
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
	public String getTERM_ID() {
		return TERM_ID;
	}
	public void setTERM_ID(String term_id) {
		TERM_ID = term_id;
	}
}
