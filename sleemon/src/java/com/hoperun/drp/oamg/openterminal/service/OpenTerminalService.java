package com.hoperun.drp.oamg.openterminal.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalChildModel;
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalModel;
import com.hoperun.drp.oamg.openterminal.model.TerminalCommModel;
import com.hoperun.sys.model.UserBean;

public interface OpenTerminalService extends IBaseService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> load(String OPEN_TERMINAL_REQ_ID);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadT(String OPEN_TERMINAL_REQ_ID);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadTt(String OPEN_TERMINAL_REQ_ID);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadTtT(String OPEN_TERMINAL_REQ_ID);
    /**
     * 编辑
     * @param OPEN_TERMINAL_REQ_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String OPEN_TERMINAL_REQ_ID, OpenTerminalModel obj,UserBean userBean);
	
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String OPEN_TERMINAL_REQ_ID, UserBean userBean);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public  int queryTerminalState(String OPEN_TERMINAL_REQ_ID);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public  int queryIsCommit(String OPEN_TERMINAL_REQ_ID);
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public  String  queryRrop(String OPEN_TERMINAL_REQ_ID);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     */
    public  void txTerminal(String OPEN_TERMINAL_REQ_ID,UserBean userBean);
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     */
    public  void upTerminal(String OPEN_TERMINAL_REQ_ID,UserBean userBean);
    /**
     * @param LOCAL_TYPE
     * @return
     */
    public  String  queryName(String LOCAL_TYPE);
    
    public List<OpenTerminalChildModel> childQuery(String OPEN_TERMINAL_REQ_ID );
    
    public  Map<String,Object> loadChild(String OPEN_TERMINAL_REQ_ID );
    
    public void txBatch4DeleteChild(Map<String,String> params);
    
    public List<OpenTerminalChildModel> loadChilds(String OPEN_TERMINAL_REQ_DTL_IDS);
    
    public void txChildEdit(String OPEN_TERMINAL_REQ_ID,OpenTerminalChildModel childModel,List<TerminalCommModel> commList);
    /**
     * 加载竞品
     * @param OPEN_TERMINAL_REQ_DTL_ID
     * @return
     */
    public List<Map<String,Object>> loadComms(String OPEN_TERMINAL_REQ_DTL_ID);
    /**
     * 删除竞品
     * @param COMMODITIES_ID
     */
    public void txDeleteComm(String COMMODITIES_ID);
    
    /**
     *查询竞品的名称
     * @param OPEN_TERMINAL_REQ_DTL_ID
     * @return
     */
    public  Map<String,Object> loadCommNames(String OPEN_TERMINAL_REQ_DTL_ID );
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public Map<String,Object> queryJudgeModel(String RNVTN_PROP);
    
    /**
     * @param TERM_NO
     * @return
     */
    public  String  queryTermIdByNo(String TERM_NO);
    /**
     * @return
     */
    public  String  queryMaxNo();
    
    /**
     * @return
     */
    public  int queryOpenTermNo(String TERM_NO);
}
