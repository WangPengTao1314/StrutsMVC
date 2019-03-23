<!-- 
  *@module 系统模块 
  *@fuc 系统模块 
  *@version 1.1
  *@author 朱晓文
 -->
 <%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="com.hoperun.commons.util.*" %>
<%@ page import="com.hoperun.commons.model.DataObject" %>
<%@ page import="com.hoperun.commons.select.object.TableObject,com.hoperun.sys.model.UserBean" %>
<%
	String contextPath = request.getContextPath();
    UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
	Vector vec = (Vector)request.getAttribute("result");
	String closeImgsrc = contextPath +"/styles/"+userBean.getUSERSTYLE()+"/images/plus/nodeclose.gif";
	String endImgsrc = contextPath +"/styles/"+userBean.getUSERSTYLE()+"/images/plus/nodeend.gif";
    String oldCndt = StringUtil.tranCodeP(request.getParameter("oldCndt"));
	String getCld = StringUtil.tranCodeP(request.getParameter("getCld"));
	String nodeValue = StringUtil.tranCodeP(request.getParameter("nodeValue"));
	String keyValue = StringUtil.tranCodeP(request.getParameter("keyValue"));
	String selCommId = StringUtil.tranCodeP(request.getParameter("selCommId"));
	String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));
	String specialTable = StringUtil.tranCodeP(request.getParameter("specialTable"));
	String specialAsTable = StringUtil.tranCodeP(request.getParameter("specialAsTable"));
	String keyNames = StringUtil.tranCodeP(request.getParameter("keyNames"));
	String selCommFields = StringUtil.tranCodeP(request.getParameter("selCommFields"));
	String selectType = StringUtil.tranCodeP(request.getParameter("selectType"));
	String isShowSearchLayer = StringUtil.tranCodeP(request.getParameter("isShowSearchLayer"));
	String searchCondition = StringUtil.tranCodeP(request.getParameter("searchCondition"));
	TableObject tableObject = (TableObject)request.getAttribute("TableObject");
	TableObject[] subTableObjects = null;
	if(tableObject!=null){
		subTableObjects = tableObject.getSubTableObjects();
	}
%>
<html>
<head>
<title>[树形列表]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<style type="text/css">
	a:link {text-decoration:none;color:#000000}
	a:visited {text-decoration:none;color:#000000}
	a:hover {text-decoration:underline;color:#cc0000}
</style>
<script language="JavaScript" type="text/javascript" src="${ctx}/scripts/util/prototype.js"></script>
<script language="JavaScript">
	function funClick(nodeValue, keyValue,level, childrenNum){
		var obj = eval("document.all('jd"+keyValue+"')");

		var tmpobj = eval("document.all('img"+keyValue+"')");
		if(obj.style.display=="none"){
			if(obj.open=="true"){
			  obj.style.display="inline";
			  tmpobj.src = "${ctx}/styles/${theme}/images/plus/nodeopen.gif";
			  return;
			}else{
				document.forms[0].nodeValue.value=nodeValue;
				document.forms[0].keyValue.value=keyValue;
				document.forms[0].level.value=level;
				document.forms[0].getCld.value="T";
				document.forms[0].childrenNum.value=childrenNum;
				document.forms[0].target="childFrame";
				document.forms[0].submit();
				obj.style.display="inline";
				var space = "";
				for(var i=0;i<level;i++){
					space+="&nbsp;&nbsp;";
				}
				obj.innerHTML=space+"loading......<br/>";
			}
		}else{
			obj.style.display="none";
			tmpobj.src = "<%=closeImgsrc%>";
		}
		
	}

	function fixParent(){
		top.document.all('waitlayer').style.display="none";
		top.document.all('selComm').style.display="inline";
		var keyNames = top.obj.selCommArgs[4].split(",");
		if(top.keyElemValues[0]==null) return;
		var objArr = new Array();
		if(document.forms[0].checkbox!=null){
			if(document.forms[0].checkbox.length==null) {
				objArr[0]=document.forms[0].checkbox;
			}else{
				objArr = document.forms[0].checkbox;
			}
			for(var i=0;i<objArr.length;i++){
				for(var n=0;n<top.keyElemValues[0].length;n++){
					var matchedFlag = false;
					for(var j=0;j<keyNames.length;j++){
						var continueFlag = false;
						var tmpValue = eval("document.all('"+keyNames[j]+"_"+objArr[i].value+"').value");
						if(top.keyElemValues[j][n]==tmpValue){
						
							continueFlag = true;
						}
						if(!continueFlag)
						 break;
						if(j==keyNames.length-1) {
						
							matchedFlag=true;
						}
					}
					
					if(matchedFlag)	{
						objArr[i].checked=true;
					}
				}
			}
			
		}
		<%if(getCld.equals("T")) {%>
		parent.document.all("img<%=keyValue%>").src = "${ctx}/styles/${theme}/images/plus/nodeopen.gif";
		parent.document.all("jd<%=keyValue%>").open="true";
		parent.document.all("jd<%=keyValue%>").innerHTML=document.all("fixSpan").innerHTML;
		<%}%>
	}
	/* 改变所有子节点状态  */
	function changeAll(obj,state)//state --添加还是删除
	{
		var value= obj.value;
		var id = obj.id;
		var key = id.substring(0,id.indexOf("_"));
		var level = id.substring(id.indexOf("_")+1,id.length);
		var inps = document.getElementsByName("checkbox");//找到所有的checkbox
		for(var i=0,len=inps.length;i<len;i++)
				{
					if(inps[i].id.indexOf(value)!=-1)
					{
						if(inps[i].id.substring(inps[i].id.indexOf("_")+1,inps[i].id.length)>level)//所有下级
						{
							if(state=="add")
							{
								inps[i].checked=true;
								addElement(inps[i].value);
								changeAll(inps[i],"add");
							}else
							{
								inps[i].checked=false;
								delElement(inps[i].value);
								changeAll(inps[i],"");
							}
						}
					}
				}	
		
	}
	function changePNode(obj,state) //state   checked  is true or false
	{
		var value= obj.value;
		var id = obj.id;
		var key = id.substring(0,id.indexOf("_"));
		var level = id.substring(id.indexOf("_")+1,id.length);
		var inps = document.getElementsByName("checkbox");//找到所有的checkbox
		for(var i=0,len=inps.length;i<len;i++)
				{
					if(inps[i].value.indexOf(key)!=-1)
					{
						if(inps[i].id.substring(inps[i].id.indexOf("_")+1,inps[i].id.length)<level)//所有上级
						{
							if(state=="add")
							{
								inps[i].checked=true;
								addElement(inps[i].value);
								changePNode(inps[i],"add");
							}else
							{
								inps[i].checked=false;
								delElement(inps[i].value);
								if(!equalNodes(inps[i]))
								{
									changePNode(inps[i],"");
								}
							}
						}
					}
				}	
		
	}
	function changeNodes(obj)
	{
		var value= obj.value;
		var id = obj.id;
		var key = id.substring(0,id.indexOf("_"));		
		if(value==key)//root
		{
			if(obj.checked)//checked=true
			{
				changeAll(obj,"add");
			}else//checked=false
			{
				changeAll(obj,"");
			}
		}else//not root
		{
			if(obj.checked)//checked=true
			{
			
				changeAll(obj,"add");
				changePNode(obj,"add");
				
			}else//checked=false
			{
				changeAll(obj,"");
				if(equalNodes(obj))//同级至少有一个被选中
				{
					changePNode(obj,"add");
				}else
				{
					changePNode(obj,"");
				}
				
				
				
			}
		
		}
		
	
  }
  function equalNodes(obj)
  {
  		var b =false;
  		var value= obj.value;
		var id = obj.id;
		var key = id.substring(0,id.indexOf("_"));
		var level = id.substring(id.indexOf("_")+1,id.length);
		var inps = document.getElementsByName("checkbox");//找到所有的checkbox
		for(var i=0,len=inps.length;i<len;i++)
				{
					if(inps[i].id==id)
					{
						if(inps[i].checked)
						{
						b=true;
						break;
						}
					}
				}	
				
  	return b;
  	
  }
	
	function eventClick(obj){
	    if(obj.checked){
			addElement(obj.value);
			//changeNodes(obj);
		}else{
			delElement(obj.value);
			changeNodes(obj);
		}
		top.countSelected();
	}
	

	function hasValue(arr,obj)
	{
		var b=false;
		for(var i=0,size=arr.length;i<size;i++)
		{
			if(arr[i]==obj)
			{
				b=true;
				break;
			}
		}
		return b;
	}
	function addElement(id){
		var keyNames = top.obj.selCommArgs[4].split(",");
		for(var i=0;i<keyNames.length;i++){
			if(!hasValue(top.keyElemValues[i],id))
			{
			
				top.keyElemValues[i].push(eval("document.all('"+keyNames[i]+"_"+id+"').value"));
			}
		}
		var selCommFields = top.obj.selCommArgs[7].split(",");
		for(var i=0;i<selCommFields.length;i++){
		if(!hasValue(top.selCommElemValues[i],id))
			{
			top.selCommElemValues[i].push(eval("document.all('"+selCommFields[i]+"_"+id+"').value"));
			}
		}
		<%if(selectType.equals("false")){%>
		var objArr = new Array();
		if(document.forms[0].checkbox.length==null) {
			objArr[0]=document.forms[0].checkbox;
		}else{
			objArr = document.forms[0].checkbox;
		}
		for(var i=0;i<objArr.length;i++){
			if(objArr[i].checked&&objArr[i].value!=id) objArr[i].checked=false;
		}
		for(var i=0;i<top.keyElemValues[0].length-1;i++){
			for(var j=0;j<keyNames.length;j++){
                if(top.keyElemValues[j].length<=1){
                    continue;
                }
				top.keyElemValues[j].shift();
			}
			for(var j=0;j<selCommFields.length;j++){
                if(top.selCommElemValues[j].length<=1){
                    continue;
                }
                top.selCommElemValues[j].shift();
			}
		}
		<%}%>
	}

	function delElement(id){
		var keyNames = top.obj.selCommArgs[4].split(",");
		var selCommFields = top.obj.selCommArgs[7].split(",");
		var j = 0;
		for(j=0;j<top.keyElemValues[0].length;j++){
			var matchedFlag = false;
			for(var n=0;n<keyNames.length;n++){
				var continueFlag = false;
				var tmpValue = eval("document.all('"+keyNames[n]+"_"+id+"').value");
				if(top.keyElemValues[n][j]==tmpValue){
					continueFlag = true;
				}
				if(!continueFlag) break;
				if(n==keyNames.length-1) matchedFlag=true;
			}
			if(matchedFlag) break;
		}
		for(var i=0;i<keyNames.length;i++){
			var tmpArr = new Array();
			var n = 0;
			for(var k=0;k<top.keyElemValues[i].length;k++){
				if(k!=j) {
					tmpArr[n++] = top.keyElemValues[i][k];
				}
			}
			top.keyElemValues[i] = tmpArr;
		}
		for(var i=0;i<selCommFields.length;i++){
			var tmpArr = new Array();
			var n=0;
			for(var k=0;k<top.selCommElemValues[i].length;k++){
				if(k!=j) {
					tmpArr[n++] = top.selCommElemValues[i][k];
				}
			}
			top.selCommElemValues[i] = tmpArr;
		}
	}

	function openSubTab(keyValue){
		for(var i=0;;i++){
			var tmpo = eval("document.forms[0].PK_"+keyValue+"_"+i);
			if(tmpo==null){
				break;
			}else{
				parent.SubFrame.keyValues[i] = tmpo.value;
			}
		}
		parent.SubFrame.reloadCurSubPage();
	}
</script>
</head>
<body style="overflow:hidden;margin:0px 0px 0px 0px;" onLoad="fixParent();if(top.butView!=null){ top.butView.disabled=false;top.butView.value=' 列表(T) ';}">
<form name="treeForm" method="post" action="${ctx}/common/select.shtml?action=doPost">
<div class="mdiv" style="overflow:auto;width:100%;height:<%=(subTableObjects!=null&&subTableObjects.length>0)?"328px":"538px"%>;">
<span id="fixSpan">
<%
if(vec!=null&&vec.size()>0){
	for(int i=0;i<vec.size();i++){
		DataObject dataObject = (DataObject)vec.get(i);
		for(int j=1;j<dataObject.getLevel();j++){
			out.print("&nbsp;&nbsp;");
		}
		if(dataObject.getSelectFlag()&&tableObject.getSelectFlag()){
			if(dataObject.getParentNode()==null||dataObject.getParentNode().equals(""))
			{
			
				dataObject.setDisplayStr("<input  name =\"checkbox\" id=\""+dataObject.getNode().concat("_"+dataObject.getLevel())+"\" type=\"checkbox\" level=\""+dataObject.getLevel()+"\" value=\""+dataObject.getKeyValue()+"\"  onClick=\"eventClick(this)\">" + dataObject.getDisplayStrOld());
				
			}else
			{
				dataObject.setDisplayStr("<input name=\"checkbox\" id=\""+dataObject.getParentNode().concat("_"+dataObject.getLevel())+"\" type=\"checkbox\" level=\""+dataObject.getLevel()+"\" value=\""+dataObject.getKeyValue()+"\" onClick=\"eventClick(this)\">" + dataObject.getDisplayStrOld());
				
			}
		}
		String str = "";
		for(int j=0;j<dataObject.getAllName().length;j++){
			String tmpName = dataObject.getAllName()[j];
			if((","+keyNames+",").indexOf(","+tmpName+",")>=0||(","+selCommFields+",").indexOf(","+tmpName+",")>=0)	{
				str += "<input id='"+tmpName+"_"+dataObject.getKeyValue()+"' type='hidden' value='"+dataObject.getAllValue().get(tmpName)+"'>";
			}
		}

		String subtabstr = "";
		if(subTableObjects!=null&&subTableObjects.length>0){
			Hashtable ht = dataObject.getAllValue();
			for(int j=0;j<tableObject.getFieldAsName().length;j++){
				String tmpName = tableObject.getFieldAsName()[j];
				if(tmpName==null||tmpName.equals("")){
					tmpName = tableObject.getFieldPyName()[j];
				}
				String value = (String)ht.get(tmpName);
				if(value==null)  value="";
				if(tableObject.getPrimaryKeyId()[j] != null && !tableObject.getPrimaryKeyId()[j].equals("")){
					subtabstr += "<textarea Name=\"PK_"+dataObject.getKeyValue()+"_"+tableObject.getPrimaryKeyId()[j]+"\" style=\"display:none\">"+value+"</textarea>";
				}
			}
		}

		if(dataObject.getChildrenNum()==0||dataObject.getIsLeaf()){
			out.print("<img id='img"+dataObject.getKeyValue()+"' style='cursor:default' src='"+endImgsrc+"'>");
			if(subTableObjects!=null&&subTableObjects.length>0){
				out.print("<a href='#implied' onClick=\"openSubTab('"+dataObject.getKeyValue()+"')\">");
				out.print(subtabstr);
			}
			out.print(dataObject.getSelectFlag()?str:"");
			out.print(dataObject.getDisplayStr() + "<br/>");
			if(tableObject.getSubTableObjects()!=null&&tableObject.getSubTableObjects().length>0){
				out.print("</a>");
			}
		}else{
			out.print("<img id='img"+dataObject.getKeyValue()+"' style='cursor:hand' src='"+closeImgsrc+"' onClick=\"funClick('"+dataObject.getNode()+"','"+dataObject.getKeyValue()+"', "+dataObject.getLevel()+", "+dataObject.getChildrenNum()+")\">");
			if(subTableObjects!=null&&subTableObjects.length>0){
				out.print("<a href='#implied' onClick=\"openSubTab('"+dataObject.getKeyValue()+"')\">");
				out.print(subtabstr);
			}
			out.print(dataObject.getSelectFlag()?str:"");
			out.print(dataObject.getDisplayStr() + "<br/>");
			if(subTableObjects!=null&&subTableObjects.length>0){
				out.print("</a>");
			}
			out.print("<span id='jd"+dataObject.getKeyValue()+"' style='display:none' open='false'></span>");
		}
	}
}else{
	out.print("<br/><br/><div align='center'>没有符合条件的数据！</div>");
}
%>
</span>
</div>
<input type="hidden" name="nodeValue" value="">
<input type="hidden" name="keyValue" value="">
<input type="hidden" name="level" value="">
<input type="hidden" name="childrenNum" value="">
<input type="hidden" name="cmdFlag" value="ShowTree">
<input type="hidden" name="getCld" value="">
<input type="hidden" name="oldCndt" value="<%=oldCndt%>">
<input type="hidden" name="tableId" value="<%=tableId%>">
<input type="hidden" name="selCommId" value="<%=selCommId%>">
<input type="hidden" name="specialTable" value="<%=specialTable%>">
<input type="hidden" name="specialAsTable" value="<%=specialAsTable%>">
<input type="hidden" name="keyNames" value="<%=keyNames%>">
<input type="hidden" name="selCommFields" value="<%=selCommFields%>">

<input type="hidden" name="selectType" value="<%=selectType%>">
<input type="hidden" name="isShowSearchLayer" value="<%=isShowSearchLayer%>">
<input type="hidden" name="searchCondition" value="<%=searchCondition%>">

</form>
<iframe src="" id="childFrame" name="childFrame" frameborder="0" style="display:none"></iframe>
</body>
</html>
