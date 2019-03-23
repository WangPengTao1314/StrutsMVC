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
		function chkAll(){
		    var rad = document.getElementById("radio");
			var chk = document.getElementsByName("checkbox");
			for(var i = 0;i < chk.length;i++){
			      if(rad.checked){
			           chk[i].checked=true;
			      }else{
			           chk[i].checked=false;
			      }
				//var obj = chk[i];
				//obj.checked?obj.checked=false:obj.checked=true;
			}
		}
		
		function chkAllT(){
		    var rad = document.getElementById("radioT");
			var chk = document.getElementsByName("checkboxT");
			for(var i = 0;i < chk.length;i++){
			  if(rad.checked){
		           chk[i].checked=true;
		      }else{
		           chk[i].checked=false;
		      }
			}
		}
		
		function chkAllTt(){
		    var rad = document.getElementById("radioTt");
			var chk = document.getElementsByName("checkboxTt");
			for(var i = 0;i < chk.length;i++){
				if(rad.checked){
		           chk[i].checked=true;
			    }else{
			           chk[i].checked=false;
			    }
			}
		}
		
		//增加行
		function addRow(){
			var tab = document.getElementById("tbody");
			//添加行
			var newTr = document.createElement("tr");
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
			
			newTd1.innerHTML = '<input type="text" name="EXIST_STORE_NAME"  id="EXIST_STORE_NAME"  json="EXIST_STORE_NAME"  style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd2.innerHTML = '<input type="text" name="EXIST_STORE_ADDR"  id="EXIST_STORE_ADDR"  json="EXIST_STORE_ADDR"  style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd3.innerHTML = '<input type="text" name="EXIST_STORE_AREA"  id="EXIST_STORE_AREA"  json="EXIST_STORE_AREA"  style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd4.innerHTML = '<input type="text" name="EXIST_STORE_RANGE" id="EXIST_STORE_RANGE" json="EXIST_STORE_RANGE" style="align:center;" autocheck="true"  inputtype="string" mustinput="true"/><font color="red">*</font>';
			newTd5.innerHTML = '<input type="text" name="EXIST_STORE_COMPETITION" id="EXIST_STORE_COMPETITION" json="EXIST_STORE_COMPETITION" style="align:center;">';
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			newTr.appendChild(newTd5);
			tab.appendChild(newTr);
		}
		
		function addRowT(){
			var tab = document.getElementById("tbodyT");
			var len = tab.rows.length+1;
			//添加行
			var newTr = document.createElement("tr");
			//添加列
			var newTd0 = document.createElement("td");
			var newTd1 = document.createElement("td");
			var newTd2 = document.createElement("td");
			var newTd3 = document.createElement("td");
			var newTd4 = document.createElement("td");
		    var newTd5 = document.createElement("td");
			var newTd6 = document.createElement("td");
			//设置单元格内容
			newTd0.innerHTML = '<input type=checkbox name="checkboxT" id="box" >';
			
			newTd1.innerHTML = '<input type="text" name="NEW_STORE_NAME"  id="NEW_STORE_NAME"  json="NEW_STORE_NAME"  style="align:center;"/>';
			newTd2.innerHTML = '<input type="text" name="NEW_STORE_ADDR"  id="NEW_STORE_ADDR"  json="NEW_STORE_ADDR"  style="align:center;"/>';
			newTd3.innerHTML = '<input type="text" name="NEW_STORE_AREA"  id="NEW_STORE_AREA"  json="NEW_STORE_AREA"  style="align:center;">';
			newTd4.innerHTML = '<input type="text" name="NEW_STORE_RANGE" id="NEW_STORE_RANGE" json="NEW_STORE_RANGE" style="align:center;">';
			for(var i=0;i<len;i++){
			    newTd5.innerHTML = '<input type="text" name="NEW_STORE_DATE"  id="NEW_STORE_DATE'+i+'"  json="NEW_STORE_DATE'+i+'"  style="align:center;" onclick="SelectDate();" onblur="CompareDate(NEW_STORE_DATE'+i+','+i+');" READONLY>' +
			                       '<img align="absmiddle" style="cursor: hand"src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(NEW_STORE_DATE'+i+');">';
			}
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			newTr.appendChild(newTd5);
			tab.appendChild(newTr);
		}
		
		function addRowTt(){
			var tab = document.getElementById("tbodyTt");
			var len = tab.rows.length+1;
			//添加行
			var newTr = document.createElement("tr");
			//添加列
			var newTd0 = document.createElement("td");
			var newTd1 = document.createElement("td");
			var newTd2 = document.createElement("td");
			var newTd3 = document.createElement("td");
			var newTd4 = document.createElement("td");
		    var newTd5 = document.createElement("td");
			var newTd6 = document.createElement("td");
			//设置单元格内容
			newTd0.innerHTML = '<input type=checkbox name="checkboxTt" id="box" >';
			newTd1.innerHTML = '<input type="text" name="CHANN_NAME"  id="CHANN_NAME'+len+'" json="CHANN_NAME" style="align:center;"/>';
 		    newTd2.innerHTML = '<input type="text" name="BUSS_SCOPE"  id="BUSS_SCOPE'+len+'" json="BUSS_SCOPE'+len+'" style="align:center;" />';
			newTd3.innerHTML = '<input type="text" name="STORE_NAME"  id="STORE_NAME" json="STORE_NAME" style="align:center;">';
			newTd4.innerHTML = '<input type="text" name="PURPOSE_STORE_NAME" id="PURPOSE_STORE_NAME" json="PURPOSE_STORE_NAME" style="align:center;">';
			newTd5.innerHTML = '<input type="text" name="TEL"  id="TEL" json="TEL" style="align:center;">';
			//添加进表格
			newTr.appendChild(newTd0);
			newTr.appendChild(newTd1);
			newTr.appendChild(newTd2);
			newTr.appendChild(newTd3);
			newTr.appendChild(newTd4);
			newTr.appendChild(newTd5);
			tab.appendChild(newTr);
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
		
		
		//删除行
		function delRow(){
			var tbody = document.getElementById("tbody");
			var radios = document.getElementsByName("checkbox");
			var count = 0;
			for(var i = 0; radios !=null && i < radios.length; i++)
			{
				var item = radios[i];
				if(item.checked == true)
				{ 
					var parentNode = item.parentNode.parentNode;
					tbody.removeChild(parentNode);
					i = i - 1;
					count+=1;
					//break;
				}
			}
			if(count==0 ){  
			  parent.showErrorMsg("请选择要删除的列");
			}
			//refresh();
		}  
		
		function delRowT(){
			var tbodyT = document.getElementById("tbodyT");
			var radiosT = document.getElementsByName("checkboxT");
			var count = 0;
			for(var i = 0; radiosT !=null && i < radiosT.length; i++)
			{
				var itemT = radiosT[i];
				if(itemT.checked == true)
				{
					var parentNode = itemT.parentNode.parentNode;
					tbodyT.removeChild(parentNode);
					i = i - 1;
					count+=1;
					//break;
				}
			}
			if(count==0 ){  
			  parent.showErrorMsg("请选择要删除的列");
			}
			//refresh();
		}  
    
       function delRowTt(){
			var tbody = document.getElementById("tbodyTt");
			var radios = document.getElementsByName("checkboxTt");
			var count = 0;
			for(var i = 0; radios !=null && i < radios.length; i++)
			{
				var item = radios[i];
				if(item.checked == true)
				{
					var parentNode = item.parentNode.parentNode;
					tbody.removeChild(parentNode);
					i = i - 1;
					count+=1;
					//break;
				}
			}
			if(count==0 ){  
			  parent.showErrorMsg("请选择要删除的列");
			}
			//refresh();
		}  
		
