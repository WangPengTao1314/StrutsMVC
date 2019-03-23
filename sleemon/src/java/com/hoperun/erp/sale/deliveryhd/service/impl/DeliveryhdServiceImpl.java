package com.hoperun.erp.sale.deliveryhd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.deliveryhd.model.DeliveryhdGchldModel;
import com.hoperun.erp.sale.deliveryhd.service.DeliveryhdService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public class DeliveryhdServiceImpl extends BaseService implements DeliveryhdService {

	/**
	 * 查询并分页.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Deliveryhd.pageQuery",
				"Deliveryhd.pageCount", params, pageNo);
	}
	public List queryEdit(Map<String,String> map){
		return this.findList("Deliveryhd.QueryEdit", map);
	}
	public void txDelete(Map<String,String> map){
		this.update("Deliveryhd.delete", map);
		this.update("Deliveryhd.delChldById", map);
		this.update("Deliveryhd.delGchldById", map);
	}
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID 主表ID
	 * @return the list
	 */
	public List<TurnoverplanChildModel> childQuery(Map<String, String> params) {
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Deliveryhd.queryChldByFkId", params);
	}
	
	  public List <TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID){
		    Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		    // 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			return this.findList("Deliveryhd.queryChldByFkId", params);
	  }
	
	/**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <Map<String,String>> gchildQuery(String DELIVER_ORDER_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Deliveryhd.queryGchldByFkId", params);
    }
	
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID){
		return (Map<String, String>) this.load("Deliveryhd.loadById", DELIVER_ORDER_ID);
	}
	
	/**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
	public String txEdit(TurnoverplanModel model,List<TurnoverplanChildModel> chldList,
			UserBean userBean,String SHIP_POINT_ID,String SHIP_POINT_NAME,
			List<DeliveryhdGchldModel> gchldModelList)throws Exception{
		Map<String,String> map=new HashMap<String, String>();
		String DELIVER_ORDER_ID=model.getDELIVER_ORDER_ID();
		String DELIVER_ORDER_NO=LogicUtil.getBmgz("ERP_JOIN_DELIVER_ORDER_NO");
    	map.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
    	map.put("DELIVER_TYPE", model.getDELIVER_TYPE());
    	map.put("PRVD_ID", model.getPRVD_ID());
    	map.put("PRVD_NO", model.getPRVD_NO());
    	map.put("PRVD_NAME", model.getPRVD_NAME());
    	map.put("TRUCK_TYPE", model.getTRUCK_TYPE());
    	map.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
    	map.put("REMARK", model.getREMARK());
    	map.put("SHIP_POINT_ID", SHIP_POINT_ID);
    	map.put("SHIP_POINT_NAME", SHIP_POINT_NAME);
    	map.put("APPCH_TIME", model.getAPPCH_TIME());
    	map.put("BILL_TYPE", "销售发运");
    	if(StringUtil.isEmpty(DELIVER_ORDER_ID)){
    		map.put("STATE", BusinessConsts.UNCOMMIT);
			DELIVER_ORDER_ID=StringUtil.uuid32len();
			map.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
	    	map.putAll(LogicUtil.sysFild(userBean));
	    	this.insert("Deliveryhd.insert", map);
	    	if(!chldList.isEmpty()){
	    		txChildEdit(DELIVER_ORDER_ID, chldList, userBean);
	    	}
	    	if(!gchldModelList.isEmpty()){
	    		txGchildEdit(DELIVER_ORDER_ID, gchldModelList);
	    	}
		}else{
			map.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			map.put("UPDATOR", userBean.getXTYHID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "sysdate");
			this.update("Deliveryhd.updateById", map);
		}
    	return DELIVER_ORDER_NO;
    }
	 public List getDeliverDtlId(String DELIVER_ORDER_ID) {
			// 这里的可用信用=渠道的当前信用+有效的零时信用-渠道表的冻结的信用
			return  findList("Pdtdeliver.queryDeliverDtlId", DELIVER_ORDER_ID);
		}
	
	/**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public String txCloseChilds(String DELIVER_ORDER_NO,String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,UserBean userBean,String strJsonData,String IS_ALL_CLOSE){
    	String strMessage = "操作成功";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("DELIVER_ORDER_DTL_IDS", DELIVER_ORDER_DTL_IDS);
    	if("true".equals(IS_ALL_CLOSE)){
    		params.put("IS_SEND_FIN", "0");
    	}else{
    		params.put("IS_SEND_FIN", "3");
    	}
    	
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		this.update("Pdtdeliver.closePdtDeliverOrderDtl", params);
		if("true".equals(IS_ALL_CLOSE)){
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			params.put("STATE", "未提交");
			this.update("Pdtdeliver.updateDeliverIdStat", params);
		}
		String strResult = null;
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {
			try {
				strResult = LogicUtil.createShipPlan(strJsonData);
			} catch (Exception ex) {
				throw new ServiceException("调用ESB接口失败!");
			}

			Map<String, String> resMap = new Gson().fromJson(strResult,
					new TypeToken<Map<String, String>>() {
					}.getType());
			if (resMap != null) {
				String stats = resMap.get("IsOK");
				if ("0".equals(stats)) {
					StringBuffer buf = new StringBuffer();
					buf.append("操作失败!");
					String Msg = resMap.get("Msg");
					buf.append("错误信息:" + Msg);
					throw new ServiceException(buf.toString());
				}
			} else {
				strMessage = "调用接口失败,查看LOG日志";
				throw new ServiceException(strMessage);
			}
		}
		return strMessage;
    }
    public String checkISExsitCloseRec(String DELIVER_ORDER_DTL_IDS){
    	List deliverList = findList("Pdtdeliver.checkIsExsitClose", DELIVER_ORDER_DTL_IDS);
    	if(deliverList!=null && deliverList.size()>0){
    		HashMap deliverMap = (HashMap)deliverList.get(0);
    		String PRD_NO = (String)deliverMap.get("PRD_NO");
    		return PRD_NO;
    	}
    	return "true";
    }
    /**检查是否可以整单撤销
     * @param FROMBILLID
     * @param userBean
     * @return
     */
    public boolean checkIsAllClose(String FROM_BILL_ID,UserBean userBean){
    	//如果子单未提交库房,直接删除,如果子单提交库房了,要选撤子单,
    	List list = null;
    	try{
    	 list = findList("Pdtdeliver.queryDeliverByFromId", FROM_BILL_ID);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(list==null || list.size() == 0){
    		return true;
    	}
    	for(int i=0;i<list.size();i++){
    		Map deliverOrder = (Map)list.get(i);
    		String stats = (String)deliverOrder.get("STATE");
    		String DELIVER_ORDER_ID = (String)deliverOrder.get("DELIVER_ORDER_ID");
    		String DELIVER_ORDER_NO = (String)deliverOrder.get("DELIVER_ORDER_NO");
    		if("未提交".equals(stats)||"已提交生产".equals(stats)){
    			HashMap params = new HashMap();
    			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    			params.put("DEL_FLAG", "1");
    			params.put("UPDATOR", userBean.getXTYHID());
    			params.put("UPD_NAME", userBean.getYHM());
    			params.put("UPD_TIME", DateUtil.now());
    			this.delete("Pdtdeliver.delete", params);
    		}else{
    			String message = "子单据"+DELIVER_ORDER_NO+"处于"+stats+",不提撤销,请选撤销子单!";
    			throw new ServiceException(message);
    		}
    	}
    	return true;
    }
	 /**
	 * 插入销售订单生命周期
	 * @param parent 主表字段信息
	 * @param userBean
	 */
	public void inertSaleordertrace(Map<String,String> parent,UserBean userBean){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("GOODS_ORDER_TRACE_ID", StringUtil.uuid32len());
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		paramMap.put("BILL_NO", parent.get("DELIVER_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", "已提交生产 ");//已提交生产 
//		paramMap.put("TRACE_URL","");
		 
		paramMap.put("BILL_TYPE", "销售发运");
		paramMap.put("ACTION_NAME", "提交交付计划");
		
		this.insert("Saleorder.insertOrderTrack", paramMap);
	}
	
	
   /**
    * * 根据主表Id查询生命周期子表记录
    * @param SALE_ORDER_ID the SALE_ORDER_ID
    * @return the list
    */
   @SuppressWarnings("unchecked")
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID){
   	 Map<String,String> params = new HashMap<String,String>();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        return this.findList("Saleorder.queryTraceByFkId", params);
   }
   
   /**
	 * * 子表批量删除:软删除
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 */
	@Override
	public void txBatch4DeleteChild(String DELIVER_ORDER_DTL_IDS) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_DTL_IDS", DELIVER_ORDER_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Deliveryhd.deleteChldByIds", params);
	}
    
    /**
	 * * 明细数据编辑
	 * 
	 */
	@Override
	public void  txChildEdit(String DELIVER_ORDER_ID,List<TurnoverplanChildModel> chldList,UserBean userBean)throws Exception {
		// 修改列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
//		String salDtlIDs = "";
//		for(TurnoverplanChildModel model : chldList){
//			String SALE_ORDER_DTL_ID = model.getSALE_ORDER_DTL_ID();
//			salDtlIDs = salDtlIDs+"'"+SALE_ORDER_DTL_ID+"',";
//		}
//		salDtlIDs = salDtlIDs.substring(0,salDtlIDs.length()-1);

		
		for (TurnoverplanChildModel model : chldList) {
			String DELIVER_ORDER_DTL_ID=model.getDELIVER_ORDER_DTL_ID();
			Map<String, String> params = new HashMap<String, String>();
			String SALE_ORDER_DTL_ID = model.getSALE_ORDER_DTL_ID();
			if(StringUtil.isEmpty(DELIVER_ORDER_DTL_ID)){
				params.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
				params.put("PLAN_NUM", model.getPLAN_NUM());
				params.put("REMARK", model.getREMARK());
				params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
				params.put("DELIVER_ORDER_DTL_ID", StringUtil.uuid32len());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			}else{
				params.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
				params.put("PLAN_NUM", model.getPLAN_NUM());
				params.put("REMARK", model.getREMARK());
				params.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
				upList.add(params);
			}
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Deliveryhd.insertChld", addList);
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Deliveryhd.updateChld", upList);
		} 
		//查重
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		List<Map<String,String>> list = this.findList("Deliveryhd.querySaleDtls", paramMap);
		if(null != list && !list.isEmpty()){
			String msg = "";
			for(Map<String,String> model : list){
				msg = msg +"单号["+model.get("SALE_ORDER_NO")+"]下的货品["+
				model.get("PRD_NO")+"]重复添加，请检查";
			}
			throw new ServiceException(msg);
		}
		
		
	}
	public List downQuery(Map<String,String> map){
		return this.findList("Deliveryhd.downGetById", map);
	}
	public IListPage queryEdit(Map<String,String> map, int pageNo){
		return this.pageQuery("Deliveryhd.pageQueryEdit",
				"Deliveryhd.pageCountEdit", map, pageNo);
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Deliveryhd.pageCountEdit", map);
		return Long.parseLong(obj.toString());
	}
	public List<TurnoverplanChildModel> findChld(String SALE_ORDER_DTL_ID,String CHANNS_CHARG){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
		map.put("CHANNS_CHARG", CHANNS_CHARG);
		return this.findList("Deliveryhd.QueryEdit", map);
	}
	/**
	 * 提交库房
	 * @param DELIVER_ORDER_ID 发运单ID
	 * @param childList 发运单明细
	 * @param userBean
	 */
	public Map<String,Object> txCommitStore(String DELIVER_ORDER_ID,
			Map<String, String> dliverMap, String strJsonData,
			List<TurnoverplanChildModel> childList, UserBean userBean,String AppCode,String UId,String OPRCODE)
			throws Exception {
		//获取发运单状态并且锁住该行防止并发提交
		String STATE=(String) this.load("Deliveryhd.QueryState",DELIVER_ORDER_ID);
		if(!BusinessConsts.UNCOMMIT.equals(STATE)){
			throw new ServiceException("对不起，您的单据已提交，不能重复提交！");
		}
		String strMessage = "操作成功";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("STATE", "已提交库房");
		params.put("DELIVER_ORDER_ID",DELIVER_ORDER_ID);// 未提交
		this.update("Pdtdeliver.updateById", params);
		List channList = getFreeChann(DELIVER_ORDER_ID);
		for(int i = 0;i<channList.size();i++){
			HashMap chanMap = (HashMap)channList.get(i);
			String CHANN_ID = (String)chanMap.get("ORDER_CHANN_ID");
			String CHANN_NO = (String)chanMap.get("ORDER_CHANN_NO");
			String checkCredit = CreditCtrlUtil.txDoDeliverOrderFreeCredit(DELIVER_ORDER_ID, CHANN_ID,CHANN_NO);
			if(!"true".equals(checkCredit)){
				throw new ServiceException("客户:"+CHANN_NO+checkCredit);
			}			
		}

		
		String strResult = null;
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {
			ArrayList bodyList = new ArrayList();
			ArrayList dtlList = null;
			try {
				strResult = InterfaceInvokeUtil.createShipPlan(strJsonData);
				LogicUtil.actLog("出货单管理", "出货单接口返回值", "UA", "成功",strResult,AppCode,UId,OPRCODE);			
    			JesonImplUtil jsUtil = new JesonImplUtil(true,strResult);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			dtlList = jsUtil.getMbDtlList();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
			} catch (Exception ex) {
				throw new ServiceException("调用ESB接口失败!");
			}

			Map<String, String> resMap = new Gson().fromJson(strResult,
					new TypeToken<Map<String, String>>() {
					}.getType());
			if (resMap != null) {
				String stats = resMap.get("IsOK");
				if ("0".equals(stats)) {
					StringBuffer buf = new StringBuffer();
					buf.append("操作失败!");
					String Msg = resMap.get("Msg");
					buf.append("错误信息:" + Msg);
					throw new ServiceException(buf.toString());
				}
				updateExpandRemark(dtlList);  
			} else {
				strMessage = "调用接口失败,查看LOG日志";
				throw new ServiceException(strMessage);
			}
		}

		Map<String,Object> returnMap = new HashMap<String, Object>(); 
		returnMap.put("strMessage", strMessage);

		return returnMap;
	}
	
    /**返填排骨贺信用到出货计划明细上
     * @param bodyList
     */
    private void updateExpandRemark(ArrayList bodyList){
    	for(int i=0;bodyList!=null && i<bodyList.size();i++){
    		HashMap bodyMap = (HashMap)bodyList.get(i);
    		if(bodyMap!=null){
    			String DeliverPlanLineID = (String)bodyMap.get("DeliverPlanLineID");
    			if(!StringUtil.isEmpty(DeliverPlanLineID)){
    				String Memo = (String)bodyMap.get("Memo");
            		HashMap param = new HashMap();
            		param.put("DELIVER_ORDER_DTL_ID", DeliverPlanLineID);
            		param.put("EXPAND_REMARK", Memo);
            		update("Deliveryhd.udpateExpandRemark",param);
    			}
    		}
    	}
    }

	/***整单撤销**/
	public String txCloseAllOrder(String DELIVER_ORDER_ID,String strJsonData){
			String strMessage = "操作成功";
			Map<String, String> param = new HashMap<String, String>();
			param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			param.put("STATE", BusinessConsts.UNCOMMIT);
		   this.update("Deliveryhd.updateById", param);
		   try{
			   List channList = getFreeChann(DELIVER_ORDER_ID);
				for(int i = 0;i<channList.size();i++){
					HashMap chanMap = (HashMap)channList.get(i);
					String CHANN_ID = (String)chanMap.get("ORDER_CHANN_ID");
					CreditCtrlUtil.txUpdateFreeCreditForCancel(DELIVER_ORDER_ID, CHANN_ID);		
				}
		   }catch(Exception ex){
			   throw new ServiceException("整单撤销时扣减冻结信用出错");
		   }
			String strResult = null;
			if ("true".equals(Consts.INVOKE_SYS_FLG)) {
				try {
					strResult = LogicUtil.createShipPlan(strJsonData);
				} catch (Exception ex) {
					throw new ServiceException("调用ESB接口失败!");
				}

				Map<String, String> resMap = new Gson().fromJson(strResult,
						new TypeToken<Map<String, String>>() {
						}.getType());
				if (resMap != null) {
					String stats = resMap.get("IsOK");
					if ("0".equals(stats)) {
						StringBuffer buf = new StringBuffer();
						buf.append("操作失败!");
						String Msg = resMap.get("Msg");
						buf.append("错误信息:" + Msg);
						throw new ServiceException(buf.toString());
					}
				} else {
					strMessage = "调用接口失败,查看LOG日志";
					throw new ServiceException(strMessage);
				}
			}
			return strMessage;
	  }
	   
	   
	   /**********整单关闭*****************/
	   public String txClose(String DELIVER_ORDER_ID,String strJsonData){
		   String strMessage = "操作成功";
		   Map<String, String> paramap = new HashMap<String, String>();
		   paramap.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		   paramap.put("STATE", BusinessConsts.COMMON_COLSE);
		   paramap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		   paramap.put("IS_SEND_FIN", "3");
		   this.update("Deliveryhd.updateById", paramap);
		   this.update("Deliveryhd.updateChldById", paramap);
		   
		   try{
			   List channList = getFreeChann(DELIVER_ORDER_ID);
				for(int i = 0;i<channList.size();i++){
					HashMap chanMap = (HashMap)channList.get(i);
					String CHANN_ID = (String)chanMap.get("ORDER_CHANN_ID");
					String CHANN_NO = (String)chanMap.get("ORDER_CHANN_NO");
					CreditCtrlUtil.txUpdateFreeCreditForClose(DELIVER_ORDER_ID, CHANN_ID);		
				}
		   }catch(Exception ex){
			   throw new ServiceException("整单关闭时扣减冻结信用出错!");
		   }
		   
		   String strResult = null;
			if ("true".equals(Consts.INVOKE_SYS_FLG)&&!StringUtil.isEmpty(strJsonData)) {
				try {
					strResult = LogicUtil.createShipPlan(strJsonData);
				} catch (Exception ex) {
					throw new ServiceException("调用ESB接口失败!");
				}

				Map<String, String> resMap = new Gson().fromJson(strResult,
						new TypeToken<Map<String, String>>() {
						}.getType());
				if (resMap != null) {
					String stats = resMap.get("IsOK");
					if ("0".equals(stats)) {
						StringBuffer buf = new StringBuffer();
						buf.append("操作失败!");
						String Msg = resMap.get("Msg");
						buf.append("错误信息:" + Msg);
						throw new ServiceException(buf.toString());
					}
				} else {
					strMessage = "调用接口失败,查看LOG日志";
					throw new ServiceException(strMessage);
				}
			}
			return strMessage;
	   }
	   public void txGchildEdit(String DELIVER_ORDER_ID,List<DeliveryhdGchldModel> gchldModelList)throws ServiceException{
		// 修改列表
			List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
			List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
			for (DeliveryhdGchldModel model : gchldModelList) {
				String DELIVER_FREEZE_DTL_ID=model.getDELIVER_FREEZE_DTL_ID();
				Map<String, String> params = new HashMap<String, String>();
				if(StringUtil.isEmpty(DELIVER_FREEZE_DTL_ID)){
					params.put("DELIVER_FREEZE_DTL_ID", StringUtil.uuid32len());
					params.put("DELIVER_ORDER_ID",DELIVER_ORDER_ID);
					params.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID());
					params.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO());
					params.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME());
					params.put("FREEZE_AMOUNT", model.getFREEZE_AMOUNT());
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					addList.add(params);
				}else{
					params.put("DELIVER_FREEZE_DTL_ID", DELIVER_FREEZE_DTL_ID);
					params.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID());
					params.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO());
					params.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME());
					params.put("FREEZE_AMOUNT", model.getFREEZE_AMOUNT());
					upList.add(params);
				}
			}
			if (!addList.isEmpty()) {
				this.batch4Update("Deliveryhd.insertGchld", addList);
			}
			if (!upList.isEmpty()) {
				this.batch4Update("Deliveryhd.updateGchld", upList);
			}
			int count=StringUtil.getInteger(this.load("Deliveryhd.checkChann", DELIVER_ORDER_ID));
			if(count>0){
				throw new ServiceException("存在重复冻结渠道，请检查后重新保存！");
			}
	   }
	   public List<DeliveryhdGchldModel> freeChildQuery(String DELIVER_ORDER_ID){
		   Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			return this.findList("Deliveryhd.coreSqlGchld", params);
	   }
	   public List<DeliveryhdGchldModel> freeChildQueryByIds(String DELIVER_FREEZE_DTL_IDS){
		   Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_FREEZE_DTL_IDS", DELIVER_FREEZE_DTL_IDS);
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			return this.findList("Deliveryhd.queryEditGchld", params);
	   }
	   /**
		 * * 子表批量删除:软删除
		 * 
		 * @param ADVC_ORDER_DTL_IDs
		 *            the ADVC_ORDER_DTL_IDs
		 */
		public void txBatch4DeleteGchild(String DELIVER_FREEZE_DTL_IDS) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_FREEZE_DTL_IDS", DELIVER_FREEZE_DTL_IDS);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			update("Deliveryhd.delGchldByIds", params);
		}
		public int checkFreeChann(Map<String,String> map){
			return StringUtil.getInteger(this.load("Deliveryhd.checkFreeChann", map));
		}
		/**
		 * 根据发运单id查询明细中的订货方Id
		 * @param DELIVER_ORDER_ID
		 * @return
		 */
		public List<Map> getFreeChann(String DELIVER_ORDER_ID){
			Map<String,String> map=new HashMap<String, String>();
			map.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			return this.findList("Deliveryhd.getFreeChann", map);
		}
}
