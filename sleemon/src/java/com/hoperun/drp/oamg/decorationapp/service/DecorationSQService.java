package com.hoperun.drp.oamg.decorationapp.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.promoexpen.model.PromoexpenModel;
import com.hoperun.sys.model.UserBean;

public interface DecorationSQService extends IBaseService {

	/**
	 * @param CHANN_RNVTN_ID
	 * @param obj
	 * @param userBean
	 * @param SelType
	 */
	public void txEdit(String CHANN_RNVTN_ID, DecorationappModel obj, UserBean userBean);
	
	/**
	 * @param CHANN_RNVTN_ID
	 * @param RNVTN_SCALE
	 * @param RNVTN_REMARK
	 * @param SelType
	 */
	public void txEditReit(String CHANN_RNVTN_ID,String RNVTN_SCALE,String RNVTN_REMARK,String SelType);
	
	/**
	 * @param CHANN_RNVTN_ID
	 * @param userBean
	 */
	public void txReitDtl(String CHANN_RNVTN_ID, UserBean userBean);
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo);
    
    /**
     * @param params
     * @param pageNo
     * @param CHANN_RNVTN_ID
     * @return
     */
    public IListPage  queryDecor(Map <String, String> params, int pageNo,String CHANN_RNVTN_ID);
    
    /**
     * @param params
     * @return
     */
    public List<DecorationappModel>  queryDecorT (Map <String, String> params);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadByConfId(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadByConfIdF(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public String queryReitAmount(String CHANN_RNVTN_ID);
    
    /**
     * @return
     */
    public String queryGZZXXID();
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadT(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadTt(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadByConfIdT(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadByConfIdTt(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadTypeByConfId(String CHANN_RNVTN_ID);
    
    /**
     * @param localType
     * @return
     */
    public List<DecorationappModel> loadByDictionary(String localType);
    
    /**
     * @param CHANN_RNVTN_ID
     */
    public  void  updateState(String CHANN_RNVTN_ID);
    
    /**
     * @param USE_AREA
     * @param LOCAL_TYPE
     * @param TERM_ID
     */
    public  void  updateTerm(Integer USE_AREA,String LOCAL_TYPE,String TERM_ID);
    
    /**
     * 查询报销次数
     * @param CHANN_RNVTN_ID
     * @return
     */
    public  int  queryReitDtl(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public  int   getRowcount(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public  int queryRvinState(String CHANN_RNVTN_ID);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String CHANN_RNVTN_ID, UserBean userBean);
    
    /**
     * @param BUSS_SCOPE
     * @param LEDGER_ID
     * @param LOCAL_TYPE
     * @return
     */
    public List queryByBussScope(String BUSS_SCOPE,String LEDGER_ID,String LOCAL_TYPE);
    
    /**
     * @param TERM_WHICH_NUM
     * @return
     */
    public  List<String> queryTermWhichNum(String TERM_WHICH_NUM);
    
    /**
     * 根据装修申请单号修改报销频次
     * @param CHANN_RNVTN_ID
     * @param REIT_POLICY
     */
    public  void updatePolicy(String CHANN_RNVTN_ID,String REIT_POLICY);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public  String  loadById(String CHANN_RNVTN_ID);
    
    /**
     * @param LOCAL_TYPE
     * @return
     */
    public  String  queryName(String LOCAL_TYPE);
    
    /**
     * @param CHANN_RNVTN_ID
     * @param LOCAL_TYPE
     * @param COMPACT_AMOUNT
     * @param IS_STAD_FLAG
     * @param REIT_AMOUNT_PS
     * @param REIT_AMOUNT
     */	
    public void updateByRnvtnId(String CHANN_RNVTN_ID,String LOCAL_TYPE,String COMPACT_AMOUNT,String IS_STAD_FLAG,String REIT_AMOUNT_PS,String REIT_AMOUNT);
    
    /**
     * @param CHANN_RNVTN_ID
     * @param COMPACT_AMOUNT
     * @param IS_STAD_FLAG
     * @param REIT_AMOUNT_PS
     * @param REIT_AMOUNT
     */
    public void updateByRnvtnIdT(String CHANN_RNVTN_ID,String COMPACT_AMOUNT,String IS_STAD_FLAG,String REIT_AMOUNT_PS,String REIT_AMOUNT);
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public String toQRnvtnPropt(String RNVTN_PROP);
    
    /**
     * @param LOCAL_TYPE
     * @return
     */
    public String toQLocalType(String LOCAL_TYPE);
    
    /**
     * @param BUSS_SCOPE
     * @return
     */
    public String toQBussScope(String BUSS_SCOPE);
    /**
     * @param RNVTN_PROP
     * @return
     */
    public String toReQRnvtnPropt(String RNVTN_PROP);
    
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	
	/**
     * 获取经营品牌
     * @return
     */
    public List<DecorationappModel>  queryBussScopeT();
    
	/**
     * 获取装修性质
     * @return
     */
    public List<DecorationappModel>  queryRnvtnProp();
    
    /**
     * 获取商场位置类别
     * @return
     */
    public List<DecorationappModel>  queryLocalType();
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID);
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZWT(String RYXXID,String GZZXXID);
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryStoreManage(String RYXXID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public String queryReitAmountPS(String CHANN_RNVTN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @throws Exception
     */
    public void txStoreManage(String CHANN_RNVTN_ID) throws Exception;
    
	/**
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public List <DecorationappModel> childQuery(String CHANN_RNVTN_ID);
}
