/**
 * @prjName:ϲ����Ӫ��ƽ̨
 * @fileName:Repairstoreout_Frame
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
	//���ҳ���ʼ��
	framePageInit("repairrecv.shtml?action=toParentEdit");
});

//bottomcontentҳ����ת.�����ǩ�����ѡ�еļ�¼��ѯ�ӱ���Ϣ��
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
		url = "repairrecv.shtml?action=modifyToChildEdit"
	}//else if //����Ƕ��ֱ�Ӷ��弴��
	//��֡ҳ����ת
	$("#bottomcontent").attr("src",url+"&ERP_REPAIR_ORDER_ID="+selRowId);
}