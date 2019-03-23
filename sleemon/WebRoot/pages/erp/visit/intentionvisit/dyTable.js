       //获得控件
		/*
		function $(objId){
			return document.getElementById(objId);
		}
		function byName(objName){
			return document.getElementsByName(objName);
		}*/
		//刷新序号
		function refresh(){
			var tab = document.getElementById("tbody");
			for(var i=0;i<tab.rows.length;i++){
				tab.rows[i].cells[1].innerText = i+1;
			}
		}
		
		function chkAll(obj){
			var checkboxs = $(obj).parent().parent().parent().parent().find("tr:gt(0) input[type='checkbox']");
			var checked = $(obj).attr("checked");
			 
			if(checked == "checked"){
				checkboxs.attr("checked","checked");
			}else{
				checkboxs.removeAttr("checked");
			}
		}
 
		
		//增加行
		function addRow(arrs){
			if(null == arrs){
				arrs = ['','','','',''];
			}
			var tab = document.getElementById("tbody");
			//添加行
			var newTr = document.createElement("tr");
		    newTr.className = "detail_label";
			//添加列
			var newTd0 = document.createElement("td");
			var newTd1 = document.createElement("td");
			var newTd2 = document.createElement("td");
			var newTd3 = document.createElement("td");
			var newTd4 = document.createElement("td");
		    var newTd5 = document.createElement("td");
			var newTd6 = document.createElement("td");
			//设置单元格内容
			newTd0.innerHTML = '<input type=checkbox name="checkbox" id="box" >';
			
			newTd1.innerHTML = '<input type="text" name="EXIST_STORE_NAME"    json="EXIST_STORE_NAME"  value="'+arrs[0]+'" label="现有卖场名称" style="align:center;" autocheck="true"  inputtype="string" mustinput="true" /><font color="red">*</font>';
			newTd2.innerHTML = '<input type="text" name="EXIST_STORE_ADDR"    json="EXIST_STORE_ADDR"  value="'+arrs[1]+'" label="现有卖场地址" style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd3.innerHTML = '<input type="text" name="EXIST_STORE_AREA"    json="EXIST_STORE_AREA"  value="'+arrs[2]+'" label="现有卖场面积" style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd4.innerHTML = '<input type="text" name="EXIST_STORE_RANGE"   json="EXIST_STORE_RANGE" value="'+arrs[3]+'" label="档次排名" style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd5.innerHTML = '<input type="text" name="EXIST_STORE_COMPETITION"   json="EXIST_STORE_COMPETITION"  label="进驻主竞品" value="'+arrs[4]+'" style="align:center;" />';
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			newTr.appendChild(newTd5);
			tab.appendChild(newTr);
		}
		
		function addRow1(arrs){
			if(null == arrs){
				arrs = ['','','','',''];
			}
			var tab = document.getElementById("tbody1");
			var len = tab.rows.length+1;
			//添加行
			var newTr = document.createElement("tr");
			newTr.className = "detail_label";
			//添加列
			var newTd0 = document.createElement("td");
			var newTd1 = document.createElement("td");
			var newTd2 = document.createElement("td");
			var newTd3 = document.createElement("td");
			var newTd4 = document.createElement("td");
		    var newTd5 = document.createElement("td");
			var newTd6 = document.createElement("td");
			//设置单元格内容
			newTd0.innerHTML = '<input type=checkbox name="checkbox" id="box" >';
			
			newTd1.innerHTML = '<input type="text" name="NEW_STORE_NAME"     json="NEW_STORE_NAME"  label="近期新开卖场名称" value="'+arrs[0]+'"  style="align:center;"/>';
			newTd2.innerHTML = '<input type="text" name="NEW_STORE_ADDR"     json="NEW_STORE_ADDR"  label="近期新开卖场地址" value="'+arrs[1]+'"  style="align:center;"/>';
			newTd3.innerHTML = '<input type="text" name="NEW_STORE_AREA"     json="NEW_STORE_AREA"  label="近期新开卖场面积" value="'+arrs[2]+'"  autocheck="true" inputtype="int" style="align:center;"/>';
			newTd4.innerHTML = '<input type="text" name="NEW_STORE_RANGE"    json="NEW_STORE_RANGE" label="近期新开卖场档次排名" value="'+arrs[3]+'"  style="align:center;"/>';
			var i = len-1;
			newTd5.innerHTML = '<input type="text" name="NEW_STORE_DATE"  id="NEW_STORE_DATE'+i+'" label="近期新开卖场开始时间" json="NEW_STORE_DATE" value="'+arrs[4]+'"  style="align:center;" onclick="SelectDate();"   READONLY>' +
			                       '<img align="absmiddle" style="cursor: hand"src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(NEW_STORE_DATE'+i+');">';
			 
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			newTr.appendChild(newTd5);
			tab.appendChild(newTr);
		}
		
		function addRow2(arrs){
			if(null == arrs){
				arrs = ['',''];
			}
			var tab = document.getElementById("tbody2");
			var len = tab.rows.length+1;
			//添加行
			var newTr = document.createElement("tr");
			newTr.className = "detail_label";
			//添加列
			var newTd0 = document.createElement("td");
			var newTd1 = document.createElement('td');
			var newTd2 = document.createElement("td");
			var newTd3 = document.createElement("td");
			var newTd4 = document.createElement("td");
		 
			var cs = convertToChinese(len);
			var VISIT_TYPE_ID = "VISIT_TYPE"+len;
			//设置单元格内容
			newTd0.innerHTML = '<input type=checkbox name="checkbox" id="box" >';
			newTd1.innerHTML = '第'+cs+'次拜访方式';
 		    newTd2.innerHTML = ' <select id="'+VISIT_TYPE_ID+'" name="VISIT_TYPE" label="拜访形式" style="width: 155px;" value="'+arrs[0]+'" json="VISIT_TYPE" label="第'+cs+'次拜访方式"></select>  ';
			newTd3.innerHTML = '第'+cs+'次拜访/跟进情况说明';
			newTd4.innerHTML = '<input type="text" name="VISIT_REMARK" id="VISIT_REMARK" label="拜访/跟进情况说明" json="VISIT_REMARK" value="'+arrs[1]+'"   label="第'+cs+'次拜访/跟进情况说明" >';
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			 
			tab.appendChild(newTr);
			SelDictShow(VISIT_TYPE_ID,"BS_154",arrs[0],"");
		}
		
		
		function CompareDate(paramsDate,param){
		    //开始时间
			var NEW_STORE_DATE = paramsDate.value;
			if(null == NEW_STORE_DATE || "" == NEW_STORE_DATE){
				return;
			}
			NEW_STORE_DATE = NEW_STORE_DATE.replace("-","/");//替换字符，变成标准格式
			var d1 = new Date(Date.parse(NEW_STORE_DATE));
			var d2=new Date();    //取今天的日期
			if(d1<d2){
			    parent.showErrorMsg("开始时间应该大于当前日期");
				$("#NEW_STORE_DATE"+param).val("");
				return ;
			}
		}
		
		
	    function showChanName(params){
		  	var obj= selCommon('BS_107', false, "CHANN_NAME"+params, "CHANN_NAME", 'forms[0]',"CHANN_NAME"+params,"CHANN_NAME", 'selectParamst');
		    rtnArr=multiSelCommonSet(obj,"CHANN_NAME",params);
		}
		
	    function delRow(tableId){
	    	if(null == tableId){
				tableId = "myTable"
			}
 
	    	var checkboxs = $("#"+tableId+" tr:gt(0) input:checked");
	    	if(checkboxs.length == 0){
	    		parent.showErrorMsg("请选择要删除的行");
	    	}else{
	    		checkboxs.each(function(){
	    			$(this).parent().parent().remove();
	    		});
	    	}
	    }
