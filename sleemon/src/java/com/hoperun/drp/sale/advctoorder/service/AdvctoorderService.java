/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderService
 */
package com.hoperun.drp.sale.advctoorder.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelChld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelGoodChlds;
import com.hoperun.sys.model.UserBean;

/**
 * *@service *@func *@version 1.1 *@author lyg *@createdate 2013-08-11 17:17:29
 */
public interface AdvctoorderService extends IBaseService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	public IListPage getAdv(Map<String, String> params, int pageNo);

	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_IDS,
			List<AdvctoorderModelGoodChlds> chldModelList, UserBean userBean);

	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvctoorderModelChld> childQuery(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录并合并货品
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvctoorderModelChld> combineChildQuery(String ADVC_ORDER_ID);

	/**
	 * 按预订单id获取分组后的货品信息
	 */
	@SuppressWarnings("unchecked")
	public List findGroInfo(Map<String, String> map);

	/**
	 * 按订货方id查询区域折扣率
	 */
	public String getDECT_RATE(String ORDER_CHANN_ID);

	/** 根据订货方id查找订货方信息 */
	public Map<String, String> loadChann(String RECV_CHANN_ID);

	/**
	 * 根据货品id和当前登录帐套查找库存信息
	 */
	@SuppressWarnings("unchecked")
	public List loadProduct(Map<String, String> map);

	/**
	 * 打回
	 */
	public void txReverse(Map<String, String> map,UserBean userBean)throws Exception ;

	// 按预订单id查询未删除明细条数
	public int getCount(Map<String, String> map);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID);
	//-- 0156143--Start--刘曰刚--2014-06-16//
//	/**
//	 * 根据查出来的数据里的货品id查询没有查出来的货品编号（区域扁平表里不存在的货品编号）
//	 * @param includedId
//	 * @return
//	 */
//	public String getNotIncludedPrd(String includedId,String ADVC_ORDER_IDS);
	 
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID);
	/**
	 * 查询预订单对应的客户收款单是否核销
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public boolean chackHavaWRITE(String ADVC_ORDER_ID);
	//验证是否已经创建发货申请单
	public boolean checkShipmentFlag(String ADVC_ORDER_ID);
	//验证是否生成订货订单
	public String checkOrderFlag(String ADVC_ORDER_ID);
	public boolean checkamountFlag(String ADVC_ORDER_ID);
	public boolean checkWriteFlag(String ADVC_ORDER_ID);
	public List downQuery(Map<String,String> map);
	public String getStatementsNo(String ADVC_ORDER_ID);
	public Map<String,String> getChannInfo(String CHANN_ID);
}