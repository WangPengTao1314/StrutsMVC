/**
  *@module 系统模块   
  *@fuc 系统模块   
  *@version 1.1
  *@author 朱晓文      
*/
package com.hoperun.commons.util;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class QXUtil.
 * 
 * @module 共通设计
 * @fuc 业务共通公用方法
 * @version 1.1
 * @author 朱晓文
 */
public class QXUtil {
	 
 	/** The logger. */
 	private static Logger logger = Logger.getLogger(QXUtil.class);
	
	 //判断当前登录人是否具有模块编号 的权限
	  /**
 	 * Check mkqx.
 	 * 
 	 * @param session the session
 	 * @param MKBH the mKBH
 	 * 
 	 * @return true, if successful
 	 */
 	public static boolean checkMKQX(UserBean userBean, String MKBH)
	  {
	    try {
	      if("1".equals(StringUtil.nullToSring(userBean.getQXMap().get(MKBH))))
	    	  return true;
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return false;
	  }
 	//判断当前登录人是否具有模块编号 的权限
	  /**
	 * Check mkqx.
	 * 
	 * @param session the session
	 * @param MKBH the mKBH
	 * 
	 * @return true, if successful
	 */
	public static String checkPvg(UserBean userBean, String MKBH)
	  {
	    try {
	      if("1".equals(StringUtil.nullToSring(userBean.getQXMap().get(MKBH))))
	    	  return "1";
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return "0";
	  }
	//根据传入的模块编号，返回当前登录人的数据隔离权限
     /**
	 * Gets the qXTJ.
	 * 
	 * @param aUserBean the a user bean
	 * @param MKBH the mKBH
	 * 
	 * @return the qXTJ
	 */
	public static String getQXTJ(UserBean aUserBean, String MKBH)
	  {
	    try {
	    	String QXTJ=aUserBean.getQXJBMap().get(MKBH);
	    	if(QXTJ==null||QXTJ.equals(""))
	    		return " 1<>1 ";
	    	else
	    		return QXTJ;
	    	
	       }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return " 1<>1 ";
	    
	  }
     
      //根据传入的模块编号，返回当前登录人的数据隔离权限
	  /**
       * Gets the qXTJ.
       * 
       * @param session the session
       * @param MKBH the mKBH
       * 
       * @return the qXTJ
       */
      public static String getQXTJ(HttpSession session, String MKBH)
	  {
		  UserBean userBean = (UserBean) session.getAttribute("UserBean");
	    try {
	    	String QXTJ=userBean.getQXJBMap().get(MKBH);
	    	if(QXTJ==null||QXTJ.equals(""))
	    		return " 1<>1 ";
	    	else
	    		return QXTJ;
	    	
	     }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return " 1<>1 ";
	  }
	  
	  
	  //获得审批过滤条件
	  /**
  	 * Gets the aud condition.
  	 * 
  	 * @param userBean the user bean
  	 * @param tableName the table name
  	 * @param businessType the business type
  	 * 
  	 * @return the aud condition
  	 */
  	public static String getAudCondition(UserBean userBean, String tableName,String businessType) {
		    String xtyhid = userBean.getXTYHID();
		    String bmxxid = userBean.getBMXXID();
		    String xtjsids = userBean.getXTJSIDS();
		    String gzzxxids = userBean.getGZZXXIDS();
		    StringBuffer aReSQL=new StringBuffer();
		    aReSQL.append("(select a.CORRELATIONID  from T_SYS_FLOWINSTANCE a, T_SYS_FLOWMODEL b  where  a.flowmodelid=b.flowmodelid  and instanceState='进行' and correlation='" );
		    aReSQL.append(tableName);
		    aReSQL.append("' and b.businessType='");
		    aReSQL.append(businessType);
		    aReSQL.append("'");
		    aReSQL.append("and flowInstanceId in ");
		    aReSQL.append("(select flowInstanceId from V_processNode_instanceNode where ");
		    aReSQL.append("(allFlag=0 and ((operationer='0' and operationerId='"+xtyhid);
		    aReSQL.append("') or (operationer='1' and operationerId ='"+bmxxid);
		    aReSQL.append("') or (operationer='2' and operationerId in ("+xtjsids+")  ");
		    aReSQL.append(") or (operationer='3' and operationerId in ("+gzzxxids+")  ");
		    
		    //aReSQL.append("') or (operationer='2' and operationerId in ("+xtjsids+") and ( DELAYFLAG=0 or '"+xtyhid+"'  in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR )) ");
		    // aReSQL.append(") or (operationer='3' and operationerId in ("+gzzxxids+") and ( DELAYFLAG=0 or '"+xtyhid+"'  in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR )) ");
		   
		    aReSQL.append(" )))))");
		    return aReSQL.toString();
      }
	  //获得审批查询
	  /**
  	 * Gets the aud qurey con.
  	 * 
  	 * @param userBean the user bean
  	 * @param tableName the table name
  	 * @param businessType the business type
  	 * 
  	 * @return the aud qurey con
  	 */
  	public static String getAudQureyCon(UserBean userBean, String tableName,String businessType) {
		    String xtyhid = userBean.getXTYHID();
		    String bmxxid = userBean.getBMXXID();
		    String xtjsids = userBean.getXTJSIDS();
		    String gzzxxids = userBean.getGZZXXIDS();
		    StringBuffer aReSQL=new StringBuffer();
		    
		    aReSQL.append("(select a.CORRELATIONID  from T_SYS_FLOWINSTANCE a left join T_SYS_FLOWTRACE d on a.CORRELATIONID=d.BUSINESSID  , T_SYS_FLOWMODEL b,V_PROCESSNODE_INSTANCENODE_ALL c  where  a.flowmodelid=b.flowmodelid  and correlation='" );
		    aReSQL.append(tableName);
		    aReSQL.append("' and b.businessType='");
		    aReSQL.append(businessType);
		    aReSQL.append("'");
		    aReSQL.append(" and  a.flowInstanceId=c.flowInstanceId");
		    aReSQL.append(" and (a.flowInstanceId = c.FLOWINSTANCEID_JX or  d.operator='"+xtyhid+"')  ");
		    
		    //aReSQL.append(" and ((INSTANCESTATE='进行' and c.FLOWINSTANCEID_JX is not null ) or   INSTANCESTATE<>'进行' ) ");
		    aReSQL.append(" and (allFlag=0 and ((operationer='0' and operationerId='"+xtyhid);
		    aReSQL.append("') or (operationer='1' and operationerId ='"+bmxxid);
		    aReSQL.append("') or (operationer='2' and operationerId in ("+xtjsids+")  ");
		    aReSQL.append(") or (operationer='3' and operationerId in ("+gzzxxids+")  ");
		    
		    //aReSQL.append("') or (operationer='2' and operationerId in ("+xtjsids+") and ( DELAYFLAG=0 or '"+xtyhid+"' in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR  )) ");
		    //aReSQL.append(") or (operationer='3' and operationerId in ("+gzzxxids+") and ( DELAYFLAG=0 or '"+xtyhid+"' in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR  )) ");
		    
		    
		    //因为当前所有的 allFlag 都等于0  所以这个条件暂时不加
		    //aReSQL.append(" or (allFlag=1 and instanceNodeId in (select instanceNodeId from ");
		    //aReSQL.append("T_SYS_INSTANCENODEOperation where operationUser='"+xtyhid+"' and operationFlag=0))");
		    aReSQL.append("))))");
		    return aReSQL.toString();
      }
	  
	//获得待审批列表
		/**
	 * Gets the aud list.
	 * 
	 * @param userBean the user bean
	 * 
	 * @return the aud list
	 */
	public static String getAudList(UserBean userBean) {
		    String xtyhid = userBean.getXTYHID();
		    String bmxxid = userBean.getBMXXID();
		    String xtjsids = userBean.getXTJSIDS();
		    String gzzxxids = userBean.getGZZXXIDS();
		    StringBuffer aReSQL=new StringBuffer();
		    aReSQL.append("select  a.CORRElATIONID,a.CORRELATION,a.PRIMARYKEY,b.BUSINESSTYPE,c.MENU_URL,c.MENU_NAME,c.MENU_ID,c.MENU_QXCODE");
		    aReSQL.append(" from "+Consts.SYSSCHEMA+".T_SYS_FLOWINSTANCE a, "+Consts.SYSSCHEMA+".T_SYS_FLOWMODEL b  left join "+Consts.SYSSCHEMA+".T_SYS_MENUINFO c on  b.BUSINESSTYPE=c.BUSINESSTYPE where  a.flowmodelid=b.flowmodelid  and instanceState='进行' " );
		    aReSQL.append("and flowInstanceId in ");
		    aReSQL.append("(select FLOWINSTANCEID from "+Consts.SYSSCHEMA+".V_PROCESSNODE_INSTANCENODE where ");
		    aReSQL.append("(allFlag=0 and ((operationer='0' and operationerId='"+xtyhid);
		    aReSQL.append("') or (operationer='1' and operationerId ='"+bmxxid);
		    aReSQL.append("') or (operationer='2' and operationerId in ("+xtjsids+") and ( DELAYFLAG=0 or '"+xtyhid+"' in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR  )) ");
		    aReSQL.append(") or (operationer='3' and operationerId in ("+gzzxxids+") and ( DELAYFLAG=0 or '"+xtyhid+"' in( select  PAR_USER_ID  from T_SYS_USER_REL k  where k.USER_ID=u.CREATOR  )) )))");
		    aReSQL.append(" or (allFlag=1 and instanceNodeId in (select INSTANCENODEID from ");
		    aReSQL.append(Consts.SYSSCHEMA+".T_SYS_INSTANCENODEOperation where operationUser='"+xtyhid+"' and operationFlag=0)))");
		    aReSQL.append(" order by c.MENU_ID ASC ,a.CORRELATION  ");
		    return aReSQL.toString();
		} 
	 
}
