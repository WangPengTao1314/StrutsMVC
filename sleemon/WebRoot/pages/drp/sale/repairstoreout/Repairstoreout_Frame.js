/**
 * @prjName:ϲ����Ӫ��ƽ̨
 * @fileName:Repairstoreout_Frame
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
	    framePageInit("repairout.shtml?action=toList&frameTolist=frameTolist" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("repairout.shtml?action=toList&module=" + module+"&frameTolist=frameTolist");
	 }	
});

//bottomcontentҳ����ת
function gotoBottomPage(showLabelId){
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
		url = "repairout.shtml?action=childList"
	}else{
		url = "repairout.shtml?action=toDetail"
	}
	//��֡ҳ����ת
	$("#bottomcontent").attr("src",url+"&ERP_REPAIR_ORDER_ID="+selRowId);
}