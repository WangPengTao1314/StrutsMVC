package com.hoperun.drp.oamg.channscoremkm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmDtlModel;
import com.hoperun.drp.oamg.channscoremkm.model.ChannScoreMkmModel;
import com.hoperun.drp.oamg.channscoremkm.model.ScoreStandModel;
import com.hoperun.drp.oamg.channscoremkm.service.ChannScoreMkmService;
import com.hoperun.sys.model.UserBean;

public class ChannScoreMkmServiceImpl extends BaseService implements ChannScoreMkmService{
   
	/***
	 * @param CHANN_SCORE_MKM_ID
	 * @param scoreMkm
	 * @param userBean
	 */
	public void txEdit(String CHANN_SCORE_MKM_ID,ChannScoreMkmModel  scoreMkm ,ScoreStandModel scoreStand,
			ChannScoreMkmDtlModel scoreMkmdtl,UserBean userBean){
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_ID",   scoreMkm.getMKM_ID());
		params.put("MKM_NAME", scoreMkm.getMKM_NAME());
		params.put("SCORE_ID", scoreMkm.getSCORE_ID());
        params.put("SCORE_NAME", scoreMkm.getSCORE_NAME());
        params.put("SCORE_DATE", scoreMkm.getSCORE_DATE());
        params.put("SCORE_TOTAL",scoreMkm.getSCORE_TOTAL());
        
        List<String> list = getScoreOption(scoreStand);
        List<String> libyOption1 = getOptionDescs1(scoreStand);
        List<String> libyOption2 = getOptionDescs2(scoreStand);
        List<String> libyOption3 = getOptionDescs3(scoreStand);
        List<String> libyOption4 = getOptionDescs4(scoreStand);
        List<String> libyScoreval= getScoreVal(scoreStand);
        
        String UID = StringUtil.uuid32len();
        String UIDScore = StringUtil.uuid32len();
		if (StringUtils.isEmpty(CHANN_SCORE_MKM_ID)) {
			params.put("DEL_FLAG", "0");
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM()); // 制单人名称
			params.put("CRE_TIME", DateUtil.now()); // 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID()); // 制单机构id
			params.put("ORG_NAME", userBean.getJGMC()); // 制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); // 帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); // 帐套名称
			params.put("CHANN_SCORE_MKM_ID", UID);
			params.put("CHANN_SCORE_MKM_NO", LogicUtil.getBmgz("DRP_CHANN_SCORE_MKM_NO"));
			params.put("STATE", "未提交");
			this.insert("channScoreMkm.insert", params);
			
			//评分标准
			Map<String, String> paramsbyscore = new HashMap<String, String>();
			String SCORE_STAND_NO = LogicUtil.getBmgz("DRP_SCORE_STAND_NO");
			paramsbyscore.put("SCORE_STAND_NO", SCORE_STAND_NO);
			String UIDScoreID = StringUtil.uuid32len();
	        for(int i=0;i<7;i++){
	        	paramsbyscore.put("SCORE_STAND_ID",UIDScoreID.substring(0, UIDScoreID.lastIndexOf("1"))+i);
	        	paramsbyscore.put("SCORE_OPTION",String.valueOf(list.get(i)));
	        	paramsbyscore.put("OPTION_DESC1",String.valueOf(libyOption1.get(i)));
	        	paramsbyscore.put("OPTION_DESC2",String.valueOf(libyOption2.get(i)));
	        	paramsbyscore.put("OPTION_DESC3",String.valueOf(libyOption3.get(i)));
	        	paramsbyscore.put("OPTION_DESC4",String.valueOf(libyOption4.get(i)));
	        	paramsbyscore.put("SCORE_VAL",   String.valueOf(libyScoreval.get(i)));
	        	paramsbyscore.put("SCORE_TYPE",  "评分标准");
	        	paramsbyscore.put("SORT_NO", String.valueOf(i+1));
	            this.insert("scoreStand.insert", paramsbyscore);
	        }
	        
	        //加盟商营销经理详细
	        
	         List<String> libyMkmdtl= getOptionScore(scoreMkmdtl);
	         List<String> libyScoreDesc = getScoreDesc(scoreMkmdtl);
	         List<String> libyOptionScore = getOptionScores(scoreMkmdtl);
	         String mkmDtlID = StringUtil.uuid32len();
	         params.put("CHANN_SCORE_MKM_ID", UID);
	         for(int j=0;j<7;j++){
	        	 params.put("CHANN_SCORE_MKM_DTL_ID", mkmDtlID.substring(0,mkmDtlID.lastIndexOf("1"))+j);
	        	 params.put("SCORE_STAND_ID", UIDScoreID.substring(0, UIDScoreID.lastIndexOf("1"))+j);//UIDScore);
	        	 params.put("OPTION_SCORE", String.valueOf(libyMkmdtl.get(j)));
	        	 if(j==0){
	        		 if(libyScoreDesc.get(j).equals("如:该营销经理某月某日来我市场拜访,驻留时间为*天/*小时")){
	        			 params.put("SCORE_DESC", "");
	        		 }else{
	        			 String desc = String.valueOf(libyScoreDesc.get(j)).toString();
	        			 desc = desc.replaceAll("\r\n","<br>"); 
	        			 params.put("SCORE_DESC", desc);
	        		 }
	        	 }
	        	 if(j==1){
	        		if(libyScoreDesc.get(j).equals("如：该营销经理能主动积极将相关资料送达，并主动讲解清晰")){
	        			params.put("SCORE_DESC", "");
	        		}else{
	        			params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
	        		}
	        	 }
	        	 if(j==2){
	        		 if(libyScoreDesc.get(j).equals("如：该营销经理能及时传达精致化标准，并且现场指导执行，并且培训导购员落实精致化，并亲身示范")){
	        			 params.put("SCORE_DESC", "");
	        		 }else{
		        	     params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
		        	 }
	        	 }
	        	 if(j==3){
	        		 if(libyScoreDesc.get(j).equals("如：该营销经理在该月培训导购员一共XX场，且效果良好")){
	        			 params.put("SCORE_DESC", ""); 
	        		 }else{
		        	     params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
		        	 }
	        	 }
	        	 if(j==4){
	        		 if(libyScoreDesc.get(j).equals("如：该营销经理主导制定方案，积极参与推广活动，但未能达到预期目标")){
	        			 params.put("SCORE_DESC", ""); 
	        		 }else{
		        	     params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
		        	 }
	        	 }
	        	 if(j==5){
	        		if(libyScoreDesc.get(j).equals("如：该营销经理主导并主动协助客户开发，开发的分销质量优异，对销量帮助很大")){
	        			params.put("SCORE_DESC", ""); 
	        		}else{
	        			params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
	        		}
	        	 }
	        	 if(j==6){
	        		if(libyScoreDesc.get(j).equals("如：该营销经理对客户反映的市场问题能快速反应，积极分析问题，提出方案，整合资源并落实")){
	        			params.put("SCORE_DESC", ""); 
	        		}else{
	        			params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(j)));
	        		}
	        	 }
	        	 this.insert("channScoreMkmDtl.insert", params);
	         }
		} else{
			
			params.put("CHANN_SCORE_MKM_ID", CHANN_SCORE_MKM_ID);
			params.put("UPDATOR", userBean.getXTYHID());// 更新人id
			params.put("UPD_NAME", userBean.getXM());// 更新人名称
			params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
			this.update("channScoreMkm.updateById", params);
			
			/***
			 * 根据营销经理评分ID查询到详细ID和评分标准ID
			 */
			 List<String> ids=queryDtlId(CHANN_SCORE_MKM_ID);
			 List<String> libyScoreDesc = getScoreDesc(scoreMkmdtl);
			 List<String> libyOptionScore = getOptionScores(scoreMkmdtl);
			 for(int i=0;i<7;i++){
				 params.put("CHANN_SCORE_MKM_DTL_ID", ids.get(i));
				 params.put("SCORE_DESC", String.valueOf(libyScoreDesc.get(i)));
				 params.put("OPTION_SCORE", String.valueOf(libyOptionScore.get(i)));
				 this.update("channScoreMkmDtl.updateById", params);
			 }
		}
	} 
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @param userBean
	 * @return
	 */
	public boolean txDelete(String CHANN_SCORE_MKM_ID, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("CHANN_SCORE_MKM_ID", CHANN_SCORE_MKM_ID);
		params.put("UPDATOR", userBean.getXTYHID());// 更新人id
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		return update("channScoreMkm.delete", params);
	}

	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo){
		return this.pageQuery("channScoreMkm.pageQuery",
				"channScoreMkm.pageCount", params, pageNo);
	}
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String CHANN_SCORE_MKM_ID){
		return (Map<String, String>) load("channScoreMkm.loadById",
				CHANN_SCORE_MKM_ID);
	}
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @return
	 */
	public List<String> loadbydtl(String CHANN_SCORE_MKM_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_SCORE_MKM_ID", CHANN_SCORE_MKM_ID);
        return this.findList("channScoreMkmDtl.loadById", params);
	}
	
	public List<String> queryDtlId(String CHANN_SCORE_MKM_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_SCORE_MKM_ID", CHANN_SCORE_MKM_ID);
        return this.findList("channScoreMkmDtl.queryDtlId", params);
	}
	
	/**
	 * @param CHANN_SCORE_MKM_ID
	 * @return
	 */
	public List<String> queryOptionScore(String CHANN_SCORE_MKM_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_SCORE_MKM_ID", CHANN_SCORE_MKM_ID);
        return this.findList("channScoreMkmDtl.queryOptionScore", params);
	}
	
	/**
	 * @param CHANN_RNVTN_ID
	 * @return
	 */
	public Map<String, String>  qeryMkm(String CHANN_ID){
		 return (Map<String, String>) load("channScoreMkm.qeryMkm",
				 CHANN_ID);
	}
	
	public  List<String> getOptionDescs1(ScoreStandModel scoreStand){
		List<String> list = new ArrayList();
		list.add(scoreStand.getOPTION_DESC1_1());
		list.add(scoreStand.getOPTION_DESC1_2());
		list.add(scoreStand.getOPTION_DESC1_3());
		list.add(scoreStand.getOPTION_DESC1_4());
		list.add(scoreStand.getOPTION_DESC1_5());
		list.add(scoreStand.getOPTION_DESC1_6());
		list.add(scoreStand.getOPTION_DESC1_7());
		return list;
	} 
	
	public  List<String> getOptionDescs2(ScoreStandModel scoreStand){
		
		List<String> list = new ArrayList();
		list.add(scoreStand.getOPTION_DESC2_1());
		list.add(scoreStand.getOPTION_DESC2_2());
		list.add(scoreStand.getOPTION_DESC2_3());
		list.add(scoreStand.getOPTION_DESC2_4());
		list.add(scoreStand.getOPTION_DESC2_5());
		list.add(scoreStand.getOPTION_DESC2_6());
		list.add(scoreStand.getOPTION_DESC2_7());
		return list;
	}
	
    public  List<String> getOptionDescs3(ScoreStandModel scoreStand){
		
		List<String> list = new ArrayList();
		list.add(scoreStand.getOPTION_DESC3_1());
		list.add(scoreStand.getOPTION_DESC3_2());
		list.add(scoreStand.getOPTION_DESC3_3());
		list.add(scoreStand.getOPTION_DESC3_4());
		list.add(scoreStand.getOPTION_DESC3_5());
		list.add(scoreStand.getOPTION_DESC3_6());
		list.add(scoreStand.getOPTION_DESC3_7());
		return list;
	}
    
    public  List<String> getOptionDescs4(ScoreStandModel scoreStand){
		
		List<String> list = new ArrayList();
		list.add(scoreStand.getOPTION_DESC4_1());
		list.add(scoreStand.getOPTION_DESC4_2());
		list.add(scoreStand.getOPTION_DESC4_3());
		list.add(scoreStand.getOPTION_DESC4_4());
		list.add(scoreStand.getOPTION_DESC4_5());
		list.add(scoreStand.getOPTION_DESC4_6());
		list.add(scoreStand.getOPTION_DESC4_7());
		return list;
	}
    
    public List<String> getScoreVal(ScoreStandModel scoreStand){
    	List<String> list = new ArrayList();
        list.add(scoreStand.getSCORE_VAL1());
        list.add(scoreStand.getSCORE_VAL2());
        list.add(scoreStand.getSCORE_VAL3());
        list.add(scoreStand.getSCORE_VAL4());
        list.add(scoreStand.getSCORE_VAL5());
        list.add(scoreStand.getSCORE_VAL6());
        list.add(scoreStand.getSCORE_VAL7());
    	return list;
    }
    
    public List<String> getScoreOption(ScoreStandModel scoreStand){
    	
    	 List<String> list = new ArrayList();
         list.add(scoreStand.getSCORE_OPTION1());
         list.add(scoreStand.getSCORE_OPTION2());
         list.add(scoreStand.getSCORE_OPTION3());
         list.add(scoreStand.getSCORE_OPTION4());
         list.add(scoreStand.getSCORE_OPTION5());
         list.add(scoreStand.getSCORE_OPTION6());
         list.add(scoreStand.getSCORE_OPTION7());
         return list;
    }
    
    //单项得分
    public List<String> getOptionScore(ChannScoreMkmDtlModel scoreMkmdtl){
    	 List<String> list = new ArrayList();
    	 list.add(scoreMkmdtl.getOPTION_SCORE1());
    	 list.add(scoreMkmdtl.getOPTION_SCORE2());
    	 list.add(scoreMkmdtl.getOPTION_SCORE3());
    	 list.add(scoreMkmdtl.getOPTION_SCORE4());
    	 list.add(scoreMkmdtl.getOPTION_SCORE5());
    	 list.add(scoreMkmdtl.getOPTION_SCORE6());
    	 list.add(scoreMkmdtl.getOPTION_SCORE7());
    	 return list;
    }
    
    //评价描述
    public List<String> getScoreDesc(ChannScoreMkmDtlModel scoreMkmdtl){
    	List<String> list = new ArrayList();
    	list.add(scoreMkmdtl.getSCORE_DESC1());
    	list.add(scoreMkmdtl.getSCORE_DESC2());
    	list.add(scoreMkmdtl.getSCORE_DESC3());
    	list.add(scoreMkmdtl.getSCORE_DESC4());
    	list.add(scoreMkmdtl.getSCORE_DESC5());
    	list.add(scoreMkmdtl.getSCORE_DESC6());
    	list.add(scoreMkmdtl.getSCORE_DESC7());
    	return list;
    }
    
    //单项得分
    public List<String> getOptionScores(ChannScoreMkmDtlModel scoreMkmdtl){
    	List<String> list = new ArrayList();
    	list.add(scoreMkmdtl.getOPTION_SCORE1());
    	list.add(scoreMkmdtl.getOPTION_SCORE2());
    	list.add(scoreMkmdtl.getOPTION_SCORE3());
    	list.add(scoreMkmdtl.getOPTION_SCORE4());
    	list.add(scoreMkmdtl.getOPTION_SCORE5());
    	list.add(scoreMkmdtl.getOPTION_SCORE6());
    	list.add(scoreMkmdtl.getOPTION_SCORE7());
    	return list;
    }
}
