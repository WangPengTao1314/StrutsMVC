package com.hoperun.base.table.model;

import com.hoperun.commons.model.BaseModel;

public class TableModel  extends BaseModel {
    
	private static final long serialVersionUID = 1L;
	
	/**系统业务表NAME英文**/
	 private  String  TABLE_NAME_EN;
	 /**系统业务表NAME中文**/
	 private  String  TABLE_NAME_ZH;
	 /**系统业务表业务描述**/
	 private  String  TABLE_DESC;
	 /**创建人**/
	 private  String  TABLE_OWNER;
	 
	 /////
	 private  String  TABLE_COL_EN;
	 private  String  TABLE_COL_ZH;
	 private  String  COL_TYPE;
	 private  String  COL_LENGTH;
	 private  String  RELATE_TABLE_NAME_EN;
	 private  String  RELATE_TABLE_NAME_ZH;
	 
	public String getRELATE_TABLE_NAME_ZH() {
		return RELATE_TABLE_NAME_ZH;
	}
	public void setRELATE_TABLE_NAME_ZH(String relate_table_name_zh) {
		RELATE_TABLE_NAME_ZH = relate_table_name_zh;
	}
	public String getRELATE_TABLE_NAME_EN() {
		return RELATE_TABLE_NAME_EN;
	}
	public void setRELATE_TABLE_NAME_EN(String relate_table_name_en) {
		RELATE_TABLE_NAME_EN = relate_table_name_en;
	}
	public String getTABLE_NAME_EN() {
		return TABLE_NAME_EN;
	}
	public void setTABLE_NAME_EN(String table_name_en) {
		TABLE_NAME_EN = table_name_en;
	}
	public String getTABLE_NAME_ZH() {
		return TABLE_NAME_ZH;
	}
	public void setTABLE_NAME_ZH(String table_name_zh) {
		TABLE_NAME_ZH = table_name_zh;
	}
	public String getTABLE_DESC() {
		return TABLE_DESC;
	}
	public void setTABLE_DESC(String table_desc) {
		TABLE_DESC = table_desc;
	}
	public String getTABLE_OWNER() {
		return TABLE_OWNER;
	}
	public void setTABLE_OWNER(String table_owner) {
		TABLE_OWNER = table_owner;
	}
	public String getTABLE_COL_EN() {
		return TABLE_COL_EN;
	}
	public void setTABLE_COL_EN(String table_col_en) {
		TABLE_COL_EN = table_col_en;
	}
	public String getTABLE_COL_ZH() {
		return TABLE_COL_ZH;
	}
	public void setTABLE_COL_ZH(String table_col_zh) {
		TABLE_COL_ZH = table_col_zh;
	}
	public String getCOL_TYPE() {
		return COL_TYPE;
	}
	public void setCOL_TYPE(String col_type) {
		COL_TYPE = col_type;
	}
	public String getCOL_LENGTH() {
		return COL_LENGTH;
	}
	public void setCOL_LENGTH(String col_length) {
		COL_LENGTH = col_length;
	}
}
