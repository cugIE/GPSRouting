
var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("pic/rqx_tm0.png", new BMap.Size(47,53));

var longitude = $(".Alarm-longitude").attr("value");
var latitude = $(".Alarm-latitde").attr("value");

var gpspoint = new BMap.Point(longitude,latitude);

var marker = new BMap.Marker(gpspoint);  // 创建标注
map.addOverlay(marker);               // 将标注添加到地图中
marker.setAnimation(BMAP_ANIMATION_BOUNCE);

