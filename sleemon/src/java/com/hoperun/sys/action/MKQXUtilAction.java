package com.hoperun.sys.action;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.sys.service.MKQXUtilService;

// TODO: Auto-generated Javadoc
/**
 * The Class MKQXUtilAction.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class MKQXUtilAction extends BaseAction
{
	
	/** The m kqx util service. */
	private MKQXUtilService mKQXUtilService;
	
	
	
	/**
	 * Tran code p.
	 * 
	 * @param Str the str
	 * 
	 * @return the string
	 */
	public String tranCodeP(String Str) {
		return "";
	}

	/**
	 * Tran code n.
	 * 
	 * @param Str the str
	 * 
	 * @return the string
	 */
	public String tranCodeN(Object Str) {
		return "";
	}

	/**
	 * Gets the mKQX util service.
	 * 
	 * @return the mKQX util service
	 */
	public MKQXUtilService getMKQXUtilService() {
		return mKQXUtilService;
	}

	/**
	 * Sets the mKQX util service.
	 * 
	 * @param utilService the new mKQX util service
	 */
	public void setMKQXUtilService(MKQXUtilService utilService) {
		mKQXUtilService = utilService;
	}
	
	
}
