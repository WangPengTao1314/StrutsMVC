package com.hoperun.erp.sale.areasaleplan.model;

import com.hoperun.commons.model.BaseModel;

public class AreasaleplandtlModel extends BaseModel{
     
	     private static final long serialVersionUID = 1L;
		 /**区域销售计划明细ID**/
	     private  String  AREA_SALE_PLAN_DTL_ID;
	     /**区域销售计划ID**/
	     private  String  AREA_SALE_PLAN_ID;
	     /**计划月份**/
	     private  String  PLAN_MONTH;
	     /**计划年份**/
	     private  String  PLAN_YEAR;
	     /**计划发货**/
	     private  String  PLAN_SALE_AMOUNT;
	     /**加盟商零售**/
	     private  String  CHANN_SALE_AMOUNT;
		public String getAREA_SALE_PLAN_DTL_ID() {
			return AREA_SALE_PLAN_DTL_ID;
		}
		public void setAREA_SALE_PLAN_DTL_ID(String area_sale_plan_dtl_id) {
			AREA_SALE_PLAN_DTL_ID = area_sale_plan_dtl_id;
		}
		public String getPLAN_MONTH() {
			return PLAN_MONTH;
		}
		public void setPLAN_MONTH(String plan_month) {
			PLAN_MONTH = plan_month;
		}
		public String getPLAN_YEAR() {
			return PLAN_YEAR;
		}
		public void setPLAN_YEAR(String plan_year) {
			PLAN_YEAR = plan_year;
		}
		public String getPLAN_SALE_AMOUNT() {
			return PLAN_SALE_AMOUNT;
		}
		public void setPLAN_SALE_AMOUNT(String plan_sale_amount) {
			PLAN_SALE_AMOUNT = plan_sale_amount;
		}
		public String getCHANN_SALE_AMOUNT() {
			return CHANN_SALE_AMOUNT;
		}
		public void setCHANN_SALE_AMOUNT(String chann_sale_amount) {
			CHANN_SALE_AMOUNT = chann_sale_amount;
		}
		public String getAREA_SALE_PLAN_ID() {
			return AREA_SALE_PLAN_ID;
		}
		public void setAREA_SALE_PLAN_ID(String area_sale_plan_id) {
			AREA_SALE_PLAN_ID = area_sale_plan_id;
		}
	 
}
