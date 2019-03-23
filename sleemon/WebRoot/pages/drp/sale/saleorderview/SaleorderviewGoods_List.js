/**
 * @prjName:喜临门营销平台
 * @fileName:分销 -订货订单维护
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	//主从及主从从列表页面通用加载方法
	var SALE_ORDER_ID=$("#SALE_ORDER_ID").val();
	listPageInit("saleorderview.shtml?action=toGoodsList&SALE_ORDER_ID=" + SALE_ORDER_ID);
	
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		//设置选择的记录的ID
		setSelRowId();
		 
	});
		
});


function setSelEid(obj,data){
	var checked =  $(obj).attr("checked"); 
	if(checked){
		$(obj).attr("checked",'true');
	}else{
		  $(obj).removeAttr("checked");
	}
	
	setSelRowId();
}
	


function setSelRowId(){
	var ids = "";
	$("#ordertb tr:gt(0) input:checked").each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	 
	//设置选中的Id
	parent.document.getElementById("selRowId").value = ids;
	
	//刷新子页面
	parent.window.gotoBottomPage();
 
}

