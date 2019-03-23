package com.hoperun.drp.base.paramset.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.paramset.model.ParamsetModel;
import com.hoperun.sys.model.UserBean;

public interface ParamsetService extends IBaseService {

	 /**
     * 分页查询
     * Description :.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    
    /**
     * 查询表单条记录详细信息
     * Description :.
     * @param DATA_CONF_ID
     * @return the map< string, string>
     */
    public Map <String, String> load(String LEDGER_ID);
    
    
    public Map <String, String> loadByConfId(String DATA_CONF_ID);
    
    
    /**
     * 编辑：新增/修改
     * Description :.
     * @param CHANN_ID the 渠道ID
     * @param modelList the  参数list
     * @param userBean the user bean
     */
    public void txEdit(String CHANN_ID, List<ParamsetModel> modelList, UserBean userBean);
    
    
    /**
     * 删除数据
     * 
     * @param DATA_CONF_IDS 
     * @return true, if tx delete
     */
    public boolean txDelete(String DATA_CONF_IDS, UserBean userBean);
    
    /**
     * 修改最大冻结天数
     */
    public void  upFreenDaysById(Map<String,String> map);
    
    
    /**
     * 按渠道id获取渠道信息
     */
    public Map<String,String> getChannInfo(String CHANN_ID);
    
    /**
     * 查询数据表中是否有参数名称、类型、值的记录存在
     */
    public int queryParams(String dataName,String dataType,String dataVal);
    
    public List<ParamsetModel> isHaveParamsetList(String dataName,String dataType,String dataVal);
    
    /**
     * 查询
     * @param DATA_TYPE 
     * @param LEDGER_ID
     * @return
     */
    public List<ParamsetModel> query(String DATA_TYPE,String LEDGER_ID);
    
    /**
     * 查询 参数类型
     */
    public List<Map<String,String>> loadType(String LEDGER_ID);
    
    
    
}
