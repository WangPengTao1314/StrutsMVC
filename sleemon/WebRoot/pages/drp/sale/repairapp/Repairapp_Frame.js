/**
 * @prjName:ϲ����Ӫ��ƽ̨
 * @fileName:Repairapp_Frame
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
	//���ҳ���ʼ��
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("repairapp.shtml?action=toList&frameTolist=frameTolist" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("repairapp.shtml?action=toList&module=" + module+"&frameTolist=frameTolist");
	 }	
});

//bottomcontentҳ����ת
function gotoBottomPage(showLabelId){
	var module = document.getElementById("module").value;
    //��ȡ��ǰѡ�еļ�¼
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//���õ�ǰ��ʾ�ı�ǩҳ
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "repairapp.shtml?action=childList&module=" + module
	}else{
		url = "repairapp.shtml?action=toDetail"
	}
	//��֡ҳ����ת
	$("#bottomcontent").attr("src",url+"&ERP_REPAIR_ORDER_ID="+selRowId);
}