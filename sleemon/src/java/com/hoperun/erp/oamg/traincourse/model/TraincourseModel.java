package com.hoperun.erp.oamg.traincourse.model;

import com.hoperun.commons.model.BaseModel;

public class TraincourseModel extends BaseModel {
	
	private static final long serialVersionUID = -3671598104654899486L;
	
	private String TRAIN_COURSE_ID;  // 培训课程ID	
	private String TRAIN_COURSE_NO;  //培训课程编号
	private String TRAIN_COURSE_NAME ;  //培训课程名称
	private String TRAIN_TYPE ;  //培训类型 
	private String FIXED_PSON_NUM ;  //额定人数
	private String TRAIN_TIME_BEG ;  //培训时间从
	private String TRAIN_TIME_END ;  //培训时间到
	private String TRAIN_ADDR ;  //培训地点 
	private String TRAIN_GOAL ;  //培训目的
	private String TRAIN_CONTENT ;  //培训内容
	private String STATE;  //状态
	private String REMARK;  //备注
		
	private String PIC_PATH; //图片路径
	
	
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pICPATH) {
		PIC_PATH = pICPATH;
	}
	
	public String getTRAIN_COURSE_ID() {
		return TRAIN_COURSE_ID;
	}
	public void setTRAIN_COURSE_ID(String tRAINCOURSEID) {
		TRAIN_COURSE_ID = tRAINCOURSEID;
	}
	public String getTRAIN_COURSE_NO() {
		return TRAIN_COURSE_NO;
	}
	public void setTRAIN_COURSE_NO(String tRAINCOURSENO) {
		TRAIN_COURSE_NO = tRAINCOURSENO;
	}
	public String getTRAIN_COURSE_NAME() {
		return TRAIN_COURSE_NAME;
	}
	public void setTRAIN_COURSE_NAME(String tRAINCOURSENAME) {
		TRAIN_COURSE_NAME = tRAINCOURSENAME;
	}
	public String getTRAIN_TYPE() {
		return TRAIN_TYPE;
	}
	public void setTRAIN_TYPE(String tRAINTYPE) {
		TRAIN_TYPE = tRAINTYPE;
	}
	public String getFIXED_PSON_NUM() {
		return FIXED_PSON_NUM;
	}
	public void setFIXED_PSON_NUM(String fIXEDPSONNUM) {
		FIXED_PSON_NUM = fIXEDPSONNUM;
	}
	public String getTRAIN_TIME_BEG() {
		return TRAIN_TIME_BEG;
	}
	public void setTRAIN_TIME_BEG(String tRAINTIMEBEG) {
		TRAIN_TIME_BEG = tRAINTIMEBEG;
	}
	public String getTRAIN_TIME_END() {
		return TRAIN_TIME_END;
	}
	public void setTRAIN_TIME_END(String tRAINTIMEEND) {
		TRAIN_TIME_END = tRAINTIMEEND;
	}
	public String getTRAIN_ADDR() {
		return TRAIN_ADDR;
	}
	public void setTRAIN_ADDR(String tRAINADDR) {
		TRAIN_ADDR = tRAINADDR;
	}
	public String getTRAIN_GOAL() {
		return TRAIN_GOAL;
	}
	public void setTRAIN_GOAL(String tRAINGOAL) {
		TRAIN_GOAL = tRAINGOAL;
	}
	public String getTRAIN_CONTENT() {
		return TRAIN_CONTENT;
	}
	public void setTRAIN_CONTENT(String tRAINCONTENT) {
		TRAIN_CONTENT = tRAINCONTENT;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}
