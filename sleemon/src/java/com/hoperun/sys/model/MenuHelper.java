package com.hoperun.sys.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.sort.Compare;
import com.hoperun.commons.util.sort.SortVector;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuHelper.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class MenuHelper {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(MenuHelper.class);
	
	/** 缺省顶级菜单. */
	public static final String MENU_ROOT = Properties.getString("MENU_ROOT");

	// 所有菜单
	/** The all menus. */
	private Collection allMenus;
	// 顶级菜单
	/** The top menus. */
	private List topMenus;

	/**
	 * Instantiates a new menu helper.
	 * 
	 * @param menus the menus
	 */
	public MenuHelper(Collection menus) {
		this.allMenus = menus;
	}

	/**
	 * 分离子菜单并排序.
	 * 
	 * @param menuId the menu id
	 * @param lever </p>
	 * 
	 * @return the list
	 */
	private List findChildrenByParent(String menuId, int lever) {
		SortVector children = getSortVector();
		for (Iterator iter = allMenus.iterator(); iter.hasNext();) {
			MenuInfo menu = (MenuInfo) iter.next();
			if (menu != null && menuId.equals(menu.getMenuParId())) {
				children.add(menu);
				logger.info("Menu: " + menu.getMenuId());
			}
		}
		allMenus.removeAll(children);
		children.sort();
		return children;
	}

	/**
	 * 顶级菜单.
	 */
	private void findTopMenus() {
		topMenus = findChildrenByParent(MENU_ROOT, 1);
	}

	/**
	 * 二级菜单.
	 */
	private void findSecMenus() {
		for (Iterator iter = topMenus.iterator(); iter.hasNext();) {
			MenuInfo menu = (MenuInfo) iter.next();
			findChildrenByParent(menu, 2);
		}
	}

	/**
	 * 功能菜单.
	 * 
	 * @param lever the lever
	 * @param parent the parent
	 */
	private void findChildrenByParent(MenuInfo parent, int lever) {
		SortVector children = getSortVector();
		for (Iterator iter = allMenus.iterator(); iter.hasNext();) {
			MenuInfo menu = (MenuInfo) iter.next();
			if (menu != null && parent.getMenuId().equals(menu.getMenuParId())) {
				children.add(menu);
				// logger.info("Menu: " + menu.getMenuId());
			}
		}
		allMenus.removeAll(children);
		children.sort();
		parent.setChildren(children);
		if (lever < 3) {
			lever++;
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				MenuInfo menu = (MenuInfo) iter.next();
				findChildrenByParent(menu, lever);
			}
		}
	}
	
	/**
	 * Gets the sort vector.
	 * 
	 * @return the sort vector
	 */
	private SortVector getSortVector(){
		return new SortVector(new Compare() {
			public boolean lessThan(Object lhs, Object rhs) {
				Integer a = ((MenuInfo) lhs).getMenuSort();
				Integer b = ((MenuInfo) rhs).getMenuSort();
				return a.intValue() < b.intValue();
			}

			public boolean lessThanOrEqual(Object lhs, Object rhs) {
				return lessThan(lhs, rhs);
			}
		});
	}

	/**
	 * Gets the json data4 menu tree.
	 * 
	 * @return the json data4 menu tree
	 */
	public String getJsonData4MenuTree() {
		findTopMenus();
		findSecMenus();
        StringBuffer bf = new StringBuffer();
		// 设置顶级菜单
		List children = topMenus;
		if (children == null || children.isEmpty()) {
			return "{size:0}";
		} else {
			bf.append("{");
			bf.append("size:").append(children.size()).append(",");
			bf.append("children:[");
			int i = 0;
			for (; i < children.size() - 1; i++) {
				loadMenu((MenuInfo) children.get(i), bf);
				bf.append(",");
			}
			loadMenu((MenuInfo) children.get(i), bf);
			bf.append("]");
			bf.append("}");
		}
		return bf.toString();
	}

	/**
	 * 加载子菜单.
	 * 
	 * @param menu the menu
	 * @param bf the bf
	 */
	private void loadMenu(MenuInfo menu, StringBuffer bf) {
		bf.append("{");
		bf.append("id:'").append(menu.getMenuId()).append("',");
		bf.append("name:'").append(menu.getMenuName()).append("',");
		bf.append("url:'").append(menu.getMenuUrl()).append("',");
		if (!StringUtil.isEmpty(menu.getMenuImg())) {
			bf.append("img:'").append(menu.getMenuImg()).append("',");
		}
		if (!StringUtil.isEmpty(menu.getMenuDesc())) {
			bf.append("dsc:'").append(menu.getMenuDesc()).append("',");
		}
		List children = menu.getChildren();
		if (children == null || children.isEmpty()) {
			bf.append("size:0");
		} else {
			bf.append("size:").append(children.size()).append(",");
			bf.append("children:[");
			int i = 0;
			for (; i < children.size() - 1; i++) {
				loadMenu((MenuInfo) children.get(i), bf);
				bf.append(",");
			}
			loadMenu((MenuInfo) children.get(i), bf);
			bf.append("]");
		}
		bf.append("}");
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List menus = new ArrayList();
		MenuInfo menu = new MenuInfo();
		menu.setMenuId("010000");
		menu.setMenuName("报表查询");
		menu.setMenuParId("000000");
		menu.setMenuImg("bbcx.gif");
		menu.setMenuSort(new Integer(1));
		menus.add(menu);
		MenuInfo menu2 = new MenuInfo();
		menu2.setMenuId("990000");
		menu2.setMenuName("系统管理");
		menu2.setMenuParId("000000");
		menu2.setMenuImg("xtgl.gif");
		menu2.setMenuSort(Integer.valueOf(99));
		menus.add(menu2);
		MenuHelper menuHelper = new MenuHelper(menus);
		String jsonMenus = menuHelper.getJsonData4MenuTree();
		System.out.println(jsonMenus);
	}
}