package com.hoperun.drp.base.retalprice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.retalprice.model.RetalpriceModel;
import com.hoperun.sys.model.UserBean;

public interface RetalpriceService extends IBaseService {
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
    public void txEdit(List<RetalpriceModel> model, UserBean userBean);
    public Map<String,String> loadPRO(String PRD_ID);
    public long getCount(Map<String,String> map);
}
