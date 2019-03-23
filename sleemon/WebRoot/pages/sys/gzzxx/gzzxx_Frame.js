/*
  *@module 系统管理
  *@func 工作组信息
  *@version 1.1
  *@author 吴亚林
  */
  $(function(){
	//框架页面初始化
	framePageInit("gzzxx.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("gzzxx.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("gzzxx.shtml?action=toList&module=" + module);

});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "gzzxx.shtml?action=childList";
	}else{
		url = "gzzxx.shtml?action=toDetail";
	}
	
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&GZZXXID="+selRowId);
}
  
  
  
  
  /* function funClick(oprationType)
        {
            //插入操作；
            if (oprationType == 1)
            {
                $("#bottomcontent").attr("src","xtjs.shtml?action=toAdd");
                return;
            }
            if (oprationType == 2)
            {
            	var edID =$.trim($("#selRowIdxId").val());  
			    if(!checkEid(edID))return;
			    var edurl="xtjs.shtml?action=toUpdate&XTJSID="+edID;
                $("#bottomcontent").attr("src",edurl);
                return;
            }
            if (oprationType == 3)
            {

                if (confirm('确定要删除本条记录?'))
                {
                    var edID =$.trim($("#selRowIdxId").val());  
			        if(!checkEid(edID))return;
			        var delurl="xtjs.shtml?action=delete&XTJSID="+edID;
                    $("#bottomcontent").attr("src",delurl);
                    return;
                }
                else
                {
                    return;
                }
            }
            if (oprationType == 4)
            {
                showDialog("xtjs.shtml?action=toQuery",580,530,"help:no;scroll:no; status:no; center:yes");
                return;
            }
            if(oprationType == 5){
            var edID =$.trim($("#selRowIdxId").val());  
			    if(!checkEid(edID))return;
                showDialog("readxml.shtml?action=toXTJSQXList&XTJSID="+edID,580,530,"help:no;scroll:no; status:no; center:yes");
                return;
                }
        }
        function checkEid(edID){		
			if(edID==null || ""==edID.trim()){
				showErrorMsg("请选择机构对象！");
				return false;
			}
			return true;
		}	

        
     var oldLableObject = null;
    function showit(myLable, file, lmax)
    {
        if(myLable==null)
        {
            myLable="label_0";
        }
        var obj = $(myLable);
        try
        {
            
            switchLabel(myLable.substring(myLable.length - 1, myLable.length));
        }
        catch(e)
        {
            //
        }
        if (file != $("#bottomcontent").attr("src"))
        {
            $("#bottomcontent").attr("src",file);
        }
    }*/