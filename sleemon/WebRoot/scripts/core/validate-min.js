/**<li>form check </li>
 * <li>depends: check.js,plus.js </li>
 */
var showLog = false;
var showWait = true;
var init_form = true;
var blur_form = false;
var init_text = false;
var _setDirty = false;
var _valitor_form = null;
function Log(msg, lev, type) {
	if (showLog) {
		YAHOO.log(msg, lev, type);
	}
}
// 显示日志
function showLogging() {
	showLog = true;
}
// 显示变更标识
function showDirty() {
	_setDirty = true;
}

function setDirty4Obj(inpt) {
	if (inpt.dirty) {
		return;
	}
	inpt.dirty = true;
	inpt.oldClass = inpt.className;
	inpt.className += ' invalid';
	try {
		inpt.focus();
	} catch (e) {
	}
}
function clearDirty4Obj(inpt) {
	if (inpt.dirty) {
		inpt.className = inpt.oldClass;
		inpt.dirty = false;
	}
}

function validator_log(msg, d, m) {
	var model = m || "validate";
	var debug = d || "info";// info,debug,warn,error
	Log(msg, debug, model);
}
function InitFormValidator(f) {
	validator_log("InitFormValidator.", "info", "validate");
	switch (typeof(f)) {
		case "string" :
			FormCheckInit((document.getElementById(f) || document.forms[f]));
			break;
		case "object" :
			FormCheckInit(f);
			break;
		default :
			FormCheckInit(document.forms[0]);
	}
}
var span_mustinput = "<span class='validate'>&nbsp;*</span>";
// 初始化校验
function FormCheckInit(form) {
	enterKeyListener(form)
	init_form = true;
	var el, name, val,tempMustinput,tempPttern;
	for (var i = 0, j = form.elements.length; i < j; i++) {
	    el = form.elements[i];
		name = el.name;
		val = el.value;
		tempMustinput=$(el).attr("mustinput");
		tempPttern=$(el).attr("inputtype");
		if (name) {
			if(el.readOnly){
				if("cmmx"!=el.className){
				   el.className = ' readonly '+el.className;
				}
			}
			if (tempPttern) {
				// 必输项
				if (tempMustinput && (tempMustinput == true||tempMustinput == 'true')) {
					if (el.mustinputSpan) {
					} else {
						el.insertAdjacentHTML("afterEnd", span_mustinput);
						el.mustinputSpan = true;
					}
				}
				// format
				switch (tempPttern.toLowerCase()) {
					case 'money' :
						initMoneyText(el);
						break;
					case 'date' :
						break;
					case 'float' :
					    if(val==null||val=="")
					    //  el.value=0;
						break;
					case 'zffloat' :
					    if(val==null||val=="")
					    //  el.value=0;
						break;
					case 'number' :
					    if(val==null||val=="")
					    //  el.value=0;
						break;		
					case 'int' :
					    if(val==null||val=="")
					     // el.value=0;
						break;
					case 'string':
						if (init_text) {
							initTextField(el);
						} else if ((el.tagName == "TEXTAREA" || el.tagName == "INPUT")
								&& (el.maxlength || el.maxLength)) {
								
							el.onkeydown = function() {
								changeTextArea(this);
							};
							el.onkeyup = function() {
								changeTextArea(this);
							}
							el.oVal = el.value;
						}
						break;
				}
			}
		}
	}
	//add by zhuxw 2013-08-12
	//控制备注的最大值
	/**
	 $("textarea").keyup(function(){
	 var area=$(this);
	 var max=parseInt(area.attr("maxlength"),10); 
	  alert(max);
	 if(max>0){ 
	  alert(area.val().length);
	  if(area.val().length>max){
	     area.val(area.val().substr(0,max));
	      var msg = "对不起，超过最大长度:"+max+"!";
		  //chkCanErrMsg(this, msg);
		  alert(msg);
	      return false;
	    } 
	  } 
	  });
	  **/
	init_form = false;
}

// add by zhxuw 对单行元素初始化
function trCheckInit(obj) {
	init_form = true;
	var el, name, val,tempMustinput,tempPttern;
	$(obj).each(function(i,k){
	    el = this;
		name = el.name;
		val = el.value;
		tempMustinput=$(el).attr("mustinput");
		tempPttern=$(el).attr("inputtype");
		if (name) {
			if(el.readOnly){
				if("cmmx"!=el.className){
				   el.className = ' readonly '+el.className;
				}
			}
			if (tempPttern) {
				// 必输项
				if (tempMustinput && (tempMustinput == true||tempMustinput == 'true')) {
					if (el.mustinputSpan) {
					} else {
						el.insertAdjacentHTML("afterEnd", span_mustinput);
						el.mustinputSpan = true;
					}
				}
				// format
				switch (tempPttern.toLowerCase()) {
					case 'money' :
						initMoneyText(el);
						break;
					case 'date' :
						break;
					case 'float' :
					    if(val==null||val=="")
					    //  el.value=0;
						break;
					case 'zffloat' :
					    if(val==null||val=="")
					   //   el.value=0;
						break;
					case 'number' :
					    if(val==null||val=="")
					   //   el.value=0;
						break;		
					case 'int' :
					    if(val==null||val=="")
					   //   el.value=0;
						break;
					case 'string':
						if (init_text) {
							initTextField(el);
						} else if ((el.tagName == "TEXTAREA" || el.tagName == "INPUT")
								&& (el.maxlength || el.maxLength)) {
								
							el.onkeydown = function() {
								changeTextArea(this);
							};
							el.onkeyup = function() {
								changeTextArea(this);
							}
							el.oVal = el.value;
						}
						break;
				}
			}
		}
	
	});
	
	init_form = false;
}

function changeTextArea(obj) {
	if (init_form)
		return;

	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}

function FormValidate(f) {
	validator_log("FormValidate", "info", "validate");
	switch (typeof(f)) {
		case "string" :
			return FormCheck((document.getElementById(f) || document.forms[f]));
		case "object" :
			return FormCheck(f);
		default :
			return FormCheck(document.forms[0]);
	}
}
// 校验单一页面控件
function InputCheck(inpt) {
	validator_log("InputCheck Input:\t" + $(inpt).attr("name"), "info", "validate");
	if (inpt.readOnly) {
		//return true;
	}
	var tempCheck=$(inpt).attr("autocheck");
	var tempRequir=$(inpt).attr("mustinput");
	var tempVal=$(inpt).val(); 
	var tempLabel=$(inpt).attr("label");
	var tempinputtype=$(inpt).attr("inputtype");
	if (tempCheck && (tempCheck == true||tempCheck== "true")) {
		// 必输项校验
		if (tempVal.trim().length == 0) {
			if (tempRequir && (tempRequir == true||tempRequir == "true")) {
				var msg = GetSpanMessage(MsgMustInput, tempLabel, inpt.params);
				chkCanErrMsg(inpt, msg);
				return false;
			}
			return true;
		}
		// 校驗值內容
		if (tempinputtype) {
			switch (tempinputtype.toLowerCase()) {
				case "int" :
					validator_log("InputCheck Integer:\t", "info", "validate");
					// 校验整型内容
					if (!CheckIntegerInput(inpt)) {
						return false;
					}
					break;
				case "float" :
					validator_log("InputCheck double:\t", "info", "validate");
					// 校验整型内容
					if (!CheckFloatInput(inpt)) {
						return false;
					}
					break;
					
				//=============begin=======	
				case "xiaoshu" :
					validator_log("InputCheck double:\t", "info", "validate");
					// 校验整型内容
					if (!CheckFloatInput(inpt)) {
						return false;
					}
					break;
					
			  //==============end========		
					
				case "zffloat" :
					validator_log("InputCheck double:\t", "info", "validate");
					// 校验整型内容
					if (!CheckZFFloatInput(inpt)) {
						return false;
					}
					break;
				case "number" :
					validator_log("InputCheck number:\t", "info", "validate");
					// 校验整型内容
					if (!CheckNumberInput(inpt)) {
						return false;
					}
					break;
				case "date" :
					validator_log("InputCheck Date:\t", "info", "validate");
					if (!CheckYearMonth(inpt)) {
						return false;
					}
					if (!CheckDate(inpt)) {
						return false;
					}
					break;
				default :
				case "string" :
					validator_log("InputCheck String:\t", "info", "validate");
					// 校验字符串内容
					if (!CheckStringInput(inpt)) {
						return false;
					}
					break;
				case "money" :
					break;
			}
		}
	}
	return true;
}
var SpanErrorMsgID = "SpanErrorMsg";
var MsgMustInput = "请输入{0}!";
var MsgMinLength = "{0}长度不能小于{1}位!";
var MsgMustInputAll = "{0}必须输入{1}位!";
var MsgNeedSetMaxLength = "{0}控件需要设置maxlength属性!";
function newFormCheck(chkform) {
	//validator_log("FormCheck", "info", "validate");
	//ClearSpanMessage();
	//_valitor_form = form;
	var flag = true;
    var inputs = $("#"+chkform).find("input");
	inputs.each(function(){
		if (!InputCheck(this)) {
			flag = false;
			return flag;
		}
	});
	return flag;
//	if (showWait)
//		showWaitPanel('');
}
//查询页面Form过滤 by zhuxw
function qryFormCheck(chkform) {
	var flag = true;
    var inputs = $("#"+chkform).find("input");
	inputs.each(function(){
		if (!CheckStringInput(this)) {
			flag = false;
			return flag;
		}
	});
	return flag;
}
function FormCheck(form) {
	validator_log("FormCheck", "info", "validate");
	ClearSpanMessage();
	_valitor_form = form;
	var el, name, val, disabled, data = '', hasSubmit = false;
	for (var i = 0, j = form.elements.length; i < j; i++) {
		el = form.elements[i];
		disabled = el.disabled;
		name = el.name;
		val = el.value;
		if (el.hidden) {
			continue;
		}
		if (!disabled && name && el.pattern) {
			switch (el.pattern.toLowerCase()) {
				case 'int' :
				case "float" :
				case "number" :
				case 'money' :
				case 'date' :
				case 'string' :
					if (!InputCheck(el)) {
						return false;
					}
			}
		}
	}
//	if (showWait)
//		showWaitPanel('');
	return true;
}
var errDiv;
function setErrorContainer(divId) {
	// surport mult-area to show log messages
	errDiv = document.all(divId);
}
function hasErrorContainer() {
	return errDiv;
}
function WriteErrMessage(elem, errMsg) {
	var sb = [];
	sb[sb.length] = 'try{';
	sb[sb.length] = 'closeWindow();';
	if (elem.id) {
		sb[sb.length] = ' var _elem=document.getElementById("' + elem.id
				+ '");';
	} else {
		sb[sb.length] = ' var _elem=_valitor_form.' + elem.name + ';';
	}
	sb[sb.length] = '_elem.focus();';
	sb[sb.length] = '_elem.select();';
	sb[sb.length] = '}catch(e){}';
	var func = sb.join("");
	var msg = '<span class="FontRed">' + errMsg + '</span>';
	_showMsgPanel(msg, "系统提示", func);
}
function GetSpanMessage(msg, label, params) {
	var label = label ? "'" + label + "'" : "";
	var retMessage = msg.replace("{0}", label);
	if (params) {
		for (var i = 1; i <= params.length; i++) {
			var paraIndex = "{" + i + "}"; // {n}
			retMessage = retMessage.replace(paraIndex, params[i - 1]);
		}
	}
	return retMessage;
}
function ClearSpanMessage() {
	validator_log("ClearSpanMessage.", "info", "validate");
	if (hasErrorContainer()) {
		errDiv.innerText = "";
		try {
			if (errDiv.length == 2) {
				errDiv[0].innerHTML = "";
				errDiv[1].innerText = "";
			}
		} catch (e) {
		}
		return;
	}
	var spans = getByNameAndTagName(SpanErrorMsgID, "span");
	if (spans == null) {
		return;
	}
	// validator_log("ClearSpanMessage:t" + spans.length, "info", "validate");
	for (var i = 0, j = spans.length; i < j; i++) {
		spans[i].innerHTML = "";
	}
}
function getByNameAndTagName(name, tagName) {
	var s = document.getElementsByTagName(tagName);
	if (!s) {
		return null;
	}
	var j = 0;
	var ret = new Array();
	for (var i = 0, k = s.length; i < k; i++) {
		if (s[i].name == name) {
			ret[j] = s[i];
			j++;
		}
	}
	if (j == 0) {
		return null;
	}
	return ret;
}
var NOT_INPUT_NUMBER = "{0}不允许输入数字!";
var NOT_INPUT_CHINESE = "{0}不允许输入汉字!";
var NOT_INPUT_UCHAR = "{0}不允许输入大写字母!";
var NOT_INPUT_LCHAR = "{0}不允许输入小写字母!";
var NOT_INPUT_CHAR = "{0}不允许输入字母!";
var regNm = new RegExp(/[0-9]/g);
var regUChar = new RegExp(/[A-Z]/g);
var regLChar = new RegExp(/[a-z]/g);
var regChinese = new RegExp(/[\u4E00-\u9FA5]/g);

function CheckStringInput(inpt) {
	var value = $(inpt).val();
	var allowHTML= $(inpt).attr("allowHTML");
	var valueType= $(inpt).attr("valueType");
	var valueLabel= $(inpt).attr("label");
	// 过滤特殊字符
	if (allowHTML) {
	} else {
		try {
			value = value.replace(/\'/g, "");
			value = value.replace(/\·/g, "");
			value = value.replace(/\</g, "＜");
			value = value.replace(/\>/g, "＞");
			value = value.replace(/\\/g, "\\\\");
			value = value.replace(/\$/g, "");
			value = value.replace(/\%/g, "");
			value = value.replace(/\r\n/g, "");
			//value = value.replace(/\//g, "");
			while (value.indexOf('"') >= 0) {
				value = value.replace('"', '“');
				value = value.replace('"', '”');
			}
		} catch (e) {
		}
		$(inpt).val(value);
	}
	if (valueType) {
	    var vtypes = eval("({" + valueType + "})");
		var hasNumber = (vtypes.number == null||vtypes.number== "undefined")?true:vtypes.number;
		var hasUChar = (vtypes.upperChar == null||vtypes.upperChar== "undefined")?true:vtypes.upperChar; 
		var hasLChar = (vtypes.lowerChar == null||vtypes.lowerChar== "undefined")?true:vtypes.lowerChar;  
		var hasChinese =(vtypes.chinese == null||vtypes.chinese== "undefined")?true:vtypes.chinese;
		var hasDolt = vtypes.dolts != "undefined";
		var notNumber = !hasNumber && value.match(regNm);
		var notUChar = !hasUChar && value.match(regUChar);
		var notLChar = !hasLChar && value.match(regLChar);
		var notChina = !hasChinese && value.match(regChinese);
		if (notNumber) {
			// NOT_INPUT_NUMBER
			var msg = GetSpanMessage(NOT_INPUT_NUMBER, valueLabel, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
		if (!hasUChar && !hasLChar && (notUChar || notLChar)) {
			// NOT_INPUT_CHAR
			var msg = GetSpanMessage(NOT_INPUT_CHAR, valueLabel, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
		if (notUChar) {
			var msg = GetSpanMessage(NOT_INPUT_UCHAR, valueLabel, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
		if (notLChar) {
			var msg = GetSpanMessage(NOT_INPUT_LCHAR, valueLabel, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
		if (notChina) {
			var msg = GetSpanMessage(NOT_INPUT_CHINESE, valueLabel, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	 if (!mustInputAllCheck(inpt)) {
		return false;
	 }
	return true;
}

//add by zhuxw
function chkCanErrMsg(inpt, msg){
  if(parent.WriteErrMessage!=null)
	{
		parent.WriteErrMessage(inpt, msg);
	}else
	{
		WriteErrMessage(inpt, msg);
	}
}

var onlyFloat = new RegExp('^\\d+(\\.\\d{1,9})?$');
var ONLY_FLOAT = "{0}只允许输入非负数字!";
function CheckFloatInput(inpt) {
    var tempLable=$(inpt).attr("label");
	var val = $(inpt).val();
	if (!onlyFloat.test(val)) {
		var msg = GetSpanMessage(ONLY_FLOAT, tempLable, inpt.params);
		chkCanErrMsg(inpt, msg);
		return false;
	}
	//edit by zhuxw 
	var tempValType=$(inpt).attr("valueType");
	var len="";
	var pointLen="";
	if(undefined != tempValType&&tempValType!=null&&tempValType!="")
	{
	     var lenArr = tempValType.split(",");
	     if(lenArr.length>0)
	     {
	       len=lenArr[0];
	     }
	     if(lenArr.length>1)
	     {
	       pointLen=lenArr[1];
	     }
	}
	//整数位判断
	if(undefined != len&&len!=""){
		if(val.indexOf(".")>len || (-1==val.indexOf(".")&&val.length>len)){
			var FLOAT_IntegerLen = "{0}整数不得超过"+len+"位!";
			var msg = GetSpanMessage(FLOAT_IntegerLen, tempLable, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	//小数位判断
	if(undefined != pointLen&&pointLen!=""&&val.indexOf(".")!=-1){
		if((val.length-val.indexOf(".")-1)>pointLen){
			var FLOAT_IntegerLen = "{0}小数位不得超过"+pointLen+"位!";
			var msg = GetSpanMessage(FLOAT_IntegerLen, tempLable, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	
	validator_log("CheckIntegerInput.", "info", "validate");
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}

//可输入正负float型
var onlyZFFloat = new RegExp('^[+-]?\\d+(\\.\\d{1,9})?$');
var ONLY_ZFFLOAT = "{0}只允许输入数字!";
function CheckZFFloatInput(inpt) {
    var tempLable=$(inpt).attr("label");
	var val = $(inpt).val();
	if (!onlyZFFloat.test(val)) {
		var msg = GetSpanMessage(ONLY_ZFFLOAT, tempLable, inpt.params);
		chkCanErrMsg(inpt, msg);
		return false;
	}
	//edit by zhuxw 
	var tempValType=$(inpt).attr("valueType");
	var len="";
	var pointLen="";
	if(undefined != tempValType&&tempValType!=null&&tempValType!="")
	{
	     var lenArr = tempValType.split(",");
	     if(lenArr.length>0)
	     {
	       len=lenArr[0];
	     }
	     if(lenArr.length>1)
	     {
	       pointLen=lenArr[1];
	     }
	}
	val=val.replace("-","");
	if(undefined != len&&len!=""){
		if(val.indexOf(".")>len || (-1==val.indexOf(".")&&val.length>len)){
			var FLOAT_IntegerLen = "{0}整数不得超过"+len+"位!";
			var msg = GetSpanMessage(FLOAT_IntegerLen, tempLable, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	//小数位判断
	if(undefined != pointLen&&pointLen!=""&&val.indexOf(".")!=-1){
		if((val.length-val.indexOf(".")-1)>pointLen){
			var FLOAT_IntegerLen = "{0}小数位不得超过"+pointLen+"位!";
			var msg = GetSpanMessage(FLOAT_IntegerLen, tempLable, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	validator_log("CheckIntegerInput.", "info", "validate");
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}

var onlyNumber = new RegExp('^-?\\d+(\\.\\d{1,9})?$');
var ONLY_NUMBER = "{0}只允许输入数字!";
function CheckNumberInput(inpt) {
	var val = inpt.value;
	if (!onlyNumber.test(val)) {
		var msg = GetSpanMessage(ONLY_NUMBER, inpt.label, inpt.params);
		chkCanErrMsg(inpt, msg);
		return false;
	}
	
	if(undefined != inpt.integer){
		var len = inpt.integer;
		if(val.indexOf(".")>len || (-1==val.indexOf(".")&&inpt.value.length>len)){
			var NUMBER_IntegerLen = "{0}整数不得超过"+len+"位!";
			var msg = GetSpanMessage(NUMBER_IntegerLen, inpt.label, inpt.params);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	
	validator_log("CheckNumberInput.", "info", "validate");
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}

var onlyNm = new RegExp(/[^0-9]/g);
var ONLY_INTEGER = "{0}只允许输入正整数!";
function CheckIntegerInput(inpt) {
    var tempVale=$(inpt).val();
    var tempLabel=$(inpt).attr("label");
	if (tempVale.match(onlyNm)) {
		var msg = GetSpanMessage(ONLY_INTEGER, tempLabel, inpt.params);
		chkCanErrMsg(inpt, msg);
		return false;
	}
	validator_log("CheckIntegerInput.", "info", "validate");
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}
var MIN_DATE = "{0}不能小于{1}!";
var MAX_DATE = "{0}不能大于{1}!";
var DATE_MSG = "{0}格式必须为yyyy/MM/dd!";
var regDate = /^(19[3-9]\d|2[01]{2}\d)\/(0[1-9]|1[012])\/(0[1-9]|[1-2]\d|3[01])$/;
function CheckDate(inpt) {
	if (inpt.value.length > 0) {
		if (inpt.formatter == "yyyy/MM/dd") {
			var fmt = inpt.formatter ? inpt.formatter : "yyyy/MM/dd";
			if (!regDate.exec(inpt.value)) {
				var msg = GetSpanMessage(DATE_MSG, inpt.label, null);
				chkCanErrMsg(inpt, msg);
				return false;
			}
			var dt = inpt.value.toDate(fmt).getTime();
			if (inpt.minDate) {
				var min = inpt.minDate.length > 0 ? inpt.minDate.toDate(fmt)
						.getTime() : Number.NEGATIVE_INFINITY;
				if (min > dt) {
					var msg = GetSpanMessage(MIN_DATE, inpt.label,
							[inpt.minDate]);
					chkCanErrMsg(inpt, msg);
					return false;
				}
			}
			if (inpt.maxDate) {
				var max = inpt.maxDate.length > 0 ? inpt.maxDate.toDate(fmt)
						.getTime() : Number.POSITIVE_INFINITY;
				if (max < dt) {
					var msg = GetSpanMessage(MAX_DATE, inpt.label,
							[inpt.maxDate]);
					chkCanErrMsg(inpt, msg);
					return false;
				}
			}
		}
	}
	return true;
}
var yearMonth = /^(19[3-9]\d|2[01]{2}\d)\/(0[1-9]|1[012])$/;
var YEAR_MONTH = "{0}格式必须为yyyy/MM";
function CheckYearMonth(inpt) {
	if (inpt.value.length > 0) {
		if (inpt.formatter == "yyyy/MM") {
			if (!yearMonth.exec(inpt.value)) {
				var msg = GetSpanMessage(YEAR_MONTH, inpt.label, null);
				chkCanErrMsg(inpt, msg);
				return false;
			}
			var curValue = inpt.value.replace(/-|\//g, "");
			var checkMin = false;
			var checkMax = false;
			if (inpt.startDateField) {
				var start = document.getElementById(inpt.startDateField);
				if (start && start.value.length >= 7) {
					checkMin = true;
					var stValue = start.value.replace(/-|\//g, "").substr(0, 6);
					if (curValue < stValue) {
						var msg = GetSpanMessage(MIN_DATE, inpt.label,
								[stValue]);
						chkCanErrMsg(inpt, msg);
						return false;
					}
				}
			}
			if (inpt.endDateField) {
				var end = document.getElementById(inpt.endDateField);
				if (end && end.value.length >= 7) {
					checkMax = true;
					var edValue = end.value.replace(/-|\//g, "").substr(0, 6);
					if (curValue > edValue) {
						var msg = GetSpanMessage(MAX_DATE, inpt.label,
								[edValue]);
						chkCanErrMsg(inpt, msg);
						return false;
					}
				}
			}
			if (inpt.minDate) {
				var minValue = inpt.minDate.replace(/-|\//g, "").substr(0, 6);
				if (curValue < minValue) {
					var msg = GetSpanMessage(MIN_DATE, inpt.label,
							[inpt.minDate]);
					chkCanErrMsg(inpt, msg);
					return false;
				}
			}
			if (inpt.maxDate) {
				var maxValue = inpt.maxDate.replace(/-|\//g, "").substr(0, 6);
				if (curValue > maxValue) {
					var msg = GetSpanMessage(MAX_DATE, inpt.label,
							[inpt.maxDate]);
					chkCanErrMsg(inpt, msg);
					return false;
				}
			}
		}
	}
	return true;
}
var StringTooLonger = "{0}内容超出{1}位!";
mustInputAllCheck = function(inpt) {
	var maxLength = inpt.maxlength;
	if (maxLength) {
	} else {
		maxLength = inpt.maxLength;
	}
	var a = stringLength(inpt.value);
	if (maxLength) {
		if (inpt.mustInputAll) {
			if (maxLength * 1 > a) {
				var msg = GetSpanMessage(MsgMustInputAll, inpt.label,
						[maxLength]);
				chkCanErrMsg(inpt, msg);
				return false;
			}
		}
		if (maxLength < a) {
			var msg = GetSpanMessage(StringTooLonger, inpt.label, [maxLength]);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	} else {
		if (inpt.mustInputAll) {
			var msg = GetSpanMessage(MsgNeedSetMaxLength, inpt.name, null);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	var minLength = inpt.minlength;
	if (minLength) {
	} else {
		minLength = inpt.minLength;
	}
	if (minLength) {
		if (minLength * 1 > a) {
			var msg = GetSpanMessage(MsgMinLength, inpt.label, [minLength]);
			chkCanErrMsg(inpt, msg);
			return false;
		}
	}
	return true;
};
function stringLength(str) {
	   return str==null?"":str.replace(/[^\x00-\xff]/g, "***").length;
}
fmtMoney = function(n, c, d, t) {
	var p = n < 0 ? "-" : "";
	n = n.toFixed(c);
	var m = (c = Math.abs(c) + 1 ? c : 2, d = d || ",", t = t || ".", /(\d+)(?:(\.\d+)|)/
			.exec(n + "")), x = m[1].length > 3 ? m[1].length % 3 : 0;
	return p + (x ? m[1].substr(0, x) + t : "")
			+ m[1].substr(x).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + (+m[2] || 0).toFixed(c).substr(2) : "");
};
