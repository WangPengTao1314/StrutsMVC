package com.hoperun.erp.sale.turnoverhd.service.impl;

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
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.erp.sale.turnoverhd.service.TurnoverhdService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public class TurnoverhdServiceImpl extends BaseService implements TurnoverhdService {

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
		return this.pageQuery("Turnoverhd.pageQuery",
				"Turnoverhd.pageCount", params, pageNo);
	}
	
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID 主表ID
	 *             
	 * @return the list
	 */
	public List<TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Turnoverhd.queryChldByFkId", params);
	}
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID){
		return (Map<String, String>) this.load("Turnoverhd.loadById", DELIVER_ORDER_ID);
	}
	
	/**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
    public void txEdit(TurnoverplanModel model,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("DELIVER_ORDER_ID", model.getDELIVER_ORDER_ID());
    	params.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
    	params.put("REMARK", model.getREMARK());
    	params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
    	params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
    	params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
    	this.update("Turnoverhd.updateById", params);
    	
    }
    
    /**
     * 未排货品查看 
     * 查询并分页.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageNotPalanQuery(Map<String, String> params, int pageNo){
    	return this.pageQuery("Turnoverhd.pageSaleChildQuery",
				"Turnoverhd.pageSaleChildCount", params, pageNo);
    }
    
    /**
     * 重排
     * @param DELIVER_ORDER_ID 发运单ID
     */
    public void txAgain(String DELIVER_ORDER_ID,UserBean userBean){
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    	param.put("UPDATOR", userBean.getXTYHID());//更新人id
    	param.put("UPD_NAME", userBean.getXM()); 
        param.put("UPD_TIME",BusinessConsts.UPDATE_TIME);//更新时间
        
    	update("Turnoverhd.updateState",param);
    	update("Turnoverhd.deleteDtl",param);
    	param.put("STATE",BusinessConsts.PASS);// 
    	update("Turnoverhd.updateSaleOrderById",param);
    	update("Turnoverhd.updateSaleOrderDtlById",param);
    	
    }
    
    /**
     * 提交交付计划
     * @param DELIVER_ORDER_ID
     * @param userBean
     */
    public String txCommitPlan(String DELIVER_ORDER_ID,String strJsonData,UserBean userBean,String AppCode,String UId,String OPRCODE,String DELIVER_ORDER_NO)throws Exception{
    	String strMessage = "操作成功";
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	param.put("STATE",BusinessConsts.COMMIT_FACTORY);//提交生产工厂
    	param.put("UPDATOR", userBean.getXTYHID());//更新人id
    	param.put("UPD_NAME", userBean.getXM()); 
        param.put("UPD_TIME",BusinessConsts.UPDATE_TIME);//更新时间
    	update("Turnoverhd.updateState",param);
    	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		ArrayList bodyList = new ArrayList();
    		String strResult = null;
    		try{
    			String returnJsonData = LogicUtil.createSO(strJsonData); 
    			LogicUtil.actLog("销售订单管理", "销售订单接口返回值", "U9系统", "成功",returnJsonData,AppCode,UId,OPRCODE,DELIVER_ORDER_NO);
    			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    		updateDeliverDtlU9Info(bodyList);
    	}
    	return strMessage;
    }
    
    /**把U9返回的销售订单ID和明细ID返填到DM
     * @param bodyList
     */
    private void updateDeliverDtlU9Info(ArrayList bodyList){
    	for(int i=0;bodyList!=null && i<bodyList.size();i++){
    		HashMap bodyMap = (HashMap)bodyList.get(i);
    		if(bodyMap!=null){
    			String SODocNo = (String)bodyMap.get("SODocNo");
        		String SOLineID = (String)bodyMap.get("SOLineID");
        		String PlanDocLineID = (String)bodyMap.get("PlanDocLineID");
        		HashMap param = new HashMap();
        		param.put("U9_SALE_ORDER_NO", SODocNo);
        		param.put("U9_SALE_ORDER_DTL_NO", SOLineID);
        		param.put("DELIVER_ORDER_DTL_ID", PlanDocLineID);
        		update("Turnoverhd.updateU9Id",param);
    		}
    	}
    }
    
    
    /**
     * 修改交期
     * @param DELIVER_ORDER_ID 主表ID
     * @param DATE 交期
     * @param userBean
     * @throws Exception 
     */
    public String txUpdateAdvDate(String DELIVER_ORDER_ID,String strJsonData,String DATE,UserBean userBean) throws Exception{
    	String strMessage = "操作成功";
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	param.put("ADVC_SEND_DATE",DATE); 
    	param.put("UPDATOR", userBean.getXTYHID());//更新人id
    	param.put("UPD_NAME", userBean.getXM()); 
        param.put("UPD_TIME",BusinessConsts.UPDATE_TIME);//更新时间
        this.update("Turnoverhd.updateById", param);
        String strResult = null;
        if("true".equals(Consts.INVOKE_SYS_FLG)){
        	try{
        		strResult = LogicUtil.modifyRequireDate(strJsonData);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
        	
      		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
        }
        return strMessage;
      
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
	 * 撤销
	 * @param DELIVER_ORDER_ID
	 * @param userBean
	 */
	public String txCancel(String DELIVER_ORDER_ID,UserBean userBean,String strJsonData){
		String strMessage = "操作成功";
		Map<String,String> param = new HashMap<String,String>();
    	param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	param.put("STATE", BusinessConsts.UNCOMMIT);
    	param.put("UPDATOR", userBean.getXTYHID());//更新人id
    	param.put("UPD_NAME", userBean.getXM()); 
        param.put("UPD_TIME",BusinessConsts.UPDATE_TIME);//更新时间
    	this.update("Turnoverhd.updateState",param);
    	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		ArrayList bodyList = new ArrayList();
    		String strResult = null;
    		try{
    			String returnJsonData = LogicUtil.createSO(strJsonData); 
    			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    	}
    	return strMessage;
	}
   
	public List downQuery(Map<String,String> map){
		return this.findList("Turnoverhd.downGetById", map);
	}
	
	/**检查是否存在价格为0或者排车数量为0的货品
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	public List checkDeliverOrderDtl(String DELIVER_ORDER_ID){
        return this.findList("Turnoverhd.checkDeliverDtl", DELIVER_ORDER_ID);
	}
	
	

	/**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public String txCloseChilds(String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,UserBean userBean,String strJsonData){
    	String strMessage = "操作成功";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("DELIVER_ORDER_DTL_IDS", DELIVER_ORDER_DTL_IDS);
    	params.put("DEL_FLAG", BusinessConsts.INTEGER_1);
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		this.update("Pdtdeliver.closeDeliverOrderDtl", params);
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		this.update("Pdtdeliver.updateSaleOrderDtlById", params);
		
		//查询正常状态的明细
		int res  = this.queryForInt("Pdtdeliver.selectCountDeliver", params);
		if(0 == res){
			params.put("UPDATOR", userBean.getXTYHID());// 更新人id
			this.update("Pdtdeliver.delete", params);
		}
		
    	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		ArrayList bodyList = new ArrayList();
    		String strResult = null;
    		try{
    			String returnJsonData = LogicUtil.createSO(strJsonData); 
    			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    	}
		
		return strMessage;
    }
    
    
    
    
    /**
	 * * 明细数据编辑
	 * 
	 */
	@Override
	public void  txChildEdit(String DELIVER_ORDER_ID,
			List<TurnoverplanChildModel> chldList,UserBean userBean) {
		// 修改列表
		List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
		for (TurnoverplanChildModel model : chldList) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("NEW_SPEC", model.getNEW_SPEC());//新规格
			params.put("NEW_COLOR", model.getNEW_COLOR());//新花号
			params.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());//是否抵库
		
			// 如果没有明细ID的则是新增，有的是修改
			params.put("DELIVER_ORDER_DTL_ID", model.getDELIVER_ORDER_DTL_ID());
			updateList.add(params);
		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Turnoverhd.updateChldById", updateList);
		}
		  
	}
    

}
