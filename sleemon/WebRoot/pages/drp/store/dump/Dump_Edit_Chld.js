/**
 * @prjName:喜临门营销平台
 * @fileName:Dump_Edit_Chld
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
		var outId = parent.topcontent.getOutStoreId();
		var inId =  parent.topcontent.getInStoreId();
		
		if(null ==outId ||""==outId){
			parent.showErrorMsg("请选择'转出库房编号'");
			return false;
		}
		if(null ==inId ||""==inId){
			parent.showErrorMsg("请选择'转入库房编号'");
			return false;
		}
	    addRow();
//	    hideAllTd();
	});
	
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if("" == ids){
			checkBox.parent().parent().remove();
		}else{
			parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//保存之前 验证转储数量
			var f = saveCheckDumpNum();
			if(!f){
				return;
			}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
		}
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});

//主子表保存
function allSave(){
	//主表form校验
	var f = parent.topcontent.formCheckedType();
	if(!f){
		return;
	}
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//保存之前 验证转储数量
	var f = saveCheckDumpNum();
	if(!f){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	//验证转储数量 是否为0
//	if(!checkDumpNumZero()){
//		return;
//	}
	
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "dump.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"DUMP_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","dump.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
//	if(!checkDumpNumZero()){
//		return;
//	}
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "dump.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"DUMP_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


//验证 转储数量
function checkDumpNumZero(){
	var flag = true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		var DUMP_NUM = $(this).parent().parent().find("input[json='DUMP_NUM']").val();
		if(0== DUMP_NUM || "0" == DUMP_NUM){
			parent.showErrorMsg("请输入'转储数量'");
			flag = false;
			return false;
		}
	});
	return flag;
	 
}
//删除操作
function multiRecDeletes(tag){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "dump.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DUMP_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				if(tag != 1){
					parent.showMsgPanel("删除成功");
				}
			}else{
				if(tag != 1){
				   parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		}
	});
}




function reltChildDelete(ids){
    var selRowId = getSelRowId();
	//获取所有选中的记录
    var inputs = $("#jsontb tr:gt(0) input:[json='RELT_BILL_DTL_ID']");
    var array = ids.split(",");
    var DUMP_DTL_IDs = "";
    for(var i=0;i<array.length;i++){
    	inputs.each(function(){
		    if($(this).val() == array[i]){
				var DUMP_DTL_ID = $(this).parent().parent().find("input[json='DUMP_DTL_ID']").val();
				if(null != DUMP_DTL_ID && "" != DUMP_DTL_ID){
					DUMP_DTL_IDs = DUMP_DTL_IDs +"'"+DUMP_DTL_ID+"',";
				}
				$(this).parent().parent().remove();
			}
		});
    }
    if("" != DUMP_DTL_IDs){
    	DUMP_DTL_IDs = DUMP_DTL_IDs.substr(0,DUMP_DTL_IDs.length-1);
    }
    //后台保存方法已经做了处理不需要写删除了
	return;
	$.ajax({
		url: "dump.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DUMP_DTL_IDS":DUMP_DTL_IDs},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				if(tag != 1){
					parent.showMsgPanel("删除成功");
				}
			}else{
				if(tag != 1){
				   parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		}
	});
}


//返回
function gobacknew()
{
   newGoBack("dump.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(null == arrs[17] || "" == arrs[17]){
		SPCL_TECH_FLAG = '无';
	}else{
		SPCL_TECH_FLAG = "<span style='color: red'>有</span><input  class='btn' value='查看' onclick='showTechPage("+rownum+")'  type='button' />"
	}
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='checkbox"+rownum+"' json='DUMP_DTL_ID' name='DUMP_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" type="hidden"      value="'+arrs[1]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号" size="15" type="text"   inputtype="string"     mustinput="true" readonly    maxlength="30"  value="'+arrs[2]+'"/>&nbsp;' +
            '<img align="absmiddle" name="selPRD" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selPrd('+rownum+')"></td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" size="15" type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" size="12" type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  size="8" type="text"   inputtype="string"        readonly  maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"  size="5"  inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="3" type="text"   inputtype="string"     mustinput="true" readonly    maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center" label="特殊工艺"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
//            .append('<td nowrap align="left" name="OUT_STORG_NO" ><input  json="DUMP_OUT_STORG_ID" id="DUMP_OUT_STORG_ID'+rownum+'" name="DUMP_OUT_STORG_ID'+rownum+'" label="转出库位ID" type="hidden"  value="'+arrs[8]+'"/>' +
//            '<input  json="DUMP_OUT_STORG_NO" id="DUMP_OUT_STORG_NO'+rownum+'" name="DUMP_OUT_STORG_NO'+rownum+'"  autocheck="true" label="转出库位编号"  type="text"   inputtype="string"     mustinput="true"   readonly  maxlength="30"  value="'+arrs[9]+'"/>&nbsp;' +
//            '<img align="absmiddle" name="selPRD" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selOutStore('+rownum+')"> </td>')
//            .append('<td nowrap align="left" name="OUT_STORG_NAME" ><input  json="DUMP_OUT_STORG_NAME" id="DUMP_OUT_STORG_NAME'+rownum+'" name="DUMP_OUT_STORG_NAME'+rownum+'"  autocheck="true" label="转出库位名称"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="100"  value="'+arrs[10]+'"/>&nbsp;</td>')
//            .append('<td nowrap align="left" name="IN_STORG_NO" ><input  json="DUMP_IN_STORG_ID" id="DUMP_IN_STORG_ID'+rownum+'" name="DUMP_IN_STORG_ID'+rownum+'"   label="转入库位ID" type="hidden"  value="'+arrs[11]+'"/>' +
//            '<input  json="DUMP_IN_STORG_NO" id="DUMP_IN_STORG_NO'+rownum+'" name="DUMP_IN_STORG_NO'+rownum+'"  autocheck="true" label="转入库位编号"  type="text"   inputtype="string"     mustinput="true"   readonly  maxlength="30"  value="'+arrs[12]+'"/>&nbsp;' +
//            '<img align="absmiddle" name="selPRD" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selInStore('+rownum+')"></td>')
//            .append('<td nowrap align="left" name="IN_STORG_NAME" ><input  json="DUMP_IN_STORG_NAME" id="DUMP_IN_STORG_NAME'+rownum+'" name="DUMP_IN_STORG_NAME'+rownum+'"  autocheck="true" label="转入库位名称"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="100"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DUMP_NUM" id="DUMP_NUM'+rownum+'" name="DUMP_NUM'+rownum+'"  autocheck="true" label="转储数量" size="8" onkeyup="checkDumpNum('+rownum+')"  type="text"   inputtype="float"  valueType="8,2"   mustinput="true"     maxlength="8"  value="'+arrs[14]+'"/>&nbsp;<input type="hidden"  label="转出库房的库存量" name="SAFE_NUM" id="SAFE_NUM'+rownum+'" value="'+arrs[18]+'" /> </td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[15]+'"/>&nbsp;' +
            '<input type="hidden" id="RELT_BILL_DTL_ID'+rownum+'" name="RELT_BILL_DTL_ID" json="RELT_BILL_DTL_ID" value="'+arrs[19]+'"/></td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺单标记" type="hidden" value="'+arrs[17]+'"/>');
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	
	//如果　转出库位管理标记　为否　隐掉转出库位编号，名称。
	//如果　转入库位管理标记　为否，隐掉转入库位编号，名称。
//	isHide(rownum);  
	
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


function selPrd(rownum){
	var outStoreId = parent.topcontent.getOutStoreId();
	if("" == outStoreId || null == outStoreId){
		 parent.showErrorMsg("请选择调出库房编号");
		 return;
	}
	var ZTXXID = $("#ZTXXID").val();
	var PRD_ID_OLD = $("#PRD_ID"+rownum).val();
	$("#selParam").val("  STORE_ID='"+outStoreId+"' and  STOLEDGER='"+ZTXXID+"' and (COMM_FLAG=1 or ( COMM_FLAG=0 and PROLEDGER='"+ZTXXID+"')) ");
	selCommon("BS_71", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",STD_UNIT"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum+",SAFE_NUM"+rownum, "PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,PRD_COLOR,BRAND,SPCL_TECH_ID,SPCL_TECH_FLAG,SAFE_NUM","selParam");
	var SPCL_TECH_FLAG = $("#SPCL_TECH_FLAG"+rownum).val();
	$("#DUMP_NUM"+rownum).val("");
    if(SPCL_TECH_FLAG > 0){
	   var SPCL_TECH = "<span style='color: red'>有</span><input class='btn'  value='查看' onclick='showTechPage("+rownum+")'  type='button' />"
	   $("#SPECIAL_FLAG"+rownum).html(SPCL_TECH);
    }else{
	   $("#SPECIAL_FLAG"+rownum).html("无");
    }
}

//填写转储数量的 时候 验证
function checkDumpNum(rownum){
	var dumpNum = $("#DUMP_NUM"+rownum).val();
	var SAFE_NUM = $("#SAFE_NUM"+rownum).val();
	SAFE_NUM = parseFloat(SAFE_NUM);
	if(isNaN(SAFE_NUM)){
		SAFE_NUM = 0;
	}
//	if(parseInt(dumpNum)>SAFE_NUM){
//		parent.showErrorMsg("转储数量不能大于该库的库存量");
//		return;
//	}
	
}

//保存之前验证转储数量
function saveCheckDumpNum(){
	var flag = true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var beforeV = "";
	checkBox.each(function(){
		var dumpNum = $(this).parent().parent().find("input[json='DUMP_NUM']").val();
		var SAFE_NUM = $(this).parent().parent().find("input[name='SAFE_NUM']").val();
		var PRD_NO = $(this).parent().parent().find("input[json='PRD_NO']").val();
		var PRD_NAME = $(this).parent().parent().find("input[json='PRD_NAME']").val();
		
//		//转出库位管理标记
//		var OutFlag = parent.topcontent.getOutFlag();
//		//转入库位管理标记
//		var InFlag = parent.topcontent.getInFlag();
//		
//		if("1" == InFlag || 1 == InFlag){
//			beforeV = $(this).parent().parent().find("input[json='DUMP_IN_STORG_NAME']").val();
//		}else if("1" == OutFlag || 1 == OutFlag){
//			beforeV = $(this).parent().parent().find("input[json='DUMP_OUT_STORG_NAME']").val();
//		}else{
//			beforeV = $(this).parent().parent().find("input[json='STD_UNIT']").val();
//		}
//		
//		if(null == beforeV || "" == beforeV){
//			return false;
//		}
	    dumpNum = parseFloat(dumpNum);
	    if(0 == dumpNum){
	    	parent.showErrorMsg("请输入'转储数量'");
			flag = false;
			return false;
	    }
	    SAFE_NUM = parseFloat(SAFE_NUM);
		if(isNaN(SAFE_NUM)){
			SAFE_NUM = 0;
		}
		if(parseFloat(dumpNum)>parseFloat(SAFE_NUM)){
			parent.showErrorMsg("货品["+PRD_NO+"]["+PRD_NAME+"] </br>转储数量不能大于该库的库存量</br>库存量：["+SAFE_NUM+"]");
			flag = false;
			return false;
		}
	});
 	return flag;
}


function selOutStore(rownum){
	var outStoreId = parent.topcontent.getOutStoreId();
	$("#selectOutStoreParams").val(" STATE='启用' and DEL_FLAG=0 and STORE_ID='"+outStoreId+"'");
	selCommon('BS_42', false, 'DUMP_OUT_STORG_ID'+rownum, 'STORG_ID', 'forms[0]','DUMP_OUT_STORG_NO'+rownum+',DUMP_OUT_STORG_NAME'+rownum, 'STORG_NO,STORG_NAME', 'selectOutStoreParams')
}

function selInStore(rownum){
   var inStoreId = parent.topcontent.getInStoreId();
   $("#selectInStoreParams").val(" STATE='启用' and DEL_FLAG=0 and STORE_ID='"+inStoreId+"'");
   selCommon('BS_42', false, 'DUMP_IN_STORG_ID'+rownum, 'STORG_ID', 'forms[0]','DUMP_IN_STORG_NO'+rownum+',DUMP_IN_STORG_NAME'+rownum, 'STORG_NO,STORG_NAME', 'selectInStoreParams')
}


var outHiTh = '0';
var inHiTh = '0';
function isHide(rownum){
 
	//如果　转出库位管理标记　为否　隐掉转出库位编号，名称。
	//按照目前的列数 影藏，如果增加一列或者减少一列 要核对该值
	var outFlag = parent.topcontent.getOutFlag();
	if('0'==outFlag){
	  if(outHiTh == '0'){
		  hiddenOUTTh(true);
		  outHiTh = '1';
	  }
	  $("#jsontb tr:gt(0) td[name='OUT_STORG_NO']").hide();  
	  $("#jsontb tr:gt(0) td[name='OUT_STORG_NAME']").hide();
 
	  $("#jsontb tr:gt(0) td[name='OUT_STORG_NO']").find(":input").attr("mustinput","false");
	  $("#jsontb tr:gt(0) td[name='OUT_STORG_NAME']").find(":input").attr("mustinput","false");
	}
	
	//如果　转入库位管理标记　为否，隐掉转入库位编号，名称。
	var inFlag = parent.topcontent.getInFlag();
	if('0'==inFlag){
	  if(inHiTh == '0'){
		  hiddenINTh(true); 
		  inHiTh = '1';
	  }
	  $("#jsontb tr:gt(0) td[name='IN_STORG_NO']").hide();
	  $("#jsontb tr:gt(0) td[name='IN_STORG_NAME']").hide();
	  $("#jsontb tr:gt(0) td[name='IN_STORG_NO']").find(":input").attr("mustinput","false");
	  $("#jsontb tr:gt(0) td[name='IN_STORG_NAME']").find(":input").attr("mustinput","false");
	}
 }
 
  //隐藏转出库位标题
  function hiddenOUTTh(isHidden){
	  if(isHidden){
		  $("#jsontb th[name='OUT_STORG_NO']").hide(); 
          $("#jsontb th[name='OUT_STORG_NAME']").hide(); 
	  }else{
		  $("#jsontb th[name='OUT_STORG_NO']").show(); 
          $("#jsontb th[name='OUT_STORG_NAME']").show(); 
	  }
	
  }
  //隐藏转入库位标题
  function hiddenINTh(isHidden){
	  if(isHidden){
		  $("#jsontb th[name='IN_STORG_NO']").hide(); 
	      $("#jsontb th[name='IN_STORG_NAME']").hide();
	  }else{
		  $("#jsontb th[name='IN_STORG_NO']").show(); 
	      $("#jsontb th[name='IN_STORG_NAME']").show();
	  }
    
  }
  //上帧 库房发生改变的时候 改变下帧的库位
  function hideAllTd(){
		var outFlag = parent.topcontent.getOutFlag();
		if('0' == outFlag){
			//隐藏标题
			hiddenOUTTh(true);
			//隐藏列
		    $("#jsontb tr").each(function(){
		    	$(this).find("td[name='OUT_STORG_NO']").hide();
		    	$(this).find("td[name='OUT_STORG_NAME']").hide();
		    });
		    //清除input的value
		    clearOutKUWEIval();
		    
		}
		if('1' == outFlag){
			//显示
			hiddenOUTTh(false);
			//显示列
		    $("#jsontb tr").each(function(){
		    	$(this).find("td[name='OUT_STORG_NO']").show().find(":input").attr("mustinput","true");
		    	$(this).find("td[name='OUT_STORG_NAME']").show().find(":input").attr("mustinput","true");
		    	//form校验设置
	            trCheckInit($(this).find("input"));
		    });
		}
		 
		//转入库位 隐藏
		var inFlag = parent.topcontent.getInFlag();
		if('0' == inFlag){
			//隐藏
			hiddenINTh(true);
			//隐藏列
			$("#jsontb tr").each(function(){
		    	$(this).find("td[name='IN_STORG_NO']").hide();
		    	$(this).find("td[name='IN_STORG_NAME']").hide();
		    });
			//清除input的value
			clearInKUWEIval();
		}
		if('1' == inFlag){
			//显示
			hiddenINTh(false);
		    $("#jsontb tr").each(function(){
		    	$(this).find("td[name='IN_STORG_NO']").show().find(":input").attr("mustinput","true");
		    	$(this).find("td[name='IN_STORG_NAME']").show().find(":input").attr("mustinput","true");
		    	//form校验设置
	            trCheckInit($(this).find("input"));
		    }); 
		}
		
	}
  
  
  //清除 转出库位的 value
  function clearOutKUWEIval(){
		$("#jsontb tr").each(function(){
	    	$(this).find("td[name='OUT_STORG_NO']").find(":input").val(null);
	    	$(this).find("td[name='OUT_STORG_NAME']").find(":input").val(null);
	   });
  }
  
  //清除 转入库位的 value
  function clearInKUWEIval(){
	  $("#jsontb tr").each(function(){
	    	$(this).find("td[name='IN_STORG_NO']").find(":input").val(null);
	    	$(this).find("td[name='IN_STORG_NAME']").find(":input").val(null);
	   });
  }

  //特殊工艺
function showTechPage(rownum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	var data = window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

//清除字表的数据
function clrarAllChild(){
	$("#jsontb :checkbox").attr("checked","true");
//	multiRecDeletes(1);
//	$("#jsontb tr:gt(0)").remove();
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}



