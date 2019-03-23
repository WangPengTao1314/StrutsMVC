package com.hoperun.sys.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.ParamCover;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.sys.model.FlowObject;
import com.hoperun.sys.model.ProcessNode;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowService;

// TODO: Auto-generated Javadoc
/**
 * The Class FlowAction.
 * 
 * @module 共通模块
 * @func 审批流程
 * @version 1.1
 * @author 朱晓文
 */
public class FlowAction extends BaseAction {

    /** The logger. */
    private Logger      logger        = Logger.getLogger(FlowAction.class);

    /** The flow service. */
    private FlowService flowService;

    /** The xmoryhm. */
    private String      xmoryhm       = "0";                                                                 // 0:姓名 1：用户名;

    /** The state. */
    private String[]    state         = {"进行", "完成", "异常"};

    /** The business state. */
    private String[]    businessState = {"提交", "审核通过", "否决", "撤销", "已签发", "已校稿", "已下发", "已审核", "已归档", "已整理"};

    /** The remind. */
    private String      remind        = "1";                                                                 // 是否提醒下一步操作者 1:提醒


    // 审批流程设置主页面 浏览
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
        ParamUtil.setCommAttributeEx(request);
        HttpSession session = request.getSession(false);
        String flowModelId = request.getParameter("flowModelId");
        if (flowModelId == null)
            flowModelId = "";
        String clear = request.getParameter("clear");
        if (clear == null)
            clear = "T";
        if (clear.equals("T")) {
            session.removeAttribute("model_Condition");
            session.removeAttribute("model_orderColumn");
            session.removeAttribute("model_orderType");
            session.removeAttribute("model_pagenum");
        }
        String condition_F = "1=1";
        String condition = "";
        if (session.getAttribute("model_Condition") != null)
            condition = (String) session.getAttribute("model_Condition");
        if (condition == null || condition.equals("")) {
            condition = " where " + condition_F;
        } else {
            condition = " where " + condition_F + condition;
        }
        if (flowModelId == null || flowModelId.equals("")) {
            condition = condition + " order by FLOWMODELID desc";
        } else {
            condition = condition + " and FLOWMODELID = '" + flowModelId + "'";
        }
        request.setAttribute("clear", clear);
        request.setAttribute("flowModelId", flowModelId);

        return mapping.findForward("list");

    }


    // 审批流程 上框架浏览
    /**
     * To top list.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toTOPList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        String condition_F = " 1=1 ";
        String condition = (String) session.getAttribute("model_Condition");
        if (condition == null) {
            condition = "";
        }
        condition = " where " + condition_F + condition;
        String MBBH = request.getParameter("MBBH");
        String MBMC = request.getParameter("MBMC");
        String ZT = request.getParameter("ZT");
        String YWLX = request.getParameter("YWLX");
        String orderid = request.getParameter("orderId");
        String ordertype = request.getParameter("orderType");
        condition = condition + transParamter(MBBH, "modelNumber");
        condition = condition + transParamter(MBMC, "modelName");
        condition = condition + transParamter(ZT, "modelState");
        condition = condition + transParamter(YWLX, "businessType");
        StringBuffer aSelSQL = new StringBuffer();
        aSelSQL.append(" select FLOWMODELID,MODELNUMBER,MODELNAME,MODELTYPE,BUSINESSTYPE,MODELSTATE,REMARK ");
        if(Consts.DBTYPE.equals("DB2")||Consts.DBTYPE.equals("MSSQL"))
        {
          aSelSQL.append(" ,row_number() over(order by ");	
        
        if(StringUtils.isNotEmpty(orderid)){
            aSelSQL.append(orderid+" "+ordertype);
        }else{
        	 aSelSQL.append("   FLOWMODELID DESC");
        }
        
        aSelSQL.append(" )rownum_  ");
        }
        aSelSQL.append(" from ");
        aSelSQL.append(Consts.SYSSCHEMA);
        aSelSQL.append(".T_SYS_FLOWMODEL ");
        aSelSQL.append(condition);
        if(!(Consts.DBTYPE.equals("DB2")||Consts.DBTYPE.equals("MSSQL")))
        {
        	aSelSQL.append(" order by FLOWMODELID DESC");
        }
        StringBuffer aCountSQL = new StringBuffer();
        aCountSQL.append(" select count(FLOWMODELID)cnt ");
        aCountSQL.append(" from ");
        aCountSQL.append(Consts.SYSSCHEMA);
        aCountSQL.append(" . T_SYS_FLOWMODEL ");
        aCountSQL.append(condition);
        Map params = new HashMap();
        params.put("SelSQL", aSelSQL.toString());
        params.put("CouSQL", aCountSQL.toString());

        //ParamUtil.setOrderField(request, params);
        int pageNo = ParamUtil.getInt(request, "pageNo", 1);
        ParamUtil.putStr2Map(request, "pageSize", params);
        IListPage page = flowService.pageQuery(params, pageNo);
        request.setAttribute("page", page);
        return mapping.findForward("toplist");

    }


    /**
     * Trans paramter.
     * 
     * @param param the param
     * @param sqlColumn the sql column
     * 
     * @return the string
     */
    private String transParamter(String param, String sqlColumn) {

        if (!StringUtil.isEmpty(param)) {
            try {
                param = URLDecoder.decode(param, "UTF-8");
                if (-1 != param.indexOf("'")) {
                    param = StringUtils.replace(param, "'", "’’");
                }
                return " AND " + sqlColumn + " LIKE '%" + param.trim() + "%' ";
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }

    }


    // 浏览权限详细信息
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
        String[] modelParaName = {"flowModelId", "modelName", "modelType", "modelState", "businessType", "modelNumber", "remark", "jgxxid", "jgmc",
                "jumpFLag"};
        String[] modelParaValue = new String[10];
        int maxlength = modelParaValue.length;
        for (int i = 0; i < maxlength; i++)
            modelParaValue[i] = "";

        String flowModelId = tranCodeP(request.getParameter("selRowId"));
        String refreshFlag = tranCodeP(request.getParameter("refreshFlag"));

        String condition = " where  T_SYS_FLOWMODEL.FLOWMODELID='" + flowModelId
                + "' and T_SYS_FLOWMODEL.FLOWMODELID=T_SYS_FLOWNODE.FLOWMODELID AND NODEOPERATIONID='flowStartId'";
        String tableName = " T_SYS_FLOWNODE , T_SYS_FLOWMODEL LEFT JOIN T_SYS_JGXX ON   T_SYS_FLOWMODEL.JGXXID=T_SYS_JGXX.JGXXID  ";
        String listName = " T_SYS_FLOWMODEL.FLOWMODELID FLOWMODELID,MODELNAME,MODELTYPE,MODELSTATE,BUSINESSTYPE,MODELNUMBER,T_SYS_FLOWMODEL.REMARK REMARK,T_SYS_FLOWMODEL.JGXXID JGXXID,JGMC,JUMPFLAG";

        StringBuffer aSelSQL = new StringBuffer();
        aSelSQL.append(" select ");
        aSelSQL.append(listName);
        aSelSQL.append(" from ");
        aSelSQL.append(tableName);
        aSelSQL.append(condition);
        Map params = new HashMap();
        params.put("SelSQL", aSelSQL.toString());
        Map entry = flowService.selcom(params);
        if (entry != null) {
            maxlength = modelParaName.length;
            for (int i = 0; i < maxlength; i++) {
                modelParaValue[i] = tranCodeN(entry.get(modelParaName[i].toUpperCase()));
            }
        }

        boolean hasNode = false;
        if (!modelParaValue[0].equals("")) {

            params.clear();
            params.put("SelSQL", "select count(*) MODELNODE from T_SYS_FLOWNODE where FLOWMODELID='" + modelParaValue[0] + "'");
            Map temp = flowService.selcom(params);

            if (temp != null) {
                hasNode = tranCodeN(entry.get("MODELNODE")).equals("2") ? false : true;
            }
        }

        String affirmJump = "";
        if (modelParaValue[9].equals("0")) {
            affirmJump = "禁止";
        } else if (modelParaValue[9].equals("2")) {
            affirmJump = "高级人员";
        } else if (modelParaValue[9].equals("3")) {
            affirmJump = "中高级人员";
        } else if (modelParaValue[9].equals("4")) {
            affirmJump = "所有人员";
        }

        request.setAttribute("flowModelId", flowModelId);
        request.setAttribute("refreshFlag", refreshFlag);
        request.setAttribute("affirmJump", affirmJump);
        request.setAttribute("rst", entry);
        return mapping.findForward("view");

    }


    // 去到新增页面
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

    	UserBean userBean = ParamUtil.getUserBean(request);
        request.setAttribute("ADDLCZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("toadd");
    }


    // 新增一个MODEL
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

        try {
            String flowModelId = flowService.txAddModule(request);
            request.setAttribute("refreshFlag", "T");
            request.setAttribute("flowModelId", flowModelId);
            return mapping.findForward("view");
        }catch(ServiceException s){
        	request.setAttribute("msg", s.getMessage());
            return mapping.findForward(FAILURE);
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("msg", "系统异常");
            return mapping.findForward(FAILURE);
        }

    }


    // 设置审批流程
    /**
     * Sets the flow.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward setFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String xmoryhm = "0"; // 0:姓名 1：用户名;
        String flowModelId = tranCodeP(request.getParameter("selRowId"));

        if (flowModelId.equals("")) {
            request.setAttribute("msg", "没有选择对应的模板！");
            return mapping.findForward(FAILURE);
        }

        String readOnly = "0";
        Map params = new HashMap();
        params.put("SelSQL", "select MODELSTATE from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where FLOWMODELID='" + flowModelId + "'");
        Map temMap = flowService.selcom(params);
        if (temMap != null) {
            if (tranCodeN(temMap.get("MODELSTATE")).equals("已生效"))
                readOnly = "1";
        }

        String folderStyle = "";
        if (readOnly.equals("1")) {
            folderStyle = " display:none ";
        }

        String condition = " where FLOWMODELID='" + flowModelId + "'";
        String listName = "FLOWNODEID,OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME";
        params.put("SelSQL", " select " + listName + " from T_SYS_FLOWNODE,T_SYS_NODEOPERATION" + condition
                + " AND T_SYS_FLOWNODE.NODEOPERATIONID=T_SYS_NODEOPERATION.NODEOPERATIONID ");
        List resList = flowService.selcomList(params);
        List retList = new ArrayList();
        int maxlength = resList.size();
        for (int i = 0; i < maxlength; i++) {
            Map resMap = (Map) resList.get(i);
            String type = tranCodeN(resMap.get("OPERATIONER"));
            String id = tranCodeN(resMap.get("OPERATIONERID"));
            String tableName = "";
            String listName1 = "";
            String conName = "";
            if (type.equals("0")) {
                // 0:用户
                tableName = "T_SYS_XTYH,T_SYS_RYXX ";
                if (xmoryhm.equals("0")) {
                    listName1 = "XM";
                    conName = "T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND XTYHID";
                } else {
                    listName1 = "YHM";
                    conName = " XTYHID";
                }
            } else if (type.equals("1")) {
                // 1：部门
                tableName = "T_SYS_BMXX ";
                listName1 = "BMMC";
                conName = "BMXXID";
            } else if (type.equals("2")) {
                // 2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "XTJSID";
            } else if (type.equals("3")) {
                // 3：工作组
                tableName = "T_SYS_GZZXX ";
                listName1 = "GZZMC";
                conName = "GZZXXID";
            }
            String name = "";
            if (type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3")) {

                params.put("SelSQL", "select " + listName1 + " from " + tableName + " where " + conName + "='" + id + "'");

                Map temMap1 = flowService.selcom(params);
                if (temMap1 != null) {
                    name = tranCodeN(temMap1.get(listName1.toUpperCase()));
                }
            }

            Map retMap = new HashMap();
            retMap.put("id", tranCodeN(resMap.get("FLOWNODEID")));
            retMap.put("type", type);
            retMap.put("name", name);

            retMap.put("iX", tranCodeN(resMap.get("NODEX")));
            retMap.put("iY", tranCodeN(resMap.get("NODEY")));
            retMap.put("operaName", tranCodeN(resMap.get("OPERATIONNAME")));
            retList.add(retMap);

        }

        params.put("SelSQL", "select STARTNODEID,ENDNODEID  from T_SYS_NODEPATH " + condition);
        List resList1 = flowService.selcomList(params);
        List retList1 = new ArrayList();
        maxlength = resList1.size();
        for (int i = 0; i < maxlength; i++) {
            Map resMap = (Map) resList1.get(i);
            String XY = tranCodeN(resMap.get("STARTNODEID")) + "-" + tranCodeN(resMap.get("ENDNODEID"));
            Map retMap = new HashMap();
            retMap.put("path", XY);
            retList1.add(retMap);

        }

        request.setAttribute("retList", retList);
        request.setAttribute("retList1", retList1);
        request.setAttribute("flowModelId", flowModelId);
        request.setAttribute("folderStyle", folderStyle);
        request.setAttribute("readOnly", readOnly);
        return mapping.findForward("setflow");
    }


    // MODEL 生效
    /**
     * Validation.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward validation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();

        String flowModelId = ParamUtil.get(request, "flowModelId");
        try {
            Map params = new HashMap();
            params.clear();
            params.put("UpdSQL", "update T_SYS_FLOWMODEL set MODELSTATE='\u5DF2\u751F\u6548' where FLOWMODELID='" + flowModelId + "'");
            flowService.txUpdcom(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "\u6A21\u677F\u751F\u6548\u51FA\u9519\uFF01");
            return mapping.findForward(FAILURE);

        }

        request.setAttribute("refreshFlag", "T");
        request.setAttribute("flowModelId", flowModelId);
        //return mapping.findForward("view");
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        return mapping.findForward("list");
    }


    // MODEL 失效
    /**
     * Invalidation.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward invalidation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String flowModelId = ParamUtil.get(request, "flowModelId");
        try {
            Map params = new HashMap();
            params.clear();
            params.put("UpdSQL", "update T_SYS_FLOWMODEL set MODELSTATE='\u672A\u751F\u6548' where FLOWMODELID='" + flowModelId + "'");
            flowService.txUpdcom(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "\u6A21\u677F\u5931\u6548\u51FA\u9519\uFF01");
            return mapping.findForward(FAILURE);

        }

        request.setAttribute("refreshFlag", "T");
        request.setAttribute("flowModelId", flowModelId);
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        return mapping.findForward("view");
    }


    // 删除一条记录
    /**
     * Del flow.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    /**
    public ActionForward delFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String flowModelId = ParamUtil.get(request, "flowModelId");
        if (flowModelId == null || flowModelId.equals("")) {
            request.setAttribute("msg", "选中的模板不存在！");
            return mapping.findForward(FAILURE);
        }
        try {
            Map params = new HashMap();
            params.put("FLOWMODELID", flowModelId);
            flowService.txDelFlow(params);
            request.setAttribute("msg", "删除成功！");
            flowService.clearCaches(flowModelId);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "删除出错！");
            return mapping.findForward(FAILURE);

        }
        return mapping.findForward("flowsuccess");
    }
    **/
    /**
     * ReWrite by zhuxw
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public void delFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String flowModelId = ParamUtil.get(request, "flowModelId");
        if (flowModelId == null || flowModelId.equals("")) {
            request.setAttribute("msg", "选中的模板不存在！");
            jsonResult = jsonResult(false, "删除失败:模板ID为空！");
        }else
        {
        	try {
                Map params = new HashMap();
                params.put("FLOWMODELID", flowModelId);
                flowService.txDelFlow(params);
                jsonResult = jsonResult(true, "删除成功！");
                flowService.clearCaches(flowModelId);
            } catch (Exception ex) {
                ex.printStackTrace();
                jsonResult = jsonResult(false, "删除失败:"+ex.getMessage());
            }	
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

    /**
     * To update.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String flowModelId = ParamUtil.get(request, "flowModelId");

        if (flowModelId == null || flowModelId.equals("")) {
            request.setAttribute("msg", "选中的记录不存在！");
            return mapping.findForward(FAILURE);
        }
        String listName = " T_SYS_FLOWMODEL.FLOWMODELID,MODELNUMBER,MODELNAME,MODELTYPE,BUSINESSTYPE,MODELSTATE,T_SYS_FLOWMODEL.REMARK REMARK,T_SYS_FLOWMODEL.JGXXID JGXXID,JGMC,JUMPFLAG ";
        String condition = " where  T_SYS_FLOWMODEL.FLOWMODELID='" + flowModelId
                + "' and T_SYS_FLOWMODEL.FLOWMODELID=T_SYS_FLOWNODE.FLOWMODELID AND NODEOPERATIONID='flowStartId'";
        String sql = " select " + listName + " from  T_SYS_FLOWNODE , T_SYS_FLOWMODEL LEFT JOIN T_SYS_JGXX ON T_SYS_FLOWMODEL.JGXXID=T_SYS_JGXX.JGXXID " + condition;
        Map params = new HashMap();
        params.put("SelSQL", sql);
        Map entry = flowService.selcom(params);
        request.setAttribute("rst", entry);
        UserBean userBean = ParamUtil.getUserBean(request);
        request.setAttribute("ADDLCZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("modi");

    }


    /**
     * Update.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String FLOWMODELID = ParamUtil.get(request, "FLOWMODELID");
        //if (FLOWMODELID.equals("") || FLOWMODELID == null) {//modified by wang yehua
        if (StringUtil.isEmpty(FLOWMODELID)) {
            request.setAttribute("msg", "模板记录不存在！");
            return mapping.findForward(FAILURE);
        }
        Map params = new HashMap();
        ParamUtil.putStr2Map(request, "FLOWMODELID", params);
        ParamUtil.putStr2Map(request, "MODELNUMBER", params);
        ParamUtil.putStr2Map(request, "MODELNAME", params);
        ParamUtil.putStr2Map(request, "BUSINESSTYPE", params);
        ParamUtil.putStr2Map(request, "AFFIRMJUMP", params);
        ParamUtil.putStr2Map(request, "REMARK", params);
        ParamUtil.putStr2Map(request, "MODELTYPE", params);
        ParamUtil.putStr2Map(request, "JGXXID", params);
        try {
        	boolean flag=flowService.checkInfo(params);
        	if(flag){
        		throw new ServiceException("存在重复数据，不能修改！");
        	}
            flowService.txUpdatemodById(params);
            request.setAttribute("msg", "修改成功！");
        }catch(ServiceException s){
        	request.setAttribute("msg", s.getMessage());
            return mapping.findForward(FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "修改出错！");
            return mapping.findForward(FAILURE);

        }
        flowService.clearCaches(FLOWMODELID);
        request.setAttribute("refreshFlag", "T");
        request.setAttribute("flowModelId", FLOWMODELID);
        return mapping.findForward("view");
    }


    // 增加流程节点
    /**
     * To addmodnode.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toADDMODNODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        // 检查session有效性
        // if (!QXComm.checkSession(session, response)) return;
        // String MKCode = (String)session.getAttribute("MKCode");
        String MKCode = "00202";
        // 检查是否有该模块权限
        // if (!QXComm.checkMKQX(session, MKCode+"02", response)) return;

        String xmoryhm = "0";// 0:姓名 1：用户名;
        String modelId = request.getParameter("modelId");
        String operType = request.getParameter("OperType");
        String businessID = request.getParameter("BusinessID");
        String agentStyle = "disabled=true";
        String allStyle = "";
        if (operType.equals("0")) {
            agentStyle = "";
            allStyle = "disabled=true";
        }
        request.setAttribute("xmoryhm", "0");
        request.setAttribute("modelId", modelId);
        request.setAttribute("operType", operType);
        request.setAttribute("businessID", businessID);
        request.setAttribute("agentStyle", agentStyle);
        request.setAttribute("allStyle", allStyle);
        return mapping.findForward("addmodnode");

    }


    /**
     * Adds the modnodeup.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward addMODNODEUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String flowNodeId = TimeComm.getTimeStamp("");
        String nodex = "";
        String nodey = "";
        String nodeOperationId = tranCodeP(request.getParameter("nodeOperationId"));
        String nodeInsertName[] = {"flowNodeId", "operationerId", "operationer", "flowModelId", "nodeX", "nodeY", "backFlag", "jumpFlag",
                "expandFlag", "editFlag", "cancleFlag", "agentFlag", "delayFlag", "delayTime", "allFlag", "nodeOperationId"

        };
        String userParameter[] = {"OperType", "OperID", "BackFlag", "JumpFlag", "ExpandFlag", "EditFlag", "CancleFlag", "AgentFlag", "DelayFlag",
                "DelayDay", "AllFlag", "event", "IX", "IY", "ProNodeID", "ProcessName", "ProcessID", "ProcessClass"};
        String parameterValue[] = prepareArray(request, userParameter, 0);
        // 跳转页面
        String xmoryhm = "0"; // 0:姓名 1：用户名;
        String flowModelId = parameterValue[16];

        if (flowModelId.equals("")) {
            request.setAttribute("msg", "没有选择对应的模板！");
            return mapping.findForward(FAILURE);
        }

        try {
            String tempsql = " select NODEX,NODEY from  T_SYS_FLOWNODE where  FLOWNODEID='" + parameterValue[14] + "'";
            Map params = new HashMap();
            params.put("SelSQL", tempsql);
            Map resMap = flowService.selcom(params);
            if (resMap != null) {
                nodex = resMap.get("NODEX").toString();
                nodey = resMap.get("NODEY").toString();
            }

        } catch (Exception ex) {
            request.setAttribute("msg", "\u9009\u53D6\u8282\u70B9\u5750\u6807\u51FA\u9519\uFF01");
            return mapping.findForward(FAILURE);
        }
        String nodeInsertValue[] = {flowNodeId, parameterValue[1], parameterValue[0], parameterValue[16], nodex, nodey, parameterValue[2],
                parameterValue[3], parameterValue[4], parameterValue[5], parameterValue[6], parameterValue[7], parameterValue[8], parameterValue[9],
                parameterValue[10], nodeOperationId};
        try {
            flowService.txAddModuleNodeUp(flowNodeId, nodey, nodeInsertName, parameterValue, nodeInsertValue);

        } catch (Exception ex) {
            request.setAttribute("msg", "\u63D2\u5165\u8282\u70B9\u51FA\u9519\uFF01");
            return mapping.findForward(FAILURE);
        }

        String readOnly = "0";
        Map params = new HashMap();
        params.put("SelSQL", "select MODELSTATE from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where FLOWMODELID='" + flowModelId + "'");
        Map temMap = flowService.selcom(params);
        if (temMap != null) {
            if (tranCodeN(temMap.get("MODELSTATE")).equals("已生效"))
                readOnly = "1";
        }

        String folderStyle = "";
        if (readOnly.equals("1")) {
            folderStyle = " display:none ";
        }

        String condition = " where FLOWMODELID='" + flowModelId + "'";
        String listName = "FLOWNODEID,OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME";
        params.put("SelSQL", " select " + listName + " from T_SYS_FLOWNODE,T_SYS_NODEOPERATION" + condition
                + " and T_SYS_FLOWNODE.NODEOPERATIONID=T_SYS_NODEOPERATION.NODEOPERATIONID ");
        List resList = flowService.selcomList(params);
        List retList = new ArrayList();
        int maxlength = resList.size();
        for (int i = 0; i < maxlength; i++) {
            Map resMap = (Map) resList.get(i);
            String type = tranCodeN(resMap.get("OPERATIONER"));
            String id = tranCodeN(resMap.get("OPERATIONERID"));
            String tableName = "";
            String listName1 = "";
            String conName = "";
            if (type.equals("0")) {
                // 0:用户
                tableName = "T_SYS_XTYH,T_SYS_RYXX";
                if (xmoryhm.equals("0")) {
                    listName1 = "XM";
                    conName = "T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND XTYHID";
                } else {
                    listName1 = "YHM";
                    conName = " XTYHID";
                }
            } else if (type.equals("1")) {
                // 1：部门
                tableName = "T_SYS_BMXX";
                listName1 = "BMMC";
                conName = "BMXXID";
            } else if (type.equals("2")) {
                // 2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "XTJSID";
            } else if (type.equals("3")) {
                // 3：工作组
                tableName = "T_SYS_GZZXX";
                listName1 = "GZZMC";
                conName = "GZZXXID";
            }
            String name = "";
            if (type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3")) {

                params.put("SelSQL", "select " + listName1 + " from " + tableName + " where " + conName + "='" + id + "'");
                Map temMap1 = flowService.selcom(params);
                if (temMap1 != null) {
                    name = tranCodeN(temMap1.get(listName1.toUpperCase()));
                }
            }

            Map retMap = new HashMap();
            retMap.put("id", tranCodeN(resMap.get("FLOWNODEID")));
            retMap.put("type", type);
            retMap.put("name", name);
            retMap.put("iX", tranCodeN(resMap.get("NODEX")));
            retMap.put("iY", tranCodeN(resMap.get("NODEY")));
            retMap.put("operaName", tranCodeN(resMap.get("OPERATIONNAME")));

            retList.add(retMap);

        }
        params.put("SelSQL", "select STARTNODEID,ENDNODEID  from T_SYS_NODEPATH " + condition);
        List resList1 = flowService.selcomList(params);
        List retList1 = new ArrayList();
        maxlength = resList1.size();
        for (int i = 0; i < maxlength; i++) {
            Map resMap = (Map) resList1.get(i);
            String XY = tranCodeN(resMap.get("STARTNODEID")) + "-" + tranCodeN(resMap.get("ENDNODEID"));
            Map retMap = new HashMap();
            retMap.put("path", XY);
            retList1.add(retMap);

        }

        request.setAttribute("retList", retList);
        request.setAttribute("retList1", retList1);
        request.setAttribute("flowModelId", flowModelId);
        request.setAttribute("folderStyle", folderStyle);
        request.setAttribute("readOnly", readOnly);
        return mapping.findForward("setflow");

    }


    /**
     * Adds the modnodedown.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward addMODNODEDOWN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            flowService.txAddModuleDown(request);
        } catch (ServiceException e) {
            if ("441".equals(e.getMessage())) {
                request.setAttribute("msg", "\u9009\u53D6\u8282\u70B9\u5750\u6807\u51FA\u9519\uFF01");
                return mapping.findForward(Consts.FAILURE);
            } else if ("442".equals(e.getMessage())) {
                request.setAttribute("msg", "\u63D2\u5165\u8282\u70B9\u51FA\u9519\uFF01");
                return mapping.findForward(Consts.FAILURE);
            } else if ("443".equals(e.getMessage())) {
                request.setAttribute("msg", "没有选择对应的模板！");
                return mapping.findForward(Consts.FAILURE);
            } else {
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }
        return mapping.findForward("setflow");
    }


    // 增加同级操作者
    /**
     * Adds the modnodeleft.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward addMODNODELEFT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward("setflow");

    }


    //选择回退节点
    /**
     * Tosel node.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toselNode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String flag = tranCodeN(request.getParameter("flag"));
        String flowInstanceId = tranCodeN(request.getParameter("flowInstanceId"));
        String instanceNodeId = tranCodeN(request.getParameter("instanceNodeId"));
        String flowEvent = tranCodeN(request.getParameter("flowEvent"));
        String endNodeOption = "";
        if (flowEvent.equals("affirm")) {
            endNodeOption = " and operationer<>'-2'";
        }
        String sql = "";
        if (DBTYPE.equals("DB2")) {
            if (flag.equals("2")) {
                //查找当前实例节点的前导节点
                sql = "with pathTree(endNodeId, startNodeId) as ( " + "select endNodeId, startNodeId from (select endNodeId,startNodeId "
                        + "from T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1 " + "where endNodeId='" + instanceNodeId
                        + "' union all select " + "T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId "
                        + "from T_SYS_INSTANCENODEPath,pathTree where T_SYS_INSTANCENODEPath.endNodeId=pathTree.startNodeId) "
                        + "select distinct(startNodeId) nodeId from pathTree ";
            } else if (flag.equals("3")) {
                //查找当前节点的后续节点
                sql = "with pathTree(endNodeId, startNodeId) as " + "( select endNodeId, startNodeId from "
                        + "(select endNodeId,startNodeId from T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1"
                        + " where startNodeId='" + instanceNodeId + "' "
                        + "union all select T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from T_SYS_INSTANCENODEPath,pathTree "
                        + "where T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId) nodeId  from pathTree ";
            }
        } else if (DBTYPE.equals("ORACLE")) {
            if (flag.equals("2")) {
                sql = "select distinct(startNodeId) nodeId from T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId
                        + "' and endNodeId='" + instanceNodeId + "' connect by prior startNodeId=endNodeId";
            } else if (flag.equals("3")) {
                sql = "select distinct(endNodeId) nodeId from T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId
                        + "' and startNodeId='" + instanceNodeId + "' connect by prior endNodeId=startNodeId";
            }
        }else if (DBTYPE.equals("MSSQL")){
        	 if (flag.equals("2")) {
                 //查找当前实例节点的前导节点
                 sql = "with PATHTREE(ENDNODEID, STARTNODEID) as ( " + "select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID "
                         + "from T_SYS_INSTANCENODEPATH where FLOWINSTANCEID='" + flowInstanceId + "' ) resu1 " + "where ENDNODEID='" + instanceNodeId
                         + "' union all select " + "T_SYS_INSTANCENODEPATH.ENDNODEID,T_SYS_INSTANCENODEPATH.STARTNODEID "
                         + "from T_SYS_INSTANCENODEPATH,PATHTREE where T_SYS_INSTANCENODEPATH.ENDNODEID=PATHTREE.STARTNODEID) "
                         + "select distinct(STARTNODEID) NODEID from PATHTREE ";
             } else if (flag.equals("3")) {
                 //查找当前节点的后续节点
                 sql = "with PATHTREE(ENDNODEID, STARTNODEID) as " + "( select ENDNODEID, STARTNODEID from "
                         + "(select ENDNODEID,STARTNODEID from T_SYS_INSTANCENODEPATH where FLOWINSTANCEID='" + flowInstanceId + "' ) resu1"
                         + " where STARTNODEID='" + instanceNodeId + "' "
                         + "union all select T_SYS_INSTANCENODEPATH.ENDNODEID,T_SYS_INSTANCENODEPATH.STARTNODEID FROM T_SYS_INSTANCENODEPATH,PATHTREE "
                         + "where T_SYS_INSTANCENODEPATH.STARTNODEID=PATHTREE.ENDNODEID) select distinct(ENDNODEID) NODEID  from PATHTREE ";
             }
        }

        StringBuffer node = new StringBuffer("('@#$'");
        Map params = new HashMap();
        params.put("SelSQL", sql);
        List resList = flowService.selcomList(params);
        int maxlength = resList.size();
        for (int i = 0; i < maxlength; i++) {
            Map temMap = (Map) resList.get(i);
            node.append(",'");
            node.append(tranCodeN(temMap.get("NODEID")));
            node.append("'");

        }
        node.append(")");
        sql = " select distinct(T_SYS_INSTANCENODE.INSTANCENODEID),OPERATIONERID,OPERATIONER,OPERATIONNAME,NODEY from "
                + "T_SYS_INSTANCENODE,T_SYS_INSTANCENODEOPERATION WHERE OPERATIONER<>'-1' " + endNodeOption
                + " and T_SYS_INSTANCENODE.INSTANCENODEID=T_SYS_INSTANCENODEOPERATION.INSTANCENODEID" + " and T_SYS_INSTANCENODE.INSTANCENODEID in " + node
                + " order by NODEY ";
        params.put("SelSQL", sql);
        resList = flowService.selcomList(params);
        maxlength = resList.size();
        for (int i = 0; i < maxlength; i++) {
            Map temMap = (Map) resList.get(i);
            String type = tranCodeN(temMap.get("OPERATIONER"));
            String operaType = "";
            String tableName = "";
            String conName = "";
            String listName1 = "";
            if (type.equals("0")) {
                //0:用户
                tableName = "T_SYS_XTYH";
                listName1 = "YHM";
                conName = "XTYHID";
                operaType = "用户";
            } else if (type.equals("1")) {
                //1：部门
                tableName = "T_SYS_BMXX";
                listName1 = "BMMC";
                conName = "BMXXID";
                operaType = "部门";
            } else if (type.equals("2")) {
                //2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "XTJSID";
                operaType = "角色";
            } else if (type.equals("3")) {
                //3：工作组
                tableName = "T_SYS_GZZXX";
                listName1 = "GZZMC";
                conName = "GZZXXID";
                operaType = "工作组";
            }
            String name = "";
            String temsql = " select " + listName1 + "  NAME from " + tableName + " where " + conName + "='" + tranCodeN(temMap.get("OPERATIONERID"))
                    + "'";
            params.put("SelSQL", temsql);
            Map resMap = flowService.selcom(params);
            if (resMap != null) {
                name = tranCodeN(resMap.get("NAME"));
            }
            temMap.put("NAME", name);
            temMap.put("TYPE", type);
            temMap.put("OPERATYPE", operaType);
        }
        request.setAttribute("resList", resList);
        return mapping.findForward("selnode");

    }


    // 删除节点操作
    /**
     * Del modnode.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward delMODNODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            flowService.txDelMODNODE(request);
        } catch (ServiceException e) {
            if ("441".equals(e.getMessage())) {
                request.setAttribute("msg", "\u9009\u53D6\u8282\u70B9\u5750\u6807\u51FA\u9519\uFF01");
                return mapping.findForward(FAILURE);
            } else if ("442".equals(e.getMessage())) {
                request.setAttribute("msg", "\u53D6\u5F97\u6700\u5927x\u3001y\u51FA\u9519\uFF01");
                return mapping.findForward(FAILURE);
            } else if ("443".equals(e.getMessage())) {
                request.setAttribute("msg", "\u5220\u9664\u8282\u70B9\u51FA\u9519\uFF01");
                return mapping.findForward(FAILURE);
            } else if ("444".equals(e.getMessage())) {
                request.setAttribute("msg", "\u5F97\u5230\u8FDE\u63A5\u9519\uFF01");
                return mapping.findForward(FAILURE);
            } else if ("445".equals(e.getMessage())) {
                request.setAttribute("msg", "没有选择对应的模板！");
                return mapping.findForward(FAILURE);
            } else {
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }

        return mapping.findForward("setflow");

    }


    /**
     * To edit node.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditNODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        // 检查session有效性
        // if (!QXUtil.checkSession(request, response)) return;
        // String MKCode = "XT00401";
        String flowNodeId = ParamUtil.get(request, "flowNodeId");
        String tempsql = "  select FLOWNODEID,FLOWMODELID,BACKFLAG,JUMPFLAG,EXPANDFLAG,EDITFLAG,CANCLEFLAG,AGENTFLAG,DELAYFLAG,DELAYTIME,ALLFLAG,REMARK,NODEOPERATIONID,OPERATIONER,OPERATIONERID  FROM T_SYS_FLOWNODE "
                + " where FLOWNODEID='" + flowNodeId + "'";
        Map params = new HashMap();
        params.put("SelSQL", tempsql);
        Map temMap = flowService.selcom(params);

        String operationer = temMap.get("OPERATIONER").toString();
        String flowModelId = temMap.get("FLOWMODELID").toString();

        String operationerId = temMap.get("OPERATIONERID").toString();

        String tableName = "";
        String listName1 = "";
        String conName = "";
        if (operationer.equals("0")) {
            if (xmoryhm.equals("0")) {
                tableName = "T_SYS_XTYH,T_SYS_RYXX";
                listName1 = "XM";
                conName = "T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND XTYHID";
            } else {
                tableName = "T_SYS_XTYH";
                listName1 = "YHM";
                conName = "XTYHID";
            }
        } else if (operationer.equals("1")) {
            tableName = "T_SYS_BMXX";
            listName1 = "BMMC";
            conName = "BMXXID";
        } else if (operationer.equals("2")) {
            tableName = "T_SYS_XTJS";
            listName1 = "JSMC";
            conName = "XTJSID";
        } else if (operationer.equals("3")) {
            tableName = "T_SYS_GZZXX";
            listName1 = "GZZMC";
            conName = "GZZXXID";
        }

        boolean readOnly = false;
        tempsql = "select MODELSTATE from T_SYS_FLOWMODEL where FLOWMODELID='" + flowModelId + "'";
        params.put("SelSQL", tempsql);
        Map resMap = flowService.selcom(params);

        if (resMap != null) {
            if (resMap.get("MODELSTATE").toString().equals("已生效"))
                readOnly = true;
        }

        String btnStyle = "";
        if (readOnly) {
            btnStyle = " display:none ";
        }

        String name1 = "";
        if (operationer.equals("0") || operationer.equals("1") || operationer.equals("2") || operationer.equals("3")) {
            tempsql = " select " + listName1 + " from " + tableName + " where " + conName + "='" + operationerId + "'";

            params.put("SelSQL", tempsql);
            Map resMap1 = flowService.selcom(params);
            if (resMap1 != null) {
                name1 = resMap1.get(listName1.toUpperCase()).toString();
            }
        }
        request.setAttribute("operType", operationer);
        request.setAttribute("name1", name1);
        request.setAttribute("xmoryhm", "0");
        request.setAttribute("btnStyle", btnStyle);
        request.setAttribute("rst", temMap);
        return mapping.findForward("nodeedit");

    }


    /**
     * To savenode.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toSAVENODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            flowService.txSaveNodes(request);
        } catch (ServiceException e) {
            if ("441".equals(e.getMessage())) {
                request.setAttribute("msg", "\u66F4\u65B0\u6A21\u677F\u51FA\u9519\uFF01");
                return mapping.findForward(FAILURE);
            } else if ("442".equals(e.getMessage())) {
                request.setAttribute("msg", "没有选择对应的模板！");
                return mapping.findForward(FAILURE);
            } else {
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }
        return mapping.findForward("setflow");
    }


    /**
     * To in stance.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toInStance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String tableName = tranCodeN(request.getParameter("tableName"));

        String fieldName = tranCodeN(request.getParameter("fieldName"));
        String id = tranCodeN(request.getParameter("id"));
        String flowInstanceId = tranCodeN(request.getParameter("flowInstanceId"));
        String refresh = tranCodeN(request.getParameter("refresh"));
        String hasOfficial = tranCodeN(request.getParameter("hasOfficial"));

        String flowInterfaceName = tranCodeN(request.getParameter("flowInterfaceName"));
        String ztbgFieldName = tranCodeN(request.getParameter("ztbgFieldName"));
        String businessType = tranCodeN(request.getParameter("businessType"));
        String operateFlag = tranCodeN(request.getParameter("operateFlag"));
        String sourceURI = tranCodeN(request.getParameter("sourceURI"));

        request.setAttribute("flowInterfaceName", flowInterfaceName);
        request.setAttribute("ztbgFieldName", ztbgFieldName);
        request.setAttribute("businessType", businessType);
        request.setAttribute("operateFlag", operateFlag);
        request.setAttribute("sourceURI", sourceURI);

        request.setAttribute("tableName", tableName);
        request.setAttribute("fieldName", fieldName);
        request.setAttribute("id", id);
        request.setAttribute("flowInstanceId", flowInstanceId);
        request.setAttribute("refresh", refresh);
        request.setAttribute("hasOfficial", hasOfficial);
        return mapping.findForward("instance");

    }


    /**
     * To history.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String tableName = tranCodeN(request.getParameter("tableName"));

        String fieldName = tranCodeN(request.getParameter("fieldName"));
        String id = tranCodeN(request.getParameter("id"));
        String businessType = tranCodeN(request.getParameter("businessType"));

        request.setAttribute("tableName", tableName);
        request.setAttribute("fieldName", fieldName);
        request.setAttribute("id", id);
        request.setAttribute("businessType", businessType);
        return mapping.findForward("history");
    }


    /**
     * To flowshow.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFlowshow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        /**
         * 原始方法
        	String correlation = tranCodeN(request.getParameter("tableName"));
        	String correlationId = tranCodeN(request.getParameter("id"));
        	String primaryKey = tranCodeN(request.getParameter("fieldName"));
        	List resList = flowService.getFlowInfo(correlation, correlationId,
        			primaryKey);
        	request.setAttribute("resList", resList);
        	return mapping.findForward("flowshow");
        */
        String correlationId = tranCodeN(request.getParameter("id"));
        List resList = flowService.getFlowInfos(correlationId);
        request.setAttribute("resList", resList);
        return mapping.findForward("flowshow");
    }


    /**
     * To straight.
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
    public ActionForward toStraight(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(false);
        String flowInstanceId = tranCodeP(request.getParameter("flowInstanceId"));
        String tableName = tranCodeP(request.getParameter("tableName"));
        String id = tranCodeP(request.getParameter("id"));
        String instanceNodeId = tranCodeP(request.getParameter("instanceNodeId"));
        ProcessNode proNode;
        if (!flowInstanceId.equals("")) {
            proNode = flowService.getProcessNode(session, flowInstanceId, 0);
        } else {
            proNode = flowService.getProcessNode(session, tableName, id);
        }
        if (proNode == null) {
            request.setAttribute("msg", "可能原因：1.未提交数据到审批流程！2.你已经提交过审批意见！请使用流程跟踪查验！");

            request.setAttribute("flowInstanceId", flowInstanceId);
            request.setAttribute("refresh", "T");
            request.setAttribute("flowNext", "N");
            return mapping.findForward("instance");
        }
        flowInstanceId = proNode.getFlowInstanceId();
        instanceNodeId = proNode.getInstanceNodeId();

        String type = proNode.getOperationer();
        String operator = proNode.getOperationerId();
        boolean canAuditing = false;
        UserBean user = (UserBean) session.getAttribute("UserBean");
        if (type.equals("0")) {
            // 0:用户
            if (operator.equals(user.getXTYHID()))
                canAuditing = true;
        } else if (type.equals("1")) {
            // 1：部门
            if (operator.equals(user.getBMXXID()))
                canAuditing = true;
        } else if (type.equals("2")) {
            // 2：角色
            if (user.getXTJSIDS().indexOf(operator) != -1)
                canAuditing = true;
        } else if (type.equals("3")) {
            // 3：工作组
            if (user.getGZZXXIDS().indexOf(operator) != -1)
                canAuditing = true;
        }

        if (proNode.getAllFlag().equals("1")) {
            try {
                canAuditing = flowService.isDealUser(proNode, user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String cancleDis = "disabled=true";
        String backDis = "disabled=true";
        String jumpDis = "disabled=true";
        String expandstyle = "style='display:none'";
        if (proNode.getCancleFlag().equals("1")) {
            cancleDis = "";
        }
        if (proNode.getBackFlag().equals("1")) {
            backDis = "";
        }
        if (proNode.getJumpFlag().equals("1")) {
            jumpDis = "";
        }
        if (proNode.getExpandFlag().equals("1")) {
            expandstyle = "";
        }
        String operName = proNode.getOperationName();

        String flowInterfaceName = tranCodeN(request.getParameter("flowInterfaceName"));
        String ztbgFieldName = tranCodeN(request.getParameter("ztbgFieldName"));
        String businessType = tranCodeN(request.getParameter("businessType"));
        String operateFlag = tranCodeN(request.getParameter("operateFlag"));
        String sourceURI = tranCodeN(request.getParameter("sourceURI"));

        request.setAttribute("flowInterfaceName", flowInterfaceName);
        request.setAttribute("ztbgFieldName", ztbgFieldName);
        request.setAttribute("businessType", businessType);
        request.setAttribute("operateFlag", operateFlag);
        request.setAttribute("sourceURI", sourceURI);

        request.setAttribute("flowInstanceId", flowInstanceId);
        request.setAttribute("operName", operName);
        request.setAttribute("instanceNodeId", instanceNodeId);
        request.setAttribute("canAuditing", canAuditing);
        request.setAttribute("cancleDis", cancleDis);
        request.setAttribute("backDis", backDis);
        request.setAttribute("jumpDis", jumpDis);
        request.setAttribute("expandstyle", expandstyle);
        return mapping.findForward("straight");

    }


    // 处理单据提交
    /**
     * To affirm.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toAffirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        // 0 1 2 3 4 5 6 7 8 9
    	String[] paraName = {"businessType", "tableName", "fieldName", "id", "operateFlag", "sourceURI", "flowInterfaceName", "ztbgFieldName",
        		"stateName"};
        String[] paraValue = this.prepareArray(request, paraName, 0);
        HttpSession session = request.getSession(false);
        for (int i = 0; i < 6; i++) {
            if (paraValue[i] == null || paraValue[i].equals("")) {
                request.setAttribute("msg", "审批的参数设置不完整!");
                return mapping.findForward(FAILURE);
            }

        }
        FlowObject flowObjectInS = new FlowObject();
        flowObjectInS.setBusinessType(paraValue[0]);
        flowObjectInS.setTableName(paraValue[1]);
        flowObjectInS.setFieldName(paraValue[2]);
        flowObjectInS.setId(paraValue[3]);
        flowObjectInS.setFlowInterfaceName(paraValue[6]);
        flowObjectInS.setZtbgFieldName(paraValue[7]);

        if (!paraValue[8].equals("")) {
            flowObjectInS.setStateName(paraValue[8]);
        }
        try {
            String rst = flowService.txToAffirm(request, paraValue, session, flowObjectInS, flowService, remind);
            if ("affirmCanJump".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/affirm_jump.jsp");
            } else if ("userAndAgent".equals(rst)) {
                return mapping.findForward("jsp/admin/flow/userAndAgent.jsp");
            } else if ("selOperators".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/selOperators.jsp");
            }
        } catch (ServiceException e) {
            if ("441".equals(e.getMessage())) {
                String faultInfo = "提交出错，请联系管理员!";
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                request.setAttribute("msg", faultInfo);
                return mapping.findForward(FAILURE);
            } else if ("442".equals(e.getMessage())) {
                String faultInfo = "提交出错，请联系管理员!";
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                request.setAttribute("msg", faultInfo);
                return mapping.findForward(FAILURE);
            } else {
          	  	  request.setAttribute("msg", e.toString());
            	  if (request.getAttribute("faultInfo") != null) {
            		  request.setAttribute("msg",request.getAttribute("faultInfo") );
                  }
                return mapping.findForward(Consts.FAILURE);
            }
        }
        //add by zhuxw 2012.3.21
        ParamCover aParamCover = new ParamCover(request);
        aParamCover.set_backUrl(StringUtil.utf8Decoder(paraValue[5]));
        request.setAttribute(PARAM_COVER, aParamCover);

        return mapping.findForward(SUCCESS);
    }


    /**
     * Auditing.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward Auditing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String rst = flowService.txAuditing(request, flowService, remind);
            if ("instance".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/instance.jsp");
            } else if ("userAndAgent".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/userAndAgent.jsp");
            } else if ("selOperators".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/selOperators.jsp");
            }
        } catch (Exception e) {
            if ("441".equals(e.getMessage())) {
                return mapping.findForward(FAILURE);
            } else {
                logger.error(e);
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }
        return mapping.findForward("instance");

    }


    // 撤销单据处理
    /**
     * To repeal.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toRepeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String rst = flowService.txToRepeal(request, remind, flowService);
        } catch (Exception e) {
            if ("441".equals(e.getMessage())) {
                return mapping.findForward(FAILURE);
            } else {
                logger.error(e);
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }

        // 提醒节点处理者
        // if (proOpera != null)
        // flowRemind(proOpera, "工作提示", proOpera.get(4).toString()+"已撤销！");

        request.setAttribute("msg", "撤销成功！");
        ParamCover aParamCover = new ParamCover(request);
        String backUrl = request.getParameter("sourceURI");
        aParamCover.set_backUrl(StringUtil.utf8Decoder(backUrl));
        request.setAttribute(PARAM_COVER, aParamCover);
        request.setAttribute("refresh", "T");
        return mapping.findForward(SUCCESS);

    }


    /**
     * Event handle.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward eventHandle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String rst = flowService.txEventHandle(request, flowService, remind);
            if ("affirm_jump".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/affirm_jump.jsp");
            } else if ("userAndAgent".equals(rst)) {
                return mapping.findForward("jsp/admin/flow/userAndAgent.jsp");
            } else if ("selOperators".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/selOperators.jsp");
            } else if ("instance".equals(rst)) {
                return mapping.findForward("pages/sys/spflow/instance.jsp");
            } else if ("sourceURI".equals(rst)) {
                String sourceURI = tranCodeP(request.getParameter("sourceURI"));
                return mapping.findForward(sourceURI);
            }
        } catch (Exception e) {
            if ("441".equals(e.getMessage())) {
                return mapping.findForward(FAILURE);
            } else {
                logger.error(e);
                request.setAttribute("msg", "系统异常");
                return mapping.findForward(Consts.FAILURE);
            }
        }
        return null;
    }


    // 准备从request对象获取参数
    /**
     * Prepare array.
     * 
     * @param request the request
     * @param arg_name the arg_name
     * @param arg_type the arg_type
     * @param start_pos the start_pos
     * 
     * @return the string[]
     */
    public String[] prepareArray(HttpServletRequest request, String[] arg_name, String[] arg_type, int start_pos) {

        if (arg_name == null || arg_type == null) {
            return null;
        }
        if (arg_name.length != arg_type.length) {
            return null;
        }
        int i = 0;
        String[] arg_value = new String[arg_name.length];
        for (i = start_pos; i < arg_name.length; i++) {
            if (arg_type[i] != null && arg_type[i].equals("")) {
                arg_value[i] = tranCodeP(request.getParameter(arg_name[i]));
            }
            // 如果其他特殊类型
            // ..
            // end.
        }
        return arg_value;
    }


    // 根据制定的开始位置获取request中的参数
    /**
     * Prepare array.
     * 
     * @param request the request
     * @param arg_name the arg_name
     * @param start_pos the start_pos
     * 
     * @return the string[]
     */
    public String[] prepareArray(HttpServletRequest request, String[] arg_name, int start_pos) {

        if (arg_name == null) {
            return null;
        }

        int i = 0;
        String[] arg_value = new String[arg_name.length];
        for (i = start_pos; i < arg_name.length; i++) {
            arg_value[i] = tranCodeP(request.getParameter(arg_name[i])).trim();
        }
        return arg_value;
    }


    /**
     * Gets the agent.
     * 
     * @param nodeId the node id
     * @param operationFlag the operation flag
     * 
     * @return the agent
     * 
     * @throws Exception the exception
     */
    Vector getAgent(String nodeId, String operationFlag) throws Exception {

        String nextUserSql;
        if (operationFlag.equals("1")) {
            nextUserSql = "select INSTANCENODEID,OPERATIONERID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                    + "AGENTFLAG=1 and OPERATIONER='0' and INSTANCENODEID in " + "(select ENDNODEID from " + Consts.SYSSCHEMA
                    + ".T_SYS_INSTANCENODEPATH where STARTNODEID='" + nodeId + "')  ";
        } else {
            nextUserSql = "select INSTANCENODEID,OPERATIONERID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                    + "AGENTFLAG=1 and OPERATIONER='0' and INSTANCENODEID='" + nodeId + "' ";
        }

        Vector userAndAgent = new Vector();
        try {
            StringBuffer nextNode = new StringBuffer("('@#$'");
            Map params = new HashMap();
            params.put("SelSQL", nextUserSql);
            List reList = flowService.selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                nextNode.append(",'");
                nextNode.append(tranCodeN(resMap.get("INSTANCENODEID")));
                nextNode.append("'");
            }
            nextNode.append(")");

            String currentTimeStamp = "";
            if (DBTYPE.equals("DB2")) {
                currentTimeStamp = " current timestamp ";
            } else if (DBTYPE.equals("ORACLE")) {
                currentTimeStamp = " sysdate ";
            } else if (DBTYPE.equals("MSSQL")){
            	currentTimeStamp = " CONVERT(varchar(100), GETDATE(), 20) ";
            }

            // 查找节点操作者是否不在公司
            // String selAgent = "select
            // instanceNodeId,bookerId,booker,bookerxm,agentId,agenter,agenterxm
            // "
            // + "from "
            // + Consts.SYSSCHEMA
            // + ".T_SYS_INSTANCENODE, "
            // + "(select bookerId,booker,bookerxm,agentId,agenter,agenterxm
            // from "
            // + "(select EGRESSINREGID,bookerId,yhm booker,T_SYS_RYXX.xm bookerxm
            // from "
            // + Consts.SYSSCHEMA
            // + ".t_egressinReg,T_SYS_XTYH,T_SYS_RYXX where bookerId=xtyhid and
            // T_SYS_RYXX.ryxxid=T_SYS_XTYH.ryxxid and agentId is not null and "
            // + currentTimeStamp
            // + " > leaveTime and (("
            // + currentTimeStamp
            // + " < returnTime and backTime is null) or ("
            // + currentTimeStamp
            // + " < backTime and backTime is not null))) resu1, "
            // + "(select EGRESSINREGID,agentId,yhm agenter,T_SYS_RYXX.xm agenterxm
            // from "
            // + Consts.SYSSCHEMA
            // + ". t_egressinReg,T_SYS_XTYH,T_SYS_RYXX where agentId=xtyhid and
            // T_SYS_RYXX.ryxxid=T_SYS_XTYH.ryxxid and agentId is not null and "
            // + currentTimeStamp
            // + " > leavetime and (("
            // + currentTimeStamp
            // + " < returnTime and backTime is null) or ("
            // + currentTimeStamp
            // + " < backTime and backTime is not null))) resu2 "
            // + " where resu1.EGRESSINREGID=resu2.EGRESSINREGID) resu3 "
            // + " where instanceNodeId in "
            // + nextNode.toString()
            // + " and operationer='0' and bookerId=operationerId ";
            // params.clear();
            // params.put("SelSQL", selAgent);
            // List reList1 = flowService.selcomList(params);
            // for (int i = 0; i < reList1.size(); i++) {
            // Map resMap = (Map) reList1.get(i);
            // userAndAgent.add(tranCodeN(resMap.get("INSTANCENODEID")));
            // userAndAgent.add(tranCodeN(resMap.get("BOOKERID")));
            // if (xmoryhm.equals("0")) {
            // userAndAgent.add(tranCodeN(resMap.get("BOOKERXM")));
            // } else {
            // userAndAgent.add(tranCodeN(resMap.get("BOOKER")));
            // }
            // userAndAgent.add(tranCodeN(resMap.get("AGENTID")));
            // if (xmoryhm.equals("0")) {
            // userAndAgent.add(tranCodeN(resMap.get("AGENTERXM")));
            // } else {
            // userAndAgent.add(tranCodeN(resMap.get("AGENTER")));
            // }
            // }
        } catch (Exception ex) {
            System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "getAgent" + ",异常信息ex="
                    + ex.getMessage());
            throw new Exception("getAgent Error in FlowServlet!");
        }
        return userAndAgent;
    }


    // void flowRemind(Vector remObj, String remTitle, String remContent) {
    // //如果用户不要求提醒下一步操作者,则推出当前方法
    // if (remind == null || !remind.equals("1")) {
    // return;
    // }
    // RemindBean remindBean = new RemindBean(null, remObj, remTitle,
    // remContent);
    // try {
    // if (remindMode.indexOf("2") != -1) {
    // remindBean.remindByMsg();
    // }
    // if (remindMode.indexOf("3") != -1) {
    // remindBean.remindByMobile();
    // }
    // if (remindMode.indexOf("1") != -1) {
    // remindBean.remindByMail();
    // }
    // }
    // catch (Exception ex) {
    // ex.printStackTrace();
    // System.err.println("发送提醒信息出错！");
    // }
    // }

    /**
     * 取得给定集合操作者成员.
     * 
     * @param operationerId the operationer id
     * @param operationer the operationer
     * 
     * @return Vector
     * Members（格式：操作者id、操作者名称，1（用户id、用户名/用户姓名），2（用户id、用户名/用户姓名）...）
     */
    Vector getMembers(String operationerId, String operationer) {

        Vector resu = new Vector();
        String tableName = "";
        String condi = "";
        String fieldName = "";

        if (operationer.equals("1")) {
            tableName = " T_SYS_XTYH,T_SYS_BMXX,T_SYS_RYXX ";
            fieldName = "T_SYS_XTYH.BMXXID OPERATORID,BMMC OPERATORMC";
            condi = " T_SYS_XTYH.BMXXID=T_SYS_BMXX.BMXXID AND T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND T_SYS_XTYH.BMXXID='" + operationerId + "' ";
        } else if (operationer.equals("2")) {
            tableName = " T_SYS_XTYHJS,T_SYS_XTYH,T_SYS_XTJS,T_SYS_RYXX ";
            fieldName = "T_SYS_XTYHJS.XTJSID OPERATORID,JSMC OPERATORMC";
            condi = " T_SYS_XTYHJS.XTYHID=T_SYS_XTYH.XTYHID AND T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND T_SYS_XTYHJS.XTJSID=T_SYS_XTJS.XTJSID AND T_SYS_XTYHJS.XTJSID='"
                    + operationerId + "' ";
        } else if (operationer.equals("3")) {
            tableName = " T_SYS_GZZCY,T_SYS_XTYH,T_SYS_GZZXX,T_SYS_RYXX ";
            fieldName = "T_SYS_GZZCY.GZZXXID OPERATORID,GZZMC OPERATORMC";
            condi = " T_SYS_GZZCY.XTYHID=T_SYS_XTYH.XTYHID AND T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID AND T_SYS_GZZCY.GZZXXID=T_SYS_GZZXX.GZZXXID AND T_SYS_GZZCY.GZZXXID='"
                    + operationerId + "' ";
        }
        String sql = "select T_SYS_XTYH.XTYHID XTYHID,YHM,T_SYS_RYXX.XM XM," + fieldName + " from " + tableName + " where " + condi + " order by RYBH ";

        String operatorId = "";
        String operatorMc = "";
        StringBuffer allmemberIds = new StringBuffer("('@#$'");
        try {
            Map params = new HashMap();
            params.put("SelSQL", sql);
            List reList = flowService.selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                operatorId = tranCodeN(resMap.get("OPERATORID"));
                operatorMc = tranCodeN(resMap.get("OPERATORMC"));
                resu.add(tranCodeN(resMap.get("XTYHID")));
                allmemberIds.append(",'");
                allmemberIds.append(tranCodeN(resMap.get("XTYHID")));
                allmemberIds.append("'");
                if (xmoryhm.equals("0")) {
                    resu.add(tranCodeN(resMap.get("XM")));
                } else {
                    resu.add(tranCodeN(resMap.get("YHM")));
                }
                resu.add("");
                resu.add("");
            }
            allmemberIds.append(")");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String currentTimeStamp = "";
        if (DBTYPE.equals("DB2")) {
            currentTimeStamp = " current timestamp ";
        } else if (DBTYPE.equals("ORACLE")) {
            currentTimeStamp = " sysdate ";
        } else if (DBTYPE.equals("MSSQL")){
        	currentTimeStamp = " CONVERT(VARCHAR,GETDATE(),20) ";
        }

        String agentsql = "select EGRESSINREGID,BOOKERID,T_SYS_XTYH.YHM BOOKERYHM,T_SYS_RYXX.XM BOOKERXM,AGENTID,AGENT.YHM AGENTYHM ,AGENT.XM AGENTXM from "
                + "T_EGRESSINREG left join (select XTYHID,YHM,XM from T_SYS_XTYH,T_SYS_RYXX where T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID) "
                + "AGENT on T_EGRESSINREG.AGENTID=AGENT.XTYHID,T_SYS_XTYH,T_SYS_RYXX where T_EGRESSINREG.BOOKERID=T_SYS_XTYH.XTYHID "
                + "and T_SYS_XTYH.RYXXID=T_SYS_RYXX.RYXXID and " + currentTimeStamp + " > LEAVETIME and " + "((" + currentTimeStamp
                + " < RETURNTIME and BACKTIME is null) or (" + currentTimeStamp + " < BACKTIME and BACKTIME is not null))" + " and BOOKERID in "
                + allmemberIds.toString();
        try {

            Map params = new HashMap();
            params.put("SelSQL", agentsql);
            List reList = flowService.selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                resu.setElementAt(tranCodeN(resMap.get("AGENTID")), resu.indexOf(tranCodeN(resMap.get("BOOKERID"))) + 2);
                if (xmoryhm.equals("0")) {
                    resu.setElementAt(tranCodeN(resMap.get("AGENTXM")), resu.indexOf(tranCodeN(resMap.get("BOOKERID"))) + 3);
                } else {
                    resu.setElementAt(tranCodeN(resMap.get("AGENTYHM")), resu.indexOf(tranCodeN(resMap.get("BOOKERID"))) + 3);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!operatorId.equals("")) {
            resu.insertElementAt(operatorId, 0);
            resu.insertElementAt(operatorMc, 1);
        }
        return resu;
    }


    /**
     * 取得给定流程实例的当前节点的操作者.
     * 
     * @param flowInstanceId the flow instance id
     * @param hasMutilChoosed the has mutil choosed
     * 
     * @return Vector 格式：1 用户集合xtyhid1,xtyhid2,...; 2 部门集合bmxxid1,bmxxid2,...; 3
     * 角色集合xtjsid1,xtjsid2,..; 4 工作组集合gzzid1,gzzid2,...; 5 提醒内容content
     */
    Vector getProNodeOpera(String flowInstanceId, boolean hasMutilChoosed) {

        Vector resu = new Vector();
        String sql = "";
        if (hasMutilChoosed) {
            sql = "select 0 OPERATIONER,OPERATIONUSER OPERATIONERID from T_SYS_INSTANCENODEOPERATION where INSTANCENODEID in (select INSTANCENODEID from T_SYS_PROCESSNODE where FLOWINSTANCEID='"
                    + flowInstanceId + "') and operationFlag=0 with ur ";
        } else {
            sql = "select OPERATIONER,OPERATIONERID from V_PROCESSNODE_INSTANCENODE where FLOWINSTANCEID='" + flowInstanceId + "' ";
        }
        try {
            StringBuffer users = new StringBuffer(); // 0
            StringBuffer departs = new StringBuffer(); // 1
            StringBuffer roles = new StringBuffer(); // 2
            StringBuffer groups = new StringBuffer(); // 3
            Map params = new HashMap();
            params.put("SelSQL", sql);
            List reList = flowService.selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                if (tranCodeN(resMap.get("OPERATIONER")).equals("0")) {
                    users.append(tranCodeN(resMap.get("OPERATIONERID")));
                    users.append(",");
                } else if (tranCodeN(resMap.get("OPERATIONER")).equals("1")) {
                    departs.append(tranCodeN(resMap.get("OPERATIONERID")));
                    departs.append(",");
                } else if (tranCodeN(resMap.get("OPERATIONER")).equals("2")) {
                    roles.append(tranCodeN(resMap.get("OPERATIONERID")));
                    roles.append(",");
                } else if (tranCodeN(resMap.get("OPERATIONER")).equals("3")) {
                    groups.append(tranCodeN(resMap.get("OPERATIONERID")));
                    groups.append(",");
                }
            }
            if (users.length() != 0) {
                users.deleteCharAt(users.length() - 1);
            }
            if (departs.length() != 0) {
                departs.deleteCharAt(departs.length() - 1);
            }
            if (roles.length() != 0) {
                roles.deleteCharAt(roles.length() - 1);
            }
            if (groups.length() != 0) {
                groups.deleteCharAt(groups.length() - 1);
            }
            resu.add(users);
            resu.add(departs);
            resu.add(roles);
            resu.add(groups);
            StringBuffer content = new StringBuffer();
            sql = "select BUSINESSTYPE,CORRELATIONID from T_SYS_FLOWINSTANCE,T_SYS_FLOWMODEL"
                    + " where T_SYS_FLOWINSTANCE.FLOWMODELID=T_SYS_FLOWMODEL.FLOWMODELID and FLOWINSTANCEID='" + flowInstanceId + "' ";
            params.clear();
            params.put("SelSQL", sql.toString());
            Map resMap = flowService.selcom(params);

            if (resMap != null) {
                content.append(tranCodeN(resMap.get("BUSINESSTYPE")));
                content.append(" ");
            }
            resu.add(content);
        } catch (Exception ex) {
            System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "getProNodeOpera" + ",异常信息ex="
                    + ex.getMessage());
            ex.printStackTrace();
            System.err.println("getProNodeOpera Error In FlowServlet!");
        }
        return resu;
    }
    
    /**
     * Tran code p.
     * 
     * @param Str the str
     * 
     * @return the string
     */
    public String tranCodeP(String Str) {

        return Str == null ? "" : Str;
    }


    /**
     * Tran code n.
     * 
     * @param Str the str
     * 
     * @return the string
     */
    public String tranCodeN(Object Str) {

        return Str == null ? "" : Str.toString();
    }


    /**
     * Gets the flow service.
     * 
     * @return the flow service
     */
    public FlowService getFlowService() {

        return flowService;
    }


    /**
     * Sets the flow service.
     * 
     * @param flowService the new flow service
     */
    public void setFlowService(FlowService flowService) {

        this.flowService = flowService;
    }


    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

        System.out.println();
    }
    
    /**
	 * 通用审批画面 ad by zhuxw 2015-01-07
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	 public ActionForward toCommAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		    String commUrl = StringUtil.utf8Decoder(StringUtil.nullToSring(request.getParameter("COMM_URL")));
		    System.err.println("commUrl========="+commUrl);
		    if(StringUtil.isEmpty(commUrl))
	    	{
	    		throw new ServiceException("对不起，传入的URL为空!");
	    	}
	    	request.setAttribute("commUrl", commUrl);
	        return mapping.findForward("commaudit");
	 }
}
