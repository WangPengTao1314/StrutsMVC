/**
 * prjName:喜临门营销平台
 * ucName:订货订单维护
 * fileName:GoodsorderhdServiceImpl
*/
package com.hoperun.drp.sale.goodsorderhd.service.impl;
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
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModel;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.goodsorderhd.service.GoodsorderhdService;
import com.hoperun.sys.model.UserBean;
/**
 * *@订货订单维护
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-15 10:35:10
 */
public class GoodsorderhdServiceImpl extends BaseService implements GoodsorderhdService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Goodsorderhd.pageQuery", "Goodsorderhd.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Goodsorderhd.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param GOODS_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Goodsorderhd.delete", params);
		 //删除子表 
		 return update("Goodsorderhd.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Goodsorderhd.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param GOODS_ORDER_ID
	 * @param GoodsorderhdModel
	 * @param userBean.
	 * @return true, if tx txEdit 
	 */
	public String txEdit(String GOODS_ORDER_ID, GoodsorderhdModel model,List<GoodsorderhdModelChld> chldList, UserBean userBean,String REBATEDSCT) {
		Map<String,String> params = new HashMap<String,String>();
		String IS_USE_REBATE = "";
		String BILL_TYPE = "";
		if(null != model){
			IS_USE_REBATE = model.getIS_USE_REBATE();
			Map<String,String> wareaMap=(Map<String, String>) this.load("Shopcar.getWareaInfo", model.getORDER_CHANN_ID());
			params.putAll(wareaMap);
			params.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID());
			params.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO());
			params.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME());
			
			params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());
			params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());
			params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());
			params.put("PERSON_CON", model.getPERSON_CON());
			params.put("TEL", model.getTEL());
			params.put("RECV_ADDR", model.getRECV_ADDR());
			params.put("SEND_CHANN_ID", model.getSEND_CHANN_ID());
			params.put("SEND_CHANN_NO", model.getSEND_CHANN_NO());
			params.put("SEND_CHANN_NAME", model.getSEND_CHANN_NAME());
			params.put("REMARK", model.getREMARK());//备注
			
			if(StringUtil.isEmpty(IS_USE_REBATE)){
				params.put("IS_USE_REBATE", "0");
			}else{
				params.put("IS_USE_REBATE", model.getIS_USE_REBATE());
			}
			if(BusinessConsts.INTEGER_1.equals(IS_USE_REBATE)){
				BILL_TYPE = "返利订货";
			}
			params.put("ORDER_RECV_DATE", model.getORDER_RECV_DATE());//要求到货日期
			params.put("AREA_ID", model.getAREA_ID());
			params.put("AREA_NO", model.getAREA_NO());
			params.put("AREA_NAME", model.getAREA_NAME());
			params.put("STORE_ID", model.getSTORE_ID());
			params.put("STORE_NO", model.getSTORE_NO());
			params.put("STORE_NAME", model.getSTORE_NAME());
			params.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());//收货地址编号
			if(StringUtil.isEmpty(GOODS_ORDER_ID)){
				GOODS_ORDER_ID = StringUtil.uuid32len();
				params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
				//判断订货订单编号是否重复了 modify by zzb 2015-01-29 
				String GOODS_ORDER_NO = LogicUtil.getBmgz("DRP_GOODS_ORDER_NO");
				int res = this.queryForInt("Shopcar.judgeGoodNo", GOODS_ORDER_NO);
				if(res >= 1){
					throw new ServiceException("sequence出问题了，请联系管理员");
				}
				params.put("GOODS_ORDER_NO", GOODS_ORDER_NO);
			    params.put("CRE_NAME",userBean.getXM());
			    params.put("CREATOR",userBean.getXTYHID());
			    params.put("UPD_NAME",userBean.getXM());
			    params.put("UPDATOR",userBean.getXTYHID());
			    params.put("DEPT_ID",userBean.getBMXXID());
			    params.put("DEPT_NAME",userBean.getBMMC());
			    params.put("ORG_ID",userBean.getJGXXID());
			    params.put("ORG_NAME",userBean.getJGMC());
			    params.put("LEDGER_ID",userBean.getLoginZTXXID());
			    params.put("LEDGER_NAME",userBean.getLoginZTMC());
			    params.put("STATE",BusinessConsts.STATE_MAKE);//制作
			    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			    //生产基地信息
				Map<String,String> param=(Map<String, String>) this.load("Shopcar.getShipPointInfo", userBean.getCHANN_ID());
				params.put("SHIP_POINT_ID", param.get("SHIP_POINT_ID"));
				params.put("SHIP_POINT_NAME", param.get("SHIP_POINT_NAME"));
			    if(StringUtil.isEmpty(BILL_TYPE)){
			     BILL_TYPE = BusinessConsts.INSTORE_ORDER_TYPE;
			    } 
			    params.put("BILL_TYPE", BILL_TYPE);
			    txInsert(params);
			    //插入生命周期表
			    saveOrderTruck(GOODS_ORDER_ID,BILL_TYPE,params.get("GOODS_ORDER_NO"),userBean);
			    
			} else{
				if("返利订货".endsWith(BILL_TYPE)){
					params.put("BILL_TYPE", BILL_TYPE);
				}
			    params.put("UPD_NAME",userBean.getXM());
			    params.put("UPDATOR",userBean.getXTYHID());
			    params.put("UPD_TIME","sysdate");
				params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
				txUpdateById(params);
				clearCaches(GOODS_ORDER_ID);
			}
		}
	
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	Map<String,String> paramsMap = new HashMap<String,String>();
	    	paramsMap.put("IS_USE_REBATE", IS_USE_REBATE);
	    	paramsMap.put("REBATEDSCT", REBATEDSCT);
			txChildEdit(GOODS_ORDER_ID, chldList,null,paramsMap,"edit");
		}
	    
	    
	    return GOODS_ORDER_ID;
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
	 * @详细
	 * @param param GOODS_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String param) {
		return (Map<String,Object>) load("Goodsorderhd.loadById", param);
	}
	
	
	/**
	 * 查询 锁住此行
	 * @param GOODS_ORDER_ID
	 * @return
	 */
	public Map<String,Object> txLoadForUpdate(String GOODS_ORDER_ID){
		return (Map<String,Object>) load("Goodsorderhd.loadByIdForUpdate", GOODS_ORDER_ID);
	}
	
	/**
	 * 子 保存
	 * @param GOODS_ORDER_ID 订单ID
	 * @param modelList 明细list
	 * @param ORDER_RECV_DATE 交期
	 * @param IS_USE_REBATE 是否使用返利 1->使用
	 * @return
	 */
    @Override
    public boolean txChildEdit(String GOODS_ORDER_ID,List<GoodsorderhdModelChld> chldList, 
    		String ORDER_RECV_DATE,Map<String,String> params,String action) {
    	String SPCL_TECH_IDS="";
    	Map<String,String>paramMap = new HashMap<String,String>();
    	paramMap.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
    	int ROW_NO = this.queryForInt("Goodsorderhd.queryMaxRowNo", paramMap);
    	//使用返利
        String IS_USE_REBATE = params.get("IS_USE_REBATE");
        //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (GoodsorderhdModelChld model : chldList) {
            Map <String, Object> child = new HashMap <String, Object>();
            child.put("PRD_ID",model.getPRD_ID());
            child.put("PRD_NO",model.getPRD_NO());
            child.put("PRD_NAME",model.getPRD_NAME());
            child.put("PRD_SPEC",model.getPRD_SPEC());
            child.put("PRD_COLOR",model.getPRD_COLOR());
            child.put("BRAND",model.getBRAND());
            child.put("STD_UNIT",model.getSTD_UNIT());
            child.put("PRICE",model.getPRICE());
            child.put("DECT_RATE",model.getDECT_RATE());
            child.put("DECT_PRICE",model.getDECT_PRICE());
            child.put("ORDER_NUM",model.getORDER_NUM());
            child.put("ORDER_AMOUNT",model.getORDER_AMOUNT());
            child.put("GOODS_ORDER_ID",GOODS_ORDER_ID); 
            child.put("VOLUME",model.getVOLUME()); 
            child.put("TOTAL_VOLUME",model.getTOTAL_VOLUME()); 
            child.put("OLD_PRICE",model.getPRICE());
            child.put("IS_NO_STAND_FLAG",model.getIS_NO_STAND_FLAG());//是否非标 1->非标
            child.put("ORDER_RECV_DATE",model.getORDER_RECV_DATE());//要求到货日期
            child.put("REMARK", model.getREMARK());//备注
            
            if(BusinessConsts.INTEGER_1.equals(IS_USE_REBATE)){
				//单价
				Double PRICE = StringUtil.getDouble(StringUtil.nullToSring(model.getPRICE()));
				//订货数量
				Integer ORDER_NUM = StringUtil.getInteger(StringUtil.nullToSring(model.getORDER_NUM()));
				//返利折扣
//				if(StringUtil.isEmpty(params.get("REBATEDSCT"))||"0".equals(params.get("REBATEDSCT"))){
//					throw new ServiceException("对不起，返利折扣为空或0，请联系管理员!");
//				}
				Double REBATE_DECT = StringUtil.getDouble(params.get("REBATEDSCT"));
				//计算折后价=单价*返利折扣
				Double DECT_PRICE = PRICE*REBATE_DECT;
				//计算订货总金额
				Double ORDER_AMOUNT  = DECT_PRICE*ORDER_NUM;
				// add by zzb 返利单价=单价-返利折扣价
				Double REBATE_PRICE = PRICE - DECT_PRICE;
				child.put("DECT_RATE", REBATE_DECT);
				child.put("DECT_PRICE",DECT_PRICE);
				child.put("ORDER_AMOUNT",ORDER_AMOUNT);
				child.put("REBATE_PRICE",REBATE_PRICE);//返利单价
				
			}else{
				child.put("DECT_RATE", model.getDECT_RATE());
				child.put("DECT_PRICE",model.getDECT_PRICE());
				child.put("ORDER_AMOUNT", model.getORDER_AMOUNT());
			}
            
            String SPCL_TECH_ID = model.getSPCL_TECH_ID();
            if(!StringUtil.isEmpty(SPCL_TECH_ID)){
            	SPCL_TECH_IDS+="'"+SPCL_TECH_ID+"',";
            }
            child.put("SPCL_TECH_ID",SPCL_TECH_ID);
            child.put("OLD_SPCL_TECH_ID", model.getOLD_SPCL_TECH_ID());
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getGOODS_ORDER_DTL_ID())) {
                child.put("GOODS_ORDER_DTL_ID", StringUtil.uuid32len());
		        child.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		        child.put("IS_BACKUP_FLAG",BusinessConsts.INTEGER_0);//是否抵库标记 默认设置未0
		        ROW_NO++;
		        child.put("ROW_NO",ROW_NO);//是否抵库标记 默认设置未0
                addList.add(child);
            } else {
                child.put("GOODS_ORDER_DTL_ID", model.getGOODS_ORDER_DTL_ID());
                updateList.add(child);
                this.upAdvSpcl(child);
            }
        }
        if(!StringUtil.isEmpty(SPCL_TECH_IDS)){
        	SPCL_TECH_IDS = SPCL_TECH_IDS.substring(0,SPCL_TECH_IDS.length()-1);
        	this.upUSE_FLAG(SPCL_TECH_IDS);
        }
 
        if (!updateList.isEmpty()) {
            this.batch4Update("Goodsorderhd.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Goodsorderhd.insertChld", addList);
        }
        
        if(!StringUtil.isEmpty(ORDER_RECV_DATE)){
        	//更新主表的 交期
            //明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
            Map<String,String> paramsMap = new HashMap<String,String>();
            paramsMap.put("ORDER_RECV_DATE", ORDER_RECV_DATE);
            paramsMap.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
            txUpdateById(paramsMap);
        }
        
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GoodsorderhdModelChld> childQuery(String GOODS_ORDER_ID) {
        Map params = new HashMap();
        params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Goodsorderhd.queryChldByFkId", params);
    }

    /**
     * * 根据主表Id查询生命周期子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List <GoodsorderhdModelTrace> traceQuery(String GOODS_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
         return this.findList("Goodsorderhd.queryTraceByFkId", params);
    }
    
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GoodsorderhdModelChld> loadChilds(Map <String, String> params) {
       return findList("Goodsorderhd.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String GOODS_ORDER_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Goodsorderhd.deleteChldByIds", params);
    }
    
	/**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> loadChann(String CHANN_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("CHANN_ID",CHANN_ID);
		return (Map<String, Object>) load("Shopcar.getChannCreDit", params);
	}
	
	/**
	 * 查询货品的 折扣率
	 * @param AREA_ID 区域ID
	 * @param PRD_ID 货品ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getRateByAreaIdPId(String AREA_ID,String PRD_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("AREA_ID", AREA_ID);
		params.put("PRD_ID", PRD_ID);
	    return (Map<String, String>) this.load("Goodsorderhd.getRate",params);
	}
	//反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS){
		Map<String,String> map=new HashMap<String,String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Goodsorderhd.upUSE_FLAG", map);
	}
	
	//反填预订单特殊工艺和购物车特殊工艺
	public void upAdvSpcl(Map<String,Object> map){
		this.update("Goodsorderhd.upAdvSpcl", map);
		this.update("Goodsorderhd.upShopSpcl", map);
	}

	public Map<String,String> queryTotal(String GOODS_ORDER_ID) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String, String>();
		map.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		map.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		return (Map<String, String>) this.load("Goodsorderhd.queryTotal",map);
	}
	
	/**
	 * 提交
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param BILL_TYPE 单据类型
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void txCommit(String GOODS_ORDER_ID,String BILL_TYPE,String GOODS_ORDER_NO,UserBean userBean) throws Exception{
    	try{
    		String AREA_SER_ID =  userBean.getAREA_SER_ID();
    		if(StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
    			//检查信用 
        		txCheckCredit(userBean.getCHANN_ID(),GOODS_ORDER_ID,BILL_TYPE);
    		}
  
    	}catch(Exception e){
    		throw new ServiceException(e.getMessage());
    	}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPD_TIME",BusinessConsts.UPDATE_TIME);
	    params.put("ORDER_DATE",BusinessConsts.UPDATE_TIME);
	    params.put("STATE",BusinessConsts.UNDONE);//提交后 状态为‘未处理’
	    
	    //如果有区域服务中心 则更新
	    Map<String,String> chann = getChann(userBean.getCHANN_ID());
	    String AREA_SER_ID = chann.get("AREA_SER_ID");
	    if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
	    	params.put("AREA_SER_ID", chann.get("AREA_SER_ID"));
	    	params.put("AREA_SER_NO", chann.get("AREA_SER_NO"));
	    	params.put("AREA_SER_NAME", chann.get("AREA_SER_NAME"));
	    	params.put("IS_AREA_SER_ORDER","1" );
	    }else{
	    	params.put("IS_AREA_SER_ORDER","0" );
	    }
	    update("Goodsorderhd.updateById",params);
	    //插入订货订单跟踪表
	    params.clear();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("BILL_NO", GOODS_ORDER_NO);//订货订单的NO
		params.put("BILL_TYPE", BILL_TYPE);//订货订单的type
		params.put("ACTION_NAME", BusinessConsts.ORDER_BILL_ACTION_COMMIT);//订货订单提交
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL + GOODS_ORDER_ID);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", BusinessConsts.UNDONE);
		insert("Goodsorderhd.insertOrderTrack", params);
		
		//没有区域服务中心的 
		if(StringUtil.isEmpty(AREA_SER_ID)){
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
					this.insert("Advcorder.insertTrace", map);
				}
			}
		}
		//修改订货订单交期
//		upAdvcOrder(GOODS_ORDER_ID);
	}
	
	/**
	 * 获取 渠道
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String, String> getChann(String CHANN_ID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANN_ID);
        return (Map<String, String>) load("chann.loadById", params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void txCheckCredit(String CHANN_ID,String GOODS_ORDER_ID,String BILL_TYPE) throws Exception{
		
		Map<String,Object> model = (Map<String,Object>) load("Goodsorderhd.loadByIdForUpdate", GOODS_ORDER_ID);
		String STATE = StringUtil.nullToSring(model.get("STATE"));
    	if(!"制作".equals(STATE) && !"总部退回".equals(STATE)){
    		throw new ServiceException("改单据已经提交到总部！");
    	}
    	
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
						throw new ServiceException("对不起，您总的信用不够订货!");
					}
				}else{
					throw new ServiceException("对不起，您的返利金额不够!");
				}				
			}else{
				LogicUtil.updFreezeCredit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
				LogicUtil.inertOrderCreditJournal(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, GOODS_ORDER_ID);
			}
		}
	}
	
	
	/**
	 * 撤销
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void revoke(String GOODS_ORDER_ID,String GOODS_ORDER_NO,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPD_TIME",BusinessConsts.UPDATE_TIME);
	    params.put("STATE",BusinessConsts.REVOKE);//撤销后 状态为‘撤销’
	    update("Goodsorderhd.updateById",params);
	}
	//-- 0156143--Start--刘曰刚--2014-06-16//
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID){
		Object obj=this.load("Goodsorderhd.getChannDiscount", CHANN_ID);
		if(null!=obj){
			return obj.toString();
		}
		return null;
	}
	
	/**
	 * 查询预订单明细
	 * @return
	 */
	public List<Map<String,Object>>toQuertAvdcDtl(Map<String,String>params){
		return this.findList("Goodsorderhd.toQuertAvdcDtl", params);
	}
	//修改订货订单交期
	public void upAdvcOrder(String GOODS_ORDER_ID){
		this.update("Goodsorderhd.upAdvcOrder", GOODS_ORDER_ID);
	}
}
