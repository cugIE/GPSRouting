function newBranch(){
	$('#dlg').dialog('open').dialog('setTitle','添加部门');
	$('#fm').form('clear');
	
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
    
    function destroyBranch() {
        var row = $('#branch-data').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','确认删除?',function(r){
                if (r){
                    $.post('ManageBranchServlet?action=delete3',{id:row.id},function(data,status){
                        if (status=="success"){
                            $('#branch-data').datagrid('reload');	// reload the user data
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
    
    function editBranch(){
        var row = $('#branch-data').datagrid('getSelected');
        url='ManageBranchServlet?action=update&id='+row.id;
        if (row){
        	parent.addTab('修改部门信息',url);
           /* $('#dlg-edit').dialog('open').dialog('setTitle','修改人员信息');
            $('#updatefm').form('load',row);*/
         //   url = 'ChangeRegionServlet?region_id='+row.id;
      
        }
        
        
        
    }
	