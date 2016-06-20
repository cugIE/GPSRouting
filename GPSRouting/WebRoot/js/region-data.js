var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.enableDoubleClickZoom();
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
function addTab(title,url){
    parent.addTab(title,url);
}