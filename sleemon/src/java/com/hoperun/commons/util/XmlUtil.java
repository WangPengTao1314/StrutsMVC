package com.hoperun.commons.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlUtil.
 * 
 * @module 共通模块
 * @func  XML 类
 * @version 1.1
 * @author 朱晓文
 */
public class XmlUtil {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(XmlUtil.class);

	/**
	 * XML写出 Description :.
	 * 
	 * @param response the response
	 * @param doc the doc
	 * @param fileName the file name
	 */
	public static void writeXml(HttpServletResponse response, Document doc,
			String fileName) {
		ServletOutputStream outputStream = null;
		try {
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(fileName.getBytes("GB2312"), "iso-8859-1") + "_"
					+ DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")
					+ ".xml" + "\"");
			outputStream = response.getOutputStream();
			Format format = Format.getPrettyFormat();
			format.setLineSeparator("\r\n");
			XMLOutputter XMLOut = new XMLOutputter(format);
			// 输出 user.xml 文件；
			XMLOut.output(doc, outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
	}
	
	/**
	 * Cre xml.
	 * 
	 * @param list the list
	 * 
	 * @return the document
	 */
	public Document creXml(List<HashMap> list) {
		// 创建根节点 list;
		Element root = new Element("ufinterface");
		root.setAttribute("account", "01");
		root.setAttribute("billtype", "IA");
		root.setAttribute("filename", "材料出库单");
		root.setAttribute("isexchange", "Y");
		root.setAttribute("proc", "add");
		root.setAttribute("receiver", "01");
		root.setAttribute("replace", "Y");
		root.setAttribute("roottag", "");
		root.setAttribute("sender", "1003");
		root.setAttribute("subbilltype", "");
        Element ia_bill = new Element("ia_bill");
		Element ia_bill_head = new Element("ia_bill_head");
		Element body = new Element("body");
		// 根节点添加到文档中；
		Document Doc = new Document(root);
		// 此处 for 循环可替换成 遍历 数据库表的结果集操作;
		for (int i = 0; i < list.size(); i++) {
			    Map model = (HashMap)list.get(i);
                ia_bill.setAttribute("id", StringUtil.nullToSring(model.get("id")));
	          
          }
        ia_bill.addContent(ia_bill_head);
		ia_bill.addContent(body);
		root.addContent(ia_bill);
		return Doc;
	}
	
}
