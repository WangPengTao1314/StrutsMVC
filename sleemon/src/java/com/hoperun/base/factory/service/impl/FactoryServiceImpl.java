/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：SCJDServiceImpl.java
 */
package com.hoperun.base.factory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.factory.model.FactoryModel;
import com.hoperun.base.factory.model.FactoryProductModel;
import com.hoperun.base.factory.service.FactoryService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class SCJDServiceImpl.
 * 
 * @module 系统管理
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public class FactoryServiceImpl extends BaseService implements FactoryService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
	@Override
	public IListPage pageQuery(Map params, int pageNo) {
		
		return this.pageQuery("FACTORY.pageQuery", "FACTORY.pageCount", params, pageNo);
	}

	/**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String param) {

        Map params = new HashMap();
        params.put("FACTORY_ID", param);
        return (Map) load("FACTORY.loadById", params);
    }
    
    /**
     * 新增.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("FACTORY.insert", params);
        return true;
    }

    /**
     * 编辑.
     * 
     * @param BMXXID the bMXXID
     * @param model the model
     * @param userBean the user bean
     */
    @Override
    public void txEdit(String FACTORY_ID, FactoryModel model, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();

        params.put("FACTORY_NO", model.getFACTORY_NO());//生产基地编号
        params.put("FACTORY_NAME", model.getFACTORY_NAME());//生产基地名称        
        params.put("PERSON_CON", model.getPERSON_CON());//联系人
        params.put("MOBILE_NO", model.getMOBILE_NO());//手机号码
        params.put("TEL", model.getTEL());//电话
        params.put("TAX", model.getTAX());//传真
        params.put("POST", model.getPOST());//邮政编号   
        params.put("ADDRESS", model.getADDRESS());//详细地址

        params.put("REMARK", "");//备注

        params.put("UPDATOR", userBean.getXTYHID());//更新人ID
        params.put("UPD_NAME", userBean.getXM());//更新人名称
  
        if (StringUtils.isEmpty(FACTORY_ID)) {
        	
            params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构ID
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
            params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
            params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
            params.put("STATE", "制定");//状态
           
            params.put("FACTORY_ID", StringUtil.uuid32len()); //生产基地ID
            txInsert(params);
            
        } else {
            params.put("FACTORY_ID", FACTORY_ID);//生产基地ID
            txUpdateById(params);
        }
    }
    /**
     * 删除.
     * 
     * @param BMXXID the bMXXID
     * @param model the model
     * @param userBean the user bean
     */
    @Override
    public boolean txDelete(String FACTORY_ID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("FACTORY_ID", FACTORY_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人ID
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        
        params.put("DEL_FLAG","1");
        
        return txUpdateById(params);
    }

    /**
     * 修改.
     * 
     * @param params the params
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean txUpdateById(Map params) {
		
		return update("FACTORY.updateById", params);
	}

	 /**
     * 判断生产工厂编号是否存在.
     * 
     * @param FACTORY_ID the FACTORY_ID
     * 
     * @return the FACTORY exits
     */
    @SuppressWarnings("unchecked")
	public int getScjdExits(String FACTORY_NO) {

    	Map params = new HashMap();
    	params.put("FACTORY_NO", FACTORY_NO);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	
        return queryForInt("FACTORY.getExits", params);
    }
    
    /**
     * 生产货品类型->列表查询
     * 
     * @param FACTORY_ID the FACTORY_ID
     * @return the list<FactoryProductModel>
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<FactoryProductModel> productQuery(String FACTORY_ID) {
		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FACTORY_ID", FACTORY_ID);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        
        return this.findList("FACTORY_PRD.query", params);
	}
	
	/**
     * 生产货品类型->批量加载表信息
     * 
     * @param FACTORY_PRD_IDS the FACTORY_PRD_IDS
     * @return the list<FactoryProductModel>
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<FactoryProductModel> loadProducts(String FACTORY_PRD_IDS) {
		
		 return findList("FACTORY_PRD.loadByIds", FACTORY_PRD_IDS);
	}

	/**
     * 生产货品类型->删除
     * 
     * @param updateList the updateList
     */
	@Override
	public void txProductDelete(List<Map<String, String>> updateList) {
	
		if (!updateList.isEmpty()) {
			this.batch4Update("FACTORY_PRD.updateById", updateList);
        }
	
	}

	/**
     * 生产货品类型->新增/修改
     * 
     * @param FACTORY_ID the FACTORY_ID
     * @param modelList the model list
     */
	@Override
	public boolean txProductEdit(String FACTORY_ID, List<FactoryProductModel> modelList) {

		 //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        
        for (FactoryProductModel model : modelList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("FACTORY_ID", FACTORY_ID);
            params.put("PRD_ID", model.getPRD_ID());
            params.put("PRD_NO", model.getPRD_NO());
            params.put("PRD_NAME", model.getPRD_NAME());
            params.put("BRAND", model.getBRAND());

            //如果没有领料明细ID的则是新增，有的是修改
            if (StringUtils.isEmpty(model.getFACTORY_PRD_ID())) {
                params.put("FACTORY_PRD_ID", StringUtil.uuid32len());
                addList.add(params);
                
            } else {
                params.put("FACTORY_PRD_ID", model.getFACTORY_PRD_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("FACTORY_PRD.updateById", updateList);
        }
        if (!addList.isEmpty()) {
            try {
                this.batch4Update("FACTORY_PRD.insert", addList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
	}

}
