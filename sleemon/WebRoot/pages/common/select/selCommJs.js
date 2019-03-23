/*
 * @v3.1 edit by zhuw 2011-12-2
 */
var selCommArgs = new Array();//传递给通用选取的参数
var preSearch = true;//是否需要预先查询,默认是

/*
 * 通用选取调用函数
 * @param id      必选项 通用查询序号
 * @param type    必选项 选择类型
 * @param keyElem 必选项 存放主键的元素
 * @param key     必选项 主键对应的字段
 * @param frmName 必选项 表单名
 * @param elems   必选项 存放选取的值得元素名
 * @param fields  必选项 选取的字段
 * @param cnName            可选项 存放查询条件的元素名
 * @param isShowSearchLayer 可选项 是否显示搜索层
 * @param specialTable      可选项 特殊情况下的表名,直接替换通用选取中的表名
 * @param specialAsTable    可选项 特殊情况下的表别名,和参数specialTable配套使用
 * @param initCondName      可选项 存放初始化的查询条件的元素
 * @return  返回一个数组 第一个元素存放的是选择的记录数，第二个元素存放的是记录是否改变 ??有问题，期待改进
 */
function selCommon(id, type, keyElem, key, frmName, elems, fields, cnName, showModel, specialTable, specialAsTable, initCondName){
	id = id+"";
	selCommArgs[0] = id;
	selCommArgs[1] = id.substring(id.indexOf("_")+1);
	selCommArgs[2] = type;
	selCommArgs[3] = keyElem;
	selCommArgs[4] = key;
	selCommArgs[5] = frmName;
	selCommArgs[6] = elems;
	selCommArgs[7] = fields;
	selCommArgs[8] = (cnName==null||cnName=="")?"":eval("document."+frmName+"."+cnName+".value");
	selCommArgs[9] = specialTable==null?"":specialTable;
	selCommArgs[10] = "";
	selCommArgs[11] = specialAsTable==null?"":specialAsTable;
	//初始化搜索条件
	selCommArgs[12] = (initCondName==null||initCondName=="")?"":eval("document."+frmName+"."+initCondName+".value");
	 if(event==null||event.srcElement==null){
		returnarr = window.showModalDialog(ctxPath+"/common/select.shtml?action=doPost&cmdFlag=topFrame&tableId="+selCommArgs[1]+"&selCommId="+selCommArgs[0]+"&isShowSearchLayer="+selCommArgs[10]+"&specialTable="+selCommArgs[9]+"&showQYTree="+showModel,window,"dialogHeight:600px;dialogWidth:800px;help:no;scroll:no;status:no;center:yes;resizable:no");
		return returnarr;
	}
	//如果除了hidden元素外其他元素都已清空则清空所有元素并返回
	var noMatchElems = doSelCommValidate(frmName, event.srcElement.name==null?event.srcElement.id:event.srcElement.name);
	var noValHidElemsNum = 0;
	var norElemsNum = 0;
	var noValNorElemsNum = 0;
	for(var i=0;i< noMatchElems.length;i++){
		if(noMatchElems[i].type=='hidden'&&noMatchElems[i].value==''){
			//隐含空值元素个数
			noValHidElemsNum++;
		}else if(noMatchElems[i].type!='hidden'){
			//普通元素个数
			norElemsNum++;
			//普通空值元素个数
			if(noMatchElems[i].value==''){
				noValNorElemsNum++;
			}
		}
	}
	if(noValNorElemsNum+noValHidElemsNum< noMatchElems.length){//非所有元素都为空值
		if(noValNorElemsNum==norElemsNum){//所有普通元素都为空值
				for(var i=0;i< noMatchElems.length;i++){
					noMatchElems[i].value='';
					noMatchElems[i].oldValue='';
				}
				moveSelCommFocus(true);//移动焦点到下一节点
				return;//返回
		}
	}

	//组建查询条件
	var cond1 = " 1=1 ";
	var cond2 = " 1=1 ";
	var arrSelCommElems = elems.split(",");
	var arrSelCommFields = fields.split(",");
	var needSearch = false;
	var isOldSelComm = false;
	for(var i=0;i< arrSelCommElems.length;i++){
		var tmpobj = eval("document."+frmName+"."+arrSelCommElems[i]);
		//判断输入框的值是否改变,如果是则置查询标记
		if(tmpobj.value!=tmpobj.oldValue) needSearch = true;
		//这段有问题  这段稍后再改 by  zhuxw
		//if(tmpobj.className==null||tmpobj.className.indexOf("selcomm")==-1){
		//	isOldSelComm = true;
		//	break;
		//}
		//edit by zhuxw
		//if(tmpobj.value!=''&&tmpobj.type!='hidden'&&tmpobj.name==event.srcElement.preSearchElemName){//跳过隐含元素和空值元素
		if(tmpobj.value!=''&&tmpobj.type!='hidden'){//跳过隐含元素和空值元素
			if(tmpobj.noQuotes=="T"){
			  cond1 = cond1 + " and (" + arrSelCommFields[i] + " = "+tmpobj.value+")";
			  cond2 = cond2 + " and (" + arrSelCommFields[i] + " = "+tmpobj.value+")";
			}else{
			  var arrConElems = tmpobj.value.split(",");
			  if(arrConElems.length==1)
			  {
			    cond1 = cond1 + " and (" + arrSelCommFields[i] + " ='"+tmpobj.value+"')";
			    cond2 = cond2 + " and (" + arrSelCommFields[i] + " like '%"+tmpobj.value+"%')";
			  }else
			  {
			    var vcon1="and (1<>1";
			    var vcon2="and (1<>1";
			    for(var k=0;k< arrConElems.length;k++){
			      vcon1 = vcon1 + ' or ' +arrSelCommFields[i] + " ='"+arrConElems[k]+"'";
			      vcon2 = vcon2 + ' or ' + arrSelCommFields[i] + " like '%"+arrConElems[k]+"%'";
			    }
			    vcon1 = vcon1+")";
			    vcon2 = vcon2+")";
			    cond1 = cond1 +vcon1;
			    cond2 = cond2 + vcon2;
			  }
			   
			  
			}
		}
	}
	var arrKeyElems = keyElem.split(",");
	var arrKeyFields = key.split(",");
	for(var i=0;i< arrKeyElems.length;i++){
		var tmpobj = eval("document."+frmName+"."+arrKeyElems[i]);
		//判断输入框的值是否改变或主键是否为空,如果是则置查询标记
		if(tmpobj.value!=tmpobj.oldValue||tmpobj.oldValue=='') needSearch = true;
		if(tmpobj.className==null||tmpobj.className.indexOf("selcomm")==-1){
			isOldSelComm = true;
			break;
		}
	}
	//如果条件不为空则做预先查询
	var isFind = false;
	var returnarr = null;
	//edit by zhuxw 
	//if(cond1!=" 1=1 "&&needSearch&&event.srcElement.forcePop!=true&&preSearch&&isOldSelComm == false){
	
	if(cond1!=" 1=1 "&&window.event.keyCode ==13){
	    selCommArgs[12] = cond1;
	    returnarr =  window.showModalDialog(ctxPath+"/common/select.shtml?action=doPost&cmdFlag=preSearchFrame&tableId="+selCommArgs[1]+"&selCommId="+selCommArgs[0]+"&isShowSearchLayer="+selCommArgs[10]+"&specialTable="+selCommArgs[9]+"&showQYTree="+showModel,window,"dialogHeight:50px;dialogWidth:200px;help:no;scroll:no;status:no;center:yes;resizable:no");
		isFind = (returnarr[0]>0);
	}
	//如果预先查询中没有找到记录，则弹出通用选取窗口
	if(!isFind||event.srcElement.forcePop){
		if(cond2!="")		
		selCommArgs[12] = cond2;
		//browserinfo();
		//if(navigator.Actual_Name=="Chrome")
		//{
		 returnarr = window.showModalDialog(ctxPath+"/common/select.shtml?action=doPost&cmdFlag=topFrame&tableId="+selCommArgs[1]+"&selCommId="+selCommArgs[0]+"&isShowSearchLayer="+selCommArgs[10]+"&specialTable="+selCommArgs[9]+"&showQYTree="+showModel,window,"dialogHeight:600px;dialogWidth:800px;help:no;scroll:no;status:no;center:yes;resizable:no");
		//}else
		//{
		//  returnarr = window.showModalDialog(ctxPath+"/common/select.shtml?action=doPost&cmdFlag=topFrame&tableId="+selCommArgs[1]+"&selCommId="+selCommArgs[0]+"&isShowSearchLayer="+selCommArgs[10]+"&specialTable="+selCommArgs[9]+"&showQYTree="+showModel,window,"dialogHeight:600px;dialogWidth:800px;help:no;scroll:no;status:no;center:yes;resizable:no");
		//}
		
		isFind = (returnarr[0]>0);
	}
	//如果选择了记录则将焦点移动到下一条记录
	moveSelCommFocus(isFind);
	return returnarr;
}

/*
 * 移动焦点
 * @param isNext true:移动到下一条元素 false:移动到第一条
 */
function moveSelCommFocus(isNext, elem){
	var arrSelCommElems = selCommArgs[6].split(",");
	var arrKeyElems = selCommArgs[3].split(",");
	var frmName = selCommArgs[5];
	//移动焦点到下一条元素
	try{
		if(isNext){
			//按tabIndex移动
			moveFocus(eval("document." + frmName + "."+arrSelCommElems[arrSelCommElems.length-1]), eval("document." + frmName + ".elements"), true, true);
		}else{//移动焦点到第一条元素
		
			var arrObj1 = new Array();
			for(i=0;i<arrKeyElems.length;i++){
				arrObj1[i] = eval("document." + frmName + "."+ arrKeyElems[i]);
			}
			if(!moveFocus(null, arrObj1, true, true)){
				var arrObj2 = new Array();
				for(var i=0;i<arrSelCommElems.length;i++){
						arrObj2[i] = eval("document." + frmName + "."+ arrSelCommElems[i]);
				}
			  moveFocus(null, arrObj2, true, true);
			}
		}
	}catch(e){
		//
	}
}

/*
 * 检查通用选取元素数据是否正确
 * @param formName 表单名,默认为forms[0]
 * @param seltarget 通用选取组名(触发通用选取的元素的名字),默认即为所有组
 * @return 如果通用选取数据不正确的元素数组
 */
function doSelCommValidate(formName, seltarget){
	if(formName==null) formName = "forms[0]";//默认的表单
	try{
		if(seltarget!=null&&eval("document."+formName+".all('"+seltarget+"')")==null&&eval("document."+formName+"."+seltarget)==null){
			
			return new Array();
		}
	}catch(e){
	  

		return new Array();
	}
	var elems = eval("document."+formName+".elements");
	var elemArr = new Array();
	var j = 0;
	if(seltarget==null) seltarget = "";
	//查询出通用选取框数据不正确的元素
	for(var i=0;i<elems.length;i++){
		if(elems[i]!=null&&elems[i].seltarget!=""&&elems[i].seltarget!=null){
			if(elems[i].oldValue!=elems[i].value){
				elemArr[j++] = elems[i];
				break;
			}
		}
	}
	//如果没有不正确的则返回空数组
	if(elemArr==null||elemArr.length==0) return elemArr;
	//将数据不正确的元素同一组的通用选取的元素放到对象数组中
	for(var i=0;i<elems.length;i++){
		if(elems[i]!=null&&elems[i].seltarget!=""&&elems[i].seltarget!=null){
			if(elemArr[0].seltarget == elems[i].seltarget&&elemArr[0] != elems[i]){
			    
				elemArr[j++] = elems[i];
			}
		}
	}
	//返回对象数组
	return elemArr;
}

/*
 * 对通用选取数据不符的元素进行处理
 * @param elems 一组通用选取数组
 * @param operType 处理类型 0:弹出通用选取重新选取 1:恢复上次选择的值 2:清空
 */
function doSelComm(elems, operType){
	if(elems==null||elems.length==0) return;
	switch(operType){
		case 0:
		  //触发事件弹出通用选取框
			eval("elems[0].form."+elems[0].seltarget+".click()");
			break;
		case 1:
		  //将原值赋回给元素
			for(var i=0;i<elems.length;i++){
				elems[i].value=elems[i].oldValue;
			}
			break;
		case 2:
		  //将元素的值均置空
			for(var i=0;i<elems.length;i++){
				elems[i].value="";
				elems[i].oldValue="";
			}
			break;
			//默认弹出通用选取框
		default:
			eval("elems[0].form."+elems[0].seltarget+".click()");
	}
}
/**
 * 移动焦点
 * @param elem 焦点移动的起点,不包括本元素,默认从表单第一个元素开始
 * @param elems 焦点移动的范围,默认为表单中的所有元素
 * @param direction 方向 true:正向 false:反向
 * @param isMoveByTabIndex 是否按照tabIndex移动
 * @return 焦点移动到的元素，如果没有移动到某个元素则返回null
 */
function moveFocus(elem, elems, direction, isMoveByTabIndex){
	//try{
		if(elems==null){
			if(elem==null){
				elems = document.forms[0].elements;
			}else{
				elems = elem.form.elements;
			}
		}
		for(var i=0;i<elems.length;i++){
			if(elem!=null&&elems[i]!=elem){
				continue;
			}else{
				if(direction==false){
					//按tabIndex的顺序反向移动焦点
					if(isMoveByTabIndex==true&&elem.tabIndex>1){
						//当前的tabIndex
						var curTabIndex = 2;
						if(elem!=null){
							curTabIndex = elem.tabIndex;
						}
						for(var j=0;j<elems.length;j++){
							//找到当前tabIndex后的元素,置焦点并返回该元素
							if(elems[j].tabIndex==curTabIndex-1){
								//判断目标元素是否能够获得焦点
								if(elems[j].style!=null&&elems[j].style.display!='none'&&elems[j].disabled==false&&elems[j].readOnly!=true){
									if((elems[j].tagName=='INPUT'&&(elems[j].type=='text'||elems[j].type=='checkbox'||elems[j].type=='radio'))||elems[j].tagName=='TEXTAREA'||elems[j].tagName=='SELECT'){
										document.selection.empty();
										elems[j].focus();
										elems[j].focus();
										if(elems[j].tagName=='INPUT'&&elems[j].type=='text'){
												elems[j].createTextRange().select();
										}
										//返回焦点成功移动后的元素
										return elems[j];
									}
								}
								//如果不能获得焦点继续移到下一个tabIndex
								while(--curTabIndex<elems.length&&curTabIndex>=0){
									for(var n=0;n<elems.length;n++){
										if(elems[n].tabIndex==curTabIndex){
											//判断目标元素是否能够获得焦点
											if(elems[n].style!=null&&elems[n].style.display!='none'&&elems[n].disabled==false&&elems[n].readOnly!=true){
												if((elems[n].tagName=='INPUT'&&(elems[n].type=='text'||elems[n].type=='checkbox'||elems[n].type=='radio'))||elems[n].tagName=='TEXTAREA'||elems[n].tagName=='SELECT'){
													document.selection.empty();
													elems[n].focus();
													elems[n].focus();
													if(elems[n].tagName=='INPUT'&&elems[n].type=='text'){
															elems[n].createTextRange().select();
													}
													//返回焦点成功移动后的元素
													return elems[n];
												}
											}
										}
									}
								}
								break;
							}
						}
					}

					//按页面元素出现的顺序反向移动焦点
					if(elem==null) i=elems.length;
					while(--i>=0){
						try{
							if(elems[i].style!=null&&elems[i].style.display!='none'&&elems[i].disabled==false&&elems[i].readOnly!=true){
								if((elems[i].tagName=='INPUT'&&(elems[i].type=='text'||elems[i].type=='checkbox'||elems[i].type=='radio'))||elems[i].tagName=='TEXTAREA'||elems[i].tagName=='SELECT'){
									document.selection.empty();
									elems[i].focus();//在有的地方用一次focus移动焦点不成功，所以用了两次
									elems[i].focus();
									if(elems[i].tagName=='INPUT'&&elems[i].type=='text'){
											elems[i].createTextRange().select();
									}
									return elems[i];
								}
							}
						}catch(e){
							continue;
						}
					}
				}else{
					//按tabIndex的顺序正向移动焦点
					if(isMoveByTabIndex==true&&(elem==null||elem.tabIndex>0)){
						//当前的tabIndex
						var curTabIndex = 0;
						if(elem!=null){
							curTabIndex = elem.tabIndex;
						}
						for(var j=0;j<elems.length;j++){
							//找到当前tabIndex后的元素,置焦点并返回该元素
							if(elems[j].tabIndex==curTabIndex+1){
								//判断目标元素是否能够获得焦点
								if(elems[j].style!=null&&elems[j].style.display!='none'&&elems[j].disabled==false&&elems[j].readOnly!=true){
									if((elems[j].tagName=='INPUT'&&(elems[j].type=='text'||elems[j].type=='checkbox'||elems[j].type=='radio'))||elems[j].tagName=='TEXTAREA'||elems[j].tagName=='SELECT'){
										document.selection.empty();
										elems[j].focus();
										elems[j].focus();
										if(elems[j].tagName=='INPUT'&&elems[j].type=='text'){
												elems[j].createTextRange().select();
										}
										return elems[j];
									}
								}
								//如果不能获得焦点继续移到下一个tabIndex
								while(++curTabIndex<elems.length){
									for(var n=0;n<elems.length;n++){
										if(elems[n].tabIndex==curTabIndex){
											//判断目标元素是否能够获得焦点
											if(elems[n].style!=null&&elems[n].style.display!='none'&&elems[n].disabled==false&&elems[n].readOnly!=true){
												if((elems[n].tagName=='INPUT'&&(elems[n].type=='text'||elems[n].type=='checkbox'||elems[n].type=='radio'))||elems[n].tagName=='TEXTAREA'||elems[n].tagName=='SELECT'){
													document.selection.empty();
													elems[n].focus();
													elems[n].focus();
													if(elems[n].tagName=='INPUT'&&elems[n].type=='text'){
															elems[n].createTextRange().select();
													}
													return elems[n];
												}
											}
										}
									}
								}
								break;
							}
						}
					}

					//按页面元素出现的顺序正向移动焦点
					if(elem==null) i=-1;
					while(++i<elems.length){
						try{
							if(elems[i].style!=null&&elems[i].style.display!='none'&&elems[i].disabled==false&&elems[i].readOnly!=true){
								if((elems[i].tagName=='INPUT'&&(elems[i].type=='text'||elems[i].type=='checkbox'||elems[i].type=='radio'))||elems[i].tagName=='TEXTAREA'||elems[i].tagName=='SELECT'){
									document.selection.empty();
									elems[i].focus();
									if(elems[i].tagName=='INPUT'&&elems[i].type=='text'){
											elems[i].createTextRange().select();
									}
									return elems[i];
								}
							}
						}catch(e){
							continue;
						}
					}
				}
			}
			break;
		}
	//}catch(e){
		//
	//}
	return false;
}