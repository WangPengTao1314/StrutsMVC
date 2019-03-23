package com.hoperun.erp.sale.turnoverplan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.erp.sale.turnoverplan.service.TurnoverplanService;
import com.hoperun.sys.model.UserBean;

public class TurnoverplanServiceImpl extends BaseService implements
		TurnoverplanService {

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
		//变颜色的日期
		params.put("BILL_DIFF_DATE_FLAG", BusinessConsts.BILL_DIFF_DATE_FLAG);
		return this.pageQuery("Turnoverplan.pageQuery",
				"Turnoverplan.pageCount", params, pageNo);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param SALE_ORDER_IDS 主表IDS
	 *             
	 * @return the list
	 */
	public List<SaleorderspModelChld> childQuery(String SALE_ORDER_IDS) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_ORDER_IDS", SALE_ORDER_IDS);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询 未取消预定的 0 正常
		params.put("CANCEL_FLAG", BusinessConsts.INTEGER_0);
		return this.findList("Turnoverplan.queryChldByFkId", params);
	}


	 /**
     * 编辑：新增
     * @param model 发运单
     * @param childList 明细
     * @return the string
     */
    public void txEdit(TurnoverplanModel model, 
    		List<TurnoverplanChildModel> childList,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	String DELIVER_ORDER_ID = StringUtil.uuid32len();
    	String DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
    	params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
    	params.put("BILL_TYPE", "销售发运");//来源于销售订单
    	
    	String DELIVER_TYPE =  model.getDELIVER_TYPE();
    	String CHANN_TYPE = model.getCHANN_TYPE();
    	
    	//整车发运的 时候 有 ‘车型’ ‘区域代发|基地发货’ 字段
    	if(BusinessConsts.DELIVER_TYPE.equals(DELIVER_TYPE)){
    		params.put("TRUCK_TYPE",model.getTRUCK_TYPE());
    		//选择了 ‘区域代发’ 就是 区域服务中心 发货 
        	if(!StringUtil.isEmpty(CHANN_TYPE) && BusinessConsts.INTEGER_1.equals(CHANN_TYPE)){
            	params.put("CHANN_TYPE","区域代发");
        	}else{
            	params.put("CHANN_TYPE","基地发货");
        	}
    	}else{
        	params.put("CHANN_TYPE","基地发货");
    	}
    	//发货方是总部
    	params.put("SEND_CHANN_ID",userBean.getBASE_CHANN_ID());
    	params.put("SEND_CHANN_NO",userBean.getBASE_CHANN_NO());
    	params.put("SEND_CHANN_NAME",userBean.getBASE_CHANN_NAME());
    	
    	params.put("DELIVER_TYPE",DELIVER_TYPE);
    	params.put("TOTAL_VOLUME",model.getTOTAL_VOLUME());
        String AREA_SER_ID = model.getAREA_SER_ID();
        if(StringUtil.isEmpty(AREA_SER_ID)){
        	params.put("ORDER_CHANN_ID",model.getRECV_CHANN_ID());
        	params.put("ORDER_CHANN_NO",model.getRECV_CHANN_NO());
        	params.put("ORDER_CHANN_NAME",model.getRECV_CHANN_NAME());
        }else{
        	params.put("ORDER_CHANN_ID",AREA_SER_ID);
        	params.put("ORDER_CHANN_NO",model.getAREA_SER_NO());
        	params.put("ORDER_CHANN_NAME",model.getAREA_SER_NAME());
        }
        	
    	params.put("AREA_SER_ID",AREA_SER_ID);
    	params.put("AREA_SER_NO",model.getAREA_SER_NO());
    	params.put("AREA_SER_NAME",model.getAREA_SER_NAME());
    	params.put("ADVC_SEND_DATE",model.getADVC_SEND_DATE());
    	params.put("SHIP_POINT_ID",model.getSHIP_POINT_ID());
    	params.put("SHIP_POINT_NAME",model.getSHIP_POINT_NAME());
    	params.put("REMARK",model.getREMARK());
    	params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		params.put("STATE",BusinessConsts.UNCOMMIT);//未提交
	    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    	
	    params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());//收货方ID
	    params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());//收货方编号
	    params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());//收货方名称
	    params.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());//收货地址编号
    	
    	insert("Turnoverplan.insertDeliverOrder",params);
    	if(null != childList && !childList.isEmpty()){
    		insertMx(DELIVER_ORDER_ID,model,DELIVER_ORDER_NO,childList);
    	}
    }
    /**
     * 编辑明细
     * @param DELIVER_ORDER_ID
     * @param model
     * @param DELIVER_ORDER_NO
     * @param childList
     */
    public void insertMx(String DELIVER_ORDER_ID,TurnoverplanModel model,
    		String DELIVER_ORDER_NO,List<TurnoverplanChildModel> childList){
    	//新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        for(TurnoverplanChildModel child : childList){
        	Map<String,Object> param = new HashMap<String,Object>();
        	String DELIVER_ORDER_DTL_ID =  StringUtil.uuid32len();
        	param.put("SALE_ORDER_DTL_ID", child.getSALE_ORDER_DTL_ID());
        	param.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
        	param.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
        	param.put("ADVC_PLAN_NUM", child.getADVC_PLAN_NUM());//预排车数量
        	param.put("PLAN_NUM", child.getADVC_PLAN_NUM());//默认 插入 预排车数量
        	
        	param.put("NEW_SPEC", child.getNEW_SPEC());//新规格
        	param.put("NEW_COLOR", child.getNEW_COLOR());//新花号
        	
        	//如果页面选择了 收货地址 则使用选择的收货地址
        	if(!StringUtil.isEmpty(model.getRECV_ADDR())){
        		param.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());//收货方ID
            	param.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());//收货方编号
            	param.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());//收货方名称
            	param.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());//收货地址编号
            	param.put("RECV_ADDR", model.getRECV_ADDR());//收货方地址详细
        	}else{
        		param.put("RECV_CHANN_ID", child.getRECV_CHANN_ID());//收货方ID
            	param.put("RECV_CHANN_NO", child.getRECV_CHANN_NO());//收货方编号
            	param.put("RECV_CHANN_NAME", child.getRECV_CHANN_NAME());//收货方名称
            	param.put("RECV_ADDR_NO", child.getRECV_ADDR_NO());//收货地址编号
            	param.put("RECV_ADDR", child.getRECV_ADDR());//收货方地址详细
        	}
        
            String REMARK = StringUtil.nullToSring(child.getREMARK());
        	param.put("REMARK", REMARK.trim());
        	param.put("NO_SEND_NUM", 0);
        	param.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        	param.put("U_CODE", DELIVER_ORDER_DTL_ID);//U9货品维度编号
        	Double LAST_PLANED_NUM = StringUtil.getDouble(child.getLAST_PLANED_NUM());//最后一次 排车数量
        	Double ADVC_PLAN_NUM = StringUtil.getDouble(child.getADVC_PLAN_NUM());//当前排车数量 即 ‘发运数量’
        	
        	//反填 销售订单明细的 已排车数量  每次和上一次的排车数量累加
        	param.put("PLANED_NUM", LAST_PLANED_NUM+ADVC_PLAN_NUM);
        	
        	addList.add(param);
        }
        
        if(!addList.isEmpty()){
        	this.batch4Update("Turnoverplan.insertDeliverOrderDtl", addList);
        	
        	this.batch4Update("Turnoverplan.updateSaleDtlById", addList);
        	
        }
    }
    
    
    /**
     * 返修 生成发运单
     */
    public void txEditFY(Map<String,Object> model, List<Map<String,String>> childList,UserBean userBean){
    	insert("Turnoverplan.insertDeliverOrder",model);
    	 
    	if(null != childList && !childList.isEmpty()){
    		this.batch4Update("Turnoverplan.insertDeliverOrderDtlFY", childList);
    	}
    }
    
    /**
     * 判断所选订单的发货方是否是同一个区域服务中心
     * @param SALE_ORDER_IDS 订单IDS
     * @return fasle->非同一个区域服务中心
     */
    public boolean justOnlyAreaSer(String SALE_ORDER_IDS){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("SALE_ORDER_IDS", SALE_ORDER_IDS);
    	List<Map<String,String>> list = this.findList("Turnoverplan.justOnlyAreaSer", params);
    	for(int i=0;i<list.size();i++){
    		String RECV_CHANN_ID = list.get(i).get("RECV_CHANN_ID");
    		String AREA_SER_ID = list.get(i).get("AREA_SER_ID");
    		for(int j=list.size()-1;j>i;j--){
    			String RECV_CHANN_ID_ = list.get(j).get("RECV_CHANN_ID");
        		String AREA_SER_ID_ = list.get(j).get("AREA_SER_ID");
        		
        		if(RECV_CHANN_ID.equals(RECV_CHANN_ID_) || RECV_CHANN_ID.equals(AREA_SER_ID_) || (null != AREA_SER_ID && (AREA_SER_ID.equals(AREA_SER_ID_) || AREA_SER_ID.equals(RECV_CHANN_ID_)))){
        			//正常
        		}else{
        			return false;//非同一个区域服务中心
        		}
    		}
    	}
    	
    	return true;
    }
    /**
     * 修改单据状态
     * @param map
     */
    public void upStart(Map<String,String> map){
    	String SALE_ORDER_IDS=map.get("SALE_ORDER_IDS");
    	int count=StringUtil.getInteger(this.load("Turnoverplan.checkPlanedNum", SALE_ORDER_IDS));
    	if(count>0){
    		throw new ServiceException("对不起，您所选的订单里有已排车订单 !");
    	}
    	this.update("Turnoverplan.upSatrtByIdS", map);
    }
    
    public List downQuery(Map<String,String> map){
		return this.findList("Turnoverplan.downGetById", map);
	}
}
