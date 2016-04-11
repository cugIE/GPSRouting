/**
 * 
 */
var map = new BMap.Map("bdmap-record-position");
var point = new BMap.Point(114.405, 30.513);
var position = $("#bdmap-record-position").attr("gps");
var pos = position.split(/,|{|}/);
var len = pos.length;
for (var i = 0; i<len; i++){
	var temp = pos.shift();
	
	if(temp!=""){
		pos.push(temp);
	}
}
map.centerAndZoom(point,15);	
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var Position1=new BMap.Point( parseFloat(pos.shift()),parseFloat(pos.shift()));									
var markers1 = new BMap.Marker(Position1);
map.centerAndZoom(Position1,15);//以巡检员3位置居中显示地图

map.addOverlay(markers1);

var label1 = new BMap.Label("检查点",{offset:new BMap.Size(20,-10)});
markers1.setLabel(label1);