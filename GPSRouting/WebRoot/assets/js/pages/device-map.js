	var map = new BMap.Map("allmap");
	var pointgg = new BMap.Point(114.405, 30.513);
	map.centerAndZoom(pointgg,15);	
	map.disableDoubleClickZoom(true);
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));

	var point1 = new BMap.Point(114.40972692836197,30.520949489750606),
		point2 = new BMap.Point(114.39977083199211,30.50905837656414),
		point3 = new BMap.Point(114.40222134440635,30.50233483664306),
		point4 = new BMap.Point(114.41564253641531,30.510551458191393);
		point5 = new BMap.Point(114.41864253641531,30.5002);


//	var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
//	driving2.search(spoi1, epoi);    //显示一条公交线路

	//--------------------------------显示路线----------------------
	function getSelRoute(){
		oListbox = document.getElementById("selectRoute");

		var indx = oListbox.selectedIndex;
		var selText = oListbox.options[indx].text;
		if(selText == "1号线"){
			var polyline1 = new BMap.Polyline([
			point1,
			new BMap.Point(114.40722692836197,30.520949489750606),
			new BMap.Point(114.40522692836197,30.511949489750606),
			new BMap.Point(114.39972692836197,30.51119489750606),	
			point2
			], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
			map.addOverlay(polyline1);
		}else if(selText == "2号线"){
			var polyline2 = new BMap.Polyline([
			point2,
			new BMap.Point(114.39977083199211,30.50435837656414),
			new BMap.Point(114.40222134440635,30.50395837656414),	
			point3
			], {strokeColor:"red", strokeWeight:6, strokeOpacity:0.5});
			map.addOverlay(polyline2);
		}else if(selText == "3号线"){
			var polyline3 = new BMap.Polyline([
			point3,
			new BMap.Point(114.40427083199211,30.50265837656414),
			new BMap.Point(114.40467083199211,30.50385837656414),
			new BMap.Point(114.41852134440635,30.50305837656414),	
			point5
			], {strokeColor:"green", strokeWeight:6, strokeOpacity:0.5});
			map.addOverlay(polyline3);
		}else
			alert(selText + "-待添加");
	}
	
	//--------------------------------displayRoute----------------------	

	//--------------------------------显示设备----------------------
		
		var marker1 = new BMap.Marker(point1,{icon:myIcon});
		var marker2 = new BMap.Marker(point2,{icon:myIcon});
		var marker3 = new BMap.Marker(point3,{icon:myIcon});
		var marker4 = new BMap.Marker(point4,{icon:myIcon});
		var marker5 = new BMap.Marker(point5,{icon:myIcon});
		map.addOverlay(marker5);
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
			title  : "设备003",      //标题
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

		marker5.addEventListener("click",fun1);
		function fun1(){
			searchInfoWindow.open(marker5);
		//	marker5.openInfoWindow(infoWindow1);
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