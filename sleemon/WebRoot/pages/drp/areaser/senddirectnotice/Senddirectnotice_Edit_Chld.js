


/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	$("#allot").click(function(){
		if(checkNum()){
			allot();
		}
	})
});

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
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
	var SPCL_TECH_FLAG;
	if(arrs[9]==null||arrs[9]==""||arrs[9]=="0"){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input   value='查看' class='btn' onclick='url("+rownum+")'  type='button' />"
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='SALE_ORDER_DTL_ID' id='SALE_ORDER_DTL_ID"+rownum+"' name='SALE_ORDER_DTL_ID' value='"+arrs[12]+"'/></td>")
		    .append('<td nowrap align="center"><input  json="SALE_ORDER_NO" id="SALE_ORDER_NO'+rownum+'" name="SALE_ORDER_NO"  inputtype="string"  autocheck="true" label="销售订单编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;</td>' )
		    .append('<td nowrap align="center"><input  json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME'+rownum+'" name="ORDER_CHANN_NAME"  inputtype="string"  autocheck="true" label="订货方名称"  type="text"  mustinput="true"   READONLY  value="'+arrs[3]+'"/>&nbsp;</td>' )
		    .append('<td nowrap align="center"><input  json="ORDER_DATE" id="ORDER_DATE'+rownum+'" name="ORDER_DATE"  inputtype="string" style="width:90px;"  autocheck="true" label="订货日期"  type="text"  mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;</td>' )
		    .append('<td nowrap align="center"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号" style="width:90px;"  type="text"  mustinput="true"   READONLY  value="'+arrs[4]+'"/>&nbsp;</td>' )
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称" style="width:120px;"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号" style="width:120px;"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="品牌"  type="text"   inputtype="string"  style="width:90px;" readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" style="width:50px;" type="text"   inputtype="float"  mustinput="true"    READONLY    maxlength="22"  value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SENDED_NUM" id="SENDED_NUM'+rownum+'" name="SENDED_NUM"  autocheck="true" label="已发货数量"  type="text" style="width:50px;"  inputtype="float"   mustinput="true"    READONLY    maxlength="22"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ALLOT_NUM" id="ALLOT_NUM'+rownum+'" name="ALLOT_NUM"  autocheck="true" label="分配数量" onkeyup="countNum('+rownum+')" type="text" style="width:50px;"  inputtype="float"     mustinput="true"        maxlength="22"  value="'+arrs[19]+'" />&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[8]+'"/>')
            .append('<input  json="SALE_ORDER_ID" id="SALE_ORDER_ID'+rownum+'" name="SALE_ORDER_ID"  label="销售订单ID" type="hidden" value="'+arrs[0]+'"/>')
            .append('<input  json="ORDER_CHANN_ID" id="ORDER_CHANN_ID'+rownum+'" name="ORDER_CHANN_ID"  label="订货方ID" type="hidden" value="'+arrs[13]+'"/>')
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID"  label="货品ID" type="hidden" value="'+arrs[14]+'"/>')
            .append('<input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"  label="货品颜色" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT"  label="标准单位" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="ORDER_CHANN_NO" id="ORDER_CHANN_NO'+rownum+'" name="ORDER_CHANN_NO"  label="订货方no" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="BASE_DELIVER_NOTICE_DTL_ID" id="BASE_DELIVER_NOTICE_DTL_ID'+rownum+'" name="BASE_DELIVER_NOTICE_DTL_ID"  label="总部直发通知单明细ID" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="BASE_DELIVER_NOTICE_ID" id="BASE_DELIVER_NOTICE_ID'+rownum+'" name="BASE_DELIVER_NOTICE_ID"  label="总部直发通知单ID" type="hidden" value="'+arrs[20]+'"/>')
            .append('<input  json="RECV_CHANN_ID" id="RECV_CHANN_ID'+rownum+'" name="RECV_CHANN_ID"  label="收货方ID" type="hidden" value="'+arrs[21]+'"/>')
            .append('<input  json="RECV_CHANN_NO" id="RECV_CHANN_NO'+rownum+'" name="RECV_CHANN_NO"  label="收货方no" type="hidden" value="'+arrs[22]+'"/>')
            .append('<input  json="RECV_CHANN_NAME" id="RECV_CHANN_NAME'+rownum+'" name="RECV_CHANN_NAME"  label="收货方name" type="hidden" value="'+arrs[23]+'"/>')
            .append('<input  json="RECV_ADDR_NO" id="RECV_ADDR_NO'+rownum+'" name="RECV_ADDR_NO"  label="收货地址编号" type="hidden" value="'+arrs[24]+'"/>')
            .append('<input  json="RECV_ADDR" id="RECV_ADDR'+rownum+'" name="RECV_ADDR"  label="收货地址" type="hidden" value="'+arrs[25]+'"/>')
            .append('<input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  label="折后单价" type="hidden" value="'+arrs[26]+'"/>')
            .append('<input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  label="单价" type="hidden" value="'+arrs[27]+'"/>')
            .append('<input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  label="折后单价" type="hidden" value="'+arrs[28]+'"/>')
              ;
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0); 
}
	function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}
	//分配数量输入时
	function countNum(id){
		//订货数量
		var temp_ORDER_NUM=$("#ORDER_NUM"+id).val();
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);
		//已发货数量
		var temp_SENDED_NUM=$("#SENDED_NUM"+id).val();
		var SENDED_NUM=isNaN(temp_SENDED_NUM)?0:parseFloat(temp_SENDED_NUM);
		//分配数量
		var temp_ALLOT_NUM=$("#ALLOT_NUM"+id).val();
		if(isNaN(temp_ALLOT_NUM)){
			alert("分配数量必须为数字");
			$("#ALLOT_NUM"+id).val("0");
			countTotalNum(id);
			return;
		}
		var ALLOT_NUM=isNaN(temp_ALLOT_NUM)?0:parseFloat(temp_ALLOT_NUM);
		if(ALLOT_NUM>(ORDER_NUM-SENDED_NUM)){
			alert("分配数量不能大于订货数量减已发货数量");
			$("#ALLOT_NUM"+id).val("0");
			countTotalNum(id);
			return;
		}
		countTotalNum(id);
	}
	//计算剩余数量
	function countTotalNum(id){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		//已分配数量
		var totalNum=0;
		checkBox.each(function(){
			var ALLOT_NUM_temp=$(this).parent().parent().find("input[name='ALLOT_NUM']").val();
			if(null!=ALLOT_NUM_temp&&""!=ALLOT_NUM_temp){
				var num=isNaN(ALLOT_NUM_temp)?0:parseFloat(ALLOT_NUM_temp);
				totalNum=totalNum+num;
			}
		})
		//总发货数量
		var REAL_SEND_NUM_temp=$("#REAL_SEND_NUM").val();
		var REAL_SEND_NUM=isNaN(REAL_SEND_NUM_temp)?0:parseFloat(REAL_SEND_NUM_temp);
		var subNum=REAL_SEND_NUM-totalNum;
		if(subNum<0){
			alert("剩余分配数量不足，请从新分配");
			if(""==id||null==id){
				id=0;
			}
			$("#ALLOT_NUM"+id).val("0");
		}else{
			//剩余数量
			$("#surplusNum").html(subNum);
		}
	}
	//验证分配数量是否全部分完
	function checkNum(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		//已分配数量
		var totalNum=0;
		var flag=true;
		checkBox.each(function(){
			var ALLOT_NUM_temp=$(this).parent().parent().find("input[name='ALLOT_NUM']").val();
			if(""==ALLOT_NUM_temp||null==ALLOT_NUM_temp||0==ALLOT_NUM_temp){
				alert("所选中的销售订单中有未分配数量！");
				flag=false;
				return false;
			}
			if(null!=ALLOT_NUM_temp&&""!=ALLOT_NUM_temp){
				var num=isNaN(ALLOT_NUM_temp)?0:parseFloat(ALLOT_NUM_temp);
				totalNum=totalNum+num;
			}
		})
		$("#allotNum").val(totalNum);
		if(!flag){
			return false;
		}
//		//总发货数量
//		var REAL_SEND_NUM_temp=$("#REAL_SEND_NUM").val();
//		var REAL_SEND_NUM=isNaN(REAL_SEND_NUM_temp)?0:parseFloat(REAL_SEND_NUM_temp);
//		var subNum=REAL_SEND_NUM-totalNum;
//		if(subNum>totalNum){
//			alert("还有剩余数量未分配，请分配完毕再确定！");
//			return false;
//		}
		return true;
	}
	//确定分配
	function allot(){
		var BASE_DELIVER_NOTICE_ID=$("#BASE_DELIVER_NOTICE_ID").val();
		var sessionId=$("#sessionId").val();
		var jsonData = multiPackJsonData();
		var allotNum=$("#allotNum").val();
		$.ajax({
		url: "senddirectnotice.shtml?action=savePrdInfo",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"BASE_DELIVER_NOTICE_ID":BASE_DELIVER_NOTICE_ID,"sessionId":sessionId,"allotNum":allotNum},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var data = jsonResult.data;
				window.returnValue=data;
				window.close();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	}