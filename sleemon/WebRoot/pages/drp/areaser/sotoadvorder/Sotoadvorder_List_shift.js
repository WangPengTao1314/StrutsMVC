
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	headColumnSort();
	InitFormValidator("queryForm");
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();;
	});
	//点击下一步跳转货品明细分组页面
	$("#nextStep").click(function () {
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		if(!checkChann()){
			return;
		}
		var ids = "";
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
		
		var ORDER_CHANN_ID=$("#ORDER_CHANN_ID").val();
		var ORDER_CHANN_NO=$("#ORDER_CHANN_NO").val();
		var ORDER_CHANN_NAME=$("#ORDER_CHANN_NAME").val();
		var RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
		var RECV_CHANN_NO=$("#RECV_CHANN_NO").val();
		var RECV_CHANN_NAME=$("#RECV_CHANN_NAME").val();
		var RECV_ADDR_NO=$("#RECV_ADDR_NO").val();
		var RECV_ADDR=$("#RECV_ADDR").val();
		var url="sotoadvorder.shtml?action=toChild&SALE_ORDER_IDS="+utf8(ids)
										+"&ORDER_CHANN_ID="+utf8(ORDER_CHANN_ID)
										+"&ORDER_CHANN_NO="+utf8(ORDER_CHANN_NO)
										+"&ORDER_CHANN_NAME="+utf8(ORDER_CHANN_NAME)
										+"&RECV_CHANN_ID="+utf8(RECV_CHANN_ID)
										+"&RECV_CHANN_NO="+utf8(RECV_CHANN_NO)
										+"&RECV_CHANN_NAME="+utf8(RECV_CHANN_NAME)
										+"&RECV_ADDR_NO="+utf8(RECV_ADDR_NO)
										+"&RECV_ADDR="+utf8(RECV_ADDR);
		$("#queryForm").attr("action",url);
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
//渠道通用选取
function selChannInfo(){
	var old_RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME','selectParams')
	var RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	if(old_RECV_CHANN_ID!=RECV_CHANN_ID){
		$("#RECV_ADDR_NO").val("");
		$("#RECV_ADDR").val("");
		upChannInfo();
	}
}
function selRevcAddr(){
	var CHANN_ID=$("#RECV_CHANN_ID").val();
	if(""==CHANN_ID||null==CHANN_ID){
		parent.showErrorMsg("请先选择收货方信息")
		return;
	}
	$("#selectAddrParams").val(" DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+CHANN_ID+"' ");
	selCommon('BS_76', false, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');
}
function checkChann(){
	var RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	var RECV_ADDR_NO=$("#RECV_ADDR_NO").val();
	if(""==RECV_CHANN_ID||null==RECV_CHANN_ID){
		parent.showErrorMsg("请选择收货方信息");
		return false;
	}
	if(""==RECV_ADDR_NO||null==RECV_ADDR_NO){
		parent.showErrorMsg("请选择收货地址");
		return false;
	}
	return true
}
function upChannInfo(){
	$("#queryForm").submit();
}