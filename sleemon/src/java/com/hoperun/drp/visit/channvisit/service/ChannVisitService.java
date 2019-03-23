package com.hoperun.drp.visit.channvisit.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.visit.channvisit.model.ChannVisitDtlModel;
import com.hoperun.drp.visit.channvisit.model.ChannVisitModel;
import com.hoperun.sys.model.UserBean;

public interface ChannVisitService extends IBaseService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
     public IListPage pageQuery(Map <String, String> params, int pageNo);
     
     /**
      * @param params
      * @param pageNo
      * @return
      */
     public IListPage pageQueryWH(Map <String, String> params, int pageNo);
     
     /**
      * @param params
      * @param pageNo
      * @return
      */
     public IListPage pageQueryT(Map <String, String> params, int pageNo);

     /**
      * @param CHANN_VISIT_ID
      * @return
      */
     public List<ChannVisitDtlModel> childQuery(String CHANN_VISIT_ID);

     /**
      * @param CHANN_VISIT_ID
      * @return
      */
     public List<ChannVisitDtlModel> childQueryT(String CHANN_VISIT_ID);

     /**
      * @param CHANN_VISIT_ID
      * @param obj
      * @param userBean
      */
     public void txEdit(String CHANN_VISIT_ID, ChannVisitModel obj, UserBean userBean);
     
     /**
      * @param CHANN_VISIT_ID
      * @param obj
      * @param userBean
      * @param EME_DEGREE
      */
     public void txEditT(String CHANN_VISIT_ID, ChannVisitModel obj, UserBean userBean,String EME_DEGREE);
     
     /**
      * @param CHANN_VISIT_DTL_ID
      * @return
      */
     public List<ChannVisitDtlModel> loadChilds(String CHANN_VISIT_DTL_ID);

     /**
      * @param CHANN_VISIT_ID
      * @param model
      */
     public void insertChild(String CHANN_VISIT_ID, ChannVisitDtlModel model);
     
     /**
      * @param CHANN_VISIT_DTL_ID
      * @param model
      */
     public void updateChild(String CHANN_VISIT_DTL_ID,ChannVisitDtlModel model);
     
     /**
      * 子表的批量删除
      * Description :.
      * @param sjzdmxids the sjzdmxids
      * @param userBean the user bean
      */
     public void txBatch4DeleteChild(String CHANN_VISIT_DTL_IDS, String CHANN_VISIT_ID,UserBean userBean);
     
     /**
      * @param CHANN_VISIT_ID
      * @return
      */
     public Map<String,String> loadByConfId(String CHANN_VISIT_ID);
     
     /**
      * @param CHANN_VISIT_ID
      * @param userBean
      */
     public void txDelete(String CHANN_VISIT_ID,UserBean userBean);
     
     /**
      * 查询数据字典信息
      * @param DATA_DTL_ID
      * @return
      */
     public String queryPro(String DATA_DTL_ID);
     
     /**
 	 * 导出excel
 	 * @return
 	 */
 	 public List expertExcelQuery(Map<String,String> params);
 	 
 	 /**
 	  * @param params
 	  * @return
 	  */
 	 public List expertExcelQuerySH(Map<String,String> params);
}
