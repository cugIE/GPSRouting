    var pid;

    function addTab(title,url){
        parent.addTab(title,url);
    }
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    function newShift(){

        $('#add-shift-dlg').dialog('open').dialog('setTitle','新班次');
        $('#add-sheet-form').form('clear');


    }

    $('#shift-list').combobox({
        onChange: function (n, o) {
            $('#times-list').combobox({
                url:'GetAllPeriodServlet?index=rest&sheet_id='+getQueryString("sheet_id")+"&shift="+n,
                valueField:'time',
                textField:'text'
            });
        }
    });

    function saveShift() {
        var str = "";
        $("input[name='times']:checkbox").each(function () {
            if ($(this).prop("checked")) {

                str += $(this).val() + ","
            }
        })
        if (str == "") {
            $.messager.show({
                title: 'Error',
                msg: "请选择时间点"
            });
        }

        else {
            var shift_name = $("#shift-name").val();
            $.post("AddPeriodServlet", {
                index: "several",
                shift: shift_name,
                periods: str,
                sheet_id: getQueryString('sheet_id')
            }, function (data, status) {
                if (data == "1") {
                    $('#add-shift-dlg').dialog('close');
                    $('#time-data').datagrid('reload');
                }
                else {
                    $.messager.show({	// show error message
                        title: 'Error',
                        msg: "添加错误"
                    });
                }

            });
        }
    }
    function newTime(){
        $('#add-time-dlg').dialog('open').dialog('setTitle','新时间点');
        $('#add-time-form').form('clear');
        $('#shift-list').combobox({
            url:'GetAllPeriodServlet?index=shift&sheet_id='+getQueryString("sheet_id"),
            valueField:'shift',
            textField:'text'
        });

        url = 'AddPeriodServlet?index=one&sheet_id='+getQueryString("sheet_id");
    }

    function stringToJson(stringValue)
    {
        eval("var theJsonValue = "+stringValue);
        return theJsonValue;
    }

    var button_up = "<a href='#' class = 'search-regions-actions-delete' style='float:right; padding-right: 5px;'>删</a><a href='#' class = 'search-regions-actions-up' style='float:right; padding-right: 5px;'>上</a>"
    var button_left = "<a href='#' class ='search-regions-actions' style='float: right; padding-right: 5px'>添加</a>"
    function editTime() {
        var row = $('#time-data').datagrid('getSelected');
        if (row){
            pid=row.id;
            $('#edit-time-dlg').dialog('open').dialog('setTitle','操作巡检区域');
            var urlall = "GetAllRegionServlet?index=period&period_id="+ pid;
            var urlrest = "GetAllRegionServlet?index=rest&period_id="+pid+"&branch_id="+getQueryString('branch_id');


            $.get(urlall,function(data,status){
                var srarrays = stringToJson(data);
                $("#SelectedRegions").children().remove();

                for (var i = 0; i<srarrays.length; i++){
                    var currsr = srarrays[i];
                    $("#SelectedRegions").append("<tr style='width: 100%;' class='selected-region-tr'><td period_id = '"+ pid + "' region_id = '" + currsr.id + "' id = '" + currsr.ptr_id + "' >"+ currsr.name + button_up+"</td><tr>");
                }
            });
            $.get(urlrest,function(data,status){
                var rarrays = stringToJson(data);
                $("#RestRegions").children().remove();
                for (var i = 0; i<rarrays.length; i++){
                    var currr = rarrays[i];
                    $("#RestRegions").append("<tr style='width: 100%;'  class= 'rest-regions-tr'><td period_id = '"+ pid +"' id = '" + currr.id + "' >"  + currr.name + button_left+"</td><tr>");
                }
            });
        }
    }

    $(document).on("click",'#SelectedRegions tr td .search-regions-actions-delete',function(){
        var th = $(this).parents("tr");
        var td = $(this).parents("td");
        var ptr_id = td.attr("id");
        var region_id = td.attr("region_id");
        var period_id = td.attr("period_id");
        var name = td.text();
        $.post("DeleteConnectionServlet",{
            ptr_id: ptr_id
        },function(data,status){
            if(data=="1"){
                alert("删除成功");
                $("#RestRegions").append("<tr class= 'rest-regions-tr'><td  period_id = '"+ period_id +"' id = '" + region_id + "' >" +  button_left+ name +"</td><tr>");
                th.remove();
            }
            else{
                alert("删除失败")
            }

        });
    });

    $(document).on("click",'#SelectedRegions tr td .search-regions-actions-up',function(){
        var th = $(this).parents("tr");
        var td = $(this).parents("td");
        var prev = th.prev();
        var len = th.prevAll().length;
        while (len!=0&&prev.attr("class")===undefined){
            prev = prev.prev();
            len=len-1;
        }
        if(prev.attr("class")!==undefined){
            var prevtd = prev.children("td");
            var previd = prevtd.attr("id");
            var tdid = td.attr("id");
            $.post("SwitchSortServlet",{
                ptr_id_bef : previd,
                ptr_id_aft : tdid
            },function(data, status){
                if(data=="success"){
                    th.insertBefore(prev);
                }
                else{
                    alert("error");
                }
            });}
        //		ptr_id_bef
        //     ptr_id_aft
        //		th.insertBefore(prev);
        //		var prevtd = prev.children("td");
    });
    $(document).on("click",'#RestRegions tr td a',function(){
        var th = $(this).parents("tr");
        var td = $(this).parents("td");
        var region_id = td.attr("id");
        var period_id = td.attr("period_id");
        var name = td.text();
        var sort = 0
        $.post("GetMaxSortServlet",{
            period_id : period_id
        },function(data, status){
            if(data!="error"){
//	 			alert(data);
                sort = parseInt(data);
                $.post("AddConnectionServlet",{
                    period_id: period_id,
                    gener_id: "0001",
                    sort: sort+1,
                    region_id: region_id
                },function(data,status){
                    if(data!="error"&&status=="success"){
                        alert("添加成功");
                        $("#SelectedRegions").append("<tr class='selected-region-tr'><td period_id = '"+ period_id + "' region_id = '" + region_id + "' id = '" + data + "' >" + name + button_up+ "</td><tr>");
                        th.remove();
                    }
                    else{
                        alert("添加失败")
                    }

                });
            }
            else{
                alert("失败，无法返回现在最大sort")
            }
        });

    });
    
    function saveTime(){
        $('#add-time-form').form('submit',{
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
                    $('#add-time-dlg').dialog('close');		// close the dialog
                    $('#time-data').datagrid('reload');	// reload the user data
                }
            }
        });
    }
    function destroyTime(){
        var row = $('#time-data').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','确认删除?',function(r){
                if (r){
                    $.post('DeletePeriodServlet',{index:"period", period_id:row.id},function(result){
                        if (result=="1"){
                            $('#time-data').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "错误"
                            });
                        }
                    },'json');
                }
            });
        }
    }
    function destroyShift(){
        var shift_name = $('#shift-search').combobox('getValue');
        $.messager.confirm('Confirm','确认删除整个班次?',function(r){
            if (r){
                $.post('DeletePeriodServlet',{index:"shift", sheet_id: getQueryString("sheet_id"), shift:shift_name},function(result){
                    if (result!="error"){
                        $('#time-data').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: "错误"
                        });
                    }
                },'json');
            }
        });
    }
    
    
    function searchShift(){
            $('#time-data').datagrid('load',{
                index: "fromshift",
                shift: $('#shift-search').combobox('getValue')
            });
    }

    function checkAll(name) {
        var el = document.getElementsByTagName('input');
        var len = el.length;
        for(var i=0; i<len; i++) {
            if((el[i].type=="checkbox") && (el[i].name==name)) {
                el[i].checked = true;
            }
        }
    }
    function clearAll(name) {
        var el = document.getElementsByTagName('input');
        var len = el.length;
        for(var i=0; i<len; i++) {
            if((el[i].type=="checkbox") && (el[i].name==name)) {
                el[i].checked = false;
            }
        }
    } 