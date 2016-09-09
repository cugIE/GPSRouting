var userTeamid = getQueryString('userTeamid');


window.onload=function()
{
	if(userTeamid>2){
		$(".datagrid-toolbar").hide();
	}	
};
/*var p = $('#people-data').datagrid('getPager'); 
$(p).pagination({ 
    pageSize: 10,//每页显示的记录条数，默认为10 
    pageList: [5,10,15],//可以设置每页记录条数的列表 
    beforePageText: '第',//页数文本框前显示的汉字 
    afterPageText: '页    共 {pages} 页', 
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
    onBeforeRefresh:function(){
        $(this).pagination('loading');
        alert('before refresh');
        $(this).pagination('loaded');
    } 
}); */


function hideTool(){
	$(".datagrid-toolbar").hide();
	
};

function newUser(){
	$('#dlg-people').dialog('open').dialog('setTitle','添加人员');
	/*$('#fm-people').form('clear');*/
	url = 'ManagePeopleServlet?action=add';
};

function saveUser() {
    $('#fm-people').form('submit',{
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
                $('#dlg-people').dialog('close');		// close the dialog
                $('#people-data').datagrid('reload');	// reload the user data
            }
        }
    });
}
function addTab(title,url){
    parent.addTab(title,url);
};
	    
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
            $('#dlg-people').dialog('open').dialog('setTitle','修改人员信息');
            $('#fm-people').form('load',row);
         //   url = 'ChangeRegionServlet?region_id='+row.id;
            url='ManagePeopleServlet?action=update2&id='+row.id;
        }
    }
	
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }