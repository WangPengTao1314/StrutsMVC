/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_Edit_Chld
 * @author zzb
 * @time   2013-08-31 11:40:47 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    //init();
	
	$("#add").click(function(){
	    addRow();
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
	
	$("#bussClose").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input[name='mx']:checked"); //加上name 防止点击其他的checkbox 也认为是选中
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要取消吗?","bottomcontent.closeFn();","N");
	});
	
	//拆单
	$("#split").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		 
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
	
			var no  = checkBox.parent().parent().find("input[name='PRD_NO']").val();
			var inputs = $("#jsontb").find("input[name='PRD_NO']");
			var count = 0;
			inputs.each(function(){
				var v = $(this).val();
				if(v == no){
					count++;
				}
			});
//			if(count>=2){
//				parent.showErrorMsg("单号'"+no+"'已拆单");
//				return false;
//			}
			 
			var jsonData = multiRadioJsonData();
			var data = eval("("+jsonData+")"); 
			var arrs; 
			 var PRD_NO = "";
			$.each(data,function(i,item){
				//alert("i= "+i+" item="+item);
				GOODS_ORDER_DTL_ID = item.GOODS_ORDER_DTL_ID;
				arrs = [
		           '',  item.PRD_ID,  item.PRD_NO, item.PRD_NAME, item.PRD_SPEC, item.PRD_COLOR, item.BRAND,
		           item.STD_UNIT, 
		           '',//备货
	               '',//非标
	               '',//特殊工艺要求
	               item.FACTORY_NAME,//生产工厂
	               '',//预计发货日期
	               item.PRICE, item.ORDER_NUM, item.ORDER_AMOUNT,
	               item.FACTORY_ID,//生产工厂ID
	               '',
	               '',
	               GOODS_ORDER_DTL_ID
	              
	            ];
			 }); 
			 addRow(arrs);
			 //加花号
			 var inputs = $("input[name='GOODS_ORDER_DTL_ID_OLD']");
			 backColor();
			 //var tds = $("#jsontb tr td[name='color_td']").css("background-color", "#F3F3F3");//#F3F3F3花号还原
			 inputs.each(function(){
				var v =  $(this).val();
				if(null != v && ""!= v && v==GOODS_ORDER_DTL_ID){
					 $(this).parent().css("background-color", "#F4E17E");//拆单的 单据加花号标注
				}
			 });
			 
		 
	});
	
   //全选   加 name='mx'过滤 checkbox 防止选取了其他的checkbox
   $("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb [name='mx']:checkbox").attr("checked","true");
		}else{
			$("#jsontb [name='mx']:checkbox").removeAttr("checked");
		}
	});
	
});


//取消 订货
function closeFn(){
	var GOODS_ORDER_ID =  getSelRowId();
	var resultSize = $("#resultSize").val();
	var NO = $.trim(parent.topcontent.$("#NO_"+GOODS_ORDER_ID).text());
	var ids = "";
	var prdNo = "";
	//明细的条数
	var mxSize = $("#jsontb tr:gt(0) input[name='mx']").length;
	//选中的明细
	var checkBox = $("#jsontb tr:gt(0) input[name='mx']:checked");
	var leg = checkBox.length;
	var isAll = false;
    if(mxSize == leg){
    	isAll = true;
    }
 
	checkBox.each(function(){
		var id = $(this).val();
		ids = ids+"'"+id+"',";
		prdNo = $(this).parent().parent().find("td[name='"+id+"']").text();
		prdNo = $.trim(prdNo);
	});
	ids = ids.substr(0,ids.length-1);
	var remark = "单据'"+NO+"'下，货品'"+prdNo+"'已取消订货";
	$.ajax({
	 	url: "sergoodsorder.shtml?action=orderClose",
		type:"POST",
		data:{"GOODS_ORDER_ID":GOODS_ORDER_ID,"mxIds":ids,"remark":remark,"isAll":isAll},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess("取消成功","sergoodsorder.shtml?action=toFrame");
				parent.showMsgPanel("取消成功");
				checkBox.parent().parent().remove();
				parent.topcontent.sendMessage(remark);
				countAmount();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var tds = $("#jsontb").find("td[json='ORDER_AMOUNT']");
	var total = 0;
	tds.each(function(){
		total = Number(total) + Number($.trim($(this).text()));
	});
	parent.topcontent.setTotalAmount(total);
}



function checkForm(){
	var flag = true;
	//明细提交时，可能会有多行，只有选中的才校验
	$("#jsontb").find("tr:gt(0)").each(function(){
		//if($(this).find("input:checked").length>0){
			var inputs = $(this).find("input:gt(0)");
			inputs.each(function(){
				if (!InputCheck(this)) {
					flag = false;
					return flag;
				}
			});
		//}
	});
	return flag;
 
}

/*
 *封装json串
 *
 *@param 
 *@return json格式字符串
 */
function getChildJsonData(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		
		var isEdit = $(this).find("input[type='input']");
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

 
 
//返回
function gobacknew()
{
   newGoBack("sergoodsorder.shtml?action=toFrame");
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
              ''
              
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
 
	var techChecked = "";
	if(1 == arrs[2]){
		techChecked = "checked";
	}
	
	var SPCL_TECH_TEXT;
	if(0 == arrs[17] || "0" == arrs[17] || "" == arrs[17] || null == arrs[17]){
		SPCL_TECH_TEXT='无';
	}else{
		var id = "'"+arrs[9]+"'";
		SPCL_TECH_TEXT='<font style="color: red">有</font>';
	}
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center' name='color_td' >" +
		    "<input type='hidden' json='GOODS_ORDER_DTL_ID' id='GOODS_ORDER_DTL_ID"+rownum+"' name='GOODS_ORDER_DTL_ID' value='"+arrs[0]+"'/>" +
		    "<input type='hidden' json='PRD_ID' id='PRD_ID"+rownum+"' name='PRD_ID' value='"+arrs[1]+"'/>" +
		    "<input type='hidden' name='PRICE' json='PRICE' id='PRICE"+rownum+"' label='单价' value='"+arrs[10]+"'/>" +
		    "<input type='hidden' name='DECT_RATE' json='DECT_RATE' id='DECT_RATE"+rownum+"' label='折扣率' value='"+arrs[12]+"'/>" +
		    "<input type='hidden' name='VOLUME' json='VOLUME' label='体积' value='"+arrs[16]+"'/>"+
		    "<input  type='checkbox' name='mx' value='"+arrs[0]+"' /></td>")
            .append('<td nowrap align="center"><input type="checkbox"  json="IS_NO_STAND_FLAG"  name="IS_NO_STAND_FLAG"  label="非标"  '+techChecked+' /></td>')
		    .append('<td nowrap align="center" name="'+arrs[3]+'" json="PRD_NO" label="货品编号">'+arrs[3]+'&nbsp;</td>')
            .append('<td nowrap align="left" name="PRD_NAME'+rownum+'" json="PRD_NAME"  label="货品名称" >'+arrs[4]+'&nbsp;</td>')
		    .append('<td nowrap align="center" name="PRD_SPEC'+rownum+'" json="PRD_SPEC"  label="规格型号" >'+arrs[5]+'&nbsp;</td>')
            .append('<td nowrap align="left" name="PRD_COLOR'+rownum+'" json="PRD_COLOR" label="花号">'+arrs[6]+'&nbsp;</td>')
            .append('<td nowrap align="center" name="BRAND'+rownum+'"  json="BRAND" label="品牌"  >'+arrs[7]+'&nbsp;</td>')
            .append('<td nowrap align="center" name="STD_UNIT'+rownum+'"  json="STD_UNIT" label="标准单位">'+arrs[8]+'&nbsp;</td>')
            .append('<td nowrap align="center"><span id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_TEXT+'</span><input type="button" value="编辑" class="btn" onclick="selectTechPage('+rownum+');"/><input type="hidden" json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="right"   name="DECT_PRICE'+rownum+'" id="DECT_PRICE'+rownum+'"  json="DECT_PRICE"  label="折后单价">'+arrs[13]+'&nbsp;</td>')
            .append('<td nowrap align="right" name="OLD_PRICE'+rownum+'" id="OLD_PRICE'+rownum+'"  json="OLD_PRICE" label="原单价"  >'+arrs[11]+'&nbsp;</td>')
            .append('<td nowrap align="right" name="ORDER_NUM'+rownum+'" id="ORDER_NUM'+rownum+'" json="ORDER_NUM"  label="订货数量">'+arrs[14]+'&nbsp;</td>')
            .append('<td nowrap align="right" name="ORDER_AMOUNT" id="ORDER_AMOUNT'+rownum+'" json="ORDER_AMOUNT"  label="金额">'+arrs[15]+'&nbsp;</td>')
            
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='radio']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	
	//form校验设置
	InitFormValidator(0);
}
/**
 * 判断拆单后的 N条单据的订货数量是否等于原始单据的订货数量
 * @return  
 */
function checkOrderNum(){                       
	var inputs = $("#jsontb").find("input[name='GOODS_ORDER_DTL_ID_OLD']");
	backColor();
	if(inputs.length>0){
		 for(var a = 0; a < inputs.length; a ++) {
			 var goodDelId = inputs[a].value;//明细ID
			 var PRD_NO = $("#"+inputs[a].id).parent().parent().find("input[name='PRD_NO']").eq(0).val();//货品编号
			 var numOldInputs = $("#jsontb").find("input[name='"+goodDelId+"']");//隐藏域的原始的 订货数量 
			 var numOldInputValue = numOldInputs.eq(0).val();
			 var sum = parseInt(0);
			 
			 //隐藏域的原始的 订货数量  与 订货数量 在同一列
			 numOldInputs.each(function(){
				 var num = $(this).parent().find("input[name='ORDER_NUM']").eq(0).val();
				 if(null !=num && ""!=num){
					 sum = sum + parseInt(num);
				 }
			 }); 
			  
			 if(numOldInputValue != sum){
				 var tds = $("#jsontb tr td[name='"+goodDelId+"']");
				 $("#jsontb tr td[name='"+goodDelId+"']").each(function(){
					 $(this).css("background-color", "#F4E17E");
					 $(this).parent().find("input[name='ORDER_NUM']").css("background-color", "#F4E17E");
				 });
				 //拆单之后订货数量之和应该等于该货品拆单之前的订货数量
				 parent.showErrorMsg("货品编号'"+PRD_NO+"'数量不匹配 \r\n 拆单之后订货数量之和应该等于该货品拆单之前的订货数量");
				 return false;
			 }
			 
		 }
	    return true;
	}
	
	
}

/**
 * 货品编号  订货数量 花号还原
 */
function backColor(){
	$("#jsontb tr td[name='color_td']").css("background-color", "#F3F3F3");
	$("#jsontb").find("input[name='PRD_NO']").parent().css("background-color", "#F3F3F3");//花号还原
	$("#jsontb").find("input[name='ORDER_NUM']").css("background-color", "")
}
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}   

function countTotal(rownum){
	var price = $("#PRICE"+rownum).text();
	if(null == price){
		return ;
	}
	price = $.trim(price);
	var amount = $("#ORDER_NUM"+rownum).val();
	if(null == amount){
		return ;
	}
	amount = $.trim(amount);
	var v = FloatMul(price,amount);
	$("#ORDER_AMOUNT"+rownum).text(v);
}

 
 
function selectShip(rownum){
	var shipId = parent.topcontent.getShipId();
	if(null == shipId || ""==shipId){
		parent.showErrorMsg("请选择发货点");
		return false;
	}
	  $("#selectParams").val(" STATE='启用' and DEL_FLAG=0 and SHIP_POINT_ID='"+shipId+"'");
	selCommon("BS_51", false, "FACTORY_ID"+rownum, "FACTORY_ID", "forms[0]","FACTORY_NAME"+rownum, "FACTORY_NAME", "selectParams");
}
//时间控件
function SelectThisTime(rownum){
	var n = "ADVC_SEND_DATE"+rownum;
//	var obj = $("#"+n);
//	obj.click();
	SelectDate(document.getElementById(n));
}

/**
 * 拼装 子表的json 
 * @param {Object} tableid 表ID
 * @param {Object} isAll true->无视是否选择 拼接所有
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function multiRadioJsonData(tableid,isAll){
	if(null == tableid){
		tableid = "jsontb";
	}
	if(null == isAll){
		isAll = false;
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = false;
		if(isAll){
			isEdit = true;
		}else{
			isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
			//isEdit = $(this).find("input[type='radio']:eq(0)").attr("checked");
		}
		if(isEdit){
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
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}

//子表保存
function childSave(){
	
	var selRowId = getSelRowId();
	var jsonData = multiRadioJsonData("jsontb",true);
	 
	$.ajax({
		url: "sergoodsorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"GOODS_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//查看特殊工艺
function selectTechPage(rownum){
    var PRD_ID=$("#PRD_ID"+rownum).val();
	var CHANN_ID=parent.topcontent.getORDER_CHANN_ID();
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
    var PRICE=$("#PRICE"+rownum).val();
    var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_GOODS_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
    if(null == data){
		return;
	}
	if(data.SPCL_TECH_FLAG!=0){
		$("#SPECIAL_FLAG"+rownum).html("<font style='color: red'>有</font>");
	}else{
		$("#SPECIAL_FLAG"+rownum).html("无");
	}
	$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	
	var PRICE = data.PRICE;//单价
	var DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
	$("#PRICE"+rownum).val(PRICE);//单价
	//$("#OLD_PRICE"+rownum).val(data.PRICE);//原价格
	$("#DECT_RATE"+rownum).val(DECT_RATE);//折扣率
	$("#DECT_PRICE"+rownum).text(DECT_PRICE);//折后单价
	var num = $.trim($("#ORDER_NUM"+rownum).text());
	var ORDER_AMOUNT = Number(DECT_PRICE)*Number(num);
	$("#ORDER_AMOUNT"+rownum).text(ORDER_AMOUNT);
	
	
    //查找当前是否有选中的记录
	var tds = $("#jsontb tr:gt(0) td[name='ORDER_AMOUNT']");
	var t = 0;
	tds.each(function(){
		 var v = $.trim($(this).text());
		 t = Number(t)+Number(v);
	});
	parent.topcontent.$("#TOTAL_AMOUNT").text(t);
	childSave();
}
