/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXAction.java
 */
package com.hoperun.sys.action;

import java.io.PrintWriter;
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
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.RYXXModel;
import com.hoperun.sys.model.RYXXTree;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.RYXXService;

// TODO: Auto-generated Javadoc
/**
 * The Class RYXXAction.
 * 
 * @module 系统管理
 * @func 人员信息
 * @version 1.1
 * @author 吴亚林
 */
public class RYXXAction extends BaseAction {

    /** The ryxx service. */
    private RYXXService ryxxService;


    /**
     * Sets the ryxx service.
     * 
     * @param ryxxService the new ryxx service
     */
    public void setryxxService(RYXXService ryxxService) {

        this.ryxxService = ryxxService;
    }


    /**
     * 人员信息框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 人员信息列表.
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

        Map params = new HashMap();
        params.put("BMXXID", ParamUtil.utf8Decoder(request, "BMXXID"));
        String RYBHQuery=ParamUtil.utf8Decoder(request, "RYBH");
        params.put("RYBHQuery", StringUtil.creCon("u.RYBH", RYBHQuery, ","));
        ParamUtil.putStr2Map(request, "RYBH", params);
        params.put("XM", ParamUtil.utf8Decoder(request, "XM"));
        params.put("RYZT", ParamUtil.utf8Decoder(request, "RYZT"));
        String JGMCQuery=ParamUtil.utf8Decoder(request, "JGMC");
        params.put("JGMCQuery", StringUtil.creCon("j.JGMC", JGMCQuery, ","));
        params.put("JGMC", ParamUtil.utf8Decoder(request, "JGMC"));
        String BMMCQuery=ParamUtil.utf8Decoder(request, "BMMC");
        params.put("BMMC", ParamUtil.utf8Decoder(request, "BMMC"));
        params.put("BMMCQuery", StringUtil.creCon("t.BMMC", BMMCQuery, ","));
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        params.put("IS_DRP_LEDGER", "0"); //过滤掉分销商的人员

        //字段排序
        ParamUtil.setOrderField(request, params, "u.CRETIME", "DESC");
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = ryxxService.pageQuery(params, pageNo);

        request.setAttribute("params", params);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 查看人员详细信息.
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

        String RYXXID = ParamUtil.get(request, "RYXXID");
        Map entry = ryxxService.load(RYXXID);
        request.setAttribute("rst", entry);

        return mapping.findForward("view");
    }


    /**
     * 点击新增或修改按钮跳转到编辑页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String RYXXID = ParamUtil.get(request, "RYXXID");
        if (StringUtils.isNotEmpty(RYXXID)) {
            Map entry = ryxxService.load(RYXXID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("toedit");
    }


    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        UserBean userBean = ParamUtil.getUserBean(request);
        String RYXXID = ParamUtil.get(request, "RYXXID");
        String jsonData = ParamUtil.get(request, "jsonData");
        RYXXModel model = new RYXXModel();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <RYXXModel>() {
            }.getType());
        }
        String BMXXID = model.getBMXXID();
        String JGXXID = model.getJGXXID();
        if (!StringUtils.isNotEmpty(model.getJGXXID()) && !StringUtils.isNotEmpty(model.getBMXXID())) {
            jsonResult = jsonResult(false, "请选择上级机构或部门");
        } else {
            if(StringUtils.isEmpty(RYXXID)&&(ryxxService.getCountBH(model.getRYBH())>0)){
            	jsonResult = jsonResult(false, "编号有重复值，请重新输入");
            }else{
            	try {
            		if(ryxxService.loadBMZT(BMXXID))
                	{
            			if (ryxxService.loadJGZT(JGXXID)) {
                    		ryxxService.txEdit(RYXXID, model, userBean); 
            			}else{
            				jsonResult = jsonResult(false, "不可选择停用的机构作为所属机构");
            			}
                	}else{
                		jsonResult = jsonResult(false, "不可选择停用的部门作为所属部门");
                	}                   
                } catch (RuntimeException e) {
                	e.printStackTrace();
                    jsonResult = jsonResult(false, "保存失败");
                }
            }           
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
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
        String RYXXID = ParamUtil.get(request, "RYXXID");

        if (StringUtils.isNotEmpty(RYXXID)) {
            try {
                ryxxService.txDelete(RYXXID, userBean);
                ryxxService.clearCaches(RYXXID);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 部门树.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward showTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        List <RYXXTree> trees = null;
        try {
        	UserBean userBean = ParamUtil.getUserBean(request);
            String theme = userBean.getUSERSTYLE();
            String ctx = request.getContextPath();
            trees = ryxxService.getTree(ctx, theme);

            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
        } catch (Exception e) {
            request.setAttribute("msg", "获取部门失败！");
            return mapping.findForward(FAILURE);
        }
        return mapping.findForward("tree");
    }


    //    private void setIcon(List trees,String ctx,String theme){
    //    	for(Object tree : trees){
    //    		tree = (RYXXTree)tree;
    //        	System.out.println(tree.getIcon()+"|"+"bm".equals(tree.getIcon()));
    //        	if("bm".equals(tree.getIcon())){
    //        		tree.setIcon(ctx+"/styles/"+theme+"/images/tree3/bm.png");
    //        	}else{
    //        		tree.setIcon(ctx+"/styles/"+theme+"/images/tree3/jg.png");
    //        	}
    //        	if(!tree.getChilds().isEmpty()){
    //        		setIcon((tree.getChilds(), ctx, theme);
    //        	}
    //        }
    //    }

    /**
     * 停用/启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    @SuppressWarnings("unchecked")
    public void changeState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        UserBean userBean = ParamUtil.getUserBean(request);
        String RYXXID = request.getParameter("RYXXID");

        Map <String, String> entry = ryxxService.load(RYXXID);

        String state = entry.get("RYZT");
        Map <String, String> params = new HashMap <String, String>();

        try {
            String changedState = "";
            params.put("RYXXID", RYXXID);
            params.put("UPDATER", userBean.getXTYHID());
            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("RYZT", BusinessConsts.JC_STATE_DISENABLE);
                ryxxService.txUpdateById(params);
                isChanged = true;
            }
            //deleted by wang yehua 
            // else if (Consts.JC_STATE_DISENABLE.equals(state)) {
            //changedState = Consts.JC_STATE_ENABLE;
            //params.put("RYZT", Consts.JC_STATE_ENABLE);
            //ryxxService.txUpdateById(params);
            //isChanged = true;
            //} 
            else {
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("RYZT", BusinessConsts.JC_STATE_ENABLE);
                ryxxService.txUpdateById(params);
                isChanged = true;
            }
            if (isChanged) {
                jsonResult = jsonResult(true, changedState);
            } else {
                jsonResult = jsonResult(false, "状态不用修改");
            }
        } catch (RuntimeException e) {
            jsonResult = jsonResult(false, "状态修改失败");
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
}
