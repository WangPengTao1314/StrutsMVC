package com.hoperun.commons.util;


import com.hoperun.commons.service.impl.InitJournalStoreAcctServiceImpl;

public class InitJournalStoreAcct {
	
	public static boolean InitStoreUtilAcct() {
		try{
		InitJournalStoreAcctServiceImpl InitJournalStoreAcctImpl = (InitJournalStoreAcctServiceImpl) SpringContextUtil
				.getBean("InitStoreUtilService");
		InitJournalStoreAcctImpl.txInitChannStoreAcct();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true; 
	}
}
