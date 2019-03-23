package com.hoperun.commons.techorder.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.techorder.model.TechorderManagerModelChld;
import com.hoperun.commons.techorder.service.TechorderManagerService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class TechorderManagerServiceImpl extends BaseService implements TechorderManagerService {
	//-- 0156143--Start--刘曰刚--2014-06-17//
		//修改特殊工艺获取价格方式 改变方法参数
	public Map<String, String> getSpclInfoById(Map<String,String> map) {
		return (Map<String, String>) load("TechorderManager.getSpclInfoById", map);
	}
	
	public Map<String, String> getPrdInfoById(Map<String,String> map) {
		return (Map<String, String>) load("TechorderManager.getPrdInfoById", map);
	}
	//-- 0156143--End--刘曰刚--2014-06-17//
	public List<Map<String, String>> getSpclById(Map<String,String> map) {
		return this.findList("TechorderManager.getSpclById", map);
	}
	
	public List<Map<String, String>> getSpclByPrdId(String PRD_ID) {
		return this.findList("TechorderManager.getSpclByPrdId", PRD_ID);
	}
	
	public Map<String,String> txEdit(String SPCL_TECH_ID,List<TechorderManagerModelChld> list,String PRD_ID,String BASE_PRICE,String DECT_RATE,String USE_FLAG,String TABLE,String BUSSID,String addFlag,UserBean userBean) {
		if(StringUtil.isEmpty(DECT_RATE)){
			DECT_RATE="0";
		}
		if(StringUtil.isEmpty(BASE_PRICE)){
			BASE_PRICE="0";
		}
		//新增list
		List<Map<String,Object>> addList=new ArrayList<Map<String,Object>>();
		StringBuffer WIDTH=new StringBuffer();//宽
		StringBuffer LENGTH=new StringBuffer();//长
		StringBuffer HEIGHT=new StringBuffer();//高
		//特殊工艺标识列  0为无特殊工艺，1为有特殊工艺 ，2为非标特殊工艺
		String SPCL_TECH_FLAG="0";
		double TECH_MULT=0;
		double TECH_AMOUNT=0;
		List<Double> sizeAdjustList = new ArrayList<Double>();
		//是否新增标记，为true时为新增
		boolean newFlag=false;
		if(StringUtil.isEmpty(SPCL_TECH_ID)){
			newFlag=true;
			SPCL_TECH_ID=StringUtil.uuid32len();
		}
		for (TechorderManagerModelChld model : list) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			map.put("SPCL_TECH_ID", SPCL_TECH_ID);//订单特殊工艺ID
			map.put("PRD_SPCL_TECH_ID", model.getPRD_SPCL_TECH_ID());//货品特殊工艺维护ID
			map.put("SPCL_TECH_TYPE",  model.getSPCL_TECH_TYPE());//特殊工艺类型
			if("部件新增".equals(model.getSPCL_TECH_TYPE())){
				if(!StringUtil.isEmpty(model.getSPCL_TECH_NAME())){
					SPCL_TECH_FLAG="1";
				}
			}
			
			//调价类型
			String PRICE_TURN_TYPE = model.getPRICE_TURN_TYPE();
			map.put("PRICE_TURN_TYPE",  model.getPRICE_TURN_TYPE());
			//调价值
			String PRICE_TURN_VAL = model.getPRICE_TURN_VAL();
			////调整范围内的  调整值
			String CUST_TURN_VAL = model.getCUST_TURN_VAL();
			
			
			//把尺寸调整拼接为特殊工艺说明
			if("尺寸调整".equals(model.getSPCL_TECH_TYPE())){
				String SPCL_TECH_NAME=model.getSPCL_TECH_NAME();
				if(SPCL_TECH_NAME.indexOf("宽")!=-1){
					WIDTH.append(SPCL_TECH_NAME).append(":").append(model.getCUST_TURN_VAL());
				}
				if(SPCL_TECH_NAME.indexOf("长")!=-1){
					LENGTH.append(SPCL_TECH_NAME).append(":").append(model.getCUST_TURN_VAL());
				}
				if(SPCL_TECH_NAME.indexOf("高")!=-1){
					HEIGHT.append(SPCL_TECH_NAME).append(":").append(model.getCUST_TURN_VAL());
				}
				
				//add by zzb 2017-8-01 尺寸调整与其他的加价不同，取尺寸调整下面的特殊明细计算之后最大的值
				Double BASE_PRICE_D = StringUtil.getDouble(BASE_PRICE);
				Double PRICE_TURN_VAL_D = StringUtil.getDouble(PRICE_TURN_VAL);
				Double CUST_TURN_VAL_D = StringUtil.getDouble(CUST_TURN_VAL);
				Double rsult = 0.0;
				if("倍率加价".equals(PRICE_TURN_TYPE)){
					rsult = BASE_PRICE_D*PRICE_TURN_VAL_D;
				}
				if("值加价".equals(PRICE_TURN_TYPE)){
					rsult =  PRICE_TURN_VAL_D;
				}
				if("单位费用".equals(PRICE_TURN_TYPE)){
					rsult = CUST_TURN_VAL_D*PRICE_TURN_VAL_D;
				}
				sizeAdjustList.add(rsult);
				
			}else{
				if("倍率加价".equals(PRICE_TURN_TYPE)&&PRICE_TURN_VAL!=null){
					TECH_MULT+=Double.parseDouble(PRICE_TURN_VAL.toString());
				}
				if("值加价".equals(PRICE_TURN_TYPE)&&PRICE_TURN_VAL!=null){
					TECH_AMOUNT+=Double.parseDouble(PRICE_TURN_VAL.toString());
				}
			}
			
			map.put("SPCL_TECH_NAME",  model.getSPCL_TECH_NAME());//特殊工艺维护名称
			String CURRT_VAL=model.getCURRT_VAL();
			map.put("CURRT_VAL", CURRT_VAL );//当前值
			map.put("CURRT_VAL_TURN_BEG",  model.getCURRT_VAL_TURN_BEG());//调整范围从
			map.put("CURRT_VAL_TURN_END",  model.getCURRT_VAL_TURN_END());//调整范围到
			
			map.put("PRICE_TURN_VAL",  PRICE_TURN_VAL);//调价值
			map.put("TUENED_VALS",  model.getTUENED_VALS());//可调整值
			 
			map.put("CUST_TURN_VAL",  CUST_TURN_VAL);//调整后值
			if(!StringUtil.isEmpty(CUST_TURN_VAL)&&!SPCL_TECH_FLAG.equals("2")){
				SPCL_TECH_FLAG="1";
			}
			String REMARK=model.getREMARK();//备注
			map.put("REMARK", REMARK);
			if("一般特殊工艺描述".equals(model.getSPCL_TECH_TYPE())&&!SPCL_TECH_FLAG.equals("2")&&!StringUtil.isEmpty(REMARK.trim())){
				SPCL_TECH_FLAG="1";
			}
			if("非标工艺说明".equals(model.getSPCL_TECH_TYPE())&&!StringUtil.isEmpty(model.getREMARK().trim())){
				if(!StringUtil.isEmpty(REMARK)&&!StringUtil.isEmpty(REMARK.trim())){
					SPCL_TECH_FLAG="2";
				}
			}
			String SPCL_TECH_DTL_ID=model.getSPCL_TECH_DTL_ID();
			SPCL_TECH_DTL_ID=StringUtil.uuid32len();
			map.put("SPCL_TECH_DTL_ID", SPCL_TECH_DTL_ID);//订单特殊工艺明细
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
			map.put("TECH_MULT", TECH_MULT+"");//特殊工艺加价倍数
			map.put("TECH_AMOUNT", TECH_AMOUNT+"");//特殊工艺加价
			addList.add(map);
		}//循环结束
		//传到页面上的参数
		String SPCL_DTL_REMARK_CHK=getTechShowStrPrice(addList);
		System.out.println(SPCL_DTL_REMARK_CHK);
		Map<String,String> checkRemark=new HashMap<String, String>();
		checkRemark.put("SPCL_DTL_REMARK_CHK", SPCL_DTL_REMARK_CHK);
		checkRemark.put("PRD_ID", PRD_ID);
		checkRemark.put("LEDGER_ID", userBean.getLoginZTXXID());
		//尺寸调整拿出来计算，取他的下面的最大值做为尺寸调整的 价格
		Double SIZE_ADJUST = 0.0;
		if(!sizeAdjustList.isEmpty()){
			int length = sizeAdjustList.size();
			Double[] d = new Double[length];
			sizeAdjustList.toArray(d);
			Arrays.sort(d);
			SIZE_ADJUST = d[length-1];
			System.out.println(SIZE_ADJUST);
		}
		
		
		//工艺单主表map
		Map<String,String> map=new HashMap<String,String>();
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		map.put("SPCL_TECH_FLAG", SPCL_TECH_FLAG);
		map.put("PRD_ID", PRD_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("TECH_MULT", subZeroAndDot(String.format("%.2f", TECH_MULT)));
		map.put("LEDGER_ID", userBean.getLoginZTXXID());
		
		
//		特殊工艺加价 金额  尺寸调整拿出来加
		TECH_AMOUNT = TECH_AMOUNT+SIZE_ADJUST;
		map.put("TECH_AMOUNT", subZeroAndDot(String.format("%.2f", TECH_AMOUNT)));
		
		//如果特殊工艺标记为1时，计算特殊工艺价格
		double TECH_CHECK_PRICE=0;
		if(SPCL_TECH_FLAG.equals("1")){
			TECH_CHECK_PRICE=Double.parseDouble(BASE_PRICE)+Double.parseDouble(BASE_PRICE)*TECH_MULT+TECH_AMOUNT;
		}else{
			TECH_CHECK_PRICE=Double.parseDouble(BASE_PRICE);
		}
		
		//加价后单价
		map.put("TECH_CHECK_PRICE", subZeroAndDot(String.format("%.2f", TECH_CHECK_PRICE)));
		//原单价
		map.put("PRICE", BASE_PRICE);
		//折扣率
		map.put("DECT_RATE", DECT_RATE);
		//折后价
		map.put("DECT_PRICE",(TECH_CHECK_PRICE*Double.parseDouble(DECT_RATE))+"");
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);//id
		map.put("SPCL_TECH_FLAG", SPCL_TECH_FLAG);//标记
		map.put("PRICE", subZeroAndDot(String.format("%.2f", TECH_CHECK_PRICE)));//单价
		Map<String,String> oldMap=(Map<String, String>) this.load("TechorderManager.checkSpclRemark", checkRemark);
		if(null!=oldMap){
			map.putAll(oldMap);
			backFill(TABLE, BUSSID, map.get("SPCL_TECH_ID"));
			return map;
		}
		//如果特殊工艺为0时，删除特殊工艺，返回空
		if(SPCL_TECH_FLAG.equals("0")){
//			不能删除主表
//			this.delete("TechorderManager.delTech", SPCL_TECH_ID);
//			add by zzb 2014-7-31 如果特殊工艺说明 没有值 则更新  (SPCL_SPEC_REMARK)特殊规格说明为空
//			spclMap.put("SPCL_SPEC_REMARK", "");
//			spclMap.put("SPCL_DTL_REMARK", "");
//			this.update("TechorderManager.update", spclMap);
			
//			this.delete("TechorderManager.delete", SPCL_TECH_ID);
			backFill(TABLE, BUSSID, "");
			//辨别是页面关闭还是没有特殊工艺 单
			map.put("SPCL_TECH_ID", "");
			return map;
		}
		//如果是使用历史记录，则不需要走数据库 直接返回值
//		if("1".equals(addFlag)){
//			return map;
//		}
		if("1".endsWith(USE_FLAG)){
			map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		}else{
			map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		}
		StringBuffer SPCL_SPEC_REMARK=new StringBuffer();
		String widthStr=WIDTH.toString();
		if(widthStr.length()>0){
			SPCL_SPEC_REMARK.append(widthStr).append(";");
		}
		String lengthStr=LENGTH.toString();
		if(lengthStr.length()>0){
			SPCL_SPEC_REMARK.append(lengthStr).append(";");
		}
		String heightStr=HEIGHT.toString();
		if(heightStr.length()>0){
			SPCL_SPEC_REMARK.append(heightStr).append(";");
		}
		String SPCL_SPEC_REMARK_STR="";
		if(SPCL_SPEC_REMARK.toString().length()>0&&"2".equals(SPCL_TECH_FLAG)){
			SPCL_SPEC_REMARK_STR="非标:"+SPCL_SPEC_REMARK.toString();
		}else if(SPCL_SPEC_REMARK.toString().length()==0&&"2".equals(SPCL_TECH_FLAG)){
			SPCL_SPEC_REMARK_STR="非标";
		}else if(SPCL_SPEC_REMARK.toString().length()>0&&"1".equals(SPCL_TECH_FLAG)){
			SPCL_SPEC_REMARK_STR="一般特殊:"+SPCL_SPEC_REMARK.toString();
		}else if(SPCL_SPEC_REMARK.toString().length()==0&&"1".equals(SPCL_TECH_FLAG)){
			SPCL_SPEC_REMARK_STR="一般特殊";
		}
		map.put("SPCL_SPEC_REMARK", SPCL_SPEC_REMARK_STR);
		String SPCL_DTL_REMARK=getTechShowStrNotPrice(addList);
		map.put("SPCL_DTL_REMARK", SPCL_DTL_REMARK);
		map.put("SPCL_DTL_REMARK_CHK", SPCL_DTL_REMARK_CHK);
		if(newFlag){
			map.put("DM_SPCL_TECH_NO", LogicUtil.getBmgz("DRP_SPCL_TECH_NO"));
			this.insert("TechorderManager.insert", map);
		}else{
			if(checkTchorderFlag(SPCL_TECH_ID)){
				this.update("TechorderManager.update", map);
				map.put("DM_SPCL_TECH_NO", this.load("TechorderManager.getDM_SPCL_TECH_NO", SPCL_TECH_ID).toString());
			}else{
				throw new ServiceException("当前工艺为生效工艺，不可编辑！");
			}
		}
		//按工艺单id删除明细所有数据
		this.delete("TechorderManager.delete", SPCL_TECH_ID);
        if (!addList.isEmpty()) {
            this.batch4Update("TechorderManager.insertChld", addList);
        }
        backFill(TABLE, BUSSID, SPCL_TECH_ID);
        return map;
	}
	//-- 0156143--Start--刘曰刚--2014-06-16//
	//修改获取渠道折扣率方式，原来从渠道折扣扁平表获取，现在改为从渠道折扣率表里获取
//	public Map<String, String> getPriceInfo(Map<String, String> params) {
//		return (Map<String, String>) this.load("TechorderManager.getPriceInfo", params);
//	}
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID){
		Object obj=this.load("TechorderManager.getChannDiscount", CHANN_ID);
		if(null!=obj){
			return obj.toString();
		}
		return null;
	}
	//-- 0156143--End--刘曰刚--2014-06-16//
	public List<Map<String, String>> getSpclByIdNotUSE_FLAG(Map<String,String> map) {
		return this.findList("TechorderManager.getSpclByIdNotUSE_FLAG", map);
	}

	public String getPrdId(String SPCL_TECH_ID) {
		Object obj = this.load("TechorderManager.getPrdId", SPCL_TECH_ID);
		if(null != obj ){
			return obj.toString();
		}else{
			return null;
		}
//		return this.load("TechorderManager.getPrdId", SPCL_TECH_ID).toString();
	}
	public void backFill(String TABLE,String BUSSID,String SPCL_TECH_ID){
		if(StringUtil.isEmpty(BUSSID)||StringUtil.isEmpty(TABLE)){
			return;
		}
		String COLUMN_NAME=this.load("TechorderManager.getColumnName", TABLE).toString();
		String BACKFILLSQL="update "+TABLE+" set SPCL_TECH_ID='"+SPCL_TECH_ID+"' where "+COLUMN_NAME+"='"+BUSSID+"'";
		this.update("TechorderManager.backFill", BACKFILLSQL);
	}
	/**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return 
	 */
	public static String subZeroAndDot(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}

	@Override
	public List<Map<String,String>> queryHistory(String querySql) {
		return this.findList("TechorderManager.queryHistory", querySql);
	}
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("TechorderManager.pageQuery", "TechorderManager.pageCount",
				params, pageNo);
	}
	/**
	 * 根据特殊工艺id查询特殊工艺
	 * @param SPCL_TECH_ID
	 * @return
	 */
	public Map<String,String> getViewSpclById(Map<String,String> map){
		return (Map<String, String>) this.load("TechorderManager.getViewSpclById", map);
	}
	
	/**
	 * 根据特殊工艺id获取特殊工艺信息
	 */
	public List<Map<String,String>> getSpclInfoById(String SPCL_TECH_ID){
		return this.findList("TechorderManager.getSpclContentById", SPCL_TECH_ID);
	}
	

	/**
	 * 获取特殊工艺编号
	 * @return
	 */
	public String getSpclNo(){
		return this.load("TechorderManager.getSpclNo","").toString();
	}
	/**
	 * 根据特殊工艺id修改特殊工艺编号
	 * @param SPCL_TECH_ID
	 * @param SPCL_TECH_NO
	 */
	public void updateSpecTechNO(String SPCL_TECH_ID,String SPCL_TECH_NO){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SPCL_TECH_ID", SPCL_TECH_ID);
		map.put("SPCL_TECH_NO", SPCL_TECH_NO);
		this.update("TechorderManager.updateSpecTechNO", map);
	}

	@Override
	public Map<String, String> getSpclDataById(String SPCL_TECH_ID) {
		// TODO Auto-generated method stub
		return (Map<String, String>)this.load("TechorderManager.getSpclDataById", SPCL_TECH_ID);
	}
	
	@Override
	public String txSpclDataByPdtNo(String specTechId,String PRD_NO) {
		// TODO Auto-generated method stub
		Map specTechNoMap =(Map<String, String>)this.load("TechorderManager.getSpclDataByPdtNo", PRD_NO);
		String newSpecTechNo = null;
		if(specTechNoMap!=null){
			newSpecTechNo =  (String)specTechNoMap.get("SPCL_TECH_NO");
		}else{
			newSpecTechNo = "0";
		}
		int ispecNo = Integer.parseInt(newSpecTechNo);
		ispecNo++;
		String strNewSpecTechNo = String.valueOf(ispecNo);
		int size=strNewSpecTechNo.length();
		StringBuffer spclNo=new StringBuffer(); 
		for (int i = size; i < 4; i++) {
			spclNo.append("0");
		}
		spclNo.append(strNewSpecTechNo);
		updateSpecTechNO(specTechId, spclNo.toString());
		return spclNo.toString();
	}
	//根据特殊工艺id查询工艺不可编辑标记  如果为0返回true 不然为false
	public boolean checkTchorderFlag(String SPCL_TECH_ID){
		String TECH_NO_EDIT_FLAG=this.load("TechorderManager.checkTchorderFlag", SPCL_TECH_ID).toString();
		if("0".equals(TECH_NO_EDIT_FLAG)){
			return true;
		}
		return false;
	}
	public boolean txUpdateSaleChld(Map<String,String> map){
		String COLUMN_NAME=this.load("TechorderManager.getColumnName", map.get("editTable")).toString();
		if("ERP_SALE_ORDER_DTL".equals(map.get("editTable"))){
			this.update("TechorderManager.upOrderBySaleId", map);
		}
		StringBuffer BACKFILLSQL=new StringBuffer();
		BACKFILLSQL.append("update ").append(map.get("editTable")).append(" set ")
				   .append(" NEW_SPEC='").append(map.get("NEW_SPEC")).append("',")
				   .append(" NEW_COLOR='").append(map.get("NEW_COLOR")).append("',")
				   .append(" PRODUCT_REMARK='").append(map.get("PRODUCT_REMARK")).append("' ")
				   .append(" where ").append(COLUMN_NAME).append("='").append(map.get("ORDERID")).append("'");
		return this.update("TechorderManager.backFill", BACKFILLSQL.toString());
	}
	//刷新所有特殊工艺主表详细特殊规格说明
	public void upSpclRemark(){
		List<String> list=this.findList("TechorderManager.getAllSpclId", "");//获取所有特殊工艺id
		StringBuffer id=new StringBuffer();
		for (String SPCL_TECH_ID : list) {
			List<Map<String,Object>> chldList=this.findList("TechorderManager.getSpclInfo", SPCL_TECH_ID) ;
			String SPCL_DTL_REMARK=getTechShowStrNotPrice(chldList);
			String SPCL_DTL_REMARK_CHK=getTechShowStrPrice(chldList);
			if(StringUtil.isEmpty(SPCL_DTL_REMARK_CHK)||StringUtil.isEmpty(SPCL_DTL_REMARK)){
				System.out.println(1);
				id.append("'").append(SPCL_TECH_ID).append("',");
			}
			Map<String,String> map=new HashMap<String, String>();
			map.put("SPCL_DTL_REMARK", SPCL_DTL_REMARK);
			map.put("SPCL_TECH_ID", SPCL_TECH_ID);
			map.put("SPCL_DTL_REMARK_CHK", SPCL_DTL_REMARK_CHK);
			this.update("TechorderManager.upSpclRemark", map);
		}
		System.out.println(id.toString());
	}
	//拼接特殊工艺说明(不带价格)
	public String getTechShowStrNotPrice(List<Map<String,Object>> list){
		//尺寸调整
		StringBuffer adjustSize=new StringBuffer();
		//部件替换
		StringBuffer unitReplace=new StringBuffer();
		//部件新增
		StringBuffer addUnit=new StringBuffer();
		//备注
		StringBuffer REMARK=new StringBuffer();
		
		StringBuffer spctchDesc=new StringBuffer();
		boolean flag=false;
		boolean adjustflag=false;
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=list.get(i);
			if("非标工艺说明".equals(map.get("SPCL_TECH_TYPE"))){
				String strRemArk = (String) map.get("REMARK");
				if(strRemArk!=null){
					REMARK.append(map.get("REMARK"));
					continue;
				}
			}
			if("尺寸调整".equals(map.get("SPCL_TECH_TYPE"))){
				adjustflag=true;
				String TechName = (String)map.get("SPCL_TECH_NAME");
				if(TechName!=null && "宽度调整".equals(TechName.trim())){
					adjustSize.append("宽:").append(map.get("CUST_TURN_VAL")).append(",");
				}else if(TechName!=null && "长度调整".equals(TechName.trim())){
					adjustSize.append("长:").append(map.get("CUST_TURN_VAL")).append(",");
				}else{
					adjustSize.append(map.get("SPCL_TECH_NAME")).append(":").append(map.get("CUST_TURN_VAL")).append(",");
				}
			}
			if("部件替换".equals(map.get("SPCL_TECH_TYPE"))){
				String currt=(String)map.get("CURRT_VAL");
				if(StringUtil.isEmpty(currt)){
					currt=" ";
				}
				unitReplace.append(currt).append(" 替换为  ").append(map.get("CUST_TURN_VAL"));
			}
			if("部件新增".equals(map.get("SPCL_TECH_TYPE"))){
				flag=true;
				addUnit.append(map.get("SPCL_TECH_NAME")).append(",");
			}
			if("一般特殊工艺描述".equals(map.get("SPCL_TECH_TYPE"))){
				String remark = (String)map.get("REMARK");
				if(remark!=null){
					spctchDesc.append(map.get("REMARK"));
				}
			}
		}
		StringBuffer explain=new StringBuffer();
		String addUnitString=addUnit.toString();
		String adjustString=adjustSize.toString();
		if(flag&&addUnit.length()>0){
			addUnitString = addUnitString.substring(0,addUnitString.length() - 1);
		}
		if(adjustflag&&adjustSize.length()>0){
			adjustString = adjustString.substring(0,adjustString.length() - 1);
		}
		if(adjustSize.toString().length()>0){
			explain.append("尺寸调整:").append(adjustSize.toString()).append(";");
		}
		if(unitReplace.toString().length()>0){
			explain.append("部件替换:").append(unitReplace.toString()).append(";");
		}
		if(!StringUtil.isEmpty(addUnitString)){
			explain.append("部件新增:").append(addUnitString).append(";");
		}
		if(spctchDesc.toString().length()>0){
			explain.append("一般特殊工艺描述:").append(spctchDesc.toString());
		}
		if(REMARK.toString().length()>0){
			explain.append("特殊工艺描述:").append(REMARK.toString()).append(";");
		}
		return explain.toString();
	}
////拼接特殊工艺说明(带价格)
	public String getTechShowStrPrice(List<Map<String,Object>> list){
		//尺寸调整
		StringBuffer adjustSize=new StringBuffer();
		//部件替换
		StringBuffer unitReplace=new StringBuffer();
		//部件新增
		StringBuffer addUnit=new StringBuffer();
		//备注
		StringBuffer REMARK=new StringBuffer();
		
		StringBuffer spctchDesc=new StringBuffer();
		boolean flag=false;
		boolean adjustflag=false;
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=list.get(i);
			if("非标工艺说明".equals(map.get("SPCL_TECH_TYPE"))){
				String strRemArk = (String) map.get("REMARK");
				if(strRemArk!=null){
					REMARK.append(map.get("REMARK"));
					continue;
				}
			}
			if("尺寸调整".equals(map.get("SPCL_TECH_TYPE"))){
				adjustflag=true;
				String TechName = (String)map.get("SPCL_TECH_NAME");
				if(TechName!=null && "宽度调整".equals(TechName.trim())){
					adjustSize.append("宽:").append(map.get("CUST_TURN_VAL")).append(",");
				}else if(TechName!=null && "长度调整".equals(TechName.trim())){
					adjustSize.append("长:").append(map.get("CUST_TURN_VAL")).append(",");
				}else{
					adjustSize.append(map.get("SPCL_TECH_NAME")).append(":").append(map.get("CUST_TURN_VAL")).append(",");
				}
				adjustSize.append("调价类型:").append(map.get("PRICE_TURN_TYPE")).append(",调价值:").append(map.get("PRICE_TURN_VAL")).append("。");
			}
			if("部件替换".equals(map.get("SPCL_TECH_TYPE"))){
				String currt=(String)map.get("CURRT_VAL");
				if(StringUtil.isEmpty(currt)){
					currt=" ";
				}
				unitReplace.append(currt).append(" 替换为  ").append(map.get("CUST_TURN_VAL"));
				unitReplace.append("调价类型:").append(map.get("PRICE_TURN_TYPE")).append(",调价值:").append(map.get("PRICE_TURN_VAL")).append("。");
			}
			if("部件新增".equals(map.get("SPCL_TECH_TYPE"))){
				flag=true;
				addUnit.append(map.get("SPCL_TECH_NAME")).append(",");
				addUnit.append("调价类型:").append(map.get("PRICE_TURN_TYPE")).append(",调价值:").append(map.get("PRICE_TURN_VAL")).append("。");
			}
			if("一般特殊工艺描述".equals(map.get("SPCL_TECH_TYPE"))){
				String remark = (String)map.get("REMARK");
				if(remark!=null){
					spctchDesc.append(map.get("REMARK"));
				}
			}
		}
		StringBuffer explain=new StringBuffer();
		String addUnitString=addUnit.toString();
		String adjustString=adjustSize.toString();
		if(flag&&addUnit.length()>0){
			addUnitString = addUnitString.substring(0,addUnitString.length() - 1);
		}
		if(adjustflag&&adjustSize.length()>0){
			adjustString = adjustString.substring(0,adjustString.length() - 1);
		}
		if(adjustSize.toString().length()>0){
			explain.append("尺寸调整:").append(adjustSize.toString()).append(";");
		}
		if(unitReplace.toString().length()>0){
			explain.append("部件替换:").append(unitReplace.toString()).append(";");
		}
		if(!StringUtil.isEmpty(addUnitString)){
			explain.append("部件新增:").append(addUnitString).append(";");
		}
		if(spctchDesc.toString().length()>0){
			explain.append("一般特殊工艺描述:").append(spctchDesc.toString());
		}
		if(REMARK.toString().length()>0){
			explain.append("特殊工艺描述:").append(REMARK.toString()).append(";");
		}
		return explain.toString();
	}
}
