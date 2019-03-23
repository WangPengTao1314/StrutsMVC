/**
 * @prjName:喜临门营销平台
 * @fileName:整车计算
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
   
	$("#q_search").click(function () {
		 $("#queryForm").attr("action","carcalculate.shtml?action=listGoodsOrder");
		 $("#queryForm").submit();
	});
 
	
	$("#q_add").click(function () {
		  addRow();
	});
	
	//计算所需的车量
	countCar();
	
	//form校验设置
	InitFormValidator("sendForm");
	 
});
  


//table增加一行
 
function addRow(arrs){
	var state  =""
	if(null == arrs){
		arrs = ['','','','','','','','','','','','',''];//添加字段的时候必须添加
	}
	//上级数据项选取规则，新增时，选取该字典下任意节点，如果该字典下无明细，则不选
	//修改时，选取该字典下非自己的任一节点，不可与已有的数据项值重复。
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)' onclick='selRadio("+rownum+")' ></tr>");
	$("#jsontb tr:last-child")
    .append("<td nowrap align='center'><input type='radio' style='height:12px;valign:middle'  name='eid' id='eid_new"+rownum+"'/><input type='hidden' name='GOODS_ORDER_DTL_ID' json='GOODS_ORDER_DTL_ID' value='' /></td>")
    .append("<td nowrap align='center'><input type='hidden' id='GOODS_ORDER_ID"+rownum+"' json='GOODS_ORDER_ID'/><input typr='text' name='GOODS_ORDER_NO' id='GOODS_ORDER_NO"+rownum+"' label='订货订单编号' mustinput='true' inputtype='string' READONLY  autocheck='true' /><img align='absmiddle' name='selJGXX' style='cursor:hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selOrder("+rownum+")'/></td>")
    .append("<td nowrap align='center'><input typr='text' name='SEND_TYPE' value='新增' readonly /></td>")
    .append("<td nowrap align='center'><input type='hidden' id='PRD_ID"+rownum+"' json='PRD_ID'/> <input typr='text'id='PRD_NO"+rownum+"' name='PRD_NO' json='PRD_NO'  label='货品编号' mustinput='true' inputtype='string' READONLY  autocheck='true'/><img align='absmiddle' name='selJGXX' style='cursor:hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selPrd("+rownum+")'/></td>")
    .append("<td nowrap align='center'><input typr='text' id='PRD_NAME"+rownum+"' name='PRD_NAME' json='PRD_NAME' readonly/></td>")
    .append("<td nowrap align='center'><input typr='text' id='PRD_SPEC"+rownum+"' name='PRD_SPEC' json='PRD_SPEC' readonly /></td>")
    .append("<td nowrap align='center'><input typr='text' id='PRD_COLOR"+rownum+"' name='PRD_COLOR' json='PRD_COLOR' readonly /></td>")
    .append("<td nowrap align='center'><input typr='text' id='BRAND"+rownum+"' name='BRAND' json='BRAND'  readonly /></td>")
    .append("<td nowrap align='center'><input typr='text' id='STD_UNIT"+rownum+"' name='STD_UNIT' json='STD_UNIT' readonly /></td>")
    .append("<td nowrap align='center'><input typr='text' id='PRICE"+rownum+"' name='PRICE' json='PRICE' readonly /></td>")
    .append("<td nowrap align='center'><input typr='text' id='HAVE_NUM"+rownum+"' name='HAVE_NUM' readonly  /></td>")
    .append("<td nowrap align='center'><input typr='text' id='ADJUST_NUM"+rownum+"' name='ADJUST_NUM' json='ORDER_NUM'  mustinput='true' inputtype='int'  autocheck='true'  onchange='countCar();' /></td>")
    .append("<td nowrap align='center' name='cuur_VOLUME'><input typr='text' id='VOLUME"+rownum+"' name='VOLUME' json='VOLUME' readonly /></td>")
    .append("<td nowrap align='center'>" +
    "<input name='q_save' type='button' class='btn' value='保存' title='Alt+S' accesskey='S' onclick='savePrd(this);'><input name='q_delete' type='button' class='btn' value='删除' title='Alt+D' accesskey='D' onclick='deletePrd(this);'></td>")
	
	
	 
	//form校验设置
	InitFormValidator("sendForm");
}

function selOrder(rownum){
	selCommon('BS_56', false, 'GOODS_ORDER_ID'+rownum, 'GOODS_ORDER_ID', 'forms[1]','GOODS_ORDER_NO'+rownum, 'GOODS_ORDER_NO', 'selectOrder');
}

function selPrd(rownum){
	selCommon("BS_21", false, "PRD_ID"+rownum, "PRD_ID", "forms[1]","PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",VOLUME"+rownum, "PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,FACT_PRICE,VOLUME", "selectParams");
}

function selRadio(rownum){
	 $("#eid_new"+rownum).attr("checked",true);
}
function setSelEid(id){
	$("#"+id).attr("checked",true);
}

//下拉框选择的车型 从后台获取该车型的容积
function getVolum(obj){
	var carType = $(obj).val();
	$.ajax({
		url: "carcalculate.shtml?action=getVolum",
		type:"POST",
		dataType:"text",
		data:{"carType":carType},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var volum = jsonResult.data.VOLUME;
				 if(null == volum || ""==volum){
					 volum = 0;
				 }
				 $("#VOLUME").val(volum);
				 countCar();
			}else{
				 
			}
		}
	});
}
//计算车量
function countCar(){
	 var total = 0;
	 var old_VOLUME = $("#VOLUME").val();//下拉框选择的车型的容积
	 if(0==old_VOLUME || "0"==old_VOLUME||null==old_VOLUME ||""==old_VOLUME){
		 $("#car").text(0);
		 return;
	 }
	 
	 $("input[name='ADJUST_NUM']").each(function(){
		 var ADJUST_NUM = Number($.trim($(this).val()));
		 var VOLUME = Number($.trim($(this).parent().parent().find("td[name='cuur_VOLUME']").text()));
		 if(0==VOLUME || "0"==VOLUME){
			 VOLUME = Number($.trim($(this).parent().parent().find("input[name='VOLUME']").val()));
		 }
//		 alert("ADJUST_NUM="+ADJUST_NUM+"  VOLUME="+VOLUME);
		 
		 total = total + ADJUST_NUM*VOLUME;
		
		 
	 });
	 var car = Number(total/old_VOLUME);
	 car = car.toFixed(2)//保留2位小数
	 $("#car").text(car);
	  
}

//保存
function savePrd(obj){
	
	//对于选中的明细校验
	if(!formChecked_car(obj)){
		return;
	}
	 
	var obj = $(obj);
	var trs = obj.parent().parent();
	var childData = getData(trs);
	$.ajax({
		url: "carcalculate.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childData":childData},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel("保存成功");
				var GOODS_ORDER_DTL_ID = jsonResult.data.GOODS_ORDER_DTL_ID;
				trs.find("input[name='GOODS_ORDER_DTL_ID']").val(GOODS_ORDER_DTL_ID);
				$("#YT_MSG_BTN_OK").click(function(){
					$("#pageForm").submit();
				});
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
	countCar();
		
}

function formChecked_car(obj){
	var flag = true;
	var obj = $(obj);
	var inputs = obj.parent().parent().find("input");
	inputs.each(function(){
		if (!InputCheck(this)) {
			flag = false;
			return flag;
		}
	});
	return flag;
}
function deletePrd(obj){
	var GOODS_ORDER_DTL_ID = $(obj).parent().parent().find("input[name='GOODS_ORDER_DTL_ID']").val();
	$.ajax({
		url: "carcalculate.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_DTL_ID":GOODS_ORDER_DTL_ID},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel("删除成功");
				$(obj).parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getData(trs){
	var jsonData = "";
	trs.each(function(){
			jsonData = jsonData+"{";
			var isFirst = true;
//			var inputs = $(this).find(":input");
			var tds = $(this).find("td");
			tds.each(function(){
				var inputs = $(this).find(":input");
				if(inputs.length>0){
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
				}else{
					if(null != $(this).attr("json")){
						var k = $(this).attr("json");
					    var text = $.trim($(this).text());
					    jsonData = jsonData+ "'" + k + "':'" + text +"',";
				    }
				}
				
				
			});
			
		
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		 
	});
	if(jsonData.length>1){
		return jsonData.substr(0,jsonData.length-1);
	}
	return "{}";
}





