
/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		save();
	});
	
	$("#add").click(function(){
		addRow();
	});
		 
})

//保存
function save(){	
	var jsonData = packToJson();
	$.ajax({
		url: "distributerSalerpt.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 window.dialogArguments.parent.showMsgPanel("保存成功");
				 window.dialogArguments.parent.framePageInit("distributerSalerpt.shtml?action=toList");
			}else{
				window.dialogArguments.parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			window.close();			
		}
	});
}

//返回
function gobacknew(){   
	window.close();
}

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	//月报时间
	var YEAR = $("#YEAR").val();
	var MONTH = $("#MONTH").val();
	if("" == YEAR || "" == MONTH){
		 parent.showErrorMsg(utf8Decode("请选择月报时间！"));
	     return false;
	}
	
	return true;
}

//添加行
function addRow(arrs){
	var emptyLen = $("#empty").length;
	if(0 != emptyLen){
		$("#empty").remove();
	}
		
	if(null == arrs){
	var arrs = [				
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
	var rownum = $("#jsontbChild tr").length;		 
	var classrow = rownum% 2;
	$("#jsontbChild tr:last-child").after("<tr class='list_row"+classrow+"' ></tr>");
	$("#jsontbChild tr:last-child")
			.append('<td nowrap="nowrap" align="center" ><input type="hidden" json="DISTRIBUTOR_SALE_RPT_DTL_ID" name="DISTRIBUTOR_SALE_RPT_DTL_ID" value="'+arrs[0]+'">' +
					'<input type="hidden" name="DISTRIBUTOR_ID" json="DISTRIBUTOR_ID" id="DISTRIBUTOR_ID'+ rownum +'" value="'+arrs[1]+'"/>' +
					'<input json="DISTRIBUTOR_NO"  name="DISTRIBUTOR_NO" id="DISTRIBUTOR_NO'+ rownum +'" type="text" inputtype="string" autocheck="true" mustinput="true" maxlength="32" label="分销商编号" size="12"	value="'+arrs[2]+'" readonly />' +
					'<img align="absmiddle" style="cursor: hand" src="'+ ctxPath + '/styles/'+ theme +'/images/plus/select.gif" onClick="selDistributer('+ rownum +');return false"></td>')				
			.append('<td nowrap="nowrap" align="center" > <input type="text" json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME" id="DISTRIBUTOR_NAME'+ rownum +'" label="分销商名称" size="10"  value="'+arrs[3]+'" readonly /> </td>')
			.append('<td nowrap="nowrap" align="center" ><input type="text" json="DISTRIBUTOR_TYPE" name="DISTRIBUTOR_TYPE" id="DISTRIBUTOR_TYPE'+ rownum +'" label="分销类型" size="10"  value="'+arrs[4]+'" readonly /></td>')
			
			.append('<td nowrap="nowrap" align="center" >' +
					'<input type="hidden" name="PRD_ID" json="PRD_ID" id="PRD_ID'+ rownum +'" value="'+arrs[5]+'"/>' +
					'<input json="PRD_NO" name="PRD_NO" id="PRD_NO'+ rownum +'" type="text" autocheck="true" label="货品编号" size="10" inputtype="string" readonly mustinput="true" maxlength="50" value="'+arrs[6]+'"/>&nbsp;' +
					'<img align="absmiddle" style="cursor:hand" src="'+ ctxPath + '/styles/'+ theme +'/images/plus/select.gif" onclick="selProduct('+ rownum +')"/></td>')
			.append('<td nowrap="nowrap" align="center" ><input json="PRD_NAME" name="PRD_NAME" id="PRD_NAME'+ rownum +'" label="货品名称" type="text" size="10" readonly value="'+arrs[7]+'"/></td>')
			.append('<td nowrap="nowrap" align="center" ><input  json="PRD_SPEC" name="PRD_SPEC" id="PRD_SPEC'+ rownum +'" label="规格型号" type="text" size="10" readonly value="'+arrs[8]+'"/></td>')
			.append('<td nowrap="nowrap" align="center" ><input json="STOREOUT_NUM" name="STOREOUT_NUM" autocheck="true" label="提货数量" type="text" size="8" inputtype="int" mustinput="true" maxlength="10" value="'+arrs[9]+'"/></td>')
			.append('<td nowrap="nowrap" align="center" ><input json="STOREOUT_AMOUNT" name="STOREOUT_AMOUNT" autocheck="true" label="提货金额" type="text" size="8" inputtype="float" valueType="12,2" mustinput="true" value="'+arrs[10]+'"/></td>')
			
			.append('<td nowrap="nowrap" align="center" ><input name="delete" type="button" class="btn" onclick="delRow(this);" value="删除(D)" title="Alt+D" accesskey="D"></td>')
            ;	
	
	//form校验设置
	InitFormValidator(0);
}

//添加空行
function addEmptyRow(){
	var rowCount = $("#jsontbChild").find("tr:gt(0)").length;
	if(0 == rowCount){
		$("#jsontbChild tr:last-child").after("<tr id='empty'></tr>");
		$("#jsontbChild tr:last-child")
			.append('<td height="25" colspan="15" align="center" class="lst_empty">&nbsp;无相关记录&nbsp;</td>');
	}
}

//删除行
function delRow(obj){
	var currRow = $(obj).parent().parent();
	var currId = $(currRow).find("input[name='DISTRIBUTOR_SALE_RPT_DTL_ID']").val();
	if("" == currId){
		$(currRow).remove();
		addEmptyRow();
	}else{		
		parent.showConfirm("您确认要删除吗","topcontent.txDelete('"+ currRow[0].rowIndex +"');");		
	}		
}

//删除操作
function txDelete(rowNum){
	var selRowId = parent.document.getElementById("selRowId").value;
	
	//获取删除的记录
	var currRow = $("#jsontbChild tr:eq("+ Number(rowNum)+")");
	var ids = "'"+$(currRow).find("input[name='DISTRIBUTOR_SALE_RPT_DTL_ID']").val()+"'";
		
	$.ajax({
		url: "distributerSalerpt.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DISTRIBUTOR_SALE_RPT_DTL_IDS":ids,"DISTRIBUTOR_SALE_RPT_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				currRow.remove();
				addEmptyRow();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//封装主子表数据
function packToJson(mainTableid,childTableid){
	if(null == mainTableid){
		mainTableid = "jsontbMain";
	}
	
	if(null == childTableid){
		childTableid = "jsontbChild";
	}
	
	//主表
	var jsonData = siglePackJsonData(mainTableid);
	jsonData = jsonData.substr(0,jsonData.length-1);
	
	//子表
	var childData = packChildsToJson(childTableid);
	jsonData = jsonData + ",'childList':" + childData + "}";
	
	return 	jsonData;
}

//封装子列表
function packChildsToJson(tableid){
	var emptyLen = $("#empty").length;
	if(0 != emptyLen){
		return "[]";
	}
	
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		jsonData = jsonData+"{";
		var inputs = $(this).find(":input");
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
						value = $(this).attr("value");
					}
						var inputtype = $(this).attr("inputtype");
						jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
			}
		});
		jsonData = jsonData.substr(0,jsonData.length-1)+"},"
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}

/*
 *通用选取多选时，回显多行数据
 *
 *@param obj 通用选取返回的对象
 *@return ids 需要填入的字段id(不含行号)，多个以逗号隔开
 *@param i 行号
 */
function multiSelSet(obj,ids,i){
	//选中的记录
	var rowlist = [];
	rowlist.push(i);
	var len = obj[0] - (i-1);
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
		
		var existRowLen = $("#jsontbChild tr:last-child")[0].rowIndex;
		for(var j=0;j<len;j++){
			var index = i+j;
			if(index > existRowLen){
				addRow();
				existRowLen = $("#jsontbChild tr:last-child")[0].rowIndex;
			}
						
			rowlist.push(index);
			for(var m=0;m<idArr.length;m++){
				var v= valArr[m][j];
				if((null != v)&&("" != v)&&('undefined' != v)&&('NaN' != v)){
					$("#"+idArr[m]+index).val(v);
				}
			}
		}
	}
	return rowlist;
}

//选择分销商
function selDistributer(rowNum){
	var obj = selCommon('BS_188', true, 'DISTRIBUTOR_ID', 'DISTRIBUTOR_ID', 'forms[0]',
						'DISTRIBUTOR_ID'+ rowNum +',DISTRIBUTOR_NO'+ rowNum +',DISTRIBUTOR_NAME'+ rowNum +',DISTRIBUTOR_TYPE'+ rowNum, 
						'DISTRIBUTOR_ID,DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE', 
						'selectDistributer');
	multiSelSet(obj,"DISTRIBUTOR_ID,DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE",rowNum);	
}
//选择货品
function selProduct(rowNum){
	var obj = selCommon("BS_21", true, "PRD_ID"+rowNum, "PRD_ID", "forms[0]",
		"PRD_ID"+rowNum+",PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum,
		"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC","selectProduct");
	multiSelSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC",rowNum);	
}
