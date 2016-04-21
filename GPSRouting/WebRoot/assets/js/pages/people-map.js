var session = new Object();
$.getJSON(
		"GetSessionServlet",
		function(data){
			session.branch_type=data.branch_type;
			session.branch_id=data.branch_id;
			
		}
	);


var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));


$("#start-date").val(CurrentDate());
$("#end-date").val(CurrentDate());
function refreshControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(5, 5);
}
refreshControl.prototype = new BMap.Control();

//自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
//在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
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
	
	
	
	
//	设置样式
	
//	绑定事件,点击一次放大两级
	
//	添加DOM元素到地图中
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
	
}
//function displayRoute(){
//	var beijingPosition=new BMap.Point(114.38977083199211,30.50905837656414),
//	hangzhouPosition=new BMap.Point(114.40507683466778,30.51356998509895),
//	taiwanPosition=new BMap.Point(114.43564253641531,30.510551458191393);
//	var points = [beijingPosition,hangzhouPosition, taiwanPosition];
//
//	var curve = new BMapLib.CurveLine(points, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象
//	map.addOverlay(curve); //添加到地图中
//	curve.enableEditing(); //开启编辑功能
//
//	var walking1 = new BMap.WalkingRoute(map, {renderOptions:{map: map, autoViewport: true}});
//	var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
//	driving2.search(beijingPosition, taiwanPosition);    //显示一条公交线路
//	walking1.search(beijingPosition, taiwanPosition);
//}
//function displayPeop(){
//	var Position1=new BMap.Point(114.385,30.509),
//		Position2=new BMap.Point(114.388,30.529),
//		Position3=new BMap.Point(114.395,30.518),
//		Position4=new BMap.Point(114.402,30.513),
//		Position5=new BMap.Point(114.425,30.510);										
//	var markers1 = new BMap.Marker(Position1),
//		markers2 = new BMap.Marker(Position2),
//		markers3 = new BMap.Marker(Position3),
//		markers4 = new BMap.Marker(Position4),
//		markers5 = new BMap.Marker(Position5);
//	map.centerAndZoom(Position3,15);//以巡检员3位置居中显示地图
//
//	map.addOverlay(markers1);
//	map.addOverlay(markers2);
//	map.addOverlay(markers3);
//	map.addOverlay(markers4);
//	map.addOverlay(markers5);
//
//	var label1 = new BMap.Label("巡检员001",{offset:new BMap.Size(20,-10)}),
//		label2 = new BMap.Label("巡检员002",{offset:new BMap.Size(20,-10)}),
//		label3 = new BMap.Label("巡检员003",{offset:new BMap.Size(20,-10)}),
//		label4 = new BMap.Label("巡检员004",{offset:new BMap.Size(20,-10)}),
//		label5 = new BMap.Label("巡检员005",{offset:new BMap.Size(20,-10)});
//	markers1.setLabel(label1);
//	markers2.setLabel(label2);
//	markers3.setLabel(label3);
//	markers4.setLabel(label4);
//	markers5.setLabel(label5);
//
//
//	var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
//                '<img src="pic/workerman.png" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:136px;margin-left:3px;"/>' +
//                '工号：111003<br/>所属部门：**站**队03号<br/>电话：(010)59928888<br/>当前路线：2号线<br/>速度：3m/s。<br/><input type="text" value="通知消息"/><input type="button" value="发送通知"/>' +
//              '</div>';
//
//	var infoWindowMan = new BMap.InfoWindow(content);
//	infoWindowMan.setTitle("巡检员003");
//	var searchInfoWindow = null;
//	searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
//		title  : "巡检员003",      //标题
//		width  : 290,             //宽度
//		height : 105,              //高度
//		panel  : "panel",         //检索结果面板
//		enableAutoPan : true,     //自动平移
//		searchTypes   :[
//			BMAPLIB_TAB_SEARCH,   //周边检索
//			BMAPLIB_TAB_TO_HERE,  //到这里去
//			BMAPLIB_TAB_FROM_HERE //从这里出发
//		]
//	});
//
//	markers3.addEventListener("click",fun1);
//	markers3.addEventListener("dblclick",fun2);
//	function fun1(){
//		markers3.openInfoWindow(infoWindowMan);
//	//	searchInfoWindow.open(markers3);
//	}
//	function fun2(){
//		
//		var polyline = new BMap.Polyline([
//			new BMap.Point(114.382,30.498),
//			new BMap.Point(114.385,30.502),
//			new BMap.Point(114.390,30.505),
//			new BMap.Point(114.392,30.509),
//			new BMap.Point(114.394,30.514),
//			new BMap.Point(114.395,30.518)
//		], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});   //创建折线
//		map.addOverlay(polyline);
//
//		map.removeOverlay(markers1);
//		map.removeOverlay(markers2);
//		map.removeOverlay(markers4);
//		map.removeOverlay(markers5);
//	}
////	markers3.addEventListener("click", function(e){
////    searchInfoWindow.open(markers3);
////	})
//}
//function getSelRoute(){
//	oListbox = document.getElementById("selectRoute");
//
//	var indx = oListbox.selectedIndex;
//	var selText = oListbox.options[indx].text;
////	alert("获得选中的选项的索引 "+ selText);
//	if(selText == "2号线"){
//		var position1=new BMap.Point(114.406,30.5154),
//			position2=new BMap.Point(114.358,30.5337),
//			position3=new BMap.Point(114.346,30.5514);
//			position4=new BMap.Point(114.34,30.5395);
//			position5=new BMap.Point(114.373,30.5272);
//			position6=new BMap.Point(114.386,30.5231);
//		map.centerAndZoom(position2,14);
//		var points1 = [position1,position2,position3,position4,position5,position6];
//		var polyline1 = new BMap.Polyline(points1,{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
//		map.addOverlay(polyline1); //添加到地图中
//	//	var curve1 = new BMapLib.CurveLine(points, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象		
//	//	curve.enableEditing(); //开启编辑功能
//	}else if(selText == "1号线"){
//		var position11=new BMap.Point(114.338,30.5408),
//			position12=new BMap.Point(114.34,30.5431),
//			position13=new BMap.Point(114.346,30.5514);
//			position14=new BMap.Point(114.34,30.5395);
//			position15=new BMap.Point(114.348,30.5366);
//			position16=new BMap.Point(114.374,30.5263);
//		map.centerAndZoom(position12,14);
//		var points2 = [position11,position12,position13,position14,position15,position16];
//		var polyline2 = new BMap.Polyline(points2,{strokeColor:"green", strokeWeight:3, strokeOpacity:0.5});
//		map.addOverlay(polyline2); //添加到地图中
//	}else if(selText == "3号线"){
//		var position21=new BMap.Point(114.406,30.5136),
//			position22=new BMap.Point(114.402,30.5141),
//			position23=new BMap.Point(114.367,30.5312);
//			position24=new BMap.Point(114.346,30.5514);
//			position25=new BMap.Point(114.346,30.5514);
//			position26=new BMap.Point(114.34,30.5395);
//		map.centerAndZoom(position23,14);
//		var points3 = [position21,position22,position23,position24,position25,position26];
//		var polyline3 = new BMap.Polyline(points3,{strokeColor:"red", strokeWeight:3, strokeOpacity:0.5});
//		map.addOverlay(polyline3); //添加到地图中
//	}
//
//}
function getUrlParam(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}
function CurrentDate()
{ 
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