<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author 朱晓文 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type=text/javascript src="${ctx}/scripts/core/jquery-1.4.1.min.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree-2.6.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>SAMPLE</title>
	<script type=text/javascript >
    $(function(){
	    //上传 
	    //param1  控件 id           必输
	    //param2  服务器二级文件夹     必输
	    //param3  是否显示下载链接     默认是 fasle
	    //param4  是否是多文件上传     默认是 fasle
	    //param5  是否是自动提交文件   默认是 true
	    //param6  回调函数
	    uploadFile('uploadOne1','SAMPLE_DIR');
	    uploadFile('uploadOne2','SAMPLE_DIR',true,false,true,yourBussiness);
	    uploadFile('uploadMul','SAMPLE_DIR',true,true,false,yourBussiness);
	    //下载
	    //param1  控件 id          必输
	    //param2  服务器二级文件夹    必输
	    //param3  是否是单文件下载    默认是fasle
	    //pram4   是否可以编辑        默认为fasle
	    displayDownFile('simpleDownload','SAMPLE_DIR',true,false);
	    displayDownFile('mulDowload','SAMPLE_DIR,SAMPLE_DIR,SAMPLE_DIR',false,false);
	    
	    
	    //下拉数据字典
	    SelDictShow("select1","32","制定","");
	    
	    
	    //通用选取
	    //单选
	    $("#simpleSelComm").click(function(){
	       selCommon('System_10', false, 'RYXXID', 'RYXXID', 'forms[0]','RYXXID,RYBH,RYMC', 'RYXXID,RYBH,XM', 'selcondition');
	    });
	    //多选
	    $("#mulSelComm").click(function(){
	       selCommon('System_10', true, 'RYXXIDS', 'RYXXID', 'forms[0]','RYXXIDS,RYBHS,RYMCS', 'RYXXID,RYBH,XM','');
	    });
	    //树形通用选取
	    $("#treeSelComm").click(function(){
	        selCommon('System_2',false , 'JGXXID', 'JGXXID', 'forms[0]','JGXXID,JGBH,JGMC', 'JGXXID,JGBH,JGMC','',"T");
	    });
	   //树形通用选取
	    $("#treeSelComm2").click(function(){
	        selCommon('System_17',false , 'JGXXID2', 'JGXXID', 'forms[0]','JGXXID2,JGBH2,JGMC2', 'JGXXID,JGBH,JGMC','',"T");
	    });
	    
	    //输入框控制
	    //不许输入中文
	    $("#noChineseBtn").click(function(){
	          var tmp=$("#noChinese");
	          InputCheck(tmp);
	    });
	    
	    $("#onIntBtn").click(function(){
	          var tmp=$("#onInt");
	          InputCheck(tmp);
	    });
	    $("#onFloatBtn").click(function(){
	          var tmp=$("#onFloat");
	          InputCheck(tmp);
	    });
	    $("#zfonFloatBtn").click(function(){
	          var tmp=$("#zfonFloat");
	          InputCheck(tmp);
	    });
	    
	    /**
	     $("#btn_chg").click(function(){
	         //注意，可以多个EXCEL一起转换，这里只是一个例子
	    	 var chagExltext=$("#changeExcel").val();
	    	 //你解析exl的模板ID
	    	 var dataModelId="NT001";
	    	 var fileName=chagExltext;
	    	 var newFileName="StandFile";
	    	 var sedPath="SAMPLE_DIR";
	    	 var newSedPath="SAMPLE_DIR";
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"EXLMODELID":"'+dataModelId+'","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'","NEWFILENAME":"'+newFileName+'","NEWSEDPATH":"'+newSedPath+'","DST_NO":"SYS0001","DST_NAME":"润和","BILL_TYPE":"水单类型","SUB_NAME":"提交人姓名"}';
	    	
	    	 //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 var bill_Type="原始水单转标准水单";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	    	
	     }); 
	     
	    **/
	    
	   });
	function yourBussiness(id)
	{  
	    //所有文件路径
	    var filePaths=$("#"+id).val();
	    //当前文件路径
	    var curFilePath=$("#"+id).attr("thePath");
	    alert("回调成功!");
	}
	</script>
</head>

<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：&gt;控件例子</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		 <tr>
			<td width="15%" align="right" class="detail_label" nowrap>上传单个文件：</td>
			<td width="35%" class="detail_content" >
				<input  type="text"  name="uploadOne1" id ="uploadOne1" />
			</td>
			<td width="15%" align="right" class="detail_label">上传单个文件：</td>
			<td width="35%" class="detail_content">
                 <input  type="text"  name="uploadOne2" id ="uploadOne2" value='2013/04-17/员工手册_6f45a2eab1754ccb93df54f39fb1826d.pdf'  />
			</td>
					
		</tr>
		 <tr >
			<td width="15%" align="right" class="detail_label"   nowrap>多文件上传：</td>
			<td width="35%" class="detail_content" colspan='3'  >
				<input  type="text"  name="uploadMul" id ="uploadMul" />
			</td>
					
		</tr>
		<tr>
			<td width="15%" align="right" class="detail_label" nowrap>单文件下载：</td>
			<td width="35%" class="detail_content" >
				<input   type="hidden"   id ="simpleDownload"  value='2013/04-17/员工手册_6f45a2eab1754ccb93df54f39fb1826d.pdf'  />
			</td>
			<td width="15%" align="right" class="detail_label">多文件下载：</td>
			<td width="35%" class="detail_content">
                 <input  type="hidden"  name="mulDowload" id ="mulDowload" value='2013/04-17/员工手册_6f45a2eab1754ccb93df54f39fb1826d.pdf,2013/04-16/1234.jpg,2013/04-17/国内BU通讯录.xls'/>&nbsp;
            </td>
		</tr>
  </table>
  <br></br>
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		 <tr>
			<td width="15%" align="right" class="detail_label" nowrap>单一下拉框：</td>
			<td width="35%" class="detail_content" >
				<select id="select1" name="select1" style="width:155px">
				</select>
			</td>
			<td width="15%" align="right" class="detail_label">复选下拉框：</td>
			<td width="35%" class="detail_content">
                 <input  type="text"  name="select2" id ="select2" />&nbsp;
			</td>
					
		</tr>
  </table>
  <br></br>
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		 <tr>
			<td width="15%" align="right" class="detail_label" nowrap>日期：</td>
			<td width="35%" class="detail_content" >
				  <input type="text" json="datesample" id="datesample"name="datesample" autocheck="true" inputtype="string" label="日期"  mustinput="true" onclick="SelectDate();" readonly />&nbsp;
				  <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(datesample);"/>
			</td>
			<td width="15%" align="right" class="detail_label">时间：</td>
			<td width="35%" class="detail_content">
                  <input type="text" json="timesample" id="timesample"name="timesample" autocheck="true" inputtype="string" label="日期"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
				  <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(timesample);"/>
			</td>
					
		</tr>
  </table>
  <br></br>
  <form name="form" method="post">
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		 <tr>
			<td width="15%" height="30" align="right" class="detail_label">单通用选取:人员编号：</td>
			<td width="35%" class="detail_content">
				<input id="selcondition" type="hidden" name="selcondition" value=" 1=1 " />
				<input json="RYBH" name="RYBH" type="text" seltarget="selRYBH" autocheck="true" label="人员编号" inputtype="string" readonly mustinput="true" value="${rst.RYBH}"/>
				<input json="RYXXID" name="RYXXID" type="hidden" value="${rst.RYXXID}" seltarget="selRYBH"/>
				<img align="absmiddle" name="simpleSelComm" id="simpleSelComm" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"/>
			</td>
			<td width="15%" height="30" align="right"
				class="detail_label">人员名称：</td>
			<td width="35%" class="detail_content">
			    <input json="RYMC" name="RYMC" id="RYMC" type="text" readonly  value="${rst.RYMC}" seltarget="selRYBH" autocheck="true" label="人员名称" inputtype="string"/>
			</td>
		</tr>
		<tr>
			<td width="15%" height="30" align="right" class="detail_label">多通用选取:人员编号：</td>
			<td width="35%" class="detail_content">
				<input json="RYBHS" name="RYBHS" type="text" seltarget="selRYBHS" autocheck="true" label="人员编号" inputtype="string" readonly mustinput="true" value="${rst.RYBH}"/>
				<input json="RYXXIDS" name="RYXXIDS" type="hidden" value="${rst.RYXXID}" seltarget="selRYBHS"/>
				<img align="absmiddle" name="mulSelComm" id="mulSelComm" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"/>
			</td>
			<td width="15%" height="30" align="right"
				class="detail_label">人员名称：</td>
			<td width="35%" class="detail_content">
			    <input json="RYMCS" name="RYMCS" id="RYMCS" type="text" readonly  value="${rst.RYMC}" seltarget="selRYBHS" autocheck="true" label="人员名称" inputtype="string"/>
			</td>
		</tr>
		
		<tr>
			<td width="15%" height="30" align="right" class="detail_label">树形(可以选叶子):机构编号：</td>
			<td width="35%" class="detail_content">
				<input json="JGBH" name="JGBH" type="text" seltarget="selJGXX" autocheck="true" label="机构编号" inputtype="string" readonly mustinput="true" />
				<input json="JGXXID" name="JGXXID" type="hidden"  seltarget="selJGXX"/>
				<img align="absmiddle" name="treeSelComm" id="treeSelComm" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"/>
			</td>
			<td width="15%" height="30" align="right"
				class="detail_label">机构名称：</td>
			<td width="35%" class="detail_content">
			    <input json="JGMC" name="JGMC" id="JGMC" type="text" readonly   seltarget="selJGXX" autocheck="true" label="机构名称" inputtype="string"/>
			</td>
		</tr>
		<tr>
			<td width="15%" height="30" align="right" class="detail_label">树形(可以选所有节点):机构编号：</td>
			<td width="35%" class="detail_content">
				<input json="JGBH2" name="JGBH2" type="text" seltarget="selJGXX2" autocheck="true" label="机构编号" inputtype="string" readonly mustinput="true" />
				<input json="JGXXID2" name="JGXXID2" type="hidden" seltarget="selJGXX2"/>
				<img align="absmiddle" name="treeSelComm" seltarget="selJGXX2" id="treeSelComm2" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif"/>
			</td>
			<td width="15%" height="30" align="right"
				class="detail_label">机构名称：</td>
			<td width="35%" class="detail_content">
			    <input json="JGMC2" name="JGMC2" id="JGMC2" type="text" readonly   seltarget="selJGXX2" autocheck="true" label="机构名称" inputtype="string"/>
			</td>
		</tr>
		<tr>
			<td width="15%" height="30" align="right" class="detail_label">非中文控制：</td>
			<td width="35%" class="detail_content">
				<input id="noChinese"  type="text" autocheck="true" inputtype="string"  valueType="chinese:false" mustinput="true"  label="非中文控制"   />
				<input id="noChineseBtn" type="button" class="btn" value="检测" />	
			</td>
			<td width="15%" height="30" align="right" class="detail_label">整数int控制：</td>
			<td width="35%" class="detail_content">
			    <input id="onInt" type="text"   autocheck="true" inputtype="int"  mustinput="true" label="整数int控制" />
			    <input id="onIntBtn" type="button" class="btn" value="检测" />
			</td>
		</tr>
		<tr>
			<td width="15%" height="30" align="right" class="detail_label">正数小数控制：</td>
			<td width="35%" class="detail_content">
				<input id="onFloat"  type="text" autocheck="true" inputtype="float"  mustinput="true" valueType="5,2" label="正数小数控制"   />
				<input id="onFloatBtn" type="button" class="btn" value="检测" />	
			</td>
			<td width="15%" height="30" align="right" class="detail_label">正负小数控制：</td>
			<td width="35%" class="detail_content">
			    <input id="zfonFloat" type="text"   autocheck="true" inputtype="zffloat" mustinput="true" valueType="2,2"  label="正负小数控制" />
			    <input id="zfonFloatBtn" type="button" class="btn" value="检测" />
			</td>
		</tr>
  </table>
  </form>
 </body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
