<!--
* @prjName:供应链_贵人鸟
* @fileName:Wdxfjh_List
* @author czy
* @time 2013-06-02 14:30:01
* @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/commons/jslibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>浏览</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
    <script type="text/javascript" src="${ctx}/pages/base/grgzjh/Grgzjhmx_List.js"></script>
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
<body>
<div class="lst_area">
<table width="100%">
    <tr>
        <td height="20px" valign="top">
            <table width="100%" cellspacing="0" cellpadding="0" class="wz">
                <tr>
                    <td>&nbsp;当前位置：个人工作计划编辑</td>
                    <td width="50" align="right"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            
            <font style="float: left; background-color: #F5F5F5"> 当前人员：${PLAN_PSON_NAME} </font>    
            <input type="hidden"  value="${RYXXID}" json="RYXXID" id="RYXXID"  label="当前人" name="RYXXID" style="width:155px"  autocheck="true" inputtype="string"  mustinput="true"/>
            <input type="button"  style="float: right;" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();"> 
            <input type="button"  style="float: right;margin-right: 20px;"  class="btn"  value="保存(S)" title="Alt+S" accesskey="S" onclick="saveMainRemark();"/>
        </td>
    </tr>
    <tr>
        <td>
           &nbsp;
            <form name="hiddenForm">
                <input id="selcondition" type="hidden" name="selcondition" value=" SSZZBM IN (SELECT A.BMXXID FROM T_SYS_BMXX A START WITH A.BMXXID = '${DEPT_ID}' CONNECT BY PRIOR A.BMXXID = A.SJBM) "/>
                <input id="SSWDID" name="SSWDID" type="hidden"/>
                <input id="SSWDMC" name="SSWDMC" type="hidden"/>
                <input id="XFJHID" name="XFJHID" value="${XFJHID}" type="hidden"/>
                <input id="currentUser" name="currentUser" value="${currentUser}" type="hidden"/>
                <input id="currentNY" name="currentNY" value="${currentNY}" type="hidden"/>
                <input id="currentNYR" name="currentNYR" value="${currentNYR}" type="hidden"/>
                <input id="IS_IMPT_FLAG" name="IS_IMPT_FLAG" value="" type="hidden"/>
                <input id="plan_year_month" name="currentNY" value="${currentNY}" type="hidden"/>
            </form>
        </td>
    </tr>
    <tr>
        <td align="left">
            <div id='calendar'></div>
        </td>
        <td  valign="top">
            <p style="font-weight: 800;text-align: center;">备注</p>                   
            <textarea rows="8" cols="20" id="REMARK" name="REMARK" json="REMARK" maxlength="250" onkeyup="changeTextAreaLength(this);" >${rst.REMARK}</textarea>
            <%--<p style="font-weight: 800;text-align: center;">
             <input type="button"  class="btn"  value="保存" onclick="saveMainRemark();"/>
            </p>       
        --%></td>
    </tr>
</table>
</div>
</body>
<script type="text/javascript">
   var events = [];
   <c:forEach items="${events}" var="event">
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
