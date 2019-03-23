/**
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_List
 * @author ghx
 * @time   2014-07-10 
 * @version 1.1
 */
$(function(){    
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("trainresult.shtml?action=toList&module=" + module);	
});
 