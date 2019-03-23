/**
 *@module 基础资料
 *@func 编码规则维护
 *@version 1.1
 *@author 孙炜
 */
package com.hoperun.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.BmgzModel;
import com.hoperun.sys.model.BmgzMxModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.BmgzService;

// TODO: Auto-generated Javadoc
/**
 * The Class BmgzServiceImpl.
 */
public class BmgzServiceImpl extends BaseService implements BmgzService {

    /**
     * 根据主表Id查询子表记录
     * Description :.
     * 
     * @param bmgzid the bmgzid
     * 
     * @return the list< bmgz mx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <BmgzMxModel> childQuery(String bmgzid) {

        Map params = new HashMap();
        params.put("BMGZID", bmgzid);
        //只查询0的记录。1为删除。0为正常
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("BMGZMX.query", params);
    }


    /**
     * 删除主表之前,首先检查子表是否有数据.
     * 
     * @param bmgzid the bmgzid
     * 
     * @return the int
     */
    @Override
    public int deleteCheck(String bmgzid) {

        return queryForInt("BMGZ.getCountFromChildTable", bmgzid);
    }


    /**
     * 查询详细信息
     * Description :.
     * 
     * @param bmgzid the bmgzid
     * 
     * @return the map< string, string>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map <String, String> load(String bmgzid) {

        return (Map <String, String>) load("BMGZ.loadById", bmgzid);
    }


    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * 
     * @param bmgzMxIds the bmgz mx ids
     * 
     * @return the list< bmgz mx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <BmgzMxModel> loadChilds(String bmgzMxIds) {

        return findList("BMGZMX.loadByIds", bmgzMxIds);
    }


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

        return this.pageQuery("BMGZ.pageQuery", "BMGZ.pageCount", params, pageNo);
    }


    /**
     * 批量删除.
     * 
     * @param bmgzMxIds the bmgz mx ids
     * @param bmgzId the bmgz id
     * @param userBean the user bean
     */
    @Override
    public void txBatch4DeleteChild(String bmgzMxIds, String bmgzId, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("bmgzMxIds", bmgzMxIds);
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("BMGZMX.insertBMGZMX", params);
        delete("BMGZMX.deleteByIds", bmgzMxIds);
        int countZcd = getZcd(bmgzId);
        Map <String, String> zcdparams = new HashMap <String, String>();
        zcdparams.put("ZCD", String.valueOf(countZcd));
        zcdparams.put("BMGZID", bmgzId);
        updateById(zcdparams);
    }


    /**
     * 子表记录编辑：新增/修改
     * Description :.
     * 
     * @param modelList the model list
     * @param userBean the user bean
     * @param bmgzid the bmgzid
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String bmgzid, List <BmgzMxModel> modelList, UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();

        for (int i = 0; i < modelList.size(); i++) {
            BmgzMxModel model = modelList.get(i);
            Map <String, String> params = new TreeMap <String, String>();
            params.put("BMGZID", bmgzid);
            // 顺序号
            String sxh = model.getSXH();
            if (StringUtil.isEmpty(sxh)) {
                sxh = String.valueOf(i + 1);
            }
            params.put("SXH", sxh);
            params.put("DLX", model.getDLX());
            params.put("DCD", model.getDCD());

            // 要确认
            if (StringUtils.isEmpty(model.getDT())) {
                params.put("DT", "");
            } else {
                params.put("DT", model.getDT());
            }

            if (StringUtils.isEmpty(model.getNYS())) {
                params.put("NYS", "");
            } else {
                params.put("NYS", model.getNYS());
            }

            if (StringUtils.isEmpty(model.getBC())) {
                params.put("BC", "0");
            } else {
                params.put("BC", model.getBC());
            }

            if (StringUtils.isEmpty(model.getCSZ())) {
                params.put("CSZ", "0");
            } else {
                params.put("CSZ", model.getCSZ());
            }
            params.put("STATE", " ");

            params.put("DELFLAG", model.getDELFLAG());
            params.put("UPDATER", userBean.getRYXXID());
            //如果没有编码规则明细ID的则是新增，有的是修改
            if (StringUtils.isEmpty(model.getBMGZMXID())) {
                params.put("BMGZMXID", StringUtil.uuid32len());
                params.put("CREATER", userBean.getXTYHID());
                addList.add(params);
            } else {
                params.put("BMGZMXID", model.getBMGZMXID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("BMGZMX.updateById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("BMGZMX.insert", addList);
        }
        int countZcd = getZcd(bmgzid);
        Map <String, String> params = new TreeMap <String, String>();
        params.put("BMGZID", bmgzid);
        params.put("ZCD", String.valueOf(countZcd));
        updateById(params);

        return true;
    }


    /**
     * 获取总长度.
     * 
     * @param bmgzid the bmgzid
     * 
     * @return int
     */
    public int getZcd(String bmgzid) {

        return queryForInt("BMGZMX.getZcd", bmgzid);
    }


    /**
     * 单条记录删除
     * Description :.
     * 
     * @param bmgzid the bmgzid
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    @Override
    public boolean txDelete(String bmgzid, UserBean userBean) {

    	 Map <String, String> params = new HashMap <String, String>();
         params.put("BMGZID", bmgzid);
         params.put("DATARECYCLEID", StringUtil.uuid32len());
         params.put("DELETOR", userBean.getYHM());
         try {
 			    insert("BMGZ.insertBMGZ", params);
 			params.put("DATARECYCLEID", StringUtil.uuid32len());
 			insert("BMGZ.insertBMGZMX", params);
 			delete("BMGZ.delete", bmgzid);
 			delete("BMGZ.deletemx", bmgzid);
 		} catch (RuntimeException e) {
 			e.printStackTrace();
 			return false;
 			
 		}
         return true;

    }


    /**
     * 主表及子表编辑 新增/修改。
     * Description :.
     * 
     * @param bmgzid the bmgzid
     * @param bmgzModel the bmgz model
     * @param bmgzMxList the bmgz mx list
     * @param userBean the user bean
     */
    @Override
    public void txEdit(String bmgzid, UserBean userBean, BmgzModel bmgzModel, List <BmgzMxModel> bmgzMxList) {

        Map <String, String> params = new TreeMap <String, String>();
        params.put("BMBH", bmgzModel.getBMBH());
        params.put("BMDX", bmgzModel.getBMDX());
        params.put("GZMC", bmgzModel.getGZMC());
        params.put("DS", "0");
        params.put("ZCD", "0");
        if (StringUtils.isEmpty(bmgzModel.getSEQUENCEMC())) {
            params.put("SEQUENCEMC", "");
        } else {
            params.put("SEQUENCEMC", bmgzModel.getSEQUENCEMC());
        }
        // 段数，总长度
//        int i = 0;
//        int zcd = 0;
//        if (null != bmgzMxList) {
//            i = bmgzMxList.size();
//            for (BmgzMxModel mxModle : bmgzMxList) {
//                int dcd = Integer.parseInt(mxModle.getDCD());
//                zcd += dcd;
//            }
//        }
//        params.put("DS", String.valueOf(i));
//        params.put("ZCD", String.valueOf(zcd));

        //params.put("STATE", bmgzModel.getSTATE());

        params.put("UPDATER", userBean.getXM());
        if (StringUtils.isEmpty(bmgzModel.getREMARK())) {
            params.put("REMARK", "");
        } else {
            params.put("REMARK", bmgzModel.getREMARK());
        }

        //下面是常用的信息，可以封装成公用方法
        params.put("ZDRID", userBean.getXTYHID());
        params.put("ZDRXM", userBean.getXM());
        params.put("ZDBMID", userBean.getBMXXID());
        params.put("ZDBMMC", userBean.getBMMC());
        params.put("ZDJGID", userBean.getJGXXID());
        params.put("ZDJGMC", userBean.getJGMC());
        params.put("ZTXXID", userBean.getLoginZTXXID());

        //如果bmgzid为空，说明是新增记录
        if (StringUtils.isEmpty(bmgzid)) {
            bmgzid = StringUtil.uuid32len();
            //自动生成编码编号
            String BMBH = "";
            String Maxbmbh = (String) this.load("BMGZ.getBMBH", "");
            if(Maxbmbh==null)
            {
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        	    String RQ = sdf.format(new Date());
        	    BMBH = "BMGZ" + RQ +"0001";
            }else{
            	String e = "";
            	//后4位
            	String BH = Maxbmbh.substring(Maxbmbh.length()-4, Maxbmbh.length());
            	//前面固定部分
            	String GDBH = Maxbmbh.substring(0, Maxbmbh.length()-4);
            	int XBH = Integer.valueOf(BH) + 1;//后4位加1
            	if(String.valueOf(XBH).length()<4){
         		   String z = "";
         		   if(String.valueOf(XBH).length()==1)
         		   {
         		        z += "000" + XBH;
         		   }else if(String.valueOf(XBH).length()==2)
         		   {
         		        z += "00" + XBH;
         		   }else if(String.valueOf(XBH).length()==3)
         		   {
         		        z += "0" + XBH;
         		   }
         		   e = GDBH + z;
         	    }else{
         		   e = GDBH + XBH;
         		}
            	BMBH = e;
            }
            params.put("BMBH", BMBH);
            params.put("BMGZID", bmgzid);
            params.put("CREATER", userBean.getXTYHID());
            params.put("CRENAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
            insert(params);
        } else {
            params.put("BMGZID", bmgzid);
            updateById(params);
        }
        //子表信息编辑
        if (null != bmgzMxList && !bmgzMxList.isEmpty()) {
            txChildEdit(bmgzid, bmgzMxList, userBean);
        }
        
        //更新总长度
        int countZcd = getZcd(bmgzid);
        Map <String, String> zcdparams = new HashMap <String, String>();
        zcdparams.put("ZCD", String.valueOf(countZcd));
        zcdparams.put("BMGZID", bmgzid);
        updateById(zcdparams);
    }


    /**
     * 新增主表记录.
     * 
     * @param params the params
     * 
     * @return true
     */
    public boolean insert(Map <String, String> params) {

        insert("BMGZ.insert", params);
        return true;
    }


    /**
     * 更新主表记录.
     * 
     * @param params the params
     * 
     * @return update的结果
     */
    public boolean updateById(Map <String, String> params) {

        return update("BMGZ.updateById", params);
    }


    /**
     * 状态修改.
     * 
     * @param params the params
     */
    @Override
    public void updateState(Map <String, String> params) {

        updateById(params);

    }


    /**
     * 新增判断，编号不能重复.
     * 
     * @param bmbh the bmbh
     * 
     * @return 该编号的数量
     */
    public int getCountByBH(String bmbh) {

        return queryForInt("BMGZ.getCountByBH", bmbh);
    }


    /**
     * 取得SEQUENCE.
     * 
     * @return the seq
     */
    @SuppressWarnings("unchecked")
    public List getSeq() {

        return (List) findList("BMGZ.getSeq", "");
    }

}
