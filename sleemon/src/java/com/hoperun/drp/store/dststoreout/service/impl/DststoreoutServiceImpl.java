package com.hoperun.drp.store.dststoreout.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.InterUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.dststoreout.model.DststoreoutModel;
import com.hoperun.drp.store.dststoreout.model.DststoreoutModelChld;
import com.hoperun.drp.store.dststoreout.service.DststoreoutService;
import com.hoperun.sys.model.UserBean;
/**
 * 预订单出库维护
*    
* 项目名称：sleemon   
* 类名称：DststoreoutServiceImpl.java
* 类描述：   
* 创建人：liu_yg   
* 创建时间：2016-1-11 下午03:06:55   
* @version
 */
public class DststoreoutServiceImpl extends BaseService implements DststoreoutService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Dststoreout.pageQuery", "Dststoreout.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Dststoreout.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREOUT_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Dststoreout.delete", params);
		 //删除子表 
		 return update("Dststoreout.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Dststoreout.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREOUT_ID
	 * @param DststoreoutModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREOUT_ID, DststoreoutModel model,List<DststoreoutModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("RECV_ADDR",model.getRECV_ADDR());
		    params.put("FROM_BILL_ID",model.getFROM_BILL_ID());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("STOREOUT_STORE_ID",model.getSTOREOUT_STORE_ID());
		    params.put("TERM_ID",model.getTERM_ID());
		    params.put("FROM_BILL_NO",model.getFROM_BILL_NO());
		    params.put("TEL",model.getTEL());
		    params.put("ORDER_RECV_DATE",model.getORDER_RECV_DATE());
		    params.put("STOREOUT_STORE_NO",model.getSTOREOUT_STORE_NO());
		    params.put("SEND_CHANN_NO",model.getSEND_CHANN_NO());
		    params.put("REMARK",model.getREMARK());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("SEND_CHANN_ID",model.getSEND_CHANN_ID());
		    params.put("SALE_DATE",model.getSALE_DATE());
		    params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		    params.put("STOREOUT_AMOUNT",model.getSTOREOUT_AMOUNT());
		    params.put("SEND_CHANN_NAME",model.getSEND_CHANN_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("STOREOUT_STORE_NAME",model.getSTOREOUT_STORE_NAME());
		if(StringUtil.isEmpty(STOREOUT_ID)){
			params.putAll(LogicUtil.sysFild(userBean));
			STOREOUT_ID= StringUtil.uuid32len();
			params.put("STOREOUT_ID", STOREOUT_ID);
			params.put("STOREOUT_NO",LogicUtil.getBmgz("DRP_DST_STOREOUT_NO"));
			params.put("STATE",BusinessConsts.UNDONE);
		txInsert(params);
		} else{
			params.put("STOREOUT_ID", STOREOUT_ID);
			params.put("UPD_NAME", userBean.getXM());// 更新人名称
			params.put("UPDATOR", userBean.getXTYHID());// 更新人ID
			params.put("UPD_TIME", "数据库时间");// 更新时间
			txUpdateById(params);
			clearCaches(STOREOUT_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STOREOUT_ID, chldList,"edit");
		}else{
			delChldById(STOREOUT_ID);
		}
	}
	
	/**
	 * @详细
	 * @param param STOREOUT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Dststoreout.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREOUT_ID, List<DststoreoutModelChld> chldList,String actionType) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (DststoreoutModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("BRAND",model.getBRAND());
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("REMARK",model.getREMARK());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("RECV_NUM",model.getRECV_NUM());
		    params.put("PRD_TYPE",model.getPRD_TYPE());
		    params.put("PRD_NAME",model.getPRD_NAME());
//		    params.put("REAL_NUM",model.getREAL_NUM());
		    params.put("PRICE",model.getPRICE());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("SPCL_TECH_ID",model.getSPCL_TECH_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("DECT_AMOUNT",model.getDECT_AMOUNT());
		    params.put("PRD_COLOR", model.getPRD_COLOR());
            params.put("STOREOUT_ID",STOREOUT_ID); 
            params.put("FROM_BILL_DTL_ID",model.getFROM_BILL_DTL_ID());
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREOUT_DTL_ID())) {
                params.put("STOREOUT_DTL_ID", StringUtil.uuid32len());
                params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREOUT_DTL_ID", model.getSTOREOUT_DTL_ID());
                updateList.add(params);
            }
        }
        if(!"list".equals(actionType)){
        	delChldById(STOREOUT_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Dststoreout.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Dststoreout.insertChld", addList);
        }
        String message=checkRevcNum(STOREOUT_ID);
        if(!StringUtil.isEmpty(message)){
        	throw new ServiceException(message);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DststoreoutModelChld> childQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        //只查询0的记录。1为删除。0为正常
        return this.findList("Dststoreout.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DststoreoutModelChld> loadChilds(Map <String, String> params) {
       return findList("Dststoreout.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREOUT_DTL_IDS", STOREOUT_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Dststoreout.deleteChldByIds", params);
    }
    public String qeuryId(String QUERYID) {
		Object id=load("Dststoreout.queryDtlId",QUERYID);
		if(null==id){
			return null;
		}else{
			return  id.toString();
		}
	}
    public void txCommit(Map<String,String> storeOutMap, UserBean userBean,String storeOutId,List chldlist) throws Exception {
         //根据出库单id验证来源单据是否只生成一张出库单
    	if(!checkAdvcSendRepeat(storeOutId)){
    		throw new ServiceException("所选单据存在重复来源，不能出库!");
    	}
    	//插预订单生命周期
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
    	params.put("ADVC_ORDER_ID",storeOutMap.get("FROM_BILL_ID"));
    	params.put("ACTION", "预订单出库处理");
    	params.put("ACTOR", userBean.getXM());
    	params.put("BILL_NO", storeOutMap.get("STOREOUT_NO"));
    	params.put("REMARK", "已生成");
    	insert("Dststoreout.insertAdvcOrderTrace", params);
    	
    	//调接口 对库存进行扣减
    	String IS_SPEC_TECH_ENABLE=getChannSpecEnableByChannId(userBean.getCHANN_ID());
    	if(BusinessConsts.YJLBJ_FLAG_TRUE.equals(IS_SPEC_TECH_ENABLE)){
    		doStoreAcct(storeOutId);
    	}else{
    		LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ADVC_ORDER_CONF_ID,storeOutId);
    	}
    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ADVC_ORDER_CONF_ID,storeOutId,userBean.getCHANN_ID());
		int count=Integer.parseInt(String.valueOf(this.load("Dststoreout.checkStoreNumByStoreOut", storeOutId)));
    	if(count>0){
    		throw new ServiceException("库存数量不足，不能出库!");
    	}
    	//更新主表信息
    	updateStoreOutStats(storeOutMap,userBean);
    	//回填预订单发货数量和解冻数量
    	for(int i=0 ;i<chldlist.size();i++){
    		HashMap chldMode = (HashMap)chldlist.get(i);
    		String STOREOUT_DTL_ID = (String)chldMode.get("STOREOUT_DTL_ID");
    		HashMap storeMap = new HashMap();
    		storeMap.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
    		storeMap.put("FROM_BILL_DTL_ID", chldMode.get("FROM_BILL_DTL_ID"));
    		//更新实际发货数量
    		update("Dststoreout.updateREAL_NUM",storeMap);
    		//不管出货多少 都把冻结数量更新为0
    		update("Dststoreout.updateAdvcSendNumAndUnFreeNum",storeMap);
    		
    	}
    	
    	//更新预订单状态和预订单发货申请状态 //理新发货申请单和预订单的状态
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("FROM_BILL_ID", storeOutMap.get("FROM_BILL_ID"));
    	
    	//去掉预订单修改多余状态
    	Map advcMap = (Map)this.load("Dststoreout.queryAdvcSendNumAndOrderNum", paramMap);
    	BigDecimal SEND_NUM = (BigDecimal)advcMap.get("SEND_NUM");
    	BigDecimal ORDER_NUM = (BigDecimal)advcMap.get("ORDER_NUM");
    	if(SEND_NUM.intValue()== ORDER_NUM.intValue()){
    	   	paramMap.put("STATE", "已发货");
    		update("Dststoreout.updateAdvcOrderById",paramMap);
    	}
    }
    /**
     * 根据出库单id验证来源单据是否只生成一张出库单
     * @param STOREOUT_ID
     * @return
     */
    public boolean checkAdvcSendRepeat(String STOREOUT_ID){
    	int count=StringUtil.getInteger(this.load("Dststoreout.checkAdvcSendRepeat", STOREOUT_ID));
    	if(count>1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**更新主表的处理人处理时间状态
    * @param storeOutMap
    * @param userBean
    */
   private void updateStoreOutStats(Map<String,String> storeOutMap,UserBean userBean){
   	//更新主表信息
   	Map<String,String> params=new HashMap<String,String>();
   	params.put("DEAL_FLAG","1");
   	params.put("DEAL_PSON_ID", userBean.getXTYHID());
   	params.put("DEAL_PSON_NAME", userBean.getXM());
    params.put("DEAL_TIME", "数据库时间");
    params.put("STATE", "已处理");
   	params.put("STOREOUT_ID", storeOutMap.get("STOREOUT_ID"));
   	update("Dststoreout.updateDealById",params);
   }
   
   /**
    * 验证发货数量是否不大于订货数量
    * @param STOREOUT_ID
    * @return
    */
   public String checkRevcNum(String STOREOUT_ID){
	   List<Map<String,String>> list=this.findList("Dststoreout.checkRevcNum",STOREOUT_ID);
	   if(!list.isEmpty()){
		   StringBuffer message=new StringBuffer();
		   for (Map<String, String> map : list) {
			   double ORDER_NUM=Double.parseDouble(String.valueOf(map.get("ORDER_NUM")));
			   double SEND_NUM=Double.parseDouble(String.valueOf(map.get("SEND_NUM")));
			   double RECV_NUM=Double.parseDouble(String.valueOf(map.get("RECV_NUM")));
			   if(RECV_NUM>ORDER_NUM){
				   message.append("货品：")
				   	      .append(map.get("PRD_NO"))
				   	      .append("操作后总发货数量为")
				   	      .append(RECV_NUM)
				   	      .append(",订货数量为")
				   	      .append(ORDER_NUM)
				   	      .append(",不能操作");
				   return message.toString();
			   }
		   }
	   }
	   return null;
   }
   /**
    * 按主表Id清空明细
    */
   public void delChldById(String STOREOUT_ID){
   	Map<String,String> map=new HashMap<String, String>();
   	map.put("STOREOUT_ID", STOREOUT_ID);
   	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
   	update("Dststoreout.delChldByFkId", map);
   }
   public List <DststoreoutModelChld> getAdvcDtlById(String ADVC_ORDER_ID){
	   return this.findList("Dststoreout.getAdvcDtlById", ADVC_ORDER_ID);
   }
   //扣库存实时帐
   public void doStoreAcct(String STOREOUT_ID)throws ServiceException {
	   //获取主表出库库房信息
	   Map<String,String> mainMap=(Map<String, String>) this.load("Dststoreout.getStoreOutIdById",STOREOUT_ID);
	   if(!BusinessConsts.UNDONE.equals(mainMap.get("STATE"))){
		   throw new ServiceException("对不起，该单据已被别人操作，请刷新页面！");
	   }
	   //根据出库Id获取出库明细
	   List<Map<String,String>> dtlList=this.findList("Dststoreout.getStoreDtlInfoById", STOREOUT_ID);
	   List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
	   StringBuffer ids=new StringBuffer();
	   //根据明细信息查询符合的库存信息（如果有特殊工艺，则优先扣除特殊工艺货品）
	   for (int i = 0; i < dtlList.size(); i++) {
			Map<String,String> params=dtlList.get(i);
			params.putAll(mainMap);
			String STORE_ACCT_ID=String.valueOf(this.load("Dststoreout.getStoreAcctIdByPrdInfo",params));
			//如果没有查到数据，有可能是带特殊工艺的货品没有库存，则不过滤特殊工艺货品
			if(StringUtil.isEmpty(STORE_ACCT_ID)){
				STORE_ACCT_ID=String.valueOf(this.load("Dststoreout.getStoreAcctIdByPrdInfoNoSpcl",params));
			}
			//如果还为空，则要不就是没有库存，要不就是查询出错
			if(StringUtil.isEmpty(STORE_ACCT_ID)){
				 throw new ServiceException("对不起，所选货品库存不足,请重新选择！");
			}
			ids.append("'").append(STORE_ACCT_ID).append("',");
			params.put("STORE_ACCT_ID", STORE_ACCT_ID);
			upList.add(params);
	   }
	   //因查询时存在order by 所以查询完毕后锁表
	   ids=InterUtil.replaceUpSql(ids);
	   this.load("Dststoreout.lockStoreAcctByIds",ids.toString());
	   if(!upList.isEmpty()){
		   this.batch4Update("Dststoreout.updateStoreAcct", upList);
	   }
   }
   private String getChannSpecEnableByChannId(String CHANN_ID){
		String sql="select NVL(IS_SPEC_TECH_ENABLE,0)IS_SPEC_TECH_ENABLE from BASE_CHANN where CHANN_ID='"+CHANN_ID+"' and DEL_FLAG=0";
		Map<String,String> params=new HashMap<String, String>();
		params.put("SelSQL", sql);
		Map<String,String> map=InterUtil.selcom(params);
		return String.valueOf(map.get("IS_SPEC_TECH_ENABLE"));
	}
   public String getChannTel(String CHANN_ID){
   	return StringUtil.nullToSring(this.load("Dststoreout.getChannTel",CHANN_ID));
   }
 //获取赠品总金额
   public double getDtlGiftAmount(String STOREOUT_ID){
   	return StringUtil.getDouble(this.load("Dststoreout.getDtlGiftAmount",STOREOUT_ID));
   }
   /**
    *根据出库单id查询预订单的付款总金额和应收总金额
    */
   public Map<String,String> getSellInfo(String STOREOUT_ID){
   	return (Map<String, String>) load("Dststoreout.getSellInfo",STOREOUT_ID);
   }
}