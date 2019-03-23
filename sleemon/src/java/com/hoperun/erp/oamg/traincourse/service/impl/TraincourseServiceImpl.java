
package com.hoperun.erp.oamg.traincourse.service.impl;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.oamg.traincourse.model.TraincourseModel;
import com.hoperun.erp.oamg.traincourse.service.TraincourseService;
import com.hoperun.sys.model.UserBean;

/**
 * 培训课程维护
 * @author gu_hongxiu
 *
 */
public class TraincourseServiceImpl extends BaseService implements TraincourseService {
	
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Traincourse.pageQuery", "Traincourse.pageCount",
				params, pageNo);
	}
	
	/**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
	public Map<String, String> load(String param) {		
		Map<String, String> params = new HashMap<String, String>();
        params.put("TRAIN_COURSE_ID", param);
        return (Map<String, String>) load("Traincourse.loadById", params);
	}
	
	/**
	 * 加载图片.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param){		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_BILL_ID", param);
        return (String) load("Att.loadAtt", params);
	}
	
	/**
     * 编辑：新增/修改
     * @param TRAIN_COURSE_ID
     * @param model
     * @param userBean
     * @return
     */
	public String txEdit(String TRAIN_COURSE_ID, TraincourseModel model, UserBean userBean) {
		Map <String, String> params = new HashMap <String, String>();
		
		params.put("TRAIN_COURSE_ID",model.getTRAIN_COURSE_ID());  // 培训课程ID 
		
		 //培训课程编号   
		if(model.getTRAIN_COURSE_NO().equals(BusinessConsts.ZDSC)){
			params.put("TRAIN_COURSE_NO",LogicUtil.getBmgz("ERP_TRAIN_COURSE_NO"));
		}else{
			params.put("TRAIN_COURSE_NO",model.getTRAIN_COURSE_NO());
		}
		
		params.put("TRAIN_COURSE_NAME",model.getTRAIN_COURSE_NAME());  //培训课程名称
		params.put("TRAIN_TYPE",model.getTRAIN_TYPE());  //培训类型  
		params.put("FIXED_PSON_NUM",model.getFIXED_PSON_NUM());  //额定人数
		params.put("TRAIN_TIME_BEG",model.getTRAIN_TIME_BEG());  //培训时间从
		params.put("TRAIN_TIME_END",model.getTRAIN_TIME_END());  //培训时间到
		params.put("TRAIN_ADDR",model.getTRAIN_ADDR());  //培训地点 
		params.put("TRAIN_GOAL",model.getTRAIN_GOAL());  //培训目的
		params.put("TRAIN_CONTENT",model.getTRAIN_CONTENT());  //培训内容
		params.put("PIC_PATH", model.getPIC_PATH());//图片路径
		params.put("STATE",model.getSTATE());  //状态		
		params.put("REMARK",model.getREMARK());  //备注 			
		
								
		if (StringUtils.isEmpty(TRAIN_COURSE_ID)) {
			TRAIN_COURSE_ID= StringUtil.uuid32len();
			params.put("TRAIN_COURSE_ID", TRAIN_COURSE_ID);
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称            
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称  
            params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
        	params.put("STATE", BusinessConsts.UNCOMMIT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记         	
            txInsert(params);            
        } else {
            params.put("TRAIN_COURSE_ID", TRAIN_COURSE_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            txUpdateById(params);            
        }
		
		txInsertAtt(params);
		clearCaches(TRAIN_COURSE_ID);
        
        return TRAIN_COURSE_ID;
	}
	
	/**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {
        insert("Traincourse.insert", params);
        return true;
    }
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("Traincourse.updateById", params);
    }
    
    /**
     * 删除
     * @param map
     * @return
     */
	public boolean txDelete(Map<String,String> map) {	
		delete("Traincourse.delete", map);
		delete("Att.deleteAtt", map);
        return true;
	}
	
	
	/**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("TRAIN_COURSE_ID");
		String currAtt = params.get("PIC_PATH");//图片路径
		
		//查询是否存在
		String att_path = loadAtt(fromBillId);
		
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", fromBillId);
		attParams.put("ATT_PATH", currAtt);
		if(StringUtils.isEmpty(att_path)){	
			//insert Att 
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR") == null ? params.get("UPDATOR") : params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME") == null ? params.get("UPD_NAME") : params.get("CRE_NAME"));
			attParams.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			insert("Att.insertAtt", attParams);
		}else{
			if(StringUtils.isEmpty(currAtt)){
				//delete Att
				attParams.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
				delete("Att.deleteAtt", attParams);
				
			}else if (!currAtt.equals(att_path)){
				//update Att
				update("Att.updateAtt", attParams);	
			}
		}
		
        return true;
	}
}