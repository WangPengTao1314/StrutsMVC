/**

* 项目名称：平台

* 系统名：财务基础数据

* 文件名：ZTWHService.java
*/
package com.hoperun.sys.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.ZTWHModel;
import com.hoperun.sys.model.ZTWHTree;

// TODO: Auto-generated Javadoc
/**
 * The Interface ZTWHService.
 * 
 * @module 财务管理
 * @fuc 帐套信息维护
 * @version 1.1
 * @author 唐赟
 */
public interface ZTWHService extends IBaseService {

    /**
     * 帐套列表信息.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);


    /**
     * 点击树时获得的列表.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery1(Map <String, String> params, int pageNo);


    /**
     * 获得一条数据，根据ID.
     * 
     * @param ztxxID the ztxx id
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String ztxxID);

    /**
     * 获得一条数据，根据ID.
     * 
     * @param ztxxID the ztxx id
     * 
     * @return the map< string, string>
     */
  public Map <String, String> loadOne(String ztxxID);

    /**
     * 更新.
     * 
     * @param params the params
     * 
     * @return true, if update by id
     */
    public boolean updateById(Map <String, String> params);


    /**
     * 插入.
     * 
     * @param params the params
     * 
     * @return true, if insert record
     */
    public boolean insertRecord(Map <String, String> params);


    /**
     * 得到所有帐套编号.
     * 
     * @return the BH list
     */
    public List <ZTWHModel> getBHList();


    /**
     * 更新状态.
     * 
     * @param params the params
     */
    public void updateZTStatus(Map <String, String> params);


    /**
     * 删除.
     * 
     * @param ztxxid the ztxxid
     */
    public void deleteById(Map <String, String> ztxxid);


    /**
     * 得到树信息.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <ZTWHTree> getTree() throws Exception;


    /**
     * 获得上级帐套有几条.
     * 
     * @param ztxxid the ztxxid
     * 
     * @return the count record
     */
    public int getCountRecord(String ztxxid);
    
    /**
     * Query sj for int.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean querySjForInt(Map<String,String> params);
    
    /**
     * 加载.
     * 
     * @param SJZT the sJZT
     * 
     * @return true, if load jgzt
     */
    public boolean loadZTZT(String SJZT);
}
