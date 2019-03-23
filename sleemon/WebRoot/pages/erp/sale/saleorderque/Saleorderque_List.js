/**
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("saleorderque.shtml?action=toList&module=" + module);
	//删掉查询状态里的未提交和已入库和已结算状态
});
