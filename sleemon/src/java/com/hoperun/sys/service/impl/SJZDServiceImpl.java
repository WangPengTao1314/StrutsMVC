/**
  *@module 系统管理
  *@fuc 数据字典数据处理实现
  *@version 1.1
  *@author 张羽
*/

package com.hoperun.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.SJZDModel;
import com.hoperun.sys.model.SJZDMxModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.SJZDService;

// TODO: Auto-generated Javadoc
/**
 * 数据字典实现类.
 * 
 * @author zhang_yu
 */
public class SJZDServiceImpl extends BaseService implements SJZDService {

    /**
     * 分页查询
     * Description :.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    @Override
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("SJZD.pageQuery", "SJZD.pageCount", params, pageNo);
    }


    /**
     * 查询详细信息
     * Description :.
     * 
     * @param sjzdId the sjzd id
     * 
     * @return the map< string, string>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map <String, String> load(String sjzdId) {

        return (Map <String, String>) load("SJZD.loadById", sjzdId);
    }


    /**
     * 单条记录删除
     * Description :.
     * 
     * @param sjzdId the sjzd id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    @Override
    public boolean txDelete(String sjzdId, UserBean userBean) {

        Map <String, String> paramsq = new HashMap <String, String>();
        paramsq.put("SJZDID", sjzdId);
        paramsq.put("DATARECYCLEID", StringUtil.uuid32len());
        paramsq.put("DELETOR", userBean.getYHM());
        insert("SJZD.insertSJZD", paramsq);
        paramsq.put("DATARECYCLEID", StringUtil.uuid32len());
        insert("SJZD.insertSJZDMX", paramsq);
        delete("SJZD.deletemx", sjzdId);
        return delete("SJZD.delete", sjzdId);
    }


    /**
     * 主表及子表编辑 新增/修改。
     * Description :.
     * 
     * @param sjzdId the sjzd id
     * @param sjzdmxList the sjzdmx list
     * @param userBean the user bean
     * @param sjzdModel the sjzd model
     */
    @Override
    public void txEdit(String sjzdId, UserBean userBean, SJZDModel sjzdModel, List <SJZDMxModel> sjzdmxList) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("SJZDBH", sjzdModel.getSJZDBH());
        params.put("SJZDMC", sjzdModel.getSJZDMC());
        params.put("REMARK", sjzdModel.getREMARK());
        params.put("UPDATER", userBean.getXTYHID());
        params.put("UPDTIME", DateUtil.now());

        //如果lxllId为空，说明是新增记录
        if (StringUtils.isEmpty(sjzdId)) {
            sjzdId = sjzdModel.getSJZDBH();
            params.put("SJZDID", sjzdId);
            params.put("CREATER", userBean.getXTYHID());
            params.put("CRENAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
            insert(params);
        } else {
            params.put("SJZDID", sjzdId);
            sjzdModel.setSJZDID(sjzdId);
            updateById(params);
        }
        //子表信息编辑
        if (null != sjzdmxList && !sjzdmxList.isEmpty()) {
            txChildEdit(sjzdId, sjzdmxList, sjzdModel);
        }
    }


    /**
     * 根据主表Id查询子表记录
     * Description :.
     * 
     * @param sjzdId the sjzd id
     * 
     * @return the list< sjzd mx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SJZDMxModel> childQuery(String sjzdId) {

        Map params = new HashMap();
        params.put("SJZDID", sjzdId);
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("SJZDMX.query", params);
    }


    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * 
     * @param sjzdMxIds the sjzd mx ids
     * 
     * @return the list< sjzd mx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SJZDMxModel> loadChilds(String sjzdMxIds) {

        return findList("SJZDMX.loadByIds", sjzdMxIds);
    }


    /**
     * 数据字典明细页面编辑。.
     * 
     * @param sjzdId the sjzd id
     * @param modelList the model list
     * @param sjzdModel the sjzd model
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String sjzdId, List <SJZDMxModel> modelList, SJZDModel sjzdModel) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (SJZDMxModel model : modelList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("SJZDID", sjzdId);
            params.put("SJXMC", model.getSJXMC());
            params.put("SJSJZDMXID", "");
            params.put("SJXZ", model.getSJXZ());
            params.put("SJGL", model.getSJGL());
            params.put("KEYCODE", model.getKEYCODE());
            params.put("REMARK", model.getREMARK());//KEYCODE,SJZDMXID,SJZDID,SJXMC,SJXZ,SJSJZDMXID,SJGL,REMARK,CRETIME,UPDTIME,DELFLAG
            //如果没有领料明细ID的则是新增，有的是修改
            if (StringUtils.isEmpty(model.getSJZDMXID())) {
                params.put("SJZDMXID", StringUtil.uuid32len());
                addList.add(params);
            } else {

                params.put("SJZDMXID", model.getSJZDMXID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("SJZDMX.updateById", updateList);
        }
        if (!addList.isEmpty()) {
            try {
                this.batch4Update("SJZDMX.insert", addList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }


    /**
     * 批量删除.
     * 
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    @Override
    public void txBatch4DeleteChild(String sjzdmxids, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("sjzdmxids", sjzdmxids);
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("SJZDMX.insertSJZDMX", params);
        delete("SJZDMX.deleteByIds", sjzdmxids);
    }


    /**
     * Insert.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean insert(Map <String, String> params) {

        insert("SJZD.insert", params);
        return true;
    }


    /**
     * Update by id.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean updateById(Map <String, String> params) {

        return update("SJZD.updateById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.SJZDService#txUpdateById(java.util.Map)
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("SJZD.updateState", params);
    }


    /**
     * 校验是否有重复编号.
     * 
     * @param sjzdID the sjzd id
     * 
     * @return the id count
     */
    public int getIdCount(String SJZDBH) {

        return (Integer) this.load("SJZD.getIdCount", SJZDBH);
    }
}
