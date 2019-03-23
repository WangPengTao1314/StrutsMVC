package com.hoperun.drp.oamg.terminalchange.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.terminalchange.model.TerminalChangeModel;
import com.hoperun.sys.model.UserBean;

public interface TerminalChangeService  extends IBaseService  {

    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> load(String TERMINAL_CHANGE_ID);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> loadT(String TERMINAL_CHANGE_ID);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> loadByHistory(String TERM_NO);
    
    /**
     * @param TERM_NO
     * @return
     */
    public List<TerminalChangeModel> loadByTerm(String TERM_NO);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param obj
     * @param userBean
     */    
	public void txEdit(String TERMINAL_CHANGE_ID, TerminalChangeModel obj, UserBean userBean);
	
	/**
	 * @param obj
	 * @param userBean
	 */
	public void txEditTerm(TerminalModel obj, UserBean userBean);
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String TERMINAL_CHANGE_ID, UserBean userBean);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public  int queryTerminalState(String TERMINAL_CHANGE_ID);
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param userBean
     */
    public  void updateTerminal(String TERMINAL_CHANGE_ID,UserBean userBean);
    
    /**
     * @param TERM_NO
     * @return
     */
    public TerminalModel queryTermHistory(String TERM_NO);
}
