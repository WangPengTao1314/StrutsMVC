/**

 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderServiceImpl
*/
package com.hoperun.erp.sale.techorderprice.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.techorderprice.model.TechorderPriceModelChld;
import com.hoperun.erp.sale.techorderprice.service.TechorderPriceService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 18:31:07
 */
public class TechorderPriceServiceImpl extends BaseService implements TechorderPriceService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("TechorderPrice.pageQuery", "TechorderPrice.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("TechorderPrice.insert", params);
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
		return update("TechorderPrice.updateById", params);
	}
	
	/**
	 * @详细
	 * @param param TECH_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("TechorderPrice.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String TECH_ORDER_ID, List<TechorderPriceModelChld> chldList) {

        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TechorderPriceModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SPCL_TECH_FLAG",model.getSPCL_TECH_FLAG());
		    params.put("IS_CAN_PRD_FLAG",model.getIS_CAN_PRD_FLAG());
		    params.put("NEW_PRD_ID",model.getNEW_PRD_ID());
		    params.put("NEW_PRD_NO",model.getNEW_PRD_NO());
		    params.put("NEW_PRD_NAME",model.getNEW_PRD_NAME());
		    params.put("NEW_PRD_SPEC",model.getNEW_PRD_SPEC());
            params.put("TECH_ORDER_ID",TECH_ORDER_ID);
            params.put("PRICE_DECIDE", model.getPRICE_DECIDE());
            params.put("REMARK",model.getREMARK()); 
            params.put("TECH_ORDER_DTL_ID", model.getTECH_ORDER_DTL_ID());
            updateList.add(params);
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("TechorderPrice.updateChldById", updateList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TechorderPriceModelChld> childQuery(String TECH_ORDER_ID) {
        Map params = new HashMap();
        params.put("TECH_ORDER_ID", TECH_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        return this.findList("TechorderPrice.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param TECH_ORDER_DTL_IDs the TECH_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TechorderPriceModelChld> loadChilds(Map <String, String> params) {
       return findList("TechorderPrice.loadChldByIds",params);
    }
    
    
    /**
     * 审核
     * @param TECH_ORDER_ID 工艺单ID
     * @param TECH_ORDER_NO 工艺单NO
     * @param SALE_ORDER_ID 销售订单ID
     * @param userBean
     */
    @SuppressWarnings("unchecked")
	public void txAudit(String TECH_ORDER_ID,String TECH_ORDER_NO,String SALE_ORDER_ID,UserBean userBean){
    	Map<String,String> maps=new HashMap<String,String>();
    	maps.put("TECH_ORDER_ID", TECH_ORDER_ID);
    	maps.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
    	//检查可生产货品
    	List<Map<String,String>> list=findList("TechorderPrice.getByNewPrdInfo",maps);
    	//检查不可生产货品
    	maps.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
    	List<Map<String,String>> noCanlist=findList("TechorderPrice.getByNewPrdInfo",maps);
    	
    	//回填可生产货品
    	for(int i=0;i<list.size();i++){
    		Map<String,String> params=list.get(i);
    		//按新增货品id到货品表里查询的货品信息
    		String PRD_ID=params.get("NEW_PRD_ID");
    		Map<String,String> prdMap=(Map<String, String>) load("TechorderPrice.getPrdInfo",PRD_ID);
    		if(null==prdMap){
    			throw new ServiceException("请检查所有可生产货品是否选择新货品编号");
    		}
    		String SALE_ORDER_DTL_ID=params.get("FROM_BILL_DTL_ID");
    		Map<String,String> oldSaleMap=(Map<String, String>) load("TechorderPrice.getOldSale",SALE_ORDER_DTL_ID);
    		if(null==oldSaleMap){
    			//事务回滚，执行不了
//    			Map<String,String> deleMap=new HashMap<String, String>();
//    			deleMap.put("TECH_ORDER_ID", TECH_ORDER_ID);
//    			deleMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
//    			deleMap.put("UPD_NAME", userBean.getXM());
//    			deleMap.put("UPDATOR", userBean.getXTYHID());
//    			this.delete("TechorderPrice.delete", deleMap);
    			throw new ServiceException("该工艺单所指向的订货订单已被删除！");
    		}
    		//需要反填到销售订单明细里的数据
    		Map<String,String> param=new HashMap<String,String>();
    		param.putAll(prdMap);
    		param.putAll(oldSaleMap);
    		param.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
    		Object obj=params.get("PRICE_DECIDE");
    		if(obj==null){
    			throw new ServiceException("请检查所有可生产货品核价是否填写");
    		}
    		//核定价|折后价
    		double PRICE_DECIDE=Double.parseDouble(obj.toString());
    		obj=params.get("PRICE");
    		if(obj.toString().equals("0")){
    			throw new ServiceException("有货品单价为0,不能核价");
    		}
    		//单价
    		double PRICE=Double.parseDouble(obj.toString());
    		//折扣率
    		double DECT_RATE=PRICE_DECIDE/PRICE;
    		obj=params.get("ORDER_NUM");
    		//订货数量
    		double ORDER_NUM=Double.parseDouble(obj.toString());
    		//总金额
    		double ORDER_AMOUNT=PRICE_DECIDE*ORDER_NUM;
    		param.put("PRICE",PRICE+"" );
    		param.put("DECT_RATE", subZeroAndDot(String.format("%.2f",DECT_RATE )));
    		param.put("DECT_PRICE",PRICE_DECIDE+"" );
    		param.put("ORDER_AMOUNT", subZeroAndDot(String.format("%.2f",ORDER_AMOUNT )));
    		param.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
    		param.put("SPCL_TECH_ID", "");
    		update("TechorderPrice.upERP_SALE_ORDER_DTL",param);
    		update("TechorderPrice.upDRP_GOODS_ORDER_DTL",param);
    		update("TechorderPrice.upDRP_ADVC_ORDER_DTL",param);
    	}
    	//回填不可生产货品
    	for (int i = 0; i < noCanlist.size(); i++) {
    		Map<String,String> params=noCanlist.get(i);
    		String SALE_ORDER_DTL_ID=params.get("FROM_BILL_DTL_ID");
    		//需要反填到销售订单明细里的数据
    		Map<String,String> param=new HashMap<String,String>();
    		param.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
    		param.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
    		update("TechorderPrice.upERP_SALE_ORDER_DTL",param);
		}
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("TECH_ORDER_ID", TECH_ORDER_ID);
        map.put("STATE", "已核价");
        map.put("AUDIT_ID", userBean.getRYXXID());
        map.put("AUDIT_NAME", userBean.getXM());
        map.put("AUDIT_TIME", "sysdate");
        map.put("UPDATOR", userBean.getRYXXID());//更新人
        map.put("UPD_NAME", userBean.getXM());
        map.put("UPD_TIME", "sysdate");
    	txUpdateById(map);
    	//更新明细状态
    	this.update("TechorderPrice.updateChldByMainId", map);
    	//更新销售订单状态
    	this.update("TechorderPrice.upSaleState", map);
    	maps.clear();
    	maps.put("TECH_ORDER_ID", TECH_ORDER_ID);
    	maps.put("TECH_ORDER_NO", TECH_ORDER_NO);
    	maps.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	//插入生命周期
    	inertSaleordertrace(maps,userBean);
    	
    }
    /**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return 
	 */
	public static String subZeroAndDot(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
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
		paramMap.put("BILL_NO", parent.get("TECH_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", "已核价");//已核价
//		paramMap.put("TRACE_URL","");
		 
		paramMap.put("BILL_TYPE", "工艺单");
		paramMap.put("ACTION_NAME", "工艺单核价");
		
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
	 * 发消息
	 * @param TECH_ORDER_ID 工艺单ID
	 * @param TECH_ORDER_NO 工艺单编号
	 * @param userBean
	 */
	@SuppressWarnings("unchecked")
	public void txSendMessage(String TECH_ORDER_ID,String TECH_ORDER_NO, UserBean userBean) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String,String> techorder = (Map<String, String>) load("Techorder.loadById", TECH_ORDER_ID);
			 
			params.put("CREATOR", techorder.get("CREATOR"));
			
			String msg = "您有单号为：[" + TECH_ORDER_NO + "]的工艺单[已核价]，请尽快处理";
			List<Map<String, String>> list = this.findList(
					"Saleorder.queryReceiver", params);
			String XTYHIDS = "";
			if (null != list) {
				for (Map<String, String> map : list) {
					XTYHIDS += "'"+map.get("XTYHID") + "',";
				}
			}
			if (!"".equals(XTYHIDS)) {
				XTYHIDS = XTYHIDS.substring(0, XTYHIDS.length() - 1);
			}
			if(StringUtil.isEmpty(XTYHIDS)){
				XTYHIDS="''";
			}
			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("YHBH", userBean.getYHBH());
			sendMap.put("SENDID", userBean.getXTYHID());
			sendMap.put("SENDER", userBean.getYHM());
			sendMap.put("MSGINFO", msg);
			sendMap.put("XTYHID", XTYHIDS);
			sendMap.put("BMXXID", "''");
			sendMap.put("JGXXID", "''");

			this.insert("MESSAGE.insertMessage", sendMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("工艺工程部核价完成给客服发消息失败，单号：" + TECH_ORDER_NO);
		}

	}
    
    
}