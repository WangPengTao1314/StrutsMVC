package com.hoperun.erp.sale.channdiscount.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.channdiscount.model.ChannDiscountModel;

public interface ChannDiscountService extends IBaseService {
	public List getChannInfo(Map<String,String> param);

	public void txInsertDsct(String CHANN_DSCT_IDS,List<ChannDiscountModel> modelList,String DECT_TYPE);
}
