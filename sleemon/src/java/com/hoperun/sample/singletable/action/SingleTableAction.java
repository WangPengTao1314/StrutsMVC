/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXAction.java
 */
package com.hoperun.sample.singletable.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sample.singletable.service.SingleTableService;
import com.hoperun.sys.model.JGXXModel;
import com.hoperun.sys.model.JGXXTree;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class SingleTableAction.
 * 
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
public class SingleTableAction extends BaseAction {

    // 日志记录
    /** The logger. */
    private Logger      logger = Logger.getLogger(SingleTableAction.class);

    /** The single table service. */
    private SingleTableService singleTableService;

    /**
     * Sets the single table service.
     * 
     * @param singleTableService the new single table service
     */
    public void setSingleTableService(SingleTableService singleTableService) {
		this.singleTableService = singleTableService;
	}


	/**
	 * 机构信息框架页面.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
    public ActionForward toFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map <String, String> params = new HashMap <String, String>();
        ParamUtil.putStr2Map(request, "JGXXID", params);
        ParamUtil.putStr2Map(request, "JGMC", params);
        ParamUtil.putStr2Map(request, "JGJC", params);
        ParamUtil.putStr2Map(request, "JGBH", params);
        ParamUtil.putStr2Map(request, "SJJG", params);
        String search = request.getParameter("search");
        String JGZT = request.getParameter("JGZT");

        if (search == null || "".equals(search)) {
            params.put("JGZT", "('" + BusinessConsts.JC_STATE_DEFAULT + "','" + BusinessConsts.JC_STATE_ENABLE + "','" + BusinessConsts.JC_STATE_DISENABLE + "')");
        } else {
            if (null == JGZT || "".equals(JGZT)) {
                params.put("JGZT", "('" + BusinessConsts.JC_STATE_DEFAULT + "','" + BusinessConsts.JC_STATE_ENABLE + "','" + BusinessConsts.JC_STATE_DISENABLE + "')");
            } else {
                params.put("JGZT", "('" + JGZT + "')");
            }
        }
        ParamUtil.putStr2Map(request, "ZTXXID", params);
        ParamUtil.putStr2Map(request, "XH", params);
        // 只查询0的记录。1为删除。0为正常
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        // 字段排序
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = singleTableService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 机构信息编辑初始化页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String jgxxId = ParamUtil.get(request, "JGXXID");
        if (StringUtils.isNotEmpty(jgxxId)) {
            Map <String, String> entry = singleTableService.load(jgxxId);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("toedit");
    }


    /**
     * 机构信息编辑 新增/修改.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("Enter edit()");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
        String jsonData = ParamUtil.get(request, "jsonData");
        JGXXModel jgxxModel = new JGXXModel();
        if (StringUtils.isNotEmpty(jsonData)) {
            jgxxModel = new Gson().fromJson(jsonData, new TypeToken <JGXXModel>() {
            }.getType());
        }
        String jgxxId = ParamUtil.get(request, "JGXXID");
        String SJJG = jgxxModel.getSJJG();
        Map <String, String> params = new HashMap <String, String>();
        params.put("JGXXID", jgxxId);
        params.put("JGBH", jgxxModel.getJGBH());
        params.put("DELFLAG", "0");
        if (singleTableService.queryForInt(params)) {
            if (singleTableService.loadJGZT(SJJG)) {
                try {
                    jgxxId = singleTableService.txEdit(jgxxId, jgxxModel, userBean);
                    jsonResult = jsonResult(true, jgxxId);
                } catch (Exception e) {
                    jsonResult = jsonResult(false, "保存失败");
                }
            } else {
                jsonResult = jsonResult(false, "不可选择停用的机构作为上级机构");
            }
        } else {
            jsonResult = jsonResult(false, "该编号已存在");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 查看机构详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String jgxxId = ParamUtil.get(request, "JGXXID");
        if (StringUtils.isNotEmpty(jgxxId)) {
            Map <String, String> entry = singleTableService.load(jgxxId);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("view");
    }


    /**
     * 删除.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
        String jsonResult = jsonResult();
        try {
            String jgxxId = ParamUtil.get(request, "JGXXID");
            singleTableService.txDelete(jgxxId, userBean);
            singleTableService.clearCaches(jgxxId);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 按钮修改状态为启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateQy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("按钮修改为启用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        try {
            logger.warn("取得对应记录的状态");
            UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
            String JGXXID = request.getParameter("JGXXID");
            // String state = entry.get("STATE");
            Map <String, String> params = new HashMap <String, String>();
            String changedState = "";
            params.put("JGXXID", JGXXID);
            params.put("UPDATER", userBean.getXTYHID());

            changedState = BusinessConsts.JC_STATE_ENABLE;
            params.put("JGZT", BusinessConsts.JC_STATE_ENABLE);
            if (singleTableService.querySjForInt(params)) {

            	singleTableService.txStartById(params);
                isChanged = true;

                if (isChanged) {
                    jsonResult = jsonResult(true, changedState);
                } else {
                    jsonResult = jsonResult(false, "状态不用修改");
                }
            } else {
                jsonResult = jsonResult(false, "请先启用上级");
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 按钮修改状态为停用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateTy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        try {
            logger.warn("取得对应记录的状态");
            UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
            String JGXXID = request.getParameter("JGXXID");
            Map <String, String> params = new HashMap <String, String>();
            String changedState = "";
            params.put("JGXXID", JGXXID);
            params.put("UPDATER", userBean.getXTYHID());
            changedState = BusinessConsts.JC_STATE_DISENABLE;
            params.put("JGZT", BusinessConsts.JC_STATE_DISENABLE);
            singleTableService.txStopById(params);
            isChanged = true;

            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
                jsonResult = jsonResult(false, "状态不用修改");
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 停用启用按钮修改单条记录状态.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("停用启用按钮修改单条记录状态");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        try {
            String JGXXID = request.getParameter("JGXXID");
            Map <String, String> entry = singleTableService.load(JGXXID);
            String state = entry.get("STATE");
            Map <String, String> params = new HashMap <String, String>();
            String changedState = state;
            params.put("JGXXID", JGXXID);
            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("JGZT", BusinessConsts.JC_STATE_DISENABLE);
                singleTableService.txUpdateById(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("JGZT", BusinessConsts.JC_STATE_ENABLE);
                singleTableService.txUpdateById(params);
                isChanged = true;
            }
            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
                jsonResult = jsonResult(false, "状态不用修改");
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
            trees = singleTableService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
            request.setAttribute("msg", "获取组织机构失败！");
            e.printStackTrace();
            return mapping.findForward(FAILURE);
        }
    }

}
