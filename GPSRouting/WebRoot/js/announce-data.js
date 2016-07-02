var branch_id = getQueryString('branch_id');
var userTeamid = getQueryString('userTeamid');


/*alert("部门id："+branch_id);*/
window.onload = function(){	
	$('#announce-data').datagrid({url:'ManageAnnounceServlet?action=announceList&userBranchid='+branch_id });
	if(userTeamid>2){
		$(".datagrid-toolbar").hide();
	}
};

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    function pushMsg(){
    	var row = $('#announce-data').datagrid('getSelected');
    	if (row){
            $.messager.confirm('Confirm','确认发送?',function(r){
                if (r){
                    $.post('SendMsgServlet',{id:row.id},function(result){
                        if (result=="1"){
                        	$.messager.show({	// show error message
                                title: 'message',
                                msg: "发送成功！"
                            });
                            /*$('#region-data').datagrid('reload');*/	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "发送失败！"
                            });
                        }
                    },'json');
                }
            });
        }
    }
    function newMsg(){
    	$('#dlg-add').dialog('open').dialog('setTitle','添加公告');
    	/*$('#fm-msg').form('clear');*/
    	url = 'ManageAnnounceServlet?action=add';
    }
    function saveMsg(){
    	$('#fm-msg').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                if (result=="error"){
                    $.messager.show({
                        title: 'Error',
                        msg: "添加错误！"
                    });
                } else {
                    $('#dlg-add').dialog('close');		// close the dialog
                    $('#announce-data').datagrid('reload');	// reload the user data
                    $.messager.show({
                        title: 'Error',
                        msg: "更新成功！"
                    });
                }
            }
        });
    }
    function editMsg(){
    	var row = $('#announce-data').datagrid('getSelected');
        if (row){
            $('#dlg-add').dialog('open').dialog('setTitle','修改公告信息');
            $('#fm-msg').form('load',row);
         //   url = 'ChangeRegionServlet?region_id='+row.id;
            url='ManageAnnounceServlet?action=update&id='+row.id;
        }
    }
    function destroyMsg(){
    	var row = $('#announce-data').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','确认删除?',function(r){
                if (r){
                    $.post('ManageAnnounceServlet?action=delete',{id:row.id},function(result){
                        if (result=="1"){
                            $('#announce-data').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "删除失败"
                            });
                        }
                    },'json');
                }
            });
        }
    }

    
    