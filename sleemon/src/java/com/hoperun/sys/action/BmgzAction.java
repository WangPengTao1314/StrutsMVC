/**
 *@module 基础资料
 *@func 编码规则维护
 *@version 1.1
 *@author 孙炜
 */
package com.hoperun.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.BmgzModel;
import com.hoperun.sys.model.BmgzMxModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.BmgzService;

// TODO: Auto-generated Javadoc
/**
 * The Class BmgzAction.
 * 
 * @author sun_wei
 */
public class BmgzAction extends BaseAction {

    /** The logger. */
    private Logger      logger = Logger.getLogger(BmgzAction.class);

    /** The bmgz service. */
    private BmgzService bmgzService;


    /**
     * Sets the bmgz service.
     * 
     * @param bmgzService the new bmgz service
     */
    public void setBmgzService(BmgzService bmgzService) {

        this.bmgzService = bmgzService;
    }


    /**
     * 尺码维护首页.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("toFrame方法调用开始");
        // 设置跳转时需要的一些公用属性
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        logger.info("toFrame方法调用结束");
        return mapping.findForward("frames");
    }


    /**
     * 尺码维护列表 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("toList方法调用开始");
        // 查询条件
        Map <String, String> params = new TreeMap <String, String>();
        ParamUtil.putStr2Map(request, "BMGZID", params);
        ParamUtil.putStr2Map(request, "BMBH", params);
        ParamUtil.putStr2Map(request, "BMDX", params);
        //ParamUtil.putStr2Map(request, "GZMC", params);
        params.put("GZMC", ParamUtil.utf8Decoder(request, "GZMC"));
        ParamUtil.putStr2Map(request, "ZCD", params);
        ParamUtil.putStr2Map(request, "CREATER", params);
        ParamUtil.putStr2Map(request, "fromCRETIME", params);
        ParamUtil.putStr2Map(request, "toCRETIME", params);

        //ParamUtil.putStr2Map(request, "STATE", params);
        params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
        // 只查询0的记录。1为删除。0为正常
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);

        //权限级别
        UserBean userBean = ParamUtil.getUserBean(request);
        //String QXJBCON = QXUtil.getQXTJ(userBean, "XT0010201");
        //params.put("QXJBCON", QXJBCON);
        // 设置字段排序，及选中的记录
        ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = bmgzService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
        request.setAttribute("page", page);
        logger.info("toList方法调用结束");
        return mapping.findForward("list");
    }


    /**
     * 零星领料编辑框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        // 设置跳转时需要的一些公用属性
        logger.info("toEditFrame方法调用开始");
        ParamUtil.setCommAttributeEx(request);
        logger.info("toEditFrame方法调用结束");
        return mapping.findForward("editFrame");
    }


    /**
     * 编码规则维护明细列表 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("childList方法调用开始");
        String bmgzID = ParamUtil.utf8Decoder(request, "BMGZID");//ParamUtil.get(request, "BMGZID");
        if (StringUtils.isNotEmpty(bmgzID)) {
            List <BmgzMxModel> result = bmgzService.childQuery(bmgzID);
            for (int i = 0; i < result.size(); i++) {
                BmgzMxModel modle = (BmgzMxModel) result.get(i);
                if (BusinessConsts.BMGZ_LSH.equals(modle.getDLX())) {
                    String dcd = modle.getDCD();
                    String csz = modle.getCSZ();
                    String newCsz = StringUtil.lpadString(csz, Integer.parseInt(dcd), "0");
                    modle.setCSZ(newCsz);
                } else if (BusinessConsts.BMGZ_YYR.equals(modle.getDLX())) {
                    modle.setBC("");
                    modle.setCSZ("");
                }
            }
            request.setAttribute("rst", result);
        }
        // 为空直接跳转显示页面，而不是错误提示。
        logger.info("childList方法调用结束");
        return mapping.findForward("childlist");
    }


    /**
     * 编码规则维护修改页面跳转 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("toParentEdit方法调用开始");
        String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request, "selRowId"));//ParamUtil.utf8Decoder(request, "BMGZID");//;
        // 为空则是新增，否则是修改
        if (StringUtils.isNotEmpty(selRowId)) {
            Map <String, String> entry = bmgzService.load(selRowId);
            request.setAttribute("rst", entry);
            request.setAttribute("isNew", false);
        } else {
            request.setAttribute("isNew", true);
        }
        logger.info("toParentEdit方法调用结束");
        return mapping.findForward("toedit");
    }


    /**
     * 显示记录详细信息 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("toDetail方法调用开始");
        String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//ParamUtil.get(request, "BMGZID");
        if (StringUtils.isNotEmpty(bmgzId)) {
            Map <String, String> entry = bmgzService.load(bmgzId);
            request.setAttribute("rst", entry);
        }
        logger.info("toDetail方法调用结束");
        return mapping.findForward("todetail");
    }


    /**
     * 规则维护明细编辑跳转页面 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("toChildEdit方法调用开始");
        // 多个Id批量查询，用逗号隔开
        String bmgzMxIds = request.getParameter("BMGZMXIDS");
        // 没有编码规则明细Id可以直接跳转。
        if (StringUtils.isNotEmpty(bmgzMxIds)) {
            List <BmgzMxModel> list = bmgzService.loadChilds(bmgzMxIds);
            for (int i = 0; i < list.size(); i++) {
                BmgzMxModel modle = (BmgzMxModel) list.get(i);
                // 流水号
                if (BusinessConsts.BMGZ_LSH.equals(modle.getDLX())) {
                    String dcd = modle.getDCD();
                    String csz = modle.getCSZ();
                    String newCsz = StringUtil.lpadString(csz, Integer.parseInt(dcd), "0");
                    modle.setCSZ(newCsz);
                } else if (BusinessConsts.BMGZ_YYR.equals(modle.getDLX())) {
                    // 年月日
                    modle.setBC("");
                    modle.setCSZ("");
                }
            }
            request.setAttribute("rst", list);
        }
        logger.info("toChildEdit方法调用结束");
        return mapping.findForward("childedit");
    }


    /**
     * 编码规则维护编辑页面加载子页面 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("modifyToChildEdit方法调用开始");
        String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//ParamUtil.get(request, "BMGZID");
        if (StringUtils.isNotEmpty(bmgzId)) {
            List <BmgzMxModel> result = bmgzService.childQuery(bmgzId);
            for (int i = 0; i < result.size(); i++) {
                BmgzMxModel modle = (BmgzMxModel) result.get(i);
                if (BusinessConsts.BMGZ_LSH.equals(modle.getDLX())) {
                    String dcd = modle.getDCD();
                    String csz = modle.getCSZ();
                    String newCsz = StringUtil.lpadString(csz, Integer.parseInt(dcd), "0");
                    modle.setCSZ(newCsz);
                } else if (BusinessConsts.BMGZ_YYR.equals(modle.getDLX())) {
                    modle.setBC("");
                    modle.setCSZ("");
                }
            }
            request.setAttribute("rst", result);
        }
        logger.info("modifyToChildEdit方法调用结束");
        return mapping.findForward("childedit");
    }


    /**
     * 编码规则维护删除 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("delete方法调用开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try {
            String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");
            boolean hasDelete = bmgzService.txDelete(bmgzId, userBean);
            bmgzService.clearCaches(bmgzId);
            if (hasDelete == false) {
                jsonResult = jsonResult(false, "启用、停用状态,不能被删除。");
            }
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        logger.info("delete方法调用结束");
    }


    /**
     * 编码规则维护编辑//新增或修改。 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    @SuppressWarnings("unchecked")
	public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("edit方法调用开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorFlg = "";
        try {
        	UserBean userBean = ParamUtil.getUserBean(request);
            String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//ParamUtil.get(request, "BMGZID");

            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            BmgzModel bmgzModel = null;
            if (StringUtils.isNotEmpty(parentJsonData)) {
                bmgzModel = new Gson().fromJson(parentJsonData, new TypeToken <BmgzModel>() {
                }.getType());
                //bmgzModel.setBMBH(bmgzModel.getBMBH().trim());
                bmgzModel.setBMDX(bmgzModel.getBMDX().trim());
            }
            if (null == bmgzModel) {
                mapping.findForward(FAILURE);
                return;
            }
            if (StringUtil.isEmpty(bmgzModel.getBMGZID())) {
                // SEQUENCEMC
                String seq = bmgzModel.getSEQUENCEMC();

                List <Map <String, String>> mapList = bmgzService.getSeq();
                for (int i = 0; i < mapList.size(); i++) {
                    Map <String, String> map = mapList.get(i);
                    String seqName = map.get("OBJECT_NAME");
                    if (seq.equals(seqName)) {

                        errorFlg = BusinessConsts.ERROR_0;
                        throw new Exception();
                    }
                }
            }

            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <BmgzMxModel> modelList = null;
            if (StringUtils.isNotEmpty(jsonDate)) {
                modelList = new Gson().fromJson(jsonDate, new TypeToken <List <BmgzMxModel>>() {
                }.getType());
            }

            // 编码对象现在可重复，需要唯一不重复
            // 判断编号重复
            List <BmgzModel> checkBmdxList = bmgzService.findList("BMGZ.getAll", null);
            if (checkBmdxList != null && checkBmdxList.size() > 0) {
                for (int i = 0; i < checkBmdxList.size(); i++) {
                    BmgzModel checkModel = checkBmdxList.get(i);
                    if (checkModel.getBMGZID().equals(bmgzModel.getBMGZID())) {
                        continue;
                    }
                    if (checkModel.getBMBH().equals(bmgzModel.getBMBH())) {
                        errorFlg = BusinessConsts.ERROR_4;
                        throw new Exception();
                    }
                    if (checkModel.getBMDX().equals(bmgzModel.getBMDX())) {
                        errorFlg = BusinessConsts.ERROR_3;
                        throw new Exception();
                    }
                    if (checkModel.getSEQUENCEMC().equals(bmgzModel.getSEQUENCEMC())) {
                        errorFlg = BusinessConsts.ERROR_0;
                        throw new Exception();
                    }
                }

            }

            // 修正时判断子表段类型是否重复和数据是否打于2条。子表最多只能有2条记录，且一条位流水号，另一条位年月日。
            List <BmgzMxModel> dbModelList = bmgzService.childQuery(bmgzId);

            List <BmgzMxModel> addModelList = new ArrayList <BmgzMxModel>();
            if (modelList != null && !"".equals(bmgzId)) {
                for (int i = 0; i < modelList.size(); i++) {
                    BmgzMxModel insertModel = modelList.get(i);
                    if (StringUtils.isEmpty(insertModel.getBMGZMXID())) {
                        addModelList.add(insertModel);
                    } else {
                        for (int k = 0; k < dbModelList.size(); k++) {
                            BmgzMxModel dbModel = dbModelList.get(k);
                            if (!dbModel.getBMGZMXID().equals(insertModel.getBMGZMXID())) {
                                if (dbModel.getDLX().equals(insertModel.getDLX())) {
                                    errorFlg = BusinessConsts.ERROR_1;
                                    throw new Exception();
                                }
                            }
                        }
                    }
                }
                if (dbModelList != null) {
                    if (dbModelList.size() > 2 || (dbModelList.size() + addModelList.size()) > 2) {
                        errorFlg = BusinessConsts.ERROR_2;
                        throw new Exception();
                    } else {
                        for (int i = 0; i < dbModelList.size(); i++) {
                            BmgzMxModel model = dbModelList.get(i);
                            for (int j = 0; j < addModelList.size(); j++) {
                                BmgzMxModel insertModel = addModelList.get(j);
                                if (insertModel.getDLX().equals(model.getDLX())) {
                                    errorFlg = BusinessConsts.ERROR_1;
                                    throw new Exception();
                                }
                            }
                        }
                        bmgzService.txEdit(bmgzId, userBean, bmgzModel, modelList);
                    }
                } else {
                    bmgzService.txEdit(bmgzId, userBean, bmgzModel, modelList);
                }
            } else {
                bmgzService.txEdit(bmgzId, userBean, bmgzModel, modelList);
            }

            //}
            //}
        } catch (Exception e) {
            if (BusinessConsts.ERROR_0.equals(errorFlg)) {
                jsonResult = jsonResult(false, "SEQUENCEMC重复，请修改再保存");
            } else if (BusinessConsts.ERROR_2.equals(errorFlg)) {
                jsonResult = jsonResult(false, "纪录大于2条");

            } else if (BusinessConsts.ERROR_1.equals(errorFlg)) {
                jsonResult = jsonResult(false, "段类型重复,请修改再保存");
            } else if (BusinessConsts.ERROR_3.equals(errorFlg)) {
                jsonResult = jsonResult(false, "编码对象重复,请修改再保存");
            } else if (BusinessConsts.ERROR_4.equals(errorFlg)) {
                jsonResult = jsonResult(false, "编码编号重复,请修改再保存");
            } else {
                jsonResult = jsonResult(false, "保存失败");
            }
        }

        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        logger.info("edit方法调用结束");
    }


    /**
     * 编码规则维护明细编辑 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("childEdit方法调用开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = null;
        // 子表最多只能有2条记录，且一条位流水号，另一条位年月日。
        String errorFlg = "";
        try {
            userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
        } catch (Exception e) {
            jsonResult = jsonResult(false, BusinessConsts.USER_SESSION_OUT_OF_DATE);
            return;
        }
        try {
            String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//request.getParameter("BMGZID");
            String jsonDate = request.getParameter("childJsonData");
            if (StringUtils.isNotEmpty(jsonDate)) {
                List <BmgzMxModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <BmgzMxModel>>() {
                }.getType());
                // 查子表记录 同1个主表db中子表数据不能多于2条
                List <BmgzMxModel> dbModelList = bmgzService.childQuery(bmgzId);

                List <BmgzMxModel> addModelList = new ArrayList <BmgzMxModel>();
                int zcd = 0;
                if (modelList != null) {
                    for (int i = 0; i < modelList.size(); i++) {
                        BmgzMxModel insertModel = modelList.get(i);
                        if (StringUtils.isEmpty(insertModel.getBMGZMXID())) {
                            addModelList.add(insertModel);
                        } else {
                            for (int k = 0; k < dbModelList.size(); k++) {
                                BmgzMxModel dbModel = dbModelList.get(k);
                                if (!dbModel.getBMGZMXID().equals(insertModel.getBMGZMXID())) {
                                    if (dbModel.getDLX().equals(insertModel.getDLX())) {
                                        errorFlg = BusinessConsts.ERROR_1;
                                        throw new Exception();
                                    }
                                }
                            }
                        }
                    }
                }

                if (dbModelList != null) {
                    if (dbModelList.size() > 2 || (dbModelList.size() + addModelList.size()) > 2) {
                        errorFlg = BusinessConsts.ERROR_2;
                        throw new Exception();
                    } else {
                        for (int i = 0; i < dbModelList.size(); i++) {
                            BmgzMxModel model = dbModelList.get(i);
                            for (int j = 0; j < addModelList.size(); j++) {
                                BmgzMxModel insertModel = addModelList.get(j);
                                if (insertModel.getDLX().equals(model.getDLX())) {
                                    errorFlg = BusinessConsts.ERROR_1;
                                    throw new Exception();
                                }
                            }
                        }
                        bmgzService.txChildEdit(bmgzId, modelList, userBean);
                    }
                } else {
                    bmgzService.txChildEdit(bmgzId, modelList, userBean);
                }
                int countZcd = bmgzService.getZcd(bmgzId);

                jsonResult = jsonResult(true, String.valueOf(countZcd));
            }

        } catch (Exception e) {
            if (BusinessConsts.ERROR_2.equals(errorFlg)) {
                jsonResult = jsonResult(false, "纪录大于2条");

            } else if (BusinessConsts.ERROR_1.equals(errorFlg)) {
                jsonResult = jsonResult(false, "段类型重复,请修改再保存");
            } else {
                jsonResult = jsonResult(false, "保存失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        logger.info("childEdit方法调用结束");
    }


    /**
     * 编码规则维护明细批量删除 Description :.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        logger.info("childDelete方法调用开始");
        UserBean userBean = ParamUtil.getUserBean(request);
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//request.getParameter("BMGZID");

            String bmgzMxIds = request.getParameter("BMGZMXIDS");
            // 批量删除，多个Id之间用逗号隔开
            bmgzService.txBatch4DeleteChild(bmgzMxIds, bmgzId, userBean);
        } catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        logger.info("childDelete方法调用结束");
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

        logger.info("changeState方法调用开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        boolean isChanged = false;
        try {
            String bmgzId = ParamUtil.utf8Decoder(request, "BMGZID");//request.getParameter("BMGZID");
            // 取得状态
            Map <String, String> entry = bmgzService.load(bmgzId);
            String state = entry.get("STATE");
            Map <String, String> params = new TreeMap <String, String>();
            String changedState = "";
            params.put("BMGZID", bmgzId);
            UserBean userBean = ParamUtil.getUserBean(request);
            params.put("UPDATER", userBean.getXM());
            // 启用==>停用
            if (BusinessConsts.JC_STATE_ENABLE.equals(state)) {
                changedState = BusinessConsts.JC_STATE_DISENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
                bmgzService.updateState(params);
                isChanged = true;
            } else if (BusinessConsts.JC_STATE_DISENABLE.equals(state) || BusinessConsts.JC_STATE_DEFAULT.equals(state)) {
                // 停用 ==>启用
                changedState = BusinessConsts.JC_STATE_ENABLE;
                params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
                bmgzService.updateState(params);
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
        logger.info("changeState方法调用结束");
    }

}
