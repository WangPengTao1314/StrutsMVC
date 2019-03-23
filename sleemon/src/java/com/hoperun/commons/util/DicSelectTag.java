/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hoperun.commons.servlet.ContextServlet;
import com.hoperun.commons.servlet.DictInfoBean;
import com.hoperun.commons.servlet.InitParameterDictService;

// TODO: Auto-generated Javadoc
/**
 * The Class DicSelectTag.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class DicSelectTag extends TagSupport {

	/** The init parameter dict service. */
	private InitParameterDictService initParameterDictService;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The property. */
	private String property;
	
	/** The length. */
	private String length;
	
	/** The size. */
	private String size;
	
	/** The key. */
	private String key;
	
	/** The jues. */
	private String jues;
	
	/** The key value. */
	private String keyValue;
	
	/** The read only. */
	private String readOnly;
	
	/** The disabled. */
	private String disabled;
	
	/** The onchange. */
	private String onchange;
	
	/** The sel id. */
	private String selId;

	/**
	 * Gets the sel id.
	 * 
	 * @return the sel id
	 */
	public String getSelId() {
		return selId;
	}

	/**
	 * Sets the sel id.
	 * 
	 * @param selId the new sel id
	 */
	public void setSelId(String selId) {
		this.selId = selId;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() {
		JspWriter out = this.pageContext.getOut();

		String keyValueStr = this.pageContext.getRequest().getAttribute(
				this.property) == null ? "" : this.pageContext.getRequest()
				.getAttribute(this.property).toString();

		if (this.keyValue != null) {
			keyValueStr = this.keyValue;
		}

		String flag = this.disabled == null ? "" : this.disabled;

		String selCommHead = "System"; // 默认为物流的配置文件
		if (this.selId.indexOf("_") != -1) {
			selCommHead = this.selId.substring(0, this.selId.indexOf("_"));
		} else {
			this.selId = selCommHead + "_" + this.selId;
		}
		String fileName = this.pageContext.getServletContext().getRealPath("/")
				+ "pages" + File.separator + "sys" + File.separator
				+ "configfiles" + File.separator + "dictionary" + File.separator
				+ "dictConfig_" + selCommHead + ".xml";

		List<?> dicList = ContextServlet.getDicList(fileName+ this.selId.substring(this.selId.indexOf("_")));
		if (null == dicList) {
			
			ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
					 .getServletContext());
			if (this.initParameterDictService==null){
			 InitParameterDictService initParameterDictService = (InitParameterDictService) ac2.getBean("initParameterDictService");
			}
			//InitParameterDictService initParameterDictService = new InitParameterDictService();
			dicList = initParameterDictService.getDictList(fileName, this.selId.substring(selCommHead.length() + 1));
		}

		// List dicList = ContextServlet.getDicListHm(this.key);
		try {
			// add by wuziwen 增加了只读和不可用的属性值

			if ("".equals(flag)) {
				out.println("<select name=\""
						+ this.property
						+ "\" "
						+ (this.readOnly == null ? "" : " readOnly = "
								+ this.readOnly)
						+ (this.onchange == null ? "" : " onchange = "
								+ this.onchange) + " >");
				//
				if (null != jues && !"".equals(jues)) {
					if ("xtgly".equals(jues)) {
						out.println("<option value=''>--请选择--</option>");
					}
				}
				int listCount = dicList.size();
				for (int i = 0; i < listCount; i++) {
					DictInfoBean dictBean = (DictInfoBean) dicList.get(i);

					if (keyValueStr.trim().equals(dictBean.getDicname()))
						out.println("<option selected value=\""
								+ dictBean.getDicname() + "\">"
								+ dictBean.getDiccnname() + "</option>");
					else {
						out
								.println("<option value=\""
										+ dictBean.getDicname() + "\">"
										+ dictBean.getDiccnname() + "</option>");
					}
				}
				out.println("</select>");
			} else {
				// 只读的情况下的展示 mod by wuziwen 2010-11-18
				for (int i = 0; i < dicList.size(); i++) {
					DictInfoBean dictBean = (DictInfoBean) dicList.get(i);

					if (keyValueStr.trim().equals(dictBean.getDicname())) {
						out.println(dictBean.getDiccnname());
					}
				}
				// end mod
				// out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 1;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() {
		return 6;
	}

	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public String getProperty() {
		return this.property;
	}

	/**
	 * Sets the property.
	 * 
	 * @param property the new property
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Gets the length.
	 * 
	 * @return the length
	 */
	public String getLength() {
		return this.length;
	}

	/**
	 * Sets the length.
	 * 
	 * @param length the new length
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * Sets the size.
	 * 
	 * @param size the new size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the key value.
	 * 
	 * @return the key value
	 */
	public String getKeyValue() {
		return this.keyValue;
	}

	/**
	 * Sets the key value.
	 * 
	 * @param keyValue the new key value
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * Gets the read only.
	 * 
	 * @return the read only
	 */
	public String getReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the read only.
	 * 
	 * @param readOnly the new read only
	 */
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Gets the disabled.
	 * 
	 * @return the disabled
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * Sets the disabled.
	 * 
	 * @param disabled the new disabled
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * Gets the jues.
	 * 
	 * @return the jues
	 */
	public String getJues() {
		return jues;
	}

	/**
	 * Sets the jues.
	 * 
	 * @param jues the new jues
	 */
	public void setJues(String jues) {
		this.jues = jues;
	}

	/**
	 * Gets the onchange.
	 * 
	 * @return the onchange
	 */
	public String getOnchange() {
		return onchange;
	}

	/**
	 * Sets the onchange.
	 * 
	 * @param onchange the new onchange
	 */
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	/**
	 * Sets the inits the parameter dict service.
	 * 
	 * @param initParameterDictService the new inits the parameter dict service
	 */
	public void setInitParameterDictService(InitParameterDictService initParameterDictService) {
		this.initParameterDictService = initParameterDictService;
	}
}
