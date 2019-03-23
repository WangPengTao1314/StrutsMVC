package com.hoperun.sys.action;

import javax.servlet.http.HttpSession;

import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class QXReader.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class QXReader {

	/**
	 * Instantiates a new qX reader.
	 */
	public QXReader() {
	}

	
	/**
	 * Check mk use.
	 * 
	 * @param XTMKID the xTMKID
	 * 
	 * @return true, if successful
	 */
	public boolean checkMKUse(String XTMKID) {
		//DBConnBean dbConnBean = new DBConnBean();
		try {
		//	return dbConnBean.getRowNum("T_SYS_XTMK", new StringBuffer().append("where (MKZT ='����' or MKZT='' or MKZT is null) AND XTMKID='").append(XTMKID).append("' with ur ").toString()) != 0;
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * Read user qx.
	 * 
	 * @param aUserBean the a user bean
	 * @param XTMKID the xTMKID
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String readUserQX(UserBean aUserBean, String XTMKID) throws Exception {
		if (aUserBean.getYHM().equalsIgnoreCase("administrator")) {
			return ("1=1");
		}
//		DBConnBean dbConnBean = new DBConnBean();
		String QXSQL = "";
//		ResultSet rs = null;
//		try {
//			rs = dbConnBean.selectSql("select QXSQL from T_SYS_USERQX where XTYHID='" + aUserBean.getXTYHID() + "' AND MKBHJB like '" + XTMKID + "%' wiht ur ");
//
//			if (rs.next()) {
//				QXSQL = rs.getString("QXSQL");
//			}
//			if (QXSQL == null || QXSQL.equals("")) {
//				QXSQL = " 1<>1 ";
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			if (rs != null)
//				rs.close();
//			if (dbConnBean != null)
//				dbConnBean.closeDB();
//		}

		return QXSQL;
	}

	/**
	 * Description:.
	 * 
	 * @param session the session
	 * @param XTMKID the xTMKID
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String readUserQX(HttpSession session, String XTMKID) throws Exception {
		UserBean aUserBean = (UserBean) session.getAttribute("UserBean");
		if (aUserBean.getYHM().equalsIgnoreCase("administrator")) {
			return ("1=1");
		}
//		DBConnBean dbConnBean = new DBConnBean();
		String QXSQL = "";
//		ResultSet rs = null;
//		try {
//			rs = dbConnBean.selectSql("select QXSQL from T_SYS_USERQX where XTYHID='" + aUserBean.getXTYHID() + "' AND MKBHJB like '" + XTMKID + "%' with ur ");
//			if (rs.next()) {
//				QXSQL = rs.getString("QXSQL");
//			}
//			if (QXSQL.equals("")) {
//				QXSQL = " 1<>1 ";
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			if (rs != null)
//				rs.close();
//			if (dbConnBean != null)
//				dbConnBean.closeDB();
//		}

		return QXSQL;
	}

	/*
	 *Description:�ж��û��Ƿ���жԸ�ģ���Ȩ��
	 *@param:
	 *@return:
	 *@throws:
	 */
	/**
	 * Check mkqx.
	 * 
	 * @param session the session
	 * @param XTMKID the xTMKID
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean checkMKQX(HttpSession session, String XTMKID) throws Exception {
		//if (session != null) {
		//System.out.println("session = "+ session);
		//UserBean aUserBean = (UserBean) session.getAttribute("UserBean");
		/*
		    if (dbConnBean.getRowNum("T_SYS_USERQX", "where MKBHJB like '%"+XTMKID+"%' AND XTYHID='"+aUserBean.getXTYHID()+"'") == 0) {
		      return false;
		    } else {
		      return true;
		    }
		    */
		//System.out.println(".....................................aUserBean.getStrUserMKXX()="+aUserBean.getStrUserMKXX());
		//System.out.println("....................................XTMKID="+XTMKID);
		//System.out.println("result="+aUserBean.getStrUserMKXX().indexOf(XTMKID));

		return true;

	}

	/*
	 *Description:
	 *@param:
	 *@return:
	 *@throws:
	 */
	/**
	 * Gets the qXJBID.
	 * 
	 * @param session the session
	 * @param XTMKID the xTMKID
	 * 
	 * @return the qXJBID
	 */
	public String getQXJBID(HttpSession session, String XTMKID) {
		String temp = null;
//		DBConnBean dbConnBean = new DBConnBean();
//		try {
//
//			UserBean aUserBean = (UserBean) session.getAttribute("UserBean");
//			ResultSet rs = dbConnBean.selectSql("select MKBHJB from T_SYS_USERQX where XTYHID='" + aUserBean.getXTYHID() + "' AND MKBHJB like '" + XTMKID + "%' with ur ");
//			if (rs.next()) {
//				temp = rs.getString("MKBHJB");
//			}
//			if (temp != null) {
//				temp = temp.substring(temp.length() - 1, temp.length());
//			}
//			rs.close();
//			dbConnBean.closeDB();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		return temp;
	}





} //End.