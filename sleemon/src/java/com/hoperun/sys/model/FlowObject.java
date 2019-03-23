package com.hoperun.sys.model;
// TODO: Auto-generated Javadoc

/**
 * The Class FlowObject.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class FlowObject {
  
  /** The business type. */
  private String businessType;
  
  /** The table name. */
  private String tableName;
  
  /** The field name. */
  private String fieldName;
  
  /** The id. */
  private String id;
  
  /** The state name. */
  private String stateName = "state";
  
  /** The flow interface name. */
  private String flowInterfaceName;
  
  /** The ztbg field name. */
  private String ztbgFieldName;
  
  /** The flow model id. */
  private String flowModelId;
  
  /** The CONNNAME. */
  private String CONNNAME;
  
  /**
   * Instantiates a new flow object.
   */
  public FlowObject() {
  }
  
  /**
   * Gets the business type.
   * 
   * @return the business type
   */
  public String getBusinessType() {
    return businessType;
  }
  
  /**
   * Sets the business type.
   * 
   * @param businessType the new business type
   */
  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }
  
  /**
   * Sets the table name.
   * 
   * @param tableName the new table name
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  
  /**
   * Gets the table name.
   * 
   * @return the table name
   */
  public String getTableName() {
    return tableName;
  }
  
  /**
   * Sets the field name.
   * 
   * @param fieldName the new field name
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  
  /**
   * Gets the field name.
   * 
   * @return the field name
   */
  public String getFieldName() {
    return fieldName;
  }
  
  /**
   * Sets the id.
   * 
   * @param id the new id
   */
  public void setId(String id) {
    this.id = id;
  }
  
  /**
   * Gets the id.
   * 
   * @return the id
   */
  public String getId() {
    return id;
  }
  
  /**
   * Sets the state name.
   * 
   * @param stateName the new state name
   */
  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
  
  /**
   * Gets the state name.
   * 
   * @return the state name
   */
  public String getStateName() {
    return stateName;
  }
	
	/**
	 * Returns the flowInterfaceName.
	 * 
	 * @return String
	 */
	public String getFlowInterfaceName() {
		return flowInterfaceName;
	}

	/**
	 * Sets the flowInterfaceName.
	 * 
	 * @param flowInterfaceName The flowInterfaceName to set
	 */
	public void setFlowInterfaceName(String flowInterfaceName) {
		this.flowInterfaceName = flowInterfaceName;
	}

	/**
	 * Returns the ztbgFieldName.
	 * 
	 * @return String
	 */
	public String getZtbgFieldName() {
		return ztbgFieldName;
	}

	/**
	 * Sets the ztbgFieldName.
	 * 
	 * @param ztbgFieldName The ztbgFieldName to set
	 */
	public void setZtbgFieldName(String ztbgFieldName) {
		this.ztbgFieldName = ztbgFieldName;
	}

	/**
	 * Returns the flowModelId.
	 * 
	 * @return String
	 */
	public String getFlowModelId() {
		return flowModelId;
	}

	/**
	 * Sets the flowModelId.
	 * 
	 * @param flowModelId The flowModelId to set
	 */
	public void setFlowModelId(String flowModelId) {
		this.flowModelId = flowModelId;
	}
  
  /**
   * Gets the cONNNAME.
   * 
   * @return the cONNNAME
   */
  public String getCONNNAME() {
    return CONNNAME;
  }
  
  /**
   * Sets the cONNNAME.
   * 
   * @param CONNNAME the new cONNNAME
   */
  public void setCONNNAME(String CONNNAME) {
    this.CONNNAME = CONNNAME;
  }

}