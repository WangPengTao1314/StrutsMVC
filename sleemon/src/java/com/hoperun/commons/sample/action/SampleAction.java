/**
 * @module 共通管理
 * @fuc 控件例子
 * @version 1.1
 * @author zhuxw
 */
package com.hoperun.commons.sample.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.sample.model.SampleTree;
import com.hoperun.commons.sample.service.SampleService;
import com.hoperun.commons.util.BmgzHelper;
import com.hoperun.sys.model.JGXXTree;
import com.hoperun.sys.service.JGXXService;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleAction.
 * 
 * @module 共通管理
 * @fuc 控件例子
 * @version 1.1
 * @author zhuxw
 */
public class SampleAction extends BaseAction {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(SampleAction.class);
	
	/** The sample service. */
	private SampleService sampleService;
	
	/** The jgxx service. */
	private JGXXService jgxxService;
	
	/** The bmgz helper. */
	private BmgzHelper bmgzHelper;
	
	/**
	 * Sets the sample service.
	 * 
	 * @param sampleService the new sample service
	 */
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	/**
	 * To frame.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in toFrame");
		return mapping.findForward("toFrame");
	}
	public ActionForward toHtml5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in toHtml5");
		return mapping.findForward("toHtml5");
	}
	/**
	 * Select.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward select(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("toFrame");
	}
	
	/**
	 * Showtree.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward showtree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("tree1");
	}
    
	/**
	 * Calendar.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward calendar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
       return mapping.findForward("calendar");
	}
	
	
	
//		public static void recure(List menuTreeModels, Element element) {
//			for (Object obj : menuTreeModels) {
//				SampleTree menuTreeModel = (SampleTree) obj;
//				Element child = new Element("item");
//				child.setAttribute("id", menuTreeModel.getId());
//				child.setAttribute("text", menuTreeModel.getText());
//				if (StringUtils.isNotBlank(menuTreeModel.getIcon())) {
					//child.setAttribute("im0", menuTreeModel.getIcon());
//				}
//				//增加非叶子节点生成URL功能
//				if(StringUtils.isNotBlank(menuTreeModel.getHref()))
//				{
//					Element e = new Element("userdata");
//					e.setAttribute("name", "url");
//					e.setText(menuTreeModel.getHref());
//					child.addContent(e);
//				}
//				element.addContent(child);
//				if (menuTreeModel.getChildren() != null
//						&& menuTreeModel.getChildren().size() != 0) {
//					recure(menuTreeModel.getChildren(), child);
//				};
//			}
//		}
	
	
	
	
	/**
 * 文件上传.
 * 
 * @param mapping ActionMapping
 * @param form ActionForm
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 * 
 * @return ActionForward
 * 
 * @throws Exception the exception
 */
	public ActionForward fileupload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String code = bmgzHelper.createCode("规则名称123");
	    
//	    BmgzHelper bh = (BmgzHelper)SpringContextUtil.getBean("bmgzHelper");
	    
//	    String code = bh.createCode("规则名称123");
		
		return mapping.findForward("fileupload");
	}
	
	/**
	 * 文件上传.
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
    public ActionForward doUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForm actionForm = (ActionForm) form;
        MultipartRequestHandler multipartRequestHandler = actionForm.getMultipartRequestHandler();
        Hashtable elements = multipartRequestHandler.getFileElements();
        Collection values = elements.values();
        Iterator formFiles = values.iterator();
        FormFile formFile = null;
        String serverFilePath = null;
        while (formFiles.hasNext()) {
            formFile = (FormFile) formFiles.next();
            serverFilePath = uploadFile(request, formFile);
            if (serverFilePath != null) {
                request.setAttribute("destPath", serverFilePath);
            }
        }
		
		return mapping.findForward("fileupload");
	}
	
	/**
	 * 获取应用的物理路径.
	 * 
	 * @param request HttpServletRequest
	 * 
	 * @return String
	 */
	public static String getAppPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }
	
	/**
	 * 随机数生成器.
	 * 
	 * @return String 32位随机数
	 */
	public static String makeUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
	
	/**
	 * 上传文件.
	 * 
	 * @param request HttpServletRequest
	 * @param formFile FormFile
	 * 
	 * @return String 文件在服务器的路径
	 * 
	 * @throws Exception the exception
	 */
	public String uploadFile(HttpServletRequest request, FormFile formFile) throws Exception {

        String serverDir = Properties.getString("UPLOADFILE_DIR");
        
        String maxSize = Properties.getString("UPLOADFILE_MAXSIZE");

        InputStream is = null;

        OutputStream output = null;

        String originalFileName = null;
        
        String serverFilePath = null;

        if (formFile != null) {
            originalFileName = formFile.getFileName();
      
            if (maxSize == null || "".equals(maxSize)) {
                maxSize = "5";
            }
            if (maxSize.indexOf("m") != -1 || maxSize.indexOf("M") != -1) {
                maxSize = maxSize.substring(0, maxSize.length() - 1);
            }
            
            if (formFile.getFileSize() <= Integer.parseInt(maxSize) * 1024 * 1024) {
                String destPath = getAppPath(request) + serverDir;
                
                try {
                    is = formFile.getInputStream();
                    File serverPathFile = new File(destPath);
                    if (!serverPathFile.exists()) {
                        if (!serverPathFile.mkdirs()) {
                            return null;
                        }
                    }
                    
                    String sourceFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                    String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
                    serverFilePath = destPath + "\\" + sourceFileName + "_" + makeUUID() + "." + fileType;
                    output = new FileOutputStream(serverFilePath);
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = is.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                } catch (FileNotFoundException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            return null;
                        }
                    }
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            return null;
                        }
                    }
                }
            }

        }

        return serverFilePath;
    }
	
	  /**
  	 * 显示树.
  	 * 
  	 * @param mapping the mapping
  	 * @param form the form
  	 * @param request the request
  	 * @param response the response
  	 * 
  	 * @return the action forward
  	 */
    public ActionForward showTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        List <JGXXTree> trees;
        try {
            trees = jgxxService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
            request.setAttribute("msg", "获取组织机构失败！");
            return mapping.findForward(FAILURE);
        }
    }
    
    /**
     * Show tree3.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTree3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        List <SampleTree> trees;
        try {
            trees = sampleService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree2");
        } catch (Exception e) {
            request.setAttribute("msg", "获取组织机构失败！");
            return mapping.findForward(FAILURE);
        }
    }
    
    /**
     * Show tree async.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTreeAsync(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	List <SampleTree> trees;
    	try {
    		trees = sampleService.getTreeAsyncInit();
    		String treeData = new Gson().toJson(trees);
    		request.setAttribute("tree", treeData);
    		return mapping.findForward("treeAsync");
    	} catch (Exception e) {
    		request.setAttribute("msg", "获取组织机构失败！");
    		return mapping.findForward(FAILURE);
    	}
    }
    
    /**
     * Gets the tree async.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the tree async
     */
    public void getTreeAsync(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	PrintWriter writer = getWriter(response);
    	String sjjg = request.getParameter("sjjg");
    	List<SampleTree> trees = null;
		try {
			trees = sampleService.getTreeAsync(sjjg);
		} catch (Exception e1) {
		}
		String treeData = new Gson().toJson(trees);
    	
    	if (null != writer) {
			writer.write(treeData);
			writer.close();
    	}
    }

	/**
	 * Gets the bmgz helper.
	 * 
	 * @return the bmgz helper
	 */
	public BmgzHelper getBmgzHelper() {
		return bmgzHelper;
	}

	/**
	 * Sets the bmgz helper.
	 * 
	 * @param bmgzHelper the new bmgz helper
	 */
	public void setBmgzHelper(BmgzHelper bmgzHelper) {
		this.bmgzHelper = bmgzHelper;
	}

	/**
	 * Sets the jgxx service.
	 * 
	 * @param jgxxService the new jgxx service
	 */
	public void setJgxxService(JGXXService jgxxService) {
		this.jgxxService = jgxxService;
	}

}
