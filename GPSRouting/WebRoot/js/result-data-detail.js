/**
 * Created by XinLi on 16/6/23.
 */
var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);

function newCheck() {
    $('#add-check-dlg').dialog('open').dialog('setTitle','审核');
}

function mapShow(record_id){
//	$('#region-map').panel('open').panel('setTitle',"巡检轨迹");
	if(record_id){
		parent.addTab("巡检轨迹",'RecordPoiServlet?recordId='+record_id);
	}
}

function checkAllRecord(record_id){
    var comment =$("#record-comment").val();
    if(confirm("确定审核完毕？")){
        $.post("CheckRecordServlet?index=all",{
            record_id : record_id,
            comment : comment
        },function(data, status){
            if(data!="error"){
                alert("成功");
                window.location.reload();
            }
            else{
                alert("失败")
            }
        });
    }
}
