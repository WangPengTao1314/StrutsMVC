<!-- 
 *@module 系统管理
 *@func 个人工作计划
 *@version 1.1
 *@author 吴军
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>浏览</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
    <script type="text/javascript" src="${ctx}/pages/base/grgzjh/Grgzjh_List_Chld.js"></script>
    <link href='${ctx}/styles/fullcalendar/fullcalendar.css' rel='stylesheet'/>
    <link href='${ctx}/styles/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print'/>
    <script src='${ctx}/scripts/fullcalendar/jquery.min.js'></script>
    <script src='${ctx}/scripts/fullcalendar/jquery-ui.custom.min.js'></script>
    <script src='${ctx}/scripts/fullcalendar/fullcalendar.js'></script>
    <script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
    <style>

        body {
            /*margin-top: 40px;*/
            text-align: left;
            font-size: 14px;
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
        }

        .event-size{
            font-size: 14px;
        }

        #calendar {
            width: 900px;
            margin: 0 auto;
        }

    </style>
</head>
<body class="bodycss1">

<table width="100%">
    <tr>
        <td>
            &nbsp;
            <form name="hiddenForm">
                <input id="selcondition" type="hidden" name="selcondition" value=" SSZZBM IN (SELECT A.BMXXID FROM T_SYS_BMXX A START WITH A.BMXXID = '${BMXXID}' CONNECT BY PRIOR A.BMXXID = A.SJBM) "/>
                <input id="SSWDID" name="SSWDID" type="hidden"/>
                <input id="SSWDMC" name="SSWDMC" type="hidden"/>
                <input id="XFJHID" name="JHID" value="${JHID}" type="hidden"/>
                <input id="RYXXID" name="RYXXID" value="${RYXXID}" type="hidden"/>
                <input id="currentNY" name="currentNY" value="${currentNY}" type="hidden"/>
                <input id="currentNYR" name="currentNYR" value="${currentNYR}" type="hidden"/>
            </form>
        </td>
    </tr>
    <tr>
        <td align="left">
            <div id='calendar'></div>
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    var events = [];
    <c:forEach items="${rst}" var="event">
    $("#PER_WORK_PLAN_ID").val('${event.PER_WORK_PLAN_ID}');
    var tt = '${event.PLAN_CONTENT}'.replaceAll("</br>","\n");
    var event = {
        id: '${event.PER_WORK_PLAN_DTL_ID}',
        title: tt,
        start: '${event.ESTART}',
        className:'event-size'
    };
    events.push(event);
    </c:forEach>
</script>
</html>