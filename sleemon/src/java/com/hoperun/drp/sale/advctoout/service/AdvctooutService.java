/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctooutService
*/
package com.hoperun.drp.sale.advctoout.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advctoout.model.AdvctooutModelChld;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public interface AdvctooutService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * @主表详细页面
	 * @param param ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ADVC_ORDER_ID);
    public boolean txUpdateById(Map<String,String> params);
	 /**
     * * 根据主表Id查询子表记录分页
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @return the list
     */
    public IListPage childQuery(Map<String,String> params, int pageNo);
    /**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @return the list
     */
    public List<AdvctooutModelChld> childQuery(String ADVC_ORDER_ID);
    /**
     * 根据货品id和当前登录帐套查找库存信息
     */
    @SuppressWarnings("unchecked")
	public List loadProduct(Map<String,String> map);
    /**
     * 按库房编号或库房名称查找库房数据
     */
    public Map<String,String> findSTOREOUT(Map<String,String> map);
	public List<Map<String,String>> getDtl(Map<String,String> map);
	//获取三天内并且库存足够发货的货品的预订单数量
	public int getCountQual(Map<String,String> map);
	//反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS);
}