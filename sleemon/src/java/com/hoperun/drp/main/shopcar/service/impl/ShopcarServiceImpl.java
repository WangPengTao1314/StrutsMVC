
package com.hoperun.drp.main.shopcar.service.impl;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.shopcar.model.ShopcarChannInfoModel;
import com.hoperun.drp.main.shopcar.model.ShopcarModel;
import com.hoperun.drp.main.shopcar.service.ShopcarService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class ShopcarServiceImpl extends BaseService implements ShopcarService {
	/**
	 * 查询
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Shopcar.pageQuery", "Shopcar.pageCount",
				params, pageNo);
	}
	/**
	 * @删除
	 * @param ADVC_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
		 return update("Shopcar.delete", params);
	}
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Shopcar.insert", params);
		return true;
	} 
	 
	/**
	 * 查询当前渠道
	 * @param CHANN_ID 渠道ID
	 * @return
	 */
	public Map<String,String> getChannInfo(String CHANN_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("CHANN_ID",CHANN_ID);
		return (Map<String, String>) load("Shopcar.getChannCreDit", params);
		
	}
	 
	 
	//更新购物车
	public void update(List<ShopcarModel> modelList,UserBean userBean) throws ParseException{
		List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
		for (ShopcarModel model : modelList) {
			Map<String,String> params = new HashMap<String,String>();
			String SHOP_CART_ID=model.getSHOP_CART_ID();
			params.put("SHOP_CART_ID", SHOP_CART_ID);
			params.put("ORDER_NUM", model.getORDER_NUM());
			params.put("ORDER_AMOUNT", model.getORDER_AMOUNT());
			String SESSIONID=model.getSESSION_ID();//会话id
			params.put("SESSION_ID", SESSIONID);
			params.put("UPD_NAME",userBean.getXM());//更新人名称
		    params.put("UPDATOR",userBean.getXTYHID());//更新人ID
		    params.put("UPD_TIME","数据库时间");//更新时间
		    params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
		    params.put("REMARK", model.getREMARK());//备注
		    
		    String ORDER_RECV_DATE = model.getORDER_RECV_DATE();
		    //0156469--start--刘曰刚--2014-06-18--//
		    //去掉交货日期必填校验，去掉交货日期必须在今天之后校验
//		    if(StringUtil.isEmpty(ORDER_RECV_DATE)){
//		    	throw new ServiceException("所订购货品有未选择交货日期!");
//		    }
//		    String currentDate=this.getDate();
//		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date d1 = df.parse(ORDER_RECV_DATE);    
//			Date d2 = df.parse(currentDate);    
//			long diff = d1.getTime() - d2.getTime();    
//			long days = diff / (1000 * 60 * 60 * 24);
//			if(days<0){
//				throw new ServiceException("交货日期不能在今天之前!");
//			}
		  //0156469--End--刘曰刚--2014-06-18--//
		    params.put("ORDER_RECV_DATE", ORDER_RECV_DATE);//交货日期
 
			updateList.add(params);
		}
		this.batch4Update("Shopcar.update", updateList);
	}
	
	/**
	 * 插入 订货订单
	 * @throws Exception 
	 */
	public Map<String,String>  txInsertDRP_GOODS_ORDER(ShopcarChannInfoModel channModel,String SHOP_CART_IDS,
			UserBean userBean,Map<String,String> info) throws ServiceException{
		Map<String,String> maps = new HashMap<String,String>();
		String GOODS_ORDER_ID = StringUtil.uuid32len();
		String BILL_TYPE = "";
		//子表信息保存
	    if (!StringUtil.isEmpty(SHOP_CART_IDS)) {
			  BILL_TYPE = txChildEdit(GOODS_ORDER_ID, SHOP_CART_IDS,info);
		}
	    if(StringUtil.isEmpty(BILL_TYPE)){
	    	BILL_TYPE="备货订货";
	    }
		maps.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		//判断订货订单编号是否重复了 modify by zzb 2015-01-29 
		String GOODS_ORDER_NO = LogicUtil.getBmgz("DRP_GOODS_ORDER_NO");
		int res = this.queryForInt("Shopcar.judgeGoodNo", GOODS_ORDER_NO);
		if(res >= 1){
			throw new ServiceException("sequence出问题了，请联系管理员");
		}
		
		maps.put("GOODS_ORDER_NO", GOODS_ORDER_NO);
		Map<String,String> wareaMap=(Map<String, String>) this.load("Shopcar.getWareaInfo", channModel.getORDER_CHANN_ID());
		maps.putAll(wareaMap);
		maps.put("ORDER_CHANN_ID", channModel.getORDER_CHANN_ID());
		maps.put("ORDER_CHANN_NO", channModel.getORDER_CHANN_NO());
		maps.put("ORDER_CHANN_NAME", channModel.getORDER_CHANN_NAME());
		
		// add by zzb 2014-7-26   走区域服务中心流程
		String AREA_SER_ID = userBean.getAREA_SER_ID();
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			maps.put("SEND_CHANN_ID", AREA_SER_ID);
			maps.put("SEND_CHANN_NO", userBean.getAREA_SER_NO());
			maps.put("SEND_CHANN_NAME", userBean.getAREA_SER_NAME());
			maps.put("IS_AREA_SER_ORDER","1" );
		}else{
			maps.put("SEND_CHANN_ID", userBean.getBASE_CHANN_ID());
			maps.put("SEND_CHANN_NO", userBean.getBASE_CHANN_NO());
			maps.put("SEND_CHANN_NAME", userBean.getBASE_CHANN_NAME());
			maps.put("IS_AREA_SER_ORDER","0" );
		}
		
		maps.put("BILL_TYPE", BILL_TYPE);
		maps.put("IS_USE_REBATE", info.get("IS_USE_REBATE"));
		maps.put("RECV_CHANN_ID", channModel.getCHANN_ID());
		maps.put("RECV_CHANN_NO", channModel.getCHANN_NO());
		maps.put("RECV_CHANN_NAME", channModel.getCHANN_NAME());
		maps.put("PERSON_CON", channModel.getPERSON_CON());
		maps.put("RECV_ADDR_NO", channModel.getRECV_ADDR_NO());
		maps.put("TEL", channModel.getTEL());
		maps.put("RECV_ADDR", channModel.getRECV_ADDR());
		Map<String,String> channInfo=getChannInfo(channModel.getCHANN_ID());
		maps.put("AREA_ID", channInfo.get("AREA_ID"));
		maps.put("AREA_NO", channInfo.get("AREA_NO"));
		maps.put("AREA_NAME", channInfo.get("AREA_NAME"));
		maps.put("STORE_ID", channModel.getSTORE_ID());
		maps.put("STORE_NO", channModel.getSTORE_NO());
		maps.put("STORE_NAME", channModel.getSTORE_NAME());
		maps.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		maps.put("STATE", info.get("STATE"));
		maps.put("CRE_NAME",userBean.getXM());//制单人名称
		maps.put("CREATOR",userBean.getXTYHID());//制单人ID
		maps.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		maps.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		maps.put("ORG_ID",userBean.getJGXXID());//制单机构ID
		maps.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		maps.put("CRE_TIME","数据库时间");//制单时间
		maps.put("LEDGER_ID",userBean.getLoginZTXXID());//帐套ID
		maps.put("LEDGER_NAME",userBean.getLoginZTMC());//帐套名称
		maps.put("ORDER_RECV_DATE", channModel.getORDER_RECV_DATE());//交期
		//生产基地信息
		Map<String,String> param=(Map<String, String>) this.load("Shopcar.getShipPointInfo", userBean.getCHANN_ID());
		maps.put("SHIP_POINT_ID", param.get("SHIP_POINT_ID"));
		maps.put("SHIP_POINT_NAME", param.get("SHIP_POINT_NAME"));
		txInsert(maps);
	    
		//检查信用 
		 if(StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)&&info.get("STATE").equals(BusinessConsts.UNDONE)){
    		try {
				txCheckCredit(userBean.getCHANN_ID(),GOODS_ORDER_ID,BILL_TYPE);
			} catch (Exception e) {
			  throw	new ServiceException(e.getMessage());
			}
		}
	    //更新购物车处理标记
	    Map<String,String> map=new HashMap<String,String>();
	    map.put("SHOP_CART_IDS", SHOP_CART_IDS);
	    map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
	    this.update("Shopcar.upDeal", map);
	    //如果状态为未处理，是提交到总部的  默认交期
	    if(BusinessConsts.UNDONE.equals(info.get("STATE"))){
//	    	upAdvcOrder(GOODS_ORDER_ID);
	    }
	    return maps;
	}
	
	
	
	public String txChildEdit(String GOODS_ORDER_ID,String SHOP_CART_IDS,Map<String,String>info){
		List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
		List<Map <String, Object>> modelList= (List<Map <String, Object>>) this.findList("Shopcar.getPrdInfo", SHOP_CART_IDS);
		String SHOP_CART_TYPE="";
		String REBATEDSCT = info.get("REBATEDSCT");//返利折扣
//		if(StringUtil.isEmpty(REBATEDSCT)||"0".equals(REBATEDSCT)){
//			throw new ServiceException("对不起，返利折扣为空或0，请联系管理员!");
//		}
		String IS_USE_REBATE = info.get("IS_USE_REBATE");//是否使用返利 1->使用
		String LARGESSFLAG=info.get("LARGESSFLAG");//是否使用赠品折扣
		String LARGESSDSCT=info.get("LARGESSDSCT");//赠品折扣
//		if(StringUtil.isEmpty(LARGESSDSCT)||"0".equals(LARGESSDSCT)){
//			throw new ServiceException("对不起，赠品折扣为空或0，请联系管理员!");
//		}
		for (int i=0;i<modelList.size();i++){
			Map<String,Object> model = modelList.get(i);
			Map<String,Object> params = new HashMap<String,Object>();
			String GOODS_ORDER_DTL_ID = StringUtil.uuid32len();
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			if("预订单转订货".equals(model.get("SHOP_CART_TYPE"))){
				SHOP_CART_TYPE="预订单转订货";
			}
			params.put("GOODS_ORDER_DTL_ID", GOODS_ORDER_DTL_ID);
			params.put("ORDER_RECV_DATE", model.get("ORDER_RECV_DATE"));//交期
			params.put("PRD_ID", model.get("PRD_ID"));
			params.put("PRD_NO", model.get("PRD_NO"));
			params.put("PRD_NAME", model.get("PRD_NAME"));
			params.put("PRD_SPEC", model.get("PRD_SPEC"));
			params.put("PRD_COLOR", model.get("PRD_COLOR"));
			params.put("BRAND", model.get("BRAND"));
			params.put("STD_UNIT", model.get("STD_UNIT"));
			params.put("ROW_NO", i+1);//行号
			//如果使用返利 就是计算返利折扣   add by zzb 2014-6-19 
			if(BusinessConsts.INTEGER_1.equals(IS_USE_REBATE)){
				SHOP_CART_TYPE="返利订货";
				Double PRICE = StringUtil.getDouble(StringUtil.nullToSring(model.get("PRICE")));
				Double REBATE_DECT = StringUtil.getDouble(REBATEDSCT);//返利折扣
				Integer ORDER_NUM=Integer.parseInt(model.get("ORDER_NUM").toString());
				Double DECT_PRICE = PRICE*REBATE_DECT;
				// add by zzb 返利单价=单价-返利折扣价
				Double REBATE_PRICE = PRICE - DECT_PRICE;//返利单价
				Double REBATE_AMOUNT=REBATE_PRICE*ORDER_NUM;//返利金额
				Double ORDER_AMOUNT=DECT_PRICE*ORDER_NUM;
				params.put("DECT_RATE", REBATE_DECT);
				params.put("DECT_PRICE",DECT_PRICE);
				params.put("ORDER_AMOUNT",ORDER_AMOUNT);
				params.put("REBATE_PRICE",REBATE_PRICE);//返利单价
				params.put("REBATE_AMOUNT", REBATE_AMOUNT);//返利金额
			//如果使用赠品折扣
			}else if(BusinessConsts.INTEGER_1.equals(LARGESSFLAG)){
				SHOP_CART_TYPE="赠品订货";
				Double PRICE = StringUtil.getDouble(StringUtil.nullToSring(model.get("PRICE")));
				Double LARGESS_DSCT = StringUtil.getDouble(LARGESSDSCT);//赠品折扣
				Integer ORDER_NUM=Integer.parseInt(model.get("ORDER_NUM").toString());
				Double DECT_PRICE = PRICE*LARGESS_DSCT;
				Double ORDER_AMOUNT=DECT_PRICE*ORDER_NUM;
				params.put("DECT_RATE", LARGESS_DSCT);
				params.put("DECT_PRICE",DECT_PRICE);
				params.put("ORDER_AMOUNT",ORDER_AMOUNT);
			}else{
				params.put("DECT_RATE", model.get("DECT_RATE"));
				params.put("DECT_PRICE",model.get("DECT_PRICE"));
				params.put("ORDER_AMOUNT", model.get("ORDER_AMOUNT"));
			}
			params.put("PRICE", model.get("PRICE"));
			params.put("ORDER_NUM",model.get("ORDER_NUM"));
			params.put("VOLUME",StringUtil.nullToSring(model.get("VOLUME")));
			params.put("TOTAL_VOLUME",StringUtil.nullToSring(model.get("TOTAL_VOLUME")));
			
			params.put("SPCL_TECH_ID", model.get("SPCL_TECH_ID"));
			Integer SPCL_TECH_FLAG = StringUtil.getInteger(StringUtil.nullToSring(model.get("SPCL_TECH_FLAG")));
			String IS_NO_STAND_FLAG = "0";
			if(SPCL_TECH_FLAG > 1){
				IS_NO_STAND_FLAG = "1";
			}
			params.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			
			params.put("REMARK", model.get("REMARK"));//备注
			addList.add(params);
		}
		this.batch4Update("Shopcar.insertDtl", addList);
		txUpShopCar(GOODS_ORDER_ID,SHOP_CART_IDS);
		return SHOP_CART_TYPE;
	}
	public void txUpShopCar(String GOODS_ORDER_ID,String SHOP_CART_IDS){
		Map<String,String> map=new HashMap<String,String>();
		map.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		map.put("SHOP_CART_IDS", SHOP_CART_IDS);
		update("Shopcar.upShopCar",map);
	}
	public void upPrice(Map<String, String> map) {
		this.update("Shopcar.upPrice", map);
	}
	// 获取数据库当前时间
	public String getDate() {
		return load("Shopcar.getDate", "").toString();
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Shopcar.pageCount", map);
		return Long.parseLong(obj.toString());
	}
	/**
     * 根据当前登录人所属渠道获取渠道类型
     * @param CHANN_ID
     * @return
     */
    public Map<String,String> getCHANN_TYPE(String CHANN_ID){
    	return (Map<String, String>) this.load("Shopcar.getCHANN_TYPE",CHANN_ID);
    }
    public String getLargessDect(String CHANN_ID){
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("CHANN_ID", CHANN_ID);
    	map.put("DECT_TYPE", "赠品折扣");
    	Object obj=this.load("Shopcar.getLargessDect", map);
    	
    	if(null==obj){
    		return null;
    	}else{
    		return obj.toString();
    	}
    }
    public void txCheckCredit(String CHANN_ID,String GOODS_ORDER_ID,String BILL_TYPE) throws Exception{
		if(!"赠品订货".equals(BILL_TYPE)){
	    	Map<String,String> paraMap=new HashMap<String,String>();
	    	paraMap.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
	    	paraMap.put("CHANN_ID", CHANN_ID);
			update("Goodsorderhd.updateCREDIT_FREEZE_PRICE", paraMap);
			if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
				if(!CreditCtrlUtil.chkCanUseCreditForCommit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID)){
					throw new ServiceException("对不起，您的信用额度不够!");
				}
			}else{
				if(!LogicUtil.chkCanUseCreditForCommit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID)){
					throw new ServiceException("对不起，您的信用额度不够!");
				}			
			}
		}
		
		Map<String,Object> orderMap = (Map<String,Object>)load("Goodsorderhd.loadById", GOODS_ORDER_ID);
		String  isUseRebate  =  orderMap.get("IS_USE_REBATE").toString();
		if(!"赠品订货".equals(BILL_TYPE)){
			if("1".equals(isUseRebate.toString())){
				if(LogicUtil.chkCanUseRebate(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, GOODS_ORDER_ID)){
					if(LogicUtil.chkAllCanUseRebate(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID)){
						LogicUtil.updFreezeCredit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
						LogicUtil.inertOrderCreditJournal(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
						LogicUtil.updateRebate(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, GOODS_ORDER_ID);
						LogicUtil.insRebateJournal(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, GOODS_ORDER_ID);
					}else{
						throw new Exception("对不起，您总的信用不够订货!");
					}
				}else{
					throw new Exception("对不起，您的返利金额不够!");
				}				
			}else{
				LogicUtil.updFreezeCredit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
				LogicUtil.inertOrderCreditJournal(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
			}
		}
    }
  //修改订货订单交期
	public void upAdvcOrder(String GOODS_ORDER_ID){
		this.update("Goodsorderhd.upAdvcOrder", GOODS_ORDER_ID);
	}
	public boolean txSaveCommit(Map<String,String> shopParams,List<ShopcarModel> ModelList,UserBean userBean,ShopcarChannInfoModel channModel) throws Exception {
		Map<String, String> info = new HashMap<String, String>();
		if (shopParams.get("type").equals("save")) {
			this.update(ModelList, userBean);
		} else if (shopParams.get("type").equals("add")) {// 下单
			info.put("STATE", BusinessConsts.STATE_MAKE);
			info.put("IS_USE_REBATE", shopParams.get("rebate"));
			info.put("REBATEDSCT", shopParams.get("REBATEDSCT"));
			info.put("LARGESSFLAG", shopParams.get("LARGESSFLAG"));
			info.put("LARGESSDSCT", shopParams.get("LARGESSDSCT"));
			this.update(ModelList, userBean);
			
			Map<String, String> returnMap = this.txInsertDRP_GOODS_ORDER(channModel, shopParams.get("SHOP_CART_IDS"),userBean, info);
			// 插入订货单的生命周期
			String GOODS_ORDER_ID=returnMap.get("GOODS_ORDER_ID");
			if(StringUtil.isEmpty(GOODS_ORDER_ID)){
				saveOrderTruck(GOODS_ORDER_ID,returnMap.get("BILL_TYPE"),returnMap.get("GOODS_ORDER_NO"), userBean);
			}
		} else if (shopParams.get("type").equals("commit")) {
			info.put("STATE", BusinessConsts.UNDONE);
			info.put("IS_USE_REBATE", shopParams.get("rebate"));
			info.put("REBATEDSCT", shopParams.get("REBATEDSCT"));
			info.put("LARGESSFLAG", shopParams.get("LARGESSFLAG"));
			info.put("LARGESSDSCT", shopParams.get("LARGESSDSCT"));
			update(ModelList, userBean);
			Map<String, String> returnMap = txInsertDRP_GOODS_ORDER(channModel, shopParams.get("SHOP_CART_IDS"),userBean, info);
			String GOODS_ORDER_ID = returnMap.get("GOODS_ORDER_ID");
			//插入订货订单跟踪表
			Map<String,String> params=new HashMap<String, String>();
			String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
			params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			params.put("BILL_NO", returnMap.get("GOODS_ORDER_NO"));//订货订单的NO
			params.put("BILL_TYPE", returnMap.get("BILL_TYPE"));//订货订单的type
			params.put("ACTION_NAME", BusinessConsts.ORDER_BILL_ACTION_COMMIT);//订货订单提交
			params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL + GOODS_ORDER_ID);
			params.put("DEAL_PSON_NAME", userBean.getXM());
			params.put("STATE", BusinessConsts.UNDONE);
			insert("Goodsorderhd.insertOrderTrack", params);

			//更新订货订单的订货日期
			Map<String,String> mapDate = new HashMap<String,String>();
			mapDate.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			mapDate.put("ORDER_DATE",BusinessConsts.UPDATE_TIME);
		    update("Goodsorderhd.updateById",mapDate);
		    
			String AREA_SER_ID = userBean.getAREA_SER_ID();
			//没有区域服务中心的 
			if(StringUtil.isEmpty(AREA_SER_ID)&&!StringUtil.isEmpty(GOODS_ORDER_ID)){
				//插入预订单跟踪表
				params.clear();
				params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
				List<Map<String,String>> advcList = this.findList("Goodsorderhd.queryAdvcOrderByGoodsOrder", params);
				if(null != advcList && advcList.size()>0){
					for(Map<String,String> advc : advcList){
						Map<String, String> map = new HashMap<String, String>();
						map.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
						map.put("ADVC_ORDER_ID", advc.get("ADVC_ORDER_ID"));
						map.put("ACTION", "预订单录入");
						map.put("REMARK", "待总部处理");
						map.put("ACTOR", userBean.getXM());
						map.put("BILL_NO", advc.get("ADVC_ORDER_NO"));
						insert("Advcorder.insertTrace", map);
					}
				}
			}
			boolean flag=LogicUtil.checkSpclOrder("DRP_GOODS_ORDER_DTL", "GOODS_ORDER_ID", GOODS_ORDER_ID);
			if(!flag){
				throw new ServiceException("对不起，您的单据明细存在非标特殊工艺，不能提交 !");
			}
		}
		if(!checkAdvcReturn(shopParams.get("SHOP_CART_IDS"))){
			throw new ServiceException("对不起，您的单据明细存在已删除的货品，请刷新页面后重新操作!");
		}
		return true;
	}
	/**
	 * 插入生命周期表
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param BILL_TYPE 单据类型
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void saveOrderTruck(String GOODS_ORDER_ID,String BILL_TYPE,String GOODS_ORDER_NO, UserBean userBean){
		Map<String,Object>params = new HashMap<String,Object>();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("BILL_NO", GOODS_ORDER_NO);//订货订单的NO
		params.put("BILL_TYPE", BILL_TYPE);//订货订单的type
		
		params.put("ACTION_NAME", BusinessConsts.ORDER_BILL_ACTION_MAKE);//订货订单制作
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL+GOODS_ORDER_ID);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", BusinessConsts.STATE_MAKE);
		
		insert("Goodsorderhd.insertOrderTrack", params);
		
	}
	/**
	 * 下单，保存，下单并提交时验证预订单转订货是否退回
	 * @param SHOP_CAR_IDS
	 * @return
	 */
	public boolean checkAdvcReturn(String SHOP_CART_IDS){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SHOP_CART_IDS", SHOP_CART_IDS);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		int count=StringUtil.getInteger(this.load("Shopcar.checkAdvcReturn", map));
		if(count>0){
			return false;
		}
		return true;
	}
}