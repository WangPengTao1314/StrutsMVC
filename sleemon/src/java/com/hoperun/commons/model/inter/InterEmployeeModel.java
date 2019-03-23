package com.hoperun.commons.model.inter;
/**  
 * @func
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-2 上午09:33:34 
 */
public class InterEmployeeModel extends FixedInterModel{
		/**
		 * 员工编码
		 */
		private String EMPLOYEE_CODE;
		/**
		 * 员工名称
		 */
		private String EMPLOYEE_NAME;
		/**
		 * 组织领导(选项)
		 */
		private String LEADER_CODE;
		/**
		 * 
		 */
		private String LEADER_CODE_VAL;
		/**
		 * 性别(选项)
		 */
		private String SEX;
		/**
		 * 
		 */
		private String SEX_VAL;
		/**
		 * 员工类别(选项)
		 */
		private String EMPLOYEE_TYPE;
		/**
		 * 
		 */
		private String EMPLOYEE_TYPE_VAL;
		/**
		 * 手机号码
		 */
		private String PHONE;
		/**
		 * 入职日期
		 */
		private String ENTRY_DATE;
		/**
		 * 所属组织(选项)
		 */
		private String BELONG_CODE;
		/**
		 * 
		 */
		private String BELONG_CODE_VAL;
		/**
		 * 在职状态(选项)
		 */
		private String WORK_STATUS;
		/**
		 * 
		 */
		private String WORK_STATUS_VAL;
		/**
		 * 身份证号
		 */
		private String ID_NUMBER;
		/**
		 * 身份证失效日期
		 */
		private String EXPIRY_DATE;
		/**
		 * 其它证件类型(选项)
		 */
		private String OTHER_CERTIFICATE;
		/**
		 * 
		 */
		private String OTHER_CERTIFICATE_VAL;
		/**
		 * 其它证件号码
		 */
		private String OTHER_NUMBER;
		/**
		 * 英文名
		 */
		private String ENGLISH_NAME;
		/**
		 * 出生日期
		 */
		private String BIRTH_DATE;
		/**
		 * 国籍(选项)
		 */
		private String NATIONALITY;
		/**
		 * 
		 */
		private String NATIONALITY_VAL;
		/**
		 * 籍贯(选项)
		 */
		private String NATIVE_PLACE;
		/**
		 * 
		 */
		private String NATIVE_PLACE_VAL;
		/**
		 * 婚姻状况(选项)
		 */
		private String MARTIAL_STATUS;
		/**
		 * 
		 */
		private String MARTIAL_STATUS_VAL;
		/**
		 * 民族(选项)
		 */
		private String NATION;
		/**
		 * 
		 */
		private String NATION_VAL;
		/**
		 * 政治面貌(选项)
		 */
		private String POLITICAL;
		/**
		 * 
		 */
		private String POLITICAL_VAL;
		/**
		 * 居住地址
		 */
		private String CURR_ADDRESS;
		/**
		 * 户籍地址
		 */
		private String REGISTER_ADDRESS;
		/**
		 * 户籍性质(选项)
		 */
		private String REGISTER_TYPE;
		/**
		 * 
		 */
		private String REGISTER_TYPE_VAL;
		/**
		 * 照片
		 */
		private String PHOTO;
		/**
		 * 最高学历(选项)
		 */
		private String EDUCATION;
		/**
		 * 
		 */
		private String EDUCATION_VAL;
		/**
		 * 最高学位(选项)
		 */
		private String DEGREE;
		/**
		 * 
		 */
		private String DEGREE_VAL;
		/**
		 * 毕业院校
		 */
		private String GRADUATE_INSTITUTION;
		/**
		 * 是否参加职业病体检(选项)
		 */
		private String DISEASE_EXAM;
		/**
		 * 
		 */
		private String DISEASE_EXAM_VAL;
		/**
		 * 办公电话
		 */
		private String OFFICE_PHONE;
		/**
		 * 办公邮箱
		 */
		private String OFFICE_EMAIL;
		/**
		 * 员工性质(选项)
		 */
		private String LEVEL_CATEGORY;
		/**
		 * 
		 */
		private String LEVEL_CATEGORY_VAL;
		/**
		 * 入职地址
		 */
		private String ENTRY_AREA;
		/**
		 * 入职前工龄
		 */
		private String PRE_WORK_AGE;
		/**
		 * 职位类型(选项)
		 */
		private String POST_TYPE;
		/**
		 * 
		 */
		private String POST_TYPE_VAL;
		/**
		 * 职位名称(选项)
		 */
		private String POST_NAME;
		/**
		 * 
		 */
		private String POST_NAME_VAL;
		/**
		 * 职务类型(选项)
		 */
		private String POSITION_TYPE;
		/**
		 * 
		 */
		private String POSITION_TYPE_VAL;
		/**
		 * 职务名称(选项)
		 */
		private String POSITION_NAME;
		/**
		 * 
		 */
		private String POSITION_NAME_VAL;
		/**
		 * 职等(选项)
		 */
		private String POST_RANK;
		/**
		 * 
		 */
		private String POST_RANK_VAL;
		/**
		 * 职级(选项)
		 */
		private String POST_LEVEL;
		/**
		 * 
		 */
		private String POST_LEVEL_VAL;
		public String getEMPLOYEE_CODE() {
			return EMPLOYEE_CODE;
		}
		public void setEMPLOYEE_CODE(String eMPLOYEECODE) {
			EMPLOYEE_CODE = eMPLOYEECODE;
		}
		public String getEMPLOYEE_NAME() {
			return EMPLOYEE_NAME;
		}
		public void setEMPLOYEE_NAME(String eMPLOYEENAME) {
			EMPLOYEE_NAME = eMPLOYEENAME;
		}
		public String getLEADER_CODE() {
			return LEADER_CODE;
		}
		public void setLEADER_CODE(String lEADERCODE) {
			LEADER_CODE = lEADERCODE;
		}
		public String getLEADER_CODE_VAL() {
			return LEADER_CODE_VAL;
		}
		public void setLEADER_CODE_VAL(String lEADERCODEVAL) {
			LEADER_CODE_VAL = lEADERCODEVAL;
		}
		public String getSEX() {
			return SEX;
		}
		public void setSEX(String sEX) {
			SEX = sEX;
		}
		public String getSEX_VAL() {
			return SEX_VAL;
		}
		public void setSEX_VAL(String sEXVAL) {
			SEX_VAL = sEXVAL;
		}
		public String getEMPLOYEE_TYPE() {
			return EMPLOYEE_TYPE;
		}
		public void setEMPLOYEE_TYPE(String eMPLOYEETYPE) {
			EMPLOYEE_TYPE = eMPLOYEETYPE;
		}
		public String getEMPLOYEE_TYPE_VAL() {
			return EMPLOYEE_TYPE_VAL;
		}
		public void setEMPLOYEE_TYPE_VAL(String eMPLOYEETYPEVAL) {
			EMPLOYEE_TYPE_VAL = eMPLOYEETYPEVAL;
		}
		public String getPHONE() {
			return PHONE;
		}
		public void setPHONE(String pHONE) {
			PHONE = pHONE;
		}
		public String getENTRY_DATE() {
			return ENTRY_DATE;
		}
		public void setENTRY_DATE(String eNTRYDATE) {
			ENTRY_DATE = eNTRYDATE;
		}
		public String getBELONG_CODE() {
			return BELONG_CODE;
		}
		public void setBELONG_CODE(String bELONGCODE) {
			BELONG_CODE = bELONGCODE;
		}
		public String getBELONG_CODE_VAL() {
			return BELONG_CODE_VAL;
		}
		public void setBELONG_CODE_VAL(String bELONGCODEVAL) {
			BELONG_CODE_VAL = bELONGCODEVAL;
		}
		public String getWORK_STATUS() {
			return WORK_STATUS;
		}
		public void setWORK_STATUS(String wORKSTATUS) {
			WORK_STATUS = wORKSTATUS;
		}
		public String getWORK_STATUS_VAL() {
			return WORK_STATUS_VAL;
		}
		public void setWORK_STATUS_VAL(String wORKSTATUSVAL) {
			WORK_STATUS_VAL = wORKSTATUSVAL;
		}
		public String getID_NUMBER() {
			return ID_NUMBER;
		}
		public void setID_NUMBER(String iDNUMBER) {
			ID_NUMBER = iDNUMBER;
		}
		public String getEXPIRY_DATE() {
			return EXPIRY_DATE;
		}
		public void setEXPIRY_DATE(String eXPIRYDATE) {
			EXPIRY_DATE = eXPIRYDATE;
		}
		public String getOTHER_CERTIFICATE() {
			return OTHER_CERTIFICATE;
		}
		public void setOTHER_CERTIFICATE(String oTHERCERTIFICATE) {
			OTHER_CERTIFICATE = oTHERCERTIFICATE;
		}
		public String getOTHER_CERTIFICATE_VAL() {
			return OTHER_CERTIFICATE_VAL;
		}
		public void setOTHER_CERTIFICATE_VAL(String oTHERCERTIFICATEVAL) {
			OTHER_CERTIFICATE_VAL = oTHERCERTIFICATEVAL;
		}
		public String getOTHER_NUMBER() {
			return OTHER_NUMBER;
		}
		public void setOTHER_NUMBER(String oTHERNUMBER) {
			OTHER_NUMBER = oTHERNUMBER;
		}
		public String getENGLISH_NAME() {
			return ENGLISH_NAME;
		}
		public void setENGLISH_NAME(String eNGLISHNAME) {
			ENGLISH_NAME = eNGLISHNAME;
		}
		public String getBIRTH_DATE() {
			return BIRTH_DATE;
		}
		public void setBIRTH_DATE(String bIRTHDATE) {
			BIRTH_DATE = bIRTHDATE;
		}
		public String getNATIONALITY() {
			return NATIONALITY;
		}
		public void setNATIONALITY(String nATIONALITY) {
			NATIONALITY = nATIONALITY;
		}
		public String getNATIONALITY_VAL() {
			return NATIONALITY_VAL;
		}
		public void setNATIONALITY_VAL(String nATIONALITYVAL) {
			NATIONALITY_VAL = nATIONALITYVAL;
		}
		public String getNATIVE_PLACE() {
			return NATIVE_PLACE;
		}
		public void setNATIVE_PLACE(String nATIVEPLACE) {
			NATIVE_PLACE = nATIVEPLACE;
		}
		public String getNATIVE_PLACE_VAL() {
			return NATIVE_PLACE_VAL;
		}
		public void setNATIVE_PLACE_VAL(String nATIVEPLACEVAL) {
			NATIVE_PLACE_VAL = nATIVEPLACEVAL;
		}
		public String getMARTIAL_STATUS() {
			return MARTIAL_STATUS;
		}
		public void setMARTIAL_STATUS(String mARTIALSTATUS) {
			MARTIAL_STATUS = mARTIALSTATUS;
		}
		public String getMARTIAL_STATUS_VAL() {
			return MARTIAL_STATUS_VAL;
		}
		public void setMARTIAL_STATUS_VAL(String mARTIALSTATUSVAL) {
			MARTIAL_STATUS_VAL = mARTIALSTATUSVAL;
		}
		public String getNATION() {
			return NATION;
		}
		public void setNATION(String nATION) {
			NATION = nATION;
		}
		public String getNATION_VAL() {
			return NATION_VAL;
		}
		public void setNATION_VAL(String nATIONVAL) {
			NATION_VAL = nATIONVAL;
		}
		public String getPOLITICAL() {
			return POLITICAL;
		}
		public void setPOLITICAL(String pOLITICAL) {
			POLITICAL = pOLITICAL;
		}
		public String getPOLITICAL_VAL() {
			return POLITICAL_VAL;
		}
		public void setPOLITICAL_VAL(String pOLITICALVAL) {
			POLITICAL_VAL = pOLITICALVAL;
		}
		public String getCURR_ADDRESS() {
			return CURR_ADDRESS;
		}
		public void setCURR_ADDRESS(String cURRADDRESS) {
			CURR_ADDRESS = cURRADDRESS;
		}
		public String getREGISTER_ADDRESS() {
			return REGISTER_ADDRESS;
		}
		public void setREGISTER_ADDRESS(String rEGISTERADDRESS) {
			REGISTER_ADDRESS = rEGISTERADDRESS;
		}
		public String getREGISTER_TYPE() {
			return REGISTER_TYPE;
		}
		public void setREGISTER_TYPE(String rEGISTERTYPE) {
			REGISTER_TYPE = rEGISTERTYPE;
		}
		public String getREGISTER_TYPE_VAL() {
			return REGISTER_TYPE_VAL;
		}
		public void setREGISTER_TYPE_VAL(String rEGISTERTYPEVAL) {
			REGISTER_TYPE_VAL = rEGISTERTYPEVAL;
		}
		public String getPHOTO() {
			return PHOTO;
		}
		public void setPHOTO(String pHOTO) {
			PHOTO = pHOTO;
		}
		public String getEDUCATION() {
			return EDUCATION;
		}
		public void setEDUCATION(String eDUCATION) {
			EDUCATION = eDUCATION;
		}
		public String getEDUCATION_VAL() {
			return EDUCATION_VAL;
		}
		public void setEDUCATION_VAL(String eDUCATIONVAL) {
			EDUCATION_VAL = eDUCATIONVAL;
		}
		public String getDEGREE() {
			return DEGREE;
		}
		public void setDEGREE(String dEGREE) {
			DEGREE = dEGREE;
		}
		public String getDEGREE_VAL() {
			return DEGREE_VAL;
		}
		public void setDEGREE_VAL(String dEGREEVAL) {
			DEGREE_VAL = dEGREEVAL;
		}
		public String getGRADUATE_INSTITUTION() {
			return GRADUATE_INSTITUTION;
		}
		public void setGRADUATE_INSTITUTION(String gRADUATEINSTITUTION) {
			GRADUATE_INSTITUTION = gRADUATEINSTITUTION;
		}
		public String getDISEASE_EXAM() {
			return DISEASE_EXAM;
		}
		public void setDISEASE_EXAM(String dISEASEEXAM) {
			DISEASE_EXAM = dISEASEEXAM;
		}
		public String getDISEASE_EXAM_VAL() {
			return DISEASE_EXAM_VAL;
		}
		public void setDISEASE_EXAM_VAL(String dISEASEEXAMVAL) {
			DISEASE_EXAM_VAL = dISEASEEXAMVAL;
		}
		public String getOFFICE_PHONE() {
			return OFFICE_PHONE;
		}
		public void setOFFICE_PHONE(String oFFICEPHONE) {
			OFFICE_PHONE = oFFICEPHONE;
		}
		public String getOFFICE_EMAIL() {
			return OFFICE_EMAIL;
		}
		public void setOFFICE_EMAIL(String oFFICEEMAIL) {
			OFFICE_EMAIL = oFFICEEMAIL;
		}
		public String getLEVEL_CATEGORY() {
			return LEVEL_CATEGORY;
		}
		public void setLEVEL_CATEGORY(String lEVELCATEGORY) {
			LEVEL_CATEGORY = lEVELCATEGORY;
		}
		public String getLEVEL_CATEGORY_VAL() {
			return LEVEL_CATEGORY_VAL;
		}
		public void setLEVEL_CATEGORY_VAL(String lEVELCATEGORYVAL) {
			LEVEL_CATEGORY_VAL = lEVELCATEGORYVAL;
		}
		public String getENTRY_AREA() {
			return ENTRY_AREA;
		}
		public void setENTRY_AREA(String eNTRYAREA) {
			ENTRY_AREA = eNTRYAREA;
		}
		public String getPRE_WORK_AGE() {
			return PRE_WORK_AGE;
		}
		public void setPRE_WORK_AGE(String pREWORKAGE) {
			PRE_WORK_AGE = pREWORKAGE;
		}
		public String getPOST_TYPE() {
			return POST_TYPE;
		}
		public void setPOST_TYPE(String pOSTTYPE) {
			POST_TYPE = pOSTTYPE;
		}
		public String getPOST_TYPE_VAL() {
			return POST_TYPE_VAL;
		}
		public void setPOST_TYPE_VAL(String pOSTTYPEVAL) {
			POST_TYPE_VAL = pOSTTYPEVAL;
		}
		public String getPOST_NAME() {
			return POST_NAME;
		}
		public void setPOST_NAME(String pOSTNAME) {
			POST_NAME = pOSTNAME;
		}
		public String getPOST_NAME_VAL() {
			return POST_NAME_VAL;
		}
		public void setPOST_NAME_VAL(String pOSTNAMEVAL) {
			POST_NAME_VAL = pOSTNAMEVAL;
		}
		public String getPOSITION_TYPE() {
			return POSITION_TYPE;
		}
		public void setPOSITION_TYPE(String pOSITIONTYPE) {
			POSITION_TYPE = pOSITIONTYPE;
		}
		public String getPOSITION_TYPE_VAL() {
			return POSITION_TYPE_VAL;
		}
		public void setPOSITION_TYPE_VAL(String pOSITIONTYPEVAL) {
			POSITION_TYPE_VAL = pOSITIONTYPEVAL;
		}
		public String getPOSITION_NAME() {
			return POSITION_NAME;
		}
		public void setPOSITION_NAME(String pOSITIONNAME) {
			POSITION_NAME = pOSITIONNAME;
		}
		public String getPOSITION_NAME_VAL() {
			return POSITION_NAME_VAL;
		}
		public void setPOSITION_NAME_VAL(String pOSITIONNAMEVAL) {
			POSITION_NAME_VAL = pOSITIONNAMEVAL;
		}
		public String getPOST_RANK() {
			return POST_RANK;
		}
		public void setPOST_RANK(String pOSTRANK) {
			POST_RANK = pOSTRANK;
		}
		public String getPOST_RANK_VAL() {
			return POST_RANK_VAL;
		}
		public void setPOST_RANK_VAL(String pOSTRANKVAL) {
			POST_RANK_VAL = pOSTRANKVAL;
		}
		public String getPOST_LEVEL() {
			return POST_LEVEL;
		}
		public void setPOST_LEVEL(String pOSTLEVEL) {
			POST_LEVEL = pOSTLEVEL;
		}
		public String getPOST_LEVEL_VAL() {
			return POST_LEVEL_VAL;
		}
		public void setPOST_LEVEL_VAL(String pOSTLEVELVAL) {
			POST_LEVEL_VAL = pOSTLEVELVAL;
		}
		
		
}
