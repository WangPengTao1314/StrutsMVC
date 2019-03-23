/**

 * 项目名称：平台 

 * 系统名：账务基础数据 

 * 文件名：ZTWHAction.java 

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
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.ZTWHModel;
import com.hoperun.sys.model.ZTWHTree;
import com.hoperun.sys.service.ZTWHService;

// TODO: Auto-generated Javadoc
/**
 * The Class ZTWHAction.
 * 
 * @module 财务管理
 * @fuc 帐套信息维护
 * @version 1.1
 * @author 唐赟
 */
public class ZTWHAction extends BaseAction {

    //帐套维护Service变量
    /** The ztwh service. */
    private ZTWHService ztwhService;


    /**
     * Gets the ztwh service.
     * 
     * @return the ztwh service
     */
    public ZTWHService getZtwhService() {

        return ztwhService;
    }


    /**
     * Sets the ztwh service.
     * 
     * @param ztwhService the new ztwh service
     */
    public void setZtwhService(ZTWHService ztwhService) {

        this.ztwhService = ztwhService;
    }


    /**
     * 帐套信息框架.
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
     * 帐套列表.
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
        ParamUtil.putStr2Map(request, "ZTXXID", params);
        ParamUtil.putStr2Map(request, "ZTBH", params);
        params.put("ZTMC", ParamUtil.utf8Decoder(request, "ZTMC"));
        ParamUtil.putStr2Map(request, "SJZT", params);
        ParamUtil.putStr2Map(request, "ZZSH", params);
        params.put("ZTLB", ParamUtil.utf8Decoder(request, "ZTLB"));
        params.put("ZT", ParamUtil.utf8Decoder(request, "ZT"));
        //初期化时只查询未提交和未审核通过的数据ZT
        //if ("".equals(search)) {
        	// params.put("conditionString", " STATE != '" + Consts.JC_STATE_DISENABLE + "'");
       // }
        //字段排序
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        IListPage page = ztwhService.pageQuery(params, pageNo);
        UserBean userBean = ParamUtil.getUserBean(request);
        //String QXJBCON = QXUtil.getQXTJ(userBean, PrivateConsts.ZTXXWH);
        //params.put("QXJBCON", QXJBCON);
        request.setAttribute("params", params);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    /**
     * To list one.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toListOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	Map <String, String> params = new HashMap <String, String>();
    	ParamUtil.putStr2Map(request, "ZTXXID", params);
        ParamUtil.putStr2Map(request, "ZTBH", params);
        ParamUtil.putStr2Map(request, "ZTMC", params);
        ParamUtil.putStr2Map(request, "SJZT", params);
        //String sjztmc = ParamUtil.get(request, "SJZT");
        //params.put("SJZT", sjztmc);
        ParamUtil.putStr2Map(request, "ZZSH", params);
        ParamUtil.putStr2Map(request, "ZTLB", params);
        ParamUtil.putStr2Map(request, "ZT", params);

        //字段排序
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        IListPage page = ztwhService.pageQuery1(params, pageNo);
        request.setAttribute("page", page);

        UserBean userBean = ParamUtil.getUserBean(request);
       // String QXJBCON = QXUtil.getQXTJ(userBean, PrivateConsts.ZTXXWH);
        //params.put("QXJBCON", QXJBCON);

        return mapping.findForward("list");
    }


    /**
     * 帐套详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String ztxxID = ParamUtil.get(request, "ZTXXID");
        Map <String, String> entry = ztwhService.load(ztxxID);
        request.setAttribute("rst", entry);
        return mapping.findForward("detail");
    }


    /**
     * 帐套信息新增或修改.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String ztxxid = ParamUtil.get(request, "ZTXXID");
        String status = "add";
        if (StringUtils.isNotEmpty(ztxxid)) {
            Map <String, String> entry = ztwhService.load(ztxxid);
            status = "modify";
            request.setAttribute("rst", entry);
        }
        request.setAttribute("status", status);
        return mapping.findForward("toedit");
    }


    /**
     * To save.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String jsonData = ParamUtil.get(request, "jsonData");
        ZTWHModel ztwh = null;
        if (StringUtils.isNotEmpty(jsonData)) {
            try {
                ztwh = new Gson().fromJson(jsonData, new TypeToken <ZTWHModel>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map <String, String> params = new HashMap <String, String>();
        params.put("ZTBH", ztwh.getZTBH());
        params.put("ZTMC", ztwh.getZTMC());
        params.put("YJZBJ", Integer.toString(ztwh.getYJZBJ()));
        params.put("SJZT", "".equals(ztwh.getSJZT()) ? null : ztwh.getSJZT());
        params.put("ZZSH", ztwh.getZZSH());
        params.put("YYZZH", ztwh.getYYZZH());
        params.put("ZTLB", ztwh.getZTLB());
        String wldwxxid = ztwh.getWLDWXXID();
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        String ztwhid = ParamUtil.get(request, "ZTXXID");
        int flag = 1;
        if (StringUtils.isEmpty(ztwhid)) {
            List <ZTWHModel> bhList = ztwhService.getBHList();
            String bh = ztwh.getZTBH();
            String ztmc = ztwh.getZTMC();
            for (int i = 0; i < bhList.size(); i++) {
                if (bh.equals(bhList.get(i).getZTBH())) {
                    flag = 0;
                    break;
                }
                if (ztmc.equals(bhList.get(i).getZTMC())) {
                    flag = 2;
                    break;
                }
            }
        }
        UserBean userBean = ParamUtil.getUserBean(request);
        if (flag == 0) {
            jsonResult = jsonResult(false, "帐套编号有重复值，请重新输入！");
        } else if (flag == 2) {
            jsonResult = jsonResult(false, "帐套名称有重复值，请重新输入！");
        } else {
            if (StringUtils.isNotEmpty(ztwhid)) {
                if (ztwh.getZTBH().equals(ztwh.getSJZT())) {
                    jsonResult = jsonResult(false, "帐套编号和上级帐套不能一样！");
                } else {
                    try {
                    	params.put("ZTXXID", ztwhid);
                        params.put("UPDATER", userBean.getXM());
                        ztwhService.updateById(params);
                        request.setAttribute("msg", "保存成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonResult = jsonResult(false, "新增保存失败");
                    }
                }
            } else {
                try {
                	if (ztwh.getSJZT() != "") {
                		String SJZT = ztwh.getSJZT();
                		if (ztwhService.loadZTZT(SJZT)) {
                			//制单人   
                        	if(wldwxxid!=null && !"".equals(wldwxxid)){ //只要wldwxxid不为空的话，就说明帐套是从wldwxx中选的
                        		params.put("ZTXXID", wldwxxid);
                        	}else{
                        		params.put("ZTXXID", ztwh.getZTBH());
                        	}
                            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
                            params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
                            params.put("CREATER", userBean.getYHBH());
                            params.put("CRENAME", userBean.getXM());
                            ztwhService.insertRecord(params);
                            request.setAttribute("msg", "保存成功");
                		}else {
                            jsonResult = jsonResult(false, "不可选择停用的帐套作为上级帐套");
                        }
                	}
                } catch (Exception e) {
                    jsonResult = jsonResult(false, "新增保存失败");
                    e.printStackTrace();
                }
            }
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        return mapping.findForward(SUCCESS);
    }


    /**
     * 更新状态.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void updateZTStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        Map <String, String> params = new HashMap <String, String>();

        String ztxxid = ParamUtil.get(request, "ztxxid");
        params.put("ztxxid", ztxxid);
        String flag = ParamUtil.get(request, "flag");
        if ("1".equals(flag)) {
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);

        } else {
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
        }
        
        try {
        	if (!"1".equals(flag)||ztwhService.querySjForInt(params)) {
        		ztwhService.updateZTStatus(params);
                request.setAttribute("msg", "更新成功！");
                jsonResult = jsonResult(true,params.get("STATE"));
            }else {
                jsonResult = jsonResult(false, "请先启用上级");
            }
        	
            
        } catch (Exception ex) {
            jsonResult = jsonResult(false, "更新失败!");
            ex.printStackTrace();
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

        String ztxxid = ParamUtil.get(request, "ZTXXID");
        UserBean userBean = ParamUtil.getUserBean(request);
        Map <String, String> params = new HashMap <String, String>();
        int countRecord = 0;
        try {
            countRecord = ztwhService.getCountRecord(ztxxid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (countRecord > 0) {
            jsonResult = jsonResult(false, "此帐套有下级帐套，请删除完下级帐套再删除!");
        } else {
            try {
                params.put("UPDATER", userBean.getXM());
                params.put("ztxxid", ztxxid);
                ztwhService.deleteById(params);
                request.setAttribute("msg", "删除成功！");
            } catch (Exception ex) {
                jsonResult = jsonResult(false, "删除失败!");
            }
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

        List <ZTWHTree> trees;
        try {
            trees = ztwhService.getTree();
            String treeData = new Gson().toJson(trees);
            request.setAttribute("tree", treeData);
            return mapping.findForward("tree");
        } catch (Exception e) {
            request.setAttribute("msg", "获取帐套失败！");
            return mapping.findForward(FAILURE);
        }
    }

}
