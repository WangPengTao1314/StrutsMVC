/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：GrgzjhServiceImpl.java
 */
package com.hoperun.base.grgzjh.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.base.grgzjh.model.GrgzjhModel;
import com.hoperun.base.grgzjh.model.GrgzjhModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.base.grgzjh.service.GrgzjhService;
/**
 * The Class GrgzjhServiceImpl.
 * @module 系统管理
 * @func 个人工作计划
 * @version 1.0
 * @author 吴军
 */
public class GrgzjhServiceImpl extends BaseService implements GrgzjhService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Grgzjh.pageQuery", "Grgzjh.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Grgzjh.insert", params);
		return true;
	}
	

	/**
	 * 删除数据
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Grgzjh.delete", params);
		 //删除子表 
		 return update("Grgzjh.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Grgzjh.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PER_WORK_PLAN_ID
	 * @param GrgzjhModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public String txEdit(String PER_WORK_PLAN_ID, GrgzjhModel model,List<GrgzjhModelChld> chldList, UserBean userBean) {
		
		Map<String,String> params = new HashMap<String,String>();
	    params.put("PLAN_PSON_ID",model.getPLAN_PSON_ID());
	    params.put("PLAN_DAY",model.getPLAN_DAY());
	    params.put("PLAN_MONTH",model.getPLAN_MONTH());
	    params.put("PLAN_YEAR",model.getPLAN_YEAR());
	    params.put("PLAN_PSON_NO",userBean.getYHBH());
	    params.put("PLAN_PSON_NAME",userBean.getXM());
	    params.put("REMARK",model.getREMARK());
	    //String JHID = "";
	    List<HashMap<String, String>> list = this.loadUserPlanId(model.getPLAN_PSON_ID(), model.getPLAN_YEAR(), model.getPLAN_MONTH());
	    if(list.size()>0){
	    	HashMap<String, String> temp = list.get(0);
	    	PER_WORK_PLAN_ID = temp.get("PER_WORK_PLAN_ID");
	    }
		if(StringUtil.isEmpty(PER_WORK_PLAN_ID)){
			PER_WORK_PLAN_ID = StringUtil.uuid32len();
			params.put("PER_WORK_PLAN_ID", PER_WORK_PLAN_ID);
			params.put("PER_WORK_PLAN_NO",LogicUtil.getBmgz("ERP_PER_WORK_PLAN_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    params.put("STATE", "未提交");
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("PER_WORK_PLAN_ID", PER_WORK_PLAN_ID);
			txUpdateById(params);
			clearCaches(PER_WORK_PLAN_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PER_WORK_PLAN_ID, chldList, userBean);
		}
	    return PER_WORK_PLAN_ID;
	}
	
	/**
	 * 获取详细信息
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Grgzjh.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PER_WORK_PLAN_ID the PER_WORK_PLAN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String PER_WORK_PLAN_ID, List<GrgzjhModelChld> chldList, UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (GrgzjhModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PLAN_YEAR",model.getPLAN_YEAR());
		    params.put("PLAN_MONTH",model.getPLAN_MONTH());
		    params.put("PLAN_DAY",model.getPLAN_DAY());   
            params.put("PER_WORK_PLAN_ID",PER_WORK_PLAN_ID); 
		    params.put("PLAN_PSON_ID",userBean.getRYXXID());
		    params.put("PLAN_CONTENT",model.getPLAN_CONTENT());
		    params.put("IS_IMPT_FLAG",model.getIS_IMPT_FLAG());
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPER_WORK_PLAN_DTL_ID())) {
                params.put("PER_WORK_PLAN_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("PER_WORK_PLAN_DTL_ID", model.getPER_WORK_PLAN_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Grgzjh.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Grgzjh.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PER_WORK_PLAN_ID the PER_WORK_PLAN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrgzjhModelChld> childQuery(String PER_WORK_PLAN_ID) {
        Map params = new HashMap();
        params.put("PER_WORK_PLAN_ID", PER_WORK_PLAN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Grgzjh.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PER_WORK_PLAN_DTL_IDs the PER_WORK_PLAN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrgzjhModelChld> loadChilds(Map <String, String> params) {
       return findList("Grgzjh.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * @param PER_WORK_PLAN_DTL_IDs the PER_WORK_PLAN_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String PER_WORK_PLAN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PER_WORK_PLAN_DTL_IDs", PER_WORK_PLAN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Grgzjh.deleteChldByIds", params);
    }
    
    /**
     * 个人工作计划明细查询
     * @param ryxxid
     * @param currentDate
     * @return
     */
    public List<HashMap<String, String>> loadEventsForCurrentUser(String ryxxid, String currentDate) {
        HashMap<String, String> paras = new HashMap<String, String>(3);
        paras.put("PLAN_PSON_ID", ryxxid);
        paras.put("PLAN_YEAR",currentDate.split("-")[0]);
        paras.put("PLAN_MONTH",currentDate.split("-")[1]);
        return this.findList("Grgzjh.loadEventsForCurrentUser",paras);
    }
    
    /**
     * 根据年、月、人员ID查询个人工作计划
     * @param ryxxid 人员ID
     * @param PLAN_YEAR	 计划年份
     * @param PLAN_MONTH	 计划月份
     * @return
     */
    public List<HashMap<String, String>> loadUserPlanId(String ryxxid, String PLAN_YEAR, String PLAN_MONTH) {
        HashMap<String, String> paras = new HashMap<String, String>(3);
        paras.put("PLAN_PSON_ID", ryxxid);
        paras.put("PLAN_YEAR",PLAN_YEAR);
        paras.put("PLAN_MONTH",PLAN_MONTH);
        return this.findList("Grgzjh.loadUserPlanId",paras);
    }
    
	/**
	 * 删除子表明细信息
	 * @param parameter
	 */
    public void deleteChild(HashMap<String, String> parameter) {
        this.delete("Grgzjh.deleteChild",parameter);
    }
    
    public void update(Map<String,String> params){
    	txUpdateById(params);
    }
}