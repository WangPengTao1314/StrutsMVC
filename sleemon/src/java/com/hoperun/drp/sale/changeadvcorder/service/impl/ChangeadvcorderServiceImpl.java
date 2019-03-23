package com.hoperun.drp.sale.changeadvcorder.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModel;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelChld;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelGchld;
import com.hoperun.drp.sale.changeadvcorder.service.ChangeadvcorderService;
import com.hoperun.sys.model.UserBean;

/**
 * 销售管理-终端销售-预订单变更
 * @author gu_hongxiu
 *
 */
public class ChangeadvcorderServiceImpl extends BaseService implements
ChangeadvcorderService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Changeadvcorder.pageQuery", "Changeadvcorder.pageCount",
				params, pageNo);
	}
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_CHANGE_ID
	 *            the ADVC_ORDER_CHANGE_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChangeadvcorderModelChld> childQuery(String ADVC_ORDER_CHANGE_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Changeadvcorder.queryChldByFkId", params);
	}
	
	/**
	 * @详细
	 * @param param
	 *            ADVC_ORDER_CHANGE_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Changeadvcorder.loadById", param);
	}
	
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_CHANGE_ID
	 * @param chldList
	 * @param userBean
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_CHANGE_ID,ChangeadvcorderModel model,
			List<ChangeadvcorderModelChld> chldList, UserBean userBean) throws ParseException{
			String ADVC_ORDER_ID=model.getADVC_ORDER_ID();
			int count=this.countAdvcApp(ADVC_ORDER_ID);
			if(count>0){
				throw new ServiceException("您所选择的预订单有正在发货的商品，请重新选择！");
			}
			Map<String,String> params = new HashMap<String,String>();
			if(model.getADVC_ORDER_CHANGE_NO().equals(BusinessConsts.ZDSC)){
				params.put("ADVC_ORDER_CHANGE_NO",LogicUtil.getBmgz("DRP_ADVC_ORDER_CHANGE_NO"));//预订单变更编号
			}else{
				params.put("ADVC_ORDER_CHANGE_NO",model.getADVC_ORDER_CHANGE_NO());//预订单变更编号
			}
		    params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
		    params.put("ADVC_ORDER_NO",model.getADVC_ORDER_NO());		   
		    params.put("TERM_ID",model.getTERM_ID());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("SALE_DATE",model.getSALE_DATE());
		    params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("RECV_ADDR",model.getRECV_ADDR());
		    params.put("ADVC_AMOUNT",model.getADVC_AMOUNT());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());		   
		    params.put("REMARK",model.getREMARK());
		    params.put("DIFF_AMOUNT", model.getDIFF_AMOUNT());
		    params.put("OLD_CUST_NAME", model.getOLD_CUST_NAME());
		    params.put("OLD_TEL", model.getOLD_TEL());
		    params.put("OLD_RECV_ADDR", model.getOLD_RECV_ADDR());
		if(StringUtil.isEmpty(ADVC_ORDER_CHANGE_ID)){
			ADVC_ORDER_CHANGE_ID= StringUtil.uuid32len();	
			params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
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
		    params.put("BILL_TYPE", "预订单变更");
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
			txUpdateById(params);
			clearCaches(ADVC_ORDER_CHANGE_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ADVC_ORDER_CHANGE_ID,model.getADVC_ORDER_ID(), chldList,"edit");
		}
	}
	
	/**
	 * 历史表记录保存
	 * @param ADVC_ORDER_CHANGE_ID
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean txHisInert(String ADVC_ORDER_CHANGE_ID,String ADVC_ORDER_DTL_ID){
		Map params = new HashMap();
		params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
		params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
		params.put("ADVC_ORDER_CHANGE_HIS_ID", StringUtil.uuid32len());
		this.insert("Changeadvcorder.insertHis", params);
		return true;
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Changeadvcorder.insert", params);
		return true;
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Changeadvcorder.updateById", params);
	}
	
	/**
     * * 明细数据编辑
     * 
     * @param ADVC_ORDER_CHANGE_ID the ADVC_ORDER_CHANGE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
	 * @throws ParseException 
     */
    @Override
    public void txChildEdit(String ADVC_ORDER_CHANGE_ID,String ADVC_ORDER_ID, List<ChangeadvcorderModelChld> chldList,String actionType) throws ParseException {
    	String currentDate=this.getDate();
        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (ChangeadvcorderModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();            
            String ADVC_ORDER_CHANGE_DTL_ID=model.getADVC_ORDER_CHANGE_DTL_ID();
                                    
            Map<String,String> checkMap=new HashMap<String,String>();
            checkMap.put("PRD_ID", model.getPRD_ID());
            checkMap.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
            checkMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            int count=Integer.parseInt(load("Changeadvcorder.checkDtl",checkMap).toString());
            if(count>0&&StringUtil.isEmpty(ADVC_ORDER_CHANGE_DTL_ID)){
            	throw new ServiceException("所添加货品有重复项，请重新添加 !");
            }            
            String ORDER_RECV_DATE=model.getORDER_RECV_DATE();
            if(!StringUtil.isEmpty(ORDER_RECV_DATE)){
            	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date d1 = df.parse(ORDER_RECV_DATE);    
     			Date d2 = df.parse(currentDate);    
     			long diff = d1.getTime() - d2.getTime();    
     			long days = diff / (1000 * 60 * 60 * 24);
     			if(days<0){
     				throw new ServiceException("交货日期不能在今天之前!");
     			}
            }
           String ORDER_NUM =  model.getORDER_NUM();
           Integer FREEZE_NUM =  model.getFREEZE_NUM();
           int I_ORDER_NUM =  Integer.parseInt(ORDER_NUM);
           int I_FREEZE_NUM = FREEZE_NUM.intValue();
           int I_UNFREEZE_NUM = 0;
           if(I_FREEZE_NUM>I_ORDER_NUM){
        	   I_UNFREEZE_NUM = I_FREEZE_NUM - I_ORDER_NUM;
        	   I_FREEZE_NUM = I_ORDER_NUM;
           }
           
            params.put("ORDER_RECV_DATE",ORDER_RECV_DATE);
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
		    params.put("PRICE",model.getPRICE());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("ORDER_NUM",model.getORDER_NUM());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		    params.put("REMARK", model.getREMARK());
		    params.put("ADVC_ORDER_DTL_ID", model.getADVC_ORDER_DTL_ID());
            params.put("ADVC_ORDER_CHANGE_ID",ADVC_ORDER_CHANGE_ID);
            params.put("FREEZE_NUM", String.valueOf(I_FREEZE_NUM));
            params.put("UNFREEZE_NUM",String.valueOf(I_UNFREEZE_NUM));
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
        		//如果没有明细ID的则是新增，有的是修改
        		if (StringUtil.isEmpty(ADVC_ORDER_CHANGE_DTL_ID)) {	            	
	                params.put("ADVC_ORDER_CHANGE_DTL_ID", StringUtil.uuid32len());	                
			        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	                addList.add(params);
	            } else {	            	
	                params.put("ADVC_ORDER_CHANGE_DTL_ID", model.getADVC_ORDER_CHANGE_DTL_ID());
	                updateList.add(params);
	            }
        	}else{
                params.put("ADVC_ORDER_CHANGE_DTL_ID", StringUtil.uuid32len());                
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
        	}
        	//历史表信息保存
    	    txHisInert(ADVC_ORDER_CHANGE_ID,model.getADVC_ORDER_DTL_ID());
        }
        if(!"list".equals(actionType)){
        	this.delete("Changeadvcorder.delChld", ADVC_ORDER_CHANGE_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Changeadvcorder.updateChldById", updateList);            
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Changeadvcorder.insertChld", addList);            
        }
        this.update("Changeadvcorder.upDIFF_AMOUNT", ADVC_ORDER_CHANGE_ID);
     }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String ADVC_ORDER_CHANGE_DTL_IDs,String ADVC_ORDER_CHANGE_ID) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("ADVC_ORDER_CHANGE_DTL_IDS", ADVC_ORDER_CHANGE_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	   params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
       update("Changeadvcorder.deleteChldByIds", params);
       update("Changeadvcorder.deleteGchldByIds", params);
       this.update("Changeadvcorder.upDIFF_AMOUNT", ADVC_ORDER_CHANGE_ID);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <ChangeadvcorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Changeadvcorder.loadChldByIds",params);
    }
    
    /**
     * 修改提交状态
     */
    public void txCommitById(Map <String, String> params){
    	update("Changeadvcorder.commitById", params);
    }    
	
    /**
	 * @删除
	 * @param ADVC_ORDER_CHANGE_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Changeadvcorder.delete", params);
		 //删除子表 
		 return update("Changeadvcorder.delChldByFkId", params);
	}

	/**
	 * @预订单明细
	 * @param param
	 *            ADVC_ORDER_DTL_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> loadOldChild(String ADVC_ORDER_DTL_ID) {
		
		return (Map<String, String>)load("Changeadvcorder.loadOldChldById", ADVC_ORDER_DTL_ID);
		
	}

	/**
	 * @预订单明细列表
	 * @param param
	 *            ADVC_ORDER_DTL_IDS
	 * 
	 * @return the List<Map<String, String>>
	 */
	public List<Map<String, String>> loadOldChildList(String ADVC_ORDER_DTL_IDS) {
										
		return (List<Map<String, String>>) findList("Changeadvcorder.loadOldChldByIds", ADVC_ORDER_DTL_IDS);
	}

	/**
	 * @预订单查询
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the List<Map<String, String>>
	 */
	public List<Map<String, String>> loadOld(String ADVC_ORDER_ID) {
		return (List<Map<String, String>>)findList("Changeadvcorder.queryByOrderId", ADVC_ORDER_ID);
	}
	// 获取数据库当前时间
	public String getDate() {
		return load("Advcorder.getDate", "").toString();
	}
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@SuppressWarnings("unchecked")
	public List<ChangeadvcorderModelGchld> gchildQuery(String ADVC_ORDER_CHANGE_ID) {
		Map params = new HashMap();
		params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Changeadvcorder.queryGchldByFkId", params);
	}
	public int countAdvcApp(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return Integer.parseInt(this.load("Changeadvcorder.countAdvcApp",map).toString());
	}
}