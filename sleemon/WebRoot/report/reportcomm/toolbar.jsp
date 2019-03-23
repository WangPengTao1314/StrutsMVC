<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<style>
	input.btn {
	border: 1px solid #a9c2c9;
	background-image:  url(btnBarBg.jpg);
	height: 22px;
	cursor: pointer;
	background-repeat: repeat-x;
}

.floatRight{ float:right;}
.floatLeft{ float:left;}
.floatCenter{ float:center;}

</style>
<div class="btnBar" style="width:100%;height:30px;" >
  <ul class="" >
    <li class="floatRight">  <div style="display:inline-block; margin:9px 4px 3px 4px; float:left; ">共<span id="t_page_span"></span>页/第<span id="c_page_span"></span>页&nbsp;&nbsp;</div></li>
  </ul>
  <ul>
      <li><a class="" href="#" onClick="report1_saveAsExcel()"><input  type='button' class='btn' value='导出Excel'></a>&nbsp;<a class="" href="#" onClick="report1_directPrint()"><input  type='button' class='btn' value='打印报表'></a>
      </li>
  </ul>
  <!-- 报表路径 -->
  <input type="hidden" id="rptModel" value="${param.rptModel}">
  <!-- sql参数 -->
  <input type="hidden" id="params" value="${param.params}">
  <!-- 报表名称 -->
  <input type="hidden" id="RPT_NAME" value="${param.RPT_NAME}">
  <!-- 判断通用选取是否删除过 -->
  <input type="hidden" id="flag" value="0"/>
<script type="text/javascript">
	$("#goback").click(function(){
		history.go(-1);
	});
	function reptshare(){
		$("#psonDiv").show();
	}
	/*
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
		        ];
			}
		//样式行
		var rownum = $("#jsontb tr:gt(2)").length+1;
		var classrow = rownum% 2;
		rownum=_row_num;
		_row_num++;
		$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' id='trId"+rownum+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
		$("#jsontb tr:last-child")
				.append("<td nowrap='nowrap' align='center' style='display: none'><input type='checkbox' checked=checked' </td>")
	            .append('<td nowrap align="left"><input style="border:none;display:block"  type="text" id="RYBH'+rownum+'" name="RYBH" jspn="RYBH" readonly /><input  json="SHARED_PSON_ID" id="SHARED_PSON_ID'+rownum+'" name="SHARED_PSON_ID"  label="被分享人ID" type="hidden"  /></td>')
	            .append('<td nowrap align="left"><input style="border:none;display:block" json="SHARED_PSON_NAME" id="SHARED_PSON_NAME'+rownum+'" name="SHARED_PSON_NAME"  label="被分享人名称" type="text" readonly /></td>')
	            .append('<td nowrap  align="center"  ><input type="button" class="btn" value="删除"  onclick="del(this)"></td>')
	              ;
	    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
		//form校验设置
		trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
		trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
		InitFormValidator(0);
	}
 
	function hidenTab(){
		$("#psonDiv").hide();
		$("#jsontb tr:gt(2)").remove();
		$("#SHAR_NAME").val("");
		$("#flag").val("0");
	}
 
	 
	function del(obj){
		$(obj).parent().parent().remove();
		setFloatPostion();
	}
	 */
	 
	 function report1_saveAsExcel(){
		 alert("excel...........");
	 }
</script>
</div>
