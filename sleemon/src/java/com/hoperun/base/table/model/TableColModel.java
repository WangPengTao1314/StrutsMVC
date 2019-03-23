package com.hoperun.base.table.model;

import com.hoperun.commons.model.BaseModel;

public class TableColModel extends BaseModel {
     
	  private static final long serialVersionUID = 1L;
	  
	  /**系统业务表_字段名称_EN**/
	  private   String  TABLE_COL_EN;
	  /**系统业务表NAME英文**/
	  private   String  TABLE_NAME_EN;
	  /**系统业务表_字段名称_中文**/
	  private   String  TABLE_COL_ZH;
	  /**字段类型**/
	  private   String  COL_TYPE;
	  /**字段长度**/
	  private   String  COL_LENGTH;
	  /**是否主键**/
	  private   String  IS_PK;
	  /**关联表名称**/
	  private   String  RELATE_TABLE_NAME_EN;
	  /**列顺序**/
	  private   String  COL_INDEX;
	public String getTABLE_COL_EN() {
		return TABLE_COL_EN;
	}
	public void setTABLE_COL_EN(String table_col_en) {
		TABLE_COL_EN = table_col_en;
	}
	public String getTABLE_NAME_EN() {
		return TABLE_NAME_EN;
	}
	public void setTABLE_NAME_EN(String table_name_en) {
		TABLE_NAME_EN = table_name_en;
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
	public String getIS_PK() {
		return IS_PK;
	}
	public void setIS_PK(String is_pk) {
		IS_PK = is_pk;
	}
	public String getRELATE_TABLE_NAME_EN() {
		return RELATE_TABLE_NAME_EN;
	}
	public void setRELATE_TABLE_NAME_EN(String relate_table_name_en) {
		RELATE_TABLE_NAME_EN = relate_table_name_en;
	}
	public String getCOL_INDEX() {
		return COL_INDEX;
	}
	public void setCOL_INDEX(String col_index) {
		COL_INDEX = col_index;
	}
}
