/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PageInfoVO.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class PageInfoVO implements Serializable
{
	
	/** The cur page. */
	public int curPage;
    
    /** The max page. */
    public int maxPage;
    
    /** The max row count. */
    public int maxRowCount;
    
    /** The rows per page. */
    public int rowsPerPage;
    
    /** The cur max row. */
    public int curMaxRow;
    
    /** The table name. */
    public String tableName;
    
    /** The list names. */
    public String listNames[];
    
    /** The listas names. */
    public String listasNames[];
    
    /** The query comm condition. */
    public String queryCommCondition;
    
    /** The query sql. */
    public String querySql;
    
    /** The order name. */
    public String orderName;
    
    /** The order type. */
    public String orderType;
    
    /** The errorcode. */
    public String errorcode;
    
    /** The is only one sql. */
    public boolean isOnlyOneSql;
    
    /** The list length. */
    public int listLength;
    
    /** The VO class name. */
    public String VOClassName;
    
    /** The str buf list names. */
    private StringBuffer strBufListNames;
    
    /** The query pre sql. */
    public String queryPreSql;
	    
    /**
     * Instantiates a new page info vo.
     */
    public PageInfoVO()
    {
        queryCommCondition = " where 1=1 ";
        orderName = null;
        orderType = "";
        errorcode = "";
        isOnlyOneSql = false;
        queryPreSql = "";
    }

    /**
     * Gets the str list names.
     * 
     * @return the str list names
     */
    public String getStrListNames()
    {
        strBufListNames = new StringBuffer("");
        for(int i = 0; i < listNames.length; i++)
        {
            strBufListNames.append(listNames[i]);
            if(i != listNames.length - 1)
                strBufListNames.append(",");
        }

        return strBufListNames.toString();
    }
    
    /**
     * Gets the str listas names.
     * 
     * @return the str listas names
     */
    public String getStrListasNames()
    {
        strBufListNames = new StringBuffer("");
        for(int i = 0; i < listasNames.length; i++)
        {
            strBufListNames.append(listasNames[i]);
            if(i != listasNames.length - 1)
                strBufListNames.append(",");
        }

        return strBufListNames.toString();
    }
    
    /**
     * Checks if is only one sql.
     * 
     * @return true, if is only one sql
     */
    public boolean isOnlyOneSql()
    {
        return isOnlyOneSql;
    }

    /**
     * Sets the checks if is only one sql.
     * 
     * @param isOnlyOneSql the new checks if is only one sql
     */
    public void setIsOnlyOneSql(boolean isOnlyOneSql)
    {
        this.isOnlyOneSql = isOnlyOneSql;
    }

    /**
     * Gets the query sql.
     * 
     * @return the query sql
     */
    public String getQuerySql()
    {
        return querySql;
    }

    /**
     * Sets the query sql.
     * 
     * @param querySql the new query sql
     */
    public void setQuerySql(String querySql)
    {
        this.querySql = querySql;
    }

    /**
     * Gets the query pre sql.
     * 
     * @return the query pre sql
     */
    public String getQueryPreSql()
    {
        return queryPreSql;
    }

    /**
     * Sets the query pre sql.
     * 
     * @param queryPreSql the new query pre sql
     */
    public void setQueryPreSql(String queryPreSql)
    {
        this.queryPreSql = queryPreSql;
    }

   
}