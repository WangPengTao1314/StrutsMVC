<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<HTML xmlns:vml = "urn:schemas-microsoft-com:vml">
<HEAD>
<TITLE>模板流程</TITLE>
<style>
v\:* { behavior: url(#default#VML); }
</style>
<STYLE type=text/css>vml\:* {
	BEHAVIOR: url(#VMLRender)
}

.12pt {
	FONT-SIZE: 12pt
}
.10pt {
	FONT-SIZE: 10.5pt
}
.9pt {
	FONT-SIZE: 9pt
}
A:link {
	COLOR: white; TEXT-DECORATION: none
}
A:visited {
	COLOR: white; TEXT-DECORATION: none
}
A:hover {
	FONT-WEIGHT: bold; COLOR: yellow
}
INPUT {
	FONT-SIZE: 9pt
}
SELECT {
	FONT-SIZE: 9pt
}
A {
	FONT-SIZE: 9pt; CURSOR: default
}
TABLE {
	FONT-SIZE: 9pt; CURSOR: default
}
.normal {
	BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid; BORDER-LEFT: 0px solid; BORDER-BOTTOM: 0px solid
}
.over {
	BORDER-RIGHT: #808080 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #808080 1px solid
}
.out {
	BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid; BORDER-LEFT: 0px solid; BORDER-BOTTOM: 0px solid
}
.down {
	BORDER-RIGHT: #ffffff 1px solid; BORDER-TOP: #808080 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: #808080 1px solid; PADDING-TOP: 2px; BORDER-BOTTOM: #ffffff 1px solid
}
.up {
	BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid; BORDER-LEFT: 0px solid; BORDER-BOTTOM: 0px solid
}
</STYLE>

<META content="MSHTML 6.00.2800.1106" name=GENERATOR></HEAD>
<BODY bgColor=#ccffff leftMargin=0 topMargin=0 >
<OBJECT id=VMLRender 
classid=CLSID:10072CEC-8CC1-11D1-986E-00A0C955B42E></OBJECT>
<SCRIPT language=JavaScript type=text/javascript><!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->
</SCRIPT>

<DIV id=hotdiv 
style="Z-INDEX: -100; LEFT: 0px; WIDTH: 150px; POSITION: absolute; TOP: 0px; HEIGHT: 100%">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width=150 border=0>
  <TBODY>
  <TR>
    <TD id=scrollallhot1 width=100 height="100%">&nbsp;</TD>
    <TD id=outfolderhot1 width=50 
height="100%">&nbsp;</TD></TR></TBODY></TABLE></DIV>
<DIV id=fake 
style="PADDING-RIGHT: 2px; DISPLAY: none; PADDING-LEFT: 2px; FONT-SIZE: 9pt; Z-INDEX: 100; LEFT: 0px; PADDING-BOTTOM: 2px; PADDING-TOP: 2px; POSITION: absolute; TOP: 304px; TEXT-ALIGN: center">
<TABLE style="FILTER: alpha(opacity=50)" cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD background=${ctx}/pages/sys/spflow/image/dot1.gif colSpan=3>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width="100%"></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD width=1 background='${ctx}/pages/sys/spflow/image/dot1.gif'>　</TD>
    <TD>
      <TABLE id=faketable cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD><IMG id=fakepic height=31 src="${ctx}/pages/sys/spflow/image/start.gif" width=32 
            border=0> </TD></TR>
        <TR>
          <TD height=20>
            <DIV id=fakename align=center>部门</DIV></TD></TR></TBODY></TABLE></TD>
    <TD width=1 background=image/dot1.gif>　</TD></TR>
  <TR>
    <TD background='${ctx}/pages/sys/spflow/image/dot1.gif' colSpan=3>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width="100%"></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></DIV>
<DIV id=l1 
style="Z-INDEX: 2; LEFT: 10px; VISIBILITY: hidden; WIDTH: 100px; POSITION: absolute; TOP: 100px; HEIGHT: 100px">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD width=1 height="100%"></TD>
    <TD width="100%" height="100%"></TD>
    <TD width=1 bgColor=#0033cc height="100%">
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width=1 bgColor=#0033cc 
      border=0>
        <TBODY>
        <TR>
          <TD></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD width=1 bgColor=#0033cc height=1></TD>
    <TD width="100%" bgColor=#0033cc height=1></TD>
    <TD width=1 bgColor=#0033cc height=1></TD></TR>
  <TR>
    <TD width=1 bgColor=#0033cc height=30>
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width=1 bgColor=#0033cc 
      border=0>
        <TBODY>
        <TR>
          <TD></TD></TR></TBODY></TABLE></TD>
    <TD width="100%" height=30></TD>
    <TD width=1 height=30></TD></TR></TBODY></TABLE>
</DIV>
<DIV id=l2 
style="Z-INDEX: 2; LEFT: 10px; VISIBILITY: hidden; WIDTH: 100px; POSITION: absolute; TOP: 246px; HEIGHT: 100px">
<TABLE id=linez style="LEFT: 0px; TOP: 1px" height="100%" cellSpacing=0 
cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD width=1 height="100%">
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width=1 bgColor=#0033cc 
      border=0>
        <TBODY>
        <TR>
          <TD></TD></TR></TBODY></TABLE></TD>
    <TD width="100%" height="100%"></TD>
    <TD width=1 height="100%"></TD></TR>
  <TR>
    <TD width=1 bgColor=#0033cc height=1></TD>
    <TD width="100%" bgColor=#0033cc height=1></TD>
    <TD width=1 bgColor=#0033cc height=1></TD></TR>
  <TR>
    <TD width=1 height=30></TD>
    <TD width="100%" height=30></TD>
    <TD width=1 height=30>
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width=1 bgColor=#0033cc 
      border=0>
        <TBODY>
        <TR>
          <TD></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
</DIV>
<DIV id=l3 
style="BORDER-RIGHT: #000000 1px; BORDER-TOP: #000000 1px; Z-INDEX: 2; LEFT: 26px; VISIBILITY: hidden; BORDER-LEFT: #000000 1px; WIDTH: 1px; BORDER-BOTTOM: #000000 1px; POSITION: absolute; TOP: 145px; HEIGHT: 100px; BACKGROUND-COLOR: #0033cc; layer-background-color: #0033CC">
</DIV>

<DIV id=list 
style="LEFT: 244px; VISIBILITY: hidden; POSITION: absolute; TOP: 16px">
<TABLE onmouseover="this.style.backgroundImage ='url(${ctx}/pages/sys/spflow/image/dot1.gif)'" 
onmouseout="this.style.backgroundImage =''" cellSpacing=1 cellPadding=0 
background="" border=0>
  <TBODY>
  <TR>
    <TD bgColor=#ccffff>
      <TABLE cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD oncontextmenu="showMenu();return false" onmousedown=moveNode() 
          style="CURSOR: hand">
            <P align=center><IMG id=listpic height=31 src="${ctx}/pages/sys/spflow/image/start.gif" 
            width=32 border=0></P></TD></TR>
        <TR>
          <TD height=20>
            <DIV id=listname 
  align=center>a开始</DIV></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
</DIV>

<IMG 
id=acute 
style="Z-INDEX: 50; LEFT: 601px; VISIBILITY: hidden; WIDTH: 5px; POSITION: absolute; TOP: 270px; HEIGHT: 5px" 
src="${ctx}/pages/sys/spflow/image/acute.gif"> 
<div id=folder 
style="BORDER-RIGHT: #000000 1px;  Z-INDEX: 200; BORDER-TOP: #000000 1px; 
LEFT: 0px; BORDER-LEFT: #000000 1px; WIDTH: 100px; BORDER-BOTTOM: #000000 1px; 
POSITION: absolute; TOP: 0px; HEIGHT: 100%; BACKGROUND-COLOR: #ffffdc; layer-background-color: #FFFFDC;${folderStyle}"> 
  <table height="100%" cellspacing=0 cellpadding=0 width="100%" border=0>
    <tbody>
      <tr> 
        <td valign=top width=96 height="100%"> <table cellspacing=0 cellpadding=0 width="100%" border=0>
            <tbody>
              <tr> 
                <td 
          style="BORDER-RIGHT: #808080 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #808080 1px solid; HEIGHT: 22px" 
          width=96 bgcolor=#d4d0c8> <div align=right> 
                    <table class=down id=menustate title=开启菜单条自动隐藏 
            style="MARGIN-RIGHT: 10px" height=18 cellspacing=0 cellpadding=0 
            width=18 bgcolor=#f2f1ee border=0>
                      <tbody>
                        <tr> 
                          <td align=middle width="100%"><img id=pushpin height=16 
                  src="${ctx}/pages/sys/spflow/image/pushpin_h.gif" width=16 
            border=0></td>
                        </tr>
                      </tbody>
                    </table>
                  </div></td>
              </tr>
              <tr> 
                <td 
          style="BORDER-RIGHT: #808080 1px solid; BORDER-LEFT: #ffffff 1px solid" 
          valign=top width=96 height="100%"> <div align=center> 
                    <table style="MARGIN-TOP: 2px; MARGIN-BOTTOM: 2px" cellspacing=0 
            cellpadding=0 border=0>
                      <tbody>
                        <tr> 
                          <td id=depselect style="CURSOR: hand" height=20> <p align=center><img height=32 src="${ctx}/pages/sys/spflow/image/depart.gif" 
                  width=32 border=0></p></td>
                        </tr>
                        <tr> 
                          <td height=20> <p align=center>部门</p></td>
                        </tr>
                        <tr> 
                          <td id=roleselect style="CURSOR: hand" height=20> <p align=center><img height=32 src="${ctx}/pages/sys/spflow/image/role.gif" 
                  width=32 border=0></p></td>
                        </tr>
                        <tr> 
                          <td height=20> <p 
                  style="MARGIN-TOP: 2px; MARGIN-LEFT: 5px; MARGIN-RIGHT: 10px">角色</p></td>
                        </tr>
                        <tr> 
                          <td id=personselect style="CURSOR: hand" height=20> 
                            <p align=center><img height=32 src="${ctx}/pages/sys/spflow/image/user.gif" 
                  width=32 border=0></p></td>
                        </tr>
                        <tr> 
                          <td height=20> <p 
                  style="MARGIN-TOP: 2px; MARGIN-LEFT: 5px; MARGIN-RIGHT: 10px">人员</p></td>
                        </tr>
                        <tr> 
                          <td id=groupselect style="CURSOR: hand" height=20> <p align=center><img height=32 src="${ctx}/pages/sys/spflow/image/group.gif" 
                  width=32 border=0></p></td>
                        </tr>
                        <tr> 
                          <td height=20> <p 
                  style="MARGIN-TOP: 2px; MARGIN-LEFT: 5px; MARGIN-RIGHT: 10px" 
                  align=center>组</p></td>
                        </tr>
                      </tbody>
                    </table>
                  </div></td>
              </tr>
            </tbody>
          </table>
          <table height="100%" cellspacing=0 cellpadding=0 width="100%" border=0>
            <tbody>
              <tr> 
                <td 
          style="BORDER-RIGHT: #808080 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #808080 1px solid" 
          width="100%" height="100%">&nbsp;</td>
              </tr>
            </tbody>
          </table></td>
        <td id=scrollallhot valign=top width=4 height="100%"> <table height="100%" cellspacing=0 cellpadding=0 width="100%" 
      bgcolor=#d4d0c8 border=0>
            <tbody>
              <tr> 
                <td 
          style="BORDER-RIGHT: #808080 1px solid; BORDER-LEFT: #ffffff 1px solid" 
          width=4 
height="100%">　</td>
              </tr>
            </tbody>
          </table></td>
      </tr>
    </tbody>
  </table>
</div>
<DIV id=ResW 
style="Z-INDEX: 101; LEFT: 598px; WIDTH: 16px; POSITION: absolute; TOP: 230px; HEIGHT: 13px"></DIV>
<DIV id=output 
style="Z-INDEX: 102; LEFT: 580px; VISIBILITY: hidden; WIDTH: 176px; POSITION: absolute; TOP: 0px; HEIGHT: 96px"></DIV>
<DIV id=set1 
style="DISPLAY: none; Z-INDEX: 1; LEFT: 298px; WIDTH: 106px; POSITION: absolute; TOP: 171px; HEIGHT: 86px">
<TABLE id=popMenu onmouseover="set1.style.display=''" 
style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #d4d0c8 1px solid; BORDER-LEFT: #d4d0c8 1px solid; BORDER-BOTTOM: #000000 1px solid" 
onmouseout="set1.style.display='none'" cellSpacing=0 cellPadding=0 
bgColor=#d4d0c8 border=0>
  <TBODY>
  <TR>
    <TD 
    style="BORDER-RIGHT: #7e7f7e 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #7e7f7e 1px solid" 
    width="100%">
      <TABLE style="MARGIN: 1px" cellSpacing=0 cellPadding=0 width=100 
        border=0><TBODY>
        <TR>
          <TD noWrap height=20>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR onmouseover=cl(this) onclick=Node_Edit(); onmouseout=nr(this)>
                <TD width=25 height=20>
                  <P style="MARGIN-LEFT: 5px"><FONT color=#ffffff><IMG height=16 src="${ctx}/pages/sys/spflow/image/statistic_h.gif" width=16 border=0></FONT></P>
                </TD>
                <TD height=20 >属性</TD>
              </TR>
              </TBODY>
            </TABLE>
          </TD>
        </TR>
        <c:if test="${readOnly eq '0'}">
         <TR>
          <TD noWrap>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD style="BORDER-BOTTOM: #808080 1px solid" width="100%">
                  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width="100%"></TD>
                    </TR>
                    </TBODY>
                  </TABLE>
                </TD>
              </TR>
              <TR>
                <TD style="BORDER-TOP: #ffffff 1px solid" width="100%">
                  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width="100%"></TD>
                    </TR>
                    </TBODY>
                  </TABLE>
                </TD>
              </TR>
              </TBODY>
            </TABLE>
          </TD>
        </TR>
        <TR>
          <TD noWrap height=20>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR onmouseover=cl(this) onclick=Node_Del(); onmouseout=nr(this)>
              	<TD width=25 height=20>
                  <P style="MARGIN-LEFT: 5px"><FONT color=#ffffff><IMG height=16 src="${ctx}/pages/sys/spflow/image/dele_h.gif" width=16 border=0></FONT></P>
                </TD>
                <TD height=20>删除</TD>
              </TR>
              </TBODY>
            </TABLE>
          </TD>
        </TR>
        </c:if>
      	</TBODY>
     	</TABLE>
    </TD>
  </TR>
  </TBODY>
</TABLE>
</DIV>
<DIV id=Layer1 
style="Z-INDEX: 103; LEFT: 281px; VISIBILITY: hidden; WIDTH: 150px; POSITION: absolute; TOP: 5px; HEIGHT: 35px"><INPUT onclick=refresh() type=button value=调整 name=Button> 
<INPUT onclick=delFlow() type=button value=删除 name=Button></DIV>
<FORM name="submitform" action="${ctx}/system/flow.shtml?action=toTOPList" method="post">
<INPUT type=hidden name=OperType> 
<INPUT type=hidden name=OperID> 
<INPUT type=hidden name=BackFlag> 
<INPUT type=hidden name=JumpFlag> 
<INPUT type=hidden name=ExpandFlag> 
<INPUT type=hidden name=EditFlag> 
<INPUT type=hidden name=CancleFlag>
<input type=hidden name=AgentFlag>
<INPUT type=hidden name=DelayFlag> 
<INPUT type=hidden name=DelayDay> 
<INPUT type=hidden name=AllFlag> 
<INPUT type=hidden name=event> 
<INPUT type=hidden name=IX> 
<INPUT type=hidden name=IY> 
<INPUT type=hidden name=ProNodeID> 
<INPUT type=hidden name=ProcessName> 
<input type=hidden name=nodeOperationId>
<INPUT id=Processid type=hidden value='${flowModelId}' name=ProcessID> 
<INPUT id=Processclass type=hidden value=2 name=ProcessClass>
</FORM>
<SPAN style="LEFT: 2000px; POSITION: absolute; TOP: 3000px">&nbsp;</SPAN>
<SCRIPT language=javascript id=ProcessDraw type=text/javascript>

	var id = new Array();
	var name = new Array();
	var type = new Array(); 
	var iX= new Array(); 
	var iY= new Array();
	var path = new Array();
	var operaName = new Array();
	
	
	<c:forEach items="${retList}" var="rst1" varStatus="row1">
	<c:set var="rr1" value="${row1.count -1}"></c:set>
	    id[${rr1}]='${rst1.id}';
		type[${rr1}]='${rst1.type}';
		name[${rr1}]='${rst1.name}';
		
		iX[${rr1}]='${rst1.iX}';
		iY[${rr1}]='${rst1.iY}';
		operaName[${rr1}]='${rst1.operaName}';
		
	</c:forEach>
		
	<c:forEach items="${retList1}" var="rst2" varStatus="row2">
	<c:set var="r1" value="${row2.count -1}"></c:set>
	path[${r1}]='${rst2.path}';
	</c:forEach>
	
	
</SCRIPT>

<SCRIPT language=JAVASCRIPT src="${ctx}/pages/sys/spflow/image/flow.js" 
type=text/javascript></SCRIPT>

<SCRIPT language=javascript type=text/javascript></SCRIPT>
<v:line style="position:absulate" from="328pt,63pt" to="328pt,90.5pt" strokecolor="red" strokeweight="2pt"/><br>
 </BODY></HTML>
