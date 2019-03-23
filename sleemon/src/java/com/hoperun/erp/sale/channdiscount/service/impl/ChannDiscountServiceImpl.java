package com.hoperun.erp.sale.channdiscount.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.channdiscount.model.ChannDiscountModel;
import com.hoperun.erp.sale.channdiscount.service.ChannDiscountService;

public class ChannDiscountServiceImpl extends BaseService implements ChannDiscountService {

	@Override
	public List getChannInfo(Map<String, String> param) {
		// TODO Auto-generated method stub
		return this.findList("Channdiscount.getChannInfo", param);
	}

	@Override
	public void txInsertDsct(String CHANN_DSCT_IDS,	List<ChannDiscountModel> modelList,String DECT_TYPE) {
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
		for (ChannDiscountModel model : modelList) {
			Map<String,String> map=new HashMap<String, String>();
			String CHANN_DSCT_ID=model.getCHANN_DSCT_ID();
			map.put("DECT_RATE", model.getDECT_RATE());
			if(StringUtil.isEmpty(CHANN_DSCT_ID)){
				CHANN_DSCT_ID=StringUtil.uuid32len();
				map.put("CHANN_DSCT_ID", CHANN_DSCT_ID);
				map.put("CHANN_ID", model.getCHANN_ID());
				map.put("CHANN_NO", model.getCHANN_NO());
				map.put("CHANN_NAME", model.getCHANN_NAME());
				map.put("CHANN_TYPE", model.getCHANN_TYPE());
				map.put("CHAA_LVL", model.getCHAA_LVL());
				map.put("DECT_TYPE", DECT_TYPE);
				addList.add(map);
			}else{
				map.put("CHANN_DSCT_ID", CHANN_DSCT_ID);
				upList.add(map);
			}
		}
//		if(!StringUtil.isEmpty(CHANN_DSCT_IDS)){
//			this.txDelete(CHANN_DSCT_IDS);
//		}
		if (!addList.isEmpty()) {
			this.batch4Update("Channdiscount.insert", addList);
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Channdiscount.update", upList);
		}
	}
	public boolean txDelete(String CHANN_DSCT_IDS) {
		return this.delete("Channdiscount.delete", CHANN_DSCT_IDS);
	}
}
