
var map = new BMap.Map("allmap");
/*var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);*/
map.enableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("assets/img/rqx_tm0.png", new BMap.Size(47,53));

var arrayName = [];
var arrayGps = [];
var rcdName = $(".recordName").attr("value");
var rcdGps = $(".recordGps").attr("value");
/*alert("区域名字："+rcdName+"---区域gps："+rcdGps);*/

arrayName = rcdName.split(";");
arrayGps = rcdGps.split(";");

/*alert("区域名字："+arrayName[1]+"---区域gps："+arrayGps[1]);*/
var pi = arrayGps[1].split(",")[0];
var pj = arrayGps[1].split(",")[1];
var center = new BMap.Point(pi,pj);
map.centerAndZoom(center,15);

var point = new Array(); //存放标注点经纬信息的数组  
var marker = new Array(); //存放标注点对象的数组
for(var i = 1;i<arrayGps.length;i++){	
		var p0 = arrayGps[i].split(",")[0];
		var p1 = arrayGps[i].split(",")[1];
		point[i]= new BMap.Point(p0, p1);
		marker[i] = new BMap.Marker(point[i]);
		map.addOverlay(marker[i]);  
        marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画  
        var label = new window.BMap.Label(arrayName[i], { offset: new window.BMap.Size(20, -10) });  
        marker[i].setLabel(label);
}

/*var polyline = new BMap.Polyline(point,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});   //创建折线
map.addOverlay(polyline);   //增加折线
*/
/*var longitude = $(".Alarm-longitude").attr("value");
var latitude = $(".Alarm-latitde").attr("value");
var alarmTime = $(".Alarm-time").attr("value");
var generName = $(".Alarm-genername").attr("value");

var gpspoint = new BMap.Point(longitude,latitude);

var marker = new BMap.Marker(gpspoint);  // 创建标注
map.addOverlay(marker);               // 将标注添加到地图中
marker.setAnimation(BMAP_ANIMATION_BOUNCE);

var opts = {
		  width : 200,     // 信息窗口宽度
		  height: 100,     // 信息窗口高度
		  title : "警报时间："+alarmTime, // 信息窗口标题
		  enableMessage:true,//设置允许信息窗发送短息
		  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
		}
var infoWindow = new BMap.InfoWindow("发起人："+generName,opts);  // 创建信息窗口对象

marker.addEventListener("click", function(){          
	map.openInfoWindow(infoWindow,gpspoint); //开启信息窗口
});*/
