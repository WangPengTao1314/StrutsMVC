package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class CooperativeVisitModel   extends BaseModel  {
     
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**合作渠道拜访拜访ID**/
	 private   String  COOPERATIVE_VISIT_ID;
	 /**营销经理日报表ID**/
	 private   String  MKM_DAY_RPT_ID;
	 /**分销商ID**/
	 private   String  DISTRIBUTOR_ID;
	 /**分销商编号**/
	 private   String  DISTRIBUTOR_NO;
	 /**分销商名称**/
	 private   String  DISTRIBUTOR_NAME;
	 /**合作商主要问题**/
	 private   String  MAIN_PROBLEM;
	 /**解决方案**/
	 private   String  SOLUTION;
	 /**合作客户商场类型**/
	 private   String  MARKET_TYPE;
	 /**商场名称**/
	 private   String  WILL_SALE_STORE_NAME;
	 /**合作客户名称**/
	 private   String  CUST_NAME;
	 /**合作客户电话**/
	 private   String  CUST_TEL;
	 /**商场地址**/
	 private   String  MARKET_ADDRESS;
	 /**经营品牌**/
	 private   String  MARKET_BUSS_BRAND;
	 /**主营分类**/
	 private   String  MARKET_BUSS_CLASS;
	 /**意向程度**/
	 private   String  WILL_DEGREE;
	 /**无意向原因**/
	 private   String  NO_WILL_RESON;
	public String getCOOPERATIVE_VISIT_ID() {
		return COOPERATIVE_VISIT_ID;
	}
	public void setCOOPERATIVE_VISIT_ID(String cooperative_visit_id) {
		COOPERATIVE_VISIT_ID = cooperative_visit_id;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getDISTRIBUTOR_ID() {
		return DISTRIBUTOR_ID;
	}
	public void setDISTRIBUTOR_ID(String distributor_id) {
		DISTRIBUTOR_ID = distributor_id;
	}
	public String getDISTRIBUTOR_NO() {
		return DISTRIBUTOR_NO;
	}
	public void setDISTRIBUTOR_NO(String distributor_no) {
		DISTRIBUTOR_NO = distributor_no;
	}
	public String getDISTRIBUTOR_NAME() {
		return DISTRIBUTOR_NAME;
	}
	public void setDISTRIBUTOR_NAME(String distributor_name) {
		DISTRIBUTOR_NAME = distributor_name;
	}
	public String getMAIN_PROBLEM() {
		return MAIN_PROBLEM;
	}
	public void setMAIN_PROBLEM(String main_problem) {
		MAIN_PROBLEM = main_problem;
	}
	public String getSOLUTION() {
		return SOLUTION;
	}
	public void setSOLUTION(String solution) {
		SOLUTION = solution;
	}
	public String getMARKET_TYPE() {
		return MARKET_TYPE;
	}
	public void setMARKET_TYPE(String market_type) {
		MARKET_TYPE = market_type;
	}
	public String getWILL_SALE_STORE_NAME() {
		return WILL_SALE_STORE_NAME;
	}
	public void setWILL_SALE_STORE_NAME(String will_sale_store_name) {
		WILL_SALE_STORE_NAME = will_sale_store_name;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cust_name) {
		CUST_NAME = cust_name;
	}
	public String getCUST_TEL() {
		return CUST_TEL;
	}
	public void setCUST_TEL(String cust_tel) {
		CUST_TEL = cust_tel;
	}
	public String getMARKET_ADDRESS() {
		return MARKET_ADDRESS;
	}
	public void setMARKET_ADDRESS(String market_address) {
		MARKET_ADDRESS = market_address;
	}
	public String getMARKET_BUSS_BRAND() {
		return MARKET_BUSS_BRAND;
	}
	public void setMARKET_BUSS_BRAND(String market_buss_brand) {
		MARKET_BUSS_BRAND = market_buss_brand;
	}
	public String getMARKET_BUSS_CLASS() {
		return MARKET_BUSS_CLASS;
	}
	public void setMARKET_BUSS_CLASS(String market_buss_class) {
		MARKET_BUSS_CLASS = market_buss_class;
	}
	public String getWILL_DEGREE() {
		return WILL_DEGREE;
	}
	public void setWILL_DEGREE(String will_degree) {
		WILL_DEGREE = will_degree;
	}
	public String getNO_WILL_RESON() {
		return NO_WILL_RESON;
	}
	public void setNO_WILL_RESON(String no_will_reson) {
		NO_WILL_RESON = no_will_reson;
	}
}
