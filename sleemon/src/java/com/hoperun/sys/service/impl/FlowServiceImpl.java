package com.hoperun.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.sys.model.FlowObject;
import com.hoperun.sys.model.NodeOperation;
import com.hoperun.sys.model.ProcessNode;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

/**
 * The Class FlowServiceImpl.
 * 
 * @module 共通模块
 * @func 审批流程
 * @version 1.1
 * @author zhuxw
 */
public class FlowServiceImpl extends BaseService implements FlowService {

    /** The logger. */
    private Logger         logger        = Logger.getLogger(FlowServiceImpl.class);

    /** The state. */
    public static String[] state         = {"进行", "完成", "异常"};

    /** The business state. */
    public static String[] businessState = {"提交", "审核通过", "否决", "撤销"};

    /** The jgxxid. */
    private String         jgxxid        = "";


    //新增一个MODEL 
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txAddModule(javax.servlet.http.HttpServletRequest)
     */
    public String txAddModule(HttpServletRequest request) throws Exception {

        // String modelNumber = createBH.returnBH("T_SYS_FLOWMODEL", "modelNumber",
        // "MBBH", 16);
    	
        String modelNumber = "MBBH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");

        String modelName = tranCodeP(request.getParameter("modelName"));
        String modelType = tranCodeP(request.getParameter("modelType"));
        String businessType = tranCodeP(request.getParameter("businessType"));
        String jgxxid = tranCodeP(request.getParameter("jgxxid"));
        String remark = tranCodeP(request.getParameter("remark"));
        String affirmJump = tranCodeP(request.getParameter("affirmJump"));
        String flowModelId = TimeComm.getTimeStamp("m");
        String startNodeId = TimeComm.getTimeStamp("s");
        String endNodeId = TimeComm.getTimeStamp("e");
        String pathId = TimeComm.getTimeStamp("p");
        String modelInsertName[] = {"flowModelId", "modelNumber", "modelName", "modelType", "modelState", "businessType", "remark", "jgxxid"};
        String modelInsertValue[] = {flowModelId, modelNumber, modelName, modelType, "\u672A\u751F\u6548", businessType, remark, jgxxid};
        String nodeInsertName[] = {"flowNodeId", "flowModelId", "backFlag", "jumpFlag", "expandFlag", "editFlag", "cancleFlag", "agentFlag",
                "delayFlag", "delayTime", "allFlag", "remark", "nodeX", "nodeY", "nodeOperationId", "operationer", "operationerId"};
        String nodeInsertValue[] = {startNodeId, flowModelId, "0", affirmJump, "0", "0", "0", "0", "0", "0", "0", "", "200", "30", "flowStartId",
                "-1", "-1"};
        String pathInsertName[] = {"nodePathId", "startNodeId", "endNodeId", "flowModelId"};
        String pathInsertValue[] = {pathId, startNodeId, endNodeId, flowModelId};

        Map params = new HashMap();
        int maxlength = modelInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(modelInsertName[i], modelInsertValue[i]);
        }
        Map checkMap=new HashMap();
        checkMap.put("BUSINESSTYPE", params.get("businessType"));
        checkMap.put("JGXXID", params.get("jgxxid"));
        checkMap.put("MODELTYPE", params.get("modelType"));
        checkMap.put("FLOWMODELID", params.get("flowModelId"));
        boolean flag=checkInfo(checkMap);
        if(flag){
        	throw new ServiceException("存在重复数据，不能修改！");
        }
        txInsertmod(params);
        params.clear();
        maxlength = nodeInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(nodeInsertName[i], nodeInsertValue[i]);
        }
        txInsertnod(params);
        params.clear();
        nodeInsertValue[0] = endNodeId;
        nodeInsertValue[3] = "0";
        nodeInsertValue[12] = "200";
        nodeInsertValue[13] = "118";
        nodeInsertValue[14] = "flowEndId";
        nodeInsertValue[15] = "-2";
        nodeInsertValue[16] = "-2";
        maxlength = nodeInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(nodeInsertName[i], nodeInsertValue[i]);
        }
        txInsertnod(params);
        params.clear();
        maxlength = pathInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(pathInsertName[i], pathInsertValue[i]);
        }
        txInsertpath(params);
        return flowModelId;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txAddModuleNodeUp(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[])
     */
    public void txAddModuleNodeUp(String flowNodeId, String nodey, String[] nodeInsertName, String[] parameterValue, String[] nodeInsertValue) {

        StringBuffer sonsNodeStr = new StringBuffer("('@#$'");
        String vsql = "";
        if (Consts.DBTYPE.equals("DB2"))
            vsql = String
                    .valueOf(String
                            .valueOf((new StringBuffer(
                                    "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                                    .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(parameterValue[14]).append(
                                            "' ").append("union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ")
                                    .append("where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
        else if (Consts.DBTYPE.equals("ORACLE"))
            vsql = String.valueOf(String.valueOf((new StringBuffer(
                    "select distinct(ENDNODEID),STARTNODEID,FLOWMODELID,LEVEL from T_SYS_NODEPATH start with flowModelId='")).append(parameterValue[16])
                    .append("' and startNodeId='").append(parameterValue[14]).append("' connect by prior endNodeId  =startNodeId")));
        else if (Consts.DBTYPE.equals("MSSQL"))
        	vsql = String
            .valueOf(String
                    .valueOf((new StringBuffer(
                            "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                            .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(parameterValue[14]).append(
                                    "' ").append("union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ")
                            .append("where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
        Map params = new HashMap();
        params.put("SelSQL", vsql);
        List resList = selcomList(params);
        int maxlength = resList.size();
        for (int i = 0; i < maxlength; i++) {
            Map temMap = (Map) resList.get(i);
            sonsNodeStr.append(",'");
            sonsNodeStr.append(temMap.get("ENDNODEID").toString());
            sonsNodeStr.append("'");
        }
        sonsNodeStr.append(")");
        String nextNode = "";
        int nodeWidth = 80;
        int nodeHeight = 88;
        String tempsql = new StringBuffer("select FLOWNODEID from T_SYS_FLOWNODE where nodeY=").append(nodey).append("+").append(nodeHeight).append(
                " and flowModelId='").append(parameterValue[16]).append("' and flowNodeId in ").append(sonsNodeStr.toString()).toString();

        params.put("SelSQL", tempsql);
        Map resMap = selcom(params);
        if (resMap != null) {
            nextNode = resMap.get("FLOWNODEID").toString();
        }
        if (!nextNode.equals("")) {
            tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeY=nodeY+")).append(nodeHeight).append(" where flowNodeId in ").append(sonsNodeStr)
                    .toString();
            params.clear();
            params.put("UpdSQL", tempsql);
            txUpdcom(params);
        }

        tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeY=nodeY+")).append(nodeHeight).append(" where flowNodeId='")
                .append(parameterValue[14]).append("'").toString();
        params.clear();
        params.put("UpdSQL", tempsql);
        txUpdcom(params);

        params.clear();
        maxlength = nodeInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(nodeInsertName[i], nodeInsertValue[i]);
        }
        params.put("remark", "");
        txInsertnod(params);

        tempsql = (new StringBuffer("update T_SYS_NODEPATH set ENDNODEID='")).append(flowNodeId).append("' where ENDNODEID='").append(parameterValue[14])
                .append("' and flowModelId='").append(parameterValue[16]).append("'").toString();
        params.clear();
        params.put("UpdSQL", tempsql);
        txUpdcom(params);

        String pathInsertName[] = {"nodePathId", "startNodeId", "endNodeId", "flowModelId"};
        String pathInsertValue[] = {TimeComm.getTimeStamp("p"), flowNodeId, parameterValue[14], parameterValue[16]};

        params.clear();
        maxlength = pathInsertName.length;
        for (int i = 0; i < maxlength; i++) {

            params.put(pathInsertName[i], pathInsertValue[i]);
        }
        txInsertpath(params);
    }


    /**
     * Gets the compatioble model.
     * 
     * @param flowObj the flow obj
     * 
     * @return the compatioble model
     * 
     * @throws Exception the exception
     */
    public String getCompatiobleModel(FlowObject flowObj) throws Exception {

        String businessType = flowObj.getBusinessType();
        String tableName = flowObj.getTableName();
        String fieldName = flowObj.getFieldName();
        String id = flowObj.getId();
        String compatibleModel = "";
        boolean hasCompatibleModel = false;
        //选取合适模板
        try {
            Vector model = new Vector();
            Vector filter = new Vector();
            StringBuffer aSQL = new StringBuffer();
            aSQL.append("select FLOWMODELID,MODELTYPE from ");
            aSQL.append(Consts.SYSSCHEMA);
            aSQL.append(".T_SYS_FLOWMODEL where ");
            aSQL.append(" jgxxid='");
            aSQL.append(jgxxid);
            aSQL.append("' and businessType='");
            aSQL.append(businessType);
            aSQL.append("' and modelState='已生效'");
            Map params = new HashMap();
            params.put("SelSQL", aSQL.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                model.add(tranCodeN(resMap.get("FLOWMODELID")));
                filter.add(tranCodeN(resMap.get("MODELTYPE")));
            }
            
            //如果本机构没有审批模板，则取通用的模板
            if(reList.size()==0)
            {
            	StringBuffer aSQL1 = new StringBuffer();
            	aSQL1.append("select FLOWMODELID,MODELTYPE from ");
            	aSQL1.append(Consts.SYSSCHEMA);
            	aSQL1.append(".T_SYS_FLOWMODEL where ");
            	aSQL1.append(" businessType='");
            	aSQL1.append(businessType);
            	aSQL1.append("' and modelState='已生效'");
                 Map params1 = new HashMap();
                 params1.put("SelSQL", aSQL1.toString());
                 List reList1 = selcomList(params1);
                 for (int i = 0; i < reList1.size(); i++) {
                     Map resMap = (Map) reList1.get(i);
                     model.add(tranCodeN(resMap.get("FLOWMODELID")));
                     filter.add(tranCodeN(resMap.get("MODELTYPE")));
                 }	
            }
            
            
            //找出符合过滤条件的模板
            for (int i = 0; i < model.size(); i++) {
                String strFilter = (String) filter.get(i);
                if (strFilter.equals("")) {
                    strFilter = " 1=1 ";
                }
                aSQL.delete(0, aSQL.toString().length());
                aSQL.append("select count(*) NUM  from ");
                aSQL.append(Consts.SYSSCHEMA);
                aSQL.append(".");
                aSQL.append(tableName);
                aSQL.append(" where ");
                aSQL.append(fieldName);
                aSQL.append("='");
                aSQL.append(id);
                aSQL.append("' and ");
                aSQL.append(strFilter);
                params.put("SelSQL", aSQL.toString().replaceAll("#", "'"));
                Map temMap = selcom(params);
                if (temMap != null && temMap.get("NUM") != null && !temMap.get("NUM").toString().trim().equals("0")) {
                    hasCompatibleModel = true;
                    compatibleModel = model.get(i).toString();
                }
                if (hasCompatibleModel) {
                    break;
                }
            }
        } catch (Exception e) {
            throw e;

        }
        return compatibleModel;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txAddModuleDown(javax.servlet.http.HttpServletRequest)
     */
    public void txAddModuleDown(HttpServletRequest request) throws ServiceException {

        String flowNodeId = TimeComm.getTimeStamp("");
        String nodex = "";
        String nodey = "";
        int nodeWidth = 80;
        int nodeHeight = 88;
        String nodeOperationId = tranCodeP(request.getParameter("nodeOperationId"));
        String nodeInsertName[] = {"flowNodeId", "operationerId", "operationer", "flowModelId", "nodeX", "nodeY", "backFlag", "jumpFlag",
                "expandFlag", "editFlag", "cancleFlag", "agentFlag", "delayFlag", "delayTime", "allFlag", "nodeOperationId"

        };
        String userParameter[] = {"OperType", "OperID", "BackFlag", "JumpFlag", "ExpandFlag", "EditFlag", "CancleFlag", "AgentFlag", "DelayFlag",
                "DelayDay", "AllFlag", "event", "IX", "IY", "ProNodeID", "ProcessName", "ProcessID", "ProcessClass"};
        String parameterValue[] = prepareArray(request, userParameter, 0);

        try {
            String tempsql = " select NODEX,NODEY +" + nodeHeight + " NODEY   from  T_SYS_FLOWNODE where  flowNodeId='" + parameterValue[14] + "'";
            Map params = new HashMap();
            params.put("SelSQL", tempsql);
            Map resMap = selcom(params);
            if (resMap != null) {
                nodex = resMap.get("NODEX").toString();
                nodey = resMap.get("NODEY").toString();
            }

        } catch (Exception ex) {
            logger.error(ex);
            throw new ServiceException("441");
        }
        String nodeInsertValue[] = {flowNodeId, parameterValue[1], parameterValue[0], parameterValue[16], nodex, nodey, parameterValue[2],
                parameterValue[3], parameterValue[4], parameterValue[5], parameterValue[6], parameterValue[7], parameterValue[8], parameterValue[9],
                parameterValue[10], nodeOperationId};

        try {
            StringBuffer sonsNodeStr = new StringBuffer("('@#$'");
            String vsql = "";
            if (Consts.DBTYPE.equals("DB2"))
                vsql = String
                        .valueOf(String
                                .valueOf((new StringBuffer(
                                        "with pathTree(endNodeId, startNodeId) as ( select endNodeId, startNodeId from (select endNodeId,startNodeId from T_SYS_NODEPATH where flowModelId='"))
                                        .append(parameterValue[16]).append("' ) resu1").append(" where startNodeId='").append(parameterValue[14])
                                        .append("' ")
                                        .append("union all select T_SYS_NODEPATH.endNodeId,T_SYS_NODEPATH.startNodeId from T_SYS_NODEPATH,pathTree ").append(
                                                "where T_SYS_NODEPATH.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ")));
            else if (Consts.DBTYPE.equals("MSSQL"))
            	 vsql = String
                 .valueOf(String
                         .valueOf((new StringBuffer(
                                 "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                                 .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(parameterValue[14])
                                 .append("' ")
                                 .append("union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ").append(
                                         "where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
            else if (Consts.DBTYPE.equals("ORACLE"))
                vsql = String.valueOf(String.valueOf((new StringBuffer(
                        "select distinct(endNodeId),startNodeId,flowModelId,level from T_SYS_NODEPATH start with flowModelId='")).append(
                        parameterValue[16]).append("' and startNodeId='").append(parameterValue[14]).append(
                        "' connect by prior endNodeId  =startNodeId")));
            Map params = new HashMap();
            params.put("SelSQL", vsql);
            List resList = selcomList(params);
            int maxlength = resList.size();
            for (int i = 0; i < maxlength; i++) {
                Map temMap = (Map) resList.get(i);
                sonsNodeStr.append(",'");
                sonsNodeStr.append(temMap.get("ENDNODEID").toString());
                sonsNodeStr.append("'");
            }
            sonsNodeStr.append(")");
            String nextNode = "";

            String tempsql = new StringBuffer("select FLOWNODEID from T_SYS_FLOWNODE where nodeY=").append(nodey).append(" and flowModelId='").append(
                    parameterValue[16]).append("' and flowNodeId in ").append(sonsNodeStr.toString()).toString();

            params.put("SelSQL", tempsql);
            Map resMap = selcom(params);
            if (resMap != null) {
                nextNode = resMap.get("FLOWNODEID").toString();
            }
            if (!nextNode.equals("")) {
                tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeY=nodeY+")).append(nodeHeight).append(" where flowNodeId in ").append(
                        sonsNodeStr).toString();
                params.clear();
                params.put("UpdSQL", tempsql);
                txUpdcom(params);
            }

            params.clear();
            for (int i = 0; i < nodeInsertName.length; i++) {

                params.put(nodeInsertName[i], nodeInsertValue[i]);
            }
            params.put("remark", "");
            txInsertnod(params);

            tempsql = (new StringBuffer("update T_SYS_NODEPATH set startNodeId='")).append(flowNodeId).append("' where startNodeId='").append(
                    parameterValue[14]).append("' and flowModelId='").append(parameterValue[16]).append("'").toString();
            params.clear();
            params.put("UpdSQL", tempsql);
            txUpdcom(params);

            String pathInsertName[] = {"nodePathId", "startNodeId", "endNodeId", "flowModelId"};
            String pathInsertValue[] = {TimeComm.getTimeStamp("p"), parameterValue[14], flowNodeId, parameterValue[16]};

            params.clear();
            maxlength = pathInsertName.length;
            for (int i = 0; i < maxlength; i++) {

                params.put(pathInsertName[i], pathInsertValue[i]);
            }
            txInsertpath(params);

        } catch (Exception ex) {
            logger.error(ex);
            throw new ServiceException("442");
        }

        // //// 保存完 跳转页面
        String xmoryhm = "0"; // 0:姓名 1：用户名;
        String flowModelId = parameterValue[16];

        if (flowModelId.equals("")) {
            throw new ServiceException("443");
        }

        String readOnly = "0";
        Map params = new HashMap();
        params.put("SelSQL", "select MODELSTATE from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where flowModelId='" + flowModelId + "'");
        Map temMap = selcom(params);
        if (temMap != null) {
            if (tranCodeN(temMap.get("MODELSTATE")).equals("已生效"))
                readOnly = "1";
        }

        String folderStyle = "";
        if (readOnly.equals("1")) {
            folderStyle = " display:none ";
        }

        String condition = " where flowModelId='" + flowModelId + "'";
        String listName = "FLOWNODEID,OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME";
        params.put("SelSQL", " select " + listName + " from T_SYS_FLOWNODE,T_SYS_NODEOPERATION" + condition
                + " and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
        List resList = selcomList(params);
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
                    conName = "T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and xtyhid";
                } else {
                    listName1 = "YHM";
                    conName = " xtyhid";
                }
            } else if (type.equals("1")) {
                // 1：部门
                tableName = "T_SYS_BMXX";
                listName1 = "BMMC";
                conName = "bmxxid";
            } else if (type.equals("2")) {
                // 2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "xtjsid";
            } else if (type.equals("3")) {
                // 3：工作组
                tableName = "T_SYS_GZZXX";
                listName1 = "GZZMC";
                conName = "gzzxxid";
            }
            String name = "";
            if (type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3")) {

                params.put("SelSQL", "select " + listName1 + " from " + tableName + " where " + conName + "='" + id + "'");
                Map temMap1 = selcom(params);
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
        List resList1 = selcomList(params);
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
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txToRepeal(javax.servlet.http.HttpServletRequest, java.lang.String, com.hoperun.sys.service.FlowService)
     */
    public String txToRepeal(HttpServletRequest request, String remind, FlowService flowService) throws ServiceException {

        Vector proOpera = null;
        try {
        	String[] paraName = {"businessType", "tableName", "fieldName", "id", "operateFlag", "sourceURI", "flowInterfaceName", "ztbgFieldName",
            		"stateName"};
            String[] paraValue = this.prepareArray(request, paraName, 0);
            HttpSession session = request.getSession(false);
            // 取得是否提醒下一步节点的参数
            //remind = request.getParameter("remind");
            int nodeWidth = 80;
            int nodeHeight = 88;
            UserBean userBean = (UserBean) session.getAttribute("UserBean");
            String xtyhid = userBean.getXTYHID();
            String ryxm = userBean.getXM();
            //remind = "1";
            for (int i = 0; i < 6; i++) {
                if (paraValue[i] == null || paraValue[i].equals("")) {
                    request.setAttribute("msg", "审批的参数设置不完整!");
                    throw new ServiceException("441");
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
                String flowInstanceId = "";
                Map params = new HashMap();
                params.put("SelSQL", "select FLOWINSTANCEID from " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='"
                        + paraValue[1] + "' and primaryKey='" + paraValue[2] + "' and correlationId='" + paraValue[3] + "' ");
                Map temMap = selcom(params);

               // if (temMap != null) {
                //    flowInstanceId = temMap.get("FLOWINSTANCEID").toString();
               // }
                // 提醒节点处理者
                //proOpera = getProNodeOpera(flowInstanceId, false);
                // 删除当前节点
                params.clear();
                params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId in (select FLOWINSTANCEID from "
                        + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + paraValue[1] + "' and correlationId='"
                        + paraValue[3] + "' and primaryKey='" + paraValue[2] + "')");
                txDelcom(params);
                // 修改流程实例状态
                params.clear();
                params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[2]
                        + "' where instanceState='进行' and correlation='" + paraValue[1] + "' and correlationId='" + paraValue[3]
                        + "' and primaryKey='" + paraValue[2] + "'");
                txUpdcom(params);

                //更新主表状态
                String ztbgsql = "";
                if (!paraValue[7].equals("")) {
                    ztbgsql = "," + paraValue[7] + "=" + toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                }
                String stateName = "state";
                if (!paraValue[8].equals("")) {
                    stateName = paraValue[8];
                }

                //修改主记录
                String sqlBus = "update " + paraValue[1] + " set " + stateName + "='" + businessState[3] + "'" + ztbgsql + " where " + paraValue[2]
                        + "='" + paraValue[3] + "'";
                params.clear();
                params.put("UpdSQL", sqlBus);
                txUpdcom(params);

                if (paraValue[6].equals("")) {
                    paraValue[6] = "com.hoperun.sys.service.FlowInterface";
                }
                FlowInterface flowInterface = (FlowInterface) Class.forName(paraValue[6]).newInstance();
                request.setAttribute("currentState", businessState[3]);
                request.setAttribute("id", paraValue[3]);

                // 做撤销通过后的动作
                flowInterface.afterAuditing(request, session, flowService);
                //插入流程跟踪
                saveFLOWTRACE(paraValue[3], "撤销", xtyhid, ryxm, "", "", "", "");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "repeal" + ",异常信息ex="
                    + ex.getMessage());
            System.err.println("撤销中得到连接失败！");
            String faultInfo = ex.toString();
            if (request.getAttribute("faultInfo") != null) {
                faultInfo = (String) request.getAttribute("faultInfo");
            }
            request.setAttribute("msg", faultInfo);
            throw new ServiceException("441");

        }
        return Consts.SUCCESS;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txDelMODNODE(javax.servlet.http.HttpServletRequest)
     */
    public void txDelMODNODE(HttpServletRequest request) throws ServiceException {

        String flowNodeId = TimeComm.getTimeStamp("");
        String nodex = "";
        String nodey = "";
        int nodeWidth = 80;
        int nodeHeight = 88;
        String nodeOperationId = tranCodeP(request.getParameter("nodeOperationId"));
        String nodeInsertName[] = {"flowNodeId", "operationerId", "operationer", "flowModelId", "nodeX", "nodeY", "backFlag", "jumpFlag",
                "expandFlag", "editFlag", "cancleFlag", "agentFlag", "delayFlag", "delayTime", "allFlag", "nodeOperationId"

        };
        String userParameter[] = {"OperType", "OperID", "BackFlag", "JumpFlag", "ExpandFlag", "EditFlag", "CancleFlag", "AgentFlag", "DelayFlag",
                "DelayDay", "AllFlag", "event", "IX", "IY", "ProNodeID", "ProcessName", "ProcessID", "ProcessClass"};
        String parameterValue[] = prepareArray(request, userParameter, 0);
        try {
            String tempsql = " select NODEX,NODEY  from  T_SYS_FLOWNODE where  flowNodeId='" + parameterValue[14] + "'";
            Map params = new HashMap();
            params.put("SelSQL", tempsql);
            Map resMap = selcom(params);
            if (resMap != null) {
                nodex = resMap.get("NODEX").toString();
                nodey = resMap.get("NODEY").toString();
            }

        } catch (Exception ex) {
            logger.error(ex);
            throw new ServiceException("441");
        }

        try {
                String firstNode = "";
                String tempsql = (new StringBuffer("select FLOWNODEID from T_SYS_FLOWNODE where nodeY=30 and flowModelId='")).append(parameterValue[16])
                        .append("'").toString();
                Map params = new HashMap();
                params.put("SelSQL", tempsql);
                Map resMap = selcom(params);
                if (resMap != null) {
                    firstNode = resMap.get("FLOWNODEID").toString();
                }

                String strMaxx = "";
                String strMaxy = "";

                tempsql = (new StringBuffer("select max(NODEX) MAXX,max(NODEY) MAXY from T_SYS_FLOWNODE where flowModelId='")).append(parameterValue[16])
                        .append("'").toString();
                params.put("SelSQL", tempsql);
                Map resMap1 = selcom(params);
                if (resMap1 != null) {
                    firstNode = resMap1.get("MAXX").toString();
                    strMaxx = resMap1.get("MAXX").toString();
                    strMaxy = resMap1.get("MAXY").toString();
                }
                try {
                    int maxx = Integer.parseInt(strMaxx);
                    int maxy = Integer.parseInt(strMaxy);
                } catch (Exception ex) {
                    logger.error(ex);
                    throw new ServiceException("442");
                }
                Vector latestParentsNode = new Vector();

                tempsql = (new StringBuffer("select STARTNODEID from T_SYS_NODEPATH where ENDNODEID='")).append(parameterValue[14]).append("'")
                        .toString();
                params.put("SelSQL", tempsql);
                List resList = selcomList(params);
                int maxlength = resList.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList.get(i);
                    latestParentsNode.add(temMap.get("STARTNODEID").toString());
                }

                Vector latestSonsNode = new Vector();
                StringBuffer latestSonsNodeStr = new StringBuffer("('@#$'");
                tempsql = (new StringBuffer("select ENDNODEID from T_SYS_NODEPATH where STARTNODEID='")).append(parameterValue[14]).append("'")
                        .toString();
                params.put("SelSQL", tempsql);
                List resList1 = selcomList(params);
                maxlength = resList1.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList1.get(i);
                    latestSonsNode.add(temMap.get("ENDNODEID").toString());
                    latestSonsNodeStr.append(",'");
                    latestSonsNodeStr.append(temMap.get("ENDNODEID").toString());
                    latestSonsNodeStr.append("'");
                }

                latestSonsNodeStr.append(")");

                Vector apposeNode = new Vector();
                StringBuffer apposeNodeStr = new StringBuffer("('@#$'");
                tempsql = (new StringBuffer(
                        "select FLOWNODEID from T_SYS_FLOWNODE where flowNodeId in (select STARTNODEID nodeId from T_SYS_NODEPATH where ENDNODEID in (select ENDNODEID from T_SYS_NODEPATH where STARTNODEID='"))
                        .append(parameterValue[14]).append("')) and ").append("flowNodeId in (select ENDNODEID from T_SYS_NODEPATH where ").append(
                                "STARTNODEID in (select STARTNODEID from T_SYS_NODEPATH where ").append("ENDNODEID='").append(parameterValue[14]).append(
                                "'))").toString();
                params.put("SelSQL", tempsql);
                List resList2 = selcomList(params);
                maxlength = resList2.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList2.get(i);
                    apposeNode.add(temMap.get("FLOWNODEID").toString());
                    apposeNodeStr.append(",'");
                    apposeNodeStr.append(temMap.get("FLOWNODEID").toString());
                    apposeNodeStr.append("'");
                }
                apposeNodeStr.append(")");
                StringBuffer sonsNodeStr = new StringBuffer("('@#$'");
                String vsql = "";

                if (Consts.DBTYPE.equals("DB2"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(endNodeId, startNodeId) as ( select endNodeId, startNodeId from (select endNodeId,startNodeId from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where startNodeId='").append(parameterValue[14])
                                            .append("' ").append(
                                                    "union all select T_SYS_NODEPATH.endNodeId,T_SYS_NODEPATH.startNodeId from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ")));
                else if (Consts.DBTYPE.equals("MSSQL"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(parameterValue[14])
                                            .append("' ").append(
                                                    "union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
                else if (Consts.DBTYPE.equals("ORACLE"))
                    vsql = String.valueOf(String.valueOf((new StringBuffer(
                            "select distinct(endNodeId),startNodeId,flowModelId,level from T_SYS_NODEPATH start with flowModelId='")).append(
                            parameterValue[16]).append("' and startNodeId='").append(parameterValue[14]).append(
                            "' connect by prior endNodeId=startNodeId")));

                params.put("SelSQL", vsql);
                List resList3 = selcomList(params);
                maxlength = resList3.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList3.get(i);
                    sonsNodeStr.append(",'");
                    sonsNodeStr.append(temMap.get("ENDNODEID").toString());
                    sonsNodeStr.append("'");

                }
                sonsNodeStr.append(")");

                tempsql = (new StringBuffer("delete from T_SYS_NODEPATH where startNodeId='")).append(parameterValue[14]).append("'").toString();
                params.clear();
                params.put("DelSQL", tempsql);
                txDelcom(params);

                tempsql = (new StringBuffer("delete from T_SYS_NODEPATH where endNodeId='")).append(parameterValue[14]).append("'").toString();
                params.clear();
                params.put("DelSQL", tempsql);
                txDelcom(params);

                tempsql = (new StringBuffer("delete from T_SYS_FLOWNODE where flowNodeId='")).append(parameterValue[14]).append("'").toString();
                params.clear();
                params.put("DelSQL", tempsql);
                txDelcom(params);

                Vector sonsOfFirst = new Vector();
                StringBuffer sonsOfFirstStr = new StringBuffer("('@#$'");
                vsql = "";
                if (Consts.DBTYPE.equals("DB2"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(endNodeId, startNodeId) as ( select endNodeId, startNodeId from (select endNodeId,startNodeId from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where startNodeId='").append(firstNode).append(
                                                    "' ").append(
                                                    "union all select T_SYS_NODEPATH.endNodeId,T_SYS_NODEPATH.startNodeId from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ")));
                else if (Consts.DBTYPE.equals("MSSQL"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(firstNode).append(
                                                    "' ").append(
                                                    "union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
                else if (Consts.DBTYPE.equals("ORACLE"))
                    vsql = String.valueOf(String.valueOf((new StringBuffer(
                            "select distinct(endNodeId),startNodeId,flowModelId,level from T_SYS_NODEPATH start with flowModelId='")).append(
                            parameterValue[16]).append("' and startNodeId='").append(firstNode).append("' connect by prior endNodeId=startNodeId")));

                params.clear();
                params.put("SelSQL", vsql);
                List resList4 = selcomList(params);
                maxlength = resList4.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList4.get(i);
                    sonsOfFirstStr.append(",'");
                    sonsOfFirst.add(temMap.get("ENDNODEID").toString());
                    sonsOfFirstStr.append(temMap.get("ENDNODEID").toString());
                    sonsOfFirstStr.append("'");

                }
                sonsOfFirstStr.append(")");

                Vector sonsOfParent = new Vector();
                StringBuffer sonsOfParentStr = new StringBuffer("('@#$'");
                vsql = "";
                if (Consts.DBTYPE.equals("DB2"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(endNodeId, startNodeId) as ( select endNodeId, startNodeId from (select endNodeId,startNodeId from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where startNodeId='").append(
                                                    latestParentsNode.get(0).toString()).append("' ").append(
                                                    "union all select T_SYS_NODEPATH.endNodeId,T_SYS_NODEPATH.startNodeId from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ")));
                else if (Consts.DBTYPE.equals("MSSQL"))
                    vsql = String
                            .valueOf(String
                                    .valueOf((new StringBuffer(
                                            "with pathTree(ENDNODEID, STARTNODEID) as ( select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID from T_SYS_NODEPATH where flowModelId='"))
                                            .append(parameterValue[16]).append("' ) resu1").append(" where STARTNODEID='").append(
                                                    latestParentsNode.get(0).toString()).append("' ").append(
                                                    "union all select T_SYS_NODEPATH.ENDNODEID,T_SYS_NODEPATH.STARTNODEID from T_SYS_NODEPATH,pathTree ").append(
                                                    "where T_SYS_NODEPATH.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ")));
                else if (Consts.DBTYPE.equals("ORACLE"))
                    vsql = String.valueOf(String.valueOf((new StringBuffer(
                            "select distinct(endNodeId),startNodeId,flowModelId,level from T_SYS_NODEPATH start with flowModelId='")).append(
                            parameterValue[16]).append("' and startNodeId='").append(latestParentsNode.get(0).toString()).append(
                            "' connect by prior endNodeId=startNodeId")));

                params.clear();
                params.put("SelSQL", vsql);
                List resList5 = selcomList(params);
                maxlength = resList5.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList5.get(i);
                    sonsOfParentStr.append(",'");
                    sonsOfParent.add(temMap.get("ENDNODEID").toString());
                    sonsOfParentStr.append(temMap.get("ENDNODEID").toString());
                    sonsOfParentStr.append("'");

                }
                sonsOfParentStr.append(")");
                String nodeNum = "";

                tempsql = (new StringBuffer("select count(*) NODENUM from T_SYS_FLOWNODE where flowNodeId in ")).append(sonsOfParentStr).append(
                        " and flowNodeId in ").append(latestSonsNodeStr).toString();
                params.clear();
                params.put("SelSQL", tempsql);
                Map resMap2 = selcom(params);
                if (resMap2 != null) {
                    nodeNum = resMap2.get("NODENUM").toString();
                }

                if (apposeNode.size() > 1) {
                    tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeX=nodeX-")).append(nodeWidth).append(" where flowNodeId in ").append(
                            apposeNodeStr).append(" and nodeX>").append(nodex).toString();
                    params.clear();
                    params.put("UpdSQL", tempsql);
                    txUpdcom(params);

                }

                else if (nodeNum.equals("0")) {
                    tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeY=nodeY-")).append(nodeHeight).append(" where flowNodeId in ").append(
                            sonsNodeStr).append(" and flowNodeId not in ").append(sonsOfFirstStr).toString();
                    params.clear();
                    params.put("UpdSQL", tempsql);
                    txUpdcom(params);
                    maxlength = latestParentsNode.size();
                    for (int i = 0; i < maxlength; i++) {
                        for (int j = 0; j < latestSonsNode.size(); j++) {
                            String pathId = TimeComm.getTimeStamp(String.valueOf(String.valueOf(i)) + String.valueOf(String.valueOf(j)));
                            String pathInsertName[] = {"nodePathId", "startNodeId", "endNodeId", "flowModelId"};
                            String pathInsertValue[] = {pathId, latestParentsNode.get(i).toString(), latestSonsNode.get(j).toString(),
                                    parameterValue[16]};
                            params.clear();
                            for (int k = 0; k < pathInsertName.length; k++) {

                                params.put(pathInsertName[k], pathInsertValue[k]);
                            }
                            txInsertpath(params);

                        }

                    }

                }

                tempsql = (new StringBuffer("select distinct(NODEX) from T_SYS_FLOWNODE where flowModelId='")).append(parameterValue[16]).append(
                        "' order by nodeX asc").toString();

                params.clear();
                params.put("SelSQL", tempsql);
                List resList6 = selcomList(params);
                maxlength = resList6.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList6.get(i);
                    String nodeX = String.valueOf(200 + nodeWidth * i);
                    if (!temMap.get("NODEX").toString().equals(nodeX)) {
                        tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeX=nodeX-")).append(nodeWidth).append(" where flowModelId='").append(
                                parameterValue[16]).append("' and nodeX>").append(nodeX).toString();
                        params.clear();
                        params.put("UpdSQL", tempsql);
                        txUpdcom(params);
                    }

                }

                tempsql = (new StringBuffer("select distinct(NODEY) from T_SYS_FLOWNODE where flowModelId='")).append(parameterValue[16]).append(
                        "' order by nodeY asc").toString();
                params.clear();
                params.put("SelSQL", tempsql);
                List resList7 = selcomList(params);
                maxlength = resList7.size();
                for (int i = 0; i < maxlength; i++) {
                    Map temMap = (Map) resList7.get(i);

                    String nodeY = String.valueOf(30 + nodeHeight * i);
                    if (!temMap.get("NODEY").toString().equals(nodeY)) {
                        tempsql = (new StringBuffer("update T_SYS_FLOWNODE set nodeY=nodeY-")).append(nodeHeight).append(" where flowModelId='").append(
                                parameterValue[16]).append("' and nodeY>").append(nodeY).toString();
                        params.clear();
                        params.put("UpdSQL", tempsql);
                        txUpdcom(params);

                    }

                }

            } catch (Exception ex) {
                logger.error(ex);
                throw new ServiceException("443");
            }

         // //// 删除完 刷新页面
        String xmoryhm = "0"; // 0:姓名 1：用户名;
        String flowModelId = parameterValue[16];

        if (flowModelId.equals("")) {
            throw new ServiceException("445");
        }

        String readOnly = "0";
        Map params = new HashMap();
        params.put("SelSQL", "select MODELSTATE from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where flowModelId='" + flowModelId + "'");
        Map temMap = selcom(params);
        if (temMap != null) {
            if (tranCodeN(temMap.get("MODELSTATE")).equals("已生效"))
                readOnly = "1";
        }

        String folderStyle = "";
        if (readOnly.equals("1")) {
            folderStyle = " display:none ";
        }

        String condition = " where flowModelId='" + flowModelId + "'";
        String listName = "FLOWNODEID,OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME";
        params.put("SelSQL", " select " + listName + " from T_SYS_FLOWNODE,T_SYS_NODEOPERATION" + condition
                + " and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
        List resList = selcomList(params);
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
                    conName = "T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and xtyhid";
                } else {
                    listName1 = "YHM";
                    conName = " xtyhid";
                }
            } else if (type.equals("1")) {
                // 1：部门
                tableName = "T_SYS_BMXX";
                listName1 = "BMMC";
                conName = "bmxxid";
            } else if (type.equals("2")) {
                // 2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "xtjsid";
            } else if (type.equals("3")) {
                // 3：工作组
                tableName = "T_SYS_GZZXX";
                listName1 = "GZZMC";
                conName = "gzzxxid";
            }
            String name = "";
            if (type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3")) {

                params.put("SelSQL", "select " + listName1 + " from " + tableName + " where " + conName + "='" + id + "'");
                Map temMap1 = selcom(params);
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
        List resList1 = selcomList(params);
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
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txSaveNodes(javax.servlet.http.HttpServletRequest)
     */
    public void txSaveNodes(HttpServletRequest request) throws ServiceException {

        String nodeOperationId = ParamUtil.get(request, "nodeOperationId");
        String userParameter[] = {"OperType", "OperID", "BackFlag", "JumpFlag", "ExpandFlag", "EditFlag", "CancleFlag", "AgentFlag", "DelayFlag",
                "DelayDay", "AllFlag", "event", "IX", "IY", "ProNodeID", "ProcessName", "ProcessID", "ProcessClass"};
        String parameterValue[] = prepareArray(request, userParameter, 0);
        String nodeUpdName[] = {"operationerId", "backFlag", "jumpFlag", "expandFlag", "editFlag", "cancleFlag", "agentFlag", "delayFlag",
                "delayTime", "allFlag", "nodeOperationId"};
        String nodeUpdValue[] = {parameterValue[1], parameterValue[2], parameterValue[3], parameterValue[4], parameterValue[5], parameterValue[6],
                parameterValue[7], parameterValue[8], parameterValue[9], parameterValue[10], nodeOperationId};
        try {
            Map params = new HashMap();
            for (int k = 0; k < nodeUpdName.length; k++) {

                params.put(nodeUpdName[k], nodeUpdValue[k]);
            }
            params.put("flowNodeId", parameterValue[14]);
            txUpdatenodeById(params);

        } catch (Exception ex) {
            logger.error(ex);
            throw new ServiceException("441");
            // ("----------Error in ModelServlet ");
        }

        // //// 修改 刷新页面
        String xmoryhm = "0"; // 0:姓名 1：用户名;
        String flowModelId = parameterValue[16];

        if (flowModelId.equals("")) {
            throw new ServiceException("442");
        }

        String readOnly = "0";
        Map params = new HashMap();
        params.put("SelSQL", "select MODELSTATE from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where flowModelId='" + flowModelId + "'");
        Map temMap = selcom(params);
        if (temMap != null) {
            if (tranCodeN(temMap.get("MODELSTATE")).equals("已生效"))
                readOnly = "1";
        }

        String folderStyle = "";
        if (readOnly.equals("1")) {
            folderStyle = " display:none ";
        }

        String condition = " where flowModelId='" + flowModelId + "'";
        String listName = "FLOWNODEID,OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME";
        params.put("SelSQL", " select " + listName + " from T_SYS_FLOWNODE,T_SYS_NODEOPERATION" + condition
                + " and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
        List resList = selcomList(params);
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
                    conName = "T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and xtyhid";
                } else {
                    listName1 = "YHM";
                    conName = " xtyhid";
                }
            } else if (type.equals("1")) {
                // 1：部门
                tableName = "T_SYS_BMXX";
                listName1 = "BMMC";
                conName = "bmxxid";
            } else if (type.equals("2")) {
                // 2：角色
                tableName = "T_SYS_XTJS";
                listName1 = "JSMC";
                conName = "xtjsid";
            } else if (type.equals("3")) {
                // 3：工作组
                tableName = "T_SYS_GZZXX";
                listName1 = "GZZMC";
                conName = "gzzxxid";
            }
            String name = "";
            if (type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3")) {

                params.put("SelSQL", "select " + listName1 + " from " + tableName + " where " + conName + "='" + id + "'");
                Map temMap1 = selcom(params);
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
        List resList1 = selcomList(params);
        List retList1 = new ArrayList();
        maxlength = retList1.size();
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
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txToAffirm(javax.servlet.http.HttpServletRequest, java.lang.String[], javax.servlet.http.HttpSession, com.hoperun.sys.model.FlowObject, com.hoperun.sys.service.FlowService, java.lang.String)
     */
    public String txToAffirm(HttpServletRequest request, String[] paraValue, HttpSession session, FlowObject flowObjectInS, FlowService flowService,
            String remind) throws ServiceException {

        // 从这里开始要放到一个事物里面，现在暂时没改
        // begin
        try {

            String flowInstanceId = "";
            try {
                if (paraValue[6].equals(""))
                    paraValue[6] = "com.hoperun.sys.service.FlowInterface";

                FlowInterface flowInterface = (FlowInterface) Class.forName(paraValue[6]).newInstance();

                boolean eventResu = false;
                request.setAttribute("id", paraValue[3]);

                eventResu = flowInterface.beforeAffirm(request, session, flowService);
                flowObjectInS.setFlowModelId((String) request.getAttribute("flowModelId"));
                if (!eventResu) {
                    throw new ServiceException("441");
                }
                // 获得审批流程节点
                flowInstanceId = txQueren(session, flowObjectInS);
                // 处理审批完成之后执行方法
                flowInterface.afterAffirm(request, session, flowService);
                request.setAttribute("currentState", businessState[1]);

                if (flowInstanceId == null || flowInstanceId.equals("")) {
                    request.setAttribute("id", paraValue[3]);
                    flowInterface.afterAuditing(request, session, flowService);
                }
            }
            // end
            catch (ServiceException e) {
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "affirm" + ",异常信息ex="
                        + e.getMessage());

                throw e;
            }

            // 根据模板设置和人员级别确定是否提交跳签
            String affirmCanJump = (String) session.getAttribute("affirmCanJump");
            session.removeAttribute("affirmCanJump");
            // affirmCanJump 1：允许跳签 0:禁止跳签
            if (affirmCanJump == null)
                affirmCanJump = "0";
            //if (!flowInstanceId.equals("") && affirmCanJump.equals("1")) {
            if (!"".equals(flowInstanceId) && "1".equals(affirmCanJump)) {
                request.setAttribute("flowInstanceId", flowInstanceId);
                request.setAttribute("flowEvent", "affirm");
                request.setAttribute("remind", remind);
                request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                return "affirmCanJump";
            }
            if (!flowInstanceId.equals("")) {
                Map params = new HashMap();
                params.put("SelSQL", " select INSTANCENODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='" + flowInstanceId
                        + "' and nodeY=30");
                Map temMap = flowService.selcom(params);

                String nodeId = "";
                if (temMap != null && temMap.get("INSTANCENODEID") != null) {
                    nodeId = temMap.get("INSTANCENODEID").toString();
                }
                // 提醒节点处理者
                // Vector proOpera = getProNodeOpera(flowInstanceId, false);

                // 取得下一步的节点代理
                Vector userAndAgent = getAgent(nodeId, "1");
                // 如果有则返回用户界面，取得用户意见
                if (userAndAgent.size() != 0) {
                    request.setAttribute("userAndAgent", userAndAgent);
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowEvent", "affirm");
                    request.setAttribute("flowInterfaceName", paraValue[6]);
                    request.setAttribute("ztbgFieldName", paraValue[7]);
                    request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                    request.setAttribute("remind", remind);
                    return "userAndAgent";
                }

                // 查找下一步设置多人处理的节点操作者：operationerId，操作类型：operationer
                Vector operationer = new Vector();
                Vector operationerId = new Vector();
                Vector instanceNodeIdV = new Vector();
                params.clear();
                params.put("SelSQL",
                        "select INSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID,DECODE(OPERATIONER,0,'用户',1,'部门',2,'角色',3,'工作组') LX from " + ""
                                + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId in (select endNodeId from " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath where startNodeId='" + nodeId + "') order by nodex");
                List reList = flowService.selcomList(params);
                int maxlength = reList.size();
                String OPERATIONERIDS = "";
                String OPERATIONERNAMES = "";
                String OPERATORTYPE = "";
                for (int i = 0; i < maxlength; i++) {
                    Map resMap = (Map) reList.get(i);
                    String allFlag = resMap.get("ALLFLAG").toString();
                    if (allFlag.equals("1")) {
                        instanceNodeIdV.add(resMap.get("INSTANCENODEID").toString());
                        operationer.add(resMap.get("OPERATIONER").toString());
                        operationerId.add(resMap.get("OPERATIONERID").toString());

                    }
                    if (i == 0) {
                        OPERATIONERIDS = resMap.get("OPERATIONERID").toString();
                        OPERATIONERNAMES = getOPERATORNAME(resMap.get("OPERATIONER").toString(), resMap.get("OPERATIONERID").toString(), flowService);
                    } else {
                        OPERATIONERIDS = "," + resMap.get("OPERATIONERID").toString();
                        OPERATIONERNAMES = ","
                                + getOPERATORNAME(resMap.get("OPERATIONER").toString(), resMap.get("OPERATIONERID").toString(), flowService);
                    }
                    OPERATORTYPE = resMap.get("LX").toString();
                }

                // 如果是非集合用户，发送提醒信息
                // if (proOpera != null && instanceNodeIdV.size() == 0)
                // flowRemind(proOpera, "工作提示",
                // proOpera.get(4).toString()+"等待您处理！");
                // 取得集合用户
                Vector selOpera = new Vector();
                maxlength = instanceNodeIdV.size();
                for (int i = 0; i < maxlength; i++) {
                    Vector operators = getMembers(operationerId.get(i).toString(), operationer.get(i).toString());
                    Vector selOperator = new Vector();
                    selOperator.add(instanceNodeIdV.get(i));
                    selOperator.add(operationer.get(i));
                    selOperator.add(operationerId.get(i));
                    selOperator.add(operators);
                    selOpera.add(selOperator);
                }
                // 如果下一步节点操作是多人
                if (selOpera.size() != 0) {
                    request.setAttribute("selOpera", selOpera);
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowEvent", "affirm");
                    request.setAttribute("flowInterfaceName", paraValue[6]);
                    request.setAttribute("ztbgFieldName", paraValue[7]);
                    request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                    request.setAttribute("remind", remind);
                    return "selOperators";
                }

                // 取得是否提醒下一步节点的参数
                //remind = request.getParameter("remind");
                //int nodeWidth = 80;
                //int nodeHeight = 88;
                UserBean userBean = (UserBean) session.getAttribute("UserBean");
                String XTYHID = userBean.getXTYHID();
                String XM = userBean.getXM();
                //remind = "1";
                //插入流程跟踪
                saveFLOWTRACE(paraValue[3], "提交", XTYHID, XM, OPERATIONERIDS, OPERATIONERNAMES, OPERATORTYPE, "");
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new ServiceException(ex.toString());
        }
        System.err.println(paraValue[5] + paraValue[3]);
        request.setAttribute("refresh", "T");
        request.setAttribute("alertMsg", request.getAttribute("faultInfo"));
        // 这里要改
        request.setAttribute("msg", "提交成功！");
        request.setAttribute("value", paraValue[5] + paraValue[3]);
        return Consts.SUCCESS;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txAuditing(javax.servlet.http.HttpServletRequest, com.hoperun.sys.service.FlowService, java.lang.String)
     */
    public String txAuditing(HttpServletRequest request, FlowService flowService, String remind) throws ServiceException {

        HttpSession session = request.getSession(false);
        // 取得是否提醒下一步节点的参数
        remind = request.getParameter("remind");
        int nodeWidth = 80;
        int nodeHeight = 88;
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String ryxm = userBean.getXM();
        String jgxxid = userBean.getJGXXID();
        String operationContent = request.getParameter("operationContent");
        String flowInstanceId = request.getParameter("flowInstanceId");
        String instanceNodeId = request.getParameter("instanceNodeId");
        String operationFlag = request.getParameter("operationFlag");
        String currentNodeType = request.getParameter("currentNodeType");
        String flowInterfaceName = request.getParameter("flowInterfaceName");

        if ("2".equals(operationFlag)) {
            Map params = new HashMap();
            params.put("SelSQL", "select  OPERATIONER   from   " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE  where instanceNodeId ='" + instanceNodeId
                    + "'");
            Map temMap = flowService.selcom(params);

            if (temMap != null) {
                currentNodeType = tranCodeN(temMap.get("OPERATIONER"));
            }
        }
        String nextNodeId = request.getParameter("nextNodeId");
        String nextNodeType = request.getParameter("nextNodeType");
        String sourceURI = request.getParameter("sourceURI");
        String flowEvent = request.getParameter("flowEvent");
        String operationUser = xtyhid;
        String operationName = "";
        String updSql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag + ",operationUser='"
                + operationUser + "',operationTime=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate())
                + ",operationContent='" + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                + "' and operationFlag=0";

        String allUpdSql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=0" + ",operationUser='" + operationUser
                + "',operationTime=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                + "' and operationFlag=0";

        try {
            Map params = new HashMap();
            params.put("SelSQL", "select OPERATIONNAME from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation" + " where instanceNodeId='"
                    + instanceNodeId + "' and OPERATIONFLAG=0  ");
            Map temMap = flowService.selcom(params);

            if (temMap != null) {
                operationName = temMap.get("OPERATIONNAME").toString();
            }
        } catch (Exception ex) {
            logger.error(ex);
            System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                    + ex.getMessage());
            logger.error("取得结点操作名称出错！");
            String faultInfo = ex.toString();
            request.setAttribute("msg", faultInfo);
            throw new ServiceException("441");
        }

        // 取得当前结点的下一步节点中包含的多人操作的节点
        Vector allInstanceNodeId = new Vector();
        Vector allOperationer = new Vector();
        Vector allOperationerId = new Vector();
        try {
            // 取得当前操作节点的子节点和子节点的前导节点{
            StringBuffer parentsOfProcessNextNode = new StringBuffer("('@#$'");
            Vector sonsOfProNode = new Vector();
            String vsql = "";
            // <!---1:通过；-1：否决； 2：回退； 3：跳签；----------->
            if (operationFlag.equals("1")) {
                vsql = "select ENDNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where startNodeId='" + instanceNodeId + "' ";
                Map params = new HashMap();
                params.put("SelSQL", vsql);
                List reList = flowService.selcomList(params);
                int maxlength = reList.size();
                for (int i = 0; i < maxlength; i++) {
                    Map resMap = (Map) reList.get(i);
                    sonsOfProNode.add(resMap.get("ENDNODEID").toString());
                }
            } else if (operationFlag.equals("2") || operationFlag.equals("3")) {
                sonsOfProNode.add(nextNodeId);
            } else if (operationFlag.equals("-1")) {
                sonsOfProNode.add(instanceNodeId);
            }
            if (sonsOfProNode.size() == 1) {
                if (operationFlag.equals("1") || operationFlag.equals("3") || operationFlag.equals("-1")) {
                    // 如果当前操作节点的下一步节点只有一个，如果操作结果是通过或跳签，选取该节点的所有前导节点
                    if (Consts.DBTYPE.equals("DB2")) {
                        vsql = "with pathTree(endNodeId, startNodeId) as ( " + "select endNodeId, startNodeId from (select endNodeId,startNodeId "
                                + "from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1 "
                                + "where endNodeId='" + sonsOfProNode.get(0) + "' union all select " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId " + "from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath,pathTree where T_SYS_INSTANCENODEPath.endNodeId=pathTree.startNodeId) "
                                + "select distinct(startNodeId)  from pathTree ";
                    } else if(Consts.DBTYPE.equals("MSSQL")){
                    	vsql = "with pathTree(ENDNODEID, STARTNODEID) as ( " + "select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID "
                        + "from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1 "
                        + "where ENDNODEID='" + sonsOfProNode.get(0) + "' union all select " + "" + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID " + "from " + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEPath,pathTree where T_SYS_INSTANCENODEPath.ENDNODEID=pathTree.STARTNODEID) "
                        + "select distinct(STARTNODEID)  from pathTree ";
                    }else if (Consts.DBTYPE.equals("ORACLE")) {
                        vsql = "select distinct(endNodeId),startNodeId,flowInstanceId,level from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and endNodeId='" + sonsOfProNode.get(0)
                                + "' connect by prior startNodeId=endNodeId ";
                    }
                } else if (operationFlag.equals("2") || operationFlag.equals("-1")) {
                    // 如果当前操作节点的下一步节点只有一个，如果操作结果是退签，选取该节点的所有后续节点

                    if (Consts.DBTYPE.equals("DB2")) {
                        vsql = "with pathTree(endNodeId, startNodeId) as " + "( select endNodeId, startNodeId from "
                                + "(select endNodeId,startNodeId from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                + flowInstanceId + "' ) resu1" + " where startNodeId='" + sonsOfProNode.get(0) + "' "
                                + "union all select T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath,pathTree " + "where " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId) startNodeId  from pathTree with ur";
                    }else if (Consts.DBTYPE.equals("MSSQL")) {
                        vsql = "with pathTree(ENDNODEID, STARTNODEID) as " + "( select ENDNODEID, STARTNODEID from "
                        + "(select ENDNODEID,STARTNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                        + flowInstanceId + "' ) resu1" + " where STARTNODEID='" + sonsOfProNode.get(0) + "' "
                        + "union all select T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from " + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEPath,pathTree " + "where " + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID) STARTNODEID  from pathTree with ur";
                    } else if (Consts.DBTYPE.equals("ORACLE")) {
                        vsql = "select distinct(endNodeId),startNodeId,flowInstanceId,level from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and startNodeId='" + sonsOfProNode.get(0)
                                + "' connect by prior endNodeId=startNodeId ";
                    }
                }
                Map params = new HashMap();
                params.put("SelSQL", vsql);
                List reList = flowService.selcomList(params);
                int maxlength = reList.size();
                for (int i = 0; i < maxlength; i++) {
                    Map resMap = (Map) reList.get(i);
                    parentsOfProcessNextNode.append(",'");
                    parentsOfProcessNextNode.append(resMap.get("STARTNODEID").toString());
                    parentsOfProcessNextNode.append("'");
                }
                parentsOfProcessNextNode.append(")");
            }
            // 取得当前操作节点的子节点和子节点的前导节点}

            // 取得当前流程实例的所有当前节点数，且这些节点同时在下一步节点的前导或后续节点中
            int processNum = 1;
            if (sonsOfProNode.size() == 1) {
                String sql = "select count(distinct(INSTANCENODEID)) PROCESSNUM from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='"
                        + flowInstanceId + "' and instanceNodeId in " + parentsOfProcessNextNode.toString();

                Map params = new HashMap();
                params.put("SelSQL", sql);
                List reList = flowService.selcomList(params);
                for (int i = 0; i < reList.size(); i++) {
                    Map resMap = (Map) reList.get(i);
                    processNum = Integer.parseInt(resMap.get("PROCESSNUM").toString());
                }
            }

            if (operationFlag.equals("1") || operationFlag.equals("2") || operationFlag.equals("3")) {
                String sql = "";
                if (operationFlag.equals("1")) {
                    sql = "select INSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID,DECODE(OPERATIONER,0,'用户',1,'部门',2,'角色',3,'工作组') LX from " + ""
                            + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId in (select ENDNODEID from " + "" + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODEPath where startNodeId='" + instanceNodeId + "') and allFlag=1 ";
                } else if (operationFlag.equals("2") || operationFlag.equals("3")) {
                    sql = "select INSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID ,DECODE(OPERATIONER,0,'用户',1,'部门',2,'角色',3,'工作组') LX from " + ""
                            + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId='" + nextNodeId + "' and allFlag=1 ";
                }
                Map params = new HashMap();
                params.put("SelSQL", sql);
                List reList = flowService.selcomList(params);

                for (int i = 0; i < reList.size(); i++) {
                    Map resMap = (Map) reList.get(i);
                    allInstanceNodeId.add(resMap.get("INSTANCENODEID").toString());
                    allOperationer.add(resMap.get("OPERATIONER").toString());
                    allOperationerId.add(resMap.get("OPERATIONERID").toString());
                }
                if (operationFlag.equals("2")) {
                    params.clear();
                    params.put("SelSQL", "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                            + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                    Map temMap = flowService.selcom(params);

                    String id = "";
                    if (temMap != null) {
                        id = temMap.get("CORRELATIONID").toString();

                    }
                    params.clear();
                    params.put("SelSQL", "select  OPERATIONER, OPERATIONERID,DECODE(OPERATIONER,0,'用户',1,'部门',2,'角色',3,'工作组') LX   from   "
                            + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE  where instanceNodeId ='" + nextNodeId + "'");
                    temMap = flowService.selcom(params);
                    String opraterid = "";
                    String opratertype = "";
                    String lx = "";
                    if (temMap != null) {
                        opraterid = temMap.get("OPERATIONERID").toString();
                        opratertype = temMap.get("OPERATIONER").toString();
                        lx = temMap.get("LX").toString();

                    }
                    //插入流程跟踪
                    saveFLOWTRACE(id, "回退", xtyhid, ryxm, opraterid, getOPERATORNAME(opratertype, opraterid, flowService), lx, operationContent);
                    //发送短消息
                    if ("1".equals(remind)) {
                        getSendMsg(opratertype, opraterid, flowService, xtyhid, ryxm, "您有单据被我回退，等待你审核！");
                    }
                }
            }
            ProcessNode pNode = flowService.getProcessNode(session, instanceNodeId);
                if (operationFlag.equals("2") || operationFlag.equals("3")) {
                    // 跳签或者退签
                    // 填写审签内容
                    // 删除当前处理节点
                    if (allInstanceNodeId.size() == 0) {
                        Map params = new HashMap();
                        params.put("UpdSQL", updSql);
                        txUpdcom(params);

                        // 如果当前节点是开始节点，则删除所有processNode
                        if (currentNodeType.equals("-1")) {
                            params.clear();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId
                                    + "' and instanceNodeId<>'" + nextNodeId + "'");
                            txDelcom(params);

                        } else {
                            params.clear();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ". T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                            txDelcom(params);

                        }
                    } else {
                        // 如果当前节点是开始节点，则删除所有processNode
                        if (currentNodeType.equals("-1")) {
                            Map params = new HashMap();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId
                                    + "' and instanceNodeId<>'" + nextNodeId + "'");
                            txDelcom(params);

                        }

                        Map params = new HashMap();
                        params.put("UpdSQL", allUpdSql);
                        txUpdcom(params);

                    }

                    /**
                     * flowInS.executeStaticSql(updSql, conn); //删除当前处理节点
                     * flowInS.executeStaticSql("delete from T_SYS_PROCESSNODE where
                     * instanceNodeId='"+instanceNodeId+"'", conn);
                     */

                    // 如果直接跳到结束节点
                    if (nextNodeType.equals("-2")) {

                        Map params = new HashMap();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId + "'");
                        txDelcom(params);

                        params.clear();
                        params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[1]
                                + "' where flowInstanceid='" + flowInstanceId + "'");
                        txUpdcom(params);
                        params.clear();
                        params.put("SelSQL", "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                        Map temMap = flowService.selcom(params);

                        String tableName = "";
                        String id = "";
                        String primaryKey = "";
                        String ztbgFieldName = "";
                        String stateName = "state";
                        if (temMap != null) {
                            tableName = temMap.get("CORRELATION").toString();
                            id = temMap.get("CORRELATIONID").toString();
                            primaryKey = temMap.get("PRIMARYKEY").toString();

                            ztbgFieldName = temMap.get("ZTBGFIELDNAME").toString();

                            String tepstateName = temMap.get("STATENAME").toString();
                            if (!tepstateName.equals("")) {
                                stateName = tepstateName;
                            }
                        }

                        String ztbgsql = "";
                        if (!ztbgFieldName.equals("")) {
                            ztbgsql = "," + ztbgFieldName + "=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                        }

                        // 修改主记录
                        String checksql = " select count(1) as NUM  from sys.user_tab_columns where TABLE_NAME='" + tableName.toUpperCase()
                                + "' and COLUMN_NAME in ('AUDIT_NAME','AUDIT_ID','AUDIT_TIME')";
                        params.clear();
                        params.put("SelSQL", checksql);
                        Map checkMap = flowService.selcom(params);
                        String Num = checkMap.get("NUM").toString();
                        String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'"
                                + ztbgsql + " where " + primaryKey + "='" + id + "'";
                        if ("3".equals(Num)) {
                            sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'" + ztbgsql
                                    + ",AUDIT_NAME='" + ryxm  + "'" + " ,AUDIT_ID ='" + xtyhid + "'" + " ,AUDIT_TIME =sysdate" + " where " + primaryKey
                                    + "='" + id + "'";
                        }
                        params.clear();
                        params.put("UpdSQL", sqlBus);
                        txUpdcom(params);
                        //插入流程跟踪
                        saveFLOWTRACE(id, "审核", xtyhid, ryxm, "", "流程结束", "", operationContent);

                        if ("1".equals(remind)) {
                        	/**
                            String ZDRsql = " select  OPERATOR,OPERATORNAME from (select OPERATOR,OPERATORNAME from T_SYS_FLOWTRACE where BUSINESSID='"
                                    + id + "' and  OPERATION='提交'  order by  OPERATETIME DESC ) where rownum<2";
                            params.clear();
                            params.put("SelSQL", ZDRsql);
                            Map ZDRMap = flowService.selcom(params);
                            if (ZDRMap != null) {
                                saveSendMsg(xtyhid, ryxm, "您有单据被我审核通过！", ZDRMap.get("OPERATOR").toString(), ZDRMap.get("OPERATORNAME").toString(),
                                        flowService);
                            }
                            **/

                        }

                        if (!flowInterfaceName.equals("")) {
                            FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                            request.setAttribute("currentState", businessState[1]);
                            request.setAttribute("id", id);
                            flowInterface.afterAuditing(request, session, flowService);
                        }
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowInstanceId", "T");
                        return "instance";
                    }

                    // 如果下一步节点的前导或后续节点中已没有当前节点
                    String processNodeId = TimeComm.getTimeStamp("pro");
                    if ((processNum == 1 || processNum == 0) && !nextNodeType.equals("-2")) {
                        // 生成新的当前节点指针
                        if (allInstanceNodeId.size() == 0) {
                            String sql = "insert into " + Consts.SYSSCHEMA
                                    + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                    + flowInstanceId + "','" + nextNodeId + "',"
                                    + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";

                            Map params = new HashMap();
                            params.put("InsSQL", sql);
                            txInscom(params);
                        }
                        // 插入新的实例节点操作
                        String allFlag = "";
                        String operationer = "";
                        String operationerId = "";
                        if (allInstanceNodeId.size() != 0) {
                            allFlag = "1";
                            operationer = allOperationer.get(0).toString();
                            operationerId = allOperationerId.get(0).toString();
                        } else {
                            allFlag = "0";
                        }
                        if (allFlag.equals("0")) {
                            String insNodeOperId = TimeComm.getTimeStamp("ino");
                            String insertSql = "insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                    + "INSTANCENODEOPERATIONID,OPERATIONNAME,CORRELATION,CORRELATIONID,OPERATIONFLAG,INSTANCENODEID,FLOWINSTANCEID) "
                                    + "select '" + insNodeOperId + "',OPERATIONNAME,CORRELATION,CORRELATIONID,0,INSTANCENODEID,FLOWINSTANCEID "
                                    + "from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='"
                                    + nextNodeId + "' and instanceNodeOperationId=(select max(INSTANCENODEOPERATIONID) from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";

                            Map params = new HashMap();
                            params.put("InsSQL", insertSql);
                            txInscom(params);

                        } else if (allFlag.equals("1")) {
                            int noDealNum = 0;
                            Map params = new HashMap();
                            params.put("SelSQL", "select COUNT(INSTANCENODEOPERATIONID) NODEALNUM from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEOperation where operationFlag=0 and instanceNodeId='" + nextNodeId + "'");
                            Map temMap = flowService.selcom(params);

                            if (temMap != null) {
                                noDealNum = Integer.parseInt(temMap.get("NODEALNUM").toString());
                            }
                            if (noDealNum == 0) {
                                String tableName = "";
                                String fieldName = "XTYHID";
                                String condi = "";
                                if (operationer.equals("1")) {
                                    tableName = " T_SYS_XTYH ";
                                    condi = " bmxxid='" + operationerId + "' ";
                                } else if (operationer.equals("2")) {
                                    tableName = " T_SYS_XTYHjs ";
                                    condi = " xtjsid='" + operationerId + "' ";
                                } else if (operationer.equals("3")) {
                                    tableName = " T_SYS_GZZCY ";
                                    condi = " gzzxxid='" + operationerId + "' ";
                                }
                                Vector allYHID = new Vector();
                                params.clear();
                                params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where " + condi);
                                List reList = flowService.selcomList(params);
                                int maxlength = reList.size();
                                for (int i = 0; i < maxlength; i++) {
                                    Map resMap = (Map) reList.get(i);
                                    allYHID.add(resMap.get(fieldName.toUpperCase()) == null ? "" : resMap.get(fieldName.toUpperCase()).toString());
                                }

                                if (allYHID.size() > 0) {
                                    for (int i = 0; i < allYHID.size(); i++) {
                                        String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i));
                                        String insertSql = "insert into "
                                                + Consts.SYSSCHEMA
                                                + ".T_SYS_INSTANCENODEOperation("
                                                + "INSTANCENODEOPERATIONID,OPERATIONNAME,CORRELATION,CORRELATIONID,OPERATIONFLAG,INSTANCENODEID,FLOWINSTANCEID,OPERATIONUSER) "
                                                + "select '" + insNodeOperId
                                                + "',OPERATIONNAME,CORRELATION,CORRELATIONID,0,INSTANCENODEID,FLOWINSTANCEID,'" + allYHID.get(i)
                                                + "' " + "from " + Consts.SYSSCHEMA
                                                + ".T_SYS_INSTANCENODEOperation where instanceNodeOperationId=(select max(INSTANCENODEOPERATIONID) from "
                                                + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";
                                        params.clear();
                                        params.put("InsSQL", insertSql);
                                        txInscom(params);

                                    }
                                } else {
                                    // request.setAttribute("msg","为设置多人处理的节点操作组添加成员!");
                                    throw new ServiceException("为设置多人处理的节点操作组添加成员！");
                                    // return mapping.findForward(FAILURE);

                                }
                            }
                        }
                    } else {
                        // return
                        // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowInstanceId", "T");
                        return "instance";
                    }

                } else if (operationFlag.equals("1")) {
                    // 处理结果：通过

                    // {填写处理意见、结果
                    // ProcessNode pNode = flowInS.getProcessNode(session,
                    // instanceNodeId);

                    // 如果当前操作节点是多人处理
                    if (pNode.getAllFlag().equals("1")) {
                        // 选取未处理的人员数

                        Map params = new HashMap();
                        params.put("SelSQL", "select COUNT(*) NODEALNUM from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='"
                                + instanceNodeId + "' and operationFlag=0 ");
                        Map temMap = flowService.selcom(params);

                        int noDealNum = 0;
                        if (temMap != null) {
                            noDealNum = Integer.parseInt(temMap.get("NODEALNUM").toString());

                        }
                        String updSql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag
                                + ",operationUser='" + operationUser + "',operationTime="
                                + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                                + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                                + "' and operationFlag=0 and operationUser='" + xtyhid + "'";
                        String allUpdSql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=0" + ",operationUser='"
                                + operationUser + "',operationTime="
                                + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                                + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                                + "' and operationFlag=0 and operationUser='" + xtyhid + "'";
                        // 填写审签内容，如果已经处理过，本次处理不会记录
                        try {
                            if (noDealNum != 1) {
                                params.clear();
                                params.put("UpdSQL", updSql1);
                                txUpdcom(params);

                            } else {
                                if (allInstanceNodeId.size() != 0 && processNum < 2) {
                                    // 如果下一步的操作中包含多人节点，并且当前处理节点是多个分支的最后一个节点的处理者
                                    params.clear();
                                    params.put("UpdSQL", allUpdSql1);
                                    txUpdcom(params);

                                } else {
                                    params.clear();
                                    params.put("UpdSQL", updSql1);
                                    txUpdcom(params);

                                    params.clear();
                                    params.put("UpdSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId
                                            + "'");
                                    txUpdcom(params);
                                }
                            }
                        } catch (Exception ex) {
                            logger.error(ex);
                            throw new ServiceException();
                        }
                        // 如果还有人没处理，则直接返回用户界面，否则接着按 通过 处理
                        if (noDealNum != 1) {
                            request.setAttribute("flowInstanceId", flowInstanceId);
                            request.setAttribute("flowInstanceId", "T");
                            return "instance";
                        }
                    } else {
                        Map params = new HashMap();
                        params.put("UpdSQL", updSql);
                        txUpdcom(params);

                    }
                    // 填写处理意见、结果}

                    // 删除当前处理节点
                    if (allInstanceNodeId.size() == 0) {

                        Map params = new HashMap();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                        txDelcom(params);

                    }
                    // 如果下一步节点的前导或后续节点中已没有当前节点
                    if (processNum == 1 || processNum == 0) {
                        Vector nextNode = new Vector();
                        String sql = "select INSTANCENODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                                + "instanceNodeId in (select ENDNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where STARTNODEID='"
                                + instanceNodeId + "') and operationer<>'-2'";

                        Map params = new HashMap();
                        params.put("SelSQL", sql);
                        List reList = flowService.selcomList(params);
                        int maxlength = reList.size();
                        for (int i = 0; i < maxlength; i++) {
                            Map resMap = (Map) reList.get(i);
                            nextNode.add(resMap.get("INSTANCENODEID").toString());
                        }
                        // flowInS.closeDB();
                        // 当前节点不是最后节点
                        if (nextNode.size() != 0) {
                            for (int i = 0; i < nextNode.size(); i++) {
                                // 设置当前节点
                                String processNodeId = TimeComm.getTimeStamp(String.valueOf(i));
                                String sqlProcessNode = "insert into " + Consts.SYSSCHEMA
                                        + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                        + flowInstanceId + "','" + nextNode.get(i) + "',"
                                        + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";
                                if (allInstanceNodeId.size() == 0) {

                                    params.clear();
                                    params.put("InsSQL", sqlProcessNode);
                                    txInscom(params);

                                }
                                // 插入新的实例节点操作
                                String allFlag = "";
                                String operationer = "";
                                String operationerId = "";
                                params.clear();
                                params.put("SelSQL",
                                        "select ALLFLAG,OPERATIONER,OPERATIONERID ,DECODE(OPERATIONER,0,'用户',1,'部门',2,'角色',3,'工作组') LX from "
                                                + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId='" + nextNode.get(i) + "'");
                                List reList1 = flowService.selcomList(params);
                                String OPERATIONERIDS = "";
                                String OPERATIONERNAMES = "";
                                String OPERATORTYPE = "";
                                for (int k = 0; k < reList1.size(); k++) {
                                    Map resMap = (Map) reList1.get(k);
                                    allFlag = resMap.get("ALLFLAG").toString();
                                    operationer = resMap.get("OPERATIONER").toString();
                                    operationerId = resMap.get("OPERATIONERID").toString();
                                    if (i == 0) {
                                        OPERATIONERIDS = resMap.get("OPERATIONERID").toString();
                                        OPERATIONERNAMES = getOPERATORNAME(resMap.get("OPERATIONER").toString(), resMap.get("OPERATIONERID")
                                                .toString(), flowService);
                                    } else {
                                        OPERATIONERIDS = "," + resMap.get("OPERATIONERID").toString();
                                        OPERATIONERNAMES = ","
                                                + getOPERATORNAME(resMap.get("OPERATIONER").toString(), resMap.get("OPERATIONERID").toString(),
                                                        flowService);
                                    }
                                    OPERATORTYPE = resMap.get("LX").toString();
                                }
                                params.clear();
                                params.put("SelSQL",
                                        "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                                + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                                Map reMap = flowService.selcom(params);
                                String id = "";
                                if (reMap != null) {
                                    id = reMap.get("CORRELATIONID") == null ? "" : reMap.get("CORRELATIONID").toString();
                                }
                                //插入流程跟踪
                                saveFLOWTRACE(id, "审核", xtyhid, ryxm, OPERATIONERIDS, OPERATIONERNAMES, OPERATORTYPE, operationContent);
                                //中间流程状态
                                //String sql1 = "update " + Consts.SYSSCHEMA	+ ".T_ERP_KC_CPZCD set STATE='审核中' where CPZCDID='" + id + "'";
                                //params.clear();
                                //params.put("UpdSQL", sql1);
                                //txUpdcom(params);
                                if ("1".equals(remind)) {
                                	/**
                                    String ZDRsql = " select  OPERATOR,OPERATORNAME from (select OPERATOR,OPERATORNAME from T_SYS_FLOWTRACE where BUSINESSID='"
                                            + id + "' and  OPERATION='提交'  order by  OPERATETIME DESC ) where rownum<2";
                                    params.clear();
                                    params.put("SelSQL", ZDRsql);
                                    Map ZDRMap = flowService.selcom(params);
                                    if (ZDRMap != null) {
                                        saveSendMsg(xtyhid, ryxm, "您有单据被我审核通过！", ZDRMap.get("OPERATOR").toString(), ZDRMap.get("OPERATORNAME")
                                                .toString(), flowService);
                                    }
                                    **/

                                }

                                if (allFlag.equals("0")) {
                                    String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i));
                                    String insertSql = "insert into "
                                            + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEOperation("
                                            + "INSTANCENODEOPERATIONID,OPERATIONNAME,CORRELATION,CORRELATIONID,OPERATIONFLAG,INSTANCENODEID,FLOWINSTANCEID) "
                                            + "select '" + insNodeOperId
                                            + "',OPERATIONNAME,CORRELATION,CORRELATIONID,0,INSTANCENODEID,FLOWINSTANCEID " + "from "
                                            + Consts.SYSSCHEMA + ".  T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='"
                                            + nextNode.get(i) + "' and instanceNodeOperationId=(select max(instanceNodeOperationId) from "
                                            + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNode.get(i) + "')";
                                    params.clear();
                                    params.put("InsSQL", insertSql);
                                    txInscom(params);
                                } else if (allFlag.equals("1")) {
                                    int noDealNum = 0;
                                    if (reMap != null) {
                                        noDealNum = Integer.parseInt(reMap.get("NODEALNUM").toString());
                                    }
                                    // flowInS.closeDB();
                                    if (noDealNum == 0) {
                                        String tableName = "";
                                        String fieldName = "XTYHID";
                                        String condi = "";
                                        if (operationer.equals("1")) {
                                            tableName = " T_SYS_XTYH ";
                                            condi = " bmxxid='" + operationerId + "' ";
                                        } else if (operationer.equals("2")) {
                                            tableName = " T_SYS_XTYHjs ";
                                            condi = " xtjsid='" + operationerId + "' ";
                                        } else if (operationer.equals("3")) {
                                            tableName = " T_SYS_GZZCY ";
                                            condi = " gzzxxid='" + operationerId + "' ";
                                        }
                                        Vector allYHID = new Vector();

                                        params.clear();
                                        params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where "
                                                + condi);
                                        List reList2 = flowService.selcomList(params);
                                        maxlength = reList2.size();
                                        for (int j = 0; j < maxlength; j++) {
                                            Map resMap = (Map) reList2.get(j);
                                            allYHID.add(resMap.get(fieldName).toString());
                                        }
                                        if (allYHID.size() > 0) {
                                            for (int j = 0; j < allYHID.size(); j++) {
                                                String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i) + String.valueOf(j));
                                                String insertSql = "insert into "
                                                        + Consts.SYSSCHEMA
                                                        + ".T_SYS_INSTANCENODEOperation("
                                                        + "INSTANCENODEOPERATIONID,OPERATIONNAME,CORRELATION,CORRELATIONID,OPERATIONFLAG,INSTANCENODEID,FLOWINSTANCEID,OPERATIONUSER) "
                                                        + "select '"
                                                        + insNodeOperId
                                                        + "',OPERATIONNAME,CORRELATION,CORRELATIONID,0,INSTANCENODEID,FLOWINSTANCEID,'"
                                                        + allYHID.get(j)
                                                        + "' "
                                                        + "from "
                                                        + Consts.SYSSCHEMA
                                                        + ".T_SYS_INSTANCENODEOperation where instanceNodeOperationId=(select MAX(INSTANCENODEOPERATIONID) from "
                                                        + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNode.get(i)
                                                        + "')";

                                                params.clear();
                                                params.put("InsSQL", insertSql);
                                                txInscom(params);

                                            }
                                        } else {
                                            request.setAttribute("msg", "为设置多人处理的节点操作组添加成员！");

                                            throw new ServiceException("为设置多人处理的节点操作组添加成员！");
                                        }
                                    }
                                }
                            }

                        } else {

                            // 当前节点是最后节点
                            // 修改实例状态
                            // 删除当前节点
                            params.clear();
                            params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[1]
                                    + "' where flowInstanceid='" + flowInstanceId + "'");
                            txUpdcom(params);

                            params.clear();
                            params.put("SelSQL",
                                    "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                            + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                            Map reMap = flowService.selcom(params);

                            String tableName = "";
                            String id = "";
                            String primaryKey = "";
                            String ztbgFieldName = "";
                            String stateName = "state";
                            if (reMap != null) {
                                tableName = reMap.get("CORRELATION") == null ? "" : reMap.get("CORRELATION").toString();
                                id = reMap.get("CORRELATIONID") == null ? "" : reMap.get("CORRELATIONID").toString();
                                primaryKey = reMap.get("PRIMARYKEY") == null ? "" : reMap.get("PRIMARYKEY").toString();

                                ztbgFieldName = reMap.get("ZTBGFIELDNAME") == null ? "" : reMap.get("ZTBGFIELDNAME").toString();
                                String stateName1 = reMap.get("STATENAME") == null ? "" : reMap.get("STATENAME").toString();
                                if (!stateName1.equals("")) {
                                    stateName = stateName1;
                                }

                            }
                            String ztbgsql = "";
                            if (!ztbgFieldName.equals("")) {
                                ztbgsql = "," + ztbgFieldName + "=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                            }
                            String checksql = " select count(1) as NUM  from sys.user_tab_columns where TABLE_NAME='" + tableName.toUpperCase()
                                    + "' and COLUMN_NAME in ('AUDIT_NAME','AUDIT_ID','AUDIT_TIME')";
                            params.clear();
                            params.put("SelSQL", checksql);
                            Map checkMap = flowService.selcom(params);
                            String Num = checkMap.get("NUM").toString();
                            // 修改主记录
                            String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'"
                                    + ztbgsql + " where " + primaryKey + "='" + id + "'";
                            if ("3".equals(Num)) {
                                sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'"
                                        + ztbgsql + ",AUDIT_NAME='" + ryxm  + "'" + " ,AUDIT_ID ='" + xtyhid + "'" + " ,AUDIT_TIME =sysdate" + " where "
                                        + primaryKey + "='" + id + "'";
                            }
                            params.clear();
                            params.put("UpdSQL", sqlBus);
                            txUpdcom(params);

                            //插入流程跟踪
                            saveFLOWTRACE(id, "审核", xtyhid, ryxm, "", "流程结束", "", operationContent);
                            if ("1".equals(remind)) {
                            	/**
                                String ZDRsql = " select  OPERATOR,OPERATORNAME from (select OPERATOR,OPERATORNAME from T_SYS_FLOWTRACE where BUSINESSID='"
                                        + id + "' and  OPERATION='提交'  order by  OPERATETIME DESC ) where rownum<2";
                                params.clear();
                                params.put("SelSQL", ZDRsql);
                                Map ZDRMap = flowService.selcom(params);
                                if (ZDRMap != null) {
                                    saveSendMsg(xtyhid, ryxm, "您有单据被我审核通过！", ZDRMap.get("OPERATOR").toString(),
                                            ZDRMap.get("OPERATORNAME").toString(), flowService);
                                }
                                **/
                            }

                            if (!flowInterfaceName.equals("")) {
                                FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                request.setAttribute("currentState", businessState[1]);
                                request.setAttribute("id", id);
                                request.setAttribute("flowInterfaceName", flowInterfaceName);
                                flowInterface.afterAuditing(request, session, flowService);
                            }
                        }
                        if (operationName.equals("发文签发") || operationName.equals("编号校稿") || operationName.equals("下发")
                                || operationName.equals("收文审核") || operationName.equals("归档") || operationName.equals("收文整理")) {
                            String busState = "";
                            if (operationName.equals("发文签发")) {
                                busState = businessState[4];
                            } else if (operationName.equals("编号校稿")) {
                                busState = businessState[5];
                            } else if (operationName.equals("下发")) {
                                busState = businessState[6];
                            } else if (operationName.equals("收文审核")) {
                                busState = businessState[7];
                            } else if (operationName.equals("归档")) {
                                busState = businessState[8];
                            } else if (operationName.equals("收文整理")) {
                                busState = businessState[9];
                            }

                            params.clear();
                            params.put("SelSQL", "select CORRELATION,CORRELATIONID,PRIMARYKEY,STATENAME from " + Consts.SYSSCHEMA
                                    + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "' ");
                            Map reMap = flowService.selcom(params);

                            String tableName = "";
                            String id = "";
                            String primaryKey = "";
                            String stateName = "state";
                            if (reMap != null) {
                                tableName = reMap.get("CORRELATION").toString();
                                id = reMap.get("CORRELATIONID").toString();
                                primaryKey = reMap.get("PRIMARYKEY").toString();
                                String stateName1 = reMap.get("STATENAME") == null ? "" : reMap.get("STATENAME").toString();
                                if (!stateName1.equals("")) {
                                    stateName = stateName1;
                                }
                            }
                            // flowInS.closeDB();
                            // 修改主记录
                            if (operationName.equals("发文签发")) {
                                String sql1 = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + busState
                                        + "',signUser=(select case when signuser is null " + "then '" + ryxm + "' else signuser||'," + ryxm
                                        + "' end signuser from " + Consts.SYSSCHEMA + ".t_file where fileId='" + id + "') where " + primaryKey + "='"
                                        + id + "'";

                                params.clear();
                                params.put("UpdSQL", sql1);
                                txUpdcom(params);

                            } else {
                                String sql1 = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + busState + "' where "
                                        + primaryKey + "='" + id + "'";
                                params.clear();
                                params.put("UpdSQL", sql1);
                                txUpdcom(params);
                            }
                        }
                    } else {
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("refresh", "T");
                        return "instance";
                    }

                } else if (operationFlag.equals("-1")) {
                    // 否决

                    // 取得收文办理的节点id
                    Map params = new HashMap();
                    params.put("SelSQL", "select DISTINCT(INSTANCENODEID) from " + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODEOperation where flowInstanceId='" + flowInstanceId
                            + "' and operationName='收文办理' and instanceNodeId in " + parentsOfProcessNextNode.toString() + " ");
                    Map reMap = flowService.selcom(params);
                    if (reMap != null) {
                        nextNodeId = reMap.get("INSTANCENODEID") == null ? "" : reMap.get("INSTANCENODEID").toString();
                    }

                    // 填写审签内容
                    String updSqlno = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag
                            + ",operationUser='" + operationUser + "',operationTime="
                            + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                            + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and operationUser='" + operationUser
                            + "' and instanceNodeId='" + instanceNodeId + "' and operationFlag=0";

                    if (pNode.getAllFlag().equals("1")) {

                        params.clear();
                        params.put("UpdSQL", updSqlno);
                        txUpdcom(params);
                    } else {

                        params.clear();
                        params.put("UpdSQL", updSql);
                        txUpdcom(params);
                    }

                    // 操作如果是“收文审核”，则跳动流程当前节点到收文办理
                    if (operationName.equals("收文审核")) {

                        // 删除当前处理节点
                        params.clear();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                        txDelcom(params);

                        // 插入新的当前节点
                        String processNodeId = TimeComm.getTimeStamp("pro");
                        String insql = "insert into " + Consts.SYSSCHEMA
                                + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                + flowInstanceId + "','" + nextNodeId + "',"
                                + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";

                        params.clear();
                        params.put("InsSQL", insql);
                        txInscom(params);

                        // 插入新的当前节点操作
                        String insNodeOperId = TimeComm.getTimeStamp("ino");
                        String insertSql = "insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId) "
                                + "select '" + insNodeOperId + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId " + "from "
                                + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='" + nextNodeId
                                + "' and instanceNodeOperationId=(select max(instanceNodeOperationId) from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";

                        params.clear();
                        params.put("InsSQL", insertSql);
                        txInscom(params);
                        operationFlag = "2";
                    } else {
                        params.clear();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                        txDelcom(params);

                        // 查看是否还有其它的当前处理节点
                        int processNodeNum = 0;

                        params.clear();
                        params.put("SelSQL", "select count(*) NUM from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='"
                                + flowInstanceId + "' ");
                        Map reMap1 = flowService.selcom(params);

                        if (reMap1 != null) {
                            processNodeNum = reMap1.get("NUM") == null ? 0 : Integer.parseInt(reMap1.get("NUM").toString());
                        }

                        if (processNodeNum == 0) {
                            // 修改实例状态

                            params.clear();
                            params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[2]
                                    + "' where flowInstanceid='" + flowInstanceId + "'");
                            txUpdcom(params);

                            params.clear();
                            params.put("SelSQL",
                                    "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                            + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "' ");
                            Map reMap2 = flowService.selcom(params);

                            String tableName = "";
                            String id = "";
                            String primaryKey = "";
                            String ztbgFieldName = "";
                            String stateName = "state";
                            if (reMap2 != null) {
                                tableName = reMap2.get("CORRELATION") == null ? "" : reMap2.get("CORRELATION").toString();
                                id = reMap2.get("CORRELATIONID") == null ? "" : reMap2.get("CORRELATIONID").toString();
                                primaryKey = reMap2.get("PRIMARYKEY") == null ? "" : reMap2.get("PRIMARYKEY").toString();

                                ztbgFieldName = reMap2.get("ZTBGFIELDNAME") == null ? "" : reMap2.get("ZTBGFIELDNAME").toString();

                                String stateName1 = reMap2.get("STATENAME") == null ? "" : reMap2.get("STATENAME").toString();
                                if (!stateName1.equals("")) {
                                    stateName = stateName1;
                                }
                            }

                            String ztbgsql = "";
                            if (!ztbgFieldName.equals("")) {
                                ztbgsql = "," + ztbgFieldName + "=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                            }

                            // 修改主记录
                            String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[2] + "'"
                                    + ztbgsql + " where " + primaryKey + "='" + id + "'";

                            params.clear();
                            params.put("UpdSQL", sqlBus);
                            txUpdcom(params);

                            //插入流程跟踪
                            saveFLOWTRACE(id, "否决", xtyhid, ryxm, "", "", "", operationContent);
                            if ("1".equals(remind)) {
                                String ZDRsql = " select  OPERATOR,OPERATORNAME from (select OPERATOR,OPERATORNAME from T_SYS_FLOWTRACE where BUSINESSID='"
                                        + id + "' and  OPERATION='提交'  order by  OPERATETIME DESC ) where rownum<2";
                                params.clear();
                                params.put("SelSQL", ZDRsql);
                                Map ZDRMap = flowService.selcom(params);
                                if (ZDRMap != null) {
                                	String mesgInfo = getFlowMsg(flowService,id);
                                    saveSendMsg(xtyhid, ryxm, mesgInfo, ZDRMap.get("OPERATOR").toString(), ZDRMap.get("OPERATORNAME").toString(),
                                            flowService);
                                }

                            }

                            if (!flowInterfaceName.equals("")) {
                                FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                request.setAttribute("currentState", businessState[2]);
                                request.setAttribute("id", id);
                                flowInterface.afterAuditing(request, session, flowService);
                            }
                        } else {

                            request.setAttribute("flowInstanceId", flowInstanceId);
                            request.setAttribute("flowInstanceId", "T");
                            return "instance";
                        }
                    }
                }

            } catch (Exception ex) {

            	System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                ex.printStackTrace();
                String faultInfo = ex.toString();
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                request.setAttribute("msg", faultInfo == null ? "" : faultInfo);
                throw new ServiceException("441");
            }
        // 判断下一步节点是否设置了代理人
        if (operationFlag.equals("1") || operationFlag.equals("2") || operationFlag.equals("3")) {
            // 查找下一节点：节点操作类型为用户，并且设置的有代理权限
            String nodeId;
            if (operationFlag.equals("1")) {
                nodeId = instanceNodeId;
            } else {
                nodeId = nextNodeId;
            }
            try {
                // 取得下一步的节点代理
                Vector userAndAgent = getAgent(nodeId, operationFlag);
                // 如果有则返回用户界面，取得用户意见
                if (userAndAgent.size() != 0) {
                    request.setAttribute("userAndAgent", userAndAgent);
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowEvent", "auditing");
                    request.setAttribute("remind", remind);
                    return "userAndAgent";
                }
            } catch (Exception ex) {
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                throw new ServiceException("441");
            }

            // 查找下一步设置多人处理的节点操作者：operationerId，操作类型：operationer
            try {
                Vector selOpera = new Vector();
                int maxlength = allInstanceNodeId.size();
                for (int i = 0; i < maxlength; i++) {
                    Vector operators = getMembers(allOperationerId.get(i).toString(), allOperationer.get(i).toString());
                    Vector selOperator = new Vector();
                    selOperator.add(allInstanceNodeId.get(i));
                    selOperator.add(allOperationer.get(i));
                    selOperator.add(allOperationerId.get(i));
                    selOperator.add(operators);
                    selOpera.add(selOperator);
                }
                // 如果下一步节点操作是多人
                if (selOpera.size() != 0) {
                    request.setAttribute("selOpera", selOpera);
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowEvent", flowEvent);
                    request.setAttribute("priorNodeId", instanceNodeId);
                    request.setAttribute("prionOperationFlag", operationFlag);
                    request.setAttribute("sourceURI", sourceURI);
                    request.setAttribute("remind", remind);
                    return "selOperators";
                }
            } catch (Exception ex) {
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                throw new ServiceException("441");
            }
        }
        // 提醒节点处理者
        Vector proOpera = null;
        proOpera = getProNodeOpera(flowInstanceId, false);
        // if (proOpera != null)
        // flowRemind(proOpera, "工作提示",
        // proOpera.get(4).toString()+"等待您处理！");
        // if (currentNodeType.equals("-1")) {

        // if (currentNodeType.equals("-1")) {
        // return mapping.findForward(sourceURI);
        // } else {

        // }
        request.setAttribute("msg", "处理成功!");
        request.setAttribute("flowInstanceId", flowInstanceId);
        request.setAttribute("refresh", "T");
        request.setAttribute("flowNext", "N");

        return Consts.SUCCESS;
    }


    /**
     * 流程提交处理.
     * 
     * @param session the session
     * @param flowObject the flow object
     * 
     * @return 提交后新生成的实例id:flowInstanceId 没有生成实例则返回""
     * 
     * @throws Exception the exception
     */
    public String txQueren(HttpSession session, FlowObject flowObject) throws Exception {

        //生成审核流程实例和实例节点
        String instanceId = "";
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        jgxxid = userBean.getJGXXID();
        String tableName = flowObject.getTableName();
        String fieldName = flowObject.getFieldName();
        String id = flowObject.getId();
        String stateName = flowObject.getStateName();
        String ztbgFieldName = flowObject.getZtbgFieldName();
        try {

            StringBuffer aSQL = new StringBuffer();
            aSQL.append(" select count(*) NUM  from ");
            aSQL.append(Consts.SYSSCHEMA);
            aSQL.append(".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='");
            aSQL.append(tableName);
            aSQL.append("' and correlationId='");
            aSQL.append(id);
            aSQL.append("' and primaryKey='");
            aSQL.append(fieldName);
            aSQL.append("'");
            Map params = new HashMap();
            params.put("SelSQL", aSQL.toString());
            Map temMap = selcom(params);
            if (temMap != null && !tranCodeN(temMap.get("NUM")).equals("0")) {
                throw new Exception("该记录已经提交，正在审核！");
            }

        } catch (Exception e) {
            throw e;
        }

        String compatibleModel = getCompatiobleModel(flowObject);
        //如果找到合适模板，生成实例
        if (!compatibleModel.equals("")) {
            Vector modelNode = new Vector();
            Vector startNode = new Vector();
            Vector endNode = new Vector();
            Vector nodeOper = new Vector();
            //
            Vector modelAllFlag = new Vector();
            Vector modelOper = new Vector();
            Vector modelOperId = new Vector();

            try {
                StringBuffer aSQL = new StringBuffer();
                aSQL.append("select FLOWNODEID,NODEOPERATIONID,ALLFLAG,OPERATIONER,OPERATIONERID from " + Consts.SYSSCHEMA
                        + ".T_SYS_FLOWNODE where flowModelId='" + compatibleModel + "' order by nodeY ");
                Map params = new HashMap();
                params.put("SelSQL", aSQL.toString());
                List reList = selcomList(params);
                for (int i = 0; i < reList.size(); i++) {
                    Map resMap = (Map) reList.get(i);
                    modelNode.add(tranCodeN(resMap.get("FLOWNODEID")));
                    nodeOper.add(tranCodeN(resMap.get("NODEOPERATIONID")));
                    modelAllFlag.add(tranCodeN(resMap.get("ALLFLAG")));
                    modelOper.add(tranCodeN(resMap.get("OPERATIONER").toString()));
                    modelOperId.add(tranCodeN(resMap.get("OPERATIONERID")));

                }
                aSQL.delete(0, aSQL.toString().length());
                aSQL.append("select STARTNODEID,ENDNODEID from " + Consts.SYSSCHEMA + ".T_SYS_NODEPATH where flowModelId='" + compatibleModel + "'");
                params.put("SelSQL", aSQL.toString());
                List reList1 = selcomList(params);
                for (int i = 0; i < reList1.size(); i++) {
                    Map resMap = (Map) reList1.get(i);
                    startNode.add(resMap.get("STARTNODEID") == null ? "" : resMap.get("STARTNODEID").toString());
                    endNode.add(resMap.get("ENDNODEID") == null ? "" : resMap.get("ENDNODEID").toString());
                }

            } catch (Exception ex) {
            }
            try {
                String flowInstanceId = TimeComm.getTimeStamp("ins");
                instanceId = flowInstanceId;
                StringBuffer aSQL = new StringBuffer();
                Map params = new HashMap();
                aSQL.append(" insert into ");
                aSQL.append(Consts.SYSSCHEMA);
                aSQL.append(".T_SYS_FLOWINSTANCE(flowInstanceId,flowModelId,instanceState,correlation,correlationId,primaryKey,ztbgFieldName,stateName) values ('");
                aSQL.append(flowInstanceId);
                aSQL.append("','");
                aSQL.append(compatibleModel);
                aSQL.append("','");
                aSQL.append(state[0]);
                aSQL.append("','");
                aSQL.append(tableName);
                aSQL.append("','");
                aSQL.append(id);
                aSQL.append("','");
                aSQL.append(fieldName);
                aSQL.append("','");
                aSQL.append(ztbgFieldName);
                aSQL.append("','");
                aSQL.append(stateName);
                aSQL.append("')");
                params.put("InsSQL", aSQL.toString());
                txInscom(params);
                //增加按照上级来找节点的过滤条件， add by zhuxw
                //remind = request.getParameter("remind");
                //插入实例节点,并记录下实例节点ID
                Vector insNode = new Vector();
                for (int i = 0; i < modelNode.size(); i++) {
                    String insNodeId = TimeComm.getTimeStamp("insN" + String.valueOf(i));
                    if (i == 0) {
                        //如果是第一个节点，则把提交人作为节点操作人
                        String insNodeName = ",backFlag,jumpFlag,expandFlag,editFlag,cancleFlag,agentFlag,delayFlag,allFlag,nodeX,nodeY,operationer";
                        Map params1 = new HashMap();
                        StringBuffer aSQL1 = new StringBuffer();
                        aSQL1.append("insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE(instanceNodeId,flowInstanceId" + insNodeName
                                + ",operationerId) select '" + insNodeId + "','" + flowInstanceId + "'" + insNodeName + ",'" + xtyhid + "' from "
                                + Consts.SYSSCHEMA + ".T_SYS_FLOWNODE where flowNodeId='" + (String) modelNode.get(i) + "'");
                        params1.put("InsSQL", aSQL1.toString());
                        txInscom(params1);

                    } else {
                        //                         2       3       4         5           6        7         8       9delayTime        10     11    12
                        String insNodeName = ",backFlag,jumpFlag,expandFlag,editFlag,cancleFlag,agentFlag,delayFlag,allFlag,nodeX,nodeY,operationer,operationerId";
                        Map params1 = new HashMap();
                        StringBuffer aSQL1 = new StringBuffer();
                        aSQL1.append("insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE(instanceNodeId,flowInstanceId" + insNodeName
                                + ",delayDays) select '" + insNodeId + "','" + flowInstanceId + "'" + insNodeName + ",delayTime from "
                                + Consts.SYSSCHEMA + ".T_SYS_FLOWNODE where flowNodeId='" + (String) modelNode.get(i) + "'");
                        params1.put("InsSQL", aSQL1.toString());
                        txInscom(params1);

                    }
                    insNode.add(insNodeId);
                    //插入新的实例节点操作
                    if (modelAllFlag.get(i).equals("0")) {
                        String insNodeOperId = TimeComm.getTimeStamp("insNO" + String.valueOf(i));
                        String insertSql = "";
                        if (i == 0) {
                            insertSql = " insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                    + "instanceNodeOperationId,operationName,correlation,correlationId,"
                                    + "instanceNodeId,flowInstanceId,operationUser,operationFlag,operationTime)" + " select '" + insNodeOperId
                                    + "',operationName,correlation,correlationId,'" + insNodeId + "','" + flowInstanceId + "','" + xtyhid + "',0,"
                                    + toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + " from " + Consts.SYSSCHEMA
                                    + ".T_SYS_NODEOPERATION where nodeOperationId='" + nodeOper.get(i).toString() + "'";
                        } else {
                            insertSql = " insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                    + "instanceNodeOperationId,operationName,correlation,correlationId,"
                                    + "instanceNodeId,flowInstanceId,operationFlag)" + " select '" + insNodeOperId
                                    + "',operationName,correlation,correlationId,'" + insNodeId + "','" + flowInstanceId + "',0 from "
                                    + Consts.SYSSCHEMA + ".T_SYS_NODEOPERATION where nodeOperationId='" + nodeOper.get(i).toString() + "'";
                        }
                        try {
                            Map params2 = new HashMap();
                            params2.put("InsSQL", insertSql);
                            txInscom(params2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            throw ex;
                        }
                    } else if (modelAllFlag.get(i).toString().equals("1")) {
                        String tableName1 = "";
                        String fieldName1 = "XTYHID";
                        String condi1 = "";
                        if (modelOper.get(i).toString().equals("1")) {
                            tableName1 = Consts.SYSSCHEMA + ".T_SYS_XTYH ";
                            condi1 = " bmxxid='" + modelOperId.get(i).toString() + "' ";
                        } else if (modelOper.get(i).toString().equals("2")) {
                            tableName1 = Consts.SYSSCHEMA + ".T_SYS_XTYHjs ";
                            condi1 = " xtjsid='" + modelOperId.get(i).toString() + "' ";
                        } else if (modelOper.get(i).toString().equals("3")) {
                            tableName1 = Consts.SYSSCHEMA + ".T_SYS_GZZCY ";
                            condi1 = " gzzxxid='" + modelOperId.get(i).toString() + "' ";
                        }
                        Vector allYHID = new Vector();
                        params.clear();
                        params.put("SelSQL", "select " + fieldName1 + " from " + Consts.SYSSCHEMA + "." + tableName1 + " where " + condi1);
                        List reList = selcomList(params);
                        for (int k = 0; k < reList.size(); k++) {
                            Map resMap = (Map) reList.get(k);
                            allYHID.add(tranCodeN(resMap.get(fieldName1)));

                        }
                         
                        if (allYHID.size() > 0) {
                            for (int j = 0; j < allYHID.size(); j++) {
                                String insNodeOperId = TimeComm.getTimeStamp("insNO" + String.valueOf(i) + String.valueOf(j));
                                String insertSql = "insert into "
                                        + Consts.SYSSCHEMA
                                        + ".T_SYS_INSTANCENODEOperation("
                                        + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId,operationUser) "
                                        + "select '" + insNodeOperId + "',operationName,correlation,correlationId,0,'" + insNodeId + "','"
                                        + flowInstanceId + "','" + allYHID.get(j) + "' " + "from " + Consts.SYSSCHEMA
                                        + ".T_SYS_NODEOPERATION where nodeOperationId='" + nodeOper.get(i).toString() + "'";
                                try {
                                    params.clear();
                                    params.put("InsSQL", insertSql);
                                    txInscom(params);

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    throw ex;
                                }
                            }
                        } else {
                            throw new Exception("为设置多人处理的节点操作者添加成员！");
                        }
                    }
                }

                //插入路径
                for (int i = 0; i < startNode.size(); i++) {
                    String insPathId = TimeComm.getTimeStamp(String.valueOf(i));
                    aSQL.delete(0, aSQL.toString().length());
                    params.clear();
                    aSQL.append(" insert into ");
                    aSQL.append(Consts.SYSSCHEMA);
                    aSQL.append(".T_SYS_INSTANCENODEPath(instanceNodePathId,flowInstanceId,startNodeId,endNodeId) values (");
                    aSQL.append("'");
                    aSQL.append(insPathId);
                    aSQL.append("','");
                    aSQL.append(flowInstanceId);
                    aSQL.append("','");
                    aSQL.append((String) insNode.get(modelNode.indexOf(startNode.get(i))));
                    aSQL.append("','");
                    aSQL.append((String) insNode.get(modelNode.indexOf(endNode.get(i))));
                    aSQL.append("'");
                    aSQL.append(")");
                    params.put("InsSQL", aSQL.toString());
                    txInscom(params);
                }

                //生成当前节点
                //查找第一个节点的直接后续节点
                for (int i = 0; i < startNode.size(); i++) {
                    if (startNode.get(i).equals(modelNode.get(0))) {
                        String processNodeId = TimeComm.getTimeStamp("p" + i);
                        String comeTime = toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                        String insSql = "insert into " + Consts.SYSSCHEMA
                                + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values ('" + processNodeId + "','"
                                + flowInstanceId + "','" + (String) insNode.get(modelNode.indexOf(endNode.get(i))) + "'," + comeTime + ")";
                        params.clear();
                        params.put("InsSQL", insSql);
                        txInscom(params);

                    }
                }
                //修改业务数据状态
                params.clear();
                params.put("UpdSQL", "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[0] + "' where "
                        + fieldName + "='" + id + "'");
                txUpdcom(params);

            } catch (Exception ex) {
                System.err.println("Error in FlowBean insert FlowInstance!");
                ex.printStackTrace();
                throw ex;
            }

        } else {
            //修改业务数据状态
            String ztbgsql = "";
            if (!ztbgFieldName.equals("")) {
                ztbgsql = "," + ztbgFieldName + "=" + this.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
            }
            Map params = new HashMap();
            params.clear();
            params.put("UpdSQL", "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'" + ztbgsql
                    + " where " + fieldName + "='" + id + "'");
            txUpdcom(params);
        }
        return instanceId;
    }


    /**
     * 取得当前用户的给定table，业务类型（提交时传入的tableName，businessType）的待处理记录的id串，使用方法如：
     * 发文模块提交时的tableName:t_file，业务类型：发文管理，id：fileId()严格区分大小写,则的待处理发文的sql
     * 条件：fileId in flowBean.getDealingCondition(session, t_file, "发文管理");
     * 与public String getDealingCondition(HttpSession session, String tableName)
     * 方法不同的是同一条业务数据可能需要多次用到审批流程，而又要区别不同的待处理记录
     * 
     * @param session the session
     * @param tableName the table name
     * @param businessType the business type
     * 
     * @return the dealing condition
     */
    public String getDealingCondition(HttpSession session, String tableName, String businessType) {

        String resu = "1=1";
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        resu = "(select CORRELATIONID from " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName
                + "' and flowModelId in (select flowModelId from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where businessType='" + businessType
                + "') and flowInstanceId in " + "(select flowInstanceId from " + Consts.SYSSCHEMA + ".V_processNode_instanceNode where "
                + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') or (operationer='1' and operationerId ='" + bmxxid
                + "') or (operationer='2' and operationerId in (" + xtjsids + ")) or (operationer='3' and operationerId in (" + gzzxxids + "))))"
                + " or (allFlag=1 and exists (select instanceNodeId from " + " " + Consts.SYSSCHEMA
                + ". T_SYS_INSTANCENODEOperation where operationUser='" + xtyhid
                + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId))))";
        return resu;
    }


    /**
     * 取得当前用户的给定table（提交时传入的tableName）的待处理记录的id串，使用方法如：
     * 发文模块提交时的tableName:t_file，id：fileId()严格区分大小写,则的待处理发文的sql
     * 条件：fileId in flowBean.getDealingCondition(session, t_file);
     * 
     * @param session the session
     * @param tableName the table name
     * 
     * @return the dealing condition
     */
    public String getDealingCondition(HttpSession session, String tableName) {

        String resu = "1=1";
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        resu = "(select CORRELATIONID from " + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName
                + "' and flowInstanceId in " + "(select flowInstanceId from " + Consts.SYSSCHEMA + ". V_processNode_instanceNode where "
                + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') " + "or (operationer='1' and operationerId ='" + bmxxid
                + "') " + "or (operationer='2' and operationerId in (" + xtjsids + ")) " + "or (operationer='3' and operationerId in (" + gzzxxids
                + "))))" + " or (allFlag=1 and exists (select instanceNodeId from  T_SYS_INSTANCENODEOperation " + "where operationUser='" + xtyhid
                + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId))))";
        return resu;
    }


    /**
     * Gets the dealing condition.
     * 
     * @param session the session
     * @param tableName the table name
     * @param operType the oper type
     * 
     * @return the dealing condition
     */
    public String getDealingCondition(HttpSession session, String tableName, int operType) {

        String resu = "1=1";
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        if (operType == 1) {
            //返回收文阅知条件
            resu = "(select CORRELATIONID from " + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName
                    + "' and flowInstanceId in " + "(select flowInstanceId from " + Consts.SYSSCHEMA
                    + ". T_SYS_INSTANCENODEOperation where operationName='收文阅知'"
                    + "and flowInstanceId in (select flowInstanceId from V_processNode_instanceNode where " + "(operationer='0' and operationerId='"
                    + xtyhid + "') or (operationer='1' and operationerId ='" + bmxxid + "') or (operationer='2' and operationerId in (" + xtjsids
                    + ")) or (operationer='3' and operationerId in (" + gzzxxids + ")))))";
        } else {
            resu = "(select CORRELATIONID from " + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName
                    + "' and flowInstanceId in " + "(select flowInstanceId from " + Consts.SYSSCHEMA + ". V_processNode_instanceNode where "
                    + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') or (operationer='1' and operationerId ='" + bmxxid
                    + "') or (operationer='2' and operationerId in (" + xtjsids + ")) or (operationer='3' and operationerId in (" + gzzxxids + "))))"
                    + " or (allFlag=1 and exists (select instanceNodeId from " + "" + Consts.SYSSCHEMA
                    + ". T_SYS_INSTANCENODEOperation where operationUser='" + xtyhid
                    + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId))))";
        }
        return resu;
    }


    /**
     * 取得当前用户的给定业务数据（tableName, id）的当前处理节点，主要用于业务数据中需要用到
     * 当前处理节点的属性，如修改业务数据权限.
     * 
     * @param session the session
     * @param tableName the table name
     * @param id the id
     * 
     * @return ProcessNode
     * 
     * @throws Exception the exception
     */
    public ProcessNode getProcessNode(HttpSession session, String tableName, String id) throws Exception {

        ProcessNode proNode = null;
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        String sql = "select * from " + Consts.SYSSCHEMA + ".V_processNode_instanceNode where " + "flowInstanceId in (select flowInstanceId from "
                + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName + "' and correlationId='" + id
                + "') and " + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') or (operationer='1' and operationerId ='"
                + bmxxid + "') or (operationer='2' and operationerId in (" + xtjsids + ")) or (operationer='3' and operationerId in (" + gzzxxids
                + ")))" + " or (allFlag=1 and exists (select instanceNodeId from " + " " + Consts.SYSSCHEMA
                + ". T_SYS_INSTANCENODEOperation where operationUser='" + xtyhid
                + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId)))";

        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            Map resMap = selcom(params);

            if (resMap != null) {
                proNode = new ProcessNode();
                proNode.setInstanceNodeId(resMap.get("INSTANCENODEID") == null ? "" : resMap.get("INSTANCENODEID").toString());
                proNode.setFlowInstanceId(resMap.get("FLOWINSTANCEID") == null ? "" : resMap.get("FLOWINSTANCEID").toString());
                proNode.setBackFlag(resMap.get("BACKFLAG") == null ? "" : resMap.get("BACKFLAG").toString());
                proNode.setJumpFlag(resMap.get("JUMPFLAG") == null ? "" : resMap.get("JUMPFLAG").toString());
                proNode.setExpandFlag(resMap.get("EXPANDFLAG") == null ? "" : resMap.get("EXPANDFLAG").toString());
                proNode.setEditFlag(resMap.get("EDITFLAG") == null ? "" : resMap.get("EDITFLAG").toString());
                proNode.setCancleFlag(resMap.get("CANCLEFLAG") == null ? "" : resMap.get("CANCLEFLAG").toString());
                proNode.setAgentFlag(resMap.get("AGENTFLAG") == null ? "" : resMap.get("AGENTFLAG").toString());
                proNode.setDelayFlag(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setDelayTime(resMap.get("DELAYTIME") == null ? "" : resMap.get("DELAYTIME").toString());
                proNode.setAllFlag(resMap.get("ALLFLAG") == null ? "" : resMap.get("ALLFLAG").toString());
                proNode.setRemark(resMap.get("REMARK") == null ? "" : resMap.get("REMARK").toString());
                proNode.setNodeX(resMap.get("NODEX") == null ? 0 : Integer.parseInt(resMap.get("NODEX").toString()));
                proNode.setNodeY(resMap.get("NODEY") == null ? 0 : Integer.parseInt(resMap.get("NODEY").toString()));
                proNode.setOperationer(resMap.get("OPERATIONER") == null ? "" : resMap.get("OPERATIONER").toString());
                proNode.setOperationerId(resMap.get("OPERATIONERID") == null ? "" : resMap.get("OPERATIONERID").toString());
            }
            if (proNode != null) {
                params.clear();
                params.put("SelSQL", "select OPERATIONNAME from " + Consts.SYSSCHEMA
                        + ". T_SYS_INSTANCENODEOperation where OPERATIONFLAG=0 and instanceNodeId='" + proNode.getInstanceNodeId() + "' ");
                Map resMap1 = selcom(params);
                if (resMap1 != null) {
                    proNode.setOperationName(resMap1.get("OPERATIONNAME") == null ? "" : resMap1.get("OPERATIONNAME").toString());
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception("得到当前处理节点出错！");
        }
        return proNode;
    }


    /**
     * 取得当前用户的给定业务数据（tableName, id）的当前处理节点，主要用于业务数据中需要用到
     * 当前处理节点的属性，如流程中设置的修改业务数据属性.
     * 
     * @param session the session
     * @param instanceNodeId the instance node id
     * 
     * @return the process node
     * 
     * @throws Exception the exception
     */
    public ProcessNode getProcessNode(HttpSession session, String instanceNodeId) throws Exception {

        ProcessNode proNode = null;
        String sql = "select * from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId='" + instanceNodeId + "'";

        Map params = new HashMap();
        params.put("SelSQL", sql.toString());
        Map resMap = selcom(params);

        try {
            if (resMap != null) {
                proNode = new ProcessNode();
                proNode.setInstanceNodeId(resMap.get("INSTANCENODEID") == null ? "" : resMap.get("INSTANCENODEID").toString());
                proNode.setFlowInstanceId(resMap.get("FLOWINSTANCEID") == null ? "" : resMap.get("FLOWINSTANCEID").toString());
                proNode.setBackFlag(resMap.get("BACKFLAG") == null ? "" : resMap.get("BACKFLAG").toString());
                proNode.setJumpFlag(resMap.get("JUMPFLAG") == null ? "" : resMap.get("JUMPFLAG").toString());
                proNode.setExpandFlag(resMap.get("EXPANDFLAG") == null ? "" : resMap.get("EXPANDFLAG").toString());
                proNode.setEditFlag(resMap.get("EDITFLAG") == null ? "" : resMap.get("EDITFLAG").toString());
                proNode.setCancleFlag(resMap.get("CANCLEFLAG") == null ? "" : resMap.get("CANCLEFLAG").toString());
                proNode.setAgentFlag(resMap.get("AGENTFLAG") == null ? "" : resMap.get("AGENTFLAG").toString());
                proNode.setDelayFlag(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setDelayTime(resMap.get("DELAYTIME") == null ? "" : resMap.get("DELAYTIME").toString());
                proNode.setAllFlag(resMap.get("ALLFLAG") == null ? "" : resMap.get("ALLFLAG").toString());
                proNode.setRemark(resMap.get("REMARK") == null ? "" : resMap.get("REMARK").toString());
                proNode.setNodeX(resMap.get("NODEX") == null ? 0 : Integer.parseInt(resMap.get("NODEX").toString()));
                proNode.setNodeY(resMap.get("NODEY") == null ? 0 : Integer.parseInt(resMap.get("NODEY").toString()));
                proNode.setOperationer(resMap.get("OPERATIONER") == null ? "" : resMap.get("OPERATIONER").toString());
                proNode.setOperationerId(resMap.get("OPERATIONERID") == null ? "" : resMap.get("OPERATIONERID").toString());
            }
            if (proNode != null) {
                params.clear();
                params.put("SelSQL", "select operationName from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='"
                        + proNode.getInstanceNodeId() + "' and OPERATIONFLAG=0 ");
                Map resMap1 = selcom(params);
                if (resMap1 != null) {
                    proNode.setOperationName(resMap.get("OPERATIONNAME") == null ? "" : resMap.get("OPERATIONNAME").toString());
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception("得到当前处理节点出错！");
        }
        return proNode;
    }


    /**
     * 取得正在进行或者处理完成的流程处理过程.
     * 
     * @param tableName the table name
     * @param fieldName the field name
     * @param id the id
     * 
     * @return String 处理意见
     */
    public String getSQYJ(String tableName, String fieldName, String id) {

        StringBuffer resu = new StringBuffer();
        String sql = "select OPERATIONNAME,OPERATIONFLAG,YHM,OPERATIONTIME," + "OPERATIONCONTENT from " + Consts.SYSSCHEMA
                + ".T_SYS_INSTANCENODEOperation," + Consts.SYSSCHEMA + ".T_SYS_XTYH where " + "flowInstanceId in (select flowInstanceId from "
                + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE " + "where correlation='" + tableName + "' and correlationId='" + id + "' and primaryKey='"
                + fieldName + "' and instanceState in ('进行','完成')) " + "and operationUser=xtyhid and operationName not in ('start','end')";
        char enter = 13;
        char newline = 10;

        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                resu.append("处理用户：");
                resu.append(resMap.get("YHM") == null ? "" : resMap.get("YHM").toString());
                resu.append(enter);
                resu.append(newline);
                resu.append("处理时间：");
                resu.append(resMap.get("OPERATIONTIME") == null ? "" : resMap.get("OPERATIONTIME").toString());
                resu.append(" ");
                resu.append(resMap.get("OPERATIONTIME") == null ? "" : resMap.get("OPERATIONTIME").toString());
                resu.append(enter);
                resu.append(newline);
                resu.append("处理结果：");
                String operateResu = "";
                String operationFlag = resMap.get("OPERATIONFLAG") == null ? "" : resMap.get("OPERATIONFLAG").toString();
                if (operationFlag.trim().equals("0")) {
                    operateResu = "";
                } else if (operationFlag.trim().equals("1")) {
                    operateResu = "通过";
                } else if (operationFlag.trim().equals("2")) {
                    operateResu = "回退";
                } else if (operationFlag.trim().equals("3")) {
                    operateResu = "跳签";
                } else if (operationFlag.trim().equals("-1")) {
                    operateResu = "否决";
                }
                resu.append(operateResu);
                resu.append(enter);
                resu.append(newline);
                resu.append("处理意见：");
                resu.append(resMap.get("OPERATIONCONTENT") == null ? "" : resMap.get("OPERATIONCONTENT").toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resu.toString();
    }


    /**
     * 取得给定提交时提供的输入项表、字段名称、id字段值、业务类型、特定节点操作的流程输入意见
     * 返回Vector 格式 resu
     * resu(0)：操作者姓名
     * resu(1)：操作时间(2003-10-10 10:10:10)
     * resu(2)：处理结果（通过、跳签、退签等）
     * resu(3)：处理意见
     * 如果有多个处理者或多个节点则依顺序累加.
     * 
     * @param tableName the table name
     * @param fieldName the field name
     * @param id the id
     * @param businessType the business type
     * @param operateName the operate name
     * 
     * @return the SQYJ
     */
    public Vector getSQYJ(String tableName, String fieldName, String id, String businessType, String operateName) {

        Vector resu = new Vector();
        String sql = "select DISTINCT(XM) XM, OPERATIONCONTENT, OPERATIONTIME, OPERATIONFLAG from " + "" + Consts.SYSSCHEMA
                + ".T_SYS_INSTANCENODEOperation," + Consts.SYSSCHEMA + ".T_SYS_XTYH," + Consts.SYSSCHEMA + ".T_SYS_RYXX where operationFlag not in (0,2) "
                + "and operationUser=xtyhid and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and " + "operationName='" + operateName + "' and flowInstanceId in "
                + "(select flowInstanceId from " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where correlation='" + tableName + "' " + "and primaryKey='"
                + fieldName + "' and correlationId='" + id
                + "' and instanceState in ('进行','完成') and flowModelId in (select flowModelId from T_SYS_FLOWMODEL where businessType='" + businessType
                + "')) ";

        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                resu.add(resMap.get("XM") == null ? "" : resMap.get("XM").toString());
                //resu.add(superBean.getDateField("operationTime") + " " + superBean.getTimeField("operationTime"));
                resu.add(resMap.get("OPERATIONTIME") == null ? "" : resMap.get("OPERATIONTIME").toString());
                String operateResu = "";

                String operationFlag = resMap.get("OPERATIONFLAG") == null ? "" : resMap.get("OPERATIONFLAG").toString();
                if (operationFlag.trim().equals("0")) {
                    operateResu = "";
                } else if (operationFlag.trim().equals("1")) {
                    operateResu = "通过";
                } else if (operationFlag.trim().equals("2")) {
                    operateResu = "回退";
                } else if (operationFlag.trim().equals("3")) {
                    operateResu = "跳签";
                } else if (operationFlag.trim().equals("-1")) {
                    operateResu = "否决";
                }
                resu.add(operateResu);
                resu.add(resMap.get("OPERATIONCONTENT") == null ? "" : resMap.get("OPERATIONCONTENT").toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resu;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#toTimeStampSQL(java.lang.String)
     */
    public String toTimeStampSQL(String timeStamp) {

        if (timeStamp == null) {
            return timeStamp;
        }
        if (Consts.DBTYPE.equals("DB2")) {
            return "'" + timeStamp + "'";
        } else if (Consts.DBTYPE.equals("ORACLE")) {
            return "to_date('" + timeStamp + "','yyyy-mm-dd hh24:mi:ss')";
        } else if(Consts.DBTYPE.equals("MSSQL")){
        	return " convert(varchar,getdate(),20) ";
        }
        return "'" + timeStamp + "'";
    }


    /**
     * 判断当前用户是否是待处理用户.
     * 
     * @param proNode the pro node
     * @param user the user
     * 
     * @return boolean
     * 
     * @throws Exception the exception
     */
    public boolean isDealUser(ProcessNode proNode, UserBean user) throws Exception {

        String sql = "select count(*) DELNUM from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where " + "instanceNodeId='"
                + proNode.getInstanceNodeId() + "' and operationFlag=0 and operationUser='" + user.getXTYHID() + "'";

        Map params = new HashMap();
        params.put("SelSQL", sql.toString());
        Map reMap = selcom(params);

        boolean resu = false;
        try {
            if (reMap != null) {
                resu = !((String) reMap.get("DELNUM")).equals("0");

            }
        } catch (Exception ex) {
            throw ex;
        }
        return resu;
    }


    /**
     * 取得指定的table、id、业务类型的当前节点，没有则返回null.
     * 
     * @param tableName the table name
     * @param id the id
     * @param businessType the business type
     * 
     * @return the current node
     * 
     * @throws Exception the exception
     */
    public ProcessNode getCurrentNode(String tableName, String id, String businessType) throws Exception {

        ProcessNode proNode = null;
        String sql = "select * from "
                + Consts.SYSSCHEMA
                + ".V_processNode_instanceNode where "
                + "exists (select flowInstanceId from "
                + Consts.SYSSCHEMA
                + ".T_SYS_FLOWINSTANCE where "
                + "instanceState='进行' and correlation='"
                + tableName
                + "' and correlationId='"
                + id
                + "' and exists (select flowModelId from "
                + Consts.SYSSCHEMA
                + ".T_SYS_FLOWMODEL where businessType='"
                + businessType
                + "' and T_SYS_FLOWINSTANCE.flowModelId=T_SYS_FLOWINSTANCE.flowModelId ) and V_processNode_instanceNode.flowInstanceId=T_SYS_FLOWINSTANCE.flowInstanceId)";
        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            Map resMap = selcom(params);
            if (resMap != null) {
                proNode = new ProcessNode();
                proNode.setInstanceNodeId(resMap.get("INSTANCENODEID") == null ? "" : resMap.get("INSTANCENODEID").toString());
                proNode.setFlowInstanceId(resMap.get("FLOWINSTANCEID") == null ? "" : resMap.get("FLOWINSTANCEID").toString());
                proNode.setBackFlag(resMap.get("BACKFLAG") == null ? "" : resMap.get("BACKFLAG").toString());
                proNode.setJumpFlag(resMap.get("JUMPFLAG") == null ? "" : resMap.get("JUMPFLAG").toString());
                proNode.setExpandFlag(resMap.get("EXITFLAG") == null ? "" : resMap.get("EXITFLAG").toString());
                proNode.setEditFlag(resMap.get("EXITFLAG") == null ? "" : resMap.get("EDITFLAG").toString());
                proNode.setCancleFlag(resMap.get("CANCLEFLAG") == null ? "" : resMap.get("CANCLEFLAG").toString());
                proNode.setAgentFlag(resMap.get("AGENTFLAG") == null ? "" : resMap.get("AGENTFLAG").toString());
                proNode.setDelayFlag(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setDelayTime(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setAllFlag(resMap.get("ALLFLAG") == null ? "" : resMap.get("ALLFLAG").toString());
                proNode.setRemark(resMap.get("REMARK") == null ? "" : resMap.get("REMARK").toString());
                proNode.setNodeX(resMap.get("NODEX") == null ? 0 : Integer.parseInt(resMap.get("NODEX").toString()));
                proNode.setNodeY(resMap.get("NODEY") == null ? 0 : Integer.parseInt(resMap.get("NODEY").toString()));
                proNode.setOperationer(resMap.get("OPERATIONER") == null ? "" : resMap.get("OPERATIONER").toString());
                proNode.setOperationerId(resMap.get("OPERATIONERID") == null ? "" : resMap.get("OPERATIONERID").toString());
            }
            int nodeHeight = 88;
            if (proNode != null) {
                sql = "select OPERATIONNAME from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='"
                        + proNode.getInstanceNodeId() + "' with ur";
                params.clear();
                params.put("SelSQL", sql.toString());
                Map resMap1 = selcom(params);
                if (resMap1 != null) {
                    proNode.setOperationName(resMap.get("OPERATIONNAME") == null ? "" : resMap.get("OPERATIONNAME").toString());
                }
                sql = "select MAX(NODEY) NODEY from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='" + proNode.getFlowInstanceId()
                        + "' with ur ";
                params.clear();
                params.put("SelSQL", sql.toString());
                Map resMap2 = selcom(params);
                if (resMap2 != null) {
                    proNode.setTotalLevel((Integer.parseInt(resMap.get("NODEY").toString()) - 30) / nodeHeight);
                }
                proNode.setNodeLevel((proNode.getNodeY() - 30) / nodeHeight);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("得到当前处理节点出错！");
        }
        return proNode;
    }


    /**
     * 取得当前用户的给定table，业务类型（提交时传入的tableName，businessType）的待处理记录的id串，使用方法如：
     * 发文模块提交时的tableName:t_file，业务类型：发文管理，id：fileId()严格区分大小写,则的待处理发文的sql
     * 条件：fileId in flowBean.getDealingCondition(session, t_file, "发文管理");
     * 与public String getDealingCondition(HttpSession session, String tableName)
     * 方法不同的是同一条业务数据可能需要多次用到审批流程，而又要区别不同的待处理记录
     * 与public String getDealingCondition(HttpSession session, String tableName, String businessType)
     * 方法不同的是该方法返回的是id串('@#$','',...),而不是形如(select  correlationId from ...)的sql段
     * 
     * @param session the session
     * @param tableName the table name
     * @param businessType the business type
     * 
     * @return the dealing condition value
     */
    public String getDealingConditionValue(HttpSession session, String tableName, String businessType) {

        StringBuffer resu = new StringBuffer("('#$%'");
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        String sql = "select CORRELATIONID from " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + tableName
                + "' and flowModelId in (select flowModelId from " + Consts.SYSSCHEMA + ".T_SYS_FLOWMODEL where businessType='" + businessType
                + "') and flowInstanceId in " + "(select flowInstanceId from " + Consts.SYSSCHEMA + ".V_processNode_instanceNode where "
                + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') or (operationer='1' and operationerId ='" + bmxxid
                + "') or (operationer='2' and operationerId in (" + xtjsids + ")) or (operationer='3' and operationerId in (" + gzzxxids + "))))"
                + " or (allFlag=1 and exists (select instanceNodeId from " + "T_SYS_INSTANCENODEOperation where operationUser='" + xtyhid
                + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId))) ";

        try {

            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                resu.append(",'");
                resu.append((String) resMap.get("CORRELATIONID"));
                resu.append("'");
            }
            //superBean.closeDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resu.append(")");
        return resu.toString();
    }


    /**
     * 取得当前用户的给定流程（flowInstanceId, type）的当前处理节点，主要用于业务数据中需要用到
     * 当前处理节点的属性，如修改业务数据权限.
     * 
     * @param session the session
     * @param flowInstanceId the flow instance id
     * @param type the type
     * 
     * @return ProcessNode
     * 
     * @throws Exception the exception
     */
    public ProcessNode getProcessNode(HttpSession session, String flowInstanceId, int type) throws Exception {

        ProcessNode proNode = null;
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String bmxxid = userBean.getBMXXID();
        String xtjsids = userBean.getXTJSIDS();
        String gzzxxids = userBean.getGZZXXIDS();
        String sql = "select * from " + Consts.SYSSCHEMA + ".V_processNode_instanceNode where " + "flowInstanceId='" + flowInstanceId + "' and "
                + "(allFlag=0 and ((operationer='0' and operationerId='" + xtyhid + "') or (operationer='1' and operationerId ='" + bmxxid
                + "') or (operationer='2' and operationerId in (" + xtjsids + ")) or (operationer='3' and operationerId in (" + gzzxxids + ")))"
                + " or (allFlag=1 and exists (select instanceNodeId from " + "" + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where operationUser='"
                + xtyhid + "' and operationFlag=0 and V_processNode_instanceNode.instanceNodeId=T_SYS_INSTANCENODEOperation.instanceNodeId))) ";
        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            Map resMap = selcom(params);
            if (resMap != null) {
                proNode = new ProcessNode();
                proNode.setInstanceNodeId(resMap.get("INSTANCENODEID") == null ? "" : resMap.get("INSTANCENODEID").toString());
                proNode.setFlowInstanceId(resMap.get("FLOWINSTANCEID") == null ? "" : resMap.get("FLOWINSTANCEID").toString());
                proNode.setBackFlag(resMap.get("BACKFLAG") == null ? "" : resMap.get("BACKFLAG").toString());
                proNode.setJumpFlag(resMap.get("JUMPFLAG") == null ? "" : resMap.get("JUMPFLAG").toString());
                proNode.setExpandFlag(resMap.get("EXITFLAG") == null ? "" : resMap.get("EXITFLAG").toString());
                proNode.setEditFlag(resMap.get("EXITFLAG") == null ? "" : resMap.get("EDITFLAG").toString());
                proNode.setCancleFlag(resMap.get("CANCLEFLAG") == null ? "" : resMap.get("CANCLEFLAG").toString());
                proNode.setAgentFlag(resMap.get("AGENTFLAG") == null ? "" : resMap.get("AGENTFLAG").toString());
                proNode.setDelayFlag(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setDelayTime(resMap.get("DELAYFLAG") == null ? "" : resMap.get("DELAYFLAG").toString());
                proNode.setAllFlag(resMap.get("ALLFLAG") == null ? "" : resMap.get("ALLFLAG").toString());
                proNode.setRemark(resMap.get("REMARK") == null ? "" : resMap.get("REMARK").toString());
                proNode.setNodeX(resMap.get("NODEX") == null ? 0 : Integer.parseInt(resMap.get("NODEX").toString()));
                proNode.setNodeY(resMap.get("NODEY") == null ? 0 : Integer.parseInt(resMap.get("NODEY").toString()));
                proNode.setOperationer(resMap.get("OPERATIONER") == null ? "" : resMap.get("OPERATIONER").toString());
                proNode.setOperationerId(resMap.get("OPERATIONERID") == null ? "" : resMap.get("OPERATIONERID").toString());

            }
            if (proNode != null) {
                params.clear();
                params.put("SelSQL", "select OPERATIONNAME from " + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEOperation where  OPERATIONFLAG=0 and instanceNodeId='" + proNode.getInstanceNodeId() + "'");
                Map resMap1 = selcom(params);
                if (resMap1 != null) {
                    proNode.setOperationName((String) resMap1.get("OPERATIONNAME"));
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception("得到当前处理节点出错！");
        }
        return proNode;
    }


    /**
     * 取得给定提交时提供的输入项表、字段名称、id字段值、业务类型、特定节点操作的流程输入意见
     * 返回Vector 格式 resu
     * resu(0)：操作者姓名
     * resu(1)：操作时间(2003-10-10 10:10:10)
     * resu(2)：处理结果（通过、跳签、退签等）
     * resu(3)：处理意见
     * 如果有多个处理者或多个节点则依顺序累加.
     * 
     * @param tableName the table name
     * @param fieldName the field name
     * @param id the id
     * @param businessType the business type
     * 
     * @return the SQYJ
     */
    public List getSQYJ(String tableName, String fieldName, String id, String businessType) {

        Vector resu = new Vector();
        String sql = "select INSTANCENODEOPERATIONID,OPERATIONNAME,CORRELATION,"
                + "CORRELATIONID,OPERATIONFLAG,OPERATIONUSER,OPERATIONTIME,OPERATIONCONTENT" + ",INSTANCENODEID,FLOWINSTANCEID,YHM,XM,BMMC from "
                + ""
                + Consts.SYSSCHEMA
                + ".T_SYS_INSTANCENODEOperation,"
                + Consts.SYSSCHEMA
                + ".T_SYS_XTYH,"
                + Consts.SYSSCHEMA
                + ".T_SYS_RYXX,"
                + Consts.SYSSCHEMA
                + ".T_SYS_BMXX "
                + "where operationUser=xtyhid and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and "
                + "T_SYS_XTYH.bmxxid=T_SYS_BMXX.bmxxid and flowInstanceId in "
                + "(select flowInstanceI"
                + ""
                + "d from "
                + Consts.SYSSCHEMA
                + ".T_SYS_FLOWINSTANCE where correlation='"
                + tableName
                + "' "
                + "and primaryKey='"
                + fieldName
                + "' and correlationId='"
                + id
                + "' and instanceState in ('进行','完成') and flowModelId in (select flowModelId from "
                + Consts.SYSSCHEMA
                + ".T_SYS_FLOWMODEL where businessType='" + businessType + "')) order by operationTime ";
        try {
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                NodeOperation nodeOper = new NodeOperation();
                nodeOper.setInstanceNodeoperationId(resMap.get("INSTANCENODEOPERATIONID") == null ? "" : resMap.get("INSTANCENODEOPERATIONID")
                        .toString());
                nodeOper.setOperationName(resMap.get("OPERATIONNAME") == null ? "" : resMap.get("OPERATIONNAME").toString());
                nodeOper.setCorrelation(resMap.get("CORRELATION") == null ? "" : resMap.get("CORRELATION").toString());
                nodeOper.setCorrelationId(resMap.get("CORRELATIONID") == null ? "" : resMap.get("CORRELATIONID").toString());
                nodeOper.setOperationFlag(resMap.get("OPERATIONFLAG") == null ? 0 : Integer.parseInt(resMap.get("OPERATIONFLAG").toString()));
                String operateResu = "";
                String operationFlag = resMap.get("OPERATIONFLAG") == null ? "" : resMap.get("OPERATIONFLAG").toString();
                if (operationFlag.trim().equals("0")) {
                    operateResu = "";
                } else if (operationFlag.trim().equals("1")) {
                    operateResu = "通过";
                } else if (operationFlag.trim().equals("2")) {
                    operateResu = "回退";
                } else if (operationFlag.trim().equals("3")) {
                    operateResu = "跳签";
                } else if (operationFlag.trim().equals("-1")) {
                    operateResu = "否决";
                }
                nodeOper.setOperateResult(operateResu);
                nodeOper.setOperationUser(resMap.get("OPERATIONUSER") == null ? "" : resMap.get("OPERATIONUSER").toString());
                nodeOper.setOperatorYHM(resMap.get("YHM") == null ? "" : resMap.get("YHM").toString());
                nodeOper.setOperatorXM(resMap.get("XM") == null ? "" : resMap.get("XM").toString());
                nodeOper.setOperationTime(resMap.get("OPERATIONTIME") == null ? "" : resMap.get("OPERATIONTIME").toString());
                nodeOper.setOperationContent(resMap.get("OPERATIONCONTENT") == null ? "" : resMap.get("OPERATIONCONTENT").toString());
                nodeOper.setInstanceNodeId(resMap.get("INSTANCENODEID") == null ? "" : resMap.get("INSTANCENODEID").toString());
                nodeOper.setFlowInstanceId(resMap.get("FLOWINSTANCEID") == null ? "" : resMap.get("FLOWINSTANCEID").toString());
                nodeOper.setDepartmentOfOperator(resMap.get("BMMC") == null ? "" : resMap.get("BMMC").toString());
                resu.add(nodeOper);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resu;
    }


    /**
     * 取得给定的系统用户、业务类型的所有业务数据id的sql串
     * 返回String 格式 形如 select correlationId  from  T_SYS_FLOWINSTANCE...
     * 
     * @param XTYHID the xTYHID
     * @param businessType the business type
     * 
     * @return the history yw
     */
    public static String getHistoryYW(String XTYHID, String businessType) {

        String resu = " select CORRELATIONID from T_SYS_FLOWINSTANCE where " + "flowModelId in (select flowModelId from T_SYS_FLOWMODEL where businessType='"
                + businessType + "')" + " and flowInstanceId in (select flowInstanceId from T_SYS_INSTANCENODEOperation where OperationUser='" + XTYHID
                + "') ";
        return resu;
    }

    //获取审批流程信息
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#getFlowInfos(java.lang.String)
     */
    public List getFlowInfos(String id) {

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" SELECT OPERATORNAME,OPERATION,");
        if (Consts.DBTYPE.equals("ORACLE")) {
         sqlBuffer.append(" to_char(OPERATETIME,'YYYY-MM-DD HH24:MI:SS')OPERATETIME");
        }
        if (Consts.DBTYPE.equals("MSSQL")) {
            sqlBuffer.append(" OPERATETIME");
         }
        if (Consts.DBTYPE.equals("DB2")) {
            sqlBuffer.append(" varchar(OPERATETIME)OPERATETIME");
           }
        sqlBuffer.append(",NEXTOPERATORNAME,REMARK,OPERATORTYPE FROM T_SYS_FLOWTRACE ");
        sqlBuffer.append(" WHERE BUSINESSID = '").append(id).append("'");
        sqlBuffer.append("  ORDER BY OPERATETIME ");
        Map params = new HashMap();
        params.put("SelSQL", sqlBuffer.toString());
        return this.findList("sqlcom.query", params);
    }


    //获得审批模板
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#getFlowInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    public List getFlowInfo(String correlation, String id, String primaryKey) {

        ArrayList list = new ArrayList();
        ArrayList tempListMb = new ArrayList();//存放模板信息的list
        ArrayList tempListXM = new ArrayList(); //存放当前流程操作人员list
        ArrayList tempListTime = new ArrayList(); //存放当前流程操作人员list
        StringBuffer sql = new StringBuffer();
        String flowInstanceId = "";
        String flowModelId = "";
        try {
            //获取flowInstanceId
            sql.append(" select FLOWINSTANCEID,T_SYS_FLOWINSTANCE.FLOWMODELID  from  T_SYS_FLOWINSTANCE,T_SYS_FLOWMODEL   where instancestate<>'异常' and  ")
                    .append("correlation='").append(correlation).append("' ").append("  and  correlationId='").append(id).append("' ").append(
                            " and primaryKey='").append(primaryKey).append("' ").append(
                            " and T_SYS_FLOWINSTANCE.flowModelId=T_SYS_FLOWMODEL.flowModelId  order by businessType,flowInstanceId desc  ");
            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            List reList1 = selcomList(params);

            for (int i = 0; i < reList1.size(); i++) {
                Map tempMap = (Map) reList1.get(i);
                flowInstanceId = tranCodeN(tempMap.get("FLOWINSTANCEID"));
                ;
                flowModelId = tranCodeN(tempMap.get("FLOWMODELID"));
            }

            //获取已经审核的人信息
            sql.delete(0, sql.length());

            if (Consts.DBTYPE.equals("DB2")) {
                sql
                        .append(" with temp(number) as (select coalesce( max( int(right(instancenodeid,1))),100)-1 number from T_SYS_PROCESSNODE  where flowInstanceId='");
                sql.append(flowInstanceId).append("' ) ");
                sql
                        .append(" select  case when a.operationflag <0 then '' when a.operationflag = 2 then ''  else   B.XM  end xm ,case when a.operationflag <0 then '' when a.operationflag = 2 then '' else  char(DATE(A.OPERATIONTIME))||' '||char(time(A.OPERATIONTIME)) end  DATE from T_SYS_INSTANCENODEOperation A ,T_SYS_RYXX B ,T_SYS_XTYH c   ,temp d where a.operationflag <> 2  and  1=1 ");

                sql.append("  and b.ryxxid = c.ryxxid and c.xtyhid = a.operationuser and A.flowInstanceId='").append(flowInstanceId).append(
                        "' and   int(right(a.instancenodeid,1))<=d.number order by OPERATIONTIME asc ");
                System.err.println("查找已经审核的人信息的***********sql:" + sql.toString());
            }
            if (Consts.DBTYPE.equals("MSSQL")) {
                sql
                        .append(" with temp(NUMBER) as (select coalesce( max( int(right(instancenodeid,1))),100)-1 NUMBER from T_SYS_PROCESSNODE  where flowInstanceId='");
                sql.append(flowInstanceId).append("' ) ");
                sql
                        .append(" select  case when a.operationflag <0 then '' when a.operationflag = 2 then ''  else   B.XM  end XM ,case when a.operationflag <0 then '' when a.operationflag = 2 then '' else  char(DATE(A.OPERATIONTIME))||' '||char(time(A.OPERATIONTIME)) end  DATE from T_SYS_INSTANCENODEOperation A ,T_SYS_RYXX B ,T_SYS_XTYH c   ,temp d where a.operationflag <> 2  and  1=1 ");

                sql.append("  and b.ryxxid = c.ryxxid and c.xtyhid = a.operationuser and A.flowInstanceId='").append(flowInstanceId).append(
                        "' and   int(right(a.instancenodeid,1))<=d.number order by OPERATIONTIME asc ");
                System.err.println("查找已经审核的人信息的***********sql:" + sql.toString());
            }
            if (Consts.DBTYPE.equals("ORACLE")) {
                String temptab = " (select nvl(max(TO_NUMBER(SUBSTR(instancenodeid,LENGTH(instancenodeid),1))),100)-1 pronum from T_SYS_PROCESSNODE where flowInstanceId='"
                        + flowInstanceId + "' ) ";
                sql
                        .append(" select  case when a.operationflag <0 then '' when a.operationflag = 2 then ''  else   B.XM  end xm ,case when a.operationflag <0 then '' when a.operationflag = 2 then '' else  to_char(A.OPERATIONTIME) end  VDATE from T_SYS_INSTANCENODEOperation A ,T_SYS_RYXX B ,T_SYS_XTYH c   ,"
                                + temptab + " d where a.operationflag <> 2  and  1=1 ");
                sql.append("  and b.ryxxid = c.ryxxid and c.xtyhid = a.operationuser and A.flowInstanceId='").append(flowInstanceId).append(
                        "' and   TO_NUMBER(SUBSTR(a.instancenodeid,LENGTH(a.instancenodeid),1))<=d.pronum order by OPERATIONTIME asc ");
            }

            params.put("SelSQL", sql.toString());
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map tempMap = (Map) reList.get(i);
                tempListXM.add(tempMap.get("XM"));
                tempListTime.add(tempMap.get("VDATE"));
            }

            //获取审批流程模板信息
            sql.delete(0, sql.length());
            if (Consts.DBTYPE.equals("DB2")) {
                sql.append(" select operationerId,int(operationer) operationer,nodeX,nodeY,operationName ");
                sql.append("  from  T_SYS_FLOWNODE,T_SYS_NODEOPERATION  ");
                sql.append(" where ");
                sql.append(" flowModelId='").append(flowModelId).append("' and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
                sql.append("   and int(operationer)>-2 ");
                sql.append("   order by nodey asc  ");
            }
            if (Consts.DBTYPE.equals("MSSQL")) {
                sql.append(" SELECT OPERATIONERID,OPERATIONER,NODEX,NODEY,OPERATIONNAME ");
                sql.append("  from  T_SYS_FLOWNODE,T_SYS_NODEOPERATION  ");
                sql.append(" where ");
                sql.append(" flowModelId='").append(flowModelId).append("' and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
                sql.append("   and operationer>-2 ");
                sql.append("   order by nodey asc  ");
            }
            if (Consts.DBTYPE.equals("ORACLE")) {
                sql.append(" select operationerId,TO_NUMBER(operationer) operationer,nodeX,nodeY,operationName ");
                sql.append("  from  T_SYS_FLOWNODE,T_SYS_NODEOPERATION  ");
                sql.append(" where ");
                sql.append(" flowModelId='").append(flowModelId).append("' and T_SYS_FLOWNODE.nodeOperationId=T_SYS_NODEOPERATION.nodeOperationId ");
                sql.append("   and TO_NUMBER(operationer)>-2 ");
                sql.append("   order by nodey asc  ");
            }
            System.err.println("查找审批流程模板信息的***********sql:" + sql.toString());

            params.put("SelSQL", sql.toString());
            reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map tempMap = (Map) reList.get(i);

                if (tranCodeN(tempMap.get("OPERATIONER")).equals("-1")) { //流程开始节点

                    tempListMb.add("提交人");
                } else {
                    String tempOPER = tranCodeN(tempMap.get("OPERATIONER"));
                    String tempStr = tranCodeN(tempMap.get("OPERATIONERID"));
                    //tempListMb.add( getNodeInfo(tempOPER,tempStr));
                    tempListMb.add(tranCodeN(tempMap.get("OPERATIONNAME")));
                }

            }
            list.add(tempListMb);
            list.add(tempListXM);
            list.add(tempListTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }


    /**
     * Gets the node info.
     * 
     * @param type the type
     * @param OPERATIONERID the oPERATIONERID
     * 
     * @return the node info
     */
    private String getNodeInfo(String type, String OPERATIONERID) {

        StringBuffer sql = new StringBuffer("");
        try {
            type = type.trim();
            System.err.println("type===" + type);
            if (type.equals("0")) { //审核人为人员
                sql.append(" select A.XM MC from T_SYS_RYXX a,T_SYS_XTYH b  where a.ryxxid = b.ryxxid and b.xtyhid ='").append(OPERATIONERID).append("'  ");
            } else if (type.equals("1")) { //审核人为部门
                sql.append(" select BMMC MC from T_SYS_BMXX where bmxxid='").append(OPERATIONERID).append("' ");
            } else if (type.equals("2")) { //审核人为角色
                sql.append(" select JSMC MC from T_SYS_XTJS where xtjsid='").append(OPERATIONERID).append("'  ");
            } else if (type.equals("3")) { //审核人为工作组
                sql.append(" select GZZMC MC  from T_SYS_GZZXX where 1=1 and  gzzxxid ='").append(OPERATIONERID).append("' ");
            }

            Map params = new HashMap();
            params.put("SelSQL", sql.toString());
            Map resMap = selcom(params);
            sql.delete(0, sql.length());
            if (resMap != null) {
                sql.append(tranCodeN(resMap.get("MC")));
            } else {
                throw new Exception("获得不到人员名称!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return sql.toString();
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


    //通用增删改查
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#selcom(java.util.Map)
     */
    public Map selcom(Map params) {

        return (Map) load("sqlcom.query", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#selcomList(java.util.Map)
     */
    public List selcomList(Map params) {

        return this.findList("sqlcom.query", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txDelcom(java.util.Map)
     */
    public boolean txDelcom(Map params) {

        return delete("sqlcom.delete", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txUpdcom(java.util.Map)
     */
    public boolean txUpdcom(Map params) {

        return update("sqlcom.update", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txInscom(java.util.Map)
     */
    public boolean txInscom(Map params) {

        insert("sqlcom.insert", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#pageQuery(java.util.Map, int)
     */
    public IListPage pageQuery(Map params, int pageNo) {

        return this.pageQuery("sqlcom.pageQuery", "sqlcom.pageCount", params, pageNo);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txInsertmod(java.util.Map)
     */
    public boolean txInsertmod(Map params) {

        insert("flow.insertmod", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txInsertnod(java.util.Map)
     */
    public boolean txInsertnod(Map params) {

        insert("flow.insertnod", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txInsertpath(java.util.Map)
     */
    public boolean txInsertpath(Map params) {

        insert("flow.insertpath", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txDelFlow(java.util.Map)
     */
    public void txDelFlow(Map params) {

        delete("flow.deletepath", params);
        delete("flow.deletenod", params);
        delete("flow.deletemod", params);

    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txUpdatemodById(java.util.Map)
     */
    public boolean txUpdatemodById(Map params) {

        return update("flow.updatemodById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txUpdatenodeById(java.util.Map)
     */
    public boolean txUpdatenodeById(Map params) {

        return update("flow.updatenodeById", params);
    }


    /**
     * Tran code p.
     * 
     * @param Str the str
     * 
     * @return the string
     */
    private String tranCodeP(String Str) {

        return Str == null ? "" : Str;
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
            condi = " T_SYS_XTYH.bmxxid=T_SYS_BMXX.bmxxid and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and T_SYS_XTYH.bmxxid='" + operationerId + "' ";
        } else if (operationer.equals("2")) {
            tableName = " T_SYS_XTYHjs,T_SYS_XTYH,T_SYS_XTJS,T_SYS_RYXX ";
            fieldName = "T_SYS_XTYHjs.XTJSID OPERATORID,JSMC OPERATORMC";
            condi = " T_SYS_XTYHjs.xtyhid=T_SYS_XTYH.xtyhid and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and T_SYS_XTYHjs.xtjsid=T_SYS_XTJS.xtjsid and T_SYS_XTYHjs.xtjsid='"
                    + operationerId + "' ";
        } else if (operationer.equals("3")) {
            tableName = " T_SYS_GZZCY,T_SYS_XTYH,T_SYS_GZZXX,T_SYS_RYXX ";
            fieldName = "T_SYS_GZZCY.GZZXXID OPERATORID,GZZMC OPERATORMC";
            condi = " T_SYS_GZZCY.xtyhid=T_SYS_XTYH.xtyhid and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and T_SYS_GZZCY.gzzxxid=T_SYS_GZZXX.gzzxxid and T_SYS_GZZCY.gzzxxid='"
                    + operationerId + "' ";
        }
        String sql = "select T_SYS_XTYH.XTYHID XTYHID,YHM,T_SYS_RYXX.XM XM," + fieldName + " from " + tableName + " where " + condi + " order by rybh ";

        String operatorId = "";
        String operatorMc = "";
        StringBuffer allmemberIds = new StringBuffer("('@#$'");
        try {
            Map params = new HashMap();
            params.put("SelSQL", sql);
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                operatorId = tranCodeN(resMap.get("OPERATORID"));
                operatorMc = tranCodeN(resMap.get("OPERATORMC"));
                resu.add(tranCodeN(resMap.get("XTYHID")));
                allmemberIds.append(",'");
                allmemberIds.append(tranCodeN(resMap.get("XTYHID")));
                allmemberIds.append("'");
                resu.add(tranCodeN(resMap.get("XM")));
                resu.add("");
                resu.add("");
            }
            allmemberIds.append(")");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String currentTimeStamp = "";
        if (Consts.DBTYPE.equals("DB2")) {
            currentTimeStamp = " current timestamp ";
        } else if (Consts.DBTYPE.equals("ORACLE")) {
            currentTimeStamp = " sysdate ";
        }else if(Consts.DBTYPE.equals("MSSQL")){
        	currentTimeStamp = " convert(varchar,getdate(),20) ";
        }

        String agentsql = "select EGRESSINREGID,BOOKERID,T_SYS_XTYH.YHM BOOKERYHM,T_SYS_RYXX.XM BOOKERXM,AGENTID,agent.YHM AGENTYHM ,agent.XM AGENTXM from "
                + "t_egressinReg left join (select xtyhid,yhm,xm from T_SYS_XTYH,T_SYS_RYXX where T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid) "
                + "agent on t_egressinReg.agentId=agent.xtyhid,T_SYS_XTYH,T_SYS_RYXX where t_egressinReg.bookerid=T_SYS_XTYH.xtyhid "
                + "and T_SYS_XTYH.ryxxid=T_SYS_RYXX.ryxxid and " + currentTimeStamp + " > leaveTime and " + "((" + currentTimeStamp
                + " < returnTime and backTime is null) or (" + currentTimeStamp + " < backTime and backTime is not null))" + " and bookerid in "
                + allmemberIds.toString();
        try {

            Map params = new HashMap();
            params.put("SelSQL", agentsql);
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                resu.setElementAt(tranCodeN(resMap.get("AGENTID")), resu.indexOf(tranCodeN(resMap.get("BOOKERID"))) + 2);
                resu.setElementAt(tranCodeN(resMap.get("AGENTXM")), resu.indexOf(tranCodeN(resMap.get("BOOKERID"))) + 3);
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
     * Gets the agent.
     * 
     * @param nodeId the node id
     * @param operationFlag the operation flag
     * 
     * @return the agent
     * 
     * @throws ServiceException the service exception
     */
    Vector getAgent(String nodeId, String operationFlag) throws ServiceException {

        String nextUserSql;
        if (operationFlag.equals("1")) {
            nextUserSql = "select INSTANCENODEID,OPERATIONERID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                    + "agentFlag=1 and operationer='0' and instanceNodeId in " + "(select ENDNODEID from " + Consts.SYSSCHEMA
                    + ".T_SYS_INSTANCENODEPath where startNodeId='" + nodeId + "')  ";
        } else {
            nextUserSql = "select INSTANCENODEID,OPERATIONERID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                    + "agentFlag=1 and operationer='0' and instanceNodeId='" + nodeId + "' ";
        }

        Vector userAndAgent = new Vector();
        try {
            StringBuffer nextNode = new StringBuffer("('@#$'");
            Map params = new HashMap();
            params.put("SelSQL", nextUserSql);
            List reList = selcomList(params);
            for (int i = 0; i < reList.size(); i++) {
                Map resMap = (Map) reList.get(i);
                nextNode.append(",'");
                nextNode.append(tranCodeN(resMap.get("INSTANCENODEID")));
                nextNode.append("'");
            }
            nextNode.append(")");

            String currentTimeStamp = "";
            if (Consts.DBTYPE.equals("DB2")) {
                currentTimeStamp = " current timestamp ";
            } else if (Consts.DBTYPE.equals("ORACLE")) {
                currentTimeStamp = " sysdate ";
            } else if (Consts.DBTYPE.equals("MSSQL")){
            	currentTimeStamp = " convert(varchar,getdate(),20) ";
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
            throw new ServiceException("getAgent Error in FlowServlet!");
        }
        return userAndAgent;
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
            sql = "select 0 OPERATIONER,OPERATIONUSER OPERATIONERID from T_SYS_INSTANCENODEOperation where instanceNodeId in (select instanceNodeId from T_SYS_PROCESSNODE where flowInstanceId='"
                    + flowInstanceId + "') and operationFlag=0 with ur ";
        } else {
            sql = "select OPERATIONER,OPERATIONERID from v_processNode_instanceNode where flowInstanceId='" + flowInstanceId + "' ";
        }
        try {
            StringBuffer users = new StringBuffer(); // 0
            StringBuffer departs = new StringBuffer(); // 1
            StringBuffer roles = new StringBuffer(); // 2
            StringBuffer groups = new StringBuffer(); // 3
            Map params = new HashMap();
            params.put("SelSQL", sql);
            List reList = selcomList(params);
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
                    + " where T_SYS_FLOWINSTANCE.flowModelId=T_SYS_FLOWMODEL.flowModelId and flowInstanceId='" + flowInstanceId + "' ";
            params.clear();
            params.put("SelSQL", sql.toString());
            Map resMap = selcom(params);

            if (resMap != null) {
                content.append(tranCodeN(resMap.get("BUSINESSTYPE")));
                content.append(" ");
            }
            resu.add(content);
        } catch (Exception ex) {
            System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "getProNodeOpera" + ",异常信息ex="
                    + ex.getMessage());
            logger.error(ex);
            logger.error("getProNodeOpera Error In FlowServlet!");
        }
        return resu;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowService#txEventHandle(javax.servlet.http.HttpServletRequest, com.hoperun.sys.service.FlowService, java.lang.String)
     */
    public String txEventHandle(HttpServletRequest request, FlowService flowService, String remind) throws ServiceException {

        // 0 1 2 3 4 5 6 7 8 9
        String[] paraName = {"BUSINESSTYPE", "TABLENAME", "FIELDNAME", "ID", "OPERATEFLAG", "SOURCEURI", "FLOWINTERFACENAME", "ZTBGFIELDNAME",
                "STATENAME", "CONNNAME"};
        String[] paraValue = this.prepareArray(request, paraName, 0);
        HttpSession session = request.getSession(false);

        // 取得是否提醒下一步节点的参数
        remind = request.getParameter("remind");

        int nodeWidth = 80;
        int nodeHeight = 88;
        UserBean userBean = (UserBean) session.getAttribute("UserBean");
        String xtyhid = userBean.getXTYHID();
        String ryxm = userBean.getXM();
        String jgxxid = userBean.getJGXXID();

        // 提交{
        if (paraValue[4].equals("affirm")) {
            remind = "1";
            for (int i = 0; i < 6; i++) {
                if (paraValue[i].equals(""))
                    request.setAttribute("msg", "no required parameter!");
                throw new ServiceException("441");
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

            // 从这里开始要放到一个事物里面，现在暂时没改
            // begin
            try {

                String flowInstanceId = "";
                try {
                    if (paraValue[6].equals(""))
                        paraValue[6] = "com.hoperun.sys.service.FlowInterface";

                    FlowInterface flowInterface = (FlowInterface) Class.forName(paraValue[6]).newInstance();

                    boolean eventResu = false;
                    request.setAttribute("id", paraValue[3]);

                    eventResu = flowInterface.beforeAffirm(request, session, flowService);

                    flowObjectInS.setFlowModelId((String) request.getAttribute("flowModelId"));
                    if (!eventResu) {
                        request.setAttribute("msg", (String) request.getAttribute("faultInfo"));
                        throw new ServiceException("441");
                    }

                    flowInstanceId = txQueren(session, flowObjectInS);
                    flowInterface.afterAffirm(request, session, flowService);

                    request.setAttribute("currentState", businessState[1]);
                    if (flowInstanceId == null || flowInstanceId.equals("")) {
                        request.setAttribute("id", paraValue[3]);
                        flowInterface.afterAuditing(request, session, flowService);
                    }
                }
                // end
                catch (Exception e) {
                    System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "affirm" + ",异常信息ex="
                            + e.getMessage());
                    throw new ServiceException();
                }
                // 根据模板设置和人员级别确定是否提交跳签
                String affirmCanJump = (String) session.getAttribute("affirmCanJump");
                session.removeAttribute("affirmCanJump");
                // affirmCanJump 1：允许跳签 0:禁止跳签
                if (affirmCanJump == null)
                    affirmCanJump = "0";
                if (!flowInstanceId.equals("") && affirmCanJump.equals("1")) {
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowEvent", "affirm");
                    request.setAttribute("remind", remind);
                    request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                    return "affirm_jump";
                }

                if (!flowInstanceId.equals("")) {
                    Map params = new HashMap();
                    params.put("SelSQL", " select INSTANCENODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='"
                            + flowInstanceId + "' and nodeY=30");
                    Map temMap = flowService.selcom(params);

                    String nodeId = "";
                    if (temMap != null && temMap.get("INSTANCENODEID") != null) {
                        nodeId = temMap.get("INSTANCENODEID").toString();
                    }
                    // 提醒节点处理者
                    Vector proOpera = getProNodeOpera(flowInstanceId, false);
                    // 取得下一步的节点代理
                    Vector userAndAgent = getAgent(nodeId, "1");
                    // 如果有则返回用户界面，取得用户意见
                    if (userAndAgent.size() != 0) {
                        request.setAttribute("userAndAgent", userAndAgent);
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowEvent", "affirm");
                        request.setAttribute("flowInterfaceName", paraValue[6]);
                        request.setAttribute("ztbgFieldName", paraValue[7]);
                        request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                        request.setAttribute("remind", remind);
                        return "userAndAgent";
                    }

                    // 查找下一步设置多人处理的节点操作者：operationerId，操作类型：operationer
                    Vector operationer = new Vector();
                    Vector operationerId = new Vector();
                    Vector instanceNodeIdV = new Vector();

                    params.clear();
                    params.put("SelSQL", "select INSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID from " + "" + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODE where instanceNodeId in (select endNodeId from " + "" + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODEPath where startNodeId='" + nodeId + "') order by nodex");
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        String allFlag = resMap.get("ALLFLAG").toString();
                        if (allFlag.equals("1")) {
                            instanceNodeIdV.add(resMap.get("INSTANCENODEID").toString());
                            operationer.add(resMap.get("OPERATIONER").toString());
                            operationerId.add(resMap.get("OPERATIONERID").toString());
                        }
                    }

                    // 如果是非集合用户，发送提醒信息
                    // if (proOpera != null && instanceNodeIdV.size() == 0)
                    // flowRemind(proOpera, "工作提示",
                    // proOpera.get(4).toString()+"等待您处理！");

                    // 取得集合用户
                    Vector selOpera = new Vector();
                    for (int i = 0; i < instanceNodeIdV.size(); i++) {
                        Vector operators = getMembers(operationerId.get(i).toString(), operationer.get(i).toString());
                        Vector selOperator = new Vector();
                        selOperator.add(instanceNodeIdV.get(i));
                        selOperator.add(operationer.get(i));
                        selOperator.add(operationerId.get(i));
                        selOperator.add(operators);
                        selOpera.add(selOperator);
                    }
                    // 如果下一步节点操作是多人
                    if (selOpera.size() != 0) {
                        request.setAttribute("selOpera", selOpera);
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowEvent", "affirm");
                        request.setAttribute("flowInterfaceName", paraValue[6]);
                        request.setAttribute("ztbgFieldName", paraValue[7]);
                        request.setAttribute("sourceURI", paraValue[5] + paraValue[3] + "&refresh=T");
                        request.setAttribute("remind", remind);
                        return "selOperators";
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                String faultInfo = ex.toString();
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                //faultInfo = faultInfo;//deleted by wang yehua
                request.setAttribute("msg", faultInfo);
                throw new ServiceException("441");
            }
            System.err.println(paraValue[5] + paraValue[3]);

            request.setAttribute("refresh", "T");
            request.setAttribute("alertMsg", request.getAttribute("faultInfo"));
            // 这里要改
            request.setAttribute("msg", paraValue[5] + paraValue[3] + "&refresh=T&alertMsg=" + (String) request.getAttribute("faultInfo"));
            request.setAttribute("value", paraValue[5] + paraValue[3]);
            throw new ServiceException("441");

            // return paraValue[5]+paraValue[3]+"&refresh=T&alertMsg="+(String)
            // request.getAttribute("faultInfo");
        }// 提交}

        // 公文编号{
        else if (paraValue[4].equals("number")) {
            String numberField = "fileNumber";
            String fileType = request.getParameter("fileType");
            String number = request.getParameter("number");
            try {
                int sameNum = 0;
                Map params = new HashMap();
                params.put("SelSQL", "select count(*) SAMENUM from " + Consts.SYSSCHEMA + ".t_file where fileNumber='" + number + "' and jgxxid='"
                        + jgxxid + "' and fileType='" + fileType + "'");
                Map temMap = flowService.selcom(params);

                if (temMap != null) {
                    sameNum = Integer.parseInt(temMap.get("SAMENUM").toString());
                }
                if (sameNum != 0) {
                    request.setAttribute("msg", "编号出错，输入的编号已经存在！");
                    throw new ServiceException("441");
                }
                params.clear();
                params.put("UpdSQL", "update " + Consts.SYSSCHEMA + "." + paraValue[1] + " set " + numberField + "='" + number
                        + "',state='已编号' where " + paraValue[2] + "='" + paraValue[3] + "'");
                txUpdcom(params);

            } catch (Exception ex) {
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "number" + ",异常信息ex="
                        + ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "number" + ",异常信息ex="
                        + ex.getMessage());
                throw new ServiceException("441");
            }
            // return
            // TranCodeBean.tranCodeC(paraValue[5]+paraValue[3]+"&refresh=T");
            // 这里要改
            request.setAttribute("msg", paraValue[5] + paraValue[3] + "&refresh=T");
            request.setAttribute("value", paraValue[5] + paraValue[3]);
            throw new ServiceException("441");
        }// 公文编号}

        // 撤签{
        else if (paraValue[4].equals("repeal")) {
            Vector proOpera = null;
            try {

                try {
                    String flowInstanceId = "";
                    Map params = new HashMap();
                    params.put("SelSQL", "select FLOWINSTANCEID from " + Consts.SYSSCHEMA
                            + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + paraValue[1] + "' and correlationId='" + paraValue[3]);
                    Map temMap = flowService.selcom(params);

                    if (temMap != null) {
                        flowInstanceId = temMap.get("FLOWINSTANCEID").toString();
                    }
                    // 提醒节点处理者
                    proOpera = getProNodeOpera(flowInstanceId, false);
                    // 删除当前节点
                    params.clear();
                    params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId in (select FLOWINSTANCEID from "
                            + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where instanceState='进行' and correlation='" + paraValue[1]
                            + "' and correlationId='" + paraValue[3] + "' and primaryKey='" + paraValue[2] + "')");
                    txDelcom(params);
                    // 修改流程实例状态
                    params.clear();
                    params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[2]
                            + "' where instanceState='进行' and correlation='" + paraValue[1] + "' and correlationId='" + paraValue[3]
                            + "' and primaryKey='" + paraValue[2] + "'");
                    txUpdcom(params);

                    // 如果是不同数据源的数据 这里不能乱改
                    // if (BUSCONNNAME.equals("")) {
                    // String ztbgsql = "";
                    // if (!paraValue[7].equals("")) {
                    // ztbgsql = "," + paraValue[7] + "=" +
                    // flowInS.toTimeStampSQL(TimeComm.getNYRDate() + " " +
                    // TimeComm.getSFMDate());
                    // }
                    // String stateName = "state";
                    // if (!paraValue[8].equals("")) {
                    // stateName = paraValue[8];
                    // }
                    //
                    // //修改主记录
                    // String sqlBus = "update " + paraValue[1] + " set " +
                    // stateName +
                    // "='" +
                    // businessState[3] + "'" + ztbgsql + " where " +
                    // paraValue[2] +
                    // "='" +
                    // paraValue[3] + "'";
                    // flowInS.executeStaticSql(sqlBus, conn);
                    // }

                    if (paraValue[6].equals("")) {
                        paraValue[6] = "com.hoperun.sys.service.FlowInterface";
                    }
                    FlowInterface flowInterface = (FlowInterface) Class.forName(paraValue[6]).newInstance();
                    request.setAttribute("currentState", businessState[3]);
                    request.setAttribute("id", paraValue[3]);
                    // 做审核通过后的动作
                    flowInterface.afterAuditing(request, session, flowService);

                } catch (Exception ex) {
                    throw ex;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "repeal" + ",异常信息ex="
                        + ex.getMessage());
                System.err.println("撤签中得到连接失败！");
                String faultInfo = ex.toString();
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                request.setAttribute("msg", faultInfo);
                throw new ServiceException("441");

            }

            // 提醒节点处理者
            // if (proOpera != null)
            // flowRemind(proOpera, "工作提示", proOpera.get(4).toString()+"已撤销！");

            // return
            // TranCodeBean.tranCodeC(paraValue[5]+paraValue[3]+"&refresh=T");
            request.setAttribute("refresh", "T");
            request.setAttribute("msg", paraValue[5] + paraValue[3] + "&refresh=T");
            request.setAttribute("value", paraValue[5] + paraValue[3]);
            throw new ServiceException("441");

        }// 撤签}

        // 审核{
        else if (paraValue[4].equals("auditing")) {

            String operationContent = request.getParameter("operationContent");
            String flowInstanceId = request.getParameter("flowInstanceId");
            String instanceNodeId = request.getParameter("instanceNodeId");
            String nextNodeId = request.getParameter("nextNodeId");
            String nextNodeType = request.getParameter("nextNodeType");
            String operationFlag = request.getParameter("operationFlag");
            String currentNodeType = request.getParameter("currentNodeType");
            String sourceURI = request.getParameter("sourceURI");
            String flowEvent = request.getParameter("flowEvent");
            String operationUser = xtyhid;

            String operationName = "";
            String updSql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag + ",operationUser='"
                    + operationUser + "',operationTime=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate())
                    + ",operationContent='" + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='"
                    + instanceNodeId + "' and operationFlag=0";

            String allUpdSql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=0" + ",operationUser='" + operationUser
                    + "',operationTime=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                    + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                    + "' and operationFlag=0";

            try {
                Map params = new HashMap();
                params.put("SelSQL", "select OPERATIONNAME from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation" + " where instanceNodeId='"
                        + instanceNodeId + "'");
                Map temMap = flowService.selcom(params);

                if (temMap != null) {
                    operationName = temMap.get("OPERATIONNAME").toString();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                System.out.println("取得结点操作名称出错！");
                String faultInfo = ex.toString();
                request.setAttribute("msg", faultInfo);
                throw new ServiceException("441");
            }

            // 取得当前结点的下一步节点中包含的多人操作的节点
            Vector allInstanceNodeId = new Vector();
            Vector allOperationer = new Vector();
            Vector allOperationerId = new Vector();
            try {
                // 取得当前操作节点的子节点和子节点的前导节点{
                StringBuffer parentsOfProcessNextNode = new StringBuffer("('@#$'");
                Vector sonsOfProNode = new Vector();
                String vsql = "";
                // <!---1:通过；-1：否决； 2：回退； 3：跳签；----------->
                if (operationFlag.equals("1")) {
                    vsql = "select ENDNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where startNodeId='" + instanceNodeId + "' ";
                    Map params = new HashMap();
                    params.put("SelSQL", vsql);
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        sonsOfProNode.add(resMap.get("ENDNODEID").toString());
                    }
                } else if (operationFlag.equals("2") || operationFlag.equals("3")) {
                    sonsOfProNode.add(nextNodeId);
                } else if (operationFlag.equals("-1")) {
                    sonsOfProNode.add(instanceNodeId);
                }
                if (sonsOfProNode.size() == 1) {
                    if (operationFlag.equals("1") || operationFlag.equals("3") || operationFlag.equals("-1")) {
                        // 如果当前操作节点的下一步节点只有一个，如果操作结果是通过或跳签，选取该节点的所有前导节点
                        if (Consts.DBTYPE.equals("DB2")) {
                            vsql = "with pathTree(endNodeId, startNodeId) as ( "
                                    + "select endNodeId, startNodeId from (select endNodeId,startNodeId " + "from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1 " + "where endNodeId='"
                                    + sonsOfProNode.get(0) + "' union all select " + "" + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId " + "from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath,pathTree where T_SYS_INSTANCENODEPath.endNodeId=pathTree.startNodeId) "
                                    + "select distinct(startNodeId)  from pathTree ";
                        } else if (Consts.DBTYPE.equals("ORAC")) {
                            vsql = "select distinct(endNodeId),startNodeId,flowInstanceId,level from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and endNodeId='" + sonsOfProNode.get(0)
                                    + "' connect by prior startNodeId=endNodeId ";
                        }else if(Consts.DBTYPE.equals("MSSQL")) {
                        	vsql = "with pathTree(ENDNODEID, STARTNODEID) as ( "
                                + "select ENDNODEID, STARTNODEID from (select ENDNODEID,STARTNODEID " + "from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath where flowInstanceId='" + flowInstanceId + "' ) resu1 " + "where ENDNODEID='"
                                + sonsOfProNode.get(0) + "' union all select " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID " + "from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath,pathTree where T_SYS_INSTANCENODEPath.ENDNODEID=pathTree.STARTNODEID) "
                                + "select distinct(STARTNODEID)  from pathTree ";
                        }
                    } else if (operationFlag.equals("2") || operationFlag.equals("-1")) {
                        // 如果当前操作节点的下一步节点只有一个，如果操作结果是退签，选取该节点的所有后续节点

                        if (Consts.DBTYPE.equals("DB2")) {
                            vsql = "with pathTree(endNodeId, startNodeId) as "
                                    + "( select endNodeId, startNodeId from "
                                    + "(select endNodeId,startNodeId from "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                    + flowInstanceId
                                    + "' ) resu1"
                                    + " where startNodeId='"
                                    + sonsOfProNode.get(0)
                                    + "' "
                                    + "union all select T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath,pathTree "
                                    + "where "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId) startNodeId  from pathTree with ur";
                        } else if (Consts.DBTYPE.equals("ORAC")) {
                            vsql = "select distinct(endNodeId),startNodeId,flowInstanceId,level from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and startNodeId='"
                                    + sonsOfProNode.get(0) + "' connect by prior endNodeId=startNodeId ";
                        }else if(Consts.DBTYPE.equals("MSSQL")){
                        	vsql = "with pathTree(ENDNODEID, STARTNODEID) as "
                                + "( select ENDNODEID, STARTNODEID from "
                                + "(select ENDNODEID,STARTNODEID from "
                                + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                + flowInstanceId
                                + "' ) resu1"
                                + " where STARTNODEID='"
                                + sonsOfProNode.get(0)
                                + "' "
                                + "union all select T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from "
                                + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath,pathTree "
                                + "where "
                                + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID) STARTNODEID  from pathTree with ur";
                        }
                    }
                    Map params = new HashMap();
                    params.put("SelSQL", vsql);
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        parentsOfProcessNextNode.append(",'");
                        parentsOfProcessNextNode.append(resMap.get("STARTNODEID").toString());
                        parentsOfProcessNextNode.append("'");
                    }
                    parentsOfProcessNextNode.append(")");
                }
                // 取得当前操作节点的子节点和子节点的前导节点}

                // 取得当前流程实例的所有当前节点数，且这些节点同时在下一步节点的前导或后续节点中
                int processNum = 1;
                if (sonsOfProNode.size() == 1) {
                    String sql = "select count(distinct(instanceNodeId)) PROCESSNUM from " + Consts.SYSSCHEMA
                            + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId + "' and instanceNodeId in "
                            + parentsOfProcessNextNode.toString();

                    Map params = new HashMap();
                    params.put("SelSQL", sql);
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        processNum = Integer.parseInt(resMap.get("PROCESSNUM").toString());
                    }
                }

                if (operationFlag.equals("1") || operationFlag.equals("2") || operationFlag.equals("3")) {
                    String sql = "";
                    if (operationFlag.equals("1")) {
                        sql = "select iNSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID from " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODE where instanceNodeId in (select endNodeId from " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath where startNodeId='" + instanceNodeId + "') and allFlag=1 ";
                    } else if (operationFlag.equals("2") || operationFlag.equals("3")) {
                        sql = "select INSTANCENODEID,ALLFLAG,OPERATIONER,OPERATIONERID from " + "" + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODE where instanceNodeId='" + nextNodeId + "' and allFlag=1 ";
                    }
                    Map params = new HashMap();
                    params.put("SelSQL", sql);
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        allInstanceNodeId.add(resMap.get("INSTANCENODEID").toString());
                        allOperationer.add(resMap.get("OPERATIONER").toString());
                        allOperationerId.add(resMap.get("OPERATIONERID").toString());
                    }
                }

                ProcessNode pNode = flowService.getProcessNode(session, instanceNodeId);

                try {
                    if (operationFlag.equals("2") || operationFlag.equals("3")) {
                        // 跳签或者退签
                        // 填写审签内容
                        // 删除当前处理节点
                        if (allInstanceNodeId.size() == 0) {
                            Map params = new HashMap();
                            params.put("UpdSQL", updSql);
                            txUpdcom(params);

                            // 如果当前节点是开始节点，则删除所有processNode
                            if (currentNodeType.equals("-1")) {
                                params.clear();
                                params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId
                                        + "' and instanceNodeId<>'" + nextNodeId + "'");
                                txDelcom(params);

                            } else {
                                params.clear();
                                params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ". T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId
                                        + "'");
                                txDelcom(params);

                            }
                        } else {
                            // 如果当前节点是开始节点，则删除所有processNode
                            if (currentNodeType.equals("-1")) {
                                Map params = new HashMap();
                                params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId
                                        + "' and instanceNodeId<>'" + nextNodeId + "'");
                                txDelcom(params);

                            }

                            Map params = new HashMap();
                            params.put("UpdSQL", allUpdSql);
                            txUpdcom(params);

                        }

                        /**
                         * flowInS.executeStaticSql(updSql, conn); //删除当前处理节点
                         * flowInS.executeStaticSql("delete from T_SYS_PROCESSNODE
                         * where instanceNodeId='"+instanceNodeId+"'", conn);
                         */

                        // 如果直接跳到结束节点
                        if (nextNodeType.equals("-2")) {

                            Map params = new HashMap();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='" + flowInstanceId + "'");
                            txDelcom(params);

                            params.clear();
                            params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[1]
                                    + "' where flowInstanceid='" + flowInstanceId + "'");
                            txUpdcom(params);
                            params.clear();
                            params.put("SelSQL",
                                    "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                            + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                            Map temMap = flowService.selcom(params);

                            String tableName = "";
                            String id = "";
                            String primaryKey = "";
                            String flowInterfaceName = "";
                            String ztbgFieldName = "";
                            String stateName = "state";
                            if (temMap != null) {
                                tableName = temMap.get("CORRELATION").toString();
                                id = temMap.get("CORRELATIONID").toString();
                                primaryKey = temMap.get("PRIMARYKEY").toString();
                                flowInterfaceName = temMap.get("FLOWINTERFACENAME").toString();
                                ztbgFieldName = temMap.get("ZTBGFIELDNAME").toString();

                                String tepstateName = temMap.get("STATENAME").toString();
                                if (!tepstateName.equals("")) {
                                    stateName = tepstateName;
                                }
                            }

                            String ztbgsql = "";
                            if (!ztbgFieldName.equals("")) {
                                ztbgsql = "," + ztbgFieldName + "=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                            }

                            // 修改主记录
                            String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'"
                                    + ztbgsql + " where " + primaryKey + "='" + id + "'";

                            params.clear();
                            params.put("UpdSQL", sqlBus);
                            txUpdcom(params);
                            if (!flowInterfaceName.equals("")) {
                                FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                request.setAttribute("currentState", businessState[1]);
                                request.setAttribute("id", id);
                                flowInterface.afterAuditing(request, session, flowService);
                            }
                            request.setAttribute("flowInstanceId", flowInstanceId);
                            request.setAttribute("flowInstanceId", "T");
                            return "instance";
                            // return
                            // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                        }

                        // 如果下一步节点的前导或后续节点中已没有当前节点
                        String processNodeId = TimeComm.getTimeStamp("pro");
                        if ((processNum == 1 || processNum == 0) && !nextNodeType.equals("-2")) {
                            // 生成新的当前节点指针
                            if (allInstanceNodeId.size() == 0) {
                                String sql = "insert into " + Consts.SYSSCHEMA
                                        + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                        + flowInstanceId + "','" + nextNodeId + "',"
                                        + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";

                                Map params = new HashMap();
                                params.put("UpdSQL", sql);
                                txUpdcom(params);

                            }

                            // 插入新的实例节点操作
                            String allFlag = "";
                            String operationer = "";
                            String operationerId = "";
                            if (allInstanceNodeId.size() != 0) {
                                allFlag = "1";
                                operationer = allOperationer.get(0).toString();
                                operationerId = allOperationerId.get(0).toString();
                            } else {
                                allFlag = "0";
                            }

                            if (allFlag.equals("0")) {
                                String insNodeOperId = TimeComm.getTimeStamp("ino");
                                String insertSql = "insert into "
                                        + Consts.SYSSCHEMA
                                        + ".T_SYS_INSTANCENODEOperation("
                                        + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId) "
                                        + "select '" + insNodeOperId + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId "
                                        + "from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='"
                                        + nextNodeId + "' and instanceNodeOperationId=(select max(instanceNodeOperationId) from " + Consts.SYSSCHEMA
                                        + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";

                                Map params = new HashMap();
                                params.put("InsSQL", insertSql);
                                txInscom(params);

                            } else if (allFlag.equals("1")) {
                                int noDealNum = 0;
                                Map params = new HashMap();
                                params.put("SelSQL", "select count(instanceNodeOperationId) NODEALNUM from " + Consts.SYSSCHEMA
                                        + ".T_SYS_INSTANCENODEOperation where operationFlag=0 and instanceNodeId='" + nextNodeId + "'");
                                Map temMap = flowService.selcom(params);

                                if (temMap != null) {
                                    noDealNum = Integer.parseInt(temMap.get("NODEALNUM").toString());
                                }
                                // flowInS.closeDB();
                                if (noDealNum == 0) {
                                    String tableName = "";
                                    String fieldName = "xtyhid";
                                    String condi = "";
                                    if (operationer.equals("1")) {
                                        tableName = " T_SYS_XTYH ";
                                        condi = " bmxxid='" + operationerId + "' ";
                                    } else if (operationer.equals("2")) {
                                        tableName = " T_SYS_XTYHjs ";
                                        condi = " xtjsid='" + operationerId + "' ";
                                    } else if (operationer.equals("3")) {
                                        tableName = " T_SYS_GZZCY ";
                                        condi = " gzzxxid='" + operationerId + "' ";
                                    }
                                    Vector allYHID = new Vector();
                                    params.clear();
                                    params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where " + condi);
                                    List reList = flowService.selcomList(params);
                                    for (int i = 0; i < reList.size(); i++) {
                                        Map resMap = (Map) reList.get(i);
                                        allYHID
                                                .add(resMap.get(fieldName.toUpperCase()) == null ? "" : resMap.get(fieldName.toUpperCase())
                                                        .toString());
                                    }

                                    if (allYHID.size() > 0) {
                                        for (int i = 0; i < allYHID.size(); i++) {
                                            String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i));
                                            String insertSql = "insert into "
                                                    + Consts.SYSSCHEMA
                                                    + ".T_SYS_INSTANCENODEOperation("
                                                    + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId,operationUser) "
                                                    + "select '"
                                                    + insNodeOperId
                                                    + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId,'"
                                                    + allYHID.get(i)
                                                    + "' "
                                                    + "from "
                                                    + Consts.SYSSCHEMA
                                                    + ".T_SYS_INSTANCENODEOperation where instanceNodeOperationId=(select max(instanceNodeOperationId) from "
                                                    + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";
                                            params.clear();
                                            params.put("InsSQL", insertSql);
                                            txInscom(params);

                                        }
                                    } else {
                                        // request.setAttribute("msg","为设置多人处理的节点操作组添加成员!");
                                        throw new Exception("为设置多人处理的节点操作组添加成员！");

                                        // return mapping.findForward(FAILURE);

                                    }
                                }
                            }
                        } else {
                            // return
                            // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                            request.setAttribute("flowInstanceId", flowInstanceId);
                            request.setAttribute("flowInstanceId", "T");
                            return "instance";
                        }

                    } else if (operationFlag.equals("1")) {
                        // 处理结果：通过

                        // {填写处理意见、结果
                        // ProcessNode pNode = flowInS.getProcessNode(session,
                        // instanceNodeId);

                        // 如果当前操作节点是多人处理
                        if (pNode.getAllFlag().equals("1")) {
                            // 选取未处理的人员数

                            Map params = new HashMap();
                            params.put("SelSQL", "select count(*) NODEALNUM from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + instanceNodeId + "' and operationFlag=0 ");
                            Map temMap = flowService.selcom(params);

                            int noDealNum = 0;
                            if (temMap != null) {
                                noDealNum = Integer.parseInt(temMap.get("NODEALNUM").toString());

                            }

                            String updSql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag
                                    + ",operationUser='" + operationUser + "',operationTime="
                                    + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                                    + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                                    + "' and operationFlag=0 and operationUser='" + xtyhid + "'";

                            String allUpdSql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=0" + ",operationUser='"
                                    + operationUser + "',operationTime="
                                    + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                                    + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and instanceNodeId='" + instanceNodeId
                                    + "' and operationFlag=0 and operationUser='" + xtyhid + "'";
                            // 填写审签内容，如果已经处理过，本次处理不会记录
                            try {
                                if (noDealNum != 1) {

                                    params.clear();
                                    params.put("UpdSQL", updSql1);
                                    txUpdcom(params);

                                } else {
                                    if (allInstanceNodeId.size() != 0 && processNum < 2) {
                                        // 如果下一步的操作中包含多人节点，并且当前处理节点是多个分支的最后一个节点的处理者
                                        params.clear();
                                        params.put("UpdSQL", allUpdSql1);
                                        txUpdcom(params);

                                    } else {
                                        params.clear();
                                        params.put("UpdSQL", updSql1);
                                        txUpdcom(params);

                                        params.clear();
                                        params.put("UpdSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='"
                                                + instanceNodeId + "'");
                                        txUpdcom(params);
                                    }
                                }
                            } catch (Exception ex) {
                                logger.error(ex);
                                throw ex;
                            }
                            // 如果还有人没处理，则直接返回用户界面，否则接着按 通过 处理
                            if (noDealNum != 1) {

                                request.setAttribute("flowInstanceId", flowInstanceId);
                                request.setAttribute("flowInstanceId", "T");
                                return "instance";
                                // return
                                // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                            }
                        } else {
                            Map params = new HashMap();
                            params.put("UpdSQL", updSql);
                            txUpdcom(params);

                        }
                        // 填写处理意见、结果}

                        // 删除当前处理节点
                        if (allInstanceNodeId.size() == 0) {

                            Map params = new HashMap();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                            txDelcom(params);

                        }

                        // 如果下一步节点的前导或后续节点中已没有当前节点
                        if (processNum == 1 || processNum == 0) {
                            Vector nextNode = new Vector();
                            String sql = "select INSTANCENODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where "
                                    + "instanceNodeId in (select endNodeId from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where startNodeId='"
                                    + instanceNodeId + "') and operationer<>'-2'";

                            Map params = new HashMap();
                            params.put("SelSQL", sql);
                            List reList = flowService.selcomList(params);
                            for (int i = 0; i < reList.size(); i++) {
                                Map resMap = (Map) reList.get(i);
                                nextNode.add(resMap.get("INSTANCENODEID").toString());
                            }
                            // flowInS.closeDB();
                            // 当前节点不是最后节点
                            if (nextNode.size() != 0) {
                                for (int i = 0; i < nextNode.size(); i++) {
                                    // 设置当前节点
                                    String processNodeId = TimeComm.getTimeStamp(String.valueOf(i));
                                    String sqlProcessNode = "insert into " + Consts.SYSSCHEMA
                                            + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                            + flowInstanceId + "','" + nextNode.get(i) + "',"
                                            + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";
                                    if (allInstanceNodeId.size() == 0) {

                                        params.clear();
                                        params.put("InsSQL", sqlProcessNode);
                                        txInscom(params);

                                    }

                                    // 插入新的实例节点操作
                                    String allFlag = "";
                                    String operationer = "";
                                    String operationerId = "";
                                    params.clear();
                                    params.put("SelSQL", "select ALLFLAG,OPERATIONER,OPERATIONERID from " + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODE where instanceNodeId='" + nextNode.get(i) + "'");
                                    List reList1 = flowService.selcomList(params);
                                    for (int k = 0; k < reList1.size(); k++) {
                                        Map resMap = (Map) reList1.get(k);
                                        allFlag = resMap.get("ALLFLAG").toString();
                                        operationer = resMap.get("OPERATIONER").toString();
                                        operationerId = resMap.get("OPERATIONERID").toString();
                                    }
                                    if (allFlag.equals("0")) {
                                        String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i));
                                        String insertSql = "insert into "
                                                + Consts.SYSSCHEMA
                                                + ".T_SYS_INSTANCENODEOperation("
                                                + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId) "
                                                + "select '" + insNodeOperId
                                                + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId " + "from"
                                                + Consts.SYSSCHEMA + ".  T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='"
                                                + nextNode.get(i) + "' and instanceNodeOperationId=(select max(instanceNodeOperationId) from "
                                                + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNode.get(i) + "')";

                                        params.clear();
                                        params.put("InsSQL", insertSql);
                                        txInscom(params);
                                    } else if (allFlag.equals("1")) {
                                        int noDealNum = 0;

                                        params.clear();
                                        params.put("SelSQL", "select count(instanceNodeOperationId) NODEALNUM from " + Consts.SYSSCHEMA
                                                + ". T_SYS_INSTANCENODEOperation where operationFlag=0 and instanceNodeId='" + nextNode.get(i) + "' ");
                                        Map reMap = flowService.selcom(params);

                                        if (reMap != null) {
                                            noDealNum = Integer.parseInt(reMap.get("NODEALNUM").toString());
                                        }
                                        // flowInS.closeDB();
                                        if (noDealNum == 0) {
                                            String tableName = "";
                                            String fieldName = "XTYHID";
                                            String condi = "";
                                            if (operationer.equals("1")) {
                                                tableName = " T_SYS_XTYH ";
                                                condi = " bmxxid='" + operationerId + "' ";
                                            } else if (operationer.equals("2")) {
                                                tableName = " T_SYS_XTYHjs ";
                                                condi = " xtjsid='" + operationerId + "' ";
                                            } else if (operationer.equals("3")) {
                                                tableName = " T_SYS_GZZCY ";
                                                condi = " gzzxxid='" + operationerId + "' ";
                                            }
                                            Vector allYHID = new Vector();

                                            params.clear();
                                            params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where "
                                                    + condi);
                                            List reList2 = flowService.selcomList(params);
                                            for (int j = 0; j < reList2.size(); j++) {
                                                Map resMap = (Map) reList2.get(j);
                                                allYHID.add(resMap.get(fieldName).toString());
                                            }
                                            if (allYHID.size() > 0) {
                                                for (int j = 0; j < allYHID.size(); j++) {
                                                    String insNodeOperId = TimeComm.getTimeStamp("ino" + String.valueOf(i) + String.valueOf(j));
                                                    String insertSql = "insert into "
                                                            + Consts.SYSSCHEMA
                                                            + ".T_SYS_INSTANCENODEOperation("
                                                            + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId,operationUser) "
                                                            + "select '"
                                                            + insNodeOperId
                                                            + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId,'"
                                                            + allYHID.get(j)
                                                            + "' "
                                                            + "from "
                                                            + Consts.SYSSCHEMA
                                                            + ".T_SYS_INSTANCENODEOperation where instanceNodeOperationId=(select max(instanceNodeOperationId) from "
                                                            + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNode.get(i)
                                                            + "')";

                                                    params.clear();
                                                    params.put("InsSQL", insertSql);
                                                    txInscom(params);

                                                }
                                            } else {
                                                request.setAttribute("msg", "为设置多人处理的节点操作组添加成员！");
                                                throw new Exception("为设置多人处理的节点操作组添加成员！");
                                                // return
                                                // mapping.findForward(FAILURE);
                                            }
                                        }
                                    }
                                }
                            } else {
                                // 当前节点是最后节点
                                // 修改实例状态
                                // 删除当前节点
                                params.clear();
                                params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[1]
                                        + "' where flowInstanceid='" + flowInstanceId + "'");
                                txUpdcom(params);

                                params.clear();
                                params.put("SelSQL",
                                        "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                                + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                                Map reMap = flowService.selcom(params);

                                String tableName = "";
                                String id = "";
                                String primaryKey = "";
                                String flowInterfaceName = "";
                                String ztbgFieldName = "";
                                String stateName = "state";
                                if (reMap != null) {
                                    tableName = reMap.get("CORRELATION") == null ? "" : reMap.get("CORRELATION").toString();
                                    id = reMap.get("CORRELATIONID") == null ? "" : reMap.get("CORRELATIONID").toString();
                                    primaryKey = reMap.get("PRIMARYKEY") == null ? "" : reMap.get("PRIMARYKEY").toString();
                                    flowInterfaceName = reMap.get("ZTBGFIELDNAME") == null ? "" : reMap.get("FLOWINTERFACENAME").toString();
                                    ztbgFieldName = reMap.get("ZTBGFIELDNAME") == null ? "" : reMap.get("ZTBGFIELDNAME").toString();
                                    String stateName1 = reMap.get("STATENAME") == null ? "" : reMap.get("STATENAME").toString();
                                    if (!stateName1.equals("")) {
                                        stateName = stateName1;
                                    }

                                }
                                String ztbgsql = "";
                                if (!ztbgFieldName.equals("")) {
                                    ztbgsql = "," + ztbgFieldName + "="
                                            + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                                }

                                // 修改主记录
                                String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[1] + "'"
                                        + ztbgsql + " where " + primaryKey + "='" + id + "'";

                                params.clear();
                                params.put("UpdSQL", sqlBus);
                                txUpdcom(params);
                                if (!flowInterfaceName.equals("")) {
                                    FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                    request.setAttribute("currentState", businessState[1]);
                                    request.setAttribute("id", id);
                                    flowInterface.afterAuditing(request, session, flowService);
                                }
                            }

                            if (operationName.equals("发文签发") || operationName.equals("编号校稿") || operationName.equals("下发")
                                    || operationName.equals("收文审核") || operationName.equals("归档") || operationName.equals("收文整理")) {
                                String busState = "";
                                if (operationName.equals("发文签发")) {
                                    busState = businessState[4];
                                } else if (operationName.equals("编号校稿")) {
                                    busState = businessState[5];
                                } else if (operationName.equals("下发")) {
                                    busState = businessState[6];
                                } else if (operationName.equals("收文审核")) {
                                    busState = businessState[7];
                                } else if (operationName.equals("归档")) {
                                    busState = businessState[8];
                                } else if (operationName.equals("收文整理")) {
                                    busState = businessState[9];
                                }

                                params.clear();
                                params.put("SelSQL", "select CORRELATION,CORRELATIONID,PRIMARYKEY,STATENAME from " + Consts.SYSSCHEMA
                                        + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "' ");
                                Map reMap = flowService.selcom(params);

                                String tableName = "";
                                String id = "";
                                String primaryKey = "";
                                String stateName = "state";
                                if (reMap != null) {
                                    tableName = reMap.get("CORRELATION").toString();
                                    id = reMap.get("CORRELATIONID").toString();
                                    primaryKey = reMap.get("PRIMARYKEY").toString();
                                    String stateName1 = reMap.get("STATENAME") == null ? "" : reMap.get("STATENAME").toString();
                                    if (!stateName1.equals("")) {
                                        stateName = stateName1;
                                    }
                                }
                                // flowInS.closeDB();
                                // 修改主记录
                                if (operationName.equals("发文签发")) {
                                    String sql1 = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + busState
                                            + "',signUser=(select case when signuser is null " + "then '" + ryxm + "' else signuser||'," + ryxm
                                            + "' end signuser from " + Consts.SYSSCHEMA + ".t_file where fileId='" + id + "') where " + primaryKey
                                            + "='" + id + "'";

                                    params.clear();
                                    params.put("UpdSQL", sql1);
                                    txUpdcom(params);

                                } else {
                                    String sql1 = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + busState + "' where "
                                            + primaryKey + "='" + id + "'";
                                    params.clear();
                                    params.put("UpdSQL", sql1);
                                    txUpdcom(params);
                                }
                            }
                        } else {
                            // return
                            // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                            request.setAttribute("flowInstanceId", flowInstanceId);
                            request.setAttribute("refresh", "T");
                            return "instance";
                        }

                    } else if (operationFlag.equals("-1")) {
                        // 否决

                        // 取得收文办理的节点id
                        Map params = new HashMap();
                        params.put("SelSQL", "select DISTINCT(INSTANCENODEID) from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEOperation where flowInstanceId='" + flowInstanceId
                                + "' and operationName='收文办理' and instanceNodeId in " + parentsOfProcessNextNode.toString() + " ");
                        Map reMap = flowService.selcom(params);
                        if (reMap != null) {
                            nextNodeId = reMap.get("INSTANCENODEID") == null ? "" : reMap.get("INSTANCENODEID").toString();
                        }

                        // 填写审签内容
                        String updSqlno = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + operationFlag
                                + ",operationUser='" + operationUser + "',operationTime="
                                + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ",operationContent='"
                                + operationContent + "' where flowInstanceId='" + flowInstanceId + "' and operationUser='" + operationUser
                                + "' and instanceNodeId='" + instanceNodeId + "' and operationFlag=0";

                        if (pNode.getAllFlag().equals("1")) {

                            params.clear();
                            params.put("UpdSQL", updSqlno);
                            txUpdcom(params);
                        } else {

                            params.clear();
                            params.put("UpdSQL", updSql);
                            txUpdcom(params);
                        }

                        // 操作如果是“收文审核”，则跳动流程当前节点到收文办理
                        if (operationName.equals("收文审核")) {

                            // 删除当前处理节点
                            params.clear();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                            txDelcom(params);

                            // 插入新的当前节点
                            String processNodeId = TimeComm.getTimeStamp("pro");
                            String insql = "insert into " + Consts.SYSSCHEMA
                                    + ".T_SYS_PROCESSNODE(processNodeId,flowInstanceId,instanceNodeId,comeTime) values('" + processNodeId + "','"
                                    + flowInstanceId + "','" + nextNodeId + "',"
                                    + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";

                            params.clear();
                            params.put("InsSQL", insql);
                            txInscom(params);

                            // 插入新的当前节点操作
                            String insNodeOperId = TimeComm.getTimeStamp("ino");
                            String insertSql = "insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                    + "instanceNodeOperationId,operationName,correlation,correlationId,operationFlag,instanceNodeId,flowInstanceId) "
                                    + "select '" + insNodeOperId + "',operationName,correlation,correlationId,0,instanceNodeId,flowInstanceId "
                                    + "from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where operationFlag<>0 and instanceNodeId='"
                                    + nextNodeId + "' and instanceNodeOperationId=(select max(instanceNodeOperationId) from " + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nextNodeId + "')";

                            params.clear();
                            params.put("InsSQL", insertSql);
                            txInscom(params);
                            operationFlag = "2";
                        } else {
                            params.clear();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + instanceNodeId + "'");
                            txDelcom(params);

                            // 查看是否还有其它的当前处理节点
                            int processNodeNum = 0;

                            params.clear();
                            params.put("SelSQL", "select count(*) NUM from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where flowInstanceId='"
                                    + flowInstanceId + "' ");
                            Map reMap1 = flowService.selcom(params);

                            if (reMap1 != null) {
                                processNodeNum = reMap1.get("NUM") == null ? 0 : Integer.parseInt(reMap1.get("NUM").toString());
                            }

                            if (processNodeNum == 0) {
                                // 修改实例状态

                                params.clear();
                                params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE set instanceState='" + state[2]
                                        + "' where flowInstanceid='" + flowInstanceId + "'");
                                txUpdcom(params);

                                params.clear();
                                params.put("SelSQL",
                                        "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                                + Consts.SYSSCHEMA + ".T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "' ");
                                Map reMap2 = flowService.selcom(params);

                                String tableName = "";
                                String id = "";
                                String primaryKey = "";
                                String flowInterfaceName = "";
                                String ztbgFieldName = "";
                                String stateName = "state";
                                if (reMap2 != null) {
                                    tableName = reMap2.get("CORRELATION") == null ? "" : reMap2.get("CORRELATION").toString();
                                    id = reMap2.get("CORRELATIONID") == null ? "" : reMap2.get("CORRELATIONID").toString();
                                    primaryKey = reMap2.get("PRIMARYKEY") == null ? "" : reMap2.get("PRIMARYKEY").toString();
                                    flowInterfaceName = reMap2.get("FLOWINTERFACENAME") == null ? "" : reMap2.get("FLOWINTERFACENAME").toString();
                                    ztbgFieldName = reMap2.get("ZTBGFIELDNAME") == null ? "" : reMap2.get("ZTBGFIELDNAME").toString();

                                    String stateName1 = reMap2.get("STATENAME") == null ? "" : reMap2.get("STATENAME").toString();
                                    if (!stateName1.equals("")) {
                                        stateName = stateName1;
                                    }
                                }

                                String ztbgsql = "";
                                if (!ztbgFieldName.equals("")) {
                                    ztbgsql = "," + ztbgFieldName + "="
                                            + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                                }

                                // 修改主记录
                                String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[2] + "'"
                                        + ztbgsql + " where " + primaryKey + "='" + id + "'";

                                params.clear();
                                params.put("UpdSQL", sqlBus);
                                txUpdcom(params);
                                if (!flowInterfaceName.equals("")) {
                                    FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                    request.setAttribute("currentState", businessState[2]);
                                    request.setAttribute("id", id);
                                    flowInterface.afterAuditing(request, session, flowService);
                                }
                            } else {

                                request.setAttribute("flowInstanceId", flowInstanceId);
                                request.setAttribute("flowInstanceId", "T");
                                return "instance";
                                // return
                                // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
                            }
                        }
                    }

                } catch (Exception ex) {

                    throw ex;
                }
            } catch (Exception ex) {
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                        + ex.getMessage());
                ex.printStackTrace();
                String faultInfo = ex.toString();
                if (request.getAttribute("faultInfo") != null) {
                    faultInfo = (String) request.getAttribute("faultInfo");
                }
                request.setAttribute("msg", faultInfo == null ? "" : faultInfo);
                throw new ServiceException("441");
            }

            // 判断下一步节点是否设置了代理人
            if (operationFlag.equals("1") || operationFlag.equals("2") || operationFlag.equals("3")) {
                // 查找下一节点：节点操作类型为用户，并且设置的有代理权限
                String nodeId;
                if (operationFlag.equals("1")) {
                    nodeId = instanceNodeId;
                } else {
                    nodeId = nextNodeId;
                }
                try {
                    // 取得下一步的节点代理
                    Vector userAndAgent = getAgent(nodeId, operationFlag);
                    // 如果有则返回用户界面，取得用户意见
                    if (userAndAgent.size() != 0) {
                        request.setAttribute("userAndAgent", userAndAgent);
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowEvent", "auditing");
                        request.setAttribute("remind", remind);
                        return "userAndAgent";
                    }
                } catch (Exception ex) {
                    System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                            + ex.getMessage());
                    ex.printStackTrace();
                    request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing"
                            + ",异常信息ex=" + ex.getMessage());
                    throw new ServiceException("441");
                }

                // 查找下一步设置多人处理的节点操作者：operationerId，操作类型：operationer
                try {
                    Vector selOpera = new Vector();
                    for (int i = 0; i < allInstanceNodeId.size(); i++) {
                        Vector operators = getMembers(allOperationerId.get(i).toString(), allOperationer.get(i).toString());
                        Vector selOperator = new Vector();
                        selOperator.add(allInstanceNodeId.get(i));
                        selOperator.add(allOperationer.get(i));
                        selOperator.add(allOperationerId.get(i));
                        selOperator.add(operators);
                        selOpera.add(selOperator);
                    }
                    // 如果下一步节点操作是多人
                    if (selOpera.size() != 0) {
                        request.setAttribute("selOpera", selOpera);
                        request.setAttribute("flowInstanceId", flowInstanceId);
                        request.setAttribute("flowEvent", flowEvent);
                        request.setAttribute("priorNodeId", instanceNodeId);
                        request.setAttribute("prionOperationFlag", operationFlag);
                        request.setAttribute("sourceURI", sourceURI);
                        request.setAttribute("remind", remind);
                        return "selOperators";
                        // return null;
                    }
                } catch (Exception ex) {
                    System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing" + ",异常信息ex="
                            + ex.getMessage());
                    ex.printStackTrace();
                    request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "auditing"
                            + ",异常信息ex=" + ex.getMessage());
                    throw new ServiceException("441");
                }
            }
            // 提醒节点处理者
            Vector proOpera = null;
            proOpera = getProNodeOpera(flowInstanceId, false);
            // if (proOpera != null)
            // flowRemind(proOpera, "工作提示",
            // proOpera.get(4).toString()+"等待您处理！");

            if (currentNodeType.equals("-1")) {

                System.err.println("sourceURI==" + sourceURI);
                return "sourceURI";
            } else {
                request.setAttribute("flowInstanceId", flowInstanceId);
                request.setAttribute("flowInstanceId", "T");
                return "instance";
                // return "jsp/admin/model/instance.jsp?flowInstanceId=" +
                // flowInstanceId +"&refresh=T";
            }
        }// 审核}

        // 用代理替换当前操作者{
        // else if (paraValue[4].equals("agent")) {
        // StringBuffer inureAgents = new StringBuffer();
        // StringBuffer inUsers = new StringBuffer();
        // String flowInstanceId =
        // TranCodeBean.tranCodeP(request.getParameter("flowInstanceId"));
        // String agentResult =
        // TranCodeBean.tranCodeP(request.getParameter("agentResult"));
        // String flowEvent =
        // TranCodeBean.tranCodeP(request.getParameter("flowEvent"));
        // String sourceURI =
        // TranCodeBean.tranCodeP(request.getParameter("sourceURI"));
        // String[] insNodes = request.getParameterValues("instanceNodeId");
        // String[] agenters = request.getParameterValues("agenter");
        // String[] inUse = request.getParameterValues("agentInUse");
        // String[] operationerId = request.getParameterValues("operationerId");
        // if (!agentResult.equals("repeal")) {
        // if (inUse != null) {
        // try {
        // Connection conn = flowInS.getConn(CONNNAME);
        // try {
        // conn.setAutoCommit(false);
        // for (int i=0; i<inUse.length; i++) {
        // int j = Integer.parseInt(inUse[i]);
        // flowInS.executeStaticSql("update T_SYS_INSTANCENODE set
        // operationerId='"+agenters[j]+"' where
        // instanceNodeId='"+insNodes[j]+"'", conn);
        // inureAgents.append(agenters[j]);
        // inureAgents.append(",");
        // inUsers.append(operationerId[j]);
        // inUsers.append(",");
        // }
        // conn.commit();
        // } catch (Exception ex) {
        // conn.rollback();
        // ex.printStackTrace();
        // System.out.println("提交到代理出错！");
        // Comm.log(1,"[*]发现错误[*]
        // 模块名称="+getClass().getPackage()+getClass().getName()+",方法名="+"agent"+",异常信息ex="+ex.getMessage());
        // return Comm.getContextPath()+"/jsp/common/errPage.jsp?errMsg=error";
        // } finally {
        // conn.setAutoCommit(true);
        // flowInS.freeConn(CONNNAME, conn);
        // }
        // } catch (Exception ex) {
        // Comm.log(1,"[*]发现错误[*]
        // 模块名称="+getClass().getPackage()+getClass().getName()+",方法名="+"agent"+",异常信息ex="+ex.getMessage());
        // ex.printStackTrace();
        // return Comm.getContextPath()+"/jsp/common/errPage.jsp?errMsg=error";
        // }
        // }
        //
        // if (inUsers.toString().length() != 0) {
        // inUsers.deleteCharAt(inUsers.length() - 1);
        // //提醒节点处理者
        // Vector proOpera = null;
        // proOpera = this.getProNodeOpera(flowInstanceId, false);
        // proOpera.remove(0);
        // proOpera.add(0, inUsers);
        // proOpera.remove(1);
        // proOpera.add(1, new StringBuffer());
        // proOpera.remove(2);
        // proOpera.add(2, new StringBuffer());
        // proOpera.remove(3);
        // proOpera.add(3, new StringBuffer());
        // if (proOpera != null)
        // flowRemind(proOpera, "工作提示",
        // proOpera.get(4).toString()+"已转给您的工作代理人！");
        // }
        //
        // if (inureAgents.toString().length() != 0) {
        // inureAgents.deleteCharAt(inureAgents.length() - 1);
        // //提醒节点代理者
        // Vector proOpera = null;
        // proOpera = this.getProNodeOpera(flowInstanceId, false);
        // proOpera.remove(0);
        // proOpera.add(0, inureAgents);
        // proOpera.remove(1);
        // proOpera.add(1, new StringBuffer());
        // proOpera.remove(2);
        // proOpera.add(2, new StringBuffer());
        // proOpera.remove(3);
        // proOpera.add(3, new StringBuffer());
        // if (proOpera != null)
        // flowRemind(proOpera, "工作提示", proOpera.get(4).toString()+"等待您处理！");
        // }
        //
        // if (flowEvent.equals("auditing")) {
        // return
        // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
        // } else if (flowEvent.equals("affirm")) {
        // return TranCodeBean.tranCodeC(sourceURI);
        // }
        // } else {
        // //撤签
        // //提醒节点处理者
        // Vector proOpera = null;
        // proOpera = this.getProNodeOpera(flowInstanceId, false);
        //
        // try {
        // Connection conn = flowInS.getConn(CONNNAME);
        // Connection busConn = null;
        // try {
        // conn.setAutoCommit(false);
        // //删除当前节点
        // flowInS.executeStaticSql("delete from T_SYS_PROCESSNODE where
        // flowInstanceId ='"+flowInstanceId+"'", conn);
        // //修改流程实例状态
        // String sql = "update T_SYS_FLOWINSTANCE set instanceState='"+state[2]+"'
        // where flowInstanceId ='"+flowInstanceId+"'";
        // flowInS.executeStaticSql(sql, conn);
        // //修改主记录
        // flowInS.selectSql("select
        // correlation,correlationId,primaryKey,flowInterfaceName,ztbgFieldName,stateName,CONNNAME
        // from T_SYS_FLOWINSTANCE where flowInstanceId='" +
        // flowInstanceId + "' with ur", conn);
        // String tableName = "";
        // String id = "";
        // String primaryKey = "";
        // String flowInterfaceName = "";
        // String ztbgFieldName = "";
        // String stateName = "state";
        // if (flowInS.rsNext()) {
        // tableName = flowInS.getField("correlation");
        // id = flowInS.getField("correlationId");
        // primaryKey = flowInS.getField("primaryKey");
        // flowInterfaceName = flowInS.getField("flowInterfaceName");
        // ztbgFieldName = flowInS.getField("ztbgFieldName");
        // if (!flowInS.getField("stateName").equals("")) {
        // stateName = flowInS.getField("stateName");
        // }
        // BUSCONNNAME = flowInS.getField("CONNNAME");
        // }
        // //flowInS.closeDB();
        //
        // String ztbgsql = "";
        // if (!ztbgFieldName.equals("")) {
        // ztbgsql = "," + ztbgFieldName + "=" +
        // flowInS.toTimeStampSQL(TimeComm.getNYRDate() + " " +
        // TimeComm.getSFMDate());
        // }
        //
        // if (BUSCONNNAME.equals("")) {
        // //修改主记录
        // String sqlBus = "update " + tableName + " set " + stateName +
        // "='" +
        // businessState[3] + "'" + ztbgsql + " where " + primaryKey +
        // "='" + id + "'";
        // flowInS.executeStaticSql(sqlBus, conn);
        // }
        // if (!flowInterfaceName.equals("")) {
        // FlowInterface flowInterface = (FlowInterface) Class.forName(
        // flowInterfaceName).newInstance();
        // request.setAttribute("currentState", businessState[3]);
        // request.setAttribute("id", id);
        // if (!BUSCONNNAME.equals("")) {
        // busConn = flowInS.getConn(BUSCONNNAME);
        // busConn.setAutoCommit(false);
        // flowInterface.afterAuditing(request, session, busConn);
        // } else {
        // flowInterface.afterAuditing(request, session, conn);
        // }
        // }
        // conn.commit();
        // } catch (Exception ex) {
        // conn.rollback();
        // throw ex;
        // } finally {
        // conn.setAutoCommit(true);
        // flowInS.freeConn(CONNNAME, conn);
        // }
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // System.out.println("撤签中得到连接失败1！");
        // String faultInfo = ex.toString();
        // Comm.log(1,"[*]发现错误[*]
        // 模块名称="+getClass().getPackage()+getClass().getName()+",方法名="+"agent"+",异常信息ex="+ex.getMessage());
        // if (request.getAttribute("faultInfo") != null) {
        // faultInfo = (String)request.getAttribute("faultInfo");
        // }
        // faultInfo = TranCodeBean.tranCodeC(faultInfo);
        // return
        // Comm.getContextPath()+"/jsp/common/errPage.jsp?errMsg="+faultInfo;
        // }
        //
        // //提醒节点处理者
        // if (proOpera != null)
        // flowRemind(proOpera, "工作提示", proOpera.get(4).toString()+"已撤销！");
        //
        // if (flowEvent.equals("auditing")) {
        // return
        // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
        // } else if (flowEvent.equals("affirm")) {
        // return TranCodeBean.tranCodeC(sourceURI);
        // }
        // }
        // }//用代理替换当前操作者{

        // 改变节点操作者{
        else if (paraValue[4].equals("changeOperator")) {
            String flowInstanceId = tranCodeP(request.getParameter("flowInstanceId"));
            String instanceNodeId = tranCodeP(request.getParameter("instanceNodeId"));
            String operationer = tranCodeP(request.getParameter("operationer"));
            String operationerId = tranCodeP(request.getParameter("operationerId"));
            String isProNode = tranCodeP(request.getParameter("isProNode"));

            String orgUserType = "";
            String orgUser = "";
            String[] updName = {"operationer", "operationerId"};
            String[] updValue = {operationer, operationerId};
            String[] updConName = {"instanceNodeId"};
            String[] updConValue = {instanceNodeId};
            try {
                if (isProNode.equals("Y")) {

                    Map params = new HashMap();
                    params.put("SelSQL", "select OPERATIONER,OPERATIONERID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId='"
                            + instanceNodeId + "' ");
                    Map reMap = flowService.selcom(params);

                    if (reMap != null) {
                        orgUserType = tranCodeN(reMap.get("OPERATIONER"));
                        orgUser = tranCodeN(reMap.get("OPERATIONERID"));
                    }
                }
                StringBuffer updateSQL=new StringBuffer();
                updateSQL.append(" update ");
                updateSQL.append(Consts.SYSSCHEMA);
                updateSQL.append(".T_SYS_INSTANCENODE set ");
                //updateSQL.append(updName);
                updateSQL.append("='");
               // updateSQL.append(updValue);
                updateSQL.append("' where ");
               // updateSQL.append(updConName);
                updateSQL.append("='");
               // updateSQL.append(updConValue);
                updateSQL.append("'");
                Map params = new HashMap();
                params.clear();
                params.put("UpdSQL", updateSQL.toString());
                txUpdcom(params);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("修改实例节点操作者出错！");
                System.err.println("[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "affirm" + ",异常信息ex="
                        + ex.getMessage());
                request.setAttribute("msg", "[*]发现错误[*] 模块名称=" + getClass().getPackage() + getClass().getName() + ",方法名=" + "affirm" + ",异常信息ex="
                        + ex.getMessage());
                throw new ServiceException("441");

            }
            if (isProNode.equals("Y")) {
                // 提醒原来节点处理者
                Vector proOpera = null;
                proOpera = this.getProNodeOpera(flowInstanceId, false);

                for (int i = 0; i < 4; i++) {
                    proOpera.remove(i);
                    if (orgUserType.equals(String.valueOf(i))) {
                        proOpera.add(i, orgUser);
                    } else {
                        proOpera.add(i, new StringBuffer());
                    }
                }
                // if (proOpera != null)
                // flowRemind(proOpera, "工作提示",
                // proOpera.get(4).toString()+"工作已转交给其他人处理！");

                // 提醒改变后节点处理者
                for (int i = 0; i < 4; i++) {
                    proOpera.remove(i);
                    if (operationer.equals(String.valueOf(i))) {
                        proOpera.add(i, operationerId);
                    } else {
                        proOpera.add(i, new StringBuffer());
                    }
                }
                // if (proOpera != null)
                // flowRemind(proOpera, "工作提示",
                // proOpera.get(4).toString()+"等待您处理！");
            }
            // return
            // "pages/sysutil/spflow/instance.jsp?flowInstanceId="+flowInstanceId+"&refresh=T";
            request.setAttribute("flowInstanceId", flowInstanceId);
            request.setAttribute("flowInstanceId", "T");
            return "instance";

        }// 改变节点操作者}

        // 设定多人操作节点的具体操作用户{
        else if (paraValue[4].equals("multiUsers")) {
            String flowInstanceId = tranCodeP(request.getParameter("flowInstanceId"));
            String flowEvent = tranCodeP(request.getParameter("flowEvent"));
            String sourceURI = tranCodeP(request.getParameter("sourceURI"));
            String priorNodeId = tranCodeP(request.getParameter("priorNodeId"));
            String prionOperationFlag = tranCodeP(request.getParameter("prionOperationFlag"));
            if (prionOperationFlag.equals("")) {
                prionOperationFlag = "0";
            }
            String allOperators = tranCodeP(request.getParameter("allOperators"));

            String[] xtyhids = request.getParameterValues("xtyhids");
            String[] agentids = request.getParameterValues("agentids");
            StringTokenizer allOperator = new StringTokenizer(allOperators, ",", false);
            allOperators = "('@#$'" + allOperators + ")";

            boolean isChoosedMems = false;

            Vector nodeV = new Vector();
            if (xtyhids != null) {
                isChoosedMems = true;
                for (int i = 0; i < xtyhids.length; i++) {
                    String nodeId = xtyhids[i].substring(0, xtyhids[i].indexOf(","));
                    int a = nodeV.indexOf(nodeId);
                    if (nodeV.indexOf(nodeId) == -1) {
                        nodeV.add(nodeId);
                        nodeV.add(new StringBuffer("('@#$'"));
                        a = nodeV.indexOf(nodeId);
                    }
                    StringBuffer nodeMem = (StringBuffer) nodeV.get(a + 1);
                    nodeMem.append(",'");
                    nodeMem.append(xtyhids[i].substring(xtyhids[i].indexOf(",") + 1));
                    nodeMem.append("'");
                    nodeV.set(a + 1, nodeMem);
                }
            }
            try {
                try {
                    // 有节点操作者选中
                    if (xtyhids != null) {
                        StringBuffer operators = new StringBuffer("('@#$'"); // 存放选中成员的节点id
                        for (int i = 0; i < nodeV.size(); i = i + 2) {
                            StringBuffer allUsers = (StringBuffer) nodeV.get(i + 1);
                            operators.append(",'");
                            operators.append(nodeV.get(i));
                            operators.append("'");
                            String delsql = "delete from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation where instanceNodeId='" + nodeV.get(i)
                                    + "' and operationFlag=0 and operationUser not in " + allUsers + ")";

                            Map params = new HashMap();
                            params.put("DelSQL", delsql);
                            txDelcom(params);

                            String processNodeId = TimeComm.getTimeStamp(String.valueOf(i));
                            String insertProcessNode = "insert into " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE select distinct '" + processNodeId + "','"
                                    + flowInstanceId + "','" + nodeV.get(i).toString() + "',"
                                    + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + " from " + Consts.SYSSCHEMA
                                    + ".T_SYS_PROCESSNODE where 1>(select " + "count(processNodeId) from " + Consts.SYSSCHEMA
                                    + ".T_SYS_PROCESSNODE where instanceNodeId='" + nodeV.get(i).toString() + "')";

                            params.clear();
                            params.put("InsSQL", insertProcessNode);
                            txInscom(params);

                        }

                        // 用选中的工作代理替换操作用户 开始
                        if (agentids != null) {
                            for (int i = 0; i < agentids.length; i++) {
                                String agentstr = agentids[i];
                                String nodeId = agentstr.substring(0, agentstr.indexOf(","));
                                agentstr = agentstr.substring(agentstr.indexOf(",") + 1);
                                String userid = agentstr.substring(0, agentstr.indexOf(","));
                                String agentid = agentstr.substring(agentstr.indexOf(",") + 1);
                                String updsql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationuser='" + agentid
                                        + "' where instanceNodeId='" + nodeId + "' and operationuser='" + userid + "' and operationFlag=0";

                                Map params = new HashMap();
                                params.put("UpdSQL", updsql);
                                txUpdcom(params);

                            }
                        }

                        // 用选中的工作代理替换操作用户 结束

                        operators.append(")");
                        String updsql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + prionOperationFlag
                                + " where instanceNodeId='" + priorNodeId + "' and operationFlag=0";
                        Map params = new HashMap();
                        params.put("InsSQL", updsql);
                        txInscom(params);

                        params.clear();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ". T_SYS_PROCESSNODE where instanceNodeId='" + priorNodeId
                                + "' and instanceNodeId not in " + operators);
                        txDelcom(params);

                        params.clear();
                        params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId in " + allOperators
                                + " and instanceNodeId not in " + operators);
                        txDelcom(params);

                        // 删除多余分支节点、节点操作
                        String useNodes = "('@#$')";
                        String unuseNodes = "('@#$')";
                        while (allOperator.hasMoreTokens()) {
                            String nodeId = allOperator.nextToken();
                            if (operators.toString().indexOf(nodeId) != -1) {
                                continue;
                            } else {
                                String vsql = ""; // 取得使用中的支线节点id的sql
                                String sql = ""; // 取得未使用的支线节点id的sql
                                if (Consts.DBTYPE.equals("DB2")) {
                                    vsql = "with pathTree(endNodeId, startNodeId) as " + "( select endNodeId, startNodeId from "
                                            + "(select endNodeId,startNodeId from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                            + flowInstanceId + "' ) resu1" + " where startNodeId='" + nodeV.get(0) + "' " + "union all select "
                                            + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from T_SYS_INSTANCENODEPath,pathTree "
                                            + "where T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ";
                                    sql = "with pathTree(endNodeId, startNodeId) as "
                                            + "( select endNodeId, startNodeId from "
                                            + "(select endNodeId,startNodeId from "
                                            + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                            + flowInstanceId
                                            + "' ) resu1"
                                            + " where startNodeId="
                                            + nodeId
                                            + " "
                                            + "union all select "
                                            + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from T_SYS_INSTANCENODEPath,pathTree "
                                            + "where T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree with ur ";

                                } else if (Consts.DBTYPE.equals("ORACLE")) {
                                    vsql = "select distinct(endNodeId) endNodeId from " + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and startNodeId='"
                                            + nodeV.get(0) + "' connect by prior endNodeId=startNodeId ";
                                    sql = "select distinct(endNodeId) endNodeId from " + Consts.SYSSCHEMA
                                            + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and startNodeId=" + nodeId
                                            + " connect by prior endNodeId=startNodeId";
                                }else if(Consts.DBTYPE.equals("MSSQL")){
                                	vsql = "with pathTree(ENDNODEID, STARTNODEID) as " + "( select ENDNODEID, STARTNODEID from "
                                    + "(select ENDNODEID,STARTNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                    + flowInstanceId + "' ) resu1" + " where STARTNODEID='" + nodeV.get(0) + "' " + "union all select "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from T_SYS_INSTANCENODEPath,pathTree "
                                    + "where T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ";
                                	sql = "with pathTree(ENDNODEID, STARTNODEID) as "
                                    + "( select ENDNODEID, STARTNODEID from "
                                    + "(select ENDNODEID,STARTNODEID from "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                    + flowInstanceId
                                    + "' ) resu1"
                                    + " where STARTNODEID="
                                    + nodeId
                                    + " "
                                    + "union all select "
                                    + Consts.SYSSCHEMA
                                    + ".T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from T_SYS_INSTANCENODEPath,pathTree "
                                    + "where T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ";
                                }
                                StringBuffer inuseNodes = new StringBuffer("('@#$'");
                                params.clear();
                                params.put("SelSQL", vsql);
                                List reList = flowService.selcomList(params);
                                for (int i = 0; i < reList.size(); i++) {
                                    Map resMap = (Map) reList.get(i);

                                    inuseNodes.append(",'");
                                    inuseNodes.append(tranCodeN(resMap.get("ENDNODEID")));
                                    inuseNodes.append("'");
                                }

                                inuseNodes.append(")");
                                useNodes = inuseNodes.toString();

                                StringBuffer nouseNodes = new StringBuffer("('@#$'");

                                params.clear();
                                params.put("SelSQL", sql);
                                List reList1 = flowService.selcomList(params);
                                for (int i = 0; i < reList1.size(); i++) {
                                    Map resMap = (Map) reList1.get(i);
                                    nouseNodes.append(",'");
                                    nouseNodes.append(tranCodeN(resMap.get("ENDNODEID")));
                                    nouseNodes.append("'");
                                }
                                nouseNodes.append(")");
                                unuseNodes = nouseNodes.toString();

                                String asql = "delete from " + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODE where instanceNodeId in " + nouseNodes
                                        + " and instanceNodeId not in " + inuseNodes + " or instanceNodeId=" + nodeId;
                                String bsql = "delete from " + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODEOperation where instanceNodeId in " + nouseNodes
                                        + " and instanceNodeId not in " + inuseNodes + " or instanceNodeId=" + nodeId;
                                String csql = "delete from " + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODEPath where (startNodeId in " + nouseNodes
                                        + " and startNodeId not in " + inuseNodes + ")  or startNodeId=" + nodeId + " or (endNodeId in " + nouseNodes
                                        + " and endNodeId not in " + inuseNodes + ") or endNodeId=" + nodeId;
                                ;

                                params.clear();
                                params.put("DelSQL", csql);
                                txDelcom(params);
                                params.clear();
                                params.put("DelSQL", bsql);
                                txDelcom(params);
                                params.clear();
                                params.put("DelSQL", asql);
                                txDelcom(params);
                            }
                        }

                        // 左移左侧有空的节点
                        // 取得并列节点的公共后续节点
                        StringBuffer sonsOfPublic = new StringBuffer("('@#$'");
                        String selsql = "select INSTANCENODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='" + flowInstanceId
                                + "' and instanceNodeId in " + useNodes + " and instanceNodeId in " + unuseNodes;

                        params.clear();
                        params.put("SelSQL", selsql);
                        List reList = flowService.selcomList(params);
                        for (int i = 0; i < reList.size(); i++) {
                            Map resMap = (Map) reList.get(i);
                            sonsOfPublic.append(",'");
                            sonsOfPublic.append(tranCodeN(resMap.get("INSTANCENODEID")));
                            sonsOfPublic.append("'");
                        }
                        // flowInS.closeDB();
                        sonsOfPublic.append(")");

                        if (!sonsOfPublic.toString().equals("('@#$')")) {
                            // flowInS.selectSql(
                            // "select min(nodeX) nodeX from T_SYS_INSTANCENODE
                            // where flowInstanceId='" +
                            // flowInstanceId + "' and instanceNodeId in " +
                            // useNodes+" with ur", conn);

                            String parentNode = "";

                            params.clear();
                            params.put("SelSQL", "select STARTNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where endNodeId='"
                                    + nodeV.get(0) + "' and flowInstanceId='" + flowInstanceId + "'");
                            Map resMap1 = flowService.selcom(params);
                            if (resMap1 != null) {
                                parentNode = tranCodeN(resMap1.get("STARTNODEID"));
                            }

                            String dsql = "";
                            StringBuffer sonsOfParent = new StringBuffer("('@#$'");
                            if (Consts.DBTYPE.equals("DB2")) {
                                dsql = "with pathTree(endNodeId, startNodeId) as "
                                        + "( select endNodeId, startNodeId from "
                                        + "(select endNodeId,startNodeId from "
                                        + Consts.SYSSCHEMA
                                        + ". T_SYS_INSTANCENODEPath where flowInstanceId='"
                                        + flowInstanceId
                                        + "' ) resu1"
                                        + " where startNodeId='"
                                        + parentNode
                                        + "' "
                                        + "union all select T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from T_SYS_INSTANCENODEPath,pathTree "
                                        + "where T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree with ur";
                            } else if (Consts.DBTYPE.equals("ORACLE")) {
                                dsql = "select distinct(endNodeId) endNodeId from " + Consts.SYSSCHEMA
                                        + ". T_SYS_INSTANCENODEPath start with flowInstanceId='" + flowInstanceId + "' and startNodeId='" + parentNode
                                        + "' connect by prior endNodeId=startNodeId with ur";
                            }else if(Consts.DBTYPE.equals("MSSQL")){
                            	dsql = "with pathTree(ENDNODEID, STARTNODEID) as "
                                    + "( select ENDNODEID, STARTNODEID from "
                                    + "(select ENDNODEID,STARTNODEID from "
                                    + Consts.SYSSCHEMA
                                    + ". T_SYS_INSTANCENODEPath where flowInstanceId='"
                                    + flowInstanceId
                                    + "' ) resu1"
                                    + " where STARTNODEID='"
                                    + parentNode
                                    + "' "
                                    + "union all select T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from T_SYS_INSTANCENODEPath,pathTree "
                                    + "where T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree";
                            }

                            params.clear();
                            params.put("SelSQL", dsql);
                            List reList2 = flowService.selcomList(params);
                            for (int i = 0; i < reList2.size(); i++) {
                                Map resMap = (Map) reList2.get(i);
                                sonsOfParent.append(",'");
                                sonsOfParent.append(tranCodeN(resMap.get("ENDNODEID")));
                                sonsOfParent.append("'");
                            }
                            sonsOfParent.append(")");

                            dsql = "select DISTINCT(NODEX) NODEX from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='"
                                    + flowInstanceId + "' and instanceNodeId in " + sonsOfParent + " and instanceNodeId not in " + sonsOfPublic
                                    + " order by nodeX ";

                            params.clear();
                            params.put("SelSQL", dsql);
                            List reList3 = flowService.selcomList(params);

                            int i = 0;
                            for (int k = 0; k < reList3.size(); k++) {
                                Map resMap = (Map) reList3.get(k);
                                String nodeX = String.valueOf(200 + nodeWidth * i);
                                if (!tranCodeN(resMap.get("NODEX")).equals(nodeX)) {
                                    String updsql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE set nodeX=" + nodeX + " where flowInstanceId='"
                                            + flowInstanceId + "' and nodeX=" + tranCodeN(resMap.get("NODEX")) + " and instanceNodeId in "
                                            + sonsOfParent + " and instanceNodeId not in " + sonsOfPublic;
                                    params.clear();
                                    params.put("UpdSQL", updsql1);
                                    txUpdcom(params);

                                }
                                i++;
                            }

                            dsql = "select DISTINCT(NODEX) NODEX from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='"
                                    + flowInstanceId + "' order by nodeX ";

                            params.clear();
                            params.put("SelSQL", dsql);
                            List reList4 = flowService.selcomList(params);

                            i = 0;
                            nodeWidth = 80;
                            for (int k = 0; k < reList4.size(); k++) {
                                Map resMap = (Map) reList4.get(k);
                                String nodeX = String.valueOf(200 + nodeWidth * i);
                                if (!tranCodeN(resMap.get("NODEX")).equals(nodeX)) {
                                    String updsql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE set nodeX=" + nodeX + " where flowInstanceId='"
                                            + flowInstanceId + "' and nodeX=" + tranCodeN(resMap.get("NODEX"));

                                    params.clear();
                                    params.put("UpdSQL", updsql1);
                                    txUpdcom(params);

                                }
                                i++;
                            }
                            // flowInS.closeDB();

                            dsql = "select DISTINCT(NODEY) NODEY from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where flowInstanceId='"
                                    + flowInstanceId + "' order by nodeY ";

                            params.clear();
                            params.put("SelSQL", dsql);
                            List reList5 = flowService.selcomList(params);

                            i = 0;
                            for (int k = 0; k < reList5.size(); k++) {
                                Map resMap = (Map) reList5.get(k);
                                String nodeY = String.valueOf(30 + nodeHeight * i);
                                if (!tranCodeN(resMap.get("NODEY")).equals(nodeY)) {
                                    String updsql1 = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE set nodeY=" + nodeY + " where flowInstanceId='"
                                            + flowInstanceId + "' and nodeY=" + tranCodeN(resMap.get("NODEY"));

                                    params.clear();
                                    params.put("UpdSQL", updsql1);
                                    txUpdcom(params);

                                }
                                i++;
                            }
                            // flowInS.closeDB();
                        }
                    } else {
                        // 没有选中任何操作者
                        while (allOperator.hasMoreTokens()) {
                            int i = 0;
                            String insNodeId = allOperator.nextToken();
                            String processNodeId = TimeComm.getTimeStamp(String.valueOf(i));
                            String updsql = "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation set operationFlag=" + prionOperationFlag
                                    + " where instanceNodeId='" + priorNodeId + "' and operationFlag=0";

                            Map params = new HashMap();
                            params.put("UpdSQL", updsql);
                            txUpdcom(params);

                            params.clear();
                            params.put("DelSQL", "delete from " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId='" + priorNodeId + "'");
                            txDelcom(params);

                            params.clear();
                            params.put("SelSQL", "select count(processNodeId) NUM from  " + Consts.SYSSCHEMA + ".T_SYS_PROCESSNODE where instanceNodeId="
                                    + insNodeId);
                            Map resMap = flowService.selcom(params);

                            int count = 0;
                            if (resMap != null) {
                                count = resMap.get("NUM") == null ? 0 : Integer.parseInt(resMap.get("NUM").toString());
                            }
                            if (count == 0) {
                                String inssql = "insert into T_SYS_PROCESSNODE values('" + processNodeId + "','" + flowInstanceId + "'," + insNodeId
                                        + "," + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";

                                params.clear();
                                params.put("InsSQL", inssql);
                                txInscom(params);

                            }
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("设置节点多人操作出错！");
                    ex.printStackTrace();
                    request.setAttribute("msg", "设置节点多人操作出错！");
                    throw new ServiceException("441");
                }
            } catch (Exception ex) {
                logger.error(ex);
                throw new ServiceException("441");
            }

            Vector proOpera = null;
            proOpera = getProNodeOpera(flowInstanceId, isChoosedMems);
            if (proOpera != null)
                // 这个功能不用
                // flowRemind(proOpera, "工作提示",
                // proOpera.get(4).toString()+"等待您处理！");

                if (flowEvent.equals("affirm")) {
                    System.err.println("sourceURI==" + sourceURI);
                    return "sourceURI";
                } else {
                    request.setAttribute("flowInstanceId", flowInstanceId);
                    request.setAttribute("flowInstanceId", "T");
                    return "instance";
                    // return
                    // "pages/sysutil/spflow/instance.jsp?flowInstanceId=" +
                    // flowInstanceId + "&refresh=T";
                }
        }// 设定多人操作节点的具体操作用户}

        else if (paraValue[4].equals("exception")) {
            String flowInstanceId = tranCodeP(request.getParameter("flowInstanceId"));
            String flowEvent = tranCodeP(request.getParameter("flowEvent"));
            String unloadType = tranCodeP(request.getParameter("unloadType"));
            String sourceURI = tranCodeP(request.getParameter("sourceURI"));

            if (unloadType.equals("1")) {
                if (flowEvent.equals("affirm")) {
                    try {


                            Map params = new HashMap();
                            params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE set instanceState='" + state[2]
                                    + "' where flowInstanceid='" + flowInstanceId + "'");
                            txUpdcom(params);

                            params.clear();
                            params.put("SelSQL",
                                    "select CORRELATION,CORRELATIONID,PRIMARYKEY,FLOWINTERFACENAME,ZTBGFIELDNAME,STATENAME,CONNNAME from "
                                            + Consts.SYSSCHEMA + ". T_SYS_FLOWINSTANCE where flowInstanceId='" + flowInstanceId + "'");
                            Map resMap = flowService.selcom(params);
                            String tableName = "";
                            String id = "";
                            String primaryKey = "";
                            String flowInterfaceName = "";
                            String ztbgFieldName = "";
                            String stateName = "state";
                            if (resMap != null) {
                                tableName = tranCodeN(resMap.get("CORRELATION"));
                                id = tranCodeN(resMap.get("CORRELATIONID"));
                                primaryKey = tranCodeN(resMap.get("PRIMARYKEY"));
                                flowInterfaceName = tranCodeN(resMap.get("FLOWINTERFACENAME"));
                                ztbgFieldName = tranCodeN(resMap.get("ZTBGFIELDNAME"));
                                if (!tranCodeN(resMap.get("STATENAME")).equals("")) {
                                    stateName = tranCodeN(resMap.get("STATENAME"));
                                }
                            }

                            String ztbgsql = "";
                            if (!ztbgFieldName.equals("")) {
                                ztbgsql = "," + ztbgFieldName + "=" + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate());
                            }

                            // 修改主记录
                            String sqlBus = "update " + Consts.SYSSCHEMA + "." + tableName + " set " + stateName + "='" + businessState[3] + "'"
                                    + ztbgsql + " where " + primaryKey + "='" + id + "'";

                            params.clear();
                            params.put("UpdSQL", sqlBus);
                            txUpdcom(params);

                            if (!flowInterfaceName.equals("")) {
                                FlowInterface flowInterface = (FlowInterface) Class.forName(flowInterfaceName).newInstance();
                                request.setAttribute("currentState", businessState[3]);
                                request.setAttribute("id", paraValue[3]);
                                flowInterface.afterAuditing(request, session, flowService);
                            }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.error(ex);
                        throw new ServiceException("441");
                    }

                    System.err.println("sourceURI==" + sourceURI);
                    return "sourceURI";
                } else {
                    return null;
                }
            }

        } else if (paraValue[4].equals("addNode")) {
            // 加签{
        	String[] userParameter = {"OperType", "OperID", "BackFlag", "JumpFlag",
                    // 4 5 6 7 8 9
                            "ExpandFlag", "EditFlag", "CancleFlag", "AgentFlag", "DelayFlag", "DelayDay",
                            // 10 11 12 13 14 15 16
                            "AllFlag", "event", "IX", "IY", "ProNodeID", "ProcessName", "ProcessID",
                            // 17 18 19 20
                            "ProcessClass", "nodeOperationId", "flowInstanceId", "instanceNodeId"};
                    String[] parameterValue = this.prepareArray(request, userParameter, 0);
            // parameterValue[9] = null;

            // 插入节点
            // 在当前节点之下串接

            // 生成要插入节点ID
            String instanceNodeId = TimeComm.getTimeStamp("");
            // 取得当前节点坐标
            String nodex = "", nodey = "";
            String nodeOperationName = "";
            try {
                String sql = " select nODEX,NODEY+" + nodeHeight + " NODEY from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODE where instanceNodeId='"
                        + parameterValue[20] + "'";

                Map params = new HashMap();
                params.put("SelSQL", sql);
                Map resMap = flowService.selcom(params);

                if (resMap != null) {
                    nodex = tranCodeN(resMap.get("NODEX"));
                    nodey = tranCodeN(resMap.get("NODEY"));
                }

                params.clear();
                params.put("SelSQL", "select OPERATIONNAME from " + Consts.SYSSCHEMA + ".T_SYS_NODEOPERATION where NODEOPERATIONID='"
                        + parameterValue[18] + "'");
                Map resMap1 = flowService.selcom(params);

                if (resMap1 != null) {
                    nodeOperationName = tranCodeN(resMap1.get("OPERATIONNAME"));
                }
            } catch (Exception ex) {
                request.setAttribute("msg", "选取节点坐标出错！");
                throw new ServiceException("441");
            }

            String[] nodeInsertName = {"instanceNodeId", "operationerId", "operationer", "flowInstanceId", "nodeX", "nodeY", "backFlag", "jumpFlag",
                    "expandFlag", "editFlag", "cancleFlag", "agentFlag", "delayFlag", "delayDays", "allFlag"};

            String[] nodeInsertValue = {instanceNodeId, parameterValue[1], parameterValue[0], parameterValue[19], nodex, nodey, parameterValue[2],
                    parameterValue[3], parameterValue[4], parameterValue[5], parameterValue[6], parameterValue[7], parameterValue[8],
                    parameterValue[9], parameterValue[10]};
            try {
                // 得到连接做事务处理
                try {
                    StringBuffer sonsNodeStr = new StringBuffer("('@#$'");
                    // 选取当前节点的所有后续节点
                    String vsql = "";
                    if (Consts.DBTYPE.equals("DB2")) {
                        vsql = "with pathTree(endNodeId, startNodeId) as " + "( select endNodeId, startNodeId from "
                                + "(select endNodeId,startNodeId from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                                + parameterValue[19] + "' ) resu1" + " where startNodeId='" + parameterValue[20] + "' "
                                + "union all select T_SYS_INSTANCENODEPath.endNodeId,T_SYS_INSTANCENODEPath.startNodeId from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath,pathTree "
                                + "where T_SYS_INSTANCENODEPath.startNodeId=pathTree.endNodeId) select distinct(endNodeId)  from pathTree ";
                    } else if (Consts.DBTYPE.equals("ORACLE")) {
                        vsql = "select distinct(endNodeId),startNodeId,flowInstanceId,level from " + Consts.SYSSCHEMA
                                + ".T_SYS_INSTANCENODEPath start with flowInstanceId='" + parameterValue[19] + "' and startNodeId='" + parameterValue[20]
                                + "' connect by prior endNodeId=startNodeId with ur";
                    }else if (Consts.DBTYPE.equals("MSSQL")) {
                    	vsql = "with pathTree(ENDNODEID, STARTNODEID) as " + "( select ENDNODEID, STARTNODEID from "
                        + "(select ENDNODEID,STARTNODEID from " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath where flowInstanceId='"
                        + parameterValue[19] + "' ) resu1" + " where STARTNODEID='" + parameterValue[20] + "' "
                        + "union all select T_SYS_INSTANCENODEPath.ENDNODEID,T_SYS_INSTANCENODEPath.STARTNODEID from " + Consts.SYSSCHEMA
                        + ".T_SYS_INSTANCENODEPath,pathTree "
                        + "where T_SYS_INSTANCENODEPath.STARTNODEID=pathTree.ENDNODEID) select distinct(ENDNODEID)  from pathTree ";
                    }

                    Map params = new HashMap();
                    params.put("SelSQL", vsql);
                    List reList = flowService.selcomList(params);
                    for (int i = 0; i < reList.size(); i++) {
                        Map resMap = (Map) reList.get(i);
                        sonsNodeStr.append(",'");
                        sonsNodeStr.append(tranCodeN(resMap.get("ENDNODEID")));
                        sonsNodeStr.append("'");
                    }
                    sonsNodeStr.append(")");

                    // 取得当前节点的下一个节点
                    String nextNode = "";
                    String sql = "select INSTANCENODEID from " + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODE where nodeY=" + nodey + " and flowInstanceId='"
                            + parameterValue[19] + "' and instanceNodeId in " + sonsNodeStr.toString();

                    params.clear();
                    params.put("SelSQL", sql);
                    Map resMap = flowService.selcom(params);

                    if (resMap != null) {
                        nextNode = tranCodeN(resMap.get("INSTANCENODEID"));
                    }
                    if (!nextNode.equals("")) {

                        params.clear();
                        params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ". T_SYS_INSTANCENODE set nodeY=nodeY+" + nodeHeight
                                + " where instanceNodeId in " + sonsNodeStr);
                        txUpdcom(params);

                    }
                    // 插入节点
                    // flowInS.insertDB("T_SYS_INSTANCENODE", nodeInsertName,
                    // nodeInsertValue, conn);
                    StringBuffer aSQL = new StringBuffer();
                    aSQL.append(" insert into " + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODE (instanceNodeId, operationerId, operationer, flowInstanceId,nodeX, nodeY,backFlag, jumpFlag, "
                            + "expandFlag, editFlag, cancleFlag,agentFlag,delayFlag, delayDays, allFlag) ");
                    aSQL.append(" values('");
                    aSQL.append(nodeInsertValue[0]);
                    aSQL.append("','");
                    aSQL.append(nodeInsertValue[1]);
                    aSQL.append("','");
                    aSQL.append(nodeInsertValue[2]);
                    aSQL.append("','");
                    aSQL.append(nodeInsertValue[3]);
                    aSQL.append("',");
                    aSQL.append(nodeInsertValue[4]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[5]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[6]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[7]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[8]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[9]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[10]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[11]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[12]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[13]);
                    aSQL.append(",");
                    aSQL.append(nodeInsertValue[14]);
                    aSQL.append(")");
                    params.clear();
                    params.put("InsSQL", aSQL.toString());
                    txInscom(params);

                    // 修改路径以当前节点为开始节点的所有节点的开始节点为插入节点

                    params.clear();
                    params.put("UpdSQL", "update " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEPath set startNodeId='" + instanceNodeId
                            + "' where startNodeId='" + parameterValue[20] + "' and flowInstanceId='" + parameterValue[19] + "'");
                    txUpdcom(params);

                    // 插入节点路径以当前节点为开始节点，以插入节点为终节点
                    String[] pathInsertName = {"instanceNodePathId", "startNodeId", "endNodeId", "flowInstanceId"};
                    String[] pathInsertValue = {TimeComm.getTimeStamp("p"), parameterValue[20], instanceNodeId, parameterValue[19]};
                    // flowInS.insertDB("T_SYS_INSTANCENODEPath", pathInsertName,
                    // pathInsertValue, conn);
                    StringBuffer aSQL1 = new StringBuffer();
                    aSQL1.append(" insert into " + Consts.SYSSCHEMA
                            + ".T_SYS_INSTANCENODEPath (instanceNodePathId, startNodeId, endNodeId, flowInstanceId) ");
                    aSQL1.append(" values('");
                    aSQL1.append(pathInsertValue[0]);
                    aSQL1.append("','");
                    aSQL1.append(pathInsertValue[1]);
                    aSQL1.append("','");
                    aSQL1.append(pathInsertValue[2]);
                    aSQL1.append("','");
                    aSQL1.append(pathInsertValue[3]);
                    aSQL1.append("')");
                    params.clear();
                    params.put("InsSQL", aSQL1.toString());
                    txInscom(params);

                    // 插入新的实例节点操作
                    if (parameterValue[10].equals("0")) {
                        String insNodeOperId = TimeComm.getTimeStamp("insNO");
                        String insertSql = "";
                        insertSql = " insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation(" + "instanceNodeOperationId,operationName,"
                                + "instanceNodeId,flowInstanceId,operationFlag,operationTime)" + " values ('" + insNodeOperId + "','"
                                + nodeOperationName + "','" + instanceNodeId + "','" + parameterValue[19] + "',0,"
                                + flowService.toTimeStampSQL(TimeComm.getNYRDate() + " " + TimeComm.getSFMDate()) + ")";
                        params.clear();
                        params.put("InsSQL", insertSql);
                        txInscom(params);

                    } else if (parameterValue[10].equals("1")) {
                        String tableName1 = "";
                        String fieldName1 = "XTYHID";
                        String condi1 = "";
                        if (parameterValue[0].equals("1")) {
                            tableName1 = " T_SYS_XTYH ";
                            condi1 = " bmxxid='" + parameterValue[1] + "' ";
                        } else if (parameterValue[0].equals("2")) {
                            tableName1 = " T_SYS_XTYHjs ";
                            condi1 = " xtjsid='" + parameterValue[1] + "' ";
                        } else if (parameterValue[0].equals("3")) {
                            tableName1 = " T_SYS_GZZCY ";
                            condi1 = " gzzxxid='" + parameterValue[1] + "' ";
                        }
                        Vector allYHID = new Vector();
                        params.clear();
                        params.put("SelSQL", "select " + fieldName1 + " from " + Consts.SYSSCHEMA + "." + tableName1 + " where " + condi1);
                        List reList1 = flowService.selcomList(params);
                        for (int i = 0; i < reList.size(); i++) {
                            Map resMap1 = (Map) reList1.get(i);
                            allYHID.add(tranCodeN(resMap.get(fieldName1.toUpperCase())));
                        }

                        if (allYHID.size() > 0) {
                            for (int j = 0; j < allYHID.size(); j++) {
                                String insNodeOperId = TimeComm.getTimeStamp("insNO" + String.valueOf(j));
                                String insertSql = "insert into " + Consts.SYSSCHEMA + ".T_SYS_INSTANCENODEOperation("
                                        + "instanceNodeOperationId,operationName,operationFlag,instanceNodeId,flowInstanceId,operationUser) "
                                        + " values ('" + insNodeOperId + "','" + nodeOperationName + "',0,'" + instanceNodeId + "','"
                                        + parameterValue[19] + "','" + allYHID.get(j) + "')";
                                params.clear();
                                params.put("InsSQL", insertSql);
                                txInscom(params);

                            }
                        } else {
                            request.setAttribute("msg", "为设置多人处理的节点操作者添加成员！");
                            throw new ServiceException("441");

                        }
                    }
                } catch (Exception ex) {
                    System.err.println("seriesDown error in FlowServlet!");
                    ex.printStackTrace();
                    request.setAttribute("msg", "插入节点出错！");
                    throw new ServiceException("441");
                }

            } catch (Exception ex) {
                System.err.println("得到连接错！in ModelServlet seriesDown");
            }
            // return "pages/sysutil/spflow/instance.jsp?flowInstanceId=" +
            // parameterValue[19] + "&refresh=T&flowNext=F";
            request.setAttribute("flowInstanceId", parameterValue[19]);
            request.setAttribute("flowInstanceId", "T");
            request.setAttribute("flowNext", "F");
            return "instance";
            //return mapping.findForward("pages/sys/spflow/instance.jsp");

        }
        return Consts.SUCCESS;
    }


    /**
     * Save flowtrace.
     * 
     * @param BUSINESSID the bUSINESSID
     * @param OPERATION the oPERATION
     * @param OPERATOR the oPERATOR
     * @param OPERATORNAME the oPERATORNAME
     * @param NEXTOPERATOR the nEXTOPERATOR
     * @param NEXTOPERATORNAME the nEXTOPERATORNAME
     * @param OPERATORTYPE the oPERATORTYPE
     * @param REMARK the rEMARK
     */
    public void saveFLOWTRACE(String BUSINESSID, String OPERATION, String OPERATOR, String OPERATORNAME, String NEXTOPERATOR,
            String NEXTOPERATORNAME, String OPERATORTYPE, String REMARK) {

        String Pkey = StringUtil.uuid32len();
        Map <String, String> params = new HashMap <String, String>(6);
        params.put("FLOWTRACEID", Pkey);
        params.put("BUSINESSID", BUSINESSID);
        params.put("OPERATION", OPERATION);
        params.put("OPERATOR", OPERATOR);
        params.put("OPERATORNAME", OPERATORNAME);
        params.put("NEXTOPERATOR", NEXTOPERATOR);
        params.put("NEXTOPERATORNAME", NEXTOPERATORNAME);
        params.put("OPERATORTYPE", OPERATORTYPE);
        params.put("REMARK", REMARK);
        insert("flow.instrace", params);
    }


    /**
     * Gets the oPERATORNAME.
     * 
     * @param operationer the operationer
     * @param operationerId the operationer id
     * @param flowService the flow service
     * 
     * @return the oPERATORNAME
     */
    public String getOPERATORNAME(String operationer, String operationerId, FlowService flowService) {

        Map <String, String> params = new HashMap <String, String>(2);
        String tableName = "";
        String fieldName = "";
        String condi = "";
        if (operationer.equals("0")) {
            tableName = "  T_SYS_XTYH a left join T_SYS_RYXX b on a.RYXXID=b.RYXXID  ";
            fieldName = " b.XM MC";
            condi = " XTYHID='" + operationerId + "' ";
        } else if (operationer.equals("1")) {
            tableName = " T_SYS_BMXX ";
            fieldName = " BMMC MC";
            condi = " BMXXID='" + operationerId + "' ";
        } else if (operationer.equals("2")) {
            tableName = " T_SYS_XTJS ";
            fieldName = " JSMC MC";
            condi = " XTJSID='" + operationerId + "' ";
        } else if (operationer.equals("3")) {
            tableName = " T_SYS_GZZXX ";
            fieldName = " GZZMC MC";
            condi = " GZZXXID='" + operationerId + "' ";
        }
        params.clear();
        params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where " + condi);
        Map resMap = flowService.selcom(params);
        return resMap.get("MC") == null ? "" : resMap.get("MC").toString();
    }


    //发送短消息
    /**
     * Gets the send msg.
     * 
     * @param operationer the operationer
     * @param operationerId the operationer id
     * @param flowService the flow service
     * @param SendID the send id
     * @param SendMC the send mc
     * @param ReMark the re mark
     * 
     * @return the send msg
     */
    public void getSendMsg(String operationer, String operationerId, FlowService flowService, String SendID, String SendMC, String ReMark) {

        Map <String, String> params = new HashMap <String, String>(2);
        String tableName = "";
        String fieldName = "";
        String condi = "";
        if (operationer.equals("0")) {
            tableName = "  T_SYS_XTYH    ";
            fieldName = " YHM MC,XTYHID ID ";
            condi = " XTYHID='" + operationerId + "' ";
        } else if (operationer.equals("1")) {
            tableName = " T_SYS_BMXX a left join T_SYS_XTYH b on a.BMXXID=b.BMXXID  and b.DELFLAG=0   ";
            fieldName = " YHM MC,XTYHID ID ";
            condi = " a.BMXXID='" + operationerId + "' and a.DELFLAG=0 ";
        } else if (operationer.equals("2")) {
            tableName = " T_SYS_XTYHJS a left join T_SYS_XTYH b on a.XTYHID=b.XTYHID  and b.DELFLAG=0    ";
            fieldName = "  b.XTYHID ID ,b.YHM  MC ";
            condi = " a.XTJSID='" + operationerId + "' and a.DELFLAG=0 ";
        } else if (operationer.equals("3")) {
            tableName = " T_SYS_GZZCY a  left join T_SYS_XTYH  b on a.XTYHID=b.XTYHID  and b.DELFLAG=0 ";
            fieldName = "  b.XTYHID ID ,b.YHM  MC ";
            condi = " GZZXXID='" + operationerId + "' and a.DELFLAG=0 ";
        }
        params.clear();
        params.put("SelSQL", "select " + fieldName + " from " + Consts.SYSSCHEMA + "." + tableName + " where " + condi);
        List resList = flowService.selcomList(params);
        int num = resList.size();
        for (int i = 0; i < num; i++) {
            Map resMap = (Map) resList.get(i);
            saveSendMsg(SendID, SendMC, ReMark, resMap.get("ID").toString(), resMap.get("MC").toString(), flowService);
        }

    }


    /**
     * Save send msg.
     * 
     * @param SendID the send id
     * @param SendMC the send mc
     * @param ReMark the re mark
     * @param RECEIVEID the rECEIVEID
     * @param RECEIVER the rECEIVER
     * @param flowService the flow service
     */
    public void saveSendMsg(String SendID, String SendMC, String ReMark, String RECEIVEID, String RECEIVER, FlowService flowService) {

        String Pkey = StringUtil.uuid32len();
        Map <String, String> insparams = new HashMap <String, String>(6);
        insparams.put("MESSAGEID", Pkey);
        insparams.put("SENDID", SendID);
        insparams.put("SENDER", SendMC);
        insparams.put("MSGINFO", ReMark);
        insparams.put("RECEIVEID", RECEIVEID);
        insparams.put("RECEIVER", RECEIVER);
        insert("flow.insMeg", insparams);
    }
    
	/**
	 * 可用于计数.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the int
	 */
	public int queryForInt(String statementName, Object paramMap){
		return super.queryForInt(statementName, paramMap);
	}
	
	/**
	 * 验证是否存在重复信息
	 * @param map
	 * @return
	 */
	public boolean checkInfo(Map<String,String> map){
		int count=Integer.parseInt(String.valueOf(this.load("flow.checkInfo", map)));
		if(count>0){
			return true;
		}
		return false;
	}
	
	/**在否决时返回的信息 
	 * @param primarykey
	 * @return
	 */
	private String getFlowMsg( FlowService flowService,String primarykey){
		String mesgInfo = "您有单据被我否决！";
		try{
			 StringBuffer selSlqBuf = new StringBuffer();
			 selSlqBuf.append(" select m.MODELNAME, f.CORRELATION,f.PRIMARYKEY from")
			 .append("  T_SYS_FLOWINSTANCE f, T_SYS_FLOWMODEL m where f.flowmodelid = m.flowmodelid and")
			 .append( " f.Correlationid = '")
			 .append(primarykey).append("'");
			 HashMap params = new HashMap();
		     params.put("SelSQL", selSlqBuf.toString());
		     List resList = flowService.selcomList(params);
		     int num = resList.size();
		     for (int i = 0; i < num; i++) {
		        Map<String,String> resMap = (Map) resList.get(i);
		        String MODELNAME = resMap.get("MODELNAME");
		        String CORRELATION = resMap.get("CORRELATION");
		        String PRIMARYKEY = resMap.get("PRIMARYKEY");
		        String PRIMARYNO = null;
		        if(!StringUtil.isEmpty(PRIMARYKEY)&&PRIMARYKEY.endsWith("_ID")){
		        	PRIMARYNO = PRIMARYKEY.replace("_ID", "_NO");
		        }
		        StringBuffer selOrderNoBuf = new StringBuffer();
		        selOrderNoBuf.append(" SELECT ").append(PRIMARYNO)
		        .append(" FROM ").append(CORRELATION)
		        .append(" WHERE ").append(PRIMARYKEY)
		        .append(" = '").append(primarykey).append("'");
		        
		        params = new HashMap();
			    params.put("SelSQL", selOrderNoBuf.toString());
			    List orderList = flowService.selcomList(params);
			    int orderNum = orderList.size();
			    Map<String,String> orderMap = (Map) orderList.get(0);
			    String bussNo = orderMap.get(PRIMARYNO);
			    mesgInfo = MODELNAME+",单号:"+bussNo+"被否决！";
			    return mesgInfo;
		     }
		}catch(Exception ex){
			return mesgInfo;
		}
		 return mesgInfo;
	     
	}
}
