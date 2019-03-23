/**

 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductServiceImpl.java
 */
package com.hoperun.base.product.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.product.model.PrdSpclTechModel;
import com.hoperun.base.product.model.PrdSuitModel;
import com.hoperun.base.product.model.PrdUnitModel;
import com.hoperun.base.product.model.ProductModel;
import com.hoperun.base.product.model.ProductTree;
import com.hoperun.base.product.service.ProductService;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;



// TODO: Auto-generated Javadoc
/**
 * The Class ProductServiceImpl.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
public class ProductServiceImpl extends BaseService implements ProductService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("product.pageQuery", "product.pageCount", params, pageNo);
    }
    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("product.insert", params);
        return true;
    }


    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params) {
        return update("product.updateById", params);
    }

    /**
     * 删除.
     * 
     * @param PRD_ID the Product id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {
    	//删除父
        update("product.delete", params);
        //删除子子表
        update("product.delGUnitByProId", params);
		 //删除子表 
		 return update("product.delGSuitByProId", params);
    }


    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {
        return update("product.updateStateById", params);
    }


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        update("product.updateStateById", params);
        checkPrdSuit(params.get("PRD_ID"));
        return true;
    }


    /**
     * 加载.
     * 
     * @param PRD_ID the Product id
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String PRD_ID) {
        return (Map) load("product.loadById", PRD_ID);
    }




    /**
     * 编辑.
     * 
     * @param PRD_ID the Product id
     * @param ProductModel the Product model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String PRD_ID, ProductModel productModel, UserBean userBean) {
        Map <String, Object> params = new HashMap <String, Object>();
        String PRD_NO=productModel.getPRD_NO();
        int count=PRD_NO.indexOf('-');
        String IS_NO_STAND_FLAG="0";
        if(count>0){
        	IS_NO_STAND_FLAG="1";
        }
        params.put("PRD_NO", PRD_NO);//货品编号
        params.put("PRD_NAME", productModel.getPRD_NAME());//货品名称
        params.put("PRD_SPEC", productModel.getPRD_SPEC());//规格型号
        params.put("PRD_COLOR", productModel.getPRD_COLOR());//颜色
        params.put("BRAND", productModel.getBRAND());//品牌
        params.put("STD_UNIT", productModel.getSTD_UNIT());//标准单位
        params.put("MEAS_UNIT", productModel.getMEAS_UNIT());//默认计量单位
        params.put("RATIO", productModel.getRATIO());//换算关系
        params.put("PAR_PRD_ID", productModel.getPAR_PRD_ID());//上级货品ID
        params.put("PAR_PRD_NO", productModel.getPAR_PRD_NO());//上级货品编号
        params.put("PAR_PRD_NAME", productModel.getPAR_PRD_NAME());//上级货品名称
        params.put("VOLUME", productModel.getVOLUME());//体积
        params.put("LENGTH", productModel.getLENGTH());//长度
        params.put("WIDTH", productModel.getWIDTH());//宽度
        params.put("HEIGHT", productModel.getHEIGHT());//高度
        params.put("FACT_PRICE", productModel.getFACT_PRICE());//出厂价
        params.put("RET_PRICE_MIN", productModel.getRET_PRICE_MIN());//最低零售价
        params.put("BEG_DATE", productModel.getBEG_DATE());//开始生产日期
        params.put("BAR_CODE", productModel.getBAR_CODE());//条码
        params.put("BAR_CODE_OLD", productModel.getBAR_CODE_OLD());//老条码
        params.put("U_CMP_CODE", productModel.getU_CMP_CODE());//财务对照码
        String PRD_SUIT_FLAG=productModel.getPRD_SUIT_FLAG();
        if(StringUtil.isEmpty(PRD_SUIT_FLAG)){
        	PRD_SUIT_FLAG=BusinessConsts.YJLBJ_FLAG_FALSE;
        }
        params.put("PRD_SUIT_FLAG", PRD_SUIT_FLAG);//货品套标记
        params.put("PIC_PATH", productModel.getPIC_PATH());//图片路径
        params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//终结点标记
        params.put("IS_CAN_FREE_FLAG", productModel.getIS_CAN_FREE_FLAG());//是否赠品
        params.put("REMARK", productModel.getREMARK());//备注
        params.put("DTL_PATH", productModel.getDTL_PATH());//详细信息路径
        params.put("PRD_MATL", productModel.getPRD_MATL());//材质
        params.put("PRVD_PRICE", productModel.getPRVD_PRICE());//供货价格
        params.put("IS_DISTRIBUT_FLAG", productModel.getIS_DISTRIBUT_FLAG());//是否分销标记
        params.put("IS_REBATE_FLAG", productModel.getIS_REBATE_FLAG());//是否返利标记
        if (StringUtils.isEmpty(PRD_ID)) {
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", "sysdate");//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
//            params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套信息 
        	params.put("PRD_ID", productModel.getPRD_NO());//货品id
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
        	params.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG);//是否非标
        	if("1".equals(userBean.getIS_DRP_LEDGER())){
        		params.put("PRD_ID", StringUtil.uuid32len());//渠道货品id
        		params.put("PRD_TYPE", "渠道货品");//货品类别
        		params.put("LEDGER_ID", userBean.getLoginZTXXID());//状态
        		params.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//通用标记
        	}else{
        		params.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        	}
            txInsert(params);
        }else{
        	 params.put("PRD_ID", PRD_ID);
             params.put("UPDATOR", userBean.getXTYHID());//更新人id
             params.put("UPD_NAME", userBean.getXM());//更新人名称
             params.put("UPD_TIME", "sysdate");//更新时间
             txUpdateById(params);
             clearCaches(PRD_ID);
        }
        checkPrdSuit(PRD_ID);
        return PRD_ID;
    }

    
    /**
     * 编辑.
     * 
     * @param PRD_ID the Product id
     * @param ProductModel the Product model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txPartAddEdit(String PRD_SPCL_TECH_ID, PrdSpclTechModel prdSpclTechModel, UserBean userBean) {
    	
    	//新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
    	
        Map <String, Object> params = new HashMap <String, Object>();
        params.put("PRD_ID", prdSpclTechModel.getPRD_ID());//货品ID
        params.put("SPCL_TECH_NAME", prdSpclTechModel.getSPCL_TECH_NAME());//特殊工艺维护名称
        params.put("SPCL_TECH_TYPE", prdSpclTechModel.getSPCL_TECH_TYPE());//特殊工艺维护类型
        params.put("CURRT_VAL", prdSpclTechModel.getCURRT_VAL());//当前值
        params.put("CURRT_VAL_TURN_BEG", prdSpclTechModel.getCURRT_VAL_TURN_BEG());//长度调整范围从
        params.put("CURRT_VAL_TURN_END", prdSpclTechModel.getCURRT_VAL_TURN_END());//长度调整范围到
        params.put("PRICE_TURN_TYPE", prdSpclTechModel.getPRICE_TURN_TYPE());//调价类型
        params.put("PRICE_TURN_VAL", prdSpclTechModel.getPRICE_TURN_VAL());//调价值
        params.put("TUENED_VALS", prdSpclTechModel.getTUENED_VALS());//调整后值
        params.put("REMARK", prdSpclTechModel.getREMARK());//高度填充物说明
        params.put("TURN_TYPE", prdSpclTechModel.getTURN_TYPE());//调整类型
        
        if (StringUtils.isEmpty(PRD_SPCL_TECH_ID)) {
        	params.put("PRD_SPCL_TECH_ID", StringUtil.uuid32len());
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	
            //txInsert(params);
            addList.add(params);
        }else{
        	params.put("PRD_SPCL_TECH_ID", PRD_SPCL_TECH_ID);
            updateList.add(params);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("product.updatePrdSpclTech", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("product.insertPrdSpclTech", addList);
        }
        return "";
    }
    
    @Override
    public void deletePrdSpclTechById(String PRD_SPCL_TECH_ID) {
	   Map<String, String> params = new HashMap<String, String>();
	   params.put("PRD_SPCL_TECH_ID", PRD_SPCL_TECH_ID);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	   //删除子子表数据
	   update("product.delPrdSpcTechById", params);
    }
    
    
    /**
     * 树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
	public List <ProductTree> getTree() throws Exception {
        List<ProductTree> menus = this.findList("product.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, ProductTree.class);
    }

    /**
     * * 根据主表Id查询子表货品计量单位记录
     * @param PRD_ID the PRD_ID
     * 
     * @return the list< new master slavemx model>
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrdUnitModel> unitQuery(String PRD_ID) {
		// TODO Auto-generated method stub
		Map params = new HashMap();
        params.put("PRD_ID", PRD_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("product.queryUnitByFkId", params);
	}
	/**
     * * 根据PrdSpcTech Id查询
     * @param PRD_ID the PRD_ID
     * 
     * @return the list< new master slavemx model>
     */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String,String>  prdspctechQuery(String PRD_SPCL_TECH_ID) {
		// TODO Auto-generated method stub
        return (Map) load("product.queryPrdSpcTechId", PRD_SPCL_TECH_ID);
	}
	
	
	/**
     * * 根据主表Id查询子表货品套记录
     * @param PRD_ID the PRD_ID
     * 
     * @return the list< new master slavemx model>
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrdSuitModel> suitQuery(String MAIN_PRD_ID) {
		// TODO Auto-generated method stub
		Map params = new HashMap();
        params.put("MAIN_PRD_ID",MAIN_PRD_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("product.querySuitByFkId", params);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PrdSpclTechModel> prdSpclTechQuery(String PRD_ID,String wheresql){
		Map params = new HashMap();
        params.put("PRD_ID", PRD_ID);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("WHERESQL",wheresql);
		
        return this.findList("product.queryPrdId", params);
		
	}
	
	/**
     * * 根据子表Id批量加载子表货品计量单位信息
     * @param PRD_UNIT_IDs the PRD_UNIT_IDs 
     * 
     * @return the list< new master slavemx model>
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrdUnitModel> loadUnits(Map<String, String> params) {
		// TODO Auto-generated method stub
		return findList("product.loadUnitByIds",params);
	}
	/**
     * * 根据子表Id批量加载子表货品套信息
     * @param PRD_UNIT_IDs the PRD_UNIT_IDs 
     * 
     * @return the list< new master slavemx model>
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrdSuitModel> loadSuits(Map<String, String> params) {
		// TODO Auto-generated method stub
		return findList("product.loadSuitByIds",params);
	}
	 /**
     * * 货品计量单位明细数据编辑
     * 
     * @param CPBLTZDID the CPBLTZDID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txUnitEdit(String PRD_ID, List<PrdUnitModel> unitList) {

        //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (PrdUnitModel model : unitList) {
            Map <String, Object> params = new HashMap <String, Object>();
            params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    params.put("PRD_ID",PRD_ID);//货品ID
		    params.put("MEAS_UNIT_ID",model.getMEAS_UNIT_ID());//计量单位ID
		    params.put("MEAS_UNIT_NO",model.getMEAS_UNIT_NO());//计量单位编号
		    params.put("MEAS_UNIT_NAME",model.getMEAS_UNIT_NAME());//计量单位名称
            params.put("MEAS_UNIT_TYPE",model.getMEAS_UNIT_TYPE());//单位类型
            params.put("RATIO",model.getRATIO());//换算关系
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRD_UNIT_ID())||model.getPRD_UNIT_ID()=="") {
                params.put("PRD_UNIT_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("PRD_UNIT_ID", model.getPRD_UNIT_ID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("product.updateUnitById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("product.insertUnit", addList);
        }
        return true;
    }
    /**
     * * 货品套明细数据编辑
     * 
     * @param CPBLTZDID the CPBLTZDID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txSuitEdit(String MAIN_PRD_ID, List<PrdSuitModel> suitList) {

        //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (PrdSuitModel model : suitList) {
            Map <String, Object> params = new HashMap <String, Object>();
            params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
            params.put("MAIN_PRD_ID", MAIN_PRD_ID);//主货品id
		    params.put("PRD_ID",model.getPRD_ID());//货品ID
		    params.put("PRD_NO",model.getPRD_NO());//货品编号
		    params.put("PRD_NAME",model.getPRD_NAME());//货品名称
		    params.put("PRD_SPEC",model.getPRD_SPEC());//规格型号
		    params.put("PRD_COLOR",model.getPRD_COLOR());//颜色
		    params.put("BRAND",model.getBRAND());//品牌
		    params.put("STD_UNIT",model.getSTD_UNIT());//标准单位
		    params.put("PRVD_PRICE", model.getPRVD_PRICE());//供货数量
		    params.put("COMP_NUM",model.getCOMP_NUM());//组成数量
		    params.put("MAIN_BOM_FLAG", model.getMAIN_BOM_FLAG());//主组成货品标记	
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRD_UNIT_ID())||model.getPRD_UNIT_ID()=="") {
                params.put("PRD_UNIT_ID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("PRD_UNIT_ID", model.getPRD_UNIT_ID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("product.updateSuitById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("product.insertSuit", addList);
        }
        checkPrdSuit(MAIN_PRD_ID);
        return true;
    }
    /**
     * * 子表货品计量单位批量删除:软删除
     * 
     * @param PRD_UNIT_IDS the PRO_UNIT_IDS
     */
    @Override
    public void txBatch4DeleteUnit(String PRD_UNIT_IDS) {
	   Map<String, String> params = new HashMap<String, String>();
	   params.put("PRD_UNIT_IDS", PRD_UNIT_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	   //删除子子表数据
	   update("product.delUnitByFkId", params);
    }
    
    
    
    /**
     * * 子表货品套批量删除:软删除
     * 
     * @param PRD_UNIT_IDS the PRD_UNIT_IDS
     */
    @Override
    public void txBatch4DeleteSuit(String PRD_UNIT_IDS) {
	   Map<String, String> params = new HashMap<String, String>();
	   params.put("PRD_UNIT_IDS", PRD_UNIT_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	   //删除子子表数据
	   update("product.delSuitByFkId", params);
	   
	   String PRD_ID=(String) this.load("product.getPrdIdByUnitIds",PRD_UNIT_IDS);
	   
	   checkPrdSuit(PRD_ID);
    }
    /**
     * 查询主表是否有相同编号
     */
    public int getCountPRD_NO(String PRD_NO) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("PRD_NO", PRD_NO);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return queryForInt("product.getCountPRD_NO",map);
	}
    /**
     * 更新是否扫码
     * 0 表示  '是 ' 1 表示  '否'
     */
    public void txUpdateScan(Map<String,String> params){
    	this.update("product.updateById", params);
    }
    /**
     * 查询子表货品计量单位编号是否重复
     * Gets the count PRD_NO.
     * 
     * @param PRD_NO the PRD_NO
     * 
     * @return the count PRD_NO
     */
	public int getCountUnitMEAS_UNIT_NO(String MEAS_UNIT_NO,String PRD_ID) {
		// TODO Auto-generated method stub
		Map<String,String> map =new HashMap<String,String>();
		map.put("MEAS_UNIT_NO", MEAS_UNIT_NO);
		map.put("PRD_ID", PRD_ID);
		return queryForInt("product.getCountNuitMEAS_UNIT_NO",map);
	}
	 /**
     * 查询子表货品套编号是否重复
     * Gets the count PRD_NO.
     * 
     * @param MEAS_UNIT_NO the MEAS_UNIT_NO
     * 
     * @return the count MEAS_UNIT_NO
     */
	public int getCountSuitPRD_NO(String PRD_NO,String MAIN_PRD_ID) {
		// TODO Auto-generated method stub
		Map<String,String> map =new HashMap<String,String>();
		map.put("PRD_NO", PRD_NO);
		map.put("MAIN_PRD_ID", MAIN_PRD_ID);
		return queryForInt("product.getCountSuitPRD_NO",map);
	}
	 /**
     * 获取所有品牌名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getBrand(String type){
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	if("toList".equals(type)){
    		map.put("STATE", "'启用','停用'");
    	}else{
    		map.put("STATE", "'启用'");
    	}
    	return this.findList("product.getBrand",map);
    }
    public List queryPrdExp(Map<String,String> map){
    	return this.findList("product.queryPrdExp", map);
    }
    /**
     * 导入
     * @param list
     * @param userBean
     */
    public void txImportExcel(List list,UserBean userBean,String DRPFLAG){
    	List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
    	String SESSIONID=StringUtil.uuid32len();
    	for(int i=0;i<list.size();i++){
			List lists =(List) list.get(i);
			for (int j = 0; j < lists.size(); j++) {
				Map<String,String> map =(Map<String,String>)lists.get(j);
				Map<String,String> params = new HashMap<String,String>();
				if(j==0){//验证第一行第一列是否有hoperun
					if(!map.get("PRD_NO").equals("hoperun")){
						throw new ServiceException("对不起，请使用模版文件进行修改上传!");
					}
					continue;
				}
				if(j==1){//第二行为列名
					continue;
				}
				String PRD_NO=map.get("PRD_NO");
				String PRD_NAME=map.get("PRD_NAME");
				//如果货品编号和名称都为空，则跳过当前数据执行下一次循环
				/*
				if(StringUtil.isEmpty(PRD_NO)&&StringUtil.isEmpty(PRD_NAME)){
					continue;
				}*/
				//如果货品编号为空,则自动生成一个货品编号
				if(StringUtil.isEmpty(PRD_NO)){
					String str = userBean.getCHANN_NO().toString();
					if(str.length()>4){// 判断是否长度大于等于4
						StringBuffer  sub = new StringBuffer(str);
						String random = getRandom();
					    PRD_NO = sub.append(random).toString();
					    map.put("PRD_NO", PRD_NO);
					}
				}else{
					Map<String, String> param = new HashMap<String, String>();
					param.put("PRD_NO", PRD_NO);
			        int count =  queryForInt("product.checkPrdNo", param);
			        if(count!=0){
			        	throw new ServiceException("货品编号已存在，请重新编辑!");
			        }
				}
				String FACT_PRICE=map.get("FACT_PRICE");
				String PRVD_PRICE=map.get("PRVD_PRICE");
				String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";
				if(!StringUtil.isEmpty(PRVD_PRICE)&&!StringUtil.regexCheck(PRVD_PRICE,checkStr)){
					if("1".equals(DRPFLAG)){
						throw new ServiceException("采购价必须为数字，请重新编辑!");
					}else{
						throw new ServiceException("供货价必须为数字，请重新编辑!");
					}
				}
				if(!StringUtil.isEmpty(FACT_PRICE)&&!StringUtil.regexCheck(FACT_PRICE,checkStr)){
					throw new ServiceException("销售价必须为数字，请重新编辑!");
				}
				params.putAll(map);
				if("0".equals(DRPFLAG)){
					if(!StringUtil.isEmpty(map.get("VOLUME"))&&!StringUtil.regexCheck(map.get("VOLUME"),checkStr)){
						throw new ServiceException("体积必须为数字，请重新编辑!");
					}
					if(!StringUtil.isEmpty(map.get("LENGTH"))&&!StringUtil.regexCheck(map.get("LENGTH"),checkStr)){
						throw new ServiceException("长度必须为数字，请重新编辑!");
					}
					if(!StringUtil.isEmpty(map.get("WIDTH"))&&!StringUtil.regexCheck(map.get("WIDTH"),checkStr)){
						throw new ServiceException("宽度必须为数字，请重新编辑!");
					}
					if(!StringUtil.isEmpty(map.get("HEIGHT"))&&!StringUtil.regexCheck(map.get("HEIGHT"),checkStr)){
						throw new ServiceException("高度必须为数字，请重新编辑!");
					}
					if(!StringUtil.isEmpty(map.get("RATIO"))&&!StringUtil.regexCheck(map.get("RATIO"),checkStr)){
						throw new ServiceException("换算关系必须为数字，请重新编辑!");
					}
					if(StringUtil.isEmpty(params.get("PRD_SPEC"))){
						params.put("PRD_SPEC", "通用规格");
					}
					if(!StringUtil.isEmpty(params.get("BEG_DATE"))){
						try {
							SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
							Date d=fm.parse(params.get("BEG_DATE"));
						} catch (Exception e) {
							throw new ServiceException("开始生产日期格式错误，请重新编辑!");
						}
					}
				}
				if(StringUtil.isEmpty(map.get("IS_CAN_FREE_FLAG"))||"否".equals(map.get("IS_CAN_FREE_FLAG"))){
					params.put("IS_CAN_FREE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				}else if ("是".equals(map.get("IS_CAN_FREE_FLAG"))){
					params.put("IS_CAN_FREE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
				}else{
					throw new ServiceException("是否赠品值错误，请重新编辑!");
				}
				if(StringUtil.isEmpty(map.get("IS_SCAN"))||"否".equals(map.get("IS_SCAN"))){
					params.put("IS_SCAN", BusinessConsts.YJLBJ_FLAG_TRUE);
				}else if ("是".equals(map.get("IS_SCAN"))){
					params.put("IS_SCAN", BusinessConsts.YJLBJ_FLAG_FALSE);
				}else{
					throw new ServiceException("是否扫码值错误，请重新编辑!");
				}
				String PAR_PRD_NO=map.get("PAR_PRD_NO");
				String PAR_PRD_NAME=map.get("PAR_PRD_NAME");
				Map<String, String> param = new HashMap<String, String>();
				param.put("PAR_PRD_NO", PAR_PRD_NO);
				String PAR_PRD_ID=(String)load("product.getParPrdId",param);
				params.put("PAR_PRD_ID", PAR_PRD_ID);
				params.put("PAR_PRD_NO", PAR_PRD_NO);
				params.put("PAR_PRD_NAME",PAR_PRD_NAME);
				params.put("SESSIONID", SESSIONID);
				params.put("BRAND", map.get("BRAND"));
				params.put("PRD_ID", StringUtil.uuid32len());
				params.put("IS_NO_STAND_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
				params.put("PRD_SUIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("UPD_NAME", userBean.getXM());
				params.putAll(LogicUtil.sysFild(userBean));
				params.put("PRD_CLASS_TYPE", BusinessConsts.YJLBJ_FLAG_FALSE);
				if("1".equals(DRPFLAG)){
					params.put("PRD_TYPE", "渠道货品");
					params.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				}else{
					params.put("PRD_TYPE", BusinessConsts.YJLBJ_FLAG_FALSE);
					params.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
				}
				addList.add(params);
			}
		}
    	if(!addList.isEmpty()){
    		boolean flag=this.batch4Update("product.insertTemp", addList);
    		if(flag==false){
         	   throw new ServiceException("插入货品临时表出错!");
            }
    	}
    	String mssage=checkPrdInfo(SESSIONID,DRPFLAG,userBean.getLoginZTXXID());
    	if(!StringUtil.isEmpty(mssage)){
    		throw new ServiceException(mssage);
    	}
    	// 临时表数据存入货品表，如果货品编号存在则修改，不存在则新增
    	potPrdInfo(SESSIONID,DRPFLAG);
    	//删除临时表
    	this.delete("product.delPrdTemp", SESSIONID);
    }
    
    /**
     * 验证导入货品临时表数据
     * @return
     */
    public String checkPrdInfo(String SESSIONID,String DRPFLAG,String LEDGER_ID){
    	
    	//检查是必填字段
    	String mssage=checkMustFld(SESSIONID, DRPFLAG);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//检查是否有重复记录
    	mssage = checkRepeatPdt(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//检查操作人所属机构是否和货品所属账套不符
    	mssage=checkPrdLedger(SESSIONID,DRPFLAG,LEDGER_ID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证货品品牌是否存在
    	mssage=checkPrdBrand(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证标准单位是否存在
    	mssage=checkPrdStdUnit(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证默认计量单位是否存在
    	mssage=checkPrdMeasUnit(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证上级货品编号和名称是否存在
    	mssage=checkParPrdInfo(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证渠道货品需要渠道编号开头
    	mssage=checkPrdHead(SESSIONID, LEDGER_ID, DRPFLAG);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//验证货品编号是否是纯数字
    	mssage=checkPrdNum(SESSIONID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	return null;
    }
    
    /**检查是必填字段
	 * @param SESSION_ID
	 * @return
	 */
	private String checkMustFld(String SESSIONID,String DRPFLAG){
		List mustList = null;
		if("1".equals(DRPFLAG)){
			mustList = this.findList("product.checkDRPMustFld", SESSIONID);
		}else{
			mustList = this.findList("product.checkMustFld", SESSIONID);
		}
		StringBuffer buf = new StringBuffer();
		if(!mustList.isEmpty()&&mustList.size()>0){
			HashMap msutMap = (HashMap)mustList.get(0);
			String PRD_NO = (String)msutMap.get("PRD_NO");
			String PRD_NAME = (String)msutMap.get("PRD_NAME");
			String BRAND = (String)msutMap.get("BRAND");
			String STD_UNIT = (String)msutMap.get("STD_UNIT");
			String PRD_SPEC = (String)msutMap.get("PRD_SPEC");
			BigDecimal PRVD_PRICE = (BigDecimal)msutMap.get("PRVD_PRICE");
			BigDecimal FACT_PRICE = (BigDecimal)msutMap.get("FACT_PRICE");
//			if(StringUtil.isEmpty(PRD_NO)){
//				buf.append("货品编号,");
//			}
			if(StringUtil.isEmpty(PRD_NAME)){
				buf.append("货品名称,");
			}
			if(StringUtil.isEmpty(BRAND)&&!"1".equals(DRPFLAG)){
				buf.append("品牌,");
			}
			if(StringUtil.isEmpty(PRD_SPEC)&&"1".equals(DRPFLAG)){
				buf.append("规格型号,");
			}
			if(StringUtil.isEmpty(STD_UNIT)){
				buf.append("标准单位,");
			}
			if(null==PRVD_PRICE){
				if("1".equals(DRPFLAG)){
					buf.append("采购价,");
				}else{
					buf.append("供货价,");
				}
			}
			if(null==FACT_PRICE&&"1".equals(DRPFLAG)){
				buf.append("销售价,");
			}
		}
		String message=buf.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"是必填字段,请重新编辑!";
		}
		return message;
	}
	/**检查是否有重复记录
	 * @param SESSION_ID
	 * @return
	 */
	private String checkRepeatPdt(String SESSIONID){
		List pdtList = this.findList("product.checkPdtRepeat", SESSIONID);
		StringBuffer str=new StringBuffer();
		if(!pdtList.isEmpty()){
			str.append("货品编号:");
			for (int i = 0; i < pdtList.size(); i++) {
				HashMap pdtMap = (HashMap)pdtList.get(i);
				String PRD_NO = (String)pdtMap.get("PRD_NO");
				str.append(PRD_NO).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"存在重复记录,请重新编辑!";
		}
		return message;
	}
	/**
	 * 检查操作人所属机构是否和货品所属账套不符
	 * @param SESSIONID
	 * @param DRPFLAG
	 * @param LEDGER_ID
	 * @return
	 */
	private String checkPrdLedger(String SESSIONID,String DRPFLAG,String LEDGER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SESSIONID", SESSIONID);
		if("1".equals(DRPFLAG)){
			map.put("SQL", " a.LEDGER_ID!='"+LEDGER_ID+"'");
		}else{
			map.put("SQL", " a.COMM_FLAG!=1 ");
		}
		List<String> prdList=this.findList("product.checkPrdLedger", map);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("货品编号：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"不属于您所属的渠道,请重新编辑!";
		}
		return message;
	}
	/**
	 * 验证品牌是否存在
	 * @param SESSIONID
	 * @return
	 */
	private String checkPrdBrand(String SESSIONID){
		List<String> prdList=this.findList("product.checkPrdBrand", SESSIONID);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("品牌：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"不存在,请重新编辑!";
		}
		return message;
	}
	/**
	 * 验证标准单位是否存在
	 * @param SESSIONID
	 * @return
	 */
	private String checkPrdStdUnit(String SESSIONID){
		List<String> prdList=this.findList("product.checkPrdStdUnit", SESSIONID);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("标准单位：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"不存在,请重新编辑!";
		}
		return message;
	}
	/**
	 * 验证默认计量单位是否存在
	 * @param SESSIONID
	 * @return
	 */
	private String checkPrdMeasUnit(String SESSIONID){
		List<String> prdList=this.findList("product.checkPrdMeasUnit", SESSIONID);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("默认计量单位：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"不存在,请重新编辑!";
		}
		return message;
	}
	/**
	 * 验证上级货品编号和名称是否存在
	 * @param SESSIONID
	 * @return
	 */
	private String checkParPrdInfo(String SESSIONID){
		//输入上级货品编号和名称有3种情况
		//1:有上级货品编号无上级货品名称的 验证上级货品编号是否存在
		List<String> prdList=this.findList("product.checkNoParPrdInfo", SESSIONID);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("上级货品编号：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
			String message=str.toString();
			if (!StringUtil.isEmpty(message)) {
				message = message.substring(0,
						message.length() - 1);
				message=message+"不存在,请重新编辑!";
			}
			return message;
		}
		//2：无上级货品编号有上级货品名称的 验证上级货品名称是否存在
		prdList=this.findList("product.checkNameParPrdInfo", SESSIONID);
		if(!prdList.isEmpty()){
			str.append("上级货品名称：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
			String message=str.toString();
			if (!StringUtil.isEmpty(message)) {
				message = message.substring(0,
						message.length() - 1);
				message=message+"不存在,请重新编辑!";
			}
			return message;
		}
		//3：有上级货品编号和上级货品名称的，验证上级货品编号和上级货品名称是否存在，是否匹配
	 
		List<Map<String,String>> li=this.findList("product.checkParPrdInfo", SESSIONID);
		if(!li.isEmpty()){
			if(li.size()>0){
				str.append("上级货品编号:").append(li.get(0).get("PAR_PRD_NO"))
				.append(",上级货品名称:").append(li.get(0).get("PAR_PRD_NAME"))
				.append("不存在，请重新编辑");
			}
			return str.toString();
		}
		 
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("SESSIONID", SESSIONID);
		map.put("SQL", " b.PRD_NAME = a.PAR_PRD_NAME ");
		map.put("MAINSQL", " a.PAR_PRD_NO is null ");
		this.update("product.upParPrdInfo", map);//修改有编号无名称的上级货品 填补名称和id
		map.put("SQL", " b.PRD_NO = a.PAR_PRD_NO ");
		map.put("MAINSQL", " a.PAR_PRD_NAME is null ");
		this.update("product.upParPrdInfo", map);//修改有名称无编号的上级货品 填补编号和id
		map.put("SQL", " b.PRD_NO = a.PAR_PRD_NO and  b.PRD_NAME = a.PAR_PRD_NAME  ");
		map.put("MAINSQL", " a.PAR_PRD_NAME is not null  and  a.PAR_PRD_NO is not null ");
		this.update("product.upParPrdInfo", map);//修改有名称有编号的上级货品 填补id
		return null;
	}
	/**
	 * 验证如果是渠道货品的话 是否是渠道编号开头
	 * @param SESSIONID
	 * @param LEDGER_ID
	 * @param DRPFLAG
	 * @return
	 */
	public String checkPrdHead(String SESSIONID,String LEDGER_ID,String DRPFLAG){
		if("1".equals(DRPFLAG)){
			StringBuffer str=new StringBuffer();
			Map<String,String> map=new HashMap<String, String>();
			map.put("LEDGER_ID", LEDGER_ID);
			map.put("SESSIONID", SESSIONID);
			List<String> prdList=this.findList("product.checkPrdHead", map);
			if (!prdList.isEmpty()) {
				str.append("货品编号：");
				for (int i = 0; i < prdList.size(); i++) {
					str.append(prdList.get(i)).append(",");
				}
				String message=str.toString();
				if (!StringUtil.isEmpty(message)) {
					message = message.substring(0,
							message.length() - 1);
					message=message+"需用本渠道编号开头,请重新编辑!";
				}
				return message;
			}
		}
		return null;
	}
	/**
	 * 验证货品是否是数字
	 * @param SESSIONID
	 * @return
	 */
	public String checkPrdNum(String SESSIONID){
		try {
			List<String> prdList=this.findList("product.checkPrdNum", SESSIONID);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "货品编号请输入纯数字编号";
		}
		
	}
	/**
	 * 把临时库数据转入货品表
	 * 货品编号存在的修改，不存在的新增
	 * @param SESSIONID
	 * @return
	 */
	public void potPrdInfo(String SESSIONID,String DRPFLAG){
		//先修改
		if("1".equals(DRPFLAG)){
			this.update("product.upDrpPrdInfo", SESSIONID);
		}else{
			this.update("product.upErpPrdInfo", SESSIONID);
		}
		//后新增
		if("1".equals(DRPFLAG)){
			this.update("product.insertDrpPrdInfo", SESSIONID);
		}else{
			this.update("product.insertErpPrdInfo", SESSIONID);
		}
	}
	/**
	 * 验证货品套货品是否有明细
	 * @param PRD_ID
	 * @return
	 */
	public void checkPrdSuit(String PRD_ID)throws ServiceException{
		//货品套启用时必须有子货品套
		Map<String,String> map=(Map<String, String>) load("product.getPrdSuitInfo",PRD_ID);
		if(null!=map){
			if(BusinessConsts.JC_STATE_ENABLE.equals(map.get("STATE"))&&BusinessConsts.YJLBJ_FLAG_TRUE.equals(String.valueOf(map.get("PRD_SUIT_FLAG")))){
				if("0".equals(String.valueOf(map.get("CNT")))){
					throw new ServiceException("操作失败，启用状态货品套需要先维护套件！");
				}
			}
			//子货品套价格总和必须等于货品供货价
			Map<String,String> checkMap=(Map<String, String>) load("product.getPrdSuitPriceInfo",PRD_ID);
			if(!String.valueOf(map.get("PRD_SUIT_FLAG")).equals(BusinessConsts.YJLBJ_FLAG_FALSE)&&BusinessConsts.JC_STATE_ENABLE.equals(checkMap.get("STATE"))){
				if(!String.valueOf(checkMap.get("PRVD_PRICE")).equals(String.valueOf(checkMap.get("PRICE")))){
					throw new ServiceException("操作失败，货品套供货价需跟货品套明细总供货价相等！");
				}
			}
		}
	}
	
	public static String getRandom(){
		int i = 1;// i在此程序中只作为判断是否是将随机数添加在首位，防止首位出现0；
		Random r = new Random();
		int tag[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		String str = "";
		int temp = 0;
		while (str.length() < 4) {
			temp = r.nextInt(10);// 取出0(含)~10(不含)之间任意数
			if (i == 1 && temp == 0) {
				continue;
			} else {
				i = 2;
				if (tag[temp] == 0) {
					str = str + temp;
					tag[temp] = 1;
				}
			}
		}
		return str;
	}
}
