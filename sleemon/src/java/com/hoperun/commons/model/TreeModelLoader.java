/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class TreeModelLoader.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class TreeModelLoader<T extends TreeModel> {
	
	/** The Constant TREE_MODEL_FLD_ID. */
	public final static String TREE_MODEL_FLD_ID = "id";
	
	/** The Constant TREE_MODEL_FLD_PID. */
	public final static String TREE_MODEL_FLD_PID = "pid";
	
	/** The Constant TREE_MODEL_FLD_NAME. */
	public final static String TREE_MODEL_FLD_NAME = "name";
	
	/** The Constant TREE_MODEL_FLD_EXPANDED. */
	public final static String TREE_MODEL_FLD_EXPANDED = "open";
	
	/** The Constant TREE_MODEL_FLD_LEVEL. */
	public final static String TREE_MODEL_FLD_LEVEL = "level";	
	
	/** The Constant TREE_MODEL_FLD_ISPARENT. */
	public final static String TREE_MODEL_FLD_ISPARENT = "isParent";	
	
	/** The tree model class. */
	private Class<T> treeModelClass;
	
	/** The map attrs. */
	private Map<String, String> mapAttrs;

	/**
	 * Instantiates a new tree model loader.
	 * 
	 * @param treeModelClass the tree model class
	 * @param mapAttrs the map attrs
	 */
	public TreeModelLoader(final Class<T> treeModelClass,
			Map<String, String> mapAttrs) {
		super();
		this.treeModelClass = treeModelClass;
		this.mapAttrs = mapAttrs;
		Assert.notNull(this.treeModelClass);
		Assert.notNull(this.mapAttrs);
		Assert.notEmpty(this.mapAttrs);
		Assert.notNull(this.mapAttrs.get(TREE_MODEL_FLD_ID));
		Assert.notNull(this.mapAttrs.get(TREE_MODEL_FLD_PID));
		Assert.notNull(this.mapAttrs.get(TREE_MODEL_FLD_NAME));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.omw.framework.core.tree.ItreeModelLoader#getTreeModel(java.lang.Object)
	 */
	/**
	 * Gets the tree model.
	 * 
	 * @param data the data
	 * 
	 * @return the tree model
	 * 
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	@SuppressWarnings("unchecked")
	public T getTreeModel(Object data) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (data == null)
			return null;
		T model = this.treeModelClass.newInstance();
		Iterator<String> keyIterator = this.mapAttrs.keySet().iterator();
		Map values = BeanUtils.describe(data);
		while(keyIterator.hasNext()){
			String fld = keyIterator.next();
			Object value = values.get(this.mapAttrs.get(fld));
			if(value!=null)
				BeanUtils.copyProperty(model, fld, value);
		}

		return model;
	}
}
