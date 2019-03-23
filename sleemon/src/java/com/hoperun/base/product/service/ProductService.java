/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductService.java
 */
package com.hoperun.base.product.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.product.model.PrdSpclTechModel;
import com.hoperun.base.product.model.PrdSuitModel;
import com.hoperun.base.product.model.PrdUnitModel;
import com.hoperun.base.product.model.ProductModel;
import com.hoperun.base.product.model.ProductTree;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProductService.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */

public interface ProductService extends IBaseService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);

    /**
     * 更新记录
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params);
    /**
     * 删除记录
     * Description :.
     * 
     * @param Zone_Id the XZQH id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params);


    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);



    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param Zone_Id the XZQH id
     * @param XZQHModel the XZQH model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String PRD_ID, ProductModel productModel, UserBean userBean);
    
    public String txPartAddEdit(String PRD_SPCL_TECH_ID, PrdSpclTechModel prdSpclTechModel, UserBean userBean);
    
    /**
     * * 
     * @param PRD_ID the PRD_ID
     * @return the list
     */
    public List <PrdSpclTechModel> prdSpclTechQuery(String PRD_ID,String param);

    /**
     * 显示树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <ProductTree> getTree() throws Exception;




    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
    /**
     * * 根据主表Id查询货品计量单位子表记录
     * @param PRD_ID the PRD_ID
     * @return the list
     */
    public List <PrdUnitModel> unitQuery(String PRD_UNIT_ID);
    /**
     * * 根据主表Id查询货品套子表记录
     * @param PRD_ID the PRD_ID
     * @return the list
     */
    public List <PrdSuitModel> suitQuery(String PRD_UNIT_ID);
    /**
     * * 根据子表Id批量加载子表信息货品计量单位
     * @param PRD_UNIT_IDs the PRD_UNIT_IDs
     * 
     * @return the list
     */
    public List <PrdUnitModel> loadUnits(Map <String, String> params) ;
    /**
     * * 根据子表Id批量加载子表信息货品套
     * @param PRD_UNIT_IDs the PRD_UNIT_IDs
     * 
     * @return the list
     */
    public List <PrdSuitModel> loadSuits(Map <String, String> params) ;
    /**
     * * 子表记录编辑：新增/修改货品计量单位
     * @param PRD_ID the PRD_ID
     * @param modelList the model list
     * 
     * @return true, if tx prd_unit edit
     */
    public boolean txUnitEdit(String PRD_ID, List <PrdUnitModel> modelList);
    /**
     * * 子表记录编辑：新增/修改货品套
     * @param PRD_ID the PRD_ID
     * @param modelList the model list
     * 
     * @return true, if tx prd_suit edit
     */
    public boolean txSuitEdit(String PRD_ID, List <PrdSuitModel> modelList);
    /**
     * * 子表的批量删除货品计量单位
     * @param PRD_UNIT_IDS the PRD_UNIT_IDS
     */
    public void txBatch4DeleteUnit(String PRD_UNIT_IDS);
    /**
     * * 子表的批量删除货品套
     * @param PRD_UNIT_IDS the PRD_UNIT_IDS
     */
    public void txBatch4DeleteSuit(String PRD_UNIT_IDS);
    /**
     * 判断编号是否重复
     * Gets the count PRD_NO.
     * 
     * @param PRD_NO the PRD_NO
     * 
     * @return the count PRD_NO
     */
    public int getCountPRD_NO(String PRD_NO);
    
    /**
     * 查询子表货品计量单位编号是否重复
     * Gets the count PRD_NO.
     * 
     * @param PRD_NO the PRD_NO
     * 
     * @return the count PRD_NO
     */
    public int getCountUnitMEAS_UNIT_NO(String MEAS_UNIT_NO,String PRD_ID);
    /**
     * 查询子表货品套编号是否重复
     * Gets the count PRD_NO.
     * 
     * @param MEAS_UNIT_NO the MEAS_UNIT_NO
     * 
     * @return the count MEAS_UNIT_NO
     */
    public int getCountSuitPRD_NO(String MEAS_UNIT_NO,String PRD_ID);
    /**
     * 获取所有品牌名称
     * @return
     */
    public List<String> getBrand(String type);
    
    public Map<String,String> prdspctechQuery(String PRD_SPCL_TECH_ID);
    
    public void deletePrdSpclTechById(String PRD_SPCL_TECH_ID);
    public List queryPrdExp(Map<String,String> map);
    
    /**
     * 导入
     * @param list
     * @param userBean
     */
    public void txImportExcel(List list,UserBean userBean,String DRPFLAG);
    /**
     * 更新是否扫码
     * 0 表示  '是 ' 1 表示  '否'
     */
    public void txUpdateScan(Map<String,String> params);
}

