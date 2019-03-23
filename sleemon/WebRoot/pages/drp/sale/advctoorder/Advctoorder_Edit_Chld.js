

/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //form校验设置
	InitFormValidator(0);
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();
	});
	//查看库存
	$("#select").click(function () {
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		window.open('advctoorder.shtml?action=toListPro&PRD_IDS='+ids,'库存','height=200, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
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
		checkBox.parent().parent().remove();
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#create").click(function(){
			$("#create").attr("disabled","disabled");
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				$("#create").removeAttr("disabled");
				return;
				}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				$("#create").removeAttr("disabled");
				return;
			}
			if(!checkNum()){
				$("#create").removeAttr("disabled");
				return;
			}
//			//检查之前预订单录入时有特殊工艺的但是没渠道折扣率的明细
//			if(!checkPrice()){
//				return;
//			}
			childSave();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		var tds=$("#jsontb").find("tr").find("td");
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
			for(var j=0;j<tds.length;j++){
				tds[j].style.background = "yellow";
			}
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
			for(var j=0;j<tds.length;j++){
				tds[j].style.background = "white";
			}
		}
	});
//	var PrdNos=$("#PrdNos").val();
//	if(""!=PrdNos&&null!=PrdNos){
//		parent.showErrorMsg("编号为"+PrdNos+"的货品没有设置渠道折扣率，不予显示");
//	}
	var error =$("#error").val();
	if(""!=error&&null!=error){
		alert("总部未设置您的货品价格，请联系总部！！");
		window.close();
	}
});

//子表保存
function childSave(){
	var jsonData = multiPackJsonData();
	var ADVC_ORDER_IDS=$("#ADVC_ORDER_IDS").val();
	$.ajax({
		url: "advctoorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_ORDER_IDS":ADVC_ORDER_IDS},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("加入成功");
				$("#pageForm").attr("action","advctoorder.shtml?action=toChild&ADVC_ORDER_IDS="+utf8(ADVC_ORDER_IDS))
				window.opener.parent.topcontent.listRef();
				window.close();
				//$("#pageForm").submit();
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var tmp_DECT_RATE=arrs[9];
	//处理 0.XX变成 .xx 的问题
	var DECT_RATE = "";
	if(tmp_DECT_RATE.substring(0,1)=='.'){
		DECT_RATE="0"+tmp_DECT_RATE
	}else{
		DECT_RATE=tmp_DECT_RATE
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(arrs[7]==null||arrs[7]==""||arrs[7]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<font style='color: red'>有</font><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />";
		
	}
	$("#jsontb tr:last-child").after("<tr id='tr"+rownum+"' class='list_row"+classrow+"' onclick='upColor("+rownum+")' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' onclick='upColor("+rownum+")' style='height:12px;valign:middle' id='PRD_ID"+rownum+"' json='PRD_ID' name='PRD_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO"  id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" style="width:200px" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="PRD_COLOR" style="width:50px" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  label="花号"  type="text"    value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" style="width:40px" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
//            .append('<td nowrap align="left"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> <input class="btn"  value="编辑" onclick="url('+rownum+')"  type="button" />&nbsp;</td>')
            .append('<td nowrap align="left"><span id="SPECIAL_FLAG'+rownum+'" >'+SPCL_TECH_FLAG+'</span> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价"   type="text"      readonly   style="width:60px"    value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率" style="width:30px"   type="text"   inputtype="float"   readonly  mustinput="true"   valueType="2,2"   value="'+DECT_RATE+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价"  type="text" mustinput="true" style="width:60px"    inputtype="float"   readonly   valueType="8,2"   value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="temp_ORDER_NUM" id="temp_ORDER_NUM'+rownum+'" name="temp_ORDER_NUM"  autocheck="true" style="width:40px" label="订货数量" readonly type="text"   mustinput="true"   inputtype="float"        maxlength="22"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SEND_NUM" id="SEND_NUM'+rownum+'" name="SEND_NUM"  autocheck="true" label="已发货数量" style="width:50px" readonly type="text"   inputtype="float"  value="'+arrs[18]+'"  maxlength="22" value="0" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  id="num'+rownum+'" name="num"  style="width:50px" label="可用库存" readonly type="text" autocheck="true"    readonly  inputtype="float"  value="'+arrs[15]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="实际订货数量"  style="width:60px" type="text" mustinput="true"  onkeyup="countPRA_ORDER_NUM('+rownum+')"    maxlength="22" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PLE_REP" id="PLE_REP'+rownum+'" name="PLE_REP"  autocheck="true" label="抵库数量" style="width:50px" readonly type="text"   inputtype="float"    maxlength="22" value="0" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT'+rownum+'"  autocheck="true" label="订货金额" style="width:80px"  type="text" mustinput="true"    readonly inputtype="float"      valueType="12,2"  />&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE'+rownum+'"  autocheck="true" label="交货日期"  type="text"   inputtype="string"  style="width:80px"  mustinput="true"  readonly   value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"  style="width:80px"       maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRDSTATE" id="PRDSTATE'+rownum+'" name="PRDSTATE"  autocheck="true" label="货品状态"  type="text" readonly  inputtype="string"  size=3      maxlength="250"  value="'+arrs[19]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="COMM_FLAG" id="COMM_FLAG'+rownum+'" name="COMM_FLAG"  autocheck="true" label="货品类型"  type="text" readonly  inputtype="string"  size=3      maxlength="250"  value="'+arrs[20]+'"/>&nbsp;</td>')
            .append('<input  json="ADVC_ORDER_DTL_ID" id="ADVC_ORDER_DTL_ID'+rownum+'" name="ADVC_ORDER_DTL_ID'+rownum+'"  label="预订单明细ID" type="hidden" value="'+arrs[14]+'"/>')
            .append('<input  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE"  label="订货类型" type="hidden" value="'+arrs[21]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input   id="tempNum'+rownum+'" name="tempNum'+rownum+'"  label="临时存储可用库存" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input   id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺标记" type="hidden" value="'+arrs[7]+'"/>')
              ;
	countFill(rownum);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}

    //新增数据后计算订货金额，折后单价,实际订货数量
    function countFill(i){
    	var temp_PRICE=$("#PRICE"+i).val();//单价
    	var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
    	var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
    	var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
    	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
    	var temp_temp_ORDER_NUM=$("#temp_ORDER_NUM"+i).val();//订货数量
    	var temp_ORDER_NUM=isNaN(temp_temp_ORDER_NUM)?0:parseFloat(temp_temp_ORDER_NUM);//订货数量
    	if(temp_ORDER_NUM==""||temp_ORDER_NUM==null||isNaN(temp_ORDER_NUM)){
    		temp_ORDER_NUM=0;
    	}
    	var PRDSTATE=$("#PRDSTATE"+i).val();//货品状态
    	var COMM_FLAG=$("#COMM_FLAG"+i).val();//货品来源（总部/渠道）
    	if("停用"==PRDSTATE){
    		$("#DECT_PRICE"+i).val(DECT_PRICE);
    		$("#DECT_AMOUNT"+i).val("0");
    		$("#ORDER_NUM"+i).val("0");
    		$("#PLE_REP"+i).val(temp_ORDER_NUM);
    	}else{
    		var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*temp_ORDER_NUM)?0:DECT_PRICE*temp_ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
	    	$("#DECT_PRICE"+i).val(DECT_PRICE);
	    	$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
	    	$("#ORDER_NUM"+i).val(temp_ORDER_NUM);
    	}
    	if("渠道货品"==COMM_FLAG){
    		$("#PLE_REP"+i).val($("#temp_ORDER_NUM"+i).val());
    		$("#ORDER_NUM"+i).val("0");
    		$("#DECT_AMOUNT"+i).val("0");
    		$("#ORDER_NUM"+i).attr("readonly","readonly");
    	}
    }
    //当键盘按键松开时触发事件
	//输入抵库数量计算实际订货数量
	function countPRA_ORDER_NUM(i){
		$("#PRD_ID"+i).attr("checked","checked");
		var tds=$("#tr"+i).find("td");
		for(var j=0;j<tds.length;j++){
			tds[j].style.background = "yellow";
		}
		var temp_ORDER_NUM=$("#temp_ORDER_NUM"+i).val();//订货数量
		if(""==temp_ORDER_NUM||null==temp_ORDER_NUM){
			temp_ORDER_NUM=0;
		}
		temp_ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var ORDER_NUM=$("#ORDER_NUM"+i).val();//实际订货数量
		if(isNaN(ORDER_NUM)){
			return;
		}
		if(""==ORDER_NUM||null==ORDER_NUM){
			ORDER_NUM=0;
		}
		ORDER_NUM=isNaN(ORDER_NUM)?0:parseFloat(ORDER_NUM);//实际订货数量
		if(ORDER_NUM>temp_ORDER_NUM){
			parent.showErrorMsg("实际订货数量不能大于订货数量");
			$("#ORDER_NUM"+i).val("");
			$("#DECT_AMOUNT"+i).val("0");
			return;
		}
		var PLE_REP=Math.round((isNaN(temp_ORDER_NUM-ORDER_NUM)?0:temp_ORDER_NUM-ORDER_NUM),2);//抵库数量
		if(PLE_REP<0){
			PLE_REP=0;
		}
		$("#PLE_REP"+i).val(PLE_REP);
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
		$("#ORDER_NUM"+i).val(ORDER_NUM);
	}
	
	
	function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	}
	//2015-8-3 by zzb 改为不要编辑只能查看
	function url_(rownum){
		var PRD_ID=$("#PRD_ID"+rownum).val();
		var CHANN_ID=$("#CHANN_ID").val();
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var ADVC_ORDER_DTL_ID=$("#ADVC_ORDER_DTL_ID"+rownum).val();
		var PRICE=$("#PRICE"+rownum).val();
		var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="
										+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="
										+ADVC_ORDER_DTL_ID+"&citeUrl=editTechWithPrice&check=N&acqModel=1&filterSpclFlag=1&PRICE="+PRICE,
				 window,"dialogwidth=800px; dialogheight=600px; status=no");
		var DECT_PRICE=0;//折后单价
		if(null==data){
			return;
		}
		$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
		if(data.SPCL_TECH_FLAG!=0){
			$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span>");
		}else{
			$("#SPECIAL_FLAG"+rownum).html("无");
		}
		if(data.SPCL_TECH_FLAG>0){
			$("#num"+rownum).val("0");
		}else{
			$("#num"+rownum).val($("#tempNum"+rownum).val());
		}
		var PRICE = data.PRICE;//单价
		var DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
		$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
		$("#PRICE"+rownum).val(PRICE);//单价
		$("#DECT_PRICE"+rownum).val(DECT_PRICE);//折后单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+rownum).val();//订货数量
    	temp_ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
    	var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*temp_ORDER_NUM)?0:DECT_PRICE*temp_ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
    	$("#DECT_AMOUNT"+rownum).val(DECT_AMOUNT);
	}
function checkPrice(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag=true;
	checkBox.each(function(){
		//特殊工艺标记
		var temp_SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
		if(""==temp_SPCL_TECH_FLAG||null==temp_SPCL_TECH_FLAG){
			temp_SPCL_TECH_FLAG=0;
		}
		var SPCL_TECH_FLAG=isNaN(temp_SPCL_TECH_FLAG)?0:parseFloat(temp_SPCL_TECH_FLAG);
		//单价
		var temp_PRICE=$(this).parent().parent().find("input[name='PRICE']").val();
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);
		//折扣率
		var temp_DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();
		var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);
		//折后价
		var temp_DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);
		
		var PRD_NO=$(this).parent().parent().find("input[name='PRD_NO']").val();
		if(0<SPCL_TECH_FLAG&&(0==DECT_RATE||0==DECT_PRICE||0==PRICE)){
			parent.showErrorMsg("所选货品编号为"+PRD_NO+"的货品之前没有设置渠道折扣率，请点击该货品特殊工艺的编辑按钮再保存后加入购物车");
			flag=false;
			return false;
		}
	});
	return flag;
}
//验证单价，折扣率，折后单价，订货数量是否为0
	function checkNum(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		var flag=true;
		$("#jsontb tr:gt(0) td").css("background-color","#FFFFFF");
		checkBox.each(function(){
			var PRDSTATE=$(this).parent().parent().find("input[name='PRDSTATE']").val();//货品状态
			var PRD_TYPE=$(this).parent().parent().find("input[name='PRD_TYPE']").val();//订货类型
			var COMM_FLAG=$(this).parent().parent().find("input[name='COMM_FLAG']").val();
			var ORDER_NUM=$(this).parent().parent().find("input[name='ORDER_NUM']").val();//实际订货数量
			var PRD_NO=$(this).parent().parent().find("input[name='PRD_NO']").val();
			if(""==ORDER_NUM||null==ORDER_NUM){
				parent.showErrorMsg("请输入实际订货数量");
	            flag=false;
				return false;
			}
			var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re2.test(ORDER_NUM)){
	            parent.showErrorMsg("实际订货数量最多可输入8位正整数");
	            flag=false;
				return false;
	        }
	        if("停用"==PRDSTATE&&ORDER_NUM>0){
	        	parent.showErrorMsg("货品编号"+PRD_NO+"已停用，不能向总部订货");
	            flag=false;
				return false;
	        }
	        var num = $(this).parent().parent().find("input[name='num']").val();//库存数量
	        var PLE_REP = $(this).parent().parent().find("input[name='PLE_REP']").val();//抵库数量
	        num = parseInt(num);
	        PLE_REP = parseInt(PLE_REP);
//	         if("赠品"!=PRD_TYPE&&"渠道货品"!=COMM_FLAG){
//	        	 if(PLE_REP > num){
//		        	parent.showErrorMsg("抵库数量不能大于可用库存");
//		        	$(this).parent().parent().find("td").css("background-color","#E6B9B8");
//		        	flag=false;
//					return false;
//		        }
//	         }
	        var SEND_NUM=$(this).parent().parent().find("input[name='SEND_NUM']").val();//已发货数量
	        var temp_ORDER_NUM=$(this).parent().parent().find("input[name='temp_ORDER_NUM']").val();//总订货数量
	        SEND_NUM=isNaN(SEND_NUM)?0:parseFloat(SEND_NUM);
	        ORDER_NUM=isNaN(ORDER_NUM)?0:parseFloat(ORDER_NUM);
	        PLE_REP=isNaN(PLE_REP)?0:parseFloat(PLE_REP);
	        temp_ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);
	        var count= Math.round((isNaN(temp_ORDER_NUM-SEND_NUM)?0:temp_ORDER_NUM-SEND_NUM)*100)/100;
	        if(count<ORDER_NUM){
	        	parent.showErrorMsg("对不起，所选货品有超出最大订货数量！");
	        	$(this).parent().parent().find("td").css("background-color","#E6B9B8");
	        	flag=false;
				return false;
	        }
	        
		});
		return flag;
	}
	function upColor(i){
		var checked = $("#PRD_ID"+i).prop("checked");
		var tds=$("#tr"+i).find("td");
		if(checked==true){
			for(var j=0;j<tds.length;j++){
				tds[j].style.background = "yellow";
			}
		}else{
			for(var j=0;j<tds.length;j++){
				tds[j].style.background = "white";
			}
		}
	}