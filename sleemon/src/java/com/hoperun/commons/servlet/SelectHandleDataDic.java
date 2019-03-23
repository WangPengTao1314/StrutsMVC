package com.hoperun.commons.servlet;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectHandleDataDic.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class SelectHandleDataDic {
	//将所有的文档对象放在Hashtable中避免重复读取文件
	/** The docs. */
	private static Hashtable docs = new Hashtable();

	//当前配置文档对象
	/** The doc. */
	private Document doc = null;

	/**
	 * SelectHandle
	 * 构造方法，获取当前文档对象.
	 * 
	 * @param fileName String
	 * 
	 * @throws Exception the exception
	 */
	public SelectHandleDataDic(String fileName) throws Exception {
		//从hashtable中取文档对象
		doc = (Document) docs.get(fileName);
		//如果没有取到则再去读取文件
		if (doc == null) {
			//创建文档对象
			Document newdoc = new SAXBuilder().build(new File(fileName));
			//将其放入Hashtable中以备下次取用
			docs.put(fileName, newdoc);
			//将其设置为当前文档对象
			doc = newdoc;
		}
	}
	
	/**
	 * getTableObjectCount
	 * 获得当前表对象对象个数.
	 * 
	 * @return int
	 */
	public int getTableObjectCount() {
		if (doc != null) {
		Element tableElem = doc.getRootElement();

		return tableElem.getChildren("table").size();
		}
		else{
			return 0;
		}
		
	}
	
	/**
	 * getCurrentObject
	 * 根据序号获得当前表对象.
	 * 
	 * @param id int
	 * @param defaultSelectFlag the default select flag
	 * 
	 * @return TableObject
	 */
	public TableObjectDataDic getCurrentTableObject(String id, boolean defaultSelectFlag) {
		TableObjectDataDic aTableObject = new TableObjectDataDic();

		Element tableElem = doc.getRootElement();
		StringTokenizer stk = new StringTokenizer(id, "_", false);
		while (stk.hasMoreTokens()) {
			String tmp = (String) stk.nextToken();
			if (tmp != null && !tmp.equals("")) {
				//此处代码依赖于xml里的table id所以定义id时必须从0开始递增且不能有间隔 刘杰 2011/9/15
				tableElem = (Element) tableElem.getChildren("table").get(
					Integer.parseInt(tmp));
			}
		}
		//取表属性
		aTableObject.setTablePyName(getAttributeValue(tableElem, "pyName"));
		aTableObject.setTableAsName(getAttributeValue(tableElem, "asName"));
		aTableObject.setTableLgName(getAttributeValue(tableElem, "lgName"));
		aTableObject.setId(getAttributeValue(tableElem, "id"));
		aTableObject.setIsDbTable(getAttributeValue(tableElem, "isDbTable").equals("T"));
		aTableObject.setIsTree(getAttributeValue(tableElem, "isTree").equals("T"));
		aTableObject.setIsSTATIC(getAttributeValue(tableElem, "isStatic").equals("T"));
		aTableObject.setOrderName(getAttributeValue(tableElem, "orderName"));
		aTableObject.setOrderType(getAttributeValue(tableElem, "orderType"));
		aTableObject.setLeafCondition(getAttributeValue(tableElem, "leafCondition"));
		aTableObject.setStartCondition(getAttributeValue(tableElem, "startCondition"));
		aTableObject.setCheckCondition(getAttributeValue(tableElem, "checkCondition"));
		aTableObject.setDisplayModel(getAttributeValue(tableElem, "displayModel"));
		if(!getAttributeValue(tableElem, "selectFlag").equals("")){
			aTableObject.setSelectFlag(getAttributeValue(tableElem, "selectFlag").equals("T"));
		}else{
			aTableObject.setSelectFlag(defaultSelectFlag);
		}
		if(aTableObject.getIsTree()){
			if(getAttributeValue(tableElem, "displayTreeFlag").equals("F")){
				aTableObject.setDisplayTreeFlag(false);
			}else{
				aTableObject.setDisplayTreeFlag(true);	
			}			
		}

		//取所有的字段属性
		List fields = tableElem.getChildren("field");
		String[] fieldPyName = new String[fields.size()];
		String[] fieldAsName = new String[fields.size()];
		String[] fieldLgName = new String[fields.size()];
		String[] fieldType = new String[fields.size()];
		String[] decimalSystem = new String[fields.size()];
		String[] decimalType = new String[fields.size()];
		String[] decimalMax = new String[fields.size()];
		String[] primaryKeyId = new String[fields.size()];
		String[] foreignKeyId = new String[fields.size()];
		boolean[] isShow = new boolean[fields.size()];
		boolean[] isSearch = new boolean[fields.size()];
		boolean[] isParentNode = new boolean[fields.size()];
		boolean[] isNode = new boolean[fields.size()];
		boolean[] isTreeKey = new boolean[fields.size()];
		
		String[] text = new String[fields.size()];
        String[] value = new String[fields.size()];

		for (int i = 0; i < fields.size(); i++) {
			Element elem = (Element) fields.get(i);
			fieldPyName[i] = getAttributeValue(elem, "pyName");
			fieldAsName[i] = getAttributeValue(elem, "asName");
			fieldLgName[i] = getAttributeValue(elem, "lgName");
			fieldType[i] = getAttributeValue(elem, "type");
			decimalSystem[i] = getAttributeValue(elem, "decimalSystem");
			decimalType[i] = getAttributeValue(elem, "decimalType");
			decimalMax[i] = getAttributeValue(elem, "decimalMax");
			isShow[i] = getAttributeValue(elem, "isShow").equals("T");
			isSearch[i] = getAttributeValue(elem, "isSearch").equals("T");
			primaryKeyId[i] = getAttributeValue(elem, "primaryKeyId");
			foreignKeyId[i] = getAttributeValue(elem, "foreignKeyId");
			isParentNode[i] = getAttributeValue(elem, "isParentNode").equals("T");
			isNode[i] = getAttributeValue(elem, "isNode").equals("T");
			isTreeKey[i] = getAttributeValue(elem, "isTreeKey").equals("T");
			
			text[i] = getAttributeValue(elem, "text");
			value[i] = getAttributeValue(elem, "value");
		}
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
		
		aTableObject.setText(text);
		aTableObject.setValue(value);

		return aTableObject;
	}

	/**
	 * getSubTableObjects
	 * 根据父表对象id,查出其所有子表对象.
	 * 
	 * @param pid String
	 * 
	 * @return TableObject[]
	 */
	public TableObjectDataDic[] getSubTableObjects(String pid) {
		Element tableElem = doc.getRootElement();

		StringTokenizer stk = new StringTokenizer(pid, "_", false);
		while (stk.hasMoreTokens()) {
			tableElem = (Element) tableElem.getChildren("table").get(Integer.
				parseInt( (String) stk.nextToken()));
		}
		List list = tableElem.getChildren("table");
		if (list == null || list.size() == 0) {
			return null;
		}
		TableObjectDataDic[] arrTableObject = new TableObjectDataDic[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arrTableObject[i] = getCurrentTableObject(pid + "_" + i, false);
		}
		return arrTableObject;
	}

	/**
	 * getAttributeValue
	 * 取元素属性值.
	 * 
	 * @param elem Element
	 * @param attName String
	 * 
	 * @return String
	 */
	public String getAttributeValue(Element elem, String attName) {
		String tmp = elem.getAttributeValue(attName);
		if (tmp == null) {
			return "";
		}
		return tmp;
	}
}
