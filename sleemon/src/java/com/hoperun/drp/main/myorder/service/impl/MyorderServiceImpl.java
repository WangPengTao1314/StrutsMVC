
package com.hoperun.drp.main.myorder.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.myorder.model.MyorderModel;
import com.hoperun.drp.main.myorder.service.MyorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class MyorderServiceImpl extends BaseService implements MyorderService {
	/**
	 * 查询
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Myorder.pageQuery", "Myorder.pageCount",
				params, pageNo);
	}
	//获取货品状态为启用状态的所有品牌
	@SuppressWarnings("unchecked")
	public List<String> findBRANDAll(Map<String,String> map) {
		return this.findList("Myorder.findBRANDAll",map);
	}
	//获取货品状态为启用状态的所有品牌
	@SuppressWarnings("unchecked")
	public List<String> findPRMT_PLANAll(Map<String,String> map) {
		return this.findList("Myorder.findPRMT_PLANAll",map);
	}
	//添加到购物车表
	@SuppressWarnings("unchecked")
	public void txEdit(List<MyorderModel> modelList,UserBean userBean) {
		List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
		List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
		for (MyorderModel model : modelList) {
			Map<String,String> params = new HashMap<String,String>();
			String SHOP_CART_ID=StringUtil.uuid32len();
			String PRD_ID=model.getPRD_ID();
			String SESSIONID=model.getSESSION_ID();//会话id
			String SPCL_TECH_ID=model.getSPCL_TECH_ID();
			String DEL_FLAG=BusinessConsts.DEL_FLAG_COMMON;
			String DEAL_FLAG=BusinessConsts.YJLBJ_FLAG_FALSE;
			//如果sessionid查到数据，则把购物车工艺单临时表里的数据转移到购物车工艺单表里
			Map<String,String> map=new HashMap<String,String>();
			map.put("PRD_ID", PRD_ID);
			map.put("DEL_FLAG", DEL_FLAG);
			map.put("DEAL_FLAG", DEAL_FLAG);
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
			map.put("SHOP_CART_TYPE", "手工新增");
//			Map<String,Object> result =new HashMap<String,Object>();
//			result =(Map<String, Object>) load("Myorder.getByPrdInfo",map);
			float ORDER_NUM=0;//订货数量
//			if(result !=null&&StringUtil.isEmpty(SPCL_TECH_ID)){
//				params.put("SHOP_CART_ID", result.get("SHOP_CART_ID").toString());
//				String oldNum=result.get("ORDER_NUM").toString();
//				ORDER_NUM=Float.parseFloat(oldNum)+Float.parseFloat(model.getORDER_NUM());
//			}else{
				params.put("SHOP_CART_ID",SHOP_CART_ID);
				ORDER_NUM=Float.parseFloat(model.getORDER_NUM());//订货数量
//			}
			params.put("SPCL_TECH_ID", SPCL_TECH_ID);
			params.put("ORDER_NUM", ORDER_NUM+"");
			String PRICE=model.getPRICE();//单价
			params.put("PRICE", PRICE);
			String DECT_RATE=model.getDECT_RATE();//折扣率
			params.put("DECT_RATE", DECT_RATE);
			String DECT_PRICE=model.getDECT_PRICE();//折扣价
			float ORDER_AMOUNT=Float.parseFloat(DECT_PRICE)*ORDER_NUM;//订货金额
			params.put("ORDER_AMOUNT", ORDER_AMOUNT+"");
			params.put("DECT_PRICE", DECT_PRICE+"");
			params.put("DEL_FLAG", DEL_FLAG);
			params.put("DEAL_FLAG", DEAL_FLAG);
			params.put("SESSION_ID", SESSIONID);
			params.put("PRD_ID", PRD_ID);
			params.put("SHOP_CART_TYPE", "手工新增");
			params.put("CREATOR",userBean.getXTYHID());//制单人ID
			params.put("CRE_NAME", userBean.getXM());//制单人名称
			params.put("UPDATOR",userBean.getXTYHID());//更新人ID
			params.put("UPD_NAME", userBean.getXM());//更新人名称
			params.put("UPD_TIME", "数据库时间");//更新时间
		    params.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		    params.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		    params.put("ORG_ID",userBean.getJGXXID());//制单机构ID
		    params.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		    params.put("CRE_TIME","数据库时间");//制单时间
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());//帐套ID
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());//帐套名称
//		    if(result!=null&&StringUtil.isEmpty(SPCL_TECH_ID)){
//		    	updateList.add(params);
//		    }else{
		    	addList.add(params);
//		    }
		}
		this.batch4Update("Myorder.insert", addList);
		this.batch4Update("Myorder.update", updateList);
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Myorder.pageCount", map);
		return Long.parseLong(obj.toString());
	}
	//-- 0156143--Start--刘曰刚--2014-06-16//
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID){
		Object obj=this.load("Myorder.getChannDiscount", CHANN_ID);
		if(null!=obj){
			return obj.toString();
		}
		return null;
	}
	//-- 0156143--End--刘曰刚--2014-06-16//
	/**
	 * 查询货品分类
	 */
	public List<String> findType(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		map.put("STATE", "'启用','停用'");
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Myorder.findType", map);
	}
}