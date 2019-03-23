package com.hoperun.drp.base.drppromoteprice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.drppromoteprice.model.DrppromotepriceModel;
import com.hoperun.sys.model.UserBean;

public interface DrppromotepriceService extends IBaseService {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    //编辑
    public void txEdit(List<DrppromotepriceModel> model, UserBean userBean,String PROMOTE_ID);
}
