/**
 * Created by XinLi on 16/6/21.
 */
var map = new BMap.Map("allmap");
var point = new BMap.Point(114.405, 30.513);
map.centerAndZoom(point,15);
map.enableDoubleClickZoom(true);
map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
var myIcon = new BMap.Icon("assets/img/rqx_tm0.png", new BMap.Size(47,53));


function addTab(title,url){
    parent.addTab(title,url);
}

/**
 * 获取session并初始化页面
 */


//初始化时间段
$("#start-date").val(CurrentDate());
$("#end-date").val(CurrentDate());
//初始化线路选择列表

var url = "GetAllRegionServlet?index=route&branch_id="+getUrlParam("branch_id");

$.getJSON(
    url,
    function(data){
        var option_all = $("<option>").val('all').text('所有');

        $("#select_route_list").append(option_all);
        for(var i = 0; i < data.length;i++){
            var rt = data[i];
            var option = $("<option>").val(rt.id).text(rt.branch+' '+rt.name);

            $("#select_route_list").append(option);
        }
    }
);





var paragenerid = getUrlParam('id');
if(paragenerid==null){
    initialAllPosition();
    var int=self.setInterval("initialAllPosition()",2000);
}
else{
    $("#search-trace").show();
    initialOnePosition();
    var int=self.setInterval("initialOnePosition()",2000);
}


var peoplemarkers = new Array();
var peoplemarker=null;
function initialAllPosition(){
    var peoplearray = new Array();
    var brchid = getUrlParam("branch_id");
    $.getJSON(
        "GetPositionServlet?index=branch&branch_id="+getUrlParam("branch_id"),
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
                    "<a class='easyui-linkbutton' iconCls='icon-search' href = '#' onclick=\"addTab('历史线路-"+pp.name+"','position-data.jsp?branch_id="+brchid+"&id="+pp.id+"')\">详情及轨迹</a>"+
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
    var paramBranch_id = getUrlParam('branch_id');
    var regionarray = new Array();

        var url = "GetAllRegionServlet?index=branch&branch_id="+paramBranch_id;
        
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
var trace = null
function showTrace(){

    var start = $("#start-date").datetimebox('getValue');
    var end = $("#end-date").datetimebox('getValue');

    $.getJSON(
        "GetRouteServlet?index=gener&gener_id="+paragenerid+"&start="+start+"&end="+end,
        function(data){
            if (trace!=null){
                map.removeOverlay(trace);
            }
            var pointArray = new Array();
            for(var i = 0; i < data.length; i++){
                var temp = data[i];
                pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
            }
            trace = new BMap.Polyline(pointArray,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
            map.addOverlay(trace);
        }
    );
}

function refreshMap() {
        map.clearOverlays();
}

function searchRoute(){
    var id = $("#select_route_list").find("option:selected").val();
    if (id == 'all'){
        var chilcount = $("#select_route_list").children('option').length;
        for(var i = 1; i< chilcount; i++){
            var $singleOpt = $("#select_route_list").find('option').eq(i); //获取option的内容
            var val = $singleOpt.val();
            if(val != "all"){
                $.getJSON(
                    "GetRouteServlet?index=region&region_id="+val,
                    function(data){
                        var pointArray = new Array();
                        for(var i = 0; i < data.length; i++){
                            var temp = data[i];
                            pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
                        }
                        var polyline = new BMap.Polyline(pointArray,{strokeColor:"green", strokeWeight:4, strokeOpacity:1});
                        map.addOverlay(polyline);
                    }
                );
            }
        }
    }
    else{
        $.getJSON(
            "GetRouteServlet?index=region&region_id="+id,
            function(data){
                var pointArray = new Array();
                for(var i = 0; i < data.length; i++){
                    var temp = data[i];
                    pointArray[i] = new BMap.Point(temp.longitude,temp.latitude);
                }
                var polyline = new BMap.Polyline(pointArray,{strokeColor:"green", strokeWeight:4, strokeOpacity:1});
                map.addOverlay(polyline);
            }
        );
    }
}

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function CurrentDate(){
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day;
    return(clock);
}

function CurrentTime(){
    var now = new Date();
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock ="";
    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return(clock);
}