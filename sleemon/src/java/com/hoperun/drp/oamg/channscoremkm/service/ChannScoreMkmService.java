package com.hoperun.drp.oamg.channscoremkm.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmDtlModel;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmModel;
import com.hoperun.drp.oamg.channscoremkm.model.ScoreStandModel;
import com.hoperun.sys.model.UserBean;

public interface ChannScoreMkmService  extends IBaseService {
    
	/***
	 * @param CHANN_SCORE_MKM_ID
	 * @param scoreMkm
	 * @param userBean
	 */
	public void txEdit(String CHANN_SCORE_MKM_ID,ChannScoreMkmModel  scoreMkm ,
			            ScoreStandModel scoreStand,ChannScoreMkmDtlModel scoreMkmdtl,UserBean userBean);
	
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String CHANN_SCORE_MKM_ID);
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @return
	 */
	public List<String> loadbydtl(String CHANN_SCORE_MKM_ID);
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @param userBean
	 * @return
	 */
	public boolean txDelete(String CHANN_SCORE_MKM_ID, UserBean userBean);
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @return
	 */
	public List<String> queryOptionScore(String CHANN_SCORE_MKM_ID);
	
    /**
     * @param CHANN_SCORE_MKM_ID
     * @return
     */
	public List<String> queryDtlId(String CHANN_SCORE_MKM_ID);
	
	/**
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public  Map<String, String>  qeryMkm(String CHANN_ID);
}
