package com.hoperun.commons.util;
import com.hoperun.commons.service.impl.InitJournalInvPdtAcctServiceImpl;

public class InitJournalInvPdtUtil {
	public static boolean InitCreditUtilAcct() throws Exception {
		InitJournalInvPdtAcctServiceImpl InitJournalInvcPdtAcctImpl = (InitJournalInvPdtAcctServiceImpl) SpringContextUtil
				.getBean("InitInvPdtUtilService");
		InitJournalInvcPdtAcctImpl.txInitInvPdtAcct("4110");
		return true; 
	}
}
