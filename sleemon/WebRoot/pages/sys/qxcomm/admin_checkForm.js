/*
 * 表单数据检查函数
 * @auther dybine
 * @last update date 2004-06-15
 **/

/*
 * 校验form单元中元素是否为空
 * @param     formName		form的名称
 * @param		  itemName		元素的名称
 * @param     itemMessage    alert的消息
 * @return    true     不满足条件
 *						false    不满足条件
 */
function CheckIsNull(formName,itemName,itemMessage){
	var tempStr = trim(formName,itemName);
	if (tempStr.length>0){
		return true;
	}else{
		if(itemMessage!=null){
			alert(itemMessage);
		}
		return false;
	}
}

/*
 * 去字符串空格
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @param flag     可选项.操作标记.默认:去掉头部和尾部空格
 *                          0.去掉所有空格
 *                          1.去掉头部空格
 *                          2.去掉尾部空格
 *                          3.去掉头部和尾部空格
 * @return trim之后的字符串
 */
function trim(formName,itemName,flag){
	  var str = eval("document."+formName+"." +itemName+".value");
	  if(str=="")  return "";
		switch(flag){
			case 0://去掉所有空格
				str = str.replace(/ /g, "");
				break;

		  case 1://去掉头部空格
				while(str.indexOf(" ")==0){
					str = str.substring(1);
				}
				break;
		  case 2://去掉尾部空格
				while(str.lastIndexOf(" ")==str.length-1){
					str = str.substring(0, str.length-1);
				}
				break;
		  case 3://去掉头部和尾部空格
				while(str.indexOf(" ")==0){
					str = str.substring(1);
				}
				while(str!=""&&str.lastIndexOf(" ")==str.length-1){
					str = str.substring(0, str.length-1);
				}
				break;
			default://默认去头部和尾部空格
				while(str.indexOf(" ")==0){
					str = str.substring(1);
				}
				while(str!=""&&str.lastIndexOf(" ")==str.length-1){
					str = str.substring(0, str.length-1);
				}
				break;
		}
		return str;
}

/*
 * 获得某个元素的值的长度
 * @param formName  form的名称
 * @param itemName  元素的名称
 * @return value的字符串长度，中文算两个字符
 */
function getItemLength(formName, itemName){
	var theValue=eval("document." + formName+"."+ itemName + ".value");
	if(theValue.length==0) return 0;
	var bytelen=0;
	var re=/[^\x00-\xff]/g;
	//re.compile();
	for(var i=0;i<theValue.length;i++){
		var bl = re.test(theValue.charAt(i));
		re.test(theValue.charAt(i));
		if(bl){
		 	bytelen=bytelen+2;
		}else{
		 	bytelen+=1;
		}
	}
	return bytelen;
}

/*
 * 检查radio输入框
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @return 选择的radio输入框的值，如果未选择则返回null
 */
function checkRadio(formName,itemName){
		var radioObjs = eval("document."+formName+"." +itemName);
		if(radioObjs.length==null){
			if(radioObjs.checked){
				return radioObjs.value;
			}else{
				return null;
			}
		}else{
			for(var i=0;i<radioObjs.length;i++){
				if(radioObjs[i].checked){
					return radioObjs[i].value;
				}
			}
			return null;
		}
}

/*
 * 检查checkbox输入框
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @return a Array 选择的checkbox输入框的值组成的数组(0~n),如果未选择则返回一个长度为0的空数组
 */
function checkCheckbox(formName,itemName){
	var checkboxObjs = eval("document."+formName+"." +itemName);
	var returnArr = new Array();
	var num = 0;
	if(checkboxObjs==null) return returnArr;
	if(checkboxObjs.length==null){
		if(checkboxObjs.checked){
			returnArr[num++] = checkboxObjs.value;
		}
	}else{
		for(var i=0;i<checkboxObjs.length;i++){
			if(checkboxObjs[i].checked){
				returnArr[num++] = checkboxObjs[i].value;
			}
		}
	}
	return returnArr;
}

/*
 *ReMark:因为删除安钮放在框架上，所以调用时如下：checkTopCheckbox(topcontent.document.forms[0],itemName)  Add by Wangjin on:2004-07-17
 * 检查checkbox输入框
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @return a Array 选择的checkbox输入框的值组成的数组(0~n),如果未选择则返回一个长度为0的空数组
 */
function checkTopCheckbox(formName,itemName){
	var checkboxObjs = eval(formName+"." +itemName);
	var returnArr = new Array();
	var num = 0;
	if(checkboxObjs==null) return returnArr;
	if(checkboxObjs.length==null){
		if(checkboxObjs.checked){
			returnArr[num++] = checkboxObjs.value;
		}
	}else{
		for(var i=0;i<checkboxObjs.length;i++){
			if(checkboxObjs[i].checked){
				returnArr[num++] = checkboxObjs[i].value;
			}
		}
	}
	return returnArr;
}

/*
 *ReMark:批量删除是调用此方法，判断状态； checkValid(topcontent.document.forms[0],itemName,state)  Add by Wangjin on:2004-07-17
 * 检查checkbox输入框
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @param state    必选项.元素名 ReMark:考虑到有的情况“制定”和“否决”都可以删除；所以：stste可以是有多个状态组成；如“制定、否决”;
 * @return 
 */
function checkValid(formName,itemName,state){
	var checkboxObjs = eval(formName+"." +itemName);
	var formCaption = eval(formName);
	var returnArr = new Array();
	if(checkboxObjs.length == null)
	{
	  if(state.indexOf(formCaption.state.value) < 0 )
      {
         alert("选中的记录部分非“"+state+"”状态,操作失败！");              
         return false;
       } 
	   //return returnArr;
	}
	if(checkboxObjs!=null)
    { 
    for(var i=0;i<checkboxObjs.length;i++)
    {   
      if(checkboxObjs[i].checked==true)
      {          
        if(state.indexOf(formCaption.state[i].value) < 0 )
         {
           alert("选中的记录部分非“"+state+"”状态,操作失败！");              
           return false;
         } 
      }
    }
  }
}

/*
 *ReMark:批量删除是调用此方法，判断状态； getItemOfState(topcontent.document.forms[0],itemName,state)  Add by Wangjin on:2004-07-17
 * 检查checkbox输入框
 * @param formName 必选项.表单名
 * @param itemName 必选项.元素名
 * @param state    必选项.元素名 ReMark:考虑到有的情况“制定”和“否决”都可以删除；所以：stste可以是有多个状态组成；如“制定、否决”;
 * @return 
 */
function getItemOfState(formName,itemName,state){
	var checkboxObjs = eval(formName+"." +itemName);
	var formCaption = eval(formName);
    if(checkboxObjs.length == null)
    {
      if(state.indexOf(formCaption.state.value) < 0)
      {
         formCaption.checkbox.checked = false;         
      } 
    }  
    if(checkboxObjs!=null)
    { 
      for(var i=0;i<checkboxObjs.length;i++)
      {   
        if(formCaption.checkbox[i].checked==true)
        {          
          if(state.indexOf(formCaption.state[i].value) < 0)
          {
            formCaption.checkbox[i].checked=false;         
          } 
        }
      }
  }
}