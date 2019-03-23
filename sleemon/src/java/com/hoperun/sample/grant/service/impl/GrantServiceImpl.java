/**
 * prjName:供应链_贵人鸟
 * ucName:不良通知单
 * fileName:GrantServiceImpl
*/
package com.hoperun.sample.grant.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.sample.grant.model.GrantModel;
import com.hoperun.sample.grant.model.GrantModelChld;
import com.hoperun.sample.grant.model.GrantModelGchld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sample.grant.service.GrantService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zhuxw
 * *@createdate 2013-05-15 10:35:31
 */
public class GrantServiceImpl extends BaseService implements GrantService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Grant.pageQuery", "Grant.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Grant.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param CPBLTZDID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Grant.delete", params);
         //删除子子表
         update("Grant.delGchldByGrantId", params);
		 //删除子表 
		 return update("Grant.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Grant.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param CPBLTZDID
	 * @param GrantModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String CPBLTZDID, GrantModel model,List<GrantModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("WLDWXXID",model.getWLDWXXID());
		    params.put("CPBLTZDBH",model.getCPBLTZDBH());
		    params.put("LCH",model.getLCH());
		    params.put("WLDWBH",model.getWLDWBH());
		    params.put("WLDWMC",model.getWLDWMC());
		    params.put("THSL",model.getTHSL());
		if(StringUtil.isEmpty(CPBLTZDID)){
			CPBLTZDID= StringUtil.uuid32len();
			params.put("CPBLTZDID", CPBLTZDID);
		    params.put("CRENAME",userBean.getXM());
		    params.put("CREATER",userBean.getXTYHID());
		    params.put("UPDNAME",userBean.getXM());
		    params.put("UPDATER",userBean.getXTYHID());
		    params.put("BMXXID",userBean.getBMXXID());
		    params.put("BMMC",userBean.getBMMC());
		    params.put("JGXXID",userBean.getJGXXID());
		    params.put("JGMC",userBean.getJGMC());
		    params.put("ZTXXID",userBean.getLoginZTXXID());
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPDNAME",userBean.getXM());
		    params.put("UPDATER",userBean.getXTYHID());
		    params.put("UPDTIME","sysdate");
			params.put("CPBLTZDID", CPBLTZDID);
			txUpdateById(params);
			clearCaches(CPBLTZDID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(CPBLTZDID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param CPBLTZDID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Grant.loadById", param);
	}
	
    /**
     * * 明细数据编辑
     * 
     * @param CPBLTZDID the CPBLTZDID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String CPBLTZDID, List<GrantModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (GrantModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("CPZJXMMC",model.getCPZJXMMC());
		    params.put("ZJXMLB",model.getZJXMLB());
		    params.put("YYBZ",model.getYYBZ());
		    params.put("JYCS",model.getJYCS());
		    params.put("HGBZ",model.getHGBZ());
            params.put("CPBLTZDID",CPBLTZDID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getCPBLTZDMXID())) {
                params.put("CPBLTZDMXID", StringUtil.uuid32len());
		        params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("CPBLTZDMXID", model.getCPBLTZDMXID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Grant.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Grant.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param CPBLTZDID the CPBLTZDID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrantModelChld> childQuery(String CPBLTZDID) {
        Map params = new HashMap();
        params.put("CPBLTZDID", CPBLTZDID);
        //只查询0的记录。1为删除。0为正常
		params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Grant.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param CPBLTZDMXIDs the CPBLTZDMXIDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrantModelChld> loadChilds(Map <String, String> params) {
       return findList("Grant.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param CPBLTZDMXIDs the CPBLTZDMXIDs
     */
    @Override
    public void txBatch4DeleteChild(String CPBLTZDMXIDs) {
	   Map params = new HashMap();
	   params.put("CPBLTZDMXIDS", CPBLTZDMXIDs);
	   params.put("DELFLAG", BusinessConsts.DEL_FLAG_DROP);
	   //删除子子表数据
	   update("Grant.delGchldByFkId", params);
       update("Grant.deleteChldByIds", params);
    }
    
	/**
     * * 根据主表Id查询子表记录
     * @param CPBLTZDID the CPBLTZDID
     * 
     * @return the list< new master GrantModelGchld model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrantModelGchld> gchildQuery(String CPBLTZDMXID) {
        Map params = new HashMap();
        params.put("CPBLTZDMXID", CPBLTZDMXID);
        //只查询0的记录。1为删除。0为正常
		params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Grant.queryGchldByFkId", params);
    }
    
    /**
     * * 根据明细表的子表Id批量加载子表信息
     * @param params the Map 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GrantModelGchld> loadGchilds(Map <String, String> params) {
       return findList("Grant.loadGchldByIds",params);
    }
    
    /**
     * * 明细数据编辑
     * 
     * @param CPBLTZDID the CPBLTZDID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txGchildEdit(String CPBLTZDID,  String CPBLTZDMXID,List <GrantModelGchld> gchldList) {
        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (GrantModelGchld model : gchldList) {
           
        	Map <String, String> params = new HashMap <String, String>();
		    params.put("WLBH",model.getWLBH());
		    params.put("WLMC",model.getWLMC());
		    params.put("GGXH",model.getGGXH());
		    params.put("WLSL",model.getWLSL());
		    params.put("REMARK",model.getREMARK());
		    params.put("CPBLTZDMXID",CPBLTZDMXID); 
            params.put("CPBLTZDID",CPBLTZDID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getCPBLTZDMXMXID())) {
                params.put("CPBLTZDMXMXID", StringUtil.uuid32len());
		        params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("CPBLTZDMXMXID", model.getCPBLTZDMXMXID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Grant.updateGchldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Grant.insertGchld", addList);
        }
        return true;
    }
    
    /**
     * * 子表批量删除:软删除
     * 
     * @param CPBLTZDMXIDs the CPBLTZDMXIDs
     */
    @Override
    public void txBatch4DeleteGchild(String CPBLTZDMXMXIDs) {
	   Map params = new HashMap();
	   params.put("CPBLTZDMXMXIDS", CPBLTZDMXMXIDs);
	   params.put("DELFLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Grant.deleteGchldByIds", params);
    }
    
}