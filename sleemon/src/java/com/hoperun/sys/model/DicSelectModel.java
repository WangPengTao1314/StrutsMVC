package com.hoperun.sys.model;

// TODO: Auto-generated Javadoc
/**
 * The Class DicSelectModel.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class DicSelectModel {

	/** The dict id. */
	private String dictId;
	
	/** The default value. */
	private String defaultValue;
	
	/** The condition. */
	private String condition;
	

	/**
	 * Gets the dict id.
	 * 
	 * @return the dict id
	 */
	public String getDictId() {
		return dictId;
	}

	/**
	 * Sets the dict id.
	 * 
	 * @param dictId the new dict id
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

    
    /**
     * Gets the default value.
     * 
     * @return the default value
     */
    public String getDefaultValue() {
    
        return defaultValue;
    }

    
    /**
     * Sets the default value.
     * 
     * @param defaultValue the new default value
     */
    public void setDefaultValue(String defaultValue) {
    
        this.defaultValue = defaultValue;
    }

    
    /**
     * Gets the condition.
     * 
     * @return the condition
     */
    public String getCondition() {
    
        return condition;
    }

    
    /**
     * Sets the condition.
     * 
     * @param condition the new condition
     */
    public void setCondition(String condition) {
    
        this.condition = condition;
    }

}
