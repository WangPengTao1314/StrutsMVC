
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	headColumnSort();
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();;
	});
	$("#reset").click(function(){
		$("#SALE_DATE_BEG").val("");
		$("#SALE_DATE_END").val("");
		$("#ORDER_RECV_DATE_BEG").val("");
		$("#ORDER_RECV_DATE_END").val("");
		$("#CRE_TIME_BEG").val("");
		$("#CRE_TIME_END").val("");
		$("#ADVC_ORDER_NO").val("");
	});
	//点击下一步跳转货品明细分组页面
	$("#nextStep").click(function () {
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var ids = "";
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
		$("#queryForm").attr("action","advctoorder.shtml?action=toChild&ADVC_ORDER_IDS="+utf8(ids))
		$("#queryForm").submit();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});

//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).removeAttr("checked");
	}else{
		$(obj).attr("checked","true");
	}
}

