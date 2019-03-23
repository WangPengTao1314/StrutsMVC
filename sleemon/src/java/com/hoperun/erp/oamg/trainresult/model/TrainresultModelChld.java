/**
 * prjName:喜临门营销平台
 * ucName:渠道培训结果反馈
 * fileName:TrainresultModelChld
 */
package com.hoperun.erp.oamg.trainresult.model;

import com.hoperun.commons.model.BaseModel;

/**
 *@serviceImpl 
 *@func 
 *@version 1.1 
 *@author ghx 
 *@createdate 2014-07-10
 */
public class TrainresultModelChld extends BaseModel {
	/** 培训申请单培训结果明细ID **/
	private String TRAIN_RESULT_DTL_ID;
	/** 培训申请单ID **/
	private String TRAIN_REQ_ID;
	/** 培训人员编号 **/
	private String TRAIN_PER_NO;
	/** 培训人员姓名 **/
	private String TRAIN_PER_NAME;
	/** 所在部门/渠道/终端 **/
	private String THE_ORGAN;
	/** 岗位 **/
	private String THE_POST;
	/** 培训签到情况 **/
	private String SIGN_STATE;
	/** 培训过程表现情况 **/
	private String TRAIN_PER;
	/** 培训考核情况 **/
	private String TRAIN_ASSES;
	/** 培训总体情况 **/
	private String TRAIN_OVERALL;

	/** 备注 **/
	private String REMARK;
	/** 状态 **/
	private String STATE;
	/** 删除标记 **/
	private String DEL_FLAG;

	public String getTRAIN_RESULT_DTL_ID() {
		return TRAIN_RESULT_DTL_ID;
	}

	public void setTRAIN_RESULT_DTL_ID(String tRAINRESULTDTLID) {
		TRAIN_RESULT_DTL_ID = tRAINRESULTDTLID;
	}

	public String getTRAIN_REQ_ID() {
		return TRAIN_REQ_ID;
	}

	public void setTRAIN_REQ_ID(String tRAINREQID) {
		TRAIN_REQ_ID = tRAINREQID;
	}

	public String getTRAIN_PER_NO() {
		return TRAIN_PER_NO;
	}

	public void setTRAIN_PER_NO(String tRAINPERNO) {
		TRAIN_PER_NO = tRAINPERNO;
	}

	public String getTRAIN_PER_NAME() {
		return TRAIN_PER_NAME;
	}

	public void setTRAIN_PER_NAME(String tRAINPERNAME) {
		TRAIN_PER_NAME = tRAINPERNAME;
	}

	public String getTHE_ORGAN() {
		return THE_ORGAN;
	}

	public void setTHE_ORGAN(String tHEORGAN) {
		THE_ORGAN = tHEORGAN;
	}

	public String getTHE_POST() {
		return THE_POST;
	}

	public void setTHE_POST(String tHEPOST) {
		THE_POST = tHEPOST;
	}

	public String getSIGN_STATE() {
		return SIGN_STATE;
	}

	public void setSIGN_STATE(String sIGNSTATE) {
		SIGN_STATE = sIGNSTATE;
	}

	public String getTRAIN_PER() {
		return TRAIN_PER;
	}

	public void setTRAIN_PER(String tRAINPER) {
		TRAIN_PER = tRAINPER;
	}

	public String getTRAIN_ASSES() {
		return TRAIN_ASSES;
	}

	public void setTRAIN_ASSES(String tRAINASSES) {
		TRAIN_ASSES = tRAINASSES;
	}

	public String getTRAIN_OVERALL() {
		return TRAIN_OVERALL;
	}

	public void setTRAIN_OVERALL(String tRAINOVERALL) {
		TRAIN_OVERALL = tRAINOVERALL;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}