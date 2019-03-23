/**

 * 项目名称：平台

 * 系统名：基础数据

 * 文件名：XTYHServiceImpl.java
 */
package com.hoperun.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.product.model.ProductUserModel;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.XTSQModel;
import com.hoperun.sys.model.XTYHBean;
import com.hoperun.sys.service.XTYHService;

// TODO: Auto-generated Javadoc
/**
 * The Class XTYHServiceImpl.
 * 
 * @module 系统管理
 * @fuc 系统用户
 * @version 1.1
 * @author 唐赟
 */
public class XTYHServiceImpl extends BaseService implements XTYHService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("XTYH.pageQueryy", "XTYH.pageCount", params, pageNo);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#insert(java.util.Map)
     */
    public boolean insert(Map <String, String> params) {

        insert("XTYH.insert", params);
        //插入机构分管明细表
        insert("XTYH.insertJgfgMx", params);
        //插入部门分管明细表
        insert("XTYH.insertBmfgMx", params);
        //插入帐套分管明细表
        insert("XTYH.insertZtfgMx", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#updateById(java.util.Map)
     */
    public boolean updateById(Map <String, String> params) {

        return update("XTYH.updateById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#txdelete(java.lang.String, com.hoperun.sys.model.UserBean)
     */
    public boolean txdelete(String xtyhID, UserBean userBean) {

        update("XTSQ.delete", xtyhID);
        Map <String, String> paramsq = new HashMap <String, String>();
        paramsq.put("XTYHID", xtyhID);
        paramsq.put("DATARECYCLEID", StringUtil.uuid32len());
        paramsq.put("DELETOR", userBean.getYHM());
        insert("XTYH.insertXTYH", paramsq);
        return delete("XTYH.updateOne", xtyhID);
    }


    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    @SuppressWarnings("unchecked")
    public Map <String, String> load(String param) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("XTYHID", param);
        return (Map <String, String>) load("XTYH.loadById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#query(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public Map <String, String> query(Map <String, String> params) {

        return (Map <String, String>) load("XTYHH.queryy", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#updateUserStatus(java.util.Map)
     */
    public void updateUserStatus(Map <String, String> params) {

        update("XTYH.updateUserStatusById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#updatePassword(java.util.Map)
     */
    public void updatePassword(Map <String, String> params) {

        update("XTYH.updatePassword", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#selectYhbh()
     */
    @SuppressWarnings("unchecked")
    public List <XTYHBean> selectYhbh() {

        return (List <XTYHBean>) findList("XTYH.selectAllYhbh", "");
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTYHService#childQuery(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List <XTSQModel> childQuery(String xtyhid) {

        Map <String, String> map = new HashMap <String, String>();
        map.put("XTYHID", xtyhid);

        return this.findList("XTSQ.loadById", map);
    }


    /**
     * 取用户的机构分管信息.
     * 
     * @param xtyhid the xtyhid
     * 
     * @return the list< map< string, string>>
     */
    public List <Map <String, String>> queryJgfgMxByXtyhid(String xtyhid) {

        return this.findList("XTYH.queryJgfgMxByXtyhid", xtyhid);
    }


    /**
     * 取用户的机构分管信息.
     * 
     * @param xtyhid the xtyhid
     * 
     * @return the list< map< string, string>>
     */
    public List <Map <String, String>> queryBmfgMxByXtyhid(String xtyhid) {

        return this.findList("XTYH.queryBmfgMxByXtyhid", xtyhid);
    }


    /**
     * 取用户的机构分管信息.
     * 
     * @param xtyhid the xtyhid
     * 
     * @return the list< map< string, string>>
     */
    public List <Map <String, String>> queryZtfgMxByXtyhid(String xtyhid) {

        return this.findList("XTYH.queryZtfgMxByXtyhid", xtyhid);
    }


    /**
     * 插入机构分管明细表.
     * 
     * @param arrJgxxid the arr jgxxid
     * @param xtyhid the xtyhid
     * 
     * @return true, if tx insert jgfg mx
     */
    public boolean txInsertJgfgMx(String[] arrJgxxid, String xtyhid) {

        Map <String, String> map = new HashMap();
        for (int i = 0; i < arrJgxxid.length; i++) {
            map.put("XTYHJGFGID", StringUtil.uuid32len());
            map.put("XTYHID", xtyhid);
            map.put("JGXXID", arrJgxxid[i]);
            this.insert("XTYH.insertJgfgMx", map);
        }
        return true;
    }


    /**
     * 插入部门分管明细表.
     * 
     * @param arrBmxxid the arr bmxxid
     * @param xtyhid the xtyhid
     * 
     * @return true, if tx insert bmfg mx
     */
    public boolean txInsertBmfgMx(String[] arrBmxxid, String xtyhid) {

        Map <String, String> map = new HashMap();
        for (int i = 0; i < arrBmxxid.length; i++) {
            map.put("XTYHBMFGID", StringUtil.uuid32len());
            map.put("XTYHID", xtyhid);
            map.put("BMXXID", arrBmxxid[i]);
            this.insert("XTYH.insertBmfgMx", map);
        }
        return true;
    }


    /**
     * 插入帐套分管明细表.
     * 
     * @param arrZtxxid the arr ztxxid
     * @param xtyhid the xtyhid
     * 
     * @return true, if tx insert ztfg mx
     */
    public boolean txInsertZtfgMx(String[] arrZtxxid, String xtyhid) {

        Map <String, String> map = new HashMap();
        for (int i = 0; i < arrZtxxid.length; i++) {
            map.put("XTYHZTDZID", StringUtil.uuid32len());
            map.put("XTYHID", xtyhid);
            map.put("ZTXXID", arrZtxxid[i]);
            this.insert("XTYH.insertZtfgMx", map);
        }
        return true;
    }


    /**
     * 删除机构分管明细表.
     * 
     * @param ids the ids
     * 
     * @return true, if tx delete jgfg mx
     */
    public boolean txDeleteJgfgMx(String ids) {

        return this.delete("XTYH.deleteJgfgMx", ids);
    }


    /**
     * 删除部门分管明细表.
     * 
     * @param ids the ids
     * 
     * @return true, if tx delete bmfg mx
     */
    public boolean txDeleteBmfgMx(String ids) {

        return this.delete("XTYH.deleteBmfgMx", ids);
    }


    /**
     * 删除帐套分管明细表.
     * 
     * @param ids the ids
     * 
     * @return true, if tx delete ztfg mx
     */
    public boolean txDeleteZtfgMx(String ids) {

        return this.delete("XTYH.deleteZtfgMx", ids);
    }


    /**
     * 得到用户编号.
     * 
     * @return the yhbh
     */
    public String getYhbh() {

        return (String) load("XTYH.getXTYHBH", "");
    }
    
     /* (non-Javadoc)
      * @see com.hoperun.sys.service.XTYHService#txDoBatIns(java.lang.String, java.util.List)
      */
     public void  txDoBatIns(String statMemt,List dataList)
    {
    	this.batch4Update(statMemt, dataList, 20000);
    }
    
     /**
      * 得到人员信息ID.
      * 
      * @return the ryxxid
      */
     public String getRyxxid(String XTYHID) {

         return (String) load("XTYH.getRyxxidByXtyhid", XTYHID);
     }
     
     /* (non-Javadoc)
      * @see com.hoperun.sys.service.XTYHService#insert(java.util.Map)
      */
     public boolean deleteMX(Map <String, String> params) {

         //删除机构分管明细表
         this.delete("XTYH.deleteJgfg", params);
         //删除部门分管明细表
         this.delete("XTYH.deleteBmfg", params);
         //删除帐套分管明细表
         this.delete("XTYH.deleteZtfg", params);
         return true;
     }
     
     /* (non-Javadoc)
      * @see com.hoperun.sys.service.XTYHService#insert(java.util.Map)
      */
     public boolean insertMX(Map <String, String> params) {

         //插入机构分管明细表
         insert("XTYH.insertJgfgMx", params);
         //插入部门分管明细表
         insert("XTYH.insertBmfgMx", params);
         //插入帐套分管明细表
         insert("XTYH.insertZtfgMx", params);
         return true;
     }
     
     
     public List <ProductUserModel> loadUserChargByIDS(String USER_CHARG_PRD_IDS){
     	return findList("XTYH.loadUserByIDS",USER_CHARG_PRD_IDS);
     }
     public void txBatch4DeleteUser(String USER_CHARG_PRD_IDS){
     	this.delete("XTYH.delUserByIDS", USER_CHARG_PRD_IDS);
     }
     public int checkPRD(String PRD_IDS,String XTYHID){
     	Map<String,String> map=new HashMap<String, String>();
     	map.put("PRD_IDS", PRD_IDS);
     	map.put("XTYHID", XTYHID);
     	Object count=this.load("XTYH.checkPRD", map);
     	if(null==count){
     		count="0";
     	}
     	return StringUtil.getInteger(count);
     }
     public void txChldEdit(List <ProductUserModel> modelList,String XTYHID){
     	List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
     	List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
     	for (int i = 0; i < modelList.size(); i++) {
     		ProductUserModel model=modelList.get(i);
     		Map<String,String> map=new HashMap<String, String>();
     		map.put("XTYHID", XTYHID);
     		map.put("PRD_ID", model.getPRD_ID());
     		if(StringUtil.isEmpty(model.getUSER_CHARG_PRD_ID())){
     			map.put("USER_CHARG_PRD_ID", StringUtil.uuid32len());
     			addList.add(map);
     		}else{
     			map.put("USER_CHARG_PRD_ID", model.getUSER_CHARG_PRD_ID());
     			upList.add(map);
     		}
 		}
     	if(!addList.isEmpty()){
     		this.batch4Update("XTYH.addCharg", addList);
     	}
     	if(!upList.isEmpty()){
     		this.batch4Update("XTYH.upCharg", upList);
     	}
     }
     public List<ProductUserModel> userQueryById(String XTYHID){
    	 return  this.findList("XTYH.queryChld",XTYHID);
     }
     
     /**
      * 设置用户分管所有渠道
      * @param params
      */
     public void txUpdateUserChrgChann(Map<String, String> params){
    	 this.insert("XTYH.upUserChannCharg", params);//
    	 params.put("IS_FG_ALL_CHANN", BusinessConsts.INTEGER_1);
    	 update("XTYH.upXtyhChargFlag",params);
     }
     /**
      * 设置用户分管所有渠道
      * @param params
      */
     public void txDeleteUserChrgChann(Map<String, String> params){
    	 this.delete("XTYH.delUserChannCharg", params);
    	 params.put("IS_FG_ALL_CHANN", BusinessConsts.INTEGER_0);
    	 update("XTYH.upXtyhChargFlag",params);
     }
     
     /**
      * 用户列表
      * @param params
      * @return
      */
     public List queryUserList(Map<String,String>params){
    	 return this.findList("XTYH.queryUserList", params);
     }
     
     /**
      * 编辑上下级关系
      * @param params
      */
     public void txEditStepUser(Map<String, String> params){
    	 String XTYHIDS = params.get("XTYHIDS");
    	 String[] arry = XTYHIDS.split(",");
    	 List<Map<String,String>> addList = new ArrayList<Map<String,String>>();
    	 //先删除之前分配的上下级关系
    	 XTYHIDS = "'"+XTYHIDS.replace(",", "','")+"'";
    	 params.put("USER_IDS", XTYHIDS);
    	 this.delete("XTYH.deleteStepUser", params);
    	 
    	 for(int i=0;i<arry.length;i++){
    		 Map<String, String> model = new HashMap<String,String>(); 
    		 model.put("USER_REL_ID", StringUtil.uuid32len());
    		 model.putAll(params);
    		 model.put("USER_ID", arry[i]);
    		 addList.add(model);
    	 }
    	 
    	 //在新增
    	 if(!addList.isEmpty()){
     		this.batch4Update("XTYH.insertStepUser", addList);
     	 }
    	
     }
     
     
     /**
      * 删除上下级关系
      * @param params
      */
     public void txDeleteStepUser(Map<String, String> params){
    	 //先删除之前分配的上下级关系
    	 String XTYHIDS = params.get("XTYHIDS");
    	 XTYHIDS = "'"+XTYHIDS.replace(",", "','")+"'";
    	 params.put("USER_IDS", XTYHIDS);
    	 this.delete("XTYH.deleteStepUser", params);
     }
     
}
