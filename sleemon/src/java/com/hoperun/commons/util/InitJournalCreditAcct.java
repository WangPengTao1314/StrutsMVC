package com.hoperun.commons.util;

import java.util.HashMap;
import java.util.List;

import com.hoperun.commons.service.impl.InitJournalCreditAcctServiceImpl;

public class InitJournalCreditAcct {

	/**新的发运逻辑初使代流水账
	 * @return
	 * @throws Exception
	 */
	public static boolean initNewsCreditUtilAcct() throws Exception {
		InitJournalCreditAcctServiceImpl InitJournalCreditAcctImpl = (InitJournalCreditAcctServiceImpl) SpringContextUtil
		.getBean("InitCreditUtilService");
		List channList = InitJournalCreditAcctImpl.getAllChann();
		for (int i = 0; i < channList.size(); i++) {
			HashMap map = (HashMap) channList.get(i);
			String chann_id = (String) map.get("CHANN_ID");
			InitJournalCreditAcctImpl.txNewInitChannAcct(chann_id);
		}
		return true;
	}
	
	/**初使化冻结信用
	 * @throws Exception
	 */
	public static void trBatchInitChannFreeCredit() throws Exception {
		InitJournalCreditAcctServiceImpl InitJournalCreditAcctImpl = (InitJournalCreditAcctServiceImpl) SpringContextUtil
		.getBean("InitCreditUtilService");
		InitJournalCreditAcctImpl.trBatchInitChannFreeCredit();
	}
	
	/**
	 * 原初使化信用流水账
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean InitCreditUtilAcct() throws Exception {
		InitJournalCreditAcctServiceImpl InitJournalCreditAcctImpl = (InitJournalCreditAcctServiceImpl) SpringContextUtil
				.getBean("InitCreditUtilService");
		List channList = InitJournalCreditAcctImpl.getAllChann();
		for (int i = 0; i < channList.size(); i++) {
			HashMap map = (HashMap) channList.get(i);
			String chann_id = (String) map.get("CHANN_ID");
			InitJournalCreditAcctImpl.txInitChannAcct(chann_id);
		}
		return true;
	}



	public static boolean InitCreateUtilAcctByChannId(String chann_id)
			throws Exception {
		InitJournalCreditAcctServiceImpl InitJournalCreditAcctImpl = (InitJournalCreditAcctServiceImpl) SpringContextUtil
				.getBean("InitCreditUtilService");
		InitJournalCreditAcctImpl.txInitChannAcct(chann_id);
		return true;
	}
}
