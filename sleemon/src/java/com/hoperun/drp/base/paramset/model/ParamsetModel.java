package com.hoperun.drp.base.paramset.model;

import com.hoperun.commons.model.BaseModel;

public class ParamsetModel extends BaseModel {

	private static final long serialVersionUID = 1L;
     
	/** 分销数据字典ID **/
	private String   DATA_CONF_ID;
	/** 数据字典类型**/
	private String   DATA_TYPE;
	/** 数据字典值**/
	private String   DATA_VAL;
	/** 帐套ID**/
    private String   LEDGER_ID;
    /** 删除标记**/
    private Integer  DEL_FLAG;
    /** 参数名称**/
    private String   DATA_NAME;
    /**是否处理标记**/
    private String IS_DEAL_FLAG;
    
	public String getDATA_NAME() {
		return DATA_NAME;
	}
	public void setDATA_NAME(String data_name) {
		DATA_NAME = data_name;
	}
	public String getDATA_CONF_ID() {
		return DATA_CONF_ID;
	}
	public void setDATA_CONF_ID(String data_conf_id) {
		DATA_CONF_ID = data_conf_id;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String data_type) {
		DATA_TYPE = data_type;
	}
	public String getDATA_VAL() {
		return DATA_VAL;
	}
	public void setDATA_VAL(String data_val) {
		DATA_VAL = data_val;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String ledger_id) {
		LEDGER_ID = ledger_id;
	}
	public Integer getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(Integer del_flag) {
		DEL_FLAG = del_flag;
	}
	/**
	 * @return the iS_DEAL_FLAG
	 */
	public String getIS_DEAL_FLAG() {
		return IS_DEAL_FLAG;
	}
	/**
	 * @param iSDEALFLAG the iS_DEAL_FLAG to set
	 */
	public void setIS_DEAL_FLAG(String iSDEALFLAG) {
		IS_DEAL_FLAG = iSDEALFLAG;
	}
	
	
}
