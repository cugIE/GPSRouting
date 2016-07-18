var userTeamid = getQueryString('userTeamid');


window.onload=function()
{
	if(userTeamid>4){
		$(".datagrid-toolbar").hide();
	}	
};
var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);	
map.enableDoubleClickZoom();
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("assets/img/rqx_tm0.png", new BMap.Size(47,53));
var regionarray = new Array();
var paramBranch_id = getQueryString('branch_id');

var url = "GetAllRegionServlet"

url = url +"?index=branch&branch_id="+paramBranch_id;
showdata(url);

function showdata(url){
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

function addTab(title,url){
    parent.addTab(title,url);
}

function editRegion(){
    var row = $('#region-data').datagrid('getSelected');
    if (row){
        $('#add-region-dlg').dialog('open').dialog('setTitle','修改区域');
        $('#add-region-form').form('load',row);
        url = 'ChangeRegionServlet?id='+row.id;
    }
}
function destroyRegion() {
    var row = $('#region-data').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','确认删除?',function(r){
            if (r){
                $.post('DeleteSingleRegionServlet',{region_id:row.id},function(result){
                    if (result=="1"){
                        $('#region-data').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.confirm({	// show error message
                            title: 'Error',
                            msg: "该区域已生成生产日志,无法删除"
                        });
                    }
                },'json');
            }
        });
    }
}

function newRegion() {
    $('#add-region-dlg').dialog('open').dialog('setTitle','区域');
    $('#add-region-form').form('clear');
    $('#region-type-list').combobox({
        url:'json/regiontype.json',
        valueField:'type',
        textField:'typename'
    });
    url = 'AddRegionServlet?branch_id='+getQueryString("branch_id")+'&range=0';
}

function saveRegion() {
    $('#add-region-form').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            if (result=="error"){
                $.messager.show({
                    title: 'Error',
                    msg: "添加错误"
                });
            } else {
                $('#add-region-dlg').dialog('close');		// close the dialog
                $('#region-data').datagrid('reload');	// reload the user data
            }
        }
    });
}

function checkRegion(){
    var row = $('#region-data').datagrid('getSelected');
    parent.addTab(getQueryString("branch_id")+'-'+row.name,'region-data-detail.jsp?region_id='+row.id+'&branch_id='+getQueryString('branch_id'));
}

function checkQRcode() {
    var row = $('#region-data').datagrid('getSelected');
    $('#show-qrcode').dialog('open').dialog('setTitle','选择二维码');
    url = "GenerateQRCode?region_id="+row.id;

}
function showQRcode() {
    url=url+'&size='+$("#qrcode-size").val();
    addTab('二维码:'+url,url);
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//GenerateQRCode?region_id=<%=rg.getId() %>&size=200"