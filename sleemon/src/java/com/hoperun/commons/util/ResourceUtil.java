/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.hoperun.commons.model.TreeModel;
import com.hoperun.commons.model.TreeModelLoader;
 

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceUtil.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class ResourceUtil {

	/**
	 * 从属性文件(*.properties)中读取数据并转换为Map<String,String>的格式
	 * 
	 * @param rb the rb
	 * 
	 * @return the map< string, string>
	 */
	public static Map<String,String> convertBundleToMap(ResourceBundle rb){
        // loop through all the beans methods and set its properties from its .properties file
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }
        return map;
	}
	
	/**
	 * 将List格式的数据转为Tree的数据结构.
	 * 
	 * @param datas List格式的数据，存放POJO
	 * @param treeModelClass the tree model class
	 * @param mapAttrs the map attrs
	 * 
	 * @return 树形结构的数据
	 * 
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws InstantiationException the instantiation exception
	 */
	/**
	 * 将List格式的数据转为Tree的数据结构
	 * 
	 * @param datas
	 *            List格式的数据，存放POJO
	 * @param modelLoader
	 *            将POJO转换为TreeModel的加载器
	 * @return 树形结构的数据
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */

	public static <T extends TreeModel> List<T> getZTreeFromList(
			List<? extends Object> datas, Class<T> treeModelClass,
			Map<String, String> mapAttrs) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			InstantiationException {
		List<T> rtn = new ArrayList<T>();
		if (datas == null || datas.isEmpty())
			return rtn;
		List<TreeModel> orgiDatas = new ArrayList<TreeModel>();
		TreeModelLoader<T> modelLoader = new TreeModelLoader<T>(treeModelClass,
				mapAttrs);
		for (Object data : datas) {
			T model = modelLoader.getTreeModel(data);
			if (model.getPid() == null)
				rtn.add(model);
			else
				orgiDatas.add(model);
		}
		for (TreeModel root : rtn) {
			root.setChilds(findZTreeChildren(orgiDatas, root));
		}
		return rtn;
	}

	/**
	 * Find z tree children.
	 * 
	 * @param models the models
	 * @param parent the parent
	 * 
	 * @return the list< tree model>
	 */
	public static List<TreeModel> findZTreeChildren(List<? extends TreeModel> models,
			TreeModel parent) {
		List<TreeModel> children = new ArrayList<TreeModel>();
		for (TreeModel model : models) {
//			if (model.getPid() != null && model.getPid().equals(parent.getId()))
//			modify by zzb 2015-01-05 添加对名称不为空的判断
		if (!StringUtil.isEmpty(model.getPid()) && !StringUtil.isEmpty(model.getName()) 
				&& model.getPid().equals(parent.getId()))
				children.add(model);
		}
		models.removeAll(children);
		for (TreeModel child : children) {
			child.setChilds(findZTreeChildren(models, child));
		}
		return children;
	}
	
	/**
	 * Gets the z tree from list.
	 * 
	 * @param datas the datas
	 * @param treeModelClass the tree model class
	 * 
	 * @return the z tree from list
	 * 
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws InstantiationException the instantiation exception
	 */
	public static <T extends TreeModel> List<T> getZTreeFromList(
			List<? extends Object> datas, Class<T> treeModelClass) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			InstantiationException {
		Map<String, String> mapFlds = new HashMap<String, String>();
		mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_ID, "id");
		mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_PID, "pid");
		mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_NAME, "name");
		return getZTreeFromList(datas, treeModelClass, mapFlds);
	}
}
