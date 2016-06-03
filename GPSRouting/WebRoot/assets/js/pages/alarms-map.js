
var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));

var longitude = $(".Alarm-longitude").attr("value");
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
});

