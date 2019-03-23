package com.hoperun.erp.sale.rebate.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

public interface RebateService extends IBaseService{
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param);
 
    /**
     * 保存返利
     * Description :.
     * @param modelList the chann list
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(List<ChannModel> modelList, UserBean userBean);
 
	/**
     * 清空返利
     * Description :.
     * 2、如果全部没有冻结  就插入返利历史记录表 ERP_REBATE_HIS
	 * 3、更新BASE_CHANN表
     * 
     * @param modelList the chann list
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txRest(UserBean userBean);
    
    
    /**
     * 看有没有被冻结的返利
     * NVL(REBATE_FREEZE,0)>0
     * @return
     */
	public List<ChannModel> isHaveRebateList();
	
	 public boolean txUpdateRebateByIds(Map params); 
	 public List downQuery(Map<String,String> map);
}
