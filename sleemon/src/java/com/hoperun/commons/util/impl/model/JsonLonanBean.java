package com.hoperun.commons.util.impl.model;

import java.util.List;

public class JsonLonanBean {
	private String TERM_NO;
	private String ORDER_NO;
	private String SALES_NO;
	private String CUST_NAME;
	private String TEL;
	private String RECV_ADDR;
	private String ADVC_AMOUNT;
	private String APP_KEY;
	private List<LonanPrd> DTL_DATAS;
	
	 public static class LonanPrd{
		private String PRD_NO;
		private String PRICE;
		private String ORDER_NUM;
		private String ORDER_RECV_DATE;
		private String PRD_DESC;
		
		public String getPRD_DESC() {
			return PRD_DESC;
		}
		public void setPRD_DESC(String pRDDESC) {
			PRD_DESC = pRDDESC;
		}
		public String getPRD_NO() {
			return PRD_NO;
		}
		public void setPRD_NO(String pRDNO) {
			PRD_NO = pRDNO;
		}
		public String getPRICE() {
			return PRICE;
		}
		public void setPRICE(String pRICE) {
			PRICE = pRICE;
		}
		public String getORDER_NUM() {
			return ORDER_NUM;
		}
		public void setORDER_NUM(String oRDERNUM) {
			ORDER_NUM = oRDERNUM;
		}
		public String getORDER_RECV_DATE() {
			return ORDER_RECV_DATE;
		}
		public void setORDER_RECV_DATE(String oRDERRECVDATE) {
			ORDER_RECV_DATE = oRDERRECVDATE;
		}
		
	}

	public String getTERM_NO() {
		return TERM_NO;
	}

	public void setTERM_NO(String tERMNO) {
		TERM_NO = tERMNO;
	}

	public String getORDER_NO() {
		return ORDER_NO;
	}

	public void setORDER_NO(String oRDERNO) {
		ORDER_NO = oRDERNO;
	}

	public String getSALES_NO() {
		return SALES_NO;
	}

	public void setSALES_NO(String sALESNO) {
		SALES_NO = sALESNO;
	}

	public String getCUST_NAME() {
		return CUST_NAME;
	}

	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public String getRECV_ADDR() {
		return RECV_ADDR;
	}

	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}

	public String getADVC_AMOUNT() {
		return ADVC_AMOUNT;
	}

	public void setADVC_AMOUNT(String aDVCAMOUNT) {
		ADVC_AMOUNT = aDVCAMOUNT;
	}

	public List<LonanPrd> getDTL_DATAS() {
		return DTL_DATAS;
	}

	public void setDTL_DATAS(List<LonanPrd> dTLDATAS) {
		DTL_DATAS = dTLDATAS;
	}

	public String getAPP_KEY() {
		return APP_KEY;
	}

	public void setAPP_KEY(String aPPKEY) {
		APP_KEY = aPPKEY;
	}
	
	
 
	

}
