/**
 * prjName:喜临门营销平台
 * ucName:门店精致化检查结果
 * fileName:TermrefinecheckModel
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
public class TermrefinecheckModel extends BaseModel{
   /** 门店精致化检查ID **/
   private String TERM_REFINE_CHECK_ID;
   /** 门店精致化检查编号 **/
   private String TERM_REFINE_CHECK_NO;
   /** 终端信息ID **/
   private String TERM_ID;
   /** 终端编号 **/
   private String TERM_NO;
   /** 终端名称 **/
   private String TERM_NAME;
   /** 品牌类型 **/
   private String BUSS_SCOPE;
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 区域经理ID **/
   private String AREA_MANAGE_ID;
   /** 区域经理名称 **/
   private String AREA_MANAGE_NAME;
   /**渠道id*/
   private String CHANN_ID;
   /**渠道NO*/
   private String CHANN_NO;
   /**渠道NAME*/
   private String CHANN_NAME;
   /** 检查机构ID **/
   private String CHECK_ORG_ID;
   /** 检查机构名称 **/
   private String CHECK_ORG_NAME;
   /** 检查日期 **/
   private String CHECK_DATE;
   /** 备注 **/
   private String REMARK;
   /** 制单人名称 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 更新人名称 **/
   private String UPD_NAME;
   /** 更新人ID **/
   private String UPDATOR;
   /** 更新时间 **/
   private String UPD_TIME;
   /** 制单部门ID **/
   private String DEPT_ID;
   /** 制单部门名称 **/
   private String DEPT_NAME;
   /** 制单机构ID **/
   private String ORG_ID;
   /** 制单机构名称 **/
   private String ORG_NAME;
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 检查任务号 **/
   private String TERM_REFINE_TASK_NO;
   /** 精致化得分 **/
   private String CHECK_TOTAL_SCORE;
   /** 神秘人评语 **/
   private String MYSTIC_CMNR;
   /** 主要扣分问题**/
   private String MAIN_DEDUCT_SCORE_REMARK;
   
   private String CHECK_PROGRAM_NO;
   
   private String PIC_PATH;
   
   public String getPIC_PATH() {
	return PIC_PATH;
	}
   public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
		public String getCHECK_PROGRAM_NO() {
		return CHECK_PROGRAM_NO;
	}
	public void setCHECK_PROGRAM_NO(String check_program_no) {
		CHECK_PROGRAM_NO = check_program_no;
	}
	public String getMYSTIC_CMNR() {
	return MYSTIC_CMNR;
	}
	public void setMYSTIC_CMNR(String mystic_cmnr) {
		MYSTIC_CMNR = mystic_cmnr;
	}
	public String getMAIN_DEDUCT_SCORE_REMARK() {
		return MAIN_DEDUCT_SCORE_REMARK;
	}
	public void setMAIN_DEDUCT_SCORE_REMARK(String main_deduct_score_remark) {
		MAIN_DEDUCT_SCORE_REMARK = main_deduct_score_remark;
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
     * get 门店精致化检查编号 value
     * @return the TERM_REFINE_CHECK_NO
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_REFINE_CHECK_NO(){
        return TERM_REFINE_CHECK_NO;
    }
	/**
     * set 门店精致化检查编号 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_REFINE_CHECK_NO(String TERM_REFINE_CHECK_NO){
        this.TERM_REFINE_CHECK_NO=TERM_REFINE_CHECK_NO;
    }
     /**
     * get 终端信息ID value
     * @return the TERM_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_ID(){
        return TERM_ID;
    }
	/**
     * set 终端信息ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_ID(String TERM_ID){
        this.TERM_ID=TERM_ID;
    }
     /**
     * get 终端编号 value
     * @return the TERM_NO
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_NO(){
        return TERM_NO;
    }
	/**
     * set 终端编号 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_NO(String TERM_NO){
        this.TERM_NO=TERM_NO;
    }
     /**
     * get 终端名称 value
     * @return the TERM_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getTERM_NAME(){
        return TERM_NAME;
    }
	/**
     * set 终端名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setTERM_NAME(String TERM_NAME){
        this.TERM_NAME=TERM_NAME;
    }
     /**
     * get 品牌类型 value
     * @return the BUSS_SCOPE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getBUSS_SCOPE(){
        return BUSS_SCOPE;
    }
	/**
     * set 品牌类型 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setBUSS_SCOPE(String BUSS_SCOPE){
        this.BUSS_SCOPE=BUSS_SCOPE;
    }
     /**
     * get 区域ID value
     * @return the AREA_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getAREA_ID(){
        return AREA_ID;
    }
	/**
     * set 区域ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setAREA_ID(String AREA_ID){
        this.AREA_ID=AREA_ID;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 区域名称 value
     * @return the AREA_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 区域名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 区域经理ID value
     * @return the AREA_MANAGE_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getAREA_MANAGE_ID(){
        return AREA_MANAGE_ID;
    }
	/**
     * set 区域经理ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setAREA_MANAGE_ID(String AREA_MANAGE_ID){
        this.AREA_MANAGE_ID=AREA_MANAGE_ID;
    }
     /**
     * get 区域经理名称 value
     * @return the AREA_MANAGE_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getAREA_MANAGE_NAME(){
        return AREA_MANAGE_NAME;
    }
	/**
     * set 区域经理名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setAREA_MANAGE_NAME(String AREA_MANAGE_NAME){
        this.AREA_MANAGE_NAME=AREA_MANAGE_NAME;
    }
     /**
     * get 检查机构ID value
     * @return the CHECK_ORG_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCHECK_ORG_ID(){
        return CHECK_ORG_ID;
    }
	/**
     * set 检查机构ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCHECK_ORG_ID(String CHECK_ORG_ID){
        this.CHECK_ORG_ID=CHECK_ORG_ID;
    }
     /**
     * get 检查机构名称 value
     * @return the CHECK_ORG_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCHECK_ORG_NAME(){
        return CHECK_ORG_NAME;
    }
	/**
     * set 检查机构名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCHECK_ORG_NAME(String CHECK_ORG_NAME){
        this.CHECK_ORG_NAME=CHECK_ORG_NAME;
    }
     /**
     * get 检查日期 value
     * @return the CHECK_DATE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCHECK_DATE(){
        return CHECK_DATE;
    }
	/**
     * set 检查日期 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCHECK_DATE(String CHECK_DATE){
        this.CHECK_DATE=CHECK_DATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-01-26 14:46:31
     * @author lyg
	 * @createdate 2014-01-26 14:46:31
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
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
	/**
	 * @return the cHANN_ID
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	/**
	 * @param cHANNID the cHANN_ID to set
	 */
	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}
	/**
	 * @return the cHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	/**
	 * @param cHANNNO the cHANN_NO to set
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}
	/**
	 * @return the cHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	/**
	 * @param cHANNNAME the cHANN_NAME to set
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}
	public String getTERM_REFINE_TASK_NO() {
		return TERM_REFINE_TASK_NO;
	}
	public void setTERM_REFINE_TASK_NO(String term_refine_task_no) {
		TERM_REFINE_TASK_NO = term_refine_task_no;
	}
	public String getCHECK_TOTAL_SCORE() {
		return CHECK_TOTAL_SCORE;
	}
	public void setCHECK_TOTAL_SCORE(String check_total_score) {
		CHECK_TOTAL_SCORE = check_total_score;
	}
    
}