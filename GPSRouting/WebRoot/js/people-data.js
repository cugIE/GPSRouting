function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','添加人员');
	$('#fm').form('clear');
	url = 'save_user.php';
};

function addTab(title,url){
    parent.addTab(title,url);
};
	
/*	function addTab(title,url){
        if ($('#tbs').tabs('exists', title)){
            $('#tbs').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="margin-left: 10%;width:80%;height:100%;"></iframe>';
            $('#tbs').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    };*/
    
    function destroyUser() {
        var row = $('#people-data').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','确认删除?',function(r){
                if (r){
                    $.post('ManagePeopleServlet?action=delete3',{id:row.id},function(result){
                        if (result=="1"){
                            $('#people-data').datagrid('reload');	// reload the user data
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
    };
    
    function destroyU(){
    	
    }
    
    function editUser(){
        var row = $('#people-data').datagrid('getSelected');
        if (row){
            $('#dlg-edit').dialog('open').dialog('setTitle','修改人员信息');
            $('#updatefm').form('load',row);
         //   url = 'ChangeRegionServlet?region_id='+row.id;
            url='ManagePeopleServlet?action=update&id='+row.id;
        }
    }
	