/**
 * prjName:喜临门营销平台
 * ucName:盘点单维护
 * fileName:InventoryServiceImpl
*/
package com.hoperun.drp.store.inventory.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.inventory.model.InventoryModel;
import com.hoperun.drp.store.inventory.model.InventoryModelChld;
import com.hoperun.drp.store.inventory.service.InventoryService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-07 09:54:59
 */
public class InventoryServiceImpl extends BaseService implements InventoryService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Inventory.pageQuery", "Inventory.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Inventory.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRD_INV_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Inventory.delete", params);
		 //删除子表 
		 return update("Inventory.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Inventory.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRD_INV_ID
	 * @param InventoryModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRD_INV_ID, InventoryModel model,List<InventoryModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("STORE_ID",model.getSTORE_ID());
		    params.put("REMARK",model.getREMARK());
		    params.put("INV_TYPE",model.getINV_TYPE());
		    params.put("STORE_NO",model.getSTORE_NO());
		    params.put("STORE_NAME",model.getSTORE_NAME());
		    params.put("STORAGE_FLAG",model.getSTORAGE_FLAG());
		    params.put("INV_PSON_ID", model.getINV_PSON_ID());
		    params.put("INV_PSON_NO", model.getINV_PSON_NO());
		    params.put("INV_PSON_NAME", model.getINV_PSON_NAME());
		    String INV_RANGE=model.getINV_RANGE();
		    //判断盘点范围，盘点范围选择库房盘点，则不新增/修改库位信息和货品信息；选择库位盘点，则不新增/修改库位信息；选择货品盘点，不新增/修改货品信息。
		    if(INV_RANGE.equals("货品盘点")){
		    	params.put("PRD_ID",model.getPRD_ID());
		    	params.put("PRD_NO",model.getPRD_NO());
			    params.put("PRD_NAME",model.getPRD_NAME());
		    }else if(INV_RANGE.equals("库位盘点")){
		    	params.put("STORG_ID",model.getSTORG_ID());
				params.put("STORG_NO",model.getSTORG_NO());
				params.put("STORG_NAME",model.getSTORG_NAME());
		    }
		    params.put("INV_RANGE",INV_RANGE);
		if(StringUtil.isEmpty(PRD_INV_ID)){
			PRD_INV_ID= StringUtil.uuid32len();
			params.put("PRD_INV_ID", PRD_INV_ID);
			params.put("PRD_INV_NO",LogicUtil.getBmgz("DRP_PRD_INV_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("PRD_INV_ID", PRD_INV_ID);
			txUpdateById(params);
			clearCaches(PRD_INV_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PRD_INV_ID, chldList,INV_RANGE,userBean,"edit");
		}
//	    else{
//			this.delete("Inventory.delChld", PRD_INV_ID);
//		}
	}
	
	/**
	 * @详细
	 * @param param PRD_INV_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Inventory.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PRD_INV_ID the PRD_INV_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String PRD_INV_ID, List<InventoryModelChld> chldList,String INV_RANGE,UserBean userBean,String actionType) {
        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (InventoryModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("INV_NUM",model.getINV_NUM());
		    if(INV_RANGE.equals("库位盘点")){
		    	params.put("STORG_ID",model.getSTORG_ID());
			    params.put("STORG_NO",model.getSTORG_NO());
			    params.put("STORG_NAME",model.getSTORG_NAME());
		    }
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("ACCT_NUM",model.getACCT_NUM());
		    params.put("DIFF_NUM",model.getDIFF_NUM());
            params.put("PRD_INV_ID",PRD_INV_ID);
            params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
        		//如果没有明细ID的则是新增，有的是修改
                if (StringUtil.isEmpty(model.getPRD_INV_DTL_ID())) {
                    params.put("PRD_INV_DTL_ID", StringUtil.uuid32len());
    		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    		        params.put("INS_FLAG", model.getINS_FLAG());
                    addList.add(params);
                } else {
                    params.put("PRD_INV_DTL_ID", model.getPRD_INV_DTL_ID());
                    updateList.add(params);
                }
        	}else{
        		 params.put("PRD_INV_DTL_ID", StringUtil.uuid32len());
 		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
 		        params.put("INS_FLAG", model.getINS_FLAG());
                 addList.add(params);
        	}
        }
        if(!"list".equals(actionType)){
        	this.delete("Inventory.delChld", PRD_INV_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Inventory.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Inventory.insertChld", addList);
        }
        String message=checkRepeat(PRD_INV_ID);
        if(!"true".equals(message)){
        	throw new ServiceException(message);
        }
        Map<String,String> upMap=new HashMap<String,String>();
        upMap.put("UPDATOR", userBean.getRYXXID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        upMap.put("PRD_INV_ID", PRD_INV_ID);
        update("Inventory.updateById",upMap);
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_INV_ID the PRD_INV_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <InventoryModelChld> childQuery(String PRD_INV_ID) {
        Map params = new HashMap();
        params.put("PRD_INV_ID", PRD_INV_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//字段排序
		params.put("orderField", "u.PRD_NO");
        return this.findList("Inventory.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <InventoryModelChld> loadChilds(Map <String, String> params) {
       return findList("Inventory.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String PRD_INV_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PRD_INV_DTL_IDS", PRD_INV_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Inventory.deleteChldByIds", params);
    }
    /**
     * 主表编辑页面根据传递盘点范围和id获取货品信息
     */
	@SuppressWarnings("unchecked")
	public List<InventoryModelChld> accTypeChildQuery(Map<String,String> map){
    	map.put("STATE", BusinessConsts.DEL_FLAG_COMMON);
    	return findList("Inventory.getPrdById",map);
    }
	//按主表id查找盘点范围
    public String getINV_RANGE(String PRD_INV_ID){
    	return load("Inventory.getINV_RANGE",PRD_INV_ID).toString();
    }
  //按主表id修改主表状态信息
    public void alterState(Map<String,String> map){
    	if("开始盘点".equals(map.get("STATE"))){
    		Map<String,String> params=(Map<String, String>) load("Inventory.loadById",map.get("PRD_INV_ID"));
        	Map<String,String> queryMap=new HashMap<String, String>();
        	queryMap.put("STORE_ID", params.get("STORE_ID"));
        	queryMap.put("PRD_INV_ID", params.get("PRD_INV_ID"));
        	queryMap.put("INS_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        	queryMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	queryMap.put("LEDGER_ID", params.get("LEDGER_ID"));
        	StringBuffer PRD_IDBuff=new StringBuffer();
        	String PRD_IDS="";
        	String PRD_ID=params.get("PRD_ID");
        	if(!StringUtil.isEmpty(PRD_ID)){
        		String[] prdId=params.get("PRD_ID").split(",");
        		for (int i = 0; i < prdId.length; i++) {
        			PRD_IDBuff.append("'").append(prdId[i]).append("',");
    			}
        		PRD_IDS=PRD_IDBuff.toString();
        		if(!StringUtil.isEmpty(PRD_IDS)){
        			PRD_IDS = PRD_IDS.substring(0,
            				PRD_IDS.length() - 1);
        		}
        	}
        	queryMap.put("PRD_IDS", PRD_IDS);
        	if(StringUtil.isEmpty(PRD_IDS)){
        		this.insert("Inventory.insertChldById", queryMap);
        	}else{
        		this.insert("Inventory.insertPrdChldById", queryMap);
        	}
        	
    	}
    	this.update("Inventory.updateById", map);
    	if("封仓盘点".equals(map.get("INV_TYPE"))){
    		this.update("Inventory.updateDRP_STOREById",map);
    	}
    }

	public void txParseExeclToDbNew(List list, UserBean userBean,String PRD_INV_ID,String type,String initData) throws ServiceException{
		String invType = "";
		if(StringUtil.isEmpty(type)){
			invType = "封仓盘点";
		}else{
			invType = "初始化盘点";
		}
		String checkInfo=checkIsExsitNoHandOrder(userBean.getLoginZTXXID(),invType);
    	if(!"true".equals(checkInfo)){
    		throw new ServiceException(checkInfo);
    	}
		// TODO Auto-generated method stub
		List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
		//按主表id查询盘点库房编号
		String STORE_NO=(String) this.load("Inventory.getStoreNo",PRD_INV_ID);
		String sessionId=StringUtil.uuid32len();
		for(int i=0;i<list.size();i++){
			List lists=(List) list.get(i);
			for (int j = 0; j < lists.size(); j++) {
				Map<String,String> map=(Map<String,String>) lists.get(j);
				Map<String,String> params=new HashMap<String,String>();
				if(j==0){//验证第一行第一列是否有hoperun
					String check;
					if(StringUtil.isEmpty(type)){
						check=map.get("PRD_NO");
					}else{
						check=map.get("STORE_NO");
					}
					if(!check.equals("hoperun")){
						throw new ServiceException("对不起，请使用模版文件进行修改上传!");
					}
					continue;
				}
				if(j==1){//第二行为列名
					continue;
				}
				String PRD_NO=map.get("PRD_NO");
				//如果盘点数量和货品编号都为空，则跳过当前数据执行下一次循环
				if(StringUtil.isEmpty(map.get("INV_NUM"))&&StringUtil.isEmpty(PRD_NO)){
					continue;
				}
				if(StringUtil.isEmpty(type)){
					params.put("STORE_NO", STORE_NO);
				}
				params.putAll(map);
				params.put("SESSION_ID", sessionId);
				params.put("TEMP_PRD_INV_DTL_ID", StringUtil.uuid32len());
				addList.add(params);
			}
		}
        if (!addList.isEmpty()) {
           boolean flag = this.batch4Update("Inventory.insertTempChld", addList);
           if(flag==false){
        	   throw new ServiceException("插入盘点临时表出错!");
           }
        }
        if(!StringUtil.isEmpty(type)){
        	String mssage=initInventory(sessionId,userBean.getLoginZTXXID(),userBean,initData);
        	if(!"true".equals(mssage)){
        		throw new ServiceException(mssage);
        	}
        }else{
        	String mssage=potInventory(sessionId,PRD_INV_ID,userBean);
        	if(!"true".equals(mssage)){
        		throw new ServiceException(mssage);
        	}
        }
	}
	
	/**封仓盘点
	 * @return
	 */
	public String potInventory(String SESSION_ID,String PRD_INV_ID,UserBean userBean){
		String LEDGER_ID =  userBean.getLoginZTXXID();
		String mssage = checkInventory(SESSION_ID);
		if(!reutnrMsg.equals(mssage)){
			return mssage;
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("PRD_INV_ID", PRD_INV_ID);
		map.put("SESSION_ID", SESSION_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		map.put("INS_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		map.put("LEDGER_ID", LEDGER_ID);
		this.insert("Inventory.updateChldUp", map);
		this.insert("Inventory.updateChldDiffNum", map);
		this.insert("Inventory.inserChldUp", map);
		this.update("Inventory.deleteTempInv", map);
		return "true";
	}
	 //--0157490--Start--刘曰刚--2014-06-25--//
    //验证盘点货品是否重复，如果重复给予提示，按货品id或特殊工艺判断
	public String checkRepeat(String PRD_INV_ID){
		List pdtInvList  = this.findList("Inventory.checkRepeat", PRD_INV_ID);
		if(pdtInvList!=null && pdtInvList.size()>0){
			Map repeatMap = (Map)pdtInvList.get(0);
			String prdNo = (String)repeatMap.get("PRD_NO");
			return "货品编号:"+prdNo+"存在重复记录，请重新编辑!";
		}
		return "true";
	}
	//--0157490--End--刘曰刚--2014-06-25--//
	public List storeAcctQuery(String LEDGER_ID){
		return this.findList("storeAcctQuery", LEDGER_ID);
	}
	
	
	/**检查是否存在没有审核通过的盘点单，没有处理的话，不能新增盘点单
	 * 1、初始化盘点时，不允许存在任何未审核通过的盘点单
	 * 2、封仓盘点时，不允许存在任何未处理的初始化盘点单
	 * @return
	 */
	private String checkIsExsitNoHandOrder(String LEDGER_ID,String InvecType){
		if("初始化盘点".equals(InvecType)){
			Map invMap  = (Map)this.load("Inventory.initCheckInv", LEDGER_ID);
			BigDecimal allNum = (BigDecimal)invMap.get("ALL_NUM");
			if(allNum.intValue()>0){
				return "存在未审核的盘点单,请先处理完再做初始化盘点!";
			}
		}else if("封仓盘点".equals(InvecType)){
			Map invMap  = (Map)this.load("Inventory.initCheckInitInv", LEDGER_ID);
			BigDecimal allNum = (BigDecimal)invMap.get("ALL_NUM");
			if(allNum.intValue()>0){
				return "存在未审核的初使货化盘点单,请先处理完再做封仓盘点!";
			}
		}
		return reutnrMsg;
	}
	
	
	private static String reutnrMsg = "true";
	/**CHECK 初始化盘点库房
	 * @param SESSION_ID
	 */
	public String initInventory(String SESSION_ID,String LEDGER_ID,UserBean userBean,String initData){
		Map channMap = getChannYearAndMonth(LEDGER_ID);
		String INIT_YEAR = null;
		String INIT_MONTH = null;
		if(channMap!=null && channMap.size()>0){
			INIT_YEAR = (String)channMap.get("INIT_YEAR");
			INIT_MONTH = (String)channMap.get("INIT_MONTH");
			if(StringUtil.isEmpty(INIT_YEAR)|| StringUtil.isEmpty(INIT_MONTH)){
				return "请先在渠道管理设置初始化年份和月份!";
			}
		}
		String mssage = checkInitInventory(SESSION_ID,LEDGER_ID);
		if(!reutnrMsg.equals(mssage)){
			return mssage;
		}
		instStoreAcctTemp(SESSION_ID,LEDGER_ID,userBean);
		delStoreAcct(SESSION_ID,LEDGER_ID);
		delCostPrice(SESSION_ID,LEDGER_ID,INIT_YEAR,INIT_MONTH);
		delInvcAcct(SESSION_ID,LEDGER_ID,INIT_YEAR,INIT_MONTH);
		boolean isInstFlg = insertStoreAcct(SESSION_ID,LEDGER_ID);
		if(!isInstFlg){
			return "插入实时账表出错!";
		}
		
		isInstFlg = insertInvcAcct(SESSION_ID,LEDGER_ID,INIT_YEAR,INIT_MONTH);
		if(!isInstFlg){
			return "插入进销售存帐出错!";
		}
		
		isInstFlg = insertCostPrice(SESSION_ID,LEDGER_ID,INIT_YEAR,INIT_MONTH);
		if(!isInstFlg){
			return "插入成本单价表出错!";
		}
		
		insertInvcOrder(SESSION_ID,userBean,initData);
		//编辑特殊工艺是否可编辑标记
		Map<String,String> map=new HashMap<String, String>();
		map.put("SESSION_ID", SESSION_ID);
		map.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Inventory.txUpSpclEditFlagBySessId", map);
		
		boolean isDelTemFlg = delTempInvDtl(SESSION_ID);
		if(!isDelTemFlg){
			return "删除盘点临时表出错!";
		}
		
		return reutnrMsg;
	}
	
	/**备份要删除的库房账
	 * @param SESSION_ID
	 * @param LEDGER_ID
	 * @param userBean
	 * @return
	 */
	private boolean instStoreAcctTemp(String SESSION_ID,String LEDGER_ID,UserBean userBean){
		HashMap params = new HashMap();
		params.put("CREATOR",userBean.getXTYHID());
		params.put("CRE_NAME",userBean.getXM());
		params.put("LEDGER_ID",LEDGER_ID);
		params.put("SESSION_ID",SESSION_ID);
		this.update("Inventory.insertStoreAcctTemp", params);
		return true;
		
	}
	
	private boolean insertInvcOrder(String SESSION_ID,UserBean userBean,String initData){
		List storeNoList = this.findList("Inventory.queryInvcTemp", SESSION_ID);
		for(int i=0;i<storeNoList.size();i++){
		  HashMap storeNoMap = (HashMap)storeNoList.get(i);
		  String STORE_NO = (String)storeNoMap.get("STORE_NO");
		  HashMap params = new HashMap();
		    String PRD_INV_ID= StringUtil.uuid32len();
			params.put("STORE_NO", STORE_NO);
			params.put("SESSION_ID", SESSION_ID);
			params.put("PRD_INV_ID", PRD_INV_ID);
			params.put("PRD_INV_NO",LogicUtil.getBmgz("DRP_PRD_INV_NO"));
			params.put("INV_TYPE","初始化盘点");
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CRE_TIME", initData);
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		    params.put("STATE",BusinessConsts.PASS);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			boolean instFlg = this.update("Inventory.insertPRDINV", params);
			if(instFlg){
				boolean dtlFlg = insertInvcOrderDtl(PRD_INV_ID,STORE_NO,SESSION_ID);
				if(!dtlFlg){
					throw new ServiceException("新增盘点单明细出错!");
				}
			}else{
				throw new ServiceException("新增盘点单主表出错!");
			}
			try{
				LogicUtil.doInitInventoryJournalAcct(PRD_INV_ID,STORE_NO,userBean.getCHANN_ID());
			}catch(Exception ex){
				throw new ServiceException(ex.getMessage());
			}
			try{
				LogicUtil.genProfitLossOrder(PRD_INV_ID);
			}catch(Exception ex){
				throw new ServiceException(ex.getMessage());
			}
		}
		return true;
		
	}
	
	private boolean insertInvcOrderDtl(String PRD_INV_ID,String STORE_NO,String SESSION_ID){
		HashMap params = new HashMap();
		params.put("PRD_INV_ID", PRD_INV_ID);
		params.put("SESSION_ID", SESSION_ID);
		params.put("STORE_NO", STORE_NO);
		params.put("STORE_NO", STORE_NO);
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("INS_FLAG","1");
		return this.update("Inventory.insertPrdInvDtl", params);
	}
	
	private boolean insertInvcAcct(String SESSION_ID,String LEDGER_ID,String INIT_YEAR,String INIT_MONTH){
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		paraMap.put("YEAR", INIT_YEAR);
		paraMap.put("MONTH", INIT_MONTH);
		return this.update("Inventory.insertIvocAcct", paraMap);
	}                                
	
	private boolean insertCostPrice(String SESSION_ID,String LEDGER_ID,String INIT_YEAR,String INIT_MONTH){
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		paraMap.put("YEAR", INIT_YEAR);
		paraMap.put("MONTH", INIT_MONTH);
		return this.update("Inventory.insertCostPrice", paraMap);
	}
	/**从盘点临时表取数据插入库存实时账表
	 * @param LEDGER_ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean insertStoreAcct(String SESSION_ID,String LEDGER_ID)throws ServiceException{
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		return this.update("Inventory.insertStoreAcct", paraMap);
	}
	
	/**物理删除库存实时账记录
	 * @param STORE_ID
	 * @param LEDGER_ID
	 * @return
	 */
	private boolean delStoreAcct(String SESSION_ID,String LEDGER_ID){
		if(StringUtil.isEmpty(SESSION_ID)||StringUtil.isEmpty(LEDGER_ID)){
			return false;
		}
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		return this.update("Inventory.deleteStoreAcct", paraMap);
	}
	
	/**更新成本单价表(存在的就更新，不存在就插入新的)
	 * @param STORE_ID
	 * @param LEDGER_ID
	 * @return
	 */
	private boolean delCostPrice(String SESSION_ID,String LEDGER_ID,String INIT_YEAR,String INIT_MONTH){
		if(StringUtil.isEmpty(SESSION_ID)||StringUtil.isEmpty(LEDGER_ID)){
			return false;
		}
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		paraMap.put("YEAR", INIT_YEAR);
		paraMap.put("MONTH", INIT_MONTH);
		return this.update("Inventory.deleteCostPrice", paraMap);
	}
	
	/**物理删除进销存库
	 * @param STORE_ID
	 * @param LEDGER_ID
	 * @return
	 */
	private boolean delInvcAcct(String SESSION_ID,String LEDGER_ID,String INIT_YEAR,String INIT_MONTH){
		if(StringUtil.isEmpty(SESSION_ID)||StringUtil.isEmpty(LEDGER_ID)){
			return false;
		}
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		paraMap.put("LEDGER_ID", LEDGER_ID);
		paraMap.put("YEAR", INIT_YEAR);
		paraMap.put("MONTH", INIT_MONTH);
		return this.update("Inventory.deleteInvocAcct", paraMap);
	}
	
	
	
	private boolean delTempInvDtl(String SESSION_ID){
		HashMap paraMap = new HashMap();
		paraMap.put("SESSION_ID", SESSION_ID);
		return this.update("Inventory.deleteTempInv", paraMap);
	}
	
	/**查检初始化盘点数据是否合法
	 * @param SESSION_ID
	 * @return
	 */
	private String checkInitInventory(String SESSION_ID,String LEDGER_ID){
		String checkMsg  = checkMustFld(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg = checkRepeatPdt(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg = checkIsExsitPdt(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg = checkStoreExsit(SESSION_ID,LEDGER_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg =checkIsExsitPdtAndSpcl(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		return checkMsg;
	}
	
	/**查检盘点导入数据是否合法
	 * @param SESSION_ID
	 * @return
	 */
	private String checkInventory(String SESSION_ID){
		String checkMsg  = checkMustFldUp(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg = checkRepeatPdt(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg = checkIsExsitPdt(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		checkMsg =checkIsExsitPdtAndSpcl(SESSION_ID);
		if(!reutnrMsg.equals(checkMsg)){
			return checkMsg;
		}
		return checkMsg;
	}
	
	
	
	
	/**检查有不存在的库房
	 * @param SESSION_ID
	 * @return
	 */
	private String checkStoreExsit(String SESSION_ID,String LEDGER_ID){
		HashMap paramMap = new HashMap();
		paramMap.put("SESSION_ID", SESSION_ID);
		paramMap.put("LEDGER_ID",LEDGER_ID);
		List StoreList = this.findList("Inventory.checkStoretExsit", paramMap);
		if(StoreList!=null && StoreList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap storeMap = (HashMap)StoreList.get(0);
			String STORE_NO = (String)storeMap.get("STORE_NO");
			if(STORE_NO==null){
				buf.append("存在为空的库房编号!");
			}else{
				buf.append("有不存在的库房,库房,编号:").append(STORE_NO).append(",请重新编辑!");
			}
			return buf.toString();
		}
		return reutnrMsg;
	}
	
	/**检查渠道是否维护了初使化年份月份
	 * @param LEDGER_ID
	 * @return
	 */
	private Map getChannYearAndMonth(String LEDGER_ID){
		StringBuffer buf = new StringBuffer();
		HashMap paramMap = new HashMap();
		paramMap.put("CHANN_ID",LEDGER_ID);
		Map channMap = (Map)this.load("Inventory.checkChannInitYearAndMonth", paramMap);
		return channMap;
	}
	
	/**检查是必填字段
	 * @param SESSION_ID
	 * @return
	 */
	private String checkMustFld(String SESSION_ID){
		List mustList = this.findList("Inventory.checkMustFld", SESSION_ID);
		if(mustList!=null && mustList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap msutMap = (HashMap)mustList.get(0);
			String STORE_NO = (String)msutMap.get("STORE_NO");
			String PRD_NO = (String)msutMap.get("PRD_NO");
			BigDecimal INV_NUM = (BigDecimal)msutMap.get("INV_NUM");
			BigDecimal INV_PRICE = (BigDecimal)msutMap.get("INV_PRICE");
			if(StringUtil.isEmpty(STORE_NO)){
				buf.append("库房编号(STORE_ON)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}else if(StringUtil.isEmpty(PRD_NO)){
				buf.append("产品编号(PRD_NO)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}else if(INV_NUM==null){
				buf.append("盘点数量(INV_NUM)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}else if(INV_PRICE==null){
				buf.append("价格(INV_PRICE)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}
		}
		return reutnrMsg;
	}
	
	/**盘点导入检查是必填字段
	 * @param SESSION_ID
	 * @return
	 */
	private String checkMustFldUp(String SESSION_ID){
		List mustList = this.findList("Inventory.checkMustFld", SESSION_ID);
		if(mustList!=null && mustList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap msutMap = (HashMap)mustList.get(0);
			String STORE_NO = (String)msutMap.get("STORE_NO");
			String PRD_NO = (String)msutMap.get("PRD_NO");
			BigDecimal INV_NUM = (BigDecimal)msutMap.get("INV_NUM");
			BigDecimal INV_PRICE = (BigDecimal)msutMap.get("INV_PRICE");
			if(StringUtil.isEmpty(PRD_NO)){
				buf.append("产品编号(PRD_NO)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}else if(INV_NUM==null){
				buf.append("盘点数量(INV_NUM)").append("是必填字段,请重新编辑!");
				return buf.toString();
			}
		}
		return reutnrMsg;
	}
	
	
	/**检查是否有重复记录
	 * @param SESSION_ID
	 * @return
	 */
	private String checkRepeatPdt(String SESSION_ID){
		List pdtList = this.findList("Inventory.checkPdtRepeat", SESSION_ID);
		if(pdtList!=null && pdtList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap pdtMap = (HashMap)pdtList.get(0);
			String PRD_NO = (String)pdtMap.get("PRD_NO");
			buf.append("货品编号:").append(PRD_NO).append("存在重复记录,请重新编辑!");
			return buf.toString();
		}
		return reutnrMsg;
	}
	
	/**检查盘点货品在货品表中是否存在
	 * @param SESSION_ID
	 * @return
	 */
	private String checkIsExsitPdt(String SESSION_ID){
		List pdtList = this.findList("Inventory.checkPdtNotExsit", SESSION_ID);
		if(pdtList!=null && pdtList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap pdtMap = (HashMap)pdtList.get(0);
			String PRD_NO = (String)pdtMap.get("PRD_NO");
			buf.append("有不存在的货品,货品编号:").append(PRD_NO).append(",请重新编辑!");
			return buf.toString();
		}
		return reutnrMsg;
	}
	public void upChld(List<InventoryModelChld> chldList){
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for (InventoryModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_INV_DTL_ID", model.getPRD_INV_DTL_ID());
            params.put("INV_NUM", model.getINV_NUM());
            params.put("upDIFF_NUM", "计算差异");
            updateList.add(params);
		}
		if (!updateList.isEmpty()) {
            this.batch4Update("Inventory.updateChldById", updateList);
        }
	}
	/**
	 * 查询
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryChld(Map<String, String> params, int pageNo){
		return this.pageQuery("Inventory.pageQueryChldByFkId", "Inventory.queryChldByFkIdCount",
				params, pageNo);
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Inventory.queryChldByFkIdCount", map);
		return Long.parseLong(obj.toString());
	}
	public int chldCountQuery(String PRD_INV_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("PRD_INV_ID", PRD_INV_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return Integer.parseInt(this.load("Inventory.queryChldByFkIdCount",map).toString());
	}
	/**检查盘点货品和特殊工艺编号是否对应
	 * @param SESSION_ID
	 * @return
	 */
	private String checkIsExsitPdtAndSpcl(String SESSION_ID){
		List pdtList = this.findList("Inventory.checkPdtAndSpclNotExsit", SESSION_ID);
		if(pdtList!=null && pdtList.size()>0){
			StringBuffer buf = new StringBuffer();
			HashMap pdtMap = (HashMap)pdtList.get(0);
			String PRD_NO = (String)pdtMap.get("PRD_NO");
			String SPCL_TECH_NO = (String)pdtMap.get("SPCL_TECH_NO");
			buf.append("有货品不对应的特殊工艺编号,货品编号:").append(PRD_NO+"特殊工艺号:"+SPCL_TECH_NO).append(",请重新编辑!");
			return buf.toString();
		}
		return reutnrMsg;
	}
	public String getChannMonth(String CHANN_ID){
		Object data=this.load("Inventory.getChannMonth",CHANN_ID);
		if(null==data){
			return null;
		}else{
			return data.toString();
		}
	}
	public Map getAllNum(Map<String,String> map){
		return (Map) this.load("Inventory.getAllNum",map);
	}
}