/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.select.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.DataObject;
import com.hoperun.commons.model.PageInfoVO;
import com.hoperun.commons.select.object.InitObject;
import com.hoperun.commons.select.object.TableObject;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectService.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class SelectService extends BaseService {

	/**
	 * 选取多条记录.
	 * 
	 * @param aPageInfoVO the a page info vo
	 * @param aTableObject the a table object
	 * 
	 * @throws Exception the exception
	 */
	public void setValues(PageInfoVO aPageInfoVO, TableObject aTableObject)
			throws Exception {
		Vector vec = new Vector();
		String tablePyName = aTableObject.getTablePyName();
		String tableAsName = aTableObject.getTableAsName();
		if (tableAsName != null && !tableAsName.equals("")) {
			tablePyName = tablePyName + " " + tableAsName;
		}

		String[] fieldPyName = aTableObject.getFieldPyName();
		String[] fieldAsName = aTableObject.getFieldAsName();
		String[] listNames = new String[fieldPyName.length];
		String[] listasNames = new String[fieldPyName.length];
		// 组建选取的列名数组
		for (int i = 0; i < fieldPyName.length; i++) {
			if (fieldAsName[i] == null || fieldAsName[i].equals("")) {
				listNames[i] = fieldPyName[i];
				listasNames[i] = fieldPyName[i];
			} else {
				listNames[i] = fieldPyName[i] + " " + fieldAsName[i];
				listasNames[i] =  fieldAsName[i];
			}
		}

		aPageInfoVO.listNames = listNames;
		aPageInfoVO.listasNames = listasNames;
		aPageInfoVO.tableName = tablePyName;
		// 如果没有排序方式
		if (aPageInfoVO.orderName == null || aPageInfoVO.orderName.equals("")) {
			// 如果配置文件中没有定义排序方式，则按第一个字段排序
			if (aTableObject.getOrderName() == null
					|| aTableObject.getOrderName().equals("")) {
				if (fieldAsName[0] == null || fieldAsName[0].equals("")) {
					aPageInfoVO.orderName = fieldPyName[0];
				} else {
					aPageInfoVO.orderName = fieldAsName[0];
				}
				aPageInfoVO.orderType = "DESC";
			} else {
				// 否则按配置文件中的定义排序
				aPageInfoVO.orderName = aTableObject.getOrderName();
				aPageInfoVO.orderType = aTableObject.getOrderType();
			}
		}

		// ResultSet rs = null;
		List rsList = null;
		try {
			rsList = selectPageRs(aPageInfoVO);

			if (rsList != null && rsList.size() > 0) {
				for (int h = 0; h < rsList.size(); h++) {

					// 将选取结果放在Hashtable中
					Hashtable<String, String> ht = new Hashtable<String, String>();
					for (int i = 0; i < fieldPyName.length; i++) {
						String tmp = "";
						if (fieldAsName[i] == null || fieldAsName[i].equals("")) {
							tmp = fieldPyName[i];
						} else {
							tmp = fieldAsName[i];
						}
						String value = getField((Map) rsList.get(h), tmp);
						if (value == null) {
							value = "";
						}
						// 按小数位格式化
						if (aTableObject.getFieldType()[i].equals("number")) {
							if (!value.equals("")
									&& aTableObject.getDecimalMax()[i] != null
									&& !aTableObject.getDecimalMax()[i]
											.equals("")) {
								value = StringUtil.round(value,
										Integer.parseInt(aTableObject
												.getDecimalMax()[i]));
							} else if (!value.equals("")
									&& aTableObject.getDecimalSystem()[i] != null
									&& !aTableObject.getDecimalSystem()[i]
											.equals("")
									&& aTableObject.getDecimalType()[i] != null
									&& !aTableObject.getDecimalType()[i]
											.equals("")) {
								value = StringUtil.getValue(value, aTableObject
										.getDecimalSystem()[i], aTableObject
										.getDecimalType()[i]);
							}
						} else if (!value.equals("")
								&& aTableObject.getFieldType()[i]
										.equals("timestamp")) {
							// 如果是时间戳则截掉秒后部分
							value = value.substring(0, value.indexOf("."));
						} else if (!value.equals("")
								&& aTableObject.getFieldType()[i]
										.equals("date")) {
							value = getDateField((Map) rsList.get(h), tmp);
						}
						ht.put(tmp, value);
					}
					vec.add(ht);
				}
			}
		} finally {
			// closePageRs();
		}
		vec.add(aPageInfoVO);
		aTableObject.setValues(vec);
	}

	/**
	 * Select page rs.
	 * 
	 * @param pageInfoVO the page info vo
	 * 
	 * @return the list
	 * 
	 * @throws Exception the exception
	 */
	public List selectPageRs(PageInfoVO pageInfoVO) throws Exception {
		// ResultSet pageRs = null;
		List pageResList = null;
		try {
			int acurPage = pageInfoVO.curPage;
			// Comm.log(1, "当前翻页Bean开始执行翻页操作：检索第" + acurPage + "页");
			calMaxRowCount(pageInfoVO);
			calMaxPage(pageInfoVO);
		    pageInfoVO.querySql = createQuerySql(pageInfoVO);
			
			
			// Comm.log(1, "查询翻页的SQL=" + pageInfoVO.querySql);
			calMaxPage(pageInfoVO);
			if (acurPage > pageInfoVO.maxPage) {
				acurPage = pageInfoVO.maxPage;
				setCurPage(acurPage, pageInfoVO);
			}
			// this.conn = getConn();
			// this.pstmt = getPstmt(this.conn, pageInfoVO.querySql);
			// pageRs = pstmtQuery(this.pstmt);
			Map params = new HashMap();
			params.put("SelSQL", pageInfoVO.querySql);
			// params.put("CouSQL", "select count(*) as allct from " +
			// pageInfoVO.tableName + " " + pageInfoVO.queryCommCondition);
			// pageResList = pageQuery("sqlcom.pageQuery","sqlcom.pageCount",
			// params,pageInfoVO.curPage,pageInfoVO.rowsPerPage);
			pageResList = findList("sqlcom.query", params);
			/*
			 * if (pageRs != null) { Comm.log(1, "当前翻页Bean查出的最大行数:" +
			 * pageInfoVO.maxRowCount); Comm.log(1, "当前翻页Bean查出的最大页数:" +
			 * pageInfoVO.maxPage); }
			 */
			calCurMaxRows(pageInfoVO);
			/*
			 * if ((pageInfoVO.curPage > 1) && (pageInfoVO.rowsPerPage != -1))
			 * pageRs.absolute( pageInfoVO.rowsPerPage * (pageInfoVO.curPage -
			 * 1));
			 */
		} catch (Exception ex) {
			pageResList = null;
			ex.printStackTrace();

			throw ex;
		}
		return pageResList;
	}

	/**
	 * Cal max page.
	 * 
	 * @param pageInfoVO the page info vo
	 */
	private void calMaxPage(PageInfoVO pageInfoVO) {
		if (pageInfoVO.maxRowCount % pageInfoVO.rowsPerPage == 0)
			pageInfoVO.maxPage = pageInfoVO.maxRowCount
					/ pageInfoVO.rowsPerPage;
		else
			pageInfoVO.maxPage = pageInfoVO.maxRowCount
					/ pageInfoVO.rowsPerPage + 1;
		if (pageInfoVO.rowsPerPage == -1)
			pageInfoVO.maxPage = 1;
	}

	/**
	 * Cal cur max rows.
	 * 
	 * @param pageInfoVO the page info vo
	 */
	private void calCurMaxRows(PageInfoVO pageInfoVO) {
		if (pageInfoVO.curPage == pageInfoVO.maxPage)
			pageInfoVO.curMaxRow = pageInfoVO.maxRowCount
					- (pageInfoVO.curPage - 1) * pageInfoVO.rowsPerPage;
		else
			pageInfoVO.curMaxRow = pageInfoVO.rowsPerPage;
	}

	/**
	 * Cal max row count.
	 * 
	 * @param pageInfoVO the page info vo
	 * 
	 * @throws Exception the exception
	 */
	private void calMaxRowCount(PageInfoVO pageInfoVO) throws Exception {
		String atableName = pageInfoVO.tableName;
		String aqueryCondition = pageInfoVO.queryCommCondition;
		String tempListSql = pageInfoVO.getStrListNames();
		tempListSql = tempListSql.toUpperCase();
		if (tempListSql.indexOf("DISTINCT") != -1) {
			String tempZD = tempListSql.substring(tempListSql
					.indexOf("DISTINCT") + 8, tempListSql.length());
			try {
				tempZD = tempZD.substring(tempZD.indexOf("(") + 1, tempZD
						.indexOf(")"));
			} catch (Exception ex) {
				tempZD = "";
			}
			if (!tempZD.equals(""))
				pageInfoVO.maxRowCount = getRowNum(atableName, aqueryCondition,
						"DISTINCT(" + tempZD + ")");
		} else {
			pageInfoVO.maxRowCount = getRowNum(atableName, aqueryCondition);
		}
	}

	/**
	 * Creates the query sql.
	 * 
	 * @param pageInfoVO the page info vo
	 * 
	 * @return the string
	 */
	private String createQuerySql(PageInfoVO pageInfoVO) {
		int rowsPage = pageInfoVO.rowsPerPage;
		int curPage = pageInfoVO.curPage;
		// Comm.log(1, "当前的实际最大页数:" + pageInfoVO.maxPage);
		// Comm.log(1, " 当前的选取目标页数:" + curPage);
		if (curPage > pageInfoVO.maxPage) {
			curPage = pageInfoVO.maxPage;
		} else if (curPage < 1) {
			curPage = 1;
			pageInfoVO.curPage = 1;
		}

		// Comm.log(1, " 计算过最大页数:" + pageInfoVO.maxPage);
		// Comm.log(1, " 计算过选取目标页数:" + curPage);

		if (pageInfoVO.isOnlyOneSql()) {
			if (rowsPage != -1) {
				if (Consts.DBTYPE.equalsIgnoreCase("ORACLE"))
					return pageInfoVO.getQuerySql();
				if (Consts.DBTYPE.equalsIgnoreCase("DB2")) {
					int maxRows = rowsPage * curPage;
					if (maxRows > pageInfoVO.maxRowCount) {
						maxRows = pageInfoVO.maxRowCount;
					}
					if (maxRows == 0) {
						maxRows = 1;
					}
					return pageInfoVO.getQuerySql() + " fetch first  "
							+ maxRows + " rows only with ur ";
				}
				
			if (Consts.DBTYPE.equalsIgnoreCase("MSSQL"))
				return pageInfoVO.getQuerySql();
			} else {
				return pageInfoVO.getQuerySql();
			}
			

		}

		StringBuffer tempSql = new StringBuffer("");
		if (rowsPage != -1) {
			if (Consts.DBTYPE.equalsIgnoreCase("ORACLE")) {
				tempSql.append(pageInfoVO.getQueryPreSql());
				tempSql.append(" select * from (select ");
				tempSql.append(pageInfoVO.getStrListasNames());
				tempSql.append(", rownum amos_rowid from(select ");
				tempSql.append(pageInfoVO.getStrListNames());
				tempSql.append(" from ");
				tempSql.append(pageInfoVO.tableName);
				tempSql.append(" ");
				tempSql.append(getQueryCommCondition(pageInfoVO));
				if ((pageInfoVO.orderName != null)
						&& (!pageInfoVO.orderName.equals(""))) {
					tempSql.append(" order by " + pageInfoVO.orderName + " ");
				}
				if ((pageInfoVO.orderType != null)
						&& (!pageInfoVO.orderType.equals(""))) {
					tempSql.append(pageInfoVO.orderType);
				}
				//edit by zhuxw 2012-10-30
				//tempSql.append(") where rownum<" + (rowsPage * curPage + 1));
				//tempSql.append(") result_1 where result_1.amos_rowid >="+ ((curPage - 1) * rowsPage + 1));
				tempSql.append(") tempData ");
				tempSql.append(") result_1 where result_1.amos_rowid >="+ ((curPage - 1) * rowsPage + 1));
				tempSql.append(" and result_1.amos_rowid<"+ (rowsPage * curPage + 1));
			} else if (Consts.DBTYPE.equalsIgnoreCase("DB2")) {
				tempSql.append(pageInfoVO.getQueryPreSql());
				tempSql.append(" select " + pageInfoVO.getStrListNames());
				tempSql.append(" from " + pageInfoVO.tableName);
				tempSql.append("  " + getQueryCommCondition(pageInfoVO));
				if ((pageInfoVO.orderName != null)
						&& (!pageInfoVO.orderName.equals(""))) {
					tempSql.append(" order by " + pageInfoVO.orderName + " ");
				}
				if ((pageInfoVO.orderType != null)
						&& (!pageInfoVO.orderType.equals(""))) {
					tempSql.append(pageInfoVO.orderType);
				}
				int maxRows = rowsPage * curPage;
				if (maxRows > pageInfoVO.maxRowCount) {
					maxRows = pageInfoVO.maxRowCount;
				}
				if (maxRows == 0) {
					maxRows = 1;
				}
				tempSql.append(" fetch first  " + maxRows
						+ " rows only with ur ");
			} else if (Consts.DBTYPE.equalsIgnoreCase("MSSQL")) {
				int maxRows = rowsPage * curPage;
				if (maxRows > pageInfoVO.maxRowCount) {
					maxRows = pageInfoVO.maxRowCount;
				}
				if (maxRows == 0) {
					maxRows = 1;
				}
				tempSql.append(pageInfoVO.getQueryPreSql());
				
				tempSql.append(" select "+ pageInfoVO.getStrListNames());
				tempSql.append(" from ");
				tempSql.append("(select row_number() over(order by "+ pageInfoVO.orderName+" "+ pageInfoVO.orderType+") rownum_,"
						+ pageInfoVO.getStrListNames());
				tempSql.append(" from " + pageInfoVO.tableName);
				tempSql.append("  " + getQueryCommCondition(pageInfoVO));
				tempSql.append(")tmp");
				tempSql.append(" where rownum_>="+ ((curPage - 1) * rowsPage + 1));
				tempSql.append(" and rownum_<"+ (rowsPage * curPage + 1));
			}
		} 
		return tempSql.toString();
	}
	
	/**
	 * Gets the query comm condition.
	 * 
	 * @param pageInfoVO the page info vo
	 * 
	 * @return the query comm condition
	 */
	private String getQueryCommCondition(PageInfoVO pageInfoVO) {
		if (pageInfoVO.queryCommCondition != null) {
			if (pageInfoVO.queryCommCondition.trim().equals("")) {
				pageInfoVO.queryCommCondition = " where 1=1 ";
			} else {
				pageInfoVO.queryCommCondition = pageInfoVO.queryCommCondition
						.trim();
				pageInfoVO.queryCommCondition = (" where ("
						+ pageInfoVO.queryCommCondition.substring(5,
								pageInfoVO.queryCommCondition.length()) + ") ");
			}
		}
		return pageInfoVO.queryCommCondition;
	}

	/**
	 * Gets the field.
	 * 
	 * @param mrs the mrs
	 * @param fieldName the field name
	 * 
	 * @return the field
	 */
	public String getField(Map mrs, String fieldName) {
		String temp = "";
		try {
			if (mrs != null) {
				temp = (mrs.get(fieldName) == null ? "" : mrs.get(fieldName)
						.toString());
				if (temp == null)
					temp = "";
			}
			return temp;
		} catch (Exception ex) {
			System.err.println("获取[" + fieldName + "]出错！" + ex.getMessage());

			temp = "";
		}
		return temp;
	}

	/**
	 * Rs is null.
	 * 
	 * @param rs the rs
	 * 
	 * @return true, if successful
	 */
	public boolean rsIsNull(ResultSet rs) {
		return rs == null;
	}

	/**
	 * Sets the cur page.
	 * 
	 * @param value the value
	 * @param pageInfoVO the page info vo
	 */
	private void setCurPage(int value, PageInfoVO pageInfoVO) {
		if (value < 1)
			value = 1;
		pageInfoVO.curPage = value;
	}

	/**
	 * Gets the date field.
	 * 
	 * @param mrs the mrs
	 * @param fieldName the field name
	 * 
	 * @return the date field
	 * 
	 * @throws SQLException the SQL exception
	 */
	private String getDateField(Map mrs, String fieldName) throws SQLException {
		String temp = "";
		//if (mrs != null)
		//	return temp;
		try {
			if (mrs.get(fieldName) == null) {
				return temp;
			}

			String strDate = String.valueOf(mrs.get(fieldName));
			return strDate;
		} catch (Exception ex) {
			System.err.println("获取[" + fieldName + "]出错！" + ex.getMessage());
		}
		return temp;
	}

	/**
	 * Gets the row num.
	 * 
	 * @param table the table
	 * @param condition the condition
	 * @param distinctZD the distinct zd
	 * 
	 * @return the row num
	 * 
	 * @throws SQLException the SQL exception
	 */
	private int getRowNum(String table, String condition, String distinctZD)
			throws SQLException {
		int rsnum = 0;
		try {
			String sql = "select count(" + distinctZD + ") as ALLCT from "
					+ table + " " + condition;// + " with ur "
			Map params = new HashMap();
			params.put("SelSQL", sql);
			List resList = findList("sqlcom.query", params);
			if (resList != null && resList.size() > 0) {
				for (int i = 0; i < resList.size(); i++) {
					Map<?, ?> resMap = (Map<?, ?>) resList.get(i);
					rsnum = (resMap.get("ALLCT") == null ? 0 : Integer
							.parseInt(resMap.get("ALLCT").toString()));
				}
			}
		} catch (Exception ex) {
			System.err.println("getRowNum\u8C03\u7528\u51FA\u9519\uFF1A"
					+ ex.toString());
		}
		// Comm.log(1, "GetRowNum\u88AB\u6210\u529F\u6267\u884C,\u627E\u5230" +
		// rsnum + "\u6761\u8BB0\u5F55!");
		return rsnum;
	}

	/**
	 * Gets the row num.
	 * 
	 * @param table the table
	 * @param condition the condition
	 * 
	 * @return the row num
	 * 
	 * @throws SQLException the SQL exception
	 */
	private int getRowNum(String table, String condition) throws SQLException {
		int rsnum = getRowNum(table, condition, "*");
		return rsnum;
	}

	/*-----------SpecialTree开始--------------*/
	/**
	 * 取的某个节点所有子节点(为空即取所有根节点) Method getChildren.
	 * 
	 * @param level the level
	 * @param nodeValue the node value
	 * @param displayModel the display model
	 * @param childrenNum the children num
	 * @param vInitObject the v init object
	 * 
	 * @return Vector
	 * 
	 * @throws Exception the exception
	 */
	public Vector<DataObject> getSpecialTreeChildren(String nodeValue,
			int level, String displayModel, int childrenNum,
			InitObject vInitObject) throws Exception {
		/*--init-beg---*/
		InitObject initObject = null;
		// String additionalSql = "";
		String tableName = "";

		initObject = vInitObject;
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			tableName = initObject.getTablePyName() + " "
					+ initObject.getTableAsName();
		} else {
			tableName = initObject.getTablePyName();
		}
		if (initObject.getKeyName() == null
				|| initObject.getKeyName().equals("")) {
			initObject.setKeyName(initObject.getNodeName());
		}
		/*--init-end---*/
		level++;
		StringBuffer sql = new StringBuffer(
				(initObject.getWithSql() == null ? "" : initObject.getWithSql()));
		sql.append("select * from (select ");
		sql.append(getListNamesForSql(initObject));

		// 添加附加查询字段childrenNum，当前节点的子节点数量
		sql.append(",(select count(*) from (select ");
		sql.append(initObject.getParentNodeName());
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where length(");
		sql.append(initObject.getBMMC());
		sql.append(")=");
		sql.append((level + 1) * initObject.getBMCD());
		sql.append(" and ");
		sql.append(initObject.getBMMC());
		sql.append(" in (select substr(");
		sql.append(initObject.getBMMC());
		sql.append(",1,");
		sql.append((level + 1) * initObject.getBMCD());
		sql.append(") from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		if (initObject.getFilterCondition() != null
				&& !initObject.getFilterCondition().equals("")) {
			sql.append(" and " + initObject.getFilterCondition());
		}
		sql.append(" and length(");
		sql.append(initObject.getBMMC());
		sql.append(")>=");
		sql.append((level + 1) * initObject.getBMCD());
		sql.append(")) treeTmpTable1 where treeTmpTable1."
				+ initObject.getParentNodeName() + "=");
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			sql.append(initObject.getTableAsName());
		} else {
			sql.append(initObject.getTablePyName());
		}
		sql.append("." + initObject.getNodeName() + ") childrenNum, ");

		// 添加附加查询字段selectFlag，字段是否能被选择，默认能
		if ((initObject.getCheckCondition() != null && !initObject
				.getCheckCondition().equals(""))
				|| (initObject.getFilterCondition() != null && !initObject
						.getFilterCondition().equals(""))) {
			sql.append("(select count(*) from (select ");
			sql.append(initObject.getNodeName());
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" where 1=1 ");
			if (initObject.getCheckCondition() != null
					&& !initObject.getCheckCondition().equals("")) {
				sql.append(" and " + initObject.getCheckCondition());
			}
			if (initObject.getFilterCondition() != null
					&& !initObject.getFilterCondition().equals("")) {
				sql.append(" and " + initObject.getFilterCondition());
			}
			sql.append(") treeTmpTable2 where treeTmpTable2."
					+ initObject.getNodeName() + "=");
			if (initObject.getTableAsName() != null
					&& !initObject.getTableAsName().equals("")) {
				sql.append(initObject.getTableAsName());
			} else {
				sql.append(initObject.getTablePyName());
			}
			sql.append("." + initObject.getNodeName() + ")");
		} else {
			sql.append("1");
		}
		sql.append(" selectFlag, ");

		// 添加附加查询字段isLeaf，字段是否终结点，默认否
		if (initObject.getLeafCondition() != null
				&& !initObject.getLeafCondition().equals("")) {
			sql.append("(select count(*) from (select ");
			sql.append(initObject.getNodeName());
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" where " + initObject.getLeafCondition());
			sql.append(") treeTmpTable3 where treeTmpTable3."
					+ initObject.getNodeName() + "=");
			if (initObject.getTableAsName() != null
					&& !initObject.getTableAsName().equals("")) {
				sql.append(initObject.getTableAsName());
			} else {
				sql.append(initObject.getTablePyName());
			}
			sql.append("." + initObject.getNodeName() + ")");
		} else {
			sql.append("0");
		}
		sql.append(" isLeaf from ");
		sql.append(tableName);
		sql.append(" where length(");
		sql.append(initObject.getBMMC());
		sql.append(")=");
		sql.append(level * initObject.getBMCD());
		sql.append(" and ");
		sql.append(initObject.getBMMC());
		sql.append(" in (select substr(");
		sql.append(initObject.getBMMC());
		sql.append(",1,");
		sql.append(level * initObject.getBMCD());
		sql.append(") from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		if (initObject.getFilterCondition() != null
				&& !initObject.getFilterCondition().equals("")) {
			sql.append(" and " + initObject.getFilterCondition());
		}
		sql.append(" and length(");
		sql.append(initObject.getBMMC());
		sql.append(")>=");
		sql.append(level * initObject.getBMCD());
		sql.append(")) treetmptable4 where ");
		if (nodeValue == null || nodeValue.equals("")) {
			if (initObject.getStartCondition() != null
					&& !initObject.getStartCondition().equals("")) {
				sql.append(initObject.getStartCondition());
			} else {
				sql.append("(" + initObject.getParentNodeName()
						+ " is null or " + initObject.getParentNodeName()
						+ " ='')");
			}
		} else {
			sql.append(initObject.getParentNodeName() + "='" + nodeValue + "'");
		}

		if (initObject.getOrderName() == null
				|| "".equals(initObject.getOrderName())) {
			sql.append(" order by " + initObject.getNodeName() + " ASC");
		} else {
			sql.append(" order by " + initObject.getOrderName() + " "
					+ initObject.getOrderType());
		}

		String[] arr = new String[initObject.getFieldPyName().length];
		for (int i = 0; i < arr.length; i++) {
			if (initObject.getFieldAsName() != null
					&& initObject.getFieldAsName()[i] != null
					&& !initObject.getFieldAsName()[i].equals("")) {
				arr[i] = initObject.getFieldAsName()[i];
			} else {
				arr[i] = initObject.getFieldPyName()[i];
			}
		}
		Vector<Hashtable<String, String>> vec1 = new Vector<Hashtable<String, String>>();
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
		 * null;
		 */

		try {
			/*
			 * conn = getConn(); pstmt = getPstmt(conn, sql.toString()); rs =
			 * pstmtQuery(pstmt);
			 */
			Map<String, String> params = new HashMap<String, String>();
			params.put("SelSQL", sql.toString());
			List rsList = findList("sqlcom.query", params);
			if (rsList != null && rsList.size() > 0) {
				for (int h = 0; h < rsList.size(); h++) {
					Map resMap = (Map) rsList.get(h);
					Hashtable<String, String> ht = new Hashtable<String, String>();
					for (int i = 0; i < arr.length; i++) {
						ht.put(arr[i], getField(resMap, arr[i]));
					}
					ht.put("CHILDRENNUM", getField(resMap, "CHILDRENNUM"));
					ht.put("SELECTFLAG", getField(resMap, "SELECTFLAG"));
					ht.put("ISLEAF", getField(resMap, "ISLEAF"));
					vec1.add(ht);
				}
			}
		} finally {
		}
		Vector<DataObject> vec2 = new Vector<DataObject>();
		if (vec1 != null && vec1.size() > 0) {
			for (int i = 0; i < vec1.size(); i++) {
				Hashtable<?, ?> tmpht = (Hashtable<?, ?>) vec1.get(i);
				DataObject dataObject = new DataObject();
				dataObject
						.setNode((String) tmpht.get(initObject.getNodeName()));
				dataObject.setKeyValue((String) tmpht.get(initObject
						.getKeyName()));

				dataObject.setParentNode((String) tmpht.get(initObject
						.getParentNodeName()));
				dataObject.setLevel(level);
				dataObject.setAllName(arr);
				dataObject.setAllValue(tmpht);
				dataObject.setChildrenNum(Integer.parseInt((String) tmpht
						.get("CHILDRENNUM")));
				dataObject.setDisplayStr(displayModel);
				if (initObject.getLeafCondition() != null
						&& !initObject.getLeafCondition().equals("")) {
					dataObject.setIsLeaf(Integer.parseInt((String) tmpht
							.get("ISLEAF")) > 0);
				} else {
					dataObject.setIsLeaf(dataObject.getChildrenNum() == 0);
				}
				if ((initObject.getCheckCondition() == null || initObject
						.getCheckCondition().equals(""))
						&& (initObject.getFilterCondition() == null || initObject
								.getFilterCondition().equals(""))) {
					dataObject.setSelectFlag(dataObject.getIsLeaf());
				} else {
					dataObject.setSelectFlag(Integer.parseInt((String) tmpht
							.get("SELECTFLAG")) > 0);
				}
				vec2.add(dataObject);
			}
		}
		return vec2;
	}

	/*-----------SpecialTree结束--------------*/

	/*-----------NormalTree开始--------------*/
	/**
	 * 取的某个节点所有子节点(为空即取所有根节点) Method getChildren.
	 * 
	 * @param level the level
	 * @param nodeValue the node value
	 * @param displayModel the display model
	 * @param childrenNum the children num
	 * @param vInitObject the v init object
	 * 
	 * @return Vector
	 * 
	 * @throws Exception the exception
	 */
	public Vector<DataObject> getNormalTreeChildren(String nodeValue,
			int level, String displayModel, int childrenNum,
			InitObject vInitObject) throws Exception {
		/** ***init*beg******* */
		InitObject initObject = null;
		String additionalSql = "";
		String tableName = "";
		initObject = vInitObject;
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			tableName = initObject.getTablePyName() + " "
					+ initObject.getTableAsName();
		} else {
			tableName = initObject.getTablePyName();
		}
		if (initObject.getKeyName() == null
				|| initObject.getKeyName().equals("")) {
			initObject.setKeyName(initObject.getNodeName());
		}
		/** ***init*end******* */
		StringBuffer sql = new StringBuffer(
				(initObject.getWithSql() == null ? "" : initObject.getWithSql()));
		sql.append("select * from (select ");
		sql.append(getListNamesForSql(initObject));

		// 添加附加查询字段childrenNum，当前节点的子节点数量
		sql.append(",(select count(*) from (select ");
		sql.append(initObject.getParentNodeName());
		sql.append(" from ");
		sql.append(tableName);
		if (initObject.getFilterCondition() != null
				&& !initObject.getFilterCondition().equals("")) {
			sql.append(" where " + initObject.getFilterCondition());
		}
		sql.append(") treeTmpTable1 where treeTmpTable1."
				+ initObject.getParentNodeName() + "=");
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			sql.append(initObject.getTableAsName());
		} else {
			sql.append(initObject.getTablePyName());
		}
		sql.append("." + initObject.getNodeName() + ") childrenNum, ");

		//添加附加查询字段selectFlag，字段是否能被选择，默认能
		if ((initObject.getCheckCondition() != null && !initObject
				.getCheckCondition().equals(""))
				|| (initObject.getFilterCondition() != null && !initObject
						.getFilterCondition().equals(""))) {
			sql.append("(select count(*) from (select ");
			sql.append(initObject.getNodeName());
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" where 1=1 ");
			if (initObject.getCheckCondition() != null
					&& !initObject.getCheckCondition().equals("")) {
				sql.append(" and " + initObject.getCheckCondition());
			}
			if (initObject.getFilterCondition() != null
					&& !initObject.getFilterCondition().equals("")) {
				sql.append(" and " + initObject.getFilterCondition());
			}
			sql.append(") treeTmpTable2 where treeTmpTable2."
					+ initObject.getNodeName() + "=");
			if (initObject.getTableAsName() != null
					&& !initObject.getTableAsName().equals("")) {
				sql.append(initObject.getTableAsName());
			} else {
				sql.append(initObject.getTablePyName());
			}
			sql.append("." + initObject.getNodeName() + ")");
		} else {
			sql.append("1");
		}
		sql.append(" selectFlag, ");

		// 添加附加查询字段isLeaf，字段是否终结点，默认否
		if (initObject.getLeafCondition() != null
				&& !initObject.getLeafCondition().equals("")) {
			sql.append("(select count(*) from (select ");
			sql.append(initObject.getNodeName());
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" where " + initObject.getLeafCondition());
			sql.append(") treeTmpTable3 where treeTmpTable3."
					+ initObject.getNodeName() + "=");
			if (initObject.getTableAsName() != null
					&& !initObject.getTableAsName().equals("")) {
				sql.append(initObject.getTableAsName());
			} else {
				sql.append(initObject.getTablePyName());
			}
			sql.append("." + initObject.getNodeName() + ")");
		} else {
			sql.append("0");
		}
		sql.append(" isLeaf from ");
		sql.append(tableName);
		if (initObject.getFilterCondition() != null
				&& !initObject.getFilterCondition().equals("")) {
			sql.append(" where " + initObject.getFilterCondition());
			if (childrenNum < 0) {
				if (additionalSql == null || "".equals(additionalSql)) {
					setAdditionalSqlNormalTree(initObject);
				}
				if (additionalSql != null && !additionalSql.equals("")) {
					sql.append(" union all " + additionalSql);
				}
			}
		}
		sql.append(") treetmptable4");
		if (nodeValue == null || nodeValue.equals("")) {
			if (initObject.getStartCondition() != null
					&& !initObject.getStartCondition().equals("")) {
				sql.append(" where " + initObject.getStartCondition());
			} else {
				sql.append(" where  (" + initObject.getParentNodeName()
						+ " is null or " + initObject.getParentNodeName()
						+ " ='')");
			}
		} else {
			sql.append(" where  " + initObject.getParentNodeName() + "='"
					+ nodeValue + "'");
		}
		if (initObject.getOrderName() == null
				|| "".equals(initObject.getOrderName())) {
			sql.append(" order by " + initObject.getNodeName() + " ASC");
		} else {
			sql.append(" order by " + initObject.getOrderName() + " "
					+ initObject.getOrderType());
		}

		String[] arr = new String[initObject.getFieldPyName().length];
		for (int i = 0; i < arr.length; i++) {
			if (initObject.getFieldAsName() != null
					&& initObject.getFieldAsName()[i] != null
					&& !initObject.getFieldAsName()[i].equals("")) {
				arr[i] = initObject.getFieldAsName()[i];
			} else {
				arr[i] = initObject.getFieldPyName()[i];
			}
		}
		Vector<Hashtable<String, String>> vec1 = new Vector<Hashtable<String, String>>();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("SelSQL", sql.toString());
			List rsList = findList("sqlcom.query", params);
			if (rsList != null && rsList.size() > 0) {
				for (int h = 0; h < rsList.size(); h++) {
					Map<?, ?> resMap = (Map<?, ?>) rsList.get(h);
					Hashtable<String, String> ht = new Hashtable<String, String>();
					for (int i = 0; i < arr.length; i++) {
						ht.put(arr[i], getField(resMap, arr[i].toUpperCase()));
					}
					ht.put("CHILDRENNUM", getField(resMap, "CHILDRENNUM"));
					ht.put("SELECTFLAG", getField(resMap, "SELECTFLAG"));
					ht.put("ISLEAF", getField(resMap, "ISLEAF"));
					vec1.add(ht);
				}
			}
		} finally {
		}
		level++;
		Vector<DataObject> vec2 = new Vector<DataObject>();
		if (vec1 != null && vec1.size() > 0) {
			for (int i = 0; i < vec1.size(); i++) {
				Hashtable<?, ?> tmpht = (Hashtable<?, ?>) vec1.get(i);
				DataObject dataObject = new DataObject();
				dataObject
						.setNode((String) tmpht.get(initObject.getNodeName()));
				dataObject.setKeyValue((String) tmpht.get(initObject
						.getKeyName()));
				dataObject.setParentNode((String) tmpht.get(initObject
						.getParentNodeName()));
				dataObject.setLevel(level);
				dataObject.setAllName(arr);
				dataObject.setAllValue(tmpht);
				dataObject.setChildrenNum(Integer.parseInt((String) tmpht
						.get("CHILDRENNUM")));
				dataObject.setDisplayStr(displayModel);
				if (initObject.getLeafCondition() != null
						&& !initObject.getLeafCondition().equals("")) {
					dataObject.setIsLeaf(Integer.parseInt((String) tmpht
							.get("ISLEAF")) > 0);
				} else {
					dataObject.setIsLeaf(dataObject.getChildrenNum() == 0);
				}
				if ((initObject.getCheckCondition() == null || initObject.getCheckCondition().equals(""))&& (initObject.getFilterCondition() == null || initObject.getFilterCondition().equals(""))) {
					dataObject.setSelectFlag(dataObject.getIsLeaf());
				} else {
					dataObject.setSelectFlag(Integer.parseInt((String) tmpht.get("SELECTFLAG")) > 0);
				}
				vec2.add(dataObject);
			}
		}
		return vec2;
	}

	/**
	 * getAdditionalSql.
	 * 
	 * @param initObject the init object
	 * 
	 * @return String
	 * 
	 * @throws Exception the exception
	 */
	private String setAdditionalSqlNormalTree(InitObject initObject)
			throws Exception {
		// 查出父结点不在当前结果集中的父结点
		String tableName = "";
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			tableName = initObject.getTablePyName() + " "
					+ initObject.getTableAsName();
		} else {
			tableName = initObject.getTablePyName();
		}
		StringBuffer sql = new StringBuffer(
				(initObject.getWithSql() == null ? "" : initObject.getWithSql())
						+ "select distinct(");
		sql.append(initObject.getParentNodeName());
		sql.append(") from ");
		sql.append(tableName);
		sql.append(" where " + initObject.getFilterCondition());
		sql.append(" and not exists (select 'X' from (select ");
		sql.append(initObject.getNodeName());
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where " + initObject.getFilterCondition());
		sql.append(") treeTmpTable where treeTmpTable."
				+ initObject.getNodeName());
		sql.append(" = ");
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			sql.append(initObject.getTableAsName());
		} else {
			sql.append(initObject.getTablePyName());
		}
		sql.append("." + initObject.getParentNodeName() + ")");

		Vector<String> vec = new Vector<String>();
		Hashtable<String, String> ht = new Hashtable<String, String>();
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
		 * null;
		 */
		try {
			/*
			 * conn = getConn(); pstmt = getPstmt(conn, sql.toString()); rs =
			 * pstmtQuery(pstmt);
			 */
			Map<String, String> params = new HashMap<String, String>();
			params.put("SelSQL", sql.toString());
			List rsList = findList("sqlcom.query", params);
			if (rsList != null && rsList.size() > 0) {
				for (int h = 0; h < rsList.size(); h++) {
					Map<?, ?> resMap = (Map<?, ?>) rsList.get(h);
					String tmp = getField(resMap, initObject
							.getParentNodeName());
					if (tmp != null && !tmp.equals("")) {
						vec.add(tmp);
						ht.put(tmp, tmp);
						getParentNode(vec, ht, tmp, initObject);
					}
				}
			}
		} finally {
		}

		if (vec.size() == 0) {
			return "";

		}

		StringBuffer returnSql = new StringBuffer("select "
				+ getListNamesForSql(initObject));
		returnSql.append(",-1 childrenNum,0 selectFlag, 0 isLeaf from ");
		returnSql.append(tableName);
		returnSql.append(" where " + initObject.getNodeName() + " in (''");
		int fieldsNum = 0;
		try {
			for (int i = 0; i < vec.size(); i++) {
				String tmp = (String) vec.get(i);
				if (tmp != null && !tmp.equals("")) {
					if (fieldsNum > 900) {
						returnSql.append(" or " + initObject.getNodeName()
								+ " in (''");
						fieldsNum = 0;
					}
					returnSql.append(",'" + tmp + "'");
				}
			}
		} finally {
		}
		returnSql.append(")");
		return returnSql.toString();
	}

	/**
	 * Gets the parent node.
	 * 
	 * @param vec the vec
	 * @param ht the ht
	 * @param currNodeValue the curr node value
	 * @param initObject the init object
	 * 
	 * @return the parent node
	 * 
	 * @throws Exception the exception
	 */
	private void getParentNode(Vector<String> vec,
			Hashtable<String, String> ht, String currNodeValue,
			InitObject initObject) throws Exception {
		String tableName = "";
		if (initObject.getTableAsName() != null
				&& !initObject.getTableAsName().equals("")) {
			tableName = initObject.getTablePyName() + " "
					+ initObject.getTableAsName();
		} else {
			tableName = initObject.getTablePyName();
		}
		if (currNodeValue == null || currNodeValue.equals(""))
			return;
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(initObject.getParentNodeName());
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(initObject.getNodeName());
		sql.append("='");
		sql.append(currNodeValue);
		sql.append("'");
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
		 * null; DBPageBean dbaccess = new DBPageBean();
		 */
		try {
			/*
			 * conn = dbaccess.getConn(); pstmt = dbaccess.getPstmt(conn,
			 * sql.toString()); rs = dbaccess.pstmtQuery(pstmt);
			 */
			Map<String, String> params = new HashMap<String, String>();
			params.put("SelSQL", sql.toString());
			List rsList = findList("sqlcom.query", params);
			if (rsList != null && rsList.size() > 0) {
				for (int h = 0; h < rsList.size(); h++) {
					Map<?, ?> resMap = (Map<?, ?>) rsList.get(h);
					String tmp = getField(resMap, initObject
							.getParentNodeName());
					if (tmp != null && !tmp.equals("")) {
						String tmpValue = (String) ht.get(tmp);
						if (tmpValue == null || tmpValue.equals("")) {
							ht.put(tmp, tmp);
							vec.add(tmp);
							getParentNode(vec, ht, tmp, initObject);
						}
					}
				}
			}
		} finally {
		}
	}

	/*-----------NormalTree结束--------------*/

	/**
	 * Gets the list names for sql.
	 * 
	 * @param initObject the init object
	 * 
	 * @return the list names for sql
	 */
	protected String getListNamesForSql(InitObject initObject) {
		StringBuffer returnStr = new StringBuffer("");
		for (int i = 0; i < initObject.getFieldPyName().length; i++) {
			if (i > 0) {
				returnStr.append(",");
			}
			returnStr.append(initObject.getFieldPyName()[i]);
			if (initObject.getFieldAsName() != null
					&& initObject.getFieldAsName()[i] != null
					&& !initObject.getFieldAsName()[i].equals("")) {
				returnStr.append(" " + initObject.getFieldAsName()[i]);
			}
		}
		return returnStr.toString();
	}
}
