/**
  *@module 系统模块   
  *@func 业务核心JS   
  *@version 1.1.3[79]  2011-12-27
  *@author zhuxw    
*/
var _row_num=1;
document.onkeydown=function(event){
	var myEvent = event || window.event;
	var keyCode = myEvent.keyCode;
	if(myEvent.altKey){
		if(keyCode == 38){
			if($("#butHidTop").length<1){
				parent.document.getElementById("butHidTop").click();
			}else{
				$("#butHidTop").click();
			}
		}else if(keyCode == 40){
			if($("#butHidBottom").length<1){
				parent.document.getElementById("butHidBottom").click();
			}else{
				$("#butHidBottom").click();
			}
		}else if(keyCode == 37 || keyCode == 39){
			//alert("不准你使用ALT+方向键前进或后退网页！");
			myEvent.returnvalue=false;
		}else if(keyCode==115){
			//屏蔽Alt+F4   
	         window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");   
	         return false;   
		}
	}
	else if(keyCode == 8){//屏蔽退格键
		var type = myEvent.srcElement.type;
		var readOnly = $(myEvent.srcElement).attr("readonly");
		var flag = (type != "text" && type != "textarea" && type != "password"&& type != "file");
		if(flag || readOnly){
			myEvent.keyCode=0;
			myEvent.returnvalue=false;
		}
	}
}
// 屏蔽右键   
// if (window.Event)  
//     document.captureEvents(Event.MOUSEUP);   
// function nocontextmenu() {  
//     event.cancelBubble = true;  
//     event.returnValue = false;   
//     return false;   
// }  
// function norightclick(e) {  
//     if (window.Event) {  
//         if (e.which == 2 || e.which == 3)  
//             return false;   
//     }  
//     else  
//         if (event.button == 2 || event.button == 3) {  
//             event.cancelBubble = true;  
//             event.returnValue = false;   
//             return false;  
//         }  
// }  
// document.oncontextmenu = nocontextmenu; // for IE5+   
// document.onmousedown = norightclick; // for all others 

 /*
 *记录根据光标悬浮和离开，显示不同的颜色
 *
 *@param obj 光标悬浮对象
 */
var keyEventObject;
function mover(obj){
	try{
	if(keyEventObject==obj){
	 return;
	}
	obj.oldClass=obj.className;
	obj.className="mover";
	}catch(e){}
}

function mout(obj){
	try{
	   if(keyEventObject==obj){
	    return;
	   }
	   obj.className=obj.oldClass;
	 }catch(e){}
}

function selectThis(obj)
{  try{
   if(keyEventObject!=null)
   {
     keyEventObject.className=obj.oldClass;
   }
   keyEventObject=obj;
   obj.className="clicked";
    }catch(e){}
}
function setDtlId(val)
{  
   try{
      parent.document.getElementById("selDtlId").value=val;
    }catch(e){}
}
/*
 *通用选取，一次性选取多条，回落时需要新增记录
 *
 *@param obj 通用选取返回的对象
 *@return ids 需要填入的字段id(不含行号)，多个以逗号隔开
 *@param i 行号
 *@param cols 填入的id对应的列数
 *@param collen 数组总长
 */
function multiSelCommonSet(obj,ids,i,callBack){
	//选中的记录
	var rowlist = [];
	rowlist.push(i);
	var len = obj[0];
	if(len>1){
		//每行需要填入的字段
		var idArr = ids.split(",");
		//存放所有返回的值
		var valArr=new Array();
		for(var j=0;j<idArr.length;j++){
			//每个字段的值。选中多条是以逗号隔开。
			var idvalus = $("#"+idArr[j]+i).val().split(",");
			//当前行应该只填入第一个值
			var v= idvalus[0];
			if((null == v)||('undefined' == v)||('NaN'==v)){
				v="";
			}
			$("#"+idArr[j]+i).val(v);
			valArr.push(idvalus);
		}
		
		for(var j=1;j<len;j++){
			var index = _row_num;
			addRow();
			var obj = $("#jsontb tr:last-child");
			rowlist.push(index);
			for(var m=0;m<idArr.length;m++){
				var v= valArr[m][j];
				if((null != v)&&("" != v)&&('undefined' != v)&&('NaN' != v)){
					$("#"+idArr[m]+index).val(v);
				}
			}
			obj.find("input[type='checkbox']").first().attr("checked","checked");
		}
	}
	return rowlist;
}	
 /*
  *解决IE下url缓存问题
  * 返回带随机参数的url
  */
 function noCacheUrl(url){
 	if(null == url){
 		return null;
 	}
 	var sign;
 	if(-1 != url.indexOf(".shtml?action")){
 		sign = "&";
 	}else{
 		sign = "?";
 	}
 	return url= url+ sign + "actionid=" + new Date().valueOf();
 }
 
 /*
 *将单个form封装成json串
 *
 *@param tableid
 *@return json格式字符串
 */
function siglePackJsonData(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "{";
	var inputs = $("#"+tableid+" :input");
	inputs.each(function(){
		if(null != $(this).attr("json")){
			var key;
			var value; 
			var type = $(this).attr("type");
			if("checkbox" == type){
				key = $(this).attr("json");
				if($(this).attr("checked")){
					value= 1;
				}else{
					value= 0;
				}
			}else if("radio" == type){
				if($(this).attr("checked")){
					key = $(this).attr("json");
					value= $(this).attr("value");
				}
			}else{
				key = $(this).attr("json");
				value= $(this).attr("value");
			}
			var inputtype = $(this).attr("inputtype");
			jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
		}
	});
	return 	jsonData = jsonData.substr(0,jsonData.length-1)+"}"
}

/*
 *将多个form封装成json串
 *
 *@param 
 *@return json格式字符串
 */
function multiPackJsonData(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
			var inputs = $(this).find(":input");
			inputs.each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if(!isFirst && "checkbox" == type){
						key = $(this).attr("json");
						if($(this).attr("checked")){
							value= 1;
						}else{
							value= 0;
						}
					}else if("radio" == type){
						if($(this).attr("checked")){
							key = $(this).attr("json");
							value= $(this).attr("value");
						}
					}else{
						key = $(this).attr("json");
						value = $(this).attr("value");
						
						isFirst = false;
					}
					var inputtype = $(this).attr("inputtype");
					jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
				}
			});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
 
/*
 *框架上下帧隐藏/显示切换
 *
 *@param topHeight 数字格式，但会当做百分数处理
 *@param bottomHeight
 *
 */
function divShowSwitch(topHeight,bottomHeight){
	//页面显示状态。hidtop,center,hidbottom.
	var showstate = "center";
	if(null == topHeight){
		topHeight = "50";
	}
	if(null == bottomHeight){
		bottomHeight = "45";
	} 
	var totalHeight = Number(topHeight) + Number(bottomHeight);
	$("#butHidTop").click(function(){
		if("center" == showstate){
			$("#topdiv").css("display","none");
			$("#bottomdiv").css("height",totalHeight+"%");
			$("#bottomdiv").show();
			showstate = "hidtop";
		}else if("hidtop" == showstate){
			showstate = "hidtop";
		}else{
			$("#topdiv").css("height",topHeight+"%");
			$("#topdiv").show();
			$("#bottomdiv").css("height",bottomHeight+"%");
			$("#bottomdiv").show();
			showstate = "center";
		}
	});
	
	$("#butHidBottom").click(function(){
		if("center" == showstate){
			$("#bottomdiv").css("display","none");
			$("#topdiv").css("height",totalHeight+"%");
			$("#topdiv").show();
			showstate = "hidbottom";
		}else if("hidtop" == showstate){
			$("#topdiv").css("height",topHeight+"%");
			$("#topdiv").show();
			$("#bottomdiv").css("height",bottomHeight+"%");
			$("#bottomdiv").show();
			showstate = "center";
		}else{
			showstate = "hidbottom";
		}
	});
}

/*
 *点击表头字段排序
 *
 *@param 表头Id
 *@param 提交的formId
 *
 */
function headColumnSort(tableId,formId){
	if(null == tableId){
		tableId = "ordertb";
	}
	if(null == formId){
		formId = "pageForm";
	}
	var orderId = $("#orderId").val();
	var orderType = $("#orderType").val();
	var ascIcon = "&nbsp;<span class='ascOrder'/>"
	var descIcon = "&nbsp;<span class='descOrder'/>"
	var ths = $("#"+tableId+" th");
	if (null != orderId && "" != orderId){
		ths.each(function(){
			if(orderId == $(this).attr("dbname")){
				if ("asc" == orderType){
					$(this).html($(this).text()+ascIcon);
				}else if("desc" == orderType){
					$(this).html($(this).text()+descIcon);
				}else{
					$(this).html($(this).text()+descIcon);
				}
			}	
			return;
		});
	}
	
	 ths.click(function(){
		var orderId = $(this).attr("dbname");
		if(null== orderId || ""==orderId){
			return;
		}
		if ("asc" == orderType){
			orderType = "desc"
		}else if("desc" == orderType){
			orderType = "asc"
		}else{
			orderType = "asc"
		}
		$("#orderType").val(orderType);
		$("#orderId").val(orderId);
		$("#"+formId).submit();
	});
}

/**
  *页面跳转时默认附加参数
  *页码，排序方式，排序类型，选中的记录Id
  */
function reqParamsEx(){
	return "&pageNo="+$("#pageNo").val()+"&orderId="+$("#orderId").val()+"&orderType="+$("#orderType").val()+"&selRowId="+$("#selRowId").val();
}

/**
 *主从及主从从列表页面通用加载方法
 *
 */
 /*
 ×author zhuxw 
 *lvl    1.1
 *1.1SAMPLE 页面初始化方法
 */
function listPageInit(url,tableId,formId){
	if(null == tableId){
		tableId = "ordertb";
	}
	if($("#"+ tableId +" input[type=radio][name='eid']:first").length>0){
		//列表页面加载时，很可能是从修改页面跳转过来，这时还应该返回跳转之前的状态，如排序，排序类型，选择记录，页码等
		var isFind=false;
		var selRowId = parent.document.getElementById("selRowId").value;
		if(null!= selRowId && "" != selRowId){
			var trs = $("#"+ tableId +" tr:gt(0)");
			trs.each(function(){
				var selRadio = $(this).find("input[type='radio']");
				var pkId = selRadio.attr("value");
				if(pkId == selRowId){
					$(this).click();
					isFind = true;
					return;
				}
			});
		}
		//如果没有找到，则默认选中第一条。
		if(!isFind){
			$("#"+ tableId +" tr:eq(1)").click();
		}
	}else{
		parent.document.getElementById("selRowId").value="";
		parent.window.gotoBottomPage();
	}
	//add by zhuxw
	setButtonCss();
	//表头排序
	headColumnSort(tableId,formId);
	$("#pageForm").attr("action",url);
	//查询页面设置
	queryInit(url);
	//防止滚动条当初翻页
	dipalyPageNo();
	 //add by zhuxw 自定义页面
	 if($("#personal").attr("type")=='button')
	 {
	   initpersonal(tableId);
	   $("#personal").click(function(){
	 	setpersonal();
	   });
	 }
	  
 }
//查询页面初始化
function queryInit(url){
	//查询设置相关 
	 $("#query").click(function(){
	 	var isShow = $("#querydiv").css("display");
	 	if("block"==isShow){
	 		$("#querydiv").css("display","none");
	 	}else{
	 		$("#querydiv").css("display","block");
	 	}
	 	//$("#querydiv").slideDown("normal");
	 }); 
	 $("#q_close").click(function(){
		 $("#querydiv").css("display","none");
	 });
	 $("#q_search").click(function(){
	 	 //修正查询后，下面页面不刷新的问题 09-14
	 	 qryformChecked('queryForm');
	 	 parent.document.getElementById("selRowId").value="";
		 $("#selRowId").val("");
		 $("#queryForm").attr("action",url+"&search=true&module="+getModule());
		 $("#queryForm").submit();
	 });
	 //自动转化为大写
	 $("#queryForm .uppercase").each(function(){
	 	$(this).keyup(function(){
	 		$(this).val($(this).val().toUpperCase());
	 	}).blur(function(){
	 		$(this).val($(this).val().toUpperCase());
	 	});
	 });
	 
	 enterKeyListener($("#queryForm"));
}

/**
  *框架页面通用加载方法
  *@param url 框架上帧页面的url
  *@param topHeight 数字格式，但会当做百分数处理
  *@param bottomHeight
  *
  */
//var _documentReadyState;
function framePageInit(url,topHeight,bottomHeight){
    browserinfo();
	//showWaitPanel();
	//加载上帧页面
	$("#topcontent").attr("src",url+reqParamsEx());
	setLabelSelected("label_1");
	setUpDown();
    //给标签页增加监听事件
	$("#label span").click(function(){
		//showWaitPanel();
		setLabelSelected($(this).attr("id"));
		if(""==$("#selRowId").val()){//解决单表点击新增，再点击返回后，点击‘详细信息’标签页面为空的bug
			var selRow = topcontent.$("#ordertb tr:gt(0) input:[type='radio']:checked");
			if(selRow.length>0){
				$("#selRowId").val(selRow.attr("value"));
			}
		}
	   gotoBottomPage($(this).attr("id"));
	});
	
	//框架上下帧隐藏显示切换
	divShowSwitch(topHeight,bottomHeight);
	//_documentreadyStateId = window.setInterval("getBottomcontentState()",100);
}

function getBottomcontentState(){
	var src = $("#bottomcontent").attr("src");
	if(null != src && ""!=src && "#"!=src){
		if(bottomcontent.document.readyState =="complete"){
			window.clearInterval(_documentreadyStateId); 
			closeWindow();
		}
	}
}

/**
 *设置框架上下移动
 */
function setUpDown(){
    $("#butHidTop").css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/frame_top.gif')");
    $("#butHidBottom").css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/frame_down.gif')");
    if(navigator.Actual_Name=='Safari')
	{
	  $("#butHidTop").attr("value"," ");
	  $("#butHidBottom").attr("value"," ");
	}else if(navigator.Actual_Name=='Chrome')
	{
	  $("#butHidTop").attr("value"," ");
	  $("#butHidBottom").attr("value"," ");
	}else
	{
	  $("#butHidTop").attr("value","    ");
	  $("#butHidBottom").attr("value","    ");
	}
}

/**
 *
 *设置浮动按钮的位置
 */

function setFloatPostion(floatDiv){
	var floatId= "floatDiv";
	if(null != floatDiv){
		floatId = floatDiv;
	}
	var top = $(window).scrollTop();
	var left = $(window).scrollLeft();
	$("#"+floatId).css("top",top).css("left",left);
}

//添加浮动层的监听
function addFloatDivListener(floatDiv){
	//设置浮动按钮
	$(window).bind("scroll", function(event){
		setFloatPostion(floatDiv);
	 });
//	 $(window).resize(function(){
//  		setFloatPostion();
//	});
}

//设置审批权限
function spflowInit(businessType,tableName,fieldName,sourceURI,flowInterfaceName,ztbgFieldName)
{
  document.affirm.businessType.value=businessType;
  document.affirm.tableName.value=tableName;
  document.affirm.fieldName.value=fieldName;
  document.affirm.sourceURI.value=sourceURI;
  document.affirm.flowInterfaceName.value=flowInterfaceName;
//  if(ztbgFieldName==''||ztbgFieldName==null)
//    document.affirm.ztbgFieldName.value='UPD_TIME';
//   else
//    document.affirm.ztbgFieldName.value=ztbgFieldName;
}

/**
  *多行校验
  *
  */
function mulitiTrValidate(){
	/*var flag = true;
	//明细提交时，可能会有多行，只有选中的才校验
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
			var inputs = $(this).find("input:gt(0)");
			inputs.each(function(){
				if (!InputCheck(this)) {
					flag = false;
					return flag;
				}
			});
		}
	});
	return flag;*/
	
	var flag = true;
	//明细提交时，可能会有多行，只有选中的才校验
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
		   
		    if(flag==false)
		    {
		     return false;
		    }
			var inputs = $(this).find("input:gt(0),select");
			inputs.each(function(){
				if (!InputCheck($(this)[0])) {
					flag = false;
					return false;
				}
			});
		}
	});
	return flag;
}

//utf8转码
function utf8(c){
	if(null == c){
		return "";
	}else{
		return encodeURI(encodeURI(c,"UTF-8"));
	}
}
//utf8解码
function utf8Decode(c){
	if(null == c){
		return "";
	}else{
		return decodeURI(decodeURI(c,"UTF-8"))
	}
}

//屏蔽退格键
function cancelBackspace(){
	document.onkeydown=function(event){
		var myEvent = event || window.event;
		var keyCode = myEvent.keyCode;
		if(keyCode == 8){
			var type = myEvent.srcElement.type;
			var readOnly = $(myEvent.srcElement).attr("readonly");
			var flag = (type != "text" && type != "textarea" && type != "password");
			if(flag || readOnly){
				myEvent.keyCode=0;
				myEvent.returnvalue=false;
			}
		}
	}
}


//单表页面使用.编辑页面跳转时往框架页面上记录编辑时的状态。
function setCommonPageInfo(flag){
	var selRowId ="";
	var pageNo ="";
	var orderType ="";
	var orderId ="";
	if(flag){
		var selRowId = $("#selRowId").val();
		var pageNo = $("#pageNo").val();
		var orderType = $("#orderType").val();
		var orderId = $("#orderId").val();
	}
	parent.document.getElementById("selRowId").value = selRowId;
	parent.document.getElementById("pageNo").value = pageNo;
	parent.document.getElementById("orderType").value = orderType;
	parent.document.getElementById("orderId").value = orderId;
}

//查询form校验。
function qryformChecked(formId){
	//先通用检验。检验成果后进行扩展的校验
	if(!qryFormCheck(formId)){
		return false;
	}
	return true;
}
//单个form校验。
function formChecked(formId){
	//先通用检验。检验成果后进行扩展的校验
	if(!newFormCheck(formId)){
		return false;
	}
	if(function_exists('formCheckedEx')){
		return formCheckedEx();
	}
	return true;
}

//明细表。多条记录的form校验
function formMutiTrChecked(){
	//先通用检验。检验成果后进行扩展的校验
	if(!mulitiTrValidate()){
		return false;
	}
	if(function_exists('formCheckedEx')){
		return formCheckedEx();
	}
	return true;
}


/**
* boolean object_exists(String objName, String parentObjName)
* 返回对象是否存在
* 
*/
function object_exists(objName, parentObjName){
if(!objName) return false;
var parentObject = parentObjName || 'window';
if(parentObject!='window'&&(!object_exists(parentObject))){
   return false;
}else{
   obj = parentObject + '.' + objName;
   return eval("typeof "+obj+"!='undefined'?true:false");
}
}
/**
* boolean function_exists(String funcName, String objName)
* 返回函数方法是否存在
* 
*/
function function_exists(funcName, objName){
if(!object_exists(funcName, objName)) return false;
func = (objName || 'window') + '.' + funcName;
return eval("typeof "+func+"!='undefined'?(typeof "+func+"=='function'?true:false):false");
}

//单表保存监听
function mtbSaveListener(actionUrl,pkId,successUrl,formId){
	$("#save").click(function(){
		$(this).attr("disabled","disabled");
		 mtbSave(actionUrl,pkId,successUrl,formId);
	});
}
//单表保存
function mtbSave(actionUrl,pkId,successUrl,formId){
    if(formId==null||formId=='')
    {
      formId="mainForm";
    }
	if(!formChecked(formId)){
			$("#save").removeAttr("disabled");
			return;
	}
	var selId = parent.document.getElementById("selRowId").value;
	var jsonData = siglePackJsonData();
	$.ajax({
		url: actionUrl+"&"+pkId+"="+selId,
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				//modify by zhuxw
				//window.parent.topcontent.location=successUrl+parent.window.reqParamsEx();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//主表删除监听
function mtbDelListener(actionUrl,pkId){
	$("#delete").click(function(){
	 	 mtbDelete(actionUrl,pkId);
	 });
}

//主表删除
function mtbDelete(actionUrl,pkId){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要删除吗?","mtbDelConfirm('"+actionUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");
}

function mtbDelConfirm(actionUrl,pkId,selRowId,goUrl){
	closeWindow();
	 $.ajax({
	 	url: actionUrl+"&"+pkId+"="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel("删除成功");
				//edit by zhuxw 2012.3.1
				//window.topcontent.location=goUrl;
				topcontent.pageForm.submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


//单表新增和修改按钮初始化
function mtbAddModiInit(){
	$("#add").click(function(){
		setCommonPageInfo(false);
		parent.window.gotoBottomPage("toEdit");
	});
	
	$("#modify").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
}

//主从及主从从主表新增和修改按钮初始化
function addModiToEditFrameInit(url){
	 $("#add").click(function(){
	 	 window.parent.location=url+"&module="+getModule(); 
	 });
	 
	 $("#modify").click(function(){
	 	 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	     var paramUrl=document.getElementById("paramUrl");
	 	     if(paramUrl!=null&&paramUrl.value!="")
	 	     {
	 	       window.parent.location=url+reqParamsEx()+"&module="+getModule()+"&paramUrl="+utf8((paramUrl.value.replaceAll("&","andflag")).replaceAll("=","equalsflag")); 
	 	     }else
	 	     {
	 	      window.parent.location=url+reqParamsEx()+"&module="+getModule(); 
	 	     }
	 	 }
	 });
}
//主表list选中记录后的操作
function setSelEid(obj,data){
	$(obj).attr("checked",'true');
	
	//设置选中的Id
	parent.document.getElementById("selRowId").value = $(obj).val();
	//清空明细Id
	if(parent.document.getElementById("selDtlId"))
	{
	  parent.document.getElementById("selDtlId").value = "";
	}
    //下帧跳转
	parent.window.gotoBottomPage();
	//parent.bottomcontent.showWaitPanel();
	//重复设置是为了修改时方便调用公用方法 reqParamsEx()
	$("#selRowId").attr("value",$(obj).val());
	//点击后可能有额外的操作。预留接口
	if(function_exists('setSelOperateEx')){
		var trObj = $(obj).parent().parent();
		setSelOperateEx(trObj,data);
	}
}

//主从页面保存成功后确认调整
function saveSuccess(msg,url){
	parent.saveConfirm(msg,"bottomcontent.goFrame('"+url+"');");
}
//new goback  add by zhuxw
function newGoBack(url){
	goFrame(url);
}
function goFrame(url){
   var paramUrl=parent.document.getElementById("paramUrl");
   if(paramUrl!=null&&paramUrl.value!="")
   {
     window.parent.location=url+parent.window.reqParamsEx()+"&module="+getModule()+"&paramUrl="+utf8(paramUrl.value); 
   }else
   {
     window.parent.location=url+parent.window.reqParamsEx()+"&module="+getModule(); 
   }
    
}

//获取模块名称
function getModule(){
	return parent.$("#module").val();
}

//设置页面标题
function setPageTitle(){
	var module = getModule();
	var title = $("#pageTitle").text();
	var name;
	if("sh"===module){
		name="审核";
	}else if("wh"===module){
		name="维护";
	}else if("bg"===module){
		name="变更";
	}else if("cl"===module){
		name="处理";
	}
	$("#pageTitle").text(title+name);
}

//审核按钮控制
function shBtnHidden(arrys){
	var module = getModule();
	if("sh"===module){
		btnHidden(arrys);
		$("#btntr").css("display","none");
	}
}
//维护按钮控制
function whBtnHidden(arrys){
	var module = getModule();
	if("wh"===module){
		btnHidden(arrys);
	}
}

//按钮隐藏
function btnHidden(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).css("display","none");
	}
}

//按钮不可用
function btnDisable(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).attr("disabled","true");
	}
}

//按钮批量重置状态重置 add by zzb 2104-12-09
function btnArrysReset(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).removeAttr("disabled");
	}
}

//按钮状态重置
function btnReset(){
	$("#qxBtnTb :input['disabled'=true]").removeAttr("disabled");
}

//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).removeAttr("checked");
	}else{
		$(obj).attr("checked","true");
	}
}

//返回上一页。有修改则刷新，无修改不刷新
function goback(){
	var actionType = getActionType();
	if("list"==actionType){
		if("true" == $("#delFlag").val()){
			parent.window.gotoBottomPage("label_1");
		}else{
			window.history.back();
		}
	}else{
	 
         javascript:history.go(-1);
	 }
}

//对原有日期控件方法兼容
function SelectDate(obj){
	if(null == obj){
		WdatePicker();
	}else{
		obj.click();
	}
}

function SelectTime(obj){
	if(null == obj){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})
	}else{
		obj.click();
	}
}

function SelectTime1(obj){
	if(null == obj){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})
	}else{
		obj.click();
	}
}
//选择年，月
function SelectDate1(obj){
	if(null == obj){
		WdatePicker({dateFmt:'yyyy-MM'})
	}else{
		obj.click();
	}
}

//下拉列表通用方法
function SelDictShow(selectId,tableId,defaultValue,condition,isAsyn){
	if(null==selectId){
		selectId="";
	}
	if(null==tableId){
		tableId="";
	}
	if(null==defaultValue){
		defaultValue="";
	}
	if(null==condition){
		condition="";
	}
	if(null==isAsyn){
		isAsyn=true;
	}
	//tableId = "sel_" + tableId;
	var mybj = {"Id":selectId,"dictId":tableId,"defaultValue":defaultValue,"condition":condition};
	//var jsonArg = "{'Id':'" + selectId + "','dictId':'" + tableId + "','defaultValue':'" + defaultValue + "','condition':'" + condition + "'}";
   	//var mybj=eval('(' + jsonArg + ')'); 
   	if ($.trim(mybj.Id)==""){
      	alert("请设置选择框ID");
      	return(false);
   	}
   	if ($.trim(mybj.dictId)==""){
      	alert("请设置字典表ID");
      	return(false);
   	}
    $.ajax({
		url: ctxPath+"/system/dicselect.shtml?action=dictSel",
		type:"POST",
		dataType:"text",
		data:mybj,
		async: isAsyn,
		complete: function(data){
			eval("jsonResult = "+data.responseText);
			if(jsonResult.success===true){
				//$("#"+).empty();js生产的select下的option节点，有时无法移除。
				var select = document.getElementById(mybj.Id);
				if(null != select){
					for (var i = select.length; i >= 0; i--) {
						select.remove(i);
					}
					$("#"+mybj.Id).append(decodeURI(jsonResult.messages,"UTF-8"));
					//$("#"+mybj.Id).val(defaultValue);
				}
				return true;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				return false;
			}
		}
   });
}

//下拉列表通用方法.该方法会将取得的select值缓存在页面。
function SelDictCache(idPre,index,tbId,defVal,cond){
	if(null==cond){
		cond="";
	}
	if($("#__sel_"+idPre).length>0){
		if(cond==$("#__sel_Value_"+idPre).attr("value")){
			selOption = $("#__sel_"+idPre).html();
			var select = document.getElementById(idPre+index);
			if(null == select){
				alert("页面未查询到id为"+idPre+index+"的控件");
				return;
			}
			for (i = select.length; i >= 0; i--) {
				select.remove(i);
			}
			$("#"+idPre+index).append(selOption);
			$("#"+idPre+index).val(defVal);
		}
		else{
			$("#__sel_"+idPre).remove();
			$("#__sel_Value_"+idPre).remove();
			setTimeout(function(){SelDictCache(idPre,index,tbId,defVal,cond)},"500");
		}
	}else{
		if(Number(index)>1&&""==cond){
			setTimeout(function(){SelDictCache(idPre,index,tbId,defVal,cond);},"50");
		}else{
			var mybj = {"Id":idPre+index,"dictId":tbId,"defaultValue":defVal,"condition":cond};
			  $.ajax({
				url: ctxPath+"/system/dicselect.shtml?action=dictSel",
				type:"POST",
				dataType:"text",
				data:mybj,
				complete: function(data){
					eval("jsonResult = "+data.responseText);
					if(jsonResult.success===true){
						//$("#"+).empty();js生产的select下的option节点，有时无法移除。
						var select = document.getElementById(idPre+index);
						if(null == select){
							alert("页面未查询到id为"+idPre+index+"的控件");
							return;
						}
						for (i = select.length; i >= 0; i--) {
							select.remove(i);
						}
						selOption = utf8Decode(jsonResult.messages);
						$("#"+idPre+index).append(selOption);
						$("#"+idPre+index).val(defVal);
						$("table").last().after("<select style='display:none' id=__sel_"+idPre+" >"+selOption+"</select>")
										 .after("<input style='display:none' id=__sel_Value_"+idPre+" value="+cond+">");
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
						return;
					}
				}
		   });
		}
	}
}


//根据类型过滤字符
function inputtypeFilter(inputtype,value){
	if(null == inputtype || "" == inputtype){
		return value;
	}
	if(inputtype.toLowerCase() == "string"){
		value = charFilter(value);
	}else{
		value = $.trim(value);
	}
	return value;
}

					
//string特殊字符过滤
function charFilter(value){
	if(null==value){
		return "";
	}
	value = $.trim(value);
	try {
		value = value.replace(/\'/g, "");
		value = value.replace(/\·/g, "");
		value = value.replace(/\</g, "＜");
		value = value.replace(/\>/g, "＞");
		value = value.replace(/\\/g, "\\\\");
		value = value.replace(/\$/g, "?");
		value = value.replace(/\r\n/g, "");
		while (value.indexOf('"') >= 0) {
			value = value.replace('"', '“');
			value = value.replace('"', '”');
		}
	} catch (e) {
	}
	return value;
}

/**
  *尺码明细页面加载
  *@param confNo 配置Id
  *@param isGroup 是否需要分组合计,默认不需要
  */
function toCmmxList(confNo,isGroup){
	//以下判断只是为了便于调试，实际情况中不应该出现该错误。
	if(null == confNo || ""==confNo){
		showErrorMsg("\u8bf7\u8f93\u5165\u5c3a\u7801\u660e\u7ec6\u914d\u7f6e\u7f16\u53f7");
		return;
	}
	var parms = "&confNo="+confNo+"&selRowId="+$("#selRowId").val();
	var url = ctxPath+"/common/cmmx.shtml?action=toCmmx"+parms;
	if(null != isGroup & ""!=isGroup){
		url=url+"&isGroup="+isGroup
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

/**
  *正则校验，只允许输入字母和数字。
  *应用场景：如手动输入的编号
  */
function regNmChar(str){
	if(null == str){
		return false;
	}
	var regNm = new RegExp(/[A-Za-z0-9]+$/g);
	return regNm.test(str);
}

/**
  * 封装月份下拉列表
  * @param id :select id
  * @param defVal: 默认值，没有默认值则为当前月
  * @return
  */
function monthSelect(id,defVal){
	var select = $("#"+id);
	var options="";
	for(var i=1; i<13;i++){
		if(i<10){
			i = "0"+i;
		}
		options = options+"<option value="+i+">"+i+"</option>";
	}
	if(null == defVal || ""==defVal){
		defVal = new Date().getMonth()+1;
		if(defVal < 10){
			defVal = "0"+defVal;
		}
	}
	$("#"+id).append(options);
	$("#"+id).attr("value",defVal);
}

/**
  * 封装年份下拉列表
  * @param id :select id
  * @param defVal: 默认值，没有默认值则为当前年
  * @return
  */
function yearSelect(id,defVal){
	var select = $("#"+id);
	var options="";
	var year = new Date().getFullYear();
	var	begYear = year-5;
	
	for(var i=0; i<15;i++){
		options = options+"<option value="+(begYear+i)+">"+(begYear+i)+"</option>";
	}
	if(null == defVal || ""==defVal){
		defVal = year;
	}
	$("#"+id).append(options);
	$("#"+id).attr("value",defVal);
}

/**
 * 小数位格式化
 * @param number :数字
 * @param len :小数位长度
 * @return 格式化后的小数.如果传入的不是数字，则直接将传入值返回。
 */
function numberFormat(number,len){
	if(isNaN(number)){
		return number;
	}
    var num = new Number(number);
	return num.toFixed(len);
}

//树展示隐藏 ---更改为拖动
function treeShowHide(){
	var d = $(document);
	var r = $("#colResize");
	r.css("cursor","e-resize");
	r.mousedown(function(e){
		var rclone = r.clone(true).insertAfter(r);
		$("body").append("<div id='_tree_resize' class='tree_resize'>&nbsp;</div>");
		d.mousemove(function(e){
			var width = e.clientX;
			if(width<1){
				width=1;
			}else if(width>600){
				width=600;
			}
			r.css({
					position: 'absolute',
					left: width
				});
		});
		d.mouseup(function(e){
			var width = e.clientX;
			if(width<1){
				width=1;
			}else if(width>600){
				width=600;
			}
			$("#leftiframe").attr("width",width);
			d.unbind('mousemove');
			d.unbind('mouseup');
			$("#_tree_resize").remove();rclone.remove();
		});
	});
}
	
function mathJLSL(argBZSL,argJLSL,argHSGZ,num){
   var tempBZSL=$("#"+argBZSL+num).val(); 
   var tempJLSL=$("#"+argJLSL+num).val(); 
   var tempHSGZ=$("#"+argHSGZ+num).val();  
   if(tempHSGZ==''||tempHSGZ==null || '0'==tempHSGZ){
      return ;
   }else{
     $("#"+argJLSL+num).val(numberFormat(Number(tempBZSL)/Number(tempHSGZ),2));
   }
}
function mathBZSL(argBZSL,argJLSL,argHSGZ,num){

   var tempBZSL=$("#"+argBZSL+num).val(); 
   var tempJLSL=$("#"+argJLSL+num).val(); 
   var tempHSGZ=$("#"+argHSGZ+num).val();  
   if(tempHSGZ==''||tempHSGZ==null || '0'==tempHSGZ){
      return ;
   }else{
     $("#"+argBZSL+num).val(numberFormat(Number(tempJLSL)*Number(tempHSGZ),2));
   }
}

function mathByHSGX(argBZSL,argJLSL,argHSGZ,num){
   var tempBZSL=$("#"+argBZSL+num).val(); 
   var tempJLSL=$("#"+argJLSL+num).val(); 
   var tempHSGZ=$("#"+argHSGZ+num).val();  
   if(tempHSGZ==''||tempHSGZ==null || '0'==tempHSGZ){
      return ;
   }else{
     if(tempJLSL!=''&&tempJLSL!=null){
      $("#"+argBZSL+num).val(numberFormat(Number(tempJLSL)*Number(tempHSGZ),2));
      return ;
    }
     if(tempBZSL!=''&&tempBZSL!=null){
        $("#"+argJLSL+num).val(numberFormat(Number(tempBZSL)/Number(tempHSGZ),2));
       return ;
     }
     }
}

//设置当前标签页为选中状态
function setLabelSelected(lableId){
	$("#showLabel").attr("value",lableId);
	
	$("#"+lableId).css({
		"border":"1px solid #cfcfc7",
		"background-image":"url('"+ctxPath+"/styles/"+theme+"/images/main/button_bg.jpg')",
		"color":"#000"
	}).siblings().css({
			"border":"",
			"background-image":"url('"+ctxPath+"/styles/"+theme+"/images/main/tab_bg.jpg')",
			"color":"#666666"
		});
	
	//$("#"+lableId).css("border","1px solid #cfcfc7");
	//$("#"+lableId).css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/button_bg.jpg')  repeat-x")
	// 	.siblings().css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/tab_bg.jpg')");
}

//根据Id获取上帧某个元素    如果当前就是上帧页面则不建议用该方法，直接$(“#id”)即可
function getTopElement(id) {
	return $(window.parent.frames["topcontent"].document).find("#"+id);
}   
//根据Id获取下帧某个元素   如果当前就是下帧页面则不建议用该方法，直接$(“#id”即可
function getBottomElement(id) {
	return $(window.parent.frames["bottomcontent"].document).find("#"+id);
}
//获取上帧当前选中记录的id
function getTopSelectedElement() {
	return $(window.parent.frames["topcontent"].document).find("input[type=radio]:checked");
}  

//定制化页面
function pageCustomized(tbNo){
	$("#pageCustomized").click(function(){
		var val = window.showModalDialog(ctxPath+'/customized.shtml?action=toSetPageColumn&tbNo='+tbNo,'','dialogwidth=300px; dialogheight=400px; status=no');
		if(1==val){
			$("#pageForm").submit();
		}
	});
}

//回车键事件监听
function enterKeyListener(obj){
	$(obj).unbind( "keydown");
	$(obj).keydown(function(event) {
 		var keyCode = event.keyCode;
 		if(keyCode==13){
 			var e = $(event.srcElement);
	 		var type = e.attr("type");
	 		var flag = (type != "button" && type != "textarea");
	 		if(flag){
		 		if(type!="file"){
		 			var selTarget = e.attr("selTarget");
					//通用选取
		 			if(null!=selTarget){
		 				var t = $(obj).find("img[name="+selTarget+"]");
		 				if(t.length>0){
			 				t.first().click();
		 				}
		 			}else{
		 				window.event.keyCode=9;
		 			}
		 		}else{
					//window.event.keyCode=9;
		 		}
	 		}
 		}
	});
}

//导出xml
function exportXml(url,id){
	if(null==id||""==id){
		id="exportXml";
	}
	
	$("#"+id).click(function(){
		var action = $("#pageForm").attr("action");
		$("#pageForm").attr("action",url);
		$("#pageForm").submit();
		$("#pageForm").attr("action",action);
	});
}

//动态合计行
function dtSumLine(){
	addSumLine();
	sumSlJeLine();
}

//添加合计行
function addSumLine(){
		$("#numhj").append("<tr class=''></tr>");
		var tds = "";
		var td3width = 0;
		var leng = $("#jsontb").find("tr:eq(0)").find("th").length;
		var obj = null;
		if($("#jsontb").length>0){
			obj = $("#jsontb");
		}else{
			obj = $("#ordertb");
		}
		obj.find("tr:eq(0)").find("th").each(function(i){
			var tdwidth = $(this).width();
			var issum = $(this).attr("issum");
			if(i!=1){
				if(issum=="true"){
					tds = tds + "<td nowrap align=\"right\" issum=\""+issum+"\" width=\""+tdwidth+"\" >0</td>";
				}else{
					tds = tds +  "<td width=\""+tdwidth+"\">&nbsp;</td>";
				}
			}else{
				tds = tds +  "<td width=\""+tdwidth+"\"><font size=\"3\">合计&nbsp;</font></td>";
			}
		});
		$("#numhj tr:eq(0)").append(tds);
}
//计算合计值
function sumSlJeLine(){
	var sum = 0;
	var obj = null;
	if($("#jsontb").length>0){
		obj = $("#jsontb");
	}else{
		obj = $("#ordertb");
	}
	obj.find("tr:eq(0)").find("th").each(function(i){
		var issum = $(this).attr("issum");
		var slje = "0";
		var v_slje = 0;
		if(issum=="true"){
			sum = 0; //SUM 清零！！
			obj.find("tr:gt(0)").each(function(j){
				$(this).find("td").each(function(k){
					slje = "0"
					v_slje = 0;
					if(i==k){ //如果与 issum=“true” 的那个th相同的列时就计算
						var slje = null;
						if($(this).find("input").length>0){
							slje = $.trim($(this).find("input:eq(0)").val());
						}else{
							slje = $.trim($(this).text());
						}
						v_slje = (isNaN(slje)||slje==''||slje==null)?0:parseFloat(slje);				
						sum = sum + v_slje;
					}
				});
			});
			//
			$("#numhj tr:eq(0)").find("td:eq("+i+")").html(numberFormat(sum,2)+"&nbsp;");
		}
	});

}

/*
 ×author xingkefa 2012-03-30
 *获取当天日期   
 *@param year 输入年份
 *@param month 输入月份
 ×返回 月份的最大天数
 */
function getCurrentData(){
	var date = new Date(); 
	
	var day = date.getDate(); //当前 日
	var month = date.getMonth()+1; //当前 月
	var year = date.getFullYear(); //当前 年
	// 不足两位 补0
	if(month < 10){
	   month = '0'+month;
	}
	// 不足两位 补0
	if(day < 10){
	  day = '0'+day;
	}
	var nowDay = year+ "-"+month + "-"+day;
	return nowDay;
}

/*
 ×author xingkefa 2012-03-30
 *获取上个月的今天日期  
 *@param year 输入年份
 *@param month 输入月份
 ×返回 月份的最大天数
 */
function getBeforData(){
	var date = new Date(); 
	
	var day = date.getDate(); //当前 日
	var month = date.getMonth()+1; //当前 月
	var year = date.getFullYear(); //当前 年

	month=month-1;
	if(month==0){
		year = year -1;
		month = 12;
	}
	var beforday = getDaysInMonth(year,month);
	if(parseInt(day)>=parseInt(beforday)){
		day = parseInt(beforday);
	}
	// 不足两位 补0
	if(month < 10){
	   month = '0'+month;
	}
	// 不足两位 补0
	if(day < 10){
	  day = '0'+day;
	}
	var firstDay = year+ "-"+month + "-" + day;
	return firstDay;
}

/*
 ×author xingkefa 2012-03-30
 *下面是使用JS编写的获取某年某月有多少天的getDaysInMonth(year, month)方法：
 *@param year 输入年份
 *@param month 输入月份
 ×返回 月份的最大天数
 */
function getDaysInMonth(year,month){
	//parseInt(number,type)这个函数后面如果不跟第2个参数来表示进制的话，默认是10进制
      month = parseInt(month,10)+1; 
      var temp = new Date(year+"/"+month+"/0");
      return temp.getDate();
}

/*
 ×author zhuxw 2013-08-13
 *切换按钮样式
 */
function setButtonCss(){
$("#:button").hover(
     function () {
        //$(this).addClass("btn_active");
      },
     function () {
       //$(this).removeClass("btn_active");
      }
    );
 }
    
    

/*
 ×author zhuxw 
 *lvl    1.1
 *1.1SAMPLE 页面初始化方法
 */
function newFramePageInit(url,topHeight,bottomHeight){
	//showWaitPanel();
	//加载上帧页面
	$("#topcontent").attr("src",url+reqParamsEx());
	setLabelSelected("label_1");
	$("#butHidTop").css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/index/top.gif')");
	$("#butHidBottom").css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/index/down.gif')");
	$("#butHidTop").attr("value","    ");
	$("#butHidBottom").attr("value","    ");
	
	//给标签页增加监听事件
	$("#label span").click(function(){
		setLabelSelected($(this).attr("id"));
		if(""==$("#selRowId").val()){//解决单表点击新增，再点击返回后，点击‘详细信息’标签页面为空的bug
			var selRow = topcontent.$("#ordertb tr:gt(0) input:[type='radio']:checked");
			if(selRow.length>0){
				$("#selRowId").val(selRow.attr("value"));
			}
		}gotoBottomPage($(this).attr("id"));
	});
	
	//框架上下帧隐藏显示切换
	divShowSwitch(topHeight,bottomHeight);
	
	var parQueryDiv=$("#querydiv");
	 $("#q_close").click(function(){
		 parQueryDiv.css("display","none");
	 });
	 
	 $("#q_search").click(function(){
		   document.getElementById("selRowId").value="";
		   topcontent.$("#selRowId").val("");
		   var inputs =$("#querydiv :input");
		   var htmlStr="";
		   var lenInputs=inputs.length;
           for(i=0;i<lenInputs;i++)
           {
            if(inputs[i].name!=""&&inputs[i].name!=null&&inputs[i].value!=""&&inputs[i].value!=null)
             {
                htmlStr+="<input type='hidden'  name='"+inputs[i].name+"' value='"+inputs[i].value+"' />";
             }
           }
         var chlidQueryForm= topcontent.$("#qureyForm");
         chlidQueryForm.attr("action",url+"&search=true");
		 $("#insParms").innerHTML=htmlStr;
		 //chlidQueryForm.innerHTML=htmlStr;
		 topcontent.document.getElementById("insParms").innerHTML=htmlStr;
		 chlidQueryForm.submit();
	 });
}


/*
 ×author zhuxw 
 *lvl    1.1
 *展现页面设置页面
 */
function setpersonal(tableId){
  if($("#pageConf")!=null)
  {
    $("#pageConf").remove();
  }
  var pagediv = "<div id='pageConf' style='background:#e0edf6;width:100%;height:100%;z-index:999;position:absolute;top:0;margin-top:70px;filter:alpha(opacity=90);'></div>"; 
  $(document.body).append(pagediv);
  var table_conf='<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">';
  table_conf+='<tr><td colspan="10" align="center"  style="font-size:15" >';
  table_conf+=" 页面列自定义 ";
  table_conf+='</td></tr>';
  if(tableId==null||tableId=="")
  tableId="ordertb";
  var num=0;
  var dbNameArr="allDbName";
  var ths = $("#"+tableId+" th");
  var cssStr="";
 if(navigator.Actual_Name=='Chrome')
   {
	   cssStr="table-cell";
   }else if(navigator.Actual_Name=='Safari')
   {
	  cssStr="table-cell";
    }else{
	  cssStr="block";
   }
   $.each(ths,function(i,k){
	        var idstr=$(this).attr("dbname");
		    var valstr=$(this).text();
		    var disstr=$(this).css("display");
		    if(idstr!=null&&idstr!="")
		    { 
		       if(num%4==0)
		       {
		         table_conf+='<BR><tr>';
		       }
		        table_conf+='<td width="8%" nowrap align="right">'+valstr+'</td>';
                table_conf+='<td width="15%">';
                if(disstr==cssStr)
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  checked name="'+idstr+'box" id='+idstr+'box   value="'+idstr+'"/>';
                else
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  name="'+idstr+'box" id='+idstr+'box  value="'+idstr+'"/>';
                table_conf+='</td>';
		        num+=1; 
		        dbNameArr=dbNameArr+","+idstr;   
		    }
		 
	  }
	)
	table_conf+='<tr><td colspan="10" align="center"  valign="middle" >';
	table_conf+="<input id='btn_ok' type='button' class='btn' value='确 认'>&nbsp;&nbsp;";
    table_conf+="<input id='btn_closed' type='button' class='btn' value='关 闭' >&nbsp;&nbsp;";
    table_conf+="<input id='btn_clear' type='button' class='btn' value='清除设置'>&nbsp;&nbsp;";
    table_conf+='</td></tr></table>'; 
    $("#pageConf").append(table_conf);
    $("#btn_ok").click(function(){
		doPageRest(tableId); 
	 });
	 $("#btn_closed").click(function(){
		 $("#pageConf").remove();
	 });
	 $("#btn_clear").click(function(){
		 clearPersonal(dbNameArr,tableId);
	 });
	 
}
function initpersonal(tableId){
  browserinfo();
  if(tableId==null||tableId=="")
  tableId="ordertb";
  var keyvalue=window.location.pathname;
  keyvalue=keyvalue.substring(keyvalue.lastIndexOf('/')+1,keyvalue.length).replace('.','');
  var hiddenArr= new Array();
  var displayArr=new Array();
  var ths = $("#"+tableId+" th");
    $.each(ths,function(i,k){
	        var idstr=$(this).attr("dbname");
		    if(idstr!=null&&idstr!="")
		    { 
		      var disflag=$.cookie(keyvalue+"_"+idstr);
		      if(disflag==null||disflag=="1")
		      {
		        displayArr.push(idstr);
		      }else if(disflag=="0")
		      {
		        hiddenArr.push(idstr);
		      }
		      
		    }
	  }
	)
  displayColumn(hiddenArr,displayArr,tableId);
}
/*
 ×author zhuxw 
 *lvl    1.1
 *1.1SAMPLE 隐藏或者显示表格列的方法
 */
function displayColumn(hiddenArr,displayArr,tableId){
    if(hiddenArr.length==0&&displayArr.length==0)
       return;
    if(null == tableId){
		tableId = "ordertb";
	}
	var hiddenNumStr="";
	var displayNumStr="";
	var ths = $("#"+tableId+" th");
    $.each(ths,function(i,k){
    var str=$(this).attr("dbname");
		    if(checkIsIn(hiddenArr,str)){
		      $(this).css("display","none");
		      hiddenNumStr+=i+",";
			}
			if(checkIsIn(displayArr,str)){
			   if(navigator.Actual_Name=='Chrome')
	           {
	             $(this).css("display","table-cell");
	           }else if(navigator.Actual_Name=='Safari')
	           {
	             $(this).css("display","table-cell");
	           }else
	           {
	              $(this).css("display","block");
	           }
			   displayNumStr+=i+",";
			}	
	  }
	)
	
	var trs = $("#"+tableId+" tr");
	$.each(trs,function(i,k){
	  if(i==0)
	  return true;
	  var tds = $(this).find("td");
	  var hiddenNumArr=hiddenNumStr.split(",");  
	  var displayNumArr=displayNumStr.split(",");   
	     $.each(hiddenNumArr,function(f,g){
		     if(g!=""&&g!=null&&tds[g]!=null){
		         tds[g].style.display="none";
		    }
		   }
	     )
	     $.each(displayNumArr,function(f,g){
		     if(g!=""&&g!=null&&tds[g]!=null){
		         tds[g].style.display="inline";
		    }
		   }
	     )
	  }
	)
		
}

function checkIsIn(strArr,str){
   for(i=0;i<strArr.length;i++)
   {
     if(str==strArr[i])
     return true;
   }
   return false;
}
function doPageRest(tableId){
    var hiddenArr= new Array();
	var displayArr=new Array();
	var pageInp=$("#pageConf input:checkbox");
	var keyvalue=window.location.pathname;
    keyvalue=keyvalue.substring(keyvalue.lastIndexOf('/')+1,keyvalue.length).replace('.','');
    $.each(pageInp,function(i,k)
    {
      if(this.checked)
      {
        displayArr.push($(this).val());
        $.cookie(keyvalue+"_"+$(this).val(),'1');
      }else
      {
        hiddenArr.push($(this).val());
        $.cookie(keyvalue+"_"+$(this).val(),'0');
      }
    }
   )
   displayColumn(hiddenArr,displayArr,tableId);
 }
 
 function clearPersonal(dbNameArr,tableId)
 {
   var keyvalue=window.location.pathname;
   keyvalue=keyvalue.substring(keyvalue.lastIndexOf('/')+1,keyvalue.length).replace('.','');
   var dbNameArr=dbNameArr.split(",");   
   for(i=1;i<dbNameArr.length;i++)
   {
     $.cookie(keyvalue+"_"+dbNameArr[i],null);
   }    
   alert("清除设置成功!");
   var hiddenArr= new Array();
   var displayArr=dbNameArr;
   displayColumn(hiddenArr,displayArr,tableId);
   
 }
 /**
  ×author zhuxw 
  *lvl    1.1
  *防止混动条挡住了翻页
  */
function dipalyPageNo(formId){
     if(null == formId){
		formId = "pageForm";
	}
	 if(document.body.scrollWidth>document.body.clientWidth)
     {
      $("#"+formId).append("<br>&nbsp;<br>&nbsp;");
     }
}
/*
 ×author zhuxw 
 *lvl    1.1
 *获得浏览器类型
 */
function browserinfo(){
        var Browser_Name=navigator.appName;
        var Browser_Version=parseFloat(navigator.appVersion);
        var Browser_Agent=navigator.userAgent;
        var Actual_Version,Actual_Name;
        var is_IE=(Browser_Name=="Microsoft Internet Explorer");//判读是否为ie浏览器
        var is_NN=(Browser_Name=="Netscape");//判断是否为netscape浏览器
        var is_op=(Browser_Name=="Opera");//判断是否为Opera浏览器
    if(is_NN){
            if(Browser_Version>=5.0){
    if(Browser_Agent.indexOf("Netscape")!=-1){
        var Split_Sign=Browser_Agent.lastIndexOf("/");
                 var Version=Browser_Agent.lastIndexOf(" ");
     var Bname=Browser_Agent.substring(0,Split_Sign);
     var Split_sign2=Bname.lastIndexOf(" ");
                 Actual_Version=Browser_Agent.substring(Split_Sign+1,Browser_Agent.length);
                 Actual_Name=Bname.substring(Split_sign2+1,Bname.length);
   
     }
    if(Browser_Agent.indexOf("Firefox")!=-1){
        var Split_Sign=Browser_Agent.lastIndexOf("/");
                 var Version=Browser_Agent.lastIndexOf(" ");
                 Actual_Version=Browser_Agent.substring(Split_Sign+1,Browser_Agent.length);
                 Actual_Name=Browser_Agent.substring(Version+1,Split_Sign);
   
    }
   if(Browser_Agent.indexOf("Safari")!=-1){
   if(Browser_Agent.indexOf("Chrome")!=-1){
   var Split_Sign=Browser_Agent.lastIndexOf(" ");
                var Version=Browser_Agent.substring(0,Split_Sign);;
             var Split_Sign2=Version.lastIndexOf("/");
                var Bname=Version.lastIndexOf(" ");
                 Actual_Version=Version.substring(Split_Sign2+1,Version.length);
                 Actual_Name=Version.substring(Bname+1,Split_Sign2);
   }
   else{
       var Split_Sign=Browser_Agent.lastIndexOf("/");
                var Version=Browser_Agent.substring(0,Split_Sign);;
             var Split_Sign2=Version.lastIndexOf("/");
                var Bname=Browser_Agent.lastIndexOf(" ");
                 Actual_Version=Browser_Agent.substring(Split_Sign2+1,Bname);
                 Actual_Name=Browser_Agent.substring(Bname+1,Split_Sign);
   
      }
     }
    }
            else{
                 Actual_Version=Browser_Version;
                 Actual_Name=Browser_Name;
             }
         }
        else if(is_IE){
            var Version_Start=Browser_Agent.indexOf("MSIE");
            var Version_End=Browser_Agent.indexOf(";",Version_Start);
             Actual_Version=Browser_Agent.substring(Version_Start+5,Version_End)
             Actual_Name=Browser_Name;
        if(Browser_Agent.indexOf("Maxthon")!=-1||Browser_Agent.indexOf("MAXTHON")!=-1){
            var mv=Browser_Agent.lastIndexOf(" ");
            var mv1=Browser_Agent.substring(mv,Browser_Agent.length-1);
            mv1="遨游版本:"+mv1;
            Actual_Name+="(Maxthon)";
            Actual_Version+=mv1;
           }
            
         }
   else if(Browser_Agent.indexOf("Opera")!=-1){
                 Actual_Name="Opera";
                var tempstart=Browser_Agent.indexOf("Opera");
                var tempend=Browser_Agent.length;
                 Actual_Version=Browser_Version;
             }
        else{
             Actual_Name="Unknown Navigator"
             Actual_Version="Unknown Version"
         }
         navigator.Actual_Name=Actual_Name;
         navigator.Actual_Version=Actual_Version;
        this.Name=Actual_Name;
        this.Version=Actual_Version;
     }
     /**
 * 验证table数据重复行
 * @param tableId table ID      默认jsontb
 * @param args 数据列名称   例如：['aa', 'bb', 'cc']
 * @param trColor 重复数据单元格背景颜色
 */
function dataColumnsValidation(tableId, args, trColor) {
    //modify by zhuxw
    trColor="#F5C2F3";
    
    if(tableId == null) {
        tableId = "jsontb";
    }
    if(trColor == null) {
        trColor = "yellow";
    }
    var checkBox = $("#"+tableId+" tr:gt(0) input:checked");
    $("#"+tableId+" tr:gt(0) td").css("background-color", "#fff");//移除重复行样式
    for(var a = 0; a < args.length; a ++) {
	    var myTr = checkBox.parent("td").parent("tr");
	    var names = myTr.find("input:text[name='"+args[a]+"']");
	    
	    for(var i = 0; i < names.length; i++) {
	        var aValue = names[i].value;
		    var breakFlag = false;//退出循环标识
	        for(var j = names.length - 1; j > i; j --) {
	            var bValue = names[j].value
	            if(aValue == bValue && aValue!=null && aValue!='') {
	                //为重复数据行加颜色标注
	                $(names[i]).parent().css("background-color", trColor);
	                $(names[j]).parent().css("background-color", trColor);
	                var aa = $(names[j]).parent();
	                //alert(aa.css("background-color"));
	                breakFlag = true;
	            }
	        }
		    if(breakFlag) {
		        parent.showErrorMsg("数据重复，保存失败！");
		        return false;
		    }
	    }
    }
    return true;
}


//是否存在指定函数 
function isExitsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}

//add by zhuxw 通用导出功能
function doCommExport(exportId){
    $.ajax({
		url:"../system/customQuery.shtml?action=getCommExportRptInfo&rpt_Id="+exportId,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var data  = jsonResult.data;
				var logic_name=data.LOGICNAME;
				var physic_name=data.PHYSICNAME;
				showExporConf(logic_name,physic_name,"",exportId);
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	}
function showExporConf(logic_name,physic_name,tableId,exportId){
  if($("#expConf")!=null)
  {
    $("#expConf").remove();
  }
  var pagediv = "<div id='expConf' style='background:#e0edf6;width:100%;height:100%;z-index:999;position:absolute;top:0;margin-top:70px;filter:alpha(opacity=90);'></div>"; 
  $(document.body).append(pagediv);
 
  var table_conf=' <form id="expForm" action="#" method="post" name="expForm"><table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">';
  table_conf+='<tr><td colspan="10" align="center"  style="font-size:15" >';
  table_conf+=" <b>导出字段设置</b> ";
  table_conf+='</td></tr>';
  if(tableId==null||tableId=="")
  tableId="ordertb";
  var num=0;
  var dbNameArr="allDbName";
  var cssStr="";
 if(navigator.Actual_Name=='Chrome')
   {
	   cssStr="table-cell";
   }else if(navigator.Actual_Name=='Safari')
   {
	  cssStr="table-cell";
    }else{
	  cssStr="block";
   }
   var names= logic_name.split(",");
   var ids= physic_name.split(",");
    
   for(  i=0;i<ids.length;i++)
   {
	        var idstr=ids[i];
	        var valstr=names[i]
	        
	        //var disstr=$(this).css("display");
	        var disstr="";
		    if(idstr!=null&&idstr!="")
		    { 
		       if(num%4==0)
		       {
		         table_conf+='<BR><tr>';
		       }
		        table_conf+='<td width="8%" nowrap align="right">'+valstr+'</td>';
                table_conf+='<td width="15%">';
                if(disstr==cssStr)
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  checked name="'+idstr+'box" id='+idstr+'box   value="'+idstr+'"/>';
                else
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  name="'+idstr+'box" id='+idstr+'box  value="'+idstr+'"/>';
                table_conf+='</td>';
		        num+=1; 
		        dbNameArr=dbNameArr+","+idstr; 
		    }
		 
	 }
	table_conf+='<tr><td colspan="10" align="center"  valign="middle" >';
	table_conf+="<input id='btn_ok1' type='button' class='btn' value='导出excel'>&nbsp;&nbsp;";
    table_conf+="<input id='btn_closed2' type='button' class='btn' value='关 闭' >&nbsp;&nbsp;";
    table_conf+="<input id='btn_clear3' type='button' class='btn' value='清除设置'>&nbsp;&nbsp;";
    table_conf+='</td></tr></table></form>'; 
    $("#expConf").append(table_conf);
    $("#btn_ok1").click(function(){
		doPageExport(exportId);
		$("#expConf").remove(); 
	 });
	 $("#btn_closed2").click(function(){
		 $("#expConf").remove();
	 });
	 $("#btn_clear3").click(function(){
		 clearPersonal(dbNameArr,tableId);
	 });
	 
}
function doPageExport(exportId){
 var displayArr= new Array();
 var hiddenArr= new Array();
  var pageInp=$("#expConf input:checkbox");
  var expCols="";
  var keyvake="keyvalue";
   $.each(pageInp,function(i,k)
    {
      if(this.checked)
      {
       // displayArr.push($(this).val());
       // $.cookie(keyvalue+"_"+$(this).val(),'1');
        expCols=expCols+",";
      }else
      {
       // hiddenArr.push($(this).val());
       // $.cookie(keyvalue+"_"+$(this).val(),'0');
      }
    }
   )
   $("#expForm").attr("action","../system/customQuery.shtml?action=commExportDataExl&rpt_Id="+exportId+"&expCols="+expCols);
   $("#expForm").submit();
 }

/**
 * 含有默认值的下拉选择 add by zzb 2014-12-24
 * @param {Object} selectId 下拉框ID
 * @param {Object} tableId  配置文件ID
 * @param {Object} value    实际值
 * @param {Object} defaultValue 默认值
 */
function SelDictShow_Default(selectId,tableId,value,defaultValue){
	if(null == value || "" == value){
		value = defaultValue;
	}
	SelDictShow(selectId,tableId,value,null,null)
}



var N = [
    "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
];
/**
 * 1234567890转换一二三四五六七八九十
 * @param {Object} num
 * @return {TypeName} 
 */
function convertToChinese(num){
    var str = num.toString();
    var len = num.toString().length;
    var C_Num = [];
    for(var i = 0; i < len; i++){
        C_Num.push(N[str.charAt(i)]);
    }
    return C_Num.join('');
}