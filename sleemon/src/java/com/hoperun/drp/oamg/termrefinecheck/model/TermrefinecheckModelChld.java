/**
 * prjName:喜临门营销平台
 * ucName:门店精致化检查结果
 * fileName:TermrefinecheckModelChld
*/
package com.hoperun.drp.oamg.termrefinecheck.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-01-26 14:46:31
 */
public class TermrefinecheckModelChld extends BaseModel{
   /** 门店精致化检查明细ID **/
   private String TERM_REFINE_CHECK_DTL_ID;
   /** 门店精致化检查ID **/
   private String TERM_REFINE_CHECK_ID;
   /** 门店精致化检查项目类型 **/
   private String PRJ_TYPE;
   /** 检查项目编号 **/
   private String PRJ_CODE;
   /** 检查项目名称 **/
   private String PRJ_NAME;
   /** 项目分值 **/
   private String PRJ_SCORE;
   /** 检查结果得分 **/
   private String CHECK_SCORE;
   /** 检查结果 **/
   private String CHECK_REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 检查结果最终得分 **/
   private String CHECK_FINISH_SCORE;
   
     /**
     * get 门店精致化检查明细ID value
     * @return the TERM_REFINE_CHECK_DTL_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_REFINE_CHECK_DTL_ID(){
        return TERM_REFINE_CHECK_DTL_ID;
    }
	/**
     * set 门店精致化检查明细ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_REFINE_CHECK_DTL_ID(String TERM_REFINE_CHECK_DTL_ID){
        this.TERM_REFINE_CHECK_DTL_ID=TERM_REFINE_CHECK_DTL_ID;
    }
     /**
     * get 门店精致化检查ID value
     * @return the TERM_REFINE_CHECK_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_REFINE_CHECK_ID(){
        return TERM_REFINE_CHECK_ID;
    }
	/**
     * set 门店精致化检查ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_REFINE_CHECK_ID(String TERM_REFINE_CHECK_ID){
        this.TERM_REFINE_CHECK_ID=TERM_REFINE_CHECK_ID;
    }
     /**
     * get 门店精致化检查项目类型 value
     * @return the PRJ_TYPE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getPRJ_TYPE(){
        return PRJ_TYPE;
    }
	/**
     * set 门店精致化检查项目类型 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setPRJ_TYPE(String PRJ_TYPE){
        this.PRJ_TYPE=PRJ_TYPE;
    }
     /**
     * get 检查项目编号 value
     * @return the PRJ_CODE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getPRJ_CODE(){
        return PRJ_CODE;
    }
	/**
     * set 检查项目编号 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setPRJ_CODE(String PRJ_CODE){
        this.PRJ_CODE=PRJ_CODE;
    }
     /**
     * get 检查项目名称 value
     * @return the PRJ_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getPRJ_NAME(){
        return PRJ_NAME;
    }
	/**
     * set 检查项目名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setPRJ_NAME(String PRJ_NAME){
        this.PRJ_NAME=PRJ_NAME;
    }
     /**
     * get 项目分值 value
     * @return the PRJ_SCORE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getPRJ_SCORE(){
        return PRJ_SCORE;
    }
	/**
     * set 项目分值 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setPRJ_SCORE(String PRJ_SCORE){
        this.PRJ_SCORE=PRJ_SCORE;
    }
     /**
     * get 检查结果得分 value
     * @return the CHECK_SCORE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCHECK_SCORE(){
        return CHECK_SCORE;
    }
	/**
     * set 检查结果得分 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCHECK_SCORE(String CHECK_SCORE){
        this.CHECK_SCORE=CHECK_SCORE;
    }
     /**
     * get 检查结果 value
     * @return the CHECK_REMARK
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCHECK_REMARK(){
        return CHECK_REMARK;
    }
	/**
     * set 检查结果 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCHECK_REMARK(String CHECK_REMARK){
        this.CHECK_REMARK=CHECK_REMARK;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getCHECK_FINISH_SCORE() {
		return CHECK_FINISH_SCORE;
	}
	public void setCHECK_FINISH_SCORE(String cHECKFINISHSCORE) {
		CHECK_FINISH_SCORE = cHECKFINISHSCORE;
	}
    
    
}