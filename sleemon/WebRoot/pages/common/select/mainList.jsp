<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ page import="com.hoperun.commons.util.*,com.hoperun.commons.model.*, java.util.*, com.hoperun.commons.select.object.*" %>
<%
	TableObject tableObject = (TableObject)request.getAttribute("TableObject");
	TableObject[] subTableObjects = tableObject.getSubTableObjects();
	Vector vec = tableObject.getValues();
	Exception e = (Exception)request.getAttribute("ErrMsg");
	PageInfoVO aPageInfoVO = (PageInfoVO)vec.get(vec.size()-1);
	String orderName = aPageInfoVO.orderName;
	String orderType = aPageInfoVO.orderType;
	int pagenum = aPageInfoVO.curPage;
	int maxRows = aPageInfoVO.maxRowCount;
	int maxPage = aPageInfoVO.maxPage;
	String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));

	Vector vecSearchChar = new Vector();
	Vector vecSearchDate = new Vector();
	Vector vecSearchNumber = new Vector();
	for(int i=0;i<(tableObject.getFieldPyName()).length;i++){
		if((tableObject.getIsSearch())[i]){
	if((tableObject.getFieldType())[i].equals("char")){
		vecSearchChar.add(String.valueOf(i));
	}
	if((tableObject.getFieldType())[i].equals("number")){
		vecSearchNumber.add(String.valueOf(i));
	}
	if((tableObject.getFieldType())[i].equals("date")||(tableObject.getFieldType())[i].equals("timestamp")){
		vecSearchDate.add(String.valueOf(i));
	}
		}
  }
	String selCommId = StringUtil.tranCodeP(request.getParameter("selCommId"));
	String selectType = StringUtil.tranCodeP(request.getParameter("selectType"));
	String keyNames = StringUtil.tranCodeP(request.getParameter("keyNames"));
	String selCommFields = StringUtil.tranCodeP(request.getParameter("selCommFields"));
	String oldCndt = StringUtil.tranCodeP(request.getParameter("oldCndt"));
	String specialTable = StringUtil.tranCodeP(request.getParameter("specialTable"));
	String specialAsTable = StringUtil.tranCodeP(request.getParameter("specialAsTable"));
	String isShowSearchLayer = StringUtil.tranCodeP(request.getParameter("isShowSearchLayer"));

	String searchCondition = (String)request.getAttribute("searchCondition");
	if(searchCondition==null) searchCondition = "";
	
	
%>
<HTML>
<HEAD>
<TITLE>通用选取主表列表</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 5.50.4923.2500" name=GENERATOR>
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<%if(vecSearchDate.size()>0){%>
<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
<%}%>
<script language="JavaScript">
   //动态排序
   function orderBy(orderName) {
	 if(document.forms[0].orderName.value==orderName&&document.forms[0].orderType.value=="ASC"){
		   document.forms[0].orderType.value="DESC";
     }else{
		   document.forms[0].orderType.value="ASC";
	   }
     document.forms[0].orderName.value=orderName;
     document.forms[0].submit();
		 top.mins = 0;
		 top.secs = 0;
	   top.document.all('waitlayer').style.display="inline";
		 top.document.all('selComm').style.display="none";
   }
   //跳页
	function goPage(pagenum){
		try{
			pagenum = "" + pagenum;
   		var num = parseInt(pagenum, 10);
		if (num == NaN||num != new Number(pagenum)) {
		  alert("页码超出允许的范围！");
		  return;
		}
		if (num>=1&&num <=<%=maxPage%>){
			 document.forms[0].pagenum.value = num;
			 document.forms[0].submit();
			 top.mins = 0;
			 top.secs = 0;
		   top.document.all('waitlayer').style.display="inline";
			 top.document.all('selComm').style.display="none";
		}else{
	      alert("页码超出允许的范围");
	      return;
		}
		}catch(e){
			alert(e.description);
		}
	}
	//查询
	function doSearch(){
		<%
			if(vecSearchNumber!=null&&vecSearchNumber.size()>0){
			for(int i=0;i<vecSearchNumber.size();i++){
				int j = Integer.parseInt((String)vecSearchNumber.get(i));
				String tmpName = tableObject.getFieldAsName()[j];
				if(tmpName==null||tmpName.equals("")){
					tmpName = tableObject.getFieldPyName()[j];
				}
				String lgName = tableObject.getFieldLgName()[j];
		%>
		if(!CheckIsNumberAndDot("forms[0]","from<%=tmpName%>","请输入合法的<%=lgName%>下限！")){
			return;
		}
		if(!CheckIsNumberAndDot("forms[0]","to<%=tmpName%>","请输入合法的<%=lgName%>上限！")){
			return;
		}
		<%}}%>
		//top.keyElemValues[0].push("");
		document.forms[0].isSearch.value="T";
		document.forms[0].submit();
		top.mins = 0;
		top.secs = 0;
	    top.document.all('waitlayer').style.display="inline";
		top.document.all('selComm').style.display="none";
	}

	function msover(obj){
		if(keyEventObject!=null){
			msout(keyEventObject);
		}
		obj.className="selected";
		keyEventObject = obj;
	}

	function msout(obj){
		if(currentSubObj==obj){
			obj.className="clicked";
			return;
		}
		obj.className="notSelect";
	}

	function init(){
	    if(top.butView!=null){
			top.butView.disabled=false;
			top.butView.value=' 树型(T) ';
		}
		top.document.all('waitlayer').style.display="none";
		top.document.all('selComm').style.display="inline";
		if(targetTable!=null) {
			targetTable.focus();
		}
		setInterval('heartBeat()',1);
		<%if(tableObject.getSelectFlag()){%>
		var keyNames = top.obj.selCommArgs[4].split(",");
		if(top.keyElemValues[0]==null) return;
		for(var i=0;i<<%=vec.size()-1%>;i++){
			for(var n=0;n<top.keyElemValues[0].length;n++){
			 var matchedFlag = false;
				for(var j=0;j<keyNames.length;j++){
					var continueFlag = false;
					var tmpValue = eval("document.forms[0]."+keyNames[j]+"_"+i+".value");
					if(top.keyElemValues[j][n]==tmpValue){
						continueFlag = true;
					}
					if(!continueFlag) break;
					if(j==keyNames.length-1) matchedFlag=true;
				}
				if(matchedFlag)	{
					eval("document.all('checkbox"+i+"')").checked=true;
					<%if(subTableObjects!=null&&subTableObjects.length>0){%>
					for(var j=0;;j++){
						var tmpo = eval("document.forms[0].PK_"+i+"_"+j);
						if(tmpo==null){
							break;
						}else{
							parent.SubFrame.keyValues[j] = tmpo.value;
						}
					}
					parent.SubFrame.reloadCurSubPage();
					<%}%>
				}
			}
		}
		<%}%>
		<%if((tableObject.getIsShowSearchLayer()&&searchCondition.equals(""))||(!tableObject.getIsShowSearchLayer()&&!searchCondition.equals("")&&maxRows==0)){%>
			try{
				var tObj = document.all("searchLayer").children[0].children[1].children[0].children;
				for(var i=0;i<tObj.length;i++){
					if(tObj[i].children[1].children[0].type.toUpperCase()=="TEXT"&&tObj[i].children[1].children[0].readOnly==false){
						tObj[i].children[1].children[0].focus();
						return;
					}else if(tObj[i].children[1].children[1]!=null&&tObj[i].children[1].children[1].type=="TEXT"){
						tObj[i].children[1].children[1].focus();
						return;
					}
				}
			}catch(e){
				//
			}
		<%}%>
	}
  function funDblClick(id){
		<%if(tableObject.getSelectFlag()){%>
		var oObj = eval("document.all('checkbox"+id+"')");
		if(oObj.checked){
			top.copyToParent();
		}else{
			addElement(id);
			top.copyToParent();
		}
		<%}%>
	}
   var currentSubObj = null;
	function checkBoxClick(id){
	  var oObj = eval("document.all('checkbox"+id+"')");
		if(oObj.checked){
		   oObj.checked=false;
		}else{
		   oObj.checked=true;
		}
	  
	}
	function trClick(id){
	  <%if(tableObject.getSelectFlag()){%>
		var oObj = eval("document.all('checkbox"+id+"')");
		if(oObj.checked){
		   oObj.checked=false;
		}else{
		   oObj.checked=true;
		}
		<%}%>
		funClick(id);
	}
	
	function funClick(id){
		<%if(tableObject.getSelectFlag()){%>
		var oObj = eval("document.all('checkbox"+id+"')");
		if(oObj.checked){
		   addElement(id);
		}else{
		   delElement(id);
		}
		top.countSelected();
		<%}%>
		if(currentSubObj!=null){
			currentSubObj.className="notSelect";
		}
		eval("document.all('tr_"+id+"').className='clicked'");
		currentSubObj = eval("document.all('tr_"+id+"')");
		<%if(subTableObjects!=null&&subTableObjects.length>0){%>
		for(var i=0;;i++){
			var tmpo = eval("document.forms[0].PK_"+id+"_"+i);
			if(tmpo==null){
				break;
			}else{
				parent.SubFrame.keyValues[i] = tmpo.value;
			}
		}
		parent.SubFrame.reloadCurSubPage();
		<%}%>
		
	}
	function addElement(id){
		var keyNames = top.obj.selCommArgs[4].split(",");
		for(var i=0;i<keyNames.length;i++){
	     if(top.keyElemValues[i].length==0){
            top.keyElemValues[i]=new Array();
           }
           if(!(top.keyElemValues[i] instanceof Array))
           {
             $("#chgArrToChar").val(top.keyElemValues[i]);
             var tmp=$("#chgArrToChar").val();
             top.keyElemValues[i]=tmp.split(",");
           }
          top.keyElemValues[i].push(eval("document.forms[0]."+keyNames[i]+"_"+id+".value"));
        }
		var selCommFields = top.obj.selCommArgs[7].split(",");
		for(var i=0;i<selCommFields.length;i++){
		    if(top.selCommElemValues[i].length==0){
               top.selCommElemValues[i]=new Array();
            }
           if(!(top.selCommElemValues[i] instanceof Array))
           {
             $("#chgArrToChar").val(top.selCommElemValues[i]);
             var tmp=$("#chgArrToChar").val();
             top.selCommElemValues[i]=tmp.split(",");
           }
           top.selCommElemValues[i].push(eval("document.forms[0]."+selCommFields[i]+"_"+id+".value"));
		}
		<%if(selectType.equals("false")){%>
		for(var i=0;i<<%=vec.size()-1%>;i++){
			if(i!=id) eval("document.all('checkbox"+i+"').checked=false");
		}
		
		for(var n=0;n<top.keyElemValues.length;n++){
		 //alert(top.keyElemValues[n].length);
			for(var i=0;i<top.keyElemValues[n].length-1;i++){
			    //alert(2);
				top.keyElemValues[n].shift();
				//alert(3);
			}
		}
		
		for(var n=0;n<top.selCommElemValues.length;n++){
			for(var i=0;i<top.selCommElemValues[n].length-1;i++){
				top.selCommElemValues[n].shift();
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
				var tmpValue = eval("document.forms[0]."+keyNames[n]+"_"+id+".value");
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
	function clickAll(){
		var tmpObject = targetTable.children[0].children;
		for(var i=0;i<tmpObject.length;i++)	{
			tmpObject[i].click();
		}
	}
	//键盘代码：
	/*
	38：上
	40：下
	32：空格
	13：回车
	33：pageup
	34：pagedown
	35：end
	36：home
	*/
	var keyEventObject = null;//当前对象
	function keyClickEvent(){
		var tmpObject = targetTable.children[0].children;
		if(event.keyCode==32){//点击空格时选中当前记录
			if(keyEventObject!=null){
				eval("document.all('checkbox"+parseInt(keyEventObject.id.substring(keyEventObject.id.lastIndexOf("_")+1))+"').click()");
				return;
			}
		}
		if(event.keyCode==13){//点击回车选中当前记录并返回
			if(keyEventObject!=null){
				funDblClick(parseInt(keyEventObject.id.substring(keyEventObject.id.lastIndexOf("_")+1)));
				return;
			}
		}
		if(event.keyCode==38){//向上移动一条记录
			if(keyEventObject==null){
				msover(tmpObject[tmpObject.length-1]);
				return;
			}
			for(var i=1;i<tmpObject.length;i++){
				if(keyEventObject==tmpObject[i]){
					if(i==1){
						msover(tmpObject[tmpObject.length-1]);
					}else{
						msover(tmpObject[i-1]);
					}
					return;
				}
			}
		}
		if(event.keyCode==40){//向下移动一条记录
			if(keyEventObject==null){
				msover(tmpObject[1]);
				return;
			}
			for(var i=1;i<tmpObject.length;i++){
				if(keyEventObject==tmpObject[i]){
					if(i==tmpObject.length-1){
						msover(tmpObject[1]);
					}else{
						msover(tmpObject[i+1]);
					}
					return;
				}
			}
		}
		keyGoPage();
	}

	function keyGoPage(){
		<%if(maxPage>1){%>
		if(event.keyCode==33){//向前翻页
			try{
				if(pageup.style.visibility!="hidden")
					pageup.click();
			}catch(e){
				return;
			}
		}
		if(event.keyCode==34){//向后翻页
			try{
				if(pagedown.style.visibility!="hidden")
					pagedown.click();
			}catch(e){
				return;
			}
		}
		if(event.keyCode==35){//翻到最后页
			goPage('<%=maxPage%>');
		}
		if(event.keyCode==36){//翻到第一页
			goPage('1');
		}
		<%}%>
	}

	var lastScrollX = 0;
	var lastScrollY = 0;
	function heartBeat() {
		if(document.all("searchLayer")==null) return;
		var percent;
		//var diffY = document.body.scrollTop;
		//if(diffY != lastScrollY) {
    //    percent = .1 * (diffY - lastScrollY);
    //    if(percent > 0) {
    //    	percent = Math.ceil(percent);
    //    } else {
    //    	percent = Math.floor(percent);
		//		}
		//		document.all("searchLayer").style.pixelTop += percent;
    //    lastScrollY = lastScrollY + percent;
	  //}
		var diffX = document.body.scrollLeft;
		if(diffX != lastScrollX) {
			percent = .1 * (diffX - lastScrollX);
			if(percent > 0){
				percent = Math.ceil(percent);
			}	else {
				percent = Math.floor(percent);
			}
			document.all("searchLayer").style.pixelLeft += percent;
			lastScrollX = lastScrollX + percent;
		}
	}

	function MM_timelineStop(tmLnName) { //v1.2
	  if (tmLnName == null)  //stop all
	    for (var i=0; i<document.MM_Time.length; i++) document.MM_Time[i].ID = null;
	  else document.MM_Time[tmLnName].ID = null; //stop one
	}

	function MM_timelineGoto(tmLnName, fNew, numGotos) { //v2.0
	  var i,j,tmLn,props,keyFrm,sprite,numKeyFr,firstKeyFr,lastKeyFr,propNum,theObj;
	  if (document.MM_Time == null) MM_initTimelines(); //if *very* 1st time
	  tmLn = document.MM_Time[tmLnName];
	  if (numGotos != null)
	    if (tmLn.gotoCount == null) tmLn.gotoCount = 1;
	    else if (tmLn.gotoCount++ >= numGotos) {tmLn.gotoCount=0; return}
	  jmpFwd = (fNew > tmLn.curFrame);
	  for (i = 0; i < tmLn.length; i++) {
	    sprite = (jmpFwd)? tmLn[i] : tmLn[(tmLn.length-1)-i]; //count bkwds if jumping back
	    if (sprite.charAt(0) == "s") {
	      numKeyFr = sprite.keyFrames.length;
	      firstKeyFr = sprite.keyFrames[0];
	      lastKeyFr = sprite.keyFrames[numKeyFr - 1];
	      if ((jmpFwd && fNew<firstKeyFr) || (!jmpFwd && lastKeyFr<fNew)) continue; //skip if untouchd
	      for (keyFrm=1; keyFrm<numKeyFr && fNew>=sprite.keyFrames[keyFrm]; keyFrm++);
	      for (j=0; j<sprite.values.length; j++) {
	        props = sprite.values[j];
	        if (numKeyFr == props.length) propNum = keyFrm-1 //keyframes only
	        else propNum = Math.min(Math.max(0,fNew-firstKeyFr),props.length-1); //or keep in legal range
	        if (sprite.obj != null) {
	          if (props.prop2 == null) sprite.obj[props.prop] = props[propNum];
	          else        sprite.obj[props.prop2][props.prop] = props[propNum];
	      } }
	    } else if (sprite.charAt(0)=='b' && fNew == sprite.frame) eval(sprite.value);
	  }
	  tmLn.curFrame = fNew;
	  if (tmLn.ID == 0) eval('MM_timelinePlay(tmLnName)');
	}

	function MM_timelinePlay(tmLnName, myID) { //v1.2
	  if(document.all("searchLayer")==null) return;
	  var i,j,tmLn,props,keyFrm,sprite,numKeyFr,firstKeyFr,propNum,theObj,firstTime=false;
	  if (document.MM_Time == null) MM_initTimelines(); //if *very* 1st time
	  tmLn = document.MM_Time[tmLnName];
	  if (myID == null) { myID = ++tmLn.ID; firstTime=true;}//if new call, incr ID
	  if (myID == tmLn.ID) { //if Im newest
	    setTimeout('MM_timelinePlay("'+tmLnName+'",'+myID+')',tmLn.delay);
	    fNew = ++tmLn.curFrame;
	    for (i=0; i<tmLn.length; i++) {
	      sprite = tmLn[i];
	      if (sprite.charAt(0) == 's') {
	        if (sprite.obj) {
	          numKeyFr = sprite.keyFrames.length; firstKeyFr = sprite.keyFrames[0];
	          if (fNew >= firstKeyFr && fNew <= sprite.keyFrames[numKeyFr-1]) {//in range
	            keyFrm=1;
	            for (j=0; j<sprite.values.length; j++) {
	              props = sprite.values[j];
	              if (numKeyFr != props.length) {
	                if (props.prop2 == null) sprite.obj[props.prop] = props[fNew-firstKeyFr];
	                else        sprite.obj[props.prop2][props.prop] = props[fNew-firstKeyFr];
	              } else {
	                while (keyFrm<numKeyFr && fNew>=sprite.keyFrames[keyFrm]) keyFrm++;
	                if (firstTime || fNew==sprite.keyFrames[keyFrm-1]) {
	                  if (props.prop2 == null) sprite.obj[props.prop] = props[keyFrm-1];
	                  else        sprite.obj[props.prop2][props.prop] = props[keyFrm-1];
	        } } } } }
	      } else if (sprite.charAt(0)=='b' && fNew == sprite.frame) eval(sprite.value);
	      if (fNew > tmLn.lastFrame) tmLn.ID = 0;
	  } }
	 // $("#selComm").BackColor=RGB(0,0,255)
	}

	function MM_initTimelines() { //v4.0
	    document.MM_Time = new Array(1);
	    document.MM_Time[0] = new Array(6);
	    document.MM_Time["Timeline1"] = document.MM_Time[0];
	    document.MM_Time[0].MM_Name = "Timeline1";
	    document.MM_Time[0].fps = 500;
	    document.MM_Time[0][0] = new String("sprite");
	    document.MM_Time[0][0].slot = 1;
	    document.MM_Time[0][0].obj = document.all("searchLayer");
	    document.MM_Time[0][0].keyFrames = new Array(1, 25, 50);
	    document.MM_Time[0][0].values = new Array(1);

		<%
			int divHeight = 165;
			int[] arrTop = new int[25];
			for(int i = 0;i<arrTop.length;i++){
				arrTop[i] = - (arrTop.length-i)*divHeight/arrTop.length;
			}
		%>
			<%
			if((tableObject.getIsShowSearchLayer()&&searchCondition.equals(""))||(!tableObject.getIsShowSearchLayer()&&!searchCondition.equals("")&&maxRows==0)){
	    	int arri = 25;
	    %>
	    document.MM_Time[0][0].values[0] = new Array(<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[++arri]%>,<%=arrTop[arri]%>);
	    <%}else{
			int arri = 0;
			%>
	    document.MM_Time[0][0].values[0] = new Array( <%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[arri++]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>,<%=arrTop[--arri]%>);
	    <%}%>
	    document.MM_Time[0][0].values[0].prop = "top";
	    document.MM_Time[0][0].values[0].prop2 = "style";

	    document.MM_Time[0][1] = new String("behavior");
	    document.MM_Time[0][1].frame = 1;
	    document.MM_Time[0][1].value = "hiddenCal()";

	    document.MM_Time[0][2] = new String("behavior");
	    document.MM_Time[0][2].frame = 25;
	    document.MM_Time[0][2].value = "MM_timelineStop()";

	    document.MM_Time[0][3] = new String("behavior");
	    document.MM_Time[0][3].frame = 26;
	    document.MM_Time[0][3].value = "hiddenCal()";

	    document.MM_Time[0][4] = new String("behavior");
	    document.MM_Time[0][4].frame = 50;
	    document.MM_Time[0][4].value = "MM_timelineStop()";

	    document.MM_Time[0][5] = new String("behavior");
	    document.MM_Time[0][5].frame = 50;
	    document.MM_Time[0][5].value = "MM_timelineGoto('Timeline1','1')";

	    document.MM_Time[0].lastFrame = 50;
	    for (i=0; i<document.MM_Time.length; i++) {
	        document.MM_Time[i].ID = null;
	        document.MM_Time[i].curFrame = 0;
	        document.MM_Time[i].delay = 1000/document.MM_Time[i].fps;
	    }
	}
	function hiddenCal(){
		try{
			hideCldTabFrm();
		}catch(e){
			//
		}
	}
	
//******************************************************************
// function  :校验form单元中元素是否位数字或小数点
// parameter :formName		form的名称
//						itemName		元素的名称
// return    :满足条件    true
//						不满足条件	false
//******************************************************************
function CheckIsNumberAndDot(formName,itemName,itemMessage){
	eval('var tempStr=document.'+formName+'.'+itemName+'.value');
	var flag=0;
	var count=0;
	var dot=0;
	if (tempStr.length == 0) return true;

	for(var i=0;i<tempStr.length;i++){
		var tempSub=tempStr.substring(i,i+1);
		var compareStr="0123456789.";
		if (compareStr.indexOf(tempSub)<0){
				flag=1;
				break;
		}

		var compareStr1="0123456789";
		if (compareStr1.indexOf(tempSub)<0){
		}else{
			count=1;
		}

		var compareStr2=".";
		if (compareStr2.indexOf(tempSub)<0){
		}else{
			dot=dot+1;
		}
	}
	if (flag==0 && count==1 && dot<=1){
		return true;
	}else{
		return false;
	}
}	
</script>
</HEAD>
<BODY style="overflow-x:auto;overflow-y:hidden;margin:0px 0px 0px 0px" onLoad="init();" ondblClick="MM_timelinePlay('Timeline1');" title="双击页面空白处或Alt+Q开始搜索" onkeypress="if(event.keyCode==27) top.close();">

<form method="Post" action="${ctx}/common/select.shtml?action=doPost" style="margin:0px 0px 0px 0px">
<input type="button" onClick="MM_timelinePlay('Timeline1');" style="width:0px;height:0px;" accesskey="Q">
<font style="color:red;height: 5px;">双击空白处查询！</font>
<div id='selComm' class="mdiv" style="width:786px;height:<%=(subTableObjects!=null&&subTableObjects.length>0)?"305px":"518px"%>;margin:0px 0px 0px 0px;"  >
<TABLE id="targetTable" onkeyup="keyClickEvent()"  cellspacing="1" cellpadding="2" width="100%" border=0 align="center" class="lst" >
	<TR>
	<%if(tableObject.getSelectFlag()){%>
	<th nowrap class="th_up" width="30" <%if(selectType.equals("true")){%>style="cursor:hand" onClick="clickAll()"<%}else{%>style="cursor:default"<%}%>>选中</th>
	<%}%>
	<%
	String conpath=request.getContextPath();
	String userStyle = (String) request.getSession(false).getAttribute("UserCSS");
	for(int i=0;i<tableObject.getFieldPyName().length;i++){
		if(tableObject.getIsShow()[i]){
			String tmpOrderName = tableObject.getFieldAsName()[i];
			if(tmpOrderName==null||tmpOrderName.equals("")){
				tmpOrderName = tableObject.getFieldPyName()[i];
			}

			String imagesrc = "";
			if(tmpOrderName.equals(orderName)){
				if(orderType.equals("DESC")){
					imagesrc = "<img src='"+conpath+"/styles/"+userStyle+"/images/main/list_order_down.gif'>";
				}
				if(orderType.equals("ASC")){
					imagesrc = "<img src='"+conpath+"/styles/"+userStyle+"/images/main/list_order_up.gif'>";
				}
			}
	%>
	<th nowrap onmousedown="this.className='th_down'" onmouseup="this.className='th_up'" class="th_up" onClick="orderBy('<%=tmpOrderName%>')" title="点击重新排序">
		<%=(tableObject.getFieldLgName())[i]+imagesrc%>
	</th>
	<%}}%>
	</TR>
	<%
		for(int j=0;j<vec.size()-1;j++){
			Hashtable ht = (Hashtable)vec.get(j);
	%>
	<TR id="tr_<%=j%>" class="notSelect"  onmouseover="msover(this)" onmouseout="msout(this)" onClick="<%if(tableObject.getSelectFlag()){%>trClick(<%=j%>)<%}else{%>funClick(<%=j%>)<%}%>" ondblClick="funDblClick(<%=j%>)" title="双击选择并返回">
	<%if(tableObject.getSelectFlag()){%>
	<td nowrap align="center"><input id="checkbox<%=j%>" type="checkbox" onClick="checkBoxClick(<%=j%>)"   value="<%=j%>"></td>
	<%}%>
	<%for(int i=0;i<tableObject.getFieldPyName().length;i++){
			String tmpName = tableObject.getFieldAsName()[i];
			if(tmpName==null||tmpName.equals("")){
				tmpName = tableObject.getFieldPyName()[i];
			}
			String value = (String)ht.get(tmpName);
			if(value==null)  value="";
			if(tableObject.getIsShow()[i]){
	%>
			<td nowrap <%if(tableObject.getFieldType()[i].equals("number")||tableObject.getFieldType()[i].equals("date")||tableObject.getFieldType()[i].equals("timestamp")) out.print("align='right'");%>><%=value%></td>
			<%}%>
			<%if(tableObject.getPrimaryKeyId()[i] != null && !tableObject.getPrimaryKeyId()[i].equals("")){%>
			<textarea Name="PK_<%=j%>_<%=tableObject.getPrimaryKeyId()[i]%>" style="display:none"><%=value%></textarea>
			<%}%>
			<%if((","+keyNames+",").indexOf(","+tmpName+",")>=0||(","+selCommFields+",").indexOf(","+tmpName+",")>=0)	{%>
			<textarea Name="<%=tmpName+"_"+j%>" style="display:none"><%=value%></textarea>
			<%}%>
	<%}%>
	</TR>
	<%}%>
</table>
<table cellspacing="0" cellpadding="0" border=0 width="100%" align="center" style="background-color:#f3f3f3;">
  <tr class='list_row'>
	<td>
	<% if (maxPage > 1){%>
	<a id="pageup" href="#implied" title="Alt+<" onclick="goPage('<%=pagenum-1%>')" <%if (pagenum <=1) {%>style="visibility:hidden"<%}%>>上一页<input type="button" onClick="goPage('<%=pagenum-1%>')"  style="width:0px;height:0px;" accesskey=","></a>
	<a id="pagedown" href="#implied" title="Alt+>" onclick="goPage('<%=pagenum+1%>')" <%if (pagenum == maxPage) {%>style="visibility:hidden"<%}%>><input type="button" onClick="goPage('<%=pagenum+1%>')" style="width:0px;height:0px;"   accesskey=".">下一页</a>
	<%}%>
	<%=pagenum%>/<%=maxPage%> 记录数:<%=maxRows%>
	<%if(maxPage>1){%>
	到第<input type="text" name="jumpPageNum" size="4" class="lineinput" onkeyup="if(event.keyCode==13){ goPage(this.value);}">页
	<a href="#implied" onclick="goPage(document.forms[0].jumpPageNum.value)" title="Enter">GO</a>
	<%}%>
	</td>
  </tr>
</table>
</div>
<%if((vecSearchChar.size()+vecSearchDate.size()+vecSearchNumber.size())>0){%>
<div id="searchLayer" class="poplayer" <%if((tableObject.getIsShowSearchLayer()&&searchCondition.equals(""))||(!tableObject.getIsShowSearchLayer()&&!searchCondition.equals("")&&maxRows==0)){%>style="top:0px"<%}%> style="width:786px;" >
<fieldset style="height:134px;">
	<legend style="color:buttonshadow;" >搜索</legend>
	<table width="100%" border="0"  align="center" cellspacing="1" cellpadding="2" onkeyup="if(event.keyCode==13) document.forms[0].serButton.click();">
		<%
			if(vecSearchDate!=null&&vecSearchDate.size()>0){
			for(int i=0;i<vecSearchDate.size();i++){
				int j = Integer.parseInt((String)vecSearchDate.get(i));
				String tmpName = tableObject.getFieldAsName()[j];
				if(tmpName==null||tmpName.equals("")){
					tmpName = tableObject.getFieldPyName()[j];
				}
		%>
		<tr class="popInput">
			<td nowrap align="right"><%=tableObject.getFieldLgName()[j]%></td>
			<td nowrap align="left"><input class="popInput" json="<%="from"+tmpName%>"  id="<%="from"+tmpName%>"  name="<%="from"+tmpName%>" readonly type="text" value="" inputtype="date" onclick="SelectDate();" ></td>
			<td nowrap align="right">到</td>
			<td nowrap align="left"><input class="popInput" json="<%="to"+tmpName%>"  id="<%="to"+tmpName%>" name="<%="to"+tmpName%>" readonly type="text" value="" inputtype="date" onclick="SelectDate();" ></td>
		</tr>
		<%}}%>

		<%
			if(vecSearchNumber!=null&&vecSearchNumber.size()>0){
			for(int i=0;i<vecSearchNumber.size();i++){
				int j = Integer.parseInt((String)vecSearchNumber.get(i));
				String tmpName = tableObject.getFieldAsName()[j];
				if(tmpName==null||tmpName.equals("")){
					tmpName = tableObject.getFieldPyName()[j];
				}
		%>
		<tr class="popInput">
			<td nowrap align="right"><%=tableObject.getFieldLgName()[j]%></td>
			<td nowrap align="left"><input class="popInput" name="<%="from"+tmpName%>" type="text" value=""></td>
			<td nowrap align="right">到</td>
			<td nowrap align="left"><input class="popInput" name="<%="to"+tmpName%>" type="text" value=""></td>
		</tr>
		<%}}%>

		<%
			if(vecSearchChar!=null&&vecSearchChar.size()>0){
			for(int i=0;i<vecSearchChar.size();i++){
				int j = Integer.parseInt((String)vecSearchChar.get(i));
				String tmpName = tableObject.getFieldAsName()[j];
				if(tmpName==null||tmpName.equals("")){
					tmpName = tableObject.getFieldPyName()[j];
				}
		%>
		<tr class="popInput">
			<td nowrap align="right"><%=tableObject.getFieldLgName()[j]%><input name="leftcheckbox<%=tmpName%>" type="checkbox" checked style="border:0px" value="%">%</td>
			<td nowrap align="left"><input class="popInput" name="ser<%=tmpName%>" type="text" value="">%<input name="rightcheckbox<%=tmpName%>" type="checkbox" checked style="border:0px" value="%"></td>
			<%
				i++;
				if(i<vecSearchChar.size()){
					j=Integer.parseInt((String)vecSearchChar.get(i));
					tmpName = tableObject.getFieldAsName()[j];
					if(tmpName==null||tmpName.equals("")){
						tmpName = tableObject.getFieldPyName()[j];
					}
			%>
			<td nowrap align="right"><%=tableObject.getFieldLgName()[j]%><input name="leftcheckbox<%=tmpName%>" type="checkbox" checked style="border:0px" value="%">%</td>
			<td nowrap align="left"><input class="popInput" name="ser<%=tmpName%>" type="text" value="">%<input name="rightcheckbox<%=tmpName%>" type="checkbox" checked style="border:0px" value="%"></td>
			<%}else{%>
			<td>&nbsp;</td><td>&nbsp;</td>
			<%}%>
		</tr>
		<%}}%>
		<tr class="popInput">
			<td colspan="4" align="right">
			<input type="radio" name="serFlag" value="0" checked style="border:0px">新的查询
			<input type="radio" name="serFlag" value="1" style="border:0px">在结果中查询
			<input type="button" class="popInput" name="serButton" value=" 查询(S) " title="Alt+S" accesskey="S" onClick="doSearch()">
			</td>
		</tr>
	</table>
</fieldset>
</div>
<%}%>
<textarea name="cmdFlag"           style="display:none">MainList</textarea>
<textarea name="orderName"         style="display:none"><%=orderName%></textarea>
<textarea name="orderType"         style="display:none"><%=orderType%></textarea>
<textarea name="pagenum"           style="display:none"><%=pagenum%></textarea>
<textarea name="isSearch"          style="display:none"></textarea>
<textarea name="selCommId"         style="display:none"><%=selCommId%></textarea>
<textarea name="tableId"           style="display:none"><%=tableId%></textarea>
<textarea name="selectType"        style="display:none"><%=selectType%></textarea>
<textarea name="keyNames"          style="display:none"><%=keyNames%></textarea>
<textarea name="selCommFields"     style="display:none"><%=selCommFields%></textarea>
<textarea name="oldCndt"           style="display:none"><%=oldCndt%></textarea>
<textarea name="specialTable"      style="display:none"><%=specialTable%></textarea>
<textarea name="specialAsTable"    style="display:none"><%=specialAsTable%></textarea>
<textarea name="isShowSearchLayer" style="display:none"><%=isShowSearchLayer%></textarea>
<textarea name="searchCondition"   style="display:none"><%=searchCondition%></textarea>
<input type="hidden" id="chgArrToChar" >
</form>
</BODY>
</HTML>
