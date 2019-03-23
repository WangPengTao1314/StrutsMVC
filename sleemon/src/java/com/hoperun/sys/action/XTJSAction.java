/**

 * 项目名称：平台

 * 系统名：基础数据

 * 文件名：XTJSAction.java

 */
package com.hoperun.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.XTJSBean;
import com.hoperun.sys.model.XTYHJSBean;
import com.hoperun.sys.service.XTJSService;

// TODO: Auto-generated Javadoc
/**
 * The Class XTJSAction.
 * 
 * @module 系统管理
 * @fuc 系统角色信息维护
 * @version 1.1
 * @author 唐赟
 */

public class XTJSAction extends BaseAction {

    //系统角色Service变量
    /** The xtjs service. */
    private XTJSService xtjsService;


    /**
     * Sets the xtjs service.
     * 
     * @param xtjsService the new xtjs service
     */
    public void setxtjsService(XTJSService xtjsService) {

        this.xtjsService = xtjsService;
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
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 主从表一起保存.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String status = ParamUtil.get(request, "XTJSID");
        UserBean userBean = null;
        try {
            userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "用户已失效");
            return;
        }
        int flag = 0;
        String jsonDateP = ParamUtil.get(request, "parentJsonData");
        XTJSBean xtjs = null;
        if (StringUtils.isNotEmpty(jsonDateP)) {
            xtjs = new Gson().fromJson(jsonDateP, new TypeToken <XTJSBean>() {
            }.getType());
                        if (StringUtils.isEmpty(status)) {
                            String jsbh = xtjs.getJSBH();
                            //判断是否有重复角色编号
                            List <XTJSBean> bhList = xtjsService.getBHList();
                            for (int i = 0; i < bhList.size(); i++) {
                                if (jsbh.equals(bhList.get(i).getJSBH())) {
                                    flag = 1;
                                }
                            }
                        }
        }

        try {
            String jsonDateC = ParamUtil.get(request, "childJsonData");
            List <XTYHJSBean> xtyhjs = null;
            if (StringUtils.isNotEmpty(jsonDateC)) {
                xtyhjs = new Gson().fromJson(jsonDateC, new TypeToken <List <XTYHJSBean>>() {
                }.getType());
            }
            if (flag == 1) {
                jsonResult = jsonResult(false, "角色编号重复，请重新输入");
            } else {
            	boolean chkFlag=true;
            	 //新增列表
                List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
                //修改列表
                List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
                //判断用户编号是否有重复值
                if(xtyhjs!=null)
                {
                for (int i = 0; i < xtyhjs.size() - 1; i++) {
                    String temp = xtyhjs.get(i).getYHBH();
                    if (temp.equals(xtyhjs.get(i + 1).getYHBH())) {
                    	jsonResult = jsonResult(false, "明细用户编号重复！");
                    	chkFlag=false;
                    	break;
                    }
                  }
                }
                //判断数据库是否有重复的用户编号
                //List <XTYHJSBean> bhList = getYHBHList(xtjs.getJSBH());
                if(chkFlag)
                {
                List <XTYHJSBean> bhList = xtjsService.getYHBHList(status);
                
                if(xtyhjs!=null)
                {
                for (int q = 0; q < xtyhjs.size(); q++) {
                    if ("".equals(xtyhjs.get(q).getXTYHJSID())) {
                        for (int j = 0; j < bhList.size(); j++) {
                            if (xtyhjs.get(q).getYHBH().equals(bhList.get(j).getXTYHID())) {
                            	jsonResult = jsonResult(false, " 用户编号重复!");
                            	chkFlag=false;
                            	break;
                            }
                        }
                    }
                }
                }
                
                
                }
                if(chkFlag)
                {
            	if (!(xtjsService.txEdit(status, userBean, xtjs, xtyhjs))) {
                    jsonResult = jsonResult(false, "保存失败");
                }
                }
            	
            }

        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
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
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String xtjsID = ParamUtil.get(request, "XTJSID");

        Map <String, String> entry = xtjsService.load(xtjsID);
        request.setAttribute("rst", entry);

        return mapping.findForward("detail");
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
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try {
            String xtjsID = ParamUtil.get(request, "JSBH");
            xtjsService.txDelete(xtjsID, userBean);
            xtjsService.clearCaches(xtjsID);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }

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
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Map <String, String> params = new HashMap <String, String>();
        ParamUtil.putStr2Map(request, "JSBH", params);
        params.put("JSMC", ParamUtil.utf8Decoder(request, "JSMC"));
        params.put("YHZT", ParamUtil.utf8Decoder(request, "YHZT"));
        params.put("XM", ParamUtil.utf8Decoder(request, "XM"));
        ParamUtil.putStr2Map(request, "YHBH", params);
        params.put("YHM", ParamUtil.utf8Decoder(request, "YHM"));
        //未删除的
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);

        //字段排序
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = xtjsService.pageQuery(params, pageNo);

        request.setAttribute("params", params);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * Child list.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String XTJSID = ParamUtil.get(request, "XTJSID");
        if (StringUtils.isNotEmpty(XTJSID)) {
            List <XTYHJSBean> result = xtjsService.childQuery(XTJSID);
            request.setAttribute("rst", result);
        }
        return mapping.findForward("childList");
    }


    /**
     * To edit frame.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ParamUtil.setCommAttributeEx(request);
        return mapping.findForward("editFrame");
    }


    /**
     * To parent edit.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String status = "add";
        String selRowId = ParamUtil.get(request, "selRowId");
        //为空则是新增，否则是修改
        if (StringUtils.isNotEmpty(selRowId)) {
            Map <String, String> entry = xtjsService.load(selRowId);
            status = "modify";
            request.setAttribute("rst", entry);
        }
        request.setAttribute("status", status);
        return mapping.findForward("toedit");

    }


    /**
     * 主从表编缉.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String xtjsId = ParamUtil.get(request, "XTJSID");
        if (StringUtils.isNotEmpty(xtjsId)) {

            List <XTYHJSBean> result = xtjsService.childQuery(xtjsId);
            request.setAttribute("rst", result);
        }

        return mapping.findForward("childedit");
    }


    /**
     * 子表保存.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        XTJSBean xtjs = new XTJSBean();
        List <XTYHJSBean> subList = null;
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String xtjsid = ParamUtil.get(request, "XTJSID");
        String jsonData = request.getParameter("childJsonData");
        if (StringUtils.isNotEmpty(jsonData)) {
            subList = new Gson().fromJson(jsonData, new TypeToken <List <XTYHJSBean>>() {
            }.getType());
            if (xtjsService.txChildEdit(xtjsid, xtjs, subList)) {
                request.setAttribute("msg", "保存成功");
            } else {
                jsonResult = jsonResult(false, "保存失败,用户编号有重复值");
            }
        } else {
            jsonResult = jsonResult(false, "保存失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 跳转到从表编缉页面前需要设置的值.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String status = "";
        //多个Id批量查询，用逗号隔开
        String xtjsids = request.getParameter("XTYHJSIDS");

        if (StringUtils.isNotEmpty(xtjsids)) {
            List <XTYHJSBean> list = xtjsService.loadChilds(xtjsids);
            status = "modify";
            request.setAttribute("rst", list);
        }
        request.setAttribute("status", status);
        return mapping.findForward("childedit");
    }


    /**
     * 明细表批量删除
     * Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        UserBean userBean = ParamUtil.getUserBean(request);
        String jsonResult = jsonResult();
        try {
            String XTJSYHID = request.getParameter("XTJSYHID");
            //批量删除，多个Id之间用逗号隔开
            xtjsService.txBatch4DeleteChild(XTJSYHID, userBean);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 启用，停用按钮.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @author tang_yun
     */
    public void updateJSStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        String flag = ParamUtil.get(request, "flag");
        Map <String, String> params = new HashMap <String, String>();
        ParamUtil.putStr2Map(request, "xtjsid", params);
        if ("1".equals(flag)) {
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
        } else {
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
        }
        try {
            xtjsService.updateJSStatus(params);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = jsonResult(false, "更新失败");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

}
