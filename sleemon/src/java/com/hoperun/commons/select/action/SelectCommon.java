/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.select.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.PageInfoVO;
import com.hoperun.commons.select.handle.SelectHandle;
import com.hoperun.commons.select.object.InitObject;
import com.hoperun.commons.select.object.TableObject;
import com.hoperun.commons.select.service.SelectService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectCommon.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class SelectCommon extends BaseAction {
    
    /** The select service. */
    private SelectService selectService;

	/**
	 * Do get.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward doGet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return doPost(mapping, form, request, response);
	}

	/**
	 * Do post.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward doPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 根据通用选择序号确定要访问的配置文件
			String selCommId = StringUtil.tranCodeP(request
					.getParameter("selCommId"));
			String selCommHead = "WL"; // 默认为物流的配置文件
			if (selCommId.indexOf("_") != -1) {
				selCommHead = selCommId.substring(0, selCommId.indexOf("_"));
			} else {
				selCommId = selCommHead + "_" + selCommId;
			}
			String fileName = getServlet().getServletContext().getRealPath("/")+ File.separator 
					+ "pages" + File.separator + "sys" + File.separator
					+ "configfiles" + File.separator + "selcomm" + File.separator
					+ "selCommConfig_" + selCommHead + ".xml";
			SelectHandle handle = null;
			try {
				handle = new SelectHandle(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("ErrMsg", new Exception("通用选取配置文件读取失败！"));
			}
			String searchFlag = StringUtil.tranCodeP(request.getParameter("isSearch"));
			String cmdFlag = StringUtil.tranCodeP(request.getParameter("cmdFlag"));
			// 查询标记，1：在结果中查询，0：新的查询
			String serFlag = StringUtil.tranCodeP(request.getParameter("serFlag"));
			// 已有的查询条件
			String oldSearchCondition = StringUtil.tranCodeP(request.getParameter("searchCondition"));
			
			if(!StringUtil.isEmpty(oldSearchCondition))
			{
				int idx=StringUtil.getCharacterPosition(oldSearchCondition.toLowerCase(), "and", 2);
				if(idx!=-1)
				{
					oldSearchCondition=oldSearchCondition.substring(0,idx);
				}
			}
			
			String searchCondition = "";
			if (cmdFlag.equals("MainFrame") || cmdFlag.equals("topFrame")) {
				String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));
				String showQYTree = StringUtil.tranCodeP(request.getParameter("showQYTree"));
				TableObject aTableObject = handle.getCurrentTableObject(tableId, true);
				aTableObject.setSubTableObjects(handle.getSubTableObjects(tableId));
				request.setAttribute("TableObject", aTableObject);
				if (cmdFlag.equals("topFrame")) {
					return mapping.findForward("topFrame");
				}
				return mapping.findForward("MainFrame");
			}
			if (cmdFlag.equals("SubFrame")) {
				String tableId = StringUtil.tranCodeP(request
						.getParameter("tableId"));
				TableObject aTableObject = handle.getCurrentTableObject(
						tableId, true);
				aTableObject.setSubTableObjects(handle
						.getSubTableObjects(tableId));
				request.setAttribute("TableObject", aTableObject);

				// 如果是往来单位选取，添加区域过滤
				if (aTableObject.getIsWLDWXX()) {
					TableObject bTableObject = createQYObject(tableId);
					TableObject[] tmpTableObjects = { aTableObject };
					bTableObject.setSubTableObjects(tmpTableObjects);
					request.setAttribute("TableObject", bTableObject);
				}
				return mapping.findForward("SubFrame");
			}
			// 如果是查询则组建查询条件
			if (searchFlag.equals("T")) {
				String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));
				TableObject aTableObject = handle.getCurrentTableObject(tableId, true);
				StringBuffer serCondition = new StringBuffer(" 1=1 ");
				//add by zhuxw  for 子表条件
				// start
				StringBuffer serChildCon=new StringBuffer("");
				for (int i = 0; i < aTableObject.getFieldPyName().length; i++) {
					String fieldType = aTableObject.getFieldType()[i];
					String fieldName = aTableObject.getFieldAsName()[i];
					if (fieldName == null || fieldName.equals("")) {
						fieldName = aTableObject.getFieldPyName()[i];
					}
					if (aTableObject.getIsSearch()[i]) {
						
						if(!aTableObject.getIsChildCon()[i])
						{
						  serCondition.append(setSelectService( fieldType, fieldName, request));
					    }else
					    {
					      String temCon=setSelectService( fieldType, fieldName, request);
					      if(!"".equals(temCon))
					      {   
					    	  String aChildTabID=aTableObject.getChildTabId()[i];
					    	  TableObject aChildTableObject = handle.getCurrentTableObject(aChildTabID, false);
					    	  String childTablePyName=aChildTableObject.getTableAsName().equals("")? aChildTableObject.getTablePyName():aChildTableObject.getTableAsName();
					    	  String [] childForignKey=aChildTableObject.getForeignKeyId();
					    	  for(int ci=0;ci<childForignKey.length;ci++)
					    	  {
					    		  if(childForignKey[ci] != null&& !childForignKey[ci].equals(""))
					    		  {
					    			  serChildCon.append("  and ");
					    			  String childForignKeyId=aChildTableObject.getFieldAsName()[ci].equals("")? aChildTableObject.getFieldPyName()[ci]:aChildTableObject.getFieldAsName()[ci]; 
					    			  serChildCon.append(childForignKeyId);  
					    		      serChildCon.append(" in ( select "); 
					    			  serChildCon.append(childForignKeyId);  
					    			  serChildCon.append("  from  "); 
					    			  serChildCon.append(childTablePyName); 
					    			  serChildCon.append(" where 1=1 "); 
					    			  serChildCon.append(setSelectService( fieldType, fieldName, request));
					    			  serChildCon.append(" ) "); 
					    			  break;
					    		  }
					    	  }
					    	  
					    	  
					    	  
					      }
					    }
						  
					}
				}
				//add by zhuxw  for 子表条件
				serCondition.append(serChildCon.toString());
				// end 
				if (serCondition.toString().equals(" 1=1 ")) {
					searchCondition = "";
				} else {
					searchCondition = serCondition.toString();
				}

				if (serFlag.equals("1") && !oldSearchCondition.equals("")) {
					if (!searchCondition.equals("")) {
						searchCondition = searchCondition + " and "
								+ oldSearchCondition;
					} else {
						searchCondition = oldSearchCondition;
					}
				}
			} else {
				searchCondition = oldSearchCondition;
			}
			request.setAttribute("searchCondition", searchCondition);

			if (cmdFlag.equals("MainList")) {
				String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));
				String showQYTree = StringUtil.tranCodeP(request.getParameter("showQYTree"));
				TableObject aTableObject = handle.getCurrentTableObject(tableId, true);
				if ( showQYTree.equals("T")) {
					cmdFlag = "ShowTree";
				}
			}

			// 根据id查询信息
			if (cmdFlag.equals("MainList")) {
				String tableId = StringUtil.tranCodeP(request
						.getParameter("tableId"));
				String orderName = StringUtil.tranCodeP(request
						.getParameter("orderName"));
				String orderType = StringUtil.tranCodeP(request
						.getParameter("orderType"));
				String strpagenum = StringUtil.tranCodeP(request
						.getParameter("pagenum"));
				String oldCndt = StringUtil.tranCodeP(request
						.getParameter("oldCndt"));
				String specialTable = StringUtil.tranCodeP(request
						.getParameter("specialTable"));
				String specialAsTable = StringUtil.tranCodeP(request
						.getParameter("specialAsTable"));
				String isShowSearchLayer = StringUtil.tranCodeP(request
						.getParameter("isShowSearchLayer"));

				try {
					PageInfoVO aPageInfoVO = new PageInfoVO();
					TableObject aTableObject = handle.getCurrentTableObject(
							tableId, true);
					if (!specialTable.equals("")) {
						aTableObject.setTablePyName(specialTable.replace("quote", "'"));
						aTableObject.setTableAsName(specialAsTable);
					}
					if (!isShowSearchLayer.equals("")) {
						aTableObject.setIsShowSearchLayer(isShowSearchLayer
								.equals("true"));
					}
					aTableObject.setSubTableObjects(handle
							.getSubTableObjects(tableId));
					int pagenum = 1;
					if (!strpagenum.equals("")) {
						pagenum = Integer.parseInt(strpagenum);
					}
					aPageInfoVO.curPage = pagenum;

					if (aTableObject.getSubTableObjects() == null) {
						aPageInfoVO.rowsPerPage = 19;
					} else {
						aPageInfoVO.rowsPerPage = 10;
					}
					aPageInfoVO.orderName = orderName;
					aPageInfoVO.orderType = orderType;

					aPageInfoVO.queryCommCondition = "where 1=1 ";
					if (!oldCndt.equals("") && aTableObject.getSelectFlag()) {
						aPageInfoVO.queryCommCondition = aPageInfoVO.queryCommCondition
								+ " and " + oldCndt;
					}
					if (!searchCondition.equals("")) {
						aPageInfoVO.queryCommCondition = aPageInfoVO.queryCommCondition
								+ " and " + searchCondition;
					}
					String checkCondition = aTableObject.getCheckCondition();
					if (!checkCondition.equals("")) {
						aPageInfoVO.queryCommCondition = aPageInfoVO.queryCommCondition
								+ " and " + checkCondition;
					}
					/** 获取数据 */
					selectService.setValues(aPageInfoVO, aTableObject);

					request.setAttribute("TableObject", aTableObject);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("ErrMsg", e);
				}

				if (StringUtil.tranCodeP(request.getParameter("preSearch"))
						.equals("T")) {
					/*
					 * forward( "jsp/common/groupware/select/preSearch.jsp",
					 * request, response);
					 */
					return mapping.findForward("MainList_T");
				} else {
					/*
					 * forward( "jsp/common/groupware/select/mainList.jsp",
					 * request, response);
					 */
					return mapping.findForward("MainList_Tother");
				}
				// return;
			}

			// 根据主表信息查询子表记录
			if (cmdFlag.equals("SubList")) {
				String tableId = StringUtil.tranCodeP(request
						.getParameter("tableId"));
				String orderName = StringUtil.tranCodeP(request
						.getParameter("orderName"));
				String orderType = StringUtil.tranCodeP(request
						.getParameter("orderType"));
				String strpagenum = StringUtil.tranCodeP(request
						.getParameter("pagenum"));
				String oldCndt = StringUtil.tranCodeP(request
						.getParameter("oldCndt"));
				try {
					PageInfoVO aPageInfoVO = new PageInfoVO();
					int pagenum = 1;
					if (!strpagenum.equals("")) {
						pagenum = Integer.parseInt(strpagenum);
					}
					aPageInfoVO.curPage = pagenum;
					aPageInfoVO.rowsPerPage = 5;
					aPageInfoVO.orderName = orderName;
					aPageInfoVO.orderType = orderType;

					TableObject aTableObject = handle.getCurrentTableObject(
							tableId, false);
					// 如果是往来单位选取，添加区域过滤
					if (aTableObject.getIsWLDWXX()) {
						aTableObject.setSelectFlag(true);
						for (int i = 0; i < aTableObject.getFieldPyName().length; i++) {
							if (aTableObject.getFieldPyName()[i]
									.equalsIgnoreCase("QYID")) {
								String[] tmp = aTableObject.getForeignKeyId();
								tmp[i] = "0";
								aTableObject.setForeignKeyId(tmp);
								break;
							}
						}
					}

					String[] foreignKeyId = aTableObject.getForeignKeyId();
					StringBuffer sb = new StringBuffer("where 1=1 ");
					for (int i = 0; i < foreignKeyId.length; i++) {
						if (foreignKeyId[i] != null&& !foreignKeyId[i].equals("")) {
							String tmpName = aTableObject.getFieldAsName()[i];
							if (tmpName == null || tmpName.equals("")) {
								tmpName = aTableObject.getFieldPyName()[i];
							}
							String pkValue = StringUtil.tranCodeP(request
									.getParameter("PK" + foreignKeyId[i]));
							if (pkValue == null || pkValue.equals("")) {
								sb.append(" and 1<>1");
							} else {
								sb.append(" and " + tmpName + " = '" + pkValue
										+ "'");
							}
						}
					}

					aPageInfoVO.queryCommCondition = sb.toString();
					aTableObject.setSubTableObjects(handle
							.getSubTableObjects(tableId));
					if (!searchCondition.equals("")) {
						aPageInfoVO.queryCommCondition = aPageInfoVO.queryCommCondition
								+ " and " + searchCondition;
					}
					if (aTableObject.getSelectFlag() && !oldCndt.equals("")) {
						aPageInfoVO.queryCommCondition = aPageInfoVO.queryCommCondition
								+ " and " + oldCndt;
					}
					/* 获取数据 */
					selectService.setValues(aPageInfoVO, aTableObject);
					request.setAttribute("TableObject", aTableObject);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("ErrMsg", e);
				}
				/*
				 * forward( "jsp/common/groupware/select/subList.jsp", request,
				 * response);
				 */
				return mapping.findForward("SubList");
			}

			if (cmdFlag.equals("ShowTree")) {
				String tableId = StringUtil.tranCodeP(request
						.getParameter("tableId"));
				String oldCndt = StringUtil.tranCodeP(request
						.getParameter("oldCndt"));
				String specialTable = StringUtil.tranCodeP(request
						.getParameter("specialTable"));
				String specialAsTable = StringUtil.tranCodeP(request
						.getParameter("specialAsTable"));
				TableObject aTableObject = handle.getCurrentTableObject(
						tableId, true);
				aTableObject.setSubTableObjects(handle
						.getSubTableObjects(tableId));
				String nodeName = "";
				String parentNodeName = "";
				String keyName = "";
				for (int i = 0; i < aTableObject.getFieldPyName().length; i++) {
					if (aTableObject.getIsNode()[i]) {
						nodeName = aTableObject.getFieldPyName()[i];
					}
					if (aTableObject.getIsParentNode()[i]) {
						parentNodeName = aTableObject.getFieldPyName()[i];
					}
					if (aTableObject.getIsTreeKey()[i]) {
						keyName = aTableObject.getFieldPyName()[i];
					}
				}
				if (!specialTable.equals("")) {
					aTableObject.setTablePyName(specialTable.replace("quote", "'"));
					aTableObject.setTableAsName(specialAsTable);
				}
                StringBuffer condition = new StringBuffer("");
				InitObject initObject = new InitObject();
				initObject.setFieldAsName(aTableObject.getFieldAsName());
				if (aTableObject.getSelectFlag() && !aTableObject.getIsWLDWXX()) {
					initObject.setFilterCondition(oldCndt);
				} else if (aTableObject.getIsWLDWXX()) {
					initObject.setFilterCondition(condition.toString());
				}

				initObject.setNodeName(nodeName);
				initObject.setOrderName(aTableObject.getOrderName());
				initObject.setOrderType(aTableObject.getOrderType());
				initObject.setFieldPyName(aTableObject.getFieldPyName());
				initObject.setParentNodeName(parentNodeName);
				initObject.setKeyName(keyName);
				initObject.setTablePyName(aTableObject.getTablePyName());
				initObject.setTableAsName(aTableObject.getTableAsName());
				initObject.setLeafCondition(aTableObject.getLeafCondition());
				initObject.setCheckCondition(aTableObject.getCheckCondition());
				try {
					// TreeDBAccessAbs dbaccess = null;
					if (aTableObject.getIsTree() && aTableObject.getIsWLXX()) {
						initObject.setBMCD(6);
						initObject.setBMMC("WLBM");
						// dbaccess = new SpecialTreeDBAccess(initObject);
					} else {
						// dbaccess = new NormalTreeDBAccess(initObject);
					}

					String nodeValue = StringUtil.tranCodeP(request
							.getParameter("nodeValue"));
					String childrenNum = StringUtil.tranCodeP(request
							.getParameter("childrenNum"));
					if (childrenNum.equals("")) {
						childrenNum = "-1";
					}
					String level = StringUtil.tranCodeP(request
							.getParameter("level"));
					if (level.equals("")) {
						level = "0";
					}
					if (aTableObject.getIsTree() && aTableObject.getIsWLXX()) {
						request.setAttribute("result", selectService.getSpecialTreeChildren(
								nodeValue, Integer.parseInt(level),
								aTableObject.getDisplayModel(), Integer.parseInt(childrenNum),initObject));
					}
					else{
						request.setAttribute("result", selectService.getNormalTreeChildren(
								nodeValue, Integer.parseInt(level),
								aTableObject.getDisplayModel(), Integer.parseInt(childrenNum),initObject));
					}

				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("ErrMsg", e);
				}
				request.setAttribute("TableObject", aTableObject);
				// forward(
				// "jsp/common/groupware/select/tree.jsp",
				// request,
				// response);
				return mapping.findForward("tree");
			}

			if (cmdFlag.equals("preSearchFrame")) {
				/*
				 * forward( "jsp/common/groupware/select/preSearchFrame.jsp",
				 * request, response);
				 */
				return mapping.findForward("preSearchFrame");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("blank");
	}

	/**
	 * Creates the qy object.
	 * 
	 * @param tableId the table id
	 * 
	 * @return the table object
	 */
	private TableObject createQYObject(String tableId) {
		TableObject aTableObject = new TableObject();
		aTableObject.setTablePyName("T_QY");
		aTableObject.setTableAsName("");
		aTableObject.setTableLgName("往来单位信息");
		aTableObject.setId(tableId);
		aTableObject.setIsShowSearchLayer(false);
		aTableObject.setIsTree(true);
		aTableObject.setIsWLXX(false);
		aTableObject.setIsWLDWXX(false);
		aTableObject.setOrderName("QYBH");
		aTableObject.setOrderType("ASC");
		aTableObject.setLeafCondition("");
		aTableObject.setStartCondition("");
		aTableObject.setCheckCondition("");
		aTableObject.setDisplayModel("[#QYBH#]#QYMC#");
		aTableObject.setDisplayTreeFlag(false);
		aTableObject.setSelectFlag(false);

		String[] fieldPyName = { "QYMC", "QYBH", "QYID", "SJQY" };
		String[] fieldAsName = { "", "", "", "" };
		String[] fieldLgName = { "", "", "", "" };
		String[] fieldType = { "char", "char", "char", "char" };
		String[] decimalSystem = { "", "", "", "" };
		String[] decimalType = { "", "", "", "" };
		String[] decimalMax = { "", "", "", "" };
		boolean[] isShow = { true, true, false, false };
		boolean[] isSearch = { true, true, false, false };
		String[] primaryKeyId = { "", "", "0", "" };
		String[] foreignKeyId = { "", "", "", "" };
		boolean[] isParentNode = { false, false, false, true };
		boolean[] isNode = { false, false, true, false };
		boolean[] isTreeKey = { false, false, true, false };

		aTableObject.setFieldPyName(fieldPyName);
		aTableObject.setFieldAsName(fieldAsName);
		aTableObject.setFieldLgName(fieldLgName);
		aTableObject.setFieldType(fieldType);
		aTableObject.setDecimalSystem(decimalSystem);
		aTableObject.setDecimalType(decimalType);
		aTableObject.setDecimalMax(decimalMax);
		aTableObject.setIsShow(isShow);
		aTableObject.setIsSearch(isSearch);
		aTableObject.setPrimaryKeyId(primaryKeyId);
		aTableObject.setForeignKeyId(foreignKeyId);
		aTableObject.setIsParentNode(isParentNode);
		aTableObject.setIsNode(isNode);
		aTableObject.setIsTreeKey(isTreeKey);
		return aTableObject;
	}

	/**
	 * Sets the select service.
	 * 
	 * @param selectService the new select service
	 */
	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}

	/**
	 * Sets the select service.
	 * 
	 * @param fieldType the field type
	 * @param fieldName the field name
	 * @param request the request
	 * 
	 * @return the string
	 */
	public String setSelectService(String fieldType,String fieldName,HttpServletRequest request) {
	StringBuffer serCondition = new StringBuffer("");
	if (fieldType.equalsIgnoreCase("number")) {
		String serFromValue = StringUtil.tranCodeP(request
				.getParameter("from" + fieldName));
		String serToValue = StringUtil.tranCodeP(request
				.getParameter("to" + fieldName));
		if (serFromValue != null
				&& !serFromValue.equals("")) {
			serCondition.append(" and " + fieldName
					+ " >= " + serFromValue);
		}
		if (serToValue != null && !serToValue.equals("")) {
			serCondition.append(" and " + fieldName
					+ " <= " + serToValue);
		}
	} else if (fieldType.equalsIgnoreCase("date")
			|| fieldType.equalsIgnoreCase("timestamp")) {
		String serFromValue = StringUtil.tranCodeP(request
				.getParameter("from" + fieldName));
		String serToValue = StringUtil.tranCodeP(request
				.getParameter("to" + fieldName));
		if (serFromValue != null
				&& !serFromValue.equals("")) {
			serCondition.append(" and "
					+ DateUtil.getDateFieldForSql(
							fieldName, DBTYPE)
					+ " >= "
					+ DateUtil.getDateValueForSql(
							serFromValue, DBTYPE));
		}
		if (serToValue != null && !serToValue.equals("")) {
			serCondition.append(" and "
					+ DateUtil.getDateFieldForSql(
							fieldName, DBTYPE)
					+ " <= "
					+ DateUtil.getDateValueForSql(
							serToValue, DBTYPE));
		}
	} else {
		String serValue = StringUtil.tranCodeP(request.getParameter("ser" + fieldName));
		if (serValue != null && !serValue.equals("")) {
			serCondition.append(" and "
					+ fieldName
					+ " like '"
					+ StringUtil.tranCodeP(request
							.getParameter("leftcheckbox"
									+ fieldName))
					+ serValue
					+ StringUtil.tranCodeP(request
							.getParameter("rightcheckbox"
									+ fieldName)) + "'");
		}
	}
	return serCondition.toString();
	}
}
