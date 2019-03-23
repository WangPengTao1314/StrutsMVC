/**
 * prjName:喜临门营销平台
 * ucName:入库单处理
 * fileName:StoreinServiceImpl
*/
package com.hoperun.drp.store.storein.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storein.model.StoreinModelChld;
import com.hoperun.drp.store.storein.model.StoreinModelGrandChld;
import com.hoperun.drp.store.storein.service.StoreinService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-22 09:20:20
 */
public class StoreinServiceImpl extends BaseService implements StoreinService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Storein.pageQuery", "Storein.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Storein.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREIN_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Storein.delete", params);
		 //删除子表 
		 return update("Storein.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Storein.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_ID
	 * @param StoreinModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	@SuppressWarnings("unchecked")
	public void txEdit(String STOREIN_ID, Map<String, String> map,List<StoreinModelChld> chldList, UserBean userBean)throws Exception {
		//更新 主表的  处理标记(DEAL_FLAG)=1, 处理时间(DEAL_TIME)=当前时间
		//处理人ID(DEAL_PSON_ID)=当前用户，处理人名称(DEAL_PSON_NAME)=userBean.getXM()  ,STATE=’已处理’
		String BILL_TYPE = map.get("BILL_TYPE");
		Map<String,String> params = new HashMap<String,String>();
		params.put("STOREIN_ID", STOREIN_ID);
		params.put("DEAL_FLAG", "1");
		params.put("DEAL_PSON_ID", userBean.getXTYHID());
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("DEAL_TIME", "数据库时间");
		params.put("STATE", BusinessConsts.DONE);
		//更新入库单为‘已处理’
		boolean updateFlag = txUpdateById(params);
		clearCaches(STOREIN_ID);
		//add by zzb 是终端退货的话 跟新来源单据的状态
		if("终端退货".equals(BILL_TYPE)){
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getXM());
		    params.put("UPD_TIME", "数据库时间");
			params.put("ADVC_ORDER_ID",map.get("FROM_BILL_ID"));
			params.put("STATE", "已退货");
			this.update("Advcreturn.updateById", params);
		}
		
		if (updateFlag&&!"下级入库".equals(BILL_TYPE)) {
			Map<String,String> param = new HashMap<String,String>();
			String STOREIN_DIFF_ID=StringUtil.uuid32len();
			param.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
			param.put("STOREIN_DIFF_NO", LogicUtil.getBmgz("DRP_STOREIN_DIFF_NO"));
			param.put("BILL_TYPE", "入库差异");//单据类型
			param.put("FROM_BILL_ID", STOREIN_ID);//来源单据ID
			param.put("FROM_BILL_NO", map.get("STOREIN_NO"));//来源单据NO
			param.put("SEND_CHANN_ID", map.get("SEND_CHANN_ID"));//发货方id
			param.put("SEND_CHANN_NO", map.get("SEND_CHANN_NO"));//发货方编号
			param.put("SEND_CHANN_NAME", map.get("SEND_CHANN_NAME"));//发货方名称
			param.put("RECV_CHANN_ID", map.get("RECV_CHANN_ID"));//收货方id
			param.put("RECV_CHANN_NO", map.get("RECV_CHANN_NO"));//收货方编号
			param.put("RECV_CHANN_NAME", map.get("RECV_CHANN_NAME"));//收货方名称
			param.put("STOREIN_STORE_ID", map.get("STOREIN_STORE_ID"));//入库库房id
			param.put("STOREIN_STORE_NO", map.get("STOREIN_STORE_NO"));//入库库房编号
			param.put("STOREIN_STORE_NAME", map.get("STOREIN_STORE_NAME"));//入库库房名称
			param.put("ORDER_CHANN_ID", map.get("ORDER_CHANN_ID"));//订货方ID
			param.put("ORDER_CHANN_NO", map.get("ORDER_CHANN_NO"));//订货方编号
			param.put("ORDER_CHANN_NAME", map.get("ORDER_CHANN_NAME"));//订货方名称
			param.put("STATE", BusinessConsts.UNCOMMIT);//状态
			param.put("REMARK", map.get("REMARK"));//备注
            param.put("CREATOR", userBean.getXTYHID());//制单人id
            param.put("CRE_NAME", userBean.getXM());//制单名称
            param.put("CRE_TIME", "数据库时间");//制单时间
            param.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            param.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            param.put("ORG_ID", userBean.getJGXXID());//制单机构ID
            param.put("ORG_NAME", userBean.getJGMC());//制单机构名称
            param.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
			param.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
            param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	//新增列表
            List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
            //差异明细
            List <Map<String,String>> diffAddList=new ArrayList<Map<String,String>>();
		    for (int i=0;i<chldList.size();i++) {
		    	Map<String,String> maps=(Map<String,String>)chldList.get(i); 
		    	Map<String,String> par = new HashMap<String,String>();
		    	
		    	par.putAll(maps);
		    	par.put("STOREIN_DIFF_DTL_ID", StringUtil.uuid32len());//入库差异单明细ID
		    	par.put("STOREIN_DIFF_ID",STOREIN_DIFF_ID);//入库差异单ID
		    	Object REAL_NUM=maps.get("REAL_NUM");
		    	if(REAL_NUM==null){
		    		REAL_NUM="0";
		    	}
		    	double difNum = Double.parseDouble(String.valueOf(REAL_NUM.toString())) - Double.parseDouble(String.valueOf(maps.get("NOTICE_NUM")));
		    	if(difNum==0){
		    		continue;
		    	}
		    	double difAmount = difNum * Double.parseDouble(String.valueOf(maps.get("DECT_PRICE")));
		    	par.put("DIFF_AMOUNT",difAmount+"");
		    	par.put("DIFF_NUM", difNum+"");
		    	par.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);	
                addList.add(par);
            	if(difNum!=0){
            		diffAddList.add(par);
		    	}
		    }
		    if(!diffAddList.isEmpty() && "订货入库".equals(BILL_TYPE)){
		    	insert("Storein.insertDRP_STOREIN_DIFF", param);
			    this.batch4Update("Storein.insertDRP_STOREIN_DIFF_DTL", diffAddList);
		    }
		    String StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF_ID;
		    if("手工新增".equals(BILL_TYPE)){
		    	StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF2_ID;
		    }else if("返修入库".equals(BILL_TYPE)){
		    	StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF3_ID;
		    }else if("调拨入库".equals(BILL_TYPE)){
		    	StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF4_ID;
		    }else if("终端退货".equals(BILL_TYPE)){
		    	StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF5_ID;
		    }else if("下级退货入库".equals(BILL_TYPE)){
		    	StoreinConfId = BusinessConsts.STOREIN_ORDER_CONF6_ID;
		    }
		    LogicUtil.doStoreAcct(StoreinConfId, STOREIN_ID);
		    LogicUtil.doJournalAcct(StoreinConfId,STOREIN_ID);
		}
		//编辑特殊工艺是否可编辑标记
		Map<String,String> spclMap=new HashMap<String, String>();
		spclMap.put("STOREIN_ID", STOREIN_ID);
		spclMap.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		spclMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		spclMap.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Storein.txUpSpclEditFlag", spclMap);
	}
	
	/**
	 * @详细
	 * @param param STOREIN_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Storein.loadById", param);
	}
	
    /**
     * * 明细数据编辑
     * 
     * @param STOREIN_ID the STOREIN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREIN_ID, List<StoreinModelChld> chldList,UserBean userBean)  {
    	Object DECT_RATE=LogicUtil.getChannDsct(userBean.getCHANN_ID());
        if(null==DECT_RATE){
        	throw new ServiceException("对不起，所属渠道里该货品未设置折扣价!");
        }
    	//新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StoreinModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREIN_DTL_ID())) {
            	String PRD_ID=model.getPRD_ID();
                params.put("PRD_ID",PRD_ID);
                params.put("PRD_NO",model.getPRD_NO());
                params.put("PRD_NAME",model.getPRD_NAME());
                params.put("PRD_SPEC",model.getPRD_SPEC());
                params.put("PRD_COLOR",model.getPRD_COLOR());
                Object PRICE=model.getPRICE();
                if(null==PRICE){
                	PRICE="0";
                }
                double DECT_PRICE=StringUtil.getDouble(PRICE)*StringUtil.getDouble(DECT_RATE);
                params.put("DECT_PRICE", DECT_PRICE+"");
                params.put("PRICE", PRICE+"");
                params.put("DECT_RATE", DECT_RATE+"");
                params.put("BRAND",model.getBRAND());
                params.put("STD_UNIT",model.getSTD_UNIT());
    		    params.put("NOTICE_NUM","0");
    		    params.put("REAL_NUM","0");
    		    params.put("DECT_AMOUNT", "0");
                params.put("INS_FLAG",model.getINS_FLAG());
                params.put("STOREIN_ID",STOREIN_ID); 
                params.put("STOREIN_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREIN_DTL_ID", model.getSTOREIN_DTL_ID());
                params.put("REAL_NUM",model.getREAL_NUM());
                updateList.add(params);
            }
        }
        Map<String,String> upMap=new HashMap<String,String>();
        upMap.put("STOREIN_ID", STOREIN_ID);
        upMap.put("UPDATOR", userBean.getRYXXID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Inventory.updateById",upMap);
        if (!updateList.isEmpty()) {
            this.batch4Update("Storein.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storein.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREIN_ID the STOREIN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreinModelChld> childQuery(String STOREIN_ID) {
        Map params = new HashMap();
        params.put("STOREIN_ID", STOREIN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storein.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreinModelChld> loadChilds(Map <String, String> params) {
       return findList("Storein.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     */
    @Override
    @SuppressWarnings("unchecked")
    public void txBatch4DeleteChild(String STOREIN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREIN_DTL_IDS", STOREIN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storein.deleteChldByIds", params);
    }
    
    /* =================孙表处理开始=================    */
    
	 /**
     * * 明细数据编辑
     * 
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txGrandChildEdit(List<StoreinModelGrandChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StoreinModelGrandChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("STORG_ID",model.getSTORG_ID());
		    params.put("STORG_NO",model.getSTORG_NO());
		    params.put("STORG_NAME",model.getSTORG_NAME());
		    params.put("REAL_NUM",model.getREAL_NUM());
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREIN_STORG_DTL_ID())) {
                params.put("STOREIN_STORG_DTL_ID", StringUtil.uuid32len());
                params.put("STOREIN_DTL_ID",model.getSTOREIN_DTL_ID());
                params.put("STOREIN_ID",model.getSTOREIN_ID());
                params.put("PRD_ID",model.getPRD_ID());
                params.put("PRD_NO",model.getPRD_NO());
                params.put("PRD_NAME",model.getPRD_NAME());
                params.put("PRD_SPEC",model.getPRD_SPEC());
                params.put("PRD_COLOR",model.getPRD_COLOR());
                params.put("BRAND",model.getBRAND());
                params.put("STD_UNIT",model.getSTD_UNIT());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREIN_STORG_DTL_ID", model.getSTOREIN_STORG_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storein.updateGrandChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storein.insertGrandChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据子表Id查询孙表记录
     * @param STOREIN_DTL_ID the STOREIN_DTL_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
	public List <StoreinModelGrandChld> grandChildQuery(String STOREIN_DTL_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DTL_ID", STOREIN_DTL_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storein.queryGrandChldByFkId", params);
    }
    
	/**
     * * 根据子表Id查询孙表记录
     * @param STOREIN_DTL_ID the STOREIN_DTL_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <StoreinModelGrandChld> grandChild(String STOREIN_ID,String STOREIN_DTL_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DTL_ID", STOREIN_DTL_ID);
        params.put("STOREIN_ID", STOREIN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storein.queryGrandChld", params);
    }

    /**
     * * 根据孙表Id批量加载孙表信息
     * @param STOREIN_STORG_DTL_IDs the STOREIN_STORG_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreinModelGrandChld> loadGrandChilds(Map <String, String> params) {
       return findList("Storein.loadGrandChldByIds",params);
    }
	
    /**
     * * 孙表批量删除:软删除
     * 
     * @param STOREIN_STORG_DTL_IDs the STOREIN_STORG_DTL_IDs
     */
    @Override
    @SuppressWarnings("unchecked")
    public void txBatch4DeleteGrandChild(String STOREIN_STORG_DTL_IDs,String STOREIN_DTL_ID) {
	   Map params = new HashMap();
	   params.put("STOREIN_STORG_DTL_IDS", STOREIN_STORG_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storein.deleteGrandChldByIds", params);
	   Map<String,String> map=new HashMap<String,String>();
	   map.put("STOREIN_DTL_ID", STOREIN_DTL_ID);
	   map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
   		update("Storein.updateGrandChld",map);
    }
    
    /**
	 * @入库处理时校验
	 * @param params map
	 *
	 * @return the map 
	 */
    @SuppressWarnings("unchecked")
    public Map<String,String> queryList(Map <String, String> param) {
    	return (Map<String,String>) load("Storein.DSSDCount", param);
	}
    
    /**
     * * 获取库位ID
     * @param params map
     * 
     * @return the map 
     */
    @SuppressWarnings("unchecked")
    public List<String> getSTORGIDS(Map<String, String> params) {
        return findList("Storein.selectIDs", params);
    }
    /**
     * 共通入库单新增
     */
    public void txAddStorein(Map<String,String> model,List<Map<String,String>> chld){
    	txInsert(model);
    	if (!chld.isEmpty()) {
            this.batch4Update("Storein.insertChld", chld);
        }
    }
    public boolean txScanChildEdit(String STOREIN_ID, List<StoreinModelChld> chldList,UserBean userBean) {
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StoreinModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_ID",STOREIN_ID); 
            params.put("STOREIN_DTL_ID", model.getSTOREIN_DTL_ID());
            params.put("REAL_NUM", model.getREAL_NUM());
            updateList.add(params);
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storein.updateScanChldById", updateList);
        } 
        Map<String,String> upMap=new HashMap<String,String>();
        upMap.put("STOREIN_ID", STOREIN_ID);
        upMap.put("UPDATOR", userBean.getXTYHID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Storein.updateById",upMap);
        return true;
    }
    /**跟据序列号查表出库单详细(ERP_STOREOUT_DTL)
     * @param model
     * @param chld
     */
    public HashMap getErpStoreInDtl(String pdtSN){
    	List storeoutDtlList = findList("Storein.queryErpStoreInDtl",pdtSN);
    	if(storeoutDtlList!=null && storeoutDtlList.size()>0 ){
    		return (HashMap)storeoutDtlList.get(0);
    	}
    	return null;
    }
    public HashMap getDrpStoreInDtl(String pdtId){
    	List storeoutDtlList = findList("Storein.queryDrpStoreInDtl",pdtId);
    	if(storeoutDtlList!=null && storeoutDtlList.size()>0 ){
    		return (HashMap)storeoutDtlList.get(0);
    	}
    	return null;
    }
    /**
     * 退回
     * @param STOREIN_ID 入库处理单id
     * @param FROM_BILL_ID 来源单据id
     * @param BILL_TYPE 单据类型
     */
    public void txReturnStorein(String STOREIN_ID,String FROM_BILL_ID,String BILL_TYPE,UserBean userBean){
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("FROM_BILL_ID", FROM_BILL_ID);
    	map.put("UPD_NAME", userBean.getYHM());
    	map.put("UPDATOR", userBean.getXTYHID());
    	//判断单据类型，为终端退货时，修改终端退货单状态为提交，否则，修改入库通知单状态为撤销
        //修改单据状态后删除入库处理单据
    	if("终端退货".equals(BILL_TYPE)){
    		map.put("STATE", BusinessConsts.COMMIT);
    		this.update("Storein.updateReturnAdvcState", map);
    	}else{
    		map.put("STATE", BusinessConsts.REVOKE);
    		this.update("Storein.updateReturnStoreState", map);
    	}
		map.put("STOREIN_ID", STOREIN_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		this.txDelete(map);
    }
    
    
    public List downQuery(Map<String,String> map){
		return this.findList("Storein.downGet", map);
	}
    
    
    /**
     * 反入库
     * @param STOREIN_ID
     * @param userBean
     */
    public void txBackiin(String STOREIN_ID,String BILL_TYPE,UserBean userBean)throws Exception {
		
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("STOREIN_ID", STOREIN_ID);
    	paramMap.put("STATE", "未处理");
    	paramMap.put("UPDATOR", userBean.getXTYHID());
    	paramMap.put("UPD_NAME", userBean.getXM());
    	paramMap.put("UPD_TIME", "数据库时间");
        update("Storein.updateById",paramMap);
        //其他处理
        
	    String StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF_ID;
	    if("手工新增".equals(BILL_TYPE)){
	    	BILL_TYPE = "手工新增入库";
	    	StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF2_ID;
	    }else if("返修入库".equals(BILL_TYPE)){
	    	StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF3_ID;
	    }else if("调拨入库".equals(BILL_TYPE)){
	    	StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF4_ID;
	    }else if("终端退货".equals(BILL_TYPE)){
	    	BILL_TYPE = "终端退货入库";
	    	StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF5_ID;
	    }else if("下级退货入库".equals(BILL_TYPE)){
	    	StoreinConfId = BusinessConsts.STOREIN_FORDER_CONF6_ID;
	    }
    	MsgInfo msgObj = LogicUtil.checkStoreAcct(STOREIN_ID,
				BusinessConsts.STORE_DESC, StoreinConfId);
		if (!msgObj.isFLAG()) {
			if (!"0".equals(msgObj.getMESS())) { // 要做库存CHECK的，返回信息
				throw new ServiceException(msgObj.getMESS()+" 不能做反入库");
			}
		}
	    LogicUtil.doStoreAcct(StoreinConfId, STOREIN_ID);
	    LogicUtil.delStoreInJournalAcct(STOREIN_ID,BILL_TYPE);
	    
        //清除明细实际入库数量
        this.update("Storein.upDtlNumById", STOREIN_ID);
    }
    
}