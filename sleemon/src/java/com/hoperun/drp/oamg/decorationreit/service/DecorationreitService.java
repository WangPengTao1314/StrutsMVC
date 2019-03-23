package com.hoperun.drp.oamg.decorationreit.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.decorationreit.model.DecorationreitModel;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.sys.model.UserBean;

public interface DecorationreitService extends IBaseService {
	
	
    /**
     * @param RNVTN_REIT_REQ_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String RNVTN_REIT_REQ_ID, DecorationreitModel obj, UserBean userBean);
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
    public Map <String, String> loadById(String RNVTN_REIT_REQ_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadT(String RNVTN_REIT_REQ_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadTt(String RNVTN_REIT_REQ_ID);
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
    public Map <String, String> loadByIdT(String RNVTN_REIT_REQ_ID);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_REIT_REQ_ID, UserBean userBean);
    
    /**
     * @param RNVTN_FISH_DATE
     * @param TERM_ID
     */
    public  void  updateTerm(String RNVTN_FISH_DATE,String TERM_ID);
    
    /**
     * 查看终端状态
     * @param DATA_CONF 
     * @return true
     */
    public List<Map<String,String>> queryTermState(String TERM_ID);
    
    /**
     * 获取报销频次
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public List<Map<String,String>>  getReimbursement(String CHANN_RNVTN_ID);
    
    /**
     * 计算已经报销的 报销金额
     * @param CHANN_RNVTN_ID  装修申请单ID
     * @return
     */
    public Map<String,String> sumCanReturnAmout(String CHANN_RNVTN_ID);
    
    /**
     * 新增的时候 自动判断是第几次申请，如果不是首次数据项自动带出
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public DecorationreitModel queryJudgeModel(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public Map<String,Object> queryJudgeModelT(String CHANN_ID,String YEAR);
    
    /**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryFnshRate(String CHANN_ID,String YEAR);
    
    /**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryYearPlanAmount(String CHANN_ID,String YEAR);
    
    /**
     * @param AREA_ID
     * @return
     */
    public String queryWareaName(String AREA_ID);
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	
	/**
     * 获取经营品牌
     * @return
     */
    public List<DecorationreitModel>  queryBussScopeT();
    
	/**
     * 获取装修性质
     * @return
     */
    public List<DecorationreitModel>  queryRnvtnProp();
    
    /**
     * 获取商场位置类别
     * @return
     */
    public List<DecorationreitModel>  queryLocalType();
    
    /**
     * @return
     */
    public  int   getRowcount();
    
    /**
     * @param BUSS_SCOPE
     * @return
     */
    public String toQBussScope(String BUSS_SCOPE);
    
    /**
     * @param LOCAL_TYPE
     * @return
     */
    public String toQLocalType(String LOCAL_TYPE);
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID);
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
    public String queryReitAmount(String RNVTN_REIT_REQ_ID);
    
    /**
     * @return
     */
    public String queryGZZXXID();
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZWT(String RYXXID,String GZZXXID);
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
    public BudgetquotaModel qyeryQuotaAmount(String RNVTN_REIT_REQ_ID);
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
	public List <DecorationreitModel> childQuery(String RNVTN_REIT_REQ_ID);
}
