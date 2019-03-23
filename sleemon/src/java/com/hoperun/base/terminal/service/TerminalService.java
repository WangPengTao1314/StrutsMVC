/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：TerminalService.java
 */
package com.hoperun.base.terminal.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface TerminalService.
 * 
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
 */
public interface TerminalService extends IBaseService {

    /**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo);


    /**
     * 删除数据
     * 
     * @param TERM_ID the termId
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String termId, UserBean userBean);


    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    @SuppressWarnings("unchecked")
    public Map load(String param);
    
    /**
     * @param param
     * @return
     */
    public Map loadT(String param);
    /**
     * 编辑：新增/删除
     * 
     * @param TERM_ID the termId
     * @param terminalModel the brand model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public void txEdit(String termId, TerminalModel model, UserBean userBean);

   
    /**
     * 更新记录
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params);

	/**
     * 查找编号是否存在
     * 
     * @param termNo the termNo
     * 
     * @return true, if tx update by id
     */
	public int getExits(String termNo);
	/**
	 * 验证部门编号是否存在
	 * @param termNo
	 * @return
	 */
	public int checkNo(String termNo);
   
    /**
     * 修改状态为停用
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);

   
    /**
     * 修改状态为启用
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
    
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	
	
	/**
	 * 修改价格计算公式
	 */
	public void txModifyExpress(Map<String, String> params )throws ServiceException;
	
	/**
	 * 测试公式
	 * @param params
	 * @return
	 */
	public String testExpress(Map<String, String> params )throws ServiceException;
}
