package com.hoperun.drp.areaser.sotoadvorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.areaser.sotoadvorder.model.SotoadvorderGoodModelChld;
import com.hoperun.drp.areaser.sotoadvorder.service.SotoadvorderService;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public class SotoadvorderServiceImpl extends BaseService implements SotoadvorderService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Sotoadvorder.pageQuery", "Sotoadvorder.pageCount",params, pageNo);
	}
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage query(Map params, int pageNo) {
		return this.pageQuery("Sotoadvorder.query", "Sotoadvorder.count",params, pageNo);
	}

	public Map<String, String> load(String SALE_ORDER_ID) {
		return (Map<String, String>) load("Sotoadvorder.loadById", SALE_ORDER_ID);
	}
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <SaleorderModelChld> childQuery(String SALE_ORDER_ID) {
        Map params = new HashMap();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Sotoadvorder.queryChldByFkId", params);
    }
    /**
	 * 按预订单id获取分组后的货品信息
	 */
	@SuppressWarnings("unchecked")
	public List findGroInfo(Map<String, String> map) {
		return this.findList("Sotoadvorder.findGroInfo", map);
	}
	
	
	/**
	 * 根据 销售订单明细ID查询 货品的库存量
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> loadProductStoreNum(Map<String, String> map) {
		return findList("Sotoadvorder.loadProduct_StORENUM", map);
	}
	
	
	/**
	 *
	 * @param GOODS_ORDER_ID
	 * @param chldList
	 * @param ORDER_RECV_DATE
	 * @return
	 */
	 public boolean txChildEdit(String GOODS_ORDER_ID,List<SotoadvorderGoodModelChld> chldList,String SALE_ORDER_IDS,String REBATEFLAG,String REBATEDSCT) {
	        //新增列表
	        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
	        StringBuffer IDS=new StringBuffer();
	        for (SotoadvorderGoodModelChld model : chldList) {
	            Map <String, String> params = new HashMap <String, String>();
	            String SALE_ORDER_DTL_ID=model.getFROM_BILL_DTL_ID();
	            IDS.append("'").append(SALE_ORDER_DTL_ID).append("',");
	            params.put("FROM_BILL_DTL_ID", SALE_ORDER_DTL_ID);
	            Object PLE_REP=model.getPLE_REP();
	            if(StringUtil.isEmpty(PLE_REP.toString())){
	            	PLE_REP="0";
	            }
			    params.put("STORED_NUM", PLE_REP.toString());
			    Object ORDER_NUM=model.getORDER_NUM();
			    int total_ORDER_NUM=Integer.parseInt(PLE_REP.toString())+Integer.parseInt(ORDER_NUM.toString());
			    params.put("ORDER_NUM",total_ORDER_NUM+"");
                addList.add(params);
	        }
	        if (!addList.isEmpty()) {
	            this.batch4Update("Sotoadvorder.upCHANGE_NUM", addList);
	        }
	        String SALE_ORDER_DTL_IDS=IDS.toString();
	        if (!StringUtil.isEmpty(SALE_ORDER_DTL_IDS)) {
	        	SALE_ORDER_DTL_IDS = SALE_ORDER_DTL_IDS.substring(0, SALE_ORDER_DTL_IDS.length() - 1);
			}
	        inserchld(GOODS_ORDER_ID, SALE_ORDER_DTL_IDS,REBATEFLAG,REBATEDSCT);
	        return true;
	    }
	 /**
		 * @编辑
		 * @Description :
		 * @param GOODS_ORDER_ID
		 * @param GoodsorderhdModel
		 * @param userBean.
		 * @return true, if tx txEdit 
		 */
		public void txEdit(String SALE_ORDER_IDS,List <SotoadvorderGoodModelChld> chldModelList,UserBean userBean, Map<String,String> map,String REBATEFLAG,String REBATEDSCT) { 
			Map<String,String> params = new HashMap<String,String>();
				String GOODS_ORDER_ID=StringUtil.uuid32len();
				params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);//订货订单ID
				params.put("GOODS_ORDER_NO", LogicUtil.getBmgz("DRP_GOODS_ORDER_NO"));//订货订单编号
				params.put("ORDER_CHANN_ID", map.get("ORDER_CHANN_ID"));//订货方ID
				params.put("ORDER_CHANN_NO", map.get("ORDER_CHANN_NO"));//订货方编号
				params.put("ORDER_CHANN_NAME", map.get("ORDER_CHANN_NAME"));//订货方名称
				params.put("SEND_CHANN_ID", userBean.getBASE_CHANN_ID());//发货方ID
				params.put("SEND_CHANN_NO", userBean.getBASE_CHANN_NO());//发货方编号
				params.put("SEND_CHANN_NAME", userBean.getBASE_CHANN_NAME());//发货方名称
				params.put("RECV_CHANN_ID", map.get("RECV_CHANN_ID"));
				params.put("RECV_CHANN_NO", map.get("RECV_CHANN_NO"));
				params.put("RECV_CHANN_NAME", map.get("RECV_CHANN_NAME"));
				//按收货方查询联系人和联系电话,所属区域服务中心信息
				Map<String,String> channInfo=(Map<String, String>) this.load("Sotoadvorder.getChannInfo",map.get("RECV_CHANN_ID"));
				params.put("PERSON_CON", channInfo.get("PERSON_CON"));
				params.put("TEL", channInfo.get("TEL"));
				params.put("AREA_ID", channInfo.get("AREA_ID"));
				params.put("AREA_NO", channInfo.get("AREA_NO"));
				params.put("AREA_NAME", channInfo.get("AREA_NAME"));
				params.put("RECV_ADDR_NO", map.get("RECV_ADDR_NO"));//收货地址编号
				params.put("RECV_ADDR", map.get("RECV_ADDR"));//收货地址
				String BILL_TYPE = "订单转订货";//订单类型
				//生产基地信息
				Map<String,String> param=(Map<String, String>) this.load("Shopcar.getShipPointInfo", userBean.getCHANN_ID());
				params.put("SHIP_POINT_ID", param.get("SHIP_POINT_ID"));
				params.put("SHIP_POINT_NAME", param.get("SHIP_POINT_NAME"));
				if(StringUtil.isEmpty(REBATEFLAG)||"0".equals(REBATEFLAG)){
					params.put("IS_USE_REBATE", "0");
				}else{
					params.put("IS_USE_REBATE", "1");
					BILL_TYPE = "返利订货"; //订单类型 返利的必须是 返利订货
				}
				params.put("BILL_TYPE",BILL_TYPE);//订单类型
				params.put("STATE", BusinessConsts.STATE_MAKE);//状态
				params.putAll(LogicUtil.sysFild(userBean));
				txInsert(params);

				if(null!=chldModelList){
					this.txChildEdit(GOODS_ORDER_ID, chldModelList,SALE_ORDER_IDS,REBATEFLAG,REBATEDSCT );
				}
			}
		/**
		 * * 增加
		 * * @param params 
		 * 
		 * @return true, if tx insert
		 */
		public boolean txInsert(Map<String,String> params) {
			insert("Sotoadvorder.insert", params);
			return true;
		}
		/**
		 * 根据渠道id查询渠道信息级别
		 * @param CHANN_ID
		 * @return
		 */
		public Map<String,String> getChannInfoLv(String CHANN_ID){
			return (Map<String, String>) this.load("Sotoadvorder.getChannInfoLv", CHANN_ID);
		}
		//根据所选销售订单IDS查询group后的明细插入订货订单明细
		public void inserchld(String GOODS_ORDER_ID,String SALE_ORDER_DTL_IDS,String REBATEFLAG,String REBATEDSCT){
			if(StringUtil.isEmpty(REBATEFLAG)){
				REBATEFLAG="0";
			}
			double dsct=Double.parseDouble(REBATEDSCT);
			Map<String,String> map=new HashMap<String, String>();
			map.put("SALE_ORDER_DTL_IDS", SALE_ORDER_DTL_IDS);
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			List<SotoadvorderGoodModelChld> chldList=this.findList("Sotoadvorder.getSaleChld", map);
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			for (int i = 0; i < chldList.size(); i++) {
				Map<String,String> model=(Map<String, String>) chldList.get(i);
				Map<String,String> param=new HashMap<String, String>();
				param.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
				param.put("GOODS_ORDER_DTL_ID", StringUtil.uuid32len());
				param.put("PRD_ID", model.get("PRD_ID"));
				param.put("PRD_NO", model.get("PRD_NO"));
				param.put("PRD_NAME", model.get("PRD_NAME"));
				param.put("PRD_SPEC", model.get("PRD_SPEC"));
				param.put("PRD_COLOR", model.get("PRD_COLOR"));
				param.put("BRAND", model.get("BRAND"));
				param.put("STD_UNIT", model.get("STD_UNIT"));
				Object IS_NO_STAND_FLAG=model.get("IS_NO_STAND_FLAG");
				param.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG.toString());
				Object PRICE=model.get("PRICE");
				param.put("PRICE", PRICE.toString());
				param.put("OLD_PRICE", PRICE.toString());
				//订货数量
				Object ORDER_NUM= model.get("ORDER_NUM");
				//抵库数量
				Object STORED_NUM=model.get("STORED_NUM");
				//实际订货数量
				Integer num=Integer.parseInt(ORDER_NUM.toString())-Integer.parseInt(STORED_NUM.toString());
				if(num<=0){
					continue;
				}
				param.put("ORDER_NUM",num+"");
				//使用返利
				if("1".equals(REBATEFLAG)){
					param.put("DECT_RATE", dsct+"");
					double DECT_PRICE=Double.parseDouble(PRICE.toString())*dsct;
					param.put("DECT_PRICE", DECT_PRICE+"");
					double ORDER_AMOUNT=num*DECT_PRICE;
					param.put("ORDER_AMOUNT", ORDER_AMOUNT+"");
					double REBATE_PRICE = Double.parseDouble(PRICE.toString()) *(1- dsct);
					Double REBATE_AMOUNT=REBATE_PRICE*num;//返利金额
					param.put("REBATE_PRICE",REBATE_PRICE+"");//返利单价
					param.put("REBATE_AMOUNT", REBATE_AMOUNT+"");//返利金额
				}else{
					Object DECT_RATE=model.get("DECT_RATE");
					param.put("DECT_RATE", DECT_RATE.toString());
					//折后单价
					Object DECT_PRICE=model.get("DECT_PRICE");
					param.put("DECT_PRICE", DECT_PRICE.toString());
					//订货金额
					Object ORDER_AMOUNT=model.get("ORDER_AMOUNT");
					//实际订货金额
					float amount=num*Float.parseFloat(DECT_PRICE.toString());
					param.put("ORDER_AMOUNT", amount+"");
				}
				param.put("ORDER_RECV_DATE", model.get("ADVC_SEND_DATE"));
				param.put("REMARK", model.get("REMARK"));
				param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				Object SENDED_NUM=model.get("SENDED_NUM");
				param.put("SENDED_NUM", SENDED_NUM.toString());
				Object VOLUME=model.get("VOLUME");
				param.put("VOLUME", VOLUME.toString());
				float TOTAL_VOLUME=Float.parseFloat(ORDER_NUM.toString())*Float.parseFloat(VOLUME.toString());
				param.put("TOTAL_VOLUME", TOTAL_VOLUME+"");
				param.put("SPCL_TECH_ID", model.get("SPCL_TECH_ID"));
				list.add(param);
			}
			if(list.size()>0){
				this.batch4Update("Goodsorderhd.insertChld", list);
			}
		}
}
