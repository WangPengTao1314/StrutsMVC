package com.hoperun.sys.service;

import javax.servlet.http.HttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface XTSQService.
 */
public interface XTSQService {

    /**
     * Gets the qX tab.
     * 
     * @param keyName the key name
     * @param keyID the key id
     * @param adminXTYHID the admin xtyhid
     * @param loginJGXXID the login jgxxid
     * @param request the request
     * 
     * @return the qX tab
     * 
     * @throws Exception the exception
     */
    public String getQXTab(String keyName, String keyID, String adminXTYHID, String loginJGXXID, HttpServletRequest request) throws Exception;


    /**
     * Gets the mK ary.
     * 
     * @param XTMC the xTMC
     * @param length the length
     * @param condition the condition
     * 
     * @return the mK ary
     * 
     * @throws Exception the exception
     */
    public String[] getMKAry(String XTMC, int length, String condition) throws Exception;


    /**
     * Return qxjb radio list.
     * 
     * @param MKQXJB the mKQXJB
     * 
     * @return the string
     * 
     * @throws Exception the exception
     */
    public String returnQXJBRadioList(String MKQXJB) throws Exception;


    /**
     * Gets the str m k2.
     * 
     * @param tableName the table name
     * @param condition the condition
     * 
     * @return the str m k2
     */
    public String getStrMK2(String tableName, String condition);


    /**
     * Gets the str mk.
     * 
     * @param tableName the table name
     * @param condition the condition
     * 
     * @return the str mk
     * 
     * @throws Exception the exception
     */
    public String getStrMK(String tableName, String condition) throws Exception;


    /**
     * Insert xtsq.
     * 
     * @param XTYHID the xTYHID
     * @param MKSM the mKSM
     * @param ins_name2 the ins_name2
     * @param ins_value2 the ins_value2
     * @param selXTMKID the sel xtmkid
     * @param request the request
     * 
     * @throws Exception the exception
     */
    public void insertXTSQ(String XTYHID, String MKSM, String[] ins_name2, String[] ins_value2, String[] selXTMKID, HttpServletRequest request)
            throws Exception;
}