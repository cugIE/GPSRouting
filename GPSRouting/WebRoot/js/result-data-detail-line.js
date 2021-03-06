/**
 * Created by XinLi on 16/6/23.
 */
var map = new BMap.Map("bdmap-record-position");
var point = new BMap.Point(114.405, 30.513);
var position = $("#bdmap-record-position").attr("gps");
var pos = position.split(/,/);

map.centerAndZoom(point,15);
map.disableDoubleClickZoom(true);
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var Position1=new BMap.Point( pos[0], pos[1]);
var markers1 = new BMap.Marker(Position1);
map.centerAndZoom(Position1,15);//以巡检员3位置居中显示地图

map.addOverlay(markers1);

var label1 = new BMap.Label("检查点",{offset:new BMap.Size(20,-10)});
markers1.setLabel(label1);

$(document).ready(function(){
    if($("#record-checker").text()==""){

    }
    else{
        $("#check-button").hide();
    }
    $("#check-button").on("click", function(){
        var record_id = $("#eva-record-id").text();
        var comment =$("#record-comment").val();
        if(confirm("确定审核完毕？")){
            $.post("CheckRecordServlet",{
                record_id : record_id,
                comment : comment
            },function(data, status){
                if(data=="1"){
                    alert("成功");
                    window.location.reload();
                }
                else{
                    alert("失败")
                }
            });
        }
    });
})