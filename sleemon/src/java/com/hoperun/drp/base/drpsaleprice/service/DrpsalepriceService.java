package com.hoperun.drp.base.drpsaleprice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.drpsaleprice.model.DrpsalepriceModel;
import com.hoperun.sys.model.UserBean;

public interface DrpsalepriceService extends IBaseService {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param);
    //编辑
    public void txEdit(List<DrpsalepriceModel> model, UserBean userBean);
    public long getCount(Map<String,String> map);
    public List queryPriceExp(Map<String,String> map);
    
    /**
     * 导入
     * @param list
     * @param userBean
     */
    public void txImportExcel(List list,UserBean userBean);
}
