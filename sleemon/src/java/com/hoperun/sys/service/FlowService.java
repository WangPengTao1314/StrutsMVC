package com.hoperun.sys.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.FlowObject;
import com.hoperun.sys.model.ProcessNode;
import com.hoperun.sys.model.UserBean;
// TODO: Auto-generated Javadoc

/**
 * The Interface FlowService.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public interface FlowService extends IBaseService {
	//获得审批模板
	/**
	 * Gets the flow info.
	 * 
	 * @param correlation the correlation
	 * @param id the id
	 * @param primaryKey the primary key
	 * 
	 * @return the flow info
	 */
	public  List  getFlowInfo(String correlation,String id,String primaryKey);
	
	/**
	 * Gets the flow infos.
	 * 
	 * @param id the id
	 * 
	 * @return the flow infos
	 */
	public  List  getFlowInfos(String id);
	
	/**
	 * Page query.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map params, int pageNo);
	
	/**
	 * Selcom.
	 * 
	 * @param params the params
	 * 
	 * @return the map
	 */
	public Map selcom(Map params);
	
	/**
	 * Selcom list.
	 * 
	 * @param params the params
	 * 
	 * @return the list
	 */
	public List selcomList(Map params);
	
	/**
	 * 取得当前用户的给定业务数据（tableName, id）的当前处理节点，主要用于业务数据中需要用到
	 * 当前处理节点的属性，如修改业务数据权限.
	 * 
	 * @param session the session
	 * @param tableName the table name
	 * @param id the id
	 * 
	 * @return ProcessNode
	 * 
	 * @throws Exception the exception
	 */
	public ProcessNode getProcessNode(HttpSession session, String tableName, String id) throws Exception;
	
	/**
	 * 取得当前用户的给定流程（flowInstanceId, type）的当前处理节点，主要用于业务数据中需要用到
	 * 当前处理节点的属性，如修改业务数据权限.
	 * 
	 * @param session the session
	 * @param flowInstanceId the flow instance id
	 * @param type the type
	 * 
	 * @return ProcessNode
	 * 
	 * @throws Exception the exception
	 */
	public ProcessNode getProcessNode(HttpSession session, String flowInstanceId, int type) throws Exception;
	
	/**
	 * 取得当前用户的给定业务数据（tableName, id）的当前处理节点，主要用于业务数据中需要用到
	 * 当前处理节点的属性，如流程中设置的修改业务数据属性.
	 * 
	 * @param session the session
	 * @param instanceNodeId the instance node id
	 * 
	 * @return the process node
	 * 
	 * @throws Exception the exception
	 */
	public ProcessNode getProcessNode(HttpSession session, String instanceNodeId) throws Exception;
	
	/**
	 * 判断当前用户是否是待处理用户.
	 * 
	 * @param proNode the pro node
	 * @param user the user
	 * 
	 * @return boolean
	 * 
	 * @throws Exception the exception
	 */
	public boolean isDealUser(ProcessNode proNode, UserBean user) throws Exception;
	
	/**
	 * To time stamp sql.
	 * 
	 * @param timeStamp the time stamp
	 * 
	 * @return the string
	 */
	public String toTimeStampSQL(String timeStamp);
	
	/**
	 * 流程提交处理.
	 * 
	 * @param session the session
	 * @param flowObject the flow object
	 * 
	 * @return 提交后新生成的实例id:flowInstanceId 没有生成实例则返回""
	 * 
	 * @throws Exception the exception
	 */
	public String txQueren(HttpSession session, FlowObject flowObject) throws Exception;
	
	/**
	 * Tx insertmod.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txInsertmod(Map params);
	
	/**
	 * Tx insertnod.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txInsertnod(Map params);
	
	/**
	 * Tx insertpath.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txInsertpath(Map params);
	
	/**
	 * Tx inscom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txInscom(Map params);
	
	/**
	 * Tx del flow.
	 * 
	 * @param params the params
	 */
	public void txDelFlow(Map params);
	
	/**
	 * Tx delcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txDelcom(Map params);
	
	/**
	 * Tx updcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txUpdcom(Map params);
	
	/**
	 * Tx updatemod by id.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txUpdatemodById(Map params);
	
	/**
	 * Tx updatenode by id.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean txUpdatenodeById(Map params);
	
	/**
	 * Tx add module.
	 * 
	 * @param request the request
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String txAddModule(HttpServletRequest request)throws Exception;
	
	/**
	 * Tx add module node up.
	 * 
	 * @param flowNodeId the flow node id
	 * @param nodey the nodey
	 * @param nodeInsertName the node insert name
	 * @param parameterValue the parameter value
	 * @param nodeInsertValue the node insert value
	 */
	public void txAddModuleNodeUp(String flowNodeId, String nodey,
			String[] nodeInsertName, String[] parameterValue,
			String[] nodeInsertValue);
	
	/**
	 * Tx add module down.
	 * 
	 * @param request the request
	 * 
	 * @throws ServiceException the service exception
	 */
	public void txAddModuleDown(HttpServletRequest request) throws ServiceException;
	
	/**
	 * Tx del modnode.
	 * 
	 * @param request the request
	 * 
	 * @throws ServiceException the service exception
	 */
	public void txDelMODNODE(HttpServletRequest request) throws ServiceException;
	
	/**
	 * Tx save nodes.
	 * 
	 * @param request the request
	 * 
	 * @throws ServiceException the service exception
	 */
	public void txSaveNodes(HttpServletRequest request) throws ServiceException;
	
	/**
	 * Tx to affirm.
	 * 
	 * @param request the request
	 * @param paraValue the para value
	 * @param session the session
	 * @param flowObjectInS the flow object in s
	 * @param service the service
	 * @param remind the remind
	 * 
	 * @return the string
	 * 
	 * @throws ServiceException the service exception
	 */
	public String txToAffirm(HttpServletRequest request,String[] paraValue, HttpSession session, FlowObject flowObjectInS,FlowService service,String remind) throws ServiceException;
	
	/**
	 * Tx auditing.
	 * 
	 * @param request the request
	 * @param flowService the flow service
	 * @param remind the remind
	 * 
	 * @return the string
	 * 
	 * @throws ServiceException the service exception
	 */
	public String txAuditing(HttpServletRequest request,FlowService flowService,String remind) throws ServiceException;
	
	/**
	 * Tx to repeal.
	 * 
	 * @param request the request
	 * @param remind the remind
	 * @param flowService the flow service
	 * 
	 * @return the string
	 * 
	 * @throws ServiceException the service exception
	 */
	public String txToRepeal(HttpServletRequest request,String remind,FlowService flowService)throws ServiceException;
	
	/**
	 * Tx event handle.
	 * 
	 * @param request the request
	 * @param flowService the flow service
	 * @param remind the remind
	 * 
	 * @return the string
	 * 
	 * @throws ServiceException the service exception
	 */
	public String txEventHandle(HttpServletRequest request,FlowService flowService,String remind) throws ServiceException;
	
	/**
	 * 可用于计数.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the int
	 */
	public int queryForInt(String statementName, Object paramMap);
	/**
	 * 验证是否存在重复信息
	 * @param map
	 * @return
	 */
	public boolean checkInfo(Map<String,String> map);
}