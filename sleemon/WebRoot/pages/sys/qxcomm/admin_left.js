//目前程序最大支持三级子菜单,如果增加则添加相应的函数和对象
var oldSystemItem = null;
var oldMenuItem = null;
var oldMenuHref = null;//OA专用

//控制第一级子菜单的隐藏和显示
//如果有显示的子菜单则隐藏,并将新的子菜单展开
function switchMenu(objectID) {
	if (objectID.style.display == "none") {
		if (oldSystemItem != null) {
			oldSystemItem.style.display = "none";
		}
		oldSystemItem = objectID;
		objectID.style.display = "inline";
	} else {
		objectID.style.display = "none";
	}
}

//控制第二级子菜单的隐藏和显示
//如果有显示的子菜单则隐藏,并将新的子菜单展开
function switchSubmenu(objectID) {
	var hrefobj = event.srcElement;
	if (objectID.style.display == "none") {
		if (oldMenuItem != null) {
			oldMenuItem.style.display = "none";
			oldMenuHref.className="12dark";//OA专用
		}
		oldMenuItem = objectID;
		oldMenuHref = hrefobj;//OA专用
		objectID.style.display = "inline";
		hrefobj.className="12dark2";//OA专用
	} else {
		objectID.style.display = "none";
	}
}

//控制底层菜单条的class,如果有别的菜单条被选中,则改变其为初始状态
//将点中的菜单条的class改变(根据onClick事件)
var oldObj = null;

function switchItem() {
	if(oldObj != null) {
		oldObj.className = "12blue";
	}
	newObj = event.srcElement;
	newObj.className = "clicked";
	oldObj = newObj;
}

function openIcon(obj,url1,url2){
  if(obj.style.display=="none"){
    window.parent.frames[2].location = url1;
  }else{
    window.parent.frames[2].location = url2;
  }  
}