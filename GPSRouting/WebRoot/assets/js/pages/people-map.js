
var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));













/**
 * 自定义地图API组件
 * 1.刷新组件
 * 2.显示区域组件
 * 3.搜索线路组件
 */
function refreshControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(5, 5);
}
refreshControl.prototype = new BMap.Control();


refreshControl.prototype.initialize = function(map){
	// 创建一个DOM元素

	var button_refresh = document.createElement("button");
	// 添加文字说明
	var i = document.createElement("i");
	i.setAttribute("class", "fa fa-refresh")

	// 设置样式
	button_refresh.setAttribute("class", "btn btn-round-grey");
	button_refresh.setAttribute("type", "button");
	button_refresh.appendChild(i);
	// 绑定事件,点击一次放大两级
	button_refresh.onclick = function(e){
		map.clearOverlays();   
	}
	// 添加DOM元素到地图中
	map.getContainer().appendChild(button_refresh);
	// 将DOM元素返回
	return button_refresh;
}

function equipControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(60, 5);
}
equipControl.prototype = new BMap.Control();


equipControl.prototype.initialize = function(map){

	var button_euip = document.createElement("button");
//	添加文字说明
	var i = document.createElement("i");
	i.setAttribute("class", "fa fa-gears")

//	设置样式
	button_euip.setAttribute("class", "btn btn-round-grey");
	button_euip.setAttribute("type", "button");
	button_euip.appendChild(i);
//	绑定事件,点击一次放大两级
	button_euip.onclick = displayEquip;
//	添加DOM元素到地图中
	map.getContainer().appendChild(button_euip);
//	将DOM元素返回
	return button_euip;
}

function searchControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(230, 5);
}
searchControl.prototype = new BMap.Control();


searchControl.prototype.initialize = function(map){
	
	var button_search = document.createElement("button");
	button_search.setAttribute("class", "btn btn-round-grey");
	button_search.setAttribute("type", "button");
	var i = document.createElement("i");
	i.setAttribute("class", "fa fa-search");
	button_search.appendChild(i);
	button_search.onclick = searchRoute;
	map.getContainer().appendChild(button_search);
//	将DOM元素返回
	return button_search;
}

function select(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(120, 10);
}
select.prototype = new BMap.Control();


select.prototype.initialize = function(map){

	var select = document.createElement("select");
//	添加文字说明
//<div class="form-group">
	

//	设置样式
	select.setAttribute("size", "1");
	select.setAttribute("id", "select_route_list");
	select.setAttribute("class", "form-control");
	select.style.width="100px";
//	绑定事件,点击一次放大两级
//	添加DOM元素到地图中
	map.getContainer().appendChild(select);
//	将DOM元素返回
	return select;
}

var rfrs = new refreshControl();
var equip = new equipControl();
var sel = new select();
var ser = new searchControl();
map.addControl(rfrs);
map.addControl(equip);
map.addControl(sel);
map.addControl(ser);






/**
 * 获取session并初始化页面
 */

var session = new Object();
$.getJSON(
		"GetSessionServlet",
		function(data){
			session.branch_type=data.branch_type;
			session.branch_id=data.branch_id;
			initRouteList();
		}
	);
//初始化时间段
$("#start-date").val(CurrentDate());
$("#end-date").val(CurrentDate());
//初始化线路选择列表
function initRouteList(){
	
	if(session.branch_id!=null){
		var url = "GetAllRegionServlet?index=route"
		if(session.branch_type!="管理"){
			url = url +"&branch_id="+session.branch_id;
		}
		$.getJSON(
				url,
				function(data){
					var option_all = $("<option>").val('all').text('所有');

					$("#select_route_list").append(option_all);
					for(var i = 0; i < data.length;i++){
						var rt = data[i];
						var option = $("<option>").val(rt.id).text(rt.branch+' '+rt.name);

						$("#select_route_list").append(option);
					}
				}
			);
	}
}




var paragenerid = getUrlParam('id');
if(paragenerid==null){
	initialAllPosition();
	var int=self.setInterval("initialAllPosition()",2000);
}
else{
	$("#search-trace").show();	
	initialOnePosition();
	var int=self.setInterval("initialOnePosition()",2000);
}


var peoplemarkers = new Array();
var peoplemarker=null;
function initialAllPosition(){
	var peoplearray = new Array();
	$.getJSON(
			"GetPositionServlet",
			function(data){
				for(var i = 0; i<data.length; i++){
					var jso = data[i];
					//alert(parseFloat(gps[0]));
					var point = new BMap.Point(jso.longitude,jso.latitude);
					var people = {
							id:jso.id,
							name:jso.gener,
							branch:jso.branch,
							team:jso.team,
							point:point

					}
					peoplearray[i]=people;
				}
				//alert(regionarray.length);
				var markerlength = peoplemarkers.length;
				for(var i = 0; i<markerlength;i++){
					map.removeOverlay(peoplemarkers.pop());
				}
				for(var i = 0; i<peoplearray.length;i++){
					var pp = peoplearray[i];
					peoplemarkers[i] = new BMap.Marker(pp.point);
					map.addOverlay(peoplemarkers[i]);
					var content  = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+pp.name+"</h4>"+
					"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>ID:"+pp.id+"</h6>" + 
					"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>所在部门:"+pp.branch+"</h6>" + 
					"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>职位:"+pp.team+"</h6>" + 
					"<a class='btn btn-default pull-right' href = 'data-position.jsp?id="+pp.id+"'>详情及轨迹</a>"+
					"</div>";

					addClickHandler(content,peoplemarkers[i]);
				}

			}
	);
}

function initialOnePosition(){
	var peoplearray = new Array();
	$.getJSON(
			"GetPositionServlet?index=gener&gener_id="+paragenerid,
			function(data){
				//alert(parseFloat(gps[0]));
				var point = new BMap.Point(data.longitude,data.latitude);
				var people = {
						id:data.id,
						name:data.gener,
						branch:data.branch,
						team:data.team,
						point:point

				}

				//alert(regionarray.length);
				if(peoplemarker!=null){
					map.removeOverlay(peoplemarker);
				}

				peoplemarker = new BMap.Marker(people.point);
				map.addOverlay(peoplemarker);
				var content  = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+people.name+"</h4>"+
				"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>ID:"+people.id+"</h6>" + 
				"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>所在部门:"+people.branch+"</h6>" + 
				"<h6 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>职位:"+people.team+"</h6>" + 
				"</div>";

				addClickHandler(content,peoplemarker);

			}
	);
}


function addClickHandler(content,marker){
	marker.addEventListener("click",function(e){
		openInfo(content,e)}
	);
}
function openInfo(content,e){
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
}





function displayEquip(){	
	var paramBranch_id = getUrlParam('branch_id');
	var regionarray = new Array();
	
	if(session.branch_id!=null){
		var url = "GetAllRegionServlet"
		if(session.branch_type=="管理"){
			if(paramBranch_id==null||paramBranch_id=='all'){
				url = url + "?index=all";
			}
			else{
				url = url +"?index=branch&branch_id="+paramBranch_id;
			}
		}
		else{
			url = url +"?index=branch&branch_id="+session.branch_id;
		}
		$.getJSON(
			url,
			function(data){
				for(var i = 0; i<data.length; i++){
					var jso = data[i];
					var gps = jso.gps.split(",");
					//alert(parseFloat(gps[0]));
					var point = new BMap.Point(parseFloat(gps[0]),parseFloat(gps[1]));
					var region = {
							id:jso.id,
							name:jso.name,
							branch:jso.branch,
							intro:jso.intro,
							type:jso.type,
							point:point
							
					}
					regionarray[i]=region;
				}
				//alert(regionarray.length);
				for(var i = 0; i<regionarray.length;i++){
					var rg = regionarray[i];
					var marker = new BMap.Marker(rg.point,{icon:myIcon});
					map.addOverlay(marker);
					var content  = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+rg.name+"</h4>"+
					"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>"+rg.intro+"</p>" + 
					"</div>";
					 
					addClickHandler(content,marker);
				}
				function addClickHandler(content,marker){
					marker.addEventListener("click",function(e){
						openInfo(content,e)}
					);
				}
				function openInfo(content,e){
					var p = e.target;
					var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
					var infoWindow = new BMap.InfoWindow(content);  // 创建信息窗口对象 
					map.openInfoWindow(infoWindow,point); //开启信息窗口
				}
			}
		);
	}

}
var trace = null
$("#search-trace-button").on('click',function(){
	var start = $("#start-date").val() + ' '+ $("#start-time").val();
	var end = $("#end-date").val() + ' ' + $("#end-time").val();
	$.getJSON(
			"GetRouteServlet?index=gener&gener_id="+paragenerid+"&start="+start+"&end="+end,
			function(data){
				if (trace!=null){
					map.removeOverlay(trace);
				}
				var pointArray = new Array();
				for(var i = 0; i < data.length; i++){
					var temp = data[i];
					pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
				}
				trace = new BMap.Polyline(pointArray,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
				map.addOverlay(trace);
			}
	);
});


function searchRoute(){
	var id = $("#select_route_list").find("option:selected").val();
	if (id = 'all'){
		var chilcount = $("#select_route_list").children('option').length;
	    for(var i = 1; i< chilcount; i++){
			var $singleOpt = $("#select_route_list").find('option').eq(i); //获取option的内容
	        var val = $singleOpt.val();
	        if(val != "all"){
	        	$.getJSON(
	    				"GetRouteServlet?index=region&region_id="+val,
	    				function(data){
	    					var pointArray = new Array();
	    					for(var i = 0; i < data.length; i++){
	    						var temp = data[i];
	    						pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
	    					}
	    					var polyline = new BMap.Polyline(pointArray,{strokeColor:"green", strokeWeight:4, strokeOpacity:1});
	    					map.addOverlay(polyline);
	    				}
	    		);
	        }
	    }
	}
	else{
		$.getJSON(
				"GetRouteServlet?index=region&region_id="+id,
				function(data){
					var pointArray = new Array();
					for(var i = 0; i < data.length; i++){
						var temp = data[i];
						pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
					}
					var polyline = new BMap.Polyline(pointArray,{strokeColor:"green", strokeWeight:4, strokeOpacity:1});
					map.addOverlay(polyline);
				}
		);
	}
}

function getUrlParam(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function CurrentDate(){ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day;
    return(clock); 
} 

function CurrentTime(){
	var now = new Date();
	var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock ="";
    if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    return(clock);
}