

var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var paragenerid = getUrlParam('id');
if(paragenerid==null){
	var int=self.setInterval("initialAllPosition()",2000);
}
else{
	var int=self.setInterval("initialOnePosition()",2000);
}
var peoplemarkers = new Array();
var peoplemarker=null;
var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));

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

	map.centerAndZoom(point,15);
	var markergg = new BMap.Marker(point,{icon:myIcon});
	var marker1 = new BMap.Marker(new BMap.Point(114.40972692836197,30.520949489750606),{icon:myIcon});
	var marker2 = new BMap.Marker(new BMap.Point(114.39977083199211,30.50905837656414),{icon:myIcon});
	var marker3 = new BMap.Marker(new BMap.Point(114.40222134440635,30.50233483664306),{icon:myIcon});
	var marker4 = new BMap.Marker(new BMap.Point(114.41564253641531,30.510551458191393),{icon:myIcon});
	map.addOverlay(markergg);
	map.addOverlay(marker1);
	map.addOverlay(marker2);
	map.addOverlay(marker3);
	map.addOverlay(marker4);	

	var scontent = '<div style="margin:0;line-height:20px;padding:2px;">' +
                '<img src="pic/equip.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:150px;height:129px;margin-left:3px;"/>' +
                '编号：111003<br/>参数1：***<br/>参数2：***<br/>所处位置:' +
              '</div>';

	var sContent5 =
	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>设备00</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	"</div>";
	var sContent1 =
	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>设备01</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	"</div>";
	var sContent2 =
	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>设备02</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	"</div>";
	var sContent3 =
	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>设备03</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	"</div>";
	var sContent4 =
	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>设备04</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	"</div>";

	var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, scontent, {
		title  : "设备001",      //标题
		width  : 290,             //宽度
		height : 140,              //高度
		panel  : "panel",         //检索结果面板
		enableAutoPan : true,     //自动平移
		searchTypes   :[
			BMAPLIB_TAB_SEARCH,   //周边检索
			BMAPLIB_TAB_TO_HERE,  //到这里去
			BMAPLIB_TAB_FROM_HERE //从这里出发
		]
	});



	var infoWindow1 = new BMap.InfoWindow(sContent1);
	var infoWindow2 = new BMap.InfoWindow(sContent2);
	var infoWindow3 = new BMap.InfoWindow(sContent3);
	var infoWindow4 = new BMap.InfoWindow(sContent4);
//	var infoWindow = new BMap.InfoWindow(sContent);


	markergg.addEventListener("click",fun1);
	function fun1(){
		searchInfoWindow.open(markergg);
	}
	marker1.addEventListener("click", function(){          
   this.openInfoWindow(infoWindow1);
   //图片加载完毕重绘infowindow
   document.getElementById('imgDemo').onload = function (){
		 infoWindow1.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	   }
	});
	marker2.addEventListener("click", function(){          
	this.openInfoWindow(infoWindow2);
	//图片加载完毕重绘infowindow
	document.getElementById('imgDemo').onload = function (){
		  infoWindow2.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		}
	});
	marker3.addEventListener("click", function(){          
		this.openInfoWindow(infoWindow3);
   //图片加载完毕重绘infowindow
   document.getElementById('imgDemo').onload = function (){
		  infoWindow3.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		}
	});
	marker4.addEventListener("click", function(){          
		this.openInfoWindow(infoWindow4);
	//图片加载完毕重绘infowindow
	document.getElementById('imgDemo').onload = function (){
	   infoWindow4.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		}
	});
}

	function remove_overlay(){
	map.clearOverlays();         
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