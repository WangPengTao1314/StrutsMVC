package com.hoperun.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.Properties;

// TODO: Auto-generated Javadoc
/**
 * 项目名称：平台 系统名：文件上传共通 文件名：FileUploadHelper.java
 * 
 * @author zhuxw and qjs
 */
public class FileUploadHelper extends BaseAction {

	/**
	 * 文件上传（无序）.
	 * 
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param moduleName the module name
	 * @param destDir the dest dir
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> uploadFile(ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String moduleName, String destDir)
			throws Exception {

		ActionForm actionForm = (ActionForm) form;
		MultipartRequestHandler multipartRequestHandler = actionForm
				.getMultipartRequestHandler();
		Hashtable elements = multipartRequestHandler.getFileElements();
		Collection values = elements.values();
		Iterator formFiles = values.iterator();
		FormFile formFile = null;
		String serverFilePath = null;
		List<String> serverFilePathList = new ArrayList<String>();
		while (formFiles.hasNext()) {
			formFile = (FormFile) formFiles.next();
			serverFilePath = doUpload(request, formFile, moduleName, destDir);
			if (serverFilePath != null) {
				serverFilePathList.add(serverFilePath);
			}
		}

		return serverFilePathList;
	}

	/**
	 * Up load list.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward upLoadList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("upload");
	}

	/**
	 * 上传文件：单个文件上传.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得容器中上传文件夹所在的物理路径
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		// 上传路径
		String serverDir = Properties.getString("UPLOADFILE_DIR");
		String uploadEnd = Properties.getString("UPLOADFILE_END");
		// 模块配置目录
		String dirpath = request.getParameter("dirpath");
		
		String userPath = Properties.getString(dirpath);
		if (StringUtils.isEmpty(userPath)) {
			jsonResult = jsonResult(false, "获取配置上传路径失败");
		} else {
			// 上传大小限制
			// int maxSize = Properties.getInt("UPLOADFILE_MAXSIZE");
			// //文件格式
			// String fileFormat = Properties.getString("UPLOADFILE_FORMAT");
			String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String fileName = "/" + dateStr + "/";
			String path = serverDir + "/" + userPath + fileName;
			File file = new File(path);
			if (!file.exists()) {
				if(!file.mkdirs())
				{
					jsonResult = jsonResult(false, "创建文件夹失败！");
					writer.write(jsonResult);
					writer.close();
					return null;
				}
			}
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload fileupload = new ServletFileUpload(fac);
			List<FileItem> fileList = fileupload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator<FileItem> it = fileList.iterator();
			String saveFileName = "";
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					String name = item.getName();
					if (name == null || name.trim().equals("")
							|| item.getSize() == 0.0) {
						continue;
					}
					if(name.indexOf(",")!=-1)
					{
						
						jsonResult = jsonResult(false, "对不起，文件名中含有英文逗号！");
						writer.write(jsonResult);
						writer.close();
						return null;
					}
					//后缀名
					String suffixName = StringUtils.substringAfterLast(name,".");
					String namePre = StringUtils.substringBeforeLast(name, ".");
					String uuid = StringUtil.uuid32len();
					String realname ="";
					String dbFileName="";
					if(namePre.indexOf("_")>0&&namePre.substring(namePre.lastIndexOf("_")).length()==33)
					{
						realname = namePre + "." + suffixName;
						dbFileName=realname;
					}else
					{
						realname = namePre + "_" + uuid + "." + suffixName;
						dbFileName=realname;
						if(!StringUtil.isEmpty(uploadEnd))
						{
							realname = realname+"."+uploadEnd;
						}
						
					}
						
				    saveFileName = fileName + dbFileName;
					File saveFile = new File(path + realname);
					item.write(saveFile);
					jsonResult = jsonResult(true, saveFileName);
				
				}
			}
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return null;
	}

	
	/**
	 * edit by zhuxw 2014-01-21 为excel 导入程序定制
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward importExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得容器中上传文件夹所在的物理路径
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		// 上传路径
		String serverDir = Properties.getString("UPLOADFILE_DIR");
		String uploadEnd = Properties.getString("UPLOADFILE_END");
		//这里和上传文件不同
		uploadEnd="";
		// 模块配置目录
		String dirpath = request.getParameter("dirpath");
		String userPath = Properties.getString(dirpath);
		if (StringUtils.isEmpty(userPath)) {
			jsonResult = jsonResult(false, "获取配置上传路径失败");
		} else {
			// 上传大小限制
			// int maxSize = Properties.getInt("UPLOADFILE_MAXSIZE");
			// //文件格式
			// String fileFormat = Properties.getString("UPLOADFILE_FORMAT");
			String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String fileName = "/" + dateStr + "/";
			String path = serverDir + "/" + userPath + fileName;
			File file = new File(path);
			if (!file.exists()) {
				if(!file.mkdirs())
				{
					jsonResult = jsonResult(false, "创建文件夹失败！");
					writer.write(jsonResult);
					writer.close();
					return null;
				}
			}
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload fileupload = new ServletFileUpload(fac);
			List<FileItem> fileList = fileupload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator<FileItem> it = fileList.iterator();
			String saveFileName = "";
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					String name = item.getName();
					if (name == null || name.trim().equals("")
							|| item.getSize() == 0.0) {
						continue;
					}
					//后缀名
					String suffixName = StringUtils.substringAfterLast(name,".");
					String namePre = StringUtils.substringBeforeLast(name, ".");
					String uuid = StringUtil.uuid32len();
					String realname ="";
					String dbFileName="";
					if(namePre.indexOf("_")>0&&namePre.substring(namePre.lastIndexOf("_")).length()==33)
					{
						realname = namePre + "." + suffixName;
						dbFileName=realname;
					}else
					{
						realname = namePre + "_" + uuid + "." + suffixName;
						dbFileName=realname;
						if(!StringUtil.isEmpty(uploadEnd))
						{
							realname = realname+"."+uploadEnd;
						}
						
					}
						
				    saveFileName = fileName + dbFileName;
					File saveFile = new File(path + realname);
					item.write(saveFile);
					jsonResult = jsonResult(true, saveFileName);
				
				}
			}
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return null;
	}
	
	/**
	 * edit by zhuxw 2014-01-21 为excel 导入程序定制
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward impPicServer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得容器中上传文件夹所在的物理路径
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		// 上传路径
		String serverDir = Properties.getString("UPLOADFILE_DIR");
		String uploadEnd = Properties.getString("UPLOADFILE_END");
		//这里和上传文件不同
		uploadEnd="";
		// 模块配置目录
		String dirpath = request.getParameter("dirpath");
		String userPath = Properties.getString(dirpath);
		if (StringUtils.isEmpty(userPath)) {
			jsonResult = jsonResult(false, "获取配置上传路径失败");
		} else {
			// 上传大小限制
			// int maxSize = Properties.getInt("UPLOADFILE_MAXSIZE");
			// //文件格式
			// String fileFormat = Properties.getString("UPLOADFILE_FORMAT");
			//String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String fileName = "/";
			String path = serverDir + "/" + userPath + fileName;
			File file = new File(path);
			if (!file.exists()) {
				if(!file.mkdirs())
				{
					jsonResult = jsonResult(false, "创建文件夹失败！");
					writer.write(jsonResult);
					writer.close();
					return null;
				}
			}
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload fileupload = new ServletFileUpload(fac);
			List<FileItem> fileList = fileupload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator<FileItem> it = fileList.iterator();
			String saveFileName = "";
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					String name = item.getName();
					if (name == null || name.trim().equals("")
							|| item.getSize() == 0.0) {
						continue;
					}
					//后缀名
					String suffixName = StringUtils.substringAfterLast(name,".");
					String namePre = StringUtils.substringBeforeLast(name, ".");
					String uuid = StringUtil.uuid32len();
					String realname ="";
					String dbFileName="";
					if(namePre.indexOf("_")>0&&namePre.substring(namePre.lastIndexOf("_")).length()==33)
					{
						realname = namePre + "." + suffixName;
						dbFileName=realname;
					}else
					{
						realname = namePre + "_" + uuid + "." + suffixName;
						dbFileName=realname;
						if(!StringUtil.isEmpty(uploadEnd))
						{
							realname = realname+"."+uploadEnd;
						}
						
					}
						
				    saveFileName = fileName + dbFileName;
					File saveFile = new File(path + realname);
					item.write(saveFile);
					jsonResult = jsonResult(true, saveFileName);
				
				}
			}
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return null;
	}
	
	/**
	 * 下载文件.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fileName = request.getParameter("fileName"); // 文件ID
		// 二级目录
		String secPath = request.getParameter("secPath"); // 文件ID
		if (!"".equals(fileName) && fileName != null && secPath != null) {
			try {
				// 一级目录
				String serverDir = Properties.getString("UPLOADFILE_DIR");
				String uploadEnd = Properties.getString("UPLOADFILE_END");
				secPath = Properties.getString(secPath);
				String p = serverDir + File.separatorChar + secPath+ File.separatorChar + fileName;
				String name = StringUtils.substringAfterLast(fileName, "/");
				if (name.indexOf("_") > 0&& name.substring(name.lastIndexOf("_"),name.lastIndexOf(".")).length() == 33) {
					if(!StringUtil.isEmpty(uploadEnd))
					{
						p=p+"."+uploadEnd;
					}
					
				}
				File f = new File(p);
				if (!f.exists()) {
					response.reset();
					request.setAttribute("msg", "附件不存在！");
					return mapping.findForward("upload");
				} else {
					
					if (name.indexOf("_") > 0&& name.substring(name.lastIndexOf("_"),name.lastIndexOf(".")).length() == 33) {
						String suffixName = StringUtils.substringAfterLast(name,".");
						name = name.substring(0, name.length() - 34
								- suffixName.length())
								+ "." + suffixName;
					}
					response.reset();
					response.setContentType("application/x-msdownload");
					response.setCharacterEncoding("utf-8");
					response.setHeader("Content-Disposition",
							"attachment; filename=\""
									+ new String(name.getBytes("GB2312"),
											"iso-8859-1") + "\"");
					ServletOutputStream out = response.getOutputStream();
					InputStream inStream = new FileInputStream(f);
					byte[] b = new byte[1024];
					int len;
					while ((len = inStream.read(b)) != -1) {
						out.write(b, 0, len);
					}
					out.close();
					out.flush();
					inStream.close();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				response.reset();
				request.setAttribute("msg", "下载过程中出错！");
				return mapping.findForward("upload");
			}
		} else {
			response.reset();
			request.setAttribute("msg", "下载参数设置不完成！");
			return mapping.findForward("upload");
		}
		return mapping.findForward("upload");

	}

	/**
	 * 文件上传（有序）.
	 * 
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param moduleName the module name
	 * @param destDir the dest dir
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> uploadFileEx(ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String moduleName, String destDir) throws Exception {

		ActionForm actionForm = (ActionForm) form;
		MultipartRequestHandler multipartRequestHandler = actionForm
				.getMultipartRequestHandler();
		Hashtable elements = multipartRequestHandler.getFileElements();
		Set keySet = elements.keySet();
		List<String> keyList = new ArrayList(keySet);

		if (keyList == null || keyList.size() == 0) {
			return null;
		}

		Collections.sort(keyList);

		FormFile formFile = null;
		String serverFilePath = null;
		List<String> serverFilePathList = new ArrayList<String>();
		String filename = "";
		for (String key : keyList) {
			formFile = (FormFile) elements.get(key);
			filename = formFile.getFileName();
			if (filename != null && !"".equals(filename)) {
				serverFilePath = doUpload(request, formFile, moduleName,
						destDir);
				serverFilePathList.add(serverFilePath);
			}
		}

		return serverFilePathList;
	}

	/**
	 * Description :删除文件.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String fileName = request.getParameter("fileName"); // 文件ID
			// 二级目录
			String secPath = request.getParameter("secPath"); // 文件ID

			// 一级目录
			String serverDir = Properties.getString("UPLOADFILE_DIR");
			secPath = Properties.getString(secPath);
			String p = serverDir + File.separatorChar + secPath
					+ File.separatorChar + fileName;
			File f = new File(p);
			if (f.exists()) {
				if(!f.delete())
				{
					jsonResult = jsonResult(false, "文件删除失败");
				}
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "文件删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		return null;
	}

	/**
	 * 获取应用的物理路径.
	 * 
	 * @param request HttpServletRequest
	 * 
	 * @return String
	 */
	private String getAppPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 上传文件.
	 * 
	 * @param request HttpServletRequest
	 * @param formFile FormFile
	 * @param moduleName the module name
	 * @param destDir the dest dir
	 * 
	 * @return String 文件在服务器的路径
	 * 
	 * @throws Exception the exception
	 */
	private String doUpload(HttpServletRequest request, FormFile formFile,
			String moduleName, String destDir) throws Exception {
		String serverDir = Properties.getString("UPLOADFILE_DIR") + "/"+ moduleName;
		if (destDir != null && !"".equals(destDir)) {
			serverDir = destDir;
		}

		String maxSize = Properties.getString("UPLOADFILE_MAXSIZE");

		InputStream is = null;

		OutputStream output = null;

		String originalFileName = null;

		String serverFilePath = null;

		if (formFile != null) {
			originalFileName = formFile.getFileName();

			if ("".equals(originalFileName)) {
				return "";
			}

			if (maxSize == null || "".equals(maxSize)) {
				maxSize = "5";
			}
			if (maxSize.indexOf("m") != -1 || maxSize.indexOf("M") != -1) {
				maxSize = maxSize.substring(0, maxSize.length() - 1);
			}

			if (formFile.getFileSize() <= Integer.parseInt(maxSize) * 1024 * 1024) {
				// String destPath = getAppPath(request) + serverDir;

				String destPath = serverDir;

				try {
					is = formFile.getInputStream();
					File serverPathFile = new File(destPath);
					if (!serverPathFile.exists()) {
						if (!serverPathFile.mkdirs()) {
							return "";
						}
					}

					String sourceFileName = originalFileName.substring(0,
							originalFileName.lastIndexOf("."));
					String fileType = originalFileName.substring(
							originalFileName.lastIndexOf(".") + 1,
							originalFileName.length());
					String uuid = StringUtil.uuid32len();
					serverFilePath = destPath + "/" + sourceFileName + "_"
							+ uuid + "." + fileType;
					output = new FileOutputStream(serverFilePath);
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = is.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();

					// 返回文件在服务器上的目录
					serverFilePath = "/"
							+ Properties.getString("WL_UPLOADFILE_DIR") + "/"
							+ moduleName + "/" + sourceFileName + "_" + uuid
							+ "." + fileType;

				} catch (FileNotFoundException e) {
					return "";
				} catch (IOException e) {
					return "";
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							return "";
						}
					}
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
							return "";
						}
					}
				}
			}

		}

		return serverFilePath;
	}

	/**
	 * 多文件压缩下载.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return return value
	 * 
	 * @throws Exception the exception
	 * 
	 * @author zhuxw
	 */
	public ActionForward mulFileDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonParams = request.getParameter("params"); // 文件ID
		List<Map<String, String>> argArray = new ArrayList<Map<String, String>>();
		if (StringUtils.isNotEmpty(jsonParams)) {
			argArray = new Gson().fromJson(jsonParams,
					new TypeToken<ArrayList<Map<String, String>>>() {
					}.getType());
		}
		String serverDir = Properties.getString("UPLOADFILE_DIR");
		String uploadEnd = Properties.getString("UPLOADFILE_END");
		// bingin press files
		String zipFileName = "/" + TimeComm.getPreTimeStamp("") + ".zip";
		String newZipFile = serverDir + zipFileName;
		FileInputStream fis = null;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(newZipFile));
		try {
			// bigin press files
			byte[] buffer = new byte[1024];
			for (Map<String, String> obj : argArray) {
				String fileName = obj.get("FILENAME");
				String sedPath = obj.get("SEDPATH");
				if (StringUtil.isEmpty(sedPath) || StringUtil.isEmpty(fileName)) {
					response.reset();
					request.setAttribute("msg", "参数不完整！");
					return mapping.findForward("upload");
				}
				String p = serverDir + File.separatorChar
						+ Properties.getString(sedPath) + File.separatorChar
						+ fileName;

				// 后缀名
				String lasfileName = fileName.substring(fileName
						.lastIndexOf("/") + 1);
				String name = lasfileName;
				if (name.indexOf("_") > 0
						&& name.substring(lasfileName.lastIndexOf("_"),
								lasfileName.lastIndexOf(".")).length() == 33) {
					String suffixName = StringUtils.substringAfterLast(
							lasfileName, ".");
					name = lasfileName.substring(0, lasfileName.length() - 34
							- suffixName.length())
							+ "." + suffixName;
					if(!StringUtil.isEmpty(uploadEnd))
					{
						p=p+"."+uploadEnd;
					}
					
				}
				File aFile = new File(p);
				if (!aFile.exists()) {
					response.reset();
					request.setAttribute("msg", "附件不存在！");
					return mapping.findForward("upload");
				} else {
					fis = new FileInputStream(p);
					out.setEncoding("GBK");
					out.putNextEntry(new ZipEntry(name));
					int len;
					while ((len = fis.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					out.closeEntry();
					fis.close();
				}
			}
			out.close();
			// press end
			// bingin download files
			if (!fileDownLoad(response, zipFileName, newZipFile)) {
				response.reset();
				request.setAttribute("msg", "下载过程中出错！");
				return mapping.findForward("upload");
			}
			// end download
			// bigin delete the file
			File delFile = new File(newZipFile);
			delFile.delete();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response.reset();
			request.setAttribute("msg", "下载过程中出错！");
			return mapping.findForward("upload");
		} catch (IOException e) {
			e.printStackTrace();
			response.reset();
			request.setAttribute("msg", "下载过程中出错！");
			return mapping.findForward("upload");
		} finally {
			if (null != fis) {
				fis.close();
			}
			if(null!=out)
			{
				out.close();
			}

		}
		return mapping.findForward("upload");
	}

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param response the response
	 * @param fileName the file name
	 * @param pathAndName the path and name
	 * 
	 * @return return value
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author administrator
	 */
	public boolean fileDownLoad(HttpServletResponse response, String fileName,
			String pathAndName) throws IOException {
		String name = StringUtils.substringAfterLast(fileName, "/");
		// 后缀名
		String suffixName = StringUtils.substringAfterLast(name, ".");
		String namePre = StringUtils.substringBeforeLast(name, ".");
		if (namePre.indexOf("_") > 0
				&& namePre.substring(namePre.lastIndexOf("_")).length() == 33) {
			name = name.substring(0, name.length() - 34 - suffixName.length())
					+ "." + suffixName;
		}
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setCharacterEncoding("utf-8");
		ServletOutputStream out = null;
		InputStream inStream = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(name.getBytes("GB2312"), "iso-8859-1") + "\"");
			out = response.getOutputStream();
			inStream = new FileInputStream(pathAndName);
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) != -1) {
				out.write(b, 0, len);
			}
			inStream.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != out) {
				out.close();
				out.flush();

			}
			if (null != inStream) {
				inStream.close();

			}

		}
	}
}
