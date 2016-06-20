    function addTab(title,url){
        parent.addTab(title,url);
    }
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    function newPeriod(){
        $('#add-sheet-dlg').dialog('open').dialog('setTitle','新表');
        $('#add-sheet-form').form('clear');

        url = 'AddSheetServlet?branch_id='+getQueryString("branch_id");
    }
    function newTime(){
        $('#add-sheet-dlg').dialog('open').dialog('setTitle','新表');
        $('#add-sheet-form').form('clear');

        url = 'AddSheetServlet?branch_id='+getQueryString("branch_id");
    }
    function editTime() {
        var row = $('#sheet-data').datagrid('getSelected');
        if (row){
            $('#add-sheet-dlg').dialog('open').dialog('setTitle','修改表');
            $('#add-sheet-form').form('load',row);
            url = 'ChangeSheetServlet?sheet_id='+row.id;
        }
    }

    function saveTime(){
        $('#add-sheet-form').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                if (result!="1"){
                    $.messager.show({
                        title: 'Error',
                        msg: "添加错误"
                    });
                } else {
                    $('#add-sheet-dlg').dialog('close');		// close the dialog
                    $('#sheet-data').datagrid('reload');	// reload the user data
                }
            }
        });
    }
    function destroyTime(){
        var row = $('#sheet-data').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','确认删除?',function(r){
                if (r){
                    $.post('DeleteSingleSheetServlet',{sheet_id:row.id},function(result){
                        if (result=="1"){
                            $('#sheet-data').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "添加错误"
                            });
                        }
                    },'json');
                }
            });
        }
    }
    function checkSheet(){
        var row = $('#sheet-data').datagrid('getSelected');
        parent.addTab(getQueryString("branch_id")+'-'+row.name,'sheet-data-detail.jsp?sheet_id='+row.id);
    }
