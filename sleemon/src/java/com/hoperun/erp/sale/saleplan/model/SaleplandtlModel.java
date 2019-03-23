package com.hoperun.erp.sale.saleplan.model;

import com.hoperun.commons.model.BaseModel;

public class SaleplandtlModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**销售计划明细ID**/
    private   String   SALE_PLAN_DTL_ID;
    /**销售计划ID**/
    private   String   SALE_PLAN_ID;
     /**计划年份**/
    private   String   PLAN_YEAR;
     /**计划月份**/
    private   String   PLAN_MONTH;
     /**计划发货**/
    private   String   PLAN_SALE_AMOUNT;
     /**加盟商零售**/
    private   String   CHANN_SALE_AMOUNT;
	public String getSALE_PLAN_DTL_ID() {
		return SALE_PLAN_DTL_ID;
	}
	public void setSALE_PLAN_DTL_ID(String sale_plan_dtl_id) {
		SALE_PLAN_DTL_ID = sale_plan_dtl_id;
	}
	public String getSALE_PLAN_ID() {
		return SALE_PLAN_ID;
	}
	public void setSALE_PLAN_ID(String sale_plan_id) {
		SALE_PLAN_ID = sale_plan_id;
	}
	public String getPLAN_YEAR() {
		return PLAN_YEAR;
	}
	public void setPLAN_YEAR(String plan_year) {
		PLAN_YEAR = plan_year;
	}
	public String getPLAN_MONTH() {
		return PLAN_MONTH;
	}
	public void setPLAN_MONTH(String plan_month) {
		PLAN_MONTH = plan_month;
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
}
