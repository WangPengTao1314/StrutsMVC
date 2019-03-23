package com.hoperun.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.servlet.ContextServlet;
import com.hoperun.commons.servlet.DictInfoBean;
import com.hoperun.commons.servlet.SelectHandleDataDic;
import com.hoperun.commons.servlet.TableObjectDataDic;

// TODO: Auto-generated Javadoc
/**
 * The Class DictService.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class DictService extends BaseService {

	/**
	 * Inits the dict list.
	 * 
	 * @param fileName the file name
	 */
	public void initDictList(String fileName) {
		// HashMap<String, List> hm = new HashMap<String, List>();

		SelectHandleDataDic handle = null;
		try {
			handle = new SelectHandleDataDic(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			// request.setAttribute("ErrMsg", new Exception("通用选取配置文件读取失败！"));
		}
		int intTabs = handle.getTableObjectCount();
		for (int h = 0; h < intTabs; h++) {
			TableObjectDataDic tabObject = handle.getCurrentTableObject(String
					.valueOf(h), true);
			String[] tablePyName = tabObject.getFieldPyName();
			String[] tableAsName = tabObject.getFieldAsName();
			String[] tableKeyId = tabObject.getPrimaryKeyId();
			if (tabObject.getIsDbTable() == true
					&& tabObject.getIsSTATIC() == true) {
				String sql = tabObject.getSqlString("");
				Map<String, String> params = new HashMap<String, String>();
				params.put("SelSQL", sql.toString());
				List rsList = findList("sqlcom.query", params);
				int rsSize=(rsList==null?0:rsList.size());
				List<Object> selList = new ArrayList<Object>();
				for (int i = 0; i < rsSize; i++) {
					Map<?, ?> resMap = (Map<?, ?>) rsList.get(i);
					DictInfoBean dictBean = new DictInfoBean();
					String displayModel = (tabObject.getDisplayModel() == null ? ""
							: tabObject.getDisplayModel());
					int pyNameSize = tablePyName.length;
					String value;
					String tmp;
					for (int j = 0; j < pyNameSize; j++) {
						tmp = "";
						if (tableAsName[j] == null || "".equals(tableAsName[j])) {
							tmp = tablePyName[j];
						} else {
							tmp = tableAsName[j];
						}
						value = ((tmp == null || "".equals(tmp)) ? "" : (resMap
								.get(tmp) == null ? "" : String.valueOf(resMap
								.get(tmp))));
						if (value == null) {
							value = "";
						}
						if ("T".equals(tableKeyId[j])) {
							dictBean.setDicname(value);
						} else {
							if (displayModel.indexOf(tmp) != -1) {
								displayModel = displayModel.replace(tmp, value);
							} else {
								displayModel = displayModel + " " + value;
							}
						}
					}
					dictBean.setDiccnname(displayModel);
					selList.add(dictBean);
				}
				if (tabObject.getIsSTATIC() == true
						&& ContextServlet.dicList.get(fileName + "_" + h) == null) {
					ContextServlet.dicList.put(fileName + "_" + h, selList);
				}
			}
			if (tabObject.getIsDbTable() == false) {
				List<Object> selList = new ArrayList<Object>();
				int fildesCount = tabObject.getFieldPyName().length;
				for (int i = 0; i < fildesCount; i++) {
					DictInfoBean dictBean = new DictInfoBean();
					dictBean.setDicname(tabObject.getFieldPyName()[i]);
					dictBean.setDiccnname(tabObject.getFieldAsName()[i]);
					selList.add(dictBean);
				}
				if (tabObject.getIsSTATIC() == true
						&& ContextServlet.dicList.get(fileName + "_" + h) == null) {
					ContextServlet.dicList.put(fileName + "_" + h, selList);
				}
				// hm.put(fileName + "_" + h, rsList);
			}
		}
		// return hm;
	}

	/**
	 * Gets the dict list.
	 * 
	 * @param fileName the file name
	 * @param selCommId the sel comm id
	 * @param condition the condition
	 * 
	 * @return the dict list
	 */
	public List<Object> getDictList(String fileName, String selCommId,String condition) {

		List<Object> dicList = ContextServlet.dicList.get(fileName + "_" + selCommId);
		if (dicList != null) {
			return dicList;
		}
		SelectHandleDataDic handle = null;
		try {
			handle = new SelectHandleDataDic(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			// request.setAttribute("ErrMsg", new Exception("通用选取配置文件读取失败！"));
		}
		TableObjectDataDic tabObject = handle.getCurrentTableObject(selCommId,
				true);
		if(condition != null && !"".equals(condition)) {
		    tabObject.setCheckCondition(condition);
		}
		String[] tablePyName = tabObject.getFieldPyName();
		if (tabObject.getIsDbTable() == true) {
			String sql = tabObject.getSqlString("");
			Map<String, String> params = new HashMap<String, String>();
			params.put("SelSQL", sql.toString());
			List<?> rsList = findList("sqlcom.query", params);
			int rsSize=(rsList==null?0:rsList.size());
			List<Object> selList = new ArrayList<Object>();
			for (int i = 0; i < rsSize; i++) {
				Map<?, ?> resMap = (Map<?, ?>) rsList.get(i);
				DictInfoBean dictBean = new DictInfoBean();
				dictBean.setDicname((String)resMap.get(tablePyName[1]));
				dictBean.setDiccnname((String)resMap.get(tablePyName[0]));
				selList.add(dictBean);
			}
			if (tabObject.getIsSTATIC() == true
					&& ContextServlet.dicList.get(fileName + "_" + selCommId) == null) {
			//	ContextServlet.dicList.put(fileName + "_" + selCommId, selList);
			}
			return selList;
		}
		if (tabObject.getIsDbTable() == false) {
			List<Object> selList = new ArrayList<Object>();
			int fildesCount = tabObject.getFieldPyName().length;
			for (int i = 0; i < fildesCount; i++) {
				DictInfoBean dictBean = new DictInfoBean();
				dictBean.setDicname(tabObject.getValue()[i]);
				dictBean.setDiccnname(tabObject.getText()[i]);
				selList.add(dictBean);
			}
			if (ContextServlet.dicList.get(fileName + "_" + selCommId) == null) {
				ContextServlet.dicList.put(fileName + "_" + selCommId, selList);
			}
			return selList;
			// hm.put(fileName + "_" + selCommId, rsList);
		}
		return null;
	}
}
