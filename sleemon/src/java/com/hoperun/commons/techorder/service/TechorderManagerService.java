package com.hoperun.commons.techorder.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.commons.techorder.model.TechorderManagerModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * 订货特殊工艺单共同
 * @author liu_yuegang
 *
 */
public interface TechorderManagerService extends IBaseService {
	//-- 0156143--Start--刘曰刚--2014-06-17//
		//修改特殊工艺获取价格方式，改变方法参数
	/**
	 * 根据订单特殊工艺id查询订单特殊工艺信息主表信息
	 * @param SPCL_TECH_ID
	 * @return
	 */
	public Map<String,String> getSpclInfoById(Map<String,String> map);
	/**
	 * 根据货品id查询货品信息
	 * @param PRD_ID
	 * @return
	 */
	public Map<String,String> getPrdInfoById(Map<String,String> map);
	//-- 0156143--End--刘曰刚--2014-06-17//
	/**
	 * 根据特殊工艺id和货品id查询货品特殊工艺明细信息
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> getSpclById(Map<String,String> map);
	/**
	 * 根据货品id查询货品特殊工艺明细信息
	 * @param PRD_ID
	 * @return
	 */
	public List<Map<String,String>> getSpclByPrdId(String PRD_ID);
	/**
	 * 根据特殊工艺id和货品id查询货品特殊工艺明细信息 不过滤使用标记
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getSpclByIdNotUSE_FLAG(Map<String,String> map);
	/**
	 * @param SPCL_TECH_ID	特殊工艺主表id
	 * @param list	明细List
	 * @param PRD_ID	货品id
	 * @param BASE_PRICE 基准价
	 * @param DECT_RATE	折扣率
	 * @param USE_FLAG	使用标记
	 * @param TABLE	需要反填的表名
	 * @param BUSSID	需要反填的id
	 * return 特殊工艺单
	 */
	public Map<String,String> txEdit(String SPCL_TECH_ID,List <TechorderManagerModelChld> list,String PRD_ID,String BASE_PRICE,String DECT_RATE,String USE_FLAG,String TABLE,String BUSSID,String addFlag,UserBean userBean);
	 //-- 0156143--Start--刘曰刚--2014-06-16//
	//修改获取渠道折扣率方式，原来从渠道折扣扁平表获取，现在改为从渠道折扣率表里获取
//	/**
//	 * 根据渠道id和货品id查询基准价和折扣率
//	 * @param params
//	 * @return
//	 */
//	public Map<String, String> getPriceInfo(Map<String, String> params);
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID);
	//-- 0156143--End--刘曰刚--2014-06-16//
	/**
	 * 按特殊工艺单id查找货品
	 */
	public String getPrdId(String SPCL_TECH_ID);
	/**
	 * 按当前登录人所属帐套信息查询历史
	 */
	public List<Map<String,String>> queryHistory(String querySql);
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	/**
	 * 根据特殊工艺id查询特殊工艺
	 * @param SPCL_TECH_ID
	 * @return
	 */
	public Map<String,String> getViewSpclById(Map<String,String> map);
	/**
	 * 根据特殊工艺id获取特殊工艺信息
	 */
	public List<Map<String,String>> getSpclInfoById(String SPCL_TECH_ID);
	/**
	 * 获取特殊工艺编号
	 * @return
	 */
	public String getSpclNo();
	/**
	 * 根据特殊工艺id修改特殊工艺编号
	 * @param SPCL_TECH_ID
	 * @param SPCL_TECH_NO
	 */
	public void updateSpecTechNO(String SPCL_TECH_ID,String SPCL_TECH_NO);
	
	public Map<String,String> getSpclDataById (String SPCL_TECH_ID);
	
	public String txSpclDataByPdtNo (String specTechId,String PRD_NO);
	public boolean txUpdateSaleChld(Map<String,String> map);
}
