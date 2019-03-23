package com.hoperun.drp.sale.advctoout.service.impl;
/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctooutServiceImpl
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.advctoout.model.AdvctooutModelChld;
import com.hoperun.drp.sale.advctoout.service.AdvctooutService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvctooutServiceImpl extends BaseService implements AdvctooutService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Advctoout.pageQuery", "Advctoout.pageCount",params, pageNo);
	}
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Advcorder.updateById", params);
	}
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, Object> param) {
		insert("Advctoout.insert", param);
		return true;
	}
	/**
	 * @详细
	 * @param param ADVC_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Advctoout.loadById", param);
	}
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <AdvctooutModelChld> childQuery(String ADVC_ORDER_ID) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        return this.findList("Advctoout.loadChld", params);
    }

    /**
     * 根据货品id和当前登录帐套查找库存信息
     */
	@SuppressWarnings("unchecked")
	public List loadProduct(Map<String,String> map){
    	return  findList("Advctoout.loadProduct",map);
    }
    /**
     * 添加特殊工艺
     * @param map
     * @return
     */
    public boolean insertSpcl(Map<String,String> map){
    	insert("Advctoout.insertSpcl", map);
		return true;
    }
    /**
     * 按库房编号或库房名称查找库房数据
     */
    @SuppressWarnings("unchecked")
	public Map<String,String> findSTOREOUT(Map<String,String> map){
    	return (Map<String,String>) load("Advctoout.findSTOREOUT", map);
    }
	@Override
	public IListPage childQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Advctoout.pageQueryChld", "Advctoout.pageCountChld",params, pageNo);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getDtl(Map<String,String> map){
		return findList("Advctoout.getDtl",map);
	}
	public int getCountQual(Map<String,String> map){
		Object obj=load("Advctoout.getCountQual",map);
		if(null==obj){
			obj="0";
		}
		return  Integer.parseInt(obj.toString());
	}
	//反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS){
		Map<String,String> map=new HashMap<String,String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advctoout.upUSE_FLAG", map);
	}
}