package com.hoperun.sys.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.ParamCover;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.MenuService;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuAction.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class MenuAction extends BaseAction {

    /** The menu service. */
    private MenuService menuService;


    /**
     * To add.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward("add");
    }


    /**
     * Adds the.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "menuId", params);
        Map entry = menuService.load(params);
        if (entry != null) {
            request.setAttribute("msg", "菜单编号已存在，不能重复增加！");
            return mapping.findForward(FAILURE);
        }
        ParamUtil.putStr2Map(request, "menuName", params);
        ParamUtil.putStr2Map(request, "menuParId", params);
        ParamUtil.putStr2Map(request, "menuDesc", params);
        ParamUtil.putStr2Map(request, "menuUrl", params);
        ParamUtil.putInt2Map(request, "menuSort", params);
        ParamUtil.putStr2Map(request, "menuImg", params);
        ParamUtil.putStr2Map(request, "menuQxCode", params);
        ParamUtil.putStr2Map(request, "BUSINESSTYPE", params);
        menuService.insert(params);
        request.setAttribute("msg", "增加成功！");
        return mapping.findForward("add");
    }


    /**
     * To modi.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toModi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "menuId", params);
        Map entry = menuService.load(params);
        request.setAttribute("entry", entry);
        request.setAttribute("pageNo", pageNo);

        return mapping.findForward("modi");
    }


    /**
     * Modi.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "menuId", params);
        ParamUtil.putStr2Map(request, "menuParId", params);
        ParamUtil.putStr2Map(request, "menuName", params);
        ParamUtil.putStr2Map(request, "menuDesc", params);
        ParamUtil.putStr2Map(request, "menuUrl", params);
        ParamUtil.putInt2Map(request, "menuSort", params);
        ParamUtil.putStr2Map(request, "menuImg", params);
        ParamUtil.putStr2Map(request, "menuQxCode", params);
        ParamUtil.putStr2Map(request, "BUSINESSTYPE", params);
        menuService.updateById(params);
        request.setAttribute("msg", "修改成功！");

        return mapping.findForward(SUCCESS);
    }


    /**
     * To view.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "menuId", params);
        Map entry = menuService.load(params);
        request.setAttribute("entry", entry);

        return mapping.findForward("view");
    }


    /**
     * Delete.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "menuId", params);
        UserBean userBean = ParamUtil.getUserBean(request);
        menuService.delete(params, userBean);
        String _backUrl = request.getParameter("_backUrl");
        if (!_backUrl.isEmpty()) {
            ParamCover aParamCover = new ParamCover(request);
            aParamCover.set_backUrl(_backUrl);
            request.setAttribute(PARAM_COVER, aParamCover);
        }
        request.setAttribute("msg", "删除成功！");
        return mapping.findForward(SUCCESS);
    }


    /**
     * To query.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        return toList(mapping, form, request, response);
    }


    /**
     * To list.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "menuId", params);
        ParamUtil.putStr2Map(request, "menuName", params);
        ParamUtil.putStr2Map(request, "menuParId", params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = menuService.pageQuery(params, pageNo);
        request.setAttribute("page", page);
        request.setAttribute("pageNo", pageNo);
        return mapping.findForward("list");
    }


    /**
     * Sets the menu service.
     * 
     * @param menuService the new menu service
     */
    public void setMenuService(MenuService menuService) {

        this.menuService = menuService;
    }

}
