/**
 * @prjName:ϲ����Ӫ��ƽ̨
 * @fileName:Prmtexit_Frame
 * @author chenj
 * @time   2013-10-19 16:54:28 
 * @version 1.1
 */
$(function(){
	//���ҳ���ʼ��
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("prmtexit.shtml?action=toList&frameTolist=frameTolist" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("prmtexit.shtml?action=toList&module=" + module+"&frameTolist=frameTolist");
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
		url = "prmtexit.shtml?action=childList"
	}else{
		url = "prmtexit.shtml?action=toDetail"
	}
	//��֡ҳ����ת
	$("#bottomcontent").attr("src",url+"&PRMT_GOODS_EXTD_ID="+selRowId);
}