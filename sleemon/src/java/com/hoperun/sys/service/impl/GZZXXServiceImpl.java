/**
 * 项目名称：平台
 * 系统名：权限管理
 * 文件名：GZZXXServiceImpl.java
 */
package com.hoperun.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.GZZCYBean;
import com.hoperun.sys.model.GZZXXBean;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.GZZXXService;

// TODO: Auto-generated Javadoc
/**
 * The Class GZZXXServiceImpl.
 * 
 * @module 系统管理
 * @func 工作组信息
 * @version 1.1
 * @author 吴亚林
 */
public class GZZXXServiceImpl extends BaseService implements GZZXXService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    @SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo) {

        return this.pageQuery("GZZXX.pageQuery", "GZZXX.pageCount", params, pageNo);
    }


    /**
     * 新增.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    @SuppressWarnings("unchecked")
    public boolean txInsert(Map params) {

        insert("GZZXX.insert", params);
        return true;
    }


    /**
     * 修改.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    @SuppressWarnings("unchecked")
    public boolean txUpdateById(Map params) {

        return update("GZZXX.updateById", params);
    }


    /**
     * 删除.
     * 
     * @param GZZXXID the gZZXXID
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    @SuppressWarnings("unchecked")
    public boolean txDelete(String GZZXXID, UserBean userBean) {

        update("CZZCY.deleteById", GZZXXID);
        Map <String, String> paramsq = new HashMap <String, String>();
        paramsq.put("GZZXXID", GZZXXID);
        paramsq.put("DATARECYCLEID", StringUtil.uuid32len());
        paramsq.put("DELETOR", userBean.getYHM());
        insert("GZZXX.insertGZZ", paramsq);
        paramsq.put("DATARECYCLEID", StringUtil.uuid32len());
        insert("GZZXX.insertGZZMX", paramsq);
        delete("GZZXX.deletemx", GZZXXID);
        return delete("GZZXX.delete", GZZXXID);
    }


    /**
     * 加载.
     * 
     * @param GZZXXID the gZZXXID
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String GZZXXID) {

        Map params = new HashMap();
        params.put("GZZXXID", GZZXXID);
        return (Map) load("GZZXX.loadById", params);
    }


    /**
     * 子表查询.
     * 
     * @param GZZXXID the gZZXXID
     * 
     * @return the list< gzzcy bean>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List <GZZCYBean> childQuery(String GZZXXID) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("GZZXXID", GZZXXID);
        //只查询0的记录。1为删除。0为正常
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);

        return this.findList("CZZCY.loadById", params);
    }


    /**
     * 工作组成员删除.
     * 
     * @param GZZCYID the gZZCYID
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String GZZCYID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("GZZCYID", GZZCYID);
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("CZZCY.insertMX", params);
        delete("CZZCY.delete", GZZCYID);
    }


    /**
     * 主表编辑保存.
     * 
     * @param GZZXXID the gZZXXID
     * @param bean the bean
     * @param userBean the user bean
     * @param list the list
     */
    @Override
    public void txEdit(String GZZXXID, GZZXXBean bean, UserBean userBean, List <GZZCYBean> list) {

        Map <String, String> params = new TreeMap <String, String>();
        //params.put("GZZBH", bean.getGZZBH());
        params.put("GZZMC", bean.getGZZMC());

        if (StringUtils.isEmpty(bean.getGZZSM())) {
            params.put("GZZSM", "");
        } else {
            params.put("GZZSM", bean.getGZZSM());
        }

        if (StringUtils.isEmpty(GZZXXID)) {
            //GZZXXID = (String) this.load("GZZXX.getXXID", "");//工作组信息ID
            params.put("GZZXXID", bean.getGZZBH());

            params.put("GZZZT", BusinessConsts.JC_STATE_DEFAULT);
            txInsert(params);
        } else {
            params.put("GZZXXID", GZZXXID);
            params.put("GZZZT", bean.getGZZZT());
            txUpdateById(params);
        }
        // 子表
        if(StringUtils.isEmpty(GZZXXID)){
        	GZZXXID=bean.getGZZBH();
        }
        if (null != list && !list.isEmpty()) {
            txChildEdit(GZZXXID, list, userBean);
        }
    }


    /**
     * 子表编辑保存.
     * 
     * @param GZZXXID the gZZXXID
     * @param list the list
     * @param userBean the user bean
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String GZZXXID, List <GZZCYBean> list, UserBean userBean) {

        // 新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        // 修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();

        for (GZZCYBean bean : list) {
            Map <String, String> params = new TreeMap <String, String>();
            params.put("XTYHID", bean.getXTYHID());
            params.put("GZZXXID", GZZXXID);
            params.put("JGXXID", bean.getJGXXID());
            params.put("BMXXID", bean.getBMXXID());
            params.put("JGMC", bean.getJGMC());
            params.put("BMMC", bean.getBMMC());
            params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);

            if (StringUtils.isEmpty(bean.getGZZCYID())) {
                params.put("GZZCYID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("GZZCYID", bean.getGZZCYID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("CZZCY.updateById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("CZZCY.insert", addList);
        }
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.GZZXXService#loadChilds(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List <GZZCYBean> loadChilds(String GZZCYID) {

        return findList("CZZCY.loadByIds", GZZCYID);
    }


    /**
     * 判断工作组编号是否已经存在.
     * 
     * @param GZZXXBH the gZZXXBH
     * 
     * @return the gzzxx exits
     */
    public int getGzzxxExits(String GZZXXBH) {

        return queryForInt("GZZXX.getExits", GZZXXBH);
    }


    /**
     * 判断明细是否重复.
     * 
     * @param params the params
     * 
     * @return the list< gzzcy bean>
     */
    @SuppressWarnings("unchecked")
    public List <GZZCYBean> checkYHBH(Map params) {

        return findList("CZZCY.checkYHBH", params);
    }
}
